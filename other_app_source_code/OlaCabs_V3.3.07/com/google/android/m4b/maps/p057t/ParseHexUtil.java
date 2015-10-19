package com.google.android.m4b.maps.p057t;

import android.support.v4.widget.ExploreByTouchHelper;

/* renamed from: com.google.android.m4b.maps.t.d */
public final class ParseHexUtil {
    public static long m11308a(String str) {
        if (ParseHexUtil.m11309a(str, 16)) {
            return Long.parseLong(ParseHexUtil.m11311c(str), 16) - Long.MIN_VALUE;
        }
        return Long.parseLong(str, 16);
    }

    public static int m11310b(String str) {
        if (ParseHexUtil.m11309a(str, 8)) {
            return Integer.parseInt(ParseHexUtil.m11311c(str), 16) + ExploreByTouchHelper.INVALID_ID;
        }
        return Integer.parseInt(str, 16);
    }

    private static boolean m11309a(String str, int i) {
        if (str.length() != i || Integer.parseInt(String.valueOf(str.charAt(0)), 16) <= 7) {
            return false;
        }
        return true;
    }

    private static String m11311c(String str) {
        return String.valueOf(Integer.parseInt(String.valueOf(str.charAt(0)), 16) - 8) + str.substring(1);
    }
}
