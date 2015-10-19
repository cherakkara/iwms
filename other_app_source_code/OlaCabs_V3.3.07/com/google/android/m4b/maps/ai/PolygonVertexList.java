package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.ai.AbstractVertexList.AbstractVertexList;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.ai.g */
class PolygonVertexList extends AbstractVertexList {
    private double[] f3149b;

    static PolygonVertexList m5019a(double[] dArr) {
        return new PolygonVertexList(dArr);
    }

    static PolygonVertexList m5020a(double[] dArr, int[] iArr) {
        if (dArr.length % 2 != 0 || iArr.length < 2 || iArr[0] != 0 || iArr[iArr.length - 1] != dArr.length / 2) {
            throw new IllegalArgumentException("ChainStartIndices for PolygonVertexList.create invalid.");
        } else if (iArr.length == 2) {
            return new PolygonVertexList(dArr);
        } else {
            return new PolygonVertexListWithHoles(dArr, iArr);
        }
    }

    protected PolygonVertexList(double[] dArr) {
        if (dArr.length % 2 != 0) {
            throw new IllegalArgumentException("Cannot create PolygonVertexList from incomplete array.");
        }
        this.a = dArr.length / 2;
        this.f3149b = dArr;
    }

    public final VertexMapping m5029b() {
        return new VertexMapping((AbstractVertexList) this);
    }

    public final SortedVertexMapping m5032c() {
        return new SortedVertexMapping(this);
    }

    public final double m5024a(int i) {
        return this.f3149b[i * 2];
    }

    public final double m5028b(int i) {
        return this.f3149b[(i * 2) + 1];
    }

    public int m5031c(int i) {
        return m4927h(i - 1);
    }

    public int m5034d(int i) {
        return m4927h(i + 1);
    }

    public final AbstractVertexList m5035e(int i) {
        int c = m5031c(i);
        int d = m5034d(i);
        double a = m4916a(c, i, d);
        Object obj = a < 0.0d ? 1 : null;
        double d2 = this.f3149b[i * 2];
        double d3 = this.f3149b[c * 2];
        Object obj2 = d3 < d2 ? 1 : null;
        Object obj3 = d3 == d2 ? 1 : null;
        Object obj4 = d3 > d2 ? 1 : null;
        double d4 = this.f3149b[d * 2];
        Object obj5 = d4 < d2 ? 1 : null;
        Object obj6 = d4 == d2 ? 1 : null;
        Object obj7 = d4 > d2 ? 1 : null;
        if (obj3 == null || obj6 == null) {
            if (a == 0.0d && !(obj3 == null && obj6 == null)) {
                obj = obj3 != null ? this.f3149b[(c * 2) + 1] < this.f3149b[(i * 2) + 1] ? obj7 : obj5 : this.f3149b[(d * 2) + 1] < this.f3149b[(i * 2) + 1] ? obj2 : obj4;
            }
            if (obj != null) {
                if (obj4 != null && (obj7 != null || obj6 != null)) {
                    return AbstractVertexList.SPLIT_VERTEX;
                }
                if (!(obj2 == null || (obj5 == null && obj6 == null))) {
                    return AbstractVertexList.MERGE_VERTEX;
                }
            } else if ((obj4 != null || obj3 != null) && obj7 != null) {
                return AbstractVertexList.START_VERTEX;
            } else {
                if (!((obj2 == null && obj3 == null) || obj5 == null)) {
                    return AbstractVertexList.END_VERTEX;
                }
            }
            if ((obj2 == null && obj3 == null) || (obj7 == null && obj6 == null)) {
                return AbstractVertexList.LEFT_VERTEX;
            }
            return AbstractVertexList.RIGHT_VERTEX;
        } else if (this.f3149b[(d * 2) + 1] >= this.f3149b[(i * 2) + 1]) {
            return this.f3149b[(c * 2) + 1] > this.f3149b[(i * 2) + 1] ? AbstractVertexList.START_VERTEX : AbstractVertexList.RIGHT_VERTEX;
        } else {
            if (this.f3149b[(c * 2) + 1] < this.f3149b[(i * 2) + 1]) {
                return AbstractVertexList.END_VERTEX;
            }
            return AbstractVertexList.LEFT_VERTEX;
        }
    }

