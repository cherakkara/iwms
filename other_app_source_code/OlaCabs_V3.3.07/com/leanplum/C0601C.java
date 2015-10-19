package com.leanplum;

import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.leanplum.C */
final class C0601C implements aa {
    private final /* synthetic */ VariablesChangedCallback f8538a;

    C0601C(VariablesChangedCallback variablesChangedCallback) {
        this.f8538a = variablesChangedCallback;
    }

    public final void m12410a(JSONObject jSONObject) {
        JSONObject b = C0618S.m12533b(jSONObject);
        Map a = C0625a.m12600a(b.optJSONObject("vars"));
        Map a2 = C0625a.m12600a(b.optJSONObject("messages"));
        Map a3 = C0625a.m12600a(b.optJSONObject("regions"));
        List a4 = C0625a.m12598a(b.optJSONArray("variants"));
        if (!(a.equals(aT.m12662a()) && a2.equals(aT.m12673b()))) {
            aT.m12668a(a, a2, a3, a4);
        }
        if (this.f8538a != null) {
            C0612M.m12512a().m12513a(this.f8538a);
        }
    }
}
