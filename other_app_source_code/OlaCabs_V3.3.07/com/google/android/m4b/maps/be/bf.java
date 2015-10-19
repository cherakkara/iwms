package com.google.android.m4b.maps.be;

import android.os.Handler;
import android.os.Looper;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UsageLogImpl */
public final class bf implements be, Runnable {
    private final Map<UsageLogImpl, UsageLogImpl> f5799a;
    private long f5800b;
    private final Clock f5801c;
    private final Handler f5802d;
    private final UsageLogImpl f5803e;
    private String f5804f;

    /* renamed from: com.google.android.m4b.maps.be.bf.a */
    static final class UsageLogImpl {
        public final UsageLog f5795a;
        public final String f5796b;

        public UsageLogImpl(UsageLog usageLog, String str) {
            this.f5795a = usageLog;
            this.f5796b = str;
        }

        public final int hashCode() {
            return (this.f5795a.cf + this.f5796b).hashCode();
        }

        public final boolean equals(Object obj) {
            return (obj instanceof UsageLogImpl) && ((UsageLogImpl) obj).f5795a.cf.equals(this.f5795a.cf) && ((UsageLogImpl) obj).f5796b.equals(this.f5796b);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bf.b */
    static final class UsageLogImpl {
        public int f5797a;
        private final long f5798b;

        public UsageLogImpl(long j) {
            this.f5798b = j;
            this.f5797a = 0;
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bf.c */
    interface UsageLogImpl {
        UsageLogImpl() {
        }

        void m8842a(String str, String str2) {
            UserEventReporter.m11502a(113, str, str2);
        }

        void m8841a() {
            UserEventReporter.m11510b();
        }
    }

    static {
        bf.class.getSimpleName();
    }

    private bf(Handler handler, Clock clock, UsageLogImpl usageLogImpl) {
        this.f5799a = au.m2807a();
        this.f5804f = Trace.NULL;
        this.f5802d = handler;
        this.f5801c = clock;
        this.f5800b = 0;
        this.f5803e = usageLogImpl;
    }

    public static be m8843b() {
        return new bf(new Handler(Looper.getMainLooper()), new Clock(), new UsageLogImpl());
    }

    public final synchronized void run() {
        this.f5800b = 0;
        long c = this.f5801c.m10153c();
        Iterator it = ar.m2516a(this.f5799a.keySet()).iterator();
        while (it.hasNext()) {
            UsageLogImpl usageLogImpl = (UsageLogImpl) it.next();
            UsageLogImpl usageLogImpl2 = (UsageLogImpl) this.f5799a.get(usageLogImpl);
            if (c >= usageLogImpl2.f5798b) {
                this.f5803e.m8842a(usageLogImpl.f5795a.cf, usageLogImpl.f5796b + "|c=" + usageLogImpl2.f5797a);
                this.f5799a.remove(usageLogImpl);
            }
        }
        m8844c();
    }

    public final synchronized void m8848b(UsageLog usageLog) {
        UsageLogImpl usageLogImpl = new UsageLogImpl(usageLog, "r=" + this.f5804f);
        UsageLogImpl usageLogImpl2 = (UsageLogImpl) this.f5799a.get(usageLogImpl);
        if (usageLogImpl2 == null) {
            usageLogImpl2 = new UsageLogImpl(this.f5801c.m10153c() + 10000);
            this.f5799a.put(usageLogImpl, usageLogImpl2);
        }
        r0.f5797a++;
        m8844c();
    }

    private void m8844c() {
        if (this.f5800b == 0 && !this.f5799a.isEmpty()) {
            this.f5800b = Long.MAX_VALUE;
            for (UsageLogImpl a : this.f5799a.values()) {
                this.f5800b = Math.min(this.f5800b, a.f5798b);
            }
            this.f5802d.removeCallbacks(this);
            this.f5802d.postAtTime(this, this.f5800b);
        }
    }

    public final synchronized void m8846a(UsageLog usageLog) {
        this.f5803e.m8842a(usageLog.cf, "r=" + this.f5804f);
    }

    public final synchronized void m8845a() {
        for (UsageLogImpl usageLogImpl : this.f5799a.keySet()) {
            this.f5803e.m8842a(usageLogImpl.f5795a.cf, usageLogImpl.f5796b + "|c=" + ((UsageLogImpl) this.f5799a.get(usageLogImpl)).f5797a);
        }
        this.f5799a.clear();
        this.f5803e.m8841a();
        this.f5802d.removeCallbacks(this);
    }

    public final void m8847a(String str) {
        this.f5804f = str;
    }
}
