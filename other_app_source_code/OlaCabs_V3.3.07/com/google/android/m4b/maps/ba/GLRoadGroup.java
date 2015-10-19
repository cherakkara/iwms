package com.google.android.m4b.maps.ba;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Road;
import com.google.android.m4b.maps.an.Stroke;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.ColorPalette;
import com.google.android.m4b.maps.ay.ColorPaletteCoordinate;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.GeometryUtil;
import com.google.android.m4b.maps.ay.StyleManager;
import com.google.android.m4b.maps.az.ColorBuffer;
import com.google.android.m4b.maps.az.ColorVBO;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.IndexVBO;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.TexCoordBufferInterface;
import com.google.android.m4b.maps.az.TexCoordVBO;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.az.VertexVBO;
import com.google.android.m4b.maps.bd.ShaderState;
import com.google.android.m4b.maps.p060x.Fade;
import com.newrelic.agent.android.api.v1.Defaults;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.p */
public final class GLRoadGroup extends GLFeature {
    private static final float f5324b;
    private float f5325A;
    private final ac f5326c;
    private final float[] f5327d;
    private final VertexBuffer f5328e;
    private final TexCoordBuffer f5329f;
    private final ColorBuffer f5330g;
    private final ColorBuffer f5331h;
    private final IndexBuffer f5332i;
    private final ColorBuffer f5333j;
    private final VertexBuffer f5334k;
    private final TexCoordBuffer f5335l;
    private final IndexBuffer f5336m;
    private final ColorBuffer f5337n;
    private final VertexBuffer f5338o;
    private final ColorPaletteCoordinate f5339p;
    private final IndexBuffer f5340q;
    private final IndexBuffer f5341r;
    private final VertexBuffer f5342s;
    private final TexCoordBuffer f5343t;
    private final IndexBuffer f5344u;
    private Fade f5345v;
    private final Point f5346w;
    private final ArrayList<Road> f5347x;
    private final ArrayList<Road> f5348y;
    private float f5349z;

    /* renamed from: com.google.android.m4b.maps.ba.p.a */
    static class GLRoadGroup {
        int f5308a;
        int f5309b;
        int f5310c;
        int f5311d;
        int f5312e;
        int f5313f;
        int f5314g;
        int f5315h;
        Boolean f5316i;

