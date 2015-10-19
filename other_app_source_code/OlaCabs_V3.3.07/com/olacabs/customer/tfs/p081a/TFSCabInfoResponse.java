package com.olacabs.customer.tfs.p081a;

import java.util.List;

/* renamed from: com.olacabs.customer.tfs.a.c */
public class TFSCabInfoResponse {
    private boolean cab_availability;
    private String city;
    private int duration;
    private List<NearestCabs> nearest_cabs;
    private String request_type;
    private String status;

    public String getCity() {
        return this.city;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequest_type() {
        return this.request_type;
    }

    public boolean isCabAvailable() {
        return this.cab_availability;
    }

    public int getDuration() {
        return this.duration;
    }

    public List<NearestCabs> getNearestCabs() {
        return this.nearest_cabs;
    }
}
