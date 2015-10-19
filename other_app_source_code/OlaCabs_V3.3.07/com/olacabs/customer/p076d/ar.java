package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: DriverCancellationResponse */
/* renamed from: com.olacabs.customer.d.ar */
public class ar {
    @SerializedName(a = "category_id")
    private String category_id;
    @SerializedName(a = "drop_landmark")
    private String drop_landmark;
    @SerializedName(a = "drop_locality")
    private String drop_locality;
    @SerializedName(a = "locality_id")
    private String locality_id;
    @SerializedName(a = "pickup_landmark")
    private String pickup_landmark;
    @SerializedName(a = "user_lat")
    private Double user_lat;
    @SerializedName(a = "user_lng")
    private Double user_lng;

    public void setCategory_id(String str) {
        this.category_id = str;
    }

    public String getCategoryId() {
        return this.category_id;
    }

    public Double getUserLat() {
        return this.user_lat;
    }

    public Double getUserLng() {
        return this.user_lng;
    }

    public String getDropLocality() {
        return this.drop_locality;
    }

    public String getDropLocalityId() {
        return this.locality_id;
    }

    public String getDropLandmark() {
        return this.drop_landmark;
    }

    public String getPickupLandmark() {
        return this.pickup_landmark;
    }
}
