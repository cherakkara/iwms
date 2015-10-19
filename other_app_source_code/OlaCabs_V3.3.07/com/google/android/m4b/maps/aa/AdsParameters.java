package com.google.android.m4b.maps.aa;

import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.aa.a */
public final class AdsParameters {
    private volatile boolean f2945a;

    public AdsParameters(ProtoBuf protoBuf) {
        boolean z = true;
        this.f2945a = true;
        if (!(this.f2945a && protoBuf.m10200b(1))) {
            z = false;
        }
        this.f2945a = z;
    }
}
