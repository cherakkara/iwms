package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: TransactionIDResponse */
/* renamed from: com.olacabs.customer.d.di */
public class di {
    private String amount;
    @SerializedName(a = "failure_url")
    private String failureUrl;
    private String header;
    @SerializedName(a = "order_id")
    private String orderId;
    private String reason;
    @SerializedName(a = "request_type")
    private String requestType;
    private String status;
    @SerializedName(a = "success_url")
    private String successUrl;
    private String text;

    public String getAmount() {
        return this.amount;
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

    public String getHeader() {
        return this.header;
    }

    public String getText() {
        return this.text;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getSuccessUrl() {
        return this.successUrl;
    }

    public String getFailureUrl() {
        return this.failureUrl;
    }
}
