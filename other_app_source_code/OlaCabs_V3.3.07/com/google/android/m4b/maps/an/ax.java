package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.Polyline.Polyline;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Clipper2D */
public final class ax {
    private Region2D f3551a;
    private int f3552b;
    private Point[] f3553c;
    private boolean f3554d;
    private final ArrayList<Polyline> f3555e;
    private final ArrayList<Clipper2D> f3556f;
    private int f3557g;

    /* renamed from: com.google.android.m4b.maps.an.ax.a */
    static class Clipper2D {
        private int[] f3549a;
        private int f3550b;

        Clipper2D() {
            this(16);
        }

        private Clipper2D(int i) {
            this.f3549a = new int[16];
            this.f3550b = 0;
        }

        final void m5720a(int i) {
            if (this.f3550b == this.f3549a.length) {
                Object obj = new int[(this.f3549a.length * 2)];
                System.arraycopy(this.f3549a, 0, obj, 0, this.f3549a.length);
                this.f3549a = obj;
            }
            int[] iArr = this.f3549a;
            int i2 = this.f3550b;
            this.f3550b = i2 + 1;
            iArr[i2] = i;
        }

        final int[] m5721a() {
            Object obj = new int[this.f3550b];
            System.arraycopy(this.f3549a, 0, obj, 0, this.f3550b);
            return obj;
        }

        final void m5722b() {
            this.f3550b = 0;
        }

        final int m5723c() {
            return this.f3550b;
        }
    }

    public ax(Region2D region2D) {
        this.f3555e = new ArrayList();
        this.f3556f = new ArrayList();
        m5725a(region2D);
    }

    private void m5725a(Region2D region2D) {
        this.f3551a = region2D;
        this.f3552b = region2D.m5750b();
        this.f3553c = new Point[this.f3552b];
        for (int i = 0; i < this.f3553c.length; i++) {
            this.f3553c[i] = new Point();
        }
    }

    public final void m5726a(Polyline polyline, List<Polyline> list) {
        List list2 = null;
        int i = 0;
        this.f3557g = 0;
        this.f3554d = false;
        Region2D a = polyline.m6015a();
        if (!this.f3551a.m5749a(a)) {
            return;
        }
        if (this.f3551a.m5751b(a)) {
            list.add(polyline);
            if (this.f3554d) {
                list2.add(list2);
                return;
            }
            return;
        }
        Point point = new Point();
        int b = polyline.m6020b();
        polyline.m6016a(0, point);
        m5724a(0, point, this.f3554d ? list2[0] : 0, true);
        int i2 = 1;
        while (i2 < b) {
            polyline.m6016a(i2, point);
            m5724a(0, point, this.f3554d ? list2[i2] : 0, false);
            i2++;
        }
        while (i < this.f3557g) {
            Polyline polyline2 = (Polyline) this.f3555e.get(i);
            if (polyline2.m6004a() > 1) {
                list.add(polyline2.m6008c());
            }
            polyline2.m6007b();
            if (this.f3554d) {
                Clipper2D clipper2D = (Clipper2D) this.f3556f.get(i);
                if (clipper2D.m5723c() > 1) {
                    list2.add(clipper2D.m5721a());
                }
                clipper2D.m5722b();
            }
            i++;
        }
    }

    private void m5724a(int i, Point point, int i2, boolean z) {
        if (i == this.f3552b) {
            if (z) {
                if (this.f3557g == this.f3555e.size()) {
                    this.f3555e.add(new Polyline());
                    this.f3556f.add(new Clipper2D());
                }
                this.f3557g++;
            }
            if (((Polyline) this.f3555e.get(this.f3557g - 1)).m6006a(point) && this.f3554d) {
                ((Clipper2D) this.f3556f.get(this.f3557g - 1)).m5720a(i2);
                return;
            }
            return;
        }
        Point h;
        Point a;
        if (i == 0) {
            h = this.f3551a.m5752h();
            a = this.f3551a.m5746a(0);
        } else {
            h = this.f3551a.m5746a(i - 1);
            a = this.f3551a.m5746a(i);
        }
        Point point2;
        if (PointUtil2D.m5988a(h, a, point) >= 0) {
            if (!z && PointUtil2D.m5988a(h, a, this.f3553c[i]) < 0) {
                point2 = new Point();
                PointUtil2D.m5992a(h, a, point, this.f3553c[i], point2);
                m5724a(i + 1, point2, i2, true);
            }
            m5724a(i + 1, point, i2, z);
        } else if (!z && PointUtil2D.m5988a(h, a, this.f3553c[i]) >= 0) {
            point2 = new Point();
            PointUtil2D.m5992a(h, a, this.f3553c[i], point, point2);
            m5724a(i + 1, point2, i2, false);
        }
        this.f3553c[i].m5950b(point);
    }
}
