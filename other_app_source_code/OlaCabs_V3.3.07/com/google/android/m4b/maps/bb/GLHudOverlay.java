package com.google.android.m4b.maps.bb;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.av.al;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.olacabs.customer.p076d.br;
import java.util.Iterator;
import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.bb.b */
public final class GLHudOverlay extends al {
    private final Vector<GLHudElement> f5399a;
    private GLHudElement f5400b;

    public GLHudOverlay() {
        this.f5399a = new Vector();
    }

    public final void m8173a(GLState gLState, Camera camera, ad adVar) {
        if (!this.f5399a.isEmpty()) {
            synchronized (this.f5399a) {
                Iterator it = this.f5399a.iterator();
                while (it.hasNext()) {
                    it.next();
                }
                GL10 x = gLState.m7541x();
                x.glPushMatrix();
                x.glMatrixMode(5889);
                x.glPushMatrix();
                x.glLoadIdentity();
                x.glOrthof(0.0f, (float) camera.m7437e(), 0.0f, (float) camera.m7438f(), -1.0f, br.DEFAULT_BACKOFF_MULT);
                x.glMatrixMode(5888);
                x.glLoadIdentity();
                try {
                    Iterator it2 = this.f5399a.iterator();
                    while (it2.hasNext()) {
                        ((GLHudElement) it2.next()).m6669a(gLState, camera, adVar);
                    }
                } finally {
                    GLHudOverlay.m8170d(gLState);
                }
            }
        }
    }

    private static void m8170d(GLState gLState) {
        GL10 x = gLState.m7541x();
        x.glMatrixMode(5889);
        x.glPopMatrix();
        x.glMatrixMode(5888);
        x.glPopMatrix();
    }

    public final boolean m8175a(Camera camera, GLState gLState) {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            ((GLHudElement) it.next()).m6672a(camera, gLState);
        }
        return super.m6672a(camera, gLState);
    }

    public final boolean m8174a(float f, float f2, Point point, Camera camera) {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            if (((GLHudElement) it.next()).m6671a(f, f2, point, camera)) {
                return true;
            }
        }
        return false;
    }

    public final boolean m8176b(float f, float f2, Camera camera) {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            GLHudElement gLHudElement = (GLHudElement) it.next();
            if (gLHudElement.m6676b(f, f2, camera)) {
                this.f5400b = gLHudElement;
                return true;
            }
        }
        return false;
    }

    public final void d_() {
        if (this.f5400b != null) {
            this.f5400b.d_();
            this.f5400b = null;
        }
    }

    public final boolean m8178c(float f, float f2, Camera camera) {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            if (((GLHudElement) it.next()).m6679c(f, f2, camera)) {
                return true;
            }
        }
        return false;
    }

    public final GLOverlay m8179d() {
        return GLOverlay.HEADS_UP_DISPLAY;
    }

    public final boolean j_() {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            if (((GLHudElement) it.next()).j_()) {
                return true;
            }
        }
        return false;
    }

    public final boolean m8177c(float f, float f2, Point point, Camera camera) {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            if (((GLHudElement) it.next()).m6678c(f, f2, point, camera)) {
                return true;
            }
        }
        return false;
    }

    public final void m8171a(GLState gLState) {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            ((GLHudElement) it.next()).m6667a(gLState);
        }
    }

    public final void m8172a(GLState gLState, RepaintCallback repaintCallback) {
        Iterator it = this.f5399a.iterator();
        while (it.hasNext()) {
            ((GLHudElement) it.next()).m6668a(gLState, repaintCallback);
        }
    }
}
