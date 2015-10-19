package com.leanplum;

import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.leanplum.P */
final class C0615P extends VariablesChangedCallback {
    private final /* synthetic */ String f8601a;
    private final /* synthetic */ VariablesChangedCallback f8602b;

    C0615P(String str, VariablesChangedCallback variablesChangedCallback) {
        this.f8601a = str;
        this.f8602b = variablesChangedCallback;
    }

    public final void variablesChanged() {
        Map l = aT.m12684l();
        if (this.f8601a == null || (l != null && l.containsKey(this.f8601a))) {
            this.f8602b.variablesChanged();
            return;
        }
        l = new HashMap();
        l.put("includeDefaults", "false");
        l.put("includeMessageId", this.f8601a);
        C0618S b = C0618S.m12530b("getVars", l);
        b.m12551a(new C0616Q(this, this.f8602b));
        b.m12550a(new C0617R(this, this.f8602b));
        b.m12557h();
    }
}
