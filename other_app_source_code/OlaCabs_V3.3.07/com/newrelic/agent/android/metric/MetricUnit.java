package com.newrelic.agent.android.metric;

public enum MetricUnit {
    PERCENT("%"),
    BYTES("bytes"),
    SECONDS("sec"),
    BYTES_PER_SECOND("bytes/second"),
    OPERATIONS("op");
    
    private String label;

    private MetricUnit(String str) {
        this.label = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }
}
