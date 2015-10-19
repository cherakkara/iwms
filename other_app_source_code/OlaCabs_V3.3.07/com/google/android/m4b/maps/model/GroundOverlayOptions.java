package com.google.android.m4b.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper.IObjectWrapper;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;
import com.google.android.m4b.maps.p047g.Preconditions;

public final class GroundOverlayOptions implements C0591c {
    public static final GroundOverlayOptionsCreator CREATOR;
    private final int f7542a;
    private BitmapDescriptor f7543b;
    private LatLng f7544c;
    private float f7545d;
    private float f7546e;
    private LatLngBounds f7547f;
    private float f7548g;
    private float f7549h;
    private boolean f7550i;
    private float f7551j;
    private float f7552k;
    private float f7553l;

    static {
        CREATOR = new GroundOverlayOptionsCreator();
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.f7550i = true;
        this.f7551j = 0.0f;
        this.f7552k = 0.5f;
        this.f7553l = 0.5f;
        this.f7542a = i;
        this.f7543b = new BitmapDescriptor(IObjectWrapper.m10120a(iBinder));
        this.f7544c = latLng;
        this.f7545d = f;
        this.f7546e = f2;
        this.f7547f = latLngBounds;
        this.f7548g = f3;
        this.f7549h = f4;
        this.f7550i = z;
        this.f7551j = f5;
        this.f7552k = f6;
        this.f7553l = f7;
    }

    public GroundOverlayOptions() {
        this.f7550i = true;
        this.f7551j = 0.0f;
        this.f7552k = 0.5f;
        this.f7553l = 0.5f;
        this.f7542a = 1;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            GroundOverlayOptionsCreatorCheddar.m10838a(this, parcel, i);
        } else {
            GroundOverlayOptionsCreator.m10835a(this, parcel, i);
        }
    }

    public final int describeContents() {
        return 0;
    }

    final IBinder m10721a() {
        return this.f7543b.m10810a().asBinder();
    }

    public final GroundOverlayOptions m10724a(BitmapDescriptor bitmapDescriptor) {
        this.f7543b = bitmapDescriptor;
        return this;
    }

    public final GroundOverlayOptions m10723a(LatLngBounds latLngBounds) {
        Preconditions.m10463a(this.f7544c == null, "Position has already been set using position: " + this.f7544c);
        this.f7547f = latLngBounds;
        return this;
    }

    public final GroundOverlayOptions m10722a(float f) {
        this.f7549h = f;
        return this;
    }

    final int m10725b() {
        return this.f7542a;
    }

    public final BitmapDescriptor m10726c() {
        return this.f7543b;
    }

    public final LatLng m10727d() {
        return this.f7544c;
    }

    public final float m10728e() {
        return this.f7545d;
    }

    public final float m10729f() {
        return this.f7546e;
    }

    public final LatLngBounds m10730g() {
        return this.f7547f;
    }

    public final float m10731h() {
        return this.f7548g;
    }

    public final float m10732i() {
        return this.f7549h;
    }

    public final float m10733j() {
        return this.f7551j;
    }

    public final float m10734k() {
        return this.f7552k;
    }

    public final float m10735l() {
        return this.f7553l;
    }

    public final boolean m10736m() {
        return this.f7550i;
    }
}
