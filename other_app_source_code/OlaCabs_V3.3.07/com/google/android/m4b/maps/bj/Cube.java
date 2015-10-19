package com.google.android.m4b.maps.bj;

import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.bj.ad.PanoramaManager;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.bj.a */
public final class Cube extends GeometryDrawer {
    private float[] f6273b;

    public Cube(ad adVar, PanoramaManager panoramaManager) {
        super(adVar, panoramaManager);
        this.f6273b = new float[2];
    }

    public final void m9714a(PanoramaConfig panoramaConfig, TextureCache textureCache, int i, int i2) {
        m9710a(panoramaConfig, textureCache, i, i2, br.DEFAULT_BACKOFF_MULT, 6);
    }

    public final Grid m9713a(int i, int i2, float f, float f2, int i3, float f3, float f4, float f5, float f6, boolean z) {
        if (i3 < 0 || i3 > 5) {
            throw new IllegalArgumentException("Invalid cube face.");
        }
        if (!z) {
            i = 1;
            i2 = 1;
        }
        int i4 = i + 1;
        int i5 = i2 + 1;
        Grid grid = new Grid(i4, i5);
        for (int i6 = 0; i6 < i4; i6++) {
            float f7 = ((float) i6) / ((float) i);
            float f8 = f7 * f5;
            float a = ar.m8592a((f7 * f3) + f, 0.0f, (float) br.DEFAULT_BACKOFF_MULT);
            for (int i7 = 0; i7 < i5; i7++) {
                float f9;
                float f10;
                float f11;
                float f12 = ((float) i7) / ((float) i2);
                float f13 = f12 * f6;
                f12 = ar.m8592a((f12 * f4) + f2, 0.0f, (float) br.DEFAULT_BACKOFF_MULT);
                switch (i3) {
                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                        f9 = (dm.DEFAULT_BACKOFF_MULT * a) - br.DEFAULT_BACKOFF_MULT;
                        f10 = br.DEFAULT_BACKOFF_MULT - (f12 * dm.DEFAULT_BACKOFF_MULT);
                        f11 = -1.0f;
                        break;
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        f9 = -1.0f;
                        f10 = br.DEFAULT_BACKOFF_MULT - (f12 * dm.DEFAULT_BACKOFF_MULT);
                        f11 = br.DEFAULT_BACKOFF_MULT - (dm.DEFAULT_BACKOFF_MULT * a);
                        break;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        f9 = br.DEFAULT_BACKOFF_MULT - (dm.DEFAULT_BACKOFF_MULT * a);
                        f10 = br.DEFAULT_BACKOFF_MULT - (f12 * dm.DEFAULT_BACKOFF_MULT);
                        f11 = br.DEFAULT_BACKOFF_MULT;
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        f9 = br.DEFAULT_BACKOFF_MULT;
                        f10 = br.DEFAULT_BACKOFF_MULT - (f12 * dm.DEFAULT_BACKOFF_MULT);
                        f11 = (dm.DEFAULT_BACKOFF_MULT * a) - br.DEFAULT_BACKOFF_MULT;
                        break;
                    case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                        f9 = (dm.DEFAULT_BACKOFF_MULT * a) - br.DEFAULT_BACKOFF_MULT;
                        f10 = br.DEFAULT_BACKOFF_MULT;
                        f11 = br.DEFAULT_BACKOFF_MULT - (f12 * dm.DEFAULT_BACKOFF_MULT);
                        break;
                    case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                        f9 = (dm.DEFAULT_BACKOFF_MULT * a) - br.DEFAULT_BACKOFF_MULT;
                        f10 = -1.0f;
                        f11 = (f12 * dm.DEFAULT_BACKOFF_MULT) - br.DEFAULT_BACKOFF_MULT;
                        break;
                    default:
                        throw new IllegalStateException("Invalid cube face: " + i3);
                }
                if (!z || this.a.f6609y == null) {
                    f12 = f9;
                    f9 = f11;
                } else {
                    ar.m8596a(f9, f10, f11, this.f6273b);
                    f12 = Math.min(200.0f, this.a.f6609y.m9957a(this.f6273b[0], this.f6273b[1], null));
                    if (f12 == 0.0f) {
                        f12 = 200.0f;
                    }
                    f10 *= f12;
                    float f14 = f9 * f12;
                    f9 = f12 * f11;
                    f12 = f14;
                }
                grid.m9977a(i6, i7, f12, f10, f9, f8, f13);
            }
        }
        return grid;
    }

    public final int m9712a(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return 1;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return 2;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return 3;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return 4;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return 5;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return 6;
            default:
                throw new IllegalArgumentException("Invalid face index for cube.");
        }
    }
}
