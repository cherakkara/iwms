package com.google.p025a.p028c;

import java.io.Serializable;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.z */
class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
    private final K f1664a;
    private final V f1665b;

    ImmutableEntry(@Nullable K k, @Nullable V v) {
        this.f1664a = k;
        this.f1665b = v;
    }

    @Nullable
    public K getKey() {
        return this.f1664a;
    }

    @Nullable
    public V getValue() {
        return this.f1665b;
    }

    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }
}
