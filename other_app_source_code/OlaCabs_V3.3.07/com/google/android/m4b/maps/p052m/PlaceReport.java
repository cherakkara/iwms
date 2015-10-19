package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.m.e */
public final class PlaceReport implements C0591c {
    public static final PlaceReportCreator CREATOR;
    final int f7503a;
    private final String f7504b;
    private final String f7505c;

    static {
        CREATOR = new PlaceReportCreator();
    }

    PlaceReport(int i, String str, String str2) {
        this.f7503a = i;
        this.f7504b = str;
        this.f7505c = str2;
    }

    public final String m10683a() {
        return this.f7504b;
    }

    public final String m10684b() {
        return this.f7505c;
    }

    public final String toString() {
        return Objects.m10456a(this).m10455a("mPlaceId", this.f7504b).m10455a("mTag", this.f7505c).toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        if (Objects.m10457a(this.f7504b, placeReport.f7504b) && Objects.m10457a(this.f7505c, placeReport.f7505c)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f7504b, this.f7505c});
    }

    public final int describeContents() {
        PlaceReportCreator placeReportCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        PlaceReportCreator placeReportCreator = CREATOR;
        PlaceReportCreator.m10686a(this, parcel);
    }
}
