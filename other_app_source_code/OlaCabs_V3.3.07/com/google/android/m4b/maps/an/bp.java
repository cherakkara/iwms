package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.au.FeatureMapRasterParser;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MapsEngineFeatureMap */
public final class bp {
    private final List<bq> f3619a;
    private final byte[] f3620b;
    private FeatureMapRasterParser f3621c;

    public bp(List<bq> list, byte[] bArr) {
        this.f3619a = list;
        this.f3620b = bArr;
    }

    public final List<bq> m5870a() {
        return this.f3619a;
    }

    public final FeatureMapRasterParser m5871b() {
        if (!(this.f3621c != null || this.f3619a.isEmpty() || this.f3620b.length == 0)) {
            List arrayList = new ArrayList(this.f3619a.size());
            for (bq a : this.f3619a) {
                arrayList.add(a.m5872a());
            }
            this.f3621c = new FeatureMapRasterParser(arrayList, this.f3620b);
        }
        return this.f3621c;
    }
}
