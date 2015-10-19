package com.google.p025a.p028c;

import com.sothree.slidinguppanel.p086a.R.R;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.y */
public abstract class ImmutableCollection<E> implements Serializable, Collection<E> {
    static final ImmutableCollection<Object> f1582b;
    private transient ac<E> f1583a;

    /* renamed from: com.google.a.c.y.a */
    public static abstract class ImmutableCollection<E> {
        public abstract ImmutableCollection<E> m2329a(E e);

        static int m2327a(int i, int i2) {
            if (i2 < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            int i3 = ((i >> 1) + i) + 1;
            if (i3 < i2) {
                i3 = Integer.highestOneBit(i2 - 1) << 1;
            }
            if (i3 < 0) {
                return Integer.MAX_VALUE;
            }
            return i3;
        }

        ImmutableCollection() {
        }

        public ImmutableCollection<E> m2328a(Iterable<? extends E> iterable) {
            for (Object a : iterable) {
                m2329a(a);
            }
            return this;
        }

        public ImmutableCollection<E> m2330a(Iterator<? extends E> it) {
            while (it.hasNext()) {
                m2329a(it.next());
            }
            return this;
        }
    }

    /* renamed from: com.google.a.c.y.b */
    private static class ImmutableCollection extends ImmutableCollection<Object> {
        private static final Object[] f1870a;

        private ImmutableCollection() {
        }

        public /* synthetic */ Iterator iterator() {
            return m2974b();
        }

        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return true;
        }

        public boolean contains(@Nullable Object obj) {
            return false;
        }

        public bs<Object> m2974b() {
            return aq.f1649a;
        }

        static {
            f1870a = new Object[0];
        }

        public Object[] toArray() {
            return f1870a;
        }

        public <T> T[] toArray(T[] tArr) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        ac<Object> m2975f() {
            return ac.m2345g();
        }

        boolean m2973a() {
            return false;
        }
    }

    abstract boolean m2284a();

    public abstract bs<E> m2285b();

    public /* synthetic */ Iterator iterator() {
        return m2285b();
    }

    static {
        f1582b = new ImmutableCollection();
    }

    ImmutableCollection() {
    }

    public Object[] toArray() {
        return ay.m2832a(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return ay.m2833a((Collection) this, (Object[]) tArr);
    }

    public boolean contains(@Nullable Object obj) {
        return obj != null && aq.m2504a(m2285b(), obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return Collections2.m2934a((Collection) this, (Collection) collection);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public String toString() {
        return Collections2.m2930a((Collection) this);
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public ac<E> m2286c() {
        ac<E> acVar = this.f1583a;
        if (acVar != null) {
            return acVar;
        }
        acVar = m2287f();
        this.f1583a = acVar;
        return acVar;
    }

    ac<E> m2287f() {
        switch (size()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ac.m2345g();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return ac.m2339a(m2285b().next());
            default:
                return new bc(this, toArray());
        }
    }
}
