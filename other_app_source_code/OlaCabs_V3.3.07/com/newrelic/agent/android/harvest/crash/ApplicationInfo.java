package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ApplicationInfo extends HarvestableObject {
    private String applicationBuild;
    private String applicationName;
    private String applicationVersion;
    private String bundleId;
    private int processId;

    public ApplicationInfo() {
        this.applicationName = Trace.NULL;
        this.applicationVersion = Trace.NULL;
        this.applicationBuild = Trace.NULL;
        this.bundleId = Trace.NULL;
        this.processId = 0;
    }

    public ApplicationInfo(ApplicationInformation applicationInformation) {
        this.applicationName = Trace.NULL;
        this.applicationVersion = Trace.NULL;
        this.applicationBuild = Trace.NULL;
        this.bundleId = Trace.NULL;
        this.processId = 0;
        this.applicationName = applicationInformation.getAppName();
        this.applicationVersion = applicationInformation.getAppVersion();
        this.applicationBuild = applicationInformation.getAppBuild();
        this.bundleId = applicationInformation.getPackageId();
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(AnalyticAttribute.APP_NAME_ATTRIBUTE, new JsonPrimitive(this.applicationName));
        jsonObject.add("appVersion", new JsonPrimitive(this.applicationVersion));
        jsonObject.add("appBuild", new JsonPrimitive(this.applicationBuild));
        jsonObject.add("bundleId", new JsonPrimitive(this.bundleId));
        jsonObject.add("processId", new JsonPrimitive(Integer.valueOf(this.processId)));
        return jsonObject;
    }

    public static ApplicationInfo newFromJson(JsonObject jsonObject) {
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.applicationName = jsonObject.get(AnalyticAttribute.APP_NAME_ATTRIBUTE).getAsString();
        applicationInfo.applicationVersion = jsonObject.get("appVersion").getAsString();
        applicationInfo.applicationBuild = jsonObject.get("appBuild").getAsString();
        applicationInfo.bundleId = jsonObject.get("bundleId").getAsString();
        applicationInfo.processId = jsonObject.get("processId").getAsInt();
        return applicationInfo;
    }
}
