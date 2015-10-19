package com.google.android.m4b.maps.ax;

import android.opengl.Matrix;
import android.util.FloatMath;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ar;
import com.google.android.m4b.maps.an.bd;
import com.google.android.m4b.maps.bd.Camera3D;
import com.google.android.m4b.maps.bd.RenderTarget;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.google.android.m4b.maps.ax.b */
public final class Camera extends Camera3D {
    private static final AtomicLong f4778C;
    public static final CameraPosition f4779a;
    private static final float f4780b;
    private static float f4781c;
    private static final Point f4782d;
    private static final float f4783e;
    private final float[] f4784A;
    private final Point f4785B;
    private volatile long f4786D;
    private CameraPosition f4787f;
    private boolean f4788g;
    private Point f4789h;
    private float f4790i;
    private float f4791j;
    private float f4792k;
    private float f4793l;
    private int f4794m;
    private int f4795n;
    private float f4796o;
    private Point f4797p;
    private Point f4798q;
    private Point f4799r;
    private ar f4800s;
    private float f4801t;
    private double[] f4802u;
    private float[] f4803v;
    private float[] f4804w;
    private float[] f4805x;
    private float[] f4806y;
    private float[] f4807z;

    static {
        f4780b = (float) (1.0d / Math.log(2.0d));
        f4781c = 262144.0f * ((float) Math.cos(1.3089969389957472d));
        f4782d = new Point(0, 0, 1);
        f4779a = new CameraPosition(new Point(0, 0), (float) dm.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f);
        f4783e = (float) (0.5d / Math.tan(0.2617993877991494d));
        f4778C = new AtomicLong(1);
    }

    public Camera(CameraPosition cameraPosition, int i, int i2, float f) {
        this(cameraPosition, i, i2, f, null);
    }

    public Camera(CameraPosition cameraPosition, int i, int i2, float f, Thread thread) {
        this(cameraPosition, i, i2, f, null, null, 0, new float[16]);
    }

    private Camera(CameraPosition cameraPosition, int i, int i2, float f, Thread thread, RenderTarget renderTarget, int i3, float[] fArr) {
        super(null, 0, fArr);
        this.f4792k = f4783e;
        this.f4803v = null;
        this.f4804w = null;
        this.f4805x = null;
        this.f4806y = null;
        this.f4807z = null;
        this.f4784A = new float[8];
        this.f4785B = new Point();
        this.f4786D = f4778C.getAndIncrement();
        this.f4795n = i2;
        this.f4794m = i;
        this.f4796o = f;
        this.f4788g = false;
        float min = Math.min(Math.max(0.0f, 30.0f), 90.0f);
        if (min != this.f4793l) {
            this.f4793l = min;
            this.f4786D = f4778C.incrementAndGet();
            this.f4791j = (float) (0.5d / Math.tan((((double) this.f4793l) * 0.017453292519943295d) * 0.5d));
            m7418b(true);
            if (!(this.f4787f == null || this.f4787f.m7463f() == 0.0f)) {
                this.f4789h = new Point();
                m7408A();
            }
        }
        m7417b(cameraPosition);
    }

    public final long m7421a() {
        return this.f4786D;
    }

    public static float m7414a(float f) {
        return ((float) Math.log((double) f)) * f4780b;
    }

    public final Point m7430b() {
        return Point.m5925a(this.f4789h);
    }

    public final void m7425a(Point point) {
        point.m5950b(this.f4789h);
    }

    public final Point m7434c() {
        return this.f4787f.m7460c();
    }

    public final boolean m7436d() {
        return this.f4788g;
    }

    public final int m7437e() {
        return this.f4794m;
    }

    public final int m7438f() {
        return this.f4795n;
    }

    public final float m7439g() {
        return this.f4796o;
    }

    public final float m7440h() {
        return this.f4793l;
    }

