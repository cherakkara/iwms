package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import java.util.Arrays;

public class StreetViewPanoramaLink implements C0591c {
    public static final StreetViewPanoramaLinkCreator CREATOR;
    public final String f7610a;
    public final float f7611b;
    private final int f7612c;

    static {
        CREATOR = new StreetViewPanoramaLinkCreator();
    }

    StreetViewPanoramaLink(int i, String str, float f) {
        this.f7612c = i;
        this.f7610a = str;
        if (((double) f) <= 0.0d) {
            f = (f % 360.0f) + 360.0f;
        }
        this.f7611b = f % 360.0f;
    }

    public StreetViewPanoramaLink(String str, float f) {
        this(1, str, f);
    }

    public void writeToParcel(Parcel parcel, int i) {
        StreetViewPanoramaLinkCreator.m11036a(this, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7610a, Float.valueOf(this.f7611b)});
    }

    final int m10793a() {
        return this.f7612c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLink)) {
            return false;
        }
        StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink) obj;
        if (this.f7610a.equals(streetViewPanoramaLink.f7610a) && Float.floatToIntBits(this.f7611b) == Float.floatToIntBits(streetViewPanoramaLink.f7611b)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return Objects.m10456a(this).m10455a("panoId", this.f7610a).m10455a("bearing", Float.valueOf(this.f7611b)).toString();
    }
}
