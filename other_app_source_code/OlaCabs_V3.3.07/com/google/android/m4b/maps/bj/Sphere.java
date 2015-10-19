package com.google.android.m4b.maps.bj;

import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.bj.ad.PanoramaManager;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.bj.c */
final class Sphere extends GeometryDrawer {
    private final float[] f6417b;

    public Sphere(ad adVar, PanoramaManager panoramaManager) {
        super(adVar, panoramaManager);
        this.f6417b = new float[4];
    }

    public final void m9853a(PanoramaConfig panoramaConfig, TextureCache textureCache, int i, int i2) {
        m9710a(panoramaConfig, textureCache, i, i2, br.DEFAULT_BACKOFF_MULT, 1);
    }

    public final Grid m9852a(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, boolean z) {
        if (i3 != 0) {
            throw new IllegalStateException("Invalid sphere face.");
        }
        int i4 = i + 1;
        int i5 = i2 + 1;
        float f7 = (br.DEFAULT_BACKOFF_MULT - f2) - f4;
        Grid grid = new Grid(i4, i5);
        for (int i6 = 0; i6 < i4; i6++) {
            float f8 = ((float) i6) / ((float) i);
            float f9 = f8 * f5;
            float a = ar.m8592a((f8 * f3) + f, 0.0f, (float) br.DEFAULT_BACKOFF_MULT);
            for (int i7 = 0; i7 < i5; i7++) {
                float f10 = ((float) i7) / ((float) i2);
                float f11 = (br.DEFAULT_BACKOFF_MULT - f10) * f6;
                f10 = ar.m8592a(((f10 * f4) + f7) * 0.5f, 0.0f, 0.5f);
                ar.m8597a(a, f10, this.f6417b, 0);
                if (z && this.a.f6609y != null) {
                    f10 = Math.min(200.0f, this.a.f6609y.m9957a(a, f10, null));
                    if (f10 == 0.0f) {
                        f10 = 200.0f;
                    }
                    for (int i8 = 0; i8 < 3; i8++) {
                        float[] fArr = this.f6417b;
                        fArr[i8] = fArr[i8] * f10;
                    }
                }
                this.f6417b[3] = br.DEFAULT_BACKOFF_MULT;
                grid.m9977a(i6, i7, this.f6417b[0], this.f6417b[1], this.f6417b[2], f9, f11);
            }
        }
        return grid;
    }

    public final int m9851a(int i) {
        return -1;
    }
}
