package com.android.volley;

import java.util.Collections;
import java.util.Map;
import org.apache.http.HttpStatus;

/* renamed from: com.android.volley.i */
public class NetworkResponse {
    public final int f497a;
    public final byte[] f498b;
    public final Map<String, String> f499c;
    public final boolean f500d;
    public final long f501e;

    public NetworkResponse(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        this.f497a = i;
        this.f498b = bArr;
        this.f499c = map;
        this.f500d = z;
        this.f501e = j;
    }

    public NetworkResponse(byte[] bArr) {
        this(HttpStatus.SC_OK, bArr, Collections.emptyMap(), false, 0);
    }

    public NetworkResponse(byte[] bArr, Map<String, String> map) {
        this(HttpStatus.SC_OK, bArr, map, false, 0);
    }
}
