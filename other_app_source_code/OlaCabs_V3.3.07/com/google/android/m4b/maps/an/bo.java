package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;

/* compiled from: LineMesh */
public final class bo implements bc {
    private Polyline f3614a;
    private Style f3615b;
    private final String f3616c;
    private int f3617d;
    private final int[] f3618e;

    public bo(int i, Polyline polyline, Style style, int i2, String str, int i3, int i4, int[] iArr) {
        this.f3614a = polyline;
        this.f3615b = style;
        this.f3616c = str;
        this.f3617d = i3;
        this.f3618e = iArr;
    }

    public final Polyline m5863a() {
        return this.f3614a;
    }

    public final Style m5866e() {
        return this.f3615b;
    }

    public final int m5867f() {
        return this.f3617d;
    }

    public final int m5864b() {
        return 5;
    }

    public final int[] m5868k() {
        return this.f3618e;
    }

    public final FeatureId m5865d() {
        return null;
    }

    public final int m5869q() {
        return ((this.f3614a.m6032i() + 44) + ModelUtil.m5885a(this.f3616c)) + ModelUtil.m5883a(this.f3615b);
    }
}
