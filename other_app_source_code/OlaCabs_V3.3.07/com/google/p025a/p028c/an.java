package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import javax.annotation.Nullable;

/* compiled from: ImmutableSortedSet */
/* renamed from: com.google.a.c.an */
public abstract class an<E> extends ao<E> implements bo<E>, SortedSet<E> {
    private static final Comparator<Comparable> f1630c;
    private static final an<Comparable> f1631d;
    final transient Comparator<? super E> f1632a;

    abstract int m2466a(@Nullable Object obj);

    abstract an<E> m2468a(E e, boolean z);

    abstract an<E> m2469a(E e, boolean z, E e2, boolean z2);

    abstract an<E> m2471b(E e, boolean z);

    public abstract bs<E> m2473b();

    public abstract bs<E> m2478e();

    public /* synthetic */ SortedSet headSet(Object obj) {
        return m2474c(obj);
    }

    public /* synthetic */ Iterator iterator() {
        return m2473b();
    }

    public /* synthetic */ SortedSet subSet(Object obj, Object obj2) {
        return m2470b(obj, obj2);
    }

    public /* synthetic */ SortedSet tailSet(Object obj) {
        return m2476d(obj);
    }

    static {
        f1630c = az.m2824b();
        f1631d = new EmptyImmutableSortedSet(f1630c);
    }

    private static <E> an<E> m2465i() {
        return f1631d;
    }

    static <E> an<E> m2461a(Comparator<? super E> comparator) {
        if (f1630c.equals(comparator)) {
            return an.m2465i();
        }
        return new EmptyImmutableSortedSet(comparator);
    }

    public static <E> an<E> m2462a(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        Preconditions.m1817a((Object) comparator);
        if (bp.m2895a(comparator, iterable) && (iterable instanceof an)) {
            an<E> anVar = (an) iterable;
            if (!anVar.m2284a()) {
                return anVar;
            }
        }
        Object[] c = ap.m2487c(iterable);
        return an.m2464b(comparator, c.length, c);
    }

    public static <E> an<E> m2463a(Comparator<? super E> comparator, Collection<? extends E> collection) {
        return an.m2462a((Comparator) comparator, (Iterable) collection);
    }

    static <E> int m2459a(Comparator<? super E> comparator, int i, E... eArr) {
        int i2 = 0;
        if (i != 0) {
            int i3;
            for (i3 = 0; i3 < i; i3++) {
                ay.m2830a(eArr[i3], i3);
            }
            Arrays.sort(eArr, 0, i, comparator);
            i3 = 1;
            i2 = 1;
            while (i3 < i) {
                int i4;
                Object obj = eArr[i3];
                if (comparator.compare(obj, eArr[i2 - 1]) != 0) {
                    i4 = i2 + 1;
                    eArr[i2] = obj;
                } else {
                    i4 = i2;
                }
                i3++;
                i2 = i4;
            }
            Arrays.fill(eArr, i2, i, null);
        }
        return i2;
    }

    static <E> an<E> m2464b(Comparator<? super E> comparator, int i, E... eArr) {
        int a = an.m2459a((Comparator) comparator, i, (Object[]) eArr);
        if (a == 0) {
            return an.m2461a((Comparator) comparator);
        }
        Object[] b;
        if (a < eArr.length) {
            b = ay.m2835b(eArr, a);
        }
        return new bg(ac.m2343b(b), comparator);
    }

    int m2467a(Object obj, Object obj2) {
        return an.m2460a(this.f1632a, obj, obj2);
    }

    static int m2460a(Comparator<?> comparator, Object obj, Object obj2) {
        return comparator.compare(obj, obj2);
    }

    an(Comparator<? super E> comparator) {
        this.f1632a = comparator;
    }

    public Comparator<? super E> comparator() {
        return this.f1632a;
    }

    public an<E> m2474c(E e) {
        return m2475c(e, false);
    }

    public an<E> m2475c(E e, boolean z) {
        return m2468a(Preconditions.m1817a((Object) e), z);
    }

    public an<E> m2470b(E e, E e2) {
        return m2472b(e, true, e2, false);
    }

    public an<E> m2472b(E e, boolean z, E e2, boolean z2) {
        Preconditions.m1817a((Object) e);
        Preconditions.m1817a((Object) e2);
        Preconditions.m1822a(this.f1632a.compare(e, e2) <= 0);
        return m2469a(e, z, e2, z2);
    }

    public an<E> m2476d(E e) {
        return m2477d(e, true);
    }

    public an<E> m2477d(E e, boolean z) {
        return m2471b(Preconditions.m1817a((Object) e), z);
    }

    public E first() {
        return m2473b().next();
    }

    public E last() {
        return m2478e().next();
    }
}
