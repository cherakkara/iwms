package com.google.android.m4b.maps.au;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/* renamed from: com.google.android.m4b.maps.au.c */
public class FeatureMapRasterParser {
    private List<String> f4074a;
    private byte[] f4075b;

    /* renamed from: com.google.android.m4b.maps.au.c.a */
    public static class FeatureMapRasterParser extends Exception {
        public FeatureMapRasterParser(String str) {
            super(str);
        }
    }

    static {
        Logger.getLogger(FeatureMapRasterParser.class.getCanonicalName());
    }

    public FeatureMapRasterParser(List<String> list, byte[] bArr) {
        boolean z = true;
        this.f4074a = null;
        this.f4075b = null;
        Preconditions.m1817a((Object) list);
        Preconditions.m1823a(list.size() > 0, (Object) "The list of featureIds is empty");
        Preconditions.m1817a((Object) bArr);
        if (bArr.length <= 0) {
            z = false;
        }
        Preconditions.m1823a(z, (Object) "The featureMapRaster is empty");
        this.f4074a = list;
        this.f4075b = bArr;
    }

    public final List<String> m6617a(int i, int i2) {
        int i3 = 0;
        if (m6615c(0) != 0) {
            FeatureMapRasterParser.m6613a(this.f4075b);
        }
        List a = ar.m2515a();
        int a2 = m6612a(3);
        int d = m6616d(i2);
        a2 = i2 == a2 + -1 ? this.f4075b.length : m6616d(i2 + 1);
        int i4 = d;
        d = 0;
        while (d <= i && i4 < a2) {
            int c = m6615c(i4);
            i4++;
            switch (c) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    d++;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    d += m6615c(i4);
                    i4++;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    d += m6612a(i4);
                    i4 += 2;
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    a.add(Integer.valueOf(m6615c(i4)));
                    i4++;
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    a.add(Integer.valueOf(m6612a(i4)));
                    i4 += 2;
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    a.add(Integer.valueOf(m6614b(i4)));
                    i4 += 3;
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    a.clear();
                    break;
                default:
                    break;
            }
        }
        List<String> a3 = ar.m2515a();
        while (i3 < a.size()) {
            if (((Integer) a.get(i3)).intValue() > this.f4074a.size() - 1) {
                throw new FeatureMapRasterParser("FeatureIndex is bigger than the total number of features available");
            }
            a3.add(this.f4074a.get(((Integer) a.get(i3)).intValue()));
            i3++;
        }
        if (a3.size() > 1) {
            Set hashSet = new HashSet();
            Collection a4 = ar.m2515a();
            for (String str : a3) {
                if (hashSet.add(str)) {
                    a4.add(str);
                }
            }
            a3.clear();
            a3.addAll(a4);
        }
        return a3;
    }

    private int m6612a(int i) {
        if (i + 1 > this.f4075b.length) {
            FeatureMapRasterParser.m6613a(this.f4075b);
        }
        return (m6615c(i) << 6) + m6615c(i + 1);
    }

    private int m6614b(int i) {
        if (i + 2 > this.f4075b.length) {
            FeatureMapRasterParser.m6613a(this.f4075b);
        }
        return ((m6615c(i) << 12) + (m6615c(i + 1) << 6)) + m6615c(i + 2);
    }

    private int m6615c(int i) {
        if (i > this.f4075b.length) {
            FeatureMapRasterParser.m6613a(this.f4075b);
        }
        return this.f4075b[i] - 63;
    }

    private int m6616d(int i) {
        return m6614b((i * 3) + 5);
    }

    private static void m6613a(byte[] bArr) {
        String str = new String(bArr);
        if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        throw new FeatureMapRasterParser("Invalid featureMap raster:" + str);
    }
}
