package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class zzqh implements SafeParcelable {
    public static final av CREATOR;
    public static final zzqh f2562a;
    public final int f2563b;
    public final String f2564c;
    public final String f2565d;
    public final String f2566e;
    public final String f2567f;
    public final String f2568g;

    static {
        f2562a = new zzqh("com.google.android.gms", Locale.getDefault(), null);
        CREATOR = new av();
    }

    public zzqh(int i, String str, String str2, String str3, String str4, String str5) {
        this.f2563b = i;
        this.f2564c = str;
        this.f2565d = str2;
        this.f2566e = str3;
        this.f2567f = str4;
        this.f2568g = str5;
    }

    public zzqh(String str, Locale locale, String str2) {
        this(1, str, locale.toString(), str2, null, null);
    }

    public int describeContents() {
        av avVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzqh)) {
            return false;
        }
        zzqh com_google_android_gms_internal_zzqh = (zzqh) obj;
        return this.f2565d.equals(com_google_android_gms_internal_zzqh.f2565d) && this.f2564c.equals(com_google_android_gms_internal_zzqh.f2564c) && C0452t.m3845a(this.f2566e, com_google_android_gms_internal_zzqh.f2566e) && C0452t.m3845a(this.f2567f, com_google_android_gms_internal_zzqh.f2567f) && C0452t.m3845a(this.f2568g, com_google_android_gms_internal_zzqh.f2568g);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2564c, this.f2565d, this.f2566e, this.f2567f, this.f2568g);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("clientPackageName", this.f2564c).m3842a("locale", this.f2565d).m3842a("accountName", this.f2566e).m3842a("gCoreClientName", this.f2567f).m3842a("chargedPackageName", this.f2568g).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        av avVar = CREATOR;
        av.m4034a(this, parcel, i);
    }
}
