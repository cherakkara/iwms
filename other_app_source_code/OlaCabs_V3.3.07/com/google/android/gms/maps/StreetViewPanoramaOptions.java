package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0553a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final C0552c CREATOR;
    private final int f2724a;
    private StreetViewPanoramaCamera f2725b;
    private String f2726c;
    private LatLng f2727d;
    private Integer f2728e;
    private Boolean f2729f;
    private Boolean f2730g;
    private Boolean f2731h;
    private Boolean f2732i;
    private Boolean f2733j;

    static {
        CREATOR = new C0552c();
    }

    public StreetViewPanoramaOptions() {
        this.f2729f = Boolean.valueOf(true);
        this.f2730g = Boolean.valueOf(true);
        this.f2731h = Boolean.valueOf(true);
        this.f2732i = Boolean.valueOf(true);
        this.f2724a = 1;
    }

    StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.f2729f = Boolean.valueOf(true);
        this.f2730g = Boolean.valueOf(true);
        this.f2731h = Boolean.valueOf(true);
        this.f2732i = Boolean.valueOf(true);
        this.f2724a = i;
        this.f2725b = streetViewPanoramaCamera;
        this.f2727d = latLng;
        this.f2728e = num;
        this.f2726c = str;
        this.f2729f = C0553a.m4470a(b);
        this.f2730g = C0553a.m4470a(b2);
        this.f2731h = C0553a.m4470a(b3);
        this.f2732i = C0553a.m4470a(b4);
        this.f2733j = C0553a.m4470a(b5);
    }

    int m4452a() {
        return this.f2724a;
    }

    byte m4453b() {
        return C0553a.m4469a(this.f2729f);
    }

    byte m4454c() {
        return C0553a.m4469a(this.f2730g);
    }

    byte m4455d() {
        return C0553a.m4469a(this.f2731h);
    }

    public int describeContents() {
        return 0;
    }

    byte m4456e() {
        return C0553a.m4469a(this.f2732i);
    }

    byte m4457f() {
        return C0553a.m4469a(this.f2733j);
    }

    public StreetViewPanoramaCamera m4458g() {
        return this.f2725b;
    }

    public LatLng m4459h() {
        return this.f2727d;
    }

    public Integer m4460i() {
        return this.f2728e;
    }

    public String m4461j() {
        return this.f2726c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0552c.m4466a(this, parcel, i);
    }
}
