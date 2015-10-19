package com.google.p025a.p027b;

import com.google.p025a.p026a.Supplier;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.google.a.b.h */
final class LongAddables {
    private static final Supplier<LongAddable> f1557a;

    /* renamed from: com.google.a.b.h.1 */
    static class LongAddables implements Supplier<LongAddable> {
        LongAddables() {
        }

        public /* synthetic */ Object m2256a() {
            return m2257b();
        }

        public LongAddable m2257b() {
            return new LongAdder();
        }
    }

    /* renamed from: com.google.a.b.h.2 */
    static class LongAddables implements Supplier<LongAddable> {
        LongAddables() {
        }

        public /* synthetic */ Object m2258a() {
            return m2259b();
        }

        public LongAddable m2259b() {
            return new LongAddables();
        }
    }

    /* renamed from: com.google.a.b.h.a */
    private static final class LongAddables extends AtomicLong implements LongAddable {
        private LongAddables() {
        }

        public void m2260a() {
            getAndIncrement();
        }

        public void m2261a(long j) {
            getAndAdd(j);
        }
    }

    static {
        Supplier longAddables;
        try {
            LongAdder longAdder = new LongAdder();
            longAddables = new LongAddables();
        } catch (Throwable th) {
            longAddables = new LongAddables();
        }
        f1557a = longAddables;
    }

    public static LongAddable m2262a() {
        return (LongAddable) f1557a.m1867a();
    }
}
