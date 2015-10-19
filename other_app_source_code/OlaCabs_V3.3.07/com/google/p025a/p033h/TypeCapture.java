package com.google.p025a.p033h;

import com.google.p025a.p026a.Preconditions;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: com.google.a.h.c */
abstract class TypeCapture<T> {
    TypeCapture() {
    }

    final Type m3018a() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Preconditions.m1824a(genericSuperclass instanceof ParameterizedType, "%s isn't parameterized", genericSuperclass);
        return ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }
}
