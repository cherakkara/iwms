package com.olacabs.p067a.p068a.p069a.p070a;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.google.gson.p063a.SerializedName;
import java.util.List;

/* renamed from: com.olacabs.a.a.a.a.n */
public class PinInfo {
    private transient Context mContext;
    @SerializedName(a = "location")
    private LocationInfo mLocationInfo;
    @SerializedName(a = "accuracy")
    private Double sAccuracy;
    private transient LocationManager sLocationManager;

    public PinInfo(Context context) {
        Location lastKnownLocation;
        this.mContext = context;
        this.sLocationManager = (LocationManager) context.getSystemService("location");
        List providers = this.sLocationManager.getProviders(true);
        Location location = null;
        int i = 0;
        while (i < providers.size()) {
            lastKnownLocation = this.sLocationManager.getLastKnownLocation((String) providers.get(i));
            if (lastKnownLocation != null) {
                break;
            }
            i++;
            location = lastKnownLocation;
        }
        lastKnownLocation = location;
        if (lastKnownLocation != null) {
            this.mLocationInfo = new LocationInfo(lastKnownLocation);
            this.sAccuracy = Double.valueOf((double) lastKnownLocation.getAccuracy());
        }
    }
}
