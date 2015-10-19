package com.google.android.m4b.maps.p039o;

import com.google.android.m4b.maps.cm.BaseConfig;

/* renamed from: com.google.android.m4b.maps.o.d */
public class TimerTask extends Task {
    private long f2992b;
    private long f2993c;
    private long f2994d;
    private long f2995e;
    private int f2996f;
    private boolean f2997g;

    public TimerTask(TaskRunner taskRunner) {
        this(taskRunner, null);
    }

    private TimerTask(TaskRunner taskRunner, Runnable runnable) {
        this(taskRunner, null, null);
    }

    private TimerTask(TaskRunner taskRunner, Runnable runnable, String str) {
        super(taskRunner, runnable, null);
        this.f2992b = 0;
        this.f2993c = -1;
        this.f2994d = -1;
        this.f2995e = -1;
        this.f2996f = -1;
        this.f2997g = false;
    }

    public final void m4763d() {
        synchronized (this) {
            if ((this.f2996f == -1 || this.f2996f > 0) && this.f2995e == -1) {
                if (this.f2993c == -1) {
                    this.f2995e = BaseConfig.m10147p().m10148h().m10151a() + this.f2992b;
                } else {
                    this.f2995e = this.f2993c + this.f2992b;
                }
            }
        }
        this.a.m11057a((AbstractTask) this);
    }

    final synchronized void m4764e() {
        if (this.f2995e != -1) {
            this.a.m11059a(this);
        }
    }

    final synchronized int m4762b() {
        int i;
        if (this.a.m11062c(this)) {
            this.f2995e = -1;
            i = this.f2996f;
        } else if (this.f2995e != -1) {
            this.f2995e = -1;
            i = this.f2996f;
        } else {
            i = 0;
        }
        return i;
    }

    public final synchronized long m4766i() {
        return this.f2995e;
    }

    public final synchronized void m4761a(long j) {
        this.f2992b = 10800000;
    }

    final void m4765g() {
        synchronized (this) {
            if (this.f2994d == -1) {
                this.f2995e = -1;
            } else {
                if (this.f2996f > 0) {
                    this.f2996f--;
                }
                if (this.f2996f == 0) {
                    this.f2995e = -1;
                } else {
                    this.f2995e = BaseConfig.m10147p().m10148h().m10151a() + this.f2994d;
                }
            }
        }
        super.m4757g();
        this.a.m11057a((AbstractTask) this);
    }
}
