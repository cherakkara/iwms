package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: RechargeCodeVerificationResponse */
/* renamed from: com.olacabs.customer.d.cl */
public class cl {
    @SerializedName(a = "balance_added")
    private String balanceAdded;
    @SerializedName(a = "balance_total")
    private String balanceTotal;
    @SerializedName(a = "code_applied")
    private String codeApplied;
    String header;
    String reason;
    @SerializedName(a = "request_type")
    String requestType;
    String status;
    String text;

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

    public void setCodeApplied(String str) {
        this.codeApplied = str;
    }

    public void setBalanceTotal(String str) {
        this.balanceTotal = str;
    }

    public void setBalanceAdded(String str) {
        this.balanceAdded = str;
    }

    public String getBalanceAdded() {
        return this.balanceAdded;
    }

    public String getBalanceTotal() {
        return this.balanceTotal;
    }

    public String getCodeApplied() {
        return this.codeApplied;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
