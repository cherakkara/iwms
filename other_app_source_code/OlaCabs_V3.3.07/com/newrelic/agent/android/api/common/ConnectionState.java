package com.newrelic.agent.android.api.common;

import com.newrelic.agent.android.api.v1.Defaults;
import java.util.concurrent.TimeUnit;

public final class ConnectionState {
    public static final ConnectionState NULL;
    private final boolean collectingNetworkErrors;
    private final String crossProcessId;
    private final Object dataToken;
    private final int errorLimit;
    private final long harvestInterval;
    private final TimeUnit harvestIntervalTimeUnit;
    private final long maxTransactionAge;
    private final TimeUnit maxTransactionAgeTimeUnit;
    private final long maxTransactionCount;
    private final int responseBodyLimit;
    private final long serverTimestamp;
    private final int stackTraceLimit;

    static {
        NULL = new ConnectionState();
    }

    private ConnectionState() {
        this.dataToken = null;
        this.crossProcessId = null;
        this.serverTimestamp = 0;
        this.harvestInterval = 60;
        this.harvestIntervalTimeUnit = TimeUnit.SECONDS;
        this.maxTransactionAge = 600;
        this.maxTransactionAgeTimeUnit = TimeUnit.SECONDS;
        this.maxTransactionCount = 1000;
        this.stackTraceLimit = 50;
        this.responseBodyLimit = Defaults.RESPONSE_BODY_LIMIT;
        this.collectingNetworkErrors = true;
        this.errorLimit = 10;
    }

    public ConnectionState(Object obj, String str, long j, long j2, TimeUnit timeUnit, long j3, TimeUnit timeUnit2, long j4, int i, int i2, boolean z, int i3) {
        this.dataToken = obj;
        this.crossProcessId = str;
        this.serverTimestamp = j;
        this.harvestInterval = j2;
        this.harvestIntervalTimeUnit = timeUnit;
        this.maxTransactionAge = j3;
        this.maxTransactionAgeTimeUnit = timeUnit2;
        this.maxTransactionCount = j4;
        this.stackTraceLimit = i;
        this.responseBodyLimit = i2;
        this.collectingNetworkErrors = z;
        this.errorLimit = i3;
    }

    public Object getDataToken() {
        return this.dataToken;
    }

    public String getCrossProcessId() {
        return this.crossProcessId;
    }

    public long getServerTimestamp() {
        return this.serverTimestamp;
    }

    public long getHarvestIntervalInSeconds() {
        return TimeUnit.SECONDS.convert(this.harvestInterval, this.harvestIntervalTimeUnit);
    }

    public long getHarvestIntervalInMilliseconds() {
        return TimeUnit.MILLISECONDS.convert(this.harvestInterval, this.harvestIntervalTimeUnit);
    }

    public long getMaxTransactionAgeInSeconds() {
        return TimeUnit.SECONDS.convert(this.maxTransactionAge, this.maxTransactionAgeTimeUnit);
    }

    public long getMaxTransactionAgeInMilliseconds() {
        return TimeUnit.MILLISECONDS.convert(this.maxTransactionAge, this.maxTransactionAgeTimeUnit);
    }

    public long getMaxTransactionCount() {
        return this.maxTransactionCount;
    }

    public int getStackTraceLimit() {
        return this.stackTraceLimit;
    }

    public int getResponseBodyLimit() {
        return this.responseBodyLimit;
    }

    public boolean isCollectingNetworkErrors() {
        return this.collectingNetworkErrors;
    }

    public int getErrorLimit() {
        return this.errorLimit;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.dataToken);
        return stringBuilder.toString();
    }
}
