package com.leanplum;

import android.util.Log;

final class aX implements Runnable {
    private /* synthetic */ aV f8717a;

    aX(aV aVVar) {
        this.f8717a = aVVar;
    }

    public final void run() {
        try {
            if (this.f8717a.f8709c != null) {
                this.f8717a.f8709c.close();
                this.f8717a.f8709c = null;
            }
        } catch (Exception e) {
            Log.d("WebSocketClient", "Error while disconnecting", e);
            this.f8717a.f8708b.m12642a(e);
        }
    }
}
