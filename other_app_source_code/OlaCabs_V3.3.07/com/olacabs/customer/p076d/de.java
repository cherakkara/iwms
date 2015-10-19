package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: SignUpResponse */
/* renamed from: com.olacabs.customer.d.de */
public class de {
    String city;
    @SerializedName(a = "credit_gained")
    String creditGained;
    String header;
    String reason;
    @SerializedName(a = "referralCode")
    String referralCode;
    @SerializedName(a = "request_type")
    String requestType;
    String status;
    String text;
    @SerializedName(a = "user_id")
    private String userId;
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

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public boolean verificationFlowNeeded() {
        return this.verificationFlow;
    }

    public void setVerificationFlow(boolean z) {
        this.verificationFlow = z;
    }

    public String getVerificationId() {
        return this.verificationId;
    }

    public void setVerificationId(String str) {
        this.verificationId = str;
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

    public String getCreditGained() {
        return this.creditGained;
    }

    public void setCreditGained(String str) {
        this.creditGained = str;
    }

    public String getReferralCode() {
        return this.referralCode;
    }

    public void setReferralCode(String str) {
        this.referralCode = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
