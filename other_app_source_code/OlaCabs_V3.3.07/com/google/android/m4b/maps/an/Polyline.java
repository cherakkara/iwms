package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.ai.S1Interval;
import com.olacabs.customer.p076d.br;
import java.io.DataInput;
import java.util.Arrays;

/* renamed from: com.google.android.m4b.maps.an.k */
public final class Polyline {
    private final int[] f3672a;
    private volatile Rectangle2D f3673b;
    private volatile float f3674c;

    /* renamed from: com.google.android.m4b.maps.an.k.1 */
    static class Polyline extends ThreadLocal<Point[]> {
        Polyline() {
        }

        protected final /* synthetic */ Object initialValue() {
            return new Point[]{new Point(), new Point()};
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.k.a */
    public static class Polyline {
        private static /* synthetic */ boolean f3669c;
        private int[] f3670a;
        private int f3671b;

        static {
            f3669c = !Polyline.class.desiredAssertionStatus();
        }

        public Polyline() {
            this(16);
        }

        public Polyline(int i) {
            if (f3669c || i > 0) {
                this.f3670a = new int[(i * 3)];
                this.f3671b = 0;
                return;
            }
            throw new AssertionError();
        }

        public final boolean m6006a(Point point) {
            int i = point.f3646a;
            int i2 = point.f3647b;
            int i3 = point.f3648c;
            if (this.f3671b * 3 == this.f3670a.length) {
                Object obj = new int[(this.f3670a.length * 2)];
                System.arraycopy(this.f3670a, 0, obj, 0, this.f3671b * 3);
                this.f3670a = obj;
            }
            int i4 = this.f3671b * 3;
            if (this.f3671b > 0 && i == this.f3670a[i4 - 3] && i2 == this.f3670a[i4 - 2] && i3 == this.f3670a[i4 - 1]) {
                return false;
            }
            this.f3670a[i4] = i;
            this.f3670a[i4 + 1] = i2;
            this.f3670a[i4 + 2] = i3;
            this.f3671b++;
            return true;
        }

        public final int m6004a() {
            return this.f3671b;
        }

        public final Point m6005a(int i) {
            return new Point(this.f3670a[0], this.f3670a[1], this.f3670a[2]);
        }

        public final void m6007b() {
            this.f3671b = 0;
        }

        public final Polyline m6008c() {
            Object obj = new int[(this.f3671b * 3)];
            System.arraycopy(this.f3670a, 0, obj, 0, this.f3671b * 3);
            return new Polyline((byte) 0);
        }
    }

    static {
        Polyline polyline = new Polyline();
    }

    private Polyline(int[] iArr) {
        this.f3672a = iArr;
        this.f3674c = -1.0f;
    }

    public final Rectangle2D m6015a() {
        if (this.f3673b == null) {
            if (this.f3672a.length / 3 > 0) {
                Point a = m6014a(0);
                int i = a.f3646a;
                int i2 = a.f3647b;
                int i3 = i;
                int i4 = i;
                i = i2;
                for (int i5 = 1; i5 < this.f3672a.length / 3; i5++) {
                    m6016a(i5, a);
                    if (a.f3646a < i4) {
                        i4 = a.f3646a;
                    }
                    if (a.f3646a > i3) {
                        i3 = a.f3646a;
                    }
                    if (a.f3647b < i) {
                        i = a.f3647b;
                    }
                    if (a.f3647b > i2) {
                        i2 = a.f3647b;
                    }
                }
                a.m5955d(i4, i);
                this.f3673b = new Rectangle2D(a, new Point(i3, i2));
            } else {
                this.f3673b = new Rectangle2D(new Point(), new Point());
            }
        }
        return this.f3673b;
    }

    public static Polyline m6012a(int[] iArr) {
        return new Polyline(iArr);
    }

    public static Polyline m6010a(Point point, Point point2) {
        int[] iArr = new int[6];
        point.m5948a(iArr, 0);
        point2.m5948a(iArr, 1);
        return new Polyline(iArr);
    }

    public static Polyline m6011a(DataInput dataInput, ac acVar) {
        int a = an.m5578a(dataInput);
        int[] iArr = new int[(a * 3)];
        for (int i = 0; i < a; i++) {
            Point.m5932a(dataInput, acVar, iArr, i);
        }
        return new Polyline(iArr);
    }

    public final int m6020b() {
        return this.f3672a.length / 3;
    }

    public final Point m6014a(int i) {
        int i2 = i * 3;
        return new Point(this.f3672a[i2], this.f3672a[i2 + 1], this.f3672a[i2 + 2]);
    }

    public final void m6016a(int i, Point point) {
        int i2 = i * 3;
        point.f3646a = this.f3672a[i2];
        point.f3647b = this.f3672a[i2 + 1];
        point.f3648c = this.f3672a[i2 + 2];
    }

    public final void m6018a(Point point) {
        int length = this.f3672a.length - 3;
        point.f3646a = this.f3672a[length];
        point.f3647b = this.f3672a[length + 1];
        point.f3648c = this.f3672a[length + 2];
    }

    public final Point m6023c() {
        int length = this.f3672a.length - 3;
        return new Point(this.f3672a[length], this.f3672a[length + 1], this.f3672a[length + 2]);
    }

    public final void m6017a(int i, Point point, Point point2) {
        int i2 = i * 3;
        point2.f3646a = this.f3672a[i2] - point.f3646a;
        point2.f3647b = this.f3672a[i2 + 1] - point.f3647b;
        point2.f3648c = this.f3672a[i2 + 2] - point.f3648c;
    }

    public final float m6026d() {
        float f = 0.0f;
        if (this.f3674c < 0.0f) {
            for (int i = 0; i < (this.f3672a.length / 3) - 1; i++) {
                f += m6019b(i);
            }
            this.f3674c = f;
        }
        return this.f3674c;
    }

    public final float m6019b(int i) {
        int i2 = i * 3;
        int i3 = i2 + 3;
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        float f = (float) (this.f3672a[i2] - this.f3672a[i3]);
        float f2 = (float) (this.f3672a[i4] - this.f3672a[i5]);
        float f3 = (float) (this.f3672a[i4 + 1] - this.f3672a[i5 + 1]);
        return (float) Math.sqrt((double) (((f * f) + (f2 * f2)) + (f3 * f3)));
    }

    public final boolean m6028e() {
        if (this.f3672a.length <= 0) {
            return false;
        }
        int length = this.f3672a.length - 3;
        if (this.f3672a[0] == this.f3672a[length] && this.f3672a[1] == this.f3672a[length + 1] && this.f3672a[2] == this.f3672a[length + 2]) {
            return true;
        }
        return false;
    }

    public final boolean m6029f() {
        int length = this.f3672a.length / 3;
        long j = 0;
        for (int i = 0; i < length - 1; i++) {
            Point a = m6014a(i);
            Point a2 = m6014a(i + 1);
            j += (((long) a.f3646a) * ((long) a2.f3647b)) - (((long) a2.f3646a) * ((long) a.f3647b));
        }
        if (!m6028e()) {
            Point c = m6023c();
            Point a3 = m6014a(0);
            j += (((long) c.f3646a) * ((long) a3.f3647b)) - (((long) a3.f3646a) * ((long) c.f3647b));
        }
        if (j > 0) {
            return true;
        }
        return false;
    }

    public final Point m6013a(float f) {
        if (f <= 0.0f) {
            return m6014a(0);
        }
        if (f >= br.DEFAULT_BACKOFF_MULT) {
            return m6023c();
        }
        int length = (this.f3672a.length / 3) - 1;
        float d = m6026d() * f;
        int i = 0;
        while (i < length) {
            float b = m6019b(i);
            if (b >= d) {
                b = d / b;
                Point point = new Point();
                Point point2 = new Point();
                m6016a(i, point);
                m6016a(i + 1, point2);
                Point.m5930a(point, point2, b, point2);
                return point2;
            }
            i++;
            d -= b;
        }
        return m6023c();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Polyline)) {
            return false;
        }
        return Arrays.equals(this.f3672a, ((Polyline) obj).f3672a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f3672a);
    }

