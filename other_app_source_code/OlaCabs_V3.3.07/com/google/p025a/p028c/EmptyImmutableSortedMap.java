package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.o */
final class EmptyImmutableSortedMap<K, V> extends al<K, V> {
    private final transient an<K> f1865a;

    public /* synthetic */ ai m2953d() {
        return n_();
    }

    public /* synthetic */ Set entrySet() {
        return m2950b();
    }

    public /* synthetic */ Set keySet() {
        return n_();
    }

    public /* synthetic */ Collection values() {
        return m2955f();
    }

    EmptyImmutableSortedMap(Comparator<? super K> comparator) {
        this.f1865a = an.m2461a((Comparator) comparator);
    }

    public V get(@Nullable Object obj) {
        return null;
    }

    public an<K> n_() {
        return this.f1865a;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public ImmutableCollection<V> m2955f() {
        return ac.m2345g();
    }

    public String toString() {
        return "{}";
    }

    boolean m2954e() {
        return false;
    }

    public ai<Entry<K, V>> m2950b() {
        return ai.m2296g();
    }

    ai<Entry<K, V>> m2952c() {
        throw new AssertionError("should never be called");
    }

    public al<K, V> m2949a(K k, boolean z) {
        Preconditions.m1817a((Object) k);
        return this;
    }

    public al<K, V> m2951b(K k, boolean z) {
        Preconditions.m1817a((Object) k);
        return this;
    }
}
