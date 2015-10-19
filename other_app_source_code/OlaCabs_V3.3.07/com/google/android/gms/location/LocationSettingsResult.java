package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.api.C0206g;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LocationSettingsResult implements C0206g, SafeParcelable {
    public static final C0533h CREATOR;
    private final int f2631a;
    private final Status f2632b;
    private final LocationSettingsStates f2633c;

    static {
        CREATOR = new C0533h();
    }

    LocationSettingsResult(int i, Status status, LocationSettingsStates locationSettingsStates) {
        this.f2631a = i;
        this.f2632b = status;
        this.f2633c = locationSettingsStates;
    }

    public LocationSettingsResult(Status status) {
        this(1, status, null);
    }

    public Status m4335a() {
        return this.f2632b;
    }

    public int m4336b() {
        return this.f2631a;
    }

    public LocationSettingsStates m4337c() {
        return this.f2633c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0533h.m4365a(this, parcel, i);
    }
}
