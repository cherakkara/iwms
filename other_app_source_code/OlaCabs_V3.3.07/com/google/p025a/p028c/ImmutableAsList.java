package com.google.p025a.p028c;

/* renamed from: com.google.a.c.w */
abstract class ImmutableAsList<E> extends ac<E> {
    abstract ImmutableCollection<E> m2366e();

    ImmutableAsList() {
    }

    public boolean contains(Object obj) {
        return m2366e().contains(obj);
    }

    public int size() {
        return m2366e().size();
    }

    public boolean isEmpty() {
        return m2366e().isEmpty();
    }

    boolean m2365a() {
        return m2366e().m2284a();
    }
}
