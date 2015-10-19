package com.olacabs.customer.p076d;

/* compiled from: PickUp */
/* renamed from: com.olacabs.customer.d.cf */
public class cf {
    private static final String JSON_ARRAY_TAG = "";
    private static final String JSON_OBJECT_TAG = "pickup";
    public static final String TAG;
    private String address;
    private String fav_name;
    private double lat;
    private double lng;
    private long time;

    static {
        TAG = cf.class.getSimpleName();
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public long getTime() {
        return this.time;
    }

    public String getAddress() {
        return this.address;
    }

    public String getFav_name() {
        return this.fav_name;
    }

    public String getJsonObjectTag() {
        return null;
    }

    public String getJsonArrayTag() {
        return null;
    }
}
