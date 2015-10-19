package com.newrelic.agent.android.sample;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.consumer.MetricMeasurementConsumer;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.Sample;

public class MachineMeasurementConsumer extends MetricMeasurementConsumer {
    public MachineMeasurementConsumer() {
        super(MeasurementType.Machine);
    }

    protected String formatMetricName(String str) {
        return str;
    }

    public void consumeMeasurement(Measurement measurement) {
    }

    public void onHarvest() {
        Sample sampleMemory = Sampler.sampleMemory();
        if (sampleMemory != null) {
            Metric metric = new Metric("Memory/Used");
            metric.sample(sampleMemory.getValue().doubleValue());
            addMetric(metric);
        }
        super.onHarvest();
    }
}
