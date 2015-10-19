package com.google.android.m4b.maps.bd;

import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.bd.u */
public class ViewRenderTarget extends RenderTarget implements Renderer {
    protected final EntityRenderer f5516a;

    public void onDrawFrame(GL10 gl10) {
        this.f5516a.m8234a();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        m8239a(i, i2);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.f5516a.m8235b();
    }
}
