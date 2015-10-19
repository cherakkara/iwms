package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.am.MapPoint;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.ay;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.ParseHexUtil;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.bk;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.al.a */
public final class BuildingBound {
    private final FeatureId f3273a;
    private final ay f3274b;
    private final Point f3275c;
    private final Set<com.google.android.m4b.maps.p057t.FeatureId> f3276d;

    public BuildingBound(FeatureId featureId, ay ayVar, Point point, String[] strArr) {
        this.f3273a = featureId;
        this.f3274b = ayVar;
        if (point == null) {
            point = ayVar.m5313a().m6052e();
        }
        this.f3275c = point;
        this.f3276d = bk.m2870a();
        for (String a : strArr) {
            this.f3276d.add(com.google.android.m4b.maps.p057t.FeatureId.m11291a(a));
        }
    }

    public final FeatureId m5257a() {
        return this.f3273a;
    }

    public final Rectangle2D m5260b() {
        return this.f3274b.m5313a();
    }

    public final boolean m5259a(Region2D region2D) {
        return this.f3274b.m5315a(region2D);
    }

    public final Point m5261c() {
        return this.f3275c;
    }

    static Point m5255a(String str) {
        MapPoint a = MapPoint.m5390a(str);
        if (a != null) {
            return Point.m5934b(a.m5393a(), a.m5394b());
        }
        if (!str.startsWith("0x1:0x")) {
            return null;
        }
        String str2;
        String substring;
        if (str.length() <= 14) {
            str2 = "0";
            substring = str.substring(6);
        } else {
            str2 = str.substring(6, str.length() - 8);
            substring = str.substring(str.length() - 8);
        }
        try {
            return new Point(ParseHexUtil.m11310b(str2), ParseHexUtil.m11310b(substring));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BuildingBound)) {
            return false;
        }
        BuildingBound buildingBound = (BuildingBound) obj;
        if (buildingBound.f3273a.equals(this.f3273a) && buildingBound.f3274b.equals(this.f3274b) && buildingBound.f3275c.equals(this.f3275c) && buildingBound.f3276d.equals(this.f3276d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((((this.f3274b.hashCode() * 31) + this.f3275c.hashCode()) * 31) + this.f3276d.hashCode()) * 31) + this.f3273a.hashCode();
    }

    public final String toString() {
        return "[" + this.f3273a + " : " + this.f3274b + " : " + this.f3275c + " : " + this.f3276d + "]";
    }

    public static Collection<BuildingBound> m5256a(Collection<BuildingBound> collection, Region2D region2D) {
        if (collection.isEmpty()) {
            return collection;
        }
        List b = ar.m2524b();
        for (BuildingBound buildingBound : collection) {
            if (buildingBound.m5259a(region2D)) {
                b.add(buildingBound);
            }
        }
        return b;
    }

    final void m5258a(Set<com.google.android.m4b.maps.p057t.FeatureId> set) {
        set.addAll(this.f3276d);
        this.f3276d.clear();
    }
}
