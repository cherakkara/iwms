package com.google.android.m4b.maps.ba;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.al.BuildingBoundProvider;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.al;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.aw;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.az.ColorBuffer;
import com.google.android.m4b.maps.az.ColorVBO;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.IndexBufferInterface;
import com.google.android.m4b.maps.az.IndexVBO;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.az.VertexVBO;
import com.google.android.m4b.maps.p060x.PopUp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.b */
public final class GLBuildingGroup extends GLFeature {
    private static final Point f5062b;
    private static final Comparator<aw> f5063c;
    private final VertexBuffer f5064d;
    private final ColorBuffer f5065e;
    private PopUp f5066f;
    private final IndexBuffer f5067g;
    private final IndexBuffer f5068h;
    private final IndexBufferInterface f5069i;
    private final Point f5070j;
    private final Point f5071k;
    private final Point f5072l;
    private final Point f5073m;
    private final Point f5074n;
    private final Point f5075o;
    private final Point f5076p;
    private final Point f5077q;
    private final Point f5078r;

    /* renamed from: com.google.android.m4b.maps.ba.b.1 */
    static class GLBuildingGroup implements Comparator<aw> {
        GLBuildingGroup() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return ((aw) obj2).m5716h() - ((aw) obj).m5716h();
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.b.a */
    static class GLBuildingGroup {
        int f5058a;
        int f5059b;
        int f5060c;
        int f5061d;

        GLBuildingGroup() {
        }
    }

    static {
        f5062b = new Point(46340, 46340, 0);
        f5063c = new GLBuildingGroup();
    }

    public static GLBuildingGroup m7827a(ac acVar, String[] strArr, VectorTile vectorTile) {
        BuildingBoundProvider h;
        if (VectorGlobalState.m7286b()) {
            h = IndoorState.m5334a().m5367h();
        } else {
            h = null;
        }
        Object arrayList = new ArrayList(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        GLBuildingGroup gLBuildingGroup = new GLBuildingGroup();
        Set hashSet = new HashSet();
        while (vectorTile.hasNext()) {
            bc a = vectorTile.m5613a();
            if (!(a instanceof aw)) {
                break;
            }
            aw awVar = (aw) a;
            int a2 = awVar.m5708a().m5571a();
            int c = awVar.m5711c();
            a2 *= 3;
            int i = (c * 4) + (gLBuildingGroup.f5058a + a2);
            if (i <= AccessibilityNodeInfoCompat.ACTION_COPY || gLBuildingGroup.f5058a <= 0) {
                gLBuildingGroup.f5058a = i;
                gLBuildingGroup.f5059b = a2 + gLBuildingGroup.f5059b;
                gLBuildingGroup.f5060c += c * 6;
                gLBuildingGroup.f5061d += c * 2;
                a2 = 1;
            } else {
                a2 = 0;
            }
            if (a2 == 0) {
                break;
            }
            for (int i2 : a.m5550k()) {
                if (i2 >= 0 && i2 < strArr.length) {
                    hashSet.add(strArr[i2]);
                }
            }
            int i3 = (awVar.m5715g() || (h != null && h.m5276a(awVar.m5712d()))) ? 1 : 0;
            if (i3 == 0) {
                arrayList.add(awVar);
            }
            vectorTile.next();
        }
        Collections.sort(arrayList, f5063c);
        Rectangle2D i4 = acVar.m5446i();
        float a3 = (float) Point.m5920a(i4.m6050c().m5949b());
        GLBuildingGroup gLBuildingGroup2 = new GLBuildingGroup(gLBuildingGroup, hashSet);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            gLBuildingGroup2.m7829a(i4, (aw) it.next(), a3);
        }
        gLBuildingGroup2.f5067g.m7666a((IndexBuffer) gLBuildingGroup2.f5069i, 0, gLBuildingGroup2.f5069i.m7556b());
        ((IndexBuffer) gLBuildingGroup2.f5069i).m7664a(null);
        return gLBuildingGroup2;
    }

