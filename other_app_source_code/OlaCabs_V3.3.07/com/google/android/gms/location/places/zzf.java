package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.concurrent.TimeUnit;

public final class zzf implements SafeParcelable {
    public static final C0546f CREATOR;
    static final long f2685a;
    final int f2686b;
    private final PlaceFilter f2687c;
    private final long f2688d;
    private final int f2689e;
    private final long f2690f;

    static {
        CREATOR = new C0546f();
        f2685a = TimeUnit.HOURS.toMillis(1);
    }

    public zzf(int i, PlaceFilter placeFilter, long j, int i2, long j2) {
        this.f2686b = i;
        this.f2687c = placeFilter;
        this.f2688d = j;
        this.f2689e = i2;
        this.f2690f = j2;
    }

    public PlaceFilter m4422a() {
        return this.f2687c;
    }

    public long m4423b() {
        return this.f2688d;
    }

    public int m4424c() {
        return this.f2689e;
    }

    public long m4425d() {
        return this.f2690f;
    }

    public int describeContents() {
        C0546f c0546f = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzf)) {
            return false;
        }
        zzf com_google_android_gms_location_places_zzf = (zzf) obj;
        return C0452t.m3845a(this.f2687c, com_google_android_gms_location_places_zzf.f2687c) && this.f2688d == com_google_android_gms_location_places_zzf.f2688d && this.f2689e == com_google_android_gms_location_places_zzf.f2689e && this.f2690f == com_google_android_gms_location_places_zzf.f2690f;
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2687c, Long.valueOf(this.f2688d), Integer.valueOf(this.f2689e), Long.valueOf(this.f2690f));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("filter", this.f2687c).m3842a("interval", Long.valueOf(this.f2688d)).m3842a("priority", Integer.valueOf(this.f2689e)).m3842a("expireAt", Long.valueOf(this.f2690f)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0546f c0546f = CREATOR;
        C0546f.m4412a(this, parcel, i);
    }
}