    public int m5025a() {
        return 0;
    }

    public final double m5033d() {
        double d = 0.0d;
        for (int i = 0; i < m5025a() + 1; i++) {
            d += m5023i(i);
        }
        return d;
    }

    private double m5023i(int i) {
        int g = m4926g(i);
        int g2 = m4926g(i + 1);
        if (g2 - g < 3) {
            return 0.0d;
        }
        double d = this.f3149b[(g2 - 1) * 2];
        double d2 = this.f3149b[((g2 - 1) * 2) + 1];
        double d3 = 0.0d;
        double d4 = 0.0d;
        int i2 = g;
        double d5 = 0.0d;
        while (i2 < g2) {
            double d6 = this.f3149b[i2 * 2] - d;
            double d7 = this.f3149b[(i2 * 2) + 1] - d2;
            d4 = ((d5 * d7) - (d3 * d6)) + d4;
            i2++;
            d3 = d7;
            d5 = d6;
        }
        return d4 / 2.0d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean m5036e() {
        /*
        r14 = this;
        r0 = 0;
        r8 = r0;
    L_0x0002:
        r0 = r14.a;
        if (r8 >= r0) goto L_0x0074;
    L_0x0006:
        r2 = r14.m5031c(r8);
        r9 = r14.m5034d(r8);
        r0 = r14.m4916a(r2, r8, r9);
        r4 = 0;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0070;
    L_0x0018:
        r0 = r14.m5024a(r2);
        r2 = r14.m5028b(r2);
        r4 = r14.m5024a(r8);
        r6 = r14.m5028b(r8);
        r10 = r14.m5024a(r9);
        r12 = r14.m5028b(r9);
        r9 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r9 == 0) goto L_0x0038;
    L_0x0034:
        r9 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r9 != 0) goto L_0x0040;
    L_0x0038:
        r9 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r9 != 0) goto L_0x0074;
    L_0x003c:
        r9 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r9 != 0) goto L_0x0074;
    L_0x0040:
        r9 = com.google.android.m4b.maps.ai.Vertex2d.m5076a(r0, r2, r4, r6);
        if (r9 < 0) goto L_0x0050;
    L_0x0046:
        r0 = com.google.android.m4b.maps.ai.Vertex2d.m5076a(r0, r2, r4, r6);
        if (r0 != 0) goto L_0x006b;
    L_0x004c:
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x006b;
    L_0x0050:
        r0 = 1;
        r9 = r0;
    L_0x0052:
        r0 = r10;
        r2 = r12;
        r0 = com.google.android.m4b.maps.ai.Vertex2d.m5076a(r0, r2, r4, r6);
        if (r0 < 0) goto L_0x0066;
    L_0x005a:
        r0 = r10;
        r2 = r12;
        r0 = com.google.android.m4b.maps.ai.Vertex2d.m5076a(r0, r2, r4, r6);
        if (r0 >= 0) goto L_0x006e;
    L_0x0062:
        r0 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x006e;
    L_0x0066:
        r0 = 1;
    L_0x0067:
        if (r9 != r0) goto L_0x0070;
    L_0x0069:
        r0 = 1;
    L_0x006a:
        return r0;
    L_0x006b:
        r0 = 0;
        r9 = r0;
        goto L_0x0052;
    L_0x006e:
        r0 = 0;
        goto L_0x0067;
    L_0x0070:
        r0 = r8 + 1;
        r8 = r0;
        goto L_0x0002;
    L_0x0074:
        r0 = 0;
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ai.g.e():boolean");
    }

    public final boolean m5037f() {
        return m5023i(0) <= 0.0d;
    }

    public final boolean m5038g() {
        for (int i = 1; i < m5025a() + 1; i++) {
            if (m5023i(i) >= 0.0d) {
                return true;
            }
        }
        return false;
    }

    public final boolean m5039h() {
        SortedVertexMapping c = m5032c();
        EdgeList edgeList = new EdgeList(this);
        for (int i = 0; i < c.c; i++) {
            boolean z;
            int j = c.m4980j(i);
            int d = c.m4967d(j);
            int e = c.m4969e(j);
            if (c.m4975g(d, j) == 0 || c.m4975g(j, e) == 0) {
                z = true;
            } else {
                AbstractVertexList e2 = m5035e(j);
                z = (e2 == AbstractVertexList.LEFT_VERTEX && (edgeList.m4944a(d, j) || edgeList.m4948b(j, e))) || ((e2 == AbstractVertexList.RIGHT_VERTEX && (edgeList.m4944a(j, e) || edgeList.m4948b(d, j))) || ((e2 == AbstractVertexList.SPLIT_VERTEX && (edgeList.m4944a(d, j) || edgeList.m4944a(j, e))) || ((e2 == AbstractVertexList.MERGE_VERTEX && (edgeList.m4948b(j, e) || edgeList.m4948b(d, j))) || ((e2 == AbstractVertexList.START_VERTEX && (edgeList.m4944a(j, e) || edgeList.m4944a(d, j))) || (e2 == AbstractVertexList.END_VERTEX && (edgeList.m4948b(d, j) || edgeList.m4948b(j, e)))))));
            }
            if (z) {
                return true;
            }
        }
        for (j = 0; j < this.a; j++) {
            int i2 = j + 1;
            while (i2 < this.a) {
                if (m4918a(j, i2) == 0) {
                    int c2 = m5031c(j);
                    int d2 = m5034d(j);
                    if (!(c2 == i2 || d2 == i2)) {
                        int c3 = m5031c(j);
                        int d3 = m5034d(j);
                        e = m5031c(i2);
                        d = m5034d(i2);
                        boolean z2 = (m4918a(c3, j) == 0 || m4918a(j, d3) == 0 || m4918a(e, i2) == 0 || m4918a(i2, d) == 0 || m5022b(j, c3, d3) || m5022b(j, c3, e) || m5022b(j, c3, d) || m5022b(j, d3, e) || m5022b(j, d3, d) || m5022b(j, e, d)) ? false : m5021a(c3, j, d3, e) && m5021a(c3, j, d3, d) && m4920a(j, d3, c3, d, e);
                        if (!z2) {
                            if (!m4920a(j, c2, m5031c(i2), d2, m5034d(i2))) {
                            }
                        }
                    }
                    return true;
                }
                i2++;
            }
        }
        return false;
    }

    private boolean m5021a(int i, int i2, int i3, int i4) {
        if (m4916a(i, i2, i3) < 0.0d) {
            if (m4916a(i, i2, i4) >= 0.0d || m4916a(i2, i3, i4) >= 0.0d) {
                return true;
            }
            return false;
        } else if (m4916a(i, i2, i4) <= 0.0d || m4916a(i2, i3, i4) <= 0.0d) {
            return false;
        } else {
            return true;
        }
    }

    private boolean m5022b(int i, int i2, int i3) {
        if (m4918a(i2, i3) == 0) {
            return true;
        }
        if (m4918a(i, i2) == 0 || m4918a(i, i3) == 0) {
            return false;
        }
        if (m4918a(i, i2) != m4918a(i, i3)) {
            return false;
        }
        if (m4916a(i, i2, i3) != 0.0d) {
            return false;
        }
        return true;
    }

    final void m5026a(int i, double[] dArr, int i2, int i3) {
        System.arraycopy(this.f3149b, i * 2, dArr, i2 * 2, i3 * 2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PolygonVertexList)) {
            return false;
        }
        PolygonVertexList polygonVertexList = (PolygonVertexList) obj;
        if (polygonVertexList.m5027a((Object) this) && Arrays.equals(this.f3149b, polygonVertexList.f3149b)) {
            return true;
        }
        return false;
    }

    protected boolean m5027a(Object obj) {
        return obj instanceof PolygonVertexList;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f3149b);
    }

    public String toString() {
        return m5030b(0, m4926g(1));
    }

    protected final String m5030b(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[(");
        stringBuilder.append(this.f3149b[i * 2]);
        stringBuilder.append(", ");
        stringBuilder.append(this.f3149b[(i * 2) + 1]);
        stringBuilder.append(")");
        for (int i3 = i + 1; i3 < i2; i3++) {
            stringBuilder.append(", (");
            stringBuilder.append(this.f3149b[i3 * 2]);
            stringBuilder.append(", ");
            stringBuilder.append(this.f3149b[(i3 * 2) + 1]);
            stringBuilder.append(")");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
