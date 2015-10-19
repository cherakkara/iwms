package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;

/* renamed from: com.leanplum.messagetemplates.o */
final class C0654o extends ActionCallback {
    C0654o() {
    }

    public final boolean onResponse(ActionContext actionContext) {
        LeanplumActivityHelper.queueActionUponActive(new C0655p(this, actionContext));
        return true;
    }
}