    public final void m7424a(int i, int i2, float f) {
        this.f4786D = f4778C.incrementAndGet();
        this.f4794m = Math.max(1, i);
        this.f4795n = Math.max(1, i2);
        this.f4796o = f;
        m7409B();
        m7408A();
        m7418b(true);
    }

    public final float m7441i() {
        return this.f4790i;
    }

    public final void m7428a(boolean z) {
        if (this.f4788g != z) {
            this.f4788g = z;
            this.f4786D = f4778C.incrementAndGet();
        }
    }

    private void m7417b(CameraPosition cameraPosition) {
        if (this.f4787f == null || !this.f4787f.equals(cameraPosition)) {
            this.f4786D = f4778C.incrementAndGet();
            m7418b(false);
            if (!(this.f4787f != null && cameraPosition.m7462e() == this.f4787f.m7462e() && cameraPosition.m7461d() == this.f4787f.m7461d())) {
                this.f4802u = null;
            }
            this.f4787f = cameraPosition;
            m7409B();
            if (this.f4787f.m7463f() != 0.0f) {
                this.f4789h = new Point();
                m7408A();
            } else {
                this.f4789h = this.f4787f.m7460c();
            }
            m7404w();
        }
    }

    public final void m7427a(CameraPosition cameraPosition) {
        m7417b(cameraPosition);
    }

    private void m7408A() {
        double e = ((double) this.f4787f.m7462e()) * 0.017453292519943295d;
        double d = ((double) this.f4787f.m7461d()) * 0.017453292519943295d;
        double f = ((double) ((this.f4787f.m7463f() * 0.5f) * this.f4793l)) * 0.017453292519943295d;
        d = (Math.sin(d) - (Math.tan(d - f) * Math.cos(d))) * ((double) this.f4790i);
        int sin = (int) (Math.sin(e) * d);
        int cos = (int) (Math.cos(e) * d);
        Point c = this.f4787f.m7460c();
        this.f4789h.m5955d(c.m5958f() + sin, cos + c.m5960g());
    }

    private void m7409B() {
        this.f4790i = ((float) (Math.pow(2.0d, (double) (30.0f - this.f4787f.m7457a())) * (((double) this.f4795n) / ((double) (256.0f * this.f4796o))))) * this.f4792k;
    }

    public final float m7442j() {
        return this.f4787f.m7462e();
    }

    public final float m7443k() {
        return this.f4787f.m7461d();
    }

    public final float m7444l() {
        return this.f4787f.m7457a();
    }

    public final float m7445m() {
        return this.f4787f.m7457a();
    }

    private double[] m7410C() {
        if (this.f4802u == null) {
            double e = 90.0d - ((double) this.f4787f.m7462e());
            if (e < 0.0d) {
                e += 360.0d;
            } else if (e >= 360.0d) {
                e -= 360.0d;
            }
            double d = ((double) this.f4787f.m7461d()) * 0.017453292519943295d;
            e *= 0.017453292519943295d;
            double sin = Math.sin(d);
            this.f4802u = new double[3];
            this.f4802u[0] = Math.cos(e) * sin;
            this.f4802u[1] = Math.sin(e) * sin;
            this.f4802u[2] = -Math.cos(d);
        }
        return this.f4802u;
    }

    public final Point m7446n() {
        if (this.f4797p == null) {
            double[] C = m7410C();
            this.f4797p = new Point((int) Math.round(((double) (-this.f4790i)) * C[0]), (int) Math.round(((double) (-this.f4790i)) * C[1]), (int) Math.round(((double) (-this.f4790i)) * C[2]));
            Point.m5931a(this.f4797p, this.f4789h, this.f4797p);
        }
        return this.f4797p;
    }

