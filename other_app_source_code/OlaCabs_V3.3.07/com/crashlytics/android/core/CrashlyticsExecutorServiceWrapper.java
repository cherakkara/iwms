package com.crashlytics.android.core;

import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import p004b.p005a.p006a.p007a.Fabric;

class CrashlyticsExecutorServiceWrapper {
    private final ExecutorService executorService;

    /* renamed from: com.crashlytics.android.core.CrashlyticsExecutorServiceWrapper.1 */
    class C01321 implements Runnable {
        final /* synthetic */ Runnable val$runnable;

        C01321(Runnable runnable) {
            this.val$runnable = runnable;
        }

        public void run() {
            try {
                this.val$runnable.run();
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to execute task.", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsExecutorServiceWrapper.2 */
    class C01332 implements Callable<T> {
        final /* synthetic */ Callable val$callable;

        C01332(Callable callable) {
            this.val$callable = callable;
        }

        public T call() throws Exception {
            try {
                return this.val$callable.call();
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to execute task.", e);
                return null;
            }
        }
    }

    public CrashlyticsExecutorServiceWrapper(ExecutorService executorService) {
        this.executorService = executorService;
    }

    <T> T executeSyncLoggingException(Callable<T> callable) {
        try {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                return this.executorService.submit(callable).get(4, TimeUnit.SECONDS);
            }
            return this.executorService.submit(callable).get();
        } catch (RejectedExecutionException e) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Throwable e2) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to execute task.", e2);
            return null;
        }
    }

    Future<?> executeAsync(Runnable runnable) {
        try {
            return this.executorService.submit(new C01321(runnable));
        } catch (RejectedExecutionException e) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    <T> Future<T> executeAsync(Callable<T> callable) {
        try {
            return this.executorService.submit(new C01332(callable));
        } catch (RejectedExecutionException e) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
