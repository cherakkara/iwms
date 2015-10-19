package com.google.android.m4b.maps.p044k;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.k.h */
public final class LocationStatus implements C0591c {
    public static final LocationStatusCreator CREATOR;
    int f7460a;
    int f7461b;
    long f7462c;
    private final int f7463d;

    static {
        CREATOR = new LocationStatusCreator();
    }

    LocationStatus(int i, int i2, int i3, long j) {
        this.f7463d = i;
        this.f7460a = i2;
        this.f7461b = i3;
        this.f7462c = j;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        LocationStatusCreator.m10561a(this, parcel);
    }

    final int m10559a() {
        return this.f7463d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f7460a), Integer.valueOf(this.f7461b), Long.valueOf(this.f7462c)});
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof LocationStatus)) {
            return false;
        }
        LocationStatus locationStatus = (LocationStatus) obj;
        if (this.f7460a == locationStatus.f7460a && this.f7461b == locationStatus.f7461b && this.f7462c == locationStatus.f7462c) {
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LocationStatus[cell status: ").append(LocationStatus.m10558a(this.f7460a));
        stringBuilder.append(", wifi status: ").append(LocationStatus.m10558a(this.f7461b));
        stringBuilder.append(", elapsed realtime ns: ").append(this.f7462c);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private static String m10558a(int i) {
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
}
