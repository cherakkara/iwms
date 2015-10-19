package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class zzp implements SafeParcelable {
    public static final C0573e CREATOR;
    private final int f2845a;
    private zza f2846b;

    static {
        CREATOR = new C0573e();
    }

    public zzp() {
        this.f2845a = 1;
    }

    zzp(int i, zza com_google_android_gms_maps_model_internal_zza) {
        this.f2845a = i;
        this.f2846b = com_google_android_gms_maps_model_internal_zza;
    }

    int m4589a() {
        return this.f2845a;
    }

    public zza m4590b() {
        return this.f2846b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0573e.m4577a(this, parcel, i);
    }
}
