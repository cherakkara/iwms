package com.google.p025a.p034i.p035a;

import com.google.p025a.p026a.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import javax.annotation.Nullable;

/* renamed from: com.google.a.i.a.a */
public abstract class AbstractFuture<V> implements ListenableFuture<V> {
    private final AbstractFuture<V> f1949a;
    private final ExecutionList f1950b;

    /* renamed from: com.google.a.i.a.a.a */
    static final class AbstractFuture<V> extends AbstractQueuedSynchronizer {
        private V f1947a;
        private Throwable f1948b;

        AbstractFuture() {
        }

        protected int tryAcquireShared(int i) {
            if (m3116b()) {
                return 1;
            }
            return -1;
        }

        protected boolean tryReleaseShared(int i) {
            setState(i);
            return true;
        }

        V m3112a(long j) throws TimeoutException, CancellationException, ExecutionException, InterruptedException {
            if (tryAcquireSharedNanos(-1, j)) {
                return m3110d();
            }
            throw new TimeoutException("Timeout waiting for task.");
        }

        V m3111a() throws CancellationException, ExecutionException, InterruptedException {
            acquireSharedInterruptibly(-1);
            return m3110d();
        }

        private V m3110d() throws CancellationException, ExecutionException {
            int state = getState();
            switch (state) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    if (this.f1948b == null) {
                        return this.f1947a;
                    }
                    throw new ExecutionException(this.f1948b);
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    throw AbstractFuture.m3119a("Task was cancelled.", this.f1948b);
                default:
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
            }
        }

        boolean m3116b() {
            return (getState() & 14) != 0;
        }

        boolean m3117c() {
            return (getState() & 12) != 0;
        }

        boolean m3113a(@Nullable V v) {
            return m3109a(v, null, 2);
        }

        boolean m3114a(Throwable th) {
            return m3109a(null, th, 2);
        }

        boolean m3115a(boolean z) {
            return m3109a(null, null, z ? 8 : 4);
        }

        private boolean m3109a(@Nullable V v, @Nullable Throwable th, int i) {
            boolean compareAndSetState = compareAndSetState(0, 1);
            if (compareAndSetState) {
                this.f1947a = v;
                if ((i & 12) != 0) {
                    th = new CancellationException("Future.cancel() was called.");
                }
                this.f1948b = th;
                releaseShared(i);
            } else if (getState() == 1) {
                acquireShared(-1);
            }
            return compareAndSetState;
        }
    }

    protected AbstractFuture() {
        this.f1949a = new AbstractFuture();
        this.f1950b = new ExecutionList();
    }

    public V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        return this.f1949a.m3112a(timeUnit.toNanos(j));
    }

    public V get() throws InterruptedException, ExecutionException {
        return this.f1949a.m3111a();
    }

    public boolean isDone() {
        return this.f1949a.m3116b();
    }

    public boolean isCancelled() {
        return this.f1949a.m3117c();
    }

    public boolean cancel(boolean z) {
        if (!this.f1949a.m3115a(z)) {
            return false;
        }
        this.f1950b.m3129a();
        if (z) {
            m3120a();
        }
        return true;
    }

    protected void m3120a() {
    }

    public void m3121a(Runnable runnable, Executor executor) {
        this.f1950b.m3130a(runnable, executor);
    }

    protected boolean m3122a(@Nullable V v) {
        boolean a = this.f1949a.m3113a((Object) v);
        if (a) {
            this.f1950b.m3129a();
        }
        return a;
    }

    protected boolean m3123a(Throwable th) {
        boolean a = this.f1949a.m3114a((Throwable) Preconditions.m1817a((Object) th));
        if (a) {
            this.f1950b.m3129a();
        }
        if (!(th instanceof Error)) {
            return a;
        }
        throw ((Error) th);
    }

    static final CancellationException m3119a(@Nullable String str, @Nullable Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }
}
