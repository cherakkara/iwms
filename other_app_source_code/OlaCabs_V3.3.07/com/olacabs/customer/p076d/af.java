package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: CurrentSurchargeResponse */
/* renamed from: com.olacabs.customer.d.af */
public class af {
    private static final String SURCHARGE_MULTIPLIER = "multiplier";
    @SerializedName(a = "header")
    private String header;
    @SerializedName(a = "peak_surcharge_applicable")
    private boolean peakSurchargeApplicable;
    @SerializedName(a = "request_type")
    private String requestType;
    private String status;
    @SerializedName(a = "surcharge_agree_text")
    private String surchargeAgreeText;
    @SerializedName(a = "surcharge_amount")
    private String surchargeAmount;
    @SerializedName(a = "surcharge_disagree_text")
    private String surchargeDisagreeText;
    @SerializedName(a = "text")
    private String text;
    private String type;

    public boolean isPeakSurchargeApplicable() {
        return this.peakSurchargeApplicable;
    }

    public String getHeader() {
        return this.header;
    }

    public String getText() {
        return this.text;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getSurchargeDisagreeText() {
        return this.surchargeDisagreeText;
    }

    public String getType() {
        return this.type;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setPeakSurchargeApplicable(boolean z) {
        this.peakSurchargeApplicable = z;
    }

    public void setSurchargeDisagreeText(String str) {
        this.surchargeDisagreeText = str;
    }

    public boolean isSurchargeMultiplier() {
        if (this.type != null) {
            return this.type.toLowerCase().contains(SURCHARGE_MULTIPLIER);
        }
        return false;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getSurchargeAmount() {
        return this.surchargeAmount;
    }

    public void setSurchargeAmount(String str) {
        this.surchargeAmount = str;
    }

    public String getSurchargeAgreeText() {
        return this.surchargeAgreeText;
    }

    public void setSurchargeAgreeText(String str) {
        this.surchargeAgreeText = str;
    }
}
