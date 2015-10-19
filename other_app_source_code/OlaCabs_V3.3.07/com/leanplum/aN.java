package com.leanplum;

import android.os.Handler;
import android.os.Looper;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.http.client.methods.HttpPost;

final class aN extends Thread {
    private /* synthetic */ aI f8680a;

    aN(aI aIVar) {
        this.f8680a = aIVar;
    }

    public final void run() {
        try {
            String[] split = aI.m12638b(new HttpPost(this.f8680a.f8667a)).split(":");
            this.f8680a.f8669c = split[0];
            String str = split[1];
            if (!Trace.NULL.equals(str)) {
                this.f8680a.f8670d = (Integer.parseInt(str) / 2) * Constants.MILLIS_IN_A_SECOND;
            }
            if (new HashSet(Arrays.asList(split[3].split(","))).contains("websocket")) {
                Looper.prepare();
                this.f8680a.f8673g = Looper.myLooper();
                this.f8680a.f8672f = new Handler();
                aI.m12639b(this.f8680a);
                Looper.loop();
                return;
            }
            throw new Exception("websocket not supported");
        } catch (Exception e) {
            this.f8680a.f8668b.m12651a(e);
        }
    }
}
