package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.messagetemplates.l */
final class C0651l extends VariablesChangedCallback {
    private final /* synthetic */ ActionContext f8844a;

    C0651l(C0650k c0650k, ActionContext actionContext) {
        this.f8844a = actionContext;
    }

    public final void variablesChanged() {
        LeanplumActivityHelper.queueActionUponActive(new C0652m(this, this.f8844a));
    }
}
