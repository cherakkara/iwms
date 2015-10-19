package com.google.android.m4b.maps.p040u;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.google.android.m4b.maps.cf.GoogleSettingsContract.GoogleSettingsContract;

/* renamed from: com.google.android.m4b.maps.u.s */
public final class Util {
    private static volatile String f7971a;

    static {
        f7971a = null;
    }

    public static String m11520a(Context context) {
        String str = f7971a;
        if (str != null) {
            return str;
        }
        if (VERSION.SDK.equals("deskdroid")) {
            return null;
        }
        str = Secure.getString(context.getContentResolver(), "logging_id2");
        if (str == null) {
            str = GoogleSettingsContract.m10137a(context.getContentResolver(), "logging_id2");
        }
        f7971a = str;
        return str;
    }
}
