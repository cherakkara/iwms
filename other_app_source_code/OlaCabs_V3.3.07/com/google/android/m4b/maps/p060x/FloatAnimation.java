package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;

/* renamed from: com.google.android.m4b.maps.x.f */
public final class FloatAnimation extends StateAnimation {
    private float f8040a;
    private float f8041b;
    private float f8042c;
    private boolean f8043d;

    public FloatAnimation(Interpolator interpolator) {
        this.f8043d = false;
        setInterpolator(interpolator);
    }

    public final void m11587a(float f) {
        if (this.f8043d) {
            this.f8040a = this.f8042c;
            this.f8041b = f;
            return;
        }
        this.f8040a = f;
        this.f8041b = f;
        this.f8042c = f;
        this.f8043d = true;
    }

    public final boolean isInitialized() {
        return this.f8043d;
    }

    public final float m11586a() {
        return this.f8041b;
    }

    public final float m11588b() {
        return this.f8042c;
    }
}
