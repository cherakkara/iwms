package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;

public final class LatLngBounds implements SafeParcelable {
    public static final C0566i CREATOR;
    public final LatLng f2765a;
    public final LatLng f2766b;
    private final int f2767c;

    static {
        CREATOR = new C0566i();
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        C0453u.m3847a((Object) latLng, (Object) "null southwest");
        C0453u.m3847a((Object) latLng2, (Object) "null northeast");
        C0453u.m3856b(latLng2.f2762a >= latLng.f2762a, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.f2762a), Double.valueOf(latLng2.f2762a));
        this.f2767c = i;
        this.f2765a = latLng;
        this.f2766b = latLng2;
    }

    int m4499a() {
        return this.f2767c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.f2765a.equals(latLngBounds.f2765a) && this.f2766b.equals(latLngBounds.f2766b);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2765a, this.f2766b);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("southwest", this.f2765a).m3842a("northeast", this.f2766b).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0574j.m4591a(this, parcel, i);
        } else {
            C0566i.m4562a(this, parcel, i);
        }
    }
}
