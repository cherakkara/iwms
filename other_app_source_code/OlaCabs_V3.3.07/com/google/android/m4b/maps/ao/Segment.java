package com.google.android.m4b.maps.ao;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.ac;

/* renamed from: com.google.android.m4b.maps.ao.c */
public final class Segment {
    public static final SegmentName f3732a;
    private static final Arc[] f3733b;
    private final int f3734c;
    private final long f3735d;
    private final SegmentName[] f3736e;
    private final Polyline f3737f;
    private Arc[] f3738g;

    static {
        f3732a = new SegmentName("Unknown Road", null, false);
        f3733b = new Arc[0];
    }

    public Segment(long j, SegmentName[] segmentNameArr, Polyline polyline, int i, int i2, int i3, int i4) {
        if (segmentNameArr.length == 0) {
            throw new IllegalArgumentException("Segments must have at least one name");
        }
        this.f3734c = i;
        this.f3735d = j;
        this.f3736e = segmentNameArr;
        this.f3737f = polyline;
        this.f3738g = f3733b;
    }

    public static long m6144a(ac acVar, int i) {
        return ((((long) acVar.m5440c()) << 48) | (((long) acVar.m5441d()) << 32)) | ((long) i);
    }

    public final void m6147a(Arc... arcArr) {
        if (arcArr.length == 0) {
            this.f3738g = f3733b;
        } else {
            this.f3738g = arcArr;
        }
    }

    private Point m6145a(int i) {
        Point point = new Point();
        m6146a(i, point);
        return point;
    }

    public final void m6146a(int i, Point point) {
        if ((this.f3734c & 4) != 0) {
            i = (this.f3737f.m6020b() - i) - 1;
        }
        this.f3737f.m6016a(i, point);
    }

    public final int hashCode() {
        return (int) (((((this.f3735d >>> 48) & 255) << 24) | (((this.f3735d >>> 32) & 255) << 16)) | (this.f3735d & 65535));
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof Segment) && this.f3735d == ((Segment) obj).f3735d) {
            return true;
        }
        return false;
    }

    public final String toString() {
        boolean z;
        boolean z2 = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[name: ").append(this.f3736e[0]);
        stringBuilder.append(" unroutable: ").append((this.f3734c & 8) != 0);
        StringBuilder append = stringBuilder.append(" leaves-region: ");
        if ((this.f3734c & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        append.append(z);
        StringBuilder append2 = stringBuilder.append(" enters-region: ");
        if ((this.f3734c & 2) == 0) {
            z2 = false;
        }
        append2.append(z2);
        stringBuilder.append(" num-points: ").append(this.f3737f.m6020b());
        stringBuilder.append(" first-point: ").append(m6145a(0).m5966j());
        stringBuilder.append(" last-point: ").append(m6145a(this.f3737f.m6020b() - 1).m5966j());
        stringBuilder.append(" num-arcs: ").append(this.f3738g.length);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
