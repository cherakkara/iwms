package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.customer.d.u */
public class ChangePasswordResponse {
    public String reason;
    @SerializedName(a = "request_type")
    public String requestType;
    public String status;
    public String text;

    public String getText() {
        return this.text;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getReason() {
        return this.reason;
    }
}
