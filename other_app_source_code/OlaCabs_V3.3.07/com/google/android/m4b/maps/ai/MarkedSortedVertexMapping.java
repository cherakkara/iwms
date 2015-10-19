package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.ai.Polygon2dTessellator.Polygon2dTessellator;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.ai.c */
final class MarkedSortedVertexMapping extends SortedVertexMapping {
    private boolean[] f3120d;

    MarkedSortedVertexMapping(PolygonVertexList polygonVertexList) {
        super(polygonVertexList);
        this.f3120d = new boolean[(m4966d() + 2)];
        Arrays.fill(this.f3120d, false);
        this.f3120d[0] = true;
    }

    public final boolean m5000a(int i, int i2) {
        int f = this.a.m4925f(i);
        int f2 = this.a.m4925f(i2);
        if (f == f2) {
            return false;
        }
        if (this.f3120d[f] && this.f3120d[f2]) {
            return false;
        }
        if (this.f3120d[f]) {
            this.f3120d[f2] = true;
            return true;
        } else if (this.f3120d[f2]) {
            this.f3120d[f] = true;
            return true;
        } else {
            throw new Polygon2dTessellator("Some outer chains have not been cut.");
        }
    }

    public final SortedVertexMapping m4999a(int[] iArr) {
        if (m4966d() == 0) {
            return this;
        }
        double[] dArr = new double[((m4968e() + iArr.length) * 2)];
        int g = this.a.m4926g(1);
        this.a.m4919a(0, dArr, 0, g);
        Arrays.fill(this.f3120d, false);
        this.f3120d[0] = true;
        for (int i = 0; i < iArr.length; i += 2) {
            int f;
            int i2;
            int i3 = iArr[i];
            int i4 = iArr[i + 1];
            int f2 = this.a.m4925f(i4);
            if (this.f3120d[f2]) {
                f = this.a.m4925f(i3);
                i2 = i3;
                f2 = i4;
            } else {
                f = f2;
                i2 = i4;
                f2 = i3;
            }
            int g2 = this.a.m4926g(f);
            int g3 = this.a.m4926g(f + 1);
            double a = this.a.m4915a(f2);
            double b = this.a.m4921b(f2);
            int a2 = MarkedSortedVertexMapping.m4997a(dArr, 2, a, b, 0, g);
            if (MarkedSortedVertexMapping.m4997a(dArr, 2, a, b, a2 + 1, g) != -1) {
                Object obj = null;
                int i5 = a2;
                while (obj == null) {
                    f2 = (i5 - 1) % g;
                    if (f2 < 0) {
                        f2 += g;
                    }
                    int i6 = (i5 + 1) % g;
                    if (i6 < 0) {
                        i6 += g;
                    }
                    boolean a3 = MarkedSortedVertexMapping.m4998a(dArr[f2 * 2], dArr[(f2 * 2) + 1], dArr[i5 * 2], dArr[(i5 * 2) + 1], dArr[i6 * 2], dArr[(i6 * 2) + 1]);
                    boolean a4 = MarkedSortedVertexMapping.m4998a(m4959b(i2), m4963c(i2), dArr[i5 * 2], dArr[(i5 * 2) + 1], dArr[i6 * 2], dArr[(i6 * 2) + 1]);
                    boolean a5 = MarkedSortedVertexMapping.m4998a(dArr[f2 * 2], dArr[(f2 * 2) + 1], dArr[i5 * 2], dArr[(i5 * 2) + 1], m4959b(i2), m4963c(i2));
                    if (a3 ? a4 && a5 : a4 || a5) {
                        i5 = MarkedSortedVertexMapping.m4997a(dArr, 2, a, b, i5 + 1, g);
                    } else {
                        obj = 1;
                    }
                }
                f2 = i5;
            } else {
                f2 = a2;
            }
            if (this.a.m4915a(i2) == a && this.a.m4921b(i2) == b) {
                System.arraycopy(dArr, (f2 + 1) * 2, dArr, ((f2 + g3) - g2) * 2, ((g - f2) - 1) * 2);
                f2++;
                i3 = (g3 - i2) - 1;
                this.a.m4919a(i2, dArr, f2, i3);
                this.a.m4919a(g2, dArr, f2 + i3, (i2 - g2) + 1);
                g += (g3 - g2) + 2;
                this.f3120d[f] = true;
            } else {
                System.arraycopy(dArr, f2 * 2, dArr, (((f2 + g3) - g2) + 2) * 2, (g - f2) * 2);
                f2++;
                i3 = g3 - i2;
                this.a.m4919a(i2, dArr, f2, i3);
                this.a.m4919a(g2, dArr, f2 + i3, (i2 - g2) + 1);
                g += (g3 - g2) + 2;
                this.f3120d[f] = true;
            }
        }
        super(dArr);
        return this;
    }

    private static int m4997a(double[] dArr, int i, double d, double d2, int i2, int i3) {
        int i4 = i2;
        while (i4 < i3) {
            if (dArr[i4 * 2] == d && dArr[(i4 * 2) + 1] == d2) {
                return i4;
            }
            i4++;
        }
        return -1;
    }

    private static boolean m4998a(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d - d3) * (d6 - d4)) - ((d2 - d4) * (d5 - d3)) > 0.0d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MarkedSortedVertexMapping)) {
            return false;
        }
        MarkedSortedVertexMapping markedSortedVertexMapping = (MarkedSortedVertexMapping) obj;
        if ((this instanceof MarkedSortedVertexMapping) && super.equals(markedSortedVertexMapping) && !Arrays.equals(this.f3120d, markedSortedVertexMapping.f3120d)) {
            return true;
        }
        return false;
    }

    public final boolean m5001a(Object obj) {
        return obj instanceof MarkedSortedVertexMapping;
    }

    public final int hashCode() {
        return super.hashCode() + (Arrays.hashCode(this.f3120d) * 31);
    }
}
