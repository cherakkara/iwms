package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.Nullable;

/* compiled from: ImmutableSortedMap */
/* renamed from: com.google.a.c.al */
public abstract class al<K, V> extends am<K, V> implements SortedMap<K, V> {
    private static final Comparator<Comparable> f1628a;
    private static final al<Comparable, Object> f1629b;

    public abstract al<K, V> m2451a(K k, boolean z);

    public abstract al<K, V> m2455b(K k, boolean z);

    public abstract ImmutableCollection<V> m2458f();

    public abstract an<K> n_();

    public /* synthetic */ ai m2456d() {
        return n_();
    }

    public /* synthetic */ Set entrySet() {
        return m2453b();
    }

    public /* synthetic */ SortedMap headMap(Object obj) {
        return m2449a(obj);
    }

    public /* synthetic */ Set keySet() {
        return n_();
    }

    public /* synthetic */ SortedMap subMap(Object obj, Object obj2) {
        return m2450a(obj, obj2);
    }

    public /* synthetic */ SortedMap tailMap(Object obj) {
        return m2454b(obj);
    }

    public /* synthetic */ Collection values() {
        return m2458f();
    }

    static {
        f1628a = az.m2824b();
        f1629b = new EmptyImmutableSortedMap(f1628a);
    }

    al() {
    }

    public int size() {
        return m2458f().size();
    }

    public boolean containsValue(@Nullable Object obj) {
        return m2458f().contains(obj);
    }

    boolean m2457e() {
        return n_().m2284a() || m2458f().m2284a();
    }

    public ai<Entry<K, V>> m2453b() {
        return super.m2313b();
    }

    public Comparator<? super K> comparator() {
        return n_().comparator();
    }

    public K firstKey() {
        return n_().first();
    }

    public K lastKey() {
        return n_().last();
    }

    public al<K, V> m2449a(K k) {
        return m2451a((Object) k, false);
    }

    public al<K, V> m2450a(K k, K k2) {
        return m2452a(k, true, k2, false);
    }

    public al<K, V> m2452a(K k, boolean z, K k2, boolean z2) {
        boolean z3;
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) k2);
        if (comparator().compare(k, k2) <= 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Preconditions.m1824a(z3, "expected fromKey <= toKey but %s > %s", k, k2);
        return m2451a((Object) k2, z2).m2455b(k, z);
    }

    public al<K, V> m2454b(K k) {
        return m2455b(k, true);
    }
}
