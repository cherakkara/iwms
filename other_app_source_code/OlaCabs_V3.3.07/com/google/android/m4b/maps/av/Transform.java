package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.olacabs.customer.p076d.br;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.av.o */
public final class Transform {
    public static void m7277a(GLState gLState, Camera camera, Point point, float f, float[] fArr) {
        Transform.m7276a(gLState, camera, point, f, true, fArr);
    }

    private static void m7276a(GLState gLState, Camera camera, Point point, float f, boolean z, float[] fArr) {
        Point point2;
        Point point3;
        if (gLState == null) {
            point2 = new Point();
            point3 = new Point();
        } else {
            point2 = gLState.f4858l;
            point3 = gLState.f4859m;
        }
        camera.m7425a(point2);
        Point.m5936b(point, point2, point3);
        if (z) {
            point3.m5965i(point3);
        }
        float q = camera.m7449q();
        fArr[0] = ((float) point3.m5958f()) * q;
        fArr[1] = ((float) point3.m5960g()) * q;
        fArr[2] = ((float) point3.m5962h()) * q;
        fArr[3] = q * f;
    }

    public static void m7275a(GLState gLState, Camera camera, Point point, float f) {
        Transform.m7277a(gLState, camera, point, f, gLState.f4857k);
        Transform.m7279a(gLState.m7541x(), gLState.f4857k);
    }

    public static void m7280b(GLState gLState, Camera camera, Point point, float f) {
        Transform.m7276a(gLState, camera, point, f, false, gLState.f4857k);
        Transform.m7279a(gLState.m7541x(), gLState.f4857k);
    }

    public static void m7279a(GL10 gl10, float[] fArr) {
        gl10.glTranslatef(fArr[0], fArr[1], fArr[2]);
        gl10.glScalef(fArr[3], fArr[3], fArr[3]);
    }

    public static void m7278a(GL10 gl10, Camera camera) {
        gl10.glRotatef(-camera.m7442j(), 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
        gl10.glRotatef(camera.m7443k() - 90.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
    }
}