    public final Polyline m6021b(float f) {
        int i = 0;
        if (this.f3672a.length <= 6) {
            return this;
        }
        int length = this.f3672a.length / 3;
        boolean[] zArr = new boolean[length];
        zArr[0] = true;
        zArr[length - 1] = true;
        int a = m6009a(f * f, 1, 0, length - 1, new Point(), new Point(), new Point(), new Point(), zArr) + 2;
        if (a == length) {
            return this;
        }
        int[] iArr = new int[(a * 3)];
        a = 0;
        while (i < length) {
            if (zArr[i]) {
                int i2 = i * 3;
                int i3 = a + 1;
                int i4 = i2 + 1;
                iArr[a] = this.f3672a[i2];
                i2 = i3 + 1;
                int i5 = i4 + 1;
                iArr[i3] = this.f3672a[i4];
                a = i2 + 1;
                iArr[i2] = this.f3672a[i5];
            }
            i++;
        }
        this(iArr);
        return this;
    }

    private int m6009a(float f, int i, int i2, int i3, Point point, Point point2, Point point3, Point point4, boolean[] zArr) {
        m6016a(i2, point);
        m6016a(i3, point2);
        int i4 = -1;
        int i5 = i2 + i;
        float f2 = f;
        while (i5 <= i3 - 1) {
            m6016a(i5, point4);
            float a = Point.m5922a(point, point2, point4, point3);
            if (a > f2) {
                i4 = i5;
            } else {
                a = f2;
            }
            i5 += i;
            f2 = a;
        }
        if (i4 < 0) {
            return 0;
        }
        int i6 = 1;
        zArr[i4] = true;
        if (i4 > i2 + 1) {
            i6 = m6009a(f, i, i2, i4, point, point2, point3, point4, zArr) + 1;
        }
        if (i4 < i3 - 1) {
            return i6 + m6009a(f, i, i4, i3, point, point2, point3, point4, zArr);
        }
        return i6;
    }

