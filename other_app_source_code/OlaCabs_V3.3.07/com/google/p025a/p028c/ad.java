package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: ImmutableMap */
/* renamed from: com.google.a.c.ad */
public abstract class ad<K, V> implements Serializable, Map<K, V> {
    private transient ai<Entry<K, V>> f1589a;
    private transient ai<K> f1590b;
    private transient ImmutableCollection<V> f1591c;

    /* renamed from: com.google.a.c.ad.a */
    public static class ImmutableMap<K, V> {
        final ArrayList<Entry<K, V>> f1605a;

        public ImmutableMap() {
            this.f1605a = ar.m2515a();
        }

        public ImmutableMap<K, V> m2362a(K k, V v) {
            this.f1605a.add(ad.m2309c(k, v));
            return this;
        }

        public ImmutableMap<K, V> m2363a(Map<? extends K, ? extends V> map) {
            this.f1605a.ensureCapacity(this.f1605a.size() + map.size());
            for (Entry entry : map.entrySet()) {
                m2362a(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public ad<K, V> m2364a() {
            return ImmutableMap.m2361a(this.f1605a);
        }

        private static <K, V> ad<K, V> m2361a(List<Entry<K, V>> list) {
            switch (list.size()) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    return ad.m2310i();
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return new bl((Entry) ap.m2486b(list));
                default:
                    return new be((Entry[]) list.toArray(new Entry[list.size()]));
            }
        }
    }

    abstract ai<Entry<K, V>> m2314c();

    abstract boolean m2316e();

    public abstract V get(@Nullable Object obj);

    public /* synthetic */ Set entrySet() {
        return m2313b();
    }

    public /* synthetic */ Set keySet() {
        return m2315d();
    }

    public /* synthetic */ Collection values() {
        return m2317f();
    }

    public static <K, V> ad<K, V> m2310i() {
        return ImmutableBiMap.m2882g();
    }

    public static <K, V> ad<K, V> m2308b(K k, V v) {
        return ImmutableBiMap.m2881a(k, v);
    }

    public static <K, V> ImmutableMap<K, V> m2311j() {
        return new ImmutableMap();
    }

    static <K, V> Entry<K, V> m2309c(K k, V v) {
        Preconditions.m1819a((Object) k, "null key in entry: null=%s", v);
        Preconditions.m1819a((Object) v, "null value in entry: %s=null", k);
        return au.m2811a((Object) k, (Object) v);
    }

    public static <K, V> ad<K, V> m2307a(Map<? extends K, ? extends V> map) {
        int i = 0;
        if ((map instanceof ad) && !(map instanceof al)) {
            ad<K, V> adVar = (ad) map;
            if (!adVar.m2316e()) {
                return adVar;
            }
        } else if (map instanceof EnumMap) {
            EnumMap enumMap = (EnumMap) map;
            for (Entry entry : enumMap.entrySet()) {
                Preconditions.m1817a(entry.getKey());
                Preconditions.m1817a(entry.getValue());
            }
            return aa.m2318a(new EnumMap(enumMap));
        }
        Entry[] entryArr = (Entry[]) map.entrySet().toArray(new Entry[0]);
        switch (entryArr.length) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ad.m2310i();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return new bl(ad.m2309c(entryArr[0].getKey(), entryArr[0].getValue()));
        }
        while (i < entryArr.length) {
            entryArr[i] = ad.m2309c(entryArr[i].getKey(), entryArr[i].getValue());
            i++;
        }
        return new be(entryArr);
    }

    ad() {
    }

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(@Nullable Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@Nullable Object obj) {
        return obj != null && au.m2821e(this, obj);
    }

    public ai<Entry<K, V>> m2313b() {
        ai<Entry<K, V>> aiVar = this.f1589a;
        if (aiVar != null) {
            return aiVar;
        }
        aiVar = m2314c();
        this.f1589a = aiVar;
        return aiVar;
    }

    public ai<K> m2315d() {
        ai<K> aiVar = this.f1590b;
        if (aiVar != null) {
            return aiVar;
        }
        aiVar = m2312a();
        this.f1590b = aiVar;
        return aiVar;
    }

    ai<K> m2312a() {
        return new af(this);
    }

    public ImmutableCollection<V> m2317f() {
        ImmutableCollection<V> immutableCollection = this.f1591c;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        immutableCollection = new ag(this);
        this.f1591c = immutableCollection;
        return immutableCollection;
    }

    public boolean equals(@Nullable Object obj) {
        return au.m2820d(this, obj);
    }

    public int hashCode() {
        return m2313b().hashCode();
    }

    public String toString() {
        return au.m2813b((Map) this);
    }
}
