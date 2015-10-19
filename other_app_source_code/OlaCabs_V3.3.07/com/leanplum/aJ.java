package com.leanplum;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

final class aJ implements Runnable {
    private /* synthetic */ aI f8674a;
    private final /* synthetic */ JSONObject f8675b;

    aJ(aI aIVar, JSONObject jSONObject) {
        this.f8674a = aIVar;
        this.f8675b = jSONObject;
    }

    public final void run() {
        aV aVVar = this.f8674a.f8671e;
        String str = "5:::%s";
        Object[] objArr = new Object[1];
        JSONObject jSONObject = this.f8675b;
        objArr[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        aVVar.m12702a(String.format(str, objArr));
    }
}
