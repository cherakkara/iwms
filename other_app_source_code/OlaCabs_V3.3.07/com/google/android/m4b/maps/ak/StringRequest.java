package com.google.android.m4b.maps.ak;

import com.google.android.m4b.maps.p038a.NetworkResponse;
import com.google.android.m4b.maps.p038a.Request;
import com.google.android.m4b.maps.p038a.Response.Response;
import java.io.UnsupportedEncodingException;

/* renamed from: com.google.android.m4b.maps.ak.i */
public final class StringRequest extends Request<String> {
    private final Response<String> f3272a;

    private StringRequest(int i, String str, Response<String> response, Response response2) {
        super(0, str, response2);
        this.f3272a = response;
    }

    public StringRequest(String str, Response<String> response, Response response2) {
        this(0, str, response, response2);
    }

    protected final com.google.android.m4b.maps.p038a.Response<String> m5253a(NetworkResponse networkResponse) {
        Object str;
        try {
            str = new String(networkResponse.f2894a, HttpHeaderParser.m5241a(networkResponse.f2895b));
        } catch (UnsupportedEncodingException e) {
            str = new String(networkResponse.f2894a);
        }
        return com.google.android.m4b.maps.p038a.Response.m4728a(str, HttpHeaderParser.m5240a(networkResponse));
    }
}
