package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;
import com.google.android.gms.p036a.C0199a.C0201a;
import com.olacabs.customer.p076d.br;

public final class MarkerOptions implements SafeParcelable {
    public static final C0577m CREATOR;
    private final int f2768a;
    private LatLng f2769b;
    private String f2770c;
    private String f2771d;
    private C0559a f2772e;
    private float f2773f;
    private float f2774g;
    private boolean f2775h;
    private boolean f2776i;
    private boolean f2777j;
    private float f2778k;
    private float f2779l;
    private float f2780m;
    private float f2781n;

    static {
        CREATOR = new C0577m();
    }

    public MarkerOptions() {
        this.f2773f = 0.5f;
        this.f2774g = br.DEFAULT_BACKOFF_MULT;
        this.f2776i = true;
        this.f2777j = false;
        this.f2778k = 0.0f;
        this.f2779l = 0.5f;
        this.f2780m = 0.0f;
        this.f2781n = br.DEFAULT_BACKOFF_MULT;
        this.f2768a = 1;
    }

    MarkerOptions(int i, LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6) {
        this.f2773f = 0.5f;
        this.f2774g = br.DEFAULT_BACKOFF_MULT;
        this.f2776i = true;
        this.f2777j = false;
        this.f2778k = 0.0f;
        this.f2779l = 0.5f;
        this.f2780m = 0.0f;
        this.f2781n = br.DEFAULT_BACKOFF_MULT;
        this.f2768a = i;
        this.f2769b = latLng;
        this.f2770c = str;
        this.f2771d = str2;
        this.f2772e = iBinder == null ? null : new C0559a(C0201a.m3147a(iBinder));
        this.f2773f = f;
        this.f2774g = f2;
        this.f2775h = z;
        this.f2776i = z2;
        this.f2777j = z3;
        this.f2778k = f3;
        this.f2779l = f4;
        this.f2780m = f5;
        this.f2781n = f6;
    }

    int m4500a() {
        return this.f2768a;
    }

    IBinder m4501b() {
        return this.f2772e == null ? null : this.f2772e.m4545a().asBinder();
    }

    public LatLng m4502c() {
        return this.f2769b;
    }

    public String m4503d() {
        return this.f2770c;
    }

    public int describeContents() {
        return 0;
    }

    public String m4504e() {
        return this.f2771d;
    }

    public float m4505f() {
        return this.f2773f;
    }

    public float m4506g() {
        return this.f2774g;
    }

    public boolean m4507h() {
        return this.f2775h;
    }

    public boolean m4508i() {
        return this.f2776i;
    }

    public boolean m4509j() {
        return this.f2777j;
    }

    public float m4510k() {
        return this.f2778k;
    }

    public float m4511l() {
        return this.f2779l;
    }

    public float m4512m() {
        return this.f2780m;
    }

    public float m4513n() {
        return this.f2781n;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0578n.m4599a(this, parcel, i);
        } else {
            C0577m.m4596a(this, parcel, i);
        }
    }
}
