package com.leanplum;

import com.leanplum.callbacks.StartCallback;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.leanplum.w */
final class C0673w extends StartCallback {
    private final /* synthetic */ String f8891a;

    C0673w(String str) {
        this.f8891a = str;
    }

    public final void onResponse(boolean z) {
        if (!C0633g.m12775a()) {
            Map hashMap = new HashMap();
            hashMap.put("gcmRegistrationId", this.f8891a);
            C0618S.m12530b("setDeviceAttributes", hashMap).m12554e();
            Leanplum.removeStartResponseHandler(Leanplum.f8575v);
        }
    }
}
