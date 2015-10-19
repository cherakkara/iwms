package com.payu.sdk;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: com.payu.sdk.o */
class C0937o extends WebChromeClient {
    final /* synthetic */ ProcessPaymentActivity f11577a;

    C0937o(ProcessPaymentActivity processPaymentActivity) {
        this.f11577a = processPaymentActivity;
    }

    public void onProgressChanged(WebView webView, int i) {
        this.f11577a.m14936a(0);
        if (i == 100) {
            this.f11577a.m14936a(8);
        }
    }
}
