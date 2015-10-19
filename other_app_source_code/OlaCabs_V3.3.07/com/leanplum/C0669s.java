package com.leanplum;

import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.leanplum.s */
final class C0669s implements Runnable {
    private final /* synthetic */ ActionCallback f8869a;
    private final /* synthetic */ ActionContext f8870b;
    private final /* synthetic */ VariablesChangedCallback f8871c;
    private final /* synthetic */ AtomicBoolean f8872d;

    C0669s(ActionCallback actionCallback, ActionContext actionContext, VariablesChangedCallback variablesChangedCallback, AtomicBoolean atomicBoolean) {
        this.f8869a = actionCallback;
        this.f8870b = actionContext;
        this.f8871c = variablesChangedCallback;
        this.f8872d = atomicBoolean;
    }

    public final void run() {
        if (this.f8869a.onResponse(this.f8870b) && this.f8871c != null && !this.f8872d.getAndSet(true)) {
            this.f8871c.variablesChanged();
        }
    }
}
