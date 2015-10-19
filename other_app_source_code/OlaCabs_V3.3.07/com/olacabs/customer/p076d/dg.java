package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: TrackBooking */
/* renamed from: com.olacabs.customer.d.dg */
public class dg {
    @SerializedName(a = "alloted_cab_info")
    private AllottedCabInfo allottedCabInfo;
    private String booking_id;
    private String crn;
    private String custom_code;
    private double pickup_lat;
    private double pickup_lng;
    private String share_ride_text;
    private String share_ride_url;

    public String getCrn() {
        return this.crn;
    }

    public String getShare_ride_url() {
        return this.share_ride_url;
    }

    public String getBookingId() {
        return this.booking_id;
    }

    public double getPickup_lat() {
        return this.pickup_lat;
    }

    public double getPickup_lng() {
        return this.pickup_lng;
    }

    public String getCouponCode() {
        return this.custom_code;
    }

    public String getShareRideText() {
        return this.share_ride_text;
    }

    public AllottedCabInfo getAllottedCabInfo() {
        return this.allottedCabInfo;
    }
}
