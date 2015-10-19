package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.instrumentation.MetricCategory;

public class MethodMeasurement extends CategorizedMeasurement {
    public MethodMeasurement(String str, String str2, long j, long j2, long j3, MetricCategory metricCategory) {
        super(MeasurementType.Method);
        setName(str);
        setScope(str2);
        setStartTime(j);
        setEndTime(j2);
        setExclusiveTime(j3);
        setCategory(metricCategory);
    }
}
