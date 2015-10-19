package com.google.android.gms.maps.internal;

import android.graphics.Point;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzy implements SafeParcelable {
    public static final C0555c CREATOR;
    private final int f2735a;
    private final Point f2736b;

    static {
        CREATOR = new C0555c();
    }

    public zzy(int i, Point point) {
        this.f2735a = i;
        this.f2736b = point;
    }

    int m4475a() {
        return this.f2735a;
    }

    public Point m4476b() {
        return this.f2736b;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzy)) {
            return false;
        }
        return this.f2736b.equals(((zzy) obj).f2736b);
    }

    public int hashCode() {
        return this.f2736b.hashCode();
    }

    public String toString() {
        return this.f2736b.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0555c.m4472a(this, parcel, i);
    }
}
