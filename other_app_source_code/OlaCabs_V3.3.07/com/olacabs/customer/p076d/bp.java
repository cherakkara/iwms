package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: LoginResponse */
/* renamed from: com.olacabs.customer.d.bp */
public class bp {
    boolean activated;
    @SerializedName(a = "session")
    private AuthTokenSession authTokenSession;
    String city;
    String header;
    String name;
    @SerializedName(a = "ola_money_balance")
    private float olaMoneyBalance;
    String phone;
    String reason;
    @SerializedName(a = "referralCode")
    String referralCode;
    @SerializedName(a = "request_type")
    String requestType;
    @SerializedName(a = "state_id")
    int stateId;
    String status;
    String text;
    @SerializedName(a = "user_id")
    String userId;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getReferralCode() {
        return this.referralCode;
    }

    public void setReferralCode(String str) {
        this.referralCode = str;
    }

    public boolean isActivated() {
        return this.activated;
    }

    public void setActivated(boolean z) {
        this.activated = z;
    }

    public int getStateId() {
        return this.stateId;
    }

    public void setStateId(int i) {
        this.stateId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public int getOlaMoneyBalance() {
        return Math.round(this.olaMoneyBalance);
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public AuthTokenSession getAuthTokenExpireSession() {
        return this.authTokenSession;
    }
}
