package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.ah.OverlayManager;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.PolygonOptions;
import com.google.android.m4b.maps.model.internal.IPolygonDelegate.IPolygonDelegate;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PolygonImpl */
public final class ak extends IPolygonDelegate implements OverlayManager, aj {
    private static AtomicInteger f5615a;
    private static final PolygonOptions f5616b;
    private final String f5617c;
    private final ah f5618d;
    private final be f5619e;
    private PolyModel f5620f;
    private ba f5621g;
    private List<LatLng> f5622h;
    private List<LatLng> f5623i;
    private List<List<LatLng>> f5624j;
    private final List<List<LatLng>> f5625k;
    private int f5626l;
    private int f5627m;
    private float f5628n;
    private float f5629o;
    private boolean f5630p;
    private boolean f5631q;
    private boolean f5632r;

    static {
        f5615a = new AtomicInteger(0);
        f5616b = new PolygonOptions();
    }

    ak(PolygonOptions polygonOptions, ah ahVar, be beVar, ba baVar) {
        this.f5625k = ar.m2515a();
        this.f5618d = (ah) Preconditions.m1817a((Object) ahVar);
        this.f5619e = (be) Preconditions.m1817a((Object) beVar);
        this.f5621g = baVar;
        this.f5617c = String.format("pg%d", new Object[]{Integer.valueOf(f5615a.getAndIncrement())});
        Preconditions.m1823a(polygonOptions.m10779e() >= 0.0f, (Object) "line width is negative");
        this.f5628n = polygonOptions.m10779e();
        this.f5626l = polygonOptions.m10780f();
        this.f5627m = polygonOptions.m10781g();
        this.f5629o = polygonOptions.m10782h();
        this.f5631q = polygonOptions.m10783i();
        this.f5630p = polygonOptions.m10784j();
        this.f5622h = ar.m2516a(polygonOptions.m10777c());
        m8474c(this.f5622h);
        this.f5624j = ar.m2515a();
        for (Iterable a : polygonOptions.m10778d()) {
            List a2 = ar.m2516a(a);
            m8474c(a2);
            this.f5624j.add(a2);
        }
        m8475t();
        if (polygonOptions.m10780f() != f5616b.m10780f()) {
            this.f5619e.m8835b(UsageLog.POLYGON_STROKE_COLOR);
        }
        if (polygonOptions.m10779e() != f5616b.m10779e()) {
            this.f5619e.m8835b(UsageLog.POLYGON_WIDTH);
        }
        if (polygonOptions.m10784j() != f5616b.m10784j()) {
            this.f5619e.m8835b(UsageLog.POLYGON_GEODESIC);
        }
        if (polygonOptions.m10783i() != f5616b.m10783i()) {
            this.f5619e.m8835b(UsageLog.POLYGON_VISIBILITY);
        }
        if (polygonOptions.m10782h() != f5616b.m10782h()) {
            this.f5619e.m8835b(UsageLog.POLYGON_Z_INDEX);
        }
        if (polygonOptions.m10778d() != f5616b.m10778d()) {
            this.f5619e.m8835b(UsageLog.POLYGON_HOLES);
        }
    }

    final void m8480a(PolyModel polyModel) {
        this.f5620f = polyModel;
    }

    private void m8473c(int i) {
        synchronized (this) {
            if (this.f5632r) {
                return;
            }
            this.f5620f.m8443a(i);
        }
    }

    private static void m8474c(List<LatLng> list) {
        if (!((LatLng) list.get(0)).equals(list.get(list.size() - 1))) {
            list.add(list.get(0));
        }
    }

    private void m8475t() {
        this.f5623i = this.f5630p ? bw.m9065a(this.f5622h) : this.f5622h;
        this.f5625k.clear();
        for (Object obj : this.f5624j) {
            Object obj2;
            List list = this.f5625k;
            if (this.f5630p) {
                obj2 = bw.m9065a((List) obj2);
            }
            list.add(obj2);
        }
    }

