package com.google.android.m4b.maps.ag;

import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;

/* renamed from: com.google.android.m4b.maps.ag.g */
public class TileCoordGeneratorFactory implements TileCoordGeneratorProvider {
    public TileCoordGenerator m4902a(ai aiVar, int i, boolean z, ah ahVar) {
        if (z) {
            return new MultiZoomTileCoordGenerator(aiVar, i, ahVar);
        }
        return new SingleZoomTileCoordGenerator(aiVar, ahVar);
    }

    public SingleZoomTileCoordGenerator m4901a(ai aiVar, boolean z, ah ahVar) {
        if (z) {
            return new SingleZoomTileCoordGenerator(aiVar, ahVar);
        }
        return null;
    }
}
