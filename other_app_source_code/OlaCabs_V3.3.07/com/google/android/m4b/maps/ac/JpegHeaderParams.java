package com.google.android.m4b.maps.ac;

/* renamed from: com.google.android.m4b.maps.ac.b */
public final class JpegHeaderParams {
    private int f3051a;
    private int f3052b;
    private int f3053c;
    private int f3054d;
    private int f3055e;

    public JpegHeaderParams(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i5 == 0 || i5 == 1) {
            this.f3051a = i;
            this.f3052b = i2;
            this.f3053c = i3;
            this.f3054d = i4;
            this.f3055e = i5;
            return;
        }
        throw new IllegalArgumentException("qualityAlgorithm = " + i5);
    }

    public final int m4850a() {
        return this.f3051a;
    }

    public final int m4851b() {
        return this.f3052b;
    }

    public final int m4852c() {
        return this.f3053c;
    }

    public final int m4853d() {
        return this.f3054d;
    }

    public final int m4854e() {
        return this.f3055e;
    }
}
