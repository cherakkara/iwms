package com.google.android.m4b.maps.bl;

import android.os.Build;
import android.os.Build.VERSION;
import java.lang.reflect.Field;

/* renamed from: com.google.android.m4b.maps.bl.a */
public final class IsEmulator {
    private static Boolean f6682a;
    private static Exception f6683b;

    public static boolean m10057a() {
        boolean z = true;
        if (f6682a != null) {
            return f6682a.booleanValue();
        }
        if (f6683b != null) {
            throw f6683b;
        }
        try {
            if (Integer.parseInt(VERSION.SDK) < 8) {
                if (!("sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT))) {
                    z = false;
                }
                f6682a = Boolean.valueOf(z);
            } else {
                Class cls = Class.forName("android.os.Build");
                Field field = cls.getField("HARDWARE");
                field.setAccessible(true);
                f6682a = Boolean.valueOf("goldfish".equals((String) field.get(cls)));
            }
            return f6682a.booleanValue();
        } catch (Exception e) {
            f6683b = e;
            throw e;
        }
    }
}
