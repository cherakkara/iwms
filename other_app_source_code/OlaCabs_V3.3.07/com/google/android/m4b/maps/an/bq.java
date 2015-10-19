package com.google.android.m4b.maps.an;

import com.google.p025a.p026a.Preconditions;

/* compiled from: MapsEngineFeaturePosition */
public final class bq {
    private final String f3622a;
    private final Point f3623b;
    private final Rectangle2D f3624c;

    public bq(String str, Point point, Rectangle2D rectangle2D) {
        this.f3622a = (String) Preconditions.m1817a((Object) str);
        this.f3623b = point;
        this.f3624c = rectangle2D;
    }

    public final String m5872a() {
        return this.f3622a;
    }

    public final Point m5873b() {
        return this.f3623b;
    }

    public final Rectangle2D m5874c() {
        return this.f3624c;
    }
}
