package com.google.p025a.p028c;

import com.google.p025a.p026a.Ascii;
import com.google.p025a.p026a.Equivalence;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Ticker;
import com.google.p025a.p028c.at.MapMakerInternalMap;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

/* compiled from: MapMaker */
/* renamed from: com.google.a.c.as */
public final class as extends GenericMapMaker<Object, Object> {
    boolean f1668b;
    int f1669c;
    int f1670d;
    int f1671e;
    MapMakerInternalMap f1672f;
    MapMakerInternalMap f1673g;
    long f1674h;
    long f1675i;
    MapMaker f1676j;
    Equivalence<Object> f1677k;
    Ticker f1678l;

    /* renamed from: com.google.a.c.as.a */
    static class MapMaker<K, V> extends AbstractMap<K, V> implements Serializable, ConcurrentMap<K, V> {
        private final MapMaker<K, V> f1656a;
        private final MapMaker f1657b;

        MapMaker(as asVar) {
            this.f1656a = asVar.m2530a();
            this.f1657b = asVar.f1676j;
        }

        public boolean containsKey(@Nullable Object obj) {
            return false;
        }

        public boolean containsValue(@Nullable Object obj) {
            return false;
        }

        public V get(@Nullable Object obj) {
            return null;
        }

        void m2528a(K k, V v) {
            this.f1656a.m2529a(new MapMaker(k, v, this.f1657b));
        }

        public V put(K k, V v) {
            Preconditions.m1817a((Object) k);
            Preconditions.m1817a((Object) v);
            m2528a(k, v);
            return null;
        }

        public V putIfAbsent(K k, V v) {
            return put(k, v);
        }

        public V remove(@Nullable Object obj) {
            return null;
        }

        public boolean remove(@Nullable Object obj, @Nullable Object obj2) {
            return false;
        }

        public V replace(K k, V v) {
            Preconditions.m1817a((Object) k);
            Preconditions.m1817a((Object) v);
            return null;
        }

        public boolean replace(K k, @Nullable V v, V v2) {
            Preconditions.m1817a((Object) k);
            Preconditions.m1817a((Object) v2);
            return false;
        }

        public Set<Entry<K, V>> entrySet() {
            return Collections.emptySet();
        }
    }

    /* renamed from: com.google.a.c.as.b */
    enum MapMaker {
        EXPLICIT {
        },
        REPLACED {
        },
        COLLECTED {
        },
        EXPIRED {
        },
        SIZE {
        };
    }

    /* renamed from: com.google.a.c.as.c */
    interface MapMaker<K, V> {
        void m2529a(MapMaker<K, V> mapMaker);
    }

    /* renamed from: com.google.a.c.as.d */
    static final class MapMaker<K, V> extends ImmutableEntry<K, V> {
        private final MapMaker f1666a;

        MapMaker(@Nullable K k, @Nullable V v, MapMaker mapMaker) {
            super(k, v);
            this.f1666a = mapMaker;
        }
    }

    public as() {
        this.f1669c = -1;
        this.f1670d = -1;
        this.f1671e = -1;
        this.f1674h = -1;
        this.f1675i = -1;
    }

    Equivalence<Object> m2531b() {
        return (Equivalence) Objects.m1812b(this.f1677k, m2534e().m2693a());
    }

    int m2532c() {
        return this.f1669c == -1 ? 16 : this.f1669c;
    }

    int m2533d() {
        return this.f1670d == -1 ? 4 : this.f1670d;
    }

    MapMakerInternalMap m2534e() {
        return (MapMakerInternalMap) Objects.m1812b(this.f1672f, MapMakerInternalMap.STRONG);
    }

    MapMakerInternalMap m2535f() {
        return (MapMakerInternalMap) Objects.m1812b(this.f1673g, MapMakerInternalMap.STRONG);
    }

    long m2536g() {
        return this.f1674h == -1 ? 0 : this.f1674h;
    }

    long m2537h() {
        return this.f1675i == -1 ? 0 : this.f1675i;
    }

    Ticker m2538i() {
        return (Ticker) Objects.m1812b(this.f1678l, Ticker.m1870b());
    }

    public <K, V> ConcurrentMap<K, V> m2539j() {
        if (!this.f1668b) {
            return new ConcurrentHashMap(m2532c(), 0.75f, m2533d());
        }
        return this.f1676j == null ? new at(this) : new MapMaker(this);
    }

    public String toString() {
        Objects.Objects a = Objects.m1809a((Object) this);
        if (this.f1669c != -1) {
            a.m1804a("initialCapacity", this.f1669c);
        }
        if (this.f1670d != -1) {
            a.m1804a("concurrencyLevel", this.f1670d);
        }
        if (this.f1671e != -1) {
            a.m1804a("maximumSize", this.f1671e);
        }
        if (this.f1674h != -1) {
            a.m1806a("expireAfterWrite", this.f1674h + "ns");
        }
        if (this.f1675i != -1) {
            a.m1806a("expireAfterAccess", this.f1675i + "ns");
        }
        if (this.f1672f != null) {
            a.m1806a("keyStrength", Ascii.m1733a(this.f1672f.toString()));
        }
        if (this.f1673g != null) {
            a.m1806a("valueStrength", Ascii.m1733a(this.f1673g.toString()));
        }
        if (this.f1677k != null) {
            a.m1802a("keyEquivalence");
        }
        if (this.a != null) {
            a.m1802a("removalListener");
        }
        return a.toString();
    }
}
