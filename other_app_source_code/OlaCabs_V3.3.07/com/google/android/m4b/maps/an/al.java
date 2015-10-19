package com.google.android.m4b.maps.an;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: TriangleList */
public final class al {
    private final int[] f3472a;
    private final int[] f3473b;

    private al(int[] iArr, int[] iArr2) {
        this.f3472a = iArr;
        this.f3473b = iArr2;
    }

    public static al m5569a(DataInput dataInput, ac acVar) {
        int a = an.m5578a(dataInput);
        if (a % 3 != 0) {
            throw new IOException("Malformed TriangleList, " + a + " vertices");
        }
        int[] iArr = new int[(a * 3)];
        for (int i = 0; i < a; i++) {
            Point.m5932a(dataInput, acVar, iArr, i);
        }
        return new al(iArr, null);
    }

    public static al m5570a(DataInput dataInput, ae aeVar) {
        int i = 0;
        int a = an.m5578a(dataInput);
        if (a % 3 != 0) {
            throw new IOException("Malformed TriangleList, " + a + " vertices");
        }
        int i2;
        int[] iArr = new int[(a * 3)];
        ac b = aeVar.m5451b();
        for (i2 = 0; i2 < a; i2++) {
            Point.m5937b(dataInput, b, iArr, i2);
        }
        i2 = an.m5578a(dataInput);
        int[] iArr2 = new int[i2];
        while (i < i2) {
            iArr2[i] = an.m5578a(dataInput);
            i++;
        }
        return new al(iArr, iArr2);
    }

    public final int m5571a() {
        return this.f3472a.length / 9;
    }

    public final void m5572a(int i, Point point, Point point2, Point point3) {
        int i2 = i * 9;
        int i3 = i2 + 1;
        point.f3646a = this.f3472a[i2];
        int i4 = i3 + 1;
        point.f3647b = this.f3472a[i3];
        i3 = i4 + 1;
        point.f3648c = this.f3472a[i4];
        i4 = i3 + 1;
        point2.f3646a = this.f3472a[i3];
        i3 = i4 + 1;
        point2.f3647b = this.f3472a[i4];
        i4 = i3 + 1;
        point2.f3648c = this.f3472a[i3];
        i3 = i4 + 1;
        point3.f3646a = this.f3472a[i4];
        i4 = i3 + 1;
        point3.f3647b = this.f3472a[i3];
        point3.f3648c = this.f3472a[i4];
    }

    public final void m5573a(int i, Point point, Point point2, Point point3, Point point4) {
        int i2 = i * 9;
        int i3 = i2 + 1;
        point2.f3646a = this.f3472a[i2] - point.f3646a;
        int i4 = i3 + 1;
        point2.f3647b = this.f3472a[i3] - point.f3647b;
        i3 = i4 + 1;
        point2.f3648c = this.f3472a[i4] - point.f3648c;
        i4 = i3 + 1;
        point3.f3646a = this.f3472a[i3] - point.f3646a;
        i3 = i4 + 1;
        point3.f3647b = this.f3472a[i4] - point.f3647b;
        i4 = i3 + 1;
        point3.f3648c = this.f3472a[i3] - point.f3648c;
        i3 = i4 + 1;
        point4.f3646a = this.f3472a[i4] - point.f3646a;
        i4 = i3 + 1;
        point4.f3647b = this.f3472a[i3] - point.f3647b;
        point4.f3648c = this.f3472a[i4] - point.f3648c;
    }

    public final void m5574a(az azVar) {
        for (int i = 0; i < m5571a(); i++) {
            Point[] pointArr = new Point[]{new Point(), new Point(), new Point()};
            m5572a(i, pointArr[0], pointArr[1], pointArr[2]);
            azVar.m5728a(new Polygon2D(pointArr));
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof al)) {
            return false;
        }
        al alVar = (al) obj;
        if (Arrays.equals(this.f3472a, alVar.f3472a) && Arrays.equals(this.f3473b, alVar.f3473b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f3472a) + Arrays.hashCode(this.f3473b);
    }

    public final int m5575b() {
        return (((this.f3473b == null ? 0 : this.f3473b.length) + this.f3472a.length) * 4) + 28;
    }
}
