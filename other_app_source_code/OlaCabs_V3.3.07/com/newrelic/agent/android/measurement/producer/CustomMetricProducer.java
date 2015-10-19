package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.MetricUnit;

public class CustomMetricProducer extends BaseMeasurementProducer {
    private static final String FILTER_REGEX = "[/\\[\\]|*]";

    public CustomMetricProducer() {
        super(MeasurementType.Custom);
    }

    public void produceMeasurement(String str, String str2, int i, double d, double d2) {
        produceMeasurement(str2, str, i, d, d2, null, null);
    }

    public void produceMeasurement(String str, String str2, int i, double d, double d2, MetricUnit metricUnit, MetricUnit metricUnit2) {
        produceMeasurement(new CustomMetricMeasurement(createMetricName(str, str2, metricUnit, metricUnit2), i, d, d2));
    }

    private String createMetricName(String str, String str2, MetricUnit metricUnit, MetricUnit metricUnit2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2.replaceAll(FILTER_REGEX, Trace.NULL));
        stringBuffer.append("/");
        stringBuffer.append(str.replaceAll(FILTER_REGEX, Trace.NULL));
        if (!(metricUnit == null && metricUnit2 == null)) {
            stringBuffer.append("[");
            if (metricUnit2 != null) {
                stringBuffer.append(metricUnit2.getLabel());
            }
            if (metricUnit != null) {
                stringBuffer.append("|");
                stringBuffer.append(metricUnit.getLabel());
            }
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }
}
