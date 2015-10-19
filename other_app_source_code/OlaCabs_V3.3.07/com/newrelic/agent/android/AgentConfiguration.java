package com.newrelic.agent.android;

import com.newrelic.agent.android.analytics.AnalyticAttributeStore;
import com.newrelic.agent.android.crashes.CrashStore;
import java.util.UUID;

public class AgentConfiguration {
    private static final String DEFAULT_COLLECTOR_HOST = "mobile-collector.newrelic.com";
    private static final String DEFAULT_CRASH_COLLECTOR_HOST = "mobile-crash.newrelic.com";
    private AnalyticAttributeStore analyticAttributeStore;
    private String appName;
    private String applicationToken;
    private String collectorHost;
    private String crashCollectorHost;
    private CrashStore crashStore;
    private String customApplicationVersion;
    private String customBuildId;
    private boolean enableAnalyticsEvents;
    private boolean reportCrashes;
    private String sessionID;
    private boolean useLocationService;
    private boolean useSsl;

    public AgentConfiguration() {
        this.collectorHost = DEFAULT_COLLECTOR_HOST;
        this.crashCollectorHost = DEFAULT_CRASH_COLLECTOR_HOST;
        this.useSsl = true;
        this.reportCrashes = true;
        this.enableAnalyticsEvents = true;
        this.sessionID = UUID.randomUUID().toString();
        this.customApplicationVersion = null;
        this.customBuildId = null;
    }

    public String getApplicationToken() {
        return this.applicationToken;
    }

    public void setApplicationToken(String str) {
        this.applicationToken = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getCollectorHost() {
        return this.collectorHost;
    }

    public void setCollectorHost(String str) {
        this.collectorHost = str;
    }

    public String getCrashCollectorHost() {
        return this.crashCollectorHost;
    }

    public void setCrashCollectorHost(String str) {
        this.crashCollectorHost = str;
    }

    public boolean useSsl() {
        return this.useSsl;
    }

    public void setUseSsl(boolean z) {
        this.useSsl = z;
    }

    public boolean useLocationService() {
        return this.useLocationService;
    }

    public void setUseLocationService(boolean z) {
        this.useLocationService = z;
    }

    public boolean getReportCrashes() {
        return this.reportCrashes;
    }

    public void setReportCrashes(boolean z) {
        this.reportCrashes = z;
    }

    public CrashStore getCrashStore() {
        return this.crashStore;
    }

    public void setCrashStore(CrashStore crashStore) {
        this.crashStore = crashStore;
    }

    public AnalyticAttributeStore getAnalyticAttributeStore() {
        return this.analyticAttributeStore;
    }

    public void setAnalyticAttributeStore(AnalyticAttributeStore analyticAttributeStore) {
        this.analyticAttributeStore = analyticAttributeStore;
    }

    public boolean getEnableAnalyticsEvents() {
        return this.enableAnalyticsEvents;
    }

    public void setEnableAnalyticsEvents(boolean z) {
        this.enableAnalyticsEvents = z;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String str) {
        this.sessionID = str;
    }

    public String getCustomApplicationVersion() {
        return this.customApplicationVersion;
    }

    public void setCustomApplicationVersion(String str) {
        this.customApplicationVersion = str;
    }

    public String getCustomBuildIdentifier() {
        return this.customBuildId;
    }

    public void setCustomBuildIdentifier(String str) {
        this.customBuildId = str;
    }
}
