package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.ah.OverlayManager;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.TileOverlayOptions;
import com.google.android.m4b.maps.model.ae;
import com.google.android.m4b.maps.model.internal.ITileOverlayDelegate.ITileOverlayDelegate;
import com.google.p025a.p026a.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: TileOverlayImpl */
public final class bd extends ITileOverlayDelegate implements OverlayManager {
    private static final TileOverlayOptions f5731a;
    private static AtomicInteger f5732b;
    private final ah f5733c;
    private final be f5734d;
    private final String f5735e;
    private TileOverlayImpl f5736f;
    private final ae f5737g;
    private ba f5738h;
    private boolean f5739i;
    private float f5740j;
    private boolean f5741k;
    private boolean f5742l;

    /* renamed from: com.google.android.m4b.maps.be.bd.a */
    public interface TileOverlayImpl {
        void m8799a();

        void m8800a(int i);

        void m8801g();
    }

    static {
        f5731a = new TileOverlayOptions();
        f5732b = new AtomicInteger(0);
    }

    bd(TileOverlayOptions tileOverlayOptions, ah ahVar, be beVar, ba baVar) {
        this.f5735e = String.format("to%d", new Object[]{Integer.valueOf(f5732b.getAndIncrement())});
        this.f5733c = ahVar;
        this.f5734d = beVar;
        this.f5738h = baVar;
        this.f5737g = tileOverlayOptions.m10805c();
        this.f5739i = tileOverlayOptions.m10807e();
        this.f5740j = tileOverlayOptions.m10806d();
        this.f5741k = tileOverlayOptions.m10808f();
        if (tileOverlayOptions.m10807e() != f5731a.m10807e()) {
            this.f5734d.m8835b(UsageLog.TILE_OVERLAY_VISIBILITY);
        }
        if (tileOverlayOptions.m10806d() != f5731a.m10806d()) {
            this.f5734d.m8835b(UsageLog.TILE_OVERLAY_Z_INDEX);
        }
        if (tileOverlayOptions.m10808f() != f5731a.m10808f()) {
            this.f5734d.m8835b(UsageLog.TILE_OVERLAY_FADE);
        }
    }

    final void m8817a(TileOverlayImpl tileOverlayImpl) {
        this.f5736f = tileOverlayImpl;
    }

    private void m8814a(int i) {
        synchronized (this) {
            if (this.f5742l) {
                return;
            }
            if (this.f5736f != null) {
                this.f5736f.m8800a(i);
            }
        }
    }

    public final String m8815a() {
        return this.f5735e;
    }

    public final void m8820b() {
        this.f5738h.m8774a();
        this.f5734d.m8835b(UsageLog.POLYLINE_REMOVE);
        m8822c();
        this.f5733c.m8435b(this);
    }

    public final void m8822c() {
        synchronized (this) {
            if (this.f5742l) {
                return;
            }
            this.f5742l = true;
            if (this.f5736f != null) {
                this.f5736f.m8799a();
            }
        }
    }

    public final ae m8823d() {
        return this.f5737g;
    }

    public final void m8824e() {
        this.f5738h.m8774a();
        this.f5734d.m8835b(UsageLog.TILE_OVERLAY_CLEAR_CACHE);
        this.f5736f.m8801g();
    }

    public final void m8816a(float f) {
        this.f5738h.m8774a();
        this.f5734d.m8835b(UsageLog.TILE_OVERLAY_Z_INDEX);
        synchronized (this) {
            this.f5740j = f;
        }
        m8814a(2);
    }

    public final float m8825f() {
        this.f5738h.m8774a();
        return m8826g();
    }

    public final synchronized float m8826g() {
        return this.f5740j;
    }

    public final void m8818a(boolean z) {
        this.f5738h.m8774a();
        this.f5734d.m8835b(UsageLog.TILE_OVERLAY_VISIBILITY);
        synchronized (this) {
            this.f5739i = z;
        }
        m8814a(4);
    }

    public final boolean m8827h() {
        this.f5738h.m8774a();
        return m8828i();
    }

    public final synchronized boolean m8828i() {
        return this.f5739i;
    }

    public final void m8821b(boolean z) {
        this.f5738h.m8774a();
        this.f5734d.m8835b(UsageLog.TILE_OVERLAY_FADE);
        synchronized (this) {
            this.f5741k = z;
        }
        m8814a(1);
    }

    public final boolean m8829j() {
        this.f5738h.m8774a();
        return m8830k();
    }

    public final synchronized boolean m8830k() {
        return this.f5741k;
    }

    public final String toString() {
        return Objects.m1809a((Object) this).m1806a("id", this.f5735e).toString();
    }

    public final boolean m8819a(com.google.android.m4b.maps.model.internal.ITileOverlayDelegate iTileOverlayDelegate) {
        return equals(iTileOverlayDelegate);
    }

    public final int m8831l() {
        return hashCode();
    }
}
