package com.google.android.m4b.maps.am;

import com.google.android.m4b.maps.af.TextUtil;
import com.google.android.m4b.maps.p041b.IoUtil;
import java.io.DataInput;
import java.io.IOException;

/* renamed from: com.google.android.m4b.maps.am.a */
public final class MapPoint {
    private static final Zoom f3355e;
    private static int[] f3356f;
    private final int f3357a;
    private final int f3358b;
    private final int f3359c;
    private final int f3360d;

    static {
        f3356f = null;
        f3355e = Zoom.m5402a(22);
    }

    private static int m5389a(int i) {
        int i2 = i;
        while (i2 < -180000000) {
            i2 += 360000000;
        }
        while (i2 > 180000000) {
            i2 -= 360000000;
        }
        return i2;
    }

    public MapPoint(int i, int i2) {
        int i3;
        int a = MapPoint.m5389a(i2);
        if (i > 80000000) {
            i3 = 80000000;
        } else {
            i3 = i;
        }
        if (i3 < -80000000) {
            i3 = -80000000;
        }
        this.f3359c = i3;
        this.f3360d = a;
        Zoom zoom = f3355e;
        this.f3357a = (int) (((((long) zoom.m5403a()) * ((long) a)) / 360000000) + (((long) zoom.m5403a()) / 2));
        zoom = f3355e;
        a = (Math.abs(i3) / 1000000) + 1;
        int abs = Math.abs(i3) % 1000000;
        int[] c = MapPoint.m5392c();
        a = (int) (((((((((((long) (((((c[a - 1] * -1) + (c[a] * 3)) - (c[a + 1] * 3)) + c[a + 2]) / 6)) * ((long) abs)) * ((long) abs)) / 1000000) * ((long) abs)) / 1000000) / 1000000) + ((((((long) ((((c[a - 1] * 3) - (c[a] * 6)) + (c[a + 1] * 3)) / 6)) * ((long) abs)) * ((long) abs)) / 1000000) / 1000000)) + ((((long) (((((c[a - 1] * -2) - (c[a] * 3)) + (c[a + 1] * 6)) - c[a + 2]) / 6)) * ((long) abs)) / 1000000)) + ((long) c[a]));
        if (i3 < 0) {
            i3 = -a;
        } else {
            i3 = a;
        }
        this.f3358b = (int) ((((long) zoom.m5403a()) / 2) - ((long) (((int) (((((long) i3) * ((long) zoom.m5403a())) * 10) / 360000000)) / 10)));
    }

