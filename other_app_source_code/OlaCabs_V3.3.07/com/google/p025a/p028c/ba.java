package com.google.p025a.p028c;

import java.lang.reflect.Array;

/* compiled from: Platform */
/* renamed from: com.google.a.c.ba */
class ba {
    static <T> T[] m2836a(T[] tArr, int i) {
        return (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
    }
}
