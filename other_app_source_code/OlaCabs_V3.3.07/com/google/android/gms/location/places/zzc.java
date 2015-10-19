package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class zzc implements SafeParcelable {
    public static final C0544d CREATOR;
    private final int f2681a;
    private final int f2682b;
    private final int f2683c;
    private final PlaceFilter f2684d;

    static {
        CREATOR = new C0544d();
    }

    zzc(int i, int i2, int i3, PlaceFilter placeFilter) {
        this.f2681a = i;
        this.f2682b = i2;
        this.f2683c = i3;
        this.f2684d = placeFilter;
    }

    public int m4418a() {
        return this.f2681a;
    }

    public int m4419b() {
        return this.f2682b;
    }

    public int m4420c() {
        return this.f2683c;
    }

    public PlaceFilter m4421d() {
        return this.f2684d;
    }

    public int describeContents() {
        C0544d c0544d = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc com_google_android_gms_location_places_zzc = (zzc) obj;
        return this.f2682b == com_google_android_gms_location_places_zzc.f2682b && this.f2683c == com_google_android_gms_location_places_zzc.f2683c && this.f2684d.equals(com_google_android_gms_location_places_zzc.f2684d);
    }

    public int hashCode() {
        return C0452t.m3843a(Integer.valueOf(this.f2682b), Integer.valueOf(this.f2683c));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("transitionTypes", Integer.valueOf(this.f2682b)).m3842a("loiteringTimeMillis", Integer.valueOf(this.f2683c)).m3842a("placeFilter", this.f2684d).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0544d c0544d = CREATOR;
        C0544d.m4406a(this, parcel, i);
    }
}
