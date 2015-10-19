package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.callbacks.ActionCallback;

/* renamed from: com.leanplum.messagetemplates.k */
final class C0650k extends ActionCallback {
    C0650k() {
    }

    public final boolean onResponse(ActionContext actionContext) {
        Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new C0651l(this, actionContext));
        return true;
    }
}
