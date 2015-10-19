package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Collection;

/* renamed from: com.google.a.c.x */
public abstract class ImmutableBiMap<K, V> extends ad<K, V> implements C0198g<K, V> {
    public abstract ImmutableBiMap<V, K> l_();

    public /* synthetic */ ImmutableCollection m2883f() {
        return m2884h();
    }

    public /* synthetic */ Collection values() {
        return m2884h();
    }

    public static <K, V> ImmutableBiMap<K, V> m2882g() {
        return EmptyImmutableBiMap.f1861a;
    }

    public static <K, V> ImmutableBiMap<K, V> m2881a(K k, V v) {
        Preconditions.m1819a((Object) k, "null key in entry: null=%s", v);
        Preconditions.m1819a((Object) v, "null value in entry: %s=null", k);
        return new bl(k, v);
    }

    ImmutableBiMap() {
    }

    public ai<V> m2884h() {
        return l_().m2315d();
    }
}
