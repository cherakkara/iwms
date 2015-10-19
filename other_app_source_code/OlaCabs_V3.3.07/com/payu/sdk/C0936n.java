package com.payu.sdk;

import android.content.Intent;

/* renamed from: com.payu.sdk.n */
class C0936n implements Runnable {
    final /* synthetic */ String f11575a;
    final /* synthetic */ C0934l f11576b;

    C0936n(C0934l c0934l, String str) {
        this.f11576b = c0934l;
        this.f11575a = str;
    }

    public void run() {
        Intent intent = new Intent();
        intent.putExtra("result", this.f11575a);
        this.f11576b.f11572a.setResult(0, intent);
        this.f11576b.f11572a.finish();
    }
}
