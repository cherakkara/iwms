package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.C0554b;

public final class CameraPosition implements SafeParcelable {
    public static final C0560c CREATOR;
    public final LatLng f2737a;
    public final float f2738b;
    public final float f2739c;
    public final float f2740d;
    private final int f2741e;

    static {
        CREATOR = new C0560c();
    }

    CameraPosition(int i, LatLng latLng, float f, float f2, float f3) {
        C0453u.m3847a((Object) latLng, (Object) "null camera target");
        boolean z = 0.0f <= f2 && f2 <= 90.0f;
        C0453u.m3855b(z, "Tilt needs to be between 0 and 90 inclusive");
        this.f2741e = i;
        this.f2737a = latLng;
        this.f2738b = f;
        this.f2739c = f2 + 0.0f;
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.f2740d = f3 % 360.0f;
    }

    int m4477a() {
        return this.f2741e;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        return this.f2737a.equals(cameraPosition.f2737a) && Float.floatToIntBits(this.f2738b) == Float.floatToIntBits(cameraPosition.f2738b) && Float.floatToIntBits(this.f2739c) == Float.floatToIntBits(cameraPosition.f2739c) && Float.floatToIntBits(this.f2740d) == Float.floatToIntBits(cameraPosition.f2740d);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2737a, Float.valueOf(this.f2738b), Float.valueOf(this.f2739c), Float.valueOf(this.f2740d));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("target", this.f2737a).m3842a("zoom", Float.valueOf(this.f2738b)).m3842a("tilt", Float.valueOf(this.f2739c)).m3842a("bearing", Float.valueOf(this.f2740d)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (C0554b.m4471a()) {
            C0561d.m4553a(this, parcel, i);
        } else {
            C0560c.m4550a(this, parcel, i);
        }
    }
}