        GLRoadGroup() {
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.p.b */
    static class GLRoadGroup {
        boolean f5317a;
        boolean f5318b;
        float f5319c;
        int f5320d;
        int f5321e;
        int f5322f;
        int f5323g;

        GLRoadGroup() {
        }

        public final String toString() {
            return "RoadAttributes{draw?=" + this.f5317a + ", renderAsLine?=" + this.f5318b + ", width=" + this.f5319c + ", outline=" + Integer.toHexString(this.f5320d) + ", fill=" + Integer.toHexString(this.f5321e) + ", highlight=" + Integer.toHexString(this.f5322f) + ", dashedHighlight=" + Integer.toHexString(this.f5323g) + '}';
        }
    }

    static {
        float[] fArr = new float[]{0.7176471f, 0.7176471f, 0.8980392f, br.DEFAULT_BACKOFF_MULT};
        f5324b = (float) (1.0d / Math.sqrt(2.0d));
    }

    public static GLRoadGroup m8098a(ac acVar, String[] strArr, VectorTile vectorTile, ShaderState shaderState, ShaderState shaderState2, StyleManager styleManager, GLState gLState) {
        Rectangle2D i = acVar.m5446i();
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
        int b = acVar.m5439b();
        GLRoadGroup gLRoadGroup = new GLRoadGroup();
        GLRoadGroup gLRoadGroup2 = new GLRoadGroup();
        int i2 = -1;
        while (vectorTile.hasNext()) {
            bc a = vectorTile.m5613a();
            if (!(a instanceof Road)) {
                break;
            }
            Road road = (Road) a;
            if (road.m6065h()) {
                if (road.m6062e().m6104k() != null) {
                    if (i2 != -1) {
                        if (i2 != road.m6062e().m6104k().m6083b()) {
                            break;
                        }
                    }
                    i2 = road.m6062e().m6104k().m6083b();
                } else {
                    i2 = -4737051;
                }
            }
            GLRoadGroup.m8100a(road, gLRoadGroup2);
            if (!GLRoadGroup.m8105a(b, road, gLRoadGroup2, gLRoadGroup)) {
                break;
            }
            for (int i3 : a.m5550k()) {
                if (i3 >= 0 && i3 < strArr.length) {
                    hashSet.add(strArr[i3]);
                }
            }
            arrayList.add(road);
            vectorTile.next();
        }
        GLRoadGroup gLRoadGroup3 = new GLRoadGroup(acVar, gLRoadGroup, hashSet, gLState, i2);
        GeometryUtil a2 = GeometryUtil.m7546a();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Road road2 = (Road) it.next();
            GLRoadGroup.m8100a(road2, gLRoadGroup2);
            if (gLRoadGroup2.f5317a) {
                boolean z = false;
                boolean z2 = false;
                Style e = road2.m6062e();
                if (e.m6094b() > 0) {
                    Stroke b2 = e.m6095b(0);
                    if (b2 != null) {
                        z = b2.m6087f();
                        z2 = b2.m6088g();
                    }
                }
                Point c = i.m6050c();
                b = i.m6053f();
                Polyline a3 = road2.m6058a();
                float a4 = gLRoadGroup3.m8095a(gLRoadGroup2.f5319c, b);
                if (!(gLRoadGroup2.f5321e == 0 && gLRoadGroup2.f5320d == 0 && gLRoadGroup2.f5322f == 0)) {
                    int a5 = gLRoadGroup3.f5328e.m7725a();
                    int b3 = gLRoadGroup3.f5332i.m7669b();
                    a2.m7550a(a3, a4, c, b, gLRoadGroup3.f5328e, gLRoadGroup3.f5329f, gLRoadGroup3.f5332i, z, z2, (byte) 0);
                    int a6 = gLRoadGroup3.f5328e.m7725a() - a5;
                    gLRoadGroup3.f5330g.m7650b(gLRoadGroup2.f5321e, a6);
                    gLRoadGroup3.f5331h.m7650b(gLRoadGroup2.f5320d, a6);
                    if (!(gLRoadGroup3.f5333j == null || gLRoadGroup2.f5322f == 0)) {
                        gLRoadGroup3.f5333j.m7650b(gLRoadGroup2.f5322f, a6);
                    }
                    if (gLRoadGroup3.f5338o != null) {
                        if (gLRoadGroup2.f5318b) {
                            gLRoadGroup3.m8099a(a3, c, b, gLRoadGroup2.f5320d);
                        } else {
                            gLRoadGroup3.f5341r.m7666a(gLRoadGroup3.f5332i, b3, gLRoadGroup3.f5332i.m7669b() - b3);
                        }
                    }
                }
                if (GLRoadGroup.m8104a(gLRoadGroup3.f5326c.m5439b(), road2)) {
                    a2.m7553a(a3, a4, false, c, b, (float) br.DEFAULT_BACKOFF_MULT, gLRoadGroup3.f5342s, gLRoadGroup3.f5344u, null);
                    gLRoadGroup3.f5347x.add(road2);
                }
                if (gLRoadGroup2.f5323g != 0) {
                    int a7 = gLRoadGroup3.f5334k.m7725a();
                    a2.m7553a(a3, a4, false, c, b, (float) br.DEFAULT_BACKOFF_MULT, gLRoadGroup3.f5334k, gLRoadGroup3.f5336m, null);
                    gLRoadGroup3.f5337n.m7650b(gLRoadGroup2.f5323g, gLRoadGroup3.f5334k.m7725a() - a7);
                    gLRoadGroup3.f5348y.add(road2);
                }
                road2.m6069l();
            }
        }
        return gLRoadGroup3;
    }

