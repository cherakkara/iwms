package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookServiceException;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.p022b.FacebookDialogFragment;
import com.facebook.p022b.Utility;
import com.facebook.p022b.WebDialog;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Locale;

class WebViewLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<WebViewLoginMethodHandler> CREATOR;
    private WebDialog f988c;
    private String f989d;

    /* renamed from: com.facebook.login.WebViewLoginMethodHandler.1 */
    class C01761 implements WebDialog.WebDialog {
        final /* synthetic */ Request f984a;
        final /* synthetic */ WebViewLoginMethodHandler f985b;

        C01761(WebViewLoginMethodHandler webViewLoginMethodHandler, Request request) {
            this.f985b = webViewLoginMethodHandler;
            this.f984a = request;
        }

        public void m1297a(Bundle bundle, FacebookException facebookException) {
            this.f985b.m1306a(this.f984a, bundle, facebookException);
        }
    }

    /* renamed from: com.facebook.login.WebViewLoginMethodHandler.2 */
    static class C01772 implements Creator {
        C01772() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1298a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1299a(i);
        }

        public WebViewLoginMethodHandler m1298a(Parcel parcel) {
            return new WebViewLoginMethodHandler(parcel);
        }

        public WebViewLoginMethodHandler[] m1299a(int i) {
            return new WebViewLoginMethodHandler[i];
        }
    }

    /* renamed from: com.facebook.login.WebViewLoginMethodHandler.a */
    static class C0178a extends WebDialog.WebDialog {
        private String f986a;
        private boolean f987b;

        public C0178a(Context context, String str, Bundle bundle) {
            super(context, str, "oauth", bundle);
        }

        public C0178a m1301a(String str) {
            this.f986a = str;
            return this;
        }

        public C0178a m1302a(boolean z) {
            this.f987b = z;
            return this;
        }

        public WebDialog m1300a() {
            Bundle e = m1157e();
            e.putString("redirect_uri", "fbconnect://success");
            e.putString("client_id", m1154b());
            e.putString("e2e", this.f986a);
            e.putString("response_type", "token,signed_request");
            e.putString("return_scopes", "true");
            if (this.f987b) {
                e.putString("auth_type", "rerequest");
            }
            return new WebDialog(m1155c(), "oauth", e, m1156d(), m1158f());
        }
    }

    WebViewLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String m1305a() {
        return "web_view";
    }

    boolean m1309c() {
        return true;
    }

    void m1308b() {
        if (this.f988c != null) {
            this.f988c.cancel();
            this.f988c = null;
        }
    }

    boolean m1307a(Request request) {
        String join;
        Bundle bundle = new Bundle();
        if (!Utility.m1127a(request.m1250a())) {
            join = TextUtils.join(",", request.m1250a());
            bundle.putString("scope", join);
            m1228a("scope", join);
        }
        bundle.putString("default_audience", request.m1253c().m1310a());
        AccessToken a = AccessToken.m690a();
        join = a != null ? a.m698b() : null;
        if (join == null || !join.equals(m1304d())) {
            Utility.m1132b(this.b.m1285b());
            m1228a("access_token", "0");
        } else {
            bundle.putString("access_token", join);
            m1228a("access_token", "1");
        }
        WebDialog.WebDialog c01761 = new C01761(this, request);
        this.f989d = LoginClient.m1273l();
        m1228a("e2e", this.f989d);
        Context b = this.b.m1285b();
        this.f988c = new C0178a(b, request.m1254d(), bundle).m1301a(this.f989d).m1302a(request.m1256f()).m1152a(c01761).m1153a();
        FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
        facebookDialogFragment.setRetainInstance(true);
        facebookDialogFragment.m945a(this.f988c);
        facebookDialogFragment.show(b.getSupportFragmentManager(), "FacebookDialogFragment");
        return true;
    }

    void m1306a(Request request, Bundle bundle, FacebookException facebookException) {
        Result a;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                this.f989d = bundle.getString("e2e");
            }
            try {
                AccessToken a2 = LoginMethodHandler.m1223a(request.m1250a(), bundle, AccessTokenSource.WEB_VIEW, request.m1254d());
                a = Result.m1260a(this.b.m1288c(), a2);
                CookieSyncManager.createInstance(this.b.m1285b()).sync();
                m1303b(a2.m698b());
            } catch (FacebookException e) {
                a = Result.m1262a(this.b.m1288c(), null, e.getMessage());
            }
        } else if (facebookException instanceof FacebookOperationCanceledException) {
            a = Result.m1261a(this.b.m1288c(), "User canceled log in.");
        } else {
            String format;
            this.f989d = null;
            String message = facebookException.getMessage();
            if (facebookException instanceof FacebookServiceException) {
                format = String.format(Locale.ROOT, "%d", new Object[]{Integer.valueOf(((FacebookServiceException) facebookException).m1216a().m1191b())});
                message = r0.toString();
            } else {
                format = null;
            }
            a = Result.m1263a(this.b.m1288c(), null, message, format);
        }
        if (!Utility.m1126a(this.f989d)) {
            m1227a(this.f989d);
        }
        this.b.m1281a(a);
    }

    private void m1303b(String str) {
        this.b.m1285b().getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", str).apply();
    }

    private String m1304d() {
        return this.b.m1285b().getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", Trace.NULL);
    }

    WebViewLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.f989d = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f989d);
    }

    static {
        CREATOR = new C01772();
    }
}
