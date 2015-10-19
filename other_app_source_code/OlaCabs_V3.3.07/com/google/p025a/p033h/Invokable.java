package com.google.p025a.p033h;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/* renamed from: com.google.a.h.b */
public abstract class Invokable<T, R> extends Element implements GenericDeclaration {

    /* renamed from: com.google.a.h.b.a */
    static class Invokable<T> extends Invokable<T, Object> {
        private final Method f1908a;

        public final TypeVariable<?>[] getTypeParameters() {
            return this.f1908a.getTypeParameters();
        }
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public final Class<? super T> getDeclaringClass() {
        return super.getDeclaringClass();
    }
}
