package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.customer.d.m */
public class BasicResponse {
    @SerializedName(a = "booking_id")
    public String bookingId;
    public String reason;
    @SerializedName(a = "request_type")
    public String requestType;
    public String status;
    public String text;

    public String getText() {
        return this.text;
    }

    public String getBookingId() {
        return this.bookingId;
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
