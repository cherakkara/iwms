package com.google.android.m4b.maps.aj;

import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.aj.n */
public final class SideSwipeRotateEvent extends AndroidRotateEvent {
    private float f3236b;
    private float f3237c;

    public SideSwipeRotateEvent(int i, GmmGestureDetector gmmGestureDetector) {
        super(i, gmmGestureDetector);
    }

    public final void m5186a(float f, float f2) {
        this.f3236b = f / dm.DEFAULT_BACKOFF_MULT;
        this.f3237c = f2 / dm.DEFAULT_BACKOFF_MULT;
    }

    public final float m5185a() {
        return this.f3236b;
    }

    public final float m5187b() {
        return this.f3237c;
    }

    public final float m5188c() {
        float c = this.a.m5172c();
        return (float) ((((double) ((this.a.m5169a() - c) * Math.signum(this.a.m5171b() - this.f3237c))) * 3.141592653589793d) / 256.0d);
    }
}
