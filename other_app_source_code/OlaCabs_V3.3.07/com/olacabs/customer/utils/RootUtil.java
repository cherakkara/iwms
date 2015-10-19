package com.olacabs.customer.utils;

import android.os.Build;
import java.io.File;

/* renamed from: com.olacabs.customer.utils.f */
public class RootUtil {
    public static boolean m14903a() {
        return RootUtil.m14904b() || RootUtil.m14905c() || RootUtil.m14906d();
    }

    private static boolean m14904b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean m14905c() {
        for (String file : new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m14906d() {
        /*
        r0 = 1;
        r1 = 0;
        r2 = 0;
        r3 = java.lang.Runtime.getRuntime();	 Catch:{ Throwable -> 0x0039, all -> 0x0042 }
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x0039, all -> 0x0042 }
        r5 = 0;
        r6 = "/system/xbin/which";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0039, all -> 0x0042 }
        r5 = 1;
        r6 = "su";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0039, all -> 0x0042 }
        r2 = r3.exec(r4);	 Catch:{ Throwable -> 0x0039, all -> 0x0042 }
        r3 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0049, all -> 0x0042 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x0049, all -> 0x0042 }
        r5 = r2.getInputStream();	 Catch:{ Throwable -> 0x0049, all -> 0x0042 }
        r4.<init>(r5);	 Catch:{ Throwable -> 0x0049, all -> 0x0042 }
        r3.<init>(r4);	 Catch:{ Throwable -> 0x0049, all -> 0x0042 }
        r3 = r3.readLine();	 Catch:{ Throwable -> 0x0049, all -> 0x0042 }
        if (r3 == 0) goto L_0x0032;
    L_0x002c:
        if (r2 == 0) goto L_0x0031;
    L_0x002e:
        r2.destroy();
    L_0x0031:
        return r0;
    L_0x0032:
        if (r2 == 0) goto L_0x0037;
    L_0x0034:
        r2.destroy();
    L_0x0037:
        r0 = r1;
        goto L_0x0031;
    L_0x0039:
        r0 = move-exception;
        r0 = r2;
    L_0x003b:
        if (r0 == 0) goto L_0x0040;
    L_0x003d:
        r0.destroy();
    L_0x0040:
        r0 = r1;
        goto L_0x0031;
    L_0x0042:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0048;
    L_0x0045:
        r2.destroy();
    L_0x0048:
        throw r0;
    L_0x0049:
        r0 = move-exception;
        r0 = r2;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.utils.f.d():boolean");
    }
}
