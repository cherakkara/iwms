package com.google.android.m4b.maps.p060x;

import android.view.animation.Interpolator;
import com.google.android.m4b.maps.an.Point;

/* renamed from: com.google.android.m4b.maps.x.j */
public final class PointAnimation extends ObjectAnimation<Point> {
    protected final /* synthetic */ void m11596a(Object obj) {
        ((Point) this.a).m5950b((Point) obj);
    }

    protected final /* bridge */ /* synthetic */ void m11597b(Object obj) {
        ((Point) this.b).m5950b((Point) obj);
    }

    protected final /* synthetic */ void m11598c(Object obj) {
        ((Point) this.c).m5950b((Point) obj);
    }

    public PointAnimation(Interpolator interpolator) {
        super(interpolator);
        this.a = new Point();
        this.b = new Point();
        this.c = new Point();
    }

    protected final void m11595a(long j) {
        Point.m5930a((Point) this.a, (Point) this.b, m8216c(j), (Point) this.c);
    }
}
