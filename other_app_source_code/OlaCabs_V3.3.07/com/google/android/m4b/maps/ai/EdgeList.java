package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.ai.AbstractVertexList.AbstractVertexList;
import com.google.android.m4b.maps.ai.Polygon2dTessellator.Polygon2dTessellator;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.ai.b */
final class EdgeList {
    private final VertexMapping f3113a;
    private final VertexMapping f3114b;
    private final VertexMapping f3115c;
    private final VertexMapping f3116d;

    /* renamed from: com.google.android.m4b.maps.ai.b.1 */
    static /* synthetic */ class EdgeList {
        static final /* synthetic */ int[] f3112a;

        static {
            f3112a = new int[AbstractVertexList.values().length];
            try {
                f3112a[AbstractVertexList.RIGHT_VERTEX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3112a[AbstractVertexList.MERGE_VERTEX.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public EdgeList(PolygonVertexList polygonVertexList) {
        this.f3113a = polygonVertexList.m5029b();
        this.f3114b = polygonVertexList.m5029b();
        this.f3115c = polygonVertexList.m5029b();
        this.f3116d = polygonVertexList.m5029b();
    }

    public EdgeList(VertexMapping vertexMapping) {
        this.f3113a = vertexMapping.m4965c();
        this.f3114b = vertexMapping.m4965c();
        this.f3115c = vertexMapping.m4965c();
        this.f3116d = vertexMapping.m4965c();
    }

    private EdgeList(VertexMapping vertexMapping, VertexMapping vertexMapping2, VertexMapping vertexMapping3, VertexMapping vertexMapping4) {
        this.f3113a = vertexMapping;
        this.f3114b = vertexMapping2;
        this.f3115c = vertexMapping3;
        this.f3116d = vertexMapping4;
    }

    public final void m4943a(int i, int i2, int i3) {
        int i4 = 0;
        if (this.f3113a.f3119c != 0) {
            i4 = m4933d(i2, i3, i2);
        }
        m4929a(i4, i2, i3, i2, -1);
        m4929a(i4 + 1, i2, i, -1, -1);
    }

    public final int m4941a(int i, int i2, AbstractVertexList abstractVertexList) {
        if (this.f3113a.m4975g(i, i2) < 0) {
            int d = m4933d(i, i2, i);
            switch (EdgeList.f3112a[abstractVertexList.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    m4929a(d, i, i2, m4934e(this.f3113a.m4967d(i), i, i), -1);
                    return -1;
                default:
                    throw new Polygon2dTessellator("Impossible EdgeList start case.");
            }
        }
        m4929a(m4933d(i2, i, i2), i2, i, -1, -1);
        return m4936f(m4932d(i2), i2);
    }

    public final int m4946b(int i, int i2, AbstractVertexList abstractVertexList) {
        int g;
        int i3 = -1;
        if (this.f3113a.m4975g(i, i2) < 0) {
            g = m4937g(i, i2);
            if (!this.f3116d.m4978i(g)) {
                i3 = this.f3116d.m4980j(g);
            }
            switch (EdgeList.f3112a[abstractVertexList.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    int d;
                    d = m4932d(i2);
                    if (!this.f3116d.m4978i(d)) {
                        i3 = this.f3116d.m4980j(d);
                    }
                    this.f3115c.m4973f(d, i2);
                    this.f3116d.m4973f(d, i2);
                    break;
            }
        }
        d = m4937g(i2, i);
        int d2 = m4932d(i);
        if (!this.f3116d.m4978i(d2)) {
            g = this.f3116d.m4980j(d2);
            this.f3116d.m4973f(d2, -1);
            i3 = g;
        }
        this.f3115c.m4973f(d2, i);
        g = d;
        m4931c(g);
        return i3;
    }

    public final void m4947b(int i, int i2, int i3) {
        int d = m4933d(i2, i3, i2);
        int e = m4934e(this.f3113a.m4980j(d - 1), this.f3114b.m4980j(d - 1), i2);
        m4936f(m4932d(i2), i2);
        m4929a(d, i2, i3, e, -1);
        m4929a(d, i2, i, -1, -1);
    }

    public final boolean m4944a(int i, int i2) {
        int i3;
        int i4;
        if (this.f3113a.m4975g(i2, i) < 0) {
            i3 = i;
            i4 = i2;
        } else {
            i3 = i2;
            i4 = i;
        }
        int d = m4933d(i4, i3, i4);
        m4929a(d, i4, i3, -1, -1);
        return m4935e(d + -1, d) || m4935e(d, d + 1);
    }

    public final boolean m4948b(int i, int i2) {
        int g;
        if (this.f3113a.m4975g(i, i2) < 0) {
            g = m4937g(i, i2);
        } else {
            g = m4937g(i2, i);
        }
        m4931c(g);
        return m4935e(g + -1, g) || m4935e(g, g + 1);
    }

    private boolean m4935e(int i, int i2) {
        if (i < 0 || i >= this.f3113a.f3119c || i2 < 0 || i2 >= this.f3113a.f3119c) {
            return false;
        }
        double g = this.f3113a.m4974g(i);
        double h = this.f3113a.m4976h(i);
        double g2 = this.f3114b.m4974g(i);
        double h2 = this.f3114b.m4976h(i);
        double g3 = this.f3113a.m4974g(i2);
        double h3 = this.f3113a.m4976h(i2);
        double g4 = this.f3114b.m4974g(i2);
        double h4 = this.f3114b.m4976h(i2);
        if ((g == g4 && h == h4) || (g3 == g2 && h3 == h2)) {
            return false;
        }
        if ((g != g3 || h != h3) && (g2 != g4 || h2 != h4)) {
            if (Vertex2d.m5075a(g, h, g2, h2, g4, h4) * Vertex2d.m5075a(g, h, g2, h2, g3, h3) > 0.0d) {
                return false;
            }
            if (Vertex2d.m5075a(g3, h3, g4, h4, g, h) * Vertex2d.m5075a(g3, h3, g4, h4, g2, h2) > 0.0d) {
                return false;
            }
            return true;
        } else if (g != g3 || h != h3 || g2 != g4 || h2 != h4) {
            return false;
        } else {
            return (this.f3113a.m4967d(this.f3113a.m4980j(i)) == this.f3114b.m4980j(i) ? 1 : null) == (this.f3113a.m4967d(this.f3113a.m4980j(i2)) == this.f3114b.m4980j(i2) ? 1 : null);
        }
    }

    public final int m4940a(int i) {
        return m4938h(m4932d(i), i);
    }

    public final int m4945b(int i) {
        int h;
        int d = m4932d(i);
        if (this.f3116d.m4978i(d)) {
            h = m4938h(d, i);
        } else {
            h = this.f3116d.m4980j(d);
        }
        this.f3116d.m4973f(d, -1);
        return h;
    }

    public final boolean m4949c(int i, int i2) {
        int h = this.f3114b.m4977h(i2, 0);
        while (h != -1 && !m4939i(h, i)) {
            h = this.f3114b.m4977h(i2, h + 1);
        }
        if (h != -1) {
            return true;
        }
        return false;
    }

    public final EdgeList m4942a(SortedVertexMapping sortedVertexMapping, int i, int i2) {
        VertexMapping c = sortedVertexMapping.m4965c();
        VertexMapping c2 = sortedVertexMapping.m4965c();
        VertexMapping c3 = sortedVertexMapping.m4965c();
        VertexMapping c4 = sortedVertexMapping.m4965c();
        int i3;
        int i4;
        int j;
        int j2;
        int j3;
        if (i > i2) {
            i3 = (i - i2) - 1;
            for (i4 = 0; i4 < this.f3113a.f3119c; i4++) {
                j = this.f3113a.m4980j(i4);
                if (j <= i2 || j >= i) {
                    j2 = this.f3114b.m4980j(i4);
                    if (j2 <= i2 || j2 >= i) {
                        j3 = this.f3115c.m4980j(i4);
                        int j4 = this.f3116d.m4980j(i4);
                        c.m4962b(EdgeList.m4930c(j, i2, i3));
                        c2.m4962b(EdgeList.m4930c(j2, i2, i3));
                        if (j3 <= i2 || j3 >= i) {
                            c3.m4962b(EdgeList.m4930c(j3, i2, i3));
                        } else {
                            c3.m4962b(EdgeList.m4930c(j, i2, i3));
                        }
                        if (j4 <= i2 || j4 >= i) {
                            c4.m4962b(EdgeList.m4930c(j4, i2, i3));
                        } else {
                            throw new Polygon2dTessellator("When cutting edge list, we lost a merge vertex.");
                        }
                    }
                }
            }
        } else {
            for (i4 = 0; i4 < this.f3113a.f3119c; i4++) {
                i3 = this.f3113a.m4980j(i4);
                if (i3 >= i && i3 <= i2) {
                    j = this.f3114b.m4980j(i4);
                    if (j >= i && j <= i2) {
                        j2 = this.f3115c.m4980j(i4);
                        j3 = this.f3116d.m4980j(i4);
                        c.m4962b(i3 - i);
                        c2.m4962b(j - i);
                        if (j2 == -1) {
                            c3.m4962b(j2);
                        } else if (j2 < i || j2 > i2) {
                            c3.m4962b(i3 - i);
                        } else {
                            c3.m4962b(j2 - i);
                        }
                        if (j3 == -1) {
                            c4.m4962b(j3);
                        } else if (j3 < i || j3 > i2) {
                            throw new Polygon2dTessellator("When cutting edge list, we lost a merge vertex.");
                        } else {
                            c4.m4962b(j3 - i);
                        }
                    }
                }
            }
        }
        return new EdgeList(c, c2, c3, c4);
    }

    private static int m4930c(int i, int i2, int i3) {
        return i <= i2 ? i : i - i3;
    }

    public final void m4950d(int i, int i2) {
        m4929a(m4933d(i, i2, i2), i, i2, i, -1);
    }

    private void m4929a(int i, int i2, int i3, int i4, int i5) {
        this.f3113a.m4970e(i, i2);
        this.f3114b.m4970e(i, i3);
        this.f3115c.m4970e(i, i4);
        this.f3116d.m4970e(i, -1);
    }

    private void m4931c(int i) {
        this.f3113a.m4957a(i);
        this.f3114b.m4957a(i);
        this.f3115c.m4957a(i);
        this.f3116d.m4957a(i);
    }

    private int m4936f(int i, int i2) {
        this.f3115c.m4973f(i, i2);
        int j = this.f3116d.m4980j(i);
        if (j != -1) {
            this.f3116d.m4973f(i, -1);
        }
        return j;
    }

    private int m4937g(int i, int i2) {
        int h = this.f3114b.m4977h(i2, 0);
        while (h != -1 && !m4939i(h, i)) {
            h = this.f3114b.m4977h(i2, h + 1);
        }
        if (h != -1) {
            return h;
        }
        throw new NullPointerException("Could not find egde in EdgeList.");
    }

    private int m4932d(int i) {
        double b = this.f3113a.m4959b(i);
        double c = this.f3113a.m4963c(i);
        int i2 = 0;
        while (m4928a(i2, b) < c && i2 < this.f3113a.f3119c) {
            i2++;
        }
        if (i2 > 0) {
            return i2 - 1;
        }
        return 0;
    }

    private int m4933d(int i, int i2, int i3) {
        double b = this.f3113a.m4959b(i3);
        double c = this.f3113a.m4963c(i3);
        int i4 = 0;
        while (i4 < this.f3113a.f3119c) {
            double a = m4928a(i4, b);
            if (a > c) {
                return i4;
            }
            if (a == c) {
                double g = this.f3114b.m4974g(i4);
                double h = this.f3114b.m4976h(i4);
                a = this.f3113a.m4959b(i);
                double c2 = this.f3113a.m4963c(i);
                double b2 = this.f3113a.m4959b(i2);
                double c3 = this.f3113a.m4963c(i2);
                if (Vertex2d.m5075a(a, c2, b2, c3, g, h) > 0.0d) {
                    return i4;
                }
                if (Vertex2d.m5076a(g, h, b2, c3) == 0 && i4 % 2 == 0) {
                    return i4;
                }
            }
            i4++;
        }
        return this.f3113a.f3119c;
    }

    private int m4938h(int i, int i2) {
        int j = this.f3115c.m4980j(i);
        double b = this.f3113a.m4959b(j);
        double c = this.f3113a.m4963c(j);
        double g = this.f3113a.m4974g(i);
        double h = this.f3113a.m4976h(i);
        if (Vertex2d.m5075a(g, h, this.f3114b.m4974g(i), this.f3114b.m4976h(i), b, c) <= 0.0d) {
            if (Vertex2d.m5075a(b, c, g, h, this.f3113a.m4959b(i2), this.f3113a.m4963c(i2)) <= 0.0d) {
                return this.f3113a.m4980j(i);
            }
        }
        return j;
    }

    private int m4934e(int i, int i2, int i3) {
        return m4938h(m4937g(i, i2), i3);
    }

    private boolean m4939i(int i, int i2) {
        return this.f3113a.m4979i(i2, i);
    }

    private double m4928a(int i, double d) {
        double g = this.f3113a.m4974g(i);
        double h = this.f3113a.m4976h(i);
        if (g == d) {
            return h;
        }
        double g2 = this.f3114b.m4974g(i);
        double h2 = this.f3114b.m4976h(i);
        if (g2 == d) {
            return h2;
        }
        g2 -= g;
        if (g2 != 0.0d) {
            return h + (((h2 - h) * (d - g)) / g2);
        }
        if (h <= h2) {
            return h2;
        }
        return h;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{start:  ");
        stringBuilder.append(this.f3113a.m4983m(3));
        stringBuilder.append("\n end:    ");
        stringBuilder.append(this.f3114b.m4983m(3));
        stringBuilder.append("\n helper: ");
        stringBuilder.append(this.f3115c.m4983m(3));
        stringBuilder.append("\n merge:  ");
        stringBuilder.append(this.f3116d.m4983m(3));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
