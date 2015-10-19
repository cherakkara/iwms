package com.google.android.m4b.maps.p059w;

import android.os.Build.VERSION;
import com.google.android.m4b.maps.bl.IsEmulator;

/* renamed from: com.google.android.m4b.maps.w.a */
public final class AndroidBuilds {
    public static boolean m11561a() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean m11562b() {
        try {
            return IsEmulator.m10057a();
        } catch (Exception e) {
            return false;
        }
    }
}
