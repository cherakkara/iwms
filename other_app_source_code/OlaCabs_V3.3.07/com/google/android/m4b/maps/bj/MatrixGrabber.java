package com.google.android.m4b.maps.bj;

import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.bj.y */
final class MatrixGrabber {
    public final float[] f6668a;
    public final float[] f6669b;

    public MatrixGrabber() {
        this.f6668a = new float[16];
        this.f6669b = new float[16];
    }

    public final void m10030a(GL10 gl10) {
        m10032c(gl10);
        m10031b(gl10);
    }

    public final void m10031b(GL10 gl10) {
        MatrixGrabber.m10029a(gl10, 5888, this.f6668a);
    }

    public final void m10032c(GL10 gl10) {
        MatrixGrabber.m10029a(gl10, 5889, this.f6669b);
    }

    private static void m10029a(GL10 gl10, int i, float[] fArr) {
        if (gl10 instanceof GLMatrixWrapper) {
            GLMatrixWrapper gLMatrixWrapper = (GLMatrixWrapper) gl10;
            gLMatrixWrapper.glMatrixMode(i);
            gLMatrixWrapper.m9967a(fArr, 0);
            return;
        }
        throw new IllegalArgumentException("gl must be GLMatrixWrapper");
    }
}
