package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;
import java.io.DataInput;

/* compiled from: Building */
public final class aw implements bc {
    private static final byte[] f3536l;
    private static final int[] f3537m;
    private final FeatureId f3538a;
    private final al f3539b;
    private final al f3540c;
    private final byte[] f3541d;
    private final Style f3542e;
    private final String f3543f;
    private final int f3544g;
    private final int f3545h;
    private final int f3546i;
    private final int f3547j;
    private final int[] f3548k;

    static {
        f3536l = new byte[]{(byte) 1, (byte) 2, (byte) 4};
        f3537m = new int[]{0, 1, 1, 2, 1, 2, 2, 3};
    }

    private aw(int i, FeatureId featureId, al alVar, al alVar2, byte[] bArr, int i2, int i3, Style style, int i4, String str, int i5, int i6, int[] iArr) {
        this.f3538a = featureId;
        this.f3539b = alVar;
        this.f3540c = alVar2;
        this.f3541d = bArr;
        this.f3544g = i2;
        this.f3545h = i3;
        this.f3542e = style;
        this.f3543f = str;
        this.f3546i = i5;
        this.f3547j = i6;
        this.f3548k = iArr;
    }

    public static aw m5707a(DataInput dataInput, ae aeVar, RenderOpInfo renderOpInfo) {
        int a;
        al a2 = al.m5569a(dataInput, aeVar.m5451b());
        al a3 = al.m5570a(dataInput, aeVar);
        if ((a3.m5571a() != 0 ? 1 : null) != null) {
            a = a3.m5571a();
        } else {
            a = a2.m5571a();
        }
        byte[] bArr = new byte[a];
        dataInput.readFully(bArr);
        int a4 = an.m5578a(dataInput);
        int a5 = an.m5578a(dataInput);
        StyleInfo a6 = StyleInfo.m6111a(dataInput, aeVar);
        byte readByte = dataInput.readByte();
        int readInt = dataInput.readInt();
        FeatureId featureId = null;
        if (ModelUtil.m5887a(1, readInt)) {
            featureId = FeatureId.m11290a(dataInput);
        } else if (ModelUtil.m5887a(2, readInt)) {
            featureId = FeatureId.m11293b(dataInput);
        }
        int a7 = an.m5578a(dataInput);
        int[] iArr = new int[a7];
        for (a = 0; a < a7; a++) {
            iArr[a] = an.m5578a(dataInput);
        }
        return new aw(renderOpInfo.m6056a(), featureId, a2, a3, bArr, a4, a5, a6.m6112a(), a6.m6114c(), a6.m6113b(), readByte, readInt, iArr);
    }

    public final FeatureId m5712d() {
        return this.f3538a;
    }

    public final al m5708a() {
        return this.f3539b;
    }

    public final boolean m5709a(int i, int i2) {
        return (this.f3541d[i] & f3536l[i2]) != 0;
    }

    public final int m5711c() {
        int i = 0;
        int i2 = 0;
        while (i < this.f3541d.length) {
            i2 += f3537m[this.f3541d[i] & 7];
            i++;
        }
        return i2;
    }

    public final boolean m5715g() {
        return ModelUtil.m5887a(this.f3547j, 4);
    }

    public final int m5716h() {
        return this.f3544g;
    }

    public final int m5717i() {
        return this.f3545h;
    }

    public final Style m5713e() {
        return this.f3542e;
    }

    public final int m5710b() {
        return 4;
    }

    public final int m5714f() {
        return this.f3546i;
    }

    public final int[] m5718k() {
        return this.f3548k;
    }

    public final int m5719q() {
        int i;
        int length = this.f3541d.length + (this.f3539b.m5575b() + 68);
        if (this.f3540c == null) {
            i = 0;
        } else {
            i = this.f3540c.m5575b();
        }
        return (((i + ModelUtil.m5884a(this.f3538a)) + ModelUtil.m5885a(this.f3543f)) + ModelUtil.m5883a(this.f3542e)) + length;
    }
}
