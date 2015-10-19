package com.google.android.m4b.maps.an;

import java.util.Arrays;

/* compiled from: Frustum2D */
public final class bd extends Region2D {
    private Point[] f3562a;
    private Rectangle2D f3563b;

    protected bd(Point[] pointArr) {
        this.f3562a = pointArr;
        this.f3563b = Rectangle2D.m6042a(pointArr);
    }

    public static bd m5753a(Point point, Point point2, Point point3, Point point4) {
        return new bd(new Point[]{point, point2, point4, point3});
    }

    public final int m5757b() {
        return 4;
    }

    public final Point m5754a(int i) {
        return this.f3562a[i];
    }

    public final Point m5761h() {
        return this.f3562a[3];
    }

    public final Rectangle2D m5755a() {
        return this.f3563b;
    }

    public final Point m5759c() {
        return this.f3562a[2];
    }

    public final Point m5760d() {
        return this.f3562a[3];
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f3562a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bd)) {
            return false;
        }
        return Arrays.equals(this.f3562a, ((bd) obj).f3562a);
    }

    public final boolean m5756a(Point point) {
        int i;
        if (PointUtil2D.m5994b(this.f3562a[0], this.f3562a[1], point)) {
            i = 1;
        } else {
            i = 0;
        }
        if (PointUtil2D.m5994b(this.f3562a[1], this.f3562a[2], point)) {
            i++;
        }
        if (PointUtil2D.m5994b(this.f3562a[2], this.f3562a[3], point)) {
            i++;
        }
        if (PointUtil2D.m5994b(this.f3562a[3], this.f3562a[0], point)) {
            i++;
        }
        if (i == 1) {
            return true;
        }
        return false;
    }

    public final boolean m5758b(Region2D region2D) {
        if (!this.f3563b.m6047a(region2D.m5747a())) {
            return false;
        }
        for (int i = 0; i < region2D.m5750b(); i++) {
            if (!m5756a(region2D.m5746a(i))) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        return "[" + this.f3562a[0] + "," + this.f3562a[1] + "," + this.f3562a[2] + "," + this.f3562a[3] + "]";
    }
}
