package com.olacabs.p067a.p068a.p069a.p071b;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* renamed from: com.olacabs.a.a.a.b.c */
public class MegatronRequest extends Request {
    private static final String f8908a;
    private Response f8909b;
    private String f8910c;

    static {
        f8908a = MegatronRequest.class.getSimpleName();
    }

    public MegatronRequest(int i, String str, String str2, Response response, Response response2) {
        super(i, str, response2);
        this.f8909b = response;
        this.f8910c = str2;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(HTTP.CONTENT_ENCODING, "gzip");
        hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip");
        hashMap.put("X-Tenant-Key", "ec1136f4-1a3f-11e5-b60b-1697f925ec7b");
        String b = MegatronCore.m12842e().m12851b();
        if (!(b == null || b.isEmpty())) {
            hashMap.put(HttpHeaders.AUTHORIZATION, String.format("consumerapps %s", new Object[]{b}));
        }
        hashMap.put(Constants.X_SESSION_ID, MegatronCore.m12837a());
        return hashMap;
    }

    public String getBodyContentType() {
        return AbstractSpiCall.ACCEPT_JSON_VALUE;
    }

    public static ByteArrayOutputStream m12822a(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes(HTTP.UTF_8));
        gZIPOutputStream.close();
        return byteArrayOutputStream;
    }

    public byte[] getBody() throws AuthFailureError {
        try {
            return MegatronRequest.m12822a(this.f8910c).toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return super.getBody();
        }
    }

    protected com.android.volley.Response parseNetworkResponse(NetworkResponse networkResponse) {
        int i = networkResponse.f497a;
        if (i >= HttpStatus.SC_BAD_REQUEST && i < HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            MegatronCore.m12842e().m12853c();
            return com.android.volley.Response.m586a(new VolleyError("HTTP 4xx"));
        } else if (i == HttpStatus.SC_CREATED) {
            return com.android.volley.Response.m587a(new JSONArray(), HttpHeaderParser.m646a(networkResponse));
        } else {
            try {
                return com.android.volley.Response.m587a(new JSONArray(), HttpHeaderParser.m646a(networkResponse));
            } catch (Throwable e) {
                return com.android.volley.Response.m586a(new ParseError(e));
            }
        }
    }

    protected void deliverResponse(Object obj) {
        this.f8909b.m585a(obj);
    }

    public int compareTo(Object obj) {
        return 0;
    }
}
