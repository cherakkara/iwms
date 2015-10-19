package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;

/* compiled from: Area */
public final class av implements bc {
    private final FeatureId f3527a;
    private final al f3528b;
    private final byte[] f3529c;
    private final Style f3530d;
    private final int f3531e;
    private final String f3532f;
    private final int f3533g;
    private final int f3534h;
    private final int[] f3535i;

    public av(int i, FeatureId featureId, al alVar, byte[] bArr, Style style, int i2, String str, int i3, int i4, int[] iArr) {
        this.f3527a = featureId;
        this.f3528b = alVar;
        this.f3529c = bArr;
        this.f3530d = style;
        this.f3531e = i2;
        this.f3532f = str;
        this.f3533g = i3;
        this.f3534h = i4;
        this.f3535i = iArr;
    }

    public final FeatureId m5697d() {
        return this.f3527a;
    }

    public final al m5694a() {
        return this.f3528b;
    }

    public final boolean m5696c() {
        return this.f3529c != null;
    }

    public final byte[] m5700g() {
        return this.f3529c;
    }

    public final Style m5698e() {
        return this.f3530d;
    }

    public final int m5701h() {
        return this.f3531e;
    }

    public final String m5702i() {
        return this.f3532f;
    }

    public final int m5695b() {
        return 3;
    }

    public final int m5699f() {
        return this.f3533g;
    }

    public final boolean m5703j() {
        return ModelUtil.m5887a(this.f3534h, 4);
    }

    public final boolean m5705l() {
        return ModelUtil.m5887a(this.f3534h, 8);
    }

    public final int[] m5704k() {
        return this.f3535i;
    }

    public final int m5706q() {
        return ((this.f3528b.m5575b() + 60) + this.f3529c.length) + ((ModelUtil.m5884a(this.f3527a) + ModelUtil.m5885a(this.f3532f)) + ModelUtil.m5883a(this.f3530d));
    }
}
