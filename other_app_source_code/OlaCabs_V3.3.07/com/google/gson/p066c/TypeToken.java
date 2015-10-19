package com.google.gson.p066c;

import com.google.gson.b.C$Gson$Preconditions;
import com.google.gson.b.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: com.google.gson.c.a */
public class TypeToken<T> {
    final Class<? super T> f8466a;
    final Type f8467b;
    final int f8468c;

    protected TypeToken() {
        this.f8467b = TypeToken.m12298a(getClass());
        this.f8466a = C$Gson$Types.m12233e(this.f8467b);
        this.f8468c = this.f8467b.hashCode();
    }

    TypeToken(Type type) {
        this.f8467b = C$Gson$Types.m12232d((Type) C$Gson$Preconditions.m12214a((Object) type));
        this.f8466a = C$Gson$Types.m12233e(this.f8467b);
        this.f8468c = this.f8467b.hashCode();
    }

    static Type m12298a(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return C$Gson$Types.m12232d(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public final Class<? super T> m12300a() {
        return this.f8466a;
    }

    public final Type m12301b() {
        return this.f8467b;
    }

    public final int hashCode() {
        return this.f8468c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof TypeToken) && C$Gson$Types.m12226a(this.f8467b, ((TypeToken) obj).f8467b);
    }

    public final String toString() {
        return C$Gson$Types.m12234f(this.f8467b);
    }

    public static TypeToken<?> m12297a(Type type) {
        return new TypeToken(type);
    }

    public static <T> TypeToken<T> m12299b(Class<T> cls) {
        return new TypeToken(cls);
    }
}
