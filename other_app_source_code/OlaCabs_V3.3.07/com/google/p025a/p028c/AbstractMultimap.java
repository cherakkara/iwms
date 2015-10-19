package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.au.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.e */
abstract class AbstractMultimap<K, V> implements av<K, V> {
    private transient Set<K> f1615a;
    private transient Map<K, Collection<V>> f1616b;

    /* renamed from: com.google.a.c.e.1 */
    class AbstractMultimap extends Maps<K, Collection<V>> {
        final /* synthetic */ AbstractMultimap f1855a;

        AbstractMultimap(AbstractMultimap abstractMultimap) {
            this.f1855a = abstractMultimap;
        }

        Map<K, Collection<V>> m2928a() {
            return this.f1855a.m2387h();
        }
    }

    abstract Map<K, Collection<V>> m2384e();

    AbstractMultimap() {
    }

    public boolean m2385f() {
        return m2377b() == 0;
    }

    public boolean m2382a(@Nullable K k, @Nullable V v) {
        return m2378b(k).add(v);
    }

    public boolean m2381a(@Nullable K k, Iterable<? extends V> iterable) {
        Preconditions.m1817a((Object) iterable);
        return iterable.iterator().hasNext() && ap.m2485a(m2378b(k), (Iterable) iterable);
    }

    public Set<K> m2386g() {
        Set<K> set = this.f1615a;
        if (set != null) {
            return set;
        }
        set = m2383d();
        this.f1615a = set;
        return set;
    }

    Set<K> m2383d() {
        return new AbstractMultimap(this);
    }

    public Map<K, Collection<V>> m2387h() {
        Map<K, Collection<V>> map = this.f1616b;
        if (map != null) {
            return map;
        }
        map = m2384e();
        this.f1616b = map;
        return map;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof av)) {
            return false;
        }
        return m2387h().equals(((av) obj).m2380h());
    }

    public int hashCode() {
        return m2387h().hashCode();
    }

    public String toString() {
        return m2387h().toString();
    }
}
