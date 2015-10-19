package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;
import java.util.List;

/* renamed from: com.olacabs.customer.d.k */
public class AuthSessionTokens implements dw {
    @SerializedName(a = "access_token")
    private String accessToken;
    @SerializedName(a = "auth_configs")
    private List<Object> authConfigs;
    @SerializedName(a = "session")
    private AuthTokenSession authTokenSession;

    public String getAccessToken() {
        return this.accessToken;
    }

    public List<Object> getAuthConfigs() {
        return this.authConfigs;
    }

    public AuthTokenSession getAuthTokenSession() {
        return this.authTokenSession;
    }

    public boolean isValid() {
        return Utils.m14924g(this.accessToken);
    }
}
