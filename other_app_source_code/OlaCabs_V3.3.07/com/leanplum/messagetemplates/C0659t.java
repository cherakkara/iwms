package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.messagetemplates.t */
final class C0659t extends VariablesChangedCallback {
    private final /* synthetic */ ActionContext f8849a;

    C0659t(C0658s c0658s, ActionContext actionContext) {
        this.f8849a = actionContext;
    }

    public final void variablesChanged() {
        LeanplumActivityHelper.queueActionUponActive(new C0660u(this, this.f8849a));
    }
}
