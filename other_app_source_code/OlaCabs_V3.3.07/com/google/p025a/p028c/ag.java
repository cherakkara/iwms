package com.google.p025a.p028c;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: ImmutableMapValues */
/* renamed from: com.google.a.c.ag */
final class ag<K, V> extends ImmutableCollection<V> {
    private final ad<K, V> f1611a;

    /* renamed from: com.google.a.c.ag.1 */
    class ImmutableMapValues extends ImmutableAsList<V> {
        final /* synthetic */ ac f1609a;
        final /* synthetic */ ag f1610c;

        ImmutableMapValues(ag agVar, ac acVar) {
            this.f1610c = agVar;
            this.f1609a = acVar;
        }

        public V get(int i) {
            return ((Entry) this.f1609a.get(i)).getValue();
        }

        ImmutableCollection<V> m2371e() {
            return this.f1610c;
        }
    }

    public /* synthetic */ Iterator iterator() {
        return m2373b();
    }

    ag(ad<K, V> adVar) {
        this.f1611a = adVar;
    }

    public int size() {
        return this.f1611a.size();
    }

    public bs<V> m2373b() {
        return au.m2805a(this.f1611a.m2313b().m2298b());
    }

    public boolean contains(Object obj) {
        return this.f1611a.containsValue(obj);
    }

    boolean m2372a() {
        return true;
    }

    ac<V> m2374f() {
        return new ImmutableMapValues(this, this.f1611a.m2313b().m2286c());
    }
}
