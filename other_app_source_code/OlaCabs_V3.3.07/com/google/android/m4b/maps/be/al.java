package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.ah.OverlayManager;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.PolylineOptions;
import com.google.android.m4b.maps.model.internal.IPolylineDelegate.IPolylineDelegate;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PolylineImpl */
public final class al extends IPolylineDelegate implements OverlayManager, aj {
    private static AtomicInteger f5633a;
    private static final PolylineOptions f5634b;
    private final String f5635c;
    private final ah f5636d;
    private final be f5637e;
    private PolyModel f5638f;
    private ba f5639g;
    private List<LatLng> f5640h;
    private List<LatLng> f5641i;
    private int f5642j;
    private float f5643k;
    private boolean f5644l;
    private float f5645m;
    private boolean f5646n;
    private boolean f5647o;

    static {
        f5633a = new AtomicInteger(0);
        f5634b = new PolylineOptions();
    }

    al(PolylineOptions polylineOptions, ah ahVar, be beVar, ba baVar) {
        boolean z;
        this.f5636d = (ah) Preconditions.m1817a((Object) ahVar);
        this.f5637e = (be) Preconditions.m1817a((Object) beVar);
        this.f5639g = baVar;
        this.f5635c = String.format("pl%d", new Object[]{Integer.valueOf(f5633a.getAndIncrement())});
        if (polylineOptions.m10787c() >= 0.0f) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.m1823a(z, (Object) "line width is negative");
        this.f5643k = polylineOptions.m10787c();
        this.f5642j = polylineOptions.m10788d();
        this.f5645m = polylineOptions.m10789e();
        this.f5647o = polylineOptions.m10790f();
        this.f5646n = polylineOptions.m10791g();
        this.f5640h = ar.m2516a(polylineOptions.m10786b());
        m8524r();
        if (polylineOptions.m10788d() != f5634b.m10788d()) {
            this.f5637e.m8835b(UsageLog.POLYLINE_COLOR);
        }
        if (polylineOptions.m10787c() != f5634b.m10787c()) {
            this.f5637e.m8835b(UsageLog.POLYLINE_WIDTH);
        }
        if (polylineOptions.m10791g() != f5634b.m10791g()) {
            this.f5637e.m8835b(UsageLog.POLYLINE_GEODESIC);
        }
        if (polylineOptions.m10790f() != f5634b.m10790f()) {
            this.f5637e.m8835b(UsageLog.POLYLINE_VISIBILITY);
        }
        if (polylineOptions.m10789e() != f5634b.m10789e()) {
            this.f5637e.m8835b(UsageLog.POLYLINE_Z_INDEX);
        }
    }

    final void m8528a(PolyModel polyModel) {
        this.f5638f = polyModel;
    }

    private void m8523b(int i) {
        synchronized (this) {
            if (this.f5644l) {
                return;
            }
            this.f5638f.m8443a(i);
        }
    }

    public final void m8542j() {
        this.f5639g.m8774a();
        this.f5637e.m8835b(UsageLog.POLYLINE_REMOVE);
        m8535c();
        this.f5636d.m8435b(this);
    }

    public final void m8535c() {
        synchronized (this) {
            if (this.f5644l) {
                return;
            }
            this.f5644l = true;
            this.f5638f.m8442a();
        }
    }

    public final String m8525a() {
        return this.f5635c;
    }

    public final void m8529a(List<LatLng> list) {
        this.f5639g.m8774a();
        this.f5637e.m8835b(UsageLog.POLYLINE_SET_POINTS);
        this.f5640h = ar.m2516a((Iterable) list);
        m8524r();
        m8523b(1);
    }

    public final List<LatLng> m8543k() {
        this.f5639g.m8774a();
        return ar.m2516a(this.f5640h);
    }

    private synchronized void m8524r() {
        this.f5641i = this.f5646n ? bw.m9065a(this.f5640h) : this.f5640h;
    }

    public final synchronized List<LatLng> m8532b() {
        return this.f5641i;
    }

    public final void m8527a(int i) {
        this.f5639g.m8774a();
        this.f5637e.m8835b(UsageLog.POLYLINE_COLOR);
        synchronized (this) {
            this.f5642j = i;
        }
        m8523b(8);
    }

    public final int m8544l() {
        this.f5639g.m8774a();
        return m8538f();
    }

    public final synchronized int m8538f() {
        return this.f5642j;
    }

    public final void m8526a(float f) {
        this.f5639g.m8774a();
        this.f5637e.m8835b(UsageLog.POLYLINE_WIDTH);
        Preconditions.m1823a(f >= 0.0f, (Object) "line width is negative");
        synchronized (this) {
            this.f5643k = f;
        }
        m8523b(4);
    }

    public final float m8545m() {
        this.f5639g.m8774a();
        return m8537e();
    }

    public final synchronized float m8537e() {
        return this.f5643k;
    }

    public final void m8533b(float f) {
        this.f5639g.m8774a();
        this.f5637e.m8835b(UsageLog.POLYLINE_Z_INDEX);
        synchronized (this) {
            this.f5645m = f;
        }
        m8523b(64);
    }

    public final float m8546n() {
        this.f5639g.m8774a();
        return m8541i();
    }

    public final synchronized float m8541i() {
        return this.f5645m;
    }

    public final void m8530a(boolean z) {
        this.f5639g.m8774a();
        this.f5637e.m8835b(UsageLog.POLYLINE_GEODESIC);
        this.f5646n = z;
        m8524r();
    }

    public final boolean m8547o() {
        this.f5639g.m8774a();
        return this.f5646n;
    }

    public final void m8534b(boolean z) {
        this.f5639g.m8774a();
        this.f5637e.m8835b(UsageLog.POLYLINE_VISIBILITY);
        synchronized (this) {
            this.f5647o = z;
        }
        m8523b(32);
    }

    public final boolean m8548p() {
        this.f5639g.m8774a();
        return m8540h();
    }

    public final synchronized boolean m8540h() {
        return this.f5647o;
    }

    public final boolean m8531a(com.google.android.m4b.maps.model.internal.IPolylineDelegate iPolylineDelegate) {
        return equals(iPolylineDelegate);
    }

    public final int m8549q() {
        return hashCode();
    }

    public final List<List<LatLng>> m8536d() {
        return Collections.emptyList();
    }

    public final int m8539g() {
        return 0;
    }
}
