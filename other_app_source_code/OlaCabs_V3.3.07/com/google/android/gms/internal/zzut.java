package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzut implements SafeParcelable {
    public static final Creator<zzut> CREATOR;
    final int f2600a;

    static {
        CREATOR = new bj();
    }

    public zzut() {
        this(1);
    }

    zzut(int i) {
        this.f2600a = i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        bj.m4075a(this, parcel, i);
    }
}
