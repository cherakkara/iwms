package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.bq.SortedLists;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: RegularImmutableSortedSet */
/* renamed from: com.google.a.c.bg */
final class bg<E> extends an<E> {
    private final transient ac<E> f1806c;

    public /* synthetic */ Iterator iterator() {
        return m2860b();
    }

    bg(ac<E> acVar, Comparator<? super E> comparator) {
        super(comparator);
        this.f1806c = acVar;
        Preconditions.m1822a(!acVar.isEmpty());
    }

    public bs<E> m2860b() {
        return this.f1806c.m2350b();
    }

    public bs<E> m2862e() {
        return this.f1806c.m_().m2350b();
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return this.f1806c.size();
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return m2853e(obj) >= 0;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public boolean containsAll(Collection<?> collection) {
        if (!bp.m2895a(comparator(), collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        Iterator b = m2860b();
        Iterator it = collection.iterator();
        Object next = it.next();
        while (b.hasNext()) {
            try {
                int a = m2467a(b.next(), next);
                if (a == 0) {
                    if (!it.hasNext()) {
                        return true;
                    }
                    next = it.next();
                } else if (a > 0) {
                    return false;
                }
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return false;
    }

    private int m2853e(Object obj) throws ClassCastException {
        return Collections.binarySearch(this.f1806c, obj, m2865i());
    }

    boolean m2858a() {
        return this.f1806c.m2284a();
    }

    public Object[] toArray() {
        return this.f1806c.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.f1806c.toArray(tArr);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (size() != set.size()) {
            return false;
        }
        if (!bp.m2895a(this.a, set)) {
            return containsAll(set);
        }
        Iterator it = set.iterator();
        try {
            Iterator b = m2860b();
            while (b.hasNext()) {
                Object next = b.next();
                Object next2 = it.next();
                if (next2 != null) {
                    if (m2467a(next, next2) != 0) {
                    }
                }
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        } catch (NoSuchElementException e2) {
            return false;
        }
    }

    public E first() {
        return this.f1806c.get(0);
    }

    public E last() {
        return this.f1806c.get(size() - 1);
    }

    an<E> m2856a(E e, boolean z) {
        return m2855a(0, m2861e(e, z));
    }

    int m2861e(E e, boolean z) {
        return bq.m2906a(this.f1806c, Preconditions.m1817a((Object) e), comparator(), z ? SortedLists.FIRST_AFTER : SortedLists.FIRST_PRESENT, SortedLists.NEXT_HIGHER);
    }

    an<E> m2857a(E e, boolean z, E e2, boolean z2) {
        return m2859b(e, z).m2468a((Object) e2, z2);
    }

    an<E> m2859b(E e, boolean z) {
        return m2855a(m2863f(e, z), size());
    }

    int m2863f(E e, boolean z) {
        return bq.m2906a(this.f1806c, Preconditions.m1817a((Object) e), comparator(), z ? SortedLists.FIRST_PRESENT : SortedLists.FIRST_AFTER, SortedLists.NEXT_HIGHER);
    }

    Comparator<Object> m2865i() {
        return this.a;
    }

    an<E> m2855a(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i < i2) {
            return new bg(this.f1806c.m2347a(i, i2), this.a);
        }
        return an.m2461a(this.a);
    }

    int m2854a(@Nullable Object obj) {
        if (obj == null) {
            return -1;
        }
        try {
            int a = bq.m2906a(this.f1806c, obj, m2865i(), SortedLists.ANY_PRESENT, SortedLists.INVERTED_INSERTION_INDEX);
            if (a < 0) {
                a = -1;
            }
            return a;
        } catch (ClassCastException e) {
            return -1;
        }
    }

    ac<E> m2864f() {
        return new ak(this, this.f1806c);
    }
}
