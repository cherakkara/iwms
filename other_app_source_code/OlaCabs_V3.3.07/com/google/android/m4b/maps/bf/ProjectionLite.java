package com.google.android.m4b.maps.bf;

import android.graphics.Point;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.VisibleRegion;
import com.google.android.m4b.maps.p047g.Objects;
import com.google.p025a.p026a.Preconditions;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.bf.k */
public final class ProjectionLite implements ProjectionDelegate {
    public final int f6189a;
    public final int f6190b;
    public final int f6191c;
    public final int f6192d;
    public final CameraPosition f6193e;
    public final int f6194f;
    public final int f6195g;
    public final double f6196h;
    private final double f6197i;
    private final Point f6198j;

    /* renamed from: com.google.android.m4b.maps.bf.k.a */
    public static class ProjectionLite {
        public long f6187a;
        public long f6188b;

        public ProjectionLite(long j, long j2) {
            this.f6187a = j;
            this.f6188b = j2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof ProjectionLite)) {
                return false;
            }
            ProjectionLite projectionLite = (ProjectionLite) obj;
            if (Objects.m10457a(Long.valueOf(this.f6187a), Long.valueOf(projectionLite.f6187a)) && Objects.m10457a(Long.valueOf(this.f6188b), Long.valueOf(projectionLite.f6188b))) {
                return true;
            }
            return false;
        }
    }

    public static ProjectionLite m9696a(LatLng latLng, double d, double d2) {
        Preconditions.m1822a(d >= 0.0d);
        Preconditions.m1817a((Object) latLng);
        double d3 = latLng.f7555b;
        double a = ProjectionLite.m9695a(d, d2);
        double d4 = a / 2.0d;
        double sin = Math.sin(Math.toRadians(latLng.f7554a));
        return new ProjectionLite((long) (((d3 / 360.0d) * a) + d4), (long) (d4 - (a * ((Math.log((1.0d + sin) / (1.0d - sin)) / 4.0d) / 3.141592653589793d))));
    }

    public static double m9695a(double d, double d2) {
        return (256.0d * Math.pow(2.0d, d)) * d2;
    }

    public ProjectionLite(CameraPosition cameraPosition, int i, int i2, double d, int i3, int i4, int i5, int i6) {
        Preconditions.m1817a((Object) cameraPosition);
        this.f6189a = i3;
        this.f6190b = i4;
        this.f6191c = i5;
        this.f6192d = i6;
        this.f6193e = cameraPosition;
        this.f6194f = i;
        this.f6195g = i2;
        this.f6196h = d;
        this.f6197i = (double) cameraPosition.f7530b;
        this.f6198j = new Point((((i - i3) - i5) / 2) + i3, (((i2 - i4) - i6) / 2) + i4);
    }

    public final LatLng m9698a(Point point) {
        ProjectionLite a = ProjectionLite.m9696a(this.f6193e.f7529a, this.f6197i, this.f6196h);
        ProjectionLite projectionLite = new ProjectionLite((a.f6187a - ((long) this.f6198j.x)) + ((long) point.x), (a.f6188b - ((long) this.f6198j.y)) + ((long) point.y));
        double a2 = ProjectionLite.m9695a(this.f6197i, this.f6196h);
        return new LatLng(Math.toDegrees((Math.atan(Math.exp(((((double) (-projectionLite.f6188b)) / a2) + 0.5d) * 6.283185307179586d)) * 2.0d) - 1.5707963267948966d), Math.toDegrees(((((double) projectionLite.f6187a) / a2) - 0.5d) * 6.283185307179586d));
    }

    public final Point m9697a(LatLng latLng) {
        ProjectionLite a = ProjectionLite.m9696a(latLng, this.f6197i, this.f6196h);
        ProjectionLite a2 = ProjectionLite.m9696a(this.f6193e.f7529a, this.f6197i, this.f6196h);
        long j = a.f6187a - a2.f6187a;
        long b = m9700b();
        if (j > b / 2) {
            j -= b;
        }
        if (j < (-b) / 2) {
            j += b;
        }
        return new Point((int) (j + ((long) this.f6198j.x)), (int) ((a.f6188b - a2.f6188b) + ((long) this.f6198j.y)));
    }

    public final VisibleRegion m9699a() {
        LatLng a = m9698a(new Point(this.f6189a, this.f6190b));
        LatLng a2 = m9698a(new Point(this.f6194f - this.f6191c, this.f6190b));
        LatLng a3 = m9698a(new Point(this.f6189a, this.f6195g - this.f6192d));
        return new VisibleRegion(a3, m9698a(new Point(this.f6194f - this.f6191c, this.f6195g - this.f6192d)), a, a2, new LatLngBounds(a3, a2));
    }

    public final long m9700b() {
        return (long) ProjectionLite.m9695a(this.f6197i, this.f6196h);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f6193e, Integer.valueOf(this.f6194f), Integer.valueOf(this.f6195g), Double.valueOf(this.f6196h), Integer.valueOf(this.f6189a), Integer.valueOf(this.f6190b), Integer.valueOf(this.f6191c), Integer.valueOf(this.f6192d)});
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProjectionLite)) {
            return false;
        }
        ProjectionLite projectionLite = (ProjectionLite) obj;
        if (Objects.m10457a(this.f6193e, projectionLite.f6193e) && Objects.m10457a(Integer.valueOf(this.f6194f), Integer.valueOf(projectionLite.f6194f)) && Objects.m10457a(Integer.valueOf(this.f6195g), Integer.valueOf(projectionLite.f6195g)) && Objects.m10457a(Double.valueOf(this.f6196h), Double.valueOf(projectionLite.f6196h)) && Objects.m10457a(Integer.valueOf(this.f6189a), Integer.valueOf(projectionLite.f6189a)) && Objects.m10457a(Integer.valueOf(this.f6190b), Integer.valueOf(projectionLite.f6190b)) && Objects.m10457a(Integer.valueOf(this.f6191c), Integer.valueOf(projectionLite.f6191c)) && Objects.m10457a(Integer.valueOf(this.f6192d), Integer.valueOf(projectionLite.f6192d))) {
            return true;
        }
        return false;
    }
}
