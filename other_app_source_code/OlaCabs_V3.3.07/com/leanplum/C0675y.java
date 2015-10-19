package com.leanplum;

import com.leanplum.callbacks.StartCallback;
import java.util.Map;

/* renamed from: com.leanplum.y */
final class C0675y extends StartCallback {
    private final /* synthetic */ String f8893a;
    private final /* synthetic */ Map f8894b;
    private final /* synthetic */ Map f8895c;

    C0675y(String str, Map map, Map map2) {
        this.f8893a = str;
        this.f8894b = map;
        this.f8895c = map2;
    }

    public final void onResponse(boolean z) {
        Leanplum.m12456c(this.f8893a, this.f8894b, this.f8895c);
    }
}
