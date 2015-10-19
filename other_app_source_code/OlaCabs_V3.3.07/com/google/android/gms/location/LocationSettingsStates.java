package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0449c;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LocationSettingsStates implements SafeParcelable {
    public static final Creator<LocationSettingsStates> CREATOR;
    private final int f2634a;
    private final boolean f2635b;
    private final boolean f2636c;
    private final boolean f2637d;
    private final boolean f2638e;
    private final boolean f2639f;
    private final boolean f2640g;

    static {
        CREATOR = new C0548q();
    }

    LocationSettingsStates(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.f2634a = i;
        this.f2635b = z;
        this.f2636c = z2;
        this.f2637d = z3;
        this.f2638e = z4;
        this.f2639f = z5;
        this.f2640g = z6;
    }

    public static LocationSettingsStates m4338a(Intent intent) {
        return (LocationSettingsStates) C0449c.m3840a(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
    }

    public int m4339a() {
        return this.f2634a;
    }

    public boolean m4340b() {
        return this.f2635b;
    }

    public boolean m4341c() {
        return this.f2638e;
    }

    public boolean m4342d() {
        return this.f2636c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m4343e() {
        return this.f2639f;
    }

    public boolean m4344f() {
        return this.f2635b || this.f2636c;
    }

    public boolean m4345g() {
        return this.f2638e || this.f2639f;
    }

    public boolean m4346h() {
        return this.f2637d;
    }

    public boolean m4347i() {
        return this.f2640g;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0548q.m4427a(this, parcel, i);
    }
}
