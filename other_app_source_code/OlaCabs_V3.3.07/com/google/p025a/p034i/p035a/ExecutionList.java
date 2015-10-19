package com.google.p025a.p034i.p035a;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.a.i.a.e */
public final class ExecutionList {
    static final Logger f1953a;
    private final Queue<ExecutionList> f1954b;
    private boolean f1955c;

    /* renamed from: com.google.a.i.a.e.a */
    private static class ExecutionList {
        final Runnable f1951a;
        final Executor f1952b;

        ExecutionList(Runnable runnable, Executor executor) {
            this.f1951a = runnable;
            this.f1952b = executor;
        }

        void m3128a() {
            try {
                this.f1952b.execute(this.f1951a);
            } catch (Throwable e) {
                ExecutionList.f1953a.log(Level.SEVERE, "RuntimeException while executing runnable " + this.f1951a + " with executor " + this.f1952b, e);
            }
        }
    }

    static {
        f1953a = Logger.getLogger(ExecutionList.class.getName());
    }

    public ExecutionList() {
        this.f1954b = ar.m2524b();
        this.f1955c = false;
    }

    public void m3130a(Runnable runnable, Executor executor) {
        Preconditions.m1818a((Object) runnable, (Object) "Runnable was null.");
        Preconditions.m1818a((Object) executor, (Object) "Executor was null.");
        Object obj = null;
        synchronized (this.f1954b) {
            if (this.f1955c) {
                obj = 1;
            } else {
                this.f1954b.add(new ExecutionList(runnable, executor));
            }
        }
        if (obj != null) {
            new ExecutionList(runnable, executor).m3128a();
        }
    }

    public void m3129a() {
        synchronized (this.f1954b) {
            if (this.f1955c) {
                return;
            }
            this.f1955c = true;
            while (!this.f1954b.isEmpty()) {
                ((ExecutionList) this.f1954b.poll()).m3128a();
            }
        }
    }
}
