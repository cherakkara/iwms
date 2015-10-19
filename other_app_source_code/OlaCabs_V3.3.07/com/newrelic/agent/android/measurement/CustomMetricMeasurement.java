package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.metric.Metric;

public class CustomMetricMeasurement extends CategorizedMeasurement {
    private Metric customMetric;

    public CustomMetricMeasurement() {
        super(MeasurementType.Custom);
    }

    public CustomMetricMeasurement(String str, int i, double d, double d2) {
        this();
        setName(str);
        this.customMetric = new Metric(str);
        this.customMetric.sample(d);
        this.customMetric.setCount((long) i);
        this.customMetric.setExclusive(Double.valueOf(d2));
    }

    public Metric getCustomMetric() {
        return this.customMetric;
    }
}
