package com.google.android.m4b.maps.p059w;

import android.os.Build;
import android.os.Build.VERSION;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.w.b */
public final class DeviceSpecificInfo {
    public static boolean f8006a;
    public static final boolean f8007b;
    public static final boolean f8008c;
    public static final boolean f8009d;
    private static final String[] f8010e;
    private static final String[] f8011f;
    private static final String[] f8012g;
    private static final String[] f8013h;
    private static final String[] f8014i;
    private static final String[] f8015j;
    private static final String[] f8016k;
    private static final String[] f8017l;
    private static final String[] f8018m;
    private static final String[] f8019n;
    private static volatile String f8020o;
    private static boolean f8021p;

    static {
        Object obj;
        boolean z = true;
        f8010e = new String[]{"SOJU", "SOJUA", "SOJUK", "SOJU_L10N", "GT-I9000", "GT-I9000B", "GT-I9000M", "GT-I9000T", "SC-02B", "SGH-T959", "SGH-T959D", "SGH-T959V", "VIBRANT T959", "SHW-M110S", "SCH-I400", "SGH-I897", "SGH-I896"};
        f8011f = new String[]{"RTGB", "SHADOW_VZW", "DAYTONA"};
        f8012g = new String[]{"SHADOW_VZW", "DAYTONA", "SPYDER_VZW"};
        f8013h = new String[]{"SHADOW", "DAYTONA", "SPYDER"};
        f8014i = new String[]{"HTC_VISION"};
        f8015j = new String[]{"HTC_MARVEL", "HTC_MARVELC", "MARVELC"};
        f8016k = new String[]{"PASSION", "PASSION_KT", "PASSION_VF"};
        f8017l = new String[]{"HTC_PYRAMID", "HTC_VIGOR"};
        f8018m = new String[]{"SONY ERICSSON"};
        f8019n = new String[]{"TG03", "F11EIF"};
        Object toUpperCase = Build.PRODUCT == null ? Trace.NULL : Build.PRODUCT.toUpperCase();
        Object toUpperCase2 = Build.BOARD == null ? Trace.NULL : Build.BOARD.toUpperCase();
        if (Build.MANUFACTURER == null) {
            obj = Trace.NULL;
        } else {
            obj = Build.MANUFACTURER.toUpperCase();
        }
        if (!Arrays.asList(f8010e).contains(toUpperCase) || AndroidBuilds.m11561a()) {
            z = false;
        }
        f8006a = z;
        Arrays.asList(f8014i).contains(toUpperCase);
        f8007b = Arrays.asList(f8011f).contains(toUpperCase);
        if (VERSION.SDK_INT == 10 && !Arrays.asList(f8012g).contains(toUpperCase)) {
            Arrays.asList(f8013h).contains(toUpperCase2);
        }
        Arrays.asList(f8018m).contains(obj);
        f8008c = Arrays.asList(f8015j).contains(toUpperCase);
        f8009d = Arrays.asList(f8016k).contains(toUpperCase);
        Arrays.asList(f8017l).contains(toUpperCase);
        Arrays.asList(f8019n).contains(toUpperCase);
    }

    public static void m11563a(String str) {
        f8020o = str;
    }

    public static void m11564a(boolean z) {
        f8021p = z;
    }

    public static boolean m11565a() {
        return f8021p;
    }
}
