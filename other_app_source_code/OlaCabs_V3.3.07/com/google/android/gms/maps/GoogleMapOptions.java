package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0553a;
import com.google.android.gms.maps.internal.C0554b;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements SafeParcelable {
    public static final C0550a CREATOR;
    private final int f2711a;
    private Boolean f2712b;
    private Boolean f2713c;
    private int f2714d;
    private CameraPosition f2715e;
    private Boolean f2716f;
    private Boolean f2717g;
    private Boolean f2718h;
    private Boolean f2719i;
    private Boolean f2720j;
    private Boolean f2721k;
    private Boolean f2722l;
    private Boolean f2723m;

    static {
        CREATOR = new C0550a();
    }

    public GoogleMapOptions() {
        this.f2714d = -1;
        this.f2711a = 1;
    }

    GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10) {
        this.f2714d = -1;
        this.f2711a = i;
        this.f2712b = C0553a.m4470a(b);
        this.f2713c = C0553a.m4470a(b2);
        this.f2714d = i2;
        this.f2715e = cameraPosition;
        this.f2716f = C0553a.m4470a(b3);
        this.f2717g = C0553a.m4470a(b4);
        this.f2718h = C0553a.m4470a(b5);
        this.f2719i = C0553a.m4470a(b6);
        this.f2720j = C0553a.m4470a(b7);
        this.f2721k = C0553a.m4470a(b8);
        this.f2722l = C0553a.m4470a(b9);
        this.f2723m = C0553a.m4470a(b10);
    }

    int m4439a() {
        return this.f2711a;
    }

    byte m4440b() {
        return C0553a.m4469a(this.f2712b);
    }

    byte m4441c() {
        return C0553a.m4469a(this.f2713c);
    }

    byte m4442d() {
        return C0553a.m4469a(this.f2716f);
    }

    public int describeContents() {
        return 0;
    }

    byte m4443e() {
        return C0553a.m4469a(this.f2717g);
    }

    byte m4444f() {
        return C0553a.m4469a(this.f2718h);
    }

    byte m4445g() {
        return C0553a.m4469a(this.f2719i);
    }

    byte m4446h() {
        return C0553a.m4469a(this.f2720j);
    }

    byte m4447i() {
        return C0553a.m4469a(this.f2721k);
    }

    byte m4448j() {
        return C0553a.m4469a(this.f2722l);
    }

    byte m4449k() {
        return C0553a.m4469a(this.f2723m);
    }

    public int m4450l() {
        return this.f2714d;
    }

    public CameraPosition m4451m() {
        return this.f2715e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0551b.m4465a(this, parcel, i);
        } else {
            C0550a.m4462a(this, parcel, i);
        }
    }
}
