package com.google.p025a.p031f;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.math.RoundingMode;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* renamed from: com.google.a.f.c */
public final class IntMath {
    static final byte[] f1898a;
    static final int[] f1899b;
    static final int[] f1900c;
    static int[] f1901d;
    private static final int[] f1902e;

    /* renamed from: com.google.a.f.c.1 */
    static /* synthetic */ class IntMath {
        static final /* synthetic */ int[] f1897a;

        static {
            f1897a = new int[RoundingMode.values().length];
            try {
                f1897a[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1897a[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1897a[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1897a[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1897a[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1897a[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1897a[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f1897a[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static boolean m3006a(int i) {
        int i2 = 1;
        int i3 = i > 0 ? 1 : 0;
        if (((i - 1) & i) != 0) {
            i2 = 0;
        }
        return i2 & i3;
    }

    public static int m3005a(int i, RoundingMode roundingMode) {
        MathPreconditions.m3007a("x", i);
        switch (IntMath.f1897a[roundingMode.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                MathPreconditions.m3008a(IntMath.m3006a(i));
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return 32 - Integer.numberOfLeadingZeros(i - 1);
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                int i2 = -1257966797 >>> numberOfLeadingZeros;
                numberOfLeadingZeros = 31 - numberOfLeadingZeros;
                if (i > i2) {
                    return numberOfLeadingZeros + 1;
                }
                return numberOfLeadingZeros;
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    static {
        f1898a = new byte[]{(byte) 9, (byte) 9, (byte) 9, (byte) 8, (byte) 8, (byte) 8, (byte) 7, (byte) 7, (byte) 7, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 5, (byte) 5, (byte) 5, (byte) 4, (byte) 4, (byte) 4, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 2, (byte) 1, (byte) 1, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        f1899b = new int[]{1, 10, 100, Constants.MILLIS_IN_A_SECOND, AbstractSpiCall.DEFAULT_TIMEOUT, 100000, 1000000, 10000000, 100000000, 1000000000};
        f1900c = new int[]{3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
        f1902e = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
        f1901d = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, AccessibilityNodeInfoCompat.ACTION_CUT, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m3004a(int r7, int r8, java.math.RoundingMode r9) {
        /*
        r0 = 1;
        r1 = 0;
        com.google.p025a.p026a.Preconditions.m1817a(r9);
        if (r8 != 0) goto L_0x000f;
    L_0x0007:
        r0 = new java.lang.ArithmeticException;
        r1 = "/ by zero";
        r0.<init>(r1);
        throw r0;
    L_0x000f:
        r2 = r7 / r8;
        r3 = r8 * r2;
        r3 = r7 - r3;
        if (r3 != 0) goto L_0x0018;
    L_0x0017:
        return r2;
    L_0x0018:
        r4 = r7 ^ r8;
        r4 = r4 >> 31;
        r5 = r4 | 1;
        r4 = com.google.p025a.p031f.IntMath.IntMath.f1897a;
        r6 = r9.ordinal();
        r4 = r4[r6];
        switch(r4) {
            case 1: goto L_0x002f;
            case 2: goto L_0x0034;
            case 3: goto L_0x0041;
            case 4: goto L_0x0035;
            case 5: goto L_0x003d;
            case 6: goto L_0x0045;
            case 7: goto L_0x0045;
            case 8: goto L_0x0045;
            default: goto L_0x0029;
        };
    L_0x0029:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x002f:
        if (r3 != 0) goto L_0x003b;
    L_0x0031:
        com.google.p025a.p031f.MathPreconditions.m3008a(r0);
    L_0x0034:
        r0 = r1;
    L_0x0035:
        if (r0 == 0) goto L_0x006d;
    L_0x0037:
        r0 = r2 + r5;
    L_0x0039:
        r2 = r0;
        goto L_0x0017;
    L_0x003b:
        r0 = r1;
        goto L_0x0031;
    L_0x003d:
        if (r5 > 0) goto L_0x0035;
    L_0x003f:
        r0 = r1;
        goto L_0x0035;
    L_0x0041:
        if (r5 < 0) goto L_0x0035;
    L_0x0043:
        r0 = r1;
        goto L_0x0035;
    L_0x0045:
        r3 = java.lang.Math.abs(r3);
        r4 = java.lang.Math.abs(r8);
        r4 = r4 - r3;
        r3 = r3 - r4;
        if (r3 != 0) goto L_0x0069;
    L_0x0051:
        r3 = java.math.RoundingMode.HALF_UP;
        if (r9 == r3) goto L_0x0062;
    L_0x0055:
        r3 = java.math.RoundingMode.HALF_EVEN;
        if (r9 != r3) goto L_0x0065;
    L_0x0059:
        r4 = r0;
    L_0x005a:
        r3 = r2 & 1;
        if (r3 == 0) goto L_0x0067;
    L_0x005e:
        r3 = r0;
    L_0x005f:
        r3 = r3 & r4;
        if (r3 == 0) goto L_0x0063;
    L_0x0062:
        r1 = r0;
    L_0x0063:
        r0 = r1;
        goto L_0x0035;
    L_0x0065:
        r4 = r1;
        goto L_0x005a;
    L_0x0067:
        r3 = r1;
        goto L_0x005f;
    L_0x0069:
        if (r3 > 0) goto L_0x0035;
    L_0x006b:
        r0 = r1;
        goto L_0x0035;
    L_0x006d:
        r0 = r2;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.a.f.c.a(int, int, java.math.RoundingMode):int");
    }
}
