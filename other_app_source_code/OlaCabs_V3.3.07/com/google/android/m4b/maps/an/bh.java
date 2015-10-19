package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.be.IndoorLevelInterface;
import com.google.android.m4b.maps.be.cf;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p028c.ar;
import java.util.List;

/* compiled from: IndoorBuilding */
public class bh implements cf {
    private final FeatureId f3576a;
    private final List<bi> f3577b;
    private final int f3578c;
    private final Point f3579d;
    private final long f3580e;
    private boolean f3581f;

    public final /* synthetic */ IndoorLevelInterface m5797c(IndoorLevelReference indoorLevelReference) {
        return m5791a(indoorLevelReference);
    }

    protected bh(FeatureId featureId, List<bi> list, int i, boolean z, Point point, long j) {
        this.f3576a = featureId;
        this.f3577b = list;
        this.f3578c = i;
        this.f3581f = z;
        this.f3579d = point;
        this.f3580e = j;
    }

    public static bh m5789a(ProtoBuf protoBuf, long j) {
        Point point = null;
        FeatureId b = FeatureId.m11300b(protoBuf.m10212h(1));
        if (b == null) {
            return null;
        }
        int i;
        int k = protoBuf.m10215k(2);
        List b2 = ar.m2523b(k);
        for (i = 0; i < k; i++) {
            bi a = bi.m5807a(protoBuf.m10202c(2, i));
            if (a != null) {
                b2.add(a);
            }
        }
        boolean b3 = protoBuf.m10200b(4);
        i = protoBuf.m10204d(3);
        if (i < 0 || i >= k) {
            i = 0;
        }
        if (b3 || k == 0) {
            i = -1;
        }
        if (protoBuf.m10214j(5)) {
            point = Point.m5927a(protoBuf.m10211g(5));
        }
        return new bh(b, b2, i, b3, point, j);
    }

    public final FeatureId m5792a() {
        return this.f3576a;
    }

    public final List<bi> m5795b() {
        return this.f3577b;
    }

    public final bi m5790a(FeatureId featureId) {
        for (bi biVar : this.f3577b) {
            if (featureId.equals(biVar.m5809b())) {
                return biVar;
            }
        }
        return null;
    }

    public final bi m5791a(IndoorLevelReference indoorLevelReference) {
        return m5790a(indoorLevelReference.m11306a());
    }

    public final int m5794b(IndoorLevelReference indoorLevelReference) {
        bi a = m5790a(indoorLevelReference.m11306a());
        if (a == null) {
            return -1;
        }
        return this.f3577b.indexOf(m5790a(a.m5809b()));
    }

    public final bi m5796c() {
        int i = this.f3578c;
        return (i < 0 || i >= this.f3577b.size()) ? null : (bi) this.f3577b.get(i);
    }

    public final int m5798d() {
        return this.f3578c;
    }

    public final boolean m5799e() {
        return this.f3581f;
    }

    public final Point m5800f() {
        return this.f3579d;
    }

    public final boolean m5793a(Clock clock) {
        return this.f3580e >= 0 && clock.m10151a() > this.f3580e;
    }

    public String toString() {
        return "[Building: " + this.f3576a + "]";
    }
}
