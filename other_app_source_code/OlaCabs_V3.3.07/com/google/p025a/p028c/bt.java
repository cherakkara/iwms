package com.google.p025a.p028c;

import java.util.ListIterator;

/* compiled from: UnmodifiableListIterator */
/* renamed from: com.google.a.c.bt */
public abstract class bt<E> extends bs<E> implements ListIterator<E> {
    protected bt() {
    }

    @Deprecated
    public final void add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void set(E e) {
        throw new UnsupportedOperationException();
    }
}
