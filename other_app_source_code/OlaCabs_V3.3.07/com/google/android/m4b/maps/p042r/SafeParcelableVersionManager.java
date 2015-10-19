package com.google.android.m4b.maps.p042r;

/* renamed from: com.google.android.m4b.maps.r.p */
public final class SafeParcelableVersionManager {
    private static boolean f7724a;

    static {
        f7724a = false;
    }

    public static void m11187a(int i) {
        f7724a = i < 3100000;
    }

    public static boolean m11188a() {
        return f7724a;
    }
}
