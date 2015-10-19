package com.google.android.gms.internal;

import android.content.Context;

public class aa {
    private final Context f2347a;
    private final String f2348b;
    private final ao<ah> f2349c;
    private final String f2350d;
    private ab f2351e;

    private aa(Context context, String str, String str2, ao<ah> aoVar) {
        this.f2347a = context;
        this.f2348b = str;
        this.f2349c = aoVar;
        this.f2351e = null;
        this.f2350d = str2;
    }

    public static aa m3897a(Context context, String str, String str2, ao<ah> aoVar) {
        return new aa(context, str, str2, aoVar);
    }
}
