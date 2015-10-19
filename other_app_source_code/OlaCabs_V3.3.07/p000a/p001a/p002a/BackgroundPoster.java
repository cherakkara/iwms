package p000a.p001a.p002a;

/* renamed from: a.a.a.b */
final class BackgroundPoster implements Runnable {
    private final PendingPostQueue f2a;
    private final EventBus f3b;
    private volatile boolean f4c;

    BackgroundPoster(EventBus eventBus) {
        this.f3b = eventBus;
        this.f2a = new PendingPostQueue();
    }

    public void m1a(Subscription subscription, Object obj) {
        PendingPost a = PendingPost.m20a(subscription, obj);
        synchronized (this) {
            this.f2a.m24a(a);
            if (!this.f4c) {
                this.f4c = true;
                this.f3b.m16b().execute(this);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r5 = this;
        r4 = 0;
    L_0x0001:
        r0 = r5.f2a;	 Catch:{ InterruptedException -> 0x0022 }
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = r0.m23a(r1);	 Catch:{ InterruptedException -> 0x0022 }
        if (r0 != 0) goto L_0x001c;
    L_0x000b:
        monitor-enter(r5);	 Catch:{ InterruptedException -> 0x0022 }
        r0 = r5.f2a;	 Catch:{ all -> 0x0046 }
        r0 = r0.m22a();	 Catch:{ all -> 0x0046 }
        if (r0 != 0) goto L_0x001b;
    L_0x0014:
        r0 = 0;
        r5.f4c = r0;	 Catch:{ all -> 0x0046 }
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
        r5.f4c = r4;
    L_0x001a:
        return;
    L_0x001b:
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
    L_0x001c:
        r1 = r5.f3b;	 Catch:{ InterruptedException -> 0x0022 }
        r1.m13a(r0);	 Catch:{ InterruptedException -> 0x0022 }
        goto L_0x0001;
    L_0x0022:
        r0 = move-exception;
        r1 = "Event";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0049 }
        r2.<init>();	 Catch:{ all -> 0x0049 }
        r3 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0049 }
        r3 = r3.getName();	 Catch:{ all -> 0x0049 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0049 }
        r3 = " was interruppted";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0049 }
        r2 = r2.toString();	 Catch:{ all -> 0x0049 }
        android.util.Log.w(r1, r2, r0);	 Catch:{ all -> 0x0049 }
        r5.f4c = r4;
        goto L_0x001a;
    L_0x0046:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
        throw r0;	 Catch:{ InterruptedException -> 0x0022 }
    L_0x0049:
        r0 = move-exception;
        r5.f4c = r4;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.b.run():void");
    }
}
