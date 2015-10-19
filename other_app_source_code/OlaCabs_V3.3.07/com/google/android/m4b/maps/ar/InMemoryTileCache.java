package com.google.android.m4b.maps.ar;

import com.google.android.m4b.maps.an.MockTile;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.au.LRUCache;
import com.newrelic.agent.android.analytics.AnalyticAttribute;

/* renamed from: com.google.android.m4b.maps.ar.g */
public final class InMemoryTileCache implements TileCache {
    private static aa f3950b;
    private LRUCache<ac, aa> f3951a;

    static {
        f3950b = new MockTile();
    }

    public InMemoryTileCache(int i) {
        this.f3951a = new LRUCache(AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
    }

    public final void m6461a(ac acVar, aa aaVar) {
        synchronized (this.f3951a) {
            this.f3951a.m6239c(acVar, aaVar);
        }
    }

    public final void a_(ac acVar) {
        m6461a(acVar, f3950b);
    }

    public final boolean m6464b(ac acVar) {
        boolean z;
        synchronized (this.f3951a) {
            z = this.f3951a.m6235b((Object) acVar) != null;
        }
        return z;
    }

    public final aa m6465c(ac acVar) {
        aa aaVar;
        synchronized (this.f3951a) {
            aaVar = (aa) this.f3951a.m6235b((Object) acVar);
        }
        return aaVar;
    }

    public final boolean m6462a() {
        synchronized (this.f3951a) {
            this.f3951a.m6231a();
        }
        return true;
    }

    public final boolean m6463a(aa aaVar) {
        return aaVar == f3950b;
    }
}
