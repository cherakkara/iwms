package com.newrelic.agent.android;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.activity.NamedActivity;
import com.newrelic.agent.android.measurement.MeasurementException;
import com.newrelic.agent.android.measurement.MeasurementPool;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MeasurementEngine {
    private final Map<String, MeasuredActivity> activities;
    private final MeasurementPool rootMeasurementPool;

    public MeasurementEngine() {
        this.activities = new ConcurrentHashMap();
        this.rootMeasurementPool = new MeasurementPool();
    }

    public MeasuredActivity startActivity(String str) {
        if (this.activities.containsKey(str)) {
            throw new MeasurementException("An activity with the name '" + str + "' has already started.");
        }
        MeasuredActivity namedActivity = new NamedActivity(str);
        this.activities.put(str, namedActivity);
        MeasurementConsumer measurementPool = new MeasurementPool();
        namedActivity.setMeasurementPool(measurementPool);
        this.rootMeasurementPool.addMeasurementConsumer(measurementPool);
        return namedActivity;
    }

    public void renameActivity(String str, String str2) {
        MeasuredActivity measuredActivity = (MeasuredActivity) this.activities.remove(str);
        if (measuredActivity != null && (measuredActivity instanceof NamedActivity)) {
            this.activities.put(str2, measuredActivity);
            ((NamedActivity) measuredActivity).rename(str2);
        }
    }

    public MeasuredActivity endActivity(String str) {
        MeasuredActivity measuredActivity = (MeasuredActivity) this.activities.get(str);
        if (measuredActivity == null) {
            throw new MeasurementException("Activity '" + str + "' has not been started.");
        }
        endActivity(measuredActivity);
        return measuredActivity;
    }

    public void endActivity(MeasuredActivity measuredActivity) {
        this.rootMeasurementPool.removeMeasurementConsumer(measuredActivity.getMeasurementPool());
        this.activities.remove(measuredActivity.getName());
        measuredActivity.finish();
    }

    public void clear() {
        this.activities.clear();
    }

    public void addMeasurementProducer(MeasurementProducer measurementProducer) {
        this.rootMeasurementPool.addMeasurementProducer(measurementProducer);
    }

    public void removeMeasurementProducer(MeasurementProducer measurementProducer) {
        this.rootMeasurementPool.removeMeasurementProducer(measurementProducer);
    }

    public void addMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        this.rootMeasurementPool.addMeasurementConsumer(measurementConsumer);
    }

    public void removeMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        this.rootMeasurementPool.removeMeasurementConsumer(measurementConsumer);
    }

    public void broadcastMeasurements() {
        this.rootMeasurementPool.broadcastMeasurements();
    }
}
