package com.google.android.m4b.maps.ag;

import com.google.android.m4b.maps.al.BuildingBound;
import com.google.android.m4b.maps.al.BuildingBoundProvider;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.af;
import com.google.android.m4b.maps.an.ag;
import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.an.bi;
import com.google.android.m4b.maps.an.bj;
import com.google.android.m4b.maps.an.bj.IndoorParameters;
import com.google.android.m4b.maps.ap.IndoorBuildingStore;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.bk;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.ag.a */
public final class IndoorTileCoordGenerator implements TileCoordGenerator {
    private final TileCoordGenerator f3060a;
    private final BuildingBoundProvider f3061b;
    private volatile boolean f3062c;
    private List<ac> f3063d;
    private List<ac> f3064e;
    private Set<ac> f3065f;
    private Set<FeatureId> f3066g;
    private FeatureId f3067h;
    private bj f3068i;
    private final LRUCache<ac, Collection<BuildingBound>> f3069j;
    private final IndoorState f3070k;
    private long f3071l;

    public IndoorTileCoordGenerator(TileCoordGenerator tileCoordGenerator, BuildingBoundProvider buildingBoundProvider, IndoorBuildingStore indoorBuildingStore, int i, IndoorState indoorState) {
        this.f3062c = false;
        this.f3067h = null;
        this.f3068i = null;
        this.f3071l = 0;
        this.f3060a = tileCoordGenerator;
        this.f3061b = buildingBoundProvider;
        this.f3069j = new LRUCache(i);
        this.f3070k = indoorState;
    }

    private void m4877e(Camera camera) {
        List<ac> a = this.f3060a.m4876a(camera);
        if (this.f3062c || a == null || !a.equals(this.f3063d)) {
            this.f3071l++;
            this.f3062c = false;
            bh c = this.f3070k.m5360c();
            Object a2;
            if (c != null) {
                a2 = c.m5792a();
            } else {
                a2 = null;
            }
            Iterable a3 = bk.m2870a();
            Set a4 = bk.m2870a();
            Set a5 = bk.m2870a();
            for (ac acVar : a) {
                Collection collection = (Collection) this.f3069j.m6235b((Object) acVar);
                if (collection == null) {
                    collection = this.f3061b.m5274a(acVar);
                    if (collection != BuildingBoundProvider.f3287a) {
                        this.f3069j.m6239c(acVar, collection);
                    }
                }
                for (BuildingBound buildingBound : r3) {
                    af afVar;
                    ag agVar;
                    a5.add(buildingBound.m5257a());
                    IndoorLevelReference a6 = this.f3070k.m5347a(buildingBound.m5257a());
                    if (a6 == null) {
                        afVar = null;
                    } else {
                        afVar = new IndoorParameters().m5814a(a6).m5815a();
                    }
                    if (afVar != null) {
                        agVar = new ag();
                        agVar.m5473a(afVar);
                        a3.add(acVar.m5434a(agVar));
                    }
                    synchronized (this) {
                        if (buildingBound.m5257a().equals(this.f3067h)) {
                            agVar = new ag();
                            agVar.m5473a(this.f3068i);
                            a3.add(acVar.m5434a(agVar));
                        }
                    }
                    if (afVar != null && buildingBound.m5257a().equals(r4)) {
                        int b = c.m5794b(afVar.m5822c());
                        if (b != -1) {
                            List b2 = c.m5795b();
                            int max = Math.max(b - 1, 0);
                            int min = Math.min((b + 1) + 1, b2.size());
                            for (int i = max; i < min; i++) {
                                if (i != b) {
                                    ag agVar2 = new ag();
                                    agVar2.m5473a(bj.m5816a(((bi) b2.get(i)).m5808a()));
                                    Object a7 = acVar.m5434a(agVar2);
                                    ac a8 = m4880a((ac) a7, camera.m7430b());
                                    if (a8 != null) {
                                        ac acVar2 = a8;
                                    }
                                    a4.add(a7);
                                }
                            }
                        }
                    }
                }
            }
            this.f3063d = a;
            this.f3064e = ar.m2516a(a3);
            this.f3065f = a4;
            this.f3066g = a5;
        }
    }

    public final List<ac> m4882a(Camera camera) {
        m4877e(camera);
        return this.f3064e;
    }

    public final long m4879a() {
        return this.f3071l;
    }

    public final Set<ac> m4884b(Camera camera) {
        m4877e(camera);
        return this.f3065f;
    }

    public final Set<FeatureId> m4886c(Camera camera) {
        m4877e(camera);
        return this.f3066g;
    }

    public final FeatureId m4888d(Camera camera) {
        double d = 8.0d;
        FeatureId featureId = null;
        m4877e(camera);
        for (Object obj : this.f3063d) {
            if (obj.m5446i().m6046a(camera.m7434c())) {
                break;
            }
        }
        Object obj2 = null;
        Collection<BuildingBound> collection = (Collection) this.f3069j.m6235b(obj2);
        if (collection != null) {
            Point c = camera.m7434c();
            double m = (double) camera.m7445m();
            if (m > 19.0d) {
                d = 8.0d / Math.pow(2.0d, m - 19.0d);
            }
            Region2D a = Rectangle2D.m6040a(c, (int) (d * c.m5956e()));
            float f = 0.0f;
            for (BuildingBound buildingBound : collection) {
                FeatureId a2;
                float f2;
                if (buildingBound.m5259a(a)) {
                    float d2 = buildingBound.m5261c().m5954d(c);
                    if (featureId == null || d2 < f) {
                        a2 = buildingBound.m5257a();
                        f2 = d2;
                        f = f2;
                        featureId = a2;
                    }
                }
                a2 = featureId;
                f2 = f;
                f = f2;
                featureId = a2;
            }
        }
        return featureId;
    }

    public final void m4885b() {
        this.f3062c = true;
    }

    public final synchronized void m4883a(FeatureId featureId, IndoorLevelReference indoorLevelReference) {
        this.f3067h = featureId;
        this.f3068i = new IndoorParameters().m5814a(indoorLevelReference).m5815a();
        this.f3062c = true;
    }

    public final synchronized void m4887c() {
        this.f3067h = null;
        this.f3068i = null;
        this.f3062c = true;
    }

    public final ac m4880a(ac acVar, Point point) {
        return this.f3060a.m4874a(acVar, point);
    }

    public final float m4878a(Point point) {
        return this.f3060a.m4872a(point);
    }

    public final List<ac> m4881a(int i, Point point) {
        return com.google.p025a.p028c.ac.m2345g();
    }
}
