package com.google.android.m4b.maps.bd;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: com.google.android.m4b.maps.bd.a */
public abstract class Animation3D extends Animation {
    private float f5467a;

    protected final float m8216c(long j) {
        getTransformation(j, null);
        return this.f5467a;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        this.f5467a = f;
    }
}
