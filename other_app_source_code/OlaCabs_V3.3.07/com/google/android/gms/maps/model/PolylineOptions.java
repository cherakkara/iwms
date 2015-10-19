package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions implements SafeParcelable {
    public static final C0581q CREATOR;
    private final int f2791a;
    private final List<LatLng> f2792b;
    private float f2793c;
    private int f2794d;
    private float f2795e;
    private boolean f2796f;
    private boolean f2797g;

    static {
        CREATOR = new C0581q();
    }

    public PolylineOptions() {
        this.f2793c = 10.0f;
        this.f2794d = ViewCompat.MEASURED_STATE_MASK;
        this.f2795e = 0.0f;
        this.f2796f = true;
        this.f2797g = false;
        this.f2791a = 1;
        this.f2792b = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2) {
        this.f2793c = 10.0f;
        this.f2794d = ViewCompat.MEASURED_STATE_MASK;
        this.f2795e = 0.0f;
        this.f2796f = true;
        this.f2797g = false;
        this.f2791a = i;
        this.f2792b = list;
        this.f2793c = f;
        this.f2794d = i2;
        this.f2795e = f2;
        this.f2796f = z;
        this.f2797g = z2;
    }

    int m4523a() {
        return this.f2791a;
    }

    public List<LatLng> m4524b() {
        return this.f2792b;
    }

    public float m4525c() {
        return this.f2793c;
    }

    public int m4526d() {
        return this.f2794d;
    }

    public int describeContents() {
        return 0;
    }

    public float m4527e() {
        return this.f2795e;
    }

    public boolean m4528f() {
        return this.f2796f;
    }

    public boolean m4529g() {
        return this.f2797g;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0582r.m4607a(this, parcel, i);
        } else {
            C0581q.m4604a(this, parcel, i);
        }
    }
}
