package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: SingletonImmutableList */
/* renamed from: com.google.a.c.bm */
final class bm<E> extends ac<E> {
    final transient E f1814a;

    public /* synthetic */ Iterator iterator() {
        return m2890b();
    }

    public /* synthetic */ List subList(int i, int i2) {
        return m2888a(i, i2);
    }

    bm(E e) {
        this.f1814a = Preconditions.m1817a((Object) e);
    }

    public E get(int i) {
        Preconditions.m1815a(i, 1);
        return this.f1814a;
    }

    public int indexOf(@Nullable Object obj) {
        return this.f1814a.equals(obj) ? 0 : -1;
    }

    public bs<E> m2890b() {
        return aq.m2497a(this.f1814a);
    }

    public int lastIndexOf(@Nullable Object obj) {
        return indexOf(obj);
    }

    public int size() {
        return 1;
    }

    public ac<E> m2888a(int i, int i2) {
        Preconditions.m1821a(i, i2, 1);
        return i == i2 ? ac.m2345g() : this;
    }

    public ac<E> m_() {
        return this;
    }

    public boolean contains(@Nullable Object obj) {
        return this.f1814a.equals(obj);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (list.size() == 1 && this.f1814a.equals(list.get(0))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f1814a.hashCode() + 31;
    }

    public String toString() {
        String obj = this.f1814a.toString();
        return new StringBuilder(obj.length() + 2).append('[').append(obj).append(']').toString();
    }

    public boolean isEmpty() {
        return false;
    }

    boolean m2889a() {
        return false;
    }

    public Object[] toArray() {
        return new Object[]{this.f1814a};
    }

    public <T> T[] toArray(T[] tArr) {
        if (tArr.length == 0) {
            tArr = ay.m2834a((Object[]) tArr, 1);
        } else if (tArr.length > 1) {
            tArr[1] = null;
        }
        tArr[0] = this.f1814a;
        return tArr;
    }
}
