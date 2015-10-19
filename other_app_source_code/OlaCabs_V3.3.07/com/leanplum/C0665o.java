package com.leanplum;

import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.o */
final class C0665o extends VariablesChangedCallback {
    private final /* synthetic */ String f8853a;

    C0665o(C0664n c0664n, String str) {
        this.f8853a = str;
    }

    public final void variablesChanged() {
        Leanplum.m12441a("Open action", this.f8853a);
    }
}
