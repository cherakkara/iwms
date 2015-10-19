package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ActivitySighting extends HarvestableArray {
    private long durationMs;
    private String name;
    private final long timestampMs;

    public ActivitySighting(long j, String str) {
        this.durationMs = 0;
        this.timestampMs = j;
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        synchronized (this) {
            this.name = str;
        }
    }

    public long getTimestampMs() {
        return this.timestampMs;
    }

    public long getDuration() {
        return this.durationMs;
    }

    public void end(long j) {
        synchronized (this) {
            this.durationMs = j - this.timestampMs;
        }
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        synchronized (this) {
            jsonArray.add(new JsonPrimitive(this.name));
            jsonArray.add(new JsonPrimitive(Long.valueOf(this.timestampMs)));
            jsonArray.add(new JsonPrimitive(Long.valueOf(this.durationMs)));
        }
        return jsonArray;
    }

    public JsonArray asJsonArrayWithoutDuration() {
        JsonArray jsonArray = new JsonArray();
        synchronized (this) {
            jsonArray.add(new JsonPrimitive(Long.valueOf(this.timestampMs)));
            jsonArray.add(new JsonPrimitive(this.name));
        }
        return jsonArray;
    }

    public static ActivitySighting newFromJson(JsonArray jsonArray) {
        return new ActivitySighting(jsonArray.get(0).getAsLong(), jsonArray.get(1).getAsString());
    }
}
