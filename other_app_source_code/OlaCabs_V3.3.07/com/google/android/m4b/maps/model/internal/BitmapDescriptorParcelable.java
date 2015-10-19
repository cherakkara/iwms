package com.google.android.m4b.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;

public final class BitmapDescriptorParcelable implements C0591c {
    public static final BitmapDescriptorParcelableCreator CREATOR;
    private final int f7643a;
    private byte f7644b;
    private Bundle f7645c;
    private Bitmap f7646d;

    static {
        CREATOR = new BitmapDescriptorParcelableCreator();
    }

    BitmapDescriptorParcelable(int i, byte b, Bundle bundle, Bitmap bitmap) {
        this.f7643a = i;
        this.f7644b = b;
        this.f7645c = bundle;
        this.f7646d = bitmap;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        BitmapDescriptorParcelableCreator.m10853a(this, parcel, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final int m10842a() {
        return this.f7643a;
    }

    public final byte m10843b() {
        return this.f7644b;
    }

    public final Bundle m10844c() {
        return this.f7645c;
    }

    public final Bitmap m10845d() {
        return this.f7646d;
    }
}
