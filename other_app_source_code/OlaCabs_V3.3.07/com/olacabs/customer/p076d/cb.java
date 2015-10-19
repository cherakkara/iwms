package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: PayUTransactionResponse */
/* renamed from: com.olacabs.customer.d.cb */
public class cb {
    private String amount;
    private String desc;
    @SerializedName(a = "error")
    private String errorCode;
    private String header;
    @SerializedName(a = "order_id")
    private String orderId;
    private String status;
    private String text;

    public String getAmount() {
        return this.amount;
    }

    public String getText() {
        return this.text;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getStatus() {
        return this.status;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getHeader() {
        return this.header;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
