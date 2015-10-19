package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class zza implements SafeParcelable {
    public static final C0567a CREATOR;
    private final int f2836a;
    private byte f2837b;
    private Bundle f2838c;
    private Bitmap f2839d;

    static {
        CREATOR = new C0567a();
    }

    zza(int i, byte b, Bundle bundle, Bitmap bitmap) {
        this.f2836a = i;
        this.f2837b = b;
        this.f2838c = bundle;
        this.f2839d = bitmap;
    }

    public int m4580a() {
        return this.f2836a;
    }

    public byte m4581b() {
        return this.f2837b;
    }

    public Bundle m4582c() {
        return this.f2838c;
    }

    public Bitmap m4583d() {
        return this.f2839d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0567a.m4565a(this, parcel, i);
    }
}
