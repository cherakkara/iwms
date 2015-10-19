package p004b.p005a.p006a.p007a.p008a.p010b;

import com.crashlytics.android.core.CrashlyticsCore;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p004b.p005a.p006a.p007a.Fabric;

/* renamed from: b.a.a.a.a.b.n */
public final class ExecutorUtils {

    /* renamed from: b.a.a.a.a.b.n.1 */
    static class ExecutorUtils implements ThreadFactory {
        final /* synthetic */ String f152a;
        final /* synthetic */ AtomicLong f153b;

        /* renamed from: b.a.a.a.a.b.n.1.1 */
        class ExecutorUtils extends BackgroundPriorityRunnable {
            final /* synthetic */ Runnable f150a;
            final /* synthetic */ ExecutorUtils f151b;

            ExecutorUtils(ExecutorUtils executorUtils, Runnable runnable) {
                this.f151b = executorUtils;
                this.f150a = runnable;
            }

            public void onRun() {
                this.f150a.run();
            }
        }

        ExecutorUtils(String str, AtomicLong atomicLong) {
            this.f152a = str;
            this.f153b = atomicLong;
        }

        public Thread newThread(Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(new ExecutorUtils(this, runnable));
            newThread.setName(this.f152a + this.f153b.getAndIncrement());
            return newThread;
        }
    }

    /* renamed from: b.a.a.a.a.b.n.2 */
    static class ExecutorUtils extends BackgroundPriorityRunnable {
        final /* synthetic */ String f154a;
        final /* synthetic */ ExecutorService f155b;
        final /* synthetic */ long f156c;
        final /* synthetic */ TimeUnit f157d;

        ExecutorUtils(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
            this.f154a = str;
            this.f155b = executorService;
            this.f156c = j;
            this.f157d = timeUnit;
        }

        public void onRun() {
            try {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Executing shutdown hook for " + this.f154a);
                this.f155b.shutdown();
                if (!this.f155b.awaitTermination(this.f156c, this.f157d)) {
                    Fabric.m512h().m474a(CrashlyticsCore.TAG, this.f154a + " did not shut down in the" + " allocated time. Requesting immediate shutdown.");
                    this.f155b.shutdownNow();
                }
            } catch (InterruptedException e) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", new Object[]{this.f154a}));
                this.f155b.shutdownNow();
            }
        }
    }

    public static ExecutorService m196a(String str) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(ExecutorUtils.m200c(str));
        ExecutorUtils.m197a(str, newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    public static ScheduledExecutorService m199b(String str) {
        Object newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(ExecutorUtils.m200c(str));
        ExecutorUtils.m197a(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    public static final ThreadFactory m200c(String str) {
        return new ExecutorUtils(str, new AtomicLong(1));
    }

    private static final void m197a(String str, ExecutorService executorService) {
        ExecutorUtils.m198a(str, executorService, 2, TimeUnit.SECONDS);
    }

    public static final void m198a(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        Runtime.getRuntime().addShutdownHook(new Thread(new ExecutorUtils(str, executorService, j, timeUnit), "Crashlytics Shutdown Hook for " + str));
    }
}
