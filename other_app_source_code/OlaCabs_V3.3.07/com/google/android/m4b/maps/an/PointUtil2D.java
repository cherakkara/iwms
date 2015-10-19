package com.google.android.m4b.maps.an;

/* renamed from: com.google.android.m4b.maps.an.i */
public final class PointUtil2D {
    public static int m5988a(Point point, Point point2, Point point3) {
        long j = (((long) (point3.f3646a - point.f3646a)) * ((long) (point3.f3647b - point2.f3647b))) - (((long) (point3.f3647b - point.f3647b)) * ((long) (point3.f3646a - point2.f3646a)));
        return (int) (((long) (j != 0 ? 1 : 0)) | (j >> 63));
    }

    public static boolean m5991a(Point point, Point point2, Point point3, Point point4) {
        int i = point2.f3646a - point.f3646a;
        int i2 = point2.f3647b - point.f3647b;
        int i3 = point4.f3646a - point3.f3646a;
        int i4 = point4.f3647b - point3.f3647b;
        int i5 = point3.f3646a - point.f3646a;
        int i6 = point3.f3647b - point.f3647b;
        long j = (((long) i3) * ((long) i2)) - (((long) i4) * ((long) i));
        if (j != 0) {
            double d = ((double) ((((long) i) * ((long) i6)) + (((long) (-i5)) * ((long) i2)))) / ((double) j);
            if (d < 0.0d || d > 1.0d) {
                return false;
            }
            d = ((double) ((((long) i5) * ((long) i4)) - (((long) i3) * ((long) i6)))) / ((double) (-j));
            return d >= 0.0d && d <= 1.0d;
        } else if (i5 == 0 && i6 == 0) {
            return true;
        } else {
            if ((((long) i5) * ((long) i2)) - (((long) i6) * ((long) i)) != 0) {
                return false;
            }
            if (i == 0 && i2 == 0) {
                return PointUtil2D.m5999f(point3, point4, point);
            }
            if (i3 == 0 && i4 == 0) {
                return PointUtil2D.m5999f(point, point2, point3);
            }
            return PointUtil2D.m5999f(point, point2, point3) || PointUtil2D.m5999f(point, point2, point4) || PointUtil2D.m5999f(point3, point4, point) || PointUtil2D.m5999f(point3, point4, point2);
        }
    }

    public static boolean m5992a(Point point, Point point2, Point point3, Point point4, Point point5) {
        int i = point.f3647b - point2.f3647b;
        int i2 = point2.f3646a - point.f3646a;
        long j = (((long) (point4.f3646a - point3.f3646a)) * ((long) i)) + (((long) (point4.f3647b - point3.f3647b)) * ((long) i2));
        if (j != 0) {
            double d = ((double) ((((long) i2) * ((long) (point.f3647b - point3.f3647b))) + (((long) (point.f3646a - point3.f3646a)) * ((long) i)))) / ((double) j);
            if (d < 0.0d || d > 1.0d) {
                return false;
            }
            point5.f3646a = (int) (((double) point3.f3646a) + (((double) (point4.f3646a - point3.f3646a)) * d));
            point5.f3647b = (int) ((d * ((double) (point4.f3647b - point3.f3647b))) + ((double) point3.f3647b));
            return true;
        } else if (PointUtil2D.m5988a(point, point2, point3) == 0) {
            point5.m5950b(point3);
            return true;
        } else if (PointUtil2D.m5988a(point, point2, point4) != 0) {
            return false;
        } else {
            point5.m5950b(point4);
            return true;
        }
    }

    public static boolean m5994b(Point point, Point point2, Point point3) {
        int i = point.f3646a;
        int i2 = point.f3647b;
        int i3 = point2.f3646a;
        int i4 = point2.f3647b;
        int i5 = point3.f3646a;
        int i6 = point3.f3647b;
        if (i2 <= i6 && i4 <= i6) {
            return false;
        }
        if (i5 >= i && i5 >= i3) {
            return false;
        }
        if (i5 < i && i5 < i3) {
            return false;
        }
        if (i3 >= i) {
            if (((long) (i5 - i)) * ((long) (i4 - i2)) > ((long) (i3 - i)) * ((long) (i6 - i2))) {
                return true;
            }
            return false;
        }
        if (((long) (i5 - i)) * ((long) (i4 - i2)) < ((long) (i3 - i)) * ((long) (i6 - i2))) {
            return true;
        }
        return false;
    }

    public static void m5996c(Point point, Point point2, Point point3) {
        point3.f3646a = point.f3646a + point2.f3646a;
        point3.f3647b = point.f3647b + point2.f3647b;
    }

    public static void m5997d(Point point, Point point2, Point point3) {
        point3.f3646a = point.f3646a - point2.f3646a;
        point3.f3647b = point.f3647b - point2.f3647b;
    }

    public static void m5998e(Point point, Point point2, Point point3) {
        point3.f3646a = (point.f3646a / 2) + (point2.f3646a / 2);
        point3.f3647b = (point.f3647b / 2) + (point2.f3647b / 2);
    }

    public static void m5989a(Point point, float f, Point point2) {
        float hypot = (float) Math.hypot((double) point.f3646a, (double) point.f3647b);
        if (hypot == 0.0f) {
            point2.f3646a = (int) f;
            point2.f3647b = 0;
            return;
        }
        hypot = f / hypot;
        point2.f3646a = (int) (((float) (-point.f3647b)) * hypot);
        point2.f3647b = (int) (hypot * ((float) point.f3646a));
    }

    public static void m5990a(Point point, Point point2) {
        point2.f3646a = -point.f3647b;
        point2.f3647b = point.f3646a;
    }

    public static float m5993b(Point point, Point point2) {
        return PointUtil2D.m5987a(point2.f3646a - point.f3646a, point2.f3647b - point.f3647b);
    }

    public static float m5987a(int i, int i2) {
        float atan2 = 90.0f - ((float) ((Math.atan2((double) i2, (double) i) * 180.0d) / 3.141592653589793d));
        if (atan2 < 0.0f) {
            return atan2 + 360.0f;
        }
        return atan2;
    }

    public static long m5995c(Point point, Point point2) {
        return (((long) point.f3646a) * ((long) point2.f3647b)) - (((long) point.f3647b) * ((long) point2.f3646a));
    }

    private static boolean m5999f(Point point, Point point2, Point point3) {
        long j = (long) (point2.f3646a - point.f3646a);
        long j2 = (long) (point2.f3647b - point.f3647b);
        long j3 = (((long) (point3.f3646a - point.f3646a)) * j) + (((long) (point3.f3647b - point.f3647b)) * j2);
        return j3 >= 0 && j3 <= (j * j) + (j2 * j2);
    }
}
