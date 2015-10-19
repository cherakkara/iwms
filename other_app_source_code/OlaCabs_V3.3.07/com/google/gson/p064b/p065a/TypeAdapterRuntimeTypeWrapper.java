package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.p064b.p065a.ReflectiveTypeAdapterFactory.ReflectiveTypeAdapterFactory;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* renamed from: com.google.gson.b.a.l */
final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson f8326a;
    private final TypeAdapter<T> f8327b;
    private final Type f8328c;

    TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.f8326a = gson;
        this.f8327b = typeAdapter;
        this.f8328c = type;
    }

    public T m12099b(JsonReader jsonReader) throws IOException {
        return this.f8327b.m12035b(jsonReader);
    }

    public void m12098a(JsonWriter jsonWriter, T t) throws IOException {
        TypeAdapter typeAdapter = this.f8327b;
        Type a = m12097a(this.f8328c, (Object) t);
        if (a != this.f8328c) {
            typeAdapter = this.f8326a.m12335a(TypeToken.m12297a(a));
            if ((typeAdapter instanceof ReflectiveTypeAdapterFactory) && !(this.f8327b instanceof ReflectiveTypeAdapterFactory)) {
                typeAdapter = this.f8327b;
            }
        }
        typeAdapter.m12034a(jsonWriter, t);
    }

    private Type m12097a(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
