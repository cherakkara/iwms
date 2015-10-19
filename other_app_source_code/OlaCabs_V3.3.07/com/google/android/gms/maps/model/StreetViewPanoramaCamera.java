package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation.C0556a;

public class StreetViewPanoramaCamera implements SafeParcelable {
    public static final C0583s CREATOR;
    public final float f2798a;
    public final float f2799b;
    public final float f2800c;
    private final int f2801d;
    private StreetViewPanoramaOrientation f2802e;

    static {
        CREATOR = new C0583s();
    }

    StreetViewPanoramaCamera(int i, float f, float f2, float f3) {
        boolean z = -90.0f <= f2 && f2 <= 90.0f;
        C0453u.m3855b(z, "Tilt needs to be between -90 and 90 inclusive");
        this.f2801d = i;
        if (((double) f) <= 0.0d) {
            f = 0.0f;
        }
        this.f2798a = f;
        this.f2799b = f2 + 0.0f;
        this.f2800c = (((double) f3) <= 0.0d ? (f3 % 360.0f) + 360.0f : f3) % 360.0f;
        this.f2802e = new C0556a().m4533a(f2).m4535b(f3).m4534a();
    }

    int m4530a() {
        return this.f2801d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaCamera)) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) obj;
        return Float.floatToIntBits(this.f2798a) == Float.floatToIntBits(streetViewPanoramaCamera.f2798a) && Float.floatToIntBits(this.f2799b) == Float.floatToIntBits(streetViewPanoramaCamera.f2799b) && Float.floatToIntBits(this.f2800c) == Float.floatToIntBits(streetViewPanoramaCamera.f2800c);
    }

    public int hashCode() {
        return C0452t.m3843a(Float.valueOf(this.f2798a), Float.valueOf(this.f2799b), Float.valueOf(this.f2800c));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("zoom", Float.valueOf(this.f2798a)).m3842a("tilt", Float.valueOf(this.f2799b)).m3842a("bearing", Float.valueOf(this.f2800c)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0583s.m4608a(this, parcel, i);
    }
}
