package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p042r.SafeParcelableVersionManager;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions implements C0591c {
    public static final PolylineOptionsCreator CREATOR;
    private final int f7598a;
    private final List<LatLng> f7599b;
    private float f7600c;
    private int f7601d;
    private float f7602e;
    private boolean f7603f;
    private boolean f7604g;

    static {
        CREATOR = new PolylineOptionsCreator();
    }

    public PolylineOptions() {
        this.f7600c = 10.0f;
        this.f7601d = ViewCompat.MEASURED_STATE_MASK;
        this.f7602e = 0.0f;
        this.f7603f = true;
        this.f7604g = false;
        this.f7598a = 1;
        this.f7599b = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2) {
        this.f7600c = 10.0f;
        this.f7601d = ViewCompat.MEASURED_STATE_MASK;
        this.f7602e = 0.0f;
        this.f7603f = true;
        this.f7604g = false;
        this.f7598a = i;
        this.f7599b = list;
        this.f7600c = f;
        this.f7601d = i2;
        this.f7602e = f2;
        this.f7603f = z;
        this.f7604g = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (SafeParcelableVersionManager.m11188a()) {
            PolylineOptionsCreatorCheddar.m11032a(this, parcel);
        } else {
            PolylineOptionsCreator.m11029a(this, parcel);
        }
    }

    public final int describeContents() {
        return 0;
    }

    final int m10785a() {
        return this.f7598a;
    }

    public final List<LatLng> m10786b() {
        return this.f7599b;
    }

    public final float m10787c() {
        return this.f7600c;
    }

    public final int m10788d() {
        return this.f7601d;
    }

    public final float m10789e() {
        return this.f7602e;
    }

    public final boolean m10790f() {
        return this.f7603f;
    }

    public final boolean m10791g() {
        return this.f7604g;
    }
}