    public final Polyline m6024c(float f) {
        boolean e = m6028e();
        int length = this.f3672a.length / 3;
        int i = length - 1;
        if (length <= 2 || f <= 0.0f) {
            return this;
        }
        if (length <= 3 && e) {
            return this;
        }
        Object obj;
        Polyline polyline = new Polyline(length);
        int i2 = e ? i - 1 : 1;
        int i3 = (i2 - 1) * 3;
        int i4 = i2 * 3;
        int i5 = ((i2 + 1) % length) * 3;
        i2 = ((i2 + 2) % length) * 3;
        Point point = new Point(this.f3672a[i3], this.f3672a[i3 + 1], 0);
        Point point2 = new Point(this.f3672a[i4], this.f3672a[i4 + 1], 0);
        Point point3 = new Point(this.f3672a[i5], this.f3672a[i5 + 1], 0);
        Point point4 = new Point(this.f3672a[i2], this.f3672a[i2 + 1], 0);
        Point point5 = new Point();
        if (!e || point2.m5951c(point3) > f) {
            obj = null;
        } else {
            obj = 1;
        }
        if (!e) {
            polyline.m6006a(point);
            if (i2 == 0) {
                if (point.m5951c(point2) > f && point2.m5951c(point3) > f) {
                    polyline.m6006a(point2);
                }
                polyline.m6006a(point3);
                return polyline.m6008c();
            }
            while (point.m5951c(point2) <= f) {
                i2 += 3;
                if (i2 == length * 3) {
                    if (point.m5951c(point3) > f && point3.m5951c(point4) > f) {
                        polyline.m6006a(point3);
                    }
                    polyline.m6006a(point4);
                    return polyline.m6008c();
                }
                point2.m5950b(point3);
                point3.m5950b(point4);
                point4.m5945a(this.f3672a[i2], this.f3672a[i2 + 1], 0);
            }
        }
        i3 = i2;
        while (i3 < length * 3) {
            point4.m5955d(this.f3672a[i3], this.f3672a[i3 + 1]);
            if (obj != null) {
                if (i3 == (i - 1) * 3) {
                    point4.m5950b(point5);
                } else if (i3 == i * 3) {
                    i3 += 3;
                }
            }
            float c = point2.m5951c(point3);
            if (c > f) {
                if (e && i3 == i2) {
                    point5.m5950b(point2);
                } else {
                    polyline.m6006a(point2);
                }
                point.m5950b(point2);
                point2.m5950b(point3);
                point3.m5950b(point4);
            } else {
                double c2 = (double) (point.m5951c(point2) + c);
                double c3 = (double) (c + point3.m5951c(point4));
                point2.m5955d((int) Math.round(((((double) point2.f3646a) * c2) + (((double) point3.f3646a) * c3)) / (c2 + c3)), (int) Math.round(((((double) point2.f3647b) * c2) + (((double) point3.f3647b) * c3)) / (c3 + c2)));
                point3.m5950b(point4);
            }
            i3 += 3;
        }
        if (e || point2.m5951c(point3) > f) {
            polyline.m6006a(point2);
        }
        if (e) {
            polyline.m6006a(polyline.m6005a(0));
        } else {
            polyline.m6006a(point3);
        }
        return polyline.m6004a() != length ? polyline.m6008c() : this;
    }

