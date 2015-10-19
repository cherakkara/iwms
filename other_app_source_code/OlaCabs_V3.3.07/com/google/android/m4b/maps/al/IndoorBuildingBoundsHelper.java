package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.al.BuildingBoundProvider.BuildingBoundProvider;
import com.google.android.m4b.maps.am.MapPoint;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p028c.ai;
import com.google.p025a.p028c.aj;
import com.google.p025a.p028c.aj.ImmutableSetMultimap;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.av;
import com.google.p025a.p028c.bk;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.al.e */
public final class IndoorBuildingBoundsHelper implements BuildingBoundProvider {
    private final av<ac, BuildingBound> f3295b;
    private final Set<FeatureId> f3296c;

    public static IndoorBuildingBoundsHelper m5288a(Reader reader, as asVar) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        Collection b = ar.m2524b();
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            Object obj;
            readLine = readLine.trim();
            if (readLine.length() == 0) {
                obj = null;
            } else {
                Object split = readLine.split("\\s+");
                if (split.length < 3) {
                    obj = null;
                } else {
                    FeatureId.FeatureId b2 = FeatureId.FeatureId.m11300b(split[0]);
                    MapPoint a = MapPoint.m5390a(split[1]);
                    MapPoint a2 = MapPoint.m5390a(split[2]);
                    if (b2 == null || a == null || a2 == null) {
                        obj = null;
                    } else {
                        Point a3;
                        int i;
                        if (split.length > 3) {
                            a3 = BuildingBound.m5255a(split[3]);
                            i = a3 != null ? 4 : 3;
                        } else {
                            i = 3;
                            a3 = null;
                        }
                        Point b3 = Point.m5934b(a.m5393a(), a.m5394b());
                        Point b4 = Point.m5934b(a2.m5393a(), a2.m5394b());
                        Object obj2 = new String[(split.length - i)];
                        System.arraycopy(split, i, obj2, 0, obj2.length);
                        obj = new BuildingBound(b2, Rectangle2D.m6041a(b3, b4), a3, obj2);
                    }
                }
            }
            if (obj != null) {
                b.add(obj);
            }
        }
        return new IndoorBuildingBoundsHelper(b, asVar);
    }

    public IndoorBuildingBoundsHelper() {
        this.f3295b = aj.m2439j();
        this.f3296c = ai.m2296g();
    }

    private IndoorBuildingBoundsHelper(Collection<BuildingBound> collection, as asVar) {
        ImmutableSetMultimap k = aj.m2440k();
        Collection a = bk.m2870a();
        for (BuildingBound buildingBound : collection) {
            as a2 = as.m5674a(buildingBound.m5260b());
            double e = ((double) a2.m5684e()) / a2.m5685f().m5956e();
            if (((double) a2.m5683d()) / a2.m5685f().m5956e() < 7000.0d && e < 7000.0d) {
                buildingBound.m5258a((Set) a);
                if (asVar == null || asVar.m5681b(buildingBound.m5260b())) {
                    Iterator it = ac.m5426a(a2, 15).iterator();
                    while (it.hasNext()) {
                        k.m2434a((ac) it.next(), buildingBound);
                    }
                }
            }
        }
        this.f3296c = ai.m2291a(a);
        this.f3295b = k.m2435a();
    }

    public final Collection<BuildingBound> m5289a(ac acVar) {
        ac a = acVar.m5431a();
        int b = a.m5439b();
        if (b < 15) {
            return ai.m2296g();
        }
        if (b == 15) {
            return this.f3295b.m2378b(a);
        }
        return BuildingBound.m5256a(this.f3295b.m2378b(a.m5432a(15)), a.m5446i());
    }

    public final boolean m5291a(FeatureId featureId) {
        return this.f3296c.contains(featureId);
    }

    public final void m5290a(BuildingBoundProvider buildingBoundProvider) {
    }

    public final void m5292b(BuildingBoundProvider buildingBoundProvider) {
    }
}
