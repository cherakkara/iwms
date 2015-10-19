package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;

public final class CircleOptions implements SafeParcelable {
    public static final C0562e CREATOR;
    private final int f2742a;
    private LatLng f2743b;
    private double f2744c;
    private float f2745d;
    private int f2746e;
    private int f2747f;
    private float f2748g;
    private boolean f2749h;

    static {
        CREATOR = new C0562e();
    }

    public CircleOptions() {
        this.f2743b = null;
        this.f2744c = 0.0d;
        this.f2745d = 10.0f;
        this.f2746e = ViewCompat.MEASURED_STATE_MASK;
        this.f2747f = 0;
        this.f2748g = 0.0f;
        this.f2749h = true;
        this.f2742a = 1;
    }

    CircleOptions(int i, LatLng latLng, double d, float f, int i2, int i3, float f2, boolean z) {
        this.f2743b = null;
        this.f2744c = 0.0d;
        this.f2745d = 10.0f;
        this.f2746e = ViewCompat.MEASURED_STATE_MASK;
        this.f2747f = 0;
        this.f2748g = 0.0f;
        this.f2749h = true;
        this.f2742a = i;
        this.f2743b = latLng;
        this.f2744c = d;
        this.f2745d = f;
        this.f2746e = i2;
        this.f2747f = i3;
        this.f2748g = f2;
        this.f2749h = z;
    }

    int m4478a() {
        return this.f2742a;
    }

    public LatLng m4479b() {
        return this.f2743b;
    }

    public double m4480c() {
        return this.f2744c;
    }

    public float m4481d() {
        return this.f2745d;
    }

    public int describeContents() {
        return 0;
    }

    public int m4482e() {
        return this.f2746e;
    }

    public int m4483f() {
        return this.f2747f;
    }

    public float m4484g() {
        return this.f2748g;
    }

    public boolean m4485h() {
        return this.f2749h;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0563f.m4557a(this, parcel, i);
        } else {
            C0562e.m4554a(this, parcel, i);
        }
    }
}
