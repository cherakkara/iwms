package com.google.android.m4b.maps.aq;

import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;

/* renamed from: com.google.android.m4b.maps.aq.a */
public final class CompositeTileCallback implements TileCallback {
    private final TileCallback f3847a;
    private final TileCallback f3848b;

    public CompositeTileCallback(TileCallback tileCallback, TileCallback tileCallback2) {
        this.f3847a = tileCallback;
        this.f3848b = tileCallback2;
    }

    public final void m6322a(ac acVar, int i, aa aaVar) {
        if (this.f3847a != null) {
            this.f3847a.m5267a(acVar, i, aaVar);
        }
        if (this.f3848b != null) {
            this.f3848b.m5267a(acVar, i, aaVar);
        }
    }
}
