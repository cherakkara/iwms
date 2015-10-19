package com.leanplum;

import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.n */
final class C0664n extends VariablesChangedCallback {
    private final /* synthetic */ String f8852a;

    C0664n(C0639m c0639m, String str) {
        this.f8852a = str;
    }

    public final void variablesChanged() {
        LeanplumPushService.m12492a(this.f8852a, new C0665o(this, this.f8852a));
    }
}
