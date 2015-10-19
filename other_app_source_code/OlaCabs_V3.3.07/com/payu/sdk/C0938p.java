package com.payu.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.payu.sdk.p */
class C0938p implements OnClickListener {
    final /* synthetic */ ProcessPaymentActivity f11578a;

    C0938p(ProcessPaymentActivity processPaymentActivity) {
        this.f11578a = processPaymentActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f11578a.f11511a = true;
        dialogInterface.dismiss();
        this.f11578a.onBackPressed();
    }
}
