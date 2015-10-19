package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate.IMapsEngineFeatureDelegate;
import com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate;

/* renamed from: com.google.android.m4b.maps.be.v */
public final class MapsEngineFeatureImpl extends IMapsEngineFeatureDelegate {
    private final aa f6079a;
    private final String f6080b;
    private final LatLng f6081c;
    private String f6082d;
    private String f6083e;

    public MapsEngineFeatureImpl(aa aaVar, String str, LatLng latLng) {
        this.f6082d = null;
        this.f6083e = null;
        this.f6079a = aaVar;
        this.f6080b = str;
        this.f6081c = latLng;
    }

    public final aa m9548a() {
        return this.f6079a;
    }

    public final void m9549a(String str, String str2) {
        this.f6082d = str;
        this.f6083e = str2;
    }

    public final IMapsEngineLayerDelegate m9551b() {
        return this.f6079a;
    }

    public final String m9552c() {
        return this.f6080b;
    }

    public final LatLng m9553d() {
        return this.f6081c;
    }

    public final String m9554e() {
        return this.f6082d;
    }

    public final String m9555f() {
        return this.f6083e;
    }

    public final boolean m9550a(com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate iMapsEngineFeatureDelegate) {
        return equals(iMapsEngineFeatureDelegate);
    }

    public final int m9556g() {
        return hashCode();
    }
}
