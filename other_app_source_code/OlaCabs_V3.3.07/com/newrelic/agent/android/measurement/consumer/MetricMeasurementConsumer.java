package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestLifecycleAware;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;

public abstract class MetricMeasurementConsumer extends BaseMeasurementConsumer implements HarvestLifecycleAware {
    protected MetricStore metrics;
    protected boolean recordUnscopedMetrics;

    protected abstract String formatMetricName(String str);

    public MetricMeasurementConsumer(MeasurementType measurementType) {
        super(measurementType);
        this.recordUnscopedMetrics = true;
        this.metrics = new MetricStore();
        Harvest.addHarvestListener(this);
    }

    public void consumeMeasurement(Measurement measurement) {
        Metric metric;
        String formatMetricName = formatMetricName(measurement.getName());
        String scope = measurement.getScope();
        double endTimeInSeconds = measurement.getEndTimeInSeconds() - measurement.getStartTimeInSeconds();
        if (scope != null) {
            metric = this.metrics.get(formatMetricName, scope);
            if (metric == null) {
                metric = new Metric(formatMetricName, scope);
                this.metrics.add(metric);
            }
            metric.sample(endTimeInSeconds);
            metric.addExclusive(measurement.getExclusiveTimeInSeconds());
        }
        if (this.recordUnscopedMetrics) {
            metric = this.metrics.get(formatMetricName);
            if (metric == null) {
                metric = new Metric(formatMetricName);
                this.metrics.add(metric);
            }
            metric.sample(endTimeInSeconds);
            metric.addExclusive(measurement.getExclusiveTimeInSeconds());
        }
    }

    protected void addMetric(Metric metric) {
        Metric metric2;
        if (metric.getScope() != null) {
            metric2 = this.metrics.get(metric.getName(), metric.getScope());
        } else {
            metric2 = this.metrics.get(metric.getName());
        }
        if (metric2 != null) {
            metric2.aggregate(metric);
        } else {
            this.metrics.add(metric);
        }
    }

    public void onHarvest() {
        for (Metric addMetric : this.metrics.getAll()) {
            Harvest.addMetric(addMetric);
        }
    }

    public void onHarvestComplete() {
        this.metrics.clear();
    }

    public void onHarvestError() {
        this.metrics.clear();
    }

    public void onHarvestSendFailed() {
        this.metrics.clear();
    }
}
