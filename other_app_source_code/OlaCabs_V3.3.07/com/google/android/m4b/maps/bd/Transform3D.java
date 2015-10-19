package com.google.android.m4b.maps.bd;

import android.opengl.Matrix;

/* renamed from: com.google.android.m4b.maps.bd.s */
public final class Transform3D {
    private float[] f5526a;

    public Transform3D() {
        this.f5526a = new float[16];
        Matrix.setIdentityM(this.f5526a, 0);
    }

    public final String toString() {
        float[] fArr = this.f5526a;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            stringBuffer.append(fArr[i] + "  ");
            if (i % 4 == 3) {
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }
}
