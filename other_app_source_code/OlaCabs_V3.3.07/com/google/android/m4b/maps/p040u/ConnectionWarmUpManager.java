package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.cm.Clock;
import com.newrelic.agent.android.instrumentation.Trace;

/* renamed from: com.google.android.m4b.maps.u.f */
public final class ConnectionWarmUpManager {
    private int f7871a;
    private String f7872b;
    private String f7873c;
    private long f7874d;
    private Object f7875e;
    private DataRequestDispatcher f7876f;
    private Clock f7877g;

    public ConnectionWarmUpManager(DataRequestDispatcher dataRequestDispatcher, Clock clock) {
        this.f7871a = 0;
        this.f7872b = null;
        this.f7873c = null;
        this.f7876f = dataRequestDispatcher;
        this.f7877g = clock;
    }

    public final void m11341a() {
        synchronized (this) {
        }
    }

    public final void m11342a(Object obj) {
        synchronized (this) {
            if (this.f7871a == 1) {
                this.f7871a = 2;
                this.f7874d = this.f7877g.m10152b();
            } else if (this.f7871a == 2) {
                this.f7871a = 3;
                this.f7875e = obj;
            }
        }
    }

    public final void m11343a(Object obj, long j, int i, int i2) {
        synchronized (this) {
            if (this.f7871a == 3 && this.f7875e == obj) {
                this.f7871a = 0;
                String str = this.f7873c;
                long j2 = this.f7874d;
                this.f7875e = null;
                int i3 = (int) (j - j2);
                ConnectionWarmUpManager.m11340a("u", str, "|d=" + i3 + "|fb=" + i + "|lb=" + i2 + "|");
                return;
            }
        }
    }

    private static void m11340a(String str, String str2, String str3) {
        UserEventReporter.m11502a(64, str, "|s=" + str2 + (str3.length() == 0 ? "|" : Trace.NULL) + str3);
    }
}
