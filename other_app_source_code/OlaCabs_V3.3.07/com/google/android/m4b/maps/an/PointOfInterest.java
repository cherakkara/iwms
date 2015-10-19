package com.google.android.m4b.maps.an;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ab.SupportedCharacterChecker;
import com.google.android.m4b.maps.an.ab.Alignment;
import com.google.android.m4b.maps.p057t.FeatureId;
import java.io.DataInput;

/* renamed from: com.google.android.m4b.maps.an.h */
public final class PointOfInterest implements bc {
    private final ac f3649a;
    private final String f3650b;
    private final String f3651c;
    private final FeatureId f3652d;
    private final Style f3653e;
    private final int f3654f;
    private final int[] f3655g;
    private final int f3656h;
    private final int f3657i;
    private final int f3658j;
    private float f3659k;
    private float f3660l;
    private final AbsolutePosition[] f3661m;
    private final bk f3662n;
    private final bk f3663o;
    private final Alignment[] f3664p;
    private final String f3665q;
    private final String f3666r;

    public PointOfInterest(int i, ac acVar, Point point, FeatureId featureId, AbsolutePosition[] absolutePositionArr, bk bkVar, bk bkVar2, Alignment[] alignmentArr, String str, Style style, int i2, String str2, int i3, int i4, int i5, int i6, String str3, String str4, Alignment alignment, int[] iArr) {
        this.f3659k = -1.0f;
        this.f3660l = -1.0f;
        this.f3652d = featureId;
        this.f3661m = absolutePositionArr;
        this.f3662n = bkVar;
        this.f3663o = bkVar2;
        this.f3664p = alignmentArr;
        this.f3651c = str;
        this.f3653e = style;
        this.f3665q = str2;
        this.f3654f = i3;
        this.f3656h = i4;
        if (i5 == -1) {
            i5 = 30;
        }
        this.f3657i = i5;
        this.f3658j = i6;
        this.f3666r = str3;
        this.f3650b = str4;
        this.f3655g = iArr;
        this.f3649a = acVar;
    }

    public static PointOfInterest m5967a(DataInput dataInput, ae aeVar, RenderOpInfo renderOpInfo) {
        int i;
        String readUTF;
        Alignment alignment;
        int a = an.m5578a(dataInput);
        AbsolutePosition[] absolutePositionArr = new AbsolutePosition[a];
        ac b = aeVar.m5451b();
        aeVar.m5449a();
        for (i = 0; i < a; i++) {
            absolutePositionArr[i] = AbsolutePosition.m5404a(dataInput, b);
        }
        Point b2 = absolutePositionArr[0].m5406b();
        StyleInfo a2 = StyleInfo.m6111a(dataInput, aeVar);
        bk a3 = bk.m5837a(dataInput, aeVar, a2);
        bk a4 = bk.m5837a(dataInput, aeVar, a2);
        a = an.m5578a(dataInput);
        Alignment[] alignmentArr = new Alignment[a];
        for (i = 0; i < a; i++) {
            alignmentArr[i] = Alignment.m5421b(dataInput);
        }
        byte readByte = dataInput.readByte();
        byte readByte2 = dataInput.readByte();
        byte readByte3 = dataInput.readByte();
        int readInt = dataInput.readInt();
        FeatureId featureId = null;
        if (ModelUtil.m5887a(1, readInt)) {
            featureId = FeatureId.m11290a(dataInput);
        } else if (ModelUtil.m5887a(2, readInt)) {
            featureId = FeatureId.m11293b(dataInput);
        }
        String str = null;
        if (ModelUtil.m5887a(32, readInt)) {
            str = SupportedCharacterChecker.m4846a(dataInput.readUTF()).intern();
        }
        String str2 = null;
        if (ModelUtil.m5887a(64, readInt)) {
            str2 = dataInput.readUTF();
        }
        if (ModelUtil.m5887a(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, readInt)) {
            readUTF = dataInput.readUTF();
        } else {
            String a5 = a3.m5840a();
            String a6 = a4.m5840a();
            if (a5.length() > 0 && a6.length() > 0) {
                a5 = a5 + '\n';
            }
            readUTF = a5 + a6;
        }
        if (aeVar.m5449a() != 11) {
            alignment = Alignment.f3380c;
        } else if (ModelUtil.m5887a(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY, readInt)) {
            alignment = Alignment.m5421b(dataInput);
        } else {
            alignment = Alignment.f3380c;
        }
        a = an.m5578a(dataInput);
        int[] iArr = new int[a];
        for (i = 0; i < a; i++) {
            iArr[i] = an.m5578a(dataInput);
        }
        return new PointOfInterest(renderOpInfo.m6056a(), b, b2, featureId, absolutePositionArr, a3, a4, alignmentArr, str, a2.m6112a(), a2.m6114c(), a2.m6113b(), readByte, readByte2, readByte3, readInt, str2, readUTF, alignment, iArr);
    }

    public final ac m5968a() {
        return this.f3649a;
    }

    public final int m5970b() {
        return 7;
    }

    public final String m5972c() {
        return this.f3650b;
    }

    public final FeatureId m5973d() {
        return this.f3652d;
    }

    public final Style m5974e() {
        return this.f3653e;
    }

    public final int m5975f() {
        return this.f3654f;
    }

    public final int m5976g() {
        return this.f3656h;
    }

    public final int m5977h() {
        return this.f3657i;
    }

    public final float m5978i() {
        return this.f3659k;
    }

    public final float m5979j() {
        return this.f3660l;
    }

    public final void m5969a(float f) {
        this.f3659k = f;
    }

    public final void m5971b(float f) {
        this.f3660l = f;
    }

    public final int[] m5980k() {
        return this.f3655g;
    }

    public final AbsolutePosition[] m5981l() {
        return this.f3661m;
    }

    public final bk m5982m() {
        return this.f3662n;
    }

    public final bk m5983n() {
        return this.f3663o;
    }

    public final Alignment[] m5984o() {
        return this.f3664p;
    }

    public final boolean m5985p() {
        return ModelUtil.m5887a(16, this.f3658j);
    }

    public final int m5986q() {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 120;
        if (this.f3650b != null) {
            i4 = ModelUtil.m5885a(this.f3650b) + 120;
        }
        if (this.f3651c != null) {
            i4 += ModelUtil.m5885a(this.f3651c);
        }
        if (this.f3661m != null) {
            AbsolutePosition[] absolutePositionArr = this.f3661m;
            i = 0;
            i2 = 0;
            while (i < absolutePositionArr.length) {
                int d = absolutePositionArr[i].m5408d() + i2;
                i++;
                i2 = d;
            }
        } else {
            i2 = 0;
        }
        if (this.f3664p != null) {
            i = 0;
            while (i < this.f3664p.length) {
                i++;
                i3 = ab.m5418c() + i3;
            }
        }
        return i4 + (((((((i2 + i3) + ModelUtil.m5881a(this.f3662n)) + ModelUtil.m5881a(this.f3663o)) + ModelUtil.m5884a(this.f3652d)) + ModelUtil.m5883a(this.f3653e)) + ModelUtil.m5885a(this.f3665q)) + ModelUtil.m5885a(this.f3666r));
    }
}
