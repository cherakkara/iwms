package com.google.android.m4b.maps.ag;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.av.ZoomTable;
import com.google.android.m4b.maps.av.ZoomTableQuadTree;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ag.i */
public abstract class ZoomTableTileCoordGenerator implements TileCoordGenerator {
    protected final ai f3072a;
    protected final ah f3073b;
    protected final ZoomTableQuadTree f3074c;

    public ZoomTableTileCoordGenerator(ai aiVar, ah ahVar) {
        this(aiVar, ParameterManager.m6650c(), ahVar);
    }

    private ZoomTableTileCoordGenerator(ai aiVar, ZoomTableQuadTree zoomTableQuadTree, ah ahVar) {
        this.f3072a = aiVar;
        this.f3074c = zoomTableQuadTree;
        this.f3073b = ahVar;
        if (zoomTableQuadTree == null) {
            throw new RuntimeException("Null zoom table");
        }
    }

    public final ac m4893a(ac acVar, Point point) {
        int a = m4891b(point).m7353a(acVar.m5439b());
        if (a < 0) {
            return null;
        }
        return acVar.m5432a(a);
    }

    public final List<ac> m4895b(ac acVar, Point point) {
        List arrayList = new ArrayList();
        int b = m4891b(point).m7355b(acVar.m5439b());
        if (b >= 0) {
            int b2 = b - acVar.m5439b();
            int i = 1 << b2;
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    arrayList.add(acVar.m5433a(b, (acVar.m5440c() << b2) + i3, (acVar.m5441d() << b2) + i2));
                }
            }
        }
        return arrayList;
    }

    public final List<ac> m4894a(int i, Point point) {
        List<ac> arrayList = new ArrayList();
        ZoomTable b = m4891b(point);
        for (int i2 = 0; i2 <= 2; i2++) {
            if (b.m7356c(i2)) {
                int i3 = 1 << i2;
                for (int i4 = 0; i4 < i3; i4++) {
                    for (int i5 = 0; i5 < i3; i5++) {
                        arrayList.add(new ac(i2, i4, i5, this.f3073b.m5476a()));
                    }
                }
            }
        }
        return arrayList;
    }

    private ZoomTable m4891b(Point point) {
        return this.f3074c.m7365a(point, this.f3072a);
    }

    public float m4892a(Point point) {
        return (float) m4891b(point).m7351a();
    }
}
