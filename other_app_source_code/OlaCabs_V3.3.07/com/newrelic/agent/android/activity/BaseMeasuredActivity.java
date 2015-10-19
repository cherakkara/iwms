package com.newrelic.agent.android.activity;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementException;
import com.newrelic.agent.android.measurement.MeasurementPool;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.tracing.TraceMachine;

public class BaseMeasuredActivity implements MeasuredActivity {
    private boolean autoInstrumented;
    private long endTime;
    private Measurement endingMeasurement;
    private ThreadInfo endingThread;
    private boolean finished;
    private MeasurementPool measurementPool;
    private String name;
    private long startTime;
    private Measurement startingMeasurement;
    private ThreadInfo startingThread;

    public String getName() {
        return this.name;
    }

    public String getMetricName() {
        return TraceMachine.formatActivityMetricName(this.name);
    }

    public String getBackgroundMetricName() {
        return TraceMachine.formatActivityBackgroundMetricName(this.name);
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public ThreadInfo getStartingThread() {
        return this.startingThread;
    }

    public ThreadInfo getEndingThread() {
        return this.endingThread;
    }

    public boolean isAutoInstrumented() {
        return this.autoInstrumented;
    }

    public Measurement getStartingMeasurement() {
        return this.startingMeasurement;
    }

    public Measurement getEndingMeasurement() {
        return this.endingMeasurement;
    }

    public MeasurementPool getMeasurementPool() {
        return this.measurementPool;
    }

    public void setName(String str) {
        throwIfFinished();
        this.name = str;
    }

    public void setStartTime(long j) {
        throwIfFinished();
        this.startTime = j;
    }

    public void setEndTime(long j) {
        throwIfFinished();
        this.endTime = j;
    }

    public void setStartingThread(ThreadInfo threadInfo) {
        throwIfFinished();
        this.startingThread = threadInfo;
    }

    public void setEndingThread(ThreadInfo threadInfo) {
        throwIfFinished();
        this.endingThread = threadInfo;
    }

    public void setAutoInstrumented(boolean z) {
        throwIfFinished();
        this.autoInstrumented = z;
    }

    public void setStartingMeasurement(Measurement measurement) {
        throwIfFinished();
        this.startingMeasurement = measurement;
    }

    public void setEndingMeasurement(Measurement measurement) {
        throwIfFinished();
        this.endingMeasurement = measurement;
    }

    public void setMeasurementPool(MeasurementPool measurementPool) {
        throwIfFinished();
        this.measurementPool = measurementPool;
    }

    public void finish() {
        this.finished = true;
    }

    public boolean isFinished() {
        return this.finished;
    }

    private void throwIfFinished() {
        if (this.finished) {
            throw new MeasurementException("Cannot modify finished Activity");
        }
    }
}
