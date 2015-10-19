package com.olacabs.customer.p076d;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request.Request;
import com.android.volley.Response.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.utils.CheckMate;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.customer.utils.p083a.ApplicationMode;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/* compiled from: OlaJsonObjectRequest */
/* renamed from: com.olacabs.customer.d.by */
public class by extends JsonObjectRequest {
    public static final int DEFAULT_TIMEOUT_MS = 5000;
    private static final String LOGTAG;
    public static final int REFRESH_TOKEN_RETRY = 300000;
    private static String X_CLIENT_ID_DELIMITER;
    private OlaApp mApp;
    private int mMethod;
    private Request mPriority;
    private URL mUrl;

    /* renamed from: com.olacabs.customer.d.by.1 */
    class OlaJsonObjectRequest implements Runnable {
        final /* synthetic */ by f9441a;

        OlaJsonObjectRequest(by byVar) {
            this.f9441a = byVar;
        }

        public void run() {
            new ForceLogoutCommand(true, true, false).m13270a(this.f9441a.mApp.getApplicationContext());
        }
    }

    static {
        LOGTAG = by.class.getSimpleName();
        X_CLIENT_ID_DELIMITER = "$$";
    }

    public by(OlaApp olaApp, int i, String str, JSONObject jSONObject, Request request, Response<JSONObject> response, Response response2) {
        this(i, str, jSONObject, response, response2);
        try {
            this.mUrl = new URL(str);
        } catch (MalformedURLException e) {
            OLog.m13318e("Ill-formed URL!", new Object[0]);
        }
        this.mApp = olaApp;
        this.mPriority = request;
        this.mMethod = i;
    }

    public by(int i, String str, JSONObject jSONObject, Response<JSONObject> response, Response response2) {
        super(i, str, jSONObject, response, response2);
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> standardHeader = ai.getStandardHeader(this.mApp);
        String xRequestId = getXRequestId();
        String authTokenSessionId = getAuthTokenSessionId();
        standardHeader.put(da.X_REQUEST_ID, xRequestId);
        if (getAuthTokenEnableStatus() && Utils.m14924g(authTokenSessionId)) {
            standardHeader.put(HttpHeaders.AUTHORIZATION, "consumerapps " + authTokenSessionId);
        }
        return standardHeader;
    }

    private boolean getAuthTokenEnableStatus() {
        return this.mApp.m12878a().m13218d().isAuthEnabled();
    }

    private String getAuthTokenSessionId() {
        return this.mApp.m12878a().m13218d().getAuthSessionId();
    }

    public Request getPriority() {
        return this.mPriority;
    }

    private void forceLogoutUser() {
        new Handler(Looper.getMainLooper()).post(new OlaJsonObjectRequest(this));
    }

    protected VolleyError parseNetworkError(VolleyError volleyError) {
        NetworkResponse networkResponse = volleyError.f464a;
        if (!(volleyError == null || networkResponse == null || (networkResponse.f497a != HttpStatus.SC_UNAUTHORIZED && networkResponse.f497a != HttpStatus.SC_UNPROCESSABLE_ENTITY))) {
            byte[] bArr = networkResponse.f498b;
            if (bArr != null) {
                try {
                    AuthErrorCodesResponse authErrorCodesResponse = (AuthErrorCodesResponse) new Gson().m12343a(new String(bArr), AuthErrorCodesResponse.class);
                    if (!(authErrorCodesResponse == null || authErrorCodesResponse.getCode() == null)) {
                        if (authErrorCodesResponse.getCode().equalsIgnoreCase(Constants.AUTH_TOKEN_UN_AUTHORIZED)) {
                            OLog.m13317d("Got HTTP 401 for auth", new Object[0]);
                            synchronized (DataManager.f9351a) {
                                if (this.mApp.m12878a().m13218d().isAuthTokenAuthorized()) {
                                    OLog.m13317d("Got HTTP 401 for auth and authorized", new Object[0]);
                                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(false);
                                    this.mApp.m12878a().m13238h();
                                    this.mApp.m12878a().m13257n();
                                }
                            }
                        } else if (authErrorCodesResponse.getCode().equalsIgnoreCase(Constants.REFRESH_TOKEN_UN_AUTHORIZED) || authErrorCodesResponse.getCode().equalsIgnoreCase(Constants.AUTH_REFRESH_TOKEN_EMPTY) || authErrorCodesResponse.getCode().equalsIgnoreCase(Constants.AUTH_TOKEN_NOT_FOUND)) {
                            OLog.m13317d("Logging out the user because got 422 or 401", new Object[0]);
                            this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                            forceLogoutUser();
                        }
                    }
                } catch (Throwable e) {
                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                    OLog.m13314b(e, e.getMessage(), new Object[0]);
                }
            }
        }
        return super.parseNetworkError(volleyError);
    }

    protected com.android.volley.Response parseNetworkResponse(NetworkResponse networkResponse) {
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
                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                    com.android.volley.Response.m586a(new VolleyError("Un formatted response"));
                } catch (Throwable e) {
                    OLog.m13314b(e, e.getMessage(), new Object[0]);
                    this.mApp.m12878a().m13218d().setTokenAuthorizedStatus(true);
                    return com.android.volley.Response.m586a(new VolleyError(e.getMessage()));
                }
            }
        }
        try {
            return com.android.volley.Response.m587a(JSONObjectInstrumentation.init(new String(networkResponse.f498b, HttpHeaderParser.m647a(networkResponse.f499c))), HttpHeaderParser.m646a(networkResponse));
        } catch (Throwable e2) {
            return com.android.volley.Response.m586a(new ParseError(e2));
        } catch (Throwable e22) {
            return com.android.volley.Response.m586a(new ParseError(e22));
        } catch (Throwable e222) {
            return com.android.volley.Response.m586a(new ParseError(e222));
        }
    }

    private String getXRequestId() {
        byte[] body;
        String str = null;
        byte[] bytes = this.mUrl.getFile().getBytes();
        if (this.mMethod == 2 || this.mMethod == 1) {
            body = getBody();
        } else {
            body = null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bytes);
            if (body != null && body.length > 0) {
                byteArrayOutputStream.write(body);
            }
            str = CheckMate.m14897a(byteArrayOutputStream.toByteArray());
        } catch (Throwable e) {
            OLog.m13311a("Failed creating byte array for checksum", new Object[0]);
            if (AppInfo.sRunningMode == ApplicationMode.QA) {
                Crashlytics.logException(e);
            }
        } catch (Throwable e2) {
            OLog.m13311a("Failed generating checksum", new Object[0]);
            if (AppInfo.sRunningMode == ApplicationMode.QA) {
                Crashlytics.logException(e2);
            }
        }
        return str;
    }
}
