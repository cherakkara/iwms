package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.x.b */
public final class ConstantInterpolator implements Interpolator {
    private final float f8031a;

    public ConstantInterpolator(float f) {
        this.f8031a = br.DEFAULT_BACKOFF_MULT;
    }

    public final float getInterpolation(float f) {
        return this.f8031a;
    }
}