    public final int m6030g() {
        int length = this.f3672a.length / 3;
        if (length == 0) {
            return -536870912;
        }
        S1Interval a = S1Interval.m5049a();
        Point point = new Point();
        m6014a(0).m5965i(point);
        int i = 1;
        S1Interval s1Interval = a;
        while (i < length) {
            Point point2 = new Point(point);
            m6014a(i).m5965i(point);
            i++;
            s1Interval = s1Interval.m5055a(S1Interval.m5050a(S1Interval.m5048a(Point.m5933b(point2.f3646a)), S1Interval.m5048a(Point.m5933b(point.f3646a))));
        }
        if (s1Interval.m5057b()) {
            return -536870912;
        }
        double a2 = S1Interval.m5048a(-536870912);
        if (a2 == -3.141592653589793d) {
            a2 = 3.141592653589793d;
        }
        if (s1Interval.m5056a(a2)) {
            return Point.m5933b((int) ((s1Interval.m5059d().m5058c() / 3.141592653589793d) * 5.36870912E8d));
        }
        return -536870912;
    }

    public final Polyline m6025c(int i) {
        Object obj = null;
        if (i == -536870912) {
            return this;
        }
        Object obj2 = i < 0 ? 1 : null;
        int length = this.f3672a.length / 3;
        Polyline polyline = new Polyline(length);
        Point point = new Point();
        for (int i2 = 0; i2 < length; i2++) {
            m6016a(i2, point);
            if (obj2 != null) {
                if (point.f3646a < i) {
                    point.f3646a += 1073741824;
                    obj = 1;
                }
            } else if (point.f3646a > i) {
                point.f3646a -= 1073741824;
                int i3 = 1;
            }
            polyline.m6006a(point);
        }
        return obj != null ? polyline.m6008c() : this;
    }

    public final float m6027d(int i) {
        int i2 = i * 3;
        return PointUtil2D.m5987a(this.f3672a[i2 + 3] - this.f3672a[i2], this.f3672a[(i2 + 3) + 1] - this.f3672a[i2 + 1]);
    }

    public final Polyline m6031h() {
        int length = this.f3672a.length;
        int[] iArr = new int[length];
        int[] iArr2 = this.f3672a;
        for (int i = 0; i < this.f3672a.length; i += 3) {
            iArr[i] = iArr2[(length - i) - 3];
            iArr[i + 1] = iArr2[(length - i) - 2];
            iArr[i + 2] = iArr2[(length - i) - 1];
        }
        return new Polyline(iArr);
    }

    public final Polyline m6022b(Point point) {
        int[] iArr = new int[this.f3672a.length];
        for (int i = 0; i < this.f3672a.length; i += 3) {
            iArr[i] = this.f3672a[i] + point.f3646a;
            iArr[i + 1] = this.f3672a[i + 1] + point.f3647b;
            iArr[i + 2] = this.f3672a[i + 2] + point.f3648c;
        }
        return new Polyline(iArr);
    }

    public final int m6032i() {
        return (this.f3672a.length * 4) + 160;
    }
}
