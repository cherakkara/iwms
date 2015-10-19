package com.google.android.m4b.maps.ba;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Stroke;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.an.al;
import com.google.android.m4b.maps.an.av;
import com.google.android.m4b.maps.an.bj;
import com.google.android.m4b.maps.an.bm;
import com.google.android.m4b.maps.av.RenderPass;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.ColorPalette;
import com.google.android.m4b.maps.ay.ColorPaletteCoordinate;
import com.google.android.m4b.maps.ay.ColorUtil;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.GeometryUtil;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.IndexVBO;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.TexCoordVBO;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.az.VertexVBO;
import com.google.android.m4b.maps.ba.GLLineGroup.GLLineGroup;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.util.List;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.a */
public final class GLAreaGroup extends GLFeature {
    private static final int[] f5048b;
    private static int f5049c;
    private static final Style f5050i;
    private static final Style f5051j;
    private static final ThreadLocal<Point[]> f5052k;
    private final VertexBuffer f5053d;
    private final VertexBuffer f5054e;
    private final ColorPaletteCoordinate f5055f;
    private final ColorPaletteCoordinate f5056g;
    private final GLAreaGroup f5057h;

    /* renamed from: com.google.android.m4b.maps.ba.a.1 */
    static class GLAreaGroup extends ThreadLocal<Point[]> {
        GLAreaGroup() {
        }

