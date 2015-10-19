package com.google.android.m4b.maps.p061y;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.br;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.av.ag;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.be.MapsEngineFeatureImpl;
import com.google.android.m4b.maps.be.MapsEngineInfocardManager;
import com.google.android.m4b.maps.be.aa;
import com.google.android.m4b.maps.be.aa.MapsEngineLayerImpl;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import java.util.Collection;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.y.k */
public final class MapsEngineLayerRendererImpl implements MapsEngineLayerImpl, Overlay {
    private float f8122a;
    private final MapsEngineInfocardManager f8123b;
    private final OverlayRendererManagerImpl f8124c;
    private final ag f8125d;
    private final aa f8126e;

    MapsEngineLayerRendererImpl(aa aaVar, MapsEngineInfocardManager mapsEngineInfocardManager, ag agVar, OverlayRendererManagerImpl overlayRendererManagerImpl) {
        this.f8126e = aaVar;
        this.f8123b = mapsEngineInfocardManager;
        this.f8124c = overlayRendererManagerImpl;
        this.f8125d = agVar;
        m11760a(-1);
    }

    public final void m11760a(int i) {
        if ((i & 2) != 0) {
            synchronized (this.f8124c) {
                this.f8122a = this.f8126e.m8290g();
                this.f8124c.m11801c();
            }
        }
    }

    public final void m11758a() {
        synchronized (this.f8124c) {
            this.f8124c.m11796a((Overlay) this);
        }
        this.f8124c.m11800b();
    }

    public final void m11764a(GLState gLState, RepaintCallback repaintCallback) {
        this.f8125d.m6738a(gLState, repaintCallback);
    }

    public final void m11763a(GLState gLState) {
        this.f8125d.m6737a(gLState);
    }

    public final void m11767a(boolean z) {
        this.f8125d.m6741a(z);
    }

    public final void m11769b() {
        this.f8125d.h_();
    }

    public final void m11770b(int i) {
        this.f8125d.m6730a(i);
    }

    public final void m11762a(Camera camera, GLState gLState) {
        synchronized (this) {
            if (this.f8126e.m8292i()) {
                this.f8125d.m6743a(camera, gLState);
                return;
            }
        }
    }

    public final void m11765a(GLState gLState, Camera camera, ad adVar) {
        synchronized (this) {
            if (this.f8126e.m8292i()) {
                this.f8125d.m6739a(gLState, camera, adVar);
                return;
            }
        }
    }

    public final boolean m11768a(float f, float f2, Point point, Camera camera) {
        synchronized (this) {
            if (this.f8126e.m8292i()) {
                List a = this.f8125d.m6752a(point, camera, this);
                if (a.isEmpty()) {
                    return false;
                }
                this.f8124c.m11788a(f, f2, point, camera, this, a);
                return true;
            }
            return false;
        }
    }

    public final void m11759a(float f, float f2, Point point, Camera camera, List<MapsEngineFeatureImpl> list, List<MapsEngineFeatureImpl> list2) {
        Collection a = this.f8125d.m6752a(point, camera, this);
        list.addAll(a);
        if (this.f8126e.m8294k()) {
            list2.addAll(a);
        }
    }

    public final void m11761a(long j) {
        this.f8125d.m6731a(j);
    }

    public final boolean m11771c() {
        return this.f8125d.m6746e();
    }

    public final void m11766a(ProtoBuf protoBuf) {
        this.f8125d.m6742a(new br(protoBuf));
        this.f8124c.m11800b();
    }

    public final String m11773e() {
        return this.f8126e.m8288e();
    }

    public final synchronized void m11772d() {
        MapsEngineFeatureImpl c = this.f8123b.m9567c();
        if (c != null && c.m9548a() == this.f8126e) {
            this.f8123b.m9566b();
        }
        this.f8125d.m6737a(null);
    }

    public final float m11774f() {
        return this.f8122a;
    }

    public final aa m11775g() {
        return this.f8126e;
    }
}
