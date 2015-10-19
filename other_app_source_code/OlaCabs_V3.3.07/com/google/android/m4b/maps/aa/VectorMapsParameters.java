package com.google.android.m4b.maps.aa;

import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.olacabs.customer.utils.Constants;

/* renamed from: com.google.android.m4b.maps.aa.i */
public final class VectorMapsParameters {
    private final int f3023a;
    private final boolean f3024b;
    private final int f3025c;
    private final int f3026d;
    private final int f3027e;
    private int f3028f;
    private boolean f3029g;
    private final boolean f3030h;
    private final boolean f3031i;
    private final String f3032j;

    public final String toString() {
        return "personalizedSmartMapsTileDuration: " + this.f3023a + " onlyRequestPsmWhenPoiInBaseTile: " + this.f3024b + " minPsmRequestZoom: " + this.f3025c + " pertileDuration: " + this.f3026d + " pertileClientCoverage: " + this.f3027e + " diskCacheServerSchemaVersion:" + this.f3028f + " offlineBorderTiles:" + this.f3029g;
    }

    public VectorMapsParameters(ProtoBuf protoBuf) {
        this.f3023a = protoBuf.m10204d(1);
        this.f3024b = protoBuf.m10200b(2);
        this.f3025c = protoBuf.m10204d(3);
        this.f3026d = protoBuf.m10204d(4);
        this.f3027e = protoBuf.m10204d(6);
        this.f3028f = protoBuf.m10204d(7);
        this.f3029g = protoBuf.m10200b(8);
        this.f3030h = protoBuf.m10213i(9);
        this.f3031i = protoBuf.m10200b(9);
        this.f3032j = protoBuf.m10212h(17);
    }

    public final long m4820a() {
        return ((long) this.f3026d) * Constants.MILLIS_IN_A_MINUTE;
    }

    public final int m4821b() {
        return this.f3028f;
    }

    public final boolean m4822c() {
        return this.f3029g;
    }

    public final boolean m4823d() {
        return this.f3030h;
    }

    public final boolean m4824e() {
        return this.f3031i;
    }

    public final String m4825f() {
        return this.f3032j;
    }
}
