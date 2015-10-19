package com.google.p025a.p028c;

import java.util.Comparator;
import javax.annotation.Nullable;

/* compiled from: ImmutableSortedAsList */
/* renamed from: com.google.a.c.ak */
final class ak<E> extends bc<E> implements bo<E> {
    /* synthetic */ ImmutableCollection m2447e() {
        return m2448i();
    }

    ak(an<E> anVar, ac<E> acVar) {
        super((ImmutableCollection) anVar, (ac) acVar);
    }

    an<E> m2448i() {
        return (an) super.m2445e();
    }

    public Comparator<? super E> comparator() {
        return m2448i().comparator();
    }

    public int indexOf(@Nullable Object obj) {
        int a = m2448i().m2466a(obj);
        return (a < 0 || !get(a).equals(obj)) ? -1 : a;
    }

    public int lastIndexOf(@Nullable Object obj) {
        return indexOf(obj);
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    ac<E> m2446b(int i, int i2) {
        return new bg(super.m2349b(i, i2), comparator()).m2286c();
    }
}
