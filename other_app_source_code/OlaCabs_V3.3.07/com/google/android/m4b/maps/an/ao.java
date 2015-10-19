package com.google.android.m4b.maps.an;

import android.util.FloatMath;

/* compiled from: Vector2f */
public final class ao {
    private float f3474a;
    private float f3475b;

    public ao() {
        this.f3474a = 0.0f;
        this.f3475b = 0.0f;
    }

    public final ao m5584a(ao aoVar) {
        this.f3474a = aoVar.f3474a;
        this.f3475b = aoVar.f3475b;
        return this;
    }

    public final ao m5585a(Point point, Point point2) {
        this.f3474a = (float) (point2.f3646a - point.f3646a);
        this.f3475b = (float) (point2.f3647b - point.f3647b);
        return this;
    }

    public final ao m5588b(ao aoVar) {
        this.f3474a += aoVar.f3474a;
        this.f3475b += aoVar.f3475b;
        return this;
    }

    public static Point m5581a(Point point, ao aoVar, Point point2) {
        point2.m5955d(point.f3646a + Math.round(aoVar.f3474a), point.f3647b + Math.round(aoVar.f3475b));
        return point2;
    }

    public final ao m5583a(float f) {
        this.f3474a *= f;
        this.f3475b *= f;
        return this;
    }

    public final ao m5582a() {
        this.f3474a = -this.f3474a;
        this.f3475b = -this.f3475b;
        return this;
    }

    public final float m5589c(ao aoVar) {
        return (this.f3474a * aoVar.f3474a) + (this.f3475b * aoVar.f3475b);
    }

    public final float m5587b() {
        return FloatMath.sqrt(m5589c(this));
    }

    public final ao m5590c() {
        float b = m5587b();
        if (b == 0.0f) {
            this.f3474a = 0.0f;
            this.f3475b = 0.0f;
        } else {
            this.f3474a /= b;
            this.f3475b /= b;
        }
        return this;
    }

    public final ao m5591d() {
        float f = this.f3474a;
        this.f3474a = -this.f3475b;
        this.f3475b = f;
        return this;
    }

    public final boolean m5592d(ao aoVar) {
        return (this.f3474a * aoVar.f3475b) - (aoVar.f3474a * this.f3475b) < 0.0f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        ao aoVar = (ao) obj;
        if (this.f3474a == aoVar.f3474a && this.f3475b == aoVar.f3475b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.f3474a) ^ Float.floatToIntBits(this.f3475b);
    }

    public final boolean m5586a(float f, float f2) {
        return this.f3474a == 0.0f && this.f3475b == 0.0f;
    }

    public final String toString() {
        return "(" + this.f3474a + "," + this.f3475b + ")";
    }
}
