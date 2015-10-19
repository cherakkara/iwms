package com.leanplum.messagetemplates;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.leanplum.ActionContext;

/* renamed from: com.leanplum.messagetemplates.r */
final class C0657r implements OnClickListener {
    private final /* synthetic */ ActionContext f8848a;

    C0657r(C0655p c0655p, ActionContext actionContext) {
        this.f8848a = actionContext;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f8848a.runActionNamed("Cancel action");
    }
}