    private static synchronized int[] m5392c() {
        int[] iArr;
        synchronized (MapPoint.class) {
            if (f3356f == null) {
                f3356f = new int[84];
                try {
                    DataInput a = IoUtil.m7768a(new byte[]{(byte) -1, (byte) -16, (byte) -67, (byte) -115, (byte) 15, (byte) 66, (byte) 115, (byte) 15, (byte) 66, (byte) 115, (byte) 15, (byte) 67, (byte) -93, (byte) 15, (byte) 70, (byte) 6, (byte) 15, (byte) 73, (byte) -103, (byte) 15, (byte) 78, (byte) 97, (byte) 15, (byte) 84, (byte) 94, (byte) 15, (byte) 91, (byte) -109, (byte) 15, (byte) 100, (byte) 2, (byte) 15, (byte) 109, (byte) -80, (byte) 15, (byte) 120, (byte) -97, (byte) 15, (byte) -124, (byte) -44, (byte) 15, (byte) -110, (byte) 84, (byte) 15, (byte) -95, (byte) 38, (byte) 15, (byte) -79, (byte) 78, (byte) 15, (byte) -62, (byte) -45, (byte) 15, (byte) -43, (byte) -67, (byte) 15, (byte) -22, (byte) 21, (byte) 15, (byte) -1, (byte) -31, (byte) 16, (byte) 23, (byte) 45, (byte) 16, (byte) 48, (byte) 1, (byte) 16, (byte) 74, (byte) 107, (byte) 16, (byte) 102, (byte) 116, (byte) 16, (byte) -124, (byte) 43, (byte) 16, (byte) -93, (byte) -100, (byte) 16, (byte) -60, (byte) -41, (byte) 16, (byte) -25, (byte) -19, (byte) 17, (byte) 12, (byte) -18, (byte) 17, (byte) 51, (byte) -20, (byte) 17, (byte) 92, (byte) -4, (byte) 17, (byte) -120, (byte) 52, (byte) 17, (byte) -75, (byte) -87, (byte) 17, (byte) -27, (byte) 118, (byte) 18, (byte) 23, (byte) -76, (byte) 18, (byte) 76, (byte) -127, (byte) 18, (byte) -125, (byte) -3, (byte) 18, (byte) -66, (byte) 70, (byte) 18, (byte) -5, (byte) -124, (byte) 19, (byte) 59, (byte) -37, (byte) 19, Byte.MAX_VALUE, (byte) 119, (byte) 19, (byte) -58, (byte) -122, (byte) 20, (byte) 17, (byte) 56, (byte) 20, (byte) 95, (byte) -60, (byte) 20, (byte) -78, (byte) 100, (byte) 21, (byte) 9, (byte) 87, (byte) 21, (byte) 100, (byte) -27, (byte) 21, (byte) -59, (byte) 86, (byte) 22, (byte) 42, (byte) -1, (byte) 22, (byte) -106, (byte) 58, (byte) 23, (byte) 7, (byte) 109, (byte) 23, Byte.MAX_VALUE, (byte) 2, (byte) 23, (byte) -3, (byte) 117, (byte) 24, (byte) -125, (byte) 72, (byte) 25, (byte) 17, (byte) 20, (byte) 25, (byte) -89, (byte) 120, (byte) 26, (byte) 71, (byte) 46, (byte) 26, (byte) -15, (byte) 3, (byte) 27, (byte) -91, (byte) -39, (byte) 28, (byte) 102, (byte) -77, (byte) 29, (byte) 52, (byte) -77, (byte) 30, (byte) 17, (byte) 31, (byte) 30, (byte) -3, (byte) 111, (byte) 31, (byte) -5, (byte) 74, (byte) 33, (byte) 12, (byte) -105, (byte) 34, (byte) 51, (byte) -120, (byte) 35, (byte) 114, (byte) -91, (byte) 36, (byte) -52, (byte) -30, (byte) 38, (byte) 69, (byte) -76, (byte) 39, (byte) -31, (byte) 42, (byte) 41, (byte) -92, (byte) 25, (byte) 43, (byte) -108, (byte) 70, (byte) 45, (byte) -72, (byte) -91, (byte) 48, (byte) 25, (byte) -84, (byte) 50, (byte) -63, (byte) -63, (byte) 53, (byte) -67, (byte) -47, (byte) 57, (byte) 30, (byte) 28, (byte) 60, (byte) -9, (byte) 105, (byte) 65, (byte) 100, (byte) -96, (byte) 70, (byte) -119, (byte) 82, (byte) 76, (byte) -107, (byte) 115, (byte) 83, (byte) -53, (byte) 79, (byte) 92, (byte) -119, (byte) 52, (byte) 103, (byte) 90, (byte) 12});
                    int[] iArr2 = f3356f;
                    iArr2[0] = a.readInt();
                    for (int i = 1; i < iArr2.length; i++) {
                        iArr2[i] = iArr2[i - 1] + (((a.readUnsignedByte() << 16) | (a.readUnsignedByte() << 8)) | a.readUnsignedByte());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Can't read mercator.dat");
                }
            }
            iArr = f3356f;
        }
        return iArr;
    }

    public final int m5393a() {
        return this.f3359c;
    }

    public final int m5394b() {
        return this.f3360d;
    }

    public final String toString() {
        return TextUtil.m4869a(this.f3359c) + ',' + TextUtil.m4869a(this.f3360d);
    }

    public static MapPoint m5390a(String str) {
        if (str.indexOf(44) == -1) {
            return null;
        }
        try {
            String[] a = TextUtil.m4871a(str, ',');
            return new MapPoint(MapPoint.m5391b(a[0]), MapPoint.m5391b(a[1]));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static int m5391b(String str) {
        int i = 1;
        String trim = str.trim();
        if (trim.indexOf(46) == -1) {
            return Integer.parseInt(trim) * 1000000;
        }
        String[] a = TextUtil.m4871a(trim, '.');
        if (a.length > 2) {
            throw new NumberFormatException("Coordinate has more than one decimal point: " + trim);
        }
        int parseInt = Integer.parseInt(a[0]);
        int i2 = a[0].indexOf("-") != -1 ? 1 : 0;
        String substring = a[1].substring(0, Math.min(6, a[1].length()));
        if (substring.length() == 0 || substring.charAt(0) < '0' || substring.charAt(0) > '9') {
            throw new NumberFormatException("Invalid fractional part in \"" + trim + "\"");
        }
        int parseInt2 = Integer.parseInt(TextUtil.m4870a(substring, 6, '0'));
        int i3 = 1000000 * parseInt;
        if (i2 != 0) {
            i = -1;
        }
        return (parseInt2 * i) + i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MapPoint)) {
            return false;
        }
        MapPoint mapPoint = (MapPoint) obj;
        if (this.f3359c == mapPoint.f3359c && this.f3360d == mapPoint.f3360d) {
            return true;
        }
        if (this.f3357a == mapPoint.f3357a && this.f3358b == mapPoint.f3358b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.f3359c * 29) + this.f3360d;
    }
}
