package com.google.android.m4b.maps.aa;

import com.google.android.m4b.maps.bx.ClientParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.aa.f */
public final class PrefetcherSettingsParameters {
    private final int f2979a;
    private final int f2980b;
    private final int f2981c;
    private final int f2982d;
    private final int f2983e;
    private final int f2984f;

    public final String toString() {
        return "maxTiles: " + this.f2979a + " maxServerTiles: " + this.f2980b + " prefetchPeriod: " + this.f2981c + " prefetchInitiatorDelay: " + this.f2982d + " prefetchInitiatorPeriod: " + this.f2983e + " timeToWipe: " + this.f2984f;
    }

    public PrefetcherSettingsParameters(ProtoBuf protoBuf) {
        this.f2979a = protoBuf.m10204d(1);
        this.f2980b = protoBuf.m10204d(2);
        this.f2981c = protoBuf.m10204d(3);
        this.f2982d = protoBuf.m10204d(4);
        this.f2983e = protoBuf.m10204d(5);
        this.f2984f = protoBuf.m10204d(6);
        protoBuf.m10204d(7);
        protoBuf.m10207e(8);
        protoBuf.m10200b(9);
    }

    public final long m4747a() {
        return ((long) this.f2984f) * 86400000;
    }

    public static ProtoBuf m4746b() {
        return new ProtoBuf(ClientParameters.f6987l);
    }
}
