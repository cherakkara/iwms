package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;

/* renamed from: com.leanplum.messagetemplates.b */
final class C0642b extends ActionCallback {
    C0642b() {
    }

    public final boolean onResponse(ActionContext actionContext) {
        LeanplumActivityHelper.queueActionUponActive(new C0643c(this, actionContext));
        return true;
    }
}
