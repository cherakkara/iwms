package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: PasswordResetResponse */
/* renamed from: com.olacabs.customer.d.ca */
public class ca {
    String email;
    String message;
    String reason;
    @SerializedName(a = "request_type")
    String requestType;
    String status;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
