package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;
import java.io.DataInput;

/* compiled from: Line */
public class bm implements bc {
    private final FeatureId f3605a;
    private final Polyline f3606b;
    private final bk[] f3607c;
    private final Style f3608d;
    private final String f3609e;
    private final int f3610f;
    private final float f3611g;
    private final boolean f3612h;
    private final int[] f3613i;

    public bm(int i, FeatureId featureId, Polyline polyline, bk[] bkVarArr, Style style, int i2, String str, int i3, float f, int i4, int[] iArr) {
        this(i, featureId, polyline, bkVarArr, style, i2, str, i3, f, i4, iArr, false);
    }

    public bm(int i, FeatureId featureId, Polyline polyline, bk[] bkVarArr, Style style, int i2, String str, int i3, float f, int i4, int[] iArr, boolean z) {
        this.f3605a = featureId;
        this.f3606b = polyline;
        this.f3607c = bkVarArr;
        this.f3608d = style;
        this.f3609e = str;
        this.f3610f = i3;
        this.f3611g = f;
        this.f3613i = iArr;
        this.f3612h = z;
    }

    public static bm m5848a(DataInput dataInput, ae aeVar, RenderOpInfo renderOpInfo) {
        return m5849a(dataInput, aeVar, renderOpInfo, false);
    }

    protected static bm m5849a(DataInput dataInput, ae aeVar, RenderOpInfo renderOpInfo, boolean z) {
        int i;
        int i2 = 0;
        Polyline a = Polyline.m6011a(dataInput, aeVar.m5451b());
        StyleInfo a2 = StyleInfo.m6111a(dataInput, aeVar);
        int a3 = an.m5578a(dataInput);
        bk[] bkVarArr = new bk[a3];
        for (i = 0; i < a3; i++) {
            bkVarArr[i] = bk.m5837a(dataInput, aeVar, a2);
        }
        byte readByte = dataInput.readByte();
        float readByte2 = ((float) dataInput.readByte()) / 4.0f;
        int readInt = dataInput.readInt();
        FeatureId featureId = null;
        if (ModelUtil.m5887a(1, readInt)) {
            featureId = FeatureId.m11290a(dataInput);
        } else if (ModelUtil.m5887a(2, readInt)) {
            featureId = FeatureId.m11293b(dataInput);
        }
        i = an.m5578a(dataInput);
        int[] iArr = new int[i];
        while (i2 < i) {
            iArr[i2] = an.m5578a(dataInput);
            i2++;
        }
        return z ? new bn(renderOpInfo.m6056a(), featureId, a, bkVarArr, a2.m6112a(), a2.m6114c(), a2.m6113b(), readByte, readByte2, readInt, iArr) : new bm(renderOpInfo.m6056a(), featureId, a, bkVarArr, a2.m6112a(), a2.m6114c(), a2.m6113b(), readByte, readByte2, readInt, iArr);
    }

    public final FeatureId m5854d() {
        return this.f3605a;
    }

    public final Polyline m5851a() {
        return this.f3606b;
    }

    public final bk m5850a(int i) {
        return this.f3607c[i];
    }

    public final int m5853c() {
        return this.f3607c.length;
    }

    public final Style m5855e() {
        return this.f3608d;
    }

    public int m5852b() {
        return 8;
    }

    public final int m5856f() {
        return this.f3610f;
    }

    public final float m5857g() {
        return this.f3611g;
    }

    public final boolean m5858h() {
        return this.f3612h;
    }

    public final int[] m5859k() {
        return this.f3613i;
    }

    public final int m5860q() {
        int i = 0;
        int i2 = this.f3606b.m6032i();
        if (this.f3607c != null) {
            bk[] bkVarArr = this.f3607c;
            int i3 = 0;
            while (i3 < bkVarArr.length) {
                int d = bkVarArr[i3].m5843d() + i;
                i3++;
                i = d;
            }
        }
        return (i + (((ModelUtil.m5884a(this.f3605a) + 60) + ModelUtil.m5885a(this.f3609e)) + ModelUtil.m5883a(this.f3608d))) + i2;
    }
}
