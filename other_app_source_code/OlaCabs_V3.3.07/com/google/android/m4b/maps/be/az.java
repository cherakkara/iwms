package com.google.android.m4b.maps.be;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.bl.IsEmulator;

/* compiled from: SystemUtil */
public final class az {
    public static boolean m8758a(Activity activity) {
        if ((activity.getWindow().getAttributes().flags & ViewCompat.MEASURED_STATE_TOO_SMALL) != 0) {
            return true;
        }
        try {
            if ((activity.getPackageManager().getActivityInfo(activity.getComponentName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).flags & AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY) == 0) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean m8760a(boolean z) {
        if (!z || VERSION.SDK_INT < 14) {
            return false;
        }
        try {
            if (IsEmulator.m10057a()) {
                return false;
            }
        } catch (Exception e) {
        }
        if (ServerControlledParametersManager.m4808c().m4823d()) {
            return ServerControlledParametersManager.m4808c().m4824e();
        }
        if (VERSION.SDK_INT >= 16) {
            return true;
        }
        return false;
    }

    public static boolean m8759a(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean m8761b(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.hypot((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), (double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi)) >= 5.0d;
    }

    public static void m8757a(int i, String str) {
        Log.println(i, "Google Maps Android API", str);
    }
}