    public final void m8496j() {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_REMOVE);
        m8489c();
        this.f5618d.m8435b(this);
    }

    public final void m8489c() {
        synchronized (this) {
            if (this.f5632r) {
                return;
            }
            this.f5632r = true;
            this.f5620f.m8442a();
        }
    }

    public final String m8477a() {
        return this.f5617c;
    }

    public final void m8481a(List<LatLng> list) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_SET_POINTS);
        synchronized (this) {
            this.f5622h = ar.m2516a((Iterable) list);
            m8474c(this.f5622h);
            m8475t();
        }
        m8473c(1);
    }

    public final List<LatLng> m8497k() {
        List a;
        this.f5621g.m8774a();
        synchronized (this) {
            a = ar.m2516a(this.f5622h);
        }
        return a;
    }

    public final synchronized List<LatLng> m8484b() {
        return this.f5623i;
    }

    public final void m8487b(List list) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_HOLES);
        synchronized (this) {
            this.f5624j.clear();
            for (Iterable a : list) {
                List a2 = ar.m2516a(a);
                m8474c(a2);
                this.f5624j.add(a2);
            }
            m8475t();
        }
        m8473c(2);
    }

    public final List m8498l() {
        this.f5621g.m8774a();
        List a = ar.m2515a();
        synchronized (this) {
            for (Iterable a2 : this.f5624j) {
                a.add(ar.m2516a(a2));
            }
        }
        return a;
    }

    public final synchronized List<List<LatLng>> m8490d() {
        return this.f5625k;
    }

    public final void m8479a(int i) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_STROKE_COLOR);
        synchronized (this) {
            this.f5626l = i;
        }
        m8473c(8);
    }

    public final int m8499m() {
        this.f5621g.m8774a();
        return m8492f();
    }

    public final synchronized int m8492f() {
        return this.f5626l;
    }

    public final void m8486b(int i) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_FILL_COLOR);
        synchronized (this) {
            this.f5627m = i;
        }
        m8473c(16);
    }

    public final int m8500n() {
        this.f5621g.m8774a();
        return m8493g();
    }

    public final synchronized int m8493g() {
        return this.f5627m;
    }

    public final void m8478a(float f) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_WIDTH);
        Preconditions.m1823a(f >= 0.0f, (Object) "line width is negative");
        synchronized (this) {
            this.f5628n = f;
        }
        m8473c(4);
    }

    public final float m8501o() {
        this.f5621g.m8774a();
        return m8491e();
    }

    public final synchronized float m8491e() {
        return this.f5628n;
    }

    public final void m8485b(float f) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_Z_INDEX);
        synchronized (this) {
            this.f5629o = f;
        }
        m8473c(64);
    }

    public final float m8502p() {
        this.f5621g.m8774a();
        return m8495i();
    }

    public final synchronized float m8495i() {
        return this.f5629o;
    }

    public final void m8482a(boolean z) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_GEODESIC);
        synchronized (this) {
            this.f5630p = z;
            m8475t();
        }
        m8473c(3);
    }

    public final boolean m8503q() {
        this.f5621g.m8774a();
        return m8476u();
    }

    private synchronized boolean m8476u() {
        return this.f5630p;
    }

    public final void m8488b(boolean z) {
        this.f5621g.m8774a();
        this.f5619e.m8835b(UsageLog.POLYGON_VISIBILITY);
        synchronized (this) {
            this.f5631q = z;
        }
        m8473c(32);
    }

    public final boolean m8504r() {
        this.f5621g.m8774a();
        return m8494h();
    }

    public final synchronized boolean m8494h() {
        return this.f5631q;
    }

    public final boolean m8483a(com.google.android.m4b.maps.model.internal.IPolygonDelegate iPolygonDelegate) {
        return equals(iPolygonDelegate);
    }

    public final int m8505s() {
        return hashCode();
    }
}
