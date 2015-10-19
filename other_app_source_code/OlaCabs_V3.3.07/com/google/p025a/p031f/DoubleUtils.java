package com.google.p025a.p031f;

import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.a.f.b */
final class DoubleUtils {
    private static final long f1896a;

    static int m3001a(double d) {
        return ((int) ((Double.doubleToRawLongBits(d) & 9218868437227405312L) >>> 52)) - 1023;
    }

    static long m3002b(double d) {
        Preconditions.m1823a(DoubleUtils.m3003c(d), (Object) "not a normal value");
        long doubleToRawLongBits = Double.doubleToRawLongBits(d) & 4503599627370495L;
        return DoubleUtils.m3001a(d) == -1023 ? doubleToRawLongBits << 1 : 4503599627370496L | doubleToRawLongBits;
    }

    static boolean m3003c(double d) {
        return DoubleUtils.m3001a(d) <= 1023;
    }

    static {
        f1896a = Double.doubleToRawLongBits(1.0d);
    }
}
