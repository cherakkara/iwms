package com.leanplum.callbacks;

public abstract class RegisterDeviceCallback implements Runnable {
    private EmailCallback f8789a;

    public abstract class EmailCallback implements Runnable {
        private String f8552a;

        public abstract void onResponse(String str);

        public void setResponseHandler(String str) {
            this.f8552a = str;
        }

        public void run() {
            onResponse(this.f8552a);
        }
    }

    public abstract void onResponse(EmailCallback emailCallback);

    public void setResponseHandler(EmailCallback emailCallback) {
        this.f8789a = emailCallback;
    }

    public void run() {
        onResponse(this.f8789a);
    }
}
