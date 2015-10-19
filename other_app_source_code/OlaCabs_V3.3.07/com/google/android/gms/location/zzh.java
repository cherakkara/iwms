package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class zzh implements SafeParcelable {
    public static final C0539o CREATOR;
    static final List<Location> f2704a;
    List<Location> f2705b;
    private final int f2706c;

    static {
        f2704a = Collections.emptyList();
        CREATOR = new C0539o();
    }

    zzh(int i, List<Location> list) {
        this.f2706c = i;
        this.f2705b = list;
    }

    public boolean m4435a() {
        return !this.f2705b.isEmpty();
    }

    int m4436b() {
        return this.f2706c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh com_google_android_gms_location_zzh = (zzh) obj;
        if (com_google_android_gms_location_zzh.f2705b.size() != this.f2705b.size()) {
            return false;
        }
        Iterator it = this.f2705b.iterator();
        for (Location time : com_google_android_gms_location_zzh.f2705b) {
            if (((Location) it.next()).getTime() != time.getTime()) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 17;
        for (Location time : this.f2705b) {
            long time2 = time.getTime();
            i = ((int) (time2 ^ (time2 >>> 32))) + (i * 31);
        }
        return i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LocationResult[success: ").append(m4435a());
        stringBuilder.append(", locations: ").append(this.f2705b);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0539o.m4379a(this, parcel, i);
    }
}
