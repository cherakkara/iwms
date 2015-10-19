package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ApplicationInformation extends HarvestableArray {
    private String appBuild;
    private String appName;
    private String appVersion;
    private String packageId;

    public ApplicationInformation(String str, String str2, String str3, String str4) {
        this();
        this.appName = str;
        this.appVersion = str2;
        this.packageId = str3;
        this.appBuild = str4;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        notEmpty(this.appName);
        jsonArray.add(new JsonPrimitive(this.appName));
        notEmpty(this.appVersion);
        jsonArray.add(new JsonPrimitive(this.appVersion));
        notEmpty(this.packageId);
        jsonArray.add(new JsonPrimitive(this.packageId));
        return jsonArray;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppBuild(String str) {
        this.appBuild = str;
    }

    public String getAppBuild() {
        return this.appBuild;
    }

    public void setPackageId(String str) {
        this.packageId = str;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ApplicationInformation applicationInformation = (ApplicationInformation) obj;
        if (this.appName == null ? applicationInformation.appName != null : !this.appName.equals(applicationInformation.appName)) {
            return false;
        }
        if (this.appVersion == null ? applicationInformation.appVersion != null : !this.appVersion.equals(applicationInformation.appVersion)) {
            return false;
        }
        if (this.appBuild == null ? applicationInformation.appBuild != null : !this.appBuild.equals(applicationInformation.appBuild)) {
            return false;
        }
        if (this.packageId != null) {
            if (this.packageId.equals(applicationInformation.packageId)) {
                return true;
            }
        } else if (applicationInformation.packageId == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.appName != null) {
            hashCode = this.appName.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.appVersion != null) {
            hashCode = this.appVersion.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.appBuild != null) {
            hashCode = this.appBuild.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.packageId != null) {
            i = this.packageId.hashCode();
        }
        return hashCode + i;
    }
}
