package com.olacabs.customer.app;

import android.content.Context;
import android.text.TextUtils;
import com.olacabs.customer.p076d.AuthRefreshResponse;
import com.olacabs.customer.p076d.AuthSession;
import com.olacabs.customer.p076d.AuthSessionResponse;
import com.olacabs.customer.p076d.AuthSessionTokens;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.utils.Constants;
import java.lang.ref.WeakReference;

/* renamed from: com.olacabs.customer.app.b */
public class AuthDataUpdater extends DataUpdater {
    private aj f8994i;
    private aj f8995j;

    /* renamed from: com.olacabs.customer.app.b.1 */
    class AuthDataUpdater implements aj {
        final /* synthetic */ AuthDataUpdater f8983a;

        AuthDataUpdater(AuthDataUpdater authDataUpdater) {
            this.f8983a = authDataUpdater;
        }

        public void onFailure(Throwable th) {
            OLog.m13312a(th, "Refreshing Auth token failed", new Object[0]);
        }

        public void onSuccess(Object obj) {
            OLog.m13311a("Auth.OnSuccess", new Object[0]);
            AuthSessionResponse authSessionResponse = (AuthSessionResponse) obj;
            if (authSessionResponse != null && authSessionResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                AuthSessionTokens authSessionToken = authSessionResponse.getAuthSessionToken();
                if (authSessionToken != null && authSessionToken.getAuthTokenSession() != null && authSessionToken.getAuthTokenSession().isValid()) {
                    this.f8983a.d.setAuthSessionId(authSessionToken.getAuthTokenSession().getAuthToken());
                    this.f8983a.d.setAuthRefreshToken(authSessionToken.getAuthTokenSession().getRefreshToken());
                    this.f8983a.d.setAuthSessionExpiry(authSessionToken.getAuthTokenSession().getExpiryFromNow() + System.currentTimeMillis());
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.b.2 */
    class AuthDataUpdater implements aj {
        final /* synthetic */ AuthDataUpdater f8984a;

        AuthDataUpdater(AuthDataUpdater authDataUpdater) {
            this.f8984a = authDataUpdater;
        }

        public void onFailure(Throwable th) {
            this.f8984a.d.setTokenAuthorizedStatus(true);
        }

        public void onSuccess(Object obj) {
            AuthRefreshResponse authRefreshResponse = (AuthRefreshResponse) obj;
            this.f8984a.d.setTokenAuthorizedStatus(true);
            if (authRefreshResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                AuthSession authSession = authRefreshResponse.getAuthSession();
                OLog.m13313b("Refreshed Auth Session token" + authSession.getOlaAuthToken(), new Object[0]);
                this.f8984a.d.setAuthSessionId(authSession.getOlaAuthToken());
                this.f8984a.d.setAuthSessionExpiry(authSession.getAccessTokenExpiryFromNow() + System.currentTimeMillis());
            }
        }
    }

    public AuthDataUpdater(Context context) {
        super(context);
        this.f8994i = new AuthDataUpdater(this);
        this.f8995j = new AuthDataUpdater(this);
    }

    public void m12887a() {
        OLog.m13311a("AuthDataUpdater called", new Object[0]);
        if (this.d.isAuthEnabled()) {
            m12884b();
        }
    }

    private void m12884b() {
        da d = this.e.m13218d();
        if (d.isPreviouslyLoggedIn()) {
            if (m12885c() || m12886d()) {
                this.e.m13256m(new WeakReference(this.f8994i), Constants.AUTH_API_TAG);
            } else if (d.getAuthSessionExpiry() != 0 && d.getAuthSessionExpiry() - d.getAuthRefreshTokenThreshold() <= System.currentTimeMillis()) {
                OLog.m13313b("Auth token is expired", new Object[0]);
                this.e.m13258n(new WeakReference(this.f8995j), Constants.REFRESH_API_TAG);
            }
        } else if (m12885c() && d.isAuthEnabled()) {
            this.e.m13255m();
        }
    }

    private boolean m12885c() {
        return TextUtils.isEmpty(this.e.m13218d().getAuthSessionId());
    }

    private boolean m12886d() {
        return TextUtils.isEmpty(this.e.m13218d().getAuthRefreshToken());
    }
}
