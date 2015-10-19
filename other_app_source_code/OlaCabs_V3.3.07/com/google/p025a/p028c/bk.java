package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: Sets */
/* renamed from: com.google.a.c.bk */
public final class bk {

    /* renamed from: com.google.a.c.bk.a */
    static abstract class Sets<E> extends AbstractSet<E> {
        Sets() {
        }

        public boolean removeAll(Collection<?> collection) {
            return bk.m2876a((Set) this, (Collection) collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return super.retainAll((Collection) Preconditions.m1817a((Object) collection));
        }
    }

    /* renamed from: com.google.a.c.bk.b */
    private static class Sets<E> extends AbstractSet<E> implements Serializable, Set<E> {
        private final Map<E, Boolean> f1809a;
        private transient Set<E> f1810b;

        Sets(Map<E, Boolean> map) {
            Preconditions.m1823a(map.isEmpty(), (Object) "Map is non-empty");
            this.f1809a = map;
            this.f1810b = map.keySet();
        }

        public void clear() {
            this.f1809a.clear();
        }

        public int size() {
            return this.f1809a.size();
        }

        public boolean isEmpty() {
            return this.f1809a.isEmpty();
        }

        public boolean contains(Object obj) {
            return this.f1809a.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return this.f1809a.remove(obj) != null;
        }

        public boolean add(E e) {
            return this.f1809a.put(e, Boolean.TRUE) == null;
        }

        public Iterator<E> iterator() {
            return this.f1810b.iterator();
        }

        public Object[] toArray() {
            return this.f1810b.toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f1810b.toArray(tArr);
        }

        public String toString() {
            return this.f1810b.toString();
        }

        public int hashCode() {
            return this.f1810b.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || this.f1810b.equals(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return this.f1810b.containsAll(collection);
        }

        public boolean removeAll(Collection<?> collection) {
            return this.f1810b.removeAll(collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return this.f1810b.retainAll(collection);
        }
    }

    public static <E> HashSet<E> m2870a() {
        return new HashSet();
    }

    public static <E> HashSet<E> m2871a(int i) {
        return new HashSet(au.m2812b(i));
    }

    public static <E> HashSet<E> m2872a(Iterable<? extends E> iterable) {
        return iterable instanceof Collection ? new HashSet(Collections2.m2932a((Iterable) iterable)) : bk.m2873a(iterable.iterator());
    }

    public static <E> HashSet<E> m2873a(Iterator<? extends E> it) {
        HashSet<E> a = bk.m2870a();
        while (it.hasNext()) {
            a.add(it.next());
        }
        return a;
    }

    public static <E> LinkedHashSet<E> m2878b() {
        return new LinkedHashSet();
    }

    public static <E> LinkedHashSet<E> m2879b(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return new LinkedHashSet(Collections2.m2932a((Iterable) iterable));
        }
        LinkedHashSet<E> b = bk.m2878b();
        for (Object add : iterable) {
            b.add(add);
        }
        return b;
    }

    public static <E> Set<E> m2880c() {
        return bk.m2874a(au.m2819d());
    }

    public static <E> Set<E> m2874a(Map<E, Boolean> map) {
        return new Sets(map);
    }

    static int m2869a(Set<?> set) {
        int i = 0;
        for (Object next : set) {
            int hashCode;
            if (next != null) {
                hashCode = next.hashCode();
            } else {
                hashCode = 0;
            }
            i = ((i + hashCode) ^ -1) ^ -1;
        }
        return i;
    }

    static boolean m2875a(Set<?> set, @Nullable Object obj) {
        boolean z = true;
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (!(set.size() == set2.size() && set.containsAll(set2))) {
                z = false;
            }
            return z;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    static boolean m2877a(Set<?> set, Iterator<?> it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    static boolean m2876a(Set<?> set, Collection<?> collection) {
        Preconditions.m1817a((Object) collection);
        if (collection instanceof aw) {
            collection = ((aw) collection).m2822a();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return bk.m2877a((Set) set, collection.iterator());
        }
        Iterator it = set.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                z = true;
                it.remove();
            }
        }
        return z;
    }
}
