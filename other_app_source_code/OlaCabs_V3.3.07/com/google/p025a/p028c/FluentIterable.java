package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Predicate;
import java.util.Iterator;

/* renamed from: com.google.a.c.q */
public abstract class FluentIterable<E> implements Iterable<E> {
    private final Iterable<E> f1633a;

    /* renamed from: com.google.a.c.q.1 */
    static class FluentIterable extends FluentIterable<E> {
        final /* synthetic */ Iterable f1866a;

        FluentIterable(Iterable iterable, Iterable iterable2) {
            this.f1866a = iterable2;
            super(iterable);
        }

        public Iterator<E> iterator() {
            return this.f1866a.iterator();
        }
    }

    protected FluentIterable() {
        this.f1633a = this;
    }

    FluentIterable(Iterable<E> iterable) {
        this.f1633a = (Iterable) Preconditions.m1817a((Object) iterable);
    }

    public static <E> FluentIterable<E> m2479a(Iterable<E> iterable) {
        return iterable instanceof FluentIterable ? (FluentIterable) iterable : new FluentIterable(iterable, iterable);
    }

    public String toString() {
        return ap.m2484a(this.f1633a);
    }

    public final FluentIterable<E> m2481a(Predicate<? super E> predicate) {
        return FluentIterable.m2479a(ap.m2483a(this.f1633a, (Predicate) predicate));
    }

    public final ai<E> m2480a() {
        return ai.m2290a(this.f1633a);
    }
}
