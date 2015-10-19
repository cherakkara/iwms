package com.google.android.gms.maps.model.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class zzc implements SafeParcelable {
    public static final C0568b CREATOR;
    private final int f2840a;
    private int f2841b;
    private Bundle f2842c;

    static {
        CREATOR = new C0568b();
    }

    zzc(int i, int i2, Bundle bundle) {
        this.f2840a = i;
        this.f2841b = i2;
        this.f2842c = bundle;
    }

    public int m4584a() {
        return this.f2840a;
    }

    public int m4585b() {
        return this.f2841b;
    }

    public Bundle m4586c() {
        return this.f2842c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0568b.m4568a(this, parcel, i);
    }
}
