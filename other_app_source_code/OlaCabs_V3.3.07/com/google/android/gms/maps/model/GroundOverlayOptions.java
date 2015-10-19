package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;
import com.google.android.gms.p036a.C0199a.C0201a;

public final class GroundOverlayOptions implements SafeParcelable {
    public static final C0564g CREATOR;
    private final int f2750a;
    private C0559a f2751b;
    private LatLng f2752c;
    private float f2753d;
    private float f2754e;
    private LatLngBounds f2755f;
    private float f2756g;
    private float f2757h;
    private boolean f2758i;
    private float f2759j;
    private float f2760k;
    private float f2761l;

    static {
        CREATOR = new C0564g();
    }

    public GroundOverlayOptions() {
        this.f2758i = true;
        this.f2759j = 0.0f;
        this.f2760k = 0.5f;
        this.f2761l = 0.5f;
        this.f2750a = 1;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.f2758i = true;
        this.f2759j = 0.0f;
        this.f2760k = 0.5f;
        this.f2761l = 0.5f;
        this.f2750a = i;
        this.f2751b = new C0559a(C0201a.m3147a(iBinder));
        this.f2752c = latLng;
        this.f2753d = f;
        this.f2754e = f2;
        this.f2755f = latLngBounds;
        this.f2756g = f3;
        this.f2757h = f4;
        this.f2758i = z;
        this.f2759j = f5;
        this.f2760k = f6;
        this.f2761l = f7;
    }

    IBinder m4486a() {
        return this.f2751b.m4545a().asBinder();
    }

    int m4487b() {
        return this.f2750a;
    }

    public LatLng m4488c() {
        return this.f2752c;
    }

    public float m4489d() {
        return this.f2753d;
    }

    public int describeContents() {
        return 0;
    }

    public float m4490e() {
        return this.f2754e;
    }

    public LatLngBounds m4491f() {
        return this.f2755f;
    }

    public float m4492g() {
        return this.f2756g;
    }

    public float m4493h() {
        return this.f2757h;
    }

    public float m4494i() {
        return this.f2759j;
    }

    public float m4495j() {
        return this.f2760k;
    }

    public float m4496k() {
        return this.f2761l;
    }

    public boolean m4497l() {
        return this.f2758i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0565h.m4561a(this, parcel, i);
        } else {
            C0564g.m4558a(this, parcel, i);
        }
    }
}
