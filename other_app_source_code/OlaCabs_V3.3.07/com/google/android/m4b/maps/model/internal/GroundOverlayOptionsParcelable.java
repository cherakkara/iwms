package com.google.android.m4b.maps.model.internal;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;

public final class GroundOverlayOptionsParcelable implements C0591c {
    public static final GroundOverlayOptionsParcelableCreator CREATOR;
    private final int f7650a;
    private BitmapDescriptorParcelable f7651b;

    static {
        CREATOR = new GroundOverlayOptionsParcelableCreator();
    }

    public GroundOverlayOptionsParcelable() {
        this.f7650a = 1;
    }

    GroundOverlayOptionsParcelable(int i, BitmapDescriptorParcelable bitmapDescriptorParcelable) {
        this.f7650a = i;
        this.f7651b = bitmapDescriptorParcelable;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        GroundOverlayOptionsParcelableCreator.m10859a(this, parcel, i);
    }

    public final int describeContents() {
        return 0;
    }

    final int m10849a() {
        return this.f7650a;
    }

    public final BitmapDescriptorParcelable m10850b() {
        return this.f7651b;
    }
}
