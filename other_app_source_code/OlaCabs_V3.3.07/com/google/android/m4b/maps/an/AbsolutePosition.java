package com.google.android.m4b.maps.an;

import java.io.DataInput;

/* renamed from: com.google.android.m4b.maps.an.a */
public final class AbsolutePosition {
    private final Point f3371a;
    private final int f3372b;
    private final float f3373c;
    private final Point f3374d;
    private final float f3375e;
    private final float f3376f;
    private final float f3377g;

    public AbsolutePosition(Point point, int i, float f, Point point2, float f2, float f3, float f4) {
        this.f3371a = point;
        this.f3372b = i;
        this.f3373c = f;
        this.f3374d = point2;
        this.f3375e = f2;
        this.f3376f = f3;
        this.f3377g = f4;
    }

    public static AbsolutePosition m5404a(DataInput dataInput, ac acVar) {
        float a;
        float a2;
        float a3;
        float a4;
        Point a5 = Point.m5928a(dataInput, acVar);
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (ModelUtil.m5887a(readUnsignedByte, 1)) {
            a = ((float) an.m5578a(dataInput)) / 10.0f;
        } else {
            a = Float.NaN;
        }
        Point point = null;
        if (ModelUtil.m5887a(readUnsignedByte, 2)) {
            point = Point.m5928a(dataInput, acVar);
            a2 = ((float) an.m5578a(dataInput)) / 10.0f;
            a3 = ((float) an.m5578a(dataInput)) / 8.0f;
            a4 = ((float) an.m5578a(dataInput)) / 8.0f;
        } else {
            a4 = Float.NaN;
            a3 = Float.NaN;
            a2 = Float.NaN;
        }
        return new AbsolutePosition(a5, readUnsignedByte, a, point, a2, a3, a4);
    }

    public final boolean m5405a() {
        return ModelUtil.m5887a(this.f3372b, 1);
    }

    public final Point m5406b() {
        return this.f3371a;
    }

    public final float m5407c() {
        return this.f3373c;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((((this.f3374d == null ? 0 : this.f3374d.hashCode()) + 31) * 31) + Float.floatToIntBits(this.f3376f)) * 31) + Float.floatToIntBits(this.f3375e)) * 31) + Float.floatToIntBits(this.f3377g)) * 31) + this.f3372b) * 31;
        if (this.f3371a != null) {
            i = this.f3371a.hashCode();
        }
        return ((hashCode + i) * 31) + Float.floatToIntBits(this.f3373c);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbsolutePosition absolutePosition = (AbsolutePosition) obj;
        if (this.f3374d == null) {
            if (absolutePosition.f3374d != null) {
                return false;
            }
        } else if (!this.f3374d.equals(absolutePosition.f3374d)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3376f) != Float.floatToIntBits(absolutePosition.f3376f)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3375e) != Float.floatToIntBits(absolutePosition.f3375e)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3377g) != Float.floatToIntBits(absolutePosition.f3377g)) {
            return false;
        }
        if (this.f3372b != absolutePosition.f3372b) {
            return false;
        }
        if (this.f3371a == null) {
            if (absolutePosition.f3371a != null) {
                return false;
            }
        } else if (!this.f3371a.equals(absolutePosition.f3371a)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3373c) != Float.floatToIntBits(absolutePosition.f3373c)) {
            return false;
        }
        return true;
    }

    public final int m5408d() {
        return (ModelUtil.m5882a(this.f3371a) + 40) + ModelUtil.m5882a(this.f3374d);
    }
}
