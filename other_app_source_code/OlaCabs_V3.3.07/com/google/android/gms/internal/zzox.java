package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzox implements SafeParcelable {
    public static final ad CREATOR;
    public final int f2492a;
    public final String f2493b;
    private final int f2494c;

    static {
        CREATOR = new ad();
    }

    zzox(int i, int i2, String str) {
        this.f2494c = i;
        this.f2492a = i2;
        this.f2493b = str;
    }

    int m4251a() {
        return this.f2494c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzox)) {
            return false;
        }
        zzox com_google_android_gms_internal_zzox = (zzox) obj;
        return com_google_android_gms_internal_zzox.f2492a == this.f2492a && C0452t.m3845a(com_google_android_gms_internal_zzox.f2493b, this.f2493b);
    }

    public int hashCode() {
        return this.f2492a;
    }

    public String toString() {
        return String.format("%d:%s", new Object[]{Integer.valueOf(this.f2492a), this.f2493b});
    }

    public void writeToParcel(Parcel parcel, int i) {
        ad.m3909a(this, parcel, i);
    }
}
