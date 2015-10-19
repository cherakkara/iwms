package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonPrimitive;

public class HarvestableLong extends HarvestableValue {
    private long value;

    public HarvestableLong(long j) {
        this();
        this.value = j;
    }

    public JsonPrimitive asJsonPrimitive() {
        return new JsonPrimitive(Long.valueOf(this.value));
    }
}
