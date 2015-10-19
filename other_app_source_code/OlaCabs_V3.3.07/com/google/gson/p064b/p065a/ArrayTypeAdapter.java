package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.b.C$Gson$Types;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.gson.b.a.a */
public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {
    public static final TypeAdapterFactory f8280a;
    private final Class<E> f8281b;
    private final TypeAdapter<E> f8282c;

    /* renamed from: com.google.gson.b.a.a.1 */
    static class ArrayTypeAdapter implements TypeAdapterFactory {
        ArrayTypeAdapter() {
        }

        public <T> TypeAdapter<T> m12032a(Gson gson, TypeToken<T> typeToken) {
            Type b = typeToken.m12301b();
            if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
                return null;
            }
            b = C$Gson$Types.m12235g(b);
            return new ArrayTypeAdapter(gson, gson.m12335a(TypeToken.m12297a(b)), C$Gson$Types.m12233e(b));
        }
    }

    static {
        f8280a = new ArrayTypeAdapter();
    }

    public ArrayTypeAdapter(Gson gson, TypeAdapter<E> typeAdapter, Class<E> cls) {
        this.f8282c = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, cls);
        this.f8281b = cls;
    }

    public Object m12037b(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        List arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(this.f8282c.m12035b(jsonReader));
        }
        jsonReader.endArray();
        Object newInstance = Array.newInstance(this.f8281b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void m12036a(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f8282c.m12034a(jsonWriter, Array.get(obj, i));
        }
        jsonWriter.endArray();
    }
}
