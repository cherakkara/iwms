package com.google.android.m4b.maps.an;

import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.an.j */
public final class Polygon2D extends Region2D {
    private Point[] f3667a;
    private volatile Rectangle2D f3668b;

    public Polygon2D(Point[] pointArr) {
        this.f3667a = pointArr;
    }

    public final int m6003b() {
        return this.f3667a.length;
    }

    public final Point m6000a(int i) {
        return this.f3667a[i];
    }

    public final Rectangle2D m6001a() {
        if (this.f3668b == null) {
            this.f3668b = Rectangle2D.m6042a(this.f3667a);
        }
        return this.f3668b;
    }

    public final boolean m6002a(Point point) {
        if (!m6001a().m6046a(point)) {
            return false;
        }
        int length = this.f3667a.length;
        int i = 0;
        Point point2 = this.f3667a[length - 1];
        int i2 = 0;
        while (i < length) {
            Point point3 = this.f3667a[i];
            if (PointUtil2D.m5994b(point2, point3, point)) {
                i2++;
            }
            i++;
            point2 = point3;
        }
        return (i2 & 1) == 1;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f3667a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Polygon2D)) {
            return false;
        }
        return Arrays.equals(this.f3667a, ((Polygon2D) obj).f3667a);
    }
}
