package com.google.android.m4b.maps.bj;

import java.util.HashMap;

/* renamed from: com.google.android.m4b.maps.bj.u */
public class LRUCache<Key, Value> {
    protected final int f6492a;
    private final HashMap<Key, LRUCache<Key, Value>> f6493b;
    private LRUCache<Key, Value> f6494c;
    private LRUCache<Key, Value> f6495d;

    /* renamed from: com.google.android.m4b.maps.bj.u.a */
    static class LRUCache<K, V> {
        public LRUCache<K, V> f6626a;
        public LRUCache<K, V> f6627b;
        public K f6628c;
        public V f6629d;

        LRUCache() {
        }
    }

    public LRUCache(int i) {
        this.f6493b = new HashMap();
        this.f6492a = i;
    }

    public final void m9924b() {
        m9918a(0);
    }

    protected void m9922a(Key key, Value value) {
    }

    public final void m9925b(Key key, Value value) {
        LRUCache lRUCache = (LRUCache) this.f6493b.get(key);
        if (lRUCache == null) {
            m9918a(this.f6492a - 1);
        }
        LRUCache lRUCache2 = new LRUCache();
        lRUCache2.f6629d = value;
        lRUCache2.f6628c = key;
        if (lRUCache != null) {
            m9920b(lRUCache);
            m9922a(key, lRUCache.f6629d);
            Object obj = lRUCache.f6629d;
        }
        this.f6493b.put(lRUCache2.f6628c, lRUCache2);
        m9919a(lRUCache2);
    }

    public final Value m9921a(Key key) {
        LRUCache lRUCache = (LRUCache) this.f6493b.get(key);
        if (lRUCache == null) {
            return null;
        }
        m9920b(lRUCache);
        m9919a(lRUCache);
        return lRUCache.f6629d;
    }

    public final Value m9923b(Key key) {
        LRUCache lRUCache = (LRUCache) this.f6493b.remove(key);
        if (lRUCache == null) {
            return null;
        }
        m9920b(lRUCache);
        m9922a(key, lRUCache.f6629d);
        return lRUCache.f6629d;
    }

    private void m9918a(int i) {
        while (this.f6493b.size() > i) {
            Object obj = this.f6494c.f6628c;
            m9923b(this.f6494c.f6628c);
        }
    }

    private void m9919a(LRUCache<Key, Value> lRUCache) {
        if (this.f6495d == null) {
            this.f6494c = lRUCache;
            this.f6495d = lRUCache;
            return;
        }
        LRUCache lRUCache2 = this.f6495d;
        lRUCache.f6626a = lRUCache2;
        lRUCache2.f6627b = lRUCache;
        this.f6495d = lRUCache;
    }

    private void m9920b(LRUCache<Key, Value> lRUCache) {
        LRUCache lRUCache2 = lRUCache.f6626a;
        LRUCache lRUCache3 = lRUCache.f6627b;
        if (lRUCache2 != null) {
            lRUCache2.f6627b = lRUCache3;
        }
        if (lRUCache3 != null) {
            lRUCache3.f6626a = lRUCache2;
        }
        lRUCache.f6626a = null;
        lRUCache.f6627b = null;
        if (this.f6494c == lRUCache) {
            this.f6494c = lRUCache3;
        }
        if (this.f6495d == lRUCache) {
            this.f6495d = lRUCache2;
        }
    }

    protected final void m9926c() {
        m9918a(this.f6492a - 1);
    }
}
