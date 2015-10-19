package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.ai.AbstractVertexList.AbstractVertexList;

/* renamed from: com.google.android.m4b.maps.ai.m */
class VertexMapping {
    protected AbstractVertexList f3117a;
    protected int[] f3118b;
    int f3119c;

    public final VertexMapping m4965c() {
        return new VertexMapping(this.f3117a);
    }

    protected VertexMapping(double[] dArr) {
        this.f3117a = PolygonVertexList.m5019a(dArr);
        this.f3118b = new int[4];
        this.f3119c = 0;
    }

    protected VertexMapping(AbstractVertexList abstractVertexList) {
        this.f3117a = abstractVertexList;
        this.f3118b = new int[4];
        this.f3119c = 0;
    }

    protected VertexMapping(AbstractVertexList abstractVertexList, int[] iArr) {
        this.f3117a = abstractVertexList;
        this.f3118b = iArr;
        this.f3119c = iArr.length;
    }

    public final double m4959b(int i) {
        return this.f3117a.m4915a(i);
    }

    public final double m4963c(int i) {
        return this.f3117a.m4921b(i);
    }

    public final int m4975g(int i, int i2) {
        return this.f3117a.m4918a(i, i2);
    }

    public final int m4967d(int i) {
        return this.f3117a.m4922c(i);
    }

    public final int m4969e(int i) {
        return this.f3117a.m4923d(i);
    }

    public final int m4966d() {
        return this.f3117a.m4917a();
    }

    public final int m4968e() {
        return this.f3117a.f3111a;
    }

    public final double m4955a(int i, int i2, int i3) {
        return this.f3117a.m4916a(i, i2, i3);
    }

    public final AbstractVertexList m4972f(int i) {
        return this.f3117a.m4924e(i);
    }

    public final double m4974g(int i) {
        return this.f3117a.m4915a(m4980j(i));
    }

    public final double m4976h(int i) {
        return this.f3117a.m4921b(m4980j(i));
    }

    public final boolean m4978i(int i) {
        return m4980j(i) == -1;
    }

    public final int m4980j(int i) {
        return this.f3118b[m4952o(i)];
    }

    private int m4951n(int i) {
        return this.f3117a.m4927h(m4980j(i - 1));
    }

    public final int m4971f() {
        return this.f3117a.m4927h(m4980j(this.f3119c - 2));
    }

    public final int m4977h(int i, int i2) {
        int h = this.f3117a.m4927h(i);
        while (i2 < this.f3119c) {
            if (m4980j(i2) == h) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public final boolean m4979i(int i, int i2) {
        return this.f3117a.m4927h(m4980j(i2)) == this.f3117a.m4927h(i);
    }

    public final boolean m4981k(int i) {
        return m4951n(i) == this.f3117a.m4923d(m4980j(i));
    }

    public final boolean m4982l(int i) {
        return m4951n(i) == this.f3117a.m4922c(m4980j(i));
    }

    public final double m4960b(int i, int i2, int i3) {
        return (-(((this.f3117a.m4915a(this.f3118b[i]) - this.f3117a.m4915a(this.f3118b[i2])) * (this.f3117a.m4921b(this.f3118b[i3]) - this.f3117a.m4921b(this.f3118b[i2]))) - ((this.f3117a.m4921b(this.f3118b[i]) - this.f3117a.m4921b(this.f3118b[i2])) * (this.f3117a.m4915a(this.f3118b[i3]) - this.f3117a.m4915a(this.f3118b[i2]))))) / 2.0d;
    }

    public final double m4964c(int i, int i2, int i3) {
        AbstractVertexList abstractVertexList = this.f3117a;
        return (-(((abstractVertexList.m4915a(i) - abstractVertexList.m4915a(i2)) * (abstractVertexList.m4921b(i3) - abstractVertexList.m4921b(i2))) - ((abstractVertexList.m4915a(i3) - abstractVertexList.m4915a(i2)) * (abstractVertexList.m4921b(i) - abstractVertexList.m4921b(i2))))) / 2.0d;
    }

    public void m4956a() {
        this.f3119c = 0;
    }

    public void m4962b(int... iArr) {
        m4954q(iArr.length);
        for (int p : iArr) {
            this.f3118b[this.f3119c] = m4953p(p);
            this.f3119c++;
        }
    }

    public void m4970e(int i, int i2) {
        m4954q(1);
        System.arraycopy(this.f3118b, i, this.f3118b, i + 1, this.f3119c - i);
        this.f3118b[i] = m4953p(i2);
        this.f3119c++;
    }

    public void m4973f(int i, int i2) {
        this.f3118b[m4952o(i)] = m4953p(i2);
    }

    public void m4961b() {
        this.f3119c--;
    }

    public void m4957a(int i) {
        System.arraycopy(this.f3118b, i + 1, this.f3118b, i, (this.f3119c - i) - 1);
        this.f3119c--;
    }

    private int m4952o(int i) {
        if (this.f3119c == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i2 = i % this.f3119c;
        return i2 < 0 ? i2 + this.f3119c : i2;
    }

    private int m4953p(int i) {
        if (i == -1) {
            return -1;
        }
        return this.f3117a.m4927h(i);
    }

    private void m4954q(int i) {
        if (this.f3118b.length < this.f3119c + i) {
            Object obj = new int[(this.f3119c + Math.max(i, (int) ((((double) this.f3118b.length) * 1.5d) + 1.0d)))];
            System.arraycopy(this.f3118b, 0, obj, 0, this.f3118b.length);
            this.f3118b = obj;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VertexMapping)) {
            return false;
        }
        VertexMapping vertexMapping = (VertexMapping) obj;
        if (vertexMapping.m4958a((Object) this) && this.f3117a.equals(vertexMapping.f3117a)) {
            boolean z;
            int[] iArr = this.f3118b;
            int i = this.f3119c;
            int[] iArr2 = vertexMapping.f3118b;
            int i2 = vertexMapping.f3119c;
            if (i == -1) {
                i = iArr.length;
            }
            if (i2 == -1) {
                i2 = iArr2.length;
            }
            if (i != i2 || iArr == null || iArr2 == null || iArr.length < i || iArr2.length < i) {
                z = false;
            } else {
                for (i2 = 0; i2 < i; i2++) {
                    if (iArr[i2] != iArr2[i2]) {
                        z = false;
                        break;
                    }
                }
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    protected boolean m4958a(Object obj) {
        return obj instanceof VertexMapping;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = this.f3117a.hashCode();
        int[] iArr = this.f3118b;
        int i2 = this.f3119c;
        if (iArr != null) {
            i = 1;
            int i3 = 0;
            while (i3 < i2) {
                int i4 = iArr[i3] + (i * 31);
                i3++;
                i = i4;
            }
        }
        return (i * 31) + hashCode;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append(m4983m(5));
        stringBuilder.append(",");
        stringBuilder.append(this.f3119c);
        stringBuilder.append(",\n");
        stringBuilder.append(this.f3117a);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    final String m4983m(int i) {
        int i2 = this.f3119c - 1;
        if (i2 == -1) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        int i3 = 0;
        while (true) {
            stringBuilder.append(String.format("%" + i + "s", new Object[]{Integer.valueOf(this.f3118b[i3])}));
            if (i3 == i2) {
                return stringBuilder.append(']').toString();
            }
            stringBuilder.append(", ");
            i3++;
        }
    }
}