    public final Point m7447o() {
        if (this.f4799r == null) {
            Point n = m7446n();
            if (this.f4787f.m7461d() == 0.0f || (n.m5958f() == this.f4789h.m5958f() && n.m5960g() == this.f4789h.m5960g())) {
                double d = (double) (-this.f4787f.m7462e());
                if (d < 0.0d) {
                    d += 360.0d;
                } else if (d >= 360.0d) {
                    d -= 360.0d;
                }
                d *= 0.017453292519943295d;
                this.f4799r = new Point((int) (Math.cos(d) * 65536.0d), (int) (Math.sin(d) * 65536.0d));
            } else {
                n = this.f4789h.m5959f(n).m5961g(f4782d);
                Point.m5935b(n, 65536.0f, n);
                this.f4799r = n;
            }
        }
        return this.f4799r;
    }

    public final Point m7448p() {
        if (this.f4798q == null) {
            Point n = m7446n();
            if (((double) this.f4787f.m7461d()) == 0.0d || (n.m5958f() == this.f4789h.m5958f() && n.m5960g() == this.f4789h.m5960g())) {
                double e = 90.0d - ((double) this.f4787f.m7462e());
                if (e < 0.0d) {
                    e += 360.0d;
                } else if (e >= 360.0d) {
                    e -= 360.0d;
                }
                e *= 0.017453292519943295d;
                this.f4798q = new Point((int) (Math.cos(e) * 65536.0d), (int) (Math.sin(e) * 65536.0d));
            } else {
                n = this.f4789h.m5959f(n);
                Point.m5935b(n, 16384.0f, n);
                n = m7447o().m5961g(n);
                Point.m5935b(n, 65536.0f, n);
                this.f4798q = n;
            }
        }
        return this.f4798q;
    }

    public final float m7449q() {
        if (this.f4801t < 0.0f) {
            this.f4801t = br.DEFAULT_BACKOFF_MULT / ((float) m7446n().m5962h());
        }
        return this.f4801t;
    }

    public final float m7419a(float f, float f2) {
        return (f2 * f) / (this.f4791j * ((float) this.f4795n));
    }

    public final float m7429b(float f, float f2) {
        return ((this.f4791j * f) * ((float) this.f4795n)) / f2;
    }

    public final float m7420a(Point point, boolean z) {
        double[] C = m7410C();
        Point.m5936b(point, m7446n(), this.f4785B);
        this.f4785B.m5965i(this.f4785B);
        return (float) ((C[2] * ((double) this.f4785B.m5962h())) + ((((double) this.f4785B.m5958f()) * C[0]) + (((double) this.f4785B.m5960g()) * C[1])));
    }

    public final float m7432c(float f, float f2) {
        return 30.0f - Camera.m7414a((f / f2) * (256.0f * this.f4796o));
    }

    public final float m7450r() {
        return m7419a((float) br.DEFAULT_BACKOFF_MULT, this.f4790i);
    }

    public final float[] m7451s() {
        if (this.f4803v == null) {
            m7412E();
        }
        return this.f4803v;
    }

    public final float[] m7452t() {
        if (this.f4804w == null) {
            m7411D();
        }
        return this.f4804w;
    }

    public final Point m7435d(float f, float f2) {
        if (this.f4807z == null) {
            m7413F();
        }
        float[] fArr = this.f4784A;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = br.DEFAULT_BACKOFF_MULT;
        fArr[3] = br.DEFAULT_BACKOFF_MULT;
        Matrix.multiplyMV(fArr, 4, this.f4807z, 0, fArr, 0);
        float f3 = br.DEFAULT_BACKOFF_MULT / fArr[7];
        double d = (double) (fArr[4] * f3);
        double d2 = (double) (fArr[5] * f3);
        double d3 = (double) (fArr[6] * f3);
        if (d3 >= 1.0d) {
            return null;
        }
        Point n = m7446n();
        int f4 = n.m5958f();
        int g = n.m5960g();
        int h = n.m5962h();
        d3 = 1.0d / (1.0d - d3);
        return new Point((int) (((((d * ((double) h)) + ((double) this.f4789h.m5958f())) - ((double) f4)) * d3) + ((double) f4)), (int) (((double) g) + (d3 * (((((double) h) * d2) + ((double) this.f4789h.m5960g())) - ((double) g)))));
    }

