package com.google.android.m4b.maps.ay;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.PointUtil2D;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.ao;
import com.google.android.m4b.maps.az.IndexBufferInterface;
import com.google.android.m4b.maps.az.TexCoordBufferInterface;
import com.google.android.m4b.maps.az.VertexBufferInterface;
import com.olacabs.customer.p076d.br;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ay.f */
public final class GeometryUtil {
    private static final float f4873a;
    private static final ThreadLocal<GeometryUtil> f4874t;
    private static final int[] f4875u;
    private static final int[] f4876v;
    private static final int[] f4877w;
    private static final int[] f4878x;
    private static final int[][] f4879y;
    private final Point f4880b;
    private final Point f4881c;
    private final Point f4882d;
    private final Point f4883e;
    private final Point f4884f;
    private final Point f4885g;
    private final Point f4886h;
    private final Point f4887i;
    private final Point f4888j;
    private final Point f4889k;
    private final ao f4890l;
    private final ao f4891m;
    private final ao f4892n;
    private final ao f4893o;
    private final ao f4894p;
    private final ao f4895q;
    private final ao f4896r;
    private final ao f4897s;

    /* renamed from: com.google.android.m4b.maps.ay.f.1 */
    static class GeometryUtil extends ThreadLocal<GeometryUtil> {
        GeometryUtil() {
        }

        protected final /* synthetic */ Object initialValue() {
            return new GeometryUtil();
        }
    }

    static {
        f4873a = (float) Math.sqrt(2.0d);
        f4874t = new GeometryUtil();
        f4875u = new int[]{0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, 0, AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_PASTE};
        f4876v = new int[]{0, AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_PASTE, 0, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_CUT};
        f4877w = new int[]{0, AccessibilityNodeInfoCompat.ACTION_COPY, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_COPY};
        f4878x = new int[]{0, AccessibilityNodeInfoCompat.ACTION_COPY, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_COPY, AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_COPY, 0, AccessibilityNodeInfoCompat.ACTION_COPY, AccessibilityNodeInfoCompat.ACTION_CUT, AccessibilityNodeInfoCompat.ACTION_COPY};
        f4879y = new int[16][];
    }

    private GeometryUtil() {
        this.f4880b = new Point();
        this.f4881c = new Point();
        this.f4882d = new Point();
        this.f4883e = new Point();
        this.f4884f = new Point();
        this.f4885g = new Point();
        this.f4886h = new Point();
        this.f4887i = new Point();
        this.f4888j = new Point();
        this.f4889k = new Point();
        this.f4890l = new ao();
        this.f4891m = new ao();
        this.f4892n = new ao();
        this.f4893o = new ao();
        this.f4894p = new ao();
        this.f4895q = new ao();
        this.f4896r = new ao();
        this.f4897s = new ao();
    }

    public static GeometryUtil m7546a() {
        return (GeometryUtil) f4874t.get();
    }

