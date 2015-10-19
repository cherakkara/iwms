package com.google.android.m4b.maps.an;

import java.lang.reflect.Array;
import java.util.Arrays;

/* compiled from: WrappedFrustum2D */
public final class ar extends at {
    private final Point[] f3507b;
    private final Point[] f3508c;
    private final bd f3509d;
    private final as f3510e;
    private final Rectangle2D f3511f;
    private Point[][] f3512g;

    private ar(Point[] pointArr) {
        int i;
        this.f3508c = new Point[4];
        for (i = 0; i < 4; i++) {
            this.f3508c[i] = new Point();
            pointArr[i].m5965i(this.f3508c[i]);
        }
        this.f3507b = pointArr;
        this.f3509d = new bd(pointArr);
        this.f3511f = this.f3509d.m5755a();
        this.f3510e = as.m5674a(this.f3511f);
        this.a = this.f3510e.a;
        if (this.a) {
            this.f3512g = (Point[][]) Array.newInstance(Point.class, new int[]{6, 2});
            int i2 = 0;
            i = 0;
            int i3 = 0;
            while (i2 < 4) {
                int i4 = !this.f3508c[i2].equals(this.f3507b[i2]) ? 1 : 0;
                if (i4 == i3) {
                    i4 = i3;
                } else if (i2 > 0 && i < 5) {
                    m5661a(this.f3507b[i2 - 1], this.f3507b[i2], i);
                    i++;
                }
                if (i2 > 0) {
                    this.f3512g[i - 1][1] = this.f3508c[i2];
                }
                this.f3512g[i][0] = this.f3508c[i2];
                i2++;
                i++;
                i3 = i4;
            }
            if (i < 6) {
                m5661a(this.f3507b[3], this.f3507b[0], i);
            }
            this.f3512g[5][1] = this.f3508c[0];
        }
    }

    private void m5661a(Point point, Point point2, int i) {
        int i2 = (int) (((((double) ((point2.f3646a > 0 ? 536870913 : -536870913) - point.f3646a)) / ((double) (point2.f3646a - point.f3646a))) * ((double) (point2.f3647b - point.f3647b))) + ((double) point.f3647b));
        if (point.f3646a > point2.f3646a) {
            this.f3512g[i - 1][1] = new Point(-536870913, i2);
            this.f3512g[i][0] = new Point(536870913, i2);
            return;
        }
        this.f3512g[i - 1][1] = new Point(536870913, i2);
        this.f3512g[i][0] = new Point(-536870913, i2);
    }

    public static ar m5660a(Point point, Point point2, Point point3, Point point4) {
        return new ar(new Point[]{point, point2, point4, point3});
    }

    public static ar m5659a(ar arVar, Point point, float f) {
        return m5660a(Point.m5926a(arVar.f3508c[0], point, 0.2f), Point.m5926a(arVar.f3508c[1], point, 0.2f), Point.m5926a(arVar.f3508c[3], point, 0.2f), Point.m5926a(arVar.f3508c[2], point, 0.2f));
    }

    public final as m5662a() {
        return this.f3510e;
    }

    public final Rectangle2D m5667b() {
        return this.f3511f;
    }

    public final Region2D m5668c() {
        return this.f3509d;
    }

    public final Point m5669d() {
        return this.f3508c[0];
    }

    public final Point m5670e() {
        return this.f3508c[1];
    }

    public final Point m5671f() {
        return this.f3508c[2];
    }

    public final Point m5672g() {
        return this.f3508c[3];
    }

    public final Point m5663a(int i) {
        return this.f3508c[i];
    }

    public final int m5673h() {
        return this.a ? 6 : 4;
    }

    public final void m5664a(int i, Point[] pointArr) {
        if (this.a) {
            pointArr[0] = this.f3512g[i][0];
            pointArr[1] = this.f3512g[i][1];
            return;
        }
        pointArr[0] = this.f3508c[i];
        pointArr[1] = this.f3508c[(i + 1) % 4];
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f3507b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ar)) {
            return false;
        }
        return Arrays.equals(this.f3507b, ((ar) obj).f3507b);
    }

    public final boolean m5665a(Point point) {
        if (!this.a) {
            return this.f3509d.m5756a(point);
        }
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            if (PointUtil2D.m5994b(this.f3512g[i2][0], this.f3512g[i2][1], point)) {
                i++;
            }
        }
        return i == 1;
    }

    public final boolean m5666a(Region2D region2D) {
        if (!this.f3510e.m5681b(region2D.m5747a())) {
            return false;
        }
        for (int i = 0; i < region2D.m5750b(); i++) {
            if (!m5665a(region2D.m5746a(i))) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        return "[" + this.f3507b[0] + "," + this.f3507b[1] + "," + this.f3507b[2] + "," + this.f3507b[3] + "]";
    }
}
