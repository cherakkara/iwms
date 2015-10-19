package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: Favourite */
/* renamed from: com.olacabs.customer.d.aw */
public class aw {
    private String address;
    private int id;
    private double lat;
    private double lng;
    private String name;
    @SerializedName(a = "type")
    public String type;
    @SerializedName(a = "usage_count")
    private int usageCount;

    public void setType(String str) {
        this.type = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public void setUsageCount(int i) {
        this.usageCount = i;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public int getUsageCount() {
        return this.usageCount;
    }

    public String getAddress() {
        return this.address;
    }

    public String getType() {
        return this.type;
    }
}
