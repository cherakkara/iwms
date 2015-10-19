package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.olacabs.customer.p076d.br;
import java.io.DataInput;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.an.s */
public final class Stroke {
    private static Stroke f3696e;
    private final int f3697a;
    private final float f3698b;
    private final int[] f3699c;
    private final int f3700d;

    static {
        f3696e = new Stroke(0, br.DEFAULT_BACKOFF_MULT, new int[0], 0);
    }

    public Stroke(int i, float f, int[] iArr, int i2) {
        this.f3697a = i;
        this.f3698b = f;
        this.f3699c = iArr;
        this.f3700d = i2;
    }

    public static Stroke m6082a(DataInput dataInput) {
        int readInt = dataInput.readInt();
        float readUnsignedByte = ((float) dataInput.readUnsignedByte()) / 8.0f;
        int a = an.m5578a(dataInput);
        int[] iArr = new int[a];
        for (int i = 0; i < a; i++) {
            iArr[i] = dataInput.readShort();
        }
        if (DeviceSpecificInfo.f8007b) {
            iArr = new int[0];
        }
        for (int i2 : r0) {
            if (i2 == 0) {
                iArr = new int[0];
                break;
            }
        }
        return new Stroke(readInt, readUnsignedByte, iArr, dataInput.readUnsignedByte());
    }

    public static Stroke m6081a() {
        return f3696e;
    }

    public final int m6083b() {
        return this.f3697a;
    }

    public final float m6084c() {
        return this.f3698b;
    }

    public final int[] m6085d() {
        return this.f3699c;
    }

    public final boolean m6086e() {
        return this.f3699c != null && this.f3699c.length > 0;
    }

    public final boolean m6087f() {
        return (this.f3700d & 1) != 0;
    }

    public final boolean m6088g() {
        return (this.f3700d & 2) != 0;
    }

    public final int hashCode() {
        return ((((((this.f3697a + 31) * 31) + Arrays.hashCode(this.f3699c)) * 31) + this.f3700d) * 31) + Float.floatToIntBits(this.f3698b);
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
        Stroke stroke = (Stroke) obj;
        if (this.f3697a != stroke.f3697a) {
            return false;
        }
        if (!Arrays.equals(this.f3699c, stroke.f3699c)) {
            return false;
        }
        if (this.f3700d != stroke.f3700d) {
            return false;
        }
        if (Float.floatToIntBits(this.f3698b) != Float.floatToIntBits(stroke.f3698b)) {
            return false;
        }
        return true;
    }

    public final int m6089h() {
        return (this.f3699c.length * 4) + 24;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stroke{color=").append(Integer.toHexString(this.f3697a)).append(", width=").append(this.f3698b).append(", dashes=").append(Arrays.toString(this.f3699c)).append(", endCaps=");
        if ((this.f3700d & 1) != 0) {
            stringBuilder.append("S");
        }
        if ((this.f3700d & 2) != 0) {
            stringBuilder.append("E");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
