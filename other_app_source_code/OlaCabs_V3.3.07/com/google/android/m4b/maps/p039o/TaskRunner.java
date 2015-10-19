package com.google.android.m4b.maps.p039o;

import android.support.v4.media.TransportMediator;
import com.google.android.m4b.maps.cm.BaseConfig;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p050j.ThreadFactory;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Vector;

/* renamed from: com.google.android.m4b.maps.o.c */
public final class TaskRunner implements Runnable {
    private Vector<Task> f7671a;
    private Vector<TimerTask> f7672b;
    private Object f7673c;
    private Clock f7674d;
    private ThreadFactory f7675e;
    private Thread[] f7676f;
    private String f7677g;
    private int f7678h;
    private boolean f7679i;

    public TaskRunner(ThreadFactory threadFactory) {
        this(threadFactory, "TaskRunner", 1);
    }

    private TaskRunner(ThreadFactory threadFactory, String str, int i) {
        this.f7671a = new Vector();
        this.f7672b = new Vector();
        this.f7673c = new Object();
        this.f7678h = TransportMediator.KEYCODE_MEDIA_PAUSE;
        this.f7679i = false;
        this.f7675e = threadFactory;
        this.f7677g = str;
        this.f7676f = new Thread[1];
        this.f7674d = BaseConfig.m10147p().m10148h();
    }

    final int m11056a() {
        return this.f7678h;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void m11057a(com.google.android.m4b.maps.p039o.AbstractTask r3) {
        /*
        r2 = this;
        r1 = r2.f7673c;
        monitor-enter(r1);
        r0 = r3.m4753c();	 Catch:{ all -> 0x0010 }
        switch(r0) {
            case 0: goto L_0x000c;
            case 1: goto L_0x000a;
            case 2: goto L_0x000a;
            case 3: goto L_0x0013;
            default: goto L_0x000a;
        };	 Catch:{ all -> 0x0010 }
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x0010 }
        return;
    L_0x000c:
        r3.m4755e();	 Catch:{ all -> 0x0010 }
        goto L_0x000a;
    L_0x0010:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0013:
        r0 = 4;
        r3.m4751a(r0);	 Catch:{ all -> 0x0010 }
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.o.c.a(com.google.android.m4b.maps.o.a):void");
    }

    final void m11058a(Task task) {
        synchronized (this.f7673c) {
            int h = task.m4760h();
            int i = 0;
            int size = this.f7671a.size();
            while (i < size) {
                int i2 = (i + size) / 2;
                if (h <= ((Task) this.f7671a.elementAt(i2)).m4760h()) {
                    i = i2 + 1;
                } else {
                    size = i2;
                }
            }
            this.f7671a.insertElementAt(task, i);
            task.m4751a(2);
            this.f7673c.notifyAll();
        }
    }

    final void m11059a(TimerTask timerTask) {
        synchronized (this.f7673c) {
            long i = timerTask.m4766i();
            int i2 = 0;
            int size = this.f7672b.size();
            while (i2 < size) {
                int i3 = (i2 + size) / 2;
                if (i >= ((TimerTask) this.f7672b.elementAt(i3)).m4766i()) {
                    i2 = i3 + 1;
                } else {
                    size = i3;
                }
            }
            this.f7672b.insertElementAt(timerTask, i2);
            timerTask.m4751a(1);
            this.f7673c.notifyAll();
        }
    }

    public final int m11060b(AbstractTask abstractTask) {
        int b;
        synchronized (this.f7673c) {
            b = abstractTask.m4752b();
        }
        return b;
    }

    final boolean m11062c(AbstractTask abstractTask) {
        synchronized (this.f7673c) {
            switch (abstractTask.m4753c()) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f7672b.removeElement(abstractTask);
                    abstractTask.m4751a(0);
                    this.f7673c.notifyAll();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.f7671a.removeElement(abstractTask);
                    abstractTask.m4751a(0);
                    this.f7673c.notifyAll();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    abstractTask.m4751a(0);
                    return false;
                default:
                    return false;
            }
        }
    }

    private boolean m11054c() {
        if (this.f7679i && this.f7671a.isEmpty()) {
            try {
                if (this.f7672b.isEmpty()) {
                    this.f7673c.wait();
                } else {
                    long i = ((TimerTask) this.f7672b.elementAt(0)).m4766i() - this.f7674d.m10151a();
                    if (i > 0) {
                        this.f7673c.wait(i);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.f7679i;
    }

    private Task m11055d() {
        while (!this.f7672b.isEmpty()) {
            Task task = (TimerTask) this.f7672b.elementAt(0);
            if (task.m4766i() - this.f7674d.m10151a() > 0) {
                break;
            }
            this.f7672b.removeElementAt(0);
            m11058a(task);
        }
        if (this.f7671a.isEmpty()) {
            return null;
        }
        task = (Task) this.f7671a.elementAt(0);
        task.m4751a(3);
        this.f7671a.removeElementAt(0);
        return task;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r4 = this;
        r0 = 0;
    L_0x0001:
        r1 = r4.f7673c;
        monitor-enter(r1);
        if (r0 == 0) goto L_0x0014;
    L_0x0006:
        r2 = r0.m4753c();	 Catch:{ all -> 0x0028 }
        r3 = 4;
        if (r2 != r3) goto L_0x001c;
    L_0x000d:
        r2 = 0;
        r0.m4751a(r2);	 Catch:{ all -> 0x0028 }
        r0.m4759e();	 Catch:{ all -> 0x0028 }
    L_0x0014:
        r0 = r4.m11054c();	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x002b;
    L_0x001a:
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        return;
    L_0x001c:
        r2 = r0.m4753c();	 Catch:{ all -> 0x0028 }
        r3 = 3;
        if (r2 != r3) goto L_0x0014;
    L_0x0023:
        r2 = 0;
        r0.m4751a(r2);	 Catch:{ all -> 0x0028 }
        goto L_0x0014;
    L_0x0028:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x002b:
        r0 = r4.m11055d();	 Catch:{ all -> 0x0028 }
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        if (r0 == 0) goto L_0x0001;
    L_0x0032:
        r0.m4757g();	 Catch:{ Throwable -> 0x0036 }
        goto L_0x0001;
    L_0x0036:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0001;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.o.c.run():void");
    }

    public final void m11061b() {
        synchronized (this.f7673c) {
            if (!this.f7679i) {
                this.f7679i = true;
                for (int i = 0; i < this.f7676f.length; i++) {
                    this.f7676f[i] = this.f7675e.m10534a(this.f7677g + "-" + i, this);
                    this.f7676f[i].start();
                }
            }
        }
    }

    public final String toString() {
        return super.toString();
    }
}
