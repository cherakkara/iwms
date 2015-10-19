package com.payu.sdk;

import android.webkit.JavascriptInterface;
import com.newrelic.agent.android.instrumentation.Trace;

/* renamed from: com.payu.sdk.l */
class C0934l {
    final /* synthetic */ ProcessPaymentActivity f11572a;

    C0934l(ProcessPaymentActivity processPaymentActivity) {
        this.f11572a = processPaymentActivity;
    }

    @JavascriptInterface
    public void onFailure() {
        onFailure(Trace.NULL);
    }

    @JavascriptInterface
    public void onFailure(String str) {
        this.f11572a.runOnUiThread(new C0936n(this, str));
    }

    @JavascriptInterface
    public void onSuccess() {
        onSuccess(Trace.NULL);
    }

    @JavascriptInterface
    public void onSuccess(String str) {
        this.f11572a.runOnUiThread(new C0935m(this, str));
    }
}
