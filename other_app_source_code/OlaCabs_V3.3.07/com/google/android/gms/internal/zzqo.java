package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzqo implements SafeParcelable {
    public static final az CREATOR;
    public static final zzqo f2575a;
    public static final zzqo f2576b;
    final int f2577c;
    private final String f2578d;

    static {
        CREATOR = new az();
        f2575a = new zzqo(0, "Home");
        f2576b = new zzqo(0, "Work");
    }

    zzqo(int i, String str) {
        this.f2577c = i;
        this.f2578d = str;
    }

    public String m4301a() {
        return this.f2578d;
    }

    public int describeContents() {
        az azVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqo)) {
            return false;
        }
        return C0452t.m3845a(this.f2578d, ((zzqo) obj).f2578d);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2578d);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("alias", this.f2578d).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        az azVar = CREATOR;
        az.m4046a(this, parcel, i);
    }
}
