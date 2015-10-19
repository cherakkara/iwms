package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.RenderPass.RenderPass;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLDrawable;
import java.util.Collection;
import java.util.List;

/* compiled from: GLOverlay */
public abstract class al implements GLDrawable {

    /* renamed from: com.google.android.m4b.maps.av.al.a */
    public enum GLOverlay {
        UNUSED,
        BASE_IMAGERY,
        VECTORS,
        NIGHT_DIMMER,
        DESATURATE,
        TRAFFIC,
        INDOOR,
        LAYER_SHAPES,
        TRANSIT,
        BUILDINGS,
        POLYLINE,
        LABELS,
        FADE_OUT_OVERLAY,
        ROUTE_OVERVIEW_POLYLINE,
        TURN_ARROW_OVERLAY,
        IMPORTANT_LABELS,
        DEBUG_LABELS,
        INTERSECTION,
        SKY,
        MY_LOCATION_OVERLAY_DA,
        LAYERS_RAW_GPS,
        LAYER_MARKERS_SHADOW,
        LAYER_MARKERS,
        MY_LOCATION_OVERLAY_VECTORMAPS,
        COMPASS_OVERLAY,
        LOADING_SPINNY,
        BUBBLE,
        HEADS_UP_DISPLAY
    }

    public abstract void m6669a(GLState gLState, Camera camera, ad adVar);

    public abstract GLOverlay m6680d();

    protected RenderPass m6684m() {
        return RenderPass.DEFAULT;
    }

    protected final RenderPass m6662a(RenderPass renderPass) {
        return new RenderPass(this, renderPass, new RenderTweak[0]);
    }

    protected final RenderPass m6664a(RenderPass renderPass, RenderTweak... renderTweakArr) {
        return new RenderPass(this, renderPass, renderTweakArr);
    }

    protected final RenderPass m6663a(RenderPass renderPass, Collection<RenderTweak> collection, Collection<RenderTweak> collection2) {
        return new RenderPass(this, renderPass, collection, collection2);
    }

    public boolean m6673a(List<RenderPass> list) {
        if (!list.isEmpty()) {
            return false;
        }
        list.add(m6662a(m6684m()));
        return true;
    }

    public AnimationCallback m6682h() {
        return null;
    }

    public void m6674b(GLState gLState) {
    }

    public void m6677c(GLState gLState) {
        m6674b(gLState);
    }

    public boolean m6672a(Camera camera, GLState gLState) {
        return true;
    }

    public void m6668a(GLState gLState, RepaintCallback repaintCallback) {
    }

    public void m6667a(GLState gLState) {
    }

    public void m6670a(boolean z) {
    }

    public void h_() {
    }

    public boolean m6671a(float f, float f2, Point point, Camera camera) {
        return false;
    }

    public boolean a_(float f, float f2, Camera camera) {
        return false;
    }

    public boolean m6675b(float f, float f2, Point point, Camera camera) {
        return false;
    }

    public boolean j_() {
        return false;
    }

    public boolean m6679c(float f, float f2, Camera camera) {
        return false;
    }

    public boolean m6678c(float f, float f2, Point point, Camera camera) {
        return false;
    }

    public boolean m6676b(float f, float f2, Camera camera) {
        return false;
    }

    public void d_() {
    }

    public boolean m6683l() {
        return false;
    }

    public void m6665a(int i) {
    }

    public void m6666a(long j) {
    }

    public boolean m6681e() {
        return true;
    }

    protected boolean e_() {
        return false;
    }
}