    public final int m7550a(Polyline polyline, float f, Point point, int i, VertexBufferInterface vertexBufferInterface, TexCoordBufferInterface texCoordBufferInterface, IndexBufferInterface indexBufferInterface, boolean z, boolean z2, byte b) {
        if (f < br.DEFAULT_BACKOFF_MULT) {
            return 0;
        }
        int b2 = polyline.m6020b();
        int a;
        Point point2;
        Point point3;
        int i2;
        if (b2 == 2) {
            a = vertexBufferInterface.m7565a();
            point2 = this.f4880b;
            point3 = this.f4881c;
            Point point4 = this.f4882d;
            Point point5 = this.f4883e;
            Point point6 = this.f4884f;
            Point point7 = this.f4885g;
            Point point8 = this.f4886h;
            polyline.m6017a(0, point, point2);
            polyline.m6017a(1, point, point3);
            PointUtil2D.m5997d(point3, point2, point4);
            PointUtil2D.m5989a(point4, f, point5);
            PointUtil2D.m5990a(point5, point6);
            if (z) {
                PointUtil2D.m5996c(point2, point6, point2);
            }
            if (z2) {
                PointUtil2D.m5997d(point3, point6, point3);
            }
            PointUtil2D.m5996c(point2, point5, point8);
            vertexBufferInterface.m7569a(point8, i, (byte) 0);
            PointUtil2D.m5997d(point2, point5, point8);
            vertexBufferInterface.m7569a(point8, i, (byte) 0);
            PointUtil2D.m5998e(point2, point3, point7);
            PointUtil2D.m5996c(point7, point5, point8);
            vertexBufferInterface.m7569a(point8, i, (byte) 0);
            PointUtil2D.m5997d(point7, point5, point8);
            vertexBufferInterface.m7569a(point8, i, (byte) 0);
            PointUtil2D.m5996c(point3, point5, point8);
            vertexBufferInterface.m7569a(point8, i, (byte) 0);
            PointUtil2D.m5997d(point3, point5, point8);
            vertexBufferInterface.m7569a(point8, i, (byte) 0);
            i2 = (int) (((-0.25f * (point4.m5964i() / f)) + 0.5f) * 65536.0f);
            if (z) {
                texCoordBufferInterface.m7560a(0, (int) AccessibilityNodeInfoCompat.ACTION_CUT);
                texCoordBufferInterface.m7560a((int) AccessibilityNodeInfoCompat.ACTION_CUT, (int) AccessibilityNodeInfoCompat.ACTION_CUT);
            } else {
                texCoordBufferInterface.m7560a(0, i2);
                texCoordBufferInterface.m7560a((int) AccessibilityNodeInfoCompat.ACTION_CUT, i2);
            }
            texCoordBufferInterface.m7560a(0, i2);
            texCoordBufferInterface.m7560a((int) AccessibilityNodeInfoCompat.ACTION_CUT, i2);
            if (z2) {
                texCoordBufferInterface.m7560a(0, (int) AccessibilityNodeInfoCompat.ACTION_CUT);
                texCoordBufferInterface.m7560a((int) AccessibilityNodeInfoCompat.ACTION_CUT, (int) AccessibilityNodeInfoCompat.ACTION_CUT);
            } else {
                texCoordBufferInterface.m7560a(0, i2);
                texCoordBufferInterface.m7560a((int) AccessibilityNodeInfoCompat.ACTION_CUT, i2);
            }
            indexBufferInterface.m7555a(a, a + 1, a + 2, a + 3);
            indexBufferInterface.m7555a(a + 2, a + 3, a + 4, a + 5);
            return 6;
        } else if (b2 < 2) {
            return 0;
        } else {
            Point point9 = this.f4880b;
            Point point10 = this.f4881c;
            Point point11 = this.f4882d;
            Point point12 = this.f4883e;
            Point point13 = this.f4884f;
            Point point14 = this.f4885g;
            Point point15 = this.f4886h;
            point3 = this.f4887i;
            Point point16 = this.f4888j;
            point2 = this.f4889k;
            i2 = vertexBufferInterface.m7565a();
            polyline.m6017a(0, point, point9);
            polyline.m6017a(1, point, point10);
            PointUtil2D.m5997d(point10, point9, point12);
            PointUtil2D.m5989a(point12, f, point14);
            PointUtil2D.m5990a(point14, point3);
            PointUtil2D.m5996c(point9, point3, point9);
            PointUtil2D.m5996c(point9, point14, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            PointUtil2D.m5997d(point9, point14, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            PointUtil2D.m5997d(point9, point3, point9);
            PointUtil2D.m5996c(point9, point14, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            PointUtil2D.m5997d(point9, point14, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            texCoordBufferInterface.m7561a(f4875u);
            int i3 = 4;
            if (z) {
                indexBufferInterface.m7555a(i2, i2 + 1, i2 + 2, i2 + 3);
            } else {
                indexBufferInterface.m7555a(i2 + 2, i2 + 2, i2 + 2, i2 + 2);
            }
            indexBufferInterface.m7555a(i2 + 2, i2 + 3, i2 + 4, i2 + 5);
            int i4 = i2 + 4;
            float f2 = f * f;
            int i5 = 1;
            while (i5 < b2 - 1) {
                int i6;
                Object obj;
                polyline.m6017a(i5 + 1, point, point11);
                PointUtil2D.m5997d(point11, point10, point13);
                PointUtil2D.m5989a(point13, f, point15);
                Object obj2 = PointUtil2D.m5995c(point12, point13) > 0 ? 1 : null;
                PointUtil2D.m5996c(point14, point15, point16);
                float a2 = Point.m5921a(point15, point16);
                if (a2 > br.DEFAULT_BACKOFF_MULT && Point.m5921a(point12, point13) >= 0.0f) {
                    Point.m5929a(point16, f2 / a2, point16);
                    PointUtil2D.m5996c(point10, point16, point2);
                    PointUtil2D.m5997d(point10, point16, point3);
                    Point point17 = obj2 != null ? point2 : point3;
                    if (Point.m5938c(point10, point9, point17) < 0.5f && Point.m5938c(point10, point11, point17) < 0.5f) {
                        vertexBufferInterface.m7569a(point2, i, (byte) 0);
                        vertexBufferInterface.m7569a(point3, i, (byte) 0);
                        i3 += 2;
                        texCoordBufferInterface.m7561a(f4877w);
                        indexBufferInterface.m7555a(i4, i4 + 1, i4 + 2, i4 + 3);
                        i6 = i4 + 2;
                        obj = null;
                        a = i6;
                        i6 = i3;
                        if (obj == null) {
                            PointUtil2D.m5996c(point10, point14, point2);
                            vertexBufferInterface.m7569a(point2, i, (byte) 0);
                            PointUtil2D.m5997d(point10, point14, point2);
                            vertexBufferInterface.m7569a(point2, i, (byte) 0);
                            vertexBufferInterface.m7569a(point10, i, (byte) 0);
                            PointUtil2D.m5996c(point10, point15, point2);
                            vertexBufferInterface.m7569a(point2, i, (byte) 0);
                            PointUtil2D.m5997d(point10, point15, point2);
                            vertexBufferInterface.m7569a(point2, i, (byte) 0);
                            texCoordBufferInterface.m7561a(f4878x);
                            i6 += 5;
                            if (obj2 == null) {
                                indexBufferInterface.m7554a(a + 2, a + 1, a + 4);
                            } else {
                                indexBufferInterface.m7554a(a + 0, a + 2, a + 3);
                            }
                            indexBufferInterface.m7555a(a + 3, a + 4, a + 5, a + 6);
                            i2 = a + 5;
                            a = i6;
                        } else {
                            i2 = a;
                            a = i6;
                        }
                        point14.m5950b(point15);
                        point12.m5950b(point13);
                        point9.m5950b(point10);
                        point10.m5950b(point11);
                        i5++;
                        i4 = i2;
                        i3 = a;
                    }
                }
                a = i4;
                i4 = 1;
                i6 = i3;
                if (obj == null) {
                    i2 = a;
                    a = i6;
                } else {
                    PointUtil2D.m5996c(point10, point14, point2);
                    vertexBufferInterface.m7569a(point2, i, (byte) 0);
                    PointUtil2D.m5997d(point10, point14, point2);
                    vertexBufferInterface.m7569a(point2, i, (byte) 0);
                    vertexBufferInterface.m7569a(point10, i, (byte) 0);
                    PointUtil2D.m5996c(point10, point15, point2);
                    vertexBufferInterface.m7569a(point2, i, (byte) 0);
                    PointUtil2D.m5997d(point10, point15, point2);
                    vertexBufferInterface.m7569a(point2, i, (byte) 0);
                    texCoordBufferInterface.m7561a(f4878x);
                    i6 += 5;
                    if (obj2 == null) {
                        indexBufferInterface.m7554a(a + 0, a + 2, a + 3);
                    } else {
                        indexBufferInterface.m7554a(a + 2, a + 1, a + 4);
                    }
                    indexBufferInterface.m7555a(a + 3, a + 4, a + 5, a + 6);
                    i2 = a + 5;
                    a = i6;
                }
                point14.m5950b(point15);
                point12.m5950b(point13);
                point9.m5950b(point10);
                point10.m5950b(point11);
                i5++;
                i4 = i2;
                i3 = a;
            }
            PointUtil2D.m5996c(point11, point15, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            PointUtil2D.m5997d(point11, point15, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            PointUtil2D.m5990a(point15, point3);
            PointUtil2D.m5997d(point11, point3, point11);
            PointUtil2D.m5996c(point11, point15, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            PointUtil2D.m5997d(point11, point15, point2);
            vertexBufferInterface.m7569a(point2, i, (byte) 0);
            texCoordBufferInterface.m7561a(f4876v);
            i2 = i3 + 4;
            if (z2) {
                indexBufferInterface.m7555a(i4, i4 + 1, i4 + 2, i4 + 3);
                return i2;
            }
            indexBufferInterface.m7555a(i4, i4, i4, i4);
            return i2;
        }
    }

    public static int m7544a(Polyline polyline) {
        int b = polyline.m6020b() - 1;
        if (b == 1) {
            return 6;
        }
        return ((b - 1) * 5) + 8;
    }

    public static int m7548b(Polyline polyline) {
        int b = polyline.m6020b() - 1;
        if (b == 1) {
            return 12;
        }
        return ((b - 1) * 3) + ((b + 2) * 6);
    }

    public static int m7545a(List<Polyline> list) {
        int i = 0;
        for (Polyline b : list) {
            i = (b.m6020b() * 5) + i;
        }
        return i;
    }

    public static int m7549b(List<Polyline> list) {
        int i = 0;
        for (Polyline polyline : list) {
            int i2;
            int b = polyline.m6020b() - 1;
            if (polyline.m6028e()) {
                i2 = b + 1;
            } else {
                i2 = b;
            }
            i = ((i2 * 3) * 6) + i;
        }
        return i;
    }

    public final void m7553a(Polyline polyline, float f, boolean z, Point point, int i, float f2, VertexBufferInterface vertexBufferInterface, IndexBufferInterface indexBufferInterface, TexCoordBufferInterface texCoordBufferInterface) {
        int b = polyline.m6020b();
        int i2 = b - 1;
        int a = vertexBufferInterface.m7565a();
        if (i2 > 0) {
            int i3;
            if (z) {
                i3 = 5;
            } else {
                i3 = 4;
            }
            int i4 = i3 * i2;
            Point point2 = this.f4880b;
            Point point3 = this.f4881c;
            vertexBufferInterface.m7567a(vertexBufferInterface.m7565a() + i4);
            Point point4 = this.f4882d;
            if (texCoordBufferInterface != null) {
                texCoordBufferInterface.m7563c(texCoordBufferInterface.m7564g() + i4);
            }
            polyline.m6016a(0, point3);
            PointUtil2D.m5997d(point3, point, point3);
            float f3 = 0.0f;
            int i5 = 1;
            while (i5 < b) {
                polyline.m6016a(i5, point2);
                PointUtil2D.m5997d(point2, point, point2);
                Point point5 = this.f4882d;
                Point point6 = this.f4883e;
                PointUtil2D.m5997d(point2, point3, point5);
                PointUtil2D.m5989a(point5, f, point6);
                PointUtil2D.m5996c(point3, point6, point5);
                vertexBufferInterface.m7568a(point5, i);
                PointUtil2D.m5997d(point3, point6, point5);
                vertexBufferInterface.m7568a(point5, i);
                PointUtil2D.m5997d(point2, point6, point5);
                vertexBufferInterface.m7568a(point5, i);
                PointUtil2D.m5996c(point2, point6, point5);
                vertexBufferInterface.m7568a(point5, i);
                if (z) {
                    vertexBufferInterface.m7568a(point2, i);
                }
                if (texCoordBufferInterface != null) {
                    PointUtil2D.m5997d(point2, point3, point4);
                    float i6 = (point4.m5964i() / ((float) i)) * f2;
                    texCoordBufferInterface.m7559a(0.0f, f3);
                    texCoordBufferInterface.m7559a((float) br.DEFAULT_BACKOFF_MULT, f3);
                    f3 += i6;
                    texCoordBufferInterface.m7559a((float) br.DEFAULT_BACKOFF_MULT, f3);
                    texCoordBufferInterface.m7559a(0.0f, f3);
                    if (z) {
                        texCoordBufferInterface.m7559a(0.5f, f3);
                    }
                }
                i5++;
                Point point7 = point2;
                point2 = point3;
                point3 = point7;
            }
            if (indexBufferInterface != null) {
                int i7 = a + i4;
                if (i7 > 32767) {
                    throw new ArrayIndexOutOfBoundsException(i7 + " required, but we can only store 32767");
                }
                Point point8 = this.f4882d;
                Point point9 = this.f4883e;
                Point point10 = this.f4884f;
                int i8 = i2 * 2;
                i7 = i2 - (polyline.m6028e() ? 0 : 1);
                if (z) {
                    indexBufferInterface.m7557b(((i7 + i8) * 3) + indexBufferInterface.m7556b());
                } else {
                    indexBufferInterface.m7557b(indexBufferInterface.m7556b() + (i8 * 3));
                }
                for (i7 = 0; i7 < i2; i7++) {
                    i8 = (i7 * i3) + a;
                    indexBufferInterface.m7554a(i8, i8 + 1, i8 + 2);
                    indexBufferInterface.m7554a(i8, i8 + 2, i8 + 3);
                }
                if (z) {
                    for (i7 = 0; i7 < i2 - 1; i7++) {
                        polyline.m6016a(i7, point3);
                        polyline.m6016a(i7 + 1, point2);
                        polyline.m6016a(i7 + 2, point8);
                        PointUtil2D.m5997d(point2, point3, point9);
                        PointUtil2D.m5997d(point8, point2, point10);
                        i8 = (i7 * 5) + a;
                        int i9 = i8 + 5;
                        if ((PointUtil2D.m5995c(point9, point10) > 0 ? 1 : null) != null) {
                            indexBufferInterface.m7554a(i8 + 2, i9 + 1, i8 + 4);
                        } else {
                            indexBufferInterface.m7554a(i8 + 3, i8 + 4, i9);
                        }
                    }
                    if (polyline.m6028e()) {
                        polyline.m6016a(i2 - 1, point3);
                        polyline.m6016a(0, point2);
                        polyline.m6016a(1, point8);
                        PointUtil2D.m5997d(point2, point3, point9);
                        PointUtil2D.m5997d(point8, point2, point10);
                        i7 = ((i2 - 1) * 5) + a;
                        if ((PointUtil2D.m5995c(point9, point10) > 0 ? 1 : null) != null) {
                            indexBufferInterface.m7554a(i7 + 2, a + 1, i7 + 4);
                        } else {
                            indexBufferInterface.m7554a(i7 + 3, i7 + 4, a);
                        }
                    }
                }
            }
        }
    }

    public static void m7547a(int i, boolean z, int i2, int[] iArr, TexCoordBufferInterface texCoordBufferInterface) {
        int i3;
        texCoordBufferInterface.m7563c(((i - 1) * 5) + texCoordBufferInterface.m7564g());
        if (f4879y[i2] == null) {
            int[][] iArr2 = f4879y;
            int i4 = 1 << i2;
            int[] iArr3 = new int[((i4 * 5) * 2)];
            int i5 = AccessibilityNodeInfoCompat.ACTION_PASTE / i4;
            for (i3 = 0; i3 < iArr3.length; i3 += 10) {
                iArr3[i3] = 0;
                iArr3[i3 + 1] = i5;
                iArr3[i3 + 2] = AccessibilityNodeInfoCompat.ACTION_CUT;
                iArr3[i3 + 3] = i5;
                iArr3[i3 + 4] = AccessibilityNodeInfoCompat.ACTION_CUT;
                iArr3[i3 + 5] = i5;
                iArr3[i3 + 6] = 0;
                iArr3[i3 + 7] = i5;
                iArr3[i3 + 8] = AccessibilityNodeInfoCompat.ACTION_PASTE;
                iArr3[i3 + 9] = i5;
                i5 += AccessibilityNodeInfoCompat.ACTION_CUT / i4;
            }
            iArr2[i2] = iArr3;
        }
        int[] iArr4 = f4879y[i2];
        int i6 = iArr[0];
        for (i3 = 1; i3 < i; i3++) {
            texCoordBufferInterface.m7562a(iArr4, (i6 * 5) * 2, 10);
        }
    }

    public final void m7552a(Polyline polyline, float f, Point point, int i, float f2, VertexBufferInterface vertexBufferInterface, IndexBufferInterface indexBufferInterface, TexCoordBufferInterface texCoordBufferInterface, TexCoordBufferInterface texCoordBufferInterface2) {
        int b = polyline.m6020b();
        int i2 = b - 1;
        int a = vertexBufferInterface.m7565a();
        int i3 = i2 * 4;
        Point point2 = this.f4880b;
        Point point3 = this.f4881c;
        Point point4 = this.f4882d;
        Point point5 = this.f4883e;
        Point point6 = this.f4884f;
        vertexBufferInterface.m7567a(vertexBufferInterface.m7565a() + i3);
        if (texCoordBufferInterface != null) {
            texCoordBufferInterface.m7563c(i3 + texCoordBufferInterface.m7564g());
        }
        polyline.m6016a(0, point2);
        PointUtil2D.m5997d(point2, point, point2);
        float f3 = 0.0f;
        Point point7 = point3;
        int i4 = 1;
        while (i4 < b) {
            polyline.m6016a(i4, point7);
            PointUtil2D.m5997d(point7, point, point7);
            PointUtil2D.m5997d(point7, point2, point4);
            PointUtil2D.m5989a(point4, -f, point5);
            vertexBufferInterface.m7568a(point2, i);
            PointUtil2D.m5996c(point2, point5, point6);
            vertexBufferInterface.m7568a(point6, i);
            PointUtil2D.m5996c(point7, point5, point6);
            vertexBufferInterface.m7568a(point6, i);
            vertexBufferInterface.m7568a(point7, i);
            if (texCoordBufferInterface != null) {
                float i5 = (point4.m5964i() / ((float) i)) * f2;
                texCoordBufferInterface.m7559a(0.0f, f3);
                texCoordBufferInterface.m7559a((float) br.DEFAULT_BACKOFF_MULT, f3);
                f3 += i5;
                texCoordBufferInterface.m7559a((float) br.DEFAULT_BACKOFF_MULT, f3);
                texCoordBufferInterface.m7559a(0.0f, f3);
            }
            i4++;
            Point point8 = point2;
            point2 = point7;
            point7 = point8;
        }
        IndexBufferInterface indexBufferInterface2 = indexBufferInterface;
        indexBufferInterface2.m7557b((((i2 * 2) + (i2 - 1)) * 3) + indexBufferInterface.m7556b());
        point3 = this.f4882d;
        Point point9 = this.f4883e;
        point4 = this.f4884f;
        i3 = 0;
        while (true) {
            int i6 = (i3 * 4) + a;
            if (f > 0.0f) {
                indexBufferInterface.m7554a(i6, i6 + 1, i6 + 2);
                indexBufferInterface.m7554a(i6, i6 + 2, i6 + 3);
            } else {
                indexBufferInterface.m7554a(i6, i6 + 2, i6 + 1);
                indexBufferInterface.m7554a(i6, i6 + 3, i6 + 2);
            }
            if (i3 != i2 - 1) {
                polyline.m6016a(i3, point2);
                polyline.m6016a(i3 + 1, point7);
                polyline.m6016a(i3 + 2, point3);
                PointUtil2D.m5997d(point7, point2, point9);
                PointUtil2D.m5997d(point3, point7, point4);
                if (((float) PointUtil2D.m5995c(point9, point4)) * f > 0.0f) {
                    int i7 = i6 + 4;
                    if (f > 0.0f) {
                        indexBufferInterface.m7554a(i6 + 3, i6 + 2, i7 + 1);
                    } else {
                        indexBufferInterface.m7554a(i6 + 2, i6 + 3, i7 + 1);
                    }
                }
                i3++;
            } else {
                return;
            }
        }
    }

    public final void m7551a(Polyline polyline, float f, float f2, Point point, int i, int i2, int i3, VertexBufferInterface vertexBufferInterface, IndexBufferInterface indexBufferInterface, TexCoordBufferInterface texCoordBufferInterface) {
        int b = polyline.m6020b();
        if (b > 1) {
            int i4 = b - 1;
            int a = vertexBufferInterface.m7565a();
            int i5 = b * 5;
            boolean e = polyline.m6028e();
            vertexBufferInterface.m7567a(a + i5);
            if (texCoordBufferInterface != null) {
                texCoordBufferInterface.m7563c(i5 + texCoordBufferInterface.m7564g());
            }
            indexBufferInterface.m7557b(((i4 * 3) * 6) + indexBufferInterface.m7556b());
            Point point2 = this.f4880b;
            Point point3 = this.f4881c;
            Point point4 = this.f4882d;
            Point point5 = this.f4883e;
            Point point6 = this.f4884f;
            Point point7 = this.f4885g;
            Point point8 = this.f4886h;
            ao aoVar = this.f4890l;
            ao aoVar2 = this.f4891m;
            ao aoVar3 = this.f4892n;
            ao aoVar4 = this.f4893o;
            ao aoVar5 = this.f4894p;
            ao aoVar6 = this.f4895q;
            int i6 = -1;
            int i7 = -1;
            int i8 = (int) (((((float) i2) * f2) + (((float) i3) * f)) / (f + f2));
            int i9 = 0;
            int i10 = -1;
            int i11 = -1;
            int i12 = -1;
            i5 = a;
            while (i9 < b) {
                ao a2;
                ao a3;
                Object obj;
                int i13;
                int i14;
                int i15;
                int i16;
                boolean d;
                float c;
                float f3;
                float min;
                int i17;
                int i18;
                Object obj2;
                polyline.m6017a(i9, point, point3);
                a = i9 - 1;
                i4 = i9 + 1;
                if (e) {
                    if (a < 0) {
                        a = b - 2;
                    }
                    if (i4 >= b) {
                        i4 = a;
                        a = 1;
                        if (i4 < 0) {
                            polyline.m6017a(i4, point, point2);
                            a2 = this.f4896r.m5585a(point3, point2);
                        } else {
                            a2 = null;
                        }
                        if (a >= b) {
                            polyline.m6017a(a, point, point4);
                            a3 = this.f4897s.m5585a(point3, point4);
                        } else {
                            a3 = null;
                        }
                        if (a2 == null && a3 != null) {
                            aoVar.m5584a(a2).m5590c();
                            aoVar2.m5584a(a3).m5590c();
                            aoVar3.m5584a(aoVar).m5588b(aoVar2);
                            if (aoVar3.m5586a(0.0f, 0.0f)) {
                                aoVar3.m5584a(aoVar).m5591d();
                                obj = 1;
                            } else {
                                obj = null;
                            }
                        } else if (a2 == null) {
                            aoVar3.m5584a(a2).m5591d().m5590c();
                            i13 = 1;
                        } else {
                            aoVar3.m5584a(a3).m5591d().m5590c().m5582a();
                            i13 = 1;
                        }
                        if (obj == null) {
                            ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f), point5);
                            ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(f2), point6);
                            vertexBufferInterface.m7568a(point5, i);
                            vertexBufferInterface.m7568a(point6, i);
                            if (texCoordBufferInterface != null) {
                                texCoordBufferInterface.m7560a(i2, 0);
                                texCoordBufferInterface.m7560a(i3, 0);
                            }
                            i4 = i5 + 1;
                            i14 = i5;
                            i15 = i4;
                            i16 = i4 + 1;
                            a = i6;
                            i6 = i7;
                            i7 = i4;
                            i4 = i5;
                        } else {
                            aoVar3.m5590c();
                            d = a2.m5592d(a3);
                            aoVar4.m5584a(a3).m5591d().m5590c();
                            c = aoVar3.m5589c(aoVar4);
                            f3 = (d ? f : f2) / (-c);
                            aoVar5.m5584a(aoVar3).m5583a(f3);
                            min = Math.min(a2.m5587b() / Math.abs(aoVar5.m5589c(aoVar6.m5584a(a2).m5590c())), a3.m5587b() / Math.abs(aoVar5.m5589c(aoVar6.m5584a(a3).m5590c())));
                            if (min < br.DEFAULT_BACKOFF_MULT) {
                                f3 *= Math.max(Math.abs(c), min);
                            }
                            if (d) {
                                ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(f3), point6);
                                ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f), point5);
                                ao.m5581a(point3, aoVar6.m5584a(a2).m5591d().m5590c().m5583a(-f), point7);
                                ao.m5581a(point3, aoVar6.m5584a(a3).m5591d().m5590c().m5583a(f), point8);
                                vertexBufferInterface.m7568a(point5, i);
                                vertexBufferInterface.m7568a(point6, i);
                                vertexBufferInterface.m7568a(point7, i);
                                vertexBufferInterface.m7568a(point8, i);
                                if (texCoordBufferInterface != null) {
                                    texCoordBufferInterface.m7560a(i2, 0);
                                    texCoordBufferInterface.m7560a(i3, 0);
                                    texCoordBufferInterface.m7560a(i2, 0);
                                    texCoordBufferInterface.m7560a(i2, 0);
                                }
                                i7 = i5 + 1;
                                i6 = i7 + 1;
                                i4 = i6 + 1;
                                i14 = i6;
                                i15 = i7;
                                i16 = i4 + 1;
                                a = i6;
                                i6 = i4;
                                i17 = i4;
                                i4 = i5;
                                i5 = i17;
                            } else {
                                ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f2), point5);
                                ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f3), point6);
                                ao.m5581a(point3, aoVar6.m5584a(a3).m5591d().m5590c().m5583a(-f2), point7);
                                ao.m5581a(point3, aoVar6.m5584a(a2).m5591d().m5590c().m5583a(f2), point8);
                                vertexBufferInterface.m7568a(point5, i);
                                vertexBufferInterface.m7568a(point6, i);
                                vertexBufferInterface.m7568a(point7, i);
                                vertexBufferInterface.m7568a(point8, i);
                                if (texCoordBufferInterface != null) {
                                    texCoordBufferInterface.m7560a(i3, 0);
                                    texCoordBufferInterface.m7560a(i2, 0);
                                    texCoordBufferInterface.m7560a(i3, 0);
                                    texCoordBufferInterface.m7560a(i3, 0);
                                }
                                i4 = i5 + 1;
                                i6 = i4 + 1;
                                i7 = i6 + 1;
                                i14 = i4;
                                i15 = i6;
                                i16 = i7 + 1;
                                a = i6;
                                i6 = i7;
                                i17 = i4;
                                i4 = i5;
                                i5 = i17;
                            }
                        }
                        i18 = i16 + 1;
                        vertexBufferInterface.m7568a(point3, i);
                        if (texCoordBufferInterface != null) {
                            texCoordBufferInterface.m7560a(i8, 0);
                        }
                        obj2 = (e || i9 != b - 1) ? null : 1;
                        if (obj == null && obj2 == null) {
                            indexBufferInterface.m7554a(a, i16, i4);
                            indexBufferInterface.m7554a(i16, i6, i4);
                        }
                        if (i9 > 0) {
                            indexBufferInterface.m7554a(i11, i10, i16);
                            indexBufferInterface.m7554a(i10, i12, i16);
                            indexBufferInterface.m7554a(i11, i16, i14);
                            indexBufferInterface.m7554a(i16, i12, i7);
                        }
                        i9++;
                        i10 = i16;
                        i11 = i5;
                        i12 = i15;
                        i7 = i6;
                        i6 = a;
                        i5 = i18;
                    }
                }
                i17 = i4;
                i4 = a;
                a = i17;
                if (i4 < 0) {
                    a2 = null;
                } else {
                    polyline.m6017a(i4, point, point2);
                    a2 = this.f4896r.m5585a(point3, point2);
                }
                if (a >= b) {
                    a3 = null;
                } else {
                    polyline.m6017a(a, point, point4);
                    a3 = this.f4897s.m5585a(point3, point4);
                }
                if (a2 == null) {
                }
                if (a2 == null) {
                    aoVar3.m5584a(a3).m5591d().m5590c().m5582a();
                    i13 = 1;
                } else {
                    aoVar3.m5584a(a2).m5591d().m5590c();
                    i13 = 1;
                }
                if (obj == null) {
                    aoVar3.m5590c();
                    d = a2.m5592d(a3);
                    aoVar4.m5584a(a3).m5591d().m5590c();
                    c = aoVar3.m5589c(aoVar4);
                    if (d) {
                    }
                    f3 = (d ? f : f2) / (-c);
                    aoVar5.m5584a(aoVar3).m5583a(f3);
                    min = Math.min(a2.m5587b() / Math.abs(aoVar5.m5589c(aoVar6.m5584a(a2).m5590c())), a3.m5587b() / Math.abs(aoVar5.m5589c(aoVar6.m5584a(a3).m5590c())));
                    if (min < br.DEFAULT_BACKOFF_MULT) {
                        f3 *= Math.max(Math.abs(c), min);
                    }
                    if (d) {
                        ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(f3), point6);
                        ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f), point5);
                        ao.m5581a(point3, aoVar6.m5584a(a2).m5591d().m5590c().m5583a(-f), point7);
                        ao.m5581a(point3, aoVar6.m5584a(a3).m5591d().m5590c().m5583a(f), point8);
                        vertexBufferInterface.m7568a(point5, i);
                        vertexBufferInterface.m7568a(point6, i);
                        vertexBufferInterface.m7568a(point7, i);
                        vertexBufferInterface.m7568a(point8, i);
                        if (texCoordBufferInterface != null) {
                            texCoordBufferInterface.m7560a(i2, 0);
                            texCoordBufferInterface.m7560a(i3, 0);
                            texCoordBufferInterface.m7560a(i2, 0);
                            texCoordBufferInterface.m7560a(i2, 0);
                        }
                        i7 = i5 + 1;
                        i6 = i7 + 1;
                        i4 = i6 + 1;
                        i14 = i6;
                        i15 = i7;
                        i16 = i4 + 1;
                        a = i6;
                        i6 = i4;
                        i17 = i4;
                        i4 = i5;
                        i5 = i17;
                    } else {
                        ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f2), point5);
                        ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f3), point6);
                        ao.m5581a(point3, aoVar6.m5584a(a3).m5591d().m5590c().m5583a(-f2), point7);
                        ao.m5581a(point3, aoVar6.m5584a(a2).m5591d().m5590c().m5583a(f2), point8);
                        vertexBufferInterface.m7568a(point5, i);
                        vertexBufferInterface.m7568a(point6, i);
                        vertexBufferInterface.m7568a(point7, i);
                        vertexBufferInterface.m7568a(point8, i);
                        if (texCoordBufferInterface != null) {
                            texCoordBufferInterface.m7560a(i3, 0);
                            texCoordBufferInterface.m7560a(i2, 0);
                            texCoordBufferInterface.m7560a(i3, 0);
                            texCoordBufferInterface.m7560a(i3, 0);
                        }
                        i4 = i5 + 1;
                        i6 = i4 + 1;
                        i7 = i6 + 1;
                        i14 = i4;
                        i15 = i6;
                        i16 = i7 + 1;
                        a = i6;
                        i6 = i7;
                        i17 = i4;
                        i4 = i5;
                        i5 = i17;
                    }
                } else {
                    ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(-f), point5);
                    ao.m5581a(point3, aoVar6.m5584a(aoVar3).m5583a(f2), point6);
                    vertexBufferInterface.m7568a(point5, i);
                    vertexBufferInterface.m7568a(point6, i);
                    if (texCoordBufferInterface != null) {
                        texCoordBufferInterface.m7560a(i2, 0);
                        texCoordBufferInterface.m7560a(i3, 0);
                    }
                    i4 = i5 + 1;
                    i14 = i5;
                    i15 = i4;
                    i16 = i4 + 1;
                    a = i6;
                    i6 = i7;
                    i7 = i4;
                    i4 = i5;
                }
                i18 = i16 + 1;
                vertexBufferInterface.m7568a(point3, i);
                if (texCoordBufferInterface != null) {
                    texCoordBufferInterface.m7560a(i8, 0);
                }
                if (e) {
                }
                indexBufferInterface.m7554a(a, i16, i4);
                indexBufferInterface.m7554a(i16, i6, i4);
                if (i9 > 0) {
                    indexBufferInterface.m7554a(i11, i10, i16);
                    indexBufferInterface.m7554a(i10, i12, i16);
                    indexBufferInterface.m7554a(i11, i16, i14);
                    indexBufferInterface.m7554a(i16, i12, i7);
                }
                i9++;
                i10 = i16;
                i11 = i5;
                i12 = i15;
                i7 = i6;
                i6 = a;
                i5 = i18;
            }
        }
    }
}
