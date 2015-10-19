package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class zzqq implements SafeParcelable {
    public static final ba CREATOR;
    final int f2579a;
    private final String f2580b;
    private final String f2581c;
    private final List<zzqu> f2582d;
    private final List<zzqo> f2583e;
    private final List<zzqm> f2584f;

    static {
        CREATOR = new ba();
    }

    zzqq(int i, String str, String str2, List<zzqu> list, List<zzqo> list2, List<zzqm> list3) {
        this.f2579a = i;
        this.f2580b = str;
        this.f2581c = str2;
        this.f2582d = list;
        this.f2583e = list2;
        this.f2584f = list3;
    }

    public String m4302a() {
        return this.f2580b;
    }

    public String m4303b() {
        return this.f2581c;
    }

    public List<zzqo> m4304c() {
        return this.f2583e;
    }

    public List<zzqm> m4305d() {
        return this.f2584f;
    }

    public int describeContents() {
        ba baVar = CREATOR;
        return 0;
    }

    public List<zzqu> m4306e() {
        return this.f2582d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqq)) {
            return false;
        }
        zzqq com_google_android_gms_internal_zzqq = (zzqq) obj;
        return this.f2580b.equals(com_google_android_gms_internal_zzqq.f2580b) && this.f2581c.equals(com_google_android_gms_internal_zzqq.f2581c) && this.f2582d.equals(com_google_android_gms_internal_zzqq.f2582d) && this.f2583e.equals(com_google_android_gms_internal_zzqq.f2583e) && this.f2584f.equals(com_google_android_gms_internal_zzqq.f2584f);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2580b, this.f2581c, this.f2582d, this.f2583e, this.f2584f);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("accountName", this.f2580b).m3842a("placeId", this.f2581c).m3842a("testDataImpls", this.f2582d).m3842a("placeAliases", this.f2583e).m3842a("hereContents", this.f2584f).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        ba baVar = CREATOR;
        ba.m4052a(this, parcel, i);
    }
}
