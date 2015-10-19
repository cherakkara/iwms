package com.google.p025a.p026a;

import com.newrelic.agent.android.api.v1.Defaults;

/* renamed from: com.google.a.a.h */
final class Platform {
    private static final ThreadLocal<char[]> f1359a;

    /* renamed from: com.google.a.a.h.1 */
    static class Platform extends ThreadLocal<char[]> {
        Platform() {
        }

        protected /* synthetic */ Object initialValue() {
            return m1813a();
        }

        protected char[] m1813a() {
            return new char[Defaults.RESPONSE_BODY_LIMIT];
        }
    }

    static long m1814a() {
        return System.nanoTime();
    }

    static {
        f1359a = new Platform();
    }
}
