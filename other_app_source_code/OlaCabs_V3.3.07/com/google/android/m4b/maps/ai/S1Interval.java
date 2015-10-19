package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.an.Point;

/* renamed from: com.google.android.m4b.maps.ai.i */
public final class S1Interval {
    private final double f3151a;
    private final double f3152b;

    private S1Interval(double d, double d2, boolean z) {
        this.f3151a = d;
        this.f3152b = d2;
    }

    public static S1Interval m5049a() {
        return new S1Interval(3.141592653589793d, -3.141592653589793d, true);
    }

    private static S1Interval m5052e() {
        return new S1Interval(-3.141592653589793d, 3.141592653589793d, true);
    }

    public static S1Interval m5050a(double d, double d2) {
        double d3;
        double d4;
        if (d == -3.141592653589793d) {
            d3 = 3.141592653589793d;
        } else {
            d3 = d;
        }
        if (d2 == -3.141592653589793d) {
            d4 = 3.141592653589793d;
        } else {
            d4 = d2;
        }
        if (S1Interval.m5051b(d3, d4) <= 3.141592653589793d) {
            return new S1Interval(d3, d4, true);
        }
        return new S1Interval(d4, d3, true);
    }

    public final boolean m5057b() {
        return this.f3152b - this.f3151a == 6.283185307179586d;
    }

    private boolean m5053f() {
        return this.f3151a - this.f3152b == 6.283185307179586d;
    }

    private boolean m5054g() {
        return this.f3151a > this.f3152b;
    }

    public final double m5058c() {
        double d = 0.5d * (this.f3151a + this.f3152b);
        if (m5054g()) {
            return d <= 0.0d ? d + 3.141592653589793d : d - 3.141592653589793d;
        } else {
            return d;
        }
    }

    public final S1Interval m5059d() {
        if (this.f3151a == this.f3152b) {
            return S1Interval.m5052e();
        }
        return new S1Interval(this.f3152b, this.f3151a, true);
    }

    public final boolean m5056a(double d) {
        if (m5054g()) {
            if ((d >= this.f3151a || d <= this.f3152b) && !m5053f()) {
                return true;
            }
            return false;
        } else if (d < this.f3151a || d > this.f3152b) {
            return false;
        } else {
            return true;
        }
    }

    public final S1Interval m5055a(S1Interval s1Interval) {
        boolean z = true;
        if (s1Interval.m5053f()) {
            return this;
        }
        if (m5056a(s1Interval.f3151a)) {
            if (!m5056a(s1Interval.f3152b)) {
                return new S1Interval(this.f3151a, s1Interval.f3152b, true);
            }
            if (m5054g()) {
                if (s1Interval.m5054g()) {
                    if (s1Interval.f3151a < this.f3151a || s1Interval.f3152b > this.f3152b) {
                        z = false;
                    }
                } else if ((s1Interval.f3151a < this.f3151a && s1Interval.f3152b > this.f3152b) || m5053f()) {
                    z = false;
                }
            } else if (s1Interval.m5054g()) {
                if (!(m5057b() || s1Interval.m5053f())) {
                    z = false;
                }
            } else if (s1Interval.f3151a < this.f3151a || s1Interval.f3152b > this.f3152b) {
                z = false;
            }
            if (z) {
                return this;
            }
            return S1Interval.m5052e();
        } else if (m5056a(s1Interval.f3152b)) {
            return new S1Interval(s1Interval.f3151a, this.f3152b, true);
        } else {
            if (m5053f() || s1Interval.m5056a(this.f3151a)) {
                return s1Interval;
            }
            if (S1Interval.m5051b(s1Interval.f3152b, this.f3151a) < S1Interval.m5051b(this.f3152b, s1Interval.f3151a)) {
                return new S1Interval(s1Interval.f3151a, this.f3152b, true);
            }
            return new S1Interval(this.f3151a, s1Interval.f3152b, true);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof S1Interval)) {
            return false;
        }
        S1Interval s1Interval = (S1Interval) obj;
        if (this.f3151a == s1Interval.f3151a && this.f3152b == s1Interval.f3152b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        long doubleToLongBits = ((629 + Double.doubleToLongBits(this.f3151a)) * 37) + Double.doubleToLongBits(this.f3152b);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public final String toString() {
        return "[" + this.f3151a + ", " + this.f3152b + "]";
    }

    private static double m5051b(double d, double d2) {
        double d3 = d2 - d;
        return d3 >= 0.0d ? d3 : (d2 + 3.141592653589793d) - (d - 3.141592653589793d);
    }

    public static double m5048a(int i) {
        return (((double) Point.m5933b(i)) / 5.36870912E8d) * 3.141592653589793d;
    }
}
