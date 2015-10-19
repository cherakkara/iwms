package com.google.p025a.p028c;

import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.t */
public abstract class ForwardingSet<E> extends ForwardingCollection<E> implements Set<E> {
    protected abstract Set<E> m2969c();

    protected /* synthetic */ Collection m2967a() {
        return m2969c();
    }

    protected /* synthetic */ Object m2968b() {
        return m2969c();
    }

    protected ForwardingSet() {
    }

    public boolean equals(@Nullable Object obj) {
        return obj == this || m2969c().equals(obj);
    }

    public int hashCode() {
        return m2969c().hashCode();
    }
}
