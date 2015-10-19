package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import com.newrelic.agent.android.activity.config.ActivityTraceConfigurationDeserializer;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.analytics.EventManager;
import com.newrelic.agent.android.harvest.HarvestResponse.Code;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.com.google.gson.GsonBuilder;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Harvester {
    private AgentConfiguration agentConfiguration;
    private HarvestConfiguration configuration;
    private HarvestConnection harvestConnection;
    private HarvestData harvestData;
    private final Collection<HarvestLifecycleAware> harvestListeners;
    private final AgentLog log;
    private State state;
    protected boolean stateChanged;

    /* renamed from: com.newrelic.agent.android.harvest.Harvester.1 */
    static /* synthetic */ class C07341 {
        static final /* synthetic */ int[] f8901xa09b1364;
        static final /* synthetic */ int[] $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State;

        static {
            $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State = new int[State.values().length];
            try {
                $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[State.DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            f8901xa09b1364 = new int[Code.values().length];
            try {
                f8901xa09b1364[Code.UNAUTHORIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8901xa09b1364[Code.INVALID_AGENT_ID.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f8901xa09b1364[Code.FORBIDDEN.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f8901xa09b1364[Code.UNSUPPORTED_MEDIA_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f8901xa09b1364[Code.ENTITY_TOO_LARGE.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    protected enum State {
        UNINITIALIZED,
        DISCONNECTED,
        CONNECTED,
        DISABLED
    }

    public Harvester() {
        this.log = AgentLogManager.getAgentLog();
        this.state = State.UNINITIALIZED;
        this.configuration = HarvestConfiguration.getDefaultHarvestConfiguration();
        this.harvestListeners = new ArrayList();
    }

    public void start() {
        fireOnHarvestStart();
    }

    public void stop() {
        fireOnHarvestStop();
    }

    protected void uninitialized() {
        if (this.agentConfiguration == null) {
            this.log.error("Agent configuration unavailable.");
            return;
        }
        if (Agent.getImpl().updateSavedConnectInformation()) {
            configureHarvester(HarvestConfiguration.getDefaultHarvestConfiguration());
            this.harvestData.getDataToken().clear();
        }
        Harvest.setHarvestConnectInformation(new ConnectInformation(Agent.getApplicationInformation(), Agent.getDeviceInformation()));
        this.harvestConnection.setApplicationToken(this.agentConfiguration.getApplicationToken());
        this.harvestConnection.setCollectorHost(this.agentConfiguration.getCollectorHost());
        this.harvestConnection.useSsl(this.agentConfiguration.useSsl());
        transition(State.DISCONNECTED);
        execute();
    }

    protected void disconnected() {
        if (this.configuration == null) {
            configureHarvester(HarvestConfiguration.getDefaultHarvestConfiguration());
        }
        if (this.harvestData.isValid()) {
            this.log.verbose("Skipping connect call, saved state is available: " + this.harvestData.getDataToken());
            StatsEngine.get().sample("Session/Start", br.DEFAULT_BACKOFF_MULT);
            fireOnHarvestConnected();
            transition(State.CONNECTED);
            execute();
            return;
        }
        this.log.info("Connecting, saved state is not available: " + this.harvestData.getDataToken());
        HarvestResponse sendConnect = this.harvestConnection.sendConnect();
        if (sendConnect == null) {
            this.log.error("Unable to connect to the Collector.");
        } else if (sendConnect.isOK()) {
            HarvestConfiguration parseHarvesterConfiguration = parseHarvesterConfiguration(sendConnect);
            if (parseHarvesterConfiguration == null) {
                this.log.error("Unable to configure Harvester using Collector configuration.");
                return;
            }
            configureHarvester(parseHarvesterConfiguration);
            StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", sendConnect.getResponseTime());
            fireOnHarvestConnected();
            transition(State.CONNECTED);
        } else {
            this.log.debug("Harvest connect response: " + sendConnect.getResponseCode());
            switch (C07341.f8901xa09b1364[sendConnect.getResponseCode().ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.harvestData.getDataToken().clear();
                    fireOnHarvestDisconnected();
                    return;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    if (!sendConnect.isDisableCommand()) {
                        this.log.error("Unexpected Collector response: FORBIDDEN");
                        break;
                    }
                    this.log.error("Collector has commanded Agent to disable.");
                    fireOnHarvestDisabled();
                    transition(State.DISABLED);
                    return;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    this.log.error("Invalid ConnectionInformation was sent to the Collector.");
                    break;
                default:
                    this.log.error("An unknown error occurred when connecting to the Collector.");
                    break;
            }
            fireOnHarvestError();
        }
    }

    protected void connected() {
        this.log.info("Harvester: connected");
        this.log.info("Harvester: Sending " + this.harvestData.getHttpTransactions().count() + " HTTP transactions.");
        this.log.info("Harvester: Sending " + this.harvestData.getHttpErrors().count() + " HTTP errors.");
        this.log.info("Harvester: Sending " + this.harvestData.getActivityTraces().count() + " activity traces.");
        this.harvestData.setAnalyticsEnabled(this.agentConfiguration.getEnableAnalyticsEvents());
        if (this.agentConfiguration.getEnableAnalyticsEvents() && FeatureFlag.featureEnabled(FeatureFlag.AnalyticsEvents)) {
            EventManager eventManager = AnalyticsControllerImpl.getInstance().getEventManager();
            if (eventManager.isTransmitRequired()) {
                Set hashSet = new HashSet();
                hashSet.addAll(AnalyticsControllerImpl.getInstance().getSystemAttributes());
                hashSet.addAll(AnalyticsControllerImpl.getInstance().getUserAttributes());
                this.harvestData.setSessionAttributes(hashSet);
                this.log.info("Harvester: Sending " + this.harvestData.getSessionAttributes().size() + " session attributes.");
                this.harvestData.setAnalyticsEvents(eventManager.getQueuedEvents());
                eventManager.empty();
            }
            this.log.info("Harvester: Sending " + this.harvestData.getAnalyticsEvents().size() + " analytics events.");
        }
        HarvestResponse sendData = this.harvestConnection.sendData(this.harvestData);
        if (sendData == null || sendData.isUnknown()) {
            fireOnHarvestSendFailed();
            return;
        }
        this.harvestData.reset();
        StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", sendData.getResponseTime());
        this.log.debug("Harvest data response: " + sendData.getResponseCode());
        this.log.debug("Harvest data response status code: " + sendData.getStatusCode());
        this.log.debug("Harvest data response BODY: " + sendData.getResponseBody());
        if (sendData.isError()) {
            fireOnHarvestError();
            switch (C07341.f8901xa09b1364[sendData.getResponseCode().ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.harvestData.getDataToken().clear();
                    transition(State.DISCONNECTED);
                    return;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    if (sendData.isDisableCommand()) {
                        this.log.error("Collector has commanded Agent to disable.");
                        transition(State.DISABLED);
                        return;
                    }
                    this.log.error("Unexpected Collector response: FORBIDDEN");
                    transition(State.DISCONNECTED);
                    return;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    this.log.error("Invalid ConnectionInformation was sent to the Collector.");
                    return;
                default:
                    this.log.error("An unknown error occurred when connecting to the Collector.");
                    return;
            }
        }
        HarvestConfiguration parseHarvesterConfiguration = parseHarvesterConfiguration(sendData);
        if (parseHarvesterConfiguration == null) {
            this.log.error("Unable to configure Harvester using Collector configuration.");
            return;
        }
        configureHarvester(parseHarvesterConfiguration);
        fireOnHarvestComplete();
    }

    protected void disabled() {
        Harvest.stop();
        fireOnHarvestDisabled();
    }

    protected void execute() {
        this.log.debug("Harvester state: " + this.state);
        this.stateChanged = false;
        try {
            expireHarvestData();
            switch (C07341.$SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[this.state.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    uninitialized();
                    return;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    fireOnHarvestBefore();
                    disconnected();
                    return;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    fireOnHarvestBefore();
                    fireOnHarvest();
                    fireOnHarvestFinalize();
                    TaskQueue.synchronousDequeue();
                    connected();
                    return;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    disabled();
                    return;
                default:
                    throw new IllegalStateException();
            }
        } catch (Exception e) {
            this.log.error("Exception encountered while attempting to harvest", e);
            AgentHealth.noticeException(e);
        }
        this.log.error("Exception encountered while attempting to harvest", e);
        AgentHealth.noticeException(e);
    }

    protected void transition(State state) {
        if (this.stateChanged) {
            this.log.debug("Ignoring multiple transition: " + state);
        } else if (this.state != state) {
            switch (C07341.$SwitchMap$com$newrelic$agent$android$harvest$Harvester$State[this.state.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    if (!stateIn(state, State.DISCONNECTED, state, State.CONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    if (!stateIn(state, State.UNINITIALIZED, State.CONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    if (!stateIn(state, State.DISCONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
            changeState(state);
        }
    }

    private HarvestConfiguration parseHarvesterConfiguration(HarvestResponse harvestResponse) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ActivityTraceConfiguration.class, new ActivityTraceConfigurationDeserializer());
        try {
            return (HarvestConfiguration) gsonBuilder.create().fromJson(harvestResponse.getResponseBody(), HarvestConfiguration.class);
        } catch (Exception e) {
            this.log.error("Unable to parse collector configuration: " + e.getMessage());
            AgentHealth.noticeException(e);
            return null;
        }
    }

    private void configureHarvester(HarvestConfiguration harvestConfiguration) {
        this.configuration.reconfigure(harvestConfiguration);
        this.harvestData.setDataToken(this.configuration.getDataToken());
        Harvest.setHarvestConfiguration(this.configuration);
    }

    private void changeState(State state) {
        this.log.debug("Harvester changing state: " + this.state + " -> " + state);
        if (this.state == State.CONNECTED) {
            if (state == State.DISCONNECTED) {
                fireOnHarvestDisconnected();
            } else if (state == State.DISABLED) {
                fireOnHarvestDisabled();
            }
        }
        this.state = state;
        this.stateChanged = true;
    }

    private boolean stateIn(State state, State... stateArr) {
        for (State state2 : stateArr) {
            if (state == state2) {
                return true;
            }
        }
        return false;
    }

    public State getCurrentState() {
        return this.state;
    }

    public boolean isDisabled() {
        return State.DISABLED == this.state;
    }

    public void addHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        if (harvestLifecycleAware == null) {
            this.log.error("Can't add null harvest listener");
            new Exception().printStackTrace();
            return;
        }
        synchronized (this.harvestListeners) {
            if (this.harvestListeners.contains(harvestLifecycleAware)) {
                return;
            }
            this.harvestListeners.add(harvestLifecycleAware);
        }
    }

    public void removeHarvestListener(HarvestLifecycleAware harvestLifecycleAware) {
        synchronized (this.harvestListeners) {
            if (this.harvestListeners.contains(harvestLifecycleAware)) {
                this.harvestListeners.remove(harvestLifecycleAware);
                return;
            }
        }
    }

    public void expireHarvestData() {
        expireHttpErrors();
        expireHttpTransactions();
        expireActivityTraces();
    }

    public void expireHttpErrors() {
        HttpErrors httpErrors = this.harvestData.getHttpErrors();
        synchronized (httpErrors) {
            Collection<HttpError> arrayList = new ArrayList();
            long currentTimeMillis = System.currentTimeMillis();
            long reportMaxTransactionAgeMilliseconds = this.configuration.getReportMaxTransactionAgeMilliseconds();
            for (HttpError httpError : httpErrors.getHttpErrors()) {
                if (httpError.getTimestamp().longValue() < currentTimeMillis - reportMaxTransactionAgeMilliseconds) {
                    this.log.debug("HttpError too old, purging: " + httpError);
                    arrayList.add(httpError);
                }
            }
            for (HttpError httpError2 : arrayList) {
                httpErrors.removeHttpError(httpError2);
            }
        }
    }

    public void expireHttpTransactions() {
        HttpTransactions httpTransactions = this.harvestData.getHttpTransactions();
        synchronized (httpTransactions) {
            Collection<HttpTransaction> arrayList = new ArrayList();
            long currentTimeMillis = System.currentTimeMillis();
            long reportMaxTransactionAgeMilliseconds = this.configuration.getReportMaxTransactionAgeMilliseconds();
            for (HttpTransaction httpTransaction : httpTransactions.getHttpTransactions()) {
                if (httpTransaction.getTimestamp().longValue() < currentTimeMillis - reportMaxTransactionAgeMilliseconds) {
                    this.log.debug("HttpTransaction too old, purging: " + httpTransaction);
                    arrayList.add(httpTransaction);
                }
            }
            for (HttpTransaction httpTransaction2 : arrayList) {
                httpTransactions.remove(httpTransaction2);
            }
        }
    }

    public void expireActivityTraces() {
        ActivityTraces activityTraces = this.harvestData.getActivityTraces();
        synchronized (activityTraces) {
            Collection<ActivityTrace> arrayList = new ArrayList();
            long activity_trace_max_report_attempts = (long) this.configuration.getActivity_trace_max_report_attempts();
            for (ActivityTrace activityTrace : activityTraces.getActivityTraces()) {
                if (activityTrace.getReportAttemptCount() >= activity_trace_max_report_attempts) {
                    this.log.debug("ActivityTrace has had " + activityTrace.getReportAttemptCount() + " report attempts, purging: " + activityTrace);
                    arrayList.add(activityTrace);
                }
            }
            for (ActivityTrace activityTrace2 : arrayList) {
                activityTraces.remove(activityTrace2);
            }
        }
    }

    public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
        this.agentConfiguration = agentConfiguration;
    }

    public void setHarvestConnection(HarvestConnection harvestConnection) {
        this.harvestConnection = harvestConnection;
    }

    public HarvestConnection getHarvestConnection() {
        return this.harvestConnection;
    }

    public void setHarvestData(HarvestData harvestData) {
        this.harvestData = harvestData;
    }

    public HarvestData getHarvestData() {
        return this.harvestData;
    }

    private void fireOnHarvestBefore() {
        try {
            for (HarvestLifecycleAware onHarvestBefore : getHarvestListeners()) {
                onHarvestBefore.onHarvestBefore();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestBefore", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestStart() {
        try {
            for (HarvestLifecycleAware onHarvestStart : getHarvestListeners()) {
                onHarvestStart.onHarvestStart();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestStart", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestStop() {
        try {
            for (HarvestLifecycleAware onHarvestStop : getHarvestListeners()) {
                onHarvestStop.onHarvestStop();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestStop", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvest() {
        try {
            for (HarvestLifecycleAware onHarvest : getHarvestListeners()) {
                onHarvest.onHarvest();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvest", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestFinalize() {
        try {
            for (HarvestLifecycleAware onHarvestFinalize : getHarvestListeners()) {
                onHarvestFinalize.onHarvestFinalize();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestFinalize", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestDisabled() {
        try {
            for (HarvestLifecycleAware onHarvestDisabled : getHarvestListeners()) {
                onHarvestDisabled.onHarvestDisabled();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestDisabled", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestDisconnected() {
        try {
            for (HarvestLifecycleAware onHarvestDisconnected : getHarvestListeners()) {
                onHarvestDisconnected.onHarvestDisconnected();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestDisconnected", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestError() {
        try {
            for (HarvestLifecycleAware onHarvestError : getHarvestListeners()) {
                onHarvestError.onHarvestError();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestError", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestSendFailed() {
        try {
            for (HarvestLifecycleAware onHarvestSendFailed : getHarvestListeners()) {
                onHarvestSendFailed.onHarvestSendFailed();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestSendFailed", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestComplete() {
        try {
            for (HarvestLifecycleAware onHarvestComplete : getHarvestListeners()) {
                onHarvestComplete.onHarvestComplete();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestComplete", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestConnected() {
        try {
            for (HarvestLifecycleAware onHarvestConnected : getHarvestListeners()) {
                onHarvestConnected.onHarvestConnected();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestConnected", e);
            AgentHealth.noticeException(e);
        }
    }

    public void setConfiguration(HarvestConfiguration harvestConfiguration) {
        this.configuration = harvestConfiguration;
    }

    private Collection<HarvestLifecycleAware> getHarvestListeners() {
        return new ArrayList(this.harvestListeners);
    }
}
