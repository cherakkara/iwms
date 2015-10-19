package com.olacabs.customer.p076d;

/* compiled from: RideEstimate */
/* renamed from: com.olacabs.customer.d.cr */
public class cr {
    private double approx_travel_time;
    private String car_category;
    private String distance;
    private String fare;

    public String getDistance() {
        return this.distance;
    }

    public String getFare() {
        if (this.fare != null) {
            return this.fare;
        }
        return "0";
    }

    public String getCar_category() {
        return this.car_category;
    }

    public double getApprox_travel_time() {
        return this.approx_travel_time;
    }
}
