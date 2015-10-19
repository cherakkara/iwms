package com.google.p025a.p028c;

import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

/* compiled from: ImmutableEnumSet */
/* renamed from: com.google.a.c.ab */
final class ab<E extends Enum<E>> extends ai<E> {
    private final transient EnumSet<E> f1593a;
    private transient int f1594c;

    public /* synthetic */ Iterator iterator() {
        return m2325b();
    }

    static <E extends Enum<E>> ai<E> m2323a(EnumSet<E> enumSet) {
        switch (enumSet.size()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return ai.m2296g();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return ai.m2294b(ap.m2486b(enumSet));
            default:
                return new ab(enumSet);
        }
    }

    private ab(EnumSet<E> enumSet) {
        this.f1593a = enumSet;
    }

    boolean m2324a() {
        return false;
    }

    public bs<E> m2325b() {
        return aq.m2498a(this.f1593a.iterator());
    }

    public int size() {
        return this.f1593a.size();
    }

    public boolean contains(Object obj) {
        return this.f1593a.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.f1593a.containsAll(collection);
    }

    public boolean isEmpty() {
        return this.f1593a.isEmpty();
    }

    public Object[] toArray() {
        return this.f1593a.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.f1593a.toArray(tArr);
    }

    public boolean equals(Object obj) {
        return obj == this || this.f1593a.equals(obj);
    }

    public int hashCode() {
        int i = this.f1594c;
        if (i != 0) {
            return i;
        }
        i = this.f1593a.hashCode();
        this.f1594c = i;
        return i;
    }

    public String toString() {
        return this.f1593a.toString();
    }
}
