package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.aq.TileCallback;
import com.google.android.m4b.maps.ar.DiskTileCacheListener;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import java.io.File;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.ap.m */
public final class VectorModifierTileStore extends VectorTileStore {
    public static final VectorModifierTileStore f3843e;
    private boolean f3844g;
    private volatile VectorModifierTileStore f3845h;

    /* renamed from: com.google.android.m4b.maps.ap.m.a */
    public interface VectorModifierTileStore {
        VectorModifierTileStore() {
        }
    }

    static {
        f3843e = new VectorModifierTileStore();
    }

    public VectorModifierTileStore(DataRequestDispatcherInterface dataRequestDispatcherInterface, ai aiVar, int i, float f, Locale locale, boolean z, File file, VectorModifierTileStore vectorModifierTileStore, DiskTileCacheListener diskTileCacheListener) {
        super(dataRequestDispatcherInterface, aiVar, i, f, locale, z, file, null);
        this.f3844g = false;
        this.f3845h = vectorModifierTileStore;
    }

    public final void m6318a(ac acVar, TileCallback tileCallback) {
        m6317a(acVar, null, tileCallback);
    }

    public final void m6317a(ac acVar, aq aqVar, TileCallback tileCallback) {
        if (this.f3845h != null) {
            VectorModifierTileStore vectorModifierTileStore = this.f3845h;
        }
        if (1 != 0) {
            super.m6207a(acVar, tileCallback);
        } else {
            tileCallback.m5267a(acVar, 2, null);
        }
    }
}
