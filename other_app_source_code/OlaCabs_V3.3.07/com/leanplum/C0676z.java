package com.leanplum;

import com.leanplum.callbacks.StartCallback;
import java.util.Map;

/* renamed from: com.leanplum.z */
final class C0676z extends StartCallback {
    private final /* synthetic */ String f8896a;
    private final /* synthetic */ Map f8897b;
    private final /* synthetic */ Map f8898c;

    C0676z(String str, Map map, Map map2) {
        this.f8896a = str;
        this.f8897b = map;
        this.f8898c = map2;
    }

    public final void onResponse(boolean z) {
        Leanplum.m12459d(this.f8896a, this.f8897b, this.f8898c);
    }
}
