package com.google.android.m4b.maps.an;

import java.io.DataInput;

/* renamed from: com.google.android.m4b.maps.an.z */
public final class TextStyle {
    private static TextStyle f3721g;
    private final int f3722a;
    private final int f3723b;
    private final int f3724c;
    private final float f3725d;
    private final float f3726e;
    private final int f3727f;

    static {
        f3721g = new TextStyle(0, 0, 12, 0.0f, 0.0f, 0);
    }

    public TextStyle(int i, int i2, int i3, float f, float f2, int i4) {
        this.f3722a = i;
        this.f3723b = i2;
        this.f3724c = i3;
        this.f3725d = f;
        this.f3726e = f2;
        this.f3727f = i4;
    }

    public static TextStyle m6121a(DataInput dataInput) {
        return new TextStyle(dataInput.readInt(), dataInput.readInt(), dataInput.readUnsignedByte(), ((float) dataInput.readUnsignedShort()) / 100.0f, ((float) dataInput.readUnsignedShort()) / 100.0f, dataInput.readUnsignedByte());
    }

    public static TextStyle m6120a() {
        return f3721g;
    }

    public final boolean m6122b() {
        return ModelUtil.m5887a(1, this.f3727f);
    }

    public final boolean m6123c() {
        return ModelUtil.m5887a(2, this.f3727f);
    }

    public final int m6124d() {
        return this.f3722a;
    }

    public final int m6125e() {
        return this.f3723b;
    }

    public final int m6126f() {
        return this.f3724c;
    }

    public final float m6127g() {
        return this.f3725d;
    }

    public final int hashCode() {
        return ((((((((((this.f3727f + 31) * 31) + this.f3722a) * 31) + Float.floatToIntBits(this.f3725d)) * 31) + this.f3723b) * 31) + this.f3724c) * 31) + Float.floatToIntBits(this.f3726e);
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
        TextStyle textStyle = (TextStyle) obj;
        if (this.f3727f != textStyle.f3727f) {
            return false;
        }
        if (this.f3722a != textStyle.f3722a) {
            return false;
        }
        if (this.f3725d != textStyle.f3725d) {
            return false;
        }
        if (this.f3723b != textStyle.f3723b) {
            return false;
        }
        if (this.f3724c != textStyle.f3724c) {
            return false;
        }
        if (this.f3726e != textStyle.f3726e) {
            return false;
        }
        return true;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TextStyle{color=").append(Integer.toHexString(this.f3722a)).append(", outlineColor=").append(Integer.toHexString(this.f3723b)).append(", size=").append(this.f3724c).append(", leadingRatio=").append(this.f3725d).append(", trackingRatio=").append(this.f3726e).append(", attributes=").append(this.f3727f).append('}');
        return stringBuilder.toString();
    }
}
