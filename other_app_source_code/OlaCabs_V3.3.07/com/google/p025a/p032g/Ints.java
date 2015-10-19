package com.google.p025a.p032g;

import android.support.v4.widget.ExploreByTouchHelper;

/* renamed from: com.google.a.g.a */
public final class Ints {
    public static int m3009a(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        return (int) j;
    }
}
