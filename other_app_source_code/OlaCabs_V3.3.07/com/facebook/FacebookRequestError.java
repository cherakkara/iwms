package com.facebook;

import com.facebook.p022b.FacebookRequestErrorClassification;
import com.facebook.p022b.Utility;
import com.olacabs.customer.utils.Constants;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.i */
public final class FacebookRequestError {
    static final FacebookRequestError f915a;
    private final FacebookRequestError f916b;
    private final int f917c;
    private final int f918d;
    private final int f919e;
    private final String f920f;
    private final String f921g;
    private final String f922h;
    private final String f923i;
    private final String f924j;
    private final JSONObject f925k;
    private final JSONObject f926l;
    private final Object f927m;
    private final HttpURLConnection f928n;
    private final FacebookException f929o;

    /* renamed from: com.facebook.i.a */
    public enum FacebookRequestError {
        LOGIN_RECOVERABLE,
        OTHER,
        TRANSIENT
    }

    /* renamed from: com.facebook.i.b */
    private static class FacebookRequestError {
        private final int f913a;
        private final int f914b;

        private FacebookRequestError(int i, int i2) {
            this.f913a = i;
            this.f914b = i2;
        }

        boolean m1187a(int i) {
            return this.f913a <= i && i <= this.f914b;
        }
    }

    static {
        f915a = new FacebookRequestError(299, null);
    }

    private FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, boolean z, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        this.f917c = i;
        this.f918d = i2;
        this.f919e = i3;
        this.f920f = str;
        this.f921g = str2;
        this.f926l = jSONObject;
        this.f925k = jSONObject2;
        this.f927m = obj;
        this.f928n = httpURLConnection;
        this.f922h = str3;
        this.f923i = str4;
        Object obj2 = null;
        if (facebookException != null) {
            this.f929o = facebookException;
            obj2 = 1;
        } else {
            this.f929o = new FacebookServiceException(this, str2);
        }
        FacebookRequestErrorClassification g = FacebookRequestError.m1189g();
        this.f916b = obj2 != null ? FacebookRequestError.OTHER : g.m950a(i2, i3, z);
        this.f924j = g.m951a(this.f916b);
    }

    FacebookRequestError(HttpURLConnection httpURLConnection, Exception exception) {
        FacebookException facebookException;
        if (exception instanceof FacebookException) {
            facebookException = (FacebookException) exception;
        } else {
            facebookException = new FacebookException((Throwable) exception);
        }
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, facebookException);
    }

    public FacebookRequestError(int i, String str, String str2) {
        this(-1, i, -1, str, str2, null, null, false, null, null, null, null, null);
    }

    public int m1190a() {
        return this.f917c;
    }

    public int m1191b() {
        return this.f918d;
    }

    public String m1192c() {
        return this.f920f;
    }

    public String m1193d() {
        if (this.f921g != null) {
            return this.f921g;
        }
        return this.f929o.getLocalizedMessage();
    }

    public JSONObject m1194e() {
        return this.f925k;
    }

    public FacebookException m1195f() {
        return this.f929o;
    }

    public String toString() {
        return "{HttpStatus: " + this.f917c + ", errorCode: " + this.f918d + ", errorType: " + this.f920f + ", errorMessage: " + m1193d() + "}";
    }

    static FacebookRequestError m1188a(JSONObject jSONObject, Object obj, HttpURLConnection httpURLConnection) {
        try {
            if (jSONObject.has("code")) {
                JSONObject jSONObject2;
                int i = jSONObject.getInt("code");
                Object a = Utility.m1095a(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
                if (a != null && (a instanceof JSONObject)) {
                    jSONObject2 = (JSONObject) a;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    boolean z = false;
                    int i2 = -1;
                    int i3 = -1;
                    Object obj2 = null;
                    if (jSONObject2.has("error")) {
                        JSONObject jSONObject3 = (JSONObject) Utility.m1095a(jSONObject2, "error", null);
                        str = jSONObject3.optString(Constants.BUNDLE_TYPE, null);
                        str2 = jSONObject3.optString("message", null);
                        i2 = jSONObject3.optInt("code", -1);
                        i3 = jSONObject3.optInt("error_subcode", -1);
                        str3 = jSONObject3.optString("error_user_msg", null);
                        str4 = jSONObject3.optString("error_user_title", null);
                        z = jSONObject3.optBoolean("is_transient", false);
                        obj2 = 1;
                    } else if (jSONObject2.has("error_code") || jSONObject2.has("error_msg") || jSONObject2.has("error_reason")) {
                        str = jSONObject2.optString("error_reason", null);
                        str2 = jSONObject2.optString("error_msg", null);
                        i2 = jSONObject2.optInt("error_code", -1);
                        i3 = jSONObject2.optInt("error_subcode", -1);
                        obj2 = 1;
                    }
                    if (obj2 != null) {
                        return new FacebookRequestError(i, i2, i3, str, str2, str4, str3, z, jSONObject2, jSONObject, obj, httpURLConnection, null);
                    }
                }
                if (!f915a.m1187a(i)) {
                    if (jSONObject.has("body")) {
                        jSONObject2 = (JSONObject) Utility.m1095a(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
                    } else {
                        jSONObject2 = null;
                    }
                    return new FacebookRequestError(i, -1, -1, null, null, null, null, false, jSONObject2, jSONObject, obj, httpURLConnection, null);
                }
            }
        } catch (JSONException e) {
        }
        return null;
    }

    static synchronized FacebookRequestErrorClassification m1189g() {
        FacebookRequestErrorClassification a;
        synchronized (FacebookRequestError.class) {
            Utility.Utility d = Utility.m1140d(FacebookSdk.m1210h());
            if (d == null) {
                a = FacebookRequestErrorClassification.m946a();
            } else {
                a = d.m1086c();
            }
        }
        return a;
    }
}
