package com.leanplum.messagetemplates;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.leanplum.ActionContext;

/* renamed from: com.leanplum.messagetemplates.q */
final class C0656q implements OnClickListener {
    private final /* synthetic */ ActionContext f8847a;

    C0656q(C0655p c0655p, ActionContext actionContext) {
        this.f8847a = actionContext;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f8847a.runTrackedActionNamed("Accept action");
    }
}
