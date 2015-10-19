package com.google.android.m4b.maps.be;

import android.os.Handler;
import android.os.Looper;

/* compiled from: HandlerPostDelayer */
public final class cc {
    private final Handler f6003a;
    private final Runnable f6004b;
    private volatile boolean f6005c;

    /* renamed from: com.google.android.m4b.maps.be.cc.1 */
    class HandlerPostDelayer implements Runnable {
        private /* synthetic */ Runnable f6001a;
        private /* synthetic */ cc f6002b;

        HandlerPostDelayer(cc ccVar, Runnable runnable) {
            this.f6002b = ccVar;
            this.f6001a = runnable;
        }

        public final void run() {
            this.f6002b.f6005c = false;
            this.f6001a.run();
        }
    }

    private cc(Handler handler, Runnable runnable) {
        this.f6003a = handler;
        this.f6004b = new HandlerPostDelayer(this, runnable);
    }

    public cc(Runnable runnable) {
        this(new Handler(Looper.getMainLooper()), runnable);
    }

    public final void m9353a() {
        if (!this.f6005c) {
            this.f6005c = true;
            this.f6003a.post(this.f6004b);
        }
    }
}
