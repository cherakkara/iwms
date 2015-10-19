package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import com.google.android.m4b.maps.p047g.Preconditions;
import java.util.Arrays;

public class StreetViewPanoramaOrientation implements C0591c {
    public static final StreetViewPanoramaOrientationCreator CREATOR;
    public final float f7619a;
    public final float f7620b;
    private final int f7621c;

    /* renamed from: com.google.android.m4b.maps.model.StreetViewPanoramaOrientation.a */
    public static final class C0596a {
        public float f7617a;
        public float f7618b;

        public final C0596a m10795a(float f) {
            this.f7618b = f;
            return this;
        }

        public final C0596a m10797b(float f) {
            this.f7617a = f;
            return this;
        }

        public final StreetViewPanoramaOrientation m10796a() {
            return new StreetViewPanoramaOrientation(this.f7618b, this.f7617a);
        }
    }

    static {
        CREATOR = new StreetViewPanoramaOrientationCreator();
    }

    StreetViewPanoramaOrientation(int i, float f, float f2) {
        boolean z = -90.0f <= f && f <= 90.0f;
        Preconditions.m10466b(z, "Tilt needs to be between -90 and 90 inclusive");
        this.f7621c = i;
        this.f7619a = 0.0f + f;
        if (((double) f2) <= 0.0d) {
            f2 = (f2 % 360.0f) + 360.0f;
        }
        this.f7620b = f2 % 360.0f;
    }

    public StreetViewPanoramaOrientation(float f, float f2) {
        this(1, f, f2);
    }

    public void writeToParcel(Parcel parcel, int i) {
        StreetViewPanoramaOrientationCreator.m11042a(this, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f7619a), Float.valueOf(this.f7620b)});
    }

    final int m10798a() {
        return this.f7621c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaOrientation)) {
            return false;
        }
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) obj;
        if (Float.floatToIntBits(this.f7619a) == Float.floatToIntBits(streetViewPanoramaOrientation.f7619a) && Float.floatToIntBits(this.f7620b) == Float.floatToIntBits(streetViewPanoramaOrientation.f7620b)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return Objects.m10456a(this).m10455a("tilt", Float.valueOf(this.f7619a)).m10455a("bearing", Float.valueOf(this.f7620b)).toString();
    }
}
