package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.ai.AbstractVertexList.AbstractVertexList;
import com.google.android.m4b.maps.ch.QuickSorter;
import com.google.android.m4b.maps.ch.Sortable;

/* renamed from: com.google.android.m4b.maps.ai.j */
class SortedVertexMapping extends VertexMapping implements Sortable {
    SortedVertexMapping(PolygonVertexList polygonVertexList) {
        super((AbstractVertexList) polygonVertexList);
        m4986a((AbstractVertexList) polygonVertexList);
    }

    protected SortedVertexMapping(double[] dArr) {
        super(dArr);
        m4986a(this.a);
    }

    private SortedVertexMapping(double[] dArr, int[] iArr) {
        super(PolygonVertexList.m5019a(dArr), iArr);
    }

    private void m4986a(AbstractVertexList abstractVertexList) {
        this.c = abstractVertexList.f3111a;
        this.b = new int[this.c];
        for (int i = 0; i < this.c; i++) {
            this.b[i] = i;
        }
        QuickSorter.m10142a().m10138a(this, 0, this.c - 1);
    }

    public final SortedVertexMapping m4990b(int i, int i2) {
        if (this.a.m4917a() != 0) {
            throw new NullPointerException("Cannot create a sorted sublist when there are holes.");
        } else if (i > i2) {
            r1 = new double[((((i2 + 1) + m4968e()) - i) * 2)];
            this.a.m4919a(0, r1, 0, i2 + 1);
            this.a.m4919a(i, r1, i2 + 1, m4968e() - i);
            return new SortedVertexMapping(r1, m4987a(i, i2));
        } else {
            int i3 = (i2 - i) + 1;
            r1 = new double[(i3 * 2)];
            this.a.m4919a(i, r1, 0, i3);
            return new SortedVertexMapping(r1, m4987a(i, i2));
        }
    }

    private int[] m4987a(int i, int i2) {
        int i3 = 0;
        int[] iArr;
        if (i > i2) {
            int i4 = (i - i2) - 1;
            iArr = new int[(this.c - i4)];
            for (int i5 : this.b) {
                if (i5 <= i2) {
                    iArr[i3] = i5;
                    i3++;
                }
                if (i5 >= i) {
                    iArr[i3] = i5 - i4;
                    i3++;
                }
            }
            return iArr;
        }
        iArr = new int[((i2 - i) + 1)];
        for (int i6 : this.b) {
            if (i6 >= i && i6 <= i2) {
                iArr[i3] = i6 - i;
                i3++;
            }
        }
        return iArr;
    }

    public final boolean m4993c(int i, int i2) {
        int a = this.a.m4918a(this.b[i], this.b[i2]);
        if (a == 0) {
            a = this.b[i];
            int i3 = this.b[i2];
            int[] iArr = new int[]{a, this.a.m4923d(a)};
            int[] iArr2 = new int[]{i3, this.a.m4923d(i3)};
            int[] iArr3 = new int[]{a, this.a.m4922c(a)};
            int[] iArr4 = new int[]{i3, this.a.m4922c(i3)};
            AbstractVertexList abstractVertexList = new AbstractVertexList(this.a, a);
            boolean z;
            if (abstractVertexList.m4913a(iArr, iArr2) < 0) {
                z = true;
            } else {
                z = false;
            }
            if (abstractVertexList.m4913a(iArr3, iArr4) < 0) {
                if (abstractVertexList.m4913a(iArr3, iArr2) < 0 || r0) {
                    return true;
                }
                return false;
            } else if (abstractVertexList.m4913a(iArr, iArr4) >= 0 || !r0) {
                return false;
            } else {
                return true;
            }
        } else if (a < 0) {
            return true;
        } else {
            return false;
        }
    }

    public final void m4994d(int i, int i2) {
        int i3 = this.b[i];
        this.b[i] = this.b[i2];
        this.b[i2] = i3;
    }

    public final void m4988a() {
        throw new UnsupportedOperationException("SortedVertexMapping is immutable.");
    }

    public final void m4992b(int... iArr) {
        throw new UnsupportedOperationException("SortedVertexMapping is immutable.");
    }

    public final void m4995e(int i, int i2) {
        throw new UnsupportedOperationException("SortedVertexMapping is immutable.");
    }

    public final void m4996f(int i, int i2) {
        throw new UnsupportedOperationException("SortedVertexMapping is immutable.");
    }

    public final void m4991b() {
        throw new UnsupportedOperationException("SortedVertexMapping is immutable.");
    }

    public final void m4989a(int i) {
        throw new UnsupportedOperationException("SortedVertexMapping is immutable.");
    }
}
