package com.google.android.m4b.maps.ba;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.FloatMath;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.PointUtil2D;
import com.google.android.m4b.maps.an.Polygon2D;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.Road;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.at;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bk;
import com.google.android.m4b.maps.an.bm;
import com.google.android.m4b.maps.av.Renderer;
import com.google.android.m4b.maps.av.TextGenerator.TextGenerator;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.PointPool;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.p060x.Fade;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.ba.k */
public final class GLLineLabel extends GLLabel {
    private float f5209A;
    private final int f5210B;
    private boolean f5211C;
    private int f5212D;
    private boolean f5213E;
    private final String f5214F;
    private final float f5215G;
    private final float[] f5216H;
    private final String f5217h;
    private final TextGenerator f5218i;
    private final Polyline f5219j;
    private Polyline f5220k;
    private final float f5221l;
    private Polygon2D f5222m;
    private GLLineLabel[] f5223n;
    private final com.google.android.m4b.maps.av.TextGenerator f5224o;
    private Texture f5225p;
    private TexCoordBuffer f5226q;
    private TexCoordBuffer f5227r;
    private boolean f5228s;
    private TexCoordBuffer f5229t;
    private Fade f5230u;
    private final float f5231v;
    private final float f5232w;
    private final float f5233x;
    private int f5234y;
    private int f5235z;

    /* renamed from: com.google.android.m4b.maps.ba.k.a */
    static class GLLineLabel {
        Point f5203a;
        float f5204b;
        float f5205c;
        float f5206d;
        float f5207e;
        float f5208f;

        private GLLineLabel(Point point, Point point2, float f) {
            this.f5203a = point.m5942a(point2, 0.5f);
            this.f5204b = point.m5951c(point2) * 0.5f;
            this.f5205c = f;
            this.f5206d = PointUtil2D.m5993b(point, point2);
        }
    }

    public static GLLineLabel m8018a(Road road, LabelSource labelSource, bk bkVar, Polyline polyline, int i, boolean z, float f, float f2, TextGenerator textGenerator, Camera camera, com.google.android.m4b.maps.av.TextGenerator textGenerator2, boolean z2) {
        return GLLineLabel.m8016a((bc) road, labelSource, bkVar, polyline, i, z, f, f2, textGenerator, camera, textGenerator2, z2);
    }

    public static GLLineLabel m8017a(bm bmVar, LabelSource labelSource, bk bkVar, Polyline polyline, boolean z, float f, TextGenerator textGenerator, float f2, Camera camera, com.google.android.m4b.maps.av.TextGenerator textGenerator2, boolean z2) {
        return GLLineLabel.m8016a((bc) bmVar, labelSource, bkVar, polyline, 0, z, f, f2, textGenerator, camera, textGenerator2, z2);
    }

    private static GLLineLabel m8016a(bc bcVar, LabelSource labelSource, bk bkVar, Polyline polyline, int i, boolean z, float f, float f2, TextGenerator textGenerator, Camera camera, com.google.android.m4b.maps.av.TextGenerator textGenerator2, boolean z2) {
        Polyline b;
        float r = camera.m7450r();
        if (polyline.m6020b() > 2) {
            b = polyline.m6021b((f * r) * 0.2f);
        } else {
            b = polyline;
        }
        Style e = bcVar.m5548e();
        for (int i2 = 0; i2 < bkVar.m5841b(); i2++) {
            if (bkVar.m5839a(i2).m5825a()) {
                e = bkVar.m5839a(i2).m5834j();
                break;
            }
        }
        String a = bkVar.m5840a();
        float a2 = textGenerator2.m7262a(a, textGenerator, e != null ? e.m6102i() : null, f);
        float a3 = camera.m7419a(br.DEFAULT_BACKOFF_MULT + a2, camera.m7441i());
        if (a2 == 0.0f) {
            return null;
        }
        if (a3 > b.m6026d()) {
            return null;
        }
        GLLineLabel gLLineLabel = new GLLineLabel(bcVar, labelSource, a, e, bcVar.m5549f(), i, z, -1.0f, -1.0f, r, (int) camera.m7442j(), b, f, f2, textGenerator, a2, textGenerator2, z2);
        if (gLLineLabel.m8019c()) {
            return gLLineLabel;
        }
        return null;
    }

    private GLLineLabel(bc bcVar, LabelSource labelSource, String str, Style style, int i, int i2, boolean z, float f, float f2, float f3, int i3, Polyline polyline, float f4, float f5, TextGenerator textGenerator, float f6, com.google.android.m4b.maps.av.TextGenerator textGenerator2, boolean z2) {
        super(bcVar, labelSource, style, -1.0f, -1.0f, i, z, z2);
        this.f5228s = false;
        this.f5216H = new float[8];
        this.f5217h = str;
        this.f5214F = "L" + this.f5217h;
        this.f5219j = polyline;
        this.f5221l = f6 * f3;
        this.f5210B = i2;
        this.f5224o = textGenerator2;
        this.f5231v = f3;
        this.f5232w = f4;
        this.f5233x = f5;
        this.f5218i = textGenerator;
        this.f5213E = false;
        this.f5235z = i3;
        this.f5212D = 0;
        this.f5215G = f6 * f4;
    }

    public final float m8026n() {
        return this.f5215G;
    }

    public final String m8029t() {
        return this.f5214F;
    }

    public final boolean m8024b(Camera camera, GLState gLState) {
        this.f5212D++;
        return m8019c();
    }

