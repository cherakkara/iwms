package com.google.p025a.p028c;

import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.k */
final class EmptyImmutableBiMap extends ImmutableBiMap<Object, Object> {
    static final EmptyImmutableBiMap f1861a;

    public /* synthetic */ Set entrySet() {
        return m2936b();
    }

    public /* synthetic */ Set keySet() {
        return m2938d();
    }

    static {
        f1861a = new EmptyImmutableBiMap();
    }

    private EmptyImmutableBiMap() {
    }

    public ImmutableBiMap<Object, Object> l_() {
        return this;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public Object get(@Nullable Object obj) {
        return null;
    }

    public ai<Entry<Object, Object>> m2936b() {
        return ai.m2296g();
    }

    ai<Entry<Object, Object>> m2937c() {
        throw new AssertionError("should never be called");
    }

    public ai<Object> m2938d() {
        return ai.m2296g();
    }

    boolean m2939e() {
        return false;
    }
}
