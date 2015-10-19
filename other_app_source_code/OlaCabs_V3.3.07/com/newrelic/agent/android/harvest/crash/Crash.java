package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentImpl;
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsEvent;
import com.newrelic.agent.android.crashes.CrashReporter;
import com.newrelic.agent.android.harvest.ActivityHistory;
import com.newrelic.agent.android.harvest.DataToken;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonParser;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.olacabs.customer.utils.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Crash extends HarvestableObject {
    public static final int PROTOCOL_VERSION = 1;
    private ActivityHistory activityHistory;
    private final boolean analyticsEnabled;
    private final String appToken;
    private ApplicationInfo applicationInfo;
    private final String buildId;
    private DeviceInfo deviceInfo;
    private Collection<AnalyticsEvent> events;
    private ExceptionInfo exceptionInfo;
    private Set<AnalyticAttribute> sessionAttributes;
    private List<ThreadInfo> threads;
    private final long timestamp;
    private final UUID uuid;

    public static String getBuildId() {
        return "5fdc30ba-079b-4436-b388-80d1c5a068f1";
    }

    public Crash(UUID uuid, String str, long j) {
        AgentImpl impl = Agent.getImpl();
        this.uuid = uuid;
        this.buildId = str;
        this.timestamp = j;
        this.appToken = CrashReporter.getAgentConfiguration().getApplicationToken();
        this.deviceInfo = new DeviceInfo(impl.getDeviceInformation(), impl.getEnvironmentInformation());
        this.applicationInfo = new ApplicationInfo(impl.getApplicationInformation());
        this.exceptionInfo = new ExceptionInfo();
        this.threads = new ArrayList();
        this.activityHistory = new ActivityHistory(new ArrayList());
        this.sessionAttributes = new HashSet();
        this.events = new HashSet();
        this.analyticsEnabled = false;
    }

    public Crash(Throwable th) {
        this(th, new HashSet(), new HashSet(), false);
    }

    public Crash(Throwable th, Set<AnalyticAttribute> set, Collection<AnalyticsEvent> collection, boolean z) {
        AgentImpl impl = Agent.getImpl();
        Throwable rootCause = getRootCause(th);
        this.uuid = new UUID(Util.getRandom().nextLong(), Util.getRandom().nextLong());
        this.buildId = getBuildId();
        this.timestamp = System.currentTimeMillis() / 1000;
        this.appToken = CrashReporter.getAgentConfiguration().getApplicationToken();
        this.deviceInfo = new DeviceInfo(impl.getDeviceInformation(), impl.getEnvironmentInformation());
        this.applicationInfo = new ApplicationInfo(impl.getApplicationInformation());
        this.exceptionInfo = new ExceptionInfo(rootCause);
        this.threads = ThreadInfo.extractThreads(rootCause);
        this.activityHistory = TraceMachine.getActivityHistory();
        this.sessionAttributes = set;
        this.events = collection;
        this.analyticsEnabled = z;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public ExceptionInfo getExceptionInfo() {
        return this.exceptionInfo;
    }

    public void setSessionAttributes(Set<AnalyticAttribute> set) {
        this.sessionAttributes = set;
    }

    public void setAnalyticsEvents(List<AnalyticsEvent> list) {
        this.events = list;
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("protocolVersion", new JsonPrimitive(Integer.valueOf(PROTOCOL_VERSION)));
        jsonObject.add("platform", new JsonPrimitive("Android"));
        jsonObject.add(AnalyticAttribute.UUID_ATTRIBUTE, new JsonPrimitive(this.uuid.toString()));
        jsonObject.add("buildId", new JsonPrimitive(this.buildId));
        jsonObject.add(Constants.PUSH_ACK_TIME, new JsonPrimitive(Long.valueOf(this.timestamp)));
        jsonObject.add("appToken", new JsonPrimitive(this.appToken));
        jsonObject.add("deviceInfo", this.deviceInfo.asJsonObject());
        jsonObject.add("appInfo", this.applicationInfo.asJsonObject());
        jsonObject.add("exception", this.exceptionInfo.asJsonObject());
        jsonObject.add("threads", getThreadsAsJson());
        jsonObject.add("activityHistory", this.activityHistory.asJsonArrayWithoutDuration());
        if (this.analyticsEnabled && FeatureFlag.featureEnabled(FeatureFlag.AnalyticsEvents)) {
            JsonElement jsonObject2 = new JsonObject();
            if (this.sessionAttributes != null) {
                for (AnalyticAttribute analyticAttribute : this.sessionAttributes) {
                    if (analyticAttribute.isStringAttribute()) {
                        jsonObject2.add(analyticAttribute.getName(), new JsonPrimitive(analyticAttribute.getStringValue()));
                    } else {
                        jsonObject2.add(analyticAttribute.getName(), new JsonPrimitive(Float.valueOf(analyticAttribute.getFloatValue())));
                    }
                }
            }
            jsonObject.add("sessionAttributes", jsonObject2);
            jsonObject2 = new JsonArray();
            if (this.events != null) {
                for (AnalyticsEvent asJsonObject : this.events) {
                    jsonObject2.add(asJsonObject.asJsonObject());
                }
            }
            jsonObject.add("analyticsEvents", jsonObject2);
        }
        DataToken dataToken = Harvest.getHarvestConfiguration().getDataToken();
        if (dataToken != null) {
            jsonObject.add("dataToken", dataToken.asJsonArray());
        }
        return jsonObject;
    }

    public static Crash crashFromJsonString(String str) {
        JsonObject asJsonObject = new JsonParser().parse(str).getAsJsonObject();
        String asString = asJsonObject.get(AnalyticAttribute.UUID_ATTRIBUTE).getAsString();
        Crash crash = new Crash(UUID.fromString(asString), asJsonObject.get("buildId").getAsString(), asJsonObject.get(Constants.PUSH_ACK_TIME).getAsLong());
        crash.deviceInfo = DeviceInfo.newFromJson(asJsonObject.get("deviceInfo").getAsJsonObject());
        crash.applicationInfo = ApplicationInfo.newFromJson(asJsonObject.get("appInfo").getAsJsonObject());
        crash.exceptionInfo = ExceptionInfo.newFromJson(asJsonObject.get("exception").getAsJsonObject());
        crash.threads = ThreadInfo.newListFromJson(asJsonObject.get("threads").getAsJsonArray());
        crash.activityHistory = ActivityHistory.newFromJson(asJsonObject.get("activityHistory").getAsJsonArray());
        return crash;
    }

    protected static Throwable getRootCause(Throwable th) {
        Throwable cause = th.getCause();
        return cause == null ? th : getRootCause(cause);
    }

    protected JsonArray getThreadsAsJson() {
        JsonArray jsonArray = new JsonArray();
        if (this.threads != null) {
            for (ThreadInfo asJsonObject : this.threads) {
                jsonArray.add(asJsonObject.asJsonObject());
            }
        }
        return jsonArray;
    }
}
