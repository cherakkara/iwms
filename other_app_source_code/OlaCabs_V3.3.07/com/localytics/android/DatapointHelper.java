package com.localytics.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.newrelic.agent.android.api.common.WanType;
import com.olacabs.customer.utils.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.apache.http.protocol.HTTP;

final class DatapointHelper {
    private static final Object[] HARDWARE_TELEPHONY;
    private static final String INVALID_ANDROID_ID = "9774d56d682e549c";
    private static final String LEGACY_DEVICE_ID_FILE = "/localytics/device_id";
    private static final Class<?>[] STRING_CLASS_ARRAY;

    static class AdvertisingInfo {
        public String id;
        public boolean limitAdTracking;

        public AdvertisingInfo(String str, boolean z) {
            this.id = str;
            this.limitAdTracking = z;
        }
    }

    static {
        STRING_CLASS_ARRAY = new Class[]{String.class};
        HARDWARE_TELEPHONY = new Object[]{"android.hardware.telephony"};
    }

    private DatapointHelper() {
        throw new UnsupportedOperationException("This class is non-instantiable");
    }

    static int getApiLevel() {
        try {
            return Integer.parseInt((String) VERSION.class.getField("SDK").get(null));
        } catch (Throwable e) {
            Log.m12802w("Caught exception", e);
            try {
                return VERSION.class.getField("SDK_INT").getInt(null);
            } catch (Throwable e2) {
                Log.m12802w("Caught exception", e2);
                return 3;
            }
        }
    }

    static String getAndroidIdHashOrNull(Context context) {
        String androidIdOrNull = getAndroidIdOrNull(context);
        return androidIdOrNull == null ? null : getSha256_buggy(androidIdOrNull);
    }

