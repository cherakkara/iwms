package com.google.android.gms.internal;

import android.util.Base64;

/* renamed from: com.google.android.gms.internal.t */
public final class C0515t {
    public static String m4163a(byte[] bArr) {
        return bArr == null ? null : Base64.encodeToString(bArr, 0);
    }

    public static String m4164b(byte[] bArr) {
        return bArr == null ? null : Base64.encodeToString(bArr, 10);
    }
}
