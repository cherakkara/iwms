package com.google.p025a.p027b;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p034i.p035a.Futures;
import com.google.p025a.p034i.p035a.ListenableFuture;

/* renamed from: com.google.a.b.c */
public abstract class CacheLoader<K, V> {

    /* renamed from: com.google.a.b.c.a */
    public static final class CacheLoader extends RuntimeException {
        public CacheLoader(String str) {
            super(str);
        }
    }

    public abstract V m1915a(K k) throws Exception;

    protected CacheLoader() {
    }

    public ListenableFuture<V> m1914a(K k, V v) throws Exception {
        Preconditions.m1817a((Object) k);
        Preconditions.m1817a((Object) v);
        return Futures.m3134a(m1915a(k));
    }
}
