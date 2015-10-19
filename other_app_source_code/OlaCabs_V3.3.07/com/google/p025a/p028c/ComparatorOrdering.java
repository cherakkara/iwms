package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.j */
final class ComparatorOrdering<T> extends az<T> implements Serializable {
    final Comparator<T> f1860a;

    ComparatorOrdering(Comparator<T> comparator) {
        this.f1860a = (Comparator) Preconditions.m1817a((Object) comparator);
    }

    public int compare(T t, T t2) {
        return this.f1860a.compare(t, t2);
    }

    public <E extends T> ac<E> m2935a(Iterable<E> iterable) {
        Object[] c = ap.m2487c(iterable);
        for (Object a : c) {
            Preconditions.m1817a(a);
        }
        Arrays.sort(c, this.f1860a);
        return ac.m2343b(c);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComparatorOrdering)) {
            return false;
        }
        return this.f1860a.equals(((ComparatorOrdering) obj).f1860a);
    }

    public int hashCode() {
        return this.f1860a.hashCode();
    }

    public String toString() {
        return this.f1860a.toString();
    }
}
