package com.google.p025a.p028c;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Predicate;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: Iterables */
/* renamed from: com.google.a.c.ap */
public final class ap {

    /* renamed from: com.google.a.c.ap.1 */
    static class Iterables extends FluentIterable<T> {
        final /* synthetic */ Iterable f1634a;
        final /* synthetic */ Predicate f1635b;

        Iterables(Iterable iterable, Predicate predicate) {
            this.f1634a = iterable;
            this.f1635b = predicate;
        }

        public Iterator<T> iterator() {
            return aq.m2499a(this.f1634a.iterator(), this.f1635b);
        }
    }

    /* renamed from: com.google.a.c.ap.2 */
    static class Iterables extends FluentIterable<T> {
        final /* synthetic */ Iterable f1636a;
        final /* synthetic */ Function f1637b;

        Iterables(Iterable iterable, Function function) {
            this.f1636a = iterable;
            this.f1637b = function;
        }

        public Iterator<T> iterator() {
            return aq.m2501a(this.f1636a.iterator(), this.f1637b);
        }
    }

    public static String m2484a(Iterable<?> iterable) {
        return aq.m2507b(iterable.iterator());
    }

    public static <T> T m2486b(Iterable<T> iterable) {
        return aq.m2508c(iterable.iterator());
    }

    static Object[] m2487c(Iterable<?> iterable) {
        return ap.m2488d(iterable).toArray();
    }

    private static <E> Collection<E> m2488d(Iterable<E> iterable) {
        return iterable instanceof Collection ? (Collection) iterable : ar.m2517a(iterable.iterator());
    }

    public static <T> boolean m2485a(Collection<T> collection, Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll(Collections2.m2932a((Iterable) iterable));
        }
        return aq.m2503a((Collection) collection, iterable.iterator());
    }

    public static <T> Iterable<T> m2483a(Iterable<T> iterable, Predicate<? super T> predicate) {
        Preconditions.m1817a((Object) iterable);
        Preconditions.m1817a((Object) predicate);
        return new Iterables(iterable, predicate);
    }

    public static <F, T> Iterable<T> m2482a(Iterable<F> iterable, Function<? super F, ? extends T> function) {
        Preconditions.m1817a((Object) iterable);
        Preconditions.m1817a((Object) function);
        return new Iterables(iterable, function);
    }
}
