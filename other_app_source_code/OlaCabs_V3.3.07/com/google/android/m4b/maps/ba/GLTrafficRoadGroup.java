package com.google.android.m4b.maps.ba;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.aj;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.GeometryUtil;
import com.google.android.m4b.maps.az.ColorBuffer;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashSet;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.s */
public final class GLTrafficRoadGroup extends GLFeature {
    private static final int[] f5354b;
    private static volatile boolean f5355c;
    private static final float f5356i;
    private final ac f5357d;
    private final VertexBuffer f5358e;
    private final ColorBuffer f5359f;
    private final TexCoordBuffer f5360g;
    private final IndexBuffer f5361h;

    /* renamed from: com.google.android.m4b.maps.ba.s.a */
    static class GLTrafficRoadGroup {
        int f5352a;
        int f5353b;

        private GLTrafficRoadGroup() {
        }
    }

    static {
        f5354b = new int[]{12, 13, 14, 15, 16, 17, 18, 19};
        f5355c = false;
        f5356i = (float) Math.sqrt(2.0d);
    }

    public static GLTrafficRoadGroup m8119a(ac acVar, String[] strArr, VectorTile vectorTile) {
        Set hashSet = new HashSet();
        GLTrafficRoadGroup gLTrafficRoadGroup = new GLTrafficRoadGroup();
        vectorTile.m5614b();
        while (vectorTile.hasNext()) {
            bc bcVar = (bc) vectorTile.next();
            if (!(bcVar instanceof aj)) {
                break;
            }
            Object obj;
            int b = ((aj) bcVar).m5553a().m6020b() - 1;
            int i = b * 4;
            if (gLTrafficRoadGroup.f5352a + i > AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH) {
                obj = null;
            } else {
                gLTrafficRoadGroup.f5352a = i + gLTrafficRoadGroup.f5352a;
                gLTrafficRoadGroup.f5353b = (((b - 1) + (b * 2)) * 3) + gLTrafficRoadGroup.f5353b;
                obj = 1;
            }
            if (obj == null) {
                break;
            }
            for (int i2 : bcVar.m5550k()) {
                if (i2 >= 0 && i2 < strArr.length) {
                    hashSet.add(strArr[i2]);
                }
            }
        }
        vectorTile.m5615c();
        GLTrafficRoadGroup gLTrafficRoadGroup2 = new GLTrafficRoadGroup(acVar, gLTrafficRoadGroup, hashSet);
        Rectangle2D i3 = acVar.m5446i();
        GeometryUtil a = GeometryUtil.m7546a();
        while (vectorTile.hasNext()) {
            bc a2 = vectorTile.m5613a();
            if (!(a2 instanceof aj)) {
                return gLTrafficRoadGroup2;
            }
            aj ajVar = (aj) a2;
            Polyline b2 = ajVar.m5553a().m6021b((((float) i3.m6053f()) * br.DEFAULT_BACKOFF_MULT) / 256.0f);
            int b3 = b2.m6020b() - 1;
            int a3 = gLTrafficRoadGroup2.f5358e.m7725a() + (b3 * 4);
            Point c = i3.m6050c();
            i = i3.m6053f();
            Style e = ajVar.m5557e();
            if (e.m6094b() > 0) {
                float f;
                float c2 = e.m6095b(0).m6084c();
                int b4 = e.m6095b(e.m6094b() - 1).m6083b();
                float f2 = ((((float) i) * c2) * 1.25f) / 256.0f;
                if (!ajVar.m5555c()) {
                    f2 = -f2;
                }
                gLTrafficRoadGroup2.f5359f.m7645a(a3);
                gLTrafficRoadGroup2.f5359f.m7650b(b4, b3 * 4);
                switch (ajVar.m5559g()) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        f = 360.0f / c2;
                        break;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        f = 240.0f / c2;
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        f = 160.0f / c2;
                        break;
                    default:
                        f = 80.0f / c2;
                        break;
                }
                a.m7552a(b2, f2, c, i, f, gLTrafficRoadGroup2.f5358e, gLTrafficRoadGroup2.f5361h, gLTrafficRoadGroup2.f5360g, null);
            }
            vectorTile.next();
        }
        return gLTrafficRoadGroup2;
    }

    private GLTrafficRoadGroup(ac acVar, GLTrafficRoadGroup gLTrafficRoadGroup, Set<String> set) {
        super(set);
        this.f5357d = acVar;
        this.f5358e = new VertexBuffer(gLTrafficRoadGroup.f5352a);
        this.f5359f = new ColorBuffer(gLTrafficRoadGroup.f5352a);
        this.f5360g = new TexCoordBuffer(gLTrafficRoadGroup.f5352a);
        this.f5361h = new IndexBuffer(gLTrafficRoadGroup.f5353b);
    }

    public static void m8120a(GLState gLState) {
        GL10 x = gLState.m7541x();
        gLState.m7533p();
        x.glBlendFunc(1, 771);
        x.glTexEnvx(8960, 8704, 8448);
    }

    public final void m8124b(GLState gLState) {
        this.f5358e.m7732b(gLState);
        this.f5359f.m7648a(gLState);
        this.f5360g.m7702b(gLState);
        this.f5361h.m7671b(gLState);
    }

    public final void m8125c(GLState gLState) {
        this.f5358e.m7734c(gLState);
        this.f5359f.m7651b(gLState);
        this.f5360g.m7705c(gLState);
        this.f5361h.m7673c(gLState);
    }

    public final void m8122a(GLState gLState, Camera camera, ad adVar) {
        if (this.f5361h.m7669b() != 0) {
            this.f5358e.m7736d(gLState);
            this.f5360g.m7706d(gLState);
            Object obj = camera.m7444l() - ((float) this.f5357d.m5439b()) >= 0.5f ? 1 : null;
            if (obj != null) {
                gLState.m7541x().glMatrixMode(5890);
                gLState.m7541x().glLoadIdentity();
                gLState.m7541x().glScalef(f5356i, 0.0f, 0.0f);
                gLState.m7541x().glMatrixMode(5888);
            }
            com.google.android.m4b.maps.av.ac a = adVar.m6701a();
            if (a == com.google.android.m4b.maps.av.ac.NORMAL || a == com.google.android.m4b.maps.av.ac.TERRAIN) {
                gLState.m7513a().m7630a(10).m7613a(gLState.m7541x());
                gLState.m7541x().glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
                this.f5361h.m7665a(gLState, 4);
            }
            gLState.m7513a().m7630a(11).m7613a(gLState.m7541x());
            gLState.m7531n();
            this.f5359f.m7652c(gLState);
            this.f5361h.m7665a(gLState, 4);
            if (obj != null) {
                gLState.m7541x().glMatrixMode(5890);
                gLState.m7541x().glLoadIdentity();
                gLState.m7541x().glMatrixMode(5888);
            }
        }
    }

    public final int m8121a() {
        return ((this.f5358e.m7733c() + this.f5359f.m7644a()) + this.f5360g.m7701b()) + this.f5361h.m7672c();
    }

    public final int m8123b() {
        return (((this.f5358e.m7735d() + 184) + this.f5359f.m7649b()) + this.f5360g.m7703c()) + this.f5361h.m7674d();
    }
}
