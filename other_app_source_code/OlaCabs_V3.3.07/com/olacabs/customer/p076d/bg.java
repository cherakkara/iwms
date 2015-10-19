package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: Geometry */
/* renamed from: com.olacabs.customer.d.bg */
public class bg {
    @SerializedName(a = "location")
    private bh location;
    @SerializedName(a = "location_type")
    private String location_type;

    public String getLocation_type() {
        return this.location_type;
    }

    public void setLocation_type(String str) {
        this.location_type = str;
    }

    public bh getLocation() {
        return this.location;
    }

    public void setLocation(bh bhVar) {
        this.location = bhVar;
    }
}
