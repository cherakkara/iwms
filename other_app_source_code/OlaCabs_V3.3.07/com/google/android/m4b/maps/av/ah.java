package com.google.android.m4b.maps.av;

import android.os.SystemClock;
import com.google.android.m4b.maps.au.DriveAboutThread;
import com.google.android.m4b.maps.p059w.AndroidBuilds;
import org.apache.http.HttpStatus;

/* compiled from: FrameRateRegulator */
public final class ah implements RepaintCallback {
    static final int f4199a;
    final FrameRateRegulator f4200b;
    private int f4201c;
    private long f4202d;
    private int f4203e;
    private boolean f4204f;
    private boolean f4205g;
    private boolean f4206h;
    private int f4207i;
    private int f4208j;
    private int f4209k;
    private FrameRateRegulator f4210l;
    private boolean f4211m;

    /* renamed from: com.google.android.m4b.maps.av.ah.a */
    class FrameRateRegulator extends DriveAboutThread {
        private volatile boolean f4193a;
        private volatile int f4194b;
        private volatile boolean f4195c;
        private boolean f4196d;
        private long f4197e;
        private /* synthetic */ ah f4198f;

        public FrameRateRegulator(ah ahVar) {
            this.f4198f = ahVar;
            super("RenderDrive");
            this.f4193a = true;
            this.f4194b = ah.f4199a;
            this.f4195c = false;
            this.f4196d = false;
            this.f4197e = Long.MAX_VALUE;
        }

        public final void m6761f() {
            while (this.f4193a) {
                do {
                    try {
                        this.f4195c = false;
                        FrameRateRegulator.sleep((long) this.f4194b);
                    } catch (InterruptedException e) {
                    }
                } while (this.f4195c);
                synchronized (this) {
                    if (this.f4196d || this.f4197e <= SystemClock.uptimeMillis()) {
                        this.f4196d = false;
                        this.f4197e = Long.MAX_VALUE;
                        this.f4198f.f4200b.m6763w();
                    }
                }
            }
        }

        public final synchronized void m6754a() {
            this.f4198f.f4200b.m6763w();
        }

        public final void m6757b() {
            this.f4193a = false;
            interrupt();
        }

        public final void m6755a(int i) {
            if (this.f4194b != i && i > 15) {
                this.f4194b = i;
                this.f4195c = true;
                interrupt();
            }
        }

        public final int m6758c() {
            return this.f4194b;
        }

        public final void m6759d() {
            m6760e();
            interrupt();
        }

        public final synchronized void m6760e() {
            if (this.f4198f.f4200b != null) {
                this.f4198f.f4200b.m6764x();
            }
            this.f4196d = true;
        }

        public final synchronized void m6756a(long j) {
            this.f4197e = j;
        }

        public final synchronized long m6762g() {
            return this.f4197e;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.ah.b */
    public interface FrameRateRegulator {
        void m6763w();

        void m6764x();
    }

    static {
        f4199a = AndroidBuilds.m11561a() ? 16 : 20;
    }

    public ah(FrameRateRegulator frameRateRegulator) {
        this.f4211m = false;
        m6766c(f4199a);
        this.f4200b = frameRateRegulator;
    }

    public final boolean m6771a(int i) {
        int i2 = this.f4201c + i;
        if (this.f4201c != 0 && i2 > 35000) {
            return false;
        }
        this.f4201c = i2;
        return true;
    }

    public final void m6773b(int i) {
        this.f4201c += i;
    }

    public final void m6767a() {
        this.f4201c = 0;
        this.f4202d = SystemClock.elapsedRealtime();
    }

    public final void m6772b() {
        int elapsedRealtime = ((int) (SystemClock.elapsedRealtime() - this.f4202d)) + 5;
        int i = this.f4204f ? f4199a : this.f4203e;
        if (this.f4205g) {
            i += HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        i = Math.max(i, 15);
        synchronized (this) {
            if (this.f4210l != null) {
                this.f4210l.m6758c();
                if (this.f4206h) {
                    this.f4210l.m6759d();
                    this.f4206h = false;
                } else {
                    if (this.f4204f) {
                        this.f4210l.m6760e();
                    } else if (this.f4210l.m6762g() != Long.MAX_VALUE) {
                        i = (int) (this.f4210l.m6762g() - SystemClock.uptimeMillis());
                    }
                    this.f4210l.m6755a(i);
                }
            }
        }
        if (!this.f4205g && !this.f4204f) {
            this.f4207i = elapsedRealtime + this.f4207i;
            this.f4208j = i + this.f4208j;
            i = this.f4209k + 1;
            this.f4209k = i;
            if (i == 20) {
                float f = ((float) (this.f4208j - this.f4207i)) / ((float) this.f4208j);
                if (f < 0.23000002f) {
                    m6766c((int) (((float) this.f4203e) * 1.1f));
                } else if (f > 0.37f) {
                    m6766c((int) (((float) this.f4203e) * 0.9f));
                }
                this.f4209k = 0;
                this.f4207i = 0;
                this.f4208j = 0;
            }
        }
    }

    public final void m6769a(boolean z) {
        this.f4204f = z;
    }

    public final void m6774b(boolean z) {
        this.f4205g = z;
    }

    public final synchronized void m6775c() {
        this.f4206h = true;
        if (this.f4210l != null) {
            this.f4210l.m6759d();
        }
    }

    private void m6766c(int i) {
        this.f4203e = Math.max(f4199a, i);
    }

    public final void m6770a(boolean z, boolean z2) {
        synchronized (this) {
            if (z) {
                m6776c(true);
            }
            if (this.f4210l != null) {
                if (z2) {
                    this.f4210l.m6759d();
                }
                this.f4210l.m6760e();
            }
        }
    }

    public final void m6777d() {
        synchronized (this) {
            if (this.f4210l != null) {
                this.f4210l.m6754a();
            } else {
                this.f4200b.m6763w();
            }
        }
    }

    public final void m6778e() {
        synchronized (this) {
            this.f4210l = new FrameRateRegulator(this);
            this.f4210l.start();
        }
    }

    public final void m6779f() {
        synchronized (this) {
            if (this.f4210l != null) {
                this.f4210l.m6757b();
                this.f4210l = null;
            }
        }
    }

    public final synchronized void m6776c(boolean z) {
        this.f4211m = true;
    }

    public final synchronized boolean m6780g() {
        boolean z;
        z = this.f4211m;
        this.f4211m = false;
        return z;
    }

    public final void m6768a(long j) {
        synchronized (this) {
            if (this.f4210l != null) {
                this.f4210l.m6756a(j);
            }
        }
    }

    public final boolean m6781h() {
        FrameRateRegulator frameRateRegulator;
        synchronized (this) {
            frameRateRegulator = this.f4210l;
        }
        return frameRateRegulator == null || frameRateRegulator.f4196d;
    }
}
