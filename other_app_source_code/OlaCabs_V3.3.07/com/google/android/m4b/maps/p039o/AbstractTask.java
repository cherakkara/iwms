package com.google.android.m4b.maps.p039o;

/* renamed from: com.google.android.m4b.maps.o.a */
public abstract class AbstractTask {
    private static final AbstractTask[] f2985b;
    protected TaskRunner f2986a;
    private Runnable f2987c;
    private int f2988d;
    private int f2989e;
    private Object f2990f;

    abstract int m4752b();

    abstract void m4755e();

    static {
        f2985b = new AbstractTask[0];
    }

    public AbstractTask(TaskRunner taskRunner, Runnable runnable, String str) {
        this.f2990f = new Object();
        this.f2986a = taskRunner;
        this.f2987c = runnable;
    }

    public final int m4750a() {
        return this.f2986a.m11060b(this);
    }

    private AbstractTask[] m4749h() {
        AbstractTask[] abstractTaskArr;
        synchronized (this) {
            abstractTaskArr = f2985b;
        }
        return abstractTaskArr;
    }

    protected final int m4753c() {
        return this.f2988d;
    }

    protected final void m4751a(int i) {
        this.f2988d = i;
    }

    public void m4754d() {
        synchronized (this.f2990f) {
            this.f2989e = 0;
        }
        this.f2986a.m11057a(this);
    }

    protected void m4756f() {
        if (this.f2987c != null) {
            this.f2987c.run();
        }
    }

    void m4757g() {
        try {
            m4756f();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        synchronized (this.f2990f) {
            this.f2989e++;
            this.f2990f.notifyAll();
        }
        AbstractTask[] h = m4749h();
        for (AbstractTask d : h) {
            d.m4754d();
        }
    }
}
