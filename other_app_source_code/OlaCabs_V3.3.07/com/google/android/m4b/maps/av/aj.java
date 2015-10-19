package com.google.android.m4b.maps.av;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.ax;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.GeometryUtil;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.p025a.p028c.ar;
import com.olacabs.customer.p076d.dm;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLColoredPolylineOverlay */
public final class aj extends al {
    private static final Point f4228a;
    private static final Point f4229b;
    private final Polyline f4230c;
    private Polyline f4231d;
    private Polyline f4232e;
    private final List<Polyline> f4233f;
    private final FeatureId f4234g;
    private Rectangle2D f4235h;
    private float f4236i;
    private float f4237j;
    private final VertexBuffer f4238k;
    private final IndexBuffer f4239l;
    private final TexCoordBuffer f4240m;
    private float f4241n;
    private int f4242o;
    private boolean f4243p;
    private final int f4244q;
    private boolean f4245r;

    static {
        f4228a = new Point(1073741824, 0);
        f4229b = new Point(-1073741824, 0);
    }

    public aj(Polyline polyline, float f, int i, FeatureId featureId, boolean z) {
        this.f4245r = false;
        this.f4230c = polyline;
        this.f4241n = f;
        this.f4242o = i;
        this.f4233f = ar.m2515a();
        this.f4234g = featureId;
        this.f4245r = z;
        List a = ar.m2515a();
        a.add(polyline);
        this.f4244q = GeometryUtil.m7545a(a);
        this.f4238k = new VertexBuffer(this.f4244q);
        this.f4240m = new TexCoordBuffer(this.f4244q);
        this.f4239l = new IndexBuffer(GeometryUtil.m7549b(a));
    }

    public aj(Polyline polyline, float f, int i, FeatureId featureId) {
        this(polyline, f, i, null, false);
    }

    public final GLOverlay m6809d() {
        return GLOverlay.UNUSED;
    }

    public final boolean m6806a(Camera camera, GLState gLState) {
        boolean z;
        if (this.f4235h == null) {
            z = true;
        } else {
            float i = camera.m7441i();
            z = (i > this.f4236i * dm.DEFAULT_BACKOFF_MULT || i < this.f4236i / dm.DEFAULT_BACKOFF_MULT) ? true : !this.f4235h.m6049b(camera.m7453u().m5668c());
        }
        if (z) {
            m6802b(camera);
            synchronized (this) {
                this.f4243p = true;
            }
        }
        return true;
    }

