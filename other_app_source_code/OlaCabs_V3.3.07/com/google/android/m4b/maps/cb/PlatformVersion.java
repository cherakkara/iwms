package com.google.android.m4b.maps.cb;

import android.os.Build.VERSION;

/* renamed from: com.google.android.m4b.maps.cb.b */
public final class PlatformVersion {
    public static boolean m10087a() {
        return PlatformVersion.m10088a(11);
    }

    public static boolean m10089b() {
        return PlatformVersion.m10088a(13);
    }

    private static boolean m10088a(int i) {
        return VERSION.SDK_INT >= i;
    }
}
