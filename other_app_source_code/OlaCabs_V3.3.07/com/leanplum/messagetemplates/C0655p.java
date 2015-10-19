package com.leanplum.messagetemplates;

import android.app.AlertDialog.Builder;
import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.messagetemplates.p */
final class C0655p extends VariablesChangedCallback {
    private final /* synthetic */ ActionContext f8846a;

    C0655p(C0654o c0654o, ActionContext actionContext) {
        this.f8846a = actionContext;
    }

    public final void variablesChanged() {
        Builder builder = new Builder(LeanplumActivityHelper.getCurrentActivity());
        builder.setTitle(this.f8846a.stringNamed("Title")).setMessage(this.f8846a.stringNamed("Message")).setCancelable(false).setPositiveButton(this.f8846a.stringNamed("Accept text"), new C0656q(this, this.f8846a)).setNegativeButton(this.f8846a.stringNamed("Cancel text"), new C0657r(this, this.f8846a));
        builder.create().show();
    }
}
