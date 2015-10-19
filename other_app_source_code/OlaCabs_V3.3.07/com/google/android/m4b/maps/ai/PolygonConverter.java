package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.ai.Polygon2dTessellator.Polygon2dTessellator;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.az.IndexBufferInterface;
import com.google.android.m4b.maps.az.VertexBufferInterface;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ai.f */
public final class PolygonConverter {

    /* renamed from: com.google.android.m4b.maps.ai.f.1 */
    static /* synthetic */ class PolygonConverter {
        static final /* synthetic */ int[] f3142a;

        static {
            f3142a = new int[PolygonConverter.values().length];
            try {
                f3142a[PolygonConverter.AREA_VERIFICATION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3142a[PolygonConverter.AREA_VERIFICATION_WITH_REASON.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3142a[PolygonConverter.COMPLETE_VERIFICATION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3142a[PolygonConverter.NO_VERIFICATION_WITH_REASON.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ai.f.a */
    public enum PolygonConverter {
        NO_VERIFICATION,
        NO_VERIFICATION_WITH_REASON,
        AREA_VERIFICATION,
        AREA_VERIFICATION_WITH_REASON,
        COMPLETE_VERIFICATION
    }

    public static TriangleMesh2d m5015a(List<Polyline> list, PolygonConverter polygonConverter) {
        if (list.size() == 0) {
            return TriangleMesh2d.m5060a();
        }
        int[] iArr = new int[(list.size() + 1)];
        int i = 0;
        int i2 = 0;
        for (Polyline polyline : list) {
            int i3;
            iArr[i2] = i;
            i += polyline.m6020b() - 1;
            if (polyline.m6023c().equals(polyline.m6014a(0))) {
                i3 = i;
            } else {
                i3 = i + 1;
            }
            i2++;
            i = i3;
        }
        iArr[i2] = i;
        double[] dArr = new double[(i * 2)];
        PolygonConverter.m5018a((Polyline) list.get(0), false, dArr, 0);
        i = 1;
        for (Polyline polyline2 : list.subList(1, list.size())) {
            PolygonConverter.m5018a(polyline2, true, dArr, iArr[i]);
            i++;
        }
        Polygon2d polygon2d = new Polygon2d(PolygonVertexList.m5020a(dArr, iArr));
        if (polygonConverter == PolygonConverter.COMPLETE_VERIFICATION) {
            Polygon2dTessellator b = Polygon2dTessellator.m5014b(polygon2d);
            if (b != null) {
                throw new Polygon2dTessellator("Verification failed, the polygon violates " + b.toString(), null);
            }
        }
        try {
            TriangleMesh2d a = Polygon2dTessellator.m5010a(polygon2d);
            switch (PolygonConverter.f3142a[polygonConverter.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    if (a.m5065a(polygon2d)) {
                        return a;
                    }
                    throw new Polygon2dTessellator("Could not tessellate polygon, area not equal", null);
                default:
                    return a;
            }
        } catch (Polygon2dTessellator e) {
            switch (PolygonConverter.f3142a[polygonConverter.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    Polygon2dTessellator.m5014b(polygon2d);
                    break;
            }
            throw e;
        }
    }

    private static void m5018a(Polyline polyline, boolean z, double[] dArr, int i) {
        int i2 = 0;
        Point point = new Point();
        int b = z ? polyline.m6020b() - 1 : 0;
        int i3 = z ? -1 : 1;
        int b2 = polyline.m6020b();
        if (polyline.m6023c().equals(polyline.m6014a(0))) {
            b2--;
        }
        while (i2 < b2) {
            polyline.m6016a((i3 * i2) + b, point);
            dArr[(i + i2) * 2] = (double) point.m5958f();
            dArr[((i + i2) * 2) + 1] = (double) point.m5960g();
            i2++;
        }
    }

    public static void m5017a(TriangleMesh2d triangleMesh2d, VertexBufferInterface vertexBufferInterface, Point point, int i) {
        Point point2 = new Point();
        int e = triangleMesh2d.m5070e();
        vertexBufferInterface.m7567a(vertexBufferInterface.m7565a() + e);
        for (int i2 = 0; i2 < e; i2++) {
            point2.m5955d((int) triangleMesh2d.m5062a(i2), (int) triangleMesh2d.m5066b(i2));
            Point.m5936b(point2, point, point2);
            vertexBufferInterface.m7568a(point2, i);
        }
    }

    public static void m5016a(TriangleMesh2d triangleMesh2d, IndexBufferInterface indexBufferInterface, int i) {
        indexBufferInterface.m7557b(indexBufferInterface.m7556b() + (triangleMesh2d.m5069d() * 3));
        int d = triangleMesh2d.m5069d();
        for (int i2 = 0; i2 < d; i2++) {
            indexBufferInterface.m7554a(triangleMesh2d.m5063a(i2, 0) + i, triangleMesh2d.m5063a(i2, 1) + i, triangleMesh2d.m5063a(i2, 2) + i);
        }
    }
}
