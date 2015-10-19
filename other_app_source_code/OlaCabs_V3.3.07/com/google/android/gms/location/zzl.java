package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.sothree.slidinguppanel.p086a.R.R;

public class zzl implements SafeParcelable {
    public static final C0549r CREATOR;
    int f2707a;
    int f2708b;
    long f2709c;
    private final int f2710d;

    static {
        CREATOR = new C0549r();
    }

    public zzl(int i, int i2, int i3, long j) {
        this.f2710d = i;
        this.f2707a = i2;
        this.f2708b = i3;
        this.f2709c = j;
    }

    private String m4437a(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return "STATUS_SUCCESSFUL";
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return "STATUS_TIMED_OUT_ON_SCAN";
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return "STATUS_NO_INFO_IN_DATABASE";
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return "STATUS_INVALID_SCAN";
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return "STATUS_UNABLE_TO_QUERY_DATABASE";
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return "STATUS_SCANS_DISABLED_IN_SETTINGS";
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return "STATUS_LOCATION_DISABLED_IN_SETTINGS";
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                return "STATUS_IN_PROGRESS";
            default:
                return "STATUS_UNKNOWN";
        }
    }

    int m4438a() {
        return this.f2710d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl com_google_android_gms_location_zzl = (zzl) obj;
        return this.f2707a == com_google_android_gms_location_zzl.f2707a && this.f2708b == com_google_android_gms_location_zzl.f2708b && this.f2709c == com_google_android_gms_location_zzl.f2709c;
    }

    public int hashCode() {
        return C0452t.m3843a(Integer.valueOf(this.f2707a), Integer.valueOf(this.f2708b), Long.valueOf(this.f2709c));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LocationStatus[cell status: ").append(m4437a(this.f2707a));
        stringBuilder.append(", wifi status: ").append(m4437a(this.f2708b));
        stringBuilder.append(", elapsed realtime ns: ").append(this.f2709c);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0549r.m4430a(this, parcel, i);
    }
}
