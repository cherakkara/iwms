package com.leanplum.messagetemplates;

import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.messagetemplates.m */
final class C0652m extends VariablesChangedCallback {
    private final /* synthetic */ ActionContext f8845a;

    C0652m(C0651l c0651l, ActionContext actionContext) {
        this.f8845a = actionContext;
    }

    public final void variablesChanged() {
        new CenterPopup(LeanplumActivityHelper.getCurrentActivity(), new CenterPopupOptions(this.f8845a)).show();
    }
}
