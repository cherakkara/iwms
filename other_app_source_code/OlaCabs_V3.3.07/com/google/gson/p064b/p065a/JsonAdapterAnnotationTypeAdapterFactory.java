package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.p063a.JsonAdapter;
import com.google.gson.p064b.ConstructorConstructor;
import com.google.gson.p066c.TypeToken;

/* renamed from: com.google.gson.b.a.d */
public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor f8290a;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.f8290a = constructorConstructor;
    }

    public <T> TypeAdapter<T> m12051a(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter jsonAdapter = (JsonAdapter) typeToken.m12300a().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return JsonAdapterAnnotationTypeAdapterFactory.m12050a(this.f8290a, gson, typeToken, jsonAdapter);
    }

    static TypeAdapter<?> m12050a(ConstructorConstructor constructorConstructor, Gson gson, TypeToken<?> typeToken, JsonAdapter jsonAdapter) {
        Class a = jsonAdapter.m12020a();
        if (TypeAdapter.class.isAssignableFrom(a)) {
            return (TypeAdapter) constructorConstructor.m12254a(TypeToken.m12299b(a)).m12238a();
        }
        if (TypeAdapterFactory.class.isAssignableFrom(a)) {
            return ((TypeAdapterFactory) constructorConstructor.m12254a(TypeToken.m12299b(a)).m12238a()).m12031a(gson, typeToken);
        }
        throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
    }
}
