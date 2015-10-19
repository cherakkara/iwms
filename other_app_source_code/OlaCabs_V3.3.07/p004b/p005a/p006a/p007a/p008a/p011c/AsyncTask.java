package p004b.p005a.p006a.p007a.p008a.p011c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: b.a.a.a.a.c.a */
public abstract class AsyncTask<Params, Progress, Result> {
    private static final int f222a;
    public static final Executor f223b;
    public static final Executor f224c;
    private static final int f225d;
    private static final int f226e;
    private static final ThreadFactory f227f;
    private static final BlockingQueue<Runnable> f228g;
    private static final AsyncTask f229h;
    private static volatile Executor f230i;
    private final AsyncTask<Params, Result> f231j;
    private final FutureTask<Result> f232k;
    private volatile AsyncTask f233l;
    private final AtomicBoolean f234m;
    private final AtomicBoolean f235n;

    /* renamed from: b.a.a.a.a.c.a.1 */
    static class AsyncTask implements ThreadFactory {
        private final AtomicInteger f201a;

        AsyncTask() {
            this.f201a = new AtomicInteger(1);
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f201a.getAndIncrement());
        }
    }

    /* renamed from: b.a.a.a.a.c.a.e */
    private static abstract class AsyncTask<Params, Result> implements Callable<Result> {
        Params[] f202b;

        private AsyncTask() {
        }
    }

    /* renamed from: b.a.a.a.a.c.a.2 */
    class AsyncTask extends AsyncTask<Params, Result> {
        final /* synthetic */ AsyncTask f203a;

        AsyncTask(AsyncTask asyncTask) {
            this.f203a = asyncTask;
            super();
        }

        public Result call() throws Exception {
            this.f203a.f235n.set(true);
            Process.setThreadPriority(10);
            return this.f203a.m268d(this.f203a.m271a(this.b));
        }
    }

    /* renamed from: b.a.a.a.a.c.a.3 */
    class AsyncTask extends FutureTask<Result> {
        final /* synthetic */ AsyncTask f204a;

        AsyncTask(AsyncTask asyncTask, Callable callable) {
            this.f204a = asyncTask;
            super(callable);
        }

        protected void done() {
            try {
                this.f204a.m267c(get());
            } catch (Throwable e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.f204a.m267c(null);
            }
        }
    }

    /* renamed from: b.a.a.a.a.c.a.4 */
    static /* synthetic */ class AsyncTask {
        static final /* synthetic */ int[] f205a;

        static {
            f205a = new int[AsyncTask.values().length];
            try {
                f205a[AsyncTask.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f205a[AsyncTask.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: b.a.a.a.a.c.a.a */
    private static class AsyncTask<Data> {
        final AsyncTask f206a;
        final Data[] f207b;

        AsyncTask(AsyncTask asyncTask, Data... dataArr) {
            this.f206a = asyncTask;
            this.f207b = dataArr;
        }
    }

    /* renamed from: b.a.a.a.a.c.a.b */
    private static class AsyncTask extends Handler {
        public AsyncTask() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            AsyncTask asyncTask = (AsyncTask) message.obj;
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    asyncTask.f206a.m269e(asyncTask.f207b[0]);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    asyncTask.f206a.m277b(asyncTask.f207b);
                default:
            }
        }
    }

    /* renamed from: b.a.a.a.a.c.a.c */
    private static class AsyncTask implements Executor {
        final LinkedList<Runnable> f210a;
        Runnable f211b;

        /* renamed from: b.a.a.a.a.c.a.c.1 */
        class AsyncTask implements Runnable {
            final /* synthetic */ Runnable f208a;
            final /* synthetic */ AsyncTask f209b;

            AsyncTask(AsyncTask asyncTask, Runnable runnable) {
                this.f209b = asyncTask;
                this.f208a = runnable;
            }

            public void run() {
                try {
                    this.f208a.run();
                } finally {
                    this.f209b.m259a();
                }
            }
        }

        private AsyncTask() {
            this.f210a = new LinkedList();
        }

        public synchronized void execute(Runnable runnable) {
            this.f210a.offer(new AsyncTask(this, runnable));
            if (this.f211b == null) {
                m259a();
            }
        }

        protected synchronized void m259a() {
            Runnable runnable = (Runnable) this.f210a.poll();
            this.f211b = runnable;
            if (runnable != null) {
                AsyncTask.f223b.execute(this.f211b);
            }
        }
    }

    /* renamed from: b.a.a.a.a.c.a.d */
    public enum AsyncTask {
        PENDING,
        RUNNING,
        FINISHED
    }

    protected abstract Result m271a(Params... paramsArr);

    static {
        f222a = Runtime.getRuntime().availableProcessors();
        f225d = f222a + 1;
        f226e = (f222a * 2) + 1;
        f227f = new AsyncTask();
        f228g = new LinkedBlockingQueue(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        f223b = new ThreadPoolExecutor(f225d, f226e, 1, TimeUnit.SECONDS, f228g, f227f);
        f224c = new AsyncTask();
        f229h = new AsyncTask();
        f230i = f224c;
    }

    public AsyncTask() {
        this.f233l = AsyncTask.PENDING;
        this.f234m = new AtomicBoolean();
        this.f235n = new AtomicBoolean();
        this.f231j = new AsyncTask(this);
        this.f232k = new AsyncTask(this, this.f231j);
    }

    private void m267c(Result result) {
        if (!this.f235n.get()) {
            m268d(result);
        }
    }

    private Result m268d(Result result) {
        f229h.obtainMessage(1, new AsyncTask(this, result)).sendToTarget();
        return result;
    }

    public final AsyncTask m275b() {
        return this.f233l;
    }

    protected void m272a() {
    }

    protected void m273a(Result result) {
    }

    protected void m277b(Progress... progressArr) {
    }

    protected void m276b(Result result) {
        m278c();
    }

    protected void m278c() {
    }

    public final boolean m279d() {
        return this.f234m.get();
    }

    public final boolean m274a(boolean z) {
        this.f234m.set(true);
        return this.f232k.cancel(z);
    }

    public final AsyncTask<Params, Progress, Result> m270a(Executor executor, Params... paramsArr) {
        if (this.f233l != AsyncTask.PENDING) {
            switch (AsyncTask.f205a[this.f233l.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f233l = AsyncTask.RUNNING;
        m272a();
        this.f231j.f202b = paramsArr;
        executor.execute(this.f232k);
        return this;
    }

    private void m269e(Result result) {
        if (m279d()) {
            m276b((Object) result);
        } else {
            m273a((Object) result);
        }
        this.f233l = AsyncTask.FINISHED;
    }
}
