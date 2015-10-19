package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;

public final class MapsEngineLayerInfo implements C0591c {
    public static final MapsEngineLayerInfoCreator CREATOR;
    private final int f7564a;
    private String f7565b;
    private String f7566c;
    private String f7567d;
    private boolean f7568e;
    private String f7569f;

    static {
        CREATOR = new MapsEngineLayerInfoCreator();
    }

    public MapsEngineLayerInfo() {
        this.f7569f = "published";
        this.f7564a = 1;
    }

    MapsEngineLayerInfo(int i, String str, String str2, String str3, boolean z, String str4) {
        this.f7569f = "published";
        this.f7564a = i;
        this.f7565b = str;
        this.f7566c = str2;
        this.f7569f = str4;
        this.f7567d = str3;
        this.f7568e = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        MapsEngineLayerInfoCreator.m11015a(this, parcel);
    }

    public final int describeContents() {
        return 0;
    }

    final int m10747a() {
        return this.f7564a;
    }

    public final String m10748b() {
        return this.f7565b;
    }

    public final String m10749c() {
        return this.f7566c;
    }

    public final String m10750d() {
        return this.f7569f;
    }

    public final String m10751e() {
        return this.f7567d;
    }

    public final boolean m10752f() {
        return this.f7568e;
    }
}
