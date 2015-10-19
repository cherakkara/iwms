package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p028c.ar;
import java.util.Collection;

/* renamed from: com.google.android.m4b.maps.al.c */
public interface BuildingBoundProvider {
    public static final Collection<BuildingBound> f3287a;

    /* renamed from: com.google.android.m4b.maps.al.c.a */
    public interface BuildingBoundProvider {
        void m5273a();
    }

    Collection<BuildingBound> m5274a(ac acVar);

    void m5275a(BuildingBoundProvider buildingBoundProvider);

    boolean m5276a(FeatureId featureId);

    void m5277b(BuildingBoundProvider buildingBoundProvider);

    static {
        f3287a = ar.m2515a();
    }
}
