package com.olacabs.customer.app;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.olacabs.customer.p076d.AuthRefreshResponse;
import com.olacabs.customer.p076d.AuthSession;
import com.olacabs.customer.p076d.AuthSessionResponse;
import com.olacabs.customer.p076d.AuthSessionTokens;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.utils.Constants;
import java.lang.ref.WeakReference;
import java.util.UUID;

/* renamed from: com.olacabs.customer.app.j */
public class HandleAppUpdateCommand {
    private static final String f9382a;
    private DataManager f9383b;
    private aj f9384c;
    private aj f9385d;

    /* renamed from: com.olacabs.customer.app.j.1 */
    class HandleAppUpdateCommand implements aj {
        final /* synthetic */ HandleAppUpdateCommand f9380a;

        HandleAppUpdateCommand(HandleAppUpdateCommand handleAppUpdateCommand) {
            this.f9380a = handleAppUpdateCommand;
        }

        public void onFailure(Throwable th) {
            OLog.m13317d("Failed to fetch Auth token", th);
        }

        public void onSuccess(Object obj) {
            AuthSessionResponse authSessionResponse = (AuthSessionResponse) obj;
            if (authSessionResponse == null || !authSessionResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13317d("Filed to fetch Auth token", new Object[0]);
                return;
            }
            AuthSessionTokens authSessionToken = authSessionResponse.getAuthSessionToken();
            if (authSessionToken == null || authSessionToken.getAuthTokenSession() == null || authSessionToken.getAuthTokenSession().getAuthToken() == null) {
                OLog.m13317d("Auth token is null", new Object[0]);
                return;
            }
            da d = this.f9380a.f9383b.m13218d();
            d.setTokenAuthorizedStatus(true);
            d.setAuthSessionId(authSessionToken.getAuthTokenSession().getAuthToken());
            d.setAuthRefreshToken(authSessionToken.getAuthTokenSession().getRefreshToken());
            d.setAuthSessionExpiry(authSessionToken.getAuthTokenSession().getExpiryFromNow() + System.currentTimeMillis());
            this.f9380a.f9383b.m13175a(null, HandleAppUpdateCommand.f9382a);
        }
    }

    /* renamed from: com.olacabs.customer.app.j.2 */
    class HandleAppUpdateCommand implements aj {
        final /* synthetic */ HandleAppUpdateCommand f9381a;

        HandleAppUpdateCommand(HandleAppUpdateCommand handleAppUpdateCommand) {
            this.f9381a = handleAppUpdateCommand;
        }

        public void onFailure(Throwable th) {
            OLog.m13317d("Failed to fetch Refresh token", th);
        }

        public void onSuccess(Object obj) {
            AuthRefreshResponse authRefreshResponse = (AuthRefreshResponse) obj;
            da d = this.f9381a.f9383b.m13218d();
            if (authRefreshResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                d.setTokenAuthorizedStatus(true);
                AuthSession authSession = authRefreshResponse.getAuthSession();
                OLog.m13313b("Refreshed Auth Session token" + authSession.getOlaAuthToken(), new Object[0]);
                d.setAuthSessionId(authSession.getOlaAuthToken());
                d.setAuthSessionExpiry(authSession.getAccessTokenExpiryFromNow() + System.currentTimeMillis());
                this.f9381a.f9383b.m13175a(null, HandleAppUpdateCommand.f9382a);
                return;
            }
            OLog.m13317d("Failed to fetch Refresh token", new Object[0]);
        }
    }

    public HandleAppUpdateCommand() {
        this.f9384c = new HandleAppUpdateCommand(this);
        this.f9385d = new HandleAppUpdateCommand(this);
    }

    static {
        f9382a = HandleAppUpdateCommand.class.getSimpleName();
    }

    public void m13273a(Context context) {
        OLog.m13311a("HandleAppUpdateCommand execute", new Object[0]);
        this.f9383b = ((OlaApp) context.getApplicationContext()).m12878a();
        da d = this.f9383b.m13218d();
        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(da.NEW_INSTALL_IDENTIFIER_KEY, true)) {
            d.markIsNewInstall(true);
            d.setInstallationId(UUID.randomUUID().toString());
        } else {
            this.f9383b.m13246j();
        }
        if (TextUtils.isEmpty(d.getAuthSessionId())) {
            OLog.m13313b("Initialising auth token in " + f9382a, new Object[0]);
            this.f9383b.m13256m(new WeakReference(this.f9384c), Constants.AUTH_API_TAG);
        } else if (d.getAuthSessionExpiry() < System.currentTimeMillis()) {
            OLog.m13313b("Refreshing auth token in HUC", new Object[0]);
            this.f9383b.m13258n(new WeakReference(this.f9385d), Constants.REFRESH_API_TAG);
        } else {
            this.f9383b.m13175a(null, f9382a);
        }
    }
}
