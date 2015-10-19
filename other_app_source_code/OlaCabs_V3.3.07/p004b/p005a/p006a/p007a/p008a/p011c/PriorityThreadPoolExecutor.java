package p004b.p005a.p006a.p007a.p008a.p011c;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: b.a.a.a.a.c.k */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {
    private static final int f249a;
    private static final int f250b;
    private static final int f251c;

    /* renamed from: b.a.a.a.a.c.k.a */
    protected static final class PriorityThreadPoolExecutor implements ThreadFactory {
        private final int f248a;

        public PriorityThreadPoolExecutor(int i) {
            this.f248a = i;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(this.f248a);
            thread.setName("Queue");
            return thread;
        }
    }

    public /* synthetic */ BlockingQueue getQueue() {
        return m302b();
    }

    static {
        f249a = Runtime.getRuntime().availableProcessors();
        f250b = f249a + 1;
        f251c = (f249a * 2) + 1;
    }

    <T extends Runnable & Dependency & Task & PriorityProvider> PriorityThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, DependencyPriorityBlockingQueue<T> dependencyPriorityBlockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, dependencyPriorityBlockingQueue, threadFactory);
        prestartAllCoreThreads();
    }

    public static <T extends Runnable & Dependency & Task & PriorityProvider> PriorityThreadPoolExecutor m301a(int i, int i2) {
        return new PriorityThreadPoolExecutor(i, i2, 1, TimeUnit.SECONDS, new DependencyPriorityBlockingQueue(), new PriorityThreadPoolExecutor(10));
    }

    public static PriorityThreadPoolExecutor m300a() {
        return PriorityThreadPoolExecutor.m301a(f250b, f251c);
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new PriorityFutureTask(runnable, t);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new PriorityFutureTask(callable);
    }

    public void execute(Runnable runnable) {
        if (PriorityTask.isProperDelegate(runnable)) {
            super.execute(runnable);
        } else {
            super.execute(newTaskFor(runnable, null));
        }
    }

    protected void afterExecute(Runnable runnable, Throwable th) {
        Task task = (Task) runnable;
        task.setFinished(true);
        task.setError(th);
        m302b().m289d();
        super.afterExecute(runnable, th);
    }

    public DependencyPriorityBlockingQueue m302b() {
        return (DependencyPriorityBlockingQueue) super.getQueue();
    }
}
