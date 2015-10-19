package com.google.p025a.p028c;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.m */
final class EmptyImmutableSet extends ai<Object> {
    static final EmptyImmutableSet f1863a;

    public /* synthetic */ Iterator iterator() {
        return m2946b();
    }

    static {
        f1863a = new EmptyImmutableSet();
    }

    private EmptyImmutableSet() {
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

    public bs<Object> m2946b() {
        return aq.m2496a();
    }

    boolean m2945a() {
        return false;
    }

    public Object[] toArray() {
        return ay.f1788a;
    }

    public <T> T[] toArray(T[] tArr) {
        return m2947c().toArray(tArr);
    }

    public ac<Object> m2947c() {
        return ac.m2345g();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public final int hashCode() {
        return 0;
    }

    boolean m2948d() {
        return true;
    }

    public String toString() {
        return "[]";
    }
}
