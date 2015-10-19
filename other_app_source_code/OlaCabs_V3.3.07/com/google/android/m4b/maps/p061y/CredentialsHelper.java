package com.google.android.m4b.maps.p061y;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.instrumentation.Trace;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.google.android.m4b.maps.y.c */
public final class CredentialsHelper {
    private static final String[] f8068a;
    private static final String f8069b;

    static {
        CredentialsHelper.class.getSimpleName();
        String[] strArr = new String[]{"com.google.android.geo.API_KEY", "com.google.android.maps.v2.API_KEY"};
        f8068a = strArr;
        f8069b = strArr[1];
    }

    public static String m11643a(PackageManager packageManager, String str) {
        String str2 = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length == 0 || packageInfo.signatures[0] == null)) {
                byte[] toByteArray = packageInfo.signatures[0].toByteArray();
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                if (instance != null) {
                    toByteArray = instance.digest(toByteArray);
                    if (toByteArray != null) {
                        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                        StringBuffer stringBuffer = new StringBuffer(toByteArray.length * 2);
                        for (byte b : toByteArray) {
                            stringBuffer.append(cArr[(b >> 4) & 15]);
                            stringBuffer.append(cArr[(b >> 0) & 15]);
                        }
                        str2 = stringBuffer.toString();
                    }
                }
            }
        } catch (NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e2) {
        }
        return str2;
    }

    public static String m11642a(Context context, String str) {
        int i = 0;
        String str2 = null;
        ApplicationInfo b = CredentialsHelper.m11644b(context, str);
        if (b.metaData != null) {
            String[] strArr = f8068a;
            for (int i2 = 0; i2 < 2; i2++) {
                String str3 = strArr[i2];
                if (b.metaData.containsKey(str3)) {
                    str2 = b.metaData.getString(str3);
                    i++;
                }
            }
            if (i > 1) {
                throw new RuntimeException("The API key can only be specified once. It is recommended that you use the meta-data tag with the name: " + f8069b + " in the <application> element of AndroidManifest.xml");
            }
        }
        if (str2 != null) {
            return str2;
        }
        throw new RuntimeException("API key not found.  Check that <meta-data android:name=\"" + f8069b + "\" android:value=\"your API key\"/> is in the <application> element of AndroidManifest.xml");
    }

    private static ApplicationInfo m11644b(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } catch (NameNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    public static PackageInfo m11641a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    public static String m11645b(Context context) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null || packagesForUid.length <= 0) {
            return Trace.NULL;
        }
        return packagesForUid[0];
    }
}
