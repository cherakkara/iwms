package com.google.p025a.p028c;

import com.google.p025a.p028c.ai.ImmutableSet;

/* compiled from: RegularImmutableSet */
/* renamed from: com.google.a.c.bf */
final class bf<E> extends ImmutableSet<E> {
    final transient Object[] f1803c;
    private final transient int f1804d;
    private final transient int f1805e;

    bf(Object[] objArr, int i, Object[] objArr2, int i2) {
        super(objArr);
        this.f1803c = objArr2;
        this.f1804d = i2;
        this.f1805e = i;
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        int a = Hashing.m2971a(obj.hashCode());
        while (true) {
            Object obj2 = this.f1803c[this.f1804d & a];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            a++;
        }
    }

    public int hashCode() {
        return this.f1805e;
    }

    boolean m2852d() {
        return true;
    }
}
