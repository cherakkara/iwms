package com.google.android.gms.internal;

import android.content.Context;
import com.olacabs.customer.utils.Constants;
import java.util.regex.Pattern;

/* renamed from: com.google.android.gms.internal.u */
public final class C0516u {
    private static Pattern f2439a;

    static {
        f2439a = null;
    }

    public static int m4165a(int i) {
        return i / Constants.MILLIS_IN_A_SECOND;
    }

    public static boolean m4166a(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    public static int m4167b(int i) {
        return (i % Constants.MILLIS_IN_A_SECOND) / 100;
    }

    public static boolean m4168c(int i) {
        return C0516u.m4167b(i) == 3;
    }
}
