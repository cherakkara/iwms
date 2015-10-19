package com.google.p025a.p028c;

import java.util.Collection;

/* compiled from: ObjectArrays */
/* renamed from: com.google.a.c.ay */
public final class ay {
    static final Object[] f1788a;

    static {
        f1788a = new Object[0];
    }

    public static <T> T[] m2834a(T[] tArr, int i) {
        return ba.m2836a(tArr, i);
    }

    static <T> T[] m2835b(T[] tArr, int i) {
        Object a = ay.m2834a((Object[]) tArr, i);
        System.arraycopy(tArr, 0, a, 0, Math.min(tArr.length, i));
        return a;
    }

    static <T> T[] m2833a(Collection<?> collection, T[] tArr) {
        Object[] a;
        int size = collection.size();
        if (tArr.length < size) {
            a = ay.m2834a((Object[]) tArr, size);
        }
        ay.m2831a((Iterable) collection, a);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    static Object[] m2832a(Collection<?> collection) {
        return ay.m2831a((Iterable) collection, new Object[collection.size()]);
    }

    private static Object[] m2831a(Iterable<?> iterable, Object[] objArr) {
        int i = 0;
        for (Object obj : iterable) {
            int i2 = i + 1;
            objArr[i] = obj;
            i = i2;
        }
        return objArr;
    }

    static Object m2830a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }
}
