package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Comparator;
import java.util.SortedSet;

/* compiled from: SortedIterables */
/* renamed from: com.google.a.c.bp */
final class bp {
    public static boolean m2895a(Comparator<?> comparator, Iterable<?> iterable) {
        Object a;
        Preconditions.m1817a((Object) comparator);
        Preconditions.m1817a((Object) iterable);
        if (iterable instanceof SortedSet) {
            a = bp.m2894a((SortedSet) iterable);
        } else if (!(iterable instanceof bo)) {
            return false;
        } else {
            a = ((bo) iterable).comparator();
        }
        return comparator.equals(a);
    }

    public static <E> Comparator<? super E> m2894a(SortedSet<E> sortedSet) {
        Comparator<? super E> comparator = sortedSet.comparator();
        if (comparator == null) {
            return az.m2824b();
        }
        return comparator;
    }
}
