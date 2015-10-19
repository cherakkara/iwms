package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.ac.MapsEngineParameterProvider;
import com.google.android.m4b.maps.be.ah.OverlayManager;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.MapsEngineLayerInfo;
import com.google.android.m4b.maps.model.MapsEngineLayerOptions;
import com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate.IMapsEngineLayerDelegate;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: MapsEngineLayerImpl */
public final class aa extends IMapsEngineLayerDelegate implements MapsEngineParameterProvider, OverlayManager {
    private static final MapsEngineLayerOptions f5538a;
    private static AtomicInteger f5539b;
    private final ah f5540c;
    private ba f5541d;
    private final be f5542e;
    private final ac f5543f;
    private MapsEngineLayerImpl f5544g;
    private final String f5545h;
    private final MapsEngineLayerInfo f5546i;
    private boolean f5547j;
    private float f5548k;
    private boolean f5549l;
    private boolean f5550m;

    /* renamed from: com.google.android.m4b.maps.be.aa.a */
    public interface MapsEngineLayerImpl extends MapsEngineParameterProvider {
        void m8262a();

        void m8263a(int i);
    }

    static {
        f5538a = new MapsEngineLayerOptions();
        f5539b = new AtomicInteger(0);
    }

    static aa m8276a(MapsEngineLayerOptions mapsEngineLayerOptions, by byVar, ScheduledExecutorService scheduledExecutorService, DataRequestDispatcher dataRequestDispatcher, ah ahVar, be beVar, ba baVar) {
        return new aa(mapsEngineLayerOptions, ahVar, beVar, new ac(mapsEngineLayerOptions.m10754b(), byVar, scheduledExecutorService, dataRequestDispatcher), baVar);
    }

    private aa(MapsEngineLayerOptions mapsEngineLayerOptions, ah ahVar, be beVar, ac acVar, ba baVar) {
        this.f5545h = String.format("me%d", new Object[]{Integer.valueOf(f5539b.getAndIncrement())});
        this.f5540c = ahVar;
        this.f5542e = beVar;
        this.f5541d = baVar;
        this.f5546i = mapsEngineLayerOptions.m10754b();
        this.f5547j = mapsEngineLayerOptions.m10756d();
        this.f5548k = mapsEngineLayerOptions.m10755c();
        this.f5549l = mapsEngineLayerOptions.m10757e();
        this.f5543f = acVar;
        this.f5543f.m8299a(this);
        this.f5543f.m8298a();
        if (mapsEngineLayerOptions.m10756d() != f5538a.m10756d()) {
            this.f5542e.m8835b(UsageLog.MAPS_ENGINE_OVERLAY_VISIBILITY);
        }
        if (mapsEngineLayerOptions.m10755c() != f5538a.m10755c()) {
            this.f5542e.m8835b(UsageLog.MAPS_ENGINE_OVERLAY_Z_INDEX);
        }
        if (mapsEngineLayerOptions.m10757e() != f5538a.m10757e()) {
            this.f5542e.m8835b(UsageLog.MAPS_ENGINE_OVERLAY_DEFAULT_UI);
        }
    }

    private void m8277a(int i) {
        synchronized (this) {
            if (this.f5550m) {
                return;
            }
            if (this.f5544g != null) {
                this.f5544g.m8263a(i);
            }
        }
    }

    public final void m8280a(MapsEngineLayerImpl mapsEngineLayerImpl) {
        this.f5544g = mapsEngineLayerImpl;
    }

    public final ProtoBuf m8278a() {
        return this.f5543f.m8304e();
    }

    public final void m8281a(ProtoBuf protoBuf) {
        this.f5544g.m8261a(protoBuf);
    }

    public final void m8284b() {
        this.f5541d.m8774a();
        this.f5542e.m8835b(UsageLog.POLYLINE_REMOVE);
        m8286c();
        this.f5540c.m8435b(this);
    }

    public final void m8286c() {
        synchronized (this) {
            if (this.f5550m) {
                return;
            }
            this.f5550m = true;
            if (this.f5544g != null) {
                this.f5544g.m8262a();
            }
            this.f5543f.m8301b();
        }
    }

    public final MapsEngineLayerInfo m8287d() {
        this.f5541d.m8774a();
        return this.f5546i;
    }

    public final String m8288e() {
        return this.f5545h;
    }

    public final void m8279a(float f) {
        this.f5541d.m8774a();
        this.f5542e.m8835b(UsageLog.TILE_OVERLAY_Z_INDEX);
        synchronized (this) {
            this.f5548k = f;
        }
        m8277a(2);
    }

    public final float m8289f() {
        this.f5541d.m8774a();
        return m8290g();
    }

    public final synchronized float m8290g() {
        return this.f5548k;
    }

    public final void m8282a(boolean z) {
        this.f5541d.m8774a();
        this.f5542e.m8835b(UsageLog.TILE_OVERLAY_VISIBILITY);
        synchronized (this) {
            this.f5547j = z;
        }
        m8277a(4);
    }

    public final boolean m8291h() {
        this.f5541d.m8774a();
        return m8292i();
    }

    public final synchronized boolean m8292i() {
        return this.f5547j;
    }

    public final void m8285b(boolean z) {
        this.f5541d.m8774a();
        this.f5542e.m8835b(UsageLog.MAPS_ENGINE_OVERLAY_DEFAULT_UI);
        this.f5549l = z;
        m8277a(1);
    }

    public final boolean m8293j() {
        this.f5541d.m8774a();
        return m8294k();
    }

    public final synchronized boolean m8294k() {
        return this.f5549l;
    }

    public final boolean m8283a(com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate iMapsEngineLayerDelegate) {
        return equals(iMapsEngineLayerDelegate);
    }

    public final int m8295l() {
        return hashCode();
    }
}
