package com.google.android.m4b.maps.p060x;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ay.GLState;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.x.e */
public final class Fade {
    private final IntegerAnimation f8039a;

    /* renamed from: com.google.android.m4b.maps.x.e.1 */
    static /* synthetic */ class Fade {
        static final /* synthetic */ int[] f8034a;

        static {
            f8034a = new int[Fade.values().length];
            try {
                f8034a[Fade.FADE_IN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8034a[Fade.FADE_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8034a[Fade.FADE_BETWEEN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.x.e.a */
    public enum Fade {
        FADE_IN,
        FADE_OUT,
        FADE_BETWEEN
    }

    public Fade(long j, Fade fade) {
        this(0, 500, fade);
    }

    public Fade(long j, long j2, Fade fade) {
        this(j, j2, fade, 0, 0);
    }

    public Fade(long j, long j2, Fade fade, int i, int i2) {
        this.f8039a = new IntegerAnimation(new DelayedLinearInterpolator(((float) j) / ((float) (j + j2))));
        this.f8039a.setDuration(j + j2);
        switch (Fade.f8034a[fade.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f8039a.m11590a(0);
                this.f8039a.m11590a((int) AccessibilityNodeInfoCompat.ACTION_CUT);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f8039a.m11590a((int) AccessibilityNodeInfoCompat.ACTION_CUT);
                this.f8039a.m11590a(0);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f8039a.m11590a(i);
                this.f8039a.m11590a(i2);
            default:
        }
    }

    public final int m11584a(GLState gLState) {
        long e = gLState.m7522e();
        if (!this.f8039a.hasStarted()) {
            this.f8039a.start();
        }
        this.f8039a.m11591a(e);
        int b = this.f8039a.m11592b();
        if (!this.f8039a.hasEnded()) {
            gLState.m7517b();
        }
        return b;
    }

    public final void m11585a() {
        this.f8039a.start();
    }
}
