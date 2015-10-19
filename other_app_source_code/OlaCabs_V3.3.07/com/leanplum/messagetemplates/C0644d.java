package com.leanplum.messagetemplates;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.leanplum.ActionContext;

/* renamed from: com.leanplum.messagetemplates.d */
final class C0644d implements OnClickListener {
    private final /* synthetic */ ActionContext f8838a;

    C0644d(C0643c c0643c, ActionContext actionContext) {
        this.f8838a = actionContext;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f8838a.runActionNamed("Dismiss action");
    }
}
