package com.google.android.m4b.maps.ba;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.at;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.av.ac;
import com.google.android.m4b.maps.av.al;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.p025a.p028c.ar;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.ba.h */
public abstract class GLLabel extends al {
    protected final bc f5093a;
    protected final Style f5094b;
    protected final LabelSource f5095c;
    protected final int f5096d;
    protected boolean f5097e;
    protected boolean f5098f;
    protected int f5099g;
    private float f5100h;
    private float f5101i;
    private boolean f5102j;
    private boolean f5103k;

    /* renamed from: com.google.android.m4b.maps.ba.h.1 */
    static /* synthetic */ class GLLabel {
        static final /* synthetic */ int[] f5129a;

        static {
            f5129a = new int[ac.values().length];
            try {
                f5129a[ac.HYBRID.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5129a[ac.NIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public abstract float m7910n();

    public abstract Region2D m7911o();

    public abstract String m7916t();

    protected GLLabel(bc bcVar, LabelSource labelSource, Style style, float f, float f2, int i, boolean z, boolean z2) {
        this.f5103k = false;
        this.f5098f = false;
        ar.m2515a();
        this.f5099g = AccessibilityNodeInfoCompat.ACTION_CUT;
        this.f5093a = bcVar;
        this.f5094b = style;
        this.f5095c = labelSource;
        this.f5100h = f;
        this.f5101i = f2;
        this.f5096d = i;
        this.f5102j = z;
        this.f5103k = z2;
    }

    protected final boolean m7909i() {
        return this.f5103k;
    }

    public final void m7902a(int i) {
        this.f5098f = (i & 2) != 0;
    }

    public static int m7896a(float f) {
        if (f >= br.DEFAULT_BACKOFF_MULT || f < 0.25f) {
            return AccessibilityNodeInfoCompat.ACTION_CUT;
        }
        return (int) (65536.0d * Math.sqrt(1.3333333730697632d * ((double) (f - 0.25f))));
    }

    public final GLOverlay m7908d() {
        return GLOverlay.LABELS;
    }

    public boolean m7906b(Camera camera, GLState gLState) {
        return false;
    }

    public void m7907c(GLState gLState) {
        super.m6677c(gLState);
    }

    public void m7904b(GLState gLState) {
        super.m6674b(gLState);
    }

    public final LabelSource m7912p() {
        return this.f5095c;
    }

    public final float m7913q() {
        return this.f5100h;
    }

    public final float m7914r() {
        return this.f5101i;
    }

    public int m7915s() {
        return this.f5096d;
    }

    public final bc m7917u() {
        return this.f5093a;
    }

    public final boolean m7918v() {
        return this.f5102j;
    }

    public boolean m7903a(at atVar) {
        return atVar.m5651a().m5679a(m7911o().m5747a()) && atVar.m5655a(m7911o());
    }

    public static int m7898a(Style style, ac acVar) {
        switch (GLLabel.f5129a[acVar.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return -1;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return -4144960;
            default:
                int d;
                if (style.m6098e()) {
                    d = style.m6102i().m6124d();
                } else {
                    d = ViewCompat.MEASURED_STATE_MASK;
                }
                if (d == 0) {
                    return ViewCompat.MEASURED_STATE_MASK;
                }
                return d;
        }
    }

    public static int m7900b(Style style, ac acVar) {
        switch (GLLabel.f5129a[acVar.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return -1610612736;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return ExploreByTouchHelper.INVALID_ID;
            default:
                if (style.m6098e()) {
                    return style.m6102i().m6125e();
                }
                int a = GLLabel.m7898a(style, acVar);
                int a2 = GLLabel.m7897a(a, 160);
                return GLLabel.m7901c(a) >= 192 ? 8421504 | a2 : ViewCompat.MEASURED_SIZE_MASK | a2;
        }
    }

    static int m7899b(int i) {
        int a = GLLabel.m7897a(i, (int) MotionEventCompat.ACTION_MASK);
        return GLLabel.m7901c(i) >= 192 ? a : a | ViewCompat.MEASURED_SIZE_MASK;
    }

    private static int m7897a(int i, int i2) {
        return (((i >>> 24) * i2) / MotionEventCompat.ACTION_MASK) << 24;
    }

    private static int m7901c(int i) {
        return (((((i >>> 16) & MotionEventCompat.ACTION_MASK) * 77) + (((i >>> 8) & MotionEventCompat.ACTION_MASK) * 151)) + ((i & MotionEventCompat.ACTION_MASK) * 28)) / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH;
    }

    public static float m7895a(Style style, float f, int i, int i2, float f2) {
        return Math.max((float) i, Math.min((float) i2, ((float) (style.m6098e() ? style.m6102i().m6126f() : 0)) * f)) * f2;
    }

    public final void m7905b(boolean z) {
        this.f5097e = true;
    }
}
