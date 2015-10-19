package com.google.android.m4b.maps.ai;

/* renamed from: com.google.android.m4b.maps.ai.l */
public final class Vertex2d {
    private double f3156a;
    private double f3157b;

    public static int m5076a(double d, double d2, double d3, double d4) {
        int compare = Double.compare(d, d3);
        if (compare == 0) {
            return Double.compare(d2, d4);
        }
        return compare;
    }

    public static double m5075a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2));
        return Math.abs(d7) < (((((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6))) + ((d - d3) * (d - d3))) + ((d2 - d4) * (d2 - d4))) * 1.0E-10d ? 0.0d : d7;
    }

    public final String toString() {
        return "(" + this.f3156a + "," + this.f3157b + ")";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vertex2d)) {
            return false;
        }
        Vertex2d vertex2d = (Vertex2d) obj;
        if (vertex2d.f3156a == this.f3156a && vertex2d.f3157b == this.f3157b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f3156a) ^ (Double.doubleToLongBits(this.f3157b) * 31);
        return ((int) (doubleToLongBits >> 32)) ^ ((int) doubleToLongBits);
    }
}
