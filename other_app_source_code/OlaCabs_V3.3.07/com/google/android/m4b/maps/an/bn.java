package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;
import java.io.DataInput;

/* compiled from: LineLabel */
public final class bn extends bm {
    public bn(int i, FeatureId featureId, Polyline polyline, bk[] bkVarArr, Style style, int i2, String str, int i3, float f, int i4, int[] iArr) {
        super(i, featureId, polyline, bkVarArr, style, i2, str, i3, f, i4, iArr);
    }

    public final int m5862b() {
        return 11;
    }

    public static bm m5861b(DataInput dataInput, ae aeVar, RenderOpInfo renderOpInfo) {
        return bm.m5849a(dataInput, aeVar, renderOpInfo, true);
    }
}
