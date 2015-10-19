package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLLabel;
import java.util.List;

/* compiled from: DebugLabelOverlay */
public final class ab extends al {
    private List<GLLabel> f4125a;

    public final GLOverlay m6699d() {
        return GLOverlay.DEBUG_LABELS;
    }

    public final boolean m6697a(Camera camera, GLState gLState) {
        return true;
    }

    public final void m6698b(List<GLLabel> list) {
        this.f4125a = list;
    }

    public final void m6696a(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6704b() <= 0 && adVar.m6701a() != ac.NONE && this.f4125a != null && !this.f4125a.isEmpty()) {
            gLState.m7533p();
            gLState.m7541x().glBlendFunc(1, 771);
            gLState.m7541x().glTexEnvx(8960, 8704, 8448);
            synchronized (this) {
                for (GLLabel gLLabel : this.f4125a) {
                    gLState.m7543z();
                    gLLabel.m6669a(gLState, camera, adVar);
                    gLState.m7503A();
                }
            }
        }
    }
}
