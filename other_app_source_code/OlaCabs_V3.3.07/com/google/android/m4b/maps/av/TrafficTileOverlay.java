package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.ag.TileCoordGeneratorProvider;
import com.google.android.m4b.maps.as.TileFetcher;
import com.google.android.m4b.maps.av.RenderPass.RenderPass;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.newrelic.agent.android.analytics.AnalyticAttribute;

/* renamed from: com.google.android.m4b.maps.av.n */
public final class TrafficTileOverlay extends TileOverlay {
    private float f4641d;
    private boolean f4642e;

    TrafficTileOverlay(TileFetcher tileFetcher, TileCoordGeneratorProvider tileCoordGeneratorProvider, int i, int i2, int i3, GLOverlay gLOverlay, int i4, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        super(tileFetcher.m6562a(), tileFetcher, tileCoordGeneratorProvider, i, i2, 0, gLOverlay, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, false, true, false, false, false, false);
        this.f4642e = false;
        this.f4641d = 30.0f;
    }

    protected final RenderPass m7274m() {
        return RenderPass.DEFAULT;
    }

    public final boolean m7273a(Camera camera, GLState gLState) {
        if (camera.m7444l() < this.f4641d) {
            this.f4642e = false;
            return super.m6743a(camera, gLState);
        }
        this.f4642e = true;
        return true;
    }

    public final void m7272a(GLState gLState, Camera camera, ad adVar) {
        if (camera.m7444l() < this.f4641d) {
            if (this.f4642e) {
                super.m6743a(camera, gLState);
            }
            super.m6739a(gLState, camera, adVar);
            return;
        }
        this.b = true;
    }
}
