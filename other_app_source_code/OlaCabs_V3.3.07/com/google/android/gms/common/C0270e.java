package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.R.R;
import com.google.android.gms.common.C0398h.C0373r;
import com.google.android.gms.common.internal.C0418g;
import com.google.android.gms.common.internal.C0420i;
import com.google.android.gms.common.internal.C0439o;
import com.google.android.gms.internal.C0516u;
import com.google.android.gms.internal.C0519x;
import com.olacabs.customer.utils.Constants;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.gms.common.e */
public final class C0270e {
    public static boolean f2126a;
    public static boolean f2127b;
    private static int f2128c;
    private static final Object f2129d;

    static {
        f2126a = false;
        f2127b = false;
        f2128c = -1;
        f2129d = new Object();
    }

    private C0270e() {
    }

    public static int m3379a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (!C0418g.f2234a) {
            try {
                context.getResources().getString(R.common_google_play_services_unknown_issue);
            } catch (Throwable th) {
                Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
            }
        }
        if (!C0418g.f2234a) {
            C0270e.m3398c(context);
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            C0399i a = C0399i.m3510a();
            if (!C0516u.m4168c(packageInfo.versionCode) && !C0516u.m4166a(context)) {
                try {
                    if (a.m3512a(packageManager.getPackageInfo("com.android.vending", 64), C0373r.f2177a) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (a.m3512a(packageInfo, a.m3512a(packageManager.getPackageInfo("com.android.vending", 64), C0373r.f2177a)) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (NameNotFoundException e) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                    return 9;
                }
            } else if (a.m3512a(packageInfo, C0373r.f2177a) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (C0516u.m4165a(packageInfo.versionCode) < C0516u.m4165a(7095000)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 7095000 but found " + packageInfo.versionCode);
                return 2;
            }
            try {
                return !packageManager.getApplicationInfo("com.google.android.gms", 0).enabled ? 3 : 0;
            } catch (NameNotFoundException e2) {
                Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
                e2.printStackTrace();
                return 1;
            }
        } catch (NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    public static Dialog m3380a(int i, Activity activity, int i2) {
        return C0270e.m3381a(i, activity, i2, null);
    }

    public static Dialog m3381a(int i, Activity activity, int i2, OnCancelListener onCancelListener) {
        return C0270e.m3391b(i, activity, null, i2, onCancelListener);
    }

    public static Intent m3382a(int i) {
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return C0439o.m3680b("com.google.android.gms");
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return C0439o.m3679a("com.google.android.gms");
            case 42:
                return C0439o.m3678a();
            default:
                return null;
        }
    }

    public static boolean m3383a() {
        return f2126a ? f2127b : "user".equals(Build.TYPE);
    }

    public static boolean m3384a(int i, Activity activity, Fragment fragment, int i2, OnCancelListener onCancelListener) {
        boolean z = false;
        Dialog b = C0270e.m3391b(i, activity, fragment, i2, onCancelListener);
        if (b == null) {
            return z;
        }
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
        }
        if (z) {
            C0271f.m3400a(b, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), "GooglePlayServicesErrorDialog");
        } else if (C0519x.m4171a()) {
            C0262b.m3362a(b, onCancelListener).show(activity.getFragmentManager(), "GooglePlayServicesErrorDialog");
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
        return true;
    }

    public static boolean m3385a(Context context, int i) {
        return C0270e.m3386a(context, i, "com.google.android.gms") && C0270e.m3389a(context.getPackageManager(), "com.google.android.gms");
    }

    public static boolean m3386a(Context context, int i, String str) {
        if (C0519x.m4175d()) {
            try {
                ((AppOpsManager) context.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        }
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (str == null || packagesForUid == null) {
            return false;
        }
        for (Object equals : packagesForUid) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    private static boolean m3387a(Context context, String str) {
        if (C0519x.m4176e()) {
            for (SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (str.equals(appPackageName.getAppPackageName())) {
                    return true;
                }
            }
        }
        try {
            if (context.getPackageManager().getApplicationInfo(str, AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD).enabled) {
                return true;
            }
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    public static boolean m3388a(PackageManager packageManager) {
        synchronized (f2129d) {
            if (f2128c == -1) {
                try {
                    if (C0399i.m3510a().m3512a(packageManager.getPackageInfo("com.google.android.gms", 64), C0398h.f2187b[1]) != null) {
                        f2128c = 1;
                    } else {
                        f2128c = 0;
                    }
                } catch (NameNotFoundException e) {
                    f2128c = 0;
                }
            }
        }
        return f2128c != 0;
    }

    @Deprecated
    public static boolean m3389a(PackageManager packageManager, String str) {
        return C0399i.m3510a().m3514a(packageManager, str);
    }

    public static boolean m3390a(Resources resources) {
        if (resources == null) {
            return false;
        }
        return (C0519x.m4171a() && ((resources.getConfiguration().screenLayout & 15) > 3)) || C0270e.m3396b(resources);
    }

    private static Dialog m3391b(int i, Activity activity, Fragment fragment, int i2, OnCancelListener onCancelListener) {
        Builder builder;
        Intent a;
        OnClickListener c0420i;
        CharSequence c;
        if (C0516u.m4166a((Context) activity) && i == 2) {
            i = 42;
        }
        if (C0519x.m4174c()) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new Builder(activity, 5);
                if (builder == null) {
                    builder = new Builder(activity);
                }
                builder.setMessage(C0270e.m3392b(activity, i));
                if (onCancelListener != null) {
                    builder.setOnCancelListener(onCancelListener);
                }
                a = C0270e.m3382a(i);
                c0420i = fragment != null ? new C0420i(activity, a, i2) : new C0420i(fragment, a, i2);
                c = C0270e.m3397c(activity, i);
                if (c != null) {
                    builder.setPositiveButton(c, c0420i);
                }
                switch (i) {
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                        return null;
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        return builder.setTitle(R.common_google_play_services_install_title).create();
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        return builder.setTitle(R.common_google_play_services_update_title).create();
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        return builder.setTitle(R.common_google_play_services_enable_title).create();
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                        return builder.create();
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                        Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                        return builder.setTitle(R.common_google_play_services_invalid_account_title).create();
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                        Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                        return builder.setTitle(R.common_google_play_services_network_error_title).create();
                    case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                        Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                        return builder.create();
                    case HTTP.HT /*9*/:
                        Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                        return builder.setTitle(R.common_google_play_services_unsupported_title).create();
                    case HTTP.LF /*10*/:
                        Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                        return builder.create();
                    case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                        Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                        return builder.create();
                    case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                        Log.e("GooglePlayServicesUtil", "One of the API components you attempted to connect to is not available.");
                        return builder.create();
                    case LangUtils.HASH_SEED /*17*/:
                        Log.e("GooglePlayServicesUtil", "The specified account could not be signed in.");
                        return builder.setTitle(R.common_google_play_services_sign_in_failed_title).create();
                    case 42:
                        return builder.setTitle(R.common_android_wear_update_title).create();
                    default:
                        Log.e("GooglePlayServicesUtil", "Unexpected error code " + i);
                        return builder.create();
                }
            }
        }
        builder = null;
        if (builder == null) {
            builder = new Builder(activity);
        }
        builder.setMessage(C0270e.m3392b(activity, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        a = C0270e.m3382a(i);
        if (fragment != null) {
        }
        c = C0270e.m3397c(activity, i);
        if (c != null) {
            builder.setPositiveButton(c, c0420i);
        }
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return null;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return builder.setTitle(R.common_google_play_services_install_title).create();
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return builder.setTitle(R.common_google_play_services_update_title).create();
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return builder.setTitle(R.common_google_play_services_enable_title).create();
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return builder.create();
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                return builder.setTitle(R.common_google_play_services_invalid_account_title).create();
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                return builder.setTitle(R.common_google_play_services_network_error_title).create();
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                return builder.create();
            case HTTP.HT /*9*/:
                Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                return builder.setTitle(R.common_google_play_services_unsupported_title).create();
            case HTTP.LF /*10*/:
                Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                return builder.create();
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                return builder.create();
            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                Log.e("GooglePlayServicesUtil", "One of the API components you attempted to connect to is not available.");
                return builder.create();
            case LangUtils.HASH_SEED /*17*/:
                Log.e("GooglePlayServicesUtil", "The specified account could not be signed in.");
                return builder.setTitle(R.common_google_play_services_sign_in_failed_title).create();
            case 42:
                return builder.setTitle(R.common_android_wear_update_title).create();
            default:
                Log.e("GooglePlayServicesUtil", "Unexpected error code " + i);
                return builder.create();
        }
    }

    public static String m3392b(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return C0270e.m3390a(context.getResources()) ? resources.getString(R.common_google_play_services_install_text_tablet) : resources.getString(R.common_google_play_services_install_text_phone);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return resources.getString(R.common_google_play_services_update_text);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return resources.getString(R.common_google_play_services_enable_text);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return resources.getString(R.common_google_play_services_invalid_account_text);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return resources.getString(R.common_google_play_services_network_error_text);
            case HTTP.HT /*9*/:
                return resources.getString(R.common_google_play_services_unsupported_text);
            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                return resources.getString(R.commono_google_play_services_api_unavailable_text);
            case LangUtils.HASH_SEED /*17*/:
                return resources.getString(R.common_google_play_services_sign_in_failed_text);
            case 42:
                return resources.getString(R.common_android_wear_update_text);
            default:
                return resources.getString(R.common_google_play_services_unknown_issue);
        }
    }

    public static void m3393b(Context context) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(10436);
        } catch (SecurityException e) {
        }
    }

    public static boolean m3394b(int i) {
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case HTTP.HT /*9*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean m3395b(PackageManager packageManager) {
        return C0270e.m3388a(packageManager) || !C0270e.m3383a();
    }

    private static boolean m3396b(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return C0519x.m4173b() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600;
    }

    public static String m3397c(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return resources.getString(R.common_google_play_services_install_button);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case 42:
                return resources.getString(R.common_google_play_services_update_button);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return resources.getString(R.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    private static void m3398c(Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } catch (Throwable e) {
            Log.wtf("GooglePlayServicesUtil", "This should never happen.", e);
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null) {
            int i = bundle.getInt("com.google.android.gms.version");
            if (i != 7095000) {
                throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 7095000 but found " + i + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
            }
            return;
        }
        throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
    }

    public static boolean m3399d(Context context, int i) {
        return i == 1 ? C0270e.m3387a(context, "com.google.android.gms") : false;
    }
}
