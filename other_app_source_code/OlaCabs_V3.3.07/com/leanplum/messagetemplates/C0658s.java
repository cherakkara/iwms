package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.callbacks.ActionCallback;

/* renamed from: com.leanplum.messagetemplates.s */
final class C0658s extends ActionCallback {
    C0658s() {
    }

    public final boolean onResponse(ActionContext actionContext) {
        Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new C0659t(this, actionContext));
        return true;
    }
}
