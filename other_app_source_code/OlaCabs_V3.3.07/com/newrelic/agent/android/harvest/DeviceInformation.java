package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeviceInformation extends HarvestableArray {
    private String agentName;
    private String agentVersion;
    private String architecture;
    private String countryCode;
    private String deviceId;
    private String manufacturer;
    private Map<String, String> misc;
    private String model;
    private String osBuild;
    private String osName;
    private String osVersion;
    private String regionCode;
    private String runTime;
    private String size;

    public DeviceInformation() {
        this.misc = new HashMap();
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        notEmpty(this.osName);
        jsonArray.add(new JsonPrimitive(this.osName));
        notEmpty(this.osVersion);
        jsonArray.add(new JsonPrimitive(this.osVersion));
        notEmpty(this.manufacturer);
        notEmpty(this.model);
        jsonArray.add(new JsonPrimitive(this.manufacturer + " " + this.model));
        notEmpty(this.agentName);
        jsonArray.add(new JsonPrimitive(this.agentName));
        notEmpty(this.agentVersion);
        jsonArray.add(new JsonPrimitive(this.agentVersion));
        notEmpty(this.deviceId);
        jsonArray.add(new JsonPrimitive(this.deviceId));
        jsonArray.add(new JsonPrimitive(optional(this.countryCode)));
        jsonArray.add(new JsonPrimitive(optional(this.regionCode)));
        jsonArray.add(new JsonPrimitive(this.manufacturer));
        if (this.misc == null || this.misc.isEmpty()) {
            this.misc = Collections.emptyMap();
        }
        jsonArray.add(new Gson().toJsonTree(this.misc, GSON_STRING_MAP_TYPE));
        return jsonArray;
    }

    public void setOsName(String str) {
        this.osName = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setOsBuild(String str) {
        this.osBuild = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setRegionCode(String str) {
        this.regionCode = str;
    }

    public void setAgentName(String str) {
        this.agentName = str;
    }

    public void setAgentVersion(String str) {
        this.agentVersion = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setArchitecture(String str) {
        this.architecture = str;
    }

    public void setRunTime(String str) {
        this.runTime = str;
    }

    public void setSize(String str) {
        this.size = str;
        addMisc("size", str);
    }

    public void setMisc(Map<String, String> map) {
        this.misc = new HashMap(map);
    }

    public void addMisc(String str, String str2) {
        this.misc.put(str, str2);
    }

    public String getOsName() {
        return this.osName;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getOsBuild() {
        return this.osBuild;
    }

    public String getModel() {
        return this.model;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public String getAgentVersion() {
        return this.agentVersion;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getArchitecture() {
        return this.architecture;
    }

    public String getRunTime() {
        return this.runTime;
    }

    public String getSize() {
        return this.size;
    }

    public String toJsonString() {
        return "DeviceInformation{manufacturer='" + this.manufacturer + '\'' + ", osName='" + this.osName + '\'' + ", osVersion='" + this.osVersion + '\'' + ", model='" + this.model + '\'' + ", agentName='" + this.agentName + '\'' + ", agentVersion='" + this.agentVersion + '\'' + ", deviceId='" + this.deviceId + '\'' + ", countryCode='" + this.countryCode + '\'' + ", regionCode='" + this.regionCode + '\'' + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeviceInformation deviceInformation = (DeviceInformation) obj;
        if (this.agentName == null ? deviceInformation.agentName != null : !this.agentName.equals(deviceInformation.agentName)) {
            return false;
        }
        if (this.agentVersion == null ? deviceInformation.agentVersion != null : !this.agentVersion.equals(deviceInformation.agentVersion)) {
            return false;
        }
        if (this.architecture == null ? deviceInformation.architecture != null : !this.architecture.equals(deviceInformation.architecture)) {
            return false;
        }
        if (this.deviceId == null ? deviceInformation.deviceId != null : !this.deviceId.equals(deviceInformation.deviceId)) {
            return false;
        }
        if (this.manufacturer == null ? deviceInformation.manufacturer != null : !this.manufacturer.equals(deviceInformation.manufacturer)) {
            return false;
        }
        if (this.model == null ? deviceInformation.model != null : !this.model.equals(deviceInformation.model)) {
            return false;
        }
        if (this.osBuild == null ? deviceInformation.osBuild != null : !this.osBuild.equals(deviceInformation.osBuild)) {
            return false;
        }
        if (this.osName == null ? deviceInformation.osName != null : !this.osName.equals(deviceInformation.osName)) {
            return false;
        }
        if (this.osVersion == null ? deviceInformation.osVersion != null : !this.osVersion.equals(deviceInformation.osVersion)) {
            return false;
        }
        if (this.runTime == null ? deviceInformation.runTime != null : !this.runTime.equals(deviceInformation.runTime)) {
            return false;
        }
        if (this.size != null) {
            if (this.size.equals(deviceInformation.size)) {
                return true;
            }
        } else if (deviceInformation.size == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.osName != null ? this.osName.hashCode() : 0) * 31;
        if (this.osVersion != null) {
            hashCode = this.osVersion.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.osBuild != null) {
            hashCode = this.osBuild.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.model != null) {
            hashCode = this.model.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.agentName != null) {
            hashCode = this.agentName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.agentVersion != null) {
            hashCode = this.agentVersion.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.deviceId != null) {
            hashCode = this.deviceId.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.manufacturer != null) {
            hashCode = this.manufacturer.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.architecture != null) {
            hashCode = this.architecture.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.runTime != null) {
            hashCode = this.runTime.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.size != null) {
            i = this.size.hashCode();
        }
        return hashCode + i;
    }
}
