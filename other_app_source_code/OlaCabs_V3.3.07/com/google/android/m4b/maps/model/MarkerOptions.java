package com.google.android.m4b.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper.IObjectWrapper;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;
import com.olacabs.customer.p076d.br;

public final class MarkerOptions implements C0591c {
    public static final MarkerOptionsCreator CREATOR;
    private final int f7575a;
    private LatLng f7576b;
    private String f7577c;
    private String f7578d;
    private BitmapDescriptor f7579e;
    private float f7580f;
    private float f7581g;
    private boolean f7582h;
    private boolean f7583i;
    private boolean f7584j;
    private float f7585k;
    private float f7586l;
    private float f7587m;
    private float f7588n;

    static {
        CREATOR = new MarkerOptionsCreator();
    }

    public MarkerOptions() {
        this.f7580f = 0.5f;
        this.f7581g = br.DEFAULT_BACKOFF_MULT;
        this.f7583i = true;
        this.f7584j = false;
        this.f7585k = 0.0f;
        this.f7586l = 0.5f;
        this.f7587m = 0.0f;
        this.f7588n = br.DEFAULT_BACKOFF_MULT;
        this.f7575a = 1;
    }

    MarkerOptions(int i, LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6) {
        BitmapDescriptor bitmapDescriptor;
        this.f7580f = 0.5f;
        this.f7581g = br.DEFAULT_BACKOFF_MULT;
        this.f7583i = true;
        this.f7584j = false;
        this.f7585k = 0.0f;
        this.f7586l = 0.5f;
        this.f7587m = 0.0f;
        this.f7588n = br.DEFAULT_BACKOFF_MULT;
        this.f7575a = i;
        this.f7576b = latLng;
        this.f7577c = str;
        this.f7578d = str2;
        if (iBinder == null) {
            bitmapDescriptor = null;
        } else {
            bitmapDescriptor = new BitmapDescriptor(IObjectWrapper.m10120a(iBinder));
        }
        this.f7579e = bitmapDescriptor;
        this.f7580f = f;
        this.f7581g = f2;
        this.f7582h = z;
        this.f7583i = z2;
        this.f7584j = z3;
        this.f7585k = f3;
        this.f7586l = f4;
        this.f7587m = f5;
        this.f7588n = f6;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            MarkerOptionsCreatorCheddar.m11024a(this, parcel, i);
        } else {
            MarkerOptionsCreator.m11021a(this, parcel, i);
        }
    }

    public final int describeContents() {
        return 0;
    }

    final int m10758a() {
        return this.f7575a;
    }

    final IBinder m10761b() {
        return this.f7579e == null ? null : this.f7579e.m10810a().asBinder();
    }

    public final MarkerOptions m10759a(LatLng latLng) {
        this.f7576b = latLng;
        return this;
    }

    public final MarkerOptions m10760a(BitmapDescriptor bitmapDescriptor) {
        this.f7579e = bitmapDescriptor;
        return this;
    }

    public final LatLng m10762c() {
        return this.f7576b;
    }

    public final String m10763d() {
        return this.f7577c;
    }

    public final String m10764e() {
        return this.f7578d;
    }

    public final BitmapDescriptor m10765f() {
        return this.f7579e;
    }

    public final float m10766g() {
        return this.f7580f;
    }

    public final float m10767h() {
        return this.f7581g;
    }

    public final boolean m10768i() {
        return this.f7582h;
    }

    public final boolean m10769j() {
        return this.f7583i;
    }

    public final boolean m10770k() {
        return this.f7584j;
    }

    public final float m10771l() {
        return this.f7585k;
    }

    public final float m10772m() {
        return this.f7586l;
    }

    public final float m10773n() {
        return this.f7587m;
    }

    public final float m10774o() {
        return this.f7588n;
    }
}
