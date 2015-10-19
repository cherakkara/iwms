package com.payu.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import java.util.Timer;

/* renamed from: com.payu.sdk.t */
class C0942t implements OnDismissListener {
    final /* synthetic */ Timer f11585a;
    final /* synthetic */ ProcessPaymentActivity f11586b;

    C0942t(ProcessPaymentActivity processPaymentActivity, Timer timer) {
        this.f11586b = processPaymentActivity;
        this.f11585a = timer;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f11585a.cancel();
    }
}
