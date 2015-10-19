package com.google.android.m4b.maps.av;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.p060x.Fade;
import com.olacabs.customer.p076d.br;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLBubbleOverlay */
public final class ai extends al {
    private final GLOverlay f4212a;
    private final float[] f4213b;
    private BubbleBlower f4214c;
    private final Point f4215d;
    private Bitmap f4216e;
    private BubbleView f4217f;
    private Bitmap f4218g;
    private Texture f4219h;
    private GLBubbleOverlay f4220i;
    private int f4221j;
    private int f4222k;
    private int f4223l;
    private int f4224m;
    private float f4225n;
    private final int f4226o;
    private final Fade f4227p;

    /* renamed from: com.google.android.m4b.maps.av.ai.a */
    public interface GLBubbleOverlay {
        void m6782b(BubbleBlower bubbleBlower);
    }

    public ai(Resources resources) {
        this(GLOverlay.BUBBLE, resources);
    }

    private ai(GLOverlay gLOverlay, Resources resources) {
        int i;
        this.f4213b = new float[8];
        this.f4215d = new Point();
        this.f4227p = new Fade(3000, 10000, Fade.Fade.FADE_BETWEEN, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_PASTE);
        this.f4212a = gLOverlay;
        if (resources == null) {
            i = 16776960;
        } else {
            i = resources.getColor(R.bubble_highlight);
        }
        this.f4226o = i;
    }

    public final synchronized void m6788a(GLBubbleOverlay gLBubbleOverlay) {
        this.f4220i = gLBubbleOverlay;
    }

    public final GLOverlay m6798d() {
        return this.f4212a;
    }

    public final synchronized void m6790a(GLState gLState) {
        m6786i();
    }

    public final synchronized void m6791a(GLState gLState, RepaintCallback repaintCallback) {
        if (this.f4214c != null) {
            this.f4218g = m6787j();
        }
    }

