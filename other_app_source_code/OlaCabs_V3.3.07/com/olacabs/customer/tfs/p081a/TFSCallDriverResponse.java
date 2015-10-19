package com.olacabs.customer.tfs.p081a;

/* renamed from: com.olacabs.customer.tfs.a.d */
public class TFSCallDriverResponse {
    public String booking_type;
    public String driver_name;
    public String driver_number;
    public String pickup_area;
    public String pickup_city;
    public String pickup_date;
    public String pickup_landmark;
    public double pickup_latitude;
    public double pickup_longitude;
    public String pickup_time;
    public String request_type;
    public String status;
    public String status_message;
    public String vehicle_number;

    public String getStatus_message() {
        return this.status_message;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequest_type() {
        return this.request_type;
    }

    public String getPickup_time() {
        return this.pickup_time;
    }

    public double getPickup_latitude() {
        return this.pickup_latitude;
    }

    public String getPickup_area() {
        return this.pickup_area;
    }

    public String getPickup_landmark() {
        return this.pickup_landmark;
    }

    public String getPickup_city() {
        return this.pickup_city;
    }

    public String getVehicle_number() {
        return this.vehicle_number;
    }

    public String getPickup_date() {
        return this.pickup_date;
    }

    public String getDriver_number() {
        return this.driver_number;
    }

    public double getPickup_longitude() {
        return this.pickup_longitude;
    }

    public String getBooking_type() {
        return this.booking_type;
    }

    public String getDriver_name() {
        return this.driver_name;
    }
}
