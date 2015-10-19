package com.leanplum.callbacks;

public abstract class StartCallback implements Runnable {
    private boolean f8526a;

    public abstract void onResponse(boolean z);

    public void setSuccess(boolean z) {
        this.f8526a = z;
    }

    public void run() {
        onResponse(this.f8526a);
    }
}
