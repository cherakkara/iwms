package com.google.p025a.p028c;

import java.util.ListIterator;

/* compiled from: RegularImmutableAsList */
/* renamed from: com.google.a.c.bc */
class bc<E> extends ImmutableAsList<E> {
    private final ImmutableCollection<E> f1626a;
    private final ac<? extends E> f1627c;

    public /* synthetic */ ListIterator listIterator(int i) {
        return m2444a(i);
    }

    bc(ImmutableCollection<E> immutableCollection, ac<? extends E> acVar) {
        this.f1626a = immutableCollection;
        this.f1627c = acVar;
    }

    bc(ImmutableCollection<E> immutableCollection, Object[] objArr) {
        this((ImmutableCollection) immutableCollection, ac.m2343b(objArr));
    }

    ImmutableCollection<E> m2445e() {
        return this.f1626a;
    }

    public bt<E> m2444a(int i) {
        return this.f1627c.m2348a(i);
    }

    public Object[] toArray() {
        return this.f1627c.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.f1627c.toArray(tArr);
    }

    public int indexOf(Object obj) {
        return this.f1627c.indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return this.f1627c.lastIndexOf(obj);
    }

    public boolean equals(Object obj) {
        return this.f1627c.equals(obj);
    }

    public int hashCode() {
        return this.f1627c.hashCode();
    }

    public E get(int i) {
        return this.f1627c.get(i);
    }
}
