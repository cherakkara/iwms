package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzqu extends bb implements SafeParcelable {
    public static final bc CREATOR;
    final int f2585a;
    private final String f2586b;

    static {
        CREATOR = new bc();
    }

    zzqu(int i, String str) {
        this.f2585a = i;
        this.f2586b = str;
    }

    public String m4307a() {
        return this.f2586b;
    }

    public int describeContents() {
        bc bcVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqu)) {
            return false;
        }
        return this.f2586b.equals(((zzqu) obj).f2586b);
    }

    public int hashCode() {
        return this.f2586b.hashCode();
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("testName", this.f2586b).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        bc bcVar = CREATOR;
        bc.m4055a(this, parcel, i);
    }
}
