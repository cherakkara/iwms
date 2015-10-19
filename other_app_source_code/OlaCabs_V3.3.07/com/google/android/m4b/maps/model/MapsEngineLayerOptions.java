package com.google.android.m4b.maps.model;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;

public final class MapsEngineLayerOptions implements C0591c {
    public static final MapsEngineLayerOptionsCreator CREATOR;
    private final int f7570a;
    private MapsEngineLayerInfo f7571b;
    private boolean f7572c;
    private float f7573d;
    private boolean f7574e;

    static {
        CREATOR = new MapsEngineLayerOptionsCreator();
    }

    public MapsEngineLayerOptions() {
        this.f7571b = new MapsEngineLayerInfo();
        this.f7572c = true;
        this.f7574e = true;
        this.f7570a = 1;
    }

    MapsEngineLayerOptions(int i, MapsEngineLayerInfo mapsEngineLayerInfo, boolean z, float f, boolean z2) {
        this.f7571b = new MapsEngineLayerInfo();
        this.f7572c = true;
        this.f7574e = true;
        this.f7570a = i;
        this.f7571b = mapsEngineLayerInfo;
        this.f7572c = z;
        this.f7573d = f;
        this.f7574e = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        MapsEngineLayerOptionsCreator.m11018a(this, parcel, i);
    }

    public final int describeContents() {
        return 0;
    }

    final int m10753a() {
        return this.f7570a;
    }

    public final MapsEngineLayerInfo m10754b() {
        return this.f7571b;
    }

    public final float m10755c() {
        return this.f7573d;
    }

    public final boolean m10756d() {
        return this.f7572c;
    }

    public final boolean m10757e() {
        return this.f7574e;
    }
}
