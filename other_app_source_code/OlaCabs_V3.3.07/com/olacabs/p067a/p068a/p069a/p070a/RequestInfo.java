package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.p063a.SerializedName;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* renamed from: com.olacabs.a.a.a.a.o */
public class RequestInfo {
    private transient byte[] mBody;
    @SerializedName(a = "paramSize")
    private int mBodyPayloadSize;
    @SerializedName(a = "headers")
    private Map<String, String> mHeaders;
    @SerializedName(a = "type")
    private String mMethodType;
    @SerializedName(a = "params")
    private String mParams;
    @SerializedName(a = "url")
    private String mUrl;

    public void setGetParam(String str) {
        this.mParams = str;
    }

    public void setPostParam(String str) {
        this.mParams = str;
    }

    public byte[] getBody() {
        return this.mBody;
    }

    public void setBody(byte[] bArr) {
        this.mBody = bArr;
        this.mBodyPayloadSize = bArr != null ? bArr.length : 0;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setMethodType(int i) {
        Gson b = new GsonBuilder().m12354a().m12355b();
        switch (i) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                this.mMethodType = "DEPRECATED_GET_OR_POST";
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.mMethodType = "GET";
                try {
                    setGetParam(b.m12346a(getGetParams()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.mMethodType = "POST";
                if (this.mBody != null) {
                    try {
                        setPostParam(new String(this.mBody, HTTP.UTF_8));
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                }
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.mMethodType = "PUT";
                if (this.mBody != null) {
                    try {
                        setPostParam(new String(this.mBody, HTTP.UTF_8));
                    } catch (UnsupportedEncodingException e22) {
                        e22.printStackTrace();
                    }
                }
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.mMethodType = "DELETE";
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                this.mMethodType = "HEAD";
                try {
                    setGetParam(b.m12346a(getGetParams()));
                } catch (UnsupportedEncodingException e222) {
                    e222.printStackTrace();
                }
            default:
        }
    }

    public JSONObject getParamString() {
        String str = Trace.NULL;
        return new JSONObject(getParams());
    }

    public String getMethodType() {
        return this.mMethodType;
    }

    public Map<String, String> getParams() {
        if ("GET".equals(this.mMethodType)) {
            try {
                return getGetParams();
            } catch (UnsupportedEncodingException e) {
            }
        } else {
            if ("POST".equals(this.mMethodType)) {
                return getPostParams(this.mBody.toString());
            }
            return null;
        }
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public void setHeaders(Map<String, String> map) {
        this.mHeaders = map;
    }

    public int getBodyPayloadSize() {
        return this.mBodyPayloadSize;
    }

    public Map<String, String> getGetParams() throws UnsupportedEncodingException {
        Map<String, String> linkedHashMap = new LinkedHashMap();
        try {
            for (String str : new URL(this.mUrl).getQuery().split("&")) {
                int indexOf = str.indexOf("=");
                linkedHashMap.put(URLDecoder.decode(str.substring(0, indexOf), HTTP.UTF_8), URLDecoder.decode(str.substring(indexOf + 1), HTTP.UTF_8));
            }
        } catch (MalformedURLException e) {
        } catch (Throwable th) {
        }
        return linkedHashMap;
    }

    public Map<String, String> getPostParams(String str) {
        Map<String, String> linkedHashMap = new LinkedHashMap();
        try {
            for (String str2 : str.split(",")) {
                int indexOf = str2.indexOf(":");
                if (indexOf != -1) {
                    linkedHashMap.put(URLDecoder.decode(str2.substring(0, indexOf), HTTP.UTF_8), URLDecoder.decode(str2.substring(indexOf + 1), HTTP.UTF_8));
                }
            }
        } catch (UnsupportedEncodingException e) {
        } catch (StringIndexOutOfBoundsException e2) {
            e2.printStackTrace();
        }
        return linkedHashMap;
    }
}
