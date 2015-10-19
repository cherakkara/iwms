package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;
import com.google.p025a.p026a.Preconditions;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.x.c */
public final class DelayedLinearInterpolator implements Interpolator {
    private final float f8032a;

    public DelayedLinearInterpolator(float f) {
        boolean z = 0.0f <= f && f < br.DEFAULT_BACKOFF_MULT;
        Preconditions.m1822a(z);
        this.f8032a = f;
    }

    public final float getInterpolation(float f) {
        float min = Math.min(Math.max(f, 0.0f), br.DEFAULT_BACKOFF_MULT);
        if (min < this.f8032a) {
            return 0.0f;
        }
        return (float) (((double) (min - this.f8032a)) / (1.0d - ((double) this.f8032a)));
    }
}
