package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: RideEstimateResponse */
/* renamed from: com.olacabs.customer.d.cs */
public class cs {
    String header;
    @SerializedName(a = "surcharge_applicable")
    private boolean isSurchargeApplicable;
    @SerializedName(a = "request_type")
    String requestType;
    @SerializedName(a = "estimates")
    cr rideEstimate;
    String status;
    @SerializedName(a = "display_text")
    private String surchargeText;
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

    public String getHeader() {
        return this.header;
    }

    public String getText() {
        return this.text;
    }

    public cr getRideEstimate() {
        return this.rideEstimate;
    }

    public boolean isSurchargeApplicable() {
        return this.isSurchargeApplicable;
    }

    public String getSurchargeText() {
        return this.surchargeText;
    }
}
