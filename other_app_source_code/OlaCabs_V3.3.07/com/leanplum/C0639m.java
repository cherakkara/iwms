package com.leanplum;

import android.os.Bundle;
import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.leanplum.m */
final class C0639m extends VariablesChangedCallback {
    private final /* synthetic */ Bundle f8817a;

    C0639m(GcmBroadcastReceiver gcmBroadcastReceiver, Bundle bundle) {
        this.f8817a = bundle;
    }

    public final void variablesChanged() {
        String b = LeanplumPushService.m12496b(this.f8817a);
        if (b == null) {
            Leanplum.m12441a("Open action", b);
        } else if (LeanplumPushService.m12493a(this.f8817a)) {
            Map hashMap = new HashMap();
            hashMap.put("Open action", C0625a.m12599a(this.f8817a.getString("_lpx")));
            ActionContext actionContext = new ActionContext(C0629c.f8784a, hashMap, b);
            actionContext.m12402a();
            actionContext.runTrackedActionNamed("Open action");
        } else {
            Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new C0664n(this, b));
        }
    }
}
