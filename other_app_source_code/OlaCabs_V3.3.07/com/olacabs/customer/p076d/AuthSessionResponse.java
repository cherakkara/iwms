package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.d.j */
public class AuthSessionResponse implements dw {
    @SerializedName(a = "session_tokens")
    private AuthSessionTokens authSessionTokens;
    @SerializedName(a = "request_type")
    private String requestType;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public AuthSessionTokens getAuthSessionToken() {
        return this.authSessionTokens;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public boolean isValid() {
        return getAuthSessionToken() != null && Utils.m14924g(getAuthSessionToken().getAccessToken());
    }
}
