package com.crashlytics.android.answers;

import p004b.p005a.p006a.p007a.p008a.p011c.p012a.RetryState;

class RetryManager {
    private static final long NANOSECONDS_IN_MS = 1000000;
    long lastRetry;
    private RetryState retryState;

    public RetryManager(RetryState retryState) {
        if (retryState == null) {
            throw new NullPointerException("retryState must not be null");
        }
        this.retryState = retryState;
    }

    public boolean canRetry(long j) {
        return j - this.lastRetry >= NANOSECONDS_IN_MS * this.retryState.m260a();
    }

    public void recordRetry(long j) {
        this.lastRetry = j;
        this.retryState = this.retryState.m261b();
    }

    public void reset() {
        this.lastRetry = 0;
        this.retryState = this.retryState.m262c();
    }
}
