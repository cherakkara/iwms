package com.google.p025a.p026a;

/* renamed from: com.google.a.a.q */
public abstract class Ticker {
    private static final Ticker f1387a;

    /* renamed from: com.google.a.a.q.1 */
    static class Ticker extends Ticker {
        Ticker() {
        }

        public long m1872a() {
            return Platform.m1814a();
        }
    }

    public abstract long m1871a();

    protected Ticker() {
    }

    public static Ticker m1870b() {
        return f1387a;
    }

    static {
        f1387a = new Ticker();
    }
}
