package com.leanplum;

import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.leanplum.t */
final class C0670t extends VariablesChangedCallback {
    private final /* synthetic */ String f8873a;

    C0670t(String str) {
        this.f8873a = str;
    }

    public final void variablesChanged() {
        Map hashMap = new HashMap();
        hashMap.put("messageId", this.f8873a);
        Leanplum.m12439a("Cancel", 0.0d, null, null, hashMap);
    }
}
