package com.google.gson;

import com.google.gson.p064b.p065a.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* renamed from: com.google.gson.w */
public abstract class TypeAdapter<T> {
    public abstract void m12034a(JsonWriter jsonWriter, T t) throws IOException;

    public abstract T m12035b(JsonReader jsonReader) throws IOException;

    public final JsonElement m12033a(T t) {
        try {
            JsonWriter jsonTreeWriter = new JsonTreeWriter();
            m12034a(jsonTreeWriter, t);
            return jsonTreeWriter.m12058a();
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }
}
