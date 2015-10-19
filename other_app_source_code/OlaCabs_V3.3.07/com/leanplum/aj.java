package com.leanplum;

import com.leanplum.callbacks.StartCallback;

final class aj implements Runnable {
    private final /* synthetic */ StartCallback f8747a;

    aj(ai aiVar, StartCallback startCallback) {
        this.f8747a = startCallback;
    }

    public final void run() {
        if (this.f8747a != null) {
            this.f8747a.onResponse(false);
        }
    }
}
