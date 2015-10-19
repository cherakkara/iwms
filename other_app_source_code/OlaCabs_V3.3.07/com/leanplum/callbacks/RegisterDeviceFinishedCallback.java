package com.leanplum.callbacks;

public abstract class RegisterDeviceFinishedCallback implements Runnable {
    private boolean f8790a;

    public abstract void onResponse(boolean z);

    public void setSuccess(boolean z) {
        this.f8790a = z;
    }

    public void run() {
        onResponse(this.f8790a);
    }
}