    private GLRoadGroup(ac acVar, GLRoadGroup gLRoadGroup, HashSet<String> hashSet, GLState gLState, int i) {
        super(hashSet);
        this.f5346w = new Point();
        this.f5326c = acVar;
        this.f5327d = new float[]{((float) ((i >>> 16) & MotionEventCompat.ACTION_MASK)) / 255.0f, ((float) ((i >>> 8) & MotionEventCompat.ACTION_MASK)) / 255.0f, ((float) (i & MotionEventCompat.ACTION_MASK)) / 255.0f, ((float) ((i >>> 24) & MotionEventCompat.ACTION_MASK)) / 255.0f};
        this.f5328e = new VertexVBO(gLRoadGroup.f5308a, true);
        this.f5329f = new TexCoordVBO(gLRoadGroup.f5308a, true);
        this.f5331h = new ColorVBO(gLRoadGroup.f5308a, true);
        this.f5330g = new ColorVBO(gLRoadGroup.f5308a, true);
        this.f5332i = new IndexVBO(gLRoadGroup.f5309b, true);
        if (gLRoadGroup.f5316i == Boolean.TRUE) {
            this.f5333j = new ColorVBO(gLRoadGroup.f5308a);
        } else {
            this.f5333j = null;
        }
        if (gLRoadGroup.f5310c > 0) {
            this.f5338o = new VertexVBO(gLRoadGroup.f5310c);
            this.f5339p = new ColorPaletteCoordinate(gLRoadGroup.f5310c, gLState.m7507E());
            this.f5340q = new IndexVBO(gLRoadGroup.f5311d);
            this.f5341r = new IndexVBO(gLRoadGroup.f5309b);
        } else {
            this.f5338o = null;
            this.f5339p = null;
            this.f5340q = null;
            this.f5341r = null;
        }
        if (gLRoadGroup.f5312e > 0) {
            this.f5342s = new VertexVBO(gLRoadGroup.f5312e);
            this.f5343t = new TexCoordVBO(gLRoadGroup.f5312e);
            this.f5344u = new IndexVBO(gLRoadGroup.f5313f);
        } else {
            this.f5342s = null;
            this.f5343t = null;
            this.f5344u = null;
        }
        this.f5347x = new ArrayList();
        this.f5349z = -1.0f;
        if (gLRoadGroup.f5314g > 0) {
            this.f5334k = new VertexVBO(gLRoadGroup.f5314g);
            this.f5335l = new TexCoordVBO(gLRoadGroup.f5314g);
            this.f5336m = new IndexVBO(gLRoadGroup.f5315h);
            this.f5337n = new ColorVBO(gLRoadGroup.f5314g);
        } else {
            this.f5334k = null;
            this.f5335l = null;
            this.f5336m = null;
            this.f5337n = null;
        }
        this.f5348y = new ArrayList();
        this.f5325A = -1.0f;
    }

    public static int m8097a(Camera camera, com.google.android.m4b.maps.av.ac acVar) {
        int i = 0;
        float l = camera.m7444l();
        if (acVar != com.google.android.m4b.maps.av.ac.HYBRID || l <= 17.5f) {
            i = 32;
        }
        if (acVar == com.google.android.m4b.maps.av.ac.NORMAL) {
            i |= 20;
        }
        if (l >= 15.5f && (acVar == com.google.android.m4b.maps.av.ac.NORMAL || acVar == com.google.android.m4b.maps.av.ac.NIGHT)) {
            i |= 64;
        }
        return i | 384;
    }

    public static void m8102a(GLState gLState, float f, int i) {
        GL10 x = gLState.m7541x();
        x.glBlendFunc(770, 771);
        x.glTexEnvx(8960, 8704, 7681);
        gLState.m7531n();
        gLState.m7533p();
        float f2 = f - ((float) i);
        int i2 = f2 >= 4.0f ? 2 : f2 >= 3.0f ? 3 : f2 >= 2.25f ? 4 : (f2 < br.DEFAULT_BACKOFF_MULT || i < 17) ? 22 : 5;
        gLState.m7513a().m7630a(i2).m7613a(x);
    }

    public static void m8103a(GLState gLState, float f, int i, com.google.android.m4b.maps.av.ac acVar) {
        int i2;
        GL10 x = gLState.m7541x();
        x.glBlendFunc(770, 771);
        x.glTexEnvx(8960, 8704, 7681);
        gLState.m7531n();
        gLState.m7533p();
        if (acVar == com.google.android.m4b.maps.av.ac.HYBRID) {
            i2 = f < 16.0f ? 7 : 21;
        } else {
            float f2 = f - ((float) i);
            i2 = f2 >= 4.0f ? 1 : f2 >= 3.0f ? 2 : f2 >= 2.25f ? 3 : (f2 < br.DEFAULT_BACKOFF_MULT || i < 17) ? 6 : 4;
        }
        gLState.m7513a().m7630a(i2).m7613a(x);
    }

    public static void m8101a(GLState gLState) {
        GL10 x = gLState.m7541x();
        x.glBlendFunc(770, 771);
        x.glTexEnvx(8960, 8704, 8448);
        gLState.m7533p();
        gLState.m7513a().m7630a(0).m7613a(x);
    }

    public static void m8106b(GLState gLState, float f, int i) {
        GL10 x = gLState.m7541x();
        x.glBlendFunc(770, 771);
        x.glTexEnvx(8960, 8704, 7681);
        gLState.m7531n();
        gLState.m7533p();
        float f2 = f - ((float) i);
        int i2 = f2 >= 4.0f ? 25 : f2 >= 3.0f ? 26 : f2 >= 2.25f ? 27 : (f2 < br.DEFAULT_BACKOFF_MULT || i < 17) ? 29 : 28;
        gLState.m7513a().m7630a(i2).m7613a(x);
    }

    public static void m8107d(GLState gLState) {
        GL10 x = gLState.m7541x();
        x.glBlendFunc(770, 771);
        x.glTexEnvx(8960, 8704, 7681);
        gLState.m7531n();
        gLState.m7533p();
        gLState.m7513a().m7630a(30).m7613a(x);
    }

