package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.d.l */
public class AuthTokenSession implements dw {
    @SerializedName(a = "created_at")
    private long createdAt;
    @SerializedName(a = "expiry_from_now")
    private long expiryFromNow;
    @SerializedName(a = "expiry_time")
    private long expiryTime;
    @SerializedName(a = "id")
    private String oAuthToken;
    @SerializedName(a = "refresh_token")
    private String refreshToken;
    @SerializedName(a = "updated_at")
    private long updatedAt;

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public long getExpiryTime() {
        return this.expiryTime;
    }

    public String getAuthToken() {
        return this.oAuthToken;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public long getUpdatedAt() {
        return this.updatedAt;
    }

    public long getExpiryFromNow() {
        return this.expiryFromNow;
    }

    public boolean isValid() {
        return Utils.m14924g(getRefreshToken()) && Utils.m14924g(getAuthToken()) && 0 != this.expiryFromNow;
    }
}
