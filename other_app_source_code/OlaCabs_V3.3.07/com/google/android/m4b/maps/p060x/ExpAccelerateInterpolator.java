package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.x.d */
public final class ExpAccelerateInterpolator implements Interpolator {
    private final float f8033a;

    public ExpAccelerateInterpolator(float f) {
        this.f8033a = 0.99f;
    }

    public final float getInterpolation(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        return (float) (1.0d - Math.pow((double) (br.DEFAULT_BACKOFF_MULT - this.f8033a), (double) f));
    }
}
