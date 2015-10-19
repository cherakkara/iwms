package com.google.p025a.p033h;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import javax.annotation.Nullable;

/* renamed from: com.google.a.h.a */
class Element extends AccessibleObject implements Member {
    private final AccessibleObject f1906a;
    private final Member f1907b;

    public final boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return this.f1906a.isAnnotationPresent(cls);
    }

    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this.f1906a.getAnnotation(cls);
    }

    public final Annotation[] getAnnotations() {
        return this.f1906a.getAnnotations();
    }

    public final Annotation[] getDeclaredAnnotations() {
        return this.f1906a.getDeclaredAnnotations();
    }

    public final void setAccessible(boolean z) throws SecurityException {
        this.f1906a.setAccessible(z);
    }

    public final boolean isAccessible() {
        return this.f1906a.isAccessible();
    }

    public Class<?> getDeclaringClass() {
        return this.f1907b.getDeclaringClass();
    }

    public final String getName() {
        return this.f1907b.getName();
    }

    public final int getModifiers() {
        return this.f1907b.getModifiers();
    }

    public final boolean isSynthetic() {
        return this.f1907b.isSynthetic();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Element)) {
            return false;
        }
        return this.f1907b.equals(((Element) obj).f1907b);
    }

    public int hashCode() {
        return this.f1907b.hashCode();
    }

    public String toString() {
        return this.f1907b.toString();
    }
}