    private GLBuildingGroup(GLBuildingGroup gLBuildingGroup, Set<String> set) {
        super(set);
        this.f5066f = new PopUp();
        this.f5064d = new VertexVBO(gLBuildingGroup.f5058a);
        this.f5065e = new ColorVBO(gLBuildingGroup.f5058a);
        this.f5067g = new IndexVBO(gLBuildingGroup.f5060c + gLBuildingGroup.f5059b);
        this.f5068h = new IndexVBO(gLBuildingGroup.f5061d);
        this.f5069i = new IndexBuffer(gLBuildingGroup.f5060c);
        this.f5070j = new Point();
        this.f5071k = new Point();
        this.f5072l = new Point();
        this.f5073m = new Point();
        this.f5074n = new Point();
        this.f5075o = new Point();
        this.f5076p = new Point();
        this.f5077q = new Point();
        this.f5078r = new Point();
    }

    public final void m7834b(GLState gLState) {
        this.f5064d.m7732b(gLState);
        this.f5065e.m7648a(gLState);
        this.f5067g.m7671b(gLState);
        this.f5068h.m7671b(gLState);
    }

    public final void m7835c(GLState gLState) {
        this.f5064d.m7734c(gLState);
        this.f5065e.m7651b(gLState);
        this.f5067g.m7673c(gLState);
        this.f5068h.m7673c(gLState);
    }

