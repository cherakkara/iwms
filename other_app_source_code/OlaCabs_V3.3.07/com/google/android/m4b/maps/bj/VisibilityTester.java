package com.google.android.m4b.maps.bj;

import android.opengl.Matrix;
import android.opengl.Visibility;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.bj.j */
final class VisibilityTester {
    private final MatrixGrabber f6518a;
    private final float[] f6519b;

    public VisibilityTester() {
        this.f6519b = new float[16];
        this.f6518a = new MatrixGrabber();
    }

    public final void m9947a(GL10 gl10) {
        this.f6518a.m10030a(gl10);
        Matrix.multiplyMM(this.f6519b, 0, this.f6518a.f6669b, 0, this.f6518a.f6668a, 0);
    }

    public final int m9946a(float[] fArr, int i, char[] cArr, int i2, int i3) {
        return Visibility.visibilityTest(this.f6519b, 0, fArr, 0, cArr, 0, i3);
    }

    public final int m9945a(float[] fArr, int i, int i2, int[] iArr, int i3, int i4) {
        return Visibility.frustumCullSpheres(this.f6519b, 0, fArr, 0, i2, iArr, 0, i4);
    }
}
