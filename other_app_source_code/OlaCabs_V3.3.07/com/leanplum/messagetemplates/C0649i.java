package com.leanplum.messagetemplates;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.leanplum.messagetemplates.i */
final class C0649i implements OnClickListener {
    private /* synthetic */ BaseMessageDialog f8843a;

    C0649i(BaseMessageDialog baseMessageDialog) {
        this.f8843a = baseMessageDialog;
    }

    public final void onClick(View view) {
        if (!this.f8843a.f8820c) {
            this.f8843a.options.accept();
            this.f8843a.cancel();
        }
    }
}
