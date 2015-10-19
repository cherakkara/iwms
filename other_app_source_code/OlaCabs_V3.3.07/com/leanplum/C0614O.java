package com.leanplum;

import android.os.Bundle;
import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.HashMap;

/* renamed from: com.leanplum.O */
final class C0614O extends VariablesChangedCallback {
    private /* synthetic */ LeanplumPushService f8598a;
    private final /* synthetic */ String f8599b;
    private final /* synthetic */ Bundle f8600c;

    C0614O(LeanplumPushService leanplumPushService, String str, Bundle bundle) {
        this.f8598a = leanplumPushService;
        this.f8599b = str;
        this.f8600c = bundle;
    }

    public final void variablesChanged() {
        new HashMap().put("messageId", this.f8599b);
        this.f8598a.m12499c(this.f8600c);
    }
}
