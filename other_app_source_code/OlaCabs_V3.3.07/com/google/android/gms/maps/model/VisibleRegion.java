package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;

public final class VisibleRegion implements SafeParcelable {
    public static final aa CREATOR;
    public final LatLng f2828a;
    public final LatLng f2829b;
    public final LatLng f2830c;
    public final LatLng f2831d;
    public final LatLngBounds f2832e;
    private final int f2833f;

    static {
        CREATOR = new aa();
    }

    VisibleRegion(int i, LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this.f2833f = i;
        this.f2828a = latLng;
        this.f2829b = latLng2;
        this.f2830c = latLng3;
        this.f2831d = latLng4;
        this.f2832e = latLngBounds;
    }

    int m4544a() {
        return this.f2833f;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        return this.f2828a.equals(visibleRegion.f2828a) && this.f2829b.equals(visibleRegion.f2829b) && this.f2830c.equals(visibleRegion.f2830c) && this.f2831d.equals(visibleRegion.f2831d) && this.f2832e.equals(visibleRegion.f2832e);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2828a, this.f2829b, this.f2830c, this.f2831d, this.f2832e);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("nearLeft", this.f2828a).m3842a("nearRight", this.f2829b).m3842a("farLeft", this.f2830c).m3842a("farRight", this.f2831d).m3842a("latLngBounds", this.f2832e).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            ab.m4549a(this, parcel, i);
        } else {
            aa.m4546a(this, parcel, i);
        }
    }
}
