package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ImmutableCollection.ImmutableCollection;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.Nullable;

/* compiled from: ImmutableList */
/* renamed from: com.google.a.c.ac */
public abstract class ac<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {

    /* renamed from: com.google.a.c.ac.1 */
    class ImmutableList extends AbstractIndexedListIterator<E> {
        final /* synthetic */ ac f1595a;

        ImmutableList(ac acVar, int i, int i2) {
            this.f1595a = acVar;
            super(i, i2);
        }

        protected E m2326a(int i) {
            return this.f1595a.get(i);
        }
    }

    /* renamed from: com.google.a.c.ac.a */
    public static final class ImmutableList<E> extends ImmutableCollection<E> {
        private Object[] f1596a;
        private int f1597b;

        public /* synthetic */ ImmutableCollection m2333a(Iterable iterable) {
            return m2336b(iterable);
        }

        public /* synthetic */ ImmutableCollection m2334a(Object obj) {
            return m2337b(obj);
        }

        public /* synthetic */ ImmutableCollection m2335a(Iterator it) {
            return m2338b(it);
        }

        public ImmutableList() {
            this(4);
        }

        ImmutableList(int i) {
            this.f1596a = new Object[i];
            this.f1597b = 0;
        }

        ImmutableList<E> m2331a(int i) {
            if (this.f1596a.length < i) {
                this.f1596a = ay.m2835b(this.f1596a, ImmutableCollection.m2327a(this.f1596a.length, i));
            }
            return this;
        }

        public ImmutableList<E> m2337b(E e) {
            Preconditions.m1817a((Object) e);
            m2331a(this.f1597b + 1);
            Object[] objArr = this.f1596a;
            int i = this.f1597b;
            this.f1597b = i + 1;
            objArr[i] = e;
            return this;
        }

        public ImmutableList<E> m2336b(Iterable<? extends E> iterable) {
            if (iterable instanceof Collection) {
                Collection collection = (Collection) iterable;
                m2331a(collection.size() + this.f1597b);
            }
            super.m2328a((Iterable) iterable);
            return this;
        }

        public ImmutableList<E> m2338b(Iterator<? extends E> it) {
            super.m2330a((Iterator) it);
            return this;
        }

