package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;

public final class CircleOptions implements C0591c {
    public static final CircleOptionsCreator CREATOR;
    private final int f7534a;
    private LatLng f7535b;
    private double f7536c;
    private float f7537d;
    private int f7538e;
    private int f7539f;
    private float f7540g;
    private boolean f7541h;

    static {
        CREATOR = new CircleOptionsCreator();
    }

    public CircleOptions() {
        this.f7535b = null;
        this.f7536c = 0.0d;
        this.f7537d = 10.0f;
        this.f7538e = ViewCompat.MEASURED_STATE_MASK;
        this.f7539f = 0;
        this.f7540g = 0.0f;
        this.f7541h = true;
        this.f7534a = 1;
    }

    CircleOptions(int i, LatLng latLng, double d, float f, int i2, int i3, float f2, boolean z) {
        this.f7535b = null;
        this.f7536c = 0.0d;
        this.f7537d = 10.0f;
        this.f7538e = ViewCompat.MEASURED_STATE_MASK;
        this.f7539f = 0;
        this.f7540g = 0.0f;
        this.f7541h = true;
        this.f7534a = i;
        this.f7535b = latLng;
        this.f7536c = d;
        this.f7537d = f;
        this.f7538e = i2;
        this.f7539f = i3;
        this.f7540g = f2;
        this.f7541h = z;
    }

    final int m10713a() {
        return this.f7534a;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            CircleOptionsCreatorCheddar.m10834a(this, parcel, i);
        } else {
            CircleOptionsCreator.m10831a(this, parcel, i);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final LatLng m10714b() {
        return this.f7535b;
    }

    public final double m10715c() {
        return this.f7536c;
    }

    public final float m10716d() {
        return this.f7537d;
    }

    public final int m10717e() {
        return this.f7538e;
    }

    public final int m10718f() {
        return this.f7539f;
    }

    public final float m10719g() {
        return this.f7540g;
    }

    public final boolean m10720h() {
        return this.f7541h;
    }
}
