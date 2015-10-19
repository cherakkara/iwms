package com.crashlytics.android.answers;

import java.io.File;
import java.util.List;
import p004b.p005a.p006a.p007a.p008a.p011c.p012a.DefaultRetryPolicy;
import p004b.p005a.p006a.p007a.p008a.p011c.p012a.ExponentialBackoff;
import p004b.p005a.p006a.p007a.p008a.p011c.p012a.RetryState;
import p004b.p005a.p006a.p007a.p008a.p013d.FilesSender;

class AnswersRetryFilesSender implements FilesSender {
    private static final int BACKOFF_MS = 1000;
    private static final int BACKOFF_POWER = 8;
    private static final double JITTER_PERCENT = 0.1d;
    private static final int MAX_RETRIES = 5;
    private final SessionAnalyticsFilesSender filesSender;
    private final RetryManager retryManager;

    public static AnswersRetryFilesSender build(SessionAnalyticsFilesSender sessionAnalyticsFilesSender) {
        return new AnswersRetryFilesSender(sessionAnalyticsFilesSender, new RetryManager(new RetryState(new RandomBackoff(new ExponentialBackoff(1000, BACKOFF_POWER), JITTER_PERCENT), new DefaultRetryPolicy(MAX_RETRIES))));
    }

    AnswersRetryFilesSender(SessionAnalyticsFilesSender sessionAnalyticsFilesSender, RetryManager retryManager) {
        this.filesSender = sessionAnalyticsFilesSender;
        this.retryManager = retryManager;
    }

    public boolean send(List<File> list) {
        long nanoTime = System.nanoTime();
        if (!this.retryManager.canRetry(nanoTime)) {
            return false;
        }
        if (this.filesSender.send(list)) {
            this.retryManager.reset();
            return true;
        }
        this.retryManager.recordRetry(nanoTime);
        return false;
    }
}
