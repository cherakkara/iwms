package com.google.android.m4b.maps.ba;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.bm;
import com.google.android.m4b.maps.an.bo;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.ColorPalette;
import com.google.android.m4b.maps.ay.ColorPaletteCoordinate;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.az.VertexVBO;
import com.olacabs.customer.p076d.br;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.l */
public final class GLLineMesh extends GLFeature {
    private static int f5236b;
    private static int f5237c;
    private static float f5238d;
    private static float f5239e;
    private final VertexBuffer f5240f;
    private final ColorPaletteCoordinate f5241g;
    private final int f5242h;
    private final Point f5243i;

    static {
        f5236b = AccessibilityNodeInfoCompat.ACTION_COPY;
        f5237c = 1;
        f5238d = br.DEFAULT_BACKOFF_MULT;
        f5239e = br.DEFAULT_BACKOFF_MULT;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.m4b.maps.ba.GLLineMesh m8030a(com.google.android.m4b.maps.an.ac r11, java.lang.String[] r12, com.google.android.m4b.maps.an.aq.VectorTile r13, int r14, com.google.android.m4b.maps.ay.GLState r15) {
        /*
        r4 = r11.m5446i();
        r5 = new java.util.HashSet;
        r5.<init>();
        r1 = 0;
        r6 = new java.util.ArrayList;
        r6.<init>();
        r0 = -1;
        r2 = r0;
        r3 = r1;
    L_0x0012:
        r0 = r13.hasNext();
        if (r0 == 0) goto L_0x00ab;
    L_0x0018:
        r1 = r13.m5613a();
        r0 = r1 instanceof com.google.android.m4b.maps.an.bo;
        if (r0 == 0) goto L_0x005b;
    L_0x0020:
        r0 = 1;
        if (r2 > r0) goto L_0x00ab;
    L_0x0023:
        r2 = 1;
        r0 = r1;
        r0 = (com.google.android.m4b.maps.an.bo) r0;
        r0 = r0.m5863a();
        r0 = r0.m6020b();
        r7 = f5236b;
        if (r0 <= r7) goto L_0x0037;
    L_0x0033:
        r13.next();
        goto L_0x0012;
    L_0x0037:
        r7 = r0 + r3;
        r8 = f5236b;
        if (r7 > r8) goto L_0x00ab;
    L_0x003d:
        r0 = r0 + r3;
        r6.add(r1);
        r10 = r2;
        r2 = r0;
        r0 = r10;
    L_0x0044:
        r3 = r1.m5550k();
        r7 = r3.length;
        r1 = 0;
    L_0x004a:
        if (r1 >= r7) goto L_0x00a4;
    L_0x004c:
        r8 = r3[r1];
        if (r8 < 0) goto L_0x0058;
    L_0x0050:
        r9 = r12.length;
        if (r8 >= r9) goto L_0x0058;
    L_0x0053:
        r8 = r12[r8];
        r5.add(r8);
    L_0x0058:
        r1 = r1 + 1;
        goto L_0x004a;
    L_0x005b:
        r0 = r1 instanceof com.google.android.m4b.maps.an.bm;
        if (r0 == 0) goto L_0x00ab;
    L_0x005f:
        r0 = r1;
        r0 = (com.google.android.m4b.maps.an.bm) r0;
        r0 = com.google.android.m4b.maps.ba.GLLineMesh.m8035a(r0);
        if (r0 == 0) goto L_0x00ab;
    L_0x0068:
        r0 = r1.m5548e();
        r7 = 0;
        r0 = r0.m6095b(r7);
        r0 = r0.m6084c();
        r0 = com.google.android.m4b.maps.ba.GLLineMesh.m8036b(r0);
        if (r0 == r2) goto L_0x007e;
    L_0x007b:
        if (r2 > 0) goto L_0x00ab;
    L_0x007d:
        r2 = r0;
    L_0x007e:
        r0 = r1;
        r0 = (com.google.android.m4b.maps.an.bm) r0;
        r0 = r0.m5851a();
        r0 = r0.m6020b();
        r0 = r0 + -1;
        r0 = r0 * 2;
        r7 = f5236b;
        if (r0 <= r7) goto L_0x0096;
    L_0x0091:
        r13.next();
        goto L_0x0012;
    L_0x0096:
        r7 = r0 + r3;
        r8 = f5236b;
        if (r7 > r8) goto L_0x00ab;
    L_0x009c:
        r0 = r0 + r3;
        r6.add(r1);
        r10 = r2;
        r2 = r0;
        r0 = r10;
        goto L_0x0044;
    L_0x00a4:
        r13.next();
        r3 = r2;
        r2 = r0;
        goto L_0x0012;
    L_0x00ab:
        r0 = r2;
        if (r0 >= 0) goto L_0x00af;
    L_0x00ae:
        r0 = 1;
    L_0x00af:
        r1 = new com.google.android.m4b.maps.ba.l;
        r1.<init>(r3, r0, r5, r15);
        r2 = r6.iterator();
    L_0x00b8:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x00df;
    L_0x00be:
        r0 = r2.next();
        r0 = (com.google.android.m4b.maps.an.bc) r0;
        r3 = r0.m5546b();
        r5 = 5;
        if (r3 != r5) goto L_0x00d1;
    L_0x00cb:
        r0 = (com.google.android.m4b.maps.an.bo) r0;
        r1.m8034a(r4, r0);
        goto L_0x00b8;
    L_0x00d1:
        r3 = r0.m5546b();
        r5 = 8;
        if (r3 != r5) goto L_0x00b8;
    L_0x00d9:
        r0 = (com.google.android.m4b.maps.an.bm) r0;
        r1.m8033a(r4, r0);
        goto L_0x00b8;
    L_0x00df:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ba.l.a(com.google.android.m4b.maps.an.ac, java.lang.String[], com.google.android.m4b.maps.an.aq$b, int, com.google.android.m4b.maps.ay.e):com.google.android.m4b.maps.ba.l");
    }

    public static synchronized boolean m8035a(bm bmVar) {
        boolean z = true;
        synchronized (GLLineMesh.class) {
            Style e = bmVar.m5855e();
            boolean z2;
            if (!e.m6097d() || e.m6095b(0).m6085d().length == 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (e.m6094b() != 1 || e.m6095b(0).m6084c() > f5238d || r2) {
                z = false;
            }
        }
        return z;
    }

    public static synchronized void m8032a(int i) {
        synchronized (GLLineMesh.class) {
            f5237c = i;
            f5238d = Math.max(br.DEFAULT_BACKOFF_MULT, ((float) Math.min(5, f5237c)) / f5239e);
        }
    }

    public static synchronized void m8031a(float f) {
        synchronized (GLLineMesh.class) {
            f5239e = f;
            f5238d = Math.max(br.DEFAULT_BACKOFF_MULT, ((float) Math.min(5, f5237c)) / f5239e);
        }
    }

    private static synchronized int m8036b(float f) {
        int max;
        synchronized (GLLineMesh.class) {
            max = Math.max(1, Math.min(f5237c, Math.round(f5239e * f)));
        }
        return max;
    }

    private GLLineMesh(int i, int i2, Set<String> set, GLState gLState) {
        super(set);
        this.f5240f = new VertexVBO(i);
        this.f5241g = new ColorPaletteCoordinate(i, gLState.m7507E());
        this.f5243i = new Point();
        this.f5242h = AccessibilityNodeInfoCompat.ACTION_CUT * i2;
    }

    public final void m8040b(GLState gLState) {
        this.f5240f.m7732b(gLState);
        this.f5241g.m7480b(gLState);
    }

    public final void m8041c(GLState gLState) {
        this.f5240f.m7734c(gLState);
        this.f5241g.m7481c(gLState);
    }

    public final void m8038a(GLState gLState, Camera camera, ad adVar) {
        if (this.f5240f.m7725a() != 0) {
            GL10 x = gLState.m7541x();
            if (this.f5242h > AccessibilityNodeInfoCompat.ACTION_CUT) {
                x.glLineWidthx(this.f5242h);
            }
            this.f5240f.m7736d(gLState);
            this.f5241g.m7478a(gLState);
            ColorPalette.m7469c(gLState);
            x.glDrawArrays(1, 0, this.f5240f.m7725a());
            x.glLineWidthx(AccessibilityNodeInfoCompat.ACTION_CUT);
            ColorPalette.m7470d(gLState);
        }
    }

    private void m8034a(Rectangle2D rectangle2D, bo boVar) {
        Style e = boVar.m5866e();
        if (e.m6094b() != 0 && e.m6095b(0).m6084c() != 0.0f && e.m6095b(0).m6083b() != 0) {
            Polyline a = boVar.m5863a();
            int b = a.m6020b();
            Point c = rectangle2D.m6050c();
            int f = rectangle2D.m6053f();
            for (int i = 0; i < b; i++) {
                a.m6016a(i, this.f5243i);
                Point.m5936b(this.f5243i, c, this.f5243i);
                this.f5240f.m7728a(this.f5243i, f);
            }
            this.f5241g.m7477a(e.m6095b(0).m6083b(), b);
        }
    }

    private void m8033a(Rectangle2D rectangle2D, bm bmVar) {
        Style e = bmVar.m5855e();
        if (e.m6094b() != 0 && e.m6095b(0).m6084c() != 0.0f && e.m6095b(0).m6083b() != 0) {
            Polyline a = bmVar.m5851a();
            int b = a.m6020b() - 1;
            int i = b * 2;
            Point c = rectangle2D.m6050c();
            int f = rectangle2D.m6053f();
            int i2 = 0;
            while (i2 <= b) {
                a.m6016a(i2, this.f5243i);
                Point.m5936b(this.f5243i, c, this.f5243i);
                this.f5240f.m7728a(this.f5243i, f);
                if (i2 > 0 && i2 < b) {
                    this.f5240f.m7728a(this.f5243i, f);
                }
                i2++;
            }
            this.f5241g.m7477a(e.m6095b(0).m6083b(), i);
        }
    }

    public final int m8037a() {
        return this.f5240f.m7733c() + this.f5241g.m7476a();
    }

    public final int m8039b() {
        return (this.f5240f.m7735d() + 120) + this.f5241g.m7479b();
    }
}
