package com.leanplum.messagetemplates;

import android.app.AlertDialog.Builder;
import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.messagetemplates.c */
final class C0643c extends VariablesChangedCallback {
    private final /* synthetic */ ActionContext f8837a;

    C0643c(C0642b c0642b, ActionContext actionContext) {
        this.f8837a = actionContext;
    }

    public final void variablesChanged() {
        Builder builder = new Builder(LeanplumActivityHelper.getCurrentActivity());
        builder.setTitle(this.f8837a.stringNamed("Title")).setMessage(this.f8837a.stringNamed("Message")).setCancelable(false).setPositiveButton(this.f8837a.stringNamed("Dismiss text"), new C0644d(this, this.f8837a));
        builder.create().show();
    }
}