    public final void m8109a(GLState gLState, Camera camera, ad adVar) {
        float l = camera.m7444l() - ((float) this.f5326c.m5439b());
        Road road;
        int i;
        switch (adVar.m6704b()) {
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.f5338o != null && l <= 0.25f) {
                    this.f5338o.m7736d(gLState);
                    this.f5339p.m7478a(gLState);
                    ColorPalette.m7469c(gLState);
                    this.f5340q.m7665a(gLState, 1);
                    ColorPalette.m7470d(gLState);
                }
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                this.f5329f.m7706d(gLState);
                this.f5328e.m7736d(gLState);
                this.f5331h.m7652c(gLState);
                if (this.f5341r == null || l > 0.25f) {
                    this.f5332i.m7665a(gLState, 4);
                } else {
                    this.f5341r.m7665a(gLState, 4);
                }
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                this.f5329f.m7706d(gLState);
                this.f5328e.m7736d(gLState);
                this.f5330g.m7652c(gLState);
                if (this.f5341r == null || l > 0.25f) {
                    this.f5332i.m7665a(gLState, 4);
                } else {
                    this.f5341r.m7665a(gLState, 4);
                }
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                if (this.f5342s != null) {
                    float a = 6.0f * GLRoadGroup.m8094a(camera.m7444l());
                    if (((double) a) > 0.0d) {
                        if (a != this.f5349z) {
                            this.f5349z = a;
                            this.f5343t.m7695a(gLState);
                            TexCoordBufferInterface texCoordBufferInterface = this.f5343t;
                            Iterator it = this.f5347x.iterator();
                            while (it.hasNext()) {
                                road = (Road) it.next();
                                float f = br.DEFAULT_BACKOFF_MULT / a;
                                float f2 = br.DEFAULT_BACKOFF_MULT / (16.0f * a);
                                int f3 = this.f5326c.m5446i().m6053f();
                                Polyline a2 = road.m6058a();
                                int b = a2.m6020b() - 1;
                                float a3 = dm.DEFAULT_BACKOFF_MULT * m8095a(GLRoadGroup.m8096a(road.m6062e()), f3);
                                for (i = 0; i < b; i++) {
                                    int i2 = 0;
                                    int i3 = 0;
                                    f3 = 0;
                                    float b2 = a2.m6019b(i) * f2;
                                    if (b2 > 0.5f) {
                                        i2 = (int) ((a3 * f) * 32768.0f);
                                        i3 = (int) (65536.0f * b2);
                                        b2 -= (float) ((int) b2);
                                        if (b2 > 0.125f && b2 < 0.375f) {
                                            f3 = 40960;
                                        }
                                    } else {
                                        f3 = 49152;
                                    }
                                    int i4 = AccessibilityNodeInfoCompat.ACTION_PASTE - i2;
                                    i2 += AccessibilityNodeInfoCompat.ACTION_PASTE;
                                    if (road.m6064g()) {
                                        texCoordBufferInterface.m7560a(i2, i3 + f3);
                                        texCoordBufferInterface.m7560a(i4, i3 + f3);
                                        texCoordBufferInterface.m7560a(i4, f3);
                                        texCoordBufferInterface.m7560a(i2, f3);
                                    } else {
                                        texCoordBufferInterface.m7560a(i4, f3);
                                        texCoordBufferInterface.m7560a(i2, f3);
                                        texCoordBufferInterface.m7560a(i2, i3 + f3);
                                        texCoordBufferInterface.m7560a(i4, f3 + i3);
                                    }
                                }
                            }
                            this.f5345v = new Fade(0, 500, Fade.Fade.FADE_BETWEEN, 0, 100);
                        }
                        l = br.DEFAULT_BACKOFF_MULT;
                        if (this.f5345v != null) {
                            l = ((float) this.f5345v.m11584a(gLState)) / 100.0f;
                            if (l > 0.99f) {
                                this.f5345v = null;
                            }
                        }
                        gLState.m7541x().glColor4f(this.f5327d[0], this.f5327d[1], this.f5327d[2], l * this.f5327d[3]);
                        this.f5343t.m7706d(gLState);
                        this.f5342s.m7736d(gLState);
                        this.f5344u.m7665a(gLState, 4);
                    }
                }
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                if (this.f5333j != null) {
                    this.f5329f.m7706d(gLState);
                    this.f5328e.m7736d(gLState);
                    this.f5333j.m7652c(gLState);
                    if (this.f5341r == null || l > 0.25f) {
                        this.f5332i.m7665a(gLState, 4);
                    } else {
                        this.f5341r.m7665a(gLState, 4);
                    }
                }
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                if (this.f5334k != null) {
                    l = 6.0f * GLRoadGroup.m8094a(camera.m7444l());
                    if (((double) l) > 0.0d) {
                        if (l != this.f5325A) {
                            this.f5325A = l;
                            float f4 = br.DEFAULT_BACKOFF_MULT / l;
                            float f5 = br.DEFAULT_BACKOFF_MULT / (16.0f * l);
                            this.f5335l.m7695a(gLState);
                            Iterator it2 = this.f5348y.iterator();
                            i = this.f5326c.m5446i().m6053f();
                            while (it2.hasNext()) {
                                road = (Road) it2.next();
                                Polyline a4 = road.m6058a();
                                int b3 = a4.m6020b() - 1;
                                float a5 = dm.DEFAULT_BACKOFF_MULT * m8095a(GLRoadGroup.m8096a(road.m6062e()), i);
                                for (int i5 = 0; i5 < b3; i5++) {
                                    int i6 = (int) ((a5 * f4) * 32768.0f);
                                    int b4 = (int) ((a4.m6019b(i5) * f5) * 65536.0f);
                                    int i7 = AccessibilityNodeInfoCompat.ACTION_PASTE - i6;
                                    i6 += AccessibilityNodeInfoCompat.ACTION_PASTE;
                                    this.f5335l.m7693a(i6, b4);
                                    this.f5335l.m7693a(i7, b4);
                                    this.f5335l.m7693a(i7, 0);
                                    this.f5335l.m7693a(i6, 0);
                                }
                            }
                        }
                        this.f5335l.m7706d(gLState);
                        this.f5334k.m7736d(gLState);
                        this.f5337n.m7652c(gLState);
                        this.f5336m.m7665a(gLState, 4);
                    }
                }
            default:
        }
    }

