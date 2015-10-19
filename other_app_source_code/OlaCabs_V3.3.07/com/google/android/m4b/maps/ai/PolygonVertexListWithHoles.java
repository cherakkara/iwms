package com.google.android.m4b.maps.ai;

import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.ai.h */
final class PolygonVertexListWithHoles extends PolygonVertexList {
    private int[] f3150b;

    protected PolygonVertexListWithHoles(double[] dArr, int[] iArr) {
        super(dArr);
        this.f3150b = iArr;
    }

    public final int m5044c(int i) {
        int f = m5046f(i);
        if (m5040c(i - 1, f)) {
            return i - 1;
        }
        return this.f3150b[f + 1] - 1;
    }

    public final int m5045d(int i) {
        int f = m5046f(i);
        if (m5040c(i + 1, f)) {
            return i + 1;
        }
        return this.f3150b[f];
    }

    public final int m5046f(int i) {
        if (i < 0 || i >= this.a) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i2 = 0;
        while (i >= this.f3150b[i2]) {
            i2++;
        }
        return i2 - 1;
    }

    public final int m5047g(int i) {
        return this.f3150b[i];
    }

    public final int m5042a() {
        return this.f3150b.length - 2;
    }

    private boolean m5040c(int i, int i2) {
        return i >= this.f3150b[i2] && i < this.f3150b[i2 + 1];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PolygonVertexListWithHoles)) {
            return false;
        }
        PolygonVertexListWithHoles polygonVertexListWithHoles = (PolygonVertexListWithHoles) obj;
        if ((this instanceof PolygonVertexListWithHoles) && super.equals(obj) && Arrays.equals(this.f3150b, polygonVertexListWithHoles.f3150b)) {
            return true;
        }
        return false;
    }

    protected final boolean m5043a(Object obj) {
        return obj instanceof PolygonVertexListWithHoles;
    }

    public final int hashCode() {
        return super.hashCode() + (Arrays.hashCode(this.f3150b) * 31);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append(super.toString());
        stringBuilder.append(";");
        stringBuilder.append(m5041i());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private String m5041i() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < this.f3150b.length - 1; i++) {
            stringBuilder.append("\nHole ");
            stringBuilder.append(i);
            stringBuilder.append(":");
            stringBuilder.append(m5030b(this.f3150b[i], this.f3150b[i + 1]));
        }
        return stringBuilder.toString();
    }
}