    public final void m6805a(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6704b() == 0) {
            if (this.f4235h == null) {
                m6802b(camera);
            }
            if (m6801a(camera)) {
                this.f4238k.m7730a(gLState);
                this.f4239l.m7664a(gLState);
                this.f4240m.m7695a(gLState);
                for (Polyline a : this.f4233f) {
                    m6799a(a, camera);
                }
                this.f4237j = camera.m7441i();
            }
            if (this.f4238k.m7725a() > 0) {
                RenderTweak renderTweak;
                GL10 x = gLState.m7541x();
                x.glPushMatrix();
                GL10 x2 = gLState.m7541x();
                if (this.f4234g == null || this.f4233f.size() == 0 || ((Polyline) this.f4233f.get(0)).m6020b() == 0) {
                    renderTweak = null;
                } else {
                    Object d = IndoorState.m5334a().m5362d(this.f4234g);
                }
                if (renderTweak != null) {
                    renderTweak.m5295a(gLState, camera, adVar, ((Polyline) this.f4233f.get(0)).m6014a(0));
                }
                Transform.m7280b(gLState, camera, this.f4235h.m6050c(), (float) this.f4235h.m6053f());
                gLState.m7533p();
                x2.glBlendFunc(1, 771);
                x2.glTexEnvx(8960, 8704, 8448);
                m6800a(x2);
                gLState.m7513a().m7630a(24).m7613a(gLState.m7541x());
                this.f4238k.m7736d(gLState);
                this.f4240m.m7706d(gLState);
                this.f4239l.m7665a(gLState, 4);
                gLState.m7541x().glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
                if (renderTweak != null) {
                    renderTweak.m5294a(gLState, adVar);
                }
                x.glPopMatrix();
            }
        }
    }

    public final void m6804a(GLState gLState) {
        m6808b(gLState);
    }

    public final void m6808b(GLState gLState) {
        this.f4238k.m7734c(gLState);
        this.f4239l.m7673c(gLState);
        this.f4240m.m7705c(gLState);
    }

    private void m6800a(GL10 gl10) {
        int i;
        int i2;
        int i3;
        int i4;
        synchronized (this) {
            i = (this.f4242o >> 16) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            i2 = (this.f4242o >> 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            i3 = this.f4242o & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            i4 = (this.f4242o << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        }
        gl10.glColor4x(i2, i3, i4, i);
    }

    private boolean m6801a(Camera camera) {
        synchronized (this) {
            if (this.f4243p) {
                this.f4243p = false;
                return true;
            }
            float i = camera.m7441i();
            if (i > this.f4237j * 1.25f || i < this.f4237j / 1.25f) {
                return true;
            }
            return false;
        }
    }

    private void m6802b(Camera camera) {
        Polyline polyline;
        Point point;
        Point point2;
        this.f4233f.clear();
        float l = camera.m7444l();
        if (l > 10.0f) {
            polyline = this.f4230c;
        } else {
            if (this.f4231d == null) {
                this.f4231d = this.f4230c.m6021b((float) am.m6841a(10, this.f4245r));
                this.f4232e = this.f4231d.m6021b((float) am.m6841a(6, this.f4245r));
            }
            polyline = l > 6.0f ? this.f4231d : this.f4232e;
        }
        Rectangle2D b = camera.m7453u().m5667b();
        int f = b.m6053f();
        int g = b.m6054g();
        Point point3;
        if (f > 119304647 || g > 119304647) {
            point3 = new Point(b.m6052e().m5958f() - 536870912, -1073741824);
            point = new Point((b.m6052e().m5958f() + 536870912) - 1, 1073741823);
            point2 = point3;
        } else {
            Point point4 = new Point(f * 4, g * 4);
            point3 = b.m6050c().m5959f(point4);
            point = b.m6051d().m5957e(point4);
            point2 = point3;
        }
        this.f4235h = new Rectangle2D(point2, point);
        List<Polyline> a = ar.m2515a();
        Polyline b2 = polyline.m6021b((float) am.m6841a(((int) camera.m7444l()) + 1, this.f4245r));
        List a2;
        if (b2.m6020b() < 2) {
            a2 = ar.m2518a(b2);
        } else {
            List a3 = ar.m2515a();
            Polyline.Polyline polyline2 = new Polyline.Polyline(b2.m6020b());
            Point a4 = b2.m6014a(0);
            polyline2.m6006a(a4);
            Point point5 = new Point();
            for (int i = 1; i < b2.m6020b(); i++) {
                b2.m6016a(i, point5);
                int f2;
                int g2;
                int round;
                if (point5.m5958f() < a4.m5958f()) {
                    f2 = (point5.m5958f() - a4.m5958f()) + 1073741824;
                    if (f2 < a4.m5958f() - point5.m5958f()) {
                        g2 = point5.m5960g() - a4.m5960g();
                        round = ((int) Math.round((((double) g2) * ((double) (536870912 - a4.m5958f()))) / ((double) f2))) + a4.m5960g();
                        polyline2.m6006a(new Point(536870911, round));
                        a3.add(polyline2.m6008c());
                        polyline2.m6007b();
                        polyline2.m6006a(new Point(-536870912, round));
                    }
                } else if (point5.m5958f() > a4.m5958f()) {
                    f2 = (a4.m5958f() - point5.m5958f()) + 1073741824;
                    if (f2 < point5.m5958f() - a4.m5958f()) {
                        g2 = point5.m5960g() - a4.m5960g();
                        round = ((int) Math.round((((double) g2) * ((double) (a4.m5958f() + 536870912))) / ((double) f2))) + a4.m5960g();
                        polyline2.m6006a(new Point(-536870912, round));
                        a3.add(polyline2.m6008c());
                        polyline2.m6007b();
                        polyline2.m6006a(new Point(536870911, round));
                    }
                }
                polyline2.m6006a(point5);
                a4.m5950b(point5);
            }
            polyline = polyline2.m6008c();
            if (polyline.m6020b() >= 2) {
                a3.add(polyline);
            }
            a2 = a3;
        }
        for (Polyline polyline3 : r2) {
            new ax(this.f4235h).m5726a(polyline3, a);
            this.f4233f.addAll(a);
            a.clear();
            new ax(new Rectangle2D(point2.m5957e(f4228a), point.m5957e(f4228a))).m5726a(polyline3, a);
            for (Polyline b3 : a) {
                this.f4233f.add(b3.m6022b(f4229b));
            }
            a.clear();
            new ax(new Rectangle2D(point2.m5957e(f4229b), point.m5957e(f4229b))).m5726a(polyline3, a);
            for (Polyline polyline32 : a) {
                this.f4233f.add(polyline32.m6022b(f4228a));
            }
            a.clear();
        }
        this.f4236i = camera.m7441i();
    }

    private void m6799a(Polyline polyline, Camera camera) {
        float r;
        Point c = this.f4235h.m6050c();
        int f = this.f4235h.m6053f();
        synchronized (this) {
            r = (((camera.m7450r() * this.f4241n) * 0.5f) / 7.0f) * 8.0f;
        }
        GeometryUtil.m7546a().m7551a(polyline, r, r, c, f, 0, (int) AccessibilityNodeInfoCompat.ACTION_CUT, this.f4238k, this.f4239l, this.f4240m);
    }

    public final synchronized void m6807b(int i) {
        this.f4242o = i;
    }

    public final synchronized void m6803a(float f) {
        this.f4241n = f;
        this.f4243p = true;
    }
}
