package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.ah.OverlayManager;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.CircleOptions;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.internal.ICircleDelegate.ICircleDelegate;
import com.google.p025a.p026a.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: CircleImpl */
public final class br extends ICircleDelegate implements OverlayManager, aj {
    private static final AtomicInteger f5868a;
    private static final CircleOptions f5869b;
    private final String f5870c;
    private final ah f5871d;
    private final be f5872e;
    private PolyModel f5873f;
    private LatLng f5874g;
    private double f5875h;
    private List<LatLng> f5876i;
    private int f5877j;
    private int f5878k;
    private float f5879l;
    private float f5880m;
    private boolean f5881n;
    private boolean f5882o;
    private ba f5883p;

    static {
        f5868a = new AtomicInteger(0);
        f5869b = new CircleOptions();
    }

    br(CircleOptions circleOptions, ah ahVar, be beVar, ba baVar) {
        boolean z;
        boolean z2 = true;
        this.f5871d = (ah) Preconditions.m1817a((Object) ahVar);
        this.f5872e = (be) Preconditions.m1817a((Object) beVar);
        this.f5883p = baVar;
        this.f5870c = String.format("ci%d", new Object[]{Integer.valueOf(f5868a.getAndIncrement())});
        if (circleOptions.m10716d() >= 0.0f) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.m1823a(z, (Object) "line width is negative");
        Preconditions.m1817a(circleOptions.m10714b());
        if (circleOptions.m10715c() < 0.0d) {
            z2 = false;
        }
        Preconditions.m1823a(z2, (Object) "radius is negative");
        this.f5874g = circleOptions.m10714b();
        this.f5875h = circleOptions.m10715c();
        this.f5879l = circleOptions.m10716d();
        this.f5877j = circleOptions.m10717e();
        this.f5878k = circleOptions.m10718f();
        this.f5880m = circleOptions.m10719g();
        this.f5881n = circleOptions.m10720h();
        this.f5876i = bw.m9064a(this.f5874g, this.f5875h, 100);
        if (circleOptions.m10718f() != f5869b.m10718f()) {
            this.f5872e.m8835b(UsageLog.CIRCLE_FILL_COLOR);
        }
        if (circleOptions.m10717e() != f5869b.m10717e()) {
            this.f5872e.m8835b(UsageLog.CIRCLE_STROKE_COLOR);
        }
        if (circleOptions.m10716d() != f5869b.m10716d()) {
            this.f5872e.m8835b(UsageLog.CIRCLE_WIDTH);
        }
        if (circleOptions.m10720h() != f5869b.m10720h()) {
            this.f5872e.m8835b(UsageLog.CIRCLE_VISIBILITY);
        }
        if (circleOptions.m10719g() != f5869b.m10719g()) {
            this.f5872e.m8835b(UsageLog.CIRCLE_Z_INDEX);
        }
    }

    final void m9008a(PolyModel polyModel) {
        this.f5873f = polyModel;
    }

    private void m9003c(int i) {
        synchronized (this) {
            if (this.f5882o) {
                return;
            }
            if (this.f5873f != null) {
                this.f5873f.m8443a(i);
            }
        }
    }

    public final void m9022j() {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_REMOVE);
        m9015c();
        this.f5871d.m8435b(this);
    }

    public final void m9015c() {
        synchronized (this) {
            if (this.f5882o) {
                return;
            }
            this.f5882o = true;
            if (this.f5873f != null) {
                this.f5873f.m8442a();
            }
        }
    }

    public final String m9004a() {
        return this.f5870c;
    }

    public final void m9009a(LatLng latLng) {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_SET_CENTER);
        synchronized (this) {
            this.f5874g = latLng;
        }
        this.f5876i = bw.m9064a(latLng, this.f5875h, 100);
        m9003c(1);
    }

    public final LatLng m9023k() {
        this.f5883p.m8774a();
        return this.f5874g;
    }

    public final void m9005a(double d) {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_SET_RADIUS);
        synchronized (this) {
            this.f5875h = d;
        }
        this.f5876i = bw.m9064a(this.f5874g, d, 100);
        m9003c(1);
    }

    public final double m9024l() {
        this.f5883p.m8774a();
        return this.f5875h;
    }

    public final synchronized List<LatLng> m9012b() {
        return this.f5876i;
    }

    public final synchronized List<List<LatLng>> m9016d() {
        return Collections.emptyList();
    }

    public final void m9007a(int i) {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_STROKE_COLOR);
        synchronized (this) {
            this.f5877j = i;
        }
        m9003c(8);
    }

    public final int m9025m() {
        this.f5883p.m8774a();
        return m9018f();
    }

    public final synchronized int m9018f() {
        return this.f5877j;
    }

    public final void m9014b(int i) {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_FILL_COLOR);
        synchronized (this) {
            this.f5878k = i;
        }
        m9003c(16);
    }

    public final int m9026n() {
        this.f5883p.m8774a();
        return m9019g();
    }

    public final synchronized int m9019g() {
        return this.f5878k;
    }

    public final void m9006a(float f) {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_WIDTH);
        Preconditions.m1823a(f >= 0.0f, (Object) "line width is negative");
        synchronized (this) {
            this.f5879l = f;
        }
        m9003c(4);
    }

    public final float m9027o() {
        this.f5883p.m8774a();
        return m9017e();
    }

    public final synchronized float m9017e() {
        return this.f5879l;
    }

    public final void m9013b(float f) {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_Z_INDEX);
        synchronized (this) {
            this.f5880m = f;
        }
        m9003c(64);
    }

    public final float m9028p() {
        this.f5883p.m8774a();
        return m9021i();
    }

    public final synchronized float m9021i() {
        return this.f5880m;
    }

    public final void m9010a(boolean z) {
        this.f5883p.m8774a();
        this.f5872e.m8835b(UsageLog.CIRCLE_VISIBILITY);
        synchronized (this) {
            this.f5881n = z;
        }
        m9003c(32);
    }

    public final boolean m9029q() {
        this.f5883p.m8774a();
        return m9020h();
    }

    public final synchronized boolean m9020h() {
        return this.f5881n;
    }

    public final boolean m9011a(com.google.android.m4b.maps.model.internal.ICircleDelegate iCircleDelegate) {
        return equals(iCircleDelegate);
    }

    public final int m9030r() {
        return hashCode();
    }
}
