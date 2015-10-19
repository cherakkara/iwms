package com.google.android.m4b.maps.p058v;

import android.content.res.Resources;
import android.os.Build.VERSION;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p059w.AndroidBuilds;
import com.google.android.m4b.maps.p062z.CapabilitiesController;

/* renamed from: com.google.android.m4b.maps.v.b */
public final class GmmSettings {
    private static final GmmSettings f7992a;

    static {
        f7992a = new GmmSettings();
    }

    private GmmSettings() {
    }

    public static GmmSettings m11527a() {
        return f7992a;
    }

    public static boolean m11529b() {
        return false;
    }

    public static boolean m11528a(Resources resources) {
        return VERSION.SDK_INT >= 11 ? false : resources.getBoolean(R.is_tablet);
    }

    public static String m11530c() {
        return "http://clients4.google.com/glm/mmap/api";
    }

    public final boolean m11536d() {
        if (AndroidBuilds.m11562b()) {
            return true;
        }
        return CapabilitiesController.m12017a(Config.m11320a().m11338l());
    }

    public static boolean m11531e() {
        return false;
    }

    public static boolean m11532f() {
        return ServerControlledParametersManager.m4794a().m4745a();
    }

    public static boolean m11533g() {
        return (ParameterManager.m6641a() == null || ParameterManager.m6641a().m6629e()) ? false : true;
    }

    public static void m11534h() {
    }

    public static boolean m11535i() {
        return false;
    }
}
