package com.google.p025a.p028c;

import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: Multimap */
/* renamed from: com.google.a.c.av */
public interface av<K, V> {
    boolean m2375a(@Nullable K k, Iterable<? extends V> iterable);

    boolean m2376a(@Nullable K k, @Nullable V v);

    int m2377b();

    Collection<V> m2378b(@Nullable K k);

    boolean m2379f();

    Map<K, Collection<V>> m2380h();
}
