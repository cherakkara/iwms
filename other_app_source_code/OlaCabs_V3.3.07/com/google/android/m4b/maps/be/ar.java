package com.google.android.m4b.maps.be;

import android.util.FloatMath;
import com.olacabs.customer.p076d.br;

/* compiled from: StreetMath */
public final class ar {
    private static double f5671a;

    static {
        f5671a = Math.log(2.0d);
    }

    public static float m8591a(float f, float f2) {
        if (f >= 0.0f && f < 360.0f) {
            return f;
        }
        float f3 = f / 360.0f;
        return (f3 - FloatMath.floor(f3)) * 360.0f;
    }

    public static float m8590a(float f) {
        return f - (FloatMath.floor(f / 360.0f) * 360.0f);
    }

    public static float m8598b(float f) {
        return f - FloatMath.floor(f);
    }

    public static int m8594a(int i, int i2, int i3) {
        return Math.min(i3, Math.max(0, i));
    }

    public static float m8592a(float f, float f2, float f3) {
        return Math.min(f3, Math.max(f2, f));
    }

    public static float m8600c(float f) {
        return m8592a(f, 0.0f, (float) br.DEFAULT_BACKOFF_MULT);
    }

    public static float m8602d(float f) {
        return (float) (Math.log((double) f) / f5671a);
    }

    public static float m8604e(float f) {
        return (float) Math.exp(((double) f) * f5671a);
    }

    public static int m8595a(int i, int i2, int i3, int i4) {
        return Math.max(0, (int) FloatMath.ceil(m8602d(Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3))))) + 1;
    }

    public static float m8605f(float f) {
        return FloatMath.sin(f * 6.2831855f) / FloatMath.cos(6.2831855f * f);
    }

    public static float m8606g(float f) {
        return ((float) Math.atan((double) f)) * 0.15915494f;
    }

    private static float m8603d(float f, float f2) {
        float atan2 = ((float) Math.atan2((double) f, (double) f2)) * 0.15915494f;
        return atan2 - FloatMath.floor(atan2);
    }

    public static float m8607h(float f) {
        return FloatMath.sin(0.017453292f * f);
    }

    public static float m8608i(float f) {
        return FloatMath.cos(0.017453292f * f);
    }

    public static float m8609j(float f) {
        return 0.15915494f * f;
    }

    public static float m8610k(float f) {
        return 6.2831855f * f;
    }

    public static float m8611l(float f) {
        return 0.0027777778f * f;
    }

    public static float m8612m(float f) {
        return 360.0f * f;
    }

    public static float m8613n(float f) {
        return 57.29578f * f;
    }

    public static float m8614o(float f) {
        return 0.017453292f * f;
    }

    public static float m8599b(float f, float f2) {
        float a = m8590a(f - f2);
        return a < 180.0f ? a : a - 360.0f;
    }

    public static float m8601c(float f, float f2) {
        float f3 = f - f2;
        f3 -= FloatMath.floor(f3);
        return ((double) f3) < 0.5d ? f3 : f3 - br.DEFAULT_BACKOFF_MULT;
    }

    public static void m8597a(float f, float f2, float[] fArr, int i) {
        float sin = FloatMath.sin(f * 6.2831855f);
        float cos = FloatMath.cos(f * 6.2831855f);
        float sin2 = FloatMath.sin(f2 * 6.2831855f);
        float f3 = -FloatMath.cos(6.2831855f * f2);
        cos *= sin2;
        fArr[0] = sin * (-sin2);
        fArr[1] = f3;
        fArr[2] = cos;
    }

    public static void m8596a(float f, float f2, float f3, float[] fArr) {
        fArr[0] = m8603d(-f, f3);
        fArr[1] = m8603d(FloatMath.sqrt((f * f) + (f3 * f3)), -f2);
    }

    public static int m8615p(float f) {
        return ((int) FloatMath.floor((22.5f + f) / 45.0f)) & 7;
    }

    public static float m8593a(float f, float f2, float f3, float f4, float f5) {
        float f6 = br.DEFAULT_BACKOFF_MULT - f5;
        return (float) ((((((double) (f6 * 3.0f)) * Math.pow((double) f5, 2.0d)) * 0.949999988079071d) + ((Math.pow((double) f6, 3.0d) * 0.0d) + (((3.0d * Math.pow((double) f6, 2.0d)) * ((double) f5)) * 0.10000000149011612d))) + (Math.pow((double) f5, 3.0d) * 1.0d));
    }
}