    public final bd m7423a(float f, float f2, float f3, float f4) {
        if (this.f4807z == null) {
            m7413F();
        }
        Point n = m7446n();
        int f5 = n.m5958f();
        int g = n.m5960g();
        int h = n.m5962h();
        int f6 = this.f4789h.m5958f() - f5;
        int g2 = this.f4789h.m5960g() - g;
        float[] fArr = this.f4784A;
        fArr[0] = f;
        fArr[1] = f3;
        fArr[2] = br.DEFAULT_BACKOFF_MULT;
        fArr[3] = br.DEFAULT_BACKOFF_MULT;
        Matrix.multiplyMV(fArr, 4, this.f4807z, 0, fArr, 0);
        float f7 = br.DEFAULT_BACKOFF_MULT / fArr[7];
        double d = (double) (fArr[4] * f7);
        double d2 = (double) (fArr[5] * f7);
        double d3 = (double) (f7 * fArr[6]);
        if (d3 >= 1.0d) {
            return null;
        }
        d3 = 1.0d / (1.0d - d3);
        double d4 = (double) f6;
        d4 = (double) f5;
        d4 = (double) g2;
        Point point = new Point((int) ((((d * ((double) h)) + r0) * d3) + r0), (int) ((((d2 * ((double) h)) + r0) * d3) + ((double) g)));
        fArr[0] = f2;
        Matrix.multiplyMV(fArr, 4, this.f4807z, 0, fArr, 0);
        f7 = br.DEFAULT_BACKOFF_MULT / fArr[7];
        d = (double) (fArr[4] * f7);
        d2 = (double) (fArr[5] * f7);
        d3 = (double) (f7 * fArr[6]);
        if (d3 >= 1.0d) {
            return null;
        }
        d3 = 1.0d / (1.0d - d3);
        d4 = (double) f6;
        d4 = (double) f5;
        d4 = (double) g2;
        Point point2 = new Point((int) ((((d * ((double) h)) + r0) * d3) + r0), (int) ((((d2 * ((double) h)) + r0) * d3) + ((double) g)));
        fArr[1] = f4;
        Matrix.multiplyMV(fArr, 4, this.f4807z, 0, fArr, 0);
        f7 = br.DEFAULT_BACKOFF_MULT / fArr[7];
        d = (double) (fArr[4] * f7);
        d2 = (double) (fArr[5] * f7);
        d4 = (double) (f7 * fArr[6]);
        if (d4 >= 1.0d) {
            return null;
        }
        d4 = 1.0d / (1.0d - d4);
        double d5 = (double) f6;
        d5 = (double) f5;
        d5 = (double) g2;
        d4 = (double) g;
        Point point3 = new Point((int) ((((d * ((double) h)) + r0) * d4) + r0), (int) ((((d2 * ((double) h)) + r0) * d4) + r0));
        fArr[0] = f;
        Matrix.multiplyMV(fArr, 4, this.f4807z, 0, fArr, 0);
        f7 = br.DEFAULT_BACKOFF_MULT / fArr[7];
        d = (double) (fArr[4] * f7);
        d2 = (double) (fArr[5] * f7);
        double d6 = (double) (fArr[6] * f7);
        if (d6 >= 1.0d) {
            return null;
        }
        d6 = 1.0d / (1.0d - d6);
        return bd.m5753a(new Point((int) ((((d * ((double) h)) + ((double) f6)) * d6) + ((double) f5)), (int) ((d6 * ((d2 * ((double) h)) + ((double) g2))) + ((double) g))), point3, point, point2);
    }

    public final int[] m7431b(Point point) {
        m7426a(point, new float[8]);
        return new int[]{Math.round(r0[0]), Math.round(r0[1])};
    }

