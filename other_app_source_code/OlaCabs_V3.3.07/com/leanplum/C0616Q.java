package com.leanplum;

import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.leanplum.Q */
final class C0616Q implements aa {
    private final /* synthetic */ VariablesChangedCallback f8603a;

    C0616Q(C0615P c0615p, VariablesChangedCallback variablesChangedCallback) {
        this.f8603a = variablesChangedCallback;
    }

    public final void m12515a(JSONObject jSONObject) {
        Map map = null;
        JSONObject b = C0618S.m12533b(jSONObject);
        Map a = C0625a.m12600a(b.optJSONObject("vars"));
        Map a2 = C0625a.m12600a(b.optJSONObject("messages"));
        Map a3 = C0625a.m12600a(b.optJSONObject("regions"));
        List a4 = C0625a.m12598a(b.optJSONArray("variants"));
        if (!C0633g.f8808o || a.equals(aT.m12662a())) {
            a = null;
        }
        if (!a2.equals(aT.m12673b())) {
            map = a2;
        }
        if (!(a == null && map == null)) {
            aT.m12668a(a, map, a3, a4);
        }
        this.f8603a.variablesChanged();
    }
}
