package com.leanplum;

import android.util.Log;
import com.leanplum.callbacks.StartCallback;
import org.json.JSONObject;

final class ah implements Runnable {
    private final /* synthetic */ JSONObject f8744a;
    private final /* synthetic */ StartCallback f8745b;

    ah(ag agVar, JSONObject jSONObject, StartCallback startCallback) {
        this.f8744a = jSONObject;
        this.f8745b = startCallback;
    }

    public final void run() {
        JSONObject b = C0618S.m12533b(this.f8744a);
        if (!C0618S.m12539c(b)) {
            Log.e("Leanplum", C0618S.m12541d(b));
            if (this.f8745b != null) {
                this.f8745b.onResponse(false);
            }
        } else if (this.f8745b != null) {
            this.f8745b.onResponse(true);
        }
    }
}
