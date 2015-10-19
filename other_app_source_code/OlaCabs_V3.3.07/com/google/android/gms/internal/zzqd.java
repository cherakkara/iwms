package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.olacabs.customer.utils.Constants;
import java.util.List;

@Deprecated
public final class zzqd implements SafeParcelable {
    public static final at CREATOR;
    public final int f2556a;
    public final String f2557b;
    public final String f2558c;
    public final String f2559d;
    public final String f2560e;
    public final List<String> f2561f;

    static {
        CREATOR = new at();
    }

    public zzqd(int i, String str, String str2, String str3, String str4, List<String> list) {
        this.f2556a = i;
        this.f2557b = str;
        this.f2558c = str2;
        this.f2559d = str3;
        this.f2560e = str4;
        this.f2561f = list;
    }

    public int describeContents() {
        at atVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqd)) {
            return false;
        }
        zzqd com_google_android_gms_internal_zzqd = (zzqd) obj;
        return C0452t.m3845a(this.f2557b, com_google_android_gms_internal_zzqd.f2557b) && C0452t.m3845a(this.f2558c, com_google_android_gms_internal_zzqd.f2558c) && C0452t.m3845a(this.f2559d, com_google_android_gms_internal_zzqd.f2559d) && C0452t.m3845a(this.f2560e, com_google_android_gms_internal_zzqd.f2560e) && C0452t.m3845a(this.f2561f, com_google_android_gms_internal_zzqd.f2561f);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2557b, this.f2558c, this.f2559d, this.f2560e);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a(Constants.BUNDLE_NAME, this.f2557b).m3842a(Constants.BUNDLE_ADDRESS, this.f2558c).m3842a("internationalPhoneNumber", this.f2559d).m3842a("regularOpenHours", this.f2560e).m3842a("attributions", this.f2561f).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        at atVar = CREATOR;
        at.m4030a(this, parcel, i);
    }
}
