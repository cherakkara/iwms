package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;

public class Event extends HarvestableArray {
    private long eventName;
    private Map<String, String> params;
    private long timestamp;

    public Event() {
        this.params = new HashMap();
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(Long.valueOf(this.timestamp)));
        jsonArray.add(new JsonPrimitive(Long.valueOf(this.eventName)));
        jsonArray.add(new Gson().toJsonTree(this.params, GSON_STRING_MAP_TYPE));
        return jsonArray;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public long getEventName() {
        return this.eventName;
    }

    public void setEventName(long j) {
        this.eventName = j;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> map) {
        this.params = map;
    }
}
