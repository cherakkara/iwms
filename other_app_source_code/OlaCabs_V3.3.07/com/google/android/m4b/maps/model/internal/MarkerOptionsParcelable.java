package com.google.android.m4b.maps.model.internal;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;

public final class MarkerOptionsParcelable implements C0591c {
    public static final MarkerOptionsParcelableCreator CREATOR;
    private final int f7652a;
    private BitmapDescriptorParcelable f7653b;

    static {
        CREATOR = new MarkerOptionsParcelableCreator();
    }

    public MarkerOptionsParcelable() {
        this.f7652a = 1;
    }

    MarkerOptionsParcelable(int i, BitmapDescriptorParcelable bitmapDescriptorParcelable) {
        this.f7652a = i;
        this.f7653b = bitmapDescriptorParcelable;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        MarkerOptionsParcelableCreator.m11007a(this, parcel, i);
    }

    public final int describeContents() {
        return 0;
    }

    final int m10851a() {
        return this.f7652a;
    }

    public final BitmapDescriptorParcelable m10852b() {
        return this.f7653b;
    }
}
