package android.support.v4.app;

import android.app.ActivityManager;
import android.os.Build.VERSION;

public final class ActivityManagerCompat {
    private ActivityManagerCompat() {
    }

    public static boolean isLowRamDevice(ActivityManager activityManager) {
        if (VERSION.SDK_INT >= 19) {
            return ActivityManagerCompatKitKat.isLowRamDevice(activityManager);
        }
        return false;
    }
}
