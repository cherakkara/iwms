package com.leanplum;

import com.leanplum.callbacks.VariablesChangedCallback;

/* renamed from: com.leanplum.R */
final class C0617R implements C0602Y {
    private final /* synthetic */ VariablesChangedCallback f8604a;

    C0617R(C0615P c0615p, VariablesChangedCallback variablesChangedCallback) {
        this.f8604a = variablesChangedCallback;
    }

    public final void m12516a() {
        this.f8604a.variablesChanged();
    }
}
