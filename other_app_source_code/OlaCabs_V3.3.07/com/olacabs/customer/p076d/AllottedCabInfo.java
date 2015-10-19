package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.customer.d.d */
public class AllottedCabInfo {
    private static final String JSON_ARRAY_TAG = "";
    private static final String JSON_OBJECT_TAG = "AllotedCabInfo";
    public static final String TAG;
    @SerializedName(a = "car_model")
    private String carModel;
    private String category_id;
    private String color;
    private ap distance;
    @SerializedName(a = "driver_image_url")
    private String driverImageUrl;
    @SerializedName(a = "driver_mobile")
    private String driverMobile;
    @SerializedName(a = "driver_name")
    private String driverName;
    private as duration;
    private String id;
    @SerializedName(a = "image_name")
    private String image_name;
    private double lat;
    private String license_number;
    private double lng;
    @SerializedName(a = "pickup_address")
    private String pickupAddress;

    static {
        TAG = AllottedCabInfo.class.getSimpleName();
    }

    public String getCategory_id() {
        return this.category_id;
    }

    public String getId() {
        return this.id;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public String getLicense_number() {
        return this.license_number;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public String getColor() {
        return this.color;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public String getDriverMobile() {
        return this.driverMobile;
    }

    public String getPickupAddress() {
        return this.pickupAddress;
    }

    public as getDuration() {
        return this.duration;
    }

    public ap getDistance() {
        return this.distance;
    }

    public String getJsonObjectTag() {
        return getJsonObjectTag();
    }

    public String getJsonArrayTag() {
        return getJsonArrayTag();
    }

    public String getDriverImageUrl() {
        return this.driverImageUrl;
    }

    public String getImage_name() {
        return this.image_name;
    }
}
