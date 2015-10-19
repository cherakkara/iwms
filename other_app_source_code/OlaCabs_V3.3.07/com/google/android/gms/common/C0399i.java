package com.google.android.gms.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.C0398h.C0272a;
import com.google.android.gms.common.C0398h.ab;

/* renamed from: com.google.android.gms.common.i */
public class C0399i {
    private static final C0399i f2190a;

    static {
        f2190a = new C0399i();
    }

    private C0399i() {
    }

    public static C0399i m3510a() {
        return f2190a;
    }

    private boolean m3511a(PackageInfo packageInfo, boolean z) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return false;
        }
        C0272a abVar = new ab(packageInfo.signatures[0].toByteArray());
        if ((z ? C0398h.m3506a() : C0398h.m3509b()).contains(abVar)) {
            return true;
        }
        if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
            Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(abVar.m3402a(), 0));
        }
        return false;
    }

    C0272a m3512a(PackageInfo packageInfo, C0272a... c0272aArr) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        C0272a abVar = new ab(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < c0272aArr.length; i++) {
            if (c0272aArr[i].equals(abVar)) {
                return c0272aArr[i];
            }
        }
        if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
            Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(abVar.m3402a(), 0));
        }
        return null;
    }

    public boolean m3513a(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (C0270e.m3395b(packageManager)) {
            return m3511a(packageInfo, true);
        }
        boolean a = m3511a(packageInfo, false);
        if (a || !m3511a(packageInfo, true)) {
            return a;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return a;
    }

    public boolean m3514a(PackageManager packageManager, String str) {
        try {
            return m3513a(packageManager, packageManager.getPackageInfo(str, 64));
        } catch (NameNotFoundException e) {
            if (Log.isLoggable("GoogleSignatureVerifier", 3)) {
                Log.d("GoogleSignatureVerifier", "Package manager can't find package " + str + ", defaulting to false");
            }
            return false;
        }
    }
}
