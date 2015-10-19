package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.analytics.SessionEvent;
import com.newrelic.agent.android.harvest.type.Harvestable;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.ActivityTrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Harvest {
    private static final boolean DISABLE_ACTIVITY_TRACE_LIMITS_FOR_DEBUGGING = false;
    private static final HarvestableCache activityTraceCache;
    protected static Harvest instance;
    private static final AgentLog log;
    private static final Collection<HarvestLifecycleAware> unregisteredLifecycleListeners;
    private HarvestConfiguration configuration;
    private HarvestConnection harvestConnection;
    protected HarvestData harvestData;
    private HarvestDataValidator harvestDataValidator;
    private HarvestTimer harvestTimer;
    private Harvester harvester;

    public Harvest() {
        this.configuration = HarvestConfiguration.getDefaultHarvestConfiguration();
    }

    static {
        log = AgentLogManager.getAgentLog();
        instance = new Harvest();
        unregisteredLifecycleListeners = new ArrayList();
        activityTraceCache = new HarvestableCache();
    }

    public static void initialize(AgentConfiguration agentConfiguration) {
        instance.initializeHarvester(agentConfiguration);
        registerUnregisteredListeners();
        addHarvestListener(StatsEngine.get());
    }

    public void initializeHarvester(AgentConfiguration agentConfiguration) {
        createHarvester();
        this.harvester.setAgentConfiguration(agentConfiguration);
        this.harvester.setConfiguration(instance.getConfiguration());
        flushHarvestableCaches();
    }

    public static void setPeriod(long j) {
        instance.getHarvestTimer().setPeriod(j);
    }

    public static void start() {
        instance.getHarvestTimer().start();
    }

    public static void stop() {
        instance.getHarvestTimer().stop();
    }

    public static void harvestNow() {
        if (isInitialized()) {
            StatsEngine.get().sampleTimeMs("Session/Duration", getMillisecondsSinceSessionStart());
            log.debug("Harvest.harvestNow - Generating sessionDuration attribute with value " + getSecondsSinceSessionStart());
            AnalyticsControllerImpl instance = AnalyticsControllerImpl.getInstance();
            instance.setAttribute(AnalyticAttribute.SESSION_DURATION_ATTRIBUTE, getSecondsSinceSessionStart());
            log.debug("Harvest.harvestNow - Generating session event.");
            instance.addEvent(new SessionEvent());
            instance.getEventManager().shutdown();
            instance.getHarvestTimer().tickNow();
        }
    }

    public static void setInstance(Harvest harvest) {
        instance = harvest;
    }

    public void createHarvester() {
        this.harvestConnection = new HarvestConnection();
        this.harvestData = new HarvestData();
        this.harvester = new Harvester();
        this.harvester.setHarvestConnection(this.harvestConnection);
        this.harvester.setHarvestData(this.harvestData);
        this.harvestTimer = new HarvestTimer(this.harvester);
        this.harvestDataValidator = new HarvestDataValidator();
        addHarvestListener(this.harvestDataValidator);
    }

    public void shutdownHarvester() {
        this.harvestTimer.shutdown();
        this.harvestTimer = null;
        this.harvester = null;
        this.harvestConnection = null;
        this.harvestData = null;
    }

    public static void shutdown() {
        if (isInitialized()) {
            stop();
            instance.shutdownHarvester();
        }
    }

    public static void addHttpError(HttpError httpError) {
        if (instance.shouldCollectNetworkErrors() && !isDisabled()) {
            HttpErrors httpErrors = instance.getHarvestData().getHttpErrors();
            instance.getHarvester().expireHttpErrors();
            int error_limit = instance.getConfiguration().getError_limit();
            if (httpErrors.count() >= error_limit) {
                StatsEngine.get().inc("Supportability/AgentHealth/ErrorsDropped");
                log.debug("Maximum number of HTTP errors (" + error_limit + ") reached. HTTP Error dropped.");
                return;
            }
            httpErrors.addHttpError(httpError);
        }
    }

    public static void addHttpTransaction(HttpTransaction httpTransaction) {
        if (!isDisabled()) {
            HttpTransactions httpTransactions = instance.getHarvestData().getHttpTransactions();
            instance.getHarvester().expireHttpTransactions();
            int report_max_transaction_count = instance.getConfiguration().getReport_max_transaction_count();
            if (httpTransactions.count() >= report_max_transaction_count) {
                StatsEngine.get().inc("Supportability/AgentHealth/TransactionsDropped");
                log.debug("Maximum number of transactions (" + report_max_transaction_count + ") reached. HTTP Transaction dropped.");
                return;
            }
            httpTransactions.add(httpTransaction);
        }
    }

    public static void addActivityTrace(ActivityTrace activityTrace) {
        if (!isDisabled()) {
            if (!isInitialized()) {
                activityTraceCache.add(activityTrace);
            } else if (activityTrace.rootTrace == null) {
                log.error("Activity trace is lacking a root trace!");
            } else if (activityTrace.rootTrace.childExclusiveTime == 0) {
                log.error("Total trace exclusive time is zero. Ignoring trace " + activityTrace.rootTrace.displayName);
            } else {
                double d = ((double) activityTrace.rootTrace.childExclusiveTime) / 1000.0d;
                if (d < instance.getConfiguration().getActivity_trace_min_utilization()) {
                    StatsEngine.get().inc("Supportability/AgentHealth/IgnoredTraces");
                    log.debug("Total trace exclusive time is too low (" + d + "). Ignoring trace " + activityTrace.rootTrace.displayName);
                    return;
                }
                ActivityTraces activityTraces = instance.getHarvestData().getActivityTraces();
                ActivityTraceConfiguration activityTraceConfiguration = instance.getActivityTraceConfiguration();
                instance.getHarvester().expireActivityTraces();
                if (activityTraces.count() >= activityTraceConfiguration.getMaxTotalTraceCount()) {
                    log.debug("Activity trace limit of " + activityTraceConfiguration.getMaxTotalTraceCount() + " exceeded. Ignoring trace: " + activityTrace.toJsonString());
                    return;
                }
                log.debug("Adding activity trace: " + activityTrace.toJsonString());
                activityTraces.add(activityTrace);
            }
        }
    }

    public static void addMetric(Metric metric) {
        if (!isDisabled() && isInitialized()) {
            instance.getHarvestData().getMetrics().addMetric(metric);
        }
    }

    public static void addAgentHealthException(AgentHealthException agentHealthException) {
        if (!isDisabled() && isInitialized()) {
            instance.getHarvestData().getAgentHealth().addException(agentHealthException);
        }
    }

    public static void addHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            log.error("Harvest: Argument to addHarvestListener cannot be null.");
        } else if (isInitialized()) {
            instance.getHarvester().addHarvestListener(harvestLifecycleAware);
        } else if (!isUnregisteredListener(harvestLifecycleAware)) {
            addUnregisteredListener(harvestLifecycleAware);
        }
    }

    public static void removeHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            log.error("Harvest: Argument to removeHarvestListener cannot be null.");
        } else if (isInitialized()) {
            instance.getHarvester().removeHarvestListener(harvestLifecycleAware);
        } else if (isUnregisteredListener(harvestLifecycleAware)) {
            removeUnregisteredListener(harvestLifecycleAware);
        }
    }

    public static boolean isInitialized() {
        return instance.getHarvester() != null;
    }

    public static int getActivityTraceCacheSize() {
        return activityTraceCache.getSize();
    }

    public static long getMillisSinceStart() {
        return instance.getHarvestTimer().timeSinceStart();
    }

    public static boolean shouldCollectActivityTraces() {
        if (isDisabled()) {
            return false;
        }
        if (!isInitialized()) {
            return true;
        }
        ActivityTraceConfiguration activityTraceConfiguration = instance.getActivityTraceConfiguration();
        if (activityTraceConfiguration == null || activityTraceConfiguration.getMaxTotalTraceCount() > 0) {
            return true;
        }
        return false;
    }

    private void flushHarvestableCaches() {
        try {
            flushActivityTraceCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void flushActivityTraceCache() {
        for (Harvestable harvestable : activityTraceCache.flush()) {
            addActivityTrace((ActivityTrace) harvestable);
        }
    }

    private static void addUnregisteredListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware != null) {
            synchronized (unregisteredLifecycleListeners) {
                unregisteredLifecycleListeners.add(harvestLifecycleAware);
            }
        }
    }

    private static void removeUnregisteredListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware != null) {
            synchronized (unregisteredLifecycleListeners) {
                unregisteredLifecycleListeners.remove(harvestLifecycleAware);
            }
        }
    }

    private static void registerUnregisteredListeners() {
        for (HarvestLifecycleAware addHarvestListener : unregisteredLifecycleListeners) {
            addHarvestListener(addHarvestListener);
        }
        unregisteredLifecycleListeners.clear();
    }

    private static boolean isUnregisteredListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            return false;
        }
        return unregisteredLifecycleListeners.contains(harvestLifecycleAware);
    }

    private HarvestTimer getHarvestTimer() {
        return this.harvestTimer;
    }

    public static Harvest getInstance() {
        return instance;
    }

    protected Harvester getHarvester() {
        return this.harvester;
    }

    public HarvestData getHarvestData() {
        return this.harvestData;
    }

    public HarvestConfiguration getConfiguration() {
        return this.configuration;
    }

    public HarvestConnection getHarvestConnection() {
        return this.harvestConnection;
    }

    public void setHarvestConnection(HarvestConnection harvestConnection) {
        this.harvestConnection = harvestConnection;
    }

    public boolean shouldCollectNetworkErrors() {
        return this.configuration.isCollect_network_errors();
    }

    public void setConfiguration(HarvestConfiguration harvestConfiguration) {
        this.configuration.reconfigure(harvestConfiguration);
        this.harvestTimer.setPeriod(TimeUnit.MILLISECONDS.convert((long) this.configuration.getData_report_period(), TimeUnit.SECONDS));
        this.harvestConnection.setServerTimestamp(this.configuration.getServer_timestamp());
        this.harvestData.setDataToken(this.configuration.getDataToken());
        this.harvester.setConfiguration(this.configuration);
    }

    public void setConnectInformation(ConnectInformation connectInformation) {
        this.harvestConnection.setConnectInformation(connectInformation);
        this.harvestData.setDeviceInformation(connectInformation.getDeviceInformation());
    }

    public static void setHarvestConfiguration(HarvestConfiguration harvestConfiguration) {
        if (isInitialized()) {
            log.debug("Harvest Configuration: " + harvestConfiguration);
            instance.setConfiguration(harvestConfiguration);
            return;
        }
        log.error("Cannot configure Harvester before initialization.");
        new Exception().printStackTrace();
    }

    public static HarvestConfiguration getHarvestConfiguration() {
        if (isInitialized()) {
            return instance.getConfiguration();
        }
        return HarvestConfiguration.getDefaultHarvestConfiguration();
    }

    public static void setHarvestConnectInformation(ConnectInformation connectInformation) {
        if (isInitialized()) {
            log.debug("Setting Harvest connect information: " + connectInformation);
            instance.setConnectInformation(connectInformation);
            return;
        }
        log.error("Cannot configure Harvester before initialization.");
        new Exception().printStackTrace();
    }

    public static boolean isDisabled() {
        if (isInitialized()) {
            return instance.getHarvester().isDisabled();
        }
        return false;
    }

    protected ActivityTraceConfiguration getActivityTraceConfiguration() {
        return this.configuration.getAt_capture();
    }

    public static long getMillisecondsSinceSessionStart() {
        if (getInstance() == null || getInstance().getHarvestTimer() == null) {
            return 0;
        }
        return instance.getHarvestTimer().timeSinceStart();
    }

    public static float getSecondsSinceSessionStart() {
        if (getInstance() == null || getInstance().getHarvestTimer() == null) {
            return 0.0f;
        }
        return ((float) instance.getHarvestTimer().timeSinceStart()) / 1000.0f;
    }
}
