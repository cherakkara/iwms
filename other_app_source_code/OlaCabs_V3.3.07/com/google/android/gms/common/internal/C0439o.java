package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityEventCompat;

/* renamed from: com.google.android.gms.common.internal.o */
public class C0439o {
    private static final Uri f2303a;
    private static final Uri f2304b;

    static {
        f2303a = Uri.parse("http://plus.google.com/");
        f2304b = f2303a.buildUpon().appendPath("circles").appendPath("find").build();
    }

    public static Intent m3678a() {
        Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }

    public static Intent m3679a(String str) {
        Uri fromParts = Uri.fromParts("package", str, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }

    public static Intent m3680b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(C0439o.m3681c(str));
        intent.setPackage("com.android.vending");
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        return intent;
    }

    private static Uri m3681c(String str) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", str).build();
    }
}
