package com.payu.sdk;

import android.content.Intent;

/* renamed from: com.payu.sdk.m */
class C0935m implements Runnable {
    final /* synthetic */ String f11573a;
    final /* synthetic */ C0934l f11574b;

    C0935m(C0934l c0934l, String str) {
        this.f11574b = c0934l;
        this.f11573a = str;
    }

    public void run() {
        Intent intent = new Intent();
        intent.putExtra("result", this.f11573a);
        this.f11574b.f11572a.setResult(-1, intent);
        this.f11574b.f11572a.finish();
    }
}
