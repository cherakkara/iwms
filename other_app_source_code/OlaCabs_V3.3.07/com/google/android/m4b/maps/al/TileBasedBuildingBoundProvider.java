package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.al.BuildingBoundFetcher.BuildingBoundFetcher;
import com.google.android.m4b.maps.al.BuildingBoundProvider.BuildingBoundProvider;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.ap.IndoorBuildingStore;
import com.google.android.m4b.maps.ap.TileStore;
import com.google.android.m4b.maps.ap.TileStoreFactory;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p028c.au;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* renamed from: com.google.android.m4b.maps.al.k */
public final class TileBasedBuildingBoundProvider implements BuildingBoundFetcher, BuildingBoundProvider {
    private final TileStore f3346b;
    private final IndoorBuildingStore f3347c;
    private final TileStore.TileStore f3348d;
    private final Map<ac, BuildingBoundFetcher> f3349e;
    private final LRUCache<ac, Collection<BuildingBound>> f3350f;
    private final Set<BuildingBoundProvider> f3351g;
    private volatile int f3352h;
    private volatile int f3353i;
    private volatile int f3354j;

    /* renamed from: com.google.android.m4b.maps.al.k.1 */
    class TileBasedBuildingBoundProvider implements TileStore.TileStore {
        private /* synthetic */ TileBasedBuildingBoundProvider f3345a;

        TileBasedBuildingBoundProvider(TileBasedBuildingBoundProvider tileBasedBuildingBoundProvider) {
            this.f3345a = tileBasedBuildingBoundProvider;
        }

        public final void m5377a() {
            this.f3345a.m5384a();
        }

        public final void m5378a(aa aaVar) {
            synchronized (this.f3345a.f3349e) {
                BuildingBoundFetcher buildingBoundFetcher = (BuildingBoundFetcher) this.f3345a.f3349e.remove(aaVar.m5409a());
            }
            synchronized (this.f3345a.f3350f) {
                Collection collection = (Collection) this.f3345a.f3350f.m6238c(aaVar.m5409a());
            }
            if (buildingBoundFetcher != null || collection != null) {
                this.f3345a.m5381b();
            }
        }
    }

    public TileBasedBuildingBoundProvider() {
        this.f3349e = au.m2807a();
        this.f3350f = new LRUCache(48);
        this.f3351g = new CopyOnWriteArraySet();
        if (TileStoreFactory.m6312a(ai.f3423a)) {
            this.f3346b = TileStoreFactory.m6313b(ai.f3423a);
            this.f3347c = IndoorBuildingStore.m6260c();
            this.f3348d = new TileBasedBuildingBoundProvider(this);
            this.f3346b.m6173a(this.f3348d);
            return;
        }
        this.f3346b = null;
        this.f3347c = null;
        this.f3348d = null;
    }

    public final Collection<BuildingBound> m5383a(ac acVar) {
        this.f3352h++;
        if (acVar.m5439b() > 14) {
            Object a = acVar.m5432a(14);
        } else {
            ac acVar2 = acVar;
        }
        synchronized (this.f3350f) {
            Collection collection = (Collection) this.f3350f.m6235b(a);
        }
        if (collection != null) {
            this.f3353i++;
            return BuildingBound.m5256a(collection, acVar.m5446i());
        }
        synchronized (this.f3349e) {
            BuildingBoundFetcher buildingBoundFetcher = (BuildingBoundFetcher) this.f3349e.get(a);
            if (buildingBoundFetcher == null) {
                buildingBoundFetcher = new BuildingBoundFetcher(this.f3346b, this.f3347c, a);
                this.f3349e.put(a, buildingBoundFetcher);
                a = 1;
            } else {
                a = null;
            }
        }
        if (a != null) {
            buildingBoundFetcher.m5270a(this);
            this.f3354j++;
        }
        return BuildingBoundProvider.f3287a;
    }

    public final void m5384a() {
        synchronized (this.f3349e) {
            this.f3349e.clear();
        }
        synchronized (this.f3350f) {
            this.f3350f.m6231a();
        }
        m5381b();
    }

    private void m5381b() {
        for (BuildingBoundProvider a : this.f3351g) {
            a.m5273a();
        }
    }

    public final boolean m5387a(FeatureId featureId) {
        return false;
    }

    public final void m5386a(BuildingBoundProvider buildingBoundProvider) {
        this.f3351g.add(buildingBoundProvider);
    }

    public final void m5388b(BuildingBoundProvider buildingBoundProvider) {
        this.f3351g.remove(buildingBoundProvider);
    }

    public final void m5385a(BuildingBoundFetcher buildingBoundFetcher, Collection<BuildingBound> collection) {
        synchronized (this.f3349e) {
            if (((BuildingBoundFetcher) this.f3349e.get(buildingBoundFetcher.m5269a())) != buildingBoundFetcher) {
                return;
            }
            this.f3349e.remove(buildingBoundFetcher.m5269a());
            if (collection != null) {
                synchronized (this.f3350f) {
                    this.f3350f.m6239c(buildingBoundFetcher.m5269a(), collection);
                }
                m5381b();
            }
        }
    }
}
