package com.google.android.m4b.maps.aa;

import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.aa.b */
public final class ApiParameters {
    private final boolean f2946a;
    private final boolean f2947b;

    public ApiParameters(ProtoBuf protoBuf) {
        this.f2946a = protoBuf.m10200b(1);
        this.f2947b = protoBuf.m10200b(2);
    }

    public final boolean m4741a() {
        return this.f2946a;
    }

    public final boolean m4742b() {
        return this.f2947b;
    }
}
