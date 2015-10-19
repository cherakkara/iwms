package com.olacabs.customer.p076d;

import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

/* compiled from: TwoTimesRetryPolicy */
/* renamed from: com.olacabs.customer.d.dm */
public class dm implements RetryPolicy {
    public static final float DEFAULT_BACKOFF_MULT = 2.0f;
    public static final int DEFAULT_MAX_RETRIES = 2;
    public static final int DEFAULT_TIMEOUT_MS = 2500;
    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;

    public dm() {
        this(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT);
    }

    public dm(int i, int i2, float f) {
        this.mCurrentTimeoutMs = i;
        this.mMaxNumRetries = i2;
        this.mBackoffMultiplier = f;
    }

    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }

    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    public void retry(VolleyError volleyError) throws VolleyError {
        this.mCurrentRetryCount++;
        this.mCurrentTimeoutMs = (int) (((float) this.mCurrentTimeoutMs) + (((float) this.mCurrentTimeoutMs) * this.mBackoffMultiplier));
        if (!hasAttemptRemaining()) {
            throw volleyError;
        }
    }

    protected boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
}
