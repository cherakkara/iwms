package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;

/* renamed from: com.google.android.m4b.maps.an.l */
public final class Raster implements bc {
    private final byte[] f3675a;
    private final int f3676b;
    private final Style f3677c;
    private final int[] f3678d;

    public Raster(int i, int i2, byte[] bArr, int i3, Style style, int[] iArr) {
        this.f3675a = bArr;
        this.f3676b = i3;
        this.f3677c = style;
        this.f3678d = iArr;
    }

    public final int m6034b() {
        return 6;
    }

    public final Style m6036e() {
        return this.f3677c;
    }

    public final int m6037f() {
        return this.f3676b;
    }

    public final byte[] m6033a() {
        return this.f3675a;
    }

    public final int[] m6038k() {
        return this.f3678d;
    }

    public final FeatureId m6035d() {
        return FeatureId.f7848a;
    }

    public final int m6039q() {
        return this.f3675a.length + 36;
    }
}