    public final synchronized boolean m6794a(Camera camera, GLState gLState) {
        this.f4225n = camera.m7419a((float) br.DEFAULT_BACKOFF_MULT, camera.m7441i());
        if (!(camera.m7437e() == this.f4221j && camera.m7438f() == this.f4222k)) {
            this.f4221j = camera.m7437e();
            this.f4222k = camera.m7438f();
            if (this.f4218g != null) {
                m6786i();
                this.f4218g = m6787j();
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"WrongCall"})
    public final void m6792a(com.google.android.m4b.maps.ay.GLState r10, com.google.android.m4b.maps.ax.Camera r11, com.google.android.m4b.maps.av.ad r12) {
        /*
        r9 = this;
        r2 = 0;
        r1 = 1;
        r0 = r12.m6704b();
        if (r0 != 0) goto L_0x0012;
    L_0x0008:
        monitor-enter(r9);
        r0 = r9.f4214c;	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x0011;
    L_0x000d:
        r0 = r9.f4218g;	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r9);	 Catch:{ all -> 0x004e }
    L_0x0012:
        return;
    L_0x0013:
        r0 = r9.f4214c;	 Catch:{ all -> 0x004e }
        r0 = r0.m7165j();	 Catch:{ all -> 0x004e }
        r3 = r9.f4214c;	 Catch:{ all -> 0x004e }
        r3 = r3.m7164g();	 Catch:{ all -> 0x004e }
        if (r3 == 0) goto L_0x0042;
    L_0x0021:
        if (r0 == 0) goto L_0x0042;
    L_0x0023:
        r4 = com.google.android.m4b.maps.al.IndoorState.m5334a();	 Catch:{ all -> 0x004e }
        r3 = r3.m11306a();	 Catch:{ all -> 0x004e }
        r3 = r4.m5362d(r3);	 Catch:{ all -> 0x004e }
        if (r3 == 0) goto L_0x0042;
    L_0x0031:
        r4 = r9.f4215d;	 Catch:{ all -> 0x004e }
        r4.m5950b(r0);	 Catch:{ all -> 0x004e }
        r4 = r9.f4215d;	 Catch:{ all -> 0x004e }
        r0 = r3.m5299a(r11, r0);	 Catch:{ all -> 0x004e }
        r0 = (int) r0;	 Catch:{ all -> 0x004e }
        r4.m5944a(r0);	 Catch:{ all -> 0x004e }
        r0 = r9.f4215d;	 Catch:{ all -> 0x004e }
    L_0x0042:
        r3 = r11.m7453u();	 Catch:{ all -> 0x004e }
        r4 = r3.m5651a();	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x0051;
    L_0x004c:
        monitor-exit(r9);	 Catch:{ all -> 0x004e }
        goto L_0x0012;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x0051:
        r4 = r4.m5678a(r0);	 Catch:{ all -> 0x004e }
        if (r4 == 0) goto L_0x005d;
    L_0x0057:
        r3 = r3.m5654a(r0);	 Catch:{ all -> 0x004e }
        if (r3 != 0) goto L_0x00a8;
    L_0x005d:
        r3 = r9.f4214c;	 Catch:{ all -> 0x004e }
        r3 = r3.m7160a(r11);	 Catch:{ all -> 0x004e }
        if (r3 != 0) goto L_0x00a8;
    L_0x0065:
        r3 = r9.f4218g;	 Catch:{ all -> 0x004e }
        if (r3 == 0) goto L_0x00a6;
    L_0x0069:
        r3 = r9.f4214c;	 Catch:{ all -> 0x004e }
        r3 = r3.m7165j();	 Catch:{ all -> 0x004e }
        r3 = r11.m7431b(r3);	 Catch:{ all -> 0x004e }
        r4 = r9.f4218g;	 Catch:{ all -> 0x004e }
        r4 = r4.getWidth();	 Catch:{ all -> 0x004e }
        r4 = r4 / 2;
        r5 = r9.f4218g;	 Catch:{ all -> 0x004e }
        r5 = r5.getHeight();	 Catch:{ all -> 0x004e }
        r6 = 0;
        r6 = r3[r6];	 Catch:{ all -> 0x004e }
        r6 = r6 - r4;
        r7 = 0;
        r7 = r3[r7];	 Catch:{ all -> 0x004e }
        r4 = r4 + r7;
        r7 = 1;
        r7 = r3[r7];	 Catch:{ all -> 0x004e }
        r7 = r7 - r5;
        r8 = 1;
        r3 = r3[r8];	 Catch:{ all -> 0x004e }
        r3 = r3 + r5;
        r5 = r11.m7437e();	 Catch:{ all -> 0x004e }
        r8 = r11.m7438f();	 Catch:{ all -> 0x004e }
        if (r4 < 0) goto L_0x00a6;
    L_0x009b:
        if (r6 >= r5) goto L_0x00a6;
    L_0x009d:
        if (r3 < 0) goto L_0x00a6;
    L_0x009f:
        if (r7 >= r8) goto L_0x00a6;
    L_0x00a1:
        if (r1 != 0) goto L_0x00a8;
    L_0x00a3:
        monitor-exit(r9);	 Catch:{ all -> 0x004e }
        goto L_0x0012;
    L_0x00a6:
        r1 = r2;
        goto L_0x00a1;
    L_0x00a8:
        r1 = r11.m7436d();	 Catch:{ all -> 0x004e }
        if (r1 != 0) goto L_0x00cb;
    L_0x00ae:
        r1 = r9.f4213b;	 Catch:{ all -> 0x004e }
        r11.m7426a(r0, r1);	 Catch:{ all -> 0x004e }
        r0 = r9.f4213b;	 Catch:{ all -> 0x004e }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ all -> 0x004e }
        r0 = java.lang.Math.round(r0);	 Catch:{ all -> 0x004e }
        r0 = (float) r0;	 Catch:{ all -> 0x004e }
        r1 = r9.f4213b;	 Catch:{ all -> 0x004e }
        r2 = 1;
        r1 = r1[r2];	 Catch:{ all -> 0x004e }
        r1 = java.lang.Math.round(r1);	 Catch:{ all -> 0x004e }
        r1 = (float) r1;	 Catch:{ all -> 0x004e }
        r0 = r11.m7435d(r0, r1);	 Catch:{ all -> 0x004e }
    L_0x00cb:
        if (r0 != 0) goto L_0x0107;
    L_0x00cd:
        r0 = "UI";
        r1 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x004e }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004e }
        r3 = "Null point for (";
        r2.<init>(r3);	 Catch:{ all -> 0x004e }
        r3 = r9.f4213b;	 Catch:{ all -> 0x004e }
        r4 = 0;
        r3 = r3[r4];	 Catch:{ all -> 0x004e }
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r3 = ",";
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r3 = r9.f4213b;	 Catch:{ all -> 0x004e }
        r4 = 1;
        r3 = r3[r4];	 Catch:{ all -> 0x004e }
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r3 = "); camera=";
        r2 = r2.append(r3);	 Catch:{ all -> 0x004e }
        r2 = r2.append(r11);	 Catch:{ all -> 0x004e }
        r2 = r2.toString();	 Catch:{ all -> 0x004e }
        r1.<init>(r2);	 Catch:{ all -> 0x004e }
        com.google.android.m4b.maps.p040u.ExceptionReporter.m11467a(r0, r1);	 Catch:{ all -> 0x004e }
        monitor-exit(r9);	 Catch:{ all -> 0x004e }
        goto L_0x0012;
    L_0x0107:
        r1 = r10.m7541x();	 Catch:{ all -> 0x004e }
        r10.m7533p();	 Catch:{ all -> 0x004e }
        r2 = 1;
        r3 = 771; // 0x303 float:1.08E-42 double:3.81E-321;
        r1.glBlendFunc(r2, r3);	 Catch:{ all -> 0x004e }
        r2 = 8960; // 0x2300 float:1.2556E-41 double:4.427E-320;
        r3 = 8704; // 0x2200 float:1.2197E-41 double:4.3003E-320;
        r4 = 7681; // 0x1e01 float:1.0763E-41 double:3.795E-320;
        r1.glTexEnvx(r2, r3, r4);	 Catch:{ all -> 0x004e }
        r2 = r9.f4219h;	 Catch:{ all -> 0x004e }
        if (r2 != 0) goto L_0x012f;
    L_0x0121:
        r2 = new com.google.android.m4b.maps.ay.l;	 Catch:{ all -> 0x004e }
        r2.<init>(r10);	 Catch:{ all -> 0x004e }
        r9.f4219h = r2;	 Catch:{ all -> 0x004e }
        r2 = r9.f4219h;	 Catch:{ all -> 0x004e }
        r3 = r9.f4218g;	 Catch:{ all -> 0x004e }
        r2.m7617b(r3);	 Catch:{ all -> 0x004e }
    L_0x012f:
        r1.glPushMatrix();	 Catch:{ all -> 0x004e }
        r2 = r9.f4225n;	 Catch:{ all -> 0x004e }
        com.google.android.m4b.maps.av.Transform.m7275a(r10, r11, r0, r2);	 Catch:{ all -> 0x004e }
        com.google.android.m4b.maps.av.Transform.m7278a(r1, r11);	 Catch:{ all -> 0x004e }
        r2 = r11.m7446n();	 Catch:{ all -> 0x004e }
        r3 = r11.m7430b();	 Catch:{ all -> 0x004e }
        r0 = com.google.android.m4b.maps.an.Point.m5938c(r2, r3, r0);	 Catch:{ all -> 0x004e }
        r2 = r9.f4218g;	 Catch:{ all -> 0x004e }
        r2 = r2.getWidth();	 Catch:{ all -> 0x004e }
        r2 = (float) r2;	 Catch:{ all -> 0x004e }
        r2 = r2 * r0;
        r3 = r9.f4218g;	 Catch:{ all -> 0x004e }
        r3 = r3.getHeight();	 Catch:{ all -> 0x004e }
        r3 = (float) r3;	 Catch:{ all -> 0x004e }
        r0 = r0 * r3;
        r3 = -r2;
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r3 = r3 * r4;
        r4 = 0;
        r5 = 0;
        r1.glTranslatef(r3, r4, r5);	 Catch:{ all -> 0x004e }
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r1.glScalef(r2, r3, r0);	 Catch:{ all -> 0x004e }
        r0 = r9.f4217f;	 Catch:{ all -> 0x004e }
        com.google.android.m4b.maps.p058v.GmmSettings.m11527a();	 Catch:{ all -> 0x004e }
        com.google.android.m4b.maps.p058v.GmmSettings.m11535i();	 Catch:{ all -> 0x004e }
        r0 = r10.f4852f;	 Catch:{ all -> 0x004e }
        r2 = r10.f4849c;	 Catch:{ all -> 0x004e }
        r9.m6784a(r1, r10, r0, r2);	 Catch:{ all -> 0x004e }
        r1.glPopMatrix();	 Catch:{ all -> 0x004e }
        monitor-exit(r9);	 Catch:{ all -> 0x004e }
        r0 = r9.f4217f;
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.ai.a(com.google.android.m4b.maps.ay.e, com.google.android.m4b.maps.ax.b, com.google.android.m4b.maps.av.ad):void");
    }

    private void m6784a(GL10 gl10, GLState gLState, VertexBuffer vertexBuffer, TexCoordBuffer texCoordBuffer) {
        vertexBuffer.m7736d(gLState);
        texCoordBuffer.m7706d(gLState);
        this.f4219h.m7613a(gl10);
        gl10.glDrawArrays(5, 0, 4);
    }

    private boolean m6785d(float f, float f2, Camera camera) {
        if (this.f4214c == null || this.f4214c.m7163f() == null) {
            return false;
        }
        int[] b = camera.m7431b(this.f4214c.m7165j());
        int i = b[0] - (this.f4223l / 2);
        int i2 = this.f4223l + i;
        if (f < ((float) i) || f > ((float) i2)) {
            return false;
        }
        int i3 = b[1];
        if (f2 < ((float) (i3 - this.f4224m)) || f2 > ((float) i3)) {
            return false;
        }
        return true;
    }

    public final boolean m6793a(float f, float f2, Point point, Camera camera) {
        synchronized (this) {
            if (m6785d(f, f2, camera)) {
                e_();
                return true;
            }
            if (this.f4214c != null) {
                m6797c();
                e_();
            }
            return false;
        }
    }

    public final boolean a_(float f, float f2, Camera camera) {
        synchronized (this) {
            if (m6785d(f, f2, camera)) {
                if (this.f4220i != null) {
                    GLBubbleOverlay gLBubbleOverlay = this.f4220i;
                    BubbleBlower bubbleBlower = this.f4214c;
                }
                return true;
            }
            return false;
        }
    }

    public final boolean m6795b(float f, float f2, Point point, Camera camera) {
        synchronized (this) {
            if (m6785d(f, f2, camera)) {
                d_();
                return true;
            }
            return false;
        }
    }

    public final synchronized boolean m6796b(float f, float f2, Camera camera) {
        boolean z;
        if (m6785d(f, f2, camera)) {
            this.f4227p.m11585a();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public final synchronized void d_() {
    }

    public final synchronized void m6789a(BubbleBlower bubbleBlower, BubbleView bubbleView) {
        this.f4217f = bubbleView;
        this.f4227p.m11585a();
        if (this.f4214c == bubbleBlower && this.f4216e == bubbleView.m7367a()) {
            if (this.f4214c != null) {
                this.f4214c.m7161b();
            }
            if (this.f4216e != null) {
                m6786i();
                this.f4218g = m6787j();
            }
        } else {
            if (this.f4214c != null) {
                this.f4214c.m7162c();
            }
            this.f4214c = bubbleBlower;
            this.f4214c.m7161b();
            if (bubbleView != null) {
                this.f4216e = bubbleView.m7367a();
            }
            if (this.f4216e != null) {
                m6786i();
                this.f4218g = m6787j();
            }
        }
    }

    public final synchronized void m6797c() {
        if (this.f4214c != null) {
            this.f4214c.m7162c();
        }
        this.f4214c = null;
    }

    private void m6786i() {
        if (this.f4219h != null) {
            this.f4219h.m7626f();
            this.f4219h = null;
        }
        if (this.f4218g != null) {
            this.f4218g.recycle();
            this.f4218g = null;
        }
    }

    protected final boolean e_() {
        boolean e_ = super.e_();
        if (this.f4220i == null) {
            return e_;
        }
        this.f4220i.m6782b(this.f4214c);
        return true;
    }

    private Bitmap m6787j() {
        this.f4223l = this.f4216e.getWidth();
        this.f4224m = this.f4216e.getHeight();
        int a = Texture.m7603a(this.f4223l, 64);
        int a2 = Texture.m7603a(this.f4224m, 32);
        Bitmap createBitmap = Bitmap.createBitmap(a, a2, Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate((float) ((a - this.f4223l) / 2), (float) (a2 - this.f4224m));
        canvas.drawBitmap(this.f4216e, 0.0f, 0.0f, new Paint());
        return createBitmap;
    }

    public static void m6783a(Camera camera, Point point, int i, int i2, Point point2) {
        float a = camera.m7419a((float) i2, camera.m7441i());
        float a2 = camera.m7419a((float) i, camera.m7441i());
        double cos = Math.cos(Math.toRadians((double) camera.m7443k()));
        double sin = Math.sin(Math.toRadians((double) camera.m7443k()));
        double cos2 = Math.cos(Math.toRadians((double) camera.m7442j()));
        double sin2 = Math.sin(Math.toRadians((double) camera.m7442j()));
        Point point3 = point2;
        point3.m5945a(point.m5958f() + ((int) (((((double) a) * cos) * sin2) + (((double) a2) * cos2))), ((int) (((cos * ((double) a)) * cos2) - (((double) a2) * sin2))) + point.m5960g(), ((int) (sin * ((double) a))) + point.m5962h());
    }
}
