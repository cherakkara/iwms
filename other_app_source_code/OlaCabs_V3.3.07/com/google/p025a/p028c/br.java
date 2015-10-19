package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Iterator;

/* compiled from: TransformedIterator */
/* renamed from: com.google.a.c.br */
abstract class br<F, T> implements Iterator<T> {
    final Iterator<? extends F> f1647b;

    abstract T m2494a(F f);

    br(Iterator<? extends F> it) {
        this.f1647b = (Iterator) Preconditions.m1817a((Object) it);
    }

    public final boolean hasNext() {
        return this.f1647b.hasNext();
    }

    public final T next() {
        return m2494a(this.f1647b.next());
    }

    public final void remove() {
        this.f1647b.remove();
    }
}
