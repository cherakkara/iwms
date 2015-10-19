package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;

public final class Tile implements C0591c {
    public static final aa CREATOR;
    public final int f7622a;
    public final int f7623b;
    public final byte[] f7624c;
    private final int f7625d;

    static {
        CREATOR = new aa();
    }

    Tile(int i, int i2, int i3, byte[] bArr) {
        this.f7625d = i;
        this.f7622a = i2;
        this.f7623b = i3;
        this.f7624c = bArr;
    }

    final int m10799a() {
        return this.f7625d;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            ab.m10814a(this, parcel);
        } else {
            aa.m10811a(this, parcel);
        }
    }

    public final int describeContents() {
        return 0;
    }
}
