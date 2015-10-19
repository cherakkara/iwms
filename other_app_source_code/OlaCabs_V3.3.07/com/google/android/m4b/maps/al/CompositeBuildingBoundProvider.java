package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.al.BuildingBoundProvider.BuildingBoundProvider;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.bk;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.al.d */
public final class CompositeBuildingBoundProvider implements BuildingBoundProvider {
    private final Object f3288b;
    private boolean f3289c;
    private boolean f3290d;
    private volatile BuildingBoundProvider f3291e;
    private volatile BuildingBoundProvider f3292f;
    private final List<BuildingBoundProvider> f3293g;
    private final CompositeBuildingBoundProvider f3294h;

    /* renamed from: com.google.android.m4b.maps.al.d.a */
    public interface CompositeBuildingBoundProvider {
        BuildingBoundProvider m5278a();

        BuildingBoundProvider m5279a(String str);
    }

    /* renamed from: com.google.android.m4b.maps.al.d.1 */
    class CompositeBuildingBoundProvider implements CompositeBuildingBoundProvider {
        CompositeBuildingBoundProvider(CompositeBuildingBoundProvider compositeBuildingBoundProvider) {
        }

        public final BuildingBoundProvider m5280a() {
            return new TileBasedBuildingBoundProvider();
        }

        public final BuildingBoundProvider m5281a(String str) {
            return new LazyBuildingBoundProvider(str);
        }
    }

    private boolean m5282a() {
        boolean z = false;
        synchronized (this.f3288b) {
            if (this.f3290d) {
                return true;
            }
            boolean z2;
            if (this.f3289c && ParameterManager.m6648b()) {
                this.f3289c = false;
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                return false;
            }
            boolean z3;
            BuildingBoundProvider a;
            BuildingBoundProvider a2;
            if (ParameterManager.m6641a().m6628d()) {
                z3 = false;
            } else {
                z3 = true;
                z = true;
            }
            if (z) {
                a = this.f3294h.m5278a();
            } else {
                a = null;
            }
            if (z3) {
                a2 = this.f3294h.m5279a("/new.building.list");
            } else {
                a2 = null;
            }
            if (a2 == null) {
                a2 = this.f3294h.m5279a("/building.list");
            }
            this.f3292f = a2;
            this.f3291e = a;
            synchronized (this.f3288b) {
                this.f3290d = true;
                for (BuildingBoundProvider c : this.f3293g) {
                    m5283c(c);
                }
                this.f3293g.clear();
            }
            return true;
        }
    }

    public CompositeBuildingBoundProvider() {
        this.f3288b = new Object();
        this.f3289c = true;
        this.f3293g = ar.m2515a();
        this.f3294h = new CompositeBuildingBoundProvider(this);
        m5282a();
    }

    public final Collection<BuildingBound> m5284a(ac acVar) {
        if (!m5282a()) {
            return BuildingBoundProvider.f3287a;
        }
        com.google.p025a.p028c.ac g = com.google.p025a.p028c.ac.m2345g();
        Collection g2 = com.google.p025a.p028c.ac.m2345g();
        if (this.f3292f != null) {
            Iterable a = this.f3292f.m5274a(acVar);
        } else {
            Object obj = g;
        }
        if (this.f3291e != null) {
            g2 = this.f3291e.m5274a(acVar);
        }
        if (r1 == BuildingBoundProvider.f3287a || r2 == BuildingBoundProvider.f3287a) {
            return BuildingBoundProvider.f3287a;
        }
        if (r1.isEmpty() && r2.isEmpty()) {
            return com.google.p025a.p028c.ac.m2345g();
        }
        Set a2 = bk.m2870a();
        for (BuildingBound a3 : r1) {
            a2.add(a3.m5257a());
        }
        Collection<BuildingBound> a4 = ar.m2516a((Iterable) r1);
        for (BuildingBound a32 : r2) {
            if (!a2.contains(a32.m5257a())) {
                a4.add(a32);
            }
        }
        return a4;
    }

    public final boolean m5286a(FeatureId featureId) {
        boolean z = false;
        if (!m5282a()) {
            return false;
        }
        if (this.f3292f != null) {
            z = this.f3292f.m5276a(featureId);
        }
        if (z || this.f3291e == null) {
            return z;
        }
        return this.f3291e.m5276a(featureId);
    }

    private void m5283c(BuildingBoundProvider buildingBoundProvider) {
        if (this.f3292f != null) {
            this.f3292f.m5275a(buildingBoundProvider);
        }
        if (this.f3291e != null) {
            this.f3291e.m5275a(buildingBoundProvider);
        }
    }

    public final void m5285a(BuildingBoundProvider buildingBoundProvider) {
        m5282a();
        synchronized (this.f3288b) {
            if (this.f3290d) {
                m5283c(buildingBoundProvider);
                return;
            }
            this.f3293g.add(buildingBoundProvider);
        }
    }

    public final void m5287b(BuildingBoundProvider buildingBoundProvider) {
        m5282a();
        synchronized (this.f3288b) {
            if (this.f3290d) {
                if (this.f3292f != null) {
                    this.f3292f.m5277b(buildingBoundProvider);
                }
                if (this.f3291e != null) {
                    this.f3291e.m5277b(buildingBoundProvider);
                    return;
                }
                return;
            }
            this.f3293g.remove(buildingBoundProvider);
        }
    }
}
