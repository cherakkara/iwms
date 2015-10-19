package com.google.p025a.p028c;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: ImmutableMultimap */
/* renamed from: com.google.a.c.ah */
public abstract class ah<K, V> extends AbstractMultimap<K, V> implements Serializable {
    final transient ad<K, ? extends ImmutableCollection<V>> f1619b;
    final transient int f1620c;

    /* renamed from: com.google.a.c.ah.a */
    public static class ImmutableMultimap<K, V> {
        av<K, V> f1612a;
        Comparator<? super K> f1613b;
        Comparator<? super V> f1614c;

        public ImmutableMultimap() {
            this.f1612a = new ImmutableMultimap();
        }
    }

    /* renamed from: com.google.a.c.ah.b */
    private static class ImmutableMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
        ImmutableMultimap() {
            super(new LinkedHashMap());
        }

        Collection<V> m2408a() {
            return ar.m2515a();
        }
    }

    public abstract ImmutableCollection<V> m2409a(K k);

    public /* synthetic */ Collection m2414b(Object obj) {
        return m2409a(obj);
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ boolean m2417f() {
        return super.m2385f();
    }

    public /* synthetic */ Set m2418g() {
        return m2415c();
    }

    public /* synthetic */ Map m2419h() {
        return m2420i();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    ah(ad<K, ? extends ImmutableCollection<V>> adVar, int i) {
        this.f1619b = adVar;
        this.f1620c = i;
    }

    @Deprecated
    public boolean m2412a(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public boolean m2411a(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    boolean m2410a() {
        return this.f1619b.m2316e();
    }

    public int m2413b() {
        return this.f1620c;
    }

    public ai<K> m2415c() {
        return this.f1619b.m2315d();
    }

    public ad<K, Collection<V>> m2420i() {
        return this.f1619b;
    }

    Map<K, Collection<V>> m2416e() {
        throw new AssertionError("should never be called");
    }
}