    public final void m8111b(GLState gLState) {
        this.f5328e.m7732b(gLState);
        this.f5329f.m7702b(gLState);
        this.f5331h.m7648a(gLState);
        this.f5330g.m7648a(gLState);
        this.f5332i.m7671b(gLState);
        if (this.f5333j != null) {
            this.f5333j.m7648a(gLState);
        }
        if (this.f5338o != null) {
            this.f5338o.m7732b(gLState);
            this.f5339p.m7480b(gLState);
            this.f5340q.m7671b(gLState);
            this.f5341r.m7671b(gLState);
        }
        if (this.f5342s != null) {
            this.f5342s.m7732b(gLState);
            this.f5343t.m7702b(gLState);
            this.f5344u.m7671b(gLState);
        }
        if (this.f5334k != null) {
            this.f5334k.m7732b(gLState);
            this.f5335l.m7702b(gLState);
            this.f5336m.m7671b(gLState);
            this.f5337n.m7648a(gLState);
        }
    }

    public final void m8112c(GLState gLState) {
        this.f5328e.m7734c(gLState);
        this.f5329f.m7705c(gLState);
        this.f5331h.m7651b(gLState);
        this.f5330g.m7651b(gLState);
        this.f5332i.m7673c(gLState);
        if (this.f5333j != null) {
            this.f5333j.m7651b(gLState);
        }
        if (this.f5338o != null) {
            this.f5338o.m7734c(gLState);
            this.f5339p.m7481c(gLState);
            this.f5340q.m7673c(gLState);
            this.f5341r.m7673c(gLState);
        }
        if (this.f5342s != null) {
            this.f5342s.m7734c(gLState);
            this.f5343t.m7705c(gLState);
            this.f5344u.m7673c(gLState);
        }
        if (this.f5334k != null) {
            this.f5334k.m7734c(gLState);
            this.f5335l.m7705c(gLState);
            this.f5336m.m7673c(gLState);
            this.f5337n.m7651b(gLState);
        }
    }

    public final boolean m8113c() {
        return this.f5328e.m7725a() > 0;
    }

    private static float m8094a(float f) {
        int i = (int) f;
        return ((f - ((float) i) > 0.5f ? f5324b : br.DEFAULT_BACKOFF_MULT) * ((float) (1 << (30 - i)))) / 256.0f;
    }

    private static float m8096a(Style style) {
        float f = 0.0f;
        if (style.m6094b() != 0) {
            for (int i = 0; i < style.m6094b(); i++) {
                f = Math.max(f, style.m6095b(i).m6084c());
            }
        }
        return f;
    }

    private float m8095a(float f, int i) {
        return (this.f5326c.m5439b() > 14 ? 0.5f : Defaults.ACTIVITY_TRACE_MIN_UTILIZATION) * ((((float) i) * f) / 256.0f);
    }

