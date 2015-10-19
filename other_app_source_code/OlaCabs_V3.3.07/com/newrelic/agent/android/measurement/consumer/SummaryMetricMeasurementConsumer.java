package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.MethodMeasurement;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceLifecycleAware;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class SummaryMetricMeasurementConsumer extends MetricMeasurementConsumer implements TraceLifecycleAware {
    private static final String ACTIVITY_METRIC_PREFIX = "Mobile/Activity/Summary/Name/";
    private static final String METRIC_PREFIX = "Mobile/Summary/";
    private static final AgentLog log;
    private final List<ActivityTrace> completedTraces;

    /* renamed from: com.newrelic.agent.android.measurement.consumer.SummaryMetricMeasurementConsumer.1 */
    static /* synthetic */ class C07431 {
        static final /* synthetic */ int[] f8903xb32dda8c;

        static {
            f8903xb32dda8c = new int[MeasurementType.values().length];
            try {
                f8903xb32dda8c[MeasurementType.Method.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8903xb32dda8c[MeasurementType.Network.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8903xb32dda8c[MeasurementType.Custom.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        log = AgentLogManager.getAgentLog();
    }

    public SummaryMetricMeasurementConsumer() {
        super(MeasurementType.Any);
        this.completedTraces = new CopyOnWriteArrayList();
        this.recordUnscopedMetrics = false;
        TraceMachine.addTraceListener(this);
    }

    public void consumeMeasurement(Measurement measurement) {
        if (measurement != null) {
            switch (C07431.f8903xb32dda8c[measurement.getType().ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    consumeMethodMeasurement((MethodMeasurement) measurement);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    consumeNetworkMeasurement((HttpTransactionMeasurement) measurement);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    consumeCustomMeasurement((CustomMetricMeasurement) measurement);
                default:
            }
        }
    }

    private void consumeMethodMeasurement(MethodMeasurement methodMeasurement) {
        if (methodMeasurement.getCategory() == null || methodMeasurement.getCategory() == MetricCategory.NONE) {
            methodMeasurement.setCategory(MetricCategory.categoryForMethod(methodMeasurement.getName()));
            if (methodMeasurement.getCategory() == MetricCategory.NONE) {
                return;
            }
        }
        Measurement baseMeasurement = new BaseMeasurement((Measurement) methodMeasurement);
        baseMeasurement.setName(methodMeasurement.getCategory().getCategoryName());
        super.consumeMeasurement(baseMeasurement);
    }

    private void consumeCustomMeasurement(CustomMetricMeasurement customMetricMeasurement) {
        if (customMetricMeasurement.getCategory() != null && customMetricMeasurement.getCategory() != MetricCategory.NONE) {
            Measurement baseMeasurement = new BaseMeasurement((Measurement) customMetricMeasurement);
            baseMeasurement.setName(customMetricMeasurement.getCategory().getCategoryName());
            super.consumeMeasurement(baseMeasurement);
        }
    }

    private void consumeNetworkMeasurement(HttpTransactionMeasurement httpTransactionMeasurement) {
        Measurement baseMeasurement = new BaseMeasurement((Measurement) httpTransactionMeasurement);
        baseMeasurement.setName(MetricCategory.NETWORK.getCategoryName());
        super.consumeMeasurement(baseMeasurement);
    }

    protected String formatMetricName(String str) {
        return METRIC_PREFIX + str.replace("#", "/");
    }

    public void onHarvest() {
        if (this.metrics.getAll().size() != 0 && this.completedTraces.size() != 0) {
            for (ActivityTrace summarizeActivityMetrics : this.completedTraces) {
                summarizeActivityMetrics(summarizeActivityMetrics);
            }
            if (this.metrics.getAll().size() != 0) {
                log.debug("Not all metrics were summarized!");
            }
            this.completedTraces.clear();
        }
    }

    private void summarizeActivityMetrics(ActivityTrace activityTrace) {
        Trace trace = activityTrace.rootTrace;
        List<Metric> removeAllWithScope = this.metrics.removeAllWithScope(trace.metricName);
        List<Metric> removeAllWithScope2 = this.metrics.removeAllWithScope(trace.metricBackgroundName);
        Map hashMap = new HashMap();
        for (Metric metric : removeAllWithScope) {
            hashMap.put(metric.getName(), metric);
        }
        for (Metric metric2 : removeAllWithScope2) {
            if (hashMap.containsKey(metric2.getName())) {
                ((Metric) hashMap.get(metric2.getName())).aggregate(metric2);
            } else {
                hashMap.put(metric2.getName(), metric2);
            }
        }
        double d = 0.0d;
        for (Metric metric22 : hashMap.values()) {
            d = metric22.getExclusive() + d;
        }
        double d2 = ((double) (trace.exitTimestamp - trace.entryTimestamp)) / 1000.0d;
        for (Metric metric222 : hashMap.values()) {
            double d3;
            if (metric222.getExclusive() == 0.0d || d == 0.0d) {
                d3 = 0.0d;
            } else {
                d3 = metric222.getExclusive() / d;
            }
            d3 *= d2;
            metric222.setTotal(Double.valueOf(d3));
            metric222.setExclusive(Double.valueOf(d3));
            metric222.setMinFieldValue(Double.valueOf(0.0d));
            metric222.setMaxFieldValue(Double.valueOf(0.0d));
            metric222.setSumOfSquares(Double.valueOf(0.0d));
            metric222.setScope(ACTIVITY_METRIC_PREFIX + trace.displayName);
            Harvest.addMetric(metric222);
            Metric metric3 = new Metric(metric222);
            metric3.setScope(null);
            Harvest.addMetric(metric3);
        }
    }

    public void onHarvestError() {
    }

    public void onHarvestComplete() {
    }

    public void onTraceStart(ActivityTrace activityTrace) {
    }

    public void onTraceComplete(ActivityTrace activityTrace) {
        if (!this.completedTraces.contains(activityTrace)) {
            this.completedTraces.add(activityTrace);
        }
    }

    public void onEnterMethod() {
    }

    public void onExitMethod() {
    }
}
