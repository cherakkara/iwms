package com.google.android.m4b.maps.an;

/* renamed from: com.google.android.m4b.maps.an.n */
public abstract class Region2D implements ay {
    public abstract Point m5746a(int i);

    public abstract boolean m5748a(Point point);

    public abstract int m5750b();

    public Point m5752h() {
        return m5746a(m5750b() - 1);
    }

    public Rectangle2D m5747a() {
        int i = m5746a(0).f3646a;
        int i2 = m5746a(0).f3647b;
        int i3 = i;
        int i4 = i;
        i = i2;
        for (int i5 = 1; i5 < m5750b(); i5++) {
            i4 = Math.min(i4, m5746a(i5).f3646a);
            i3 = Math.max(i3, m5746a(i5).f3646a);
            i = Math.min(i, m5746a(i5).f3647b);
            i2 = Math.max(i2, m5746a(i5).f3647b);
        }
        return new Rectangle2D(new Point(i4, i), new Point(i3, i2));
    }

    public boolean m5751b(Region2D region2D) {
        if (!m5747a().m6047a(region2D.m5747a())) {
            return false;
        }
        for (int i = 0; i < region2D.m5750b(); i++) {
            if (!m5748a(region2D.m5746a(i))) {
                return false;
            }
        }
        if (m5745c(region2D)) {
            return false;
        }
        return true;
    }

    public boolean m5749a(Region2D region2D) {
        if (!m5747a().m6047a(region2D.m5747a())) {
            return false;
        }
        if (m5748a(region2D.m5746a(0)) || region2D.m5748a(m5746a(0)) || m5745c(region2D)) {
            return true;
        }
        return false;
    }

    private boolean m5745c(Region2D region2D) {
        int b = m5750b();
        int b2 = region2D.m5750b();
        if (b == 0 || b2 == 0) {
            return false;
        }
        Point h = m5752h();
        Point h2 = region2D.m5752h();
        int i = 0;
        Point point = h;
        while (i < b) {
            Point a = m5746a(i);
            int i2 = 0;
            Point point2 = h2;
            while (i2 < b2) {
                Point a2 = region2D.m5746a(i2);
                if (PointUtil2D.m5991a(point, a, point2, a2)) {
                    return true;
                }
                i2++;
                point2 = a2;
            }
            i++;
            point = a;
        }
        return false;
    }
}
