package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.model.LatLng;

/* compiled from: R3 */
public final class ao {
    public final double f5659a;
    public final double f5660b;
    public final double f5661c;

    public ao(double d, double d2, double d3) {
        this.f5659a = d;
        this.f5660b = d2;
        this.f5661c = d3;
    }

    public static ao m8578a(LatLng latLng) {
        double toRadians = Math.toRadians(latLng.f7554a);
        double toRadians2 = Math.toRadians(latLng.f7555b);
        double cos = Math.cos(toRadians);
        return new ao(Math.cos(toRadians2) * cos, Math.sin(toRadians2) * cos, Math.sin(toRadians));
    }
}
