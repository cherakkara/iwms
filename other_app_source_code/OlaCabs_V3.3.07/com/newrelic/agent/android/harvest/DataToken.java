package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class DataToken extends HarvestableArray {
    private int accountId;
    private int agentId;

    public DataToken(int i, int i2) {
        this.accountId = i;
        this.agentId = i2;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(Integer.valueOf(this.accountId)));
        jsonArray.add(new JsonPrimitive(Integer.valueOf(this.agentId)));
        return jsonArray;
    }

    public void clear() {
        this.accountId = 0;
        this.agentId = 0;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public int getAgentId() {
        return this.agentId;
    }

    public void setAgentId(int i) {
        this.agentId = i;
    }

    public boolean isValid() {
        return this.accountId > 0 && this.agentId > 0;
    }

    public String toString() {
        return "DataToken{accountId=" + this.accountId + ", agentId=" + this.agentId + '}';
    }
}
