package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.MeasurementType;

public class MethodMeasurementConsumer extends MetricMeasurementConsumer {
    private static final String METRIC_PREFIX = "Method/";

    public MethodMeasurementConsumer() {
        super(MeasurementType.Method);
    }

    protected String formatMetricName(String str) {
        return METRIC_PREFIX + str.replace("#", "/");
    }
}
