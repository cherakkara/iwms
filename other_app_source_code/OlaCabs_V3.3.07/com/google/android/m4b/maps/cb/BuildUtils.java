package com.google.android.m4b.maps.cb;

import android.content.Context;
import com.olacabs.customer.utils.Constants;

/* renamed from: com.google.android.m4b.maps.cb.a */
public final class BuildUtils {
    public static int m10084a(int i) {
        return i / Constants.MILLIS_IN_A_SECOND;
    }

    public static boolean m10086b(int i) {
        return (i % Constants.MILLIS_IN_A_SECOND) / 100 == 3;
    }

    public static boolean m10085a(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }
}
