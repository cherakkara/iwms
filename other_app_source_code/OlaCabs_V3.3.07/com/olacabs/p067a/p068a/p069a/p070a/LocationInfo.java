package com.olacabs.p067a.p068a.p069a.p070a;

import android.location.Location;
import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.a.a.a.a.k */
public class LocationInfo {
    @SerializedName(a = "lat")
    private double sLatitude;
    @SerializedName(a = "lon")
    private double sLongitude;

    public LocationInfo(Location location) {
        this.sLatitude = location.getLatitude();
        this.sLongitude = location.getLongitude();
    }
}
