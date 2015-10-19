package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.JsonArray;

public class ConnectInformation extends HarvestableArray {
    private static final AgentLog log;
    private ApplicationInformation applicationInformation;
    private DeviceInformation deviceInformation;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public ConnectInformation(ApplicationInformation applicationInformation, DeviceInformation deviceInformation) {
        if (applicationInformation == null) {
            log.error("null applicationInformation passed into ConnectInformation constructor");
        }
        if (deviceInformation == null) {
            log.error("null deviceInformation passed into ConnectInformation constructor");
        }
        this.applicationInformation = applicationInformation;
        this.deviceInformation = deviceInformation;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        notNull(this.applicationInformation);
        jsonArray.add(this.applicationInformation.asJsonArray());
        notNull(this.deviceInformation);
        jsonArray.add(this.deviceInformation.asJsonArray());
        return jsonArray;
    }

    public ApplicationInformation getApplicationInformation() {
        return this.applicationInformation;
    }

    public DeviceInformation getDeviceInformation() {
        return this.deviceInformation;
    }

    public void setApplicationInformation(ApplicationInformation applicationInformation) {
        this.applicationInformation = applicationInformation;
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConnectInformation connectInformation = (ConnectInformation) obj;
        if (this.applicationInformation == null ? connectInformation.applicationInformation != null : !this.applicationInformation.equals(connectInformation.applicationInformation)) {
            return false;
        }
        if (this.deviceInformation != null) {
            if (this.deviceInformation.equals(connectInformation.deviceInformation)) {
                return true;
            }
        } else if (connectInformation.deviceInformation == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.applicationInformation != null) {
            hashCode = this.applicationInformation.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.deviceInformation != null) {
            i = this.deviceInformation.hashCode();
        }
        return hashCode + i;
    }
}
