package com.google.android.m4b.maps.an;

/* compiled from: WrappedRegion2D */
public abstract class at {
    protected boolean f3506a;

    public abstract Point m5652a(int i);

    public abstract void m5653a(int i, Point[] pointArr);

    public abstract boolean m5654a(Point point);

    public abstract Region2D m5657c();

    public abstract int m5658h();

    public as m5651a() {
        return as.m5674a(m5657c().m5747a());
    }

    public boolean m5655a(Region2D region2D) {
        if (!m5651a().m5681b(region2D.m5747a())) {
            return false;
        }
        for (int i = 0; i < region2D.m5750b(); i++) {
            if (!m5654a(region2D.m5746a(i))) {
                return false;
            }
        }
        if (m5650c(region2D)) {
            return false;
        }
        return true;
    }

    public boolean m5656b(Region2D region2D) {
        if (!m5651a().m5681b(region2D.m5747a())) {
            return false;
        }
        if (m5654a(region2D.m5746a(0)) || region2D.m5748a(m5652a(0)) || m5650c(region2D)) {
            return true;
        }
        return false;
    }

    private boolean m5650c(Region2D region2D) {
        int h = m5658h();
        int b = region2D.m5750b();
        if (h == 0 || b == 0) {
            return false;
        }
        Point[] pointArr = new Point[2];
        Point h2 = region2D.m5752h();
        for (int i = 0; i < h; i++) {
            m5653a(i, pointArr);
            int i2 = 0;
            Point point = h2;
            while (i2 < b) {
                Point a = region2D.m5746a(i2);
                if (PointUtil2D.m5991a(pointArr[0], pointArr[1], point, a)) {
                    return true;
                }
                i2++;
                point = a;
            }
        }
        return false;
    }
}