    private boolean m8019c() {
        while (this.f5212D < 6) {
            if (this.f5212D > 1 && this.f5219j.m6026d() < this.f5221l * dm.DEFAULT_BACKOFF_MULT) {
                return false;
            }
            if (this.f5212D > 3 && this.f5219j.m6026d() < this.f5221l * 3.0f) {
                return false;
            }
            int i;
            float b;
            PointPool pointPool;
            Point point;
            Point point2;
            Point point3;
            Polyline a;
            float abs;
            float f;
            Point point4;
            Point point5;
            int b2;
            Point[] pointArr;
            int i2;
            int b3 = this.f5219j.m6020b() - 1;
            if (this.f5212D == 0) {
                i = 0;
                while (i < b3) {
                    b = this.f5219j.m6019b(i);
                    if (b > this.f5221l) {
                        pointPool = (PointPool) Renderer.f4572b.get();
                        point = pointPool.f4913a;
                        point2 = pointPool.f4914b;
                        Point point6 = pointPool.f4915c;
                        point3 = pointPool.f4916d;
                        this.f5219j.m6016a(i, point6);
                        this.f5219j.m6016a(i + 1, point3);
                        float f2 = (b - this.f5221l) / b;
                        Point.m5930a(point6, point3, f2 * 0.25f, point);
                        Point.m5930a(point3, point6, f2 * 0.75f, point2);
                        a = Polyline.m6010a(point, point2);
                        i = a.m6020b();
                        if (i > 2) {
                            if ((i - 1) * 3 <= this.f5217h.length()) {
                                a = null;
                            } else {
                                b3 = a.m6020b() - 1;
                                if (b3 >= 2) {
                                    b = a.m6027d(0);
                                    while (i < b3) {
                                        abs = Math.abs(a.m6027d(i) - b);
                                        if (abs > 60.0f || abs >= 300.0f) {
                                        } else {
                                            i = 1;
                                            if (i != 0) {
                                                a = null;
                                            }
                                        }
                                    }
                                }
                                i = 0;
                                if (i != 0) {
                                    a = null;
                                }
                            }
                        }
                        this.f5220k = a;
                        if (this.f5220k == null) {
                            f = this.f5233x;
                            pointPool = (PointPool) Renderer.f4572b.get();
                            point4 = pointPool.f4913a;
                            point = pointPool.f4914b;
                            point5 = pointPool.f4915c;
                            point2 = pointPool.f4916d;
                            b2 = this.f5220k.m6020b();
                            f = ((this.f5231v * this.f5232w) * f) / dm.DEFAULT_BACKOFF_MULT;
                            pointArr = new Point[(b2 * 2)];
                            this.f5220k.m6016a(0, point2);
                            for (i2 = 1; i2 < b2; i2++) {
                                this.f5220k.m6016a(i2, point5);
                                Point.m5936b(point5, point2, point4);
                                PointUtil2D.m5989a(point4, f, point);
                                pointArr[i2] = point5.m5959f(point);
                                pointArr[((b2 * 2) - i2) - 1] = point5.m5957e(point);
                                if (i2 == 1) {
                                    pointArr[0] = point2.m5959f(point);
                                    pointArr[(b2 * 2) - 1] = point2.m5957e(point);
                                }
                                point2.m5950b(point5);
                            }
                            this.f5222m = new Polygon2D(pointArr);
                            Polyline polyline = this.f5220k;
                            pointPool = (PointPool) Renderer.f4572b.get();
                            point4 = pointPool.f4913a;
                            point3 = pointPool.f4914b;
                            polyline.m6016a(0, point4);
                            polyline.m6018a(point3);
                            this.f5234y = (int) PointUtil2D.m5993b(point4, point3);
                            return true;
                        }
                        this.f5212D++;
                    } else {
                        i++;
                    }
                }
                this.f5212D = 1;
            }
            float d = this.f5219j.m6026d();
            switch (this.f5212D) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    d = (d - this.f5221l) * 0.25f;
                    a = GLLineLabel.m8015a(this.f5219j, d, this.f5221l + d);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    d = Math.min((this.f5231v * dm.DEFAULT_BACKOFF_MULT) * this.f5232w, d - this.f5221l);
                    a = GLLineLabel.m8015a(this.f5219j, d, this.f5221l + d);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    d = Math.max(0.0f, (d - this.f5221l) - ((this.f5231v * dm.DEFAULT_BACKOFF_MULT) * this.f5232w));
                    a = GLLineLabel.m8015a(this.f5219j, d, this.f5221l + d);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    d = (d - this.f5221l) * 0.33f;
                    a = GLLineLabel.m8015a(this.f5219j, d, this.f5221l + d);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    d = (d - this.f5221l) * 0.67f;
                    a = GLLineLabel.m8015a(this.f5219j, d, this.f5221l + d);
                    break;
                default:
                    a = null;
                    break;
            }
            i = a.m6020b();
            if (i > 2) {
                if ((i - 1) * 3 <= this.f5217h.length()) {
                    b3 = a.m6020b() - 1;
                    if (b3 >= 2) {
                        b = a.m6027d(0);
                        for (i = 1; i < b3; i++) {
                            abs = Math.abs(a.m6027d(i) - b);
                            if (abs > 60.0f) {
                            }
                        }
                    }
                    i = 0;
                    if (i != 0) {
                        a = null;
                    }
                } else {
                    a = null;
                }
            }
            this.f5220k = a;
            if (this.f5220k == null) {
                this.f5212D++;
            } else {
                f = this.f5233x;
                pointPool = (PointPool) Renderer.f4572b.get();
                point4 = pointPool.f4913a;
                point = pointPool.f4914b;
                point5 = pointPool.f4915c;
                point2 = pointPool.f4916d;
                b2 = this.f5220k.m6020b();
                f = ((this.f5231v * this.f5232w) * f) / dm.DEFAULT_BACKOFF_MULT;
                pointArr = new Point[(b2 * 2)];
                this.f5220k.m6016a(0, point2);
                for (i2 = 1; i2 < b2; i2++) {
                    this.f5220k.m6016a(i2, point5);
                    Point.m5936b(point5, point2, point4);
                    PointUtil2D.m5989a(point4, f, point);
                    pointArr[i2] = point5.m5959f(point);
                    pointArr[((b2 * 2) - i2) - 1] = point5.m5957e(point);
                    if (i2 == 1) {
                        pointArr[0] = point2.m5959f(point);
                        pointArr[(b2 * 2) - 1] = point2.m5957e(point);
                    }
                    point2.m5950b(point5);
                }
                this.f5222m = new Polygon2D(pointArr);
                Polyline polyline2 = this.f5220k;
                pointPool = (PointPool) Renderer.f4572b.get();
                point4 = pointPool.f4913a;
                point3 = pointPool.f4914b;
                polyline2.m6016a(0, point4);
                polyline2.m6018a(point3);
                this.f5234y = (int) PointUtil2D.m5993b(point4, point3);
                return true;
            }
        }
        return false;
    }

    public final Region2D m8027o() {
        return this.f5222m;
    }

    public final void m8023b(GLState gLState) {
        super.m7904b(gLState);
        if (this.f5225p != null) {
            this.f5225p.m7626f();
            this.f5225p = null;
        }
        if (this.f5226q != null) {
            this.f5226q.m7702b(gLState);
            this.f5227r.m7702b(gLState);
        }
    }

    public final void m8025c(GLState gLState) {
        super.m7907c(gLState);
        if (this.f5226q != null) {
            this.f5226q.m7705c(gLState);
            this.f5227r.m7705c(gLState);
        }
    }

    public final boolean m8022a(Camera camera, GLState gLState) {
        boolean z;
        float r = camera.m7450r() / this.f5231v;
        if (m7909i() && this.f) {
            z = r >= 0.25f && r <= dm.DEFAULT_BACKOFF_MULT;
            this.g = GLLabel.m7896a(r);
        } else {
            if (r < 0.9f || r > 1.25f) {
                z = false;
            } else {
                z = true;
            }
            this.g = AccessibilityNodeInfoCompat.ACTION_CUT;
        }
        if (!z) {
            return false;
        }
        this.f5235z = (int) camera.m7442j();
        this.f5209A = camera.m7443k();
        return true;
    }

    public final int m8028s() {
        int i;
        int i2 = 0;
        int i3 = this.d;
        if (this.f5212D == 0) {
            i = 1;
        } else {
            i = 0;
        }
        i += i3;
        if (this.f5209A >= 30.0f) {
            float f = (float) this.f5210B;
            i2 = (int) (Math.abs(FloatMath.sin(((float) Math.abs(this.f5235z - this.f5234y)) * 0.017453292f)) * f);
        }
        return i + i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m8020a(com.google.android.m4b.maps.ay.GLState r20, com.google.android.m4b.maps.ax.Camera r21, com.google.android.m4b.maps.av.ad r22) {
        /*
        r19 = this;
        r0 = r19;
        r2 = r0.f5213E;
        if (r2 != 0) goto L_0x02a4;
    L_0x0006:
        r3 = r22.m6701a();
        r0 = r19;
        r2 = r0.b;
        r7 = com.google.android.m4b.maps.ba.GLLabel.m7898a(r2, r3);
        r0 = r19;
        r4 = r0.b;
        r0 = r19;
        r2 = r0.a;
        r2 = r2.m5546b();
        r5 = 2;
        if (r2 != r5) goto L_0x0086;
    L_0x0021:
        r2 = 1;
    L_0x0022:
        if (r2 == 0) goto L_0x0088;
    L_0x0024:
        r2 = com.google.android.m4b.maps.av.ac.NORMAL;
        if (r3 != r2) goto L_0x0088;
    L_0x0028:
        r2 = r4.m6094b();
        if (r2 <= 0) goto L_0x0088;
    L_0x002e:
        r2 = r4.m6094b();
        r5 = 2;
        if (r2 > r5) goto L_0x0088;
    L_0x0035:
        r2 = r4.m6094b();
        r2 = r2 + -1;
        r2 = r4.m6095b(r2);
        r8 = r2.m6083b();
        r2 = com.google.android.m4b.maps.ay.ColorUtil.m7483a(r8);
        r5 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r2 < r5) goto L_0x0088;
    L_0x004b:
        r0 = r19;
        r2 = r0.b;
        if (r2 == 0) goto L_0x008d;
    L_0x0051:
        r0 = r19;
        r2 = r0.b;
        r5 = r2.m6102i();
    L_0x0059:
        r0 = r19;
        r2 = r0.f5224o;
        r0 = r19;
        r3 = r0.f5217h;
        r0 = r19;
        r4 = r0.f5218i;
        r0 = r19;
        r6 = r0.f5232w;
        r9 = 0;
        r2 = r2.m7264a(r3, r4, r5, r6, r7, r8, r9);
        r0 = r19;
        r0.f5225p = r2;
        r0 = r19;
        r2 = r0.f5225p;
        if (r2 != 0) goto L_0x00af;
    L_0x0078:
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = r20;
        r2 = r0.m7516a(r2);
        if (r2 != 0) goto L_0x008f;
    L_0x0082:
        r2 = 0;
    L_0x0083:
        if (r2 != 0) goto L_0x02a4;
    L_0x0085:
        return;
    L_0x0086:
        r2 = 0;
        goto L_0x0022;
    L_0x0088:
        r8 = com.google.android.m4b.maps.ba.GLLabel.m7900b(r4, r3);
        goto L_0x004b;
    L_0x008d:
        r5 = 0;
        goto L_0x0059;
    L_0x008f:
        r0 = r19;
        r9 = r0.f5224o;
        r0 = r19;
        r11 = r0.f5217h;
        r0 = r19;
        r12 = r0.f5218i;
        r0 = r19;
        r14 = r0.f5232w;
        r17 = 0;
        r10 = r20;
        r13 = r5;
        r15 = r7;
        r16 = r8;
        r2 = r9.m7263a(r10, r11, r12, r13, r14, r15, r16, r17);
        r0 = r19;
        r0.f5225p = r2;
    L_0x00af:
        r0 = r19;
        r2 = r0.f5225p;
        r2 = r2.m7622d();
        r2 = (float) r2;
        r0 = r19;
        r3 = r0.f5231v;
        r2 = r2 * r3;
        r3 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r2 = r2 * r3;
        r3 = 1069547520; // 0x3fc00000 float:1.5 double:5.28426686E-315;
        r5 = r2 / r3;
        r0 = r19;
        r2 = r0.f5220k;
        r2 = r2.m6020b();
        r3 = 2;
        if (r2 != r3) goto L_0x0173;
    L_0x00cf:
        r2 = com.google.android.m4b.maps.av.Renderer.f4572b;
        r2 = r2.get();
        r2 = (com.google.android.m4b.maps.ay.PointPool) r2;
        r3 = r2.f4913a;
        r2 = r2.f4914b;
        r0 = r19;
        r4 = r0.f5220k;
        r6 = 0;
        r4.m6016a(r6, r3);
        r0 = r19;
        r4 = r0.f5220k;
        r6 = 1;
        r4.m6016a(r6, r2);
        r4 = 1;
        r4 = new com.google.android.m4b.maps.ba.GLLineLabel.GLLineLabel[r4];
        r0 = r19;
        r0.f5223n = r4;
        r0 = r19;
        r4 = r0.f5223n;
        r6 = 0;
        r7 = new com.google.android.m4b.maps.ba.k$a;
        r8 = 0;
        r7.<init>(r2, r5, r8);
        r4[r6] = r7;
        r0 = r19;
        r2 = r0.f5225p;
        r2 = r2.m7615b();
        r0 = r19;
        r3 = r0.f5225p;
        r3 = r3.m7619c();
        r0 = r19;
        r4 = r0.f5223n;
        r5 = 0;
        r4 = r4[r5];
        r4.f5207e = r2;
        r0 = r19;
        r2 = r0.f5223n;
        r4 = 0;
        r2 = r2[r4];
        r2.f5208f = r3;
    L_0x0121:
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2.length;
        r3 = 1;
        if (r2 <= r3) goto L_0x0158;
    L_0x0129:
        r0 = r19;
        r2 = r0.f5223n;
        r3 = 0;
        r2 = r2[r3];
        r3 = r2.f5206d;
        r2 = 1;
    L_0x0133:
        r0 = r19;
        r4 = r0.f5223n;
        r4 = r4.length;
        if (r2 >= r4) goto L_0x0158;
    L_0x013a:
        r0 = r19;
        r4 = r0.f5223n;
        r4 = r4[r2];
        r4 = r4.f5206d;
        r4 = r4 - r3;
        r4 = java.lang.Math.abs(r4);
        r5 = 1106247680; // 0x41f00000 float:30.0 double:5.465589745E-315;
        r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r5 <= 0) goto L_0x02a0;
    L_0x014d:
        r5 = 1134886912; // 0x43a50000 float:330.0 double:5.60708635E-315;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 >= 0) goto L_0x02a0;
    L_0x0153:
        r2 = 1;
        r0 = r19;
        r0.f5211C = r2;
    L_0x0158:
        r0 = r19;
        r2 = r0.e;
        if (r2 == 0) goto L_0x016b;
    L_0x015e:
        r2 = new com.google.android.m4b.maps.x.e;
        r4 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r3 = com.google.android.m4b.maps.p060x.Fade.Fade.FADE_IN;
        r2.<init>(r4, r3);
        r0 = r19;
        r0.f5230u = r2;
    L_0x016b:
        r2 = 1;
        r0 = r19;
        r0.f5213E = r2;
        r2 = 1;
        goto L_0x0083;
    L_0x0173:
        r0 = r19;
        r6 = r0.f5220k;
        r2 = com.google.android.m4b.maps.av.Renderer.f4572b;
        r2 = r2.get();
        r2 = (com.google.android.m4b.maps.ay.PointPool) r2;
        r3 = r2.f4913a;
        r4 = r2.f4914b;
        r8 = r6.m6020b();
        r7 = r8 + -1;
        r2 = r7 * 4;
        r9 = new com.google.android.m4b.maps.az.i;
        r10 = 1;
        r9.<init>(r2, r10);
        r0 = r19;
        r0.f5226q = r9;
        r9 = new com.google.android.m4b.maps.az.i;
        r10 = 1;
        r9.<init>(r2, r10);
        r0 = r19;
        r0.f5227r = r9;
        r2 = new com.google.android.m4b.maps.ba.GLLineLabel.GLLineLabel[r7];
        r0 = r19;
        r0.f5223n = r2;
        r9 = new float[r8];
        r2 = 0;
        r10 = 0;
        r9[r2] = r10;
        r2 = 0;
        r6.m6016a(r2, r3);
        r2 = 0;
    L_0x01b0:
        if (r2 >= r7) goto L_0x01d6;
    L_0x01b2:
        r10 = r2 + 1;
        r6.m6016a(r10, r4);
        r0 = r19;
        r10 = r0.f5223n;
        r11 = new com.google.android.m4b.maps.ba.k$a;
        r12 = 0;
        r11.<init>(r4, r5, r12);
        r10[r2] = r11;
        r10 = r3.m5951c(r4);
        r11 = r2 + 1;
        r12 = r9[r2];
        r10 = r10 + r12;
        r9[r11] = r10;
        r2 = r2 + 1;
        r18 = r4;
        r4 = r3;
        r3 = r18;
        goto L_0x01b0;
    L_0x01d6:
        r0 = r19;
        r2 = r0.f5224o;
        r0 = r19;
        r3 = r0.f5217h;
        r0 = r19;
        r4 = r0.f5218i;
        r0 = r19;
        r5 = r0.b;
        if (r5 == 0) goto L_0x020e;
    L_0x01e8:
        r0 = r19;
        r5 = r0.b;
        r5 = r5.m6102i();
    L_0x01f0:
        r0 = r19;
        r6 = r0.f5232w;
        r7 = 1;
        r5 = r2.m7267a(r3, r4, r5, r6, r7);
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r5.length;
        r3 = r3 + -1;
        r3 = r5[r3];
        r3 = r2 / r3;
        r2 = 0;
    L_0x0203:
        r4 = r5.length;
        if (r2 >= r4) goto L_0x0210;
    L_0x0206:
        r4 = r5[r2];
        r4 = r4 * r3;
        r5[r2] = r4;
        r2 = r2 + 1;
        goto L_0x0203;
    L_0x020e:
        r5 = 0;
        goto L_0x01f0;
    L_0x0210:
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r9.length;
        r3 = r3 + -1;
        r3 = r9[r3];
        r3 = r2 / r3;
        r6 = new float[r8];
        r2 = 0;
    L_0x021c:
        if (r2 >= r8) goto L_0x0230;
    L_0x021e:
        r4 = r9[r2];
        r4 = r4 * r3;
        r9[r2] = r4;
        r4 = r8 + -1;
        r4 = r4 - r2;
        r7 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r10 = r9[r2];
        r7 = r7 - r10;
        r6[r4] = r7;
        r2 = r2 + 1;
        goto L_0x021c;
    L_0x0230:
        r4 = 0;
        r3 = 0;
        r2 = 0;
    L_0x0233:
        if (r2 >= r8) goto L_0x024c;
    L_0x0235:
        r7 = r9[r2];
        r4 = com.google.android.m4b.maps.ba.GLLineLabel.m8014a(r7, r5, r4);
        r7 = r5[r4];
        r9[r2] = r7;
        r7 = r6[r2];
        r3 = com.google.android.m4b.maps.ba.GLLineLabel.m8014a(r7, r5, r3);
        r7 = r5[r3];
        r6[r2] = r7;
        r2 = r2 + 1;
        goto L_0x0233;
    L_0x024c:
        r0 = r19;
        r2 = r0.f5225p;
        r3 = r2.m7615b();
        r0 = r19;
        r2 = r0.f5225p;
        r4 = r2.m7619c();
        r2 = 0;
    L_0x025d:
        if (r2 >= r8) goto L_0x0121;
    L_0x025f:
        r5 = r9[r2];
        r5 = r5 * r3;
        r7 = r8 - r2;
        r7 = r7 + -1;
        r7 = r6[r7];
        r7 = r7 * r3;
        r0 = r19;
        r10 = r0.f5226q;
        r11 = 0;
        r10.m7692a(r5, r11);
        r0 = r19;
        r10 = r0.f5226q;
        r10.m7692a(r5, r4);
        r0 = r19;
        r10 = r0.f5227r;
        r10.m7692a(r7, r4);
        r0 = r19;
        r10 = r0.f5227r;
        r11 = 0;
        r10.m7692a(r7, r11);
        if (r2 <= 0) goto L_0x029d;
    L_0x0289:
        r0 = r19;
        r7 = r0.f5223n;
        r10 = r2 + -1;
        r7 = r7[r10];
        r7.f5207e = r5;
        r0 = r19;
        r5 = r0.f5223n;
        r7 = r2 + -1;
        r5 = r5[r7];
        r5.f5208f = r4;
    L_0x029d:
        r2 = r2 + 1;
        goto L_0x025d;
    L_0x02a0:
        r2 = r2 + 1;
        goto L_0x0133;
    L_0x02a4:
        r2 = r22.m6704b();
        if (r2 != 0) goto L_0x0085;
    L_0x02aa:
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2.length;
        if (r2 <= 0) goto L_0x0085;
    L_0x02b1:
        r5 = r20.m7541x();
        r20.m7533p();
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2.length;
        r3 = 1;
        if (r2 != r3) goto L_0x04c6;
    L_0x02c0:
        r0 = r20;
        r2 = r0.f4850d;
        r0 = r20;
        r2.m7683a(r0);
    L_0x02c9:
        r0 = r19;
        r2 = r0.f5225p;
        r2.m7613a(r5);
        r0 = r19;
        r2 = r0.f5230u;
        if (r2 == 0) goto L_0x04d1;
    L_0x02d6:
        r0 = r19;
        r2 = r0.f5230u;
        r0 = r20;
        r2 = r2.m11584a(r0);
        r3 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r2 != r3) goto L_0x02e9;
    L_0x02e4:
        r3 = 0;
        r0 = r19;
        r0.f5230u = r3;
    L_0x02e9:
        r3 = r20.m7541x();
        r3.glColor4x(r2, r2, r2, r2);
        r4 = 0;
        r0 = r19;
        r2 = r0.f5229t;
        if (r2 == 0) goto L_0x0309;
    L_0x02f7:
        r2 = r21.m7443k();
        r3 = 0;
        r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r2 > 0) goto L_0x0309;
    L_0x0300:
        r2 = r21.m7442j();
        r3 = 0;
        r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r2 <= 0) goto L_0x053a;
    L_0x0309:
        r2 = com.google.android.m4b.maps.av.Renderer.f4572b;
        r2 = r2.get();
        r2 = (com.google.android.m4b.maps.ay.PointPool) r2;
        r3 = r2.f4913a;
        r2 = r2.f4914b;
        r0 = r19;
        r6 = r0.f5220k;
        r7 = 0;
        r6.m6016a(r7, r3);
        r0 = r19;
        r6 = r0.f5220k;
        r6.m6018a(r2);
        r0 = r19;
        r6 = r0.f5216H;
        r0 = r21;
        r0.m7426a(r3, r6);
        r0 = r19;
        r3 = r0.f5216H;
        r6 = 0;
        r3 = r3[r6];
        r0 = r19;
        r6 = r0.f5216H;
        r7 = 1;
        r6 = r6[r7];
        r0 = r19;
        r7 = r0.f5216H;
        r0 = r21;
        r0.m7426a(r2, r7);
        r0 = r19;
        r2 = r0.f5216H;
        r7 = 0;
        r8 = r2[r7];
        r3 = r8 - r3;
        r2[r7] = r3;
        r0 = r19;
        r2 = r0.f5216H;
        r3 = 1;
        r7 = r2[r3];
        r6 = r7 - r6;
        r2[r3] = r6;
        r0 = r19;
        r2 = r0.f5216H;
        r3 = 0;
        r2 = r2[r3];
        r0 = r19;
        r3 = r0.f5216H;
        r6 = 1;
        r6 = r3[r6];
        r3 = r2 * r2;
        r7 = r6 * r6;
        r3 = r3 + r7;
        r7 = android.util.FloatMath.sqrt(r3);
        r3 = 0;
        r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r2 < 0) goto L_0x04d7;
    L_0x0376:
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r2;
    L_0x0379:
        r2 = 0;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x04dc;
    L_0x037e:
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
    L_0x0380:
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r6 = r6 * r2;
        r6 = r6 / r7;
        r6 = r8 - r6;
        r6 = r6 * r3;
        r0 = r19;
        r3 = r0.f5229t;
        if (r3 != 0) goto L_0x03a4;
    L_0x038d:
        r3 = 0;
        r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r3 <= 0) goto L_0x04e0;
    L_0x0392:
        r0 = r19;
        r3 = r0.f5227r;
    L_0x0396:
        r0 = r19;
        r0.f5229t = r3;
        r3 = 0;
        r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r2 <= 0) goto L_0x04e6;
    L_0x039f:
        r2 = 1;
    L_0x03a0:
        r0 = r19;
        r0.f5228s = r2;
    L_0x03a4:
        r0 = r19;
        r2 = r0.f5229t;
        r0 = r19;
        r3 = r0.f5227r;
        if (r2 != r3) goto L_0x04f2;
    L_0x03ae:
        r2 = 981668463; // 0x3a83126f float:0.001 double:4.85008663E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x04e9;
    L_0x03b5:
        r0 = r19;
        r2 = r0.f5227r;
    L_0x03b9:
        r0 = r19;
        r0.f5229t = r2;
        r2 = 981668463; // 0x3a83126f float:0.001 double:4.85008663E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x04ef;
    L_0x03c4:
        r2 = 1;
    L_0x03c5:
        r0 = r19;
        r0.f5228s = r2;
    L_0x03c9:
        r0 = r19;
        r2 = r0.f5211C;
        if (r2 != 0) goto L_0x053a;
    L_0x03cf:
        r2 = 1061158912; // 0x3f400000 float:0.75 double:5.24282163E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 > 0) goto L_0x03db;
    L_0x03d5:
        r2 = -1086324736; // 0xffffffffbf400000 float:-0.75 double:NaN;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x053a;
    L_0x03db:
        r2 = r21.m7443k();
        r2 = r2 * r6;
        r3 = r2;
    L_0x03e1:
        r2 = 0;
        r4 = r2;
    L_0x03e3:
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2.length;
        if (r4 >= r2) goto L_0x0523;
    L_0x03ea:
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2.length;
        r6 = 1;
        if (r2 != r6) goto L_0x0516;
    L_0x03f2:
        r2 = 5890; // 0x1702 float:8.254E-42 double:2.91E-320;
        r5.glMatrixMode(r2);
        r5.glLoadIdentity();
        r0 = r19;
        r2 = r0.f5228s;
        if (r2 == 0) goto L_0x043f;
    L_0x0400:
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2[r4];
        r2 = r2.f5207e;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r6;
        r0 = r19;
        r6 = r0.f5223n;
        r6 = r6[r4];
        r6 = r6.f5208f;
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r6 = r6 / r7;
        r7 = 0;
        r5.glTranslatef(r2, r6, r7);
        r2 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r6 = 0;
        r7 = 0;
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r5.glRotatef(r2, r6, r7, r8);
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2[r4];
        r2 = r2.f5207e;
        r2 = -r2;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r6;
        r0 = r19;
        r6 = r0.f5223n;
        r6 = r6[r4];
        r6 = r6.f5208f;
        r6 = -r6;
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r6 = r6 / r7;
        r7 = 0;
        r5.glTranslatef(r2, r6, r7);
    L_0x043f:
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2[r4];
        r2 = r2.f5207e;
        r0 = r19;
        r6 = r0.f5223n;
        r6 = r6[r4];
        r6 = r6.f5208f;
        r7 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r5.glScalef(r2, r6, r7);
        r2 = 5888; // 0x1700 float:8.251E-42 double:2.909E-320;
        r5.glMatrixMode(r2);
    L_0x0459:
        r5.glPushMatrix();
        r0 = r19;
        r2 = r0.f5223n;
        r6 = r2[r4];
        r7 = r20.m7541x();
        r2 = com.google.android.m4b.maps.av.Renderer.f4572b;
        r2 = r2.get();
        r2 = (com.google.android.m4b.maps.ay.PointPool) r2;
        r8 = r2.f4913a;
        r2 = r2.f4914b;
        r0 = r21;
        r0.m7425a(r2);
        r9 = r6.f5203a;
        com.google.android.m4b.maps.an.Point.m5936b(r9, r2, r8);
        r2 = r21.m7449q();
        r9 = r8.m5958f();
        r9 = (float) r9;
        r9 = r9 * r2;
        r10 = r8.m5960g();
        r10 = (float) r10;
        r10 = r10 * r2;
        r8 = r8.m5962h();
        r8 = (float) r8;
        r8 = r8 * r2;
        r7.glTranslatef(r9, r10, r8);
        r8 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r9 = r6.f5206d;
        r8 = r8 - r9;
        r9 = 0;
        r10 = 0;
        r11 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r7.glRotatef(r8, r9, r10, r11);
        r8 = 0;
        r8 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1));
        if (r8 == 0) goto L_0x04ad;
    L_0x04a6:
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r9 = 0;
        r10 = 0;
        r7.glRotatef(r3, r8, r9, r10);
    L_0x04ad:
        r8 = r6.f5204b;
        r8 = r8 * r2;
        r6 = r6.f5205c;
        r2 = r2 * r6;
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r7.glScalef(r8, r2, r6);
        r2 = 5;
        r6 = 0;
        r8 = 4;
        r7.glDrawArrays(r2, r6, r8);
        r5.glPopMatrix();
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x03e3;
    L_0x04c6:
        r0 = r20;
        r2 = r0.f4853g;
        r0 = r20;
        r2.m7736d(r0);
        goto L_0x02c9;
    L_0x04d1:
        r0 = r19;
        r2 = r0.g;
        goto L_0x02e9;
    L_0x04d7:
        r2 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r3 = r2;
        goto L_0x0379;
    L_0x04dc:
        r2 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        goto L_0x0380;
    L_0x04e0:
        r0 = r19;
        r3 = r0.f5226q;
        goto L_0x0396;
    L_0x04e6:
        r2 = 0;
        goto L_0x03a0;
    L_0x04e9:
        r0 = r19;
        r2 = r0.f5226q;
        goto L_0x03b9;
    L_0x04ef:
        r2 = 0;
        goto L_0x03c5;
    L_0x04f2:
        r2 = -1165815185; // 0xffffffffba83126f float:-0.001 double:NaN;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x050f;
    L_0x04f9:
        r0 = r19;
        r2 = r0.f5227r;
    L_0x04fd:
        r0 = r19;
        r0.f5229t = r2;
        r2 = -1165815185; // 0xffffffffba83126f float:-0.001 double:NaN;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0514;
    L_0x0508:
        r2 = 1;
    L_0x0509:
        r0 = r19;
        r0.f5228s = r2;
        goto L_0x03c9;
    L_0x050f:
        r0 = r19;
        r2 = r0.f5226q;
        goto L_0x04fd;
    L_0x0514:
        r2 = 0;
        goto L_0x0509;
    L_0x0516:
        r0 = r19;
        r2 = r0.f5229t;
        r6 = r4 * 2;
        r0 = r20;
        r2.m7696a(r0, r6);
        goto L_0x0459;
    L_0x0523:
        r0 = r19;
        r2 = r0.f5223n;
        r2 = r2.length;
        r3 = 1;
        if (r2 != r3) goto L_0x0085;
    L_0x052b:
        r2 = 5890; // 0x1702 float:8.254E-42 double:2.91E-320;
        r5.glMatrixMode(r2);
        r5.glLoadIdentity();
        r2 = 5888; // 0x1700 float:8.251E-42 double:2.909E-320;
        r5.glMatrixMode(r2);
        goto L_0x0085;
    L_0x053a:
        r3 = r4;
        goto L_0x03e1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ba.k.a(com.google.android.m4b.maps.ay.e, com.google.android.m4b.maps.ax.b, com.google.android.m4b.maps.av.ad):void");
    }

    private static int m8014a(float f, float[] fArr, int i) {
        float f2 = f - fArr[i];
        if (f2 <= 0.0f) {
            return i;
        }
        while (f2 > 0.0f && i < fArr.length - 1) {
            float f3 = f - fArr[i + 1];
            if (f3 > 0.0f) {
                i++;
                f2 = f3;
            } else if ((-f3) < f2) {
                return i + 1;
            } else {
                return i;
            }
        }
        return fArr.length - 1;
    }

    private static Polyline m8015a(Polyline polyline, float f, float f2) {
        int i;
        int i2;
        Object obj;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        PointPool pointPool = (PointPool) Renderer.f4572b.get();
        Point point = pointPool.f4919g;
        Point point2 = pointPool.f4920h;
        Point point3 = pointPool.f4921i;
        Point point4 = pointPool.f4922j;
        Object obj2 = null;
        int b = polyline.m6020b() - 1;
        float f3 = f2;
        for (i = 0; i < b; i++) {
            float b2 = polyline.m6019b(i);
            f -= b2;
            float b3;
            float f4;
            if (f <= 1.0E-4f) {
                if (f < -1.0E-4f) {
                    obj2 = 1;
                    b2 = (f / b2) + br.DEFAULT_BACKOFF_MULT;
                    polyline.m6016a(i, point4);
                    polyline.m6016a(i + 1, point3);
                    Point.m5930a(point4, point3, b2, point);
                }
                i2 = i;
                while (i2 < b) {
                    b3 = polyline.m6019b(i2);
                    f4 = f3 - b3;
                    if (f4 > 1.0E-4f) {
                        if (f4 < 1.0E-4f) {
                            f3 = br.DEFAULT_BACKOFF_MULT + (f4 / b3);
                            polyline.m6016a(i2, point4);
                            polyline.m6016a(i2 + 1, point3);
                            Point.m5930a(point4, point3, f3, point2);
                            obj = 1;
                            i++;
                            i3 = ((i2 - i) + 1) + (obj2 == null ? 1 : 0);
                            if (obj == null) {
                                i4 = 1;
                            } else {
                                i4 = 0;
                            }
                            iArr = new int[((i4 + i3) * 3)];
                            if (obj2 == null) {
                                i5 = 1;
                                point.m5948a(iArr, 0);
                            } else {
                                i5 = 0;
                            }
                            i4 = i5;
                            i5 = i;
                            while (i5 <= i2) {
                                polyline.m6016a(i5, point3);
                                i = i4 + 1;
                                point3.m5948a(iArr, i4);
                                i5++;
                                i4 = i;
                            }
                            if (obj != null) {
                                point2.m5948a(iArr, i4);
                            }
                            return Polyline.m6012a(iArr);
                        }
                        obj = null;
                        i++;
                        if (obj2 == null) {
                        }
                        i3 = ((i2 - i) + 1) + (obj2 == null ? 1 : 0);
                        if (obj == null) {
                            i4 = 0;
                        } else {
                            i4 = 1;
                        }
                        iArr = new int[((i4 + i3) * 3)];
                        if (obj2 == null) {
                            i5 = 0;
                        } else {
                            i5 = 1;
                            point.m5948a(iArr, 0);
                        }
                        i4 = i5;
                        i5 = i;
                        while (i5 <= i2) {
                            polyline.m6016a(i5, point3);
                            i = i4 + 1;
                            point3.m5948a(iArr, i4);
                            i5++;
                            i4 = i;
                        }
                        if (obj != null) {
                            point2.m5948a(iArr, i4);
                        }
                        return Polyline.m6012a(iArr);
                    }
                    i2++;
                    f3 = f4;
                }
                obj = null;
                i++;
                if (obj2 == null) {
                }
                i3 = ((i2 - i) + 1) + (obj2 == null ? 1 : 0);
                if (obj == null) {
                    i4 = 1;
                } else {
                    i4 = 0;
                }
                iArr = new int[((i4 + i3) * 3)];
                if (obj2 == null) {
                    i5 = 1;
                    point.m5948a(iArr, 0);
                } else {
                    i5 = 0;
                }
                i4 = i5;
                i5 = i;
                while (i5 <= i2) {
                    polyline.m6016a(i5, point3);
                    i = i4 + 1;
                    point3.m5948a(iArr, i4);
                    i5++;
                    i4 = i;
                }
                if (obj != null) {
                    point2.m5948a(iArr, i4);
                }
                return Polyline.m6012a(iArr);
            }
            f3 -= b2;
        }
        i2 = i;
        while (i2 < b) {
            b3 = polyline.m6019b(i2);
            f4 = f3 - b3;
            if (f4 > 1.0E-4f) {
                i2++;
                f3 = f4;
            } else {
                if (f4 < 1.0E-4f) {
                    f3 = br.DEFAULT_BACKOFF_MULT + (f4 / b3);
                    polyline.m6016a(i2, point4);
                    polyline.m6016a(i2 + 1, point3);
                    Point.m5930a(point4, point3, f3, point2);
                    obj = 1;
                    i++;
                    if (obj2 == null) {
                    }
                    i3 = ((i2 - i) + 1) + (obj2 == null ? 1 : 0);
                    if (obj == null) {
                        i4 = 0;
                    } else {
                        i4 = 1;
                    }
                    iArr = new int[((i4 + i3) * 3)];
                    if (obj2 == null) {
                        i5 = 0;
                    } else {
                        i5 = 1;
                        point.m5948a(iArr, 0);
                    }
                    i4 = i5;
                    i5 = i;
                    while (i5 <= i2) {
                        polyline.m6016a(i5, point3);
                        i = i4 + 1;
                        point3.m5948a(iArr, i4);
                        i5++;
                        i4 = i;
                    }
                    if (obj != null) {
                        point2.m5948a(iArr, i4);
                    }
                    return Polyline.m6012a(iArr);
                }
                obj = null;
                i++;
                if (obj2 == null) {
                }
                i3 = ((i2 - i) + 1) + (obj2 == null ? 1 : 0);
                if (obj == null) {
                    i4 = 1;
                } else {
                    i4 = 0;
                }
                iArr = new int[((i4 + i3) * 3)];
                if (obj2 == null) {
                    i5 = 1;
                    point.m5948a(iArr, 0);
                } else {
                    i5 = 0;
                }
                i4 = i5;
                i5 = i;
                while (i5 <= i2) {
                    polyline.m6016a(i5, point3);
                    i = i4 + 1;
                    point3.m5948a(iArr, i4);
                    i5++;
                    i4 = i;
                }
                if (obj != null) {
                    point2.m5948a(iArr, i4);
                }
                return Polyline.m6012a(iArr);
            }
        }
        obj = null;
        i++;
        if (obj2 == null) {
        }
        i3 = ((i2 - i) + 1) + (obj2 == null ? 1 : 0);
        if (obj == null) {
            i4 = 0;
        } else {
            i4 = 1;
        }
        iArr = new int[((i4 + i3) * 3)];
        if (obj2 == null) {
            i5 = 0;
        } else {
            i5 = 1;
            point.m5948a(iArr, 0);
        }
        i4 = i5;
        i5 = i;
        while (i5 <= i2) {
            polyline.m6016a(i5, point3);
            i = i4 + 1;
            point3.m5948a(iArr, i4);
            i5++;
            i4 = i;
        }
        if (obj != null) {
            point2.m5948a(iArr, i4);
        }
        return Polyline.m6012a(iArr);
    }

    public final boolean m8021a(at atVar) {
        if (atVar.m5654a(this.f5220k.m6014a(0)) && atVar.m5654a(this.f5220k.m6023c())) {
            return true;
        }
        return false;
    }
}
