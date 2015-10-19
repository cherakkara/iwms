package com.google.p025a.p028c;

import java.util.Map.Entry;
import javax.annotation.Nullable;

/* compiled from: SingletonImmutableBiMap */
/* renamed from: com.google.a.c.bl */
final class bl<K, V> extends ImmutableBiMap<K, V> {
    final transient K f1811a;
    final transient V f1812b;
    transient ImmutableBiMap<V, K> f1813c;

    bl(K k, V v) {
        this.f1811a = k;
        this.f1812b = v;
    }

    private bl(K k, V v, ImmutableBiMap<V, K> immutableBiMap) {
        this.f1811a = k;
        this.f1812b = v;
        this.f1813c = immutableBiMap;
    }

    bl(Entry<K, V> entry) {
        this(entry.getKey(), entry.getValue());
    }

    public V get(@Nullable Object obj) {
        return this.f1811a.equals(obj) ? this.f1812b : null;
    }

    public int size() {
        return 1;
    }

    public boolean containsKey(@Nullable Object obj) {
        return this.f1811a.equals(obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        return this.f1812b.equals(obj);
    }

    boolean m2887e() {
        return false;
    }

    ai<Entry<K, V>> m2886c() {
        return ai.m2294b(au.m2811a(this.f1811a, this.f1812b));
    }

    ai<K> m2885a() {
        return ai.m2294b(this.f1811a);
    }

    public ImmutableBiMap<V, K> l_() {
        ImmutableBiMap<V, K> immutableBiMap = this.f1813c;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        immutableBiMap = new bl(this.f1812b, this.f1811a, this);
        this.f1813c = immutableBiMap;
        return immutableBiMap;
    }
}
