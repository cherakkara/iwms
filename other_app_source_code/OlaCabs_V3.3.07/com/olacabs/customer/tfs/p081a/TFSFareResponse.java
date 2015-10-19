package com.olacabs.customer.tfs.p081a;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.customer.tfs.a.f */
public class TFSFareResponse {
    TFSFareDetails fares;
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

    public TFSFareDetails getFares() {
        return this.fares;
    }

    public void setFares(TFSFareDetails tFSFareDetails) {
        this.fares = tFSFareDetails;
    }
}
