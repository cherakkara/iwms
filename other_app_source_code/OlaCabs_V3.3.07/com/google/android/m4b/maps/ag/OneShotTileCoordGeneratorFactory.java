package com.google.android.m4b.maps.ag;

import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;

/* renamed from: com.google.android.m4b.maps.ag.d */
public final class OneShotTileCoordGeneratorFactory extends TileCoordGeneratorFactory {
    private TileCoordGenerator f3083a;
    private SingleZoomTileCoordGenerator f3084b;

    public final synchronized TileCoordGenerator m4904a(ai aiVar, int i, boolean z, ah ahVar) {
        if (this.f3083a == null) {
            this.f3083a = super.m4902a(aiVar, i, z, ahVar);
        }
        return this.f3083a;
    }

    public final synchronized SingleZoomTileCoordGenerator m4903a(ai aiVar, boolean z, ah ahVar) {
        if (this.f3084b == null) {
            this.f3084b = super.m4901a(aiVar, z, ahVar);
        }
        return this.f3084b;
    }
}
