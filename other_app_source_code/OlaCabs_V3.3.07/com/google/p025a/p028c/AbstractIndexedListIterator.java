package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.NoSuchElementException;

/* renamed from: com.google.a.c.a */
abstract class AbstractIndexedListIterator<E> extends bt<E> {
    private final int f1580a;
    private int f1581b;

    protected abstract E m2283a(int i);

    protected AbstractIndexedListIterator(int i, int i2) {
        Preconditions.m1825b(i2, i);
        this.f1580a = i;
        this.f1581b = i2;
    }

    public final boolean hasNext() {
        return this.f1581b < this.f1580a;
    }

    public final E next() {
        if (hasNext()) {
            int i = this.f1581b;
            this.f1581b = i + 1;
            return m2283a(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.f1581b;
    }

    public final boolean hasPrevious() {
        return this.f1581b > 0;
    }

    public final E previous() {
        if (hasPrevious()) {
            int i = this.f1581b - 1;
            this.f1581b = i;
            return m2283a(i);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.f1581b - 1;
    }
}
