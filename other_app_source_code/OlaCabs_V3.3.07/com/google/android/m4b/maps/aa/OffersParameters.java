package com.google.android.m4b.maps.aa;

import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.aa.e */
public final class OffersParameters {
    private final int f2972a;
    private final int f2973b;
    private final int f2974c;
    private final long f2975d;
    private final long f2976e;
    private final String f2977f;
    private String f2978g;

    public final String toString() {
        return "mapMoveDelayInMs: " + this.f2972a + " refreshPeriodInMs: " + this.f2973b + " minZoomLevel: " + this.f2974c + " distanceThresholdInMeters: " + this.f2975d + " useSavedSearchDistanceThresholdInmeters: " + this.f2976e + " mobileOffersHubBaseUrl: " + this.f2977f + " offersHubLogUrl: " + this.f2978g;
    }

    public OffersParameters(ProtoBuf protoBuf) {
        this.f2972a = protoBuf.m10204d(1);
        this.f2973b = protoBuf.m10204d(2);
        this.f2974c = protoBuf.m10204d(3);
        this.f2975d = protoBuf.m10207e(4);
        this.f2976e = protoBuf.m10207e(6);
        this.f2977f = protoBuf.m10212h(5);
        this.f2978g = protoBuf.m10212h(7);
    }
}
