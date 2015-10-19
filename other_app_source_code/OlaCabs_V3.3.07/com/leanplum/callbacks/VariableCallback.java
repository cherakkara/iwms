package com.leanplum.callbacks;

import com.leanplum.Var;

public abstract class VariableCallback<T> implements Runnable {
    private Var<T> f8772a;

    public abstract void handle(Var<T> var);

    public void setVariable(Var<T> var) {
        this.f8772a = var;
    }

    public void run() {
        handle(this.f8772a);
    }
}