        public ac<E> m2332a() {
            switch (this.f1597b) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    return ac.m2345g();
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return ac.m2339a(this.f1596a[0]);
                default:
                    if (this.f1597b == this.f1596a.length) {
                        return new bd(this.f1596a);
                    }
                    return new bd(ay.m2835b(this.f1596a, this.f1597b));
            }
        }
    }

    /* renamed from: com.google.a.c.ac.b */
    private static class ImmutableList<E> extends ac<E> {
        private final transient ac<E> f1600a;
        private final transient int f1601c;

        /* renamed from: com.google.a.c.ac.b.1 */
        class ImmutableList extends bt<E> {
            final /* synthetic */ bt f1598a;
            final /* synthetic */ ImmutableList f1599b;

            ImmutableList(ImmutableList immutableList, bt btVar) {
                this.f1599b = immutableList;
                this.f1598a = btVar;
            }

            public boolean hasNext() {
                return this.f1598a.hasPrevious();
            }

            public boolean hasPrevious() {
                return this.f1598a.hasNext();
            }

            public E next() {
                return this.f1598a.previous();
            }

            public int nextIndex() {
                return this.f1599b.m2354b(this.f1598a.previousIndex());
            }

            public E previous() {
                return this.f1598a.next();
            }

            public int previousIndex() {
                return this.f1599b.m2354b(this.f1598a.nextIndex());
            }
        }

        public /* synthetic */ Iterator iterator() {
            return super.m2350b();
        }

        public /* synthetic */ ListIterator listIterator() {
            return super.m2352d();
        }

        public /* synthetic */ ListIterator listIterator(int i) {
            return m2357a(i);
        }

        public /* synthetic */ List subList(int i, int i2) {
            return m2356a(i, i2);
        }

        ImmutableList(ac<E> acVar) {
            this.f1600a = acVar;
            this.f1601c = acVar.size();
        }

        private int m2354b(int i) {
            return (this.f1601c - 1) - i;
        }

        private int m2355c(int i) {
            return this.f1601c - i;
        }

        public ac<E> m_() {
            return this.f1600a;
        }

        public boolean contains(@Nullable Object obj) {
            return this.f1600a.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return this.f1600a.containsAll(collection);
        }

        public int indexOf(@Nullable Object obj) {
            int lastIndexOf = this.f1600a.lastIndexOf(obj);
            return lastIndexOf >= 0 ? m2354b(lastIndexOf) : -1;
        }

        public int lastIndexOf(@Nullable Object obj) {
            int indexOf = this.f1600a.indexOf(obj);
            return indexOf >= 0 ? m2354b(indexOf) : -1;
        }

        public ac<E> m2356a(int i, int i2) {
            Preconditions.m1821a(i, i2, this.f1601c);
            return this.f1600a.m2347a(m2355c(i2), m2355c(i)).m_();
        }

        public E get(int i) {
            Preconditions.m1815a(i, this.f1601c);
            return this.f1600a.get(m2354b(i));
        }

        public bt<E> m2357a(int i) {
            Preconditions.m1825b(i, this.f1601c);
            return new ImmutableList(this, this.f1600a.m2348a(m2355c(i)));
        }

        public int size() {
            return this.f1601c;
        }

        public boolean isEmpty() {
            return this.f1600a.isEmpty();
        }

        boolean m2358a() {
            return this.f1600a.m2284a();
        }
    }

    /* renamed from: com.google.a.c.ac.c */
    class ImmutableList extends ac<E> {
        final transient int f1602a;
        final transient int f1603c;
        final /* synthetic */ ac f1604d;

        public /* synthetic */ Iterator iterator() {
            return super.m2350b();
        }

        public /* synthetic */ ListIterator listIterator() {
            return super.m2352d();
        }

        public /* synthetic */ ListIterator listIterator(int i) {
            return super.m2348a(i);
        }

        public /* synthetic */ List subList(int i, int i2) {
            return m2359a(i, i2);
        }

        ImmutableList(ac acVar, int i, int i2) {
            this.f1604d = acVar;
            this.f1602a = i;
            this.f1603c = i2;
        }

        public int size() {
            return this.f1603c;
        }

        public E get(int i) {
            Preconditions.m1815a(i, this.f1603c);
            return this.f1604d.get(this.f1602a + i);
        }

        public ac<E> m2359a(int i, int i2) {
            Preconditions.m1821a(i, i2, this.f1603c);
            return this.f1604d.m2347a(this.f1602a + i, this.f1602a + i2);
        }

        boolean m2360a() {
            return true;
        }
    }

    public /* synthetic */ Iterator iterator() {
        return m2350b();
    }

    public /* synthetic */ ListIterator listIterator() {
        return m2352d();
    }

    public /* synthetic */ ListIterator listIterator(int i) {
        return m2348a(i);
    }

    public /* synthetic */ List subList(int i, int i2) {
        return m2347a(i, i2);
    }

    public static <E> ac<E> m2345g() {
        return EmptyImmutableList.f1862a;
    }

    public static <E> ac<E> m2339a(E e) {
        return new bm(e);
    }

    public static <E> ac<E> m2340a(Collection<? extends E> collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return ac.m2342b((Collection) collection);
        }
        Collection c = ((ImmutableCollection) collection).m2286c();
        if (c.m2284a()) {
            return ac.m2342b(c);
        }
        return c;
    }

    public static <E> ac<E> m2341a(E[] eArr) {
        switch (eArr.length) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ac.m2345g();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return new bm(eArr[0]);
            default:
                return ac.m2344c((Object[]) eArr.clone());
        }
    }

    static <E> ac<E> m2343b(Object[] objArr) {
        switch (objArr.length) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ac.m2345g();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return new bm(objArr[0]);
            default:
                return ac.m2344c(objArr);
        }
    }

    private static <E> ac<E> m2342b(Collection<? extends E> collection) {
        return ac.m2343b(collection.toArray());
    }

    private static <E> ac<E> m2344c(Object... objArr) {
        for (int i = 0; i < objArr.length; i++) {
            ay.m2830a(objArr[i], i);
        }
        return new bd(objArr);
    }

    ac() {
    }

    public bs<E> m2350b() {
        return m2352d();
    }

    public bt<E> m2352d() {
        return m2348a(0);
    }

    public bt<E> m2348a(int i) {
        return new ImmutableList(this, size(), i);
    }

    public int indexOf(@Nullable Object obj) {
        return obj == null ? -1 : ar.m2522b(this, obj);
    }

    public int lastIndexOf(@Nullable Object obj) {
        return obj == null ? -1 : ar.m2526c(this, obj);
    }

    public boolean contains(@Nullable Object obj) {
        return indexOf(obj) >= 0;
    }

    public ac<E> m2347a(int i, int i2) {
        Preconditions.m1821a(i, i2, size());
        switch (i2 - i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ac.m2345g();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return ac.m2339a(get(i));
            default:
                return m2349b(i, i2);
        }
    }

    ac<E> m2349b(int i, int i2) {
        return new ImmutableList(this, i, i2 - i);
    }

    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    public ac<E> m2351c() {
        return this;
    }

    public ac<E> m_() {
        return new ImmutableList(this);
    }

    public boolean equals(Object obj) {
        return ar.m2520a(this, obj);
    }

    public int hashCode() {
        return ar.m2521b((List) this);
    }

    public static <E> ImmutableList<E> m2346h() {
        return new ImmutableList();
    }
}
