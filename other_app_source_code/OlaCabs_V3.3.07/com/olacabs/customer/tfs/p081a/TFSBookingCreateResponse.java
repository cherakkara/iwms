package com.olacabs.customer.tfs.p081a;

/* renamed from: com.olacabs.customer.tfs.a.b */
public class TFSBookingCreateResponse {
    private boolean booking_confirmed;
    private String booking_id;
    private String request_type;
    private String status;
    private String text;
    private boolean wait;

    public String getText() {
        return this.text;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequest_type() {
        return this.request_type;
    }

    public String getBooking_id() {
        return this.booking_id;
    }

    public boolean isBookingConfirmed() {
        return this.booking_confirmed;
    }

    public boolean isWait() {
        return this.wait;
    }
}
