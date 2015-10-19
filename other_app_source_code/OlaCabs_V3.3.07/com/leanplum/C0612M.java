package com.leanplum;

import android.os.Handler;

/* renamed from: com.leanplum.M */
final class C0612M {
    private static C0612M f8596a;
    private Handler f8597b;

    C0612M() {
        this.f8597b = new Handler();
    }

    public final Boolean m12513a(Runnable runnable) {
        return Boolean.valueOf(this.f8597b.post(runnable));
    }

    public final Boolean m12514a(Runnable runnable, long j) {
        return Boolean.valueOf(this.f8597b.postDelayed(runnable, j));
    }

    static C0612M m12512a() {
        if (f8596a == null) {
            f8596a = new C0612M();
        }
        return f8596a;
    }
}
