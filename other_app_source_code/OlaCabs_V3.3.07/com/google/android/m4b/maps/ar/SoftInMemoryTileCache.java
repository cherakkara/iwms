package com.google.android.m4b.maps.ar;

import com.google.android.m4b.maps.an.MockTile;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.au.LRUCache;
import java.lang.ref.SoftReference;

/* renamed from: com.google.android.m4b.maps.ar.k */
public final class SoftInMemoryTileCache implements TileCache {
    private static aa f3995b;
    private LRUCache<ac, SoftInMemoryTileCache> f3996a;

    /* renamed from: com.google.android.m4b.maps.ar.k.a */
    static class SoftInMemoryTileCache {
        final SoftReference<aa> f3993a;
        final aa f3994b;

        public SoftInMemoryTileCache(aa aaVar) {
            aa aaVar2;
            SoftReference softReference = null;
            if (aq.m5625a(aaVar)) {
                aaVar2 = aaVar;
            } else {
                aaVar2 = null;
            }
            this.f3994b = aaVar2;
            if (this.f3994b == null) {
                softReference = new SoftReference(aaVar);
            }
            this.f3993a = softReference;
        }
    }

    static {
        f3995b = new MockTile();
    }

    public SoftInMemoryTileCache(int i) {
        this.f3996a = new LRUCache(i);
    }

    public final void m6511a(ac acVar, aa aaVar) {
        synchronized (this.f3996a) {
            this.f3996a.m6239c(acVar, new SoftInMemoryTileCache(aaVar));
        }
    }

    public final void a_(ac acVar) {
        m6511a(acVar, f3995b);
    }

    public final boolean m6514b(ac acVar) {
        return m6515c(acVar) != null;
    }

    public final aa m6515c(ac acVar) {
        synchronized (this.f3996a) {
            SoftInMemoryTileCache softInMemoryTileCache = (SoftInMemoryTileCache) this.f3996a.m6235b((Object) acVar);
            if (softInMemoryTileCache != null) {
                aa aaVar = softInMemoryTileCache.f3994b != null ? softInMemoryTileCache.f3994b : softInMemoryTileCache.f3993a == null ? null : (aa) softInMemoryTileCache.f3993a.get();
                if (aaVar == null) {
                    this.f3996a.m6238c(acVar);
                }
                return aaVar;
            }
            return null;
        }
    }

    public final boolean m6512a() {
        synchronized (this.f3996a) {
            this.f3996a.m6231a();
        }
        return true;
    }

    public final boolean m6513a(aa aaVar) {
        return aaVar == f3995b;
    }
}
