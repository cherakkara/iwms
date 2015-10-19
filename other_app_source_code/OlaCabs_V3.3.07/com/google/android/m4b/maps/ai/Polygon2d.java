package com.google.android.m4b.maps.ai;

/* renamed from: com.google.android.m4b.maps.ai.d */
public final class Polygon2d {
    private final PolygonVertexList f3121a;

    Polygon2d(PolygonVertexList polygonVertexList) {
        this.f3121a = polygonVertexList;
    }

    public final double m5002a() {
        return this.f3121a.m5033d();
    }

    public final boolean m5003b() {
        return this.f3121a.m5037f();
    }

    public final boolean m5004c() {
        return this.f3121a.m5038g();
    }

    public final boolean m5005d() {
        return this.f3121a.m5036e();
    }

    public final boolean m5006e() {
        return this.f3121a.m5039h();
    }

    final PolygonVertexList m5007f() {
        return this.f3121a;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append(this.f3121a.toString());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Polygon2d) {
            return ((Polygon2d) obj).f3121a.equals(this.f3121a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f3121a.hashCode();
    }
}
