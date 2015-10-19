package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;

/* renamed from: com.leanplum.messagetemplates.w */
final class C0662w extends ActionCallback {
    C0662w() {
    }

    public final boolean onResponse(ActionContext actionContext) {
        LeanplumActivityHelper.queueActionUponActive(new C0663x(this, actionContext));
        return true;
    }
}
