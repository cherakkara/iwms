package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.ae.PerformanceProfile;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p028c.au;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.av.u */
public final class ZoomTableQuadTree {
    private static final ac f4737b;
    private final ZoomTableQuadTree f4738a;
    private final Map<ai, ZoomTable> f4739c;
    private Point f4740d;

    /* renamed from: com.google.android.m4b.maps.av.u.a */
    static class ZoomTableQuadTree {
        private HashMap<ai, ZoomTable> f4735a;
        private ZoomTableQuadTree[] f4736b;

        public final ZoomTable m7360a(ai aiVar) {
            if (this.f4735a == null) {
                return null;
            }
            return (ZoomTable) this.f4735a.get(aiVar);
        }

        public final void m7362a(int i, int i2, int i3, ai aiVar, ZoomTable zoomTable) {
            while (i3 > 0) {
                i3--;
                int a = ZoomTableQuadTree.m7359a(i, i2, i3);
                if (this.f4736b == null) {
                    this.f4736b = new ZoomTableQuadTree[4];
                }
                ZoomTableQuadTree zoomTableQuadTree = this.f4736b[a];
                if (zoomTableQuadTree == null) {
                    zoomTableQuadTree = new ZoomTableQuadTree();
                    this.f4736b[a] = zoomTableQuadTree;
                }
                this = zoomTableQuadTree;
            }
            if (this.f4735a == null) {
                this.f4735a = au.m2807a();
            }
            this.f4735a.put(aiVar, zoomTable);
        }

        public static int m7359a(int i, int i2, int i3) {
            return ((i >> i3) & 1) + (((i2 >> i3) & 1) << 1);
        }

        public final ZoomTableQuadTree m7361a(int i) {
            if (this.f4736b == null || i < 0 || i > 3) {
                return null;
            }
            return this.f4736b[i];
        }
    }

    static {
        f4737b = new ac(0, 0, 0);
    }

    public static ZoomTableQuadTree m7364a(ProtoBuf protoBuf) {
        if (protoBuf == null) {
            return null;
        }
        int k = protoBuf.m10215k(1);
        if (k == 0) {
            return null;
        }
        PerformanceProfile.m4867a();
        ZoomTableQuadTree zoomTableQuadTree = new ZoomTableQuadTree();
        for (int i = 0; i < k; i++) {
            int[] iArr;
            int i2;
            ProtoBuf c = protoBuf.m10202c(1, i);
            int d = c.m10204d(3);
            int k2 = c.m10215k(2);
            int d2 = c.m10204d(5);
            if (k2 > 0) {
                iArr = new int[k2];
                for (i2 = 0; i2 < k2; i2++) {
                    iArr[i2] = c.m10195b(2, i2);
                }
            } else {
                iArr = new int[((d + 1) - d2)];
                for (i2 = 0; i2 <= d - d2; i2++) {
                    iArr[i2] = i2 + d2;
                }
            }
            ZoomTable zoomTable = new ZoomTable(iArr, d2, c.m10204d(1), d);
            int k3 = c.m10215k(4);
            for (d2 = 0; d2 < k3; d2++) {
                ProtoBuf c2 = c.m10202c(4, d2);
                int d3 = c2.m10204d(2);
                i2 = c2.m10204d(3);
                d = c2.m10204d(4);
                int k4 = c2.m10215k(1);
                ac acVar = new ac(d3, i2, d);
                for (int i3 = 0; i3 < k4; i3++) {
                    ai a = ai.m5501a(c2.m10195b(1, i3));
                    if (a != null) {
                        zoomTableQuadTree.m7362a(acVar.m5440c(), acVar.m5441d(), acVar.m5439b(), a, zoomTable);
                    }
                }
            }
        }
        PerformanceProfile.m4868b();
        return new ZoomTableQuadTree(zoomTableQuadTree);
    }

    private ZoomTableQuadTree(ZoomTableQuadTree zoomTableQuadTree) {
        this.f4739c = au.m2807a();
        this.f4738a = zoomTableQuadTree;
    }

    private ZoomTable m7363a(int i, int i2, int i3, ai aiVar, ZoomTableQuadTree zoomTableQuadTree) {
        ZoomTable a;
        do {
            if (zoomTableQuadTree == this.f4738a || zoomTableQuadTree.m7360a(aiVar) == null) {
                i3--;
                zoomTableQuadTree = zoomTableQuadTree.m7361a(ZoomTableQuadTree.m7359a(i, i2, i3));
            } else {
                a = zoomTableQuadTree.m7360a(aiVar);
                if (a != null) {
                    return a;
                }
                Util.m11550a("ZoomTableQuadTree", "No zoom table for tile type " + aiVar);
                return ZoomTable.f4724a;
            }
        } while (zoomTableQuadTree != null);
        a = this.f4738a.m7360a(aiVar);
        if (a != null) {
            return a;
        }
        Util.m11550a("ZoomTableQuadTree", "No root zoom table for tile type " + aiVar);
        return ZoomTable.f4724a;
    }

    public final ZoomTable m7365a(Point point, ai aiVar) {
        ZoomTable zoomTable;
        if (this.f4740d == null || !this.f4740d.equals(point)) {
            this.f4739c.clear();
        } else {
            zoomTable = (ZoomTable) this.f4739c.get(aiVar);
            if (zoomTable != null) {
                return zoomTable;
            }
        }
        ac a = ac.m5424a(30, point);
        if (a == null) {
            a = f4737b;
        }
        this.f4740d = point;
        zoomTable = m7363a(a.m5440c(), a.m5441d(), a.m5439b(), aiVar, this.f4738a);
        this.f4739c.put(aiVar, zoomTable);
        return zoomTable;
    }
}
