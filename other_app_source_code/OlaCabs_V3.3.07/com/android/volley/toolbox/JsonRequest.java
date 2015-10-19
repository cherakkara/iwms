package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response.Response;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

/* renamed from: com.android.volley.toolbox.k */
public abstract class JsonRequest<T> extends Request<T> {
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE;
    private final Response<T> mListener;
    private final String mRequestBody;

    protected abstract com.android.volley.Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    static {
        PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{PROTOCOL_CHARSET});
    }

    public JsonRequest(String str, String str2, Response<T> response, Response response2) {
        this(-1, str, str2, response, response2);
    }

    public JsonRequest(int i, String str, String str2, Response<T> response, Response response2) {
        super(i, str, response2);
        this.mListener = response;
        this.mRequestBody = str2;
    }

    protected void deliverResponse(T t) {
        this.mListener.m585a(t);
    }

    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    public byte[] getPostBody() {
        return getBody();
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public byte[] getBody() {
        byte[] bArr = null;
        try {
            if (this.mRequestBody != null) {
                bArr = this.mRequestBody.getBytes(PROTOCOL_CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.m596d("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, PROTOCOL_CHARSET);
        }
        return bArr;
    }
}
