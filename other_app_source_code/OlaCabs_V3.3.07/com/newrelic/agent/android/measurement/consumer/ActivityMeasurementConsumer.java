package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.MeasurementType;

public class ActivityMeasurementConsumer extends MetricMeasurementConsumer {
    public ActivityMeasurementConsumer() {
        super(MeasurementType.Activity);
    }

    protected String formatMetricName(String str) {
        return str;
    }
}
