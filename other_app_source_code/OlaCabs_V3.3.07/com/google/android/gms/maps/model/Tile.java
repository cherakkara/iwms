package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;

public final class Tile implements SafeParcelable {
    public static final C0587w CREATOR;
    public final int f2815a;
    public final int f2816b;
    public final byte[] f2817c;
    private final int f2818d;

    static {
        CREATOR = new C0587w();
    }

    Tile(int i, int i2, int i3, byte[] bArr) {
        this.f2818d = i;
        this.f2815a = i2;
        this.f2816b = i3;
        this.f2817c = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    int m4537a() {
        return this.f2818d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0588x.m4623a(this, parcel, i);
        } else {
            C0587w.m4620a(this, parcel, i);
        }
    }
}
