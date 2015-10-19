package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.b.C$Gson$Types;
import com.google.gson.p064b.ConstructorConstructor;
import com.google.gson.p064b.ObjectConstructor;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: com.google.gson.b.a.b */
public final class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor f8285a;

    /* renamed from: com.google.gson.b.a.b.a */
    private static final class CollectionTypeAdapterFactory<E> extends TypeAdapter<Collection<E>> {
        private final TypeAdapter<E> f8283a;
        private final ObjectConstructor<? extends Collection<E>> f8284b;

        public /* synthetic */ Object m12041b(JsonReader jsonReader) throws IOException {
            return m12038a(jsonReader);
        }

        public CollectionTypeAdapterFactory(Gson gson, Type type, TypeAdapter<E> typeAdapter, ObjectConstructor<? extends Collection<E>> objectConstructor) {
            this.f8283a = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.f8284b = objectConstructor;
        }

        public Collection<E> m12038a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Collection<E> collection = (Collection) this.f8284b.m12238a();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                collection.add(this.f8283a.m12035b(jsonReader));
            }
            jsonReader.endArray();
            return collection;
        }

        public void m12040a(JsonWriter jsonWriter, Collection<E> collection) throws IOException {
            if (collection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            for (E a : collection) {
                this.f8283a.m12034a(jsonWriter, a);
            }
            jsonWriter.endArray();
        }
    }

    public CollectionTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.f8285a = constructorConstructor;
    }

    public <T> TypeAdapter<T> m12042a(Gson gson, TypeToken<T> typeToken) {
        Type b = typeToken.m12301b();
        Class a = typeToken.m12300a();
        if (!Collection.class.isAssignableFrom(a)) {
            return null;
        }
        Type a2 = C$Gson$Types.m12221a(b, a);
        return new CollectionTypeAdapterFactory(gson, a2, gson.m12335a(TypeToken.m12297a(a2)), this.f8285a.m12254a((TypeToken) typeToken));
    }
}
