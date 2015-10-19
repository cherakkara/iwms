package com.google.android.m4b.maps.ax;

import android.util.FloatMath;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.p057t.GeoPoint;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.ax.c */
public final class CameraPosition implements CameraPositionProvider {
    private final Point f4808a;
    private final float f4809b;
    private final float f4810c;
    private final float f4811d;
    private final float f4812e;

    public CameraPosition(Point point, float f, float f2, float f3, float f4) {
        this.f4808a = new Point(point.m5958f(), point.m5960g());
        this.f4809b = Math.max(Math.min(f, 21.0f), dm.DEFAULT_BACKOFF_MULT);
        this.f4810c = f2;
        this.f4811d = f3;
        this.f4812e = f4;
    }

    public CameraPosition(GeoPoint geoPoint, float f, float f2, float f3, float f4) {
        this(Point.m5934b(geoPoint.m11304a(), geoPoint.m11305b()), f, f2, f3, f4);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        if (this.f4808a.equals(cameraPosition.f4808a) && this.f4809b == cameraPosition.f4809b && this.f4810c == cameraPosition.f4810c && this.f4811d == cameraPosition.f4811d && this.f4812e == cameraPosition.f4812e) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int floatToIntBits = (((((((Float.floatToIntBits(this.f4809b) + 37) * 37) + Float.floatToIntBits(this.f4811d)) * 37) + Float.floatToIntBits(this.f4810c)) * 37) + Float.floatToIntBits(this.f4812e)) * 37;
        Point point = this.f4808a;
        return floatToIntBits + this.f4808a.hashCode();
    }

    public final float m7457a() {
        return this.f4809b;
    }

    public final Point m7460c() {
        return Point.m5925a(this.f4808a);
    }

    public final float m7461d() {
        return this.f4810c;
    }

    public final float m7462e() {
        return this.f4811d;
    }

    public final float m7463f() {
        return this.f4812e;
    }

    public final CameraPosition m7458a(CameraPosition cameraPosition) {
        int f = this.f4808a.m5958f() - cameraPosition.f4808a.m5958f();
        if (f > 536870912) {
            return new CameraPosition(new Point(this.f4808a.m5958f() - 1073741824, this.f4808a.m5960g()), this.f4809b, this.f4810c, this.f4811d, this.f4812e);
        }
        return f < -536870912 ? new CameraPosition(new Point(this.f4808a.m5958f() + 1073741824, this.f4808a.m5960g()), this.f4809b, this.f4810c, this.f4811d, this.f4812e) : this;
    }

    public static CameraPosition m7456a(CameraPosition cameraPosition, CameraPosition cameraPosition2, float f, float f2) {
        Point a;
        float f3;
        float f4;
        if (f2 == 0.0f) {
            a = cameraPosition.f4808a.m5942a(cameraPosition2.f4808a, f);
            f3 = cameraPosition.f4809b;
            f4 = ((cameraPosition2.f4809b - f3) * f) + f3;
        } else {
            a = cameraPosition.f4808a.m5942a(cameraPosition2.f4808a, (FloatMath.cos((f - br.DEFAULT_BACKOFF_MULT) * 3.1415927f) + br.DEFAULT_BACKOFF_MULT) / dm.DEFAULT_BACKOFF_MULT);
            f3 = Math.min(((CameraPosition.m7455a(cameraPosition.f4809b) * (br.DEFAULT_BACKOFF_MULT - f)) + (CameraPosition.m7455a(cameraPosition2.f4809b) * f)) + ((float) ((Math.pow((double) FloatMath.sin(3.1415927f * f), 1.2d) * 0.5d) * Math.pow((double) f2, 0.4d))), 160.0f);
            f4 = Math.max(f3 > 0.0f ? (float) (((-Math.log(((double) f3) * 0.1d)) * 1.4426950216293335d) + 4.0d) : 32.0f, dm.DEFAULT_BACKOFF_MULT);
        }
        f3 = cameraPosition.f4810c;
        float f5 = ((cameraPosition2.f4810c - f3) * f) + f3;
        float f6 = cameraPosition.f4811d;
        f3 = cameraPosition2.f4811d;
        if (f6 > f3) {
            if (f6 - f3 > 180.0f) {
                f6 -= 360.0f;
            }
        } else if (f3 - f6 > 180.0f) {
            f3 -= 360.0f;
        }
        f6 += (f3 - f6) * f;
        if (((double) f6) < 0.0d) {
            f6 += 360.0f;
        }
        f3 = cameraPosition.f4812e;
        return new CameraPosition(a, f4, f5, f6, ((cameraPosition2.f4812e - f3) * f) + f3);
    }

    private static float m7455a(float f) {
        return (float) (10.0d * Math.exp((4.0d - ((double) f)) / 1.4426950216293335d));
    }

    public final String toString() {
        return "[target:" + this.f4808a + " zoom:" + this.f4809b + " viewingAngle:" + this.f4810c + " bearing:" + this.f4811d + " lookAhead:" + this.f4812e + "]";
    }

    public final CameraPosition m7459b() {
        return this;
    }
}
