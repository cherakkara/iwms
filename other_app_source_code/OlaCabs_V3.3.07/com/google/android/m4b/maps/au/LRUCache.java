package com.google.android.m4b.maps.au;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.google.android.m4b.maps.au.f */
public class LRUCache<Key, Value> {
    private final HashMap<Key, LRUCache<Key, Value>> f3786a;
    private LRUCache<Key, Value> f3787b;
    private LRUCache<Key, Value> f3788c;
    private int f3789d;

    /* renamed from: com.google.android.m4b.maps.au.f.a */
    public static class LRUCache<Key, Value> implements Iterator<LRUCache<Key, Value>> {
        private LRUCache<Key, Value> f4079a;

        public final /* synthetic */ Object next() {
            return m6623a();
        }

        public LRUCache(LRUCache<Key, Value> lRUCache) {
            this.f4079a = lRUCache;
        }

        public final boolean hasNext() {
            return this.f4079a != null;
        }

        public final LRUCache<Key, Value> m6623a() {
            LRUCache<Key, Value> lRUCache = new LRUCache(this.f4079a.f4084c, this.f4079a.f4085d);
            this.f4079a = this.f4079a.f4083b;
            return lRUCache;
        }

        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: com.google.android.m4b.maps.au.f.b */
    public static class LRUCache<Key, Value> {
        public Key f4080a;
        public Value f4081b;

        public LRUCache(Key key, Value value) {
            this.f4080a = key;
            this.f4081b = value;
        }
    }

    /* renamed from: com.google.android.m4b.maps.au.f.c */
    static class LRUCache<Key, Value> {
        public LRUCache<Key, Value> f4082a;
        public LRUCache<Key, Value> f4083b;
        public Key f4084c;
        public Value f4085d;

        LRUCache() {
        }
    }

    public LRUCache(int i) {
        this.f3786a = new HashMap();
        this.f3789d = i;
    }

    public final void m6231a() {
        m6232a(0);
    }

    public final int m6234b() {
        return this.f3786a.size();
    }

    protected void m6236b(Key key, Value value) {
    }

    protected void m6233a(Key key, Value value) {
    }

    public final void m6239c(Key key, Value value) {
        LRUCache lRUCache = (LRUCache) this.f3786a.get(key);
        if (lRUCache == null) {
            m6232a(this.f3789d - 1);
        }
        LRUCache lRUCache2 = new LRUCache();
        lRUCache2.f4085d = value;
        lRUCache2.f4084c = key;
        if (lRUCache != null) {
            m6229b(lRUCache);
            m6236b(key, lRUCache.f4085d);
            m6233a(key, lRUCache.f4085d);
        }
        this.f3786a.put(lRUCache2.f4084c, lRUCache2);
        m6228a(lRUCache2);
    }

    public final Value m6230a(Key key) {
        LRUCache lRUCache = (LRUCache) this.f3786a.get(key);
        return lRUCache == null ? null : lRUCache.f4085d;
    }

    public final Value m6235b(Key key) {
        LRUCache lRUCache = (LRUCache) this.f3786a.get(key);
        if (lRUCache == null) {
            return null;
        }
        m6229b(lRUCache);
        m6228a(lRUCache);
        return lRUCache.f4085d;
    }

    public final Value m6237c() {
        if (this.f3788c == null) {
            return null;
        }
        return m6238c(this.f3788c.f4084c);
    }

    public final Value m6238c(Key key) {
        LRUCache lRUCache = (LRUCache) this.f3786a.remove(key);
        if (lRUCache == null) {
            return null;
        }
        m6229b(lRUCache);
        m6236b(key, lRUCache.f4085d);
        return lRUCache.f4085d;
    }

    public final Collection<Value> m6240d() {
        Collection arrayList = new ArrayList(this.f3786a.size());
        for (LRUCache lRUCache = this.f3787b; lRUCache != null; lRUCache = lRUCache.f4083b) {
            arrayList.add(lRUCache.f4085d);
        }
        return arrayList;
    }

    public final void m6232a(int i) {
        while (this.f3786a.size() > i) {
            m6233a(this.f3787b.f4084c, m6238c(this.f3787b.f4084c));
        }
    }

    private void m6228a(LRUCache<Key, Value> lRUCache) {
        if (this.f3788c == null) {
            this.f3787b = lRUCache;
            this.f3788c = lRUCache;
            return;
        }
        LRUCache lRUCache2 = this.f3788c;
        lRUCache.f4082a = lRUCache2;
        lRUCache2.f4083b = lRUCache;
        this.f3788c = lRUCache;
    }

    private void m6229b(LRUCache<Key, Value> lRUCache) {
        LRUCache lRUCache2 = lRUCache.f4082a;
        LRUCache lRUCache3 = lRUCache.f4083b;
        if (lRUCache2 != null) {
            lRUCache2.f4083b = lRUCache3;
        }
        if (lRUCache3 != null) {
            lRUCache3.f4082a = lRUCache2;
        }
        lRUCache.f4082a = null;
        lRUCache.f4083b = null;
        if (this.f3787b == lRUCache) {
            this.f3787b = lRUCache3;
        }
        if (this.f3788c == lRUCache) {
            this.f3788c = lRUCache2;
        }
    }

    public final LRUCache<Key, Value> m6241e() {
        return new LRUCache(this.f3787b);
    }
}
