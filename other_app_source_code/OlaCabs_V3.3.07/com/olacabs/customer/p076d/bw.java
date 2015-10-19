package com.olacabs.customer.p076d;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.utils.CheckMate;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.p083a.ApplicationMode;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* compiled from: OlaGsonRequest */
/* renamed from: com.olacabs.customer.d.bw */
public class bw<T> extends Request<T> {
    public static final int DEFAULT_TIMEOUT_MS = 5000;
    private static final String LOGTAG;
    private static String X_CLIENT_ID_DELIMITER;
    protected OlaApp mApp;
    protected Gson mGson;
    protected Class<T> mKlass;
    private int mMethod;
    private Map<String, String> mParams;
    private Request.Request mPriority;
    private Response<T> mRespListner;
    private URL mUrl;

    /* renamed from: com.olacabs.customer.d.bw.1 */
    class OlaGsonRequest implements Runnable {
        final /* synthetic */ bw f9440a;

        OlaGsonRequest(bw bwVar) {
            this.f9440a = bwVar;
        }

        public void run() {
            new ForceLogoutCommand(true, true, false).m13270a(this.f9440a.mApp.getApplicationContext());
        }
    }

    static {
        LOGTAG = bw.class.getSimpleName();
        X_CLIENT_ID_DELIMITER = "$$";
    }

    protected bw(OlaApp olaApp, int i, String str, Request.Request request, Map<String, String> map, Response<T> response, Response response2, Class<T> cls) {
        super(i, str, response2);
        this.mGson = new Gson();
        try {
            this.mUrl = new URL(str);
        } catch (Throwable e) {
            OLog.m13314b(e, "Ill-formed URL!", new Object[0]);
        }
        this.mKlass = cls;
        this.mRespListner = response;
        this.mParams = map;
        this.mPriority = request;
        this.mApp = olaApp;
        this.mMethod = i;
    }

    public static bw getInstance(OlaApp olaApp, int i, String str, Request.Request request, Map<String, String> map, Response response, Response response2, Class cls) {
        String urlWithQueryString;
        Map addMandatoryParams = bw.addMandatoryParams(map);
        if (i == 0 || i == 4) {
            urlWithQueryString = bw.getUrlWithQueryString(str, addMandatoryParams);
        } else {
            urlWithQueryString = str;
        }
        return new bw(olaApp, i, urlWithQueryString, request, addMandatoryParams, response, response2, cls);
    }

    public Map<String, String> getParams() throws AuthFailureError {
        return this.mParams != null ? this.mParams : super.getParams();
    }

    public Request.Request getPriority() {
        return this.mPriority;
    }

    public byte[] getBody() throws AuthFailureError {
        Map params = getParams();
        if (params == null || params.size() <= 0) {
            return null;
        }
        return encodeParameters(params, getParamsEncoding());
    }

    public String getBodyContentType() {
        return AbstractSpiCall.ACCEPT_JSON_VALUE;
    }

    private byte[] encodeParameters(Map<String, String> map, String str) {
        JSONObject jSONObject = new JSONObject(map);
        try {
            return (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject)).getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    protected void deliverResponse(T t) {
        this.mRespListner.m585a(t);
    }

    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (!(volleyError == null || volleyError.f464a == null)) {
            int i = volleyError.f464a.f497a;
            if (i == HttpStatus.SC_UNAUTHORIZED || i == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
                byte[] bArr = volleyError.f464a.f498b;
                if (bArr != null) {
                    try {
                        AuthErrorCodesResponse authErrorCodesResponse = (AuthErrorCodesResponse) new Gson().m12343a(new String(bArr), AuthErrorCodesResponse.class);
                        if (authErrorCodesResponse.isValid()) {
                            String code = authErrorCodesResponse.getCode();
                            if (code != null) {
                                if (code.equalsIgnoreCase(Constants.AUTH_TOKEN_UN_AUTHORIZED)) {
                                    OLog.m13317d("Got HTTP 401 for auth", new Object[0]);
                                    synchronized (DataManager.f9351a) {
                                        if (this.mApp.m12878a().m13218d().isAuthTokenAuthorized()) {
                                            OLog.m13317d("Got HTTP 401 for auth and authorized", new Object[0]);
                                            this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(false);
                                            this.mApp.m12878a().m13238h();
                                            this.mApp.m12878a().m13257n();
                                        }
                                    }
                                } else if (code.equalsIgnoreCase(Constants.REFRESH_TOKEN_UN_AUTHORIZED) || code.equalsIgnoreCase(Constants.AUTH_REFRESH_TOKEN_EMPTY) || code.equalsIgnoreCase(Constants.AUTH_TOKEN_NOT_FOUND)) {
                                    OLog.m13317d("Logging out the user because got 422 or 401", new Object[0]);
                                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                                    forceLogoutUser();
                                }
                            }
                        }
                    } catch (Throwable e) {
                        OLog.m13314b(e, e.getMessage(), new Object[0]);
                        this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                    }
                }
            }
        }
        return super.parseNetworkError(volleyError);
    }

    private void forceLogoutUser() {
        new Handler(Looper.getMainLooper()).post(new OlaGsonRequest(this));
    }

