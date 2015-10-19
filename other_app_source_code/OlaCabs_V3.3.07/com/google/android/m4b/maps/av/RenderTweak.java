package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.p057t.FeatureId;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.av.i */
public interface RenderTweak extends Comparable<RenderTweak> {
    Set<? extends FeatureId> m5293a();

    void m5294a(GLState gLState, ad adVar);

    void m5295a(GLState gLState, Camera camera, ad adVar, Point point);
}
