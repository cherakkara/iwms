package com.google.p025a.p027b;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.p025a.p026a.Equivalence;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Stopwatch;
import com.google.p025a.p026a.Ticker;
import com.google.p025a.p027b.AbstractCache.AbstractCache;
import com.google.p025a.p027b.CacheBuilder.CacheBuilder;
import com.google.p025a.p027b.CacheLoader.CacheLoader;
import com.google.p025a.p028c.AbstractSequentialIterator;
import com.google.p025a.p028c.aq;
import com.google.p025a.p032g.Ints;
import com.google.p025a.p034i.p035a.ExecutionError;
import com.google.p025a.p034i.p035a.Futures;
import com.google.p025a.p034i.p035a.ListenableFuture;
import com.google.p025a.p034i.p035a.ListeningExecutorService;
import com.google.p025a.p034i.p035a.MoreExecutors;
import com.google.p025a.p034i.p035a.SettableFuture;
import com.google.p025a.p034i.p035a.UncheckedExecutionException;
import com.google.p025a.p034i.p035a.Uninterruptibles;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: com.google.a.b.f */
class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final Logger f1531a;
    static final ListeningExecutorService f1532b;
    static final LocalCache<Object, Object> f1533v;
    static final Queue<? extends Object> f1534w;
    final int f1535c;
    final int f1536d;
    final LocalCache<K, V>[] f1537e;
    final int f1538f;
    final Equivalence<Object> f1539g;
    final Equivalence<Object> f1540h;
    final LocalCache f1541i;
    final LocalCache f1542j;
    final long f1543k;
    final Weigher<K, V> f1544l;
    final long f1545m;
    final long f1546n;
    final long f1547o;
    final Queue<RemovalNotification<K, V>> f1548p;
    final RemovalListener<K, V> f1549q;
    final Ticker f1550r;
    final LocalCache f1551s;
    final AbstractCache f1552t;
    @Nullable
    final CacheLoader<? super K, V> f1553u;
    Set<K> f1554x;
    Collection<V> f1555y;
    Set<Entry<K, V>> f1556z;

    /* renamed from: com.google.a.b.f.x */
    interface LocalCache<K, V> {
        int m1916a();

        LocalCache<K, V> m1917a(ReferenceQueue<V> referenceQueue, @Nullable V v, LocalCache<K, V> localCache);

        void m1918a(@Nullable V v);

        @Nullable
        LocalCache<K, V> m1919b();

        boolean m1920c();

        boolean m1921d();

        V m1922e() throws ExecutionException;

        @Nullable
        V get();
    }

    /* renamed from: com.google.a.b.f.1 */
    static class LocalCache implements LocalCache<Object, Object> {
        LocalCache() {
        }

        public Object get() {
            return null;
        }

        public int m1923a() {
            return 0;
        }

        public LocalCache<Object, Object> m1926b() {
            return null;
        }

        public LocalCache<Object, Object> m1924a(ReferenceQueue<Object> referenceQueue, @Nullable Object obj, LocalCache<Object, Object> localCache) {
            return this;
        }

        public boolean m1927c() {
            return false;
        }

        public boolean m1928d() {
            return false;
        }

        public Object m1929e() {
            return null;
        }

        public void m1925a(Object obj) {
        }
    }

    /* renamed from: com.google.a.b.f.2 */
    static class LocalCache extends AbstractQueue<Object> {
        LocalCache() {
        }

        public boolean offer(Object obj) {
            return true;
        }

        public Object peek() {
            return null;
        }

        public Object poll() {
            return null;
        }

        public int size() {
            return 0;
        }

        public Iterator<Object> iterator() {
            return aq.m2496a();
        }
    }

    /* renamed from: com.google.a.b.f.a */
    abstract class LocalCache<T> extends AbstractSet<T> {
        final ConcurrentMap<?, ?> f1425a;
        final /* synthetic */ LocalCache f1426b;

        LocalCache(LocalCache localCache, ConcurrentMap<?, ?> concurrentMap) {
            this.f1426b = localCache;
            this.f1425a = concurrentMap;
        }

        public int size() {
            return this.f1425a.size();
        }

        public boolean isEmpty() {
            return this.f1425a.isEmpty();
        }

        public void clear() {
            this.f1425a.clear();
        }
    }

    /* renamed from: com.google.a.b.f.n */
    interface LocalCache<K, V> {
        LocalCache<K, V> m1930a();

        void m1931a(long j);

        void m1932a(LocalCache<K, V> localCache);

        void m1933a(LocalCache<K, V> localCache);

        @Nullable
        LocalCache<K, V> m1934b();

        void m1935b(long j);

        void m1936b(LocalCache<K, V> localCache);

        int m1937c();

        void m1938c(LocalCache<K, V> localCache);

        @Nullable
        K m1939d();

        void m1940d(LocalCache<K, V> localCache);

        long m1941e();

        LocalCache<K, V> m1942f();

        LocalCache<K, V> m1943g();

        long m1944h();

        LocalCache<K, V> m1945i();

        LocalCache<K, V> m1946j();
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.ab */
    static class ab<K, V> extends WeakReference<K> implements LocalCache<K, V> {
        final int f1427g;
        final LocalCache<K, V> f1428h;
        volatile LocalCache<K, V> f1429i;

        ab(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable LocalCache<K, V> localCache) {
            super(k, referenceQueue);
            this.f1429i = LocalCache.m2225o();
            this.f1427g = i;
            this.f1428h = localCache;
        }

        public K m1956d() {
            return get();
        }

        public long m1958e() {
            throw new UnsupportedOperationException();
        }

        public void m1948a(long j) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m1959f() {
            throw new UnsupportedOperationException();
        }

        public void m1949a(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m1960g() {
            throw new UnsupportedOperationException();
        }

        public void m1953b(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public long m1961h() {
            throw new UnsupportedOperationException();
        }

        public void m1952b(long j) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m1962i() {
            throw new UnsupportedOperationException();
        }

        public void m1955c(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m1963j() {
            throw new UnsupportedOperationException();
        }

        public void m1957d(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m1947a() {
            return this.f1429i;
        }

        public void m1950a(LocalCache<K, V> localCache) {
            this.f1429i = localCache;
        }

        public int m1954c() {
            return this.f1427g;
        }

        public LocalCache<K, V> m1951b() {
            return this.f1428h;
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.aa */
    static final class aa<K, V> extends ab<K, V> implements LocalCache<K, V> {
        volatile long f1430a;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1431b;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1432c;
        volatile long f1433d;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1434e;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1435f;

        aa(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable LocalCache<K, V> localCache) {
            super(referenceQueue, k, i, localCache);
            this.f1430a = Long.MAX_VALUE;
            this.f1431b = LocalCache.m2226p();
            this.f1432c = LocalCache.m2226p();
            this.f1433d = Long.MAX_VALUE;
            this.f1434e = LocalCache.m2226p();
            this.f1435f = LocalCache.m2226p();
        }

        public long m1970e() {
            return this.f1430a;
        }

        public void m1964a(long j) {
            this.f1430a = j;
        }

        public LocalCache<K, V> m1971f() {
            return this.f1431b;
        }

        public void m1965a(LocalCache<K, V> localCache) {
            this.f1431b = localCache;
        }

        public LocalCache<K, V> m1972g() {
            return this.f1432c;
        }

        public void m1967b(LocalCache<K, V> localCache) {
            this.f1432c = localCache;
        }

        public long m1973h() {
            return this.f1433d;
        }

        public void m1966b(long j) {
            this.f1433d = j;
        }

        public LocalCache<K, V> m1974i() {
            return this.f1434e;
        }

        public void m1968c(LocalCache<K, V> localCache) {
            this.f1434e = localCache;
        }

        public LocalCache<K, V> m1975j() {
            return this.f1435f;
        }

        public void m1969d(LocalCache<K, V> localCache) {
            this.f1435f = localCache;
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.ac */
    static class ac<K, V> extends WeakReference<V> implements LocalCache<K, V> {
        final LocalCache<K, V> f1436a;

        ac(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache) {
            super(v, referenceQueue);
            this.f1436a = localCache;
        }

        public int m1976a() {
            return 1;
        }

        public LocalCache<K, V> m1979b() {
            return this.f1436a;
        }

        public void m1978a(V v) {
        }

        public LocalCache<K, V> m1977a(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache) {
            return new ac(referenceQueue, v, localCache);
        }

        public boolean m1980c() {
            return false;
        }

        public boolean m1981d() {
            return true;
        }

        public V m1982e() {
            return get();
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.ad */
    static final class ad<K, V> extends ab<K, V> implements LocalCache<K, V> {
        volatile long f1437a;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1438b;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1439c;

        ad(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable LocalCache<K, V> localCache) {
            super(referenceQueue, k, i, localCache);
            this.f1437a = Long.MAX_VALUE;
            this.f1438b = LocalCache.m2226p();
            this.f1439c = LocalCache.m2226p();
        }

        public long m1986h() {
            return this.f1437a;
        }

        public void m1983b(long j) {
            this.f1437a = j;
        }

        public LocalCache<K, V> m1987i() {
            return this.f1438b;
        }

        public void m1984c(LocalCache<K, V> localCache) {
            this.f1438b = localCache;
        }

        public LocalCache<K, V> m1988j() {
            return this.f1439c;
        }

        public void m1985d(LocalCache<K, V> localCache) {
            this.f1439c = localCache;
        }
    }

    /* renamed from: com.google.a.b.f.p */
    static class LocalCache<K, V> extends SoftReference<V> implements LocalCache<K, V> {
        final LocalCache<K, V> f1440a;

        LocalCache(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache) {
            super(v, referenceQueue);
            this.f1440a = localCache;
        }

        public int m1989a() {
            return 1;
        }

        public LocalCache<K, V> m1992b() {
            return this.f1440a;
        }

        public void m1991a(V v) {
        }

        public LocalCache<K, V> m1990a(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache) {
            return new LocalCache(referenceQueue, v, localCache);
        }

        public boolean m1993c() {
            return false;
        }

        public boolean m1994d() {
            return true;
        }

        public V m1995e() {
            return get();
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.ae */
    static final class ae<K, V> extends LocalCache<K, V> {
        final int f1441b;

        ae(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache, int i) {
            super(referenceQueue, v, localCache);
            this.f1441b = i;
        }

        public int m1996a() {
            return this.f1441b;
        }

        public LocalCache<K, V> m1997a(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache) {
            return new ae(referenceQueue, v, localCache, this.f1441b);
        }
    }

    /* renamed from: com.google.a.b.f.u */
    static class LocalCache<K, V> implements LocalCache<K, V> {
        final V f1442a;

        LocalCache(V v) {
            this.f1442a = v;
        }

        public V get() {
            return this.f1442a;
        }

        public int m1998a() {
            return 1;
        }

        public LocalCache<K, V> m2001b() {
            return null;
        }

        public LocalCache<K, V> m1999a(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache) {
            return this;
        }

        public boolean m2002c() {
            return false;
        }

        public boolean m2003d() {
            return true;
        }

        public V m2004e() {
            return get();
        }

        public void m2000a(V v) {
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.af */
    static final class af<K, V> extends LocalCache<K, V> {
        final int f1443b;

        af(V v, int i) {
            super(v);
            this.f1443b = i;
        }

        public int m2005a() {
            return this.f1443b;
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.ag */
    static final class ag<K, V> extends ac<K, V> {
        final int f1444b;

        ag(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache, int i) {
            super(referenceQueue, v, localCache);
            this.f1444b = i;
        }

        public int m2006a() {
            return this.f1444b;
        }

        public LocalCache<K, V> m2007a(ReferenceQueue<V> referenceQueue, V v, LocalCache<K, V> localCache) {
            return new ag(referenceQueue, v, localCache, this.f1444b);
        }
    }

    /* renamed from: com.google.a.b.f.b */
    static abstract class LocalCache<K, V> implements LocalCache<K, V> {
        LocalCache() {
        }

        public LocalCache<K, V> m2008a() {
            throw new UnsupportedOperationException();
        }

        public void m2011a(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2012b() {
            throw new UnsupportedOperationException();
        }

        public int m2015c() {
            throw new UnsupportedOperationException();
        }

        public K m2017d() {
            throw new UnsupportedOperationException();
        }

        public long m2019e() {
            throw new UnsupportedOperationException();
        }

        public void m2009a(long j) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2020f() {
            throw new UnsupportedOperationException();
        }

        public void m2010a(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2021g() {
            throw new UnsupportedOperationException();
        }

        public void m2014b(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public long m2022h() {
            throw new UnsupportedOperationException();
        }

        public void m2013b(long j) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2023i() {
            throw new UnsupportedOperationException();
        }

        public void m2016c(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2024j() {
            throw new UnsupportedOperationException();
        }

        public void m2018d(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.ah */
    static final class ah<K, V> extends AbstractQueue<LocalCache<K, V>> {
        final LocalCache<K, V> f1450a;

        /* renamed from: com.google.a.b.f.ah.1 */
        class LocalCache extends LocalCache<K, V> {
            LocalCache<K, V> f1445a;
            LocalCache<K, V> f1446b;
            final /* synthetic */ ah f1447c;

            LocalCache(ah ahVar) {
                this.f1447c = ahVar;
                this.f1445a = this;
                this.f1446b = this;
            }

            public long m2028h() {
                return Long.MAX_VALUE;
            }

            public void m2025b(long j) {
            }

            public LocalCache<K, V> m2029i() {
                return this.f1445a;
            }

            public void m2026c(LocalCache<K, V> localCache) {
                this.f1445a = localCache;
            }

            public LocalCache<K, V> m2030j() {
                return this.f1446b;
            }

            public void m2027d(LocalCache<K, V> localCache) {
                this.f1446b = localCache;
            }
        }

        /* renamed from: com.google.a.b.f.ah.2 */
        class LocalCache extends AbstractSequentialIterator<LocalCache<K, V>> {
            final /* synthetic */ ah f1449a;

            LocalCache(ah ahVar, LocalCache localCache) {
                this.f1449a = ahVar;
                super(localCache);
            }

            protected LocalCache<K, V> m2032a(LocalCache<K, V> localCache) {
                LocalCache<K, V> i = localCache.m1945i();
                return i == this.f1449a.f1450a ? null : i;
            }
        }

        ah() {
            this.f1450a = new LocalCache(this);
        }

        public /* synthetic */ boolean offer(Object obj) {
            return m2035a((LocalCache) obj);
        }

        public /* synthetic */ Object peek() {
            return m2034a();
        }

        public /* synthetic */ Object poll() {
            return m2036b();
        }

        public boolean m2035a(LocalCache<K, V> localCache) {
            LocalCache.m2223b(localCache.m1946j(), localCache.m1945i());
            LocalCache.m2223b(this.f1450a.m1946j(), (LocalCache) localCache);
            LocalCache.m2223b((LocalCache) localCache, this.f1450a);
            return true;
        }

        public LocalCache<K, V> m2034a() {
            LocalCache<K, V> i = this.f1450a.m1945i();
            return i == this.f1450a ? null : i;
        }

        public LocalCache<K, V> m2036b() {
            LocalCache<K, V> i = this.f1450a.m1945i();
            if (i == this.f1450a) {
                return null;
            }
            remove(i);
            return i;
        }

        public boolean remove(Object obj) {
            LocalCache localCache = (LocalCache) obj;
            LocalCache j = localCache.m1946j();
            LocalCache i = localCache.m1945i();
            LocalCache.m2223b(j, i);
            LocalCache.m2224c(localCache);
            return i != LocalCache.INSTANCE;
        }

        public boolean contains(Object obj) {
            return ((LocalCache) obj).m1945i() != LocalCache.INSTANCE;
        }

        public boolean isEmpty() {
            return this.f1450a.m1945i() == this.f1450a;
        }

        public int size() {
            int i = 0;
            for (LocalCache i2 = this.f1450a.m1945i(); i2 != this.f1450a; i2 = i2.m1945i()) {
                i++;
            }
            return i;
        }

        public void clear() {
            LocalCache i = this.f1450a.m1945i();
            while (i != this.f1450a) {
                LocalCache i2 = i.m1945i();
                LocalCache.m2224c(i);
                i = i2;
            }
            this.f1450a.m1938c(this.f1450a);
            this.f1450a.m1940d(this.f1450a);
        }

        public Iterator<LocalCache<K, V>> iterator() {
            return new LocalCache(this, m2034a());
        }
    }

    /* compiled from: LocalCache */
    /* renamed from: com.google.a.b.f.ai */
    final class ai implements Entry<K, V> {
        final K f1451a;
        V f1452b;
        final /* synthetic */ LocalCache f1453c;

        ai(LocalCache localCache, K k, V v) {
            this.f1453c = localCache;
            this.f1451a = k;
            this.f1452b = v;
        }

        public K getKey() {
            return this.f1451a;
        }

        public V getValue() {
            return this.f1452b;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f1451a.equals(entry.getKey()) && this.f1452b.equals(entry.getValue())) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f1451a.hashCode() ^ this.f1452b.hashCode();
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* renamed from: com.google.a.b.f.c */
    static final class LocalCache<K, V> extends AbstractQueue<LocalCache<K, V>> {
        final LocalCache<K, V> f1458a;

        /* renamed from: com.google.a.b.f.c.1 */
        class LocalCache extends LocalCache<K, V> {
            LocalCache<K, V> f1454a;
            LocalCache<K, V> f1455b;
            final /* synthetic */ LocalCache f1456c;

            LocalCache(LocalCache localCache) {
                this.f1456c = localCache;
                this.f1454a = this;
                this.f1455b = this;
            }

            public long m2040e() {
                return Long.MAX_VALUE;
            }

            public void m2037a(long j) {
            }

            public LocalCache<K, V> m2041f() {
                return this.f1454a;
            }

            public void m2038a(LocalCache<K, V> localCache) {
                this.f1454a = localCache;
            }

            public LocalCache<K, V> m2042g() {
                return this.f1455b;
            }

            public void m2039b(LocalCache<K, V> localCache) {
                this.f1455b = localCache;
            }
        }

        /* renamed from: com.google.a.b.f.c.2 */
        class LocalCache extends AbstractSequentialIterator<LocalCache<K, V>> {
            final /* synthetic */ LocalCache f1457a;

            LocalCache(LocalCache localCache, LocalCache localCache2) {
                this.f1457a = localCache;
                super(localCache2);
            }

            protected LocalCache<K, V> m2043a(LocalCache<K, V> localCache) {
                LocalCache<K, V> f = localCache.m1942f();
                return f == this.f1457a.f1458a ? null : f;
            }
        }

        LocalCache() {
            this.f1458a = new LocalCache(this);
        }

        public /* synthetic */ boolean offer(Object obj) {
            return m2046a((LocalCache) obj);
        }

        public /* synthetic */ Object peek() {
            return m2045a();
        }

        public /* synthetic */ Object poll() {
            return m2047b();
        }

        public boolean m2046a(LocalCache<K, V> localCache) {
            LocalCache.m2221a(localCache.m1943g(), localCache.m1942f());
            LocalCache.m2221a(this.f1458a.m1943g(), (LocalCache) localCache);
            LocalCache.m2221a((LocalCache) localCache, this.f1458a);
            return true;
        }

        public LocalCache<K, V> m2045a() {
            LocalCache<K, V> f = this.f1458a.m1942f();
            return f == this.f1458a ? null : f;
        }

        public LocalCache<K, V> m2047b() {
            LocalCache<K, V> f = this.f1458a.m1942f();
            if (f == this.f1458a) {
                return null;
            }
            remove(f);
            return f;
        }

        public boolean remove(Object obj) {
            LocalCache localCache = (LocalCache) obj;
            LocalCache g = localCache.m1943g();
            LocalCache f = localCache.m1942f();
            LocalCache.m2221a(g, f);
            LocalCache.m2222b(localCache);
            return f != LocalCache.INSTANCE;
        }

        public boolean contains(Object obj) {
            return ((LocalCache) obj).m1942f() != LocalCache.INSTANCE;
        }

        public boolean isEmpty() {
            return this.f1458a.m1942f() == this.f1458a;
        }

        public int size() {
            int i = 0;
            for (LocalCache f = this.f1458a.m1942f(); f != this.f1458a; f = f.m1942f()) {
                i++;
            }
            return i;
        }

        public void clear() {
            LocalCache f = this.f1458a.m1942f();
            while (f != this.f1458a) {
                LocalCache f2 = f.m1942f();
                LocalCache.m2222b(f);
                f = f2;
            }
            this.f1458a.m1932a(this.f1458a);
            this.f1458a.m1936b(this.f1458a);
        }

        public Iterator<LocalCache<K, V>> iterator() {
            return new LocalCache(this, m2045a());
        }
    }

    /* renamed from: com.google.a.b.f.d */
    enum LocalCache {
        STRONG {
            <K, V> LocalCache<K, V> m2053a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new LocalCache(k, i, localCache2);
            }
        },
        STRONG_ACCESS {
            <K, V> LocalCache<K, V> m2055a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new LocalCache(k, i, localCache2);
            }

            <K, V> LocalCache<K, V> m2054a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, LocalCache<K, V> localCache3) {
                LocalCache<K, V> a = super.m2049a((LocalCache) localCache, (LocalCache) localCache2, (LocalCache) localCache3);
                m2051a(localCache2, a);
                return a;
            }
        },
        STRONG_WRITE {
            <K, V> LocalCache<K, V> m2057a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new LocalCache(k, i, localCache2);
            }

            <K, V> LocalCache<K, V> m2056a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, LocalCache<K, V> localCache3) {
                LocalCache<K, V> a = super.m2049a((LocalCache) localCache, (LocalCache) localCache2, (LocalCache) localCache3);
                m2052b(localCache2, a);
                return a;
            }
        },
        STRONG_ACCESS_WRITE {
            <K, V> LocalCache<K, V> m2059a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new LocalCache(k, i, localCache2);
            }

            <K, V> LocalCache<K, V> m2058a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, LocalCache<K, V> localCache3) {
                LocalCache<K, V> a = super.m2049a((LocalCache) localCache, (LocalCache) localCache2, (LocalCache) localCache3);
                m2051a(localCache2, a);
                m2052b(localCache2, a);
                return a;
            }
        },
        WEAK {
            <K, V> LocalCache<K, V> m2060a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new ab(localCache.f1499h, k, i, localCache2);
            }
        },
        WEAK_ACCESS {
            <K, V> LocalCache<K, V> m2062a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new LocalCache(localCache.f1499h, k, i, localCache2);
            }

            <K, V> LocalCache<K, V> m2061a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, LocalCache<K, V> localCache3) {
                LocalCache<K, V> a = super.m2049a((LocalCache) localCache, (LocalCache) localCache2, (LocalCache) localCache3);
                m2051a(localCache2, a);
                return a;
            }
        },
        WEAK_WRITE {
            <K, V> LocalCache<K, V> m2064a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new ad(localCache.f1499h, k, i, localCache2);
            }

            <K, V> LocalCache<K, V> m2063a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, LocalCache<K, V> localCache3) {
                LocalCache<K, V> a = super.m2049a((LocalCache) localCache, (LocalCache) localCache2, (LocalCache) localCache3);
                m2052b(localCache2, a);
                return a;
            }
        },
        WEAK_ACCESS_WRITE {
            <K, V> LocalCache<K, V> m2066a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2) {
                return new aa(localCache.f1499h, k, i, localCache2);
            }

            <K, V> LocalCache<K, V> m2065a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, LocalCache<K, V> localCache3) {
                LocalCache<K, V> a = super.m2049a((LocalCache) localCache, (LocalCache) localCache2, (LocalCache) localCache3);
                m2051a(localCache2, a);
                m2052b(localCache2, a);
                return a;
            }
        };
        
        static final LocalCache[] f1467i;

        abstract <K, V> LocalCache<K, V> m2050a(LocalCache<K, V> localCache, K k, int i, @Nullable LocalCache<K, V> localCache2);

        static {
            f1467i = new LocalCache[]{STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};
        }

        static LocalCache m2048a(LocalCache localCache, boolean z, boolean z2) {
            int i;
            int i2 = 0;
            if (localCache == LocalCache.WEAK) {
                i = 4;
            } else {
                i = 0;
            }
            int i3 = (z ? 1 : 0) | i;
            if (z2) {
                i2 = 2;
            }
            return f1467i[i2 | i3];
        }

        @GuardedBy("Segment.this")
        <K, V> LocalCache<K, V> m2049a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, LocalCache<K, V> localCache3) {
            return m2050a(localCache, localCache2.m1939d(), localCache2.m1937c(), localCache3);
        }

        @GuardedBy("Segment.this")
        <K, V> void m2051a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2) {
            localCache2.m1931a(localCache.m1941e());
            LocalCache.m2221a(localCache.m1943g(), (LocalCache) localCache2);
            LocalCache.m2221a((LocalCache) localCache2, localCache.m1942f());
            LocalCache.m2222b((LocalCache) localCache);
        }

        @GuardedBy("Segment.this")
        <K, V> void m2052b(LocalCache<K, V> localCache, LocalCache<K, V> localCache2) {
            localCache2.m1935b(localCache.m1944h());
            LocalCache.m2223b(localCache.m1946j(), (LocalCache) localCache2);
            LocalCache.m2223b((LocalCache) localCache2, localCache.m1945i());
            LocalCache.m2224c((LocalCache) localCache);
        }
    }

    /* renamed from: com.google.a.b.f.g */
    abstract class LocalCache<T> implements Iterator<T> {
        int f1469b;
        int f1470c;
        LocalCache<K, V> f1471d;
        AtomicReferenceArray<LocalCache<K, V>> f1472e;
        LocalCache<K, V> f1473f;
        ai f1474g;
        ai f1475h;
        final /* synthetic */ LocalCache f1476i;

        LocalCache(LocalCache localCache) {
            this.f1476i = localCache;
            this.f1469b = localCache.f1537e.length - 1;
            this.f1470c = -1;
            m2068b();
        }

        final void m2068b() {
            this.f1474g = null;
            if (!m2069c() && !m2070d()) {
                while (this.f1469b >= 0) {
                    LocalCache[] localCacheArr = this.f1476i.f1537e;
                    int i = this.f1469b;
                    this.f1469b = i - 1;
                    this.f1471d = localCacheArr[i];
                    if (this.f1471d.f1493b != 0) {
                        this.f1472e = this.f1471d.f1497f;
                        this.f1470c = this.f1472e.length() - 1;
                        if (m2070d()) {
                            return;
                        }
                    }
                }
            }
        }

        boolean m2069c() {
            if (this.f1473f != null) {
                this.f1473f = this.f1473f.m1934b();
                while (this.f1473f != null) {
                    if (m2067a(this.f1473f)) {
                        return true;
                    }
                    this.f1473f = this.f1473f.m1934b();
                }
            }
            return false;
        }

        boolean m2070d() {
            while (this.f1470c >= 0) {
                AtomicReferenceArray atomicReferenceArray = this.f1472e;
                int i = this.f1470c;
                this.f1470c = i - 1;
                LocalCache localCache = (LocalCache) atomicReferenceArray.get(i);
                this.f1473f = localCache;
                if (localCache != null && (m2067a(this.f1473f) || m2069c())) {
                    return true;
                }
            }
            return false;
        }

        boolean m2067a(LocalCache<K, V> localCache) {
            try {
                long a = this.f1476i.f1550r.m1871a();
                Object d = localCache.m1939d();
                Object a2 = this.f1476i.m2230a((LocalCache) localCache, a);
                if (a2 != null) {
                    this.f1474g = new ai(this.f1476i, d, a2);
                    return true;
                }
                this.f1471d.m2161m();
                return false;
            } finally {
                this.f1471d.m2161m();
            }
        }

        public boolean hasNext() {
            return this.f1474g != null;
        }

        ai m2071e() {
            if (this.f1474g == null) {
                throw new NoSuchElementException();
            }
            this.f1475h = this.f1474g;
            m2068b();
            return this.f1475h;
        }

        public void remove() {
            Preconditions.m1828b(this.f1475h != null);
            this.f1476i.remove(this.f1475h.getKey());
            this.f1475h = null;
        }
    }

    /* renamed from: com.google.a.b.f.e */
    final class LocalCache extends LocalCache<Entry<K, V>> {
        final /* synthetic */ LocalCache f1477a;

        LocalCache(LocalCache localCache) {
            this.f1477a = localCache;
            super(localCache);
        }

        public /* synthetic */ Object next() {
            return m2072a();
        }

        public Entry<K, V> m2072a() {
            return m2071e();
        }
    }

    /* renamed from: com.google.a.b.f.f */
    final class LocalCache extends LocalCache<Entry<K, V>> {
        final /* synthetic */ LocalCache f1478c;

        LocalCache(LocalCache localCache, ConcurrentMap<?, ?> concurrentMap) {
            this.f1478c = localCache;
            super(localCache, concurrentMap);
        }

        public Iterator<Entry<K, V>> iterator() {
            return new LocalCache(this.f1478c);
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            if (key == null) {
                return false;
            }
            key = this.f1478c.get(key);
            if (key == null || !this.f1478c.f1540h.m1771a(entry.getValue(), key)) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            if (key == null || !this.f1478c.remove(key, entry.getValue())) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.google.a.b.f.h */
    final class LocalCache extends LocalCache<K> {
        final /* synthetic */ LocalCache f1479a;

        LocalCache(LocalCache localCache) {
            this.f1479a = localCache;
            super(localCache);
        }

        public K next() {
            return m2071e().getKey();
        }
    }

    /* renamed from: com.google.a.b.f.i */
    final class LocalCache extends LocalCache<K> {
        final /* synthetic */ LocalCache f1480c;

        LocalCache(LocalCache localCache, ConcurrentMap<?, ?> concurrentMap) {
            this.f1480c = localCache;
            super(localCache, concurrentMap);
        }

        public Iterator<K> iterator() {
            return new LocalCache(this.f1480c);
        }

        public boolean contains(Object obj) {
            return this.a.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return this.a.remove(obj) != null;
        }
    }

    /* renamed from: com.google.a.b.f.j */
    static class LocalCache<K, V> implements LocalCache<K, V> {
        volatile LocalCache<K, V> f1481a;
        final SettableFuture<V> f1482b;
        final Stopwatch f1483c;

        public LocalCache() {
            this(LocalCache.m2225o());
        }

        public LocalCache(LocalCache<K, V> localCache) {
            this.f1482b = SettableFuture.m3143b();
            this.f1483c = new Stopwatch();
            this.f1481a = localCache;
        }

        public boolean m2082c() {
            return true;
        }

        public boolean m2083d() {
            return this.f1481a.m1921d();
        }

        public int m2075a() {
            return this.f1481a.m1916a();
        }

        public boolean m2081b(@Nullable V v) {
            return this.f1482b.m3144a((Object) v);
        }

        public boolean m2079a(Throwable th) {
            return LocalCache.m2073a(this.f1482b, th);
        }

        private static boolean m2073a(SettableFuture<?> settableFuture, Throwable th) {
            try {
                return settableFuture.m3145a(th);
            } catch (Error e) {
                return false;
            }
        }

        private ListenableFuture<V> m2074b(Throwable th) {
            SettableFuture b = SettableFuture.m3143b();
            LocalCache.m2073a(b, th);
            return b;
        }

        public void m2078a(@Nullable V v) {
            if (v != null) {
                m2081b((Object) v);
            } else {
                this.f1481a = LocalCache.m2225o();
            }
        }

        public ListenableFuture<V> m2077a(K k, CacheLoader<? super K, V> cacheLoader) {
            this.f1483c.m1863a();
            Object obj = this.f1481a.get();
            if (obj == null) {
                try {
                    obj = cacheLoader.m1915a(k);
                    return m2081b(obj) ? this.f1482b : Futures.m3134a(obj);
                } catch (Throwable th) {
                    if (th instanceof InterruptedException) {
                        Thread.currentThread().interrupt();
                    }
                    return m2079a(th) ? this.f1482b : m2074b(th);
                }
            } else {
                ListenableFuture<V> a = cacheLoader.m1914a(k, obj);
                if (a == null) {
                    return Futures.m3134a(null);
                }
                return a;
            }
        }

        public long m2085f() {
            return this.f1483c.m1862a(TimeUnit.NANOSECONDS);
        }

        public V m2084e() throws ExecutionException {
            return Uninterruptibles.m3146a(this.f1482b);
        }

        public V get() {
            return this.f1481a.get();
        }

        public LocalCache<K, V> m2086g() {
            return this.f1481a;
        }

        public LocalCache<K, V> m2080b() {
            return null;
        }

        public LocalCache<K, V> m2076a(ReferenceQueue<V> referenceQueue, @Nullable V v, LocalCache<K, V> localCache) {
            return this;
        }
    }

    /* renamed from: com.google.a.b.f.l */
    static class LocalCache<K, V> implements Serializable {
        final LocalCache<K, V> f1484a;

        private LocalCache(LocalCache<K, V> localCache) {
            this.f1484a = localCache;
        }
    }

    /* renamed from: com.google.a.b.f.k */
    static class LocalCache<K, V> extends LocalCache<K, V> implements LoadingCache<K, V> {
        LocalCache(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
            super(null);
        }

        public V m2088b(K k) throws ExecutionException {
            return this.a.m2236b((Object) k);
        }

        public V m2089c(K k) {
            try {
                return m2088b(k);
            } catch (ExecutionException e) {
                throw new UncheckedExecutionException(e.getCause());
            }
        }

        public final V m2087a(K k) {
            return m2089c(k);
        }
    }

    /* renamed from: com.google.a.b.f.m */
    private enum LocalCache implements LocalCache<Object, Object> {
        INSTANCE;

        public LocalCache<Object, Object> m2090a() {
            return null;
        }

        public void m2093a(LocalCache<Object, Object> localCache) {
        }

        public LocalCache<Object, Object> m2094b() {
            return null;
        }

        public int m2097c() {
            return 0;
        }

        public Object m2099d() {
            return null;
        }

        public long m2101e() {
            return 0;
        }

        public void m2091a(long j) {
        }

        public LocalCache<Object, Object> m2102f() {
            return this;
        }

        public void m2092a(LocalCache<Object, Object> localCache) {
        }

        public LocalCache<Object, Object> m2103g() {
            return this;
        }

        public void m2096b(LocalCache<Object, Object> localCache) {
        }

        public long m2104h() {
            return 0;
        }

        public void m2095b(long j) {
        }

        public LocalCache<Object, Object> m2105i() {
            return this;
        }

        public void m2098c(LocalCache<Object, Object> localCache) {
        }

        public LocalCache<Object, Object> m2106j() {
            return this;
        }

        public void m2100d(LocalCache<Object, Object> localCache) {
        }
    }

    /* renamed from: com.google.a.b.f.o */
    static class LocalCache<K, V> extends ReentrantLock {
        final LocalCache<K, V> f1492a;
        volatile int f1493b;
        @GuardedBy("Segment.this")
        int f1494c;
        int f1495d;
        int f1496e;
        volatile AtomicReferenceArray<LocalCache<K, V>> f1497f;
        final long f1498g;
        final ReferenceQueue<K> f1499h;
        final ReferenceQueue<V> f1500i;
        final Queue<LocalCache<K, V>> f1501j;
        final AtomicInteger f1502k;
        @GuardedBy("Segment.this")
        final Queue<LocalCache<K, V>> f1503l;
        @GuardedBy("Segment.this")
        final Queue<LocalCache<K, V>> f1504m;
        final AbstractCache f1505n;

        /* renamed from: com.google.a.b.f.o.1 */
        class LocalCache implements Runnable {
            final /* synthetic */ Object f1487a;
            final /* synthetic */ int f1488b;
            final /* synthetic */ LocalCache f1489c;
            final /* synthetic */ ListenableFuture f1490d;
            final /* synthetic */ LocalCache f1491e;

            LocalCache(LocalCache localCache, Object obj, int i, LocalCache localCache2, ListenableFuture listenableFuture) {
                this.f1491e = localCache;
                this.f1487a = obj;
                this.f1488b = i;
                this.f1489c = localCache2;
                this.f1490d = listenableFuture;
            }

            public void run() {
                try {
                    this.f1489c.m2081b(this.f1491e.m2118a(this.f1487a, this.f1488b, this.f1489c, this.f1490d));
                } catch (Throwable th) {
                    LocalCache.f1531a.log(Level.WARNING, "Exception thrown during refresh", th);
                    this.f1489c.m2079a(th);
                }
            }
        }

        LocalCache(LocalCache<K, V> localCache, int i, long j, AbstractCache abstractCache) {
            ReferenceQueue referenceQueue = null;
            this.f1502k = new AtomicInteger();
            this.f1492a = localCache;
            this.f1498g = j;
            this.f1505n = (AbstractCache) Preconditions.m1817a((Object) abstractCache);
            m2130a(m2121a(i));
            this.f1499h = localCache.m2250m() ? new ReferenceQueue() : null;
            if (localCache.m2251n()) {
                referenceQueue = new ReferenceQueue();
            }
            this.f1500i = referenceQueue;
            this.f1501j = localCache.m2243f() ? new ConcurrentLinkedQueue() : LocalCache.m2227q();
            this.f1503l = localCache.m2244g() ? new ah() : LocalCache.m2227q();
            this.f1504m = localCache.m2243f() ? new LocalCache() : LocalCache.m2227q();
        }

        AtomicReferenceArray<LocalCache<K, V>> m2121a(int i) {
            return new AtomicReferenceArray(i);
        }

        void m2130a(AtomicReferenceArray<LocalCache<K, V>> atomicReferenceArray) {
            this.f1496e = (atomicReferenceArray.length() * 3) / 4;
            if (!this.f1492a.m2237b() && ((long) this.f1496e) == this.f1498g) {
                this.f1496e++;
            }
            this.f1497f = atomicReferenceArray;
        }

        @GuardedBy("Segment.this")
        LocalCache<K, V> m2112a(K k, int i, @Nullable LocalCache<K, V> localCache) {
            return this.f1492a.f1551s.m2050a(this, Preconditions.m1817a((Object) k), i, localCache);
        }

        @GuardedBy("Segment.this")
        LocalCache<K, V> m2108a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2) {
            if (localCache.m1939d() == null) {
                return null;
            }
            LocalCache a = localCache.m1930a();
            Object obj = a.get();
            if (obj == null && a.m1921d()) {
                return null;
            }
            LocalCache<K, V> a2 = this.f1492a.f1551s.m2049a(this, (LocalCache) localCache, (LocalCache) localCache2);
            a2.m1933a(a.m1917a(this.f1500i, obj, a2));
            return a2;
        }

        @GuardedBy("Segment.this")
        void m2128a(LocalCache<K, V> localCache, K k, V v, long j) {
            LocalCache a = localCache.m1930a();
            int a2 = this.f1492a.f1544l.m1893a(k, v);
            Preconditions.m1829b(a2 >= 0, (Object) "Weights must be non-negative");
            localCache.m1933a(this.f1492a.f1542j.m2166a(this, localCache, v, a2));
            m2125a((LocalCache) localCache, a2, j);
            a.m1918a(v);
        }

        V m2115a(K k, int i, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            Preconditions.m1817a((Object) k);
            Preconditions.m1817a((Object) cacheLoader);
            try {
                V a;
                if (this.f1493b != 0) {
                    LocalCache a2 = m2110a((Object) k, i);
                    if (a2 != null) {
                        long a3 = this.f1492a.f1550r.m1871a();
                        Object c = m2146c(a2, a3);
                        if (c != null) {
                            m2126a(a2, a3);
                            this.f1505n.m1874a(1);
                            a = m2113a(a2, (Object) k, i, c, a3, (CacheLoader) cacheLoader);
                            m2161m();
                        } else {
                            LocalCache a4 = a2.m1930a();
                            if (a4.m1920c()) {
                                a = m2114a(a2, (Object) k, a4);
                                m2161m();
                            }
                        }
                        return a;
                    }
                }
                a = m2141b((Object) k, i, (CacheLoader) cacheLoader);
                m2161m();
                return a;
            } catch (ExecutionException e) {
                ExecutionException executionException = e;
                Throwable cause = executionException.getCause();
                if (cause instanceof Error) {
                    throw new ExecutionError((Error) cause);
                } else if (cause instanceof RuntimeException) {
                    throw new UncheckedExecutionException(cause);
                } else {
                    throw executionException;
                }
            } catch (Throwable th) {
                m2161m();
            }
        }

        V m2141b(K k, int i, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            lock();
            try {
                Object obj;
                LocalCache localCache;
                int i2;
                LocalCache localCache2;
                LocalCache localCache3;
                LocalCache localCache4;
                LocalCache localCache5;
                LocalCache localCache6;
                V a;
                long a2 = this.f1492a.f1550r.m1871a();
                m2148c(a2);
                int i3 = this.f1493b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache7 = (LocalCache) atomicReferenceArray.get(length);
                LocalCache localCache8 = localCache7;
                while (localCache8 != null) {
                    Object d = localCache8.m1939d();
                    if (localCache8.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(k, d)) {
                        LocalCache a3 = localCache8.m1930a();
                        if (a3.m1920c()) {
                            obj = null;
                            localCache = a3;
                        } else {
                            V v = a3.get();
                            if (v == null) {
                                m2129a(d, i, a3, RemovalCause.COLLECTED);
                            } else {
                                if (this.f1492a.m2238b(localCache8, a2)) {
                                    m2129a(d, i, a3, RemovalCause.EXPIRED);
                                } else {
                                    m2144b(localCache8, a2);
                                    this.f1505n.m1874a(1);
                                    unlock();
                                    m2162n();
                                    return v;
                                }
                            }
                            this.f1503l.remove(localCache8);
                            this.f1504m.remove(localCache8);
                            this.f1493b = i3;
                            i2 = 1;
                            localCache = a3;
                        }
                        if (obj == null) {
                            localCache2 = new LocalCache();
                            if (localCache8 != null) {
                                localCache7 = m2112a((Object) k, i, localCache7);
                                localCache7.m1933a(localCache2);
                                atomicReferenceArray.set(length, localCache7);
                                localCache3 = localCache2;
                                localCache4 = localCache7;
                                localCache5 = localCache3;
                            } else {
                                localCache8.m1933a(localCache2);
                                localCache6 = localCache2;
                                localCache4 = localCache8;
                            }
                        } else {
                            localCache5 = null;
                            localCache4 = localCache8;
                        }
                        unlock();
                        m2162n();
                        if (obj != null) {
                            return m2114a(localCache4, (Object) k, localCache);
                        }
                        try {
                            synchronized (localCache4) {
                                a = m2117a((Object) k, i, localCache5, (CacheLoader) cacheLoader);
                            }
                            return a;
                        } finally {
                            this.f1505n.m1876b(1);
                        }
                    } else {
                        localCache8 = localCache8.m1934b();
                    }
                }
                localCache = null;
                i2 = 1;
                if (obj == null) {
                    localCache5 = null;
                    localCache4 = localCache8;
                } else {
                    localCache2 = new LocalCache();
                    if (localCache8 != null) {
                        localCache8.m1933a(localCache2);
                        localCache6 = localCache2;
                        localCache4 = localCache8;
                    } else {
                        localCache7 = m2112a((Object) k, i, localCache7);
                        localCache7.m1933a(localCache2);
                        atomicReferenceArray.set(length, localCache7);
                        localCache3 = localCache2;
                        localCache4 = localCache7;
                        localCache5 = localCache3;
                    }
                }
                unlock();
                m2162n();
                if (obj != null) {
                    return m2114a(localCache4, (Object) k, localCache);
                }
                synchronized (localCache4) {
                    a = m2117a((Object) k, i, localCache5, (CacheLoader) cacheLoader);
                }
                return a;
            } catch (Throwable th) {
                unlock();
                m2162n();
            }
        }

        V m2114a(LocalCache<K, V> localCache, K k, LocalCache<K, V> localCache2) throws ExecutionException {
            if (localCache2.m1920c()) {
                Preconditions.m1829b(!Thread.holdsLock(localCache), (Object) "Recursive load");
                try {
                    V e = localCache2.m1922e();
                    if (e == null) {
                        throw new CacheLoader("CacheLoader returned null for key " + k + ".");
                    }
                    m2126a((LocalCache) localCache, this.f1492a.f1550r.m1871a());
                    return e;
                } finally {
                    this.f1505n.m1876b(1);
                }
            } else {
                throw new AssertionError();
            }
        }

        V m2117a(K k, int i, LocalCache<K, V> localCache, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            return m2118a((Object) k, i, (LocalCache) localCache, localCache.m2077a((Object) k, (CacheLoader) cacheLoader));
        }

        ListenableFuture<V> m2139b(K k, int i, LocalCache<K, V> localCache, CacheLoader<? super K, V> cacheLoader) {
            ListenableFuture<V> a = localCache.m2077a((Object) k, (CacheLoader) cacheLoader);
            a.m3118a(new LocalCache(this, k, i, localCache, a), LocalCache.f1532b);
            return a;
        }

        V m2118a(K k, int i, LocalCache<K, V> localCache, ListenableFuture<V> listenableFuture) throws ExecutionException {
            V v = null;
            try {
                v = Uninterruptibles.m3146a(listenableFuture);
                if (v == null) {
                    throw new CacheLoader("CacheLoader returned null for key " + k + ".");
                }
                this.f1505n.m1875a(localCache.m2085f());
                m2134a((Object) k, i, (LocalCache) localCache, (Object) v);
                return v;
            } finally {
                if (v == null) {
                    v = this.f1505n;
                    v.m1877b(localCache.m2085f());
                    m2133a((Object) k, i, (LocalCache) localCache);
                }
            }
        }

        V m2113a(LocalCache<K, V> localCache, K k, int i, V v, long j, CacheLoader<? super K, V> cacheLoader) {
            if (!this.f1492a.m2242e() || j - localCache.m1944h() <= this.f1492a.f1547o || localCache.m1930a().m1920c()) {
                return v;
            }
            V a = m2116a((Object) k, i, (CacheLoader) cacheLoader, true);
            if (a != null) {
                return a;
            }
            return v;
        }

        @Nullable
        V m2116a(K k, int i, CacheLoader<? super K, V> cacheLoader, boolean z) {
            V v = null;
            LocalCache a = m2107a((Object) k, i, z);
            if (a != null) {
                Future b = m2139b(k, i, a, cacheLoader);
                if (b.isDone()) {
                    try {
                        v = Uninterruptibles.m3146a(b);
                    } catch (Throwable th) {
                    }
                }
            }
            return v;
        }

        @Nullable
        LocalCache<K, V> m2107a(K k, int i, boolean z) {
            lock();
            long a = this.f1492a.f1550r.m1871a();
            m2148c(a);
            AtomicReferenceArray atomicReferenceArray = this.f1497f;
            int length = i & (atomicReferenceArray.length() - 1);
            LocalCache localCache = (LocalCache) atomicReferenceArray.get(length);
            LocalCache localCache2 = localCache;
            while (localCache2 != null) {
                Object d = localCache2.m1939d();
                if (localCache2.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(k, d)) {
                    LocalCache a2 = localCache2.m1930a();
                    if (a2.m1920c() || (z && a - localCache2.m1944h() < this.f1492a.f1547o)) {
                        unlock();
                        m2162n();
                        return null;
                    }
                    this.f1495d++;
                    LocalCache localCache3 = new LocalCache(a2);
                    localCache2.m1933a(localCache3);
                    unlock();
                    m2162n();
                    return localCache3;
                }
                try {
                    localCache2 = localCache2.m1934b();
                } catch (Throwable th) {
                    unlock();
                    m2162n();
                }
            }
            this.f1495d++;
            LocalCache localCache4 = new LocalCache();
            localCache = m2112a((Object) k, i, localCache);
            localCache.m1933a(localCache4);
            atomicReferenceArray.set(length, localCache);
            unlock();
            m2162n();
            return localCache4;
        }

        void m2122a() {
            if (tryLock()) {
                try {
                    m2142b();
                } finally {
                    unlock();
                }
            }
        }

        @GuardedBy("Segment.this")
        void m2142b() {
            if (this.f1492a.m2250m()) {
                m2147c();
            }
            if (this.f1492a.m2251n()) {
                m2151d();
            }
        }

        @GuardedBy("Segment.this")
        void m2147c() {
            int i = 0;
            while (true) {
                Reference poll = this.f1499h.poll();
                if (poll != null) {
                    this.f1492a.m2232a((LocalCache) poll);
                    int i2 = i + 1;
                    if (i2 != 16) {
                        i = i2;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        @GuardedBy("Segment.this")
        void m2151d() {
            int i = 0;
            while (true) {
                Reference poll = this.f1500i.poll();
                if (poll != null) {
                    this.f1492a.m2233a((LocalCache) poll);
                    int i2 = i + 1;
                    if (i2 != 16) {
                        i = i2;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        void m2153e() {
            if (this.f1492a.m2250m()) {
                m2154f();
            }
            if (this.f1492a.m2251n()) {
                m2155g();
            }
        }

        void m2154f() {
            do {
            } while (this.f1499h.poll() != null);
        }

        void m2155g() {
            do {
            } while (this.f1500i.poll() != null);
        }

        void m2126a(LocalCache<K, V> localCache, long j) {
            if (this.f1492a.m2246i()) {
                localCache.m1931a(j);
            }
            this.f1501j.add(localCache);
        }

        @GuardedBy("Segment.this")
        void m2144b(LocalCache<K, V> localCache, long j) {
            if (this.f1492a.m2246i()) {
                localCache.m1931a(j);
            }
            this.f1504m.add(localCache);
        }

        @GuardedBy("Segment.this")
        void m2125a(LocalCache<K, V> localCache, int i, long j) {
            m2156h();
            this.f1494c += i;
            if (this.f1492a.m2246i()) {
                localCache.m1931a(j);
            }
            if (this.f1492a.m2245h()) {
                localCache.m1935b(j);
            }
            this.f1504m.add(localCache);
            this.f1503l.add(localCache);
        }

        @GuardedBy("Segment.this")
        void m2156h() {
            while (true) {
                LocalCache localCache = (LocalCache) this.f1501j.poll();
                if (localCache == null) {
                    return;
                }
                if (this.f1504m.contains(localCache)) {
                    this.f1504m.add(localCache);
                }
            }
        }

        void m2123a(long j) {
            if (tryLock()) {
                try {
                    m2143b(j);
                } finally {
                    unlock();
                }
            }
        }

        @GuardedBy("Segment.this")
        void m2143b(long j) {
            m2156h();
            LocalCache localCache;
            do {
                localCache = (LocalCache) this.f1503l.peek();
                if (localCache == null || !this.f1492a.m2238b(localCache, j)) {
                    do {
                        localCache = (LocalCache) this.f1504m.peek();
                        if (localCache == null || !this.f1492a.m2238b(localCache, j)) {
                            return;
                        }
                    } while (m2132a(localCache, localCache.m1937c(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (m2132a(localCache, localCache.m1937c(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        @GuardedBy("Segment.this")
        void m2127a(LocalCache<K, V> localCache, RemovalCause removalCause) {
            m2129a(localCache.m1939d(), localCache.m1937c(), localCache.m1930a(), removalCause);
        }

        @GuardedBy("Segment.this")
        void m2129a(@Nullable K k, int i, LocalCache<K, V> localCache, RemovalCause removalCause) {
            this.f1494c -= localCache.m1916a();
            if (removalCause.m2272a()) {
                this.f1505n.m1873a();
            }
            if (this.f1492a.f1548p != LocalCache.f1534w) {
                this.f1492a.f1548p.offer(new RemovalNotification(k, localCache.get(), removalCause));
            }
        }

        @GuardedBy("Segment.this")
        void m2157i() {
            if (this.f1492a.m2234a()) {
                m2156h();
                while (((long) this.f1494c) > this.f1498g) {
                    LocalCache j = m2158j();
                    if (!m2132a(j, j.m1937c(), RemovalCause.SIZE)) {
                        throw new AssertionError();
                    }
                }
            }
        }

        LocalCache<K, V> m2158j() {
            for (LocalCache<K, V> localCache : this.f1504m) {
                if (localCache.m1930a().m1916a() > 0) {
                    return localCache;
                }
            }
            throw new AssertionError();
        }

        LocalCache<K, V> m2137b(int i) {
            AtomicReferenceArray atomicReferenceArray = this.f1497f;
            return (LocalCache) atomicReferenceArray.get((atomicReferenceArray.length() - 1) & i);
        }

        @Nullable
        LocalCache<K, V> m2110a(Object obj, int i) {
            for (LocalCache<K, V> b = m2137b(i); b != null; b = b.m1934b()) {
                if (b.m1937c() == i) {
                    Object d = b.m1939d();
                    if (d == null) {
                        m2122a();
                    } else if (this.f1492a.f1539g.m1771a(obj, d)) {
                        return b;
                    }
                }
            }
            return null;
        }

        @Nullable
        LocalCache<K, V> m2111a(Object obj, int i, long j) {
            LocalCache<K, V> a = m2110a(obj, i);
            if (a == null) {
                return null;
            }
            if (!this.f1492a.m2238b((LocalCache) a, j)) {
                return a;
            }
            m2123a(j);
            return null;
        }

        V m2146c(LocalCache<K, V> localCache, long j) {
            if (localCache.m1939d() == null) {
                m2122a();
                return null;
            }
            V v = localCache.m1930a().get();
            if (v == null) {
                m2122a();
                return null;
            } else if (!this.f1492a.m2238b((LocalCache) localCache, j)) {
                return v;
            } else {
                m2123a(j);
                return null;
            }
        }

        @Nullable
        V m2140b(Object obj, int i) {
            V v = null;
            try {
                if (this.f1493b != 0) {
                    long a = this.f1492a.f1550r.m1871a();
                    LocalCache a2 = m2111a(obj, i, a);
                    if (a2 != null) {
                        Object obj2 = a2.m1930a().get();
                        if (obj2 != null) {
                            m2126a(a2, a);
                            v = m2113a(a2, a2.m1939d(), i, obj2, a, this.f1492a.f1553u);
                            m2161m();
                        } else {
                            m2122a();
                        }
                    }
                    return v;
                }
                m2161m();
                return v;
            } finally {
                m2161m();
            }
        }

        boolean m2149c(Object obj, int i) {
            boolean z = false;
            try {
                if (this.f1493b != 0) {
                    LocalCache a = m2111a(obj, i, this.f1492a.f1550r.m1871a());
                    if (a != null) {
                        if (a.m1930a().get() != null) {
                            z = true;
                        }
                        m2161m();
                    }
                } else {
                    m2161m();
                }
                return z;
            } finally {
                m2161m();
            }
        }

        @Nullable
        V m2120a(K k, int i, V v, boolean z) {
            lock();
            try {
                int i2;
                LocalCache localCache;
                long a = this.f1492a.f1550r.m1871a();
                m2148c(a);
                if (this.f1493b + 1 > this.f1496e) {
                    m2159k();
                    i2 = this.f1493b + 1;
                }
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache2 = (LocalCache) atomicReferenceArray.get(length);
                for (localCache = localCache2; localCache != null; localCache = localCache.m1934b()) {
                    Object d = localCache.m1939d();
                    if (localCache.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(k, d)) {
                        LocalCache a2 = localCache.m1930a();
                        V v2 = a2.get();
                        if (v2 == null) {
                            this.f1495d++;
                            if (a2.m1921d()) {
                                m2129a((Object) k, i, a2, RemovalCause.COLLECTED);
                                m2128a(localCache, (Object) k, (Object) v, a);
                                i2 = this.f1493b;
                            } else {
                                m2128a(localCache, (Object) k, (Object) v, a);
                                i2 = this.f1493b + 1;
                            }
                            this.f1493b = i2;
                            m2157i();
                            return null;
                        } else if (z) {
                            m2144b(localCache, a);
                            unlock();
                            m2162n();
                            return v2;
                        } else {
                            this.f1495d++;
                            m2129a((Object) k, i, a2, RemovalCause.REPLACED);
                            m2128a(localCache, (Object) k, (Object) v, a);
                            m2157i();
                            unlock();
                            m2162n();
                            return v2;
                        }
                    }
                }
                this.f1495d++;
                localCache = m2112a((Object) k, i, localCache2);
                m2128a(localCache, (Object) k, (Object) v, a);
                atomicReferenceArray.set(length, localCache);
                this.f1493b++;
                m2157i();
                unlock();
                m2162n();
                return null;
            } finally {
                unlock();
                m2162n();
            }
        }

        @GuardedBy("Segment.this")
        void m2159k() {
            AtomicReferenceArray atomicReferenceArray = this.f1497f;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i = this.f1493b;
                AtomicReferenceArray a = m2121a(length << 1);
                this.f1496e = (a.length() * 3) / 4;
                int length2 = a.length() - 1;
                int i2 = 0;
                while (i2 < length) {
                    int i3;
                    LocalCache localCache = (LocalCache) atomicReferenceArray.get(i2);
                    if (localCache != null) {
                        LocalCache b = localCache.m1934b();
                        int c = localCache.m1937c() & length2;
                        if (b == null) {
                            a.set(c, localCache);
                            i3 = i;
                        } else {
                            LocalCache localCache2;
                            LocalCache localCache3 = localCache;
                            while (b != null) {
                                i3 = b.m1937c() & length2;
                                if (i3 != c) {
                                    localCache2 = b;
                                } else {
                                    i3 = c;
                                    localCache2 = localCache3;
                                }
                                b = b.m1934b();
                                localCache3 = localCache2;
                                c = i3;
                            }
                            a.set(c, localCache3);
                            localCache2 = localCache;
                            i3 = i;
                            while (localCache2 != localCache3) {
                                int i4;
                                int c2 = localCache2.m1937c() & length2;
                                localCache = m2108a(localCache2, (LocalCache) a.get(c2));
                                if (localCache != null) {
                                    a.set(c2, localCache);
                                    i4 = i3;
                                } else {
                                    m2124a(localCache2);
                                    i4 = i3 - 1;
                                }
                                localCache2 = localCache2.m1934b();
                                i3 = i4;
                            }
                        }
                    } else {
                        i3 = i;
                    }
                    i2++;
                    i = i3;
                }
                this.f1497f = a;
                this.f1493b = i;
            }
        }

        boolean m2136a(K k, int i, V v, V v2) {
            lock();
            try {
                long a = this.f1492a.f1550r.m1871a();
                m2148c(a);
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache = (LocalCache) atomicReferenceArray.get(length);
                for (LocalCache localCache2 = localCache; localCache2 != null; localCache2 = localCache2.m1934b()) {
                    Object d = localCache2.m1939d();
                    if (localCache2.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(k, d)) {
                        LocalCache a2 = localCache2.m1930a();
                        Object obj = a2.get();
                        if (obj == null) {
                            if (a2.m1921d()) {
                                int i2 = this.f1493b - 1;
                                this.f1495d++;
                                LocalCache a3 = m2109a(localCache, localCache2, d, i, a2, RemovalCause.COLLECTED);
                                int i3 = this.f1493b - 1;
                                atomicReferenceArray.set(length, a3);
                                this.f1493b = i3;
                            }
                            unlock();
                            m2162n();
                            return false;
                        } else if (this.f1492a.f1540h.m1771a(v, obj)) {
                            this.f1495d++;
                            m2129a((Object) k, i, a2, RemovalCause.REPLACED);
                            m2128a(localCache2, (Object) k, (Object) v2, a);
                            m2157i();
                            unlock();
                            m2162n();
                            return true;
                        } else {
                            m2144b(localCache2, a);
                            unlock();
                            m2162n();
                            return false;
                        }
                    }
                }
                unlock();
                m2162n();
                return false;
            } catch (Throwable th) {
                unlock();
                m2162n();
            }
        }

        @Nullable
        V m2119a(K k, int i, V v) {
            lock();
            try {
                long a = this.f1492a.f1550r.m1871a();
                m2148c(a);
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache = (LocalCache) atomicReferenceArray.get(length);
                for (LocalCache localCache2 = localCache; localCache2 != null; localCache2 = localCache2.m1934b()) {
                    Object d = localCache2.m1939d();
                    if (localCache2.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(k, d)) {
                        LocalCache a2 = localCache2.m1930a();
                        V v2 = a2.get();
                        if (v2 == null) {
                            if (a2.m1921d()) {
                                int i2 = this.f1493b - 1;
                                this.f1495d++;
                                LocalCache a3 = m2109a(localCache, localCache2, d, i, a2, RemovalCause.COLLECTED);
                                int i3 = this.f1493b - 1;
                                atomicReferenceArray.set(length, a3);
                                this.f1493b = i3;
                            }
                            unlock();
                            m2162n();
                            return null;
                        }
                        this.f1495d++;
                        m2129a((Object) k, i, a2, RemovalCause.REPLACED);
                        m2128a(localCache2, (Object) k, (Object) v, a);
                        m2157i();
                        unlock();
                        m2162n();
                        return v2;
                    }
                }
                unlock();
                m2162n();
                return null;
            } catch (Throwable th) {
                unlock();
                m2162n();
            }
        }

        @Nullable
        V m2150d(Object obj, int i) {
            lock();
            try {
                m2148c(this.f1492a.f1550r.m1871a());
                int i2 = this.f1493b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache = (LocalCache) atomicReferenceArray.get(length);
                for (LocalCache localCache2 = localCache; localCache2 != null; localCache2 = localCache2.m1934b()) {
                    Object d = localCache2.m1939d();
                    if (localCache2.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(obj, d)) {
                        RemovalCause removalCause;
                        LocalCache a = localCache2.m1930a();
                        V v = a.get();
                        if (v != null) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else if (a.m1921d()) {
                            removalCause = RemovalCause.COLLECTED;
                        } else {
                            unlock();
                            m2162n();
                            return null;
                        }
                        this.f1495d++;
                        LocalCache a2 = m2109a(localCache, localCache2, d, i, a, removalCause);
                        i2 = this.f1493b - 1;
                        atomicReferenceArray.set(length, a2);
                        this.f1493b = i2;
                        return v;
                    }
                }
                unlock();
                m2162n();
                return null;
            } finally {
                unlock();
                m2162n();
            }
        }

        boolean m2134a(K k, int i, LocalCache<K, V> localCache, V v) {
            lock();
            try {
                LocalCache localCache2;
                long a = this.f1492a.f1550r.m1871a();
                m2148c(a);
                int i2 = this.f1493b + 1;
                if (i2 > this.f1496e) {
                    m2159k();
                    i2 = this.f1493b + 1;
                }
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache3 = (LocalCache) atomicReferenceArray.get(length);
                for (localCache2 = localCache3; localCache2 != null; localCache2 = localCache2.m1934b()) {
                    Object d = localCache2.m1939d();
                    if (localCache2.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(k, d)) {
                        LocalCache a2 = localCache2.m1930a();
                        d = a2.get();
                        if (localCache == a2 || (d == null && a2 != LocalCache.f1533v)) {
                            this.f1495d++;
                            if (localCache.m2083d()) {
                                m2129a((Object) k, i, (LocalCache) localCache, d == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED);
                                i2--;
                            }
                            m2128a(localCache2, (Object) k, (Object) v, a);
                            this.f1493b = i2;
                            m2157i();
                            return true;
                        }
                        m2129a((Object) k, i, new af(v, 0), RemovalCause.REPLACED);
                        unlock();
                        m2162n();
                        return false;
                    }
                }
                this.f1495d++;
                localCache2 = m2112a((Object) k, i, localCache3);
                m2128a(localCache2, (Object) k, (Object) v, a);
                atomicReferenceArray.set(length, localCache2);
                this.f1493b = i2;
                m2157i();
                unlock();
                m2162n();
                return true;
            } finally {
                unlock();
                m2162n();
            }
        }

        boolean m2145b(Object obj, int i, Object obj2) {
            lock();
            try {
                m2148c(this.f1492a.f1550r.m1871a());
                int i2 = this.f1493b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache = (LocalCache) atomicReferenceArray.get(length);
                for (LocalCache localCache2 = localCache; localCache2 != null; localCache2 = localCache2.m1934b()) {
                    Object d = localCache2.m1939d();
                    if (localCache2.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(obj, d)) {
                        RemovalCause removalCause;
                        LocalCache a = localCache2.m1930a();
                        Object obj3 = a.get();
                        if (this.f1492a.f1540h.m1771a(obj2, obj3)) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else {
                            if (obj3 == null) {
                                if (a.m1921d()) {
                                    removalCause = RemovalCause.COLLECTED;
                                }
                            }
                            unlock();
                            m2162n();
                            return false;
                        }
                        this.f1495d++;
                        LocalCache a2 = m2109a(localCache, localCache2, d, i, a, removalCause);
                        int i3 = this.f1493b - 1;
                        atomicReferenceArray.set(length, a2);
                        this.f1493b = i3;
                        boolean z = removalCause == RemovalCause.EXPLICIT;
                        unlock();
                        m2162n();
                        return z;
                    }
                }
                unlock();
                m2162n();
                return false;
            } catch (Throwable th) {
                unlock();
                m2162n();
            }
        }

        void m2160l() {
            if (this.f1493b != 0) {
                lock();
                try {
                    AtomicReferenceArray atomicReferenceArray = this.f1497f;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        for (LocalCache localCache = (LocalCache) atomicReferenceArray.get(i); localCache != null; localCache = localCache.m1934b()) {
                            if (localCache.m1930a().m1921d()) {
                                m2127a(localCache, RemovalCause.EXPLICIT);
                            }
                        }
                    }
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    m2153e();
                    this.f1503l.clear();
                    this.f1504m.clear();
                    this.f1502k.set(0);
                    this.f1495d++;
                    this.f1493b = 0;
                } finally {
                    unlock();
                    m2162n();
                }
            }
        }

        @GuardedBy("Segment.this")
        @Nullable
        LocalCache<K, V> m2109a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, @Nullable K k, int i, LocalCache<K, V> localCache3, RemovalCause removalCause) {
            m2129a((Object) k, i, (LocalCache) localCache3, removalCause);
            this.f1503l.remove(localCache2);
            this.f1504m.remove(localCache2);
            if (!localCache3.m1920c()) {
                return m2138b((LocalCache) localCache, (LocalCache) localCache2);
            }
            localCache3.m1918a(null);
            return localCache;
        }

        @GuardedBy("Segment.this")
        @Nullable
        LocalCache<K, V> m2138b(LocalCache<K, V> localCache, LocalCache<K, V> localCache2) {
            int i = this.f1493b;
            LocalCache<K, V> b = localCache2.m1934b();
            LocalCache b2;
            while (b2 != localCache2) {
                int i2;
                LocalCache<K, V> a = m2108a(b2, (LocalCache) b);
                if (a != null) {
                    i2 = i;
                } else {
                    m2124a(b2);
                    LocalCache<K, V> localCache3 = b;
                    i2 = i - 1;
                    a = localCache3;
                }
                b2 = b2.m1934b();
                i = i2;
                b = a;
            }
            this.f1493b = i;
            return b;
        }

        @GuardedBy("Segment.this")
        void m2124a(LocalCache<K, V> localCache) {
            m2127a((LocalCache) localCache, RemovalCause.COLLECTED);
            this.f1503l.remove(localCache);
            this.f1504m.remove(localCache);
        }

        boolean m2131a(LocalCache<K, V> localCache, int i) {
            lock();
            try {
                int i2 = this.f1493b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache2 = (LocalCache) atomicReferenceArray.get(length);
                for (LocalCache localCache3 = localCache2; localCache3 != null; localCache3 = localCache3.m1934b()) {
                    if (localCache3 == localCache) {
                        this.f1495d++;
                        LocalCache a = m2109a(localCache2, localCache3, localCache3.m1939d(), i, localCache3.m1930a(), RemovalCause.COLLECTED);
                        int i3 = this.f1493b - 1;
                        atomicReferenceArray.set(length, a);
                        this.f1493b = i3;
                        return true;
                    }
                }
                unlock();
                m2162n();
                return false;
            } finally {
                unlock();
                m2162n();
            }
        }

        boolean m2135a(K k, int i, LocalCache<K, V> localCache) {
            boolean z = false;
            lock();
            try {
                int i2 = this.f1493b - 1;
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache2 = (LocalCache) atomicReferenceArray.get(length);
                for (LocalCache localCache3 = localCache2; localCache3 != null; localCache3 = localCache3.m1934b()) {
                    Object d = localCache3.m1939d();
                    if (localCache3.m1937c() == i && d != null && this.f1492a.f1539g.m1771a(k, d)) {
                        if (localCache3.m1930a() == localCache) {
                            this.f1495d++;
                            LocalCache a = m2109a(localCache2, localCache3, d, i, (LocalCache) localCache, RemovalCause.COLLECTED);
                            i2 = this.f1493b - 1;
                            atomicReferenceArray.set(length, a);
                            this.f1493b = i2;
                            z = true;
                        } else {
                            unlock();
                            if (!isHeldByCurrentThread()) {
                                m2162n();
                            }
                        }
                        return z;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    m2162n();
                }
                return z;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    m2162n();
                }
            }
        }

        boolean m2133a(K k, int i, LocalCache<K, V> localCache) {
            lock();
            try {
                AtomicReferenceArray atomicReferenceArray = this.f1497f;
                int length = i & (atomicReferenceArray.length() - 1);
                LocalCache localCache2 = (LocalCache) atomicReferenceArray.get(length);
                LocalCache localCache3 = localCache2;
                while (localCache3 != null) {
                    Object d = localCache3.m1939d();
                    if (localCache3.m1937c() != i || d == null || !this.f1492a.f1539g.m1771a(k, d)) {
                        localCache3 = localCache3.m1934b();
                    } else if (localCache3.m1930a() == localCache) {
                        if (localCache.m2083d()) {
                            localCache3.m1933a(localCache.m2086g());
                        } else {
                            atomicReferenceArray.set(length, m2138b(localCache2, localCache3));
                        }
                        unlock();
                        m2162n();
                        return true;
                    } else {
                        unlock();
                        m2162n();
                        return false;
                    }
                }
                unlock();
                m2162n();
                return false;
            } catch (Throwable th) {
                unlock();
                m2162n();
            }
        }

        @GuardedBy("Segment.this")
        boolean m2132a(LocalCache<K, V> localCache, int i, RemovalCause removalCause) {
            int i2 = this.f1493b - 1;
            AtomicReferenceArray atomicReferenceArray = this.f1497f;
            int length = i & (atomicReferenceArray.length() - 1);
            LocalCache localCache2 = (LocalCache) atomicReferenceArray.get(length);
            for (LocalCache localCache3 = localCache2; localCache3 != null; localCache3 = localCache3.m1934b()) {
                if (localCache3 == localCache) {
                    this.f1495d++;
                    LocalCache a = m2109a(localCache2, localCache3, localCache3.m1939d(), i, localCache3.m1930a(), removalCause);
                    int i3 = this.f1493b - 1;
                    atomicReferenceArray.set(length, a);
                    this.f1493b = i3;
                    return true;
                }
            }
            return false;
        }

        void m2161m() {
            if ((this.f1502k.incrementAndGet() & 63) == 0) {
                m2163o();
            }
        }

        @GuardedBy("Segment.this")
        void m2148c(long j) {
            m2152d(j);
        }

        void m2162n() {
            m2164p();
        }

        void m2163o() {
            m2152d(this.f1492a.f1550r.m1871a());
            m2164p();
        }

        void m2152d(long j) {
            if (tryLock()) {
                try {
                    m2142b();
                    m2143b(j);
                    this.f1502k.set(0);
                } finally {
                    unlock();
                }
            }
        }

        void m2164p() {
            if (!isHeldByCurrentThread()) {
                this.f1492a.m2252r();
            }
        }
    }

    /* renamed from: com.google.a.b.f.q */
    enum LocalCache {
        STRONG {
            <K, V> LocalCache<K, V> m2168a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, V v, int i) {
                return i == 1 ? new LocalCache(v) : new af(v, i);
            }

            Equivalence<Object> m2167a() {
                return Equivalence.m1768a();
            }
        },
        SOFT {
            <K, V> LocalCache<K, V> m2170a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, V v, int i) {
                return i == 1 ? new LocalCache(localCache.f1500i, v, localCache2) : new ae(localCache.f1500i, v, localCache2, i);
            }

            Equivalence<Object> m2169a() {
                return Equivalence.m1769b();
            }
        },
        WEAK {
            <K, V> LocalCache<K, V> m2172a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, V v, int i) {
                return i == 1 ? new ac(localCache.f1500i, v, localCache2) : new ag(localCache.f1500i, v, localCache2, i);
            }

            Equivalence<Object> m2171a() {
                return Equivalence.m1769b();
            }
        };

        abstract Equivalence<Object> m2165a();

        abstract <K, V> LocalCache<K, V> m2166a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2, V v, int i);
    }

    /* renamed from: com.google.a.b.f.t */
    static class LocalCache<K, V> implements LocalCache<K, V> {
        final K f1510g;
        final int f1511h;
        final LocalCache<K, V> f1512i;
        volatile LocalCache<K, V> f1513j;

        LocalCache(K k, int i, @Nullable LocalCache<K, V> localCache) {
            this.f1513j = LocalCache.m2225o();
            this.f1510g = k;
            this.f1511h = i;
            this.f1512i = localCache;
        }

        public K m2182d() {
            return this.f1510g;
        }

        public long m2184e() {
            throw new UnsupportedOperationException();
        }

        public void m2174a(long j) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2185f() {
            throw new UnsupportedOperationException();
        }

        public void m2175a(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2186g() {
            throw new UnsupportedOperationException();
        }

        public void m2179b(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public long m2187h() {
            throw new UnsupportedOperationException();
        }

        public void m2178b(long j) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2188i() {
            throw new UnsupportedOperationException();
        }

        public void m2181c(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2189j() {
            throw new UnsupportedOperationException();
        }

        public void m2183d(LocalCache<K, V> localCache) {
            throw new UnsupportedOperationException();
        }

        public LocalCache<K, V> m2173a() {
            return this.f1513j;
        }

        public void m2176a(LocalCache<K, V> localCache) {
            this.f1513j = localCache;
        }

        public int m2180c() {
            return this.f1511h;
        }

        public LocalCache<K, V> m2177b() {
            return this.f1512i;
        }
    }

    /* renamed from: com.google.a.b.f.r */
    static final class LocalCache<K, V> extends LocalCache<K, V> implements LocalCache<K, V> {
        volatile long f1514a;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1515b;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1516c;

        LocalCache(K k, int i, @Nullable LocalCache<K, V> localCache) {
            super(k, i, localCache);
            this.f1514a = Long.MAX_VALUE;
            this.f1515b = LocalCache.m2226p();
            this.f1516c = LocalCache.m2226p();
        }

        public long m2193e() {
            return this.f1514a;
        }

        public void m2190a(long j) {
            this.f1514a = j;
        }

        public LocalCache<K, V> m2194f() {
            return this.f1515b;
        }

        public void m2191a(LocalCache<K, V> localCache) {
            this.f1515b = localCache;
        }

        public LocalCache<K, V> m2195g() {
            return this.f1516c;
        }

        public void m2192b(LocalCache<K, V> localCache) {
            this.f1516c = localCache;
        }
    }

    /* renamed from: com.google.a.b.f.s */
    static final class LocalCache<K, V> extends LocalCache<K, V> implements LocalCache<K, V> {
        volatile long f1517a;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1518b;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1519c;
        volatile long f1520d;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1521e;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1522f;

        LocalCache(K k, int i, @Nullable LocalCache<K, V> localCache) {
            super(k, i, localCache);
            this.f1517a = Long.MAX_VALUE;
            this.f1518b = LocalCache.m2226p();
            this.f1519c = LocalCache.m2226p();
            this.f1520d = Long.MAX_VALUE;
            this.f1521e = LocalCache.m2226p();
            this.f1522f = LocalCache.m2226p();
        }

        public long m2202e() {
            return this.f1517a;
        }

        public void m2196a(long j) {
            this.f1517a = j;
        }

        public LocalCache<K, V> m2203f() {
            return this.f1518b;
        }

        public void m2197a(LocalCache<K, V> localCache) {
            this.f1518b = localCache;
        }

        public LocalCache<K, V> m2204g() {
            return this.f1519c;
        }

        public void m2199b(LocalCache<K, V> localCache) {
            this.f1519c = localCache;
        }

        public long m2205h() {
            return this.f1520d;
        }

        public void m2198b(long j) {
            this.f1520d = j;
        }

        public LocalCache<K, V> m2206i() {
            return this.f1521e;
        }

        public void m2200c(LocalCache<K, V> localCache) {
            this.f1521e = localCache;
        }

        public LocalCache<K, V> m2207j() {
            return this.f1522f;
        }

        public void m2201d(LocalCache<K, V> localCache) {
            this.f1522f = localCache;
        }
    }

    /* renamed from: com.google.a.b.f.v */
    static final class LocalCache<K, V> extends LocalCache<K, V> implements LocalCache<K, V> {
        volatile long f1523a;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1524b;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1525c;

        LocalCache(K k, int i, @Nullable LocalCache<K, V> localCache) {
            super(k, i, localCache);
            this.f1523a = Long.MAX_VALUE;
            this.f1524b = LocalCache.m2226p();
            this.f1525c = LocalCache.m2226p();
        }

        public long m2211h() {
            return this.f1523a;
        }

        public void m2208b(long j) {
            this.f1523a = j;
        }

        public LocalCache<K, V> m2212i() {
            return this.f1524b;
        }

        public void m2209c(LocalCache<K, V> localCache) {
            this.f1524b = localCache;
        }

        public LocalCache<K, V> m2213j() {
            return this.f1525c;
        }

        public void m2210d(LocalCache<K, V> localCache) {
            this.f1525c = localCache;
        }
    }

    /* renamed from: com.google.a.b.f.w */
    final class LocalCache extends LocalCache<V> {
        final /* synthetic */ LocalCache f1526a;

        LocalCache(LocalCache localCache) {
            this.f1526a = localCache;
            super(localCache);
        }

        public V next() {
            return m2071e().getValue();
        }
    }

    /* renamed from: com.google.a.b.f.y */
    final class LocalCache extends LocalCache<V> {
        final /* synthetic */ LocalCache f1527c;

        LocalCache(LocalCache localCache, ConcurrentMap<?, ?> concurrentMap) {
            this.f1527c = localCache;
            super(localCache, concurrentMap);
        }

        public Iterator<V> iterator() {
            return new LocalCache(this.f1527c);
        }

        public boolean contains(Object obj) {
            return this.a.containsValue(obj);
        }
    }

    /* renamed from: com.google.a.b.f.z */
    static final class LocalCache<K, V> extends ab<K, V> implements LocalCache<K, V> {
        volatile long f1528a;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1529b;
        @GuardedBy("Segment.this")
        LocalCache<K, V> f1530c;

        LocalCache(ReferenceQueue<K> referenceQueue, K k, int i, @Nullable LocalCache<K, V> localCache) {
            super(referenceQueue, k, i, localCache);
            this.f1528a = Long.MAX_VALUE;
            this.f1529b = LocalCache.m2226p();
            this.f1530c = LocalCache.m2226p();
        }

        public long m2217e() {
            return this.f1528a;
        }

        public void m2214a(long j) {
            this.f1528a = j;
        }

        public LocalCache<K, V> m2218f() {
            return this.f1529b;
        }

        public void m2215a(LocalCache<K, V> localCache) {
            this.f1529b = localCache;
        }

        public LocalCache<K, V> m2219g() {
            return this.f1530c;
        }

        public void m2216b(LocalCache<K, V> localCache) {
            this.f1530c = localCache;
        }
    }

    static {
        f1531a = Logger.getLogger(LocalCache.class.getName());
        f1532b = MoreExecutors.m3141a();
        f1533v = new LocalCache();
        f1534w = new LocalCache();
    }

    LocalCache(CacheBuilder<? super K, ? super V> cacheBuilder, @Nullable CacheLoader<? super K, V> cacheLoader) {
        Queue q;
        int i = 0;
        this.f1538f = Math.min(cacheBuilder.m1903e(), AccessibilityNodeInfoCompat.ACTION_CUT);
        this.f1541i = cacheBuilder.m1907i();
        this.f1542j = cacheBuilder.m1908j();
        this.f1539g = cacheBuilder.m1900b();
        this.f1540h = cacheBuilder.m1901c();
        this.f1543k = cacheBuilder.m1904f();
        this.f1544l = cacheBuilder.m1905g();
        this.f1545m = cacheBuilder.m1910l();
        this.f1546n = cacheBuilder.m1909k();
        this.f1547o = cacheBuilder.m1911m();
        this.f1549q = cacheBuilder.m1912n();
        if (this.f1549q == CacheBuilder.INSTANCE) {
            q = LocalCache.m2227q();
        } else {
            q = new ConcurrentLinkedQueue();
        }
        this.f1548p = q;
        this.f1550r = cacheBuilder.m1897a(m2247j());
        this.f1551s = LocalCache.m2048a(this.f1541i, m2249l(), m2248k());
        this.f1552t = (AbstractCache) cacheBuilder.m1913o().m1867a();
        this.f1553u = cacheLoader;
        int min = Math.min(cacheBuilder.m1902d(), 1073741824);
        if (m2234a() && !m2237b()) {
            min = Math.min(min, (int) this.f1543k);
        }
        int i2 = 1;
        int i3 = 0;
        while (i2 < this.f1538f && (!m2234a() || ((long) (i2 * 20)) <= this.f1543k)) {
            i3++;
            i2 <<= 1;
        }
        this.f1536d = 32 - i3;
        this.f1535c = i2 - 1;
        this.f1537e = m2240c(i2);
        i3 = min / i2;
        if (i3 * i2 < min) {
            min = i3 + 1;
        } else {
            min = i3;
        }
        int i4 = 1;
        while (i4 < min) {
            i4 <<= 1;
        }
        if (m2234a()) {
            long j = this.f1543k % ((long) i2);
            long j2 = (this.f1543k / ((long) i2)) + 1;
            while (i < this.f1537e.length) {
                long j3;
                if (((long) i) == j) {
                    j3 = j2 - 1;
                } else {
                    j3 = j2;
                }
                this.f1537e[i] = m2229a(i4, j3, (AbstractCache) cacheBuilder.m1913o().m1867a());
                i++;
                j2 = j3;
            }
            return;
        }
        while (i < this.f1537e.length) {
            this.f1537e[i] = m2229a(i4, -1, (AbstractCache) cacheBuilder.m1913o().m1867a());
            i++;
        }
    }

    boolean m2234a() {
        return this.f1543k >= 0;
    }

    boolean m2237b() {
        return this.f1544l != CacheBuilder.INSTANCE;
    }

    boolean m2239c() {
        return this.f1546n > 0;
    }

    boolean m2241d() {
        return this.f1545m > 0;
    }

    boolean m2242e() {
        return this.f1547o > 0;
    }

    boolean m2243f() {
        return m2241d() || m2234a();
    }

    boolean m2244g() {
        return m2239c();
    }

    boolean m2245h() {
        return m2239c() || m2242e();
    }

    boolean m2246i() {
        return m2241d();
    }

    boolean m2247j() {
        return m2245h() || m2246i();
    }

    boolean m2248k() {
        return m2244g() || m2245h();
    }

    boolean m2249l() {
        return m2243f() || m2246i();
    }

    boolean m2250m() {
        return this.f1541i != LocalCache.STRONG;
    }

    boolean m2251n() {
        return this.f1542j != LocalCache.STRONG;
    }

    static <K, V> LocalCache<K, V> m2225o() {
        return f1533v;
    }

    static <K, V> LocalCache<K, V> m2226p() {
        return LocalCache.INSTANCE;
    }

    static <E> Queue<E> m2227q() {
        return f1534w;
    }

    static int m2220a(int i) {
        int i2 = ((i << 15) ^ -12931) + i;
        i2 ^= i2 >>> 10;
        i2 += i2 << 3;
        i2 ^= i2 >>> 6;
        i2 += (i2 << 2) + (i2 << 14);
        return i2 ^ (i2 >>> 16);
    }

    int m2228a(@Nullable Object obj) {
        return LocalCache.m2220a(this.f1539g.m1770a(obj));
    }

    void m2233a(LocalCache<K, V> localCache) {
        LocalCache b = localCache.m1919b();
        int c = b.m1937c();
        m2235b(c).m2135a(b.m1939d(), c, (LocalCache) localCache);
    }

    void m2232a(LocalCache<K, V> localCache) {
        int c = localCache.m1937c();
        m2235b(c).m2131a((LocalCache) localCache, c);
    }

    LocalCache<K, V> m2235b(int i) {
        return this.f1537e[(i >>> this.f1536d) & this.f1535c];
    }

    LocalCache<K, V> m2229a(int i, long j, AbstractCache abstractCache) {
        return new LocalCache(this, i, j, abstractCache);
    }

    @Nullable
    V m2230a(LocalCache<K, V> localCache, long j) {
        if (localCache.m1939d() == null) {
            return null;
        }
        V v = localCache.m1930a().get();
        if (v == null || m2238b((LocalCache) localCache, j)) {
            return null;
        }
        return v;
    }

    boolean m2238b(LocalCache<K, V> localCache, long j) {
        Preconditions.m1817a((Object) localCache);
        if (m2241d() && j - localCache.m1941e() >= this.f1545m) {
            return true;
        }
        if (!m2239c() || j - localCache.m1944h() < this.f1546n) {
            return false;
        }
        return true;
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2221a(LocalCache<K, V> localCache, LocalCache<K, V> localCache2) {
        localCache.m1932a((LocalCache) localCache2);
        localCache2.m1936b((LocalCache) localCache);
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2222b(LocalCache<K, V> localCache) {
        LocalCache p = LocalCache.m2226p();
        localCache.m1932a(p);
        localCache.m1936b(p);
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2223b(LocalCache<K, V> localCache, LocalCache<K, V> localCache2) {
        localCache.m1938c(localCache2);
        localCache2.m1940d(localCache);
    }

    @GuardedBy("Segment.this")
    static <K, V> void m2224c(LocalCache<K, V> localCache) {
        LocalCache p = LocalCache.m2226p();
        localCache.m1938c(p);
        localCache.m1940d(p);
    }

    void m2252r() {
        while (true) {
            RemovalNotification removalNotification = (RemovalNotification) this.f1548p.poll();
            if (removalNotification != null) {
                try {
                    this.f1549q.m1891a(removalNotification);
                } catch (Throwable th) {
                    f1531a.log(Level.WARNING, "Exception thrown by removal listener", th);
                }
            } else {
                return;
            }
        }
    }

    final LocalCache<K, V>[] m2240c(int i) {
        return new LocalCache[i];
    }

    public boolean isEmpty() {
        int i;
        LocalCache[] localCacheArr = this.f1537e;
        long j = 0;
        for (i = 0; i < localCacheArr.length; i++) {
            if (localCacheArr[i].f1493b != 0) {
                return false;
            }
            j += (long) localCacheArr[i].f1495d;
        }
        if (j != 0) {
            for (i = 0; i < localCacheArr.length; i++) {
                if (localCacheArr[i].f1493b != 0) {
                    return false;
                }
                j -= (long) localCacheArr[i].f1495d;
            }
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    long m2253s() {
        long j = 0;
        for (LocalCache localCache : this.f1537e) {
            j += (long) localCache.f1493b;
        }
        return j;
    }

    public int size() {
        return Ints.m3009a(m2253s());
    }

    @Nullable
    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a = m2228a(obj);
        return m2235b(a).m2140b(obj, a);
    }

    V m2231a(K k, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        int a = m2228a(Preconditions.m1817a((Object) k));
        return m2235b(a).m2115a((Object) k, a, (CacheLoader) cacheLoader);
    }

    V m2236b(K k) throws ExecutionException {
        return m2231a((Object) k, this.f1553u);
    }

    public boolean containsKey(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        int a = m2228a(obj);
        return m2235b(a).m2149c(obj, a);
    }

    public boolean containsValue(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        long a = this.f1550r.m1871a();
        LocalCache[] localCacheArr = this.f1537e;
        int i = 0;
        long j = -1;
        while (i < 3) {
            long j2 = 0;
            for (LocalCache localCache : localCacheArr) {
                int i2 = localCache.f1493b;
                AtomicReferenceArray atomicReferenceArray = localCache.f1497f;
                for (int i3 = 0; i3 < atomicReferenceArray.length(); i3++) {
                    for (LocalCache localCache2 = (LocalCache) atomicReferenceArray.get(i3); localCache2 != null; localCache2 = localCache2.m1934b()) {
                        Object c = localCache.m2146c(localCache2, a);
                        if (c != null) {
                            if (this.f1540h.m1771a(obj, c)) {
                                return true;
                            }
                        }
                    }
                }
                j2 += (long) localCache.f1495d;
            }
            if (j2 == j) {
                break;
            }
            i++;
            j = j2;
        }
        return false;
    }

    public V put(K k, V v) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v);
        int a = m2228a((Object) k);
        return m2235b(a).m2120a((Object) k, a, (Object) v, false);
    }

    public V putIfAbsent(K k, V v) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v);
        int a = m2228a((Object) k);
        return m2235b(a).m2120a((Object) k, a, (Object) v, true);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public V remove(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        int a = m2228a(obj);
        return m2235b(a).m2150d(obj, a);
    }

    public boolean remove(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int a = m2228a(obj);
        return m2235b(a).m2145b(obj, a, obj2);
    }

    public boolean replace(K k, @Nullable V v, V v2) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v2);
        if (v == null) {
            return false;
        }
        int a = m2228a((Object) k);
        return m2235b(a).m2136a((Object) k, a, (Object) v, (Object) v2);
    }

    public V replace(K k, V v) {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v);
        int a = m2228a((Object) k);
        return m2235b(a).m2119a((Object) k, a, (Object) v);
    }

    public void clear() {
        for (LocalCache l : this.f1537e) {
            l.m2160l();
        }
    }

    public Set<K> keySet() {
        Set<K> set = this.f1554x;
        if (set != null) {
            return set;
        }
        set = new LocalCache(this, this);
        this.f1554x = set;
        return set;
    }

    public Collection<V> values() {
        Collection<V> collection = this.f1555y;
        if (collection != null) {
            return collection;
        }
        collection = new LocalCache(this, this);
        this.f1555y = collection;
        return collection;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = this.f1556z;
        if (set != null) {
            return set;
        }
        set = new LocalCache(this, this);
        this.f1556z = set;
        return set;
    }
}
