package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;

public final class LatLng implements SafeParcelable {
    public static final C0575k CREATOR;
    public final double f2762a;
    public final double f2763b;
    private final int f2764c;

    static {
        CREATOR = new C0575k();
    }

    LatLng(int i, double d, double d2) {
        this.f2764c = i;
        if (-180.0d > d2 || d2 >= 180.0d) {
            this.f2763b = ((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
        } else {
            this.f2763b = d2;
        }
        this.f2762a = Math.max(-90.0d, Math.min(90.0d, d));
    }

    int m4498a() {
        return this.f2764c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.doubleToLongBits(this.f2762a) == Double.doubleToLongBits(latLng.f2762a) && Double.doubleToLongBits(this.f2763b) == Double.doubleToLongBits(latLng.f2763b);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f2762a);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f2763b);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "lat/lng: (" + this.f2762a + "," + this.f2763b + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0576l.m4595a(this, parcel, i);
        } else {
            C0575k.m4592a(this, parcel, i);
        }
    }
}
