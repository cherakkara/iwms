package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class zzpo implements SafeParcelable {
    public static final Creator<zzpo> CREATOR;
    final int f2522a;
    final String f2523b;
    final String f2524c;
    final List<Integer> f2525d;
    final List<zza> f2526e;
    final int f2527f;

    public static class zza implements SafeParcelable {
        public static final Creator<zza> CREATOR;
        final int f2519a;
        final int f2520b;
        final int f2521c;

        static {
            CREATOR = new aw();
        }

        public zza(int i, int i2, int i3) {
            this.f2519a = i;
            this.f2520b = i2;
            this.f2521c = i3;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzpo_zza = (zza) obj;
            return C0452t.m3845a(Integer.valueOf(this.f2520b), Integer.valueOf(com_google_android_gms_internal_zzpo_zza.f2520b)) && C0452t.m3845a(Integer.valueOf(this.f2521c), Integer.valueOf(com_google_android_gms_internal_zzpo_zza.f2521c));
        }

        public int hashCode() {
            return C0452t.m3843a(Integer.valueOf(this.f2520b), Integer.valueOf(this.f2521c));
        }

        public String toString() {
            return C0452t.m3844a((Object) this).m3842a("offset", Integer.valueOf(this.f2520b)).m3842a("length", Integer.valueOf(this.f2521c)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            aw.m4037a(this, parcel, i);
        }
    }

    static {
        CREATOR = new aq();
    }

    zzpo(int i, String str, String str2, List<Integer> list, List<zza> list2, int i2) {
        this.f2522a = i;
        this.f2523b = str;
        this.f2524c = str2;
        this.f2525d = list;
        this.f2526e = list2;
        this.f2527f = i2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzpo)) {
            return false;
        }
        zzpo com_google_android_gms_internal_zzpo = (zzpo) obj;
        return C0452t.m3845a(this.f2523b, com_google_android_gms_internal_zzpo.f2523b) && C0452t.m3845a(this.f2524c, com_google_android_gms_internal_zzpo.f2524c) && C0452t.m3845a(this.f2525d, com_google_android_gms_internal_zzpo.f2525d) && C0452t.m3845a(this.f2526e, com_google_android_gms_internal_zzpo.f2526e) && C0452t.m3845a(Integer.valueOf(this.f2527f), Integer.valueOf(com_google_android_gms_internal_zzpo.f2527f));
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2523b, this.f2524c, this.f2525d, this.f2526e, Integer.valueOf(this.f2527f));
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("description", this.f2523b).m3842a("placeId", this.f2524c).m3842a("placeTypes", this.f2525d).m3842a("substrings", this.f2526e).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        aq.m4021a(this, parcel, i);
    }
}
