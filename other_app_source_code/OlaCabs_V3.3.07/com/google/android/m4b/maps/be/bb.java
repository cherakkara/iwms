package com.google.android.m4b.maps.be;

import android.os.Looper;
import com.google.p025a.p026a.Preconditions;

/* compiled from: ThreadCheckerImpl */
public final class bb extends ba {
    private static final ba f5711c;
    private final Thread f5712a;
    private final String f5713b;

    static {
        bb.class.desiredAssertionStatus();
        f5711c = new bb(Looper.getMainLooper().getThread(), "Not on the main thread");
    }

    private bb(Thread thread, String str) {
        this.f5712a = thread;
        this.f5713b = str;
    }

    public static ba m8775b() {
        return f5711c;
    }

    public final void m8776a() {
        Preconditions.m1829b(Thread.currentThread() == this.f5712a, this.f5713b);
    }
}
