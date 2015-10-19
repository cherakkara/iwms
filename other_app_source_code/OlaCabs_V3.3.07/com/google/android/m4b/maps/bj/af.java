package com.google.android.m4b.maps.bj;

import android.opengl.GLU;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: Projector */
public final class af {
    private final MatrixGrabber f6344a;
    private final int[] f6345b;

    public af() {
        this.f6345b = new int[4];
        this.f6344a = new MatrixGrabber();
    }

    public final void m9780a(int i, int i2, int i3, int i4) {
        this.f6345b[0] = 0;
        this.f6345b[1] = 0;
        this.f6345b[2] = i3;
        this.f6345b[3] = i4;
    }

    public final void m9782a(float[] fArr, int i, float[] fArr2, int i2) {
        GLU.gluProject(fArr[0], fArr[1], fArr[2], this.f6344a.f6668a, 0, this.f6344a.f6669b, 0, this.f6345b, 0, fArr2, 4);
    }

    public final void m9783a(float[] fArr, int i, float[] fArr2, float[] fArr3, int i2) {
        GLU.gluProject(fArr[0], fArr[1], fArr[2], fArr2 == null ? this.f6344a.f6668a : fArr2, 0, this.f6344a.f6669b, 0, this.f6345b, 0, fArr3, 0);
    }

    public final void m9785b(float[] fArr, int i, float[] fArr2, float[] fArr3, int i2) {
        GLU.gluUnProject(fArr[0], fArr[1], fArr[2], fArr2 == null ? this.f6344a.f6668a : fArr2, 0, this.f6344a.f6669b, 0, this.f6345b, 0, fArr3, 0);
    }

    public final void m9781a(GL10 gl10) {
        this.f6344a.m10032c(gl10);
    }

    public final void m9784b(GL10 gl10) {
        this.f6344a.m10031b(gl10);
    }
}
