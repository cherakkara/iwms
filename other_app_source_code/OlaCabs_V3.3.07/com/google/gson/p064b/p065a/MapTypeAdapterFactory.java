package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.b.C$Gson$Types;
import com.google.gson.p064b.ConstructorConstructor;
import com.google.gson.p064b.JsonReaderInternalAccess;
import com.google.gson.p064b.ObjectConstructor;
import com.google.gson.p064b.Streams;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.google.gson.b.a.g */
public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor f8303a;
    private final boolean f8304b;

    /* renamed from: com.google.gson.b.a.g.a */
    private final class MapTypeAdapterFactory<K, V> extends TypeAdapter<Map<K, V>> {
        final /* synthetic */ MapTypeAdapterFactory f8299a;
        private final TypeAdapter<K> f8300b;
        private final TypeAdapter<V> f8301c;
        private final ObjectConstructor<? extends Map<K, V>> f8302d;

        public /* synthetic */ Object m12063b(JsonReader jsonReader) throws IOException {
            return m12060a(jsonReader);
        }

        public MapTypeAdapterFactory(MapTypeAdapterFactory mapTypeAdapterFactory, Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, ObjectConstructor<? extends Map<K, V>> objectConstructor) {
            this.f8299a = mapTypeAdapterFactory;
            this.f8300b = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.f8301c = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, type2);
            this.f8302d = objectConstructor;
        }

        public Map<K, V> m12060a(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Map<K, V> map = (Map) this.f8302d.m12238a();
            Object b;
            if (peek == JsonToken.BEGIN_ARRAY) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginArray();
                    b = this.f8300b.m12035b(jsonReader);
                    if (map.put(b, this.f8301c.m12035b(jsonReader)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + b);
                    }
                    jsonReader.endArray();
                }
                jsonReader.endArray();
                return map;
            }
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonReader);
                b = this.f8300b.m12035b(jsonReader);
                if (map.put(b, this.f8301c.m12035b(jsonReader)) != null) {
                    throw new JsonSyntaxException("duplicate key: " + b);
                }
            }
            jsonReader.endObject();
            return map;
        }

        public void m12062a(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                jsonWriter.nullValue();
            } else if (this.f8299a.f8304b) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    int i3;
                    JsonElement a = this.f8300b.m12033a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    if (a.m12363g() || a.m12364h()) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    jsonWriter.beginArray();
                    while (i < arrayList.size()) {
                        jsonWriter.beginArray();
                        Streams.m12288a((JsonElement) arrayList.get(i), jsonWriter);
                        this.f8301c.m12034a(jsonWriter, arrayList2.get(i));
                        jsonWriter.endArray();
                        i++;
                    }
                    jsonWriter.endArray();
                    return;
                }
                jsonWriter.beginObject();
                while (i < arrayList.size()) {
                    jsonWriter.name(m12059a((JsonElement) arrayList.get(i)));
                    this.f8301c.m12034a(jsonWriter, arrayList2.get(i));
                    i++;
                }
                jsonWriter.endObject();
            } else {
                jsonWriter.beginObject();
                for (Entry entry2 : map.entrySet()) {
                    jsonWriter.name(String.valueOf(entry2.getKey()));
                    this.f8301c.m12034a(jsonWriter, entry2.getValue());
                }
                jsonWriter.endObject();
            }
        }

        private String m12059a(JsonElement jsonElement) {
            if (jsonElement.m12365i()) {
                JsonPrimitive m = jsonElement.m12369m();
                if (m.m12391p()) {
                    return String.valueOf(m.m12382a());
                }
                if (m.m12390o()) {
                    return Boolean.toString(m.m12388f());
                }
                if (m.m12392q()) {
                    return m.m12384b();
                }
                throw new AssertionError();
            } else if (jsonElement.m12366j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public MapTypeAdapterFactory(ConstructorConstructor constructorConstructor, boolean z) {
        this.f8303a = constructorConstructor;
        this.f8304b = z;
    }

    public <T> TypeAdapter<T> m12066a(Gson gson, TypeToken<T> typeToken) {
        Type b = typeToken.m12301b();
        if (!Map.class.isAssignableFrom(typeToken.m12300a())) {
            return null;
        }
        Type[] b2 = C$Gson$Types.m12230b(b, C$Gson$Types.m12233e(b));
        TypeAdapter a = m12064a(gson, b2[0]);
        TypeAdapter a2 = gson.m12335a(TypeToken.m12297a(b2[1]));
        ObjectConstructor a3 = this.f8303a.m12254a((TypeToken) typeToken);
        return new MapTypeAdapterFactory(this, gson, b2[0], a, b2[1], a2, a3);
    }

    private TypeAdapter<?> m12064a(Gson gson, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? TypeAdapters.f8367f : gson.m12335a(TypeToken.m12297a(type));
    }
}
