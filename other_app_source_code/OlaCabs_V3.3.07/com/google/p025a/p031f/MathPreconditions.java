package com.google.p025a.p031f;

import javax.annotation.Nullable;

/* renamed from: com.google.a.f.d */
final class MathPreconditions {
    static int m3007a(@Nullable String str, int i) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " (" + i + ") must be > 0");
    }

    static void m3008a(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }
}
