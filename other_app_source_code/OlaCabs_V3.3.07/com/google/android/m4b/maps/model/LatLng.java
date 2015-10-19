package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;

public final class LatLng implements C0591c {
    public static final LatLngCreator CREATOR;
    public final double f7554a;
    public final double f7555b;
    private final int f7556c;

    static {
        CREATOR = new LatLngCreator();
    }

    LatLng(int i, double d, double d2) {
        this.f7556c = i;
        if (-180.0d > d2 || d2 >= 180.0d) {
            this.f7555b = ((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
        } else {
            this.f7555b = d2;
        }
        this.f7554a = Math.max(-90.0d, Math.min(90.0d, d));
    }

    public LatLng(double d, double d2) {
        this(1, d, d2);
    }

    final int m10737a() {
        return this.f7556c;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            LatLngCreatorCheddar.m11014a(this, parcel);
        } else {
            LatLngCreator.m11011a(this, parcel);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f7554a);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f7555b);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        if (Double.doubleToLongBits(this.f7554a) == Double.doubleToLongBits(latLng.f7554a) && Double.doubleToLongBits(this.f7555b) == Double.doubleToLongBits(latLng.f7555b)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "lat/lng: (" + this.f7554a + "," + this.f7555b + ")";
    }
}
