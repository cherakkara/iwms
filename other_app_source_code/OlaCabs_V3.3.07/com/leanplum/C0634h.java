package com.leanplum;

import org.json.JSONObject;

/* renamed from: com.leanplum.h */
final class C0634h implements aa {
    private final /* synthetic */ Runnable f8811a;

    C0634h(Runnable runnable) {
        this.f8811a = runnable;
    }

    public final void m12776a(JSONObject jSONObject) {
        if (this.f8811a != null) {
            this.f8811a.run();
        }
    }
}
