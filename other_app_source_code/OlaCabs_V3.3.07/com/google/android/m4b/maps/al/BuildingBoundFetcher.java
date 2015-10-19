package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.av;
import com.google.android.m4b.maps.an.az;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.ap.IndoorBuildingStore;
import com.google.android.m4b.maps.ap.TileStore;
import com.google.android.m4b.maps.aq.IndoorBuildingCallback;
import com.google.android.m4b.maps.aq.TileCallback;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.al.b */
public final class BuildingBoundFetcher implements IndoorBuildingCallback, TileCallback {
    private final TileStore f3280a;
    private final IndoorBuildingStore f3281b;
    private final ac f3282c;
    private final Map<FeatureId, BuildingBoundFetcher> f3283d;
    private final Collection<BuildingBound> f3284e;
    private volatile boolean f3285f;
    private volatile BuildingBoundFetcher f3286g;

    /* renamed from: com.google.android.m4b.maps.al.b.a */
    static class BuildingBoundFetcher {
        private final FeatureId f3277a;
        private final az f3278b;
        private Point f3279c;

        public BuildingBoundFetcher(av avVar) {
            this.f3277a = FeatureId.m11300b(avVar.m5697d().toString());
            this.f3278b = new az();
            avVar.m5694a().m5574a(this.f3278b);
        }

        public final void m5264a(Point point) {
            this.f3279c = point;
        }

        public final BuildingBound m5263a() {
            return new BuildingBound(this.f3277a, this.f3278b, this.f3279c, new String[0]);
        }
    }

    /* renamed from: com.google.android.m4b.maps.al.b.b */
    public interface BuildingBoundFetcher {
        void m5265a(BuildingBoundFetcher buildingBoundFetcher, Collection<BuildingBound> collection);
    }

    public BuildingBoundFetcher(TileStore tileStore, IndoorBuildingStore indoorBuildingStore, ac acVar) {
        this.f3283d = Collections.synchronizedMap(au.m2807a());
        this.f3284e = Collections.synchronizedList(ar.m2515a());
        this.f3280a = tileStore;
        this.f3281b = indoorBuildingStore;
        this.f3282c = acVar;
    }

    public final void m5270a(BuildingBoundFetcher buildingBoundFetcher) {
        this.f3286g = buildingBoundFetcher;
        this.f3280a.m6172a(this.f3282c, (TileCallback) this);
    }

    public final ac m5269a() {
        return this.f3282c;
    }

    public final void m5271a(ac acVar, int i, aa aaVar) {
        if (i != 3) {
            av avVar;
            if (i != 2 && i == 1) {
                this.f3285f = true;
            }
            Collection collection = null;
            if (aaVar != null) {
                aq aqVar = (aq) aaVar;
                List a = ar.m2515a();
                VectorTile i2 = aqVar.m5643i();
                while (i2.hasNext()) {
                    bc bcVar = (bc) i2.next();
                    if (bcVar.m5546b() == 3) {
                        avVar = (av) bcVar;
                        if (avVar.m5705l()) {
                            com.google.android.m4b.maps.p057t.FeatureId d = avVar.m5697d();
                            if (!(d == null || d == com.google.android.m4b.maps.p057t.FeatureId.f7848a)) {
                                a.add(avVar);
                            }
                        }
                    }
                }
                collection = a;
            }
            if (r0 == null || r0.size() == 0) {
                m5268b();
                return;
            }
            for (av avVar2 : r0) {
                BuildingBoundFetcher buildingBoundFetcher = new BuildingBoundFetcher(avVar2);
                this.f3283d.put(buildingBoundFetcher.f3277a, buildingBoundFetcher);
            }
            for (BuildingBoundFetcher a2 : ar.m2516a(this.f3283d.values())) {
                this.f3281b.m6265a(a2.f3277a, (IndoorBuildingCallback) this);
            }
        }
    }

    public final void m5272a(FeatureId featureId, int i, bh bhVar) {
        synchronized (this.f3283d) {
            BuildingBoundFetcher buildingBoundFetcher = (BuildingBoundFetcher) this.f3283d.remove(featureId);
            boolean isEmpty = this.f3283d.isEmpty();
        }
        if (buildingBoundFetcher != null) {
            if (bhVar != null) {
                buildingBoundFetcher.m5264a(bhVar.m5800f());
                this.f3284e.add(buildingBoundFetcher.m5263a());
            }
            if (i == 1) {
                this.f3285f = true;
            }
            if (isEmpty) {
                m5268b();
            }
        }
    }

    private void m5268b() {
        if (this.f3286g != null) {
            if (this.f3285f) {
                this.f3286g.m5265a(this, null);
            } else {
                this.f3286g.m5265a(this, this.f3284e);
            }
        }
    }
}
