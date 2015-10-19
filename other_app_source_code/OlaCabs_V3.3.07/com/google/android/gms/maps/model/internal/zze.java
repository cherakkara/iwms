package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class zze implements SafeParcelable {
    public static final C0569c CREATOR;
    private final int f2843a;
    private zza f2844b;

    static {
        CREATOR = new C0569c();
    }

    public zze() {
        this.f2843a = 1;
    }

    zze(int i, zza com_google_android_gms_maps_model_internal_zza) {
        this.f2843a = i;
        this.f2844b = com_google_android_gms_maps_model_internal_zza;
    }

    int m4587a() {
        return this.f2843a;
    }

    public zza m4588b() {
        return this.f2844b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0569c.m4571a(this, parcel, i);
    }
}
