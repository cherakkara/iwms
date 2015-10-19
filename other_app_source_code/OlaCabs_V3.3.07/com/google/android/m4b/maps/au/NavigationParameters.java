package com.google.android.m4b.maps.au;

import com.google.android.m4b.maps.bx.ClientParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.au.g */
public final class NavigationParameters {
    private final int f4086a;
    private final boolean f4087b;
    private final int f4088c;
    private boolean f4089d;
    private boolean f4090e;

    NavigationParameters(ProtoBuf protoBuf) {
        Math.pow(10.0d, ((double) (-protoBuf.m10204d(1))) * 0.1d);
        NavigationParameters.m6624a(protoBuf, 2);
        protoBuf.m10204d(3);
        protoBuf.m10204d(4);
        protoBuf.m10204d(5);
        protoBuf.m10204d(6);
        protoBuf.m10204d(7);
        NavigationParameters.m6624a(protoBuf, 8);
        protoBuf.m10204d(9);
        protoBuf.m10204d(10);
        protoBuf.m10204d(11);
        protoBuf.m10204d(12);
        protoBuf.m10204d(13);
        protoBuf.m10204d(14);
        protoBuf.m10204d(27);
        protoBuf.m10204d(16);
        protoBuf.m10204d(17);
        protoBuf.m10204d(18);
        NavigationParameters.m6624a(protoBuf, 19);
        protoBuf.m10204d(20);
        protoBuf.m10204d(21);
        protoBuf.m10204d(22);
        protoBuf.m10204d(23);
        this.f4086a = protoBuf.m10204d(24);
        this.f4087b = protoBuf.m10200b(25);
        this.f4088c = protoBuf.m10204d(26);
        protoBuf.m10204d(28);
        protoBuf.m10204d(30);
        OfflineReroutingParameters offlineReroutingParameters = new OfflineReroutingParameters(protoBuf.m10214j(29) ? protoBuf.m10211g(29) : new ProtoBuf(ClientParameters.f6985j));
        protoBuf.m10200b(31);
        protoBuf.m10204d(32);
        this.f4089d = protoBuf.m10200b(33);
        this.f4090e = protoBuf.m10200b(34);
    }

    public final int m6625a() {
        return this.f4086a;
    }

    public final boolean m6626b() {
        return this.f4087b;
    }

    public final int m6627c() {
        return this.f4088c;
    }

    private static final double m6624a(ProtoBuf protoBuf, int i) {
        return ((double) protoBuf.m10204d(i)) * 1.0E-6d;
    }

    public final boolean m6628d() {
        return this.f4089d;
    }

    public final boolean m6629e() {
        return this.f4090e;
    }
}
