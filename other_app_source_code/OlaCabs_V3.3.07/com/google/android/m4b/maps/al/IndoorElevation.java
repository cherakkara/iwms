package com.google.android.m4b.maps.al;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.RenderPass.RenderPass;
import com.google.android.m4b.maps.av.RenderTweak;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.ColorUtil;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.be.IndoorLevelInterface;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ai;
import com.google.p025a.p028c.au;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.util.Map;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.al.f */
public final class IndoorElevation implements RenderTweak {
    private final Map<FeatureId, IndoorLevelInterface> f3297a;
    private Set<? extends com.google.android.m4b.maps.p057t.FeatureId> f3298b;
    private final float f3299c;
    private long f3300d;
    private int f3301e;
    private float f3302f;
    private boolean f3303g;

    public final /* synthetic */ int compareTo(Object obj) {
        RenderTweak renderTweak = (RenderTweak) obj;
        return renderTweak instanceof IndoorElevation ? Float.compare(this.f3299c, ((IndoorElevation) renderTweak).f3299c) : 0;
    }

    IndoorElevation(IndoorLevelInterface indoorLevelInterface) {
        this.f3297a = au.m2807a();
        this.f3300d = 0;
        this.f3301e = 0;
        this.f3302f = 0.0f;
        this.f3303g = false;
        this.f3299c = (float) indoorLevelInterface.m5806f();
        m5305a(indoorLevelInterface);
    }

    private IndoorElevation(float f) {
        this.f3297a = au.m2807a();
        this.f3300d = 0;
        this.f3301e = 0;
        this.f3302f = 0.0f;
        this.f3303g = false;
        this.f3299c = f;
    }

    public final boolean m5305a(IndoorLevelInterface indoorLevelInterface) {
        Preconditions.m1822a(((float) indoorLevelInterface.m5806f()) == this.f3299c);
        if (this.f3298b != null || ((float) indoorLevelInterface.m5806f()) != this.f3299c) {
            return false;
        }
        this.f3297a.put(indoorLevelInterface.m5803b(), indoorLevelInterface);
        return true;
    }

    public final Set<? extends com.google.android.m4b.maps.p057t.FeatureId> m5301a() {
        if (this.f3298b == null) {
            this.f3298b = ai.m2291a(this.f3297a.keySet());
        }
        return this.f3298b;
    }

    final IndoorElevation m5300a(FeatureId featureId) {
        if (!this.f3297a.containsKey(featureId)) {
            return this;
        }
        IndoorElevation indoorElevation = new IndoorElevation((float) ((IndoorLevelInterface) this.f3297a.get(featureId)).m5806f());
        for (IndoorLevelInterface indoorLevelInterface : this.f3297a.values()) {
            if (!indoorLevelInterface.m5803b().equals(featureId)) {
                indoorElevation.m5305a(indoorLevelInterface);
            }
        }
        return indoorElevation;
    }

    public final float m5306b() {
        return this.f3299c;
    }

    private boolean m5298g() {
        return this.f3301e != 0 && this.f3302f < br.DEFAULT_BACKOFF_MULT;
    }

    public final boolean m5307c() {
        return m5298g() && (this.f3301e & 3) != 0;
    }

    public final float m5299a(Camera camera, Point point) {
        float k = camera.m7443k();
        float l = camera.m7444l();
        int i = this.f3301e;
        float f = this.f3302f;
        float f2 = this.f3299c;
        l = f2 > 0.0f ? IndoorElevation.m5297a(l, 18.0f, 20.0f, 3.0f, 0.0f) : f2 < 0.0f ? IndoorElevation.m5297a(l, 18.0f, 20.0f, -3.0f, -1.0f) : 0.0f;
        l = IndoorElevation.m5297a(k, 0.0f, 10.0f, 0.0f, l);
        float a = IndoorElevation.m5296a(f);
        if ((i & 2) != 0) {
            l += a * 100.0f;
        } else if ((i & 1) != 0) {
            l += (br.DEFAULT_BACKOFF_MULT - a) * 100.0f;
        }
        return l * ((float) point.m5956e());
    }

    public final void m5304a(GLState gLState, Camera camera, ad adVar, Point point) {
        int a;
        GL10 x = gLState.m7541x();
        x.glPushMatrix();
        float q = camera.m7449q() * m5299a(camera, point);
        x.glTranslatef(0.0f, 0.0f, q);
        int i = this.f3301e;
        float a2 = IndoorElevation.m5296a(this.f3302f);
        if ((i & 4) == 0) {
            a2 = (i & 8) != 0 ? br.DEFAULT_BACKOFF_MULT - a2 : br.DEFAULT_BACKOFF_MULT;
        }
        if ((i & 16) != 0) {
            a2 = IndoorElevation.m5297a(a2, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.6f, br.DEFAULT_BACKOFF_MULT);
            a = ColorUtil.m7482a(br.DEFAULT_BACKOFF_MULT, a2, a2, a2);
        } else {
            a = ColorUtil.m7482a(a2, a2, a2, a2);
        }
        ColorUtil.m7485a(x, a);
        RenderPass b = adVar.m6705c().m7209b();
        boolean z = b == RenderPass.UNDERGROUND_COLOR || b == RenderPass.DROP_SHADOWS_INNER || (b == RenderPass.ANIMATED_ELEVATED_COLOR && q < 0.0f);
        this.f3303g = z;
        if (this.f3303g) {
            gLState.m7539v();
            x.glStencilOp(7680, 7680, 7680);
            x.glStencilFunc(514, MotionEventCompat.ACTION_MASK, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        }
    }

    public final void m5303a(GLState gLState, ad adVar) {
        GL10 x = gLState.m7541x();
        if (this.f3303g) {
            gLState.m7540w();
        }
        ColorUtil.m7485a(x, -1);
        x.glPopMatrix();
    }

    public final void m5302a(int i) {
        long b = Config.m11320a().m11334h().m10152b();
        this.f3301e = i;
        this.f3300d = b;
        this.f3302f = 0.0f;
    }

    public final void m5308d() {
        this.f3301e = 0;
        this.f3302f = 0.0f;
    }

    public final boolean m5309e() {
        float f = 0.0f;
        float b = ((float) (Config.m11320a().m11334h().m10152b() - this.f3300d)) / 500.0f;
        if (b >= 0.0f) {
            f = b > br.DEFAULT_BACKOFF_MULT ? br.DEFAULT_BACKOFF_MULT : b;
        }
        this.f3302f = f;
        return m5298g();
    }

    public final Set<FeatureId> m5310f() {
        return this.f3297a.keySet();
    }

    private static float m5297a(float f, float f2, float f3, float f4, float f5) {
        if (f <= f2) {
            return f4;
        }
        if (f >= f3) {
            return f5;
        }
        return f4 + (((f - f2) / (f3 - f2)) * (f5 - f4));
    }

    private static float m5296a(float f) {
        return (f * f) * (3.0f - (dm.DEFAULT_BACKOFF_MULT * f));
    }

    public final String toString() {
        return Objects.m1809a((Object) this).m1803a("height", this.f3299c).m1805a("animationStartTimeMs", this.f3300d).m1803a("animationPosition", this.f3302f).m1804a("animationType", this.f3301e).m1806a("featureIds", this.f3298b).toString();
    }
}
