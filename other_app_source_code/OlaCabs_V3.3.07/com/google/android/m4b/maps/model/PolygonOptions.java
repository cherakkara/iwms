package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;
import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions implements C0591c {
    public static final PolygonOptionsCreator CREATOR;
    private final int f7589a;
    private final List<LatLng> f7590b;
    private final List<List<LatLng>> f7591c;
    private float f7592d;
    private int f7593e;
    private int f7594f;
    private float f7595g;
    private boolean f7596h;
    private boolean f7597i;

    static {
        CREATOR = new PolygonOptionsCreator();
    }

    public PolygonOptions() {
        this.f7592d = 10.0f;
        this.f7593e = ViewCompat.MEASURED_STATE_MASK;
        this.f7594f = 0;
        this.f7595g = 0.0f;
        this.f7596h = true;
        this.f7597i = false;
        this.f7589a = 1;
        this.f7590b = new ArrayList();
        this.f7591c = new ArrayList();
    }

    PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2) {
        this.f7592d = 10.0f;
        this.f7593e = ViewCompat.MEASURED_STATE_MASK;
        this.f7594f = 0;
        this.f7595g = 0.0f;
        this.f7596h = true;
        this.f7597i = false;
        this.f7589a = i;
        this.f7590b = list;
        this.f7591c = list2;
        this.f7592d = f;
        this.f7593e = i2;
        this.f7594f = i3;
        this.f7595g = f2;
        this.f7596h = z;
        this.f7597i = z2;
    }

    final int m10775a() {
        return this.f7589a;
    }

    final List m10776b() {
        return this.f7591c;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            PolygonOptionsCreatorCheddar.m11028a(this, parcel);
        } else {
            PolygonOptionsCreator.m11025a(this, parcel);
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final List<LatLng> m10777c() {
        return this.f7590b;
    }

    public final List<List<LatLng>> m10778d() {
        return this.f7591c;
    }

    public final float m10779e() {
        return this.f7592d;
    }

    public final int m10780f() {
        return this.f7593e;
    }

    public final int m10781g() {
        return this.f7594f;
    }

    public final float m10782h() {
        return this.f7595g;
    }

    public final boolean m10783i() {
        return this.f7596h;
    }

    public final boolean m10784j() {
        return this.f7597i;
    }
}
