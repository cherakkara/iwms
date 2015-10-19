package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zztj implements SafeParcelable {
    public static final be CREATOR;
    public final int f2592a;
    public final String f2593b;
    public final int f2594c;
    public final int f2595d;
    public final String f2596e;
    public final String f2597f;
    public final boolean f2598g;
    public final String f2599h;

    static {
        CREATOR = new be();
    }

    public zztj(int i, String str, int i2, int i3, String str2, String str3, boolean z, String str4) {
        this.f2592a = i;
        this.f2593b = str;
        this.f2594c = i2;
        this.f2595d = i3;
        this.f2596e = str2;
        this.f2597f = str3;
        this.f2598g = z;
        this.f2599h = str4;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zztj)) {
            return false;
        }
        zztj com_google_android_gms_internal_zztj = (zztj) obj;
        return this.f2593b.equals(com_google_android_gms_internal_zztj.f2593b) && this.f2594c == com_google_android_gms_internal_zztj.f2594c && this.f2595d == com_google_android_gms_internal_zztj.f2595d && C0452t.m3845a(this.f2599h, com_google_android_gms_internal_zztj.f2599h) && C0452t.m3845a(this.f2596e, com_google_android_gms_internal_zztj.f2596e) && C0452t.m3845a(this.f2597f, com_google_android_gms_internal_zztj.f2597f) && this.f2598g == com_google_android_gms_internal_zztj.f2598g;
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2593b, Integer.valueOf(this.f2594c), Integer.valueOf(this.f2595d), this.f2596e, this.f2597f, Boolean.valueOf(this.f2598g));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PlayLoggerContext[");
        stringBuilder.append("package=").append(this.f2593b).append(',');
        stringBuilder.append("versionCode=").append(this.f2592a).append(',');
        stringBuilder.append("logSource=").append(this.f2595d).append(',');
        stringBuilder.append("logSourceName=").append(this.f2599h).append(',');
        stringBuilder.append("uploadAccount=").append(this.f2596e).append(',');
        stringBuilder.append("loggingId=").append(this.f2597f).append(',');
        stringBuilder.append("logAndroidId=").append(this.f2598g);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        be.m4061a(this, parcel, i);
    }
}
