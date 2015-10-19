package com.olacabs.customer.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.app.OLog;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;

/* renamed from: com.olacabs.customer.utils.g */
public class Utils {
    private static final String f11510a;

    static {
        f11510a = Utils.class.getSimpleName();
    }

    private Utils() {
    }

    public static void m14907a() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new AssertionError();
        }
    }

    public static boolean m14909a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean m14912b(Context context) {
        boolean z;
        if (((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
            z = true;
        } else {
            int networkType = ((TelephonyManager) context.getSystemService(Constants.PHONE)).getNetworkType();
            if (networkType == 8 || networkType == 10 || networkType == 15 || networkType == 9 || networkType == 13 || networkType == 3) {
                z = true;
            } else {
                z = false;
            }
        }
        OLog.m13311a("Is device on a fast network? - " + z, new Object[0]);
        return z;
    }

    public static String m14915c(Context context) {
        if (((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
            return "fast";
        }
        switch (((TelephonyManager) context.getSystemService(Constants.PHONE)).getNetworkType()) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                return "slow";
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
            case HTTP.HT /*9*/:
            case HTTP.LF /*10*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
            case HTTP.CR /*13*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                return "fast";
            default:
                return Trace.NULL;
        }
    }

    public static boolean m14917d(Context context) {
        boolean z = true;
        try {
            return Secure.getInt(context.getContentResolver(), "location_mode") != 0;
        } catch (SettingNotFoundException e) {
            OLog.m13315c("Failed to read location mode from Secure Settings", new Object[0]);
            CharSequence bestProvider = ((LocationManager) context.getSystemService("location")).getBestProvider(new Criteria(), true);
            OLog.m13311a("isLocationServiceEnabled - current best provider is " + bestProvider, new Object[0]);
            if (TextUtils.isEmpty(bestProvider) || "passive".equals(bestProvider)) {
                z = false;
            }
            return z;
        }
    }

    public static boolean m14910a(String str) {
        return !TextUtils.isEmpty(str) && Patterns.PHONE.matcher(str).matches() && str.length() == 10;
    }

    public static boolean m14913b(String str) {
        return !TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static boolean m14916c(String str) {
        return !TextUtils.isEmpty(str) && str.length() >= 1;
    }

    public static boolean m14918d(String str) {
        return !TextUtils.isEmpty(str) && str.length() >= 1;
    }

    public static boolean m14911a(String str, String str2) {
        return Utils.m14918d(str) && Utils.m14918d(str2) && str.equals(str2);
    }

    public static boolean m14914b(String str, String str2) {
        return Utils.m14918d(str) && Utils.m14918d(str2) && !str.equals(str2);
    }

    public static boolean m14920e(String str) {
        return !TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str);
    }

    public static String m14922f(String str) {
        if (TextUtils.isEmpty(str)) {
            return Trace.NULL;
        }
        return EncryptionScheme.m14899a(Base64.decode(str, 0), "PRODKEYPRODKEY12").split("##")[0];
    }

    public static boolean m14924g(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static void m14908a(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }

    public static List<String> m14919e(Context context) {
        Account[] accounts = AccountManager.get(context).getAccounts();
        List<String> arrayList = new ArrayList();
        for (Account account : accounts) {
            if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches() && !arrayList.contains(account.name)) {
                arrayList.add(account.name);
            }
        }
        return arrayList;
    }

    public static int m14921f(Context context) {
        if (VERSION.SDK_INT >= 19) {
            try {
                return Secure.getInt(context.getContentResolver(), "location_mode");
            } catch (SettingNotFoundException e) {
                e.printStackTrace();
                return 4;
            }
        }
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
        boolean isProviderEnabled2 = locationManager.isProviderEnabled("network");
        OLog.m13311a("isGPSEnabled %s isNetworkEnabled %s", Boolean.valueOf(isProviderEnabled), Boolean.valueOf(isProviderEnabled2));
        if (isProviderEnabled && isProviderEnabled2) {
            return 3;
        }
        if (isProviderEnabled) {
            return 1;
        }
        if (isProviderEnabled2) {
            return 2;
        }
        return 0;
    }

    public static String m14923g(Context context) {
        switch (Utils.m14921f(context)) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return context.getString(com.olacabs.customer.R.string.location_mode_off);
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return context.getString(com.olacabs.customer.R.string.location_mode_sensors_only);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return context.getString(com.olacabs.customer.R.string.location_mode_battery_saving);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return context.getString(com.olacabs.customer.R.string.location_mode_high_accuracy);
            default:
                return context.getString(com.olacabs.customer.R.string.location_unavailable);
        }
    }
}
