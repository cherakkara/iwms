package com.google.android.m4b.maps.p062z;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

/* renamed from: com.google.android.m4b.maps.z.a */
public final class CapabilitiesController {
    public static boolean m12017a(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= AccessibilityNodeInfoCompat.ACTION_SET_SELECTION;
    }
}
