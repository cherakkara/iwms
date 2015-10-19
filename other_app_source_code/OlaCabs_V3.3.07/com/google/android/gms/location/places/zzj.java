package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzj implements SafeParcelable {
    public static final C0547g CREATOR;
    public static final zzj f2691a;
    public static final zzj f2692b;
    public static final zzj f2693c;
    public static final Set<zzj> f2694d;
    final int f2695e;
    final String f2696f;
    final int f2697g;

    static {
        f2691a = m4426a("test_type", 1);
        f2692b = m4426a("labeled_place", 6);
        f2693c = m4426a("here_content", 7);
        f2694d = Collections.unmodifiableSet(new HashSet(Arrays.asList(new zzj[]{f2691a, f2692b, f2693c})));
        CREATOR = new C0547g();
    }

    zzj(int i, String str, int i2) {
        C0453u.m3848a(str);
        this.f2695e = i;
        this.f2696f = str;
        this.f2697g = i2;
    }

    private static zzj m4426a(String str, int i) {
        return new zzj(0, str, i);
    }

    public int describeContents() {
        C0547g c0547g = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj com_google_android_gms_location_places_zzj = (zzj) obj;
        return this.f2696f.equals(com_google_android_gms_location_places_zzj.f2696f) && this.f2697g == com_google_android_gms_location_places_zzj.f2697g;
    }

    public int hashCode() {
        return this.f2696f.hashCode();
    }

    public String toString() {
        return this.f2696f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0547g c0547g = CREATOR;
        C0547g.m4415a(this, parcel, i);
    }
}
