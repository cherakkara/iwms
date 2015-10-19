package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzqa implements SafeParcelable {
    public static final Creator<zzqa> CREATOR;
    final int f2553a;
    final zzpy f2554b;
    final float f2555c;

    static {
        CREATOR = new as();
    }

    zzqa(int i, zzpy com_google_android_gms_internal_zzpy, float f) {
        this.f2553a = i;
        this.f2554b = com_google_android_gms_internal_zzpy;
        this.f2555c = f;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqa)) {
            return false;
        }
        zzqa com_google_android_gms_internal_zzqa = (zzqa) obj;
        return this.f2554b.equals(com_google_android_gms_internal_zzqa.f2554b) && this.f2555c == com_google_android_gms_internal_zzqa.f2555c;
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2554b, Float.valueOf(this.f2555c));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("place", this.f2554b).m3842a("likelihood", Float.valueOf(this.f2555c)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        as.m4027a(this, parcel, i);
    }
}
