package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.ah.OverlayManager;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.be.bl.BitmapManager;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.GroundOverlayOptions;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.internal.BitmapDescriptorParcelable;
import com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate.IGroundOverlayDelegate;
import com.google.p025a.p026a.Preconditions;
import com.olacabs.customer.p076d.br;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: GroundOverlayImpl */
public final class cb extends IGroundOverlayDelegate implements OverlayManager {
    private static final GroundOverlayOptions f5978a;
    private static AtomicInteger f5979w;
    private final String f5980b;
    private final ah f5981c;
    private final be f5982d;
    private LatLngBounds f5983e;
    private float f5984f;
    private float f5985g;
    private LatLng f5986h;
    private float f5987i;
    private int f5988j;
    private int f5989k;
    private bk f5990l;
    private float f5991m;
    private GroundOverlayImpl f5992n;
    private float f5993o;
    private float f5994p;
    private final bl f5995q;
    private bj f5996r;
    private float f5997s;
    private boolean f5998t;
    private boolean f5999u;
    private ba f6000v;

    /* renamed from: com.google.android.m4b.maps.be.cb.a */
    public interface GroundOverlayImpl {
        void m9292a();

        void m9293a(int i);
    }

    static {
        f5978a = new GroundOverlayOptions();
        f5979w = new AtomicInteger(0);
    }

