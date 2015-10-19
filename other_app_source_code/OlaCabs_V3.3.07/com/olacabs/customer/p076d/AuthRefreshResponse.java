package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.customer.d.h */
public class AuthRefreshResponse implements dw {
    @SerializedName(a = "session")
    private AuthSession authSession;
    @SerializedName(a = "request_type")
    private String requestType;
    private String status;

    public String getRequestType() {
        return this.requestType;
    }

    public String getStatus() {
        return this.status;
    }

    public AuthSession getAuthSession() {
        return this.authSession;
    }

    public boolean isValid() {
        return this.authSession != null && this.authSession.isValid();
    }
}
