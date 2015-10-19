package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.olacabs.customer.p076d.br;
import java.io.DataInput;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.an.g */
public final class Point implements Comparable<Point> {
    int f3646a;
    int f3647b;
    int f3648c;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        Point point = (Point) obj;
        if (this.f3646a == point.f3646a) {
            return this.f3647b == point.f3647b ? this.f3648c - point.f3648c : this.f3647b - point.f3647b;
        } else {
            return this.f3646a - point.f3646a;
        }
    }

    public Point(int i, int i2) {
        this.f3646a = i;
        this.f3647b = i2;
    }

    public Point(int i, int i2, int i3) {
        this.f3646a = i;
        this.f3647b = i2;
        this.f3648c = i3;
    }

    public Point(Point point) {
        this.f3646a = point.f3646a;
        this.f3647b = point.f3647b;
        this.f3648c = point.f3648c;
    }

    public static Point m5924a(int i, int i2) {
        return Point.m5923a(((double) i) * 1.0E-7d, ((double) i2) * 1.0E-7d);
    }

    public static Point m5934b(int i, int i2) {
        return Point.m5923a(((double) i) * 1.0E-6d, ((double) i2) * 1.0E-6d);
    }

    public static Point m5939c(int i, int i2) {
        return Point.m5923a(((double) i) * 1.0E-5d, ((double) i2) * 1.0E-5d);
    }

    public static Point m5923a(double d, double d2) {
        Point point = new Point();
        point.m5955d((int) Math.round((0.017453292519943295d * d2) * 1.708913188941079E8d), (int) Math.round(Math.log(Math.tan(((d * 0.017453292519943295d) * 0.5d) + 0.7853981633974483d)) * 1.708913188941079E8d));
        return point;
    }

    public static Point m5927a(ProtoBuf protoBuf) {
        return Point.m5924a(protoBuf.m10204d(1), protoBuf.m10204d(2));
    }

    public final int m5941a() {
        return (int) Math.round(m5949b() * 1000000.0d);
    }

    public final double m5949b() {
        return ((Math.atan(Math.exp(((double) this.f3647b) * 5.8516723170686385E-9d)) - 0.7853981633974483d) * 2.0d) * 57.29577951308232d;
    }

    public final int m5952c() {
        return (int) Math.round(m5953d() * 1000000.0d);
    }

    public final double m5953d() {
        double d = (((double) this.f3646a) * 5.8516723170686385E-9d) * 57.29577951308232d;
        while (d > 180.0d) {
            d -= 360.0d;
        }
        while (d < -180.0d) {
            d += 360.0d;
        }
        return d;
    }

    public static double m5920a(double d) {
        return 5.36870912E8d / (2.0015115070354454E7d * Math.cos(0.017453292519943295d * d));
    }

    public final double m5956e() {
        return Point.m5920a(m5949b());
    }

    public static Point m5925a(Point point) {
        return new Point(point.f3646a, point.f3647b, point.f3648c);
    }

    public static Point m5928a(DataInput dataInput, ac acVar) {
        if (acVar.f3383c < 0) {
            int i = -acVar.f3383c;
            return new Point((dataInput.readShort() >> i) + acVar.f3381a, acVar.f3382b + (dataInput.readShort() >> i));
        }
        i = acVar.f3383c;
        return new Point((dataInput.readShort() << i) + acVar.f3381a, acVar.f3382b + (dataInput.readShort() << i));
    }

    static void m5932a(DataInput dataInput, ac acVar, int[] iArr, int i) {
        int i2 = i * 3;
        if (acVar.f3383c < 0) {
            int i3 = -acVar.f3383c;
            int i4 = i2 + 1;
            iArr[i2] = (dataInput.readShort() >> i3) + acVar.f3381a;
            i2 = i4 + 1;
            iArr[i4] = (dataInput.readShort() >> i3) + acVar.f3382b;
            iArr[i2] = 0;
            return;
        }
        i3 = acVar.f3383c;
        i4 = i2 + 1;
        iArr[i2] = (dataInput.readShort() << i3) + acVar.f3381a;
        i2 = i4 + 1;
        iArr[i4] = (dataInput.readShort() << i3) + acVar.f3382b;
        iArr[i2] = 0;
    }

    static void m5937b(DataInput dataInput, ac acVar, int[] iArr, int i) {
        int i2 = i * 3;
        if (acVar.f3383c < 0) {
            int i3 = -acVar.f3383c;
            int i4 = i2 + 1;
            iArr[i2] = (dataInput.readShort() >> i3) + acVar.f3381a;
            i2 = i4 + 1;
            iArr[i4] = (dataInput.readShort() >> i3) + acVar.f3382b;
            iArr[i2] = dataInput.readInt() >> i3;
            return;
        }
        i3 = acVar.f3383c;
        i4 = i2 + 1;
        iArr[i2] = (dataInput.readShort() << i3) + acVar.f3381a;
        i2 = i4 + 1;
        iArr[i4] = (dataInput.readShort() << i3) + acVar.f3382b;
        iArr[i2] = dataInput.readInt() << i3;
    }

    public final int m5958f() {
        return this.f3646a;
    }

    public final int m5960g() {
        return this.f3647b;
    }

    public final int m5962h() {
        return this.f3648c;
    }

    public final void m5948a(int[] iArr, int i) {
        int i2 = i * 3;
        iArr[i2] = this.f3646a;
        iArr[i2 + 1] = this.f3647b;
        iArr[i2 + 2] = this.f3648c;
    }

    public final void m5946a(int i, int[] iArr, int i2) {
        int i3 = (int) ((((long) this.f3647b) << 16) / ((long) i));
        int i4 = (int) ((((long) this.f3648c) << 16) / ((long) i));
        iArr[i2] = (int) ((((long) this.f3646a) << 16) / ((long) i));
        iArr[i2 + 1] = i3;
        iArr[i2 + 2] = i4;
    }

    public final void m5950b(Point point) {
        this.f3646a = point.f3646a;
        this.f3647b = point.f3647b;
        this.f3648c = point.f3648c;
    }

    public final void m5944a(int i) {
        this.f3648c = i;
    }

    public final void m5955d(int i, int i2) {
        this.f3646a = i;
        this.f3647b = i2;
        this.f3648c = 0;
    }

    public final void m5945a(int i, int i2, int i3) {
        this.f3646a = i;
        this.f3647b = i2;
        this.f3648c = i3;
    }

    public final void m5943a(float f, float f2) {
        float f3 = (3.1415927f * f) / 180.0f;
        m5955d((int) (((double) f2) * Math.sin((double) f3)), (int) (((double) f2) * Math.cos((double) f3)));
    }

    public final float m5964i() {
        return (float) Math.sqrt((double) (((((float) this.f3646a) * ((float) this.f3646a)) + (((float) this.f3647b) * ((float) this.f3647b))) + (((float) this.f3648c) * ((float) this.f3648c))));
    }

    public final float m5951c(Point point) {
        float f = (float) (this.f3646a - point.f3646a);
        float f2 = (float) (this.f3647b - point.f3647b);
        float f3 = (float) (this.f3648c - point.f3648c);
        return (float) Math.sqrt((double) (((f * f) + (f2 * f2)) + (f3 * f3)));
    }

    public final float m5954d(Point point) {
        float f = (float) (this.f3646a - point.f3646a);
        float f2 = (float) (this.f3647b - point.f3647b);
        float f3 = (float) (this.f3648c - point.f3648c);
        return ((f * f) + (f2 * f2)) + (f3 * f3);
    }

    public static Point m5926a(Point point, Point point2, float f) {
        int i;
        Point point3 = new Point();
        float f2 = -f;
        if (Math.abs(point2.f3646a) < 268435456 || ((point.f3646a < 0 && point2.f3646a < 0) || (point.f3646a >= 0 && point2.f3646a >= 0))) {
            i = point2.f3646a - point.f3646a;
        } else {
            i = 1073741824 - (Math.abs(point.f3646a) + Math.abs(point2.f3646a));
            if (point.f3646a < 0) {
                i = -i;
            }
        }
        point3.m5945a(((int) (((float) i) * f2)) + point.f3646a, ((int) (((float) (point2.f3647b - point.f3647b)) * f2)) + point.f3647b, ((int) (f2 * ((float) (point2.f3648c - point.f3648c)))) + point.f3648c);
        return point3;
    }

    public static void m5931a(Point point, Point point2, Point point3) {
        point3.f3646a = point.f3646a + point2.f3646a;
        point3.f3647b = point.f3647b + point2.f3647b;
        point3.f3648c = point.f3648c + point2.f3648c;
    }

    public final Point m5957e(Point point) {
        return new Point(this.f3646a + point.f3646a, this.f3647b + point.f3647b, this.f3648c + point.f3648c);
    }

    public static void m5936b(Point point, Point point2, Point point3) {
        point3.f3646a = point.f3646a - point2.f3646a;
        point3.f3647b = point.f3647b - point2.f3647b;
        point3.f3648c = point.f3648c - point2.f3648c;
    }

    public final Point m5959f(Point point) {
        return new Point(this.f3646a - point.f3646a, this.f3647b - point.f3647b, this.f3648c - point.f3648c);
    }

    public static void m5929a(Point point, float f, Point point2) {
        point2.f3646a = (int) (((float) point.f3646a) * f);
        point2.f3647b = (int) (((float) point.f3647b) * f);
        point2.f3648c = (int) (((float) point.f3648c) * f);
    }

    public static void m5935b(Point point, float f, Point point2) {
        float i = point.m5964i();
        point2.f3646a = (int) ((((float) point.f3646a) * f) / i);
        point2.f3647b = (int) ((((float) point.f3647b) * f) / i);
        point2.f3648c = (int) ((((float) point.f3648c) * f) / i);
    }

    public static float m5921a(Point point, Point point2) {
        return ((((float) point.f3646a) * ((float) point2.f3646a)) + (((float) point.f3647b) * ((float) point2.f3647b))) + (((float) point.f3648c) * ((float) point2.f3648c));
    }

    public final Point m5961g(Point point) {
        Point point2 = new Point();
        long j = (long) this.f3646a;
        long j2 = (long) this.f3647b;
        long j3 = (long) this.f3648c;
        long j4 = (long) point.f3646a;
        long j5 = (long) point.f3647b;
        long j6 = (long) point.f3648c;
        point2.f3646a = (int) ((j2 * j6) - (j3 * j5));
        point2.f3647b = (int) ((j3 * j4) - (j6 * j));
        point2.f3648c = (int) ((j * j5) - (j2 * j4));
        return point2;
    }

    public static void m5930a(Point point, Point point2, float f, Point point3) {
        point3.f3646a = ((int) (((float) (point2.f3646a - point.f3646a)) * f)) + point.f3646a;
        point3.f3647b = ((int) (((float) (point2.f3647b - point.f3647b)) * f)) + point.f3647b;
        point3.f3648c = ((int) (((float) (point2.f3648c - point.f3648c)) * f)) + point.f3648c;
    }

    public final Point m5942a(Point point, float f) {
        Point point2 = new Point();
        Point.m5930a(this, point, f, point2);
        return point2;
    }

    public static float m5938c(Point point, Point point2, Point point3) {
        float f = (float) (point2.f3646a - point.f3646a);
        float f2 = (float) (point2.f3647b - point.f3647b);
        float f3 = (float) (point2.f3648c - point.f3648c);
        return (((((float) (point3.f3646a - point.f3646a)) * f) + (((float) (point3.f3647b - point.f3647b)) * f2)) + (f3 * ((float) (point3.f3648c - point.f3648c)))) / (((f * f) + (f2 * f2)) + (f3 * f3));
    }

    public static float m5922a(Point point, Point point2, Point point3, Point point4) {
        float c = Point.m5938c(point, point2, point3);
        if (c <= 0.0f) {
            point4.m5950b(point);
        } else if (c >= br.DEFAULT_BACKOFF_MULT) {
            point4.m5950b(point2);
        } else {
            Point.m5930a(point, point2, c, point4);
        }
        return point3.m5954d(point4);
    }

    public static boolean m5940e(int i, int i2) {
        return i < -536870912 || i >= 536870912 || i2 < -536870912 || i2 >= 536870912;
    }

    public static int m5933b(int i) {
        int i2 = i;
        while (i2 < -536870912) {
            i2 += 1073741824;
        }
        while (i2 >= 536870912) {
            i2 -= 1073741824;
        }
        return i2;
    }

    public final void m5963h(Point point) {
        int i = -536870912;
        point.f3646a = Point.m5933b(this.f3646a);
        int i2 = this.f3647b;
        if (i2 >= -536870912) {
            i = i2 >= 536870912 ? 536870911 : i2;
        }
        point.f3647b = i;
        point.f3648c = this.f3648c;
    }

    public final void m5947a(Point point, float f, float f2) {
        point.f3646a = Point.m5933b(this.f3646a);
        int ceil = 536870912 - ((int) Math.ceil((double) ((0.5f * f2) * Model.m5880a(f))));
        if (ceil < 0) {
            ceil = 0;
        }
        if (this.f3647b > ceil) {
            point.f3647b = ceil;
        } else if (this.f3647b < (-ceil)) {
            point.f3647b = -ceil;
        } else {
            point.f3647b = this.f3647b;
        }
        point.f3648c = this.f3648c;
    }

    public final void m5965i(Point point) {
        point.f3646a = Point.m5933b(this.f3646a);
        point.f3647b = this.f3647b;
        point.f3648c = this.f3648c;
    }

    public final String toString() {
        return "(" + this.f3646a + "," + this.f3647b + "," + this.f3648c + ")";
    }

    public final String m5966j() {
        return String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(m5949b()), Double.valueOf(m5953d())});
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        if (this.f3646a == point.f3646a && this.f3647b == point.f3647b && this.f3648c == point.f3648c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f3646a;
        int i2 = this.f3647b;
        int i3 = this.f3648c;
        i = ((i - i2) - i3) ^ (i3 >> 13);
        i2 = ((i2 - i3) - i) ^ (i << 8);
        i3 = ((i3 - i) - i2) ^ (i2 >> 13);
        i = ((i - i2) - i3) ^ (i3 >> 12);
        i2 = ((i2 - i3) - i) ^ (i << 16);
        i3 = ((i3 - i) - i2) ^ (i2 >> 5);
        i = ((i - i2) - i3) ^ (i3 >> 3);
        i2 = ((i2 - i3) - i) ^ (i << 10);
        return ((i3 - i) - i2) ^ (i2 >> 15);
    }
}
