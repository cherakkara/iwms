package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;

/* renamed from: com.google.android.m4b.maps.an.d */
public final class ModelUtil {
    public static int m5881a(bk bkVar) {
        return bkVar == null ? 0 : bkVar.m5843d();
    }

    public static int m5882a(Point point) {
        return point == null ? 0 : 24;
    }

    public static int m5884a(FeatureId featureId) {
        return featureId == null ? 0 : FeatureId.m11292b();
    }

    public static int m5883a(Style style) {
        return style == null ? 0 : style.m6106m();
    }

    public static int m5885a(String str) {
        if (str == null) {
            return 0;
        }
        return ((((str.length() + 1) / 4) * 4) * 2) + 40;
    }

    public static boolean m5887a(int i, int i2) {
        return (i & i2) != 0;
    }

    public static void m5886a(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >> 16);
        i3 = i4 + 1;
        bArr[i4] = (byte) (i >> 8);
        bArr[i3] = (byte) i;
    }
}
