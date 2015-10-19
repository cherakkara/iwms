package com.google.android.m4b.maps.p061y;

import android.util.FloatMath;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.PositionChangeFinishedCallback;
import com.google.android.m4b.maps.av.VectorMapController;
import com.google.android.m4b.maps.av.aa;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.ax.CameraPositionProvider;
import com.olacabs.customer.p076d.br;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.y.v */
public final class VectorMapControllerAdapter {
    private int f8234a;
    private int f8235b;
    private int f8236c;
    private int f8237d;
    private int f8238e;
    private int f8239f;
    private int f8240g;
    private int f8241h;
    private VectorMapView f8242i;
    private final VectorMapController f8243j;

    public VectorMapControllerAdapter(VectorMapController vectorMapController, VectorMapView vectorMapView) {
        this.f8234a = 0;
        this.f8235b = 0;
        this.f8236c = 0;
        this.f8237d = 0;
        this.f8238e = 0;
        this.f8239f = 0;
        this.f8240g = 0;
        this.f8241h = 0;
        this.f8242i = null;
        this.f8243j = vectorMapController;
        this.f8242i = vectorMapView;
    }

    public final void m11956a(int i, int i2, int i3, int i4) {
        this.f8234a = i;
        this.f8235b = i2;
        this.f8236c = i3;
        this.f8237d = i4;
    }

    public final int m11953a() {
        return this.f8234a + this.f8236c;
    }

    public final int m11962b() {
        return this.f8235b + this.f8237d;
    }

    private void m11949f() {
        if (this.f8242i != null) {
            this.f8238e = (this.f8242i.getWidth() - this.f8234a) - this.f8236c;
            this.f8239f = (this.f8242i.getHeight() - this.f8235b) - this.f8237d;
            this.f8240g = this.f8234a + (this.f8238e / 2);
            this.f8241h = this.f8235b + (this.f8239f / 2);
        }
    }

    public final CameraPosition m11954a(CameraPosition cameraPosition, float f) {
        m11949f();
        Camera t = this.f8242i.m11872t();
        float pow = ((float) Math.pow(2.0d, (double) (t.m7444l() - cameraPosition.m7457a()))) * f;
        float width = ((float) ((((double) this.f8242i.getWidth()) / 2.0d) - ((double) this.f8240g))) * pow;
        width *= t.m7450r();
        pow = ((-(pow * ((float) ((((double) this.f8242i.getHeight()) / 2.0d) - ((double) this.f8241h))))) * t.m7450r()) / FloatMath.cos(t.m7443k() * 0.017453292f);
        Point o = t.m7447o();
        Point p = t.m7448p();
        Point point = new Point(o.m5958f(), o.m5960g());
        o = new Point(p.m5958f(), p.m5960g());
        Point.m5935b(point, width, point);
        Point.m5935b(o, pow, o);
        p = cameraPosition.m7460c();
        width = cameraPosition.m7457a();
        int h = p.m5962h();
        Point e = p.m5957e(point);
        Point.m5931a(e, o, e);
        e.m5944a(h);
        return new CameraPosition(e, width, cameraPosition.m7461d(), cameraPosition.m7462e(), 0.0f);
    }

    public final void m11961a(CameraPositionProvider cameraPositionProvider, int i, int i2) {
        Object obj = (this.f8242i == null || (this.f8234a == 0 && this.f8236c == 0 && this.f8235b == 0 && this.f8237d == 0)) ? null : 1;
        if (obj != null) {
            this.f8243j.m7326a(m11954a(cameraPositionProvider.m7291b(), (float) br.DEFAULT_BACKOFF_MULT), i, i2);
        } else {
            this.f8243j.m7326a(cameraPositionProvider, i, i2);
        }
    }

    private void m11948a(CameraPositionProvider cameraPositionProvider, int i) {
        m11961a(cameraPositionProvider, i, i == 0 ? 0 : -1);
    }

    public final void m11957a(Point point, int i) {
        Camera t = this.f8242i.m11872t();
        m11948a(new CameraPosition(point, t.m7444l(), t.m7443k(), t.m7442j(), 0.0f), (int) HttpStatus.SC_MULTIPLE_CHOICES);
    }

    public final CameraPosition m11964c() {
        return m11954a(this.f8243j.m7330b(), -1.0f);
    }

    public final float m11952a(Point point) {
        return this.f8243j.m7318a(point);
    }

    public final float m11965d() {
        return this.f8243j.m7314a();
    }

    public final void m11955a(float f, float f2) {
        this.f8243j.m7320a(0.0f, 0.0f);
    }

    public final void m11959a(PositionChangeFinishedCallback positionChangeFinishedCallback) {
        this.f8243j.m7322a(positionChangeFinishedCallback);
    }

    public final void m11958a(aa aaVar) {
        this.f8243j.m7321a(aaVar);
    }

    public final float m11966e() {
        return this.f8243j.m7333c();
    }

    public final float m11951a(float f, int i) {
        m11949f();
        return m11950a(f, (float) this.f8240g, (float) this.f8241h, i);
    }

    public final void m11963b(float f, int i) {
        m11949f();
        CameraPosition c = m11964c();
        m11948a(new CameraPosition(c.m7460c(), f, c.m7461d(), c.m7462e(), c.m7463f()), i);
    }

    public final float m11950a(float f, float f2, float f3, int i) {
        return this.f8243j.m7316a(f, f2, f3, i);
    }

    public final void m11960a(VectorMapController.VectorMapController vectorMapController) {
        this.f8243j.m7324a(vectorMapController);
    }
}
