package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: SingletonImmutableSet */
/* renamed from: com.google.a.c.bn */
final class bn<E> extends ai<E> {
    final transient E f1815a;
    private transient int f1816c;

    public /* synthetic */ Iterator iterator() {
        return m2892b();
    }

    bn(E e) {
        this.f1815a = Preconditions.m1817a((Object) e);
    }

    bn(E e, int i) {
        this.f1815a = e;
        this.f1816c = i;
    }

    public int size() {
        return 1;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object obj) {
        return this.f1815a.equals(obj);
    }

    public bs<E> m2892b() {
        return aq.m2497a(this.f1815a);
    }

    boolean m2891a() {
        return false;
    }

    public Object[] toArray() {
        return new Object[]{this.f1815a};
    }

    public <T> T[] toArray(T[] tArr) {
        if (tArr.length == 0) {
            tArr = ay.m2834a((Object[]) tArr, 1);
        } else if (tArr.length > 1) {
            tArr[1] = null;
        }
        tArr[0] = this.f1815a;
        return tArr;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.size() == 1 && this.f1815a.equals(set.iterator().next())) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f1816c;
        if (i != 0) {
            return i;
        }
        i = this.f1815a.hashCode();
        this.f1816c = i;
        return i;
    }

    boolean m2893d() {
        return this.f1816c != 0;
    }

    public String toString() {
        String obj = this.f1815a.toString();
        return new StringBuilder(obj.length() + 2).append('[').append(obj).append(']').toString();
    }
}
