package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.messagetemplates.x */
final class C0663x extends VariablesChangedCallback {
    private final /* synthetic */ ActionContext f8851a;

    C0663x(C0662w c0662w, ActionContext actionContext) {
        this.f8851a = actionContext;
    }

    public final void variablesChanged() {
        new WebInterstitial(LeanplumActivityHelper.getCurrentActivity(), new WebInterstitialOptions(this.f8851a)).show();
    }
}
