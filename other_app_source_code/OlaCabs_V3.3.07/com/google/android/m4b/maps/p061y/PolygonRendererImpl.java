package com.google.android.m4b.maps.p061y;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.av.am;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.be.aj;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.model.LatLng;
import com.google.p025a.p028c.ar;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.y.o */
public final class PolygonRendererImpl implements PolyModel, Overlay {
    private final aj f8144a;
    private Polyline f8145b;
    private List<Polyline> f8146c;
    private am f8147d;
    private float f8148e;
    private GLState f8149f;
    private RepaintCallback f8150g;
    private final OverlayRendererManagerImpl f8151h;

    public PolygonRendererImpl(OverlayRendererManagerImpl overlayRendererManagerImpl, aj ajVar) {
        this.f8151h = overlayRendererManagerImpl;
        this.f8144a = ajVar;
        m11807a(-1);
    }

    public final void m11807a(int i) {
        if ((i & 3) != 0) {
            this.f8145b = PolygonRendererImpl.m11805a(this.f8144a.m8445b());
            this.f8146c = ar.m2515a();
            for (List a : this.f8144a.m8446d()) {
                this.f8146c.add(PolygonRendererImpl.m11805a(a));
            }
            this.f8147d = new am(this.f8145b, this.f8146c, (int) this.f8144a.m8447e(), ConversionUtils.m11631a(this.f8144a.m8448f()), ConversionUtils.m11631a(this.f8144a.m8449g()), true);
            if (!(this.f8149f == null || this.f8150g == null)) {
                this.f8147d.m6855a(this.f8149f, this.f8150g);
            }
        }
        if ((i & 16) != 0) {
            this.f8147d.m6860c(ConversionUtils.m11631a(this.f8144a.m8449g()));
        }
        if ((i & 8) != 0) {
            this.f8147d.m6858b(ConversionUtils.m11631a(this.f8144a.m8448f()));
        }
        if ((i & 4) != 0) {
            this.f8147d.m6862d((int) this.f8144a.m8447e());
        }
        if ((i & 64) != 0) {
            synchronized (this.f8151h) {
                this.f8148e = this.f8144a.m8451i();
                this.f8151h.m11801c();
            }
        }
        this.f8151h.m11800b();
    }

    public final void m11806a() {
        synchronized (this.f8151h) {
            this.f8151h.m11796a((Overlay) this);
        }
        this.f8151h.m11800b();
    }

    private static Polyline m11805a(List<LatLng> list) {
        Polyline.Polyline polyline = new Polyline.Polyline();
        for (LatLng b : list) {
            polyline.m6006a(ConversionUtils.m11640b(b));
        }
        Polyline c = polyline.m6008c();
        if (c.m6025c(c.m6030g()).m6029f()) {
            return c;
        }
        return c.m6031h();
    }

    public final synchronized void m11811a(GLState gLState, RepaintCallback repaintCallback) {
        this.f8149f = gLState;
        this.f8150g = repaintCallback;
        this.f8147d.m6855a(gLState, repaintCallback);
    }

    public final void m11810a(GLState gLState) {
    }

    public final void m11813a(boolean z) {
    }

    public final void m11815b() {
    }

    public final void m11816b(int i) {
    }

    public final synchronized void m11809a(Camera camera, GLState gLState) {
        if (this.f8144a != null && this.f8144a.m8450h()) {
            this.f8147d.m6857a(camera, gLState);
        }
    }

    public final synchronized void m11812a(GLState gLState, Camera camera, ad adVar) {
        if (this.f8144a != null && this.f8144a.m8450h()) {
            this.f8147d.m6856a(gLState, camera, adVar);
        }
    }

    public final boolean m11814a(float f, float f2, Point point, Camera camera) {
        return false;
    }

    public final void m11808a(long j) {
    }

    public final synchronized boolean m11817c() {
        boolean z;
        z = this.f8144a == null || !this.f8144a.m8450h() || this.f8147d.m6863e();
        return z;
    }

    public final String m11819e() {
        return this.f8144a.m8444a();
    }

    public final float m11820f() {
        return this.f8148e;
    }

    public final void m11818d() {
    }
}
