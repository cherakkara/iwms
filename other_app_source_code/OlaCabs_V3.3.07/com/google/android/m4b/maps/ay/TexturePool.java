package com.google.android.m4b.maps.ay;

import android.content.res.Resources;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.ae.PerformanceProfile;

/* renamed from: com.google.android.m4b.maps.ay.m */
public final class TexturePool {
    private Texture[] f4952a;

    public TexturePool(Resources resources, GLState gLState) {
        PerformanceProfile.m4867a();
        this.f4952a = new Texture[31];
        m7629a(gLState, resources, 0, R.dav_one_way_16_256, true, false, true, false);
        m7629a(gLState, resources, 25, R.dav_road_8_128, true, false, false, true);
        m7629a(gLState, resources, 26, R.dav_road_10_128, true, false, false, true);
        m7629a(gLState, resources, 27, R.dav_road_12_128, true, false, false, true);
        m7629a(gLState, resources, 28, R.dav_road_14_128, true, false, false, true);
        m7629a(gLState, resources, 29, R.dav_road_22_128, true, false, false, true);
        m7629a(gLState, resources, 30, R.dav_dashed_highlight_16_256, true, false, true, false);
        m7629a(gLState, resources, 1, R.dav_road_32_128, true, false, false, true);
        m7629a(gLState, resources, 2, R.dav_road_40_128, true, false, false, true);
        m7629a(gLState, resources, 3, R.dav_road_48_128, true, false, false, true);
        m7629a(gLState, resources, 4, R.dav_road_56_128, true, false, false, true);
        m7629a(gLState, resources, 5, R.dav_road_32_64, true, false, false, true);
        m7629a(gLState, resources, 6, R.dav_road_44_64, true, false, false, true);
        m7629a(gLState, resources, 22, R.dav_road_48_64, true, false, false, true);
        m7629a(gLState, resources, 7, R.dav_road_hybrid_6_32_low_zoom, true, false, false, true);
        m7629a(gLState, resources, 21, R.dav_road_hybrid_6_32_high_zoom, true, false, false, true);
        m7629a(gLState, resources, 8, R.dav_route_line, false, false, false, false);
        m7629a(gLState, resources, 9, R.dav_route_line_bright, false, false, false, false);
        m7629a(gLState, resources, 10, R.dav_traffic_bg, false, false, true, true);
        m7629a(gLState, resources, 20, R.dav_background_grid, false, true, true, true);
        m7629a(gLState, resources, 23, R.dav_drop_shadow_gradient, false, false, false, false);
        m7629a(gLState, resources, 24, R.dav_colored_polyline, false, false, false, false);
        m7629a(gLState, resources, 11, R.dav_traffic_fill, false, false, true, true);
        PerformanceProfile.m4868b();
    }

    public final void m7631a() {
        for (Texture texture : this.f4952a) {
            if (texture != null) {
                texture.m7626f();
            }
        }
        this.f4952a = null;
    }

    public final Texture m7630a(int i) {
        return this.f4952a[i];
    }

    private void m7629a(GLState gLState, Resources resources, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4) {
        Texture texture = new Texture(gLState);
        texture.m7614a(z2);
        texture.m7618b(z3);
        texture.m7621c(true);
        if (z) {
            if (z4) {
                texture.m7623d(resources, i2);
            } else {
                texture.m7620c(resources, i2);
            }
        } else if (z4) {
            texture.m7616b(resources, i2);
        } else {
            texture.m7610a(resources, i2);
        }
        this.f4952a[i] = texture;
    }
}