    public static void m7830a(GLState gLState, int i) {
        GL10 x = gLState.m7541x();
        gLState.m7537t();
        gLState.m7538u();
        x.glPolygonOffsetx(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
        if (i == 11) {
            x.glBlendFunc(0, 1);
            x.glDepthFunc(513);
        } else if (i == 12) {
            gLState.m7531n();
            x.glDepthFunc(515);
            x.glBlendFunc(770, 771);
            x.glLineWidthx(AccessibilityNodeInfoCompat.ACTION_CUT);
        }
    }

    public final void m7832a(GLState gLState, Camera camera, ad adVar) {
        if (this.f5064d.m7725a() != 0) {
            int a;
            this.f5064d.m7736d(gLState);
            this.f5065e.m7652c(gLState);
            if (this.f5066f != null) {
                a = this.f5066f.m11599a(gLState);
                if (a == AccessibilityNodeInfoCompat.ACTION_CUT) {
                    this.f5066f = null;
                } else {
                    gLState.m7541x().glScalex(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, a);
                }
            } else {
                a = AccessibilityNodeInfoCompat.ACTION_CUT;
            }
            if (adVar.m6704b() == 11) {
                this.f5067g.m7665a(gLState, 4);
            } else if (adVar.m6704b() == 12) {
                this.f5067g.m7665a(gLState, 4);
                if (a == AccessibilityNodeInfoCompat.ACTION_CUT) {
                    this.f5068h.m7665a(gLState, 1);
                }
            }
        }
    }

    private void m7829a(Rectangle2D rectangle2D, aw awVar, float f) {
        Style e = awVar.m5713e();
        al a = awVar.m5708a();
        int a2 = a.m5571a();
        int c = e.m6096c();
        if (a2 != 0 && c != 0) {
            int a3;
            Point c2 = rectangle2D.m6050c();
            int f2 = rectangle2D.m6053f();
            int h = awVar.m5716h();
            int i = awVar.m5717i();
            this.f5076p.m5945a(0, 0, (int) (((float) h) * f));
            int a4 = GLBuildingGroup.m7826a(e.m6093a(0), 160);
            if (c > 1) {
                a3 = GLBuildingGroup.m7826a(e.m6093a(1), 160);
            } else {
                a3 = (((((((a4 >> 16) & MotionEventCompat.ACTION_MASK) + 765) >> 2) << 16) | (ViewCompat.MEASURED_STATE_MASK & a4)) | (((((a4 >> 8) & MotionEventCompat.ACTION_MASK) + 765) >> 2) << 8)) | (((a4 & MotionEventCompat.ACTION_MASK) + 765) >> 2);
            }
            for (int i2 = 0; i2 < a2; i2++) {
                int a5 = this.f5064d.m7725a();
                a.m5572a(i2, this.f5070j, this.f5071k, this.f5072l);
                Point.m5936b(this.f5070j, c2, this.f5070j);
                Point.m5936b(this.f5071k, c2, this.f5071k);
                Point.m5936b(this.f5072l, c2, this.f5072l);
                if (i != 0) {
                    this.f5077q.m5945a(0, 0, (int) (((float) i) * f));
                    Point.m5931a(this.f5070j, this.f5077q, this.f5070j);
                    Point.m5931a(this.f5071k, this.f5077q, this.f5071k);
                    Point.m5931a(this.f5072l, this.f5077q, this.f5072l);
                }
                Point.m5931a(this.f5070j, this.f5076p, this.f5073m);
                Point.m5931a(this.f5071k, this.f5076p, this.f5074n);
                Point.m5931a(this.f5072l, this.f5076p, this.f5075o);
                this.f5064d.m7728a(this.f5073m, f2);
                this.f5064d.m7728a(this.f5074n, f2);
                this.f5064d.m7728a(this.f5075o, f2);
                this.f5065e.m7650b(a3, 3);
                this.f5067g.m7668a((short) a5, (short) (a5 + 1), (short) (a5 + 2));
                if (awVar.m5709a(i2, 0)) {
                    m7828a(this.f5070j, this.f5071k, this.f5073m, this.f5074n, f2, a4);
                }
                if (awVar.m5709a(i2, 1)) {
                    m7828a(this.f5071k, this.f5072l, this.f5074n, this.f5075o, f2, a4);
                }
                if (awVar.m5709a(i2, 2)) {
                    m7828a(this.f5072l, this.f5070j, this.f5075o, this.f5073m, f2, a4);
                }
            }
        }
    }

    private void m7828a(Point point, Point point2, Point point3, Point point4, int i, int i2) {
        int a = this.f5064d.m7725a();
        this.f5064d.m7728a(point, i);
        this.f5064d.m7728a(point3, i);
        this.f5064d.m7728a(point2, i);
        this.f5064d.m7728a(point4, i);
        this.f5069i.m7555a(a + 1, a, a + 3, a + 2);
        this.f5068h.m7667a((short) (a + 1), (short) (a + 3));
        Point.m5936b(point2, point, this.f5078r);
        ColorBuffer colorBuffer = this.f5065e;
        Point point5 = this.f5078r;
        int i3 = ViewCompat.MEASURED_STATE_MASK & i2;
        int i4 = (i2 >> 16) & MotionEventCompat.ACTION_MASK;
        int i5 = (i2 >> 8) & MotionEventCompat.ACTION_MASK;
        int i6 = i2 & MotionEventCompat.ACTION_MASK;
        a = (int) (Point.m5921a(point5, f5062b) / point5.m5964i());
        if (a < 0) {
            a = -a;
        }
        a = ((a * 19660) >> 16) + 45875;
        colorBuffer.m7650b(((a * i6) >> 16) | ((i3 | (((i4 * a) >> 16) << 16)) | (((i5 * a) >> 16) << 8)), 4);
    }

    public final int m7831a() {
        return ((IndexBuffer) this.f5069i).m7672c() + (this.f5068h.m7672c() + ((this.f5064d.m7733c() + this.f5065e.m7644a()) + this.f5067g.m7672c()));
    }

    public final int m7833b() {
        return ((IndexBuffer) this.f5069i).m7674d() + (this.f5068h.m7674d() + (((this.f5064d.m7735d() + 352) + this.f5065e.m7649b()) + this.f5067g.m7674d()));
    }

    private static int m7826a(int i, int i2) {
        return (Math.min((i >> 24) & MotionEventCompat.ACTION_MASK, 160) << 24) | (ViewCompat.MEASURED_SIZE_MASK & i);
    }
}
