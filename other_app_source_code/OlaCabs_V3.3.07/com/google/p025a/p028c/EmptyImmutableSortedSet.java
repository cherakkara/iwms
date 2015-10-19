package com.google.p025a.p028c;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.p */
class EmptyImmutableSortedSet<E> extends an<E> {
    public /* synthetic */ Iterator iterator() {
        return m2961b();
    }

    EmptyImmutableSortedSet(Comparator<? super E> comparator) {
        super(comparator);
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean contains(@Nullable Object obj) {
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        return collection.isEmpty();
    }

    public bs<E> m2961b() {
        return aq.m2496a();
    }

    public bs<E> m2963e() {
        return aq.m2496a();
    }

    boolean m2959a() {
        return false;
    }

    public ac<E> m2962c() {
        return ac.m2345g();
    }

    public Object[] toArray() {
        return ay.f1788a;
    }

    public <T> T[] toArray(T[] tArr) {
        return m2962c().toArray(tArr);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "[]";
    }

    public E first() {
        throw new NoSuchElementException();
    }

    public E last() {
        throw new NoSuchElementException();
    }

    an<E> m2957a(E e, boolean z) {
        return this;
    }

    an<E> m2958a(E e, boolean z, E e2, boolean z2) {
        return this;
    }

    an<E> m2960b(E e, boolean z) {
        return this;
    }

    int m2956a(@Nullable Object obj) {
        return -1;
    }
}
