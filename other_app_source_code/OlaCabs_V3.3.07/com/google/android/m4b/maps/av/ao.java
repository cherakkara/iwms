package com.google.android.m4b.maps.av;

import android.support.v4.widget.ExploreByTouchHelper;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.ColorUtil;
import com.google.android.m4b.maps.ay.GLState;
import java.util.Arrays;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLSolidColorOverlay */
public class ao extends al {
    private GLOverlay f4325a;
    private int[] f4326b;

    public ao(GLOverlay gLOverlay) {
        this.f4326b = new int[(ac.f4132g + 1)];
        this.f4325a = gLOverlay;
    }

    public void m6870b(int i) {
        Arrays.fill(this.f4326b, i);
    }

    public final void m6868a(ac acVar, int i) {
        this.f4326b[acVar.m6700a()] = ExploreByTouchHelper.INVALID_ID;
    }

    public final GLOverlay m6871d() {
        return this.f4325a;
    }

    public final void m6869a(GLState gLState, Camera camera, ad adVar) {
        int i = this.f4326b[adVar.m6701a().m6700a()];
        if (adVar.m6704b() <= 0 && i != 0) {
            GL10 x = gLState.m7541x();
            x.glPushMatrix();
            x.glLoadIdentity();
            x.glTranslatef(0.0f, 0.0f, -1.0f);
            x.glBlendFunc(770, 771);
            ColorUtil.m7485a(x, i);
            gLState.f4853g.m7736d(gLState);
            x.glDrawArrays(5, 0, 4);
            x.glPopMatrix();
        }
    }
}
