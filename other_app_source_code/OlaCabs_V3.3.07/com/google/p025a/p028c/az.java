package com.google.p025a.p028c;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Preconditions;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;

/* compiled from: Ordering */
/* renamed from: com.google.a.c.az */
public abstract class az<T> implements Comparator<T> {
    public abstract int compare(@Nullable T t, @Nullable T t2);

    public static <C extends Comparable> az<C> m2824b() {
        return ax.f1787a;
    }

    public static <T> az<T> m2823a(Comparator<T> comparator) {
        return comparator instanceof az ? (az) comparator : new ComparatorOrdering(comparator);
    }

    protected az() {
    }

    public <S extends T> az<S> m2826a() {
        return new bi(this);
    }

    public <F> az<F> m2827a(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    public <E extends T> ac<E> m2825a(Iterable<E> iterable) {
        Object[] c = ap.m2487c(iterable);
        for (Object a : c) {
            Preconditions.m1817a(a);
        }
        Arrays.sort(c, this);
        return ac.m2343b(c);
    }
}
