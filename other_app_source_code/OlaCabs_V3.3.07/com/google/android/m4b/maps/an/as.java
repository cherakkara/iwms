package com.google.android.m4b.maps.an;

import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: WrappedRectangle2D */
public final class as extends at {
    private Rectangle2D f3513b;
    private Point f3514c;
    private Point f3515d;
    private int f3516e;
    private int f3517f;
    private int f3518g;
    private volatile Point f3519h;
    private volatile Point f3520i;
    private volatile Point f3521j;
    private volatile Point f3522k;
    private volatile Point f3523l;
    private volatile Point f3524m;

    public final /* bridge */ /* synthetic */ Region2D m5682c() {
        return this.f3513b;
    }

    private as(Rectangle2D rectangle2D) {
        this.f3513b = rectangle2D;
        Point c = rectangle2D.m6050c();
        Point d = rectangle2D.m6051d();
        if (c.f3646a < 0) {
            this.f3516e = -c.f3646a;
        } else if (d.f3646a > 1073741824) {
            this.f3516e = 1073741824 - d.f3646a;
        }
        this.f3514c = new Point();
        c.m5965i(this.f3514c);
        this.f3515d = new Point();
        d.m5965i(this.f3515d);
        this.a = this.f3514c.f3646a > this.f3515d.f3646a;
        this.f3517f = c.f3646a + this.f3516e;
        this.f3518g = d.f3646a + this.f3516e;
    }

    public static as m5674a(Rectangle2D rectangle2D) {
        return new as(rectangle2D);
    }

    public final Rectangle2D m5680b() {
        return this.f3513b;
    }

    public final int m5683d() {
        return this.f3513b.m6053f();
    }

    public final int m5684e() {
        return this.f3515d.f3647b - this.f3514c.f3647b;
    }

    public final Point m5685f() {
        return this.f3514c;
    }

    public final Point m5686g() {
        return this.f3515d;
    }

    public final boolean m5678a(Point point) {
        int i = (point.f3646a + this.f3516e) & 1073741823;
        return i >= this.f3517f && i <= this.f3518g && point.f3647b >= this.f3514c.f3647b && point.f3647b <= this.f3515d.f3647b;
    }

    public final as m5675a() {
        return this;
    }

    public final boolean m5679a(Region2D region2D) {
        if (!this.a) {
            return this.f3513b.m6049b(region2D);
        }
        Rectangle2D a = region2D.m5747a();
        if (this.f3514c.f3647b > a.f3679a.f3647b || this.f3515d.f3647b < a.f3680b.f3647b) {
            return false;
        }
        int i = a.f3679a.f3646a;
        int i2 = a.f3680b.f3646a;
        if ((this.f3514c.f3646a <= i && 536870912 > i2) || (-536870912 <= i && this.f3515d.f3646a >= i2)) {
            return true;
        }
        if (i < -536870912) {
            i += 1073741824;
        } else if (i >= 536870912) {
            i -= 1073741824;
        }
        if (i2 < -536870912) {
            i2 += 1073741824;
        } else if (i2 >= 536870912) {
            i2 -= 1073741824;
        }
        if (this.f3514c.f3646a > i || this.f3515d.f3646a < r0) {
            return false;
        }
        return true;
    }

    public final boolean m5681b(Region2D region2D) {
        if (!this.a) {
            return this.f3513b.m6047a(region2D);
        }
        if (!(region2D instanceof Rectangle2D)) {
            return super.m5656b(region2D);
        }
        Rectangle2D rectangle2D = (Rectangle2D) region2D;
        if (this.f3514c.f3647b > rectangle2D.f3680b.f3647b || this.f3515d.f3647b < rectangle2D.f3679a.f3647b) {
            return false;
        }
        if ((this.f3514c.f3646a > rectangle2D.f3680b.f3646a || 536870912 <= rectangle2D.f3679a.f3646a) && (-536870912 > rectangle2D.f3680b.f3646a || this.f3515d.f3646a < rectangle2D.f3679a.f3646a)) {
            return false;
        }
        return true;
    }

    public final Point m5676a(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                if (this.f3519h == null) {
                    this.f3519h = new Point(this.f3515d.f3646a, this.f3514c.f3647b);
                }
                return this.f3519h;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return this.f3515d;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.f3520i == null) {
                    this.f3520i = new Point(this.f3514c.f3646a, this.f3515d.f3647b);
                }
                return this.f3520i;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return this.f3514c;
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    public final int m5687h() {
        return this.a ? 6 : 4;
    }

    public final void m5677a(int i, Point[] pointArr) {
        if (this.a) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    pointArr[0] = m5676a(0);
                    pointArr[1] = m5676a(1);
                    return;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    pointArr[0] = m5676a(1);
                    if (this.f3521j == null) {
                        this.f3521j = new Point(-536870913, this.f3515d.f3647b);
                    }
                    pointArr[1] = this.f3521j;
                    return;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    if (this.f3522k == null) {
                        this.f3522k = new Point(536870912, this.f3515d.f3647b);
                    }
                    pointArr[0] = this.f3522k;
                    pointArr[1] = m5676a(2);
                    return;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    pointArr[0] = m5676a(2);
                    pointArr[1] = m5676a(3);
                    return;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    pointArr[0] = m5676a(3);
                    if (this.f3523l == null) {
                        this.f3523l = new Point(536870912, this.f3514c.f3647b);
                    }
                    pointArr[1] = this.f3523l;
                    return;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    if (this.f3524m == null) {
                        this.f3524m = new Point(-536870913, this.f3514c.f3647b);
                    }
                    pointArr[0] = this.f3524m;
                    pointArr[1] = m5676a(0);
                    return;
                default:
                    return;
            }
        }
        pointArr[0] = m5676a(i);
        pointArr[1] = m5676a((i + 1) % 4);
    }
}