    static String getAndroidIdOrNull(Context context) {
        BufferedReader bufferedReader;
        Throwable e;
        String string;
        File file = new File(context.getFilesDir() + LEGACY_DEVICE_ID_FILE);
        if (file.exists() && file.length() > 0) {
            char[] cArr;
            try {
                cArr = new char[100];
                bufferedReader = new BufferedReader(new FileReader(file), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            } catch (FileNotFoundException e2) {
                e = e2;
                bufferedReader = null;
                try {
                    Log.m12802w("Caught exception", e);
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    string = Secure.getString(context.getContentResolver(), "android_id");
                    if (string != null) {
                    }
                    return null;
                } catch (Throwable th) {
                    e = th;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw e;
            }
            try {
                string = String.copyValueOf(cArr, 0, bufferedReader.read(cArr));
                bufferedReader.close();
                if (bufferedReader == null) {
                    return string;
                }
                try {
                    bufferedReader.close();
                    return string;
                } catch (Throwable e3) {
                    Log.m12802w("Caught exception", e3);
                }
            } catch (FileNotFoundException e4) {
                e3 = e4;
                Log.m12802w("Caught exception", e3);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                string = Secure.getString(context.getContentResolver(), "android_id");
                if (string != null) {
                }
                return null;
            }
        }
        string = Secure.getString(context.getContentResolver(), "android_id");
        if (string != null || string.toLowerCase().equals(INVALID_ANDROID_ID)) {
            return null;
        }
        return string;
    }

    static String getSha256_buggy(String str) {
        try {
            return new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(str.getBytes(HTTP.UTF_8))).toString(16);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    static AdvertisingInfo getAdvertisingInfo(Context context) {
        try {
            Object tryInvokeStatic = ReflectionUtils.tryInvokeStatic("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[]{Context.class}, new Object[]{context});
            if (tryInvokeStatic != null) {
                String str = (String) ReflectionUtils.tryInvokeInstance(tryInvokeStatic, "getId", null, null);
                boolean booleanValue = ((Boolean) ReflectionUtils.tryInvokeInstance(tryInvokeStatic, "isLimitAdTrackingEnabled", null, null)).booleanValue();
                if (TextUtils.isEmpty(str)) {
                    str = null;
                }
                return new AdvertisingInfo(str, booleanValue);
            }
        } catch (Exception e) {
            Log.m12801w("Device doesn't have Google Play Services installed");
        }
        return null;
    }

    static String getSerialNumberHashOrNull() {
        if (Constants.CURRENT_API_LEVEL >= 9) {
            try {
                String str = (String) Build.class.getField("SERIAL").get(null);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        str = null;
        if (str == null) {
            return null;
        }
        return getSha256_buggy(str);
    }

    static String getNetworkType(TelephonyManager telephonyManager, Context context) {
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) == 0) {
                NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    return WanType.WIFI;
                }
            }
            Log.m12801w("Application does not have one more more of the following permissions: ACCESS_WIFI_STATE. Determining Wi-Fi connectivity is unavailable");
        } catch (Throwable e) {
            Log.m12802w("Application does not have the permission ACCESS_NETWORK_STATE. Determining Wi-Fi connectivity is unavailable", e);
        } catch (Throwable e2) {
            Log.m12802w("NullPointerException in getNetworkType()", e2);
        }
        return "android_network_type_" + telephonyManager.getNetworkType();
    }

    static String getManufacturer() {
        String str = WanType.UNKNOWN;
        if (Constants.CURRENT_API_LEVEL > 3) {
            try {
                return (String) Build.class.getField("MANUFACTURER").get(null);
            } catch (Throwable e) {
                Log.m12802w("Caught exception", e);
            }
        }
        return str;
    }

    static String getFBAttribution(Context context) {
        Throwable e;
        String str = "aid";
        Cursor query;
        try {
            String string;
            query = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndex("aid"));
                        if (query != null) {
                            return string;
                        }
                        query.close();
                        return string;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.m12802w("Error reading FB attribution", e);
                        if (query != null) {
                            return null;
                        }
                        query.close();
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        if (query != null) {
                            query.close();
                        }
                        throw e;
                    }
                }
            }
            string = null;
            if (query != null) {
                return string;
            }
            query.close();
            return string;
        } catch (Exception e3) {
            e = e3;
            query = null;
            Log.m12802w("Error reading FB attribution", e);
            if (query != null) {
                return null;
            }
            query.close();
            return null;
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    static String getAppVersion(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str != null) {
                return str;
            }
            Log.m12801w("versionName was null--is a versionName attribute set in the Android Manifest?");
            return WanType.UNKNOWN;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static String getTelephonyDeviceIdOrNull(Context context) {
        if (Constants.CURRENT_API_LEVEL < 7 || ((Boolean) ReflectionUtils.tryInvokeInstance(context.getPackageManager(), "hasSystemFeature", STRING_CLASS_ARRAY, HARDWARE_TELEPHONY)).booleanValue()) {
            String deviceId;
            if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
                deviceId = ((TelephonyManager) context.getSystemService(Constants.PHONE)).getDeviceId();
            } else {
                Log.m12801w("Application does not have permission READ_PHONE_STATE; determining device id is not possible.  Please consider requesting READ_PHONE_STATE in the AndroidManifest");
                deviceId = null;
            }
            return deviceId;
        }
        Log.m12797i("Device does not have telephony; cannot read telephony id");
        return null;
    }

    static String getLocalyticsAppKeyOrNull(Context context) {
        if (context == null) {
            Log.m12801w("Context passed to getLocalyticsAppKeyOrNull() is NULL. Please pass a valid context.");
            return null;
        }
        try {
            Context applicationContext = context.getApplicationContext();
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get("LOCALYTICS_APP_KEY");
                if (obj instanceof String) {
                    return (String) obj;
                }
            }
            return null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static int getLocalyticsNotificationIcon(Context context) {
        try {
            int identifier;
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo.metaData != null) {
                String str = (String) applicationInfo.metaData.get("LOCALYTICS_NOTIFICATION_ICON");
                if (str != null) {
                    identifier = context.getResources().getIdentifier(str.substring(str.lastIndexOf(47) + 1, str.lastIndexOf(46)), "drawable", context.getPackageName());
                    if (isValidResourceId(context, identifier)) {
                        return identifier;
                    }
                }
            }
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            if (applicationInfo.icon != 0) {
                identifier = applicationInfo.icon;
                if (isValidResourceId(context, identifier)) {
                    return identifier;
                }
            }
        } catch (NameNotFoundException e) {
        }
        return 17301651;
    }

    static boolean isValidResourceId(Context context, int i) {
        try {
            context.getResources().getResourceName(i);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    static String getLocalyticsRollupKeyOrNull(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get("LOCALYTICS_ROLLUP_KEY");
                if (obj instanceof String) {
                    return (String) obj;
                }
            }
            return null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
