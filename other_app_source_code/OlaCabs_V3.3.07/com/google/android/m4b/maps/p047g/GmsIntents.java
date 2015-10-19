package com.google.android.m4b.maps.p047g;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityEventCompat;

/* renamed from: com.google.android.m4b.maps.g.f */
public final class GmsIntents {
    private static final Uri f7423a;

    static {
        Uri parse = Uri.parse("http://plus.google.com/");
        f7423a = parse;
        parse.buildUpon().appendPath("circles").appendPath("find").build();
    }

    public static Intent m10381a(String str) {
        Uri fromParts = Uri.fromParts("package", str, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }

    public static Intent m10380a() {
        return new Intent("android.settings.DATE_SETTINGS");
    }

    public static Intent m10382b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details").buildUpon().appendQueryParameter("id", str).build());
        intent.setPackage("com.android.vending");
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        return intent;
    }
}
