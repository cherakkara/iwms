package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.l */
final class EmptyImmutableList extends ac<Object> {
    static final EmptyImmutableList f1862a;

    public /* synthetic */ Iterator iterator() {
        return m2943b();
    }

    public /* synthetic */ ListIterator listIterator() {
        return m2944d();
    }

    public /* synthetic */ ListIterator listIterator(int i) {
        return m2941a(i);
    }

    public /* synthetic */ List subList(int i, int i2) {
        return m2940a(i, i2);
    }

    static {
        f1862a = new EmptyImmutableList();
    }

    private EmptyImmutableList() {
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    boolean m2942a() {
        return false;
    }

    public boolean contains(@Nullable Object obj) {
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        return collection.isEmpty();
    }

    public bs<Object> m2943b() {
        return m2944d();
    }

    public Object[] toArray() {
        return ay.f1788a;
    }

    public <T> T[] toArray(T[] tArr) {
        if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    public Object get(int i) {
        Preconditions.m1815a(i, 0);
        throw new AssertionError("unreachable");
    }

    public int indexOf(@Nullable Object obj) {
        return -1;
    }

    public int lastIndexOf(@Nullable Object obj) {
        return -1;
    }

    public ac<Object> m2940a(int i, int i2) {
        Preconditions.m1821a(i, i2, 0);
        return this;
    }

    public ac<Object> m_() {
        return this;
    }

    public bt<Object> m2944d() {
        return aq.f1649a;
    }

    public bt<Object> m2941a(int i) {
        Preconditions.m1825b(i, 0);
        return aq.f1649a;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof List) {
            return ((List) obj).isEmpty();
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "[]";
    }
}
