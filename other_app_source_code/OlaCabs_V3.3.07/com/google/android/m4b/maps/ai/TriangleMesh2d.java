package com.google.android.m4b.maps.ai;

/* renamed from: com.google.android.m4b.maps.ai.k */
public class TriangleMesh2d {
    private static final TriangleMesh2d f3153a;
    private final VertexMapping f3154b;
    private int f3155c;

    /* renamed from: com.google.android.m4b.maps.ai.k.a */
    final class TriangleMesh2d extends TriangleMesh2d {
        public TriangleMesh2d() {
            super((byte) 0);
        }

        public final boolean m5071a(int i, int i2, int i3) {
            throw new UnsupportedOperationException("Cannot add triangle to immutable empty mesh");
        }

        public final double m5072c() {
            return 0.0d;
        }

        public final int m5073d() {
            return 0;
        }

        public final int m5074e() {
            return 0;
        }

        public final boolean equals(Object obj) {
            if (this == obj || super.equals(obj)) {
                return true;
            }
            if ((obj instanceof TriangleMesh2d) && ((TriangleMesh2d) obj).m5069d() == 0) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return 0;
        }
    }

    static {
        f3153a = new TriangleMesh2d();
    }

    public static TriangleMesh2d m5061a(VertexMapping vertexMapping) {
        return new TriangleMesh2d(vertexMapping.m4965c());
    }

    public static TriangleMesh2d m5060a() {
        return f3153a;
    }

    private TriangleMesh2d(VertexMapping vertexMapping) {
        this.f3155c = 0;
        this.f3154b = vertexMapping;
    }

    public boolean m5064a(int i, int i2, int i3) {
        if (this.f3154b.m4964c(i, i2, i3) > 0.0d) {
            this.f3154b.m4962b(i, i2, i3);
            return true;
        }
        this.f3155c++;
        return false;
    }

    public final boolean m5065a(Polygon2d polygon2d) {
        double a = polygon2d.m5002a();
        double c = m5068c();
        return Math.abs(a - c) <= 0.001d * a || a == c || (Double.isNaN(a) && Double.isNaN(c));
    }

    public final void m5067b() {
        this.f3155c++;
    }

    public double m5068c() {
        double d = 0.0d;
        for (int i = 0; i < this.f3154b.f3119c; i += 3) {
            d += this.f3154b.m4960b(i, i + 1, i + 2);
        }
        return d;
    }

    public int m5069d() {
        return this.f3154b == null ? 0 : this.f3154b.f3119c / 3;
    }

    public int m5070e() {
        return this.f3154b == null ? 0 : this.f3154b.m4968e();
    }

    public final double m5062a(int i) {
        if (i >= 0 && i < this.f3154b.m4968e()) {
            return this.f3154b.m4959b(i);
        }
        throw new IllegalArgumentException();
    }

    public final double m5066b(int i) {
        if (i >= 0 && i < this.f3154b.m4968e()) {
            return this.f3154b.m4963c(i);
        }
        throw new IllegalArgumentException();
    }

    public final int m5063a(int i, int i2) {
        if (i >= 0 && i < m5069d() && i2 >= 0 && i2 < 3) {
            return this.f3154b.m4980j((i * 3) + i2);
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        int i;
        int i2 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nint[] triangles = {\n    ");
        int i3 = this.f3154b.f3119c;
        for (i = 0; i < i3; i += 3) {
            stringBuilder.append(this.f3154b.m4980j(i));
            stringBuilder.append(", ");
            stringBuilder.append(this.f3154b.m4980j(i + 1));
            stringBuilder.append(", ");
            stringBuilder.append(this.f3154b.m4980j(i + 2));
            if (i == this.f3154b.f3119c - 3) {
                stringBuilder.append("\n};\n\n");
            } else {
                stringBuilder.append(",\n    ");
            }
        }
        i = this.f3154b.m4968e();
        while (i2 < i) {
            stringBuilder.append("cutVertices.add(new Vertex2d(");
            stringBuilder.append(this.f3154b.m4959b(i2));
            stringBuilder.append(", ");
            stringBuilder.append(this.f3154b.m4963c(i2));
            stringBuilder.append("));\n");
            i2++;
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof TriangleMesh2d) {
            return ((TriangleMesh2d) obj).f3154b.equals(this.f3154b);
        }
        return false;
    }

    public int hashCode() {
        return this.f3154b.hashCode();
    }
}
