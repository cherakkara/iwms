package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.io.Serializable;

/* compiled from: NaturalOrdering */
/* renamed from: com.google.a.c.ax */
final class ax extends az<Comparable> implements Serializable {
    static final ax f1787a;

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m2828a((Comparable) obj, (Comparable) obj2);
    }

    static {
        f1787a = new ax();
    }

    public int m2828a(Comparable comparable, Comparable comparable2) {
        Preconditions.m1817a((Object) comparable);
        Preconditions.m1817a((Object) comparable2);
        if (comparable == comparable2) {
            return 0;
        }
        return comparable.compareTo(comparable2);
    }

    public <S extends Comparable> az<S> m2829a() {
        return bh.f1807a;
    }

    public String toString() {
        return "Ordering.natural()";
    }

    private ax() {
    }
}
