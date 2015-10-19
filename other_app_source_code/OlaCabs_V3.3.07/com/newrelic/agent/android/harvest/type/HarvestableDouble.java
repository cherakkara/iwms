package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonPrimitive;

public class HarvestableDouble extends HarvestableValue {
    private double value;

    public HarvestableDouble(double d) {
        this();
        this.value = d;
    }

    public JsonPrimitive asJsonPrimitive() {
        return new JsonPrimitive(Double.valueOf(this.value));
    }
}
