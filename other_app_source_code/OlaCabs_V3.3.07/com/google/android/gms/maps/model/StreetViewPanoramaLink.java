package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaLink implements SafeParcelable {
    public static final C0584t CREATOR;
    public final String f2803a;
    public final float f2804b;
    private final int f2805c;

    static {
        CREATOR = new C0584t();
    }

    StreetViewPanoramaLink(int i, String str, float f) {
        this.f2805c = i;
        this.f2803a = str;
        if (((double) f) <= 0.0d) {
            f = (f % 360.0f) + 360.0f;
        }
        this.f2804b = f % 360.0f;
    }

    int m4531a() {
        return this.f2805c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLink)) {
            return false;
        }
        StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink) obj;
        return this.f2803a.equals(streetViewPanoramaLink.f2803a) && Float.floatToIntBits(this.f2804b) == Float.floatToIntBits(streetViewPanoramaLink.f2804b);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2803a, Float.valueOf(this.f2804b));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("panoId", this.f2803a).m3842a("bearing", Float.valueOf(this.f2804b)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0584t.m4611a(this, parcel, i);
    }
}
