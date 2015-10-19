package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class zzpg implements SafeParcelable {
    public static final al CREATOR;
    static final List<zzox> f2495a;
    LocationRequest f2496b;
    boolean f2497c;
    boolean f2498d;
    boolean f2499e;
    List<zzox> f2500f;
    final String f2501g;
    private final int f2502h;

    static {
        f2495a = Collections.emptyList();
        CREATOR = new al();
    }

    zzpg(int i, LocationRequest locationRequest, boolean z, boolean z2, boolean z3, List<zzox> list, String str) {
        this.f2502h = i;
        this.f2496b = locationRequest;
        this.f2497c = z;
        this.f2498d = z2;
        this.f2499e = z3;
        this.f2500f = list;
        this.f2501g = str;
    }

    private zzpg(String str, LocationRequest locationRequest) {
        this(1, locationRequest, false, true, true, f2495a, str);
    }

    public static zzpg m4252a(LocationRequest locationRequest) {
        return m4253a(null, locationRequest);
    }

    public static zzpg m4253a(String str, LocationRequest locationRequest) {
        return new zzpg(str, locationRequest);
    }

    int m4254a() {
        return this.f2502h;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzpg)) {
            return false;
        }
        zzpg com_google_android_gms_internal_zzpg = (zzpg) obj;
        return C0452t.m3845a(this.f2496b, com_google_android_gms_internal_zzpg.f2496b) && this.f2497c == com_google_android_gms_internal_zzpg.f2497c && this.f2498d == com_google_android_gms_internal_zzpg.f2498d && this.f2499e == com_google_android_gms_internal_zzpg.f2499e && C0452t.m3845a(this.f2500f, com_google_android_gms_internal_zzpg.f2500f);
    }

    public int hashCode() {
        return this.f2496b.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f2496b.toString());
        stringBuilder.append(" requestNlpDebugInfo=");
        stringBuilder.append(this.f2497c);
        stringBuilder.append(" restorePendingIntentListeners=");
        stringBuilder.append(this.f2498d);
        stringBuilder.append(" triggerUpdate=");
        stringBuilder.append(this.f2499e);
        stringBuilder.append(" clients=");
        stringBuilder.append(this.f2500f);
        if (this.f2501g != null) {
            stringBuilder.append(" tag=");
            stringBuilder.append(this.f2501g);
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        al.m4005a(this, parcel, i);
    }
}
