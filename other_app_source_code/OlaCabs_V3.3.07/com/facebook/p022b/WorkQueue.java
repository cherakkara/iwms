package com.facebook.p022b;

import com.facebook.FacebookSdk;
import java.util.concurrent.Executor;

/* renamed from: com.facebook.b.v */
public class WorkQueue {
    static final /* synthetic */ boolean f876a;
    private final Object f877b;
    private WorkQueue f878c;
    private final int f879d;
    private final Executor f880e;
    private WorkQueue f881f;
    private int f882g;

    /* renamed from: com.facebook.b.v.1 */
    class WorkQueue implements Runnable {
        final /* synthetic */ WorkQueue f868a;
        final /* synthetic */ WorkQueue f869b;

        WorkQueue(WorkQueue workQueue, WorkQueue workQueue2) {
            this.f869b = workQueue;
            this.f868a = workQueue2;
        }

        public void run() {
            try {
                this.f868a.m1161a().run();
            } finally {
                this.f869b.m1164a(this.f868a);
            }
        }
    }

    /* renamed from: com.facebook.b.v.a */
    interface WorkQueue {
    }

    /* renamed from: com.facebook.b.v.b */
    private class WorkQueue implements WorkQueue {
        static final /* synthetic */ boolean f870a;
        final /* synthetic */ WorkQueue f871b;
        private final Runnable f872c;
        private WorkQueue f873d;
        private WorkQueue f874e;
        private boolean f875f;

        static {
            f870a = !WorkQueue.class.desiredAssertionStatus();
        }

        WorkQueue(WorkQueue workQueue, Runnable runnable) {
            this.f871b = workQueue;
            this.f872c = runnable;
        }

        Runnable m1161a() {
            return this.f872c;
        }

        void m1162a(boolean z) {
            this.f875f = z;
        }

        WorkQueue m1160a(WorkQueue workQueue, boolean z) {
            if (!f870a && this.f873d != null) {
                throw new AssertionError();
            } else if (f870a || this.f874e == null) {
                WorkQueue workQueue2;
                if (workQueue == null) {
                    this.f874e = this;
                    this.f873d = this;
                    workQueue2 = this;
                } else {
                    this.f873d = workQueue;
                    this.f874e = workQueue.f874e;
                    workQueue2 = this.f873d;
                    this.f874e.f873d = this;
                    workQueue2.f874e = this;
                    workQueue2 = workQueue;
                }
                if (z) {
                    return this;
                }
                return workQueue2;
            } else {
                throw new AssertionError();
            }
        }

        WorkQueue m1159a(WorkQueue workQueue) {
            if (!f870a && this.f873d == null) {
                throw new AssertionError();
            } else if (f870a || this.f874e != null) {
                if (workQueue == this) {
                    if (this.f873d == this) {
                        workQueue = null;
                    } else {
                        workQueue = this.f873d;
                    }
                }
                this.f873d.f874e = this.f874e;
                this.f874e.f873d = this.f873d;
                this.f874e = null;
                this.f873d = null;
                return workQueue;
            } else {
                throw new AssertionError();
            }
        }
    }

    static {
        f876a = !WorkQueue.class.desiredAssertionStatus();
    }

    public WorkQueue() {
        this(8);
    }

    public WorkQueue(int i) {
        this(i, FacebookSdk.m1206d());
    }

    public WorkQueue(int i, Executor executor) {
        this.f877b = new Object();
        this.f881f = null;
        this.f882g = 0;
        this.f879d = i;
        this.f880e = executor;
    }

    public WorkQueue m1167a(Runnable runnable) {
        return m1168a(runnable, true);
    }

    public WorkQueue m1168a(Runnable runnable, boolean z) {
        WorkQueue workQueue = new WorkQueue(this, runnable);
        synchronized (this.f877b) {
            this.f878c = workQueue.m1160a(this.f878c, z);
        }
        m1163a();
        return workQueue;
    }

    private void m1163a() {
        m1164a(null);
    }

    private void m1164a(WorkQueue workQueue) {
        WorkQueue workQueue2 = null;
        synchronized (this.f877b) {
            if (workQueue != null) {
                this.f881f = workQueue.m1159a(this.f881f);
                this.f882g--;
            }
            if (this.f882g < this.f879d) {
                workQueue2 = this.f878c;
                if (workQueue2 != null) {
                    this.f878c = workQueue2.m1159a(this.f878c);
                    this.f881f = workQueue2.m1160a(this.f881f, false);
                    this.f882g++;
                    workQueue2.m1162a(true);
                }
            }
        }
        if (workQueue2 != null) {
            m1166b(workQueue2);
        }
    }

    private void m1166b(WorkQueue workQueue) {
        this.f880e.execute(new WorkQueue(this, workQueue));
    }
}