    private static boolean m8105a(int i, Road road, GLRoadGroup gLRoadGroup, GLRoadGroup gLRoadGroup2) {
        int i2 = 0;
        Polyline a = road.m6058a();
        int b = a.m6020b();
        int i3 = b - 1;
        if (!gLRoadGroup.f5317a) {
            return true;
        }
        int a2 = GeometryUtil.m7544a(a);
        if (gLRoadGroup2.f5308a > 0 && gLRoadGroup2.f5308a + a2 > AccessibilityNodeInfoCompat.ACTION_COPY) {
            return false;
        }
        Style e = road.m6062e();
        boolean z = gLRoadGroup.f5322f != 0;
        if (gLRoadGroup2.f5316i == null) {
            gLRoadGroup2.f5316i = Boolean.valueOf(z);
        } else if (gLRoadGroup2.f5316i.booleanValue() != z) {
            return false;
        }
        if (gLRoadGroup.f5323g != 0) {
            while (i2 < e.m6094b()) {
                if (e.m6095b(i2).m6085d().length != 0) {
                    int i4 = i3 * 2;
                    gLRoadGroup2.f5314g = (i3 * 4) + gLRoadGroup2.f5314g;
                    gLRoadGroup2.f5315h += i4 * 3;
                }
                i2++;
            }
        }
        gLRoadGroup2.f5308a += a2;
        gLRoadGroup2.f5309b += GeometryUtil.m7548b(a);
        if (gLRoadGroup.f5318b) {
            gLRoadGroup2.f5310c += b;
            gLRoadGroup2.f5311d += i3 * 2;
        }
        if (!GLRoadGroup.m8104a(i, road)) {
            return true;
        }
        i2 = i3 * 2;
        gLRoadGroup2.f5312e = (i3 * 4) + gLRoadGroup2.f5312e;
        gLRoadGroup2.f5313f += i2 * 3;
        return true;
    }

    private void m8099a(Polyline polyline, Point point, int i, int i2) {
        int a = this.f5338o.m7725a();
        int b = polyline.m6020b();
        for (int i3 = 0; i3 < b; i3++) {
            polyline.m6016a(i3, this.f5346w);
            Point.m5936b(this.f5346w, point, this.f5346w);
            this.f5338o.m7728a(this.f5346w, i);
            if (i3 > 0) {
                this.f5340q.m7667a((short) ((a + i3) - 1), (short) (a + i3));
            }
        }
        this.f5339p.m7477a(i2, b);
    }

    private static boolean m8104a(int i, Road road) {
        return i >= 14 && road.m6065h();
    }

    public final int m8108a() {
        int c = (((this.f5328e.m7733c() + this.f5329f.m7701b()) + this.f5330g.m7644a()) + this.f5331h.m7644a()) + this.f5332i.m7672c();
        if (this.f5333j != null) {
            c += this.f5333j.m7644a();
        }
        if (this.f5338o != null) {
            c += this.f5338o.m7733c();
        }
        if (this.f5339p != null) {
            c += this.f5339p.m7476a();
        }
        if (this.f5340q != null) {
            c += this.f5340q.m7672c();
        }
        if (this.f5341r != null) {
            c += this.f5341r.m7672c();
        }
        if (this.f5342s != null) {
            c += this.f5342s.m7733c();
        }
        if (this.f5343t != null) {
            c += this.f5343t.m7701b();
        }
        if (this.f5344u != null) {
            c += this.f5344u.m7672c();
        }
        if (this.f5334k != null) {
            c += this.f5334k.m7733c();
        }
        if (this.f5335l != null) {
            c += this.f5335l.m7701b();
        }
        if (this.f5336m != null) {
            c += this.f5336m.m7672c();
        }
        if (this.f5337n != null) {
            return c + this.f5337n.m7644a();
        }
        return c;
    }

