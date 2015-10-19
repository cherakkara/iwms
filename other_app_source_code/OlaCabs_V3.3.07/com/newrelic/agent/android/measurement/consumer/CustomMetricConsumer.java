package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.Metric;

public class CustomMetricConsumer extends MetricMeasurementConsumer {
    private static final String METRIC_PREFIX = "Custom/";

    public CustomMetricConsumer() {
        super(MeasurementType.Custom);
    }

    protected String formatMetricName(String str) {
        return METRIC_PREFIX + str;
    }

    public void consumeMeasurement(Measurement measurement) {
        Metric customMetric = ((CustomMetricMeasurement) measurement).getCustomMetric();
        customMetric.setName(formatMetricName(customMetric.getName()));
        addMetric(customMetric);
    }
}
