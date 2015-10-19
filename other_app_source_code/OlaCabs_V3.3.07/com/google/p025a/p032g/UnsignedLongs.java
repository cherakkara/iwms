package com.google.p025a.p032g;

import com.google.p025a.p026a.Preconditions;
import java.math.BigInteger;

/* renamed from: com.google.a.g.c */
public final class UnsignedLongs {
    private static final long[] f1903a;
    private static final int[] f1904b;
    private static final int[] f1905c;

    private static long m3012a(long j) {
        return Long.MIN_VALUE ^ j;
    }

    public static int m3011a(long j, long j2) {
        return Longs.m3010a(UnsignedLongs.m3012a(j), UnsignedLongs.m3012a(j2));
    }

    public static long m3016b(long j, long j2) {
        int i = 1;
        if (j2 < 0) {
            if (UnsignedLongs.m3011a(j, j2) < 0) {
                return 0;
            }
            return 1;
        } else if (j >= 0) {
            return j / j2;
        } else {
            long j3 = ((j >>> 1) / j2) << 1;
            if (UnsignedLongs.m3011a(j - (j3 * j2), j2) < 0) {
                i = 0;
            }
            return ((long) i) + j3;
        }
    }

    public static long m3017c(long j, long j2) {
        if (j2 < 0) {
            if (UnsignedLongs.m3011a(j, j2) < 0) {
                return j;
            }
            return j - j2;
        } else if (j >= 0) {
            return j % j2;
        } else {
            long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
            if (UnsignedLongs.m3011a(j3, j2) < 0) {
                j2 = 0;
            }
            return j3 - j2;
        }
    }

    public static long m3013a(String str) {
        return UnsignedLongs.m3014a(str, 10);
    }

    public static long m3014a(String str, int i) {
        Preconditions.m1817a((Object) str);
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        } else if (i < 2 || i > 36) {
            throw new NumberFormatException("illegal radix: " + i);
        } else {
            int i2 = f1905c[i] - 1;
            long j = 0;
            int i3 = 0;
            while (i3 < str.length()) {
                int digit = Character.digit(str.charAt(i3), i);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                } else if (i3 <= i2 || !UnsignedLongs.m3015a(j, digit, i)) {
                    j = (j * ((long) i)) + ((long) digit);
                    i3++;
                } else {
                    throw new NumberFormatException("Too large for unsigned long: " + str);
                }
            }
            return j;
        }
    }

    private static boolean m3015a(long j, int i, int i2) {
        if (j < 0) {
            return true;
        }
        if (j < f1903a[i2]) {
            return false;
        }
        if (j > f1903a[i2] || i > f1904b[i2]) {
            return true;
        }
        return false;
    }

    static {
        f1903a = new long[37];
        f1904b = new int[37];
        f1905c = new int[37];
        BigInteger bigInteger = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            f1903a[i] = UnsignedLongs.m3016b(-1, (long) i);
            f1904b[i] = (int) UnsignedLongs.m3017c(-1, (long) i);
            f1905c[i] = bigInteger.toString(i).length() - 1;
        }
    }
}
