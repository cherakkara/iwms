package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.io.Serializable;

/* compiled from: ReverseNaturalOrdering */
/* renamed from: com.google.a.c.bh */
final class bh extends az<Comparable> implements Serializable {
    static final bh f1807a;

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m2866a((Comparable) obj, (Comparable) obj2);
    }

    static {
        f1807a = new bh();
    }

    public int m2866a(Comparable comparable, Comparable comparable2) {
        Preconditions.m1817a((Object) comparable);
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    public <S extends Comparable> az<S> m2867a() {
        return az.m2824b();
    }

    public String toString() {
        return "Ordering.natural().reverse()";
    }

    private bh() {
    }
}
