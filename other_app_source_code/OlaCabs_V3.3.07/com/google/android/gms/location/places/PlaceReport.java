package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.C0452t.C0451a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.newrelic.agent.android.api.common.WanType;

public class PlaceReport implements SafeParcelable {
    public static final C0541a CREATOR;
    final int f2677a;
    private final String f2678b;
    private final String f2679c;
    private final String f2680d;

    static {
        CREATOR = new C0541a();
    }

    PlaceReport(int i, String str, String str2, String str3) {
        this.f2677a = i;
        this.f2678b = str;
        this.f2679c = str2;
        this.f2680d = str3;
    }

    public String m4394a() {
        return this.f2678b;
    }

    public String m4395b() {
        return this.f2679c;
    }

    public String m4396c() {
        return this.f2680d;
    }

    public int describeContents() {
        C0541a c0541a = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return C0452t.m3845a(this.f2678b, placeReport.f2678b) && C0452t.m3845a(this.f2679c, placeReport.f2679c) && C0452t.m3845a(this.f2680d, placeReport.f2680d);
    }

    public int hashCode() {
        return C0452t.m3843a(this.f2678b, this.f2679c, this.f2680d);
    }

    public String toString() {
        C0451a a = C0452t.m3844a((Object) this);
        a.m3842a("placeId", this.f2678b);
        a.m3842a("tag", this.f2679c);
        if (!WanType.UNKNOWN.equals(this.f2680d)) {
            a.m3842a("source", this.f2680d);
        }
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0541a c0541a = CREATOR;
        C0541a.m4397a(this, parcel, i);
    }
}
