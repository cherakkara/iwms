package com.google.p025a.p028c;

import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.f */
public abstract class AbstractSequentialIterator<T> extends bs<T> {
    private T f1448a;

    protected abstract T m2031a(T t);

    protected AbstractSequentialIterator(@Nullable T t) {
        this.f1448a = t;
    }

    public final boolean hasNext() {
        return this.f1448a != null;
    }

    public final T next() {
        if (hasNext()) {
            try {
                T t = this.f1448a;
                return t;
            } finally {
                this.f1448a = m2031a(this.f1448a);
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
