package com.google.android.m4b.maps.an;

import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.an.m */
public final class Rectangle2D extends Region2D {
    protected Point f3679a;
    protected Point f3680b;
    private volatile Point f3681c;
    private volatile Point f3682d;

    public Rectangle2D(Point point, Point point2) {
        this.f3679a = point;
        this.f3680b = point2;
    }

    public static Rectangle2D m6042a(Point[] pointArr) {
        Point point = pointArr[0];
        int i = point.f3646a;
        int i2 = point.f3647b;
        int i3 = i;
        int i4 = i;
        i = i2;
        for (int i5 = 1; i5 < pointArr.length; i5++) {
            Point point2 = pointArr[i5];
            if (point2.f3646a < i4) {
                i4 = point2.f3646a;
            }
            if (point2.f3646a > i3) {
                i3 = point2.f3646a;
            }
            if (point2.f3647b < i) {
                i = point2.f3647b;
            }
            if (point2.f3647b > i2) {
                i2 = point2.f3647b;
            }
        }
        return new Rectangle2D(new Point(i4, i), new Point(i3, i2));
    }

    public static Rectangle2D m6041a(Point point, Point point2) {
        int i;
        int i2;
        int i3;
        int i4;
        if (point.f3646a < point2.f3646a) {
            i = point.f3646a;
            i2 = point2.f3646a;
        } else {
            i = point2.f3646a;
            i2 = point.f3646a;
        }
        if (point.f3647b < point2.f3647b) {
            i3 = point.f3647b;
            i4 = point2.f3647b;
        } else {
            i3 = point2.f3647b;
            i4 = point.f3647b;
        }
        return new Rectangle2D(new Point(i, i3), new Point(i2, i4));
    }

    public static Rectangle2D m6040a(Point point, int i) {
        return new Rectangle2D(new Point(point.f3646a - i, point.f3647b - i), new Point(point.f3646a + i, point.f3647b + i));
    }

    public final Point m6050c() {
        return this.f3679a;
    }

    public final Point m6051d() {
        return this.f3680b;
    }

    public final Point m6052e() {
        return new Point((this.f3679a.f3646a + this.f3680b.f3646a) / 2, (this.f3679a.f3647b + this.f3680b.f3647b) / 2);
    }

    public final int m6053f() {
        return this.f3680b.f3646a - this.f3679a.f3646a;
    }

    public final int m6054g() {
        return this.f3680b.f3647b - this.f3679a.f3647b;
    }

    public final boolean m6046a(Point point) {
        return point.f3646a >= this.f3679a.f3646a && point.f3646a <= this.f3680b.f3646a && point.f3647b >= this.f3679a.f3647b && point.f3647b <= this.f3680b.f3647b;
    }

    public final Rectangle2D m6044a() {
        return this;
    }

    public final boolean m6049b(Region2D region2D) {
        Rectangle2D a = region2D.m5747a();
        return this.f3679a.f3646a <= a.f3679a.f3646a && this.f3679a.f3647b <= a.f3679a.f3647b && this.f3680b.f3646a >= a.f3680b.f3646a && this.f3680b.f3647b >= a.f3680b.f3647b;
    }

    public final boolean m6047a(Region2D region2D) {
        if (!(region2D instanceof Rectangle2D)) {
            return super.m5749a(region2D);
        }
        Rectangle2D rectangle2D = (Rectangle2D) region2D;
        return this.f3679a.f3646a <= rectangle2D.f3680b.f3646a && this.f3679a.f3647b <= rectangle2D.f3680b.f3647b && this.f3680b.f3646a >= rectangle2D.f3679a.f3646a && this.f3680b.f3647b >= rectangle2D.f3679a.f3647b;
    }

    public final int m6048b() {
        return 4;
    }

    public final Point m6043a(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                if (this.f3681c == null) {
                    this.f3681c = new Point(this.f3680b.f3646a, this.f3679a.f3647b);
                }
                return this.f3681c;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return this.f3680b;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.f3682d == null) {
                    this.f3682d = new Point(this.f3679a.f3646a, this.f3680b.f3647b);
                }
                return this.f3682d;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return this.f3679a;
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    public final Point m6055h() {
        return this.f3679a;
    }

    final void m6045a(Rectangle2D rectangle2D) {
        this.f3679a.f3646a = Math.min(this.f3679a.f3646a, rectangle2D.f3679a.f3646a);
        this.f3679a.f3647b = Math.min(this.f3679a.f3647b, rectangle2D.f3679a.f3647b);
        this.f3680b.f3646a = Math.max(this.f3680b.f3646a, rectangle2D.f3680b.f3646a);
        this.f3680b.f3647b = Math.max(this.f3680b.f3647b, rectangle2D.f3680b.f3647b);
        this.f3681c = null;
        this.f3682d = null;
    }

    public final int hashCode() {
        return ((this.f3680b.hashCode() + 31) * 31) + this.f3679a.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rectangle2D)) {
            return false;
        }
        Rectangle2D rectangle2D = (Rectangle2D) obj;
        if (rectangle2D.f3680b.equals(this.f3680b) && rectangle2D.f3679a.equals(this.f3679a)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "[" + this.f3679a + ", " + this.f3680b + "]";
    }
}
