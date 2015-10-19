package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: UserCallBackResponse */
/* renamed from: com.olacabs.customer.d.dq */
public class dq {
    String header;
    String reason;
    @SerializedName(a = "request_type")
    String requestType;
    String status;
    String text;
    @SerializedName(a = "verification_id")
    String verificationId;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setVerificationId(String str) {
        this.verificationId = str;
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

    public String getVerificationId() {
        return this.verificationId;
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
}
