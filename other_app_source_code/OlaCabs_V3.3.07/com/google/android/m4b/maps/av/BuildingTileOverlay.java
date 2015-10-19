package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.ag.TileCoordGeneratorProvider;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.am.MapPoint;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.as.TileFetcher;
import com.google.android.m4b.maps.au.GeometryConverter;
import com.google.android.m4b.maps.av.RenderPass.RenderPass;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.be.IndoorLevelInterface;
import com.newrelic.agent.android.analytics.AnalyticAttribute;

/* renamed from: com.google.android.m4b.maps.av.z */
public final class BuildingTileOverlay extends TileOverlay {
    static {
        Rectangle2D.m6041a(GeometryConverter.m6618a(new MapPoint(38000000, 136000000)), GeometryConverter.m6618a(new MapPoint(33000000, 143000000)));
    }

    BuildingTileOverlay(ai aiVar, TileFetcher tileFetcher, TileCoordGeneratorProvider tileCoordGeneratorProvider, int i, int i2, int i3, GLOverlay gLOverlay, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        super(aiVar, tileFetcher, tileCoordGeneratorProvider, i, i2, 0, gLOverlay, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, false, false, false, false, false, false);
    }

    protected final RenderPass m7369m() {
        return RenderPass.ELEVATED_COLOR;
    }

    public final void m7368a(GLState gLState, Camera camera, ad adVar) {
        boolean z;
        IndoorState a = IndoorState.m5334a();
        float m = camera.m7445m();
        camera.m7430b();
        if (a == null) {
            z = true;
        } else {
            for (IndoorLevelInterface f : a.m5365f()) {
                if (f.m5806f() < 0) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (!z) {
                if (a.m5364e().isEmpty()) {
                    z = true;
                } else if (m <= 18.0f) {
                    z = true;
                }
            }
            z = false;
        }
        if (z) {
            super.m6739a(gLState, camera, adVar);
        } else {
            this.b = true;
        }
    }
}
