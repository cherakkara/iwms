package com.google.p025a.p028c;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ad.ImmutableMap;
import com.google.p025a.p028c.ah.ImmutableMultimap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nullable;

/* compiled from: ImmutableSetMultimap */
/* renamed from: com.google.a.c.aj */
public class aj<K, V> extends ah<K, V> implements bj<K, V> {
    private final transient an<V> f1625a;

    /* renamed from: com.google.a.c.aj.a */
    public static final class ImmutableSetMultimap<K, V> extends ImmutableMultimap<K, V> {

        /* renamed from: com.google.a.c.aj.a.1 */
        class ImmutableSetMultimap implements Function<Entry<K, Collection<V>>, K> {
            final /* synthetic */ ImmutableSetMultimap f1624a;

            ImmutableSetMultimap(ImmutableSetMultimap immutableSetMultimap) {
                this.f1624a = immutableSetMultimap;
            }

            public K m2433a(Entry<K, Collection<V>> entry) {
                return entry.getKey();
            }
        }

        public ImmutableSetMultimap() {
            this.a = new ImmutableSetMultimap();
        }

        public ImmutableSetMultimap<K, V> m2434a(K k, V v) {
            this.a.m2376a(Preconditions.m1817a((Object) k), Preconditions.m1817a((Object) v));
            return this;
        }

        public aj<K, V> m2435a() {
            if (this.b != null) {
                av immutableSetMultimap = new ImmutableSetMultimap();
                List<Entry> a = ar.m2516a(this.a.m2380h().entrySet());
                Collections.sort(a, az.m2823a(this.b).m2827a(new ImmutableSetMultimap(this)));
                for (Entry entry : a) {
                    immutableSetMultimap.m2375a(entry.getKey(), (Iterable) entry.getValue());
                }
                this.a = immutableSetMultimap;
            }
            return aj.m2438b(this.a, this.c);
        }
    }

    /* renamed from: com.google.a.c.aj.b */
    private static class ImmutableSetMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
        ImmutableSetMultimap() {
            super(new LinkedHashMap());
        }

        Collection<V> m2436a() {
            return bk.m2878b();
        }
    }

    public /* synthetic */ ImmutableCollection m2441a(Object obj) {
        return m2443c(obj);
    }

    public /* synthetic */ Collection m2442b(Object obj) {
        return m2443c(obj);
    }

    public static <K, V> aj<K, V> m2439j() {
        return EmptyImmutableSetMultimap.f1864a;
    }

    public static <K, V> ImmutableSetMultimap<K, V> m2440k() {
        return new ImmutableSetMultimap();
    }

    private static <K, V> aj<K, V> m2438b(av<? extends K, ? extends V> avVar, Comparator<? super V> comparator) {
        Preconditions.m1817a((Object) avVar);
        if (avVar.m2379f() && comparator == null) {
            return aj.m2439j();
        }
        if (avVar instanceof aj) {
            aj<K, V> ajVar = (aj) avVar;
            if (!ajVar.m2410a()) {
                return ajVar;
            }
        }
        ImmutableMap j = ad.m2311j();
        int i = 0;
        for (Entry entry : avVar.m2380h().entrySet()) {
            int i2;
            Object key = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            ai a = comparator == null ? ai.m2291a(collection) : an.m2463a((Comparator) comparator, collection);
            if (a.isEmpty()) {
                i2 = i;
            } else {
                j.m2362a(key, a);
                i2 = a.size() + i;
            }
            i = i2;
        }
        return new aj(j.m2364a(), i, comparator);
    }

    aj(ad<K, ai<V>> adVar, int i, @Nullable Comparator<? super V> comparator) {
        super(adVar, i);
        this.f1625a = comparator == null ? null : an.m2461a((Comparator) comparator);
    }

    public ai<V> m2443c(@Nullable K k) {
        ai<V> aiVar = (ai) this.b.get(k);
        if (aiVar != null) {
            return aiVar;
        }
        if (this.f1625a != null) {
            return this.f1625a;
        }
        return ai.m2296g();
    }
}
