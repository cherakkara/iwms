package com.newrelic.agent.android;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.consumer.ActivityMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.CustomMetricConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpErrorHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpTransactionHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.MethodMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.SummaryMetricMeasurementConsumer;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.measurement.producer.ActivityMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.CustomMetricProducer;
import com.newrelic.agent.android.measurement.producer.HttpErrorMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MethodMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.NetworkMeasurementProducer;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.Trace;
import java.util.Map;

public class Measurements {
    private static final ActivityMeasurementConsumer activityConsumer;
    private static final ActivityMeasurementProducer activityMeasurementProducer;
    private static boolean broadcastNewMeasurements;
    private static final CustomMetricConsumer customMetricConsumer;
    private static final CustomMetricProducer customMetricProducer;
    private static final HttpErrorHarvestingConsumer httpErrorHarvester;
    private static final HttpErrorMeasurementProducer httpErrorMeasurementProducer;
    private static final HttpTransactionHarvestingConsumer httpTransactionHarvester;
    private static final AgentLog log;
    private static final MeasurementEngine measurementEngine;
    private static final MethodMeasurementConsumer methodMeasurementConsumer;
    private static final MethodMeasurementProducer methodMeasurementProducer;
    private static final NetworkMeasurementProducer networkMeasurementProducer;
    private static final SummaryMetricMeasurementConsumer summaryMetricMeasurementConsumer;

    static {
        log = AgentLogManager.getAgentLog();
        measurementEngine = new MeasurementEngine();
        httpErrorMeasurementProducer = new HttpErrorMeasurementProducer();
        networkMeasurementProducer = new NetworkMeasurementProducer();
        activityMeasurementProducer = new ActivityMeasurementProducer();
        methodMeasurementProducer = new MethodMeasurementProducer();
        customMetricProducer = new CustomMetricProducer();
        httpErrorHarvester = new HttpErrorHarvestingConsumer();
        httpTransactionHarvester = new HttpTransactionHarvestingConsumer();
        activityConsumer = new ActivityMeasurementConsumer();
        methodMeasurementConsumer = new MethodMeasurementConsumer();
        summaryMetricMeasurementConsumer = new SummaryMetricMeasurementConsumer();
        customMetricConsumer = new CustomMetricConsumer();
        broadcastNewMeasurements = true;
    }

    public static void initialize() {
        log.info("Measurement Engine initialized.");
        TaskQueue.start();
        addMeasurementProducer(httpErrorMeasurementProducer);
        addMeasurementProducer(networkMeasurementProducer);
        addMeasurementProducer(activityMeasurementProducer);
        addMeasurementProducer(methodMeasurementProducer);
        addMeasurementProducer(customMetricProducer);
        addMeasurementConsumer(httpErrorHarvester);
        addMeasurementConsumer(httpTransactionHarvester);
        addMeasurementConsumer(activityConsumer);
        addMeasurementConsumer(methodMeasurementConsumer);
        addMeasurementConsumer(summaryMetricMeasurementConsumer);
        addMeasurementConsumer(customMetricConsumer);
    }

    public static void shutdown() {
        TaskQueue.stop();
        measurementEngine.clear();
        log.info("Measurement Engine shutting down.");
        removeMeasurementProducer(httpErrorMeasurementProducer);
        removeMeasurementProducer(networkMeasurementProducer);
        removeMeasurementProducer(activityMeasurementProducer);
        removeMeasurementProducer(methodMeasurementProducer);
        removeMeasurementProducer(customMetricProducer);
        removeMeasurementConsumer(httpErrorHarvester);
        removeMeasurementConsumer(httpTransactionHarvester);
        removeMeasurementConsumer(activityConsumer);
        removeMeasurementConsumer(methodMeasurementConsumer);
        removeMeasurementConsumer(summaryMetricMeasurementConsumer);
        removeMeasurementConsumer(customMetricConsumer);
    }

    public static void addHttpError(String str, String str2, int i) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(str, str2, i);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(String str, String str2, int i, String str3) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(str, str2, i, str3);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(String str, String str2, int i, String str3, Map<String, String> map) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(str, str2, i, str3, map);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(String str, String str2, int i, String str3, Map<String, String> map, ThreadInfo threadInfo) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(str, str2, i, str3, map, threadInfo);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpTransaction(HttpTransactionMeasurement httpTransactionMeasurement) {
        if (!Harvest.isDisabled()) {
            if (httpTransactionMeasurement == null) {
                log.error("TransactionMeasurement is null. HttpTransactionMeasurement measurement not created.");
                return;
            }
            networkMeasurementProducer.produceMeasurement(httpTransactionMeasurement);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(TransactionData transactionData, String str, Map<String, String> map) {
        if (transactionData == null) {
            log.error("TransactionData is null. HttpError measurement not created.");
        } else {
            addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), str, map);
        }
    }

    public static void addCustomMetric(String str, String str2, int i, double d, double d2) {
        if (!Harvest.isDisabled()) {
            customMetricProducer.produceMeasurement(str, str2, i, d, d2);
            newMeasurementBroadcast();
        }
    }

    public static void addCustomMetric(String str, String str2, int i, double d, double d2, MetricUnit metricUnit, MetricUnit metricUnit2) {
        if (!Harvest.isDisabled()) {
            customMetricProducer.produceMeasurement(str, str2, i, d, d2, metricUnit, metricUnit2);
            newMeasurementBroadcast();
        }
    }

    public static void setBroadcastNewMeasurements(boolean z) {
        broadcastNewMeasurements = z;
    }

    private static void newMeasurementBroadcast() {
        if (broadcastNewMeasurements) {
            broadcast();
        }
    }

    public static void broadcast() {
        measurementEngine.broadcastMeasurements();
    }

    public static MeasuredActivity startActivity(String str) {
        if (Harvest.isDisabled()) {
            return null;
        }
        return measurementEngine.startActivity(str);
    }

    public static void renameActivity(String str, String str2) {
        measurementEngine.renameActivity(str, str2);
    }

    public static void endActivity(String str) {
        if (!Harvest.isDisabled()) {
            activityMeasurementProducer.produceMeasurement(measurementEngine.endActivity(str));
            newMeasurementBroadcast();
        }
    }

    public static void endActivity(MeasuredActivity measuredActivity) {
        if (!Harvest.isDisabled()) {
            measurementEngine.endActivity(measuredActivity);
            activityMeasurementProducer.produceMeasurement(measuredActivity);
            newMeasurementBroadcast();
        }
    }

    public static void endActivityWithoutMeasurement(MeasuredActivity measuredActivity) {
        if (!Harvest.isDisabled()) {
            measurementEngine.endActivity(measuredActivity);
        }
    }

    public static void addTracedMethod(Trace trace) {
        if (!Harvest.isDisabled()) {
            methodMeasurementProducer.produceMeasurement(trace);
            newMeasurementBroadcast();
        }
    }

    public static void addMeasurementProducer(MeasurementProducer measurementProducer) {
        measurementEngine.addMeasurementProducer(measurementProducer);
    }

    public static void removeMeasurementProducer(MeasurementProducer measurementProducer) {
        measurementEngine.removeMeasurementProducer(measurementProducer);
    }

    public static void addMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        measurementEngine.addMeasurementConsumer(measurementConsumer);
    }

    public static void removeMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        measurementEngine.removeMeasurementConsumer(measurementConsumer);
    }

    public static void process() {
        measurementEngine.broadcastMeasurements();
    }
}
