package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: SosResponse */
/* renamed from: com.olacabs.customer.d.df */
public class df {
    private String header;
    private String reason;
    @SerializedName(a = "request_type")
    private String request_type;
    private String status;
    private String text;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getRequest_type() {
        return this.request_type;
    }

    public void setRequest_type(String str) {
        this.request_type = str;
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
