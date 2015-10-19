package com.google.android.m4b.maps.aj;

import android.util.FloatMath;

/* renamed from: com.google.android.m4b.maps.aj.h */
public abstract class GestureMotionEvent {
    private Float f3158a;
    private Float f3159b;

    public abstract float m5078a(int i);

    public abstract long m5079a();

    public abstract float m5080b(int i);

    public abstract int m5081b();

    public abstract float m5082c();

    public abstract float m5083d();

    public abstract void m5084e();

    public final float m5085f() {
        if (this.f3158a == null) {
            this.f3158a = Float.valueOf(GestureMotionEvent.m5077a(m5078a(0), m5080b(0), m5078a(m5081b() - 1), m5080b(m5081b() - 1)));
        }
        return this.f3158a.floatValue();
    }

    public static float m5077a(float f, float f2, float f3, float f4) {
        return (float) Math.atan2((double) (f3 - f), (double) (f4 - f2));
    }

    public final float m5086g() {
        if (this.f3159b == null) {
            float a = m5078a(0) - m5078a(m5081b() - 1);
            float b = m5080b(0) - m5080b(m5081b() - 1);
            this.f3159b = Float.valueOf(FloatMath.sqrt((a * a) + (b * b)));
        }
        return this.f3159b.floatValue();
    }
}
