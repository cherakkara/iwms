package com.payu.sdk;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.TimerTask;

/* renamed from: com.payu.sdk.r */
class C0940r extends TimerTask {
    int f11580a;
    final /* synthetic */ Drawable[] f11581b;
    final /* synthetic */ ImageView f11582c;
    final /* synthetic */ ProcessPaymentActivity f11583d;

    C0940r(ProcessPaymentActivity processPaymentActivity, Drawable[] drawableArr, ImageView imageView) {
        this.f11583d = processPaymentActivity;
        this.f11581b = drawableArr;
        this.f11582c = imageView;
        this.f11580a = -1;
    }

    public synchronized void run() {
        this.f11583d.runOnUiThread(new C0941s(this));
    }
}