    protected com.android.volley.Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse.f497a == HttpStatus.SC_UNAUTHORIZED || networkResponse.f497a == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
            byte[] bArr = networkResponse.f498b;
            if (bArr != null) {
                try {
                    AuthErrorCodesResponse authErrorCodesResponse = (AuthErrorCodesResponse) new Gson().m12343a(new String(bArr), AuthErrorCodesResponse.class);
                    if (authErrorCodesResponse.isValid()) {
                        String code = authErrorCodesResponse.getCode();
                        if (code == null) {
                            return com.android.volley.Response.m586a(new VolleyError(networkResponse));
                        }
                        if (code.equalsIgnoreCase(Constants.AUTH_TOKEN_UN_AUTHORIZED)) {
                            OLog.m13317d("Got HTTP 401 for auth", new Object[0]);
                            synchronized (DataManager.f9351a) {
                                if (this.mApp.m12878a().m13218d().isAuthTokenAuthorized()) {
                                    OLog.m13317d("Got HTTP 401 for auth and authorized", new Object[0]);
                                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(false);
                                    this.mApp.m12878a().m13238h();
                                    this.mApp.m12878a().m13257n();
                                }
                            }
                            return com.android.volley.Response.m586a(new VolleyError("HTTP 401 - Auth Token expired"));
                        } else if (code.equalsIgnoreCase(Constants.REFRESH_TOKEN_UN_AUTHORIZED) || code.equalsIgnoreCase(Constants.AUTH_REFRESH_TOKEN_EMPTY) || code.equalsIgnoreCase(Constants.AUTH_TOKEN_NOT_FOUND)) {
                            OLog.m13317d("Logging out the user because got 422 or 401", new Object[0]);
                            this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                            forceLogoutUser();
                            return com.android.volley.Response.m586a(new VolleyError("HTTP 442 or 401 - Refresh token expired or empty"));
                        }
                    }
                    com.android.volley.Response.m586a(new VolleyError("Un formatted response"));
                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                } catch (Throwable e) {
                    OLog.m13314b(e, e.getMessage(), new Object[0]);
                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                    return com.android.volley.Response.m586a(new VolleyError(e.getMessage()));
                }
            }
        }
        try {
            if (this.mMethod == 4) {
                return com.android.volley.Response.m587a(new bj(networkResponse.f499c), HttpHeaderParser.m646a(networkResponse));
            }
            return com.android.volley.Response.m587a(this.mGson.m12343a(new String(networkResponse.f498b, HttpHeaderParser.m647a(networkResponse.f499c)), this.mKlass), HttpHeaderParser.m646a(networkResponse));
        } catch (Throwable e2) {
            return com.android.volley.Response.m586a(new ParseError(e2));
        } catch (Throwable e22) {
            return com.android.volley.Response.m586a(new ParseError(e22));
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        CharSequence xRequestId = getXRequestId();
        Object authTokenSessionId = getAuthTokenSessionId();
        Map<String, String> standardHeader = ai.getStandardHeader(this.mApp);
        standardHeader.put(da.X_REQUEST_ID, xRequestId);
        if (getAuthTokenEnableStatus() && !TextUtils.isEmpty(authTokenSessionId)) {
            standardHeader.put(HttpHeaders.AUTHORIZATION, "consumerapps " + authTokenSessionId);
        }
        if (AppInfo.sRunningMode == ApplicationMode.QA && TextUtils.isEmpty(xRequestId)) {
            Crashlytics.logException(new Exception("xRequestID is null/empty"));
        }
        return standardHeader;
    }

    private boolean getAuthTokenEnableStatus() {
        return this.mApp.m12878a().m13218d().isAuthEnabled();
    }

    private String getAuthTokenSessionId() {
        return this.mApp.m12878a().m13218d().getAuthSessionId();
    }

    private static String getUrlWithQueryString(String str, Map<String, String> map) {
        if (str == null) {
            return null;
        }
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (Entry entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        return buildUpon.build().toString();
    }

    private static Map<String, String> addMandatoryParams(Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        map.put(AppInfo.APP_VERSION_KEY, AppInfo.sVersionName);
        return map;
    }

    private String getXRequestId() {
        byte[] body;
        ByteArrayOutputStream byteArrayOutputStream;
        String str = null;
        byte[] bytes = this.mUrl.getFile().getBytes();
        if (this.mMethod == 2 || this.mMethod == 1) {
            try {
                body = getBody();
            } catch (AuthFailureError e) {
                OLog.m13318e("Failed to read post/put body", new Object[0]);
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bytes);
            if (body != null && body.length > 0) {
                byteArrayOutputStream.write(body);
            }
            str = CheckMate.m14897a(byteArrayOutputStream.toByteArray());
            return str;
        }
        body = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(body);
            str = CheckMate.m14897a(byteArrayOutputStream.toByteArray());
        } catch (Throwable e2) {
            OLog.m13311a("Failed creating byte array for checksum", new Object[0]);
            if (AppInfo.sRunningMode == ApplicationMode.QA) {
                Crashlytics.logException(e2);
            }
        } catch (Throwable e22) {
            OLog.m13311a("Failed generating checksum", new Object[0]);
            if (AppInfo.sRunningMode == ApplicationMode.QA) {
                Crashlytics.logException(e22);
            }
        }
        return str;
    }
}
