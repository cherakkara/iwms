package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;
import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions implements SafeParcelable {
    public static final C0579o CREATOR;
    private final int f2782a;
    private final List<LatLng> f2783b;
    private final List<List<LatLng>> f2784c;
    private float f2785d;
    private int f2786e;
    private int f2787f;
    private float f2788g;
    private boolean f2789h;
    private boolean f2790i;

    static {
        CREATOR = new C0579o();
    }

    public PolygonOptions() {
        this.f2785d = 10.0f;
        this.f2786e = ViewCompat.MEASURED_STATE_MASK;
        this.f2787f = 0;
        this.f2788g = 0.0f;
        this.f2789h = true;
        this.f2790i = false;
        this.f2782a = 1;
        this.f2783b = new ArrayList();
        this.f2784c = new ArrayList();
    }

    PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2) {
        this.f2785d = 10.0f;
        this.f2786e = ViewCompat.MEASURED_STATE_MASK;
        this.f2787f = 0;
        this.f2788g = 0.0f;
        this.f2789h = true;
        this.f2790i = false;
        this.f2782a = i;
        this.f2783b = list;
        this.f2784c = list2;
        this.f2785d = f;
        this.f2786e = i2;
        this.f2787f = i3;
        this.f2788g = f2;
        this.f2789h = z;
        this.f2790i = z2;
    }

    int m4514a() {
        return this.f2782a;
    }

    List m4515b() {
        return this.f2784c;
    }

    public List<LatLng> m4516c() {
        return this.f2783b;
    }

    public float m4517d() {
        return this.f2785d;
    }

    public int describeContents() {
        return 0;
    }

    public int m4518e() {
        return this.f2786e;
    }

    public int m4519f() {
        return this.f2787f;
    }

    public float m4520g() {
        return this.f2788g;
    }

    public boolean m4521h() {
        return this.f2789h;
    }

    public boolean m4522i() {
        return this.f2790i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0580p.m4603a(this, parcel, i);
        } else {
            C0579o.m4600a(this, parcel, i);
        }
    }
}
