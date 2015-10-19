package com.google.p025a.p028c;

import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p032g.Ints;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import javax.annotation.Nullable;

/* compiled from: Lists */
/* renamed from: com.google.a.c.ar */
public final class ar {

    /* renamed from: com.google.a.c.ar.b */
    private static class Lists<T> extends AbstractList<T> {
        private final List<T> f1651a;

        /* renamed from: com.google.a.c.ar.b.1 */
        class Lists implements ListIterator<T> {
            boolean f1652a;
            boolean f1653b;
            final /* synthetic */ ListIterator f1654c;
            final /* synthetic */ Lists f1655d;

            Lists(Lists lists, ListIterator listIterator) {
                this.f1655d = lists;
                this.f1654c = listIterator;
            }

            public void add(T t) {
                this.f1654c.add(t);
                this.f1654c.previous();
                this.f1652a = false;
                this.f1653b = false;
            }

            public boolean hasNext() {
                return this.f1654c.hasPrevious();
            }

            public boolean hasPrevious() {
                return this.f1654c.hasNext();
            }

            public T next() {
                if (hasNext()) {
                    this.f1652a = true;
                    this.f1653b = true;
                    return this.f1654c.previous();
                }
                throw new NoSuchElementException();
            }

            public int nextIndex() {
                return this.f1655d.m2512b(this.f1654c.nextIndex());
            }

            public T previous() {
                if (hasPrevious()) {
                    this.f1652a = true;
                    this.f1653b = true;
                    return this.f1654c.next();
                }
                throw new NoSuchElementException();
            }

            public int previousIndex() {
                return nextIndex() - 1;
            }

            public void remove() {
                Preconditions.m1828b(this.f1652a);
                this.f1654c.remove();
                this.f1653b = false;
                this.f1652a = false;
            }

            public void set(T t) {
                Preconditions.m1828b(this.f1653b);
                this.f1654c.set(t);
            }
        }

        Lists(List<T> list) {
            this.f1651a = (List) Preconditions.m1817a((Object) list);
        }

        List<T> m2513a() {
            return this.f1651a;
        }

        private int m2510a(int i) {
            int size = size();
            Preconditions.m1815a(i, size);
            return (size - 1) - i;
        }

        private int m2512b(int i) {
            int size = size();
            Preconditions.m1825b(i, size);
            return size - i;
        }

        public void add(int i, @Nullable T t) {
            this.f1651a.add(m2512b(i), t);
        }

        public void clear() {
            this.f1651a.clear();
        }

        public T remove(int i) {
            return this.f1651a.remove(m2510a(i));
        }

        protected void removeRange(int i, int i2) {
            subList(i, i2).clear();
        }

        public T set(int i, @Nullable T t) {
            return this.f1651a.set(m2510a(i), t);
        }

        public T get(int i) {
            return this.f1651a.get(m2510a(i));
        }

        public boolean isEmpty() {
            return this.f1651a.isEmpty();
        }

        public int size() {
            return this.f1651a.size();
        }

        public boolean contains(@Nullable Object obj) {
            return this.f1651a.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return this.f1651a.containsAll(collection);
        }

        public List<T> subList(int i, int i2) {
            Preconditions.m1821a(i, i2, size());
            return ar.m2519a(this.f1651a.subList(m2512b(i2), m2512b(i)));
        }

        public int indexOf(@Nullable Object obj) {
            int lastIndexOf = this.f1651a.lastIndexOf(obj);
            return lastIndexOf >= 0 ? m2510a(lastIndexOf) : -1;
        }

        public int lastIndexOf(@Nullable Object obj) {
            int indexOf = this.f1651a.indexOf(obj);
            return indexOf >= 0 ? m2510a(indexOf) : -1;
        }

        public Iterator<T> iterator() {
            return listIterator();
        }

        public ListIterator<T> listIterator(int i) {
            return new Lists(this, this.f1651a.listIterator(m2512b(i)));
        }
    }

    /* renamed from: com.google.a.c.ar.a */
    private static class Lists<T> extends Lists<T> implements RandomAccess {
        Lists(List<T> list) {
            super(list);
        }
    }

    public static <E> ArrayList<E> m2515a() {
        return new ArrayList();
    }

    public static <E> ArrayList<E> m2518a(E... eArr) {
        Preconditions.m1817a((Object) eArr);
        Object arrayList = new ArrayList(ar.m2514a(eArr.length));
        Collections.addAll(arrayList, eArr);
        return arrayList;
    }

    static int m2514a(int i) {
        Preconditions.m1822a(i >= 0);
        return Ints.m3009a((5 + ((long) i)) + ((long) (i / 10)));
    }

    public static <E> ArrayList<E> m2516a(Iterable<? extends E> iterable) {
        Preconditions.m1817a((Object) iterable);
        return iterable instanceof Collection ? new ArrayList(Collections2.m2932a((Iterable) iterable)) : ar.m2517a(iterable.iterator());
    }

    public static <E> ArrayList<E> m2517a(Iterator<? extends E> it) {
        Preconditions.m1817a((Object) it);
        ArrayList<E> a = ar.m2515a();
        while (it.hasNext()) {
            a.add(it.next());
        }
        return a;
    }

    public static <E> ArrayList<E> m2523b(int i) {
        Preconditions.m1822a(i >= 0);
        return new ArrayList(i);
    }

    public static <E> ArrayList<E> m2527c(int i) {
        return new ArrayList(ar.m2514a(i));
    }

    public static <E> LinkedList<E> m2524b() {
        return new LinkedList();
    }

    public static <E> LinkedList<E> m2525b(Iterable<? extends E> iterable) {
        LinkedList<E> b = ar.m2524b();
        for (Object add : iterable) {
            b.add(add);
        }
        return b;
    }

    public static <T> List<T> m2519a(List<T> list) {
        if (list instanceof Lists) {
            return ((Lists) list).m2513a();
        }
        if (list instanceof RandomAccess) {
            return new Lists(list);
        }
        return new Lists(list);
    }

    static int m2521b(List<?> list) {
        int i = 1;
        for (Object next : list) {
            i = (((next == null ? 0 : next.hashCode()) + (i * 31)) ^ -1) ^ -1;
        }
        return i;
    }

    static boolean m2520a(List<?> list, @Nullable Object obj) {
        if (obj == Preconditions.m1817a((Object) list)) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list2 = (List) obj;
        if (list.size() == list2.size() && aq.m2505a(list.iterator(), list2.iterator())) {
            return true;
        }
        return false;
    }

    static int m2522b(List<?> list, @Nullable Object obj) {
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (Objects.m1811a(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    static int m2526c(List<?> list, @Nullable Object obj) {
        ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (Objects.m1811a(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }
}
