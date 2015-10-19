package com.google.p025a.p027b;

/* renamed from: com.google.a.b.a */
public abstract class AbstractCache<K, V> {

    /* renamed from: com.google.a.b.a.b */
    public interface AbstractCache {
        void m1873a();

        void m1874a(int i);

        void m1875a(long j);

        void m1876b(int i);

        void m1877b(long j);
    }

    /* renamed from: com.google.a.b.a.a */
    public static final class AbstractCache implements AbstractCache {
        private final LongAddable f1388a;
        private final LongAddable f1389b;
        private final LongAddable f1390c;
        private final LongAddable f1391d;
        private final LongAddable f1392e;
        private final LongAddable f1393f;

        public AbstractCache() {
            this.f1388a = LongAddables.m2262a();
            this.f1389b = LongAddables.m2262a();
            this.f1390c = LongAddables.m2262a();
            this.f1391d = LongAddables.m2262a();
            this.f1392e = LongAddables.m2262a();
            this.f1393f = LongAddables.m2262a();
        }

        public void m1879a(int i) {
            this.f1388a.m2255a((long) i);
        }

        public void m1881b(int i) {
            this.f1389b.m2255a((long) i);
        }

        public void m1880a(long j) {
            this.f1390c.m2254a();
            this.f1392e.m2255a(j);
        }

        public void m1882b(long j) {
            this.f1391d.m2254a();
            this.f1392e.m2255a(j);
        }

        public void m1878a() {
            this.f1393f.m2254a();
        }
    }
}
