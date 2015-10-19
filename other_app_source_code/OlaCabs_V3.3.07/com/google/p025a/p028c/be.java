package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* compiled from: RegularImmutableMap */
/* renamed from: com.google.a.c.be */
final class be<K, V> extends ad<K, V> {
    private final transient RegularImmutableMap<K, V>[] f1800a;
    private final transient RegularImmutableMap<K, V>[] f1801b;
    private final transient int f1802c;

    /* renamed from: com.google.a.c.be.a */
    private class RegularImmutableMap extends ae<K, V> {
        final /* synthetic */ be f1798a;

        public /* synthetic */ Iterator iterator() {
            return m2841b();
        }

        private RegularImmutableMap(be beVar) {
            this.f1798a = beVar;
        }

        ad<K, V> m2842e() {
            return this.f1798a;
        }

        public bs<Entry<K, V>> m2841b() {
            return m2286c().m2350b();
        }

        ac<Entry<K, V>> m2843f() {
            return new bc((ImmutableCollection) this, this.f1798a.f1800a);
        }
    }

    /* renamed from: com.google.a.c.be.b */
    private interface RegularImmutableMap<K, V> extends Entry<K, V> {
        @Nullable
        RegularImmutableMap<K, V> m2844a();
    }

    @Immutable
    /* renamed from: com.google.a.c.be.c */
    private static final class RegularImmutableMap<K, V> extends ImmutableEntry<K, V> implements RegularImmutableMap<K, V> {
        final RegularImmutableMap<K, V> f1799a;

        RegularImmutableMap(K k, V v, RegularImmutableMap<K, V> regularImmutableMap) {
            super(k, v);
            this.f1799a = regularImmutableMap;
        }

        public RegularImmutableMap<K, V> m2845a() {
            return this.f1799a;
        }
    }

    @Immutable
    /* renamed from: com.google.a.c.be.d */
    private static final class RegularImmutableMap<K, V> extends ImmutableEntry<K, V> implements RegularImmutableMap<K, V> {
        RegularImmutableMap(K k, V v) {
            super(k, v);
        }

        @Nullable
        public RegularImmutableMap<K, V> m2846a() {
            return null;
        }
    }

    be(Entry<?, ?>... entryArr) {
        int length = entryArr.length;
        this.f1800a = m2848a(length);
        int a = Hashing.m2972a(length, 1.2d);
        this.f1801b = m2848a(a);
        this.f1802c = a - 1;
        for (int i = 0; i < length; i++) {
            Entry entry = entryArr[i];
            Object key = entry.getKey();
            int a2 = this.f1802c & Hashing.m2971a(key.hashCode());
            RegularImmutableMap regularImmutableMap = this.f1801b[a2];
            RegularImmutableMap a3 = be.m2847a(key, entry.getValue(), regularImmutableMap);
            this.f1801b[a2] = a3;
            this.f1800a[i] = a3;
            for (a3 = regularImmutableMap; a3 != null; a3 = a3.m2844a()) {
                boolean z;
                if (key.equals(a3.getKey())) {
                    z = false;
                } else {
                    z = true;
                }
                Preconditions.m1824a(z, "duplicate key: %s", key);
            }
        }
    }

    private RegularImmutableMap<K, V>[] m2848a(int i) {
        return new RegularImmutableMap[i];
    }

    private static <K, V> RegularImmutableMap<K, V> m2847a(K k, V v, @Nullable RegularImmutableMap<K, V> regularImmutableMap) {
        return regularImmutableMap == null ? new RegularImmutableMap(k, v) : new RegularImmutableMap(k, v, regularImmutableMap);
    }

    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        for (RegularImmutableMap regularImmutableMap = this.f1801b[Hashing.m2971a(obj.hashCode()) & this.f1802c]; regularImmutableMap != null; regularImmutableMap = regularImmutableMap.m2844a()) {
            if (obj.equals(regularImmutableMap.getKey())) {
                return regularImmutableMap.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.f1800a.length;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean containsValue(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        for (Entry value : this.f1800a) {
            if (value.getValue().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    boolean m2851e() {
        return false;
    }

    ai<Entry<K, V>> m2850c() {
        return new RegularImmutableMap();
    }
}
