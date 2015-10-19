package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: RideDetailsResponse */
/* renamed from: com.olacabs.customer.d.cq */
public class cq {
    @SerializedName(a = "request_type")
    private String requestType;
    @SerializedName(a = "ride")
    private cp rideDetails;
    private String status;

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

    public cp getRideDetails() {
        return this.rideDetails;
    }

    public void setRideDetails(cp cpVar) {
        this.rideDetails = cpVar;
    }
}
