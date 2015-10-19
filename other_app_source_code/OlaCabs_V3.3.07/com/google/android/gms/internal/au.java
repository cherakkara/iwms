package com.google.android.gms.internal;

import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

public class au {
    private static final String f2378a;
    private static final long f2379b;
    private final Handler f2380c;
    private final Runnable f2381d;
    private final Object f2382e;
    private ArrayList<String> f2383f;
    private ArrayList<String> f2384g;

    static {
        f2378a = au.class.getSimpleName();
        f2379b = TimeUnit.SECONDS.toMillis(1);
    }

    public void m4033a(String str, String str2) {
        synchronized (this.f2382e) {
            if (this.f2383f == null) {
                this.f2383f = new ArrayList();
                this.f2384g = new ArrayList();
                this.f2380c.postDelayed(this.f2381d, f2379b);
            }
            this.f2383f.add(str);
            this.f2384g.add(str2);
            if (this.f2383f.size() >= AbstractSpiCall.DEFAULT_TIMEOUT) {
                if (Log.isLoggable(f2378a, 5)) {
                    Log.w(f2378a, "Event buffer full, flushing");
                }
                this.f2381d.run();
                this.f2380c.removeCallbacks(this.f2381d);
                return;
            }
        }
    }
}
