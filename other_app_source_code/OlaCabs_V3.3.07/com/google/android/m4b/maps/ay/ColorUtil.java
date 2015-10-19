package com.google.android.m4b.maps.ay;

import android.support.v4.view.MotionEventCompat;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ay.c */
public final class ColorUtil {
    public static int m7482a(float f, float f2, float f3, float f4) {
        return (((ColorUtil.m7484a((int) ((f * 255.0f) + 0.5f), 0, MotionEventCompat.ACTION_MASK) << 24) | (ColorUtil.m7484a((int) ((f2 * 255.0f) + 0.5f), 0, MotionEventCompat.ACTION_MASK) << 16)) | (ColorUtil.m7484a((int) ((f3 * 255.0f) + 0.5f), 0, MotionEventCompat.ACTION_MASK) << 8)) | ColorUtil.m7484a((int) ((255.0f * f4) + 0.5f), 0, MotionEventCompat.ACTION_MASK);
    }

    public static void m7485a(GL10 gl10, int i) {
        gl10.glColor4x(((i >> 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((i >> 16) & MotionEventCompat.ACTION_MASK), (i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((i >> 8) & MotionEventCompat.ACTION_MASK), ((i << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (i & MotionEventCompat.ACTION_MASK), ((i >> 16) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((i >> 24) & MotionEventCompat.ACTION_MASK));
    }

    private static int m7484a(int i, int i2, int i3) {
        if (i < 0) {
            return 0;
        }
        return i > MotionEventCompat.ACTION_MASK ? MotionEventCompat.ACTION_MASK : i;
    }

    public static int m7483a(int i) {
        return (((((i >> 16) & MotionEventCompat.ACTION_MASK) * 3) + (((i >> 8) & MotionEventCompat.ACTION_MASK) * 10)) + (i & MotionEventCompat.ACTION_MASK)) / 14;
    }
}