        protected final /* synthetic */ Object initialValue() {
            return new Point[]{new Point(), new Point(), new Point()};
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.a.a */
    static class GLAreaGroup {
        private final VertexBuffer f5032a;
        private final IndexBuffer f5033b;
        private final TexCoordBuffer f5034c;
        private final VertexBuffer f5035d;
        private final TexCoordBuffer f5036e;
        private final int f5037f;

        public GLAreaGroup(Rectangle2D rectangle2D, List<Polyline> list, List<av> list2, GLAreaGroup gLAreaGroup) {
            int a = GeometryUtil.m7545a((List) list);
            this.f5032a = new VertexVBO(a);
            this.f5034c = new TexCoordVBO(a);
            this.f5033b = new IndexVBO(GeometryUtil.m7549b((List) list));
            for (Polyline polyline : list) {
                Polyline c;
                Point point = new Point(rectangle2D.m6050c().m5958f() + ((int) gLAreaGroup.m7805d()), rectangle2D.m6050c().m5960g() + ((int) gLAreaGroup.m7806e()));
                int f = rectangle2D.m6053f();
                int i = gLAreaGroup.m7804c() ? AccessibilityNodeInfoCompat.ACTION_CUT : 0;
                int i2 = gLAreaGroup.m7804c() ? 0 : AccessibilityNodeInfoCompat.ACTION_CUT;
                if (gLAreaGroup.m7808g() > 0.0f) {
                    c = polyline.m6024c(gLAreaGroup.m7808g());
                } else {
                    c = polyline;
                }
                GeometryUtil.m7546a().m7551a(c, gLAreaGroup.m7804c() ? gLAreaGroup.m7803b() : gLAreaGroup.m7802a(), gLAreaGroup.m7804c() ? gLAreaGroup.m7802a() : gLAreaGroup.m7803b(), point, f, i, i2, this.f5032a, this.f5033b, this.f5034c);
            }
            int i3 = 0;
            for (av a2 : list2) {
                i3 = GLAreaGroup.m7813a(a2) + i3;
            }
            if (!gLAreaGroup.m7807f() || i3 <= 0) {
                this.f5035d = null;
                this.f5036e = null;
            } else {
                this.f5035d = new VertexVBO(i3);
                this.f5036e = new TexCoordVBO(i3);
                for (av a22 : list2) {
                    m7796a(rectangle2D, a22, gLAreaGroup);
                }
            }
            this.f5037f = gLAreaGroup.m7809h();
        }

        private void m7796a(Rectangle2D rectangle2D, av avVar, GLAreaGroup gLAreaGroup) {
            if (avVar.m5698e().m6096c() != 0) {
                al a = avVar.m5694a();
                int a2 = a.m5571a();
                if (a2 != 0) {
                    int i;
                    Point point = new Point(rectangle2D.m6050c().m5958f() + ((int) gLAreaGroup.m7805d()), ((int) gLAreaGroup.m7806e()) + rectangle2D.m6050c().m5960g());
                    int f = rectangle2D.m6053f();
                    Point point2 = ((Point[]) GLAreaGroup.f5052k.get())[0];
                    Point point3 = ((Point[]) GLAreaGroup.f5052k.get())[1];
                    Point point4 = ((Point[]) GLAreaGroup.f5052k.get())[2];
                    int i2 = 0;
                    for (int i3 = 0; i3 < a2; i3++) {
                        a.m5573a(i3, point, point2, point3, point4);
                        this.f5035d.m7728a(point2, f);
                        this.f5035d.m7728a(point3, f);
                        this.f5035d.m7728a(point4, f);
                        i2 += 3;
                    }
                    if (gLAreaGroup.m7804c()) {
                        i = AccessibilityNodeInfoCompat.ACTION_CUT;
                    } else {
                        i = 0;
                    }
                    this.f5036e.m7694a(i, 0, i2);
                }
            }
        }

        public final void m7798a(GLState gLState) {
            ColorUtil.m7485a(gLState.m7541x(), this.f5037f);
            gLState.m7513a().m7630a(23).m7613a(gLState.m7541x());
            this.f5032a.m7736d(gLState);
            this.f5034c.m7706d(gLState);
            this.f5033b.m7665a(gLState, 4);
            if (!(this.f5035d == null || this.f5036e == null)) {
                this.f5035d.m7736d(gLState);
                this.f5036e.m7706d(gLState);
                gLState.m7541x().glDrawArrays(4, 0, this.f5035d.m7725a());
            }
            gLState.m7541x().glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
        }

        public final void m7800b(GLState gLState) {
            this.f5032a.m7732b(gLState);
            this.f5033b.m7671b(gLState);
            this.f5034c.m7702b(gLState);
        }

        public final void m7801c(GLState gLState) {
            this.f5032a.m7734c(gLState);
            this.f5033b.m7673c(gLState);
            this.f5034c.m7705c(gLState);
        }

        public final int m7797a() {
            int c;
            int i = 0;
            int b = this.f5034c.m7701b() + (this.f5032a.m7733c() + this.f5033b.m7672c());
            if (this.f5035d != null) {
                c = this.f5035d.m7733c();
            } else {
                c = 0;
            }
            c += b;
            if (this.f5036e != null) {
                i = this.f5036e.m7701b();
            }
            return c + i;
        }

        public final int m7799b() {
            int d;
            int i = 0;
            int c = this.f5034c.m7703c() + ((this.f5032a.m7735d() + 36) + this.f5033b.m7674d());
            if (this.f5035d != null) {
                d = this.f5035d.m7735d();
            } else {
                d = 0;
            }
            d += c;
            if (this.f5036e != null) {
                i = this.f5036e.m7703c();
            }
            return d + i;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ba.a.b */
    static class GLAreaGroup {
        private final float f5038a;
        private final float f5039b;
        private final boolean f5040c;
        private final float f5041d;
        private final float f5042e;
        private final boolean f5043f;
        private final float f5044g;
        private final int f5045h;

        public GLAreaGroup(float f, float f2, boolean z, float f3, float f4, boolean z2, float f5, int i) {
            this.f5038a = f;
            this.f5039b = f2;
            this.f5040c = z;
            this.f5041d = f3;
            this.f5042e = f4;
            this.f5043f = z2;
            this.f5044g = 0.0f;
            this.f5045h = i;
        }

        public final float m7802a() {
            return this.f5038a;
        }

        public final float m7803b() {
            return this.f5039b;
        }

        public final boolean m7804c() {
            return this.f5040c;
        }

        public final float m7805d() {
            return this.f5041d;
        }

        public final float m7806e() {
            return this.f5042e;
        }

        public final boolean m7807f() {
            return this.f5043f;
        }

        public final float m7808g() {
            return this.f5044g;
        }

        public final int m7809h() {
            return this.f5045h;
        }
    }

    static {
        f5048b = new int[]{0, 2, 2, 4, 2, 4, 4, 6};
        f5049c = AccessibilityNodeInfoCompat.ACTION_COPY;
        f5050i = new Style(-1, 2, new int[0], new Stroke[]{new Stroke(-4551839, dm.DEFAULT_BACKOFF_MULT, new int[0], 0)}, null, null, null, null);
        f5051j = new Style(-1, 2, new int[0], new Stroke[]{new Stroke(-4551839, 1.5f, new int[0], 0)}, null, null, null, null);
        f5052k = new GLAreaGroup();
    }

    private static void m7817a(GLLineGroup gLLineGroup, av avVar, List<Polyline> list, ac acVar) {
        FeatureId b;
        Style style;
        if (acVar != null) {
            b = ((bj) acVar.m5436a(TileParameters.INDOOR)).m5821b();
            style = ((float) acVar.m5439b()) > 18.0f ? f5050i : f5051j;
        } else {
            b = avVar.m5697d();
            style = avVar.m5698e();
        }
        if (avVar.m5694a().m5571a() != 0 && style.m6094b() != 0 && avVar.m5696c()) {
            int f = avVar.m5699f();
            int h = avVar.m5701h();
            String i = avVar.m5702i();
            int[] k = avVar.m5704k();
            for (Polyline polyline : list) {
                boolean z;
                if (b != null) {
                    z = true;
                } else {
                    z = false;
                }
                gLLineGroup.m7987a(new bm(-1, b, polyline, null, style, h, i, f, 0.0f, 0, k, z));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.m4b.maps.ba.GLAreaGroup m7814a(com.google.android.m4b.maps.an.ac r13, java.lang.String[] r14, com.google.android.m4b.maps.an.aq.VectorTile r15, com.google.android.m4b.maps.ba.GLLineGroup.GLLineGroup r16, com.google.android.m4b.maps.ba.GLAreaGroup.GLAreaGroup r17, int r18, com.google.android.m4b.maps.ay.GLState r19) {
        /*
        r7 = r13.m5446i();
        r8 = new java.util.ArrayList;
        r8.<init>();
        r4 = new java.util.HashSet;
        r4.<init>();
        r1 = 0;
        if (r17 == 0) goto L_0x00f1;
    L_0x0011:
        r1 = com.google.p025a.p028c.ar.m2515a();
        r6 = r1;
    L_0x0016:
        r2 = 0;
        r3 = 0;
    L_0x0018:
        r1 = r15.hasNext();
        if (r1 == 0) goto L_0x00a7;
    L_0x001e:
        r5 = r15.m5613a();
        r1 = r5 instanceof com.google.android.m4b.maps.an.av;
        if (r1 == 0) goto L_0x00a7;
    L_0x0026:
        r1 = r5;
        r1 = (com.google.android.m4b.maps.an.av) r1;
        r9 = com.google.android.m4b.maps.ba.GLAreaGroup.m7813a(r1);
        r10 = com.google.android.m4b.maps.ba.GLAreaGroup.m7819c(r1);
        r11 = f5049c;
        if (r9 > r11) goto L_0x0039;
    L_0x0035:
        r11 = f5049c;
        if (r10 <= r11) goto L_0x003d;
    L_0x0039:
        r15.next();
        goto L_0x0018;
    L_0x003d:
        r11 = r2 + r9;
        r12 = f5049c;
        if (r11 > r12) goto L_0x00a7;
    L_0x0043:
        r11 = r3 + r10;
        r12 = f5049c;
        if (r11 > r12) goto L_0x00a7;
    L_0x0049:
        r2 = r2 + r9;
        r3 = r3 + r10;
        r9 = r5.m5550k();
        r10 = r9.length;
        r5 = 0;
    L_0x0051:
        if (r5 >= r10) goto L_0x0062;
    L_0x0053:
        r11 = r9[r5];
        if (r11 < 0) goto L_0x005f;
    L_0x0057:
        r12 = r14.length;
        if (r11 >= r12) goto L_0x005f;
    L_0x005a:
        r11 = r14[r11];
        r4.add(r11);
    L_0x005f:
        r5 = r5 + 1;
        goto L_0x0051;
    L_0x0062:
        r8.add(r1);
        if (r17 == 0) goto L_0x00a5;
    L_0x0067:
        r5 = r1.m5703j();
        if (r5 == 0) goto L_0x00a5;
    L_0x006d:
        r5 = 1;
    L_0x006e:
        r9 = com.google.android.m4b.maps.ba.GLAreaGroup.m7818b(r1);
        if (r5 != 0) goto L_0x0076;
    L_0x0074:
        if (r9 != 0) goto L_0x00a0;
    L_0x0076:
        r10 = r1.m5696c();
        if (r10 == 0) goto L_0x00a0;
    L_0x007c:
        r10 = r1.m5694a();
        r11 = r1.m5700g();
        r10 = com.google.android.m4b.maps.ay.TriangleListToPolylineConverter.m7636a(r10, r11);
        if (r9 != 0) goto L_0x0090;
    L_0x008a:
        r9 = 0;
        r0 = r16;
        com.google.android.m4b.maps.ba.GLAreaGroup.m7817a(r0, r1, r10, r9);
    L_0x0090:
        r9 = r1.m5703j();
        if (r9 == 0) goto L_0x009b;
    L_0x0096:
        r0 = r16;
        com.google.android.m4b.maps.ba.GLAreaGroup.m7817a(r0, r1, r10, r13);
    L_0x009b:
        if (r5 == 0) goto L_0x00a0;
    L_0x009d:
        r6.addAll(r10);
    L_0x00a0:
        r15.next();
        goto L_0x0018;
    L_0x00a5:
        r5 = 0;
        goto L_0x006e;
    L_0x00a7:
        r5 = 0;
        if (r6 == 0) goto L_0x00d5;
    L_0x00aa:
        r1 = r6.isEmpty();
        if (r1 != 0) goto L_0x00d5;
    L_0x00b0:
        r9 = com.google.p025a.p028c.ar.m2515a();
        r5 = r8.iterator();
    L_0x00b8:
        r1 = r5.hasNext();
        if (r1 == 0) goto L_0x00ce;
    L_0x00be:
        r1 = r5.next();
        r1 = (com.google.android.m4b.maps.an.av) r1;
        r10 = r1.m5703j();
        if (r10 == 0) goto L_0x00b8;
    L_0x00ca:
        r9.add(r1);
        goto L_0x00b8;
    L_0x00ce:
        r5 = new com.google.android.m4b.maps.ba.a$a;
        r0 = r17;
        r5.<init>(r7, r6, r9, r0);
    L_0x00d5:
        r1 = new com.google.android.m4b.maps.ba.a;
        r6 = r19;
        r1.<init>(r2, r3, r4, r5, r6);
        r3 = r8.iterator();
    L_0x00e0:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x00f0;
    L_0x00e6:
        r2 = r3.next();
        r2 = (com.google.android.m4b.maps.an.av) r2;
        r1.m7815a(r7, r2);
        goto L_0x00e0;
    L_0x00f0:
        return r1;
    L_0x00f1:
        r6 = r1;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ba.a.a(com.google.android.m4b.maps.an.ac, java.lang.String[], com.google.android.m4b.maps.an.aq$b, com.google.android.m4b.maps.ba.j$a, com.google.android.m4b.maps.ba.a$b, int, com.google.android.m4b.maps.ay.e):com.google.android.m4b.maps.ba.a");
    }

    private GLAreaGroup(int i, int i2, Set<String> set, GLAreaGroup gLAreaGroup, GLState gLState) {
        super(set);
        this.f5054e = new VertexVBO(i, true);
        this.f5053d = new VertexVBO(i2, true);
        this.f5056g = new ColorPaletteCoordinate(i, gLState.m7507E());
        this.f5055f = new ColorPaletteCoordinate(i2, gLState.m7507E());
        this.f5057h = gLAreaGroup;
    }

    public final void m7824b(GLState gLState) {
        this.f5054e.m7732b(gLState);
        this.f5053d.m7732b(gLState);
        this.f5056g.m7480b(gLState);
        this.f5055f.m7480b(gLState);
        if (this.f5057h != null) {
            this.f5057h.m7800b(gLState);
        }
    }

    public final void m7825c(GLState gLState) {
        this.f5054e.m7734c(gLState);
        this.f5053d.m7734c(gLState);
        this.f5056g.m7481c(gLState);
        this.f5055f.m7481c(gLState);
        if (this.f5057h != null) {
            this.f5057h.m7801c(gLState);
        }
    }

    public static void m7816a(GLState gLState, ad adVar) {
        if (!adVar.m6705c().m7214e()) {
            gLState.m7533p();
        }
        gLState.m7541x().glLineWidthx(AccessibilityNodeInfoCompat.ACTION_CUT);
        gLState.m7541x().glBlendFunc(1, 771);
        gLState.m7541x().glTexEnvx(8960, 8704, 8448);
        gLState.m7541x().glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
    }

    public final void m7822a(GLState gLState, Camera camera, ad adVar) {
        GL10 x = gLState.m7541x();
        RenderPass c = adVar.m6705c();
        if (c.m7212c()) {
            ColorPalette.m7469c(gLState);
            if (this.f5054e.m7725a() > 0) {
                this.f5054e.m7736d(gLState);
                this.f5056g.m7478a(gLState);
                x.glDrawArrays(4, 0, this.f5054e.m7725a());
            }
            if (this.f5053d.m7725a() > 0) {
                this.f5053d.m7736d(gLState);
                this.f5055f.m7478a(gLState);
                x.glDrawArrays(1, 0, this.f5053d.m7725a());
            }
            ColorPalette.m7470d(gLState);
        } else if (c.m7214e()) {
            if (this.f5054e.m7725a() > 0) {
                this.f5054e.m7736d(gLState);
                x.glDrawArrays(4, 0, this.f5054e.m7725a());
            }
            if (this.f5053d.m7725a() > 0) {
                this.f5053d.m7736d(gLState);
                x.glDrawArrays(1, 0, this.f5053d.m7725a());
            }
        } else if (c.m7213d() && this.f5057h != null) {
            this.f5057h.m7798a(gLState);
        }
    }

    static int m7813a(av avVar) {
        if (avVar.m5698e().m6096c() == 0) {
            return 0;
        }
        return avVar.m5694a().m5571a() * 3;
    }

    private static boolean m7818b(av avVar) {
        Style e = avVar.m5698e();
        if (e.m6094b() == 0 || !avVar.m5696c()) {
            return false;
        }
        int i = 0;
        while (i < e.m6094b()) {
            if (e.m6095b(i).m6084c() > br.DEFAULT_BACKOFF_MULT || e.m6095b(i).m6086e()) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static int m7819c(av avVar) {
        int i = 0;
        if (!GLAreaGroup.m7818b(avVar)) {
            return 0;
        }
        byte[] g = avVar.m5700g();
        int i2 = 0;
        while (i < g.length) {
            i2 += f5048b[g[i] & 7];
            i++;
        }
        return i2;
    }

    private void m7815a(Rectangle2D rectangle2D, av avVar) {
        Style e = avVar.m5698e();
        al a = avVar.m5694a();
        int a2 = a.m5571a();
        if (a2 != 0) {
            Object obj;
            if (e.m6096c() > 0) {
                obj = 1;
            } else {
                obj = null;
            }
            boolean b = GLAreaGroup.m7818b(avVar);
            if (obj != null || b) {
                int b2;
                Point c = rectangle2D.m6050c();
                int f = rectangle2D.m6053f();
                int a3 = obj != null ? e.m6093a(0) : 0;
                if (b) {
                    b2 = e.m6095b(0).m6083b();
                } else {
                    b2 = 0;
                }
                byte[] g = avVar.m5700g();
                int i = 0;
                int i2 = 0;
                Point point = ((Point[]) f5052k.get())[0];
                Point point2 = ((Point[]) f5052k.get())[1];
                Point point3 = ((Point[]) f5052k.get())[2];
                for (int i3 = 0; i3 < a2; i3++) {
                    a.m5573a(i3, c, point, point2, point3);
                    if (obj != null) {
                        this.f5054e.m7728a(point, f);
                        this.f5054e.m7728a(point2, f);
                        this.f5054e.m7728a(point3, f);
                        i += 3;
                    }
                    if (b) {
                        if ((g[i3] & 1) != 0) {
                            this.f5053d.m7728a(point, f);
                            this.f5053d.m7728a(point2, f);
                            i2 += 2;
                        }
                        if ((g[i3] & 2) != 0) {
                            this.f5053d.m7728a(point2, f);
                            this.f5053d.m7728a(point3, f);
                            i2 += 2;
                        }
                        if ((g[i3] & 4) != 0) {
                            this.f5053d.m7728a(point3, f);
                            this.f5053d.m7728a(point, f);
                            i2 += 2;
                        }
                    }
                }
                this.f5056g.m7477a(a3, i);
                this.f5055f.m7477a(b2, i2);
            }
        }
    }

    public final int m7821a() {
        int i;
        int a = this.f5056g.m7476a() + ((this.f5053d.m7733c() + this.f5054e.m7733c()) + this.f5055f.m7476a());
        if (this.f5057h == null) {
            i = 0;
        } else {
            i = this.f5057h.m7797a();
        }
        return i + a;
    }

    public final int m7823b() {
        int i;
        int b = this.f5056g.m7479b() + (((this.f5053d.m7735d() + 156) + this.f5054e.m7735d()) + this.f5055f.m7479b());
        if (this.f5057h == null) {
            i = 0;
        } else {
            i = this.f5057h.m7799b();
        }
        return i + b;
    }
}
