package com.olacabs.customer.tfs.p081a;

/* renamed from: com.olacabs.customer.tfs.a.g */
public class TFSHasBookingResponse {
    private boolean booking_confirmed;
    private String booking_id;
    private String request_type;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.request_type;
    }

    public String getBookingId() {
        return this.booking_id;
    }

    public boolean isBookingConfirmed() {
        return this.booking_confirmed;
    }
}
