package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.messagetemplates.u */
final class C0660u extends VariablesChangedCallback {
    private final /* synthetic */ ActionContext f8850a;

    C0660u(C0659t c0659t, ActionContext actionContext) {
        this.f8850a = actionContext;
    }

    public final void variablesChanged() {
        new Interstitial(LeanplumActivityHelper.getCurrentActivity(), new InterstitialOptions(this.f8850a)).show();
    }
}
