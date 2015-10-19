package com.google.android.m4b.maps.ba;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ar;
import com.google.android.m4b.maps.av.BubbleBlower;
import com.google.android.m4b.maps.av.Transform;
import com.google.android.m4b.maps.av.ai;
import com.google.android.m4b.maps.av.ak;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.be.ad;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.ExceptionReporter;
import com.google.android.m4b.maps.p057t.GeoPoint;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.olacabs.customer.p076d.br;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.m */
public final class GLMarker implements BubbleBlower, Comparable<GLMarker> {
    private static float[] f5244G;
    private static Point f5245H;
    private static int f5246I;
    private final int f5247A;
    private final int f5248B;
    private final int f5249C;
    private final int f5250D;
    private boolean f5251E;
    private ar f5252F;
    private final Point f5253J;
    private int f5254K;
    private int f5255L;
    private int f5256M;
    private int f5257N;
    private int f5258O;
    private Rect f5259P;
    private ad f5260Q;
    private Point f5261a;
    private Bitmap f5262b;
    private final Bitmap f5263c;
    private int f5264d;
    private int f5265e;
    private String f5266f;
    private String f5267g;
    private ak f5268h;
    private Texture f5269i;
    private Texture f5270j;
    private TexCoordBuffer f5271k;
    private TexCoordBuffer f5272l;
    private float f5273m;
    private int f5274n;
    private boolean f5275o;
    private boolean f5276p;
    private boolean f5277q;
    private boolean f5278r;
    private boolean f5279s;
    private final boolean f5280t;
    private boolean f5281u;
    private float f5282v;
    private float f5283w;
    private final float f5284x;
    private int f5285y;
    private int f5286z;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        GLMarker gLMarker = (GLMarker) obj;
        return this.f5274n == gLMarker.f5274n ? this.f5258O - gLMarker.f5258O : this.f5274n - gLMarker.f5274n;
    }

    static {
        f5244G = new float[8];
        f5245H = new Point();
    }

    public GLMarker(Point point, Bitmap bitmap, Bitmap bitmap2, int i, int i2, String str, String str2, boolean z) {
        this.f5267g = null;
        this.f5279s = true;
        this.f5281u = true;
        this.f5282v = 0.0f;
        this.f5283w = br.DEFAULT_BACKOFF_MULT;
        this.f5253J = new Point();
        this.f5261a = point;
        this.f5262b = bitmap;
        this.f5263c = null;
        this.f5264d = i;
        this.f5265e = i2;
        this.f5266f = null;
        this.f5277q = true;
        this.f5280t = false;
        int i3 = f5246I;
        f5246I = i3 + 1;
        this.f5258O = i3;
        if (!this.f5280t || Config.m11320a().m11336j()) {
            this.f5284x = br.DEFAULT_BACKOFF_MULT;
        } else {
            this.f5284x = ((float) Config.m11320a().m11331e()) / 240.0f;
        }
        this.f5285y = m8042a(this.f5264d);
        m8060b(this.f5264d / 2, 0);
        if (this.f5262b != null) {
            this.f5247A = m8042a(this.f5262b.getWidth());
            this.f5248B = m8042a(this.f5262b.getHeight());
        } else {
            this.f5247A = 0;
            this.f5248B = 0;
        }
        if (this.f5263c != null) {
            this.f5249C = m8042a(this.f5263c.getWidth());
            this.f5250D = m8042a(this.f5263c.getHeight());
        } else {
            this.f5249C = 0;
            this.f5250D = 0;
        }
        this.f5286z = m8042a(this.f5265e);
    }

    private int m8042a(int i) {
        return (!this.f5280t || Config.m11320a().m11336j()) ? i : Math.round(((float) i) * this.f5284x);
    }

    public final boolean m8074k() {
        return this.f5277q;
    }

    public final void m8054a(boolean z) {
        this.f5278r = z;
    }

    public final boolean m8055a() {
        return this.f5278r;
    }

    public final synchronized void m8047a(float f) {
        this.f5282v = f;
    }

    public final synchronized void m8062b(boolean z) {
        this.f5281u = z;
    }

    public final synchronized void m8065c(boolean z) {
        this.f5279s = z;
    }

    public final synchronized boolean m8067d() {
        return this.f5279s;
    }

    public final synchronized void m8059b(float f) {
        this.f5283w = f;
    }

    public final synchronized void m8049a(Point point) {
        if (point != null) {
            this.f5261a = point;
        }
    }

    public final void m8048a(int i, int i2) {
        this.f5264d = i;
        this.f5265e = i2;
        this.f5285y = m8042a(this.f5264d);
        this.f5286z = m8042a(this.f5265e);
    }

    public final void m8060b(int i, int i2) {
        this.f5254K = i;
        this.f5255L = i2;
        this.f5256M = m8042a(this.f5254K);
        this.f5257N = m8042a(this.f5255L);
    }

    public final synchronized GeoPoint m8068e() {
        return new GeoPoint(this.f5261a.m5941a(), this.f5261a.m5952c());
    }

    public final void m8050a(ak akVar) {
        this.f5268h = akVar;
    }

    public final Bitmap m8071h() {
        return this.f5262b;
    }

    public final Bitmap m8072i() {
        return this.f5263c;
    }

    public final void m8064c() {
        this.f5275o = false;
        if (this.f5276p) {
            this.f5276p = false;
            this.f5268h.m6834c(this);
        }
    }

    public final void m8058b() {
        this.f5275o = true;
    }

    public final boolean m8075l() {
        return this.f5275o;
    }

    public final synchronized Point m8069f() {
        return this.f5261a;
    }

    public final IndoorLevelReference m8070g() {
        return null;
    }

    public final Point m8073j() {
        return this.f5253J;
    }

    public final void m8053a(String str) {
        this.f5266f = str;
    }

    public final String m8076m() {
        return this.f5267g;
    }

    public final void m8061b(String str) {
        this.f5267g = str;
    }

    public final void m8066d(boolean z) {
        this.f5276p = false;
    }

    public final int m8077n() {
        if (this.f5269i == null) {
            return 0;
        }
        this.f5269i.m7626f();
        int g = this.f5269i.m7627g();
        this.f5269i = null;
        return g;
    }

    public final int m8078o() {
        if (this.f5270j == null) {
            return 0;
        }
        this.f5270j.m7626f();
        int g = this.f5270j.m7627g();
        this.f5270j = null;
        return g;
    }

    public final synchronized boolean m8063b(Camera camera) {
        if (this.f5279s) {
            this.f5273m = camera.m7419a((float) br.DEFAULT_BACKOFF_MULT, camera.m7441i());
            camera.m7426a(this.f5261a, f5244G);
            this.f5274n = (int) (f5244G[1] * 65536.0f);
            m8045c(camera);
            double cos = Math.cos(Math.toRadians((double) (-this.f5282v)));
            double sin = Math.sin(Math.toRadians((double) (-this.f5282v)));
            int i = this.f5256M - this.f5285y;
            int i2 = -(this.f5257N - this.f5286z);
            double d = (((double) i) * cos) - (((double) i2) * sin);
            cos = (cos * ((double) i2)) + (sin * ((double) i));
            if (this.f5281u) {
                ai.m6783a(camera, m8069f(), (int) d, (int) cos, this.f5253J);
            } else {
                camera.m7441i();
                this.f5253J.m5945a(this.f5261a.m5958f() + ((int) (((double) this.f5273m) * d)), ((int) (cos * ((double) this.f5273m))) + this.f5261a.m5960g(), this.f5261a.m5962h());
            }
        }
        return true;
    }

    public final void m8051a(GLState gLState, Camera camera, com.google.android.m4b.maps.av.ad adVar) {
        if (adVar.m6704b() <= 1 && this.f5268h != null && this.f5273m != 0.0f && this.f5262b != null && this.f5279s) {
            float f;
            boolean z;
            float f2;
            Object obj;
            int i;
            int i2;
            int i3;
            Bitmap bitmap;
            int width;
            synchronized (this) {
                f = this.f5282v;
                z = this.f5281u;
                f2 = this.f5283w;
            }
            GL10 x = gLState.m7541x();
            Bitmap bitmap2 = this.f5263c;
            Bitmap bitmap3 = this.f5262b;
            if (adVar.m6704b() == 0) {
                if (this.f5270j == null) {
                    if (bitmap2 != null) {
                        this.f5270j = m8043a(gLState, bitmap2);
                        this.f5272l = GLMarker.m8044a(this.f5270j);
                    } else {
                        return;
                    }
                }
            } else if (this.f5269i == null) {
                this.f5269i = m8043a(gLState, bitmap3);
                this.f5271k = GLMarker.m8044a(this.f5269i);
            }
            f5245H.m5950b(this.f5261a);
            if (!camera.m7436d() && camera.m7443k() == 0.0f) {
                if (z) {
                    obj = f % 90.0f == 0.0f ? 1 : null;
                } else if ((camera.m7442j() - f) % 90.0f == 0.0f) {
                    obj = 1;
                }
                if (obj != null) {
                    camera.m7426a(f5245H, f5244G);
                    f5245H = camera.m7435d((float) Math.round(f5244G[0]), (float) Math.round(f5244G[1]));
                }
                if (this.f5261a != null) {
                    ExceptionReporter.m11467a("UI", new IllegalStateException("Null point for (" + f5244G[0] + "," + f5244G[1] + "); camera=" + camera));
                }
                x.glPushMatrix();
                synchronized (this) {
                    Transform.m7275a(gLState, camera, f5245H, this.f5273m);
                }
                if (adVar.m6704b() != 0) {
                    x.glRotatex(-2949120, 0, 0, AccessibilityNodeInfoCompat.ACTION_CUT);
                    x.glRotatex(-5898240, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0);
                    this.f5272l.m7706d(gLState);
                    this.f5270j.m7613a(x);
                    i = this.f5249C;
                    i2 = this.f5250D;
                } else {
                    if (z) {
                        x.glRotatef(-90.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
                    } else {
                        Transform.m7278a(x, camera);
                    }
                    x.glRotatef(f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
                    this.f5271k.m7706d(gLState);
                    this.f5269i.m7613a(x);
                    i = this.f5247A;
                    i3 = this.f5248B;
                    x.glBlendFunc(1, 771);
                    x.glTexEnvx(8960, 8704, 8448);
                    x.glColor4f(f2, f2, f2, f2);
                    bitmap = bitmap3;
                    i2 = i3;
                    bitmap2 = bitmap;
                }
                width = (-(this.f5264d << 16)) / bitmap2.getWidth();
                i3 = ((this.f5265e << 16) / bitmap2.getHeight()) + SupportMenu.CATEGORY_MASK;
                x.glScalex(i << 16, AccessibilityNodeInfoCompat.ACTION_CUT, i2 << 16);
                x.glTranslatex(width, 0, i3);
                x.glDrawArrays(5, 0, 4);
                x.glPopMatrix();
                return;
            }
            obj = null;
            if (obj != null) {
                camera.m7426a(f5245H, f5244G);
                f5245H = camera.m7435d((float) Math.round(f5244G[0]), (float) Math.round(f5244G[1]));
            }
            if (this.f5261a != null) {
                x.glPushMatrix();
                synchronized (this) {
                    Transform.m7275a(gLState, camera, f5245H, this.f5273m);
                }
                if (adVar.m6704b() != 0) {
                    if (z) {
                        x.glRotatef(-90.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
                    } else {
                        Transform.m7278a(x, camera);
                    }
                    x.glRotatef(f, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
                    this.f5271k.m7706d(gLState);
                    this.f5269i.m7613a(x);
                    i = this.f5247A;
                    i3 = this.f5248B;
                    x.glBlendFunc(1, 771);
                    x.glTexEnvx(8960, 8704, 8448);
                    x.glColor4f(f2, f2, f2, f2);
                    bitmap = bitmap3;
                    i2 = i3;
                    bitmap2 = bitmap;
                } else {
                    x.glRotatex(-2949120, 0, 0, AccessibilityNodeInfoCompat.ACTION_CUT);
                    x.glRotatex(-5898240, AccessibilityNodeInfoCompat.ACTION_CUT, 0, 0);
                    this.f5272l.m7706d(gLState);
                    this.f5270j.m7613a(x);
                    i = this.f5249C;
                    i2 = this.f5250D;
                }
                width = (-(this.f5264d << 16)) / bitmap2.getWidth();
                i3 = ((this.f5265e << 16) / bitmap2.getHeight()) + SupportMenu.CATEGORY_MASK;
                x.glScalex(i << 16, AccessibilityNodeInfoCompat.ACTION_CUT, i2 << 16);
                x.glTranslatex(width, 0, i3);
                x.glDrawArrays(5, 0, 4);
                x.glPopMatrix();
                return;
            }
            ExceptionReporter.m11467a("UI", new IllegalStateException("Null point for (" + f5244G[0] + "," + f5244G[1] + "); camera=" + camera));
        }
    }

    public final synchronized int m8046a(float f, float f2, Camera camera) {
        int i;
        int i2;
        float f3;
        int[] b = camera.m7431b(this.f5261a);
        if (this.f5281u) {
            f3 = this.f5282v;
        } else {
            f3 = this.f5282v - camera.m7442j();
        }
        if (f3 != 0.0f) {
            double toRadians = Math.toRadians((double) f3);
            f3 = (float) Math.sin(toRadians);
            float cos = (float) Math.cos(toRadians);
            float f4 = f - ((float) b[0]);
            float f5 = -(f2 - ((float) b[1]));
            float f6 = (f4 * cos) - (f5 * f3);
            f = ((float) b[0]) + f6;
            f2 = ((float) b[1]) - ((f3 * f4) + (cos * f5));
        }
        i = (b[0] - this.f5285y) + (this.f5247A / 2);
        i2 = (b[1] - this.f5286z) + (this.f5248B / 2);
        i = Math.max(((int) Math.abs(f - ((float) i))) - (this.f5247A / 2), 0);
        i2 = Math.max(((int) Math.abs(f2 - ((float) i2))) - (this.f5248B / 2), 0);
        return (i * i) + (i2 * i2);
    }

    public final synchronized boolean m8056a(float f, float f2, Point point, Camera camera) {
        boolean z = false;
        synchronized (this) {
            if (this.f5262b != null) {
                if (m8046a(f, f2, camera) == 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    private synchronized void m8045c(Camera camera) {
        int[] b = camera.m7431b(this.f5261a);
        int i = b[0] - this.f5285y;
        int i2 = this.f5247A + i;
        int i3 = b[1] - this.f5286z;
        int i4 = this.f5248B + i3;
        if (this.f5263c != null) {
            i2 = Math.max(i2, this.f5249C + i);
            i4 = Math.max(i4, this.f5250D + i3);
        }
        this.f5259P = new Rect(i, i3, i2, i4);
    }

    public final synchronized Rect m8079p() {
        return this.f5259P;
    }

    public final void m8052a(ad adVar) {
        this.f5260Q = adVar;
    }

    public final ad m8080q() {
        return this.f5260Q;
    }

    public final synchronized boolean m8057a(Camera camera) {
        boolean z = false;
        synchronized (this) {
            if (this.f5279s && this.f5262b != null) {
                if (this.f5259P == null) {
                    m8045c(camera);
                }
                if (camera.m7453u().equals(this.f5252F)) {
                    z = this.f5251E;
                } else {
                    if (this.f5259P.left < camera.m7437e() && this.f5259P.right >= 0 && this.f5259P.top < camera.m7438f() && this.f5259P.bottom >= 0) {
                        z = true;
                    }
                    this.f5251E = z;
                    this.f5252F = camera.m7453u();
                    z = this.f5251E;
                }
            }
        }
        return z;
    }

    private Texture m8043a(GLState gLState, Bitmap bitmap) {
        Texture texture = (Texture) this.f5268h.m6838i().get(bitmap);
        if (texture != null) {
            texture.m7625e();
            return texture;
        }
        texture = new Texture(gLState);
        texture.m7621c(true);
        texture.m7617b(bitmap);
        this.f5268h.m6838i().put(bitmap, texture);
        return texture;
    }

    private static TexCoordBuffer m8044a(Texture texture) {
        TexCoordBuffer texCoordBuffer = new TexCoordBuffer(8);
        float b = texture.m7615b();
        float c = texture.m7619c();
        texCoordBuffer.m7692a(0.0f, 0.0f);
        texCoordBuffer.m7692a(0.0f, c);
        texCoordBuffer.m7692a(b, 0.0f);
        texCoordBuffer.m7692a(b, c);
        return texCoordBuffer;
    }
}
