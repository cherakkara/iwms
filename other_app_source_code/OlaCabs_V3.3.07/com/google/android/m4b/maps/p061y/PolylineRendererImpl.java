package com.google.android.m4b.maps.p061y;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.be.aj;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.model.LatLng;
import com.google.p025a.p028c.ar;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.y.p */
public final class PolylineRendererImpl implements PolyModel, Overlay {
    private final aj f8152a;
    private final List<Polyline> f8153b;
    private final List<com.google.android.m4b.maps.av.aj> f8154c;
    private float f8155d;
    private final OverlayRendererManagerImpl f8156e;

    public PolylineRendererImpl(OverlayRendererManagerImpl overlayRendererManagerImpl, aj ajVar) {
        this.f8153b = ar.m2515a();
        this.f8154c = ar.m2515a();
        this.f8156e = overlayRendererManagerImpl;
        this.f8152a = ajVar;
        m11824a(-1);
    }

    public final void m11824a(int i) {
        if ((i & 1) != 0) {
            m11822g();
        }
        if ((i & 8) != 0) {
            for (com.google.android.m4b.maps.av.aj b : this.f8154c) {
                b.m6807b(ConversionUtils.m11631a(this.f8152a.m8448f()));
            }
        }
        if ((i & 4) != 0) {
            for (com.google.android.m4b.maps.av.aj b2 : this.f8154c) {
                b2.m6803a(this.f8152a.m8447e());
            }
        }
        if ((i & 64) != 0) {
            synchronized (this.f8156e) {
                this.f8155d = this.f8152a.m8451i();
                this.f8156e.m11801c();
            }
            this.f8156e.m11800b();
        }
        this.f8156e.m11800b();
    }

    public final void m11823a() {
        synchronized (this.f8156e) {
            this.f8156e.m11796a((Overlay) this);
        }
        this.f8156e.m11800b();
    }

    private synchronized void m11822g() {
        List<LatLng> b = this.f8152a.m8445b();
        List list = this.f8153b;
        list.clear();
        Polyline.Polyline polyline = new Polyline.Polyline();
        Point point = null;
        for (LatLng b2 : b) {
            Polyline.Polyline polyline2;
            Point b3 = ConversionUtils.m11640b(b2);
            if (point == null || Math.abs(b3.m5958f() - point.m5958f()) <= 536870912) {
                polyline.m6006a(b3);
                polyline2 = polyline;
            } else if (b3.m5958f() - point.m5958f() > 536870912) {
                r0 = ((float) (b3.m5960g() - point.m5960g())) / ((float) ((b3.m5958f() - point.m5958f()) - 1073741824));
                r6 = new Point(-536870912, (int) ((((float) (-536870912 - point.m5958f())) * r0) + ((float) point.m5960g())));
                point = new Point(536870911, (int) ((r0 * ((float) (536870912 - b3.m5958f()))) + ((float) b3.m5960g())));
                polyline.m6006a(r6);
                list.add(polyline.m6008c());
                polyline2 = new Polyline.Polyline();
                polyline2.m6006a(point);
                polyline2.m6006a(b3);
            } else if (b3.m5958f() - point.m5958f() < -536870912) {
                r0 = ((float) (point.m5960g() - b3.m5960g())) / ((float) ((point.m5958f() - b3.m5958f()) - 1073741824));
                r6 = new Point(536870911, (int) ((((float) (536870911 - point.m5958f())) * r0) + ((float) point.m5960g())));
                point = new Point(-536870912, (int) ((r0 * ((float) (-536870912 - b3.m5958f()))) + ((float) b3.m5960g())));
                polyline.m6006a(r6);
                list.add(polyline.m6008c());
                polyline2 = new Polyline.Polyline();
                polyline2.m6006a(point);
                polyline2.m6006a(b3);
            } else {
                throw new AssertionError();
            }
            point = b3;
            polyline = polyline2;
        }
        list.add(polyline.m6008c());
        PolylineRendererImpl.m11821a(this.f8153b, this.f8152a.m8447e(), this.f8152a.m8448f(), this.f8154c);
    }

    private static void m11821a(List<Polyline> list, float f, int i, List<com.google.android.m4b.maps.av.aj> list2) {
        list2.clear();
        int a = ConversionUtils.m11631a(i);
        for (Polyline polyline : list) {
            if (polyline.m6020b() > 0) {
                list2.add(new com.google.android.m4b.maps.av.aj(polyline, f, a, null));
            }
        }
    }

    public final synchronized void m11828a(GLState gLState, RepaintCallback repaintCallback) {
    }

    public final void m11827a(GLState gLState) {
    }

    public final void m11830a(boolean z) {
    }

    public final void m11832b() {
    }

    public final void m11833b(int i) {
    }

    public final synchronized void m11826a(Camera camera, GLState gLState) {
        if (this.f8152a != null && this.f8152a.m8450h()) {
            for (com.google.android.m4b.maps.av.aj a : this.f8154c) {
                a.m6806a(camera, gLState);
            }
        }
    }

    public final synchronized void m11829a(GLState gLState, Camera camera, ad adVar) {
        if (this.f8152a != null && this.f8152a.m8450h()) {
            for (com.google.android.m4b.maps.av.aj a : this.f8154c) {
                a.m6805a(gLState, camera, adVar);
            }
        }
    }

    public final boolean m11831a(float f, float f2, Point point, Camera camera) {
        return false;
    }

    public final void m11825a(long j) {
    }

    public final synchronized boolean m11834c() {
        boolean z;
        if (this.f8152a == null || !this.f8152a.m8450h()) {
            z = true;
        } else {
            for (com.google.android.m4b.maps.av.aj e : this.f8154c) {
                if (!e.m6681e()) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    public final String m11836e() {
        return this.f8152a.m8444a();
    }

    public final float m11837f() {
        return this.f8155d;
    }

    public final void m11835d() {
    }
}
