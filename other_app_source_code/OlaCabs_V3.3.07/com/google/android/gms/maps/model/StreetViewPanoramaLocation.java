package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.olacabs.customer.utils.Constants;

public class StreetViewPanoramaLocation implements SafeParcelable {
    public static final C0585u CREATOR;
    public final StreetViewPanoramaLink[] f2806a;
    public final LatLng f2807b;
    public final String f2808c;
    private final int f2809d;

    static {
        CREATOR = new C0585u();
    }

    StreetViewPanoramaLocation(int i, StreetViewPanoramaLink[] streetViewPanoramaLinkArr, LatLng latLng, String str) {
        this.f2809d = i;
        this.f2806a = streetViewPanoramaLinkArr;
        this.f2807b = latLng;
        this.f2808c = str;
    }

    int m4532a() {
        return this.f2809d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
        return this.f2808c.equals(streetViewPanoramaLocation.f2808c) && this.f2807b.equals(streetViewPanoramaLocation.f2807b);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2807b, this.f2808c);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("panoId", this.f2808c).m3842a(Constants.EXTRA_POSITION, this.f2807b.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0585u.m4614a(this, parcel, i);
    }
}
