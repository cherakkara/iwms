package com.google.android.m4b.maps.ay;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.al;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.bk;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.ay.n */
public final class TriangleListToPolylineConverter {

    /* renamed from: com.google.android.m4b.maps.ay.n.a */
    static class TriangleListToPolylineConverter {
        private final LinkedList<Point> f4953a;

        public TriangleListToPolylineConverter(Point point, Point point2) {
            this.f4953a = new LinkedList();
            this.f4953a.add(point);
            this.f4953a.add(point2);
        }

        public final boolean m7633a(TriangleListToPolylineConverter triangleListToPolylineConverter) {
            if (((Point) triangleListToPolylineConverter.f4953a.getLast()).equals(this.f4953a.getFirst())) {
                this.f4953a.removeFirst();
                this.f4953a.addAll(0, triangleListToPolylineConverter.f4953a);
                return true;
            } else if (!((Point) triangleListToPolylineConverter.f4953a.getFirst()).equals(this.f4953a.getLast())) {
                return false;
            } else {
                this.f4953a.removeLast();
                this.f4953a.addAll(triangleListToPolylineConverter.f4953a);
                return true;
            }
        }

        public final Point m7632a() {
            return (Point) this.f4953a.getFirst();
        }

        public final Point m7634b() {
            return (Point) this.f4953a.getLast();
        }

        public final Polyline m7635c() {
            Polyline.Polyline polyline = new Polyline.Polyline(this.f4953a.size());
            Iterator it = this.f4953a.iterator();
            while (it.hasNext()) {
                polyline.m6006a((Point) it.next());
            }
            return polyline.m6008c();
        }
    }

    private static void m7637a(Map<Point, TriangleListToPolylineConverter> map, Point point, Point point2) {
        TriangleListToPolylineConverter triangleListToPolylineConverter = new TriangleListToPolylineConverter(point, point2);
        TriangleListToPolylineConverter triangleListToPolylineConverter2 = (TriangleListToPolylineConverter) map.get(point);
        TriangleListToPolylineConverter triangleListToPolylineConverter3 = (TriangleListToPolylineConverter) map.get(point2);
        if (triangleListToPolylineConverter2 != null && triangleListToPolylineConverter.m7633a(triangleListToPolylineConverter2)) {
            map.remove(triangleListToPolylineConverter2.m7632a());
            map.remove(triangleListToPolylineConverter2.m7634b());
        }
        if (!(triangleListToPolylineConverter3 == null || triangleListToPolylineConverter3 == triangleListToPolylineConverter2 || !triangleListToPolylineConverter.m7633a(triangleListToPolylineConverter3))) {
            map.remove(triangleListToPolylineConverter3.m7632a());
            map.remove(triangleListToPolylineConverter3.m7634b());
        }
        map.put(triangleListToPolylineConverter.m7632a(), triangleListToPolylineConverter);
        map.put(triangleListToPolylineConverter.m7634b(), triangleListToPolylineConverter);
    }

    public static List<Polyline> m7636a(al alVar, byte[] bArr) {
        Map a = au.m2807a();
        for (int i = 0; i < alVar.m5571a(); i++) {
            Point point = new Point();
            Point point2 = new Point();
            Point point3 = new Point();
            alVar.m5572a(i, point, point2, point3);
            if ((bArr[i] & 1) != 0) {
                TriangleListToPolylineConverter.m7637a(a, point, point2);
            }
            if ((bArr[i] & 2) != 0) {
                TriangleListToPolylineConverter.m7637a(a, point2, point3);
            }
            if ((bArr[i] & 4) != 0) {
                TriangleListToPolylineConverter.m7637a(a, point3, point);
            }
        }
        Set<TriangleListToPolylineConverter> a2 = bk.m2872a(a.values());
        List<Polyline> b = ar.m2523b(a2.size());
        for (TriangleListToPolylineConverter c : a2) {
            b.add(c.m7635c());
        }
        return b;
    }
}
