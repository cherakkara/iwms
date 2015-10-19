package com.payu.sdk;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.payu.p084a.Bank;

/* renamed from: com.payu.sdk.k */
class C0933k extends WebViewClient {
    final /* synthetic */ Bank f11570a;
    final /* synthetic */ ProcessPaymentActivity f11571b;

    C0933k(ProcessPaymentActivity processPaymentActivity, Bank bank) {
        this.f11571b = processPaymentActivity;
        this.f11570a = bank;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.f11571b.f11519i = str;
        if (!(this.f11571b.f11518h == null || this.f11571b.f11518h.size() <= 0 || this.f11571b.f11518h.contains(str))) {
            this.f11571b.f11517g = true;
        }
        if (this.f11571b.f11516f != null && str.contains(this.f11571b.f11516f)) {
            this.f11570a.update();
        }
    }
}
