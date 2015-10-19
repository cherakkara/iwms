package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import com.olacabs.customer.utils.Constants;
import java.util.Arrays;

public class StreetViewPanoramaLocation implements C0591c {
    public static final StreetViewPanoramaLocationCreator CREATOR;
    public final StreetViewPanoramaLink[] f7613a;
    public final LatLng f7614b;
    public final String f7615c;
    private final int f7616d;

    static {
        CREATOR = new StreetViewPanoramaLocationCreator();
    }

    StreetViewPanoramaLocation(int i, StreetViewPanoramaLink[] streetViewPanoramaLinkArr, LatLng latLng, String str) {
        this.f7616d = i;
        this.f7613a = streetViewPanoramaLinkArr;
        this.f7614b = latLng;
        this.f7615c = str;
    }

    public StreetViewPanoramaLocation(StreetViewPanoramaLink[] streetViewPanoramaLinkArr, LatLng latLng, String str) {
        this(1, streetViewPanoramaLinkArr, latLng, str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        StreetViewPanoramaLocationCreator.m11039a(this, parcel, i);
    }

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7614b, this.f7615c});
    }

    final int m10794a() {
        return this.f7616d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
        if (this.f7615c.equals(streetViewPanoramaLocation.f7615c) && this.f7614b.equals(streetViewPanoramaLocation.f7614b)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return Objects.m10456a(this).m10455a("panoId", this.f7615c).m10455a(Constants.EXTRA_POSITION, this.f7614b.toString()).toString();
    }
}
