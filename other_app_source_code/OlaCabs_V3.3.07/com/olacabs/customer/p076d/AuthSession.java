package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.d.i */
public class AuthSession implements dw {
    @SerializedName(a = "access_token_expiry_from_now")
    private long accessTokenExpiryFromNow;
    @SerializedName(a = "access_token_expiry")
    private long expiryToken;
    @SerializedName(a = "access_token")
    private String olaAuthToken;

    public String getOlaAuthToken() {
        return this.olaAuthToken;
    }

    public long getExpiryToken() {
        return this.expiryToken;
    }

    public long getAccessTokenExpiryFromNow() {
        return this.accessTokenExpiryFromNow;
    }

    public boolean isValid() {
        return Utils.m14924g(this.olaAuthToken) && this.expiryToken != 0;
    }
}
