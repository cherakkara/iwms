package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: UpdateProfileDetailsResponse */
/* renamed from: com.olacabs.customer.d.dp */
public class dp {
    String city;
    @SerializedName(a = "credit_gained")
    String creditGained;
    String header;
    String mobile;
    String name;
    @SerializedName(a = "ola_money_balance")
    float olaMoneyBalance;
    String reason;
    @SerializedName(a = "referralCode")
    String referralCode;
    @SerializedName(a = "request_type")
    String requestType;
    boolean resend;
    String status;
    String text;
    String type;
    @SerializedName(a = "user_id")
    String userId;
    @SerializedName(a = "verification_flow")
    boolean verificationFlow;
    @SerializedName(a = "verification_id")
    String verificationId;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setResend(boolean z) {
        this.resend = z;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setCreditGained(String str) {
        this.creditGained = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getReason() {
        return this.reason;
    }

    public String getText() {
        return this.text;
    }

    public String getHeader() {
        return this.header;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getType() {
        return this.type;
    }

    public String getCity() {
        return this.city;
    }

    public String getCreditGained() {
        return this.creditGained;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public boolean isResend() {
        return this.resend;
    }

    public boolean isVerificationFlow() {
        return this.verificationFlow;
    }

    public String getVerificationId() {
        return this.verificationId;
    }

    public String getReferralCode() {
        return this.referralCode;
    }

    public int getOlaMoneyBalance() {
        return Math.round(this.olaMoneyBalance);
    }
}
