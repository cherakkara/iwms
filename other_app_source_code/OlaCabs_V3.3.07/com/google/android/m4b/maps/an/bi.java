package com.google.android.m4b.maps.an;

import android.support.v4.widget.ExploreByTouchHelper;
import com.google.android.m4b.maps.be.IndoorLevelInterface;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p026a.Function;
import com.google.p025a.p028c.ar;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.List;

/* compiled from: IndoorLevel */
public final class bi implements IndoorLevelInterface {
    private final List<FeatureId> f3583a;
    private final String f3584b;
    private final String f3585c;
    private final int f3586d;
    private final IndoorLevelReference f3587e;

    /* renamed from: com.google.android.m4b.maps.an.bi.a */
    public static class IndoorLevel {
        public static final Function<bi, com.google.android.m4b.maps.p057t.FeatureId> f3582a;

        /* renamed from: com.google.android.m4b.maps.an.bi.a.1 */
        static class IndoorLevel implements Function<bi, com.google.android.m4b.maps.p057t.FeatureId> {
            IndoorLevel() {
            }

            public final /* synthetic */ Object m5801a(Object obj) {
                return ((bi) obj).m5809b();
            }
        }

        static {
            f3582a = new IndoorLevel();
        }
    }

    private bi(FeatureId featureId, List<FeatureId> list, String str, String str2, int i, int i2, as asVar) {
        this.f3583a = list;
        this.f3584b = str;
        this.f3585c = str2;
        this.f3586d = i;
        this.f3587e = new IndoorLevelReference(featureId, i2);
    }

    public static bi m5807a(ProtoBuf protoBuf) {
        int i = 0;
        FeatureId b = FeatureId.m11300b(protoBuf.m10212h(1));
        if (b == null) {
            return null;
        }
        int k = protoBuf.m10215k(2);
        List b2 = ar.m2523b(k);
        for (int i2 = 0; i2 < k; i2++) {
            FeatureId b3 = FeatureId.m11300b(protoBuf.m10205d(2, i2));
            if (b3 != null) {
                b2.add(b3);
            }
        }
        String h = protoBuf.m10212h(3);
        String h2 = protoBuf.m10212h(4);
        if (h == null) {
            h = h2 != null ? h2 : Trace.NULL;
        }
        if (h2 == null) {
            h2 = h;
        }
        if (protoBuf.m10214j(5)) {
            i = protoBuf.m10204d(5);
        }
        int i3 = ExploreByTouchHelper.INVALID_ID;
        if (protoBuf.m10214j(8)) {
            i3 = protoBuf.m10204d(8);
        }
        as asVar = null;
        if (protoBuf.m10214j(7)) {
            ProtoBuf g = protoBuf.m10211g(7);
            Point a = Point.m5927a(g.m10211g(1));
            Point a2 = Point.m5927a(g.m10211g(2));
            if (a.f3646a > a2.f3646a) {
                a2.f3646a += 1073741824;
            }
            asVar = as.m5674a(new Rectangle2D(a, a2));
        }
        return new bi(b, b2, h, h2, i, i3, asVar);
    }

    public final IndoorLevelReference m5808a() {
        return this.f3587e;
    }

    public final FeatureId m5809b() {
        return this.f3587e.m11306a();
    }

    public final List<FeatureId> m5810c() {
        return this.f3583a;
    }

    public final String m5811d() {
        return this.f3584b;
    }

    public final String m5812e() {
        return this.f3585c;
    }

    public final int m5813f() {
        return this.f3586d;
    }

    public final String toString() {
        return "[Level: " + this.f3587e + "]";
    }
}
