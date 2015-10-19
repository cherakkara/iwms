package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.x.a */
public final class ArrayAnimation<V> extends ObjectAnimation<V> {
    private final V[] f8030e;

    public ArrayAnimation(Interpolator interpolator, V[] vArr) {
        super(interpolator);
        Preconditions.m1822a(vArr.length >= 2);
        this.f8030e = vArr;
        this.a = vArr[0];
        this.b = vArr[vArr.length - 1];
        this.c = vArr[0];
        this.d = true;
    }

    protected final void m11580a(long j) {
        this.c = this.f8030e[Math.min(Math.max((int) (m8216c(j) * ((float) (this.f8030e.length - 1))), 0), this.f8030e.length - 1)];
    }

    protected final void m11581a(V v) {
        this.a = v;
    }

    protected final void m11582b(V v) {
        this.b = v;
    }

    protected final void m11583c(V v) {
        this.c = v;
    }
}
