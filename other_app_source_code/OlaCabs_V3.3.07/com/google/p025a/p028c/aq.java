package com.google.p025a.p028c;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Joiner;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Predicate;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* compiled from: Iterators */
/* renamed from: com.google.a.c.aq */
public final class aq {
    static final bt<Object> f1649a;
    private static final Iterator<Object> f1650b;

    /* renamed from: com.google.a.c.aq.1 */
    static class Iterators extends bt<Object> {
        Iterators() {
        }

        public boolean hasNext() {
            return false;
        }

        public Object next() {
            throw new NoSuchElementException();
        }

        public boolean hasPrevious() {
            return false;
        }

        public Object previous() {
            throw new NoSuchElementException();
        }

        public int nextIndex() {
            return 0;
        }

        public int previousIndex() {
            return -1;
        }
    }

    /* renamed from: com.google.a.c.aq.2 */
    static class Iterators extends AbstractIndexedListIterator<T> {
        final /* synthetic */ Object[] f1638a;
        final /* synthetic */ int f1639b;

        Iterators(int i, int i2, Object[] objArr, int i3) {
            this.f1638a = objArr;
            this.f1639b = i3;
            super(i, i2);
        }

        protected T m2489a(int i) {
            return this.f1638a[this.f1639b + i];
        }
    }

    /* renamed from: com.google.a.c.aq.3 */
    static class Iterators extends bs<T> {
        boolean f1640a;
        final /* synthetic */ Object f1641b;

        Iterators(Object obj) {
            this.f1641b = obj;
        }

        public boolean hasNext() {
            return !this.f1640a;
        }

        public T next() {
            if (this.f1640a) {
                throw new NoSuchElementException();
            }
            this.f1640a = true;
            return this.f1641b;
        }
    }

    /* renamed from: com.google.a.c.aq.4 */
    static class Iterators implements Iterator<Object> {
        Iterators() {
        }

        public boolean hasNext() {
            return false;
        }

        public Object next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new IllegalStateException();
        }
    }

    /* renamed from: com.google.a.c.aq.5 */
    static class Iterators extends bs<T> {
        final /* synthetic */ Iterator f1642a;

        Iterators(Iterator it) {
            this.f1642a = it;
        }

        public boolean hasNext() {
            return this.f1642a.hasNext();
        }

        public T next() {
            return this.f1642a.next();
        }
    }

    /* renamed from: com.google.a.c.aq.6 */
    static class Iterators extends AbstractIterator<T> {
        final /* synthetic */ Iterator f1645a;
        final /* synthetic */ Predicate f1646b;

        Iterators(Iterator it, Predicate predicate) {
            this.f1645a = it;
            this.f1646b = predicate;
        }

        protected T m2493a() {
            while (this.f1645a.hasNext()) {
                T next = this.f1645a.next();
                if (this.f1646b.m1735a(next)) {
                    return next;
                }
            }
            return m2492b();
        }
    }

    /* renamed from: com.google.a.c.aq.7 */
    static class Iterators extends br<F, T> {
        final /* synthetic */ Function f1648a;

        Iterators(Iterator it, Function function) {
            this.f1648a = function;
            super(it);
        }

        T m2495a(F f) {
            return this.f1648a.m1778a(f);
        }
    }

    static {
        f1649a = new Iterators();
        f1650b = new Iterators();
    }

    public static <T> bs<T> m2496a() {
        return aq.m2506b();
    }

    static <T> bt<T> m2506b() {
        return f1649a;
    }

    public static <T> bs<T> m2498a(Iterator<T> it) {
        Preconditions.m1817a((Object) it);
        if (it instanceof bs) {
            return (bs) it;
        }
        return new Iterators(it);
    }

    public static boolean m2504a(Iterator<?> it, @Nullable Object obj) {
        if (obj == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return true;
                }
            }
        }
        while (it.hasNext()) {
            if (obj.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean m2505a(Iterator<?> it, Iterator<?> it2) {
        while (it.hasNext()) {
            if (!it2.hasNext()) {
                return false;
            }
            if (!Objects.m1811a(it.next(), it2.next())) {
                return false;
            }
        }
        if (it2.hasNext()) {
            return false;
        }
        return true;
    }

    public static String m2507b(Iterator<?> it) {
        return Joiner.m1780a(", ").m1791b("null").m1790a(new StringBuilder().append('['), (Iterator) it).append(']').toString();
    }

    public static <T> T m2508c(Iterator<T> it) {
        T next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("expected one element but was: <" + next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            stringBuilder.append(", " + it.next());
        }
        if (it.hasNext()) {
            stringBuilder.append(", ...");
        }
        stringBuilder.append('>');
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static <T> boolean m2503a(Collection<T> collection, Iterator<? extends T> it) {
        Preconditions.m1817a((Object) collection);
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        return z;
    }

    public static <T> bs<T> m2499a(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.m1817a((Object) it);
        Preconditions.m1817a((Object) predicate);
        return new Iterators(it, predicate);
    }

    public static <F, T> Iterator<T> m2501a(Iterator<F> it, Function<? super F, ? extends T> function) {
        Preconditions.m1817a((Object) function);
        return new Iterators(it, function);
    }

    static void m2509d(Iterator<?> it) {
        Preconditions.m1817a((Object) it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    static <T> bt<T> m2500a(T[] tArr, int i, int i2, int i3) {
        Preconditions.m1822a(i2 >= 0);
        Preconditions.m1821a(i, i + i2, tArr.length);
        return new Iterators(i2, i3, tArr, i);
    }

    public static <T> bs<T> m2497a(@Nullable T t) {
        return new Iterators(t);
    }

    static void m2502a(boolean z) {
        Preconditions.m1829b(z, (Object) "no calls to next() since the last call to remove()");
    }
}
