package com.google.android.m4b.maps.an;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.p057t.FeatureId;

/* renamed from: com.google.android.m4b.maps.an.p */
public final class Road implements bc {
    private final FeatureId f3684a;
    private final Polyline f3685b;
    private final bk[] f3686c;
    private final Style f3687d;
    private final String f3688e;
    private final int f3689f;
    private final int f3690g;
    private final int f3691h;
    private final int[] f3692i;

    public Road(int i, FeatureId featureId, Polyline polyline, bk[] bkVarArr, Style style, int i2, String str, int i3, int i4, int i5, int[] iArr) {
        this.f3684a = featureId;
        this.f3685b = polyline;
        this.f3686c = bkVarArr;
        this.f3687d = style;
        this.f3688e = str;
        this.f3689f = i3;
        this.f3690g = i4;
        this.f3691h = i5;
        this.f3692i = iArr;
    }

    public final FeatureId m6061d() {
        return this.f3684a;
    }

    public final Polyline m6058a() {
        return this.f3685b;
    }

    public final bk m6057a(int i) {
        return this.f3686c[i];
    }

    public final int m6060c() {
        return this.f3686c == null ? 0 : this.f3686c.length;
    }

    public final Style m6062e() {
        return this.f3687d;
    }

    public final int m6063f() {
        return this.f3690g;
    }

    public final int m6059b() {
        return 2;
    }

    public final boolean m6064g() {
        return ModelUtil.m5887a(this.f3691h, 1);
    }

    public final boolean m6065h() {
        return (this.f3687d.m6100g() || m6066i()) && ((m6064g() || ModelUtil.m5887a(this.f3691h, 2)) && this.f3689f < AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS && !ModelUtil.m5887a(this.f3691h, 8));
    }

    public final boolean m6066i() {
        return ModelUtil.m5887a(this.f3691h, 32) && !ModelUtil.m5887a(this.f3691h, 8);
    }

    public final boolean m6067j() {
        return ModelUtil.m5887a(this.f3691h, 64);
    }

    public final boolean m6069l() {
        return ModelUtil.m5887a(this.f3691h, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
    }

    public final int[] m6068k() {
        return this.f3692i;
    }

    public final int m6070q() {
        int i = 0;
        int i2 = this.f3685b.m6032i() + 60;
        if (this.f3686c != null) {
            bk[] bkVarArr = this.f3686c;
            int i3 = 0;
            while (i3 < bkVarArr.length) {
                int d = bkVarArr[i3].m5843d() + i;
                i3++;
                i = d;
            }
        }
        return (((i + ModelUtil.m5884a(this.f3684a)) + ModelUtil.m5883a(this.f3687d)) + ModelUtil.m5885a(this.f3688e)) + i2;
    }
}
