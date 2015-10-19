package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzkw implements SafeParcelable {
    public static final C0499e CREATOR;
    final int f2442a;
    public final String f2443b;
    public final int f2444c;

    static {
        CREATOR = new C0499e();
    }

    public zzkw(int i, String str, int i2) {
        this.f2442a = i;
        this.f2443b = str;
        this.f2444c = i2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0499e.m4113a(this, parcel, i);
    }
}
