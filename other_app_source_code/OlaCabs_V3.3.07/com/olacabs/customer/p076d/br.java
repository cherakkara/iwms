package com.olacabs.customer.p076d;

import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

/* compiled from: NoRetryPolicy */
/* renamed from: com.olacabs.customer.d.br */
public class br implements RetryPolicy {
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 0;
    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;

    public br() {
        this(0, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT);
    }

    public br(int i, int i2, float f) {
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
        this.mCurrentRetryCount += DEFAULT_MAX_RETRIES;
        this.mCurrentTimeoutMs = (int) (((float) this.mCurrentTimeoutMs) + (((float) this.mCurrentTimeoutMs) * this.mBackoffMultiplier));
        if (!hasAttemptRemaining()) {
            throw volleyError;
        }
    }

    protected boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
}