    public final void m7426a(Point point, float[] fArr) {
        if (this.f4806y == null) {
            m7413F();
        }
        int f = point.m5958f() - this.f4789h.m5958f();
        if (f >= 536870912) {
            f -= 1073741824;
        } else if (f < -536870912) {
            f += 1073741824;
        }
        int h = m7446n().m5962h();
        fArr[0] = (float) f;
        fArr[1] = (float) (point.m5960g() - this.f4789h.m5960g());
        fArr[2] = (float) point.m5962h();
        fArr[3] = (float) h;
        Matrix.multiplyMV(fArr, 4, this.f4806y, 0, fArr, 0);
        float f2 = br.DEFAULT_BACKOFF_MULT / fArr[7];
        fArr[0] = fArr[4] * f2;
        fArr[1] = f2 * fArr[5];
    }

    public final ar m7453u() {
        return m7422a(0, 0, 0, 0);
    }

    public final ar m7422a(int i, int i2, int i3, int i4) {
        if (this.f4800s == null) {
            this.f4800s = m7415a((this.f4793l * 0.5f) + this.f4787f.m7461d(), i, i2, i3, i4);
        }
        return this.f4800s;
    }

    public final ar m7454v() {
        if (this.f4787f.m7461d() == 0.0f) {
            return m7453u();
        }
        float d = this.f4787f.m7461d() * 0.017453292f;
        return m7433c(((((float) Math.atan((Math.sin((double) d) + ((double) (0.5f / this.f4791j))) / Math.cos((double) d))) - d) * 57.29578f) + this.f4787f.m7461d());
    }

    public static float m7416b(float f) {
        if (f < f4781c) {
            return ((float) Math.acos((double) (f / 262144.0f))) * 57.29578f;
        }
        return 75.0f;
    }

    public final ar m7433c(float f) {
        return m7415a(f, 0, 0, 0, 0);
    }

    private ar m7415a(float f, int i, int i2, int i3, int i4) {
        float min = Math.min(f, Camera.m7416b((float) m7446n().m5962h()));
        float f2 = ((float) this.f4795n) * this.f4791j;
        float ceil = FloatMath.ceil((((float) this.f4795n) * 0.5f) - (((float) Math.tan((double) ((min - this.f4787f.m7461d()) * 0.017453292f))) * f2));
        Point d = m7435d((float) i, (float) (this.f4795n - i4));
        Point d2 = m7435d((float) (this.f4794m - i3), (float) (this.f4795n - i4));
        Point d3 = m7435d((float) i, ((float) i2) + ceil);
        Point d4 = m7435d((float) (this.f4794m - i3), ((float) i2) + ceil);
        if (d != null && d2 != null && d3 != null && d4 != null) {
            return ar.m5660a(d, d2, d3, d4);
        }
        throw new IllegalStateException("pos: " + this.f4787f.toString() + " farAngle: " + min + " size: " + this.f4794m + "x" + this.f4795n + " top:" + ceil);
    }

    public final String toString() {
        return "[" + new Point(Math.round((float) this.f4789h.m5958f()), Math.round((float) this.f4789h.m5960g()), Math.round((float) this.f4789h.m5962h())) + ", " + this.f4790i + ", " + this.f4787f.m7462e() + ", " + this.f4787f.m7461d() + ", " + this.f4793l + "]";
    }

