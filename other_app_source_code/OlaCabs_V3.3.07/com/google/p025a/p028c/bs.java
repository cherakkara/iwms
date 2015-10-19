package com.google.p025a.p028c;

import java.util.Iterator;

/* compiled from: UnmodifiableIterator */
/* renamed from: com.google.a.c.bs */
public abstract class bs<E> implements Iterator<E> {
    protected bs() {
    }

    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
