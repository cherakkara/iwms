package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;

public class BaseMeasurement implements Measurement {
    private static final AgentLog log;
    private long endTime;
    private long exclusiveTime;
    private boolean finished;
    private String name;
    private String scope;
    private long startTime;
    private ThreadInfo threadInfo;
    private MeasurementType type;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public BaseMeasurement(MeasurementType measurementType) {
        setType(measurementType);
    }

    public BaseMeasurement(Measurement measurement) {
        setType(measurement.getType());
        setName(measurement.getName());
        setScope(measurement.getScope());
        setStartTime(measurement.getStartTime());
        setEndTime(measurement.getEndTime());
        setExclusiveTime(measurement.getExclusiveTime());
        setThreadInfo(measurement.getThreadInfo());
        this.finished = measurement.isFinished();
    }

    void setType(MeasurementType measurementType) {
        throwIfFinished();
        this.type = measurementType;
    }

    public void setName(String str) {
        throwIfFinished();
        this.name = str;
    }

    public void setScope(String str) {
        throwIfFinished();
        this.scope = str;
    }

    public void setStartTime(long j) {
        throwIfFinished();
        this.startTime = j;
    }

    public void setEndTime(long j) {
        throwIfFinished();
        if (j < this.startTime) {
            log.error("Measurement end time must not precede start time - startTime: " + this.startTime + " endTime: " + j);
        } else {
            this.endTime = j;
        }
    }

    public void setExclusiveTime(long j) {
        throwIfFinished();
        this.exclusiveTime = j;
    }

    public MeasurementType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getScope() {
        return this.scope;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public double getStartTimeInSeconds() {
        return ((double) this.startTime) / 1000.0d;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public double getEndTimeInSeconds() {
        return ((double) this.endTime) / 1000.0d;
    }

    public long getExclusiveTime() {
        return this.exclusiveTime;
    }

    public double getExclusiveTimeInSeconds() {
        return ((double) this.exclusiveTime) / 1000.0d;
    }

    public double asDouble() {
        throw new UnsupportedOperationException();
    }

    public ThreadInfo getThreadInfo() {
        return this.threadInfo;
    }

    public void setThreadInfo(ThreadInfo threadInfo) {
        this.threadInfo = threadInfo;
    }

    public boolean isInstantaneous() {
        return this.endTime == 0;
    }

    public void finish() {
        if (this.finished) {
            throw new MeasurementException("Finish called on already finished Measurement");
        }
        this.finished = true;
    }

    public boolean isFinished() {
        return this.finished;
    }

    private void throwIfFinished() {
        if (this.finished) {
            throw new MeasurementException("Attempted to modify finished Measurement");
        }
    }

    public String toString() {
        return "BaseMeasurement{type=" + this.type + ", name='" + this.name + '\'' + ", scope='" + this.scope + '\'' + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", exclusiveTime=" + this.exclusiveTime + ", threadInfo=" + this.threadInfo + ", finished=" + this.finished + '}';
    }
}
