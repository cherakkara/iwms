package com.google.android.gms.internal;

import com.newrelic.agent.android.instrumentation.Trace;

/* renamed from: com.google.android.gms.internal.p */
public final class C0511p {

    /* renamed from: com.google.android.gms.internal.p.a */
    public static final class C0510a {
        public static C0457a<Boolean> f2424a;
        public static C0457a<Integer> f2425b;
        public static C0457a<String> f2426c;
        public static C0457a<String> f2427d;
        public static C0457a<String> f2428e;
        public static C0457a<String> f2429f;
        public static C0457a<Long> f2430g;

        static {
            f2424a = C0457a.m3880a("gms:common:stats:logging:debug", false);
            f2425b = C0457a.m3877a("gms:common:stats:logging:level", Integer.valueOf(C0512q.f2431a));
            f2426c = C0457a.m3879a("gms:common:stats:logging:ignored_calling_processes", Trace.NULL);
            f2427d = C0457a.m3879a("gms:common:stats:logging:ignored_calling_services", Trace.NULL);
            f2428e = C0457a.m3879a("gms:common:stats:logging:ignored_target_processes", Trace.NULL);
            f2429f = C0457a.m3879a("gms:common:stats:logging:ignored_target_services", "com.google.android.gms.auth.GetToken");
            f2430g = C0457a.m3878a("gms:common:stats:logging:time_out_duration", Long.valueOf(600000));
        }
    }
}
