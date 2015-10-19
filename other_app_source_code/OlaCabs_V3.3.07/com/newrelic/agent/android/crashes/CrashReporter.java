package com.newrelic.agent.android.crashes;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.harvest.crash.Crash;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.stats.TicToc;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

public class CrashReporter {
    private static final String CRASH_COLLECTOR_PATH = "/mobile_crash";
    private static final int CRASH_COLLECTOR_TIMEOUT = 5000;
    private static AgentConfiguration agentConfiguration;
    protected static final AtomicBoolean initialized;
    protected static CrashReporter instance;
    private CrashStore crashStore;
    protected boolean isEnabled;
    private final AgentLog log;
    private java.lang.Thread.UncaughtExceptionHandler previousExceptionHandler;
    private boolean reportCrashes;

    private class CrashSender implements Runnable {
        private final Crash crash;

        CrashSender(Crash crash) {
            this.crash = crash;
        }

        public void run() {
            HttpURLConnection httpURLConnection;
            try {
                httpURLConnection = (HttpURLConnection) new URL((CrashReporter.agentConfiguration.useSsl() ? "https://" : "http://") + CrashReporter.agentConfiguration.getCrashCollectorHost() + CrashReporter.CRASH_COLLECTOR_PATH).openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setChunkedStreamingMode(0);
                httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE);
                httpURLConnection.setConnectTimeout(CrashReporter.CRASH_COLLECTOR_TIMEOUT);
                OutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(this.crash.toJsonString().getBytes());
                bufferedOutputStream.close();
                if (httpURLConnection.getResponseCode() == HttpStatus.SC_OK) {
                    CrashReporter.this.log.info("Crash " + this.crash.getUuid().toString() + " successfully submitted.");
                    CrashReporter.this.crashStore.delete(this.crash);
                } else {
                    CrashReporter.this.log.error("Something went wrong while submitting a crash (will try again later) - Response code " + httpURLConnection.getResponseCode());
                }
                httpURLConnection.disconnect();
            } catch (Throwable e) {
                CrashReporter.this.log.error("Unable to report crash to New Relic, will try again later.", e);
            } catch (Throwable th) {
                httpURLConnection.disconnect();
            }
        }
    }

    private class UncaughtExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
        private final AtomicBoolean handledException;

        private UncaughtExceptionHandler() {
            this.handledException = new AtomicBoolean(false);
        }

        public void uncaughtException(Thread thread, Throwable th) {
            if (this.handledException.compareAndSet(false, true)) {
                try {
                    if (CrashReporter.instance.isEnabled && FeatureFlag.featureEnabled(FeatureFlag.CrashReporting)) {
                        TicToc ticToc = new TicToc();
                        ticToc.tic();
                        CrashReporter.this.log.debug("A crash has been detected in " + thread.getStackTrace()[0].getClassName() + " and will be reported ASAP.");
                        CrashReporter.this.log.debug("Analytics data is currently " + (CrashReporter.agentConfiguration.getEnableAnalyticsEvents() ? "enabled " : "disabled"));
                        CrashReporter.this.reportCrash(new Crash(th, AnalyticsControllerImpl.getInstance().getSessionAttributes(), AnalyticsControllerImpl.getInstance().getEventManager().getQueuedEvents(), CrashReporter.agentConfiguration.getEnableAnalyticsEvents()), true);
                        CrashReporter.this.log.debug("Crash collection took " + ticToc.toc() + "ms");
                        chainExceptionHandler(thread, th);
                        return;
                    }
                    CrashReporter.this.log.debug("A crash has been detected but crash reporting is disabled!");
                    chainExceptionHandler(thread, th);
                    return;
                } catch (Throwable th2) {
                    CrashReporter.this.log.error("Error encountered while preparing crash for New Relic!", th2);
                    chainExceptionHandler(thread, th);
                    return;
                }
            }
            StatsEngine.get().inc("Supportability/AgentHealth/Recursion/UncaughtExceptionHandler");
        }

        private void chainExceptionHandler(Thread thread, Throwable th) {
            if (CrashReporter.this.previousExceptionHandler != null) {
                CrashReporter.this.log.debug("Chaining crash reporting duties to " + CrashReporter.this.previousExceptionHandler.getClass().getSimpleName());
                CrashReporter.this.previousExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    public CrashReporter() {
        this.log = AgentLogManager.getAgentLog();
        this.isEnabled = false;
        this.reportCrashes = true;
    }

    static {
        instance = new CrashReporter();
        initialized = new AtomicBoolean(false);
    }

    public static void initialize(AgentConfiguration agentConfiguration) {
        if (initialized.compareAndSet(false, true)) {
            agentConfiguration = agentConfiguration;
            instance.isEnabled = agentConfiguration.getReportCrashes();
            instance.crashStore = agentConfiguration.getCrashStore();
            instance.reportSavedCrashes();
            if (instance.isEnabled) {
                instance.installCrashHandler();
            }
        }
    }

    public static AgentConfiguration getAgentConfiguration() {
        return agentConfiguration;
    }

    public UncaughtExceptionHandler getHandler() {
        return new UncaughtExceptionHandler();
    }

    public static UncaughtExceptionHandler getInstanceHandler() {
        return instance.getHandler();
    }

    public static void setReportCrashes(boolean z) {
        instance.reportCrashes = z;
    }

    public static int getStoredCrashCount() {
        return instance.crashStore.count();
    }

    public static List<Crash> fetchAllCrashes() {
        return instance.crashStore.fetchAll();
    }

    public static void clear() {
        instance.crashStore.clear();
    }

    private void installCrashHandler() {
        java.lang.Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == null) {
            this.log.debug("Installing New Relic crash handler.");
        } else if (defaultUncaughtExceptionHandler instanceof UncaughtExceptionHandler) {
            this.log.debug("New Relic crash handler already installed.");
            return;
        } else {
            this.previousExceptionHandler = defaultUncaughtExceptionHandler;
            this.log.debug("Installing New Relic crash handler and chaining " + this.previousExceptionHandler.getClass().getName() + ".");
        }
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    }

    private void reportSavedCrashes() {
        for (Crash reportCrash : this.crashStore.fetchAll()) {
            reportCrash(reportCrash, false);
        }
    }

    private void reportCrash(Crash crash, boolean z) {
        this.crashStore.store(crash);
        if (this.reportCrashes) {
            Thread thread = new Thread(new CrashSender(crash));
            thread.start();
            if (z) {
                try {
                    thread.join();
                } catch (Throwable e) {
                    this.log.error("Exception caught while waiting to send crash", e);
                }
            }
        }
    }
}
