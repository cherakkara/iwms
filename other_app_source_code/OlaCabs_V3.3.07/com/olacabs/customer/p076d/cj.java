package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: PromoCodeVerificationResponse */
/* renamed from: com.olacabs.customer.d.cj */
public class cj {
    String header;
    String reason;
    @SerializedName(a = "request_type")
    String requestType;
    String status;
    String text;

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

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
