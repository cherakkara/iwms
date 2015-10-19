package com.google.p025a.p028c;

import java.util.Iterator;
import java.util.Map.Entry;
import javax.annotation.Nullable;

/* compiled from: ImmutableMapKeySet */
/* renamed from: com.google.a.c.af */
final class af<K, V> extends ai<K> {
    private final ad<K, V> f1608a;

    /* renamed from: com.google.a.c.af.1 */
    class ImmutableMapKeySet extends ImmutableAsList<K> {
        final /* synthetic */ ac f1606a;
        final /* synthetic */ af f1607c;

        ImmutableMapKeySet(af afVar, ac acVar) {
            this.f1607c = afVar;
            this.f1606a = acVar;
        }

        public K get(int i) {
            return ((Entry) this.f1606a.get(i)).getKey();
        }

        ImmutableCollection<K> m2367e() {
            return this.f1607c;
        }
    }

    public /* synthetic */ Iterator iterator() {
        return m2369b();
    }

    af(ad<K, V> adVar) {
        this.f1608a = adVar;
    }

    public int size() {
        return this.f1608a.size();
    }

    public bs<K> m2369b() {
        return m2286c().m2350b();
    }

    public boolean contains(@Nullable Object obj) {
        return this.f1608a.containsKey(obj);
    }

    ac<K> m2370f() {
        return new ImmutableMapKeySet(this, this.f1608a.m2313b().m2286c());
    }

    boolean m2368a() {
        return true;
    }
}
