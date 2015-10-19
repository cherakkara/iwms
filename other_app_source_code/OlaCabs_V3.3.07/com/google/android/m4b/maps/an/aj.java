package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;
import java.io.DataInput;

/* compiled from: TrafficRoad */
public final class aj implements bc {
    private final Polyline f3461a;
    private final Style f3462b;
    private final String f3463c;
    private final int f3464d;
    private final int f3465e;
    private final int[] f3466f;

    private aj(int i, Polyline polyline, Style style, int i2, String str, int i3, int i4, int[] iArr) {
        this.f3461a = polyline;
        this.f3462b = style;
        this.f3463c = str;
        this.f3465e = i3;
        this.f3464d = i4;
        this.f3466f = iArr;
    }

    public static bc m5552a(DataInput dataInput, ae aeVar, RenderOpInfo renderOpInfo) {
        Polyline a = Polyline.m6011a(dataInput, aeVar.m5451b());
        StyleInfo a2 = StyleInfo.m6111a(dataInput, aeVar);
        int a3 = an.m5578a(dataInput);
        int readInt = dataInput.readInt();
        int a4 = an.m5578a(dataInput);
        int[] iArr = new int[a4];
        for (int i = 0; i < a4; i++) {
            iArr[i] = an.m5578a(dataInput);
        }
        return new aj(renderOpInfo.m6056a(), a, a2.m6112a(), a2.m6114c(), a2.m6113b(), a3, readInt, iArr);
    }

    public final Polyline m5553a() {
        return this.f3461a;
    }

    public final boolean m5555c() {
        return ModelUtil.m5887a(this.f3464d, 1);
    }

    public final int m5559g() {
        return this.f3465e;
    }

    public final int m5558f() {
        return 0;
    }

    public final Style m5557e() {
        return this.f3462b;
    }

    public final int m5554b() {
        return 9;
    }

    public final int[] m5560k() {
        return this.f3466f;
    }

    public final FeatureId m5556d() {
        return FeatureId.f7848a;
    }

    public final int m5561q() {
        return ((this.f3461a.m6032i() + 44) + ModelUtil.m5885a(this.f3463c)) + ModelUtil.m5883a(this.f3462b);
    }
}
