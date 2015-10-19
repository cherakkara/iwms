package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;

/* renamed from: com.google.android.m4b.maps.x.g */
public final class IntegerAnimation extends StateAnimation {
    private int f8044a;
    private int f8045b;
    private int f8046c;
    private boolean f8047d;

    public IntegerAnimation(Interpolator interpolator) {
        this.f8047d = false;
        setInterpolator(interpolator);
    }

    public final void m11590a(int i) {
        if (this.f8047d) {
            this.f8044a = this.f8046c;
            this.f8045b = i;
            return;
        }
        this.f8044a = i;
        this.f8045b = i;
        this.f8046c = i;
        this.f8047d = true;
    }

    public final boolean isInitialized() {
        return this.f8047d;
    }

    public final int m11589a() {
        return this.f8045b;
    }

    public final int m11592b() {
        return this.f8046c;
    }

    public final void m11591a(long j) {
        float f = (float) this.f8044a;
        this.f8046c = Math.round((m8216c(j) * ((float) (this.f8045b - this.f8044a))) + f);
    }
}
