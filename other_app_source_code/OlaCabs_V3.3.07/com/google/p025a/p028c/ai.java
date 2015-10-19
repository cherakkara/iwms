package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ImmutableCollection.ImmutableCollection;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: ImmutableSet */
/* renamed from: com.google.a.c.ai */
public abstract class ai<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int f1584a;

    /* renamed from: com.google.a.c.ai.a */
    static abstract class ImmutableSet<E> extends ai<E> {
        final transient Object[] f1621a;

        public /* synthetic */ Iterator iterator() {
            return m2422b();
        }

        ImmutableSet(Object[] objArr) {
            this.f1621a = objArr;
        }

        public int size() {
            return this.f1621a.length;
        }

        public boolean isEmpty() {
            return false;
        }

        public bs<E> m2422b() {
            return m2286c().m2350b();
        }

        public Object[] toArray() {
            return m2286c().toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return m2286c().toArray(tArr);
        }

        public boolean containsAll(Collection<?> collection) {
            if (collection == this) {
                return true;
            }
            if (!(collection instanceof ImmutableSet)) {
                return super.containsAll(collection);
            }
            if (collection.size() > size()) {
                return false;
            }
            for (Object contains : ((ImmutableSet) collection).f1621a) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        boolean m2421a() {
            return false;
        }

        ac<E> m2423f() {
            return new bc((ImmutableCollection) this, this.f1621a);
        }
    }

    /* renamed from: com.google.a.c.ai.b */
    public static class ImmutableSet<E> extends ImmutableCollection<E> {
        Object[] f1622a;
        int f1623b;

        public /* synthetic */ ImmutableCollection m2426a(Iterable iterable) {
            return m2429b(iterable);
        }

        public /* synthetic */ ImmutableCollection m2427a(Object obj) {
            return m2430b(obj);
        }

        public /* synthetic */ ImmutableCollection m2428a(Iterator it) {
            return m2431b(it);
        }

        public ImmutableSet() {
            this(4);
        }

        ImmutableSet(int i) {
            boolean z;
            if (i >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.m1824a(z, "capacity must be >= 0 but was %s", Integer.valueOf(i));
            this.f1622a = new Object[i];
            this.f1623b = 0;
        }

        ImmutableSet<E> m2424a(int i) {
            if (this.f1622a.length < i) {
                this.f1622a = ay.m2835b(this.f1622a, ImmutableCollection.m2327a(this.f1622a.length, i));
            }
            return this;
        }

        public ImmutableSet<E> m2430b(E e) {
            m2424a(this.f1623b + 1);
            Object[] objArr = this.f1622a;
            int i = this.f1623b;
            this.f1623b = i + 1;
            objArr[i] = Preconditions.m1817a((Object) e);
            return this;
        }

        public ImmutableSet<E> m2429b(Iterable<? extends E> iterable) {
            if (iterable instanceof Collection) {
                Collection collection = (Collection) iterable;
                m2424a(collection.size() + this.f1623b);
            }
            super.m2328a((Iterable) iterable);
            return this;
        }

        public ImmutableSet<E> m2431b(Iterator<? extends E> it) {
            super.m2330a((Iterator) it);
            return this;
        }

        public ai<E> m2425a() {
            ai<E> a = ai.m2293b(this.f1623b, this.f1622a);
            this.f1623b = a.size();
            return a;
        }
    }

    public abstract bs<E> m2298b();

    public /* synthetic */ Iterator iterator() {
        return m2298b();
    }

    public static <E> ai<E> m2296g() {
        return EmptyImmutableSet.f1863a;
    }

    public static <E> ai<E> m2294b(E e) {
        return new bn(e);
    }

    private static <E> ai<E> m2293b(int i, Object... objArr) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ai.m2296g();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return ai.m2294b(objArr[0]);
            default:
                int a = ai.m2288a(i);
                Object[] objArr2 = new Object[a];
                int i2 = a - 1;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (i3 < i) {
                    Object a2 = ay.m2830a(objArr[i3], i3);
                    int hashCode = a2.hashCode();
                    int a3 = Hashing.m2971a(hashCode);
                    while (true) {
                        int i6 = a3 & i2;
                        Object obj = objArr2[i6];
                        if (obj == null) {
                            a3 = i4 + 1;
                            objArr[i4] = a2;
                            objArr2[i6] = a2;
                            i4 = i5 + hashCode;
                        } else if (obj.equals(a2)) {
                            a3 = i4;
                            i4 = i5;
                        } else {
                            a3++;
                        }
                        i3++;
                        i5 = i4;
                        i4 = a3;
                    }
                }
                Arrays.fill(objArr, i4, i, null);
                if (i4 == 1) {
                    return new bn(objArr[0], i5);
                }
                if (a != ai.m2288a(i4)) {
                    return ai.m2293b(i4, objArr);
                }
                if (i4 < objArr.length) {
                    objArr = ay.m2835b(objArr, i4);
                }
                return new bf(objArr, i5, objArr2, i2);
        }
    }

    static {
        f1584a = (int) Math.floor(7.516192768E8d);
    }

    static int m2288a(int i) {
        if (i < f1584a) {
            int highestOneBit = Integer.highestOneBit(i - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) i)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        Preconditions.m1823a(i < 1073741824, (Object) "collection too large");
        return 1073741824;
    }

    public static <E> ai<E> m2290a(Iterable<? extends E> iterable) {
        return iterable instanceof Collection ? ai.m2291a(Collections2.m2932a((Iterable) iterable)) : ai.m2292a(iterable.iterator());
    }

    public static <E> ai<E> m2292a(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return ai.m2296g();
        }
        Object next = it.next();
        if (it.hasNext()) {
            return new ImmutableSet().m2430b(next).m2431b((Iterator) it).m2425a();
        }
        return ai.m2294b(next);
    }

    public static <E> ai<E> m2291a(Collection<? extends E> collection) {
        if ((collection instanceof ai) && !(collection instanceof an)) {
            ai<E> aiVar = (ai) collection;
            if (!aiVar.m2284a()) {
                return aiVar;
            }
        } else if (collection instanceof EnumSet) {
            return ab.m2323a(EnumSet.copyOf((EnumSet) collection));
        }
        return ai.m2295b((Collection) collection);
    }

    private static <E> ai<E> m2295b(Collection<? extends E> collection) {
        Object[] toArray = collection.toArray();
        switch (toArray.length) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ai.m2296g();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return ai.m2294b(toArray[0]);
            default:
                return ai.m2293b(toArray.length, toArray);
        }
    }

    ai() {
    }

    boolean m2299d() {
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ai) && m2299d() && ((ai) obj).m2299d() && hashCode() != obj.hashCode()) {
            return false;
        }
        return bk.m2875a((Set) this, obj);
    }

    public int hashCode() {
        return bk.m2869a((Set) this);
    }

    public static <E> ImmutableSet<E> m2297h() {
        return new ImmutableSet();
    }
}
