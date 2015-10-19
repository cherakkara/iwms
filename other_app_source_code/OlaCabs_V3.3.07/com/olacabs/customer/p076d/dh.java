package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: TrackRideResponse */
/* renamed from: com.olacabs.customer.d.dh */
public class dh {
    private dg booking;
    @SerializedName(a = "booking_status")
    private String bookingStatus;
    @SerializedName(a = "cancel_key")
    private String cancelKey;
    @SerializedName(a = "cancel_reason")
    private String cancelReason;
    @SerializedName(a = "driver_cancellation")
    private ar driverCancellation;
    @SerializedName(a = "emergency_number_verified")
    private boolean emergencyNumberVerified;
    @SerializedName(a = "enable_retry")
    private boolean enableRetry;
    private String header;
    @SerializedName(a = "force_logout")
    boolean isForceLogout;
    @SerializedName(a = "next_call_after")
    private Integer nextCallAfter;
    private String reason;
    @SerializedName(a = "recharge_screen")
    private boolean rechargeScreen;
    @SerializedName(a = "recharge_text")
    private String rechargeText;
    @SerializedName(a = "state_id")
    private int stateId;
    private String status;
    private String text;
    private String title;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
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

    public boolean isRechargeScreen() {
        return this.rechargeScreen;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getNextCallAfter() {
        return this.nextCallAfter;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public boolean isEmergencyNumberVerified() {
        return this.emergencyNumberVerified;
    }

    public int getStateId() {
        return this.stateId;
    }

    public String getRechargeText() {
        return this.rechargeText;
    }

    public dg getBooking() {
        return this.booking;
    }

    public ar getDriverCancellation() {
        return this.driverCancellation;
    }

    public String getCancelKey() {
        return this.cancelKey;
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public boolean isEnableRetry() {
        return this.enableRetry;
    }

    public boolean isForceLogout() {
        return this.isForceLogout;
    }

    public void setForceLogout(boolean z) {
        this.isForceLogout = z;
    }
}
