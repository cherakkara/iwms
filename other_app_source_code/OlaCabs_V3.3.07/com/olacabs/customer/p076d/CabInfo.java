package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.d.r */
public class CabInfo implements dw {
    private String category_id;
    private String id;
    private double lat;
    private double lng;

    public String getId() {
        return this.id;
    }

    public double getLatitude() {
        return this.lat;
    }

    public double getlongitude() {
        return this.lng;
    }

    public String getCategoryId() {
        return this.category_id;
    }

    public boolean isValid() {
        return Utils.m14924g(this.id) && Utils.m14924g(this.category_id);
    }
}
