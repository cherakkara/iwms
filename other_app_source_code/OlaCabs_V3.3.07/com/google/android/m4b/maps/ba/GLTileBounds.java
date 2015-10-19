package com.google.android.m4b.maps.ba;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.av.TileOverlay;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.r */
public final class GLTileBounds implements GLDrawable {
    static final GLTileBounds f5350a;
    private static boolean f5351b;

    static {
        f5350a = new GLTileBounds();
    }

    public static void m8115a(GLState gLState, ad adVar) {
        GL10 x = gLState.m7541x();
        ai i_ = ((TileOverlay) adVar.m6705c().m7206a()).i_();
        boolean f = i_.m5515f();
        f5351b = f;
        if (f) {
            i_.m5511a(x);
            x.glLineWidth(5.0f);
            gLState.f4855i.m7736d(gLState);
        }
    }

    public final void m8116a(GLState gLState, Camera camera, ad adVar) {
        if (f5351b) {
            gLState.m7541x().glDrawArrays(2, 0, 4);
        }
    }

    public static void m8114a(GLState gLState) {
        gLState.m7543z();
        gLState.m7541x().glColor4x(AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT);
        gLState.f4851e.m7736d(gLState);
        gLState.m7532o();
        gLState.m7534q();
        gLState.m7541x().glDrawArrays(5, 0, 4);
        gLState.m7503A();
    }

    public final void m8117b(GLState gLState) {
    }

    public final void m8118c(GLState gLState) {
    }
}
