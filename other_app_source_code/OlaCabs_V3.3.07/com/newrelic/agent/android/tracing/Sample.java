package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class Sample extends HarvestableArray {
    private SampleValue sampleValue;
    private long timestamp;
    private SampleType type;

    public enum SampleType {
        MEMORY,
        CPU
    }

    public Sample(SampleType sampleType) {
        setSampleType(sampleType);
        setTimestamp(System.currentTimeMillis());
    }

    public Sample(long j) {
        setTimestamp(j);
    }

    public Sample(long j, SampleValue sampleValue) {
        setTimestamp(j);
        setSampleValue(sampleValue);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public SampleValue getSampleValue() {
        return this.sampleValue;
    }

    public void setSampleValue(SampleValue sampleValue) {
        this.sampleValue = sampleValue;
    }

    public void setSampleValue(double d) {
        this.sampleValue = new SampleValue(d);
    }

    public void setSampleValue(long j) {
        this.sampleValue = new SampleValue(j);
    }

    public Number getValue() {
        return this.sampleValue.getValue();
    }

    public SampleType getSampleType() {
        return this.type;
    }

    public void setSampleType(SampleType sampleType) {
        this.type = sampleType;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(Long.valueOf(this.timestamp)));
        jsonArray.add(new JsonPrimitive(this.sampleValue.getValue()));
        return jsonArray;
    }
}
