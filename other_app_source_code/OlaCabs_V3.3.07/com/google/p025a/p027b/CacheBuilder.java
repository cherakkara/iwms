package com.google.p025a.p027b;

import com.google.p025a.p026a.Ascii;
import com.google.p025a.p026a.Equivalence;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Supplier;
import com.google.p025a.p026a.Suppliers;
import com.google.p025a.p026a.Ticker;
import com.google.p025a.p027b.AbstractCache.AbstractCache;
import com.google.p025a.p027b.LocalCache.LocalCache;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.a.b.b */
public final class CacheBuilder<K, V> {
    static final Supplier<? extends AbstractCache> f1398a;
    static final CacheStats f1399b;
    static final Supplier<AbstractCache> f1400c;
    static final Ticker f1401d;
    private static final Logger f1402u;
    boolean f1403e;
    int f1404f;
    int f1405g;
    long f1406h;
    long f1407i;
    Weigher<? super K, ? super V> f1408j;
    LocalCache f1409k;
    LocalCache f1410l;
    long f1411m;
    long f1412n;
    long f1413o;
    Equivalence<Object> f1414p;
    Equivalence<Object> f1415q;
    RemovalListener<? super K, ? super V> f1416r;
    Ticker f1417s;
    Supplier<? extends AbstractCache> f1418t;

    /* renamed from: com.google.a.b.b.1 */
    static class CacheBuilder implements AbstractCache {
        CacheBuilder() {
        }

        public void m1884a(int i) {
        }

        public void m1886b(int i) {
        }

        public void m1885a(long j) {
        }

        public void m1887b(long j) {
        }

        public void m1883a() {
        }
    }

    /* renamed from: com.google.a.b.b.2 */
    static class CacheBuilder implements Supplier<AbstractCache> {
        CacheBuilder() {
        }

        public /* synthetic */ Object m1888a() {
            return m1889b();
        }

        public AbstractCache m1889b() {
            return new AbstractCache();
        }
    }

    /* renamed from: com.google.a.b.b.3 */
    static class CacheBuilder extends Ticker {
        CacheBuilder() {
        }

        public long m1890a() {
            return 0;
        }
    }

    /* renamed from: com.google.a.b.b.a */
    enum CacheBuilder implements RemovalListener<Object, Object> {
        INSTANCE;

        public void m1892a(RemovalNotification<Object, Object> removalNotification) {
        }
    }

    /* renamed from: com.google.a.b.b.b */
    enum CacheBuilder implements Weigher<Object, Object> {
        INSTANCE;

        public int m1894a(Object obj, Object obj2) {
            return 1;
        }
    }

    static {
        f1398a = Suppliers.m1869a(new CacheBuilder());
        f1399b = new CacheStats(0, 0, 0, 0, 0, 0);
        f1400c = new CacheBuilder();
        f1401d = new CacheBuilder();
        f1402u = Logger.getLogger(CacheBuilder.class.getName());
    }

    CacheBuilder() {
        this.f1403e = true;
        this.f1404f = -1;
        this.f1405g = -1;
        this.f1406h = -1;
        this.f1407i = -1;
        this.f1411m = -1;
        this.f1412n = -1;
        this.f1413o = -1;
        this.f1418t = f1398a;
    }

    public static CacheBuilder<Object, Object> m1895a() {
        return new CacheBuilder();
    }

    Equivalence<Object> m1900b() {
        return (Equivalence) Objects.m1812b(this.f1414p, m1907i().m2165a());
    }

    Equivalence<Object> m1901c() {
        return (Equivalence) Objects.m1812b(this.f1415q, m1908j().m2165a());
    }

    int m1902d() {
        return this.f1404f == -1 ? 16 : this.f1404f;
    }

    int m1903e() {
        return this.f1405g == -1 ? 4 : this.f1405g;
    }

    long m1904f() {
        if (this.f1411m == 0 || this.f1412n == 0) {
            return 0;
        }
        return this.f1408j == null ? this.f1406h : this.f1407i;
    }

    <K1 extends K, V1 extends V> Weigher<K1, V1> m1905g() {
        return (Weigher) Objects.m1812b(this.f1408j, CacheBuilder.INSTANCE);
    }

    public CacheBuilder<K, V> m1906h() {
        return m1898a(LocalCache.WEAK);
    }

    CacheBuilder<K, V> m1898a(LocalCache localCache) {
        boolean z;
        if (this.f1409k == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.m1830b(z, "Key strength was already set to %s", this.f1409k);
        this.f1409k = (LocalCache) Preconditions.m1817a((Object) localCache);
        return this;
    }

    LocalCache m1907i() {
        return (LocalCache) Objects.m1812b(this.f1409k, LocalCache.STRONG);
    }

    LocalCache m1908j() {
        return (LocalCache) Objects.m1812b(this.f1410l, LocalCache.STRONG);
    }

    long m1909k() {
        return this.f1411m == -1 ? 0 : this.f1411m;
    }

    long m1910l() {
        return this.f1412n == -1 ? 0 : this.f1412n;
    }

    long m1911m() {
        return this.f1413o == -1 ? 0 : this.f1413o;
    }

    Ticker m1897a(boolean z) {
        if (this.f1417s != null) {
            return this.f1417s;
        }
        return z ? Ticker.m1870b() : f1401d;
    }

    <K1 extends K, V1 extends V> RemovalListener<K1, V1> m1912n() {
        return (RemovalListener) Objects.m1812b(this.f1416r, CacheBuilder.INSTANCE);
    }

    Supplier<? extends AbstractCache> m1913o() {
        return this.f1418t;
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> m1899a(CacheLoader<? super K1, V1> cacheLoader) {
        m1896p();
        return new LocalCache(this, cacheLoader);
    }

    private void m1896p() {
        boolean z = true;
        if (this.f1408j == null) {
            if (this.f1407i != -1) {
                z = false;
            }
            Preconditions.m1829b(z, (Object) "maximumWeight requires weigher");
        } else if (this.f1403e) {
            if (this.f1407i == -1) {
                z = false;
            }
            Preconditions.m1829b(z, (Object) "weigher requires maximumWeight");
        } else if (this.f1407i == -1) {
            f1402u.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
        }
    }

    public String toString() {
        Objects.Objects a = Objects.m1809a((Object) this);
        if (this.f1404f != -1) {
            a.m1804a("initialCapacity", this.f1404f);
        }
        if (this.f1405g != -1) {
            a.m1804a("concurrencyLevel", this.f1405g);
        }
        if (this.f1406h != -1) {
            a.m1805a("maximumSize", this.f1406h);
        }
        if (this.f1407i != -1) {
            a.m1805a("maximumWeight", this.f1407i);
        }
        if (this.f1411m != -1) {
            a.m1806a("expireAfterWrite", this.f1411m + "ns");
        }
        if (this.f1412n != -1) {
            a.m1806a("expireAfterAccess", this.f1412n + "ns");
        }
        if (this.f1409k != null) {
            a.m1806a("keyStrength", Ascii.m1733a(this.f1409k.toString()));
        }
        if (this.f1410l != null) {
            a.m1806a("valueStrength", Ascii.m1733a(this.f1410l.toString()));
        }
        if (this.f1414p != null) {
            a.m1802a("keyEquivalence");
        }
        if (this.f1415q != null) {
            a.m1802a("valueEquivalence");
        }
        if (this.f1416r != null) {
            a.m1802a("removalListener");
        }
        return a.toString();
    }
}
