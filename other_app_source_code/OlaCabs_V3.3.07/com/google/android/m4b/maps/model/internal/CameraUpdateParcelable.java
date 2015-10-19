package com.google.android.m4b.maps.model.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;

public final class CameraUpdateParcelable implements C0591c {
    public static final CameraUpdateParcelableCreator CREATOR;
    private final int f7647a;
    private int f7648b;
    private Bundle f7649c;

    static {
        CREATOR = new CameraUpdateParcelableCreator();
    }

    CameraUpdateParcelable(int i, int i2, Bundle bundle) {
        this.f7647a = i;
        this.f7648b = i2;
        this.f7649c = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        CameraUpdateParcelableCreator.m10856a(this, parcel);
    }

    public final int describeContents() {
        return 0;
    }

    public final int m10846a() {
        return this.f7647a;
    }

    public final int m10847b() {
        return this.f7648b;
    }

    public final Bundle m10848c() {
        return this.f7649c;
    }
}
