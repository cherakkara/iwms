package com.google.android.m4b.maps.be;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: VersionManager */
public final class bg {
    private static boolean f5805a;
    @SuppressLint({"UseSparseArrays"})
    private static final Map<Integer, String[]> f5806b;
    private static int f5807c;

    static {
        bg.class.getSimpleName();
        Map hashMap = new HashMap();
        f5806b = hashMap;
        hashMap.put(Integer.valueOf(4000000), new String[]{"android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE"});
    }

    private bg() {
    }

    private static boolean m8852a(int i) {
        return f5805a || f5807c >= i;
    }

    public static void m8849a(int i, boolean z) {
        f5807c = i;
        f5805a = z;
    }

    public static boolean m8851a() {
        return !m8852a(4000000);
    }

    public static boolean m8853b() {
        return m8852a(6500000);
    }

    public static boolean m8854c() {
        return !m8852a(6500000);
    }

    public static void m8850a(Context context) {
        List<String> arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        for (Entry entry : f5806b.entrySet()) {
            if (f5807c >= ((Integer) entry.getKey()).intValue()) {
                for (String str : (String[]) entry.getValue()) {
                    try {
                        packageManager.getPermissionInfo(str, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                        if (context.checkCallingOrSelfPermission(str) != 0) {
                            arrayList.add(str);
                        }
                    } catch (NameNotFoundException e) {
                    }
                }
            }
        }
        if (arrayList.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder("The Maps API requires the additional following permissions to be set in the AndroidManifest.xml to ensure a correct behavior:");
            for (String str2 : arrayList) {
                stringBuilder.append("\n<uses-permission android:name=\"" + str2 + "\"/>");
            }
            throw new SecurityException(stringBuilder.toString());
        }
    }
}
