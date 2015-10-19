package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Iterator;

public class DeviceInfo extends HarvestableObject {
    private String OSBuild;
    private String OSVersion;
    private String architecture;
    private String deviceName;
    private String deviceUuid;
    private long[] diskAvailable;
    private long memoryUsage;
    private String modelNumber;
    private String networkStatus;
    private int orientation;
    private String runTime;
    private String screenResolution;

    public DeviceInfo(DeviceInformation deviceInformation, EnvironmentInformation environmentInformation) {
        this.memoryUsage = environmentInformation.getMemoryUsage();
        this.orientation = environmentInformation.getOrientation();
        this.networkStatus = environmentInformation.getNetworkStatus();
        this.diskAvailable = environmentInformation.getDiskAvailable();
        this.OSVersion = deviceInformation.getOsVersion();
        this.deviceName = deviceInformation.getManufacturer();
        this.OSBuild = deviceInformation.getOsBuild();
        this.architecture = deviceInformation.getArchitecture();
        this.modelNumber = deviceInformation.getModel();
        this.screenResolution = deviceInformation.getSize();
        this.deviceUuid = deviceInformation.getDeviceId();
        this.runTime = deviceInformation.getRunTime();
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("memoryUsage", new JsonPrimitive(Long.valueOf(this.memoryUsage)));
        jsonObject.add("orientation", new JsonPrimitive(Integer.valueOf(this.orientation)));
        jsonObject.add("networkStatus", new JsonPrimitive(this.networkStatus));
        jsonObject.add("diskAvailable", getDiskAvailableAsJson());
        jsonObject.add(AnalyticAttribute.OS_VERSION_ATTRIBUTE, new JsonPrimitive(this.OSVersion));
        jsonObject.add("deviceName", new JsonPrimitive(this.deviceName));
        jsonObject.add("osBuild", new JsonPrimitive(this.OSBuild));
        jsonObject.add("architecture", new JsonPrimitive(this.architecture));
        jsonObject.add("runTime", new JsonPrimitive(this.runTime));
        jsonObject.add("modelNumber", new JsonPrimitive(this.modelNumber));
        jsonObject.add("screenResolution", new JsonPrimitive(this.screenResolution));
        jsonObject.add("deviceUuid", new JsonPrimitive(this.deviceUuid));
        return jsonObject;
    }

    public static DeviceInfo newFromJson(JsonObject jsonObject) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.memoryUsage = jsonObject.get("memoryUsage").getAsLong();
        deviceInfo.orientation = jsonObject.get("orientation").getAsInt();
        deviceInfo.networkStatus = jsonObject.get("networkStatus").getAsString();
        deviceInfo.diskAvailable = longArrayFromJsonArray(jsonObject.get("diskAvailable").getAsJsonArray());
        deviceInfo.OSVersion = jsonObject.get(AnalyticAttribute.OS_VERSION_ATTRIBUTE).getAsString();
        deviceInfo.deviceName = jsonObject.get("deviceName").getAsString();
        deviceInfo.OSBuild = jsonObject.get("osBuild").getAsString();
        deviceInfo.architecture = jsonObject.get("architecture").getAsString();
        deviceInfo.runTime = jsonObject.get("runTime").getAsString();
        deviceInfo.modelNumber = jsonObject.get("modelNumber").getAsString();
        deviceInfo.screenResolution = jsonObject.get("screenResolution").getAsString();
        deviceInfo.deviceUuid = jsonObject.get("deviceUuid").getAsString();
        return deviceInfo;
    }

    private static long[] longArrayFromJsonArray(JsonArray jsonArray) {
        long[] jArr = new long[jsonArray.size()];
        Iterator it = jsonArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            jArr[i] = ((JsonElement) it.next()).getAsLong();
            i = i2;
        }
        return jArr;
    }

    private JsonArray getDiskAvailableAsJson() {
        JsonArray jsonArray = new JsonArray();
        for (long valueOf : this.diskAvailable) {
            jsonArray.add(new JsonPrimitive(Long.valueOf(valueOf)));
        }
        return jsonArray;
    }
}
