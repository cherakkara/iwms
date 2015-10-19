package com.google.gson;

import com.google.gson.b.C$Gson$Preconditions;
import com.google.gson.p064b.Streams;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* renamed from: com.google.gson.v */
final class TreeTypeAdapter<T> extends TypeAdapter<T> {
    private final JsonSerializer<T> f8520a;
    private final JsonDeserializer<T> f8521b;
    private final Gson f8522c;
    private final TypeToken<T> f8523d;
    private final TypeAdapterFactory f8524e;
    private TypeAdapter<T> f8525f;

    /* renamed from: com.google.gson.v.a */
    private static class TreeTypeAdapter implements TypeAdapterFactory {
        private final TypeToken<?> f8515a;
        private final boolean f8516b;
        private final Class<?> f8517c;
        private final JsonSerializer<?> f8518d;
        private final JsonDeserializer<?> f8519e;

        private TreeTypeAdapter(Object obj, TypeToken<?> typeToken, boolean z, Class<?> cls) {
            this.f8518d = obj instanceof JsonSerializer ? (JsonSerializer) obj : null;
            if (obj instanceof JsonDeserializer) {
                obj = (JsonDeserializer) obj;
            } else {
                obj = null;
            }
            this.f8519e = obj;
            boolean z2 = (this.f8518d == null && this.f8519e == null) ? false : true;
            C$Gson$Preconditions.m12215a(z2);
            this.f8515a = typeToken;
            this.f8516b = z;
            this.f8517c = cls;
        }

        public <T> TypeAdapter<T> m12393a(Gson gson, TypeToken<T> typeToken) {
            boolean isAssignableFrom = this.f8515a != null ? this.f8515a.equals(typeToken) || (this.f8516b && this.f8515a.m12301b() == typeToken.m12300a()) : this.f8517c.isAssignableFrom(typeToken.m12300a());
            if (isAssignableFrom) {
                return new TreeTypeAdapter(this.f8519e, gson, typeToken, this, null);
            }
            return null;
        }
    }

    private TreeTypeAdapter(JsonSerializer<T> jsonSerializer, JsonDeserializer<T> jsonDeserializer, Gson gson, TypeToken<T> typeToken, TypeAdapterFactory typeAdapterFactory) {
        this.f8520a = jsonSerializer;
        this.f8521b = jsonDeserializer;
        this.f8522c = gson;
        this.f8523d = typeToken;
        this.f8524e = typeAdapterFactory;
    }

    public T m12397b(JsonReader jsonReader) throws IOException {
        if (this.f8521b == null) {
            return m12394a().m12035b(jsonReader);
        }
        JsonElement a = Streams.m12286a(jsonReader);
        if (a.m12366j()) {
            return null;
        }
        return this.f8521b.m12024b(a, this.f8523d.m12301b(), this.f8522c.f8482a);
    }

    public void m12396a(JsonWriter jsonWriter, T t) throws IOException {
        if (this.f8520a == null) {
            m12394a().m12034a(jsonWriter, t);
        } else if (t == null) {
            jsonWriter.nullValue();
        } else {
            Streams.m12288a(this.f8520a.m12025a(t, this.f8523d.m12301b(), this.f8522c.f8483b), jsonWriter);
        }
    }

    private TypeAdapter<T> m12394a() {
        TypeAdapter<T> typeAdapter = this.f8525f;
        if (typeAdapter != null) {
            return typeAdapter;
        }
        typeAdapter = this.f8522c.m12336a(this.f8524e, this.f8523d);
        this.f8525f = typeAdapter;
        return typeAdapter;
    }

    public static TypeAdapterFactory m12395a(TypeToken<?> typeToken, Object obj) {
        return new TreeTypeAdapter(typeToken, false, null, null);
    }
}