    public cb(GroundOverlayOptions groundOverlayOptions, ah ahVar, bl blVar, bj bjVar, be beVar, ba baVar) {
        boolean z;
        boolean z2 = false;
        this.f5981c = (ah) Preconditions.m1817a((Object) ahVar);
        this.f5982d = (be) Preconditions.m1817a((Object) beVar);
        this.f5980b = String.format("go%d", new Object[]{Integer.valueOf(f5979w.getAndIncrement())});
        this.f5995q = blVar;
        this.f5996r = bjVar;
        this.f6000v = baVar;
        if (groundOverlayOptions.m10728e() >= 0.0f) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.m1823a(z, (Object) "line width is negative");
        Preconditions.m1823a(groundOverlayOptions.m10726c() != null, (Object) "Options doesn't specify an image");
        this.f5993o = groundOverlayOptions.m10734k();
        this.f5994p = groundOverlayOptions.m10735l();
        this.f5998t = groundOverlayOptions.m10736m();
        this.f5997s = groundOverlayOptions.m10732i();
        this.f5991m = groundOverlayOptions.m10733j();
        this.f5990l = bk.m8889a(groundOverlayOptions.m10726c());
        this.f5995q.m8906a(this.f5990l);
        this.f5988j = this.f5995q.m8907b(this.f5990l).m8903c().getHeight();
        this.f5989k = this.f5995q.m8907b(this.f5990l).m8903c().getWidth();
        if (!(groundOverlayOptions.m10727d() == null && groundOverlayOptions.m10730g() == null)) {
            z2 = true;
        }
        Preconditions.m1823a(z2, (Object) "Options doesn't specify a position");
        if (groundOverlayOptions.m10730g() != null) {
            this.f5983e = groundOverlayOptions.m10730g();
            m9318u();
        } else {
            this.f5986h = groundOverlayOptions.m10727d();
            this.f5984f = groundOverlayOptions.m10728e();
            this.f5985g = groundOverlayOptions.m10729f() != -1.0f ? groundOverlayOptions.m10729f() : (((float) this.f5988j) / ((float) this.f5989k)) * this.f5984f;
            m9319v();
        }
        this.f5987i = groundOverlayOptions.m10731h();
        if (groundOverlayOptions.m10731h() != f5978a.m10731h()) {
            this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_BEARING);
        }
        if (groundOverlayOptions.m10733j() != f5978a.m10733j()) {
            this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_TRANSPARENCY);
        }
        if (groundOverlayOptions.m10736m() != f5978a.m10736m()) {
            this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_VISIBILITY);
        }
        if (groundOverlayOptions.m10732i() != f5978a.m10732i()) {
            this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_Z_INDEX);
        }
    }

    public final void m9323a(GroundOverlayImpl groundOverlayImpl) {
        this.f5992n = groundOverlayImpl;
    }

    private void m9317a(int i) {
        synchronized (this) {
            if (this.f5999u) {
                return;
            }
            if (this.f5992n != null) {
                this.f5992n.m9293a(i);
            }
        }
    }

    public final String m9320a() {
        return this.f5980b;
    }

    public final void m9330b() {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_REMOVE);
        m9332c();
        this.f5981c.m8435b(this);
    }

    public final void m9332c() {
        if (this.f5992n != null) {
            this.f5992n.m9292a();
        }
        synchronized (this) {
            this.f5999u = true;
            this.f5995q.m8908c(this.f5990l);
        }
    }

    public final void m9321a(float f) {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_BEARING);
        synchronized (this) {
            this.f5987i = f;
        }
        m9317a(1);
    }

    public final float m9334d() {
        this.f6000v.m8774a();
        return m9336e();
    }

    public final synchronized float m9336e() {
        return this.f5987i;
    }

    public final void m9326a(LatLngBounds latLngBounds) {
        this.f6000v.m8774a();
        synchronized (this) {
            this.f5983e = latLngBounds;
            m9318u();
        }
        m9317a(16);
    }

    public final synchronized LatLngBounds m9337f() {
        this.f6000v.m8774a();
        return this.f5983e;
    }

    public final LatLngBounds m9338g() {
        return this.f5983e;
    }

    private void m9318u() {
        LatLngBounds latLngBounds = this.f5983e;
        double d = latLngBounds.f7562b.f7554a;
        double d2 = latLngBounds.f7561a.f7554a;
        double d3 = latLngBounds.f7562b.f7555b;
        double d4 = latLngBounds.f7561a.f7555b;
        if (d3 < d4) {
            d3 += 360.0d;
        }
        float f = this.f5993o;
        float f2 = this.f5994p;
        this.f5986h = new LatLng((d * ((double) (br.DEFAULT_BACKOFF_MULT - f2))) + (d2 * ((double) f2)), (d3 * ((double) f)) + (d4 * ((double) (br.DEFAULT_BACKOFF_MULT - f))));
        LatLngBounds latLngBounds2 = this.f5983e;
        this.f5984f = (float) (Math.cos(Math.toRadians(this.f5986h.f7554a)) * (Math.toRadians(bw.m9062a(latLngBounds2.f7562b.f7555b, latLngBounds2.f7561a.f7555b)) * 6371009.0d));
        latLngBounds2 = this.f5983e;
        LatLng latLng = this.f5986h;
        this.f5985g = (float) (Math.toRadians(latLngBounds2.f7562b.f7554a - latLngBounds2.f7561a.f7554a) * 6371009.0d);
    }

    public final void m9325a(LatLng latLng) {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_SET_LOCATION);
        synchronized (this) {
            this.f5986h = latLng;
            m9319v();
        }
        m9317a(16);
    }

    public final LatLng m9339h() {
        this.f6000v.m8774a();
        return this.f5986h;
    }

    public final void m9331b(float f) {
        m9322a(f, -1.0f);
    }

    public final void m9322a(float f, float f2) {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_SET_DIMENSIONS);
        synchronized (this) {
            this.f5984f = f;
            if (f2 == -1.0f) {
                f2 = (((float) this.f5988j) / ((float) this.f5989k)) * f;
            }
            this.f5985g = f2;
            m9319v();
        }
        m9317a(2);
    }

    public final float m9340i() {
        this.f6000v.m8774a();
        return this.f5985g;
    }

    public final float m9341j() {
        this.f6000v.m8774a();
        return this.f5984f;
    }

    private void m9319v() {
        double a = bw.m9063a(this.f5986h, (double) this.f5984f);
        LatLng latLng = this.f5986h;
        double a2 = bw.m9061a((double) this.f5985g);
        double d = this.f5986h.f7554a + (((double) this.f5994p) * a2);
        this.f5983e = new LatLngBounds(new LatLng(this.f5986h.f7554a - (a2 * ((double) (br.DEFAULT_BACKOFF_MULT - this.f5994p))), this.f5986h.f7555b - (a * ((double) this.f5993o))), new LatLng(d, this.f5986h.f7555b + (((double) (br.DEFAULT_BACKOFF_MULT - this.f5993o)) * a)));
    }

    public final synchronized float m9342k() {
        return this.f5993o;
    }

    public final synchronized float m9343l() {
        return this.f5994p;
    }

    public final void m9333c(float f) {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_TRANSPARENCY);
        boolean z = f >= 0.0f && f <= br.DEFAULT_BACKOFF_MULT;
        Preconditions.m1823a(z, (Object) "Transparency must be in the range [0..1]");
        synchronized (this) {
            this.f5991m = f;
        }
        m9317a(64);
    }

    public final float m9344m() {
        this.f6000v.m8774a();
        return m9345n();
    }

    public final synchronized float m9345n() {
        return this.f5991m;
    }

    public final void m9335d(float f) {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_Z_INDEX);
        this.f5997s = f;
        m9317a(8);
    }

    public final float m9346o() {
        this.f6000v.m8774a();
        return this.f5997s;
    }

    public final float m9347p() {
        return this.f5997s;
    }

    public final void m9328a(boolean z) {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_VISIBILITY);
        synchronized (this) {
            this.f5998t = z;
        }
        m9317a(32);
    }

    public final boolean m9348q() {
        this.f6000v.m8774a();
        return m9349r();
    }

    public final synchronized boolean m9349r() {
        return this.f5998t;
    }

    public final void m9324a(IObjectWrapper iObjectWrapper) {
        this.f6000v.m8774a();
        this.f5982d.m8835b(UsageLog.GROUND_OVERLAY_SET_IMAGE);
        synchronized (this) {
            this.f5995q.m8908c(this.f5990l);
            this.f5990l = bk.m8888a(iObjectWrapper);
            this.f5995q.m8906a(this.f5990l);
            this.f5988j = this.f5995q.m8907b(this.f5990l).m8903c().getHeight();
            this.f5989k = this.f5995q.m8907b(this.f5990l).m8903c().getWidth();
        }
        m9317a(4);
    }

    public final void m9327a(BitmapDescriptorParcelable bitmapDescriptorParcelable) {
        this.f6000v.m8774a();
        m9324a(this.f5996r.m8879a(bitmapDescriptorParcelable));
    }

    public final synchronized BitmapManager m9350s() {
        return this.f5995q.m8907b(this.f5990l);
    }

    public final boolean m9329a(com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate iGroundOverlayDelegate) {
        return equals(iGroundOverlayDelegate);
    }

    public final int m9351t() {
        return hashCode();
    }
}
