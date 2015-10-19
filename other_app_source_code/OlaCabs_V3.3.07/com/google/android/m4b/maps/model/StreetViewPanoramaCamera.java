package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.model.StreetViewPanoramaOrientation.C0596a;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import com.google.android.m4b.maps.p047g.Preconditions;
import java.util.Arrays;

public class StreetViewPanoramaCamera implements C0591c {
    public static final StreetViewPanoramaCameraCreator CREATOR;
    public final float f7605a;
    public final float f7606b;
    public final float f7607c;
    private final int f7608d;
    private StreetViewPanoramaOrientation f7609e;

    static {
        CREATOR = new StreetViewPanoramaCameraCreator();
    }

    StreetViewPanoramaCamera(int i, float f, float f2, float f3) {
        float f4;
        boolean z = -90.0f <= f2 && f2 <= 90.0f;
        Preconditions.m10466b(z, "Tilt needs to be between -90 and 90 inclusive");
        this.f7608d = i;
        if (((double) f) <= 0.0d) {
            f = 0.0f;
        }
        this.f7605a = f;
        this.f7606b = f2 + 0.0f;
        if (((double) f3) <= 0.0d) {
            f4 = (f3 % 360.0f) + 360.0f;
        } else {
            f4 = f3;
        }
        this.f7607c = f4 % 360.0f;
        this.f7609e = new C0596a().m10795a(f2).m10797b(f3).m10796a();
    }

    public StreetViewPanoramaCamera(float f, float f2, float f3) {
        this(1, f, f2, f3);
    }

    public void writeToParcel(Parcel parcel, int i) {
        StreetViewPanoramaCameraCreator.m11033a(this, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f7605a), Float.valueOf(this.f7606b), Float.valueOf(this.f7607c)});
    }

    final int m10792a() {
        return this.f7608d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaCamera)) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) obj;
        if (Float.floatToIntBits(this.f7605a) == Float.floatToIntBits(streetViewPanoramaCamera.f7605a) && Float.floatToIntBits(this.f7606b) == Float.floatToIntBits(streetViewPanoramaCamera.f7606b) && Float.floatToIntBits(this.f7607c) == Float.floatToIntBits(streetViewPanoramaCamera.f7607c)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return Objects.m10456a(this).m10455a("zoom", Float.valueOf(this.f7605a)).m10455a("tilt", Float.valueOf(this.f7606b)).m10455a("bearing", Float.valueOf(this.f7607c)).toString();
    }
}