    public final int hashCode() {
        int i;
        int floatToIntBits = (((((((((Float.floatToIntBits(this.f4790i) + 31) * 31) + this.f4795n) * 31) + this.f4794m) * 31) + Float.floatToIntBits(this.f4793l)) * 31) + Float.floatToIntBits(this.f4796o)) * 31;
        if (this.f4787f == null) {
            i = 0;
        } else {
            i = this.f4787f.hashCode();
        }
        return i + floatToIntBits;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Camera)) {
            return false;
        }
        Camera camera = (Camera) obj;
        if (this.f4790i == camera.f4790i && this.f4787f.equals(camera.f4787f) && this.f4793l == camera.f4793l && this.f4795n == camera.f4795n && this.f4794m == camera.f4794m && this.f4796o == camera.f4796o) {
            return true;
        }
        return false;
    }

    private void m7418b(boolean z) {
        this.f4797p = null;
        this.f4798q = null;
        this.f4799r = null;
        this.f4800s = null;
        this.f4801t = -1.0f;
        this.f4803v = null;
        this.f4806y = null;
        this.f4807z = null;
        if (z) {
            this.f4804w = null;
            this.f4805x = null;
        }
    }

    private void m7411D() {
        this.f4804w = new float[16];
        float f = (((float) this.f4794m) * br.DEFAULT_BACKOFF_MULT) / ((float) this.f4795n);
        float tan = 0.1f * ((float) Math.tan((((double) this.f4793l) * 0.017453292519943295d) * 0.5d));
        float f2 = -tan;
        Matrix.frustumM(this.f4804w, 0, f2 * f, tan * f, f2, tan, 0.1f, 20.0f);
    }

    private void m7412E() {
        this.f4803v = new float[16];
        float q = m7449q();
        Point f = m7446n().m5959f(this.f4789h);
        float f2 = ((float) f.m5958f()) * q;
        float g = ((float) f.m5960g()) * q;
        q *= (float) f.m5962h();
        f = m7448p();
        float[] fArr = this.f4803v;
        float f3 = (float) f.m5958f();
        float g2 = (float) f.m5960g();
        float h = (float) f.m5962h();
        float f4 = -f2;
        float f5 = -g;
        float f6 = -q;
        float length = br.DEFAULT_BACKOFF_MULT / Matrix.length(f4, f5, f6);
        f4 *= length;
        f5 *= length;
        f6 *= length;
        length = (f5 * h) - (f6 * g2);
        h = (f6 * f3) - (h * f4);
        f3 = (g2 * f4) - (f3 * f5);
        g2 = br.DEFAULT_BACKOFF_MULT / Matrix.length(length, h, f3);
        length *= g2;
        h *= g2;
        f3 *= g2;
        g2 = (h * f6) - (f3 * f5);
        float f7 = (f3 * f4) - (length * f6);
        float f8 = (length * f5) - (h * f4);
        fArr[0] = length;
        fArr[1] = g2;
        fArr[2] = -f4;
        fArr[3] = 0.0f;
        fArr[4] = h;
        fArr[5] = f7;
        fArr[6] = -f5;
        fArr[7] = 0.0f;
        fArr[8] = f3;
        fArr[9] = f8;
        fArr[10] = -f6;
        fArr[11] = 0.0f;
        fArr[12] = 0.0f;
        fArr[13] = 0.0f;
        fArr[14] = 0.0f;
        fArr[15] = br.DEFAULT_BACKOFF_MULT;
        Matrix.translateM(fArr, 0, -f2, -g, -q);
    }

    private void m7413F() {
        if (this.f4803v == null) {
            m7412E();
        }
        if (this.f4804w == null) {
            m7411D();
        }
        if (this.f4805x == null) {
            this.f4805x = new float[16];
            this.f4805x[0] = ((float) this.f4794m) * 0.5f;
            this.f4805x[5] = ((float) this.f4795n) * -0.5f;
            this.f4805x[10] = br.DEFAULT_BACKOFF_MULT;
            this.f4805x[15] = br.DEFAULT_BACKOFF_MULT;
            this.f4805x[12] = ((float) this.f4794m) * 0.5f;
            this.f4805x[13] = ((float) this.f4795n) * 0.5f;
        }
        this.f4806y = new float[16];
        float[] fArr = new float[16];
        Matrix.multiplyMM(fArr, 0, this.f4805x, 0, this.f4804w, 0);
        Matrix.multiplyMM(this.f4806y, 0, fArr, 0, this.f4803v, 0);
        this.f4807z = fArr;
        Matrix.invertM(this.f4807z, 0, this.f4806y, 0);
    }
}
