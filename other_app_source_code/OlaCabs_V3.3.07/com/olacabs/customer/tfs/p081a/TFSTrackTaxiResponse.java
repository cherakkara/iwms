package com.olacabs.customer.tfs.p081a;

/* renamed from: com.olacabs.customer.tfs.a.i */
public class TFSTrackTaxiResponse {
    private int booking_fare;
    private String booking_id;
    private int booking_status;
    private String carType;
    private float distance;
    private int eta;
    private String latitude;
    private String longitude;
    private String pickup_area_latitude;
    private String pickup_area_longitude;
    private String request_type;
    private String service_status;
    private String state;
    private String status;
    private String status_message;

    public String getBooking_id() {
        return this.booking_id;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequest_type() {
        return this.request_type;
    }

    public int getBooking_fare() {
        return this.booking_fare;
    }

    public String getPickup_area_longitude() {
        return this.pickup_area_longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public String getStatus_message() {
        return this.status_message;
    }

    public String getPickup_area_latitude() {
        return this.pickup_area_latitude;
    }

    public int getBooking_status() {
        return this.booking_status;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public int getEta() {
        return this.eta;
    }

    public float getDistance() {
        return this.distance;
    }

    public String getCarType() {
        return this.carType;
    }

    public String getState() {
        return this.state;
    }

    public String getServiceStatus() {
        return this.service_status;
    }
}
