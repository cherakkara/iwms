package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nullable;

/* compiled from: RegularImmutableList */
/* renamed from: com.google.a.c.bd */
class bd<E> extends ac<E> {
    private final transient int f1795a;
    private final transient int f1796c;
    private final transient Object[] f1797d;

    public /* synthetic */ ListIterator listIterator(int i) {
        return m2838a(i);
    }

    bd(Object[] objArr, int i, int i2) {
        this.f1795a = i;
        this.f1796c = i2;
        this.f1797d = objArr;
    }

    bd(Object[] objArr) {
        this(objArr, 0, objArr.length);
    }

    public int size() {
        return this.f1796c;
    }

    public boolean isEmpty() {
        return false;
    }

    boolean m2839a() {
        return (this.f1795a == 0 && this.f1796c == this.f1797d.length) ? false : true;
    }

    public Object[] toArray() {
        Object obj = new Object[size()];
        System.arraycopy(this.f1797d, this.f1795a, obj, 0, this.f1796c);
        return obj;
    }

    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f1796c) {
            tArr = ay.m2834a((Object[]) tArr, this.f1796c);
        } else if (tArr.length > this.f1796c) {
            tArr[this.f1796c] = null;
        }
        System.arraycopy(this.f1797d, this.f1795a, tArr, 0, this.f1796c);
        return tArr;
    }

    public E get(int i) {
        Preconditions.m1815a(i, this.f1796c);
        return this.f1797d[this.f1795a + i];
    }

    ac<E> m2840b(int i, int i2) {
        return new bd(this.f1797d, this.f1795a + i, i2 - i);
    }

    public bt<E> m2838a(int i) {
        return aq.m2500a(this.f1797d, this.f1795a, this.f1796c, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List<Object> list = (List) obj;
        if (size() != list.size()) {
            return false;
        }
        int i = this.f1795a;
        int i2;
        if (obj instanceof bd) {
            bd bdVar = (bd) obj;
            i2 = bdVar.f1795a;
            while (i2 < bdVar.f1795a + bdVar.f1796c) {
                int i3 = i + 1;
                if (!this.f1797d[i].equals(bdVar.f1797d[i2])) {
                    return false;
                }
                i2++;
                i = i3;
            }
        } else {
            for (Object equals : list) {
                i2 = i + 1;
                if (!this.f1797d[i].equals(equals)) {
                    return false;
                }
                i = i2;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder append = Collections2.m2931a(size()).append('[').append(this.f1797d[this.f1795a]);
        for (int i = this.f1795a + 1; i < this.f1795a + this.f1796c; i++) {
            append.append(", ").append(this.f1797d[i]);
        }
        return append.append(']').toString();
    }
}
