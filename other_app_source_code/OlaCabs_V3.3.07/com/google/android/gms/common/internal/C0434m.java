package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;

/* renamed from: com.google.android.gms.common.internal.m */
public abstract class C0434m {
    private static final Object f2285a;
    private static C0434m f2286b;

    static {
        f2285a = new Object();
    }

    public static C0434m m3652a(Context context) {
        synchronized (f2285a) {
            if (f2286b == null) {
                f2286b = new C0438n(context.getApplicationContext());
            }
        }
        return f2286b;
    }

    public abstract boolean m3653a(String str, ServiceConnection serviceConnection, String str2);

    public abstract void m3654b(String str, ServiceConnection serviceConnection, String str2);
}
