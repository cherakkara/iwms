package com.payu.sdk;

import android.webkit.WebView;
import com.payu.p084a.Bank;
import com.payu.p084a.PayUWebChromeClient;

/* renamed from: com.payu.sdk.j */
class C0932j extends PayUWebChromeClient {
    final /* synthetic */ ProcessPaymentActivity f11569a;

    C0932j(ProcessPaymentActivity processPaymentActivity, Bank bank) {
        this.f11569a = processPaymentActivity;
        super(bank);
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        this.f11569a.m14937b(0);
        if (i >= 95 && this.f11569a.f11517g) {
            this.f11569a.m14936a(8);
        }
    }
}
