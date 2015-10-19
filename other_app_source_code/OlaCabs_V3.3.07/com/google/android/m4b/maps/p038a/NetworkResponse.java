package com.google.android.m4b.maps.p038a;

import java.util.Map;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.a.h */
public final class NetworkResponse {
    public final byte[] f2894a;
    public final Map<String, String> f2895b;
    public final boolean f2896c;

    public NetworkResponse(int i, byte[] bArr, Map<String, String> map, boolean z) {
        this.f2894a = bArr;
        this.f2895b = map;
        this.f2896c = z;
    }

    public NetworkResponse(byte[] bArr, Map<String, String> map) {
        this(HttpStatus.SC_OK, bArr, map, false);
    }
}