    public final int m8110b() {
        int d = ((((this.f5328e.m7735d() + 464) + this.f5329f.m7703c()) + this.f5330g.m7649b()) + this.f5331h.m7649b()) + this.f5332i.m7674d();
        if (this.f5333j != null) {
            d += this.f5333j.m7649b();
        }
        if (this.f5338o != null) {
            d += this.f5338o.m7735d();
        }
        if (this.f5339p != null) {
            d += this.f5339p.m7479b();
        }
        if (this.f5340q != null) {
            d += this.f5340q.m7674d();
        }
        if (this.f5341r != null) {
            d += this.f5341r.m7674d();
        }
        if (this.f5342s != null) {
            d += this.f5342s.m7735d();
        }
        if (this.f5343t != null) {
            d += this.f5343t.m7703c();
        }
        if (this.f5344u != null) {
            d += this.f5344u.m7674d();
        }
        if (this.f5334k != null) {
            d += this.f5334k.m7735d();
        }
        if (this.f5335l != null) {
            d += this.f5335l.m7703c();
        }
        if (this.f5336m != null) {
            d += this.f5336m.m7674d();
        }
        if (this.f5337n != null) {
            d += this.f5337n.m7649b();
        }
        d += 24;
        Iterator it = this.f5347x.iterator();
        int i = d;
        while (it.hasNext()) {
            i = ((Road) it.next()).m6070q() + i;
        }
        d = i + 24;
        it = this.f5348y.iterator();
        i = d;
        while (it.hasNext()) {
            i = ((Road) it.next()).m6070q() + i;
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m8100a(com.google.android.m4b.maps.an.Road r9, com.google.android.m4b.maps.ba.GLRoadGroup.GLRoadGroup r10) {
        /*
        r3 = 0;
        r4 = 1;
        r8 = 1114636288; // 0x42700000 float:60.0 double:5.507034975E-315;
        r1 = 2;
        r5 = 0;
        r6 = r9.m6062e();
        r0 = com.google.android.m4b.maps.ba.GLRoadGroup.m8096a(r6);
        r10.f5319c = r0;
        r10.f5320d = r5;
        r10.f5321e = r5;
        r10.f5322f = r5;
        r0 = r6.m6094b();
        if (r0 < r1) goto L_0x0085;
    L_0x001c:
        r0 = r6.m6095b(r5);
        r0 = r0.m6083b();
        r10.f5320d = r0;
        r0 = r6.m6095b(r4);
        r0 = r0.m6083b();
        r10.f5321e = r0;
        r0 = r6.m6094b();
        if (r0 <= r1) goto L_0x0052;
    L_0x0036:
        r0 = r1;
    L_0x0037:
        r2 = r6.m6094b();
        if (r0 >= r2) goto L_0x0194;
    L_0x003d:
        r2 = r6.m6095b(r0);
        r2 = r2.m6085d();
        r2 = r2.length;
        if (r2 != 0) goto L_0x0082;
    L_0x0048:
        r0 = r6.m6095b(r0);
        r0 = r0.m6083b();
        r10.f5322f = r0;
    L_0x0052:
        r10.f5323g = r5;
        r0 = r6.m6094b();
        if (r0 <= r1) goto L_0x00a9;
    L_0x005a:
        r0 = r1;
        r2 = r3;
    L_0x005c:
        r7 = r6.m6094b();
        if (r0 >= r7) goto L_0x00a9;
    L_0x0062:
        r7 = r6.m6095b(r0);
        r7 = r7.m6085d();
        r7 = r7.length;
        if (r7 <= 0) goto L_0x0096;
    L_0x006d:
        r2 = r6.m6095b(r0);
        r2 = r2.m6084c();
        r7 = r6.m6095b(r0);
        r7 = r7.m6083b();
        r10.f5323g = r7;
    L_0x007f:
        r0 = r0 + 1;
        goto L_0x005c;
    L_0x0082:
        r0 = r0 + 1;
        goto L_0x0037;
    L_0x0085:
        r0 = r6.m6094b();
        if (r0 <= 0) goto L_0x0052;
    L_0x008b:
        r0 = r6.m6095b(r5);
        r0 = r0.m6083b();
        r10.f5321e = r0;
        goto L_0x0052;
    L_0x0096:
        r7 = r10.f5323g;
        if (r7 == 0) goto L_0x007f;
    L_0x009a:
        r7 = r6.m6095b(r0);
        r7 = r7.m6084c();
        r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1));
        if (r7 <= 0) goto L_0x007f;
    L_0x00a6:
        r10.f5323g = r5;
        goto L_0x007f;
    L_0x00a9:
        r0 = r10.f5323g;
        if (r0 == 0) goto L_0x00af;
    L_0x00ad:
        r10.f5322f = r5;
    L_0x00af:
        r0 = r10.f5322f;
        if (r0 == 0) goto L_0x00cd;
    L_0x00b3:
        r0 = r10.f5321e;
        r0 = com.google.android.m4b.maps.ay.ColorUtil.m7483a(r0);
        r2 = r10.f5320d;
        r2 = com.google.android.m4b.maps.ay.ColorUtil.m7483a(r2);
        if (r0 < r2) goto L_0x00c9;
    L_0x00c1:
        r0 = r10.f5319c;
        r2 = 1091567616; // 0x41100000 float:9.0 double:5.39306059E-315;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x00cd;
    L_0x00c9:
        r0 = r10.f5322f;
        r10.f5321e = r0;
    L_0x00cd:
        r0 = r9.m6058a();
        r0 = r0.m6020b();
        if (r0 < r1) goto L_0x012f;
    L_0x00d7:
        r0 = r10.f5319c;
        r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1));
        if (r0 == 0) goto L_0x012f;
    L_0x00dd:
        r0 = r10.f5321e;
        if (r0 != 0) goto L_0x00f3;
    L_0x00e1:
        r0 = r10.f5320d;
        if (r0 != 0) goto L_0x00f3;
    L_0x00e5:
        r0 = r10.f5322f;
        if (r0 != 0) goto L_0x00f3;
    L_0x00e9:
        r0 = r10.f5323g;
        if (r0 != 0) goto L_0x00f3;
    L_0x00ed:
        r0 = r9.m6066i();
        if (r0 == 0) goto L_0x012f;
    L_0x00f3:
        r0 = r4;
    L_0x00f4:
        r10.f5317a = r0;
        r0 = r9.m6067j();
        if (r0 == 0) goto L_0x0192;
    L_0x00fc:
        r0 = r10.f5323g;
        if (r0 != 0) goto L_0x0192;
    L_0x0100:
        r0 = r10.f5322f;
        if (r0 != 0) goto L_0x0192;
    L_0x0104:
        r0 = r10.f5321e;
        r1 = r0 >> 16;
        r1 = r1 & 255;
        r1 = (float) r1;
        r2 = r0 >> 8;
        r2 = r2 & 255;
        r2 = (float) r2;
        r0 = r0 & 255;
        r0 = (float) r0;
        r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r3 != 0) goto L_0x0131;
    L_0x0117:
        r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r3 != 0) goto L_0x0131;
    L_0x011b:
        r0 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
    L_0x011d:
        r1 = 1117782016; // 0x42a00000 float:80.0 double:5.522576936E-315;
        r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r1 < 0) goto L_0x0190;
    L_0x0123:
        r1 = 1126170624; // 0x43200000 float:160.0 double:5.564022167E-315;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 > 0) goto L_0x0190;
    L_0x0129:
        r0 = r4;
    L_0x012a:
        if (r0 != 0) goto L_0x0192;
    L_0x012c:
        r10.f5318b = r4;
        return;
    L_0x012f:
        r0 = r5;
        goto L_0x00f4;
    L_0x0131:
        r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r3 < 0) goto L_0x0140;
    L_0x0135:
        r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r3 < 0) goto L_0x0140;
    L_0x0139:
        r2 = r2 - r0;
        r2 = r2 * r8;
        r0 = r1 - r0;
        r0 = r2 / r0;
        goto L_0x011d;
    L_0x0140:
        r3 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1));
        if (r3 <= 0) goto L_0x0153;
    L_0x0144:
        r3 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1));
        if (r3 < 0) goto L_0x0153;
    L_0x0148:
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r1 - r0;
        r0 = r2 - r0;
        r0 = r1 / r0;
        r0 = r3 - r0;
        r0 = r0 * r8;
        goto L_0x011d;
    L_0x0153:
        r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r3 < 0) goto L_0x0164;
    L_0x0157:
        r3 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r3 <= 0) goto L_0x0164;
    L_0x015b:
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r0 - r1;
        r1 = r2 - r1;
        r0 = r0 / r1;
        r0 = r0 + r3;
        r0 = r0 * r8;
        goto L_0x011d;
    L_0x0164:
        r3 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r3 <= 0) goto L_0x0176;
    L_0x0168:
        r3 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1));
        if (r3 <= 0) goto L_0x0176;
    L_0x016c:
        r3 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r2 = r2 - r1;
        r0 = r0 - r1;
        r0 = r2 / r0;
        r0 = r3 - r0;
        r0 = r0 * r8;
        goto L_0x011d;
    L_0x0176:
        r3 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r3 <= 0) goto L_0x0187;
    L_0x017a:
        r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r3 < 0) goto L_0x0187;
    L_0x017e:
        r3 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r1 = r1 - r2;
        r0 = r0 - r2;
        r0 = r1 / r0;
        r0 = r0 + r3;
        r0 = r0 * r8;
        goto L_0x011d;
    L_0x0187:
        r3 = 1086324736; // 0x40c00000 float:6.0 double:5.367157323E-315;
        r0 = r0 - r2;
        r1 = r1 - r2;
        r0 = r0 / r1;
        r0 = r3 - r0;
        r0 = r0 * r8;
        goto L_0x011d;
    L_0x0190:
        r0 = r5;
        goto L_0x012a;
    L_0x0192:
        r4 = r5;
        goto L_0x012c;
    L_0x0194:
        r0 = r1;
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ba.p.a(com.google.android.m4b.maps.an.p, com.google.android.m4b.maps.ba.p$b):void");
    }
}
