package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaOrientation implements SafeParcelable {
    public static final C0586v CREATOR;
    public final float f2812a;
    public final float f2813b;
    private final int f2814c;

    /* renamed from: com.google.android.gms.maps.model.StreetViewPanoramaOrientation.a */
    public static final class C0556a {
        public float f2810a;
        public float f2811b;

        public C0556a m4533a(float f) {
            this.f2811b = f;
            return this;
        }

        public StreetViewPanoramaOrientation m4534a() {
            return new StreetViewPanoramaOrientation(this.f2811b, this.f2810a);
        }

        public C0556a m4535b(float f) {
            this.f2810a = f;
            return this;
        }
    }

    static {
        CREATOR = new C0586v();
    }

    public StreetViewPanoramaOrientation(float f, float f2) {
        this(1, f, f2);
    }

    StreetViewPanoramaOrientation(int i, float f, float f2) {
        boolean z = -90.0f <= f && f <= 90.0f;
        C0453u.m3855b(z, "Tilt needs to be between -90 and 90 inclusive");
        this.f2814c = i;
        this.f2812a = 0.0f + f;
        if (((double) f2) <= 0.0d) {
            f2 = (f2 % 360.0f) + 360.0f;
        }
        this.f2813b = f2 % 360.0f;
    }

    int m4536a() {
        return this.f2814c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaOrientation)) {
            return false;
        }
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) obj;
        return Float.floatToIntBits(this.f2812a) == Float.floatToIntBits(streetViewPanoramaOrientation.f2812a) && Float.floatToIntBits(this.f2813b) == Float.floatToIntBits(streetViewPanoramaOrientation.f2813b);
    }

    public int hashCode() {
        return C0452t.m3843a(Float.valueOf(this.f2812a), Float.valueOf(this.f2813b));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("tilt", Float.valueOf(this.f2812a)).m3842a("bearing", Float.valueOf(this.f2813b)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0586v.m4617a(this, parcel, i);
    }
}
