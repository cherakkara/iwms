package com.google.android.m4b.maps.p057t;

/* renamed from: com.google.android.m4b.maps.t.b */
public final class GeoPoint {
    private final int f7855a;
    private final int f7856b;

    public GeoPoint(int i, int i2) {
        if (i2 == -180000000) {
            i2 *= -1;
        }
        this.f7855a = i;
        this.f7856b = i2;
    }

    public final int m11304a() {
        return this.f7855a;
    }

    public final int m11305b() {
        return this.f7856b;
    }

    public final String toString() {
        return String.valueOf(this.f7855a) + "," + String.valueOf(this.f7856b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoPoint)) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        if (geoPoint.f7855a == this.f7855a && geoPoint.f7856b == this.f7856b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.f7855a * 29) + this.f7856b;
    }
}
