package com.google.gson.p064b.p065a;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.b.C$Gson$Types;
import com.google.gson.p063a.JsonAdapter;
import com.google.gson.p063a.SerializedName;
import com.google.gson.p064b.ConstructorConstructor;
import com.google.gson.p064b.Excluder;
import com.google.gson.p064b.ObjectConstructor;
import com.google.gson.p064b.Primitives;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.google.gson.b.a.i */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor f8319a;
    private final FieldNamingStrategy f8320b;
    private final Excluder f8321c;

    /* renamed from: com.google.gson.b.a.i.b */
    static abstract class ReflectiveTypeAdapterFactory {
        final String f8308g;
        final boolean f8309h;
        final boolean f8310i;

        abstract void m12070a(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        abstract void m12071a(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        abstract boolean m12072a(Object obj) throws IOException, IllegalAccessException;

        protected ReflectiveTypeAdapterFactory(String str, boolean z, boolean z2) {
            this.f8308g = str;
            this.f8309h = z;
            this.f8310i = z2;
        }
    }

    /* renamed from: com.google.gson.b.a.i.1 */
    class ReflectiveTypeAdapterFactory extends ReflectiveTypeAdapterFactory {
        final TypeAdapter<?> f8311a;
        final /* synthetic */ Gson f8312b;
        final /* synthetic */ Field f8313c;
        final /* synthetic */ TypeToken f8314d;
        final /* synthetic */ boolean f8315e;
        final /* synthetic */ ReflectiveTypeAdapterFactory f8316f;

        ReflectiveTypeAdapterFactory(ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory, String str, boolean z, boolean z2, Gson gson, Field field, TypeToken typeToken, boolean z3) {
            this.f8316f = reflectiveTypeAdapterFactory;
            this.f8312b = gson;
            this.f8313c = field;
            this.f8314d = typeToken;
            this.f8315e = z3;
            super(str, z, z2);
            this.f8311a = this.f8316f.m12080a(this.f8312b, this.f8313c, this.f8314d);
        }

        void m12074a(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
            new TypeAdapterRuntimeTypeWrapper(this.f8312b, this.f8311a, this.f8314d.m12301b()).m12034a(jsonWriter, this.f8313c.get(obj));
        }

        void m12073a(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
            Object b = this.f8311a.m12035b(jsonReader);
            if (b != null || !this.f8315e) {
                this.f8313c.set(obj, b);
            }
        }

        public boolean m12075a(Object obj) throws IOException, IllegalAccessException {
            if (this.h && this.f8313c.get(obj) != obj) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.google.gson.b.a.i.a */
    public static final class ReflectiveTypeAdapterFactory<T> extends TypeAdapter<T> {
        private final ObjectConstructor<T> f8317a;
        private final Map<String, ReflectiveTypeAdapterFactory> f8318b;

        private ReflectiveTypeAdapterFactory(ObjectConstructor<T> objectConstructor, Map<String, ReflectiveTypeAdapterFactory> map) {
            this.f8317a = objectConstructor;
            this.f8318b = map;
        }

        public T m12077b(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            T a = this.f8317a.m12238a();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory = (ReflectiveTypeAdapterFactory) this.f8318b.get(jsonReader.nextName());
                    if (reflectiveTypeAdapterFactory == null || !reflectiveTypeAdapterFactory.f8310i) {
                        jsonReader.skipValue();
                    } else {
                        reflectiveTypeAdapterFactory.m12070a(jsonReader, (Object) a);
                    }
                }
                jsonReader.endObject();
                return a;
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void m12076a(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory : this.f8318b.values()) {
                    if (reflectiveTypeAdapterFactory.m12072a(t)) {
                        jsonWriter.name(reflectiveTypeAdapterFactory.f8308g);
                        reflectiveTypeAdapterFactory.m12071a(jsonWriter, (Object) t);
                    }
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder) {
        this.f8319a = constructorConstructor;
        this.f8320b = fieldNamingStrategy;
        this.f8321c = excluder;
    }

    public boolean m12086a(Field field, boolean z) {
        return ReflectiveTypeAdapterFactory.m12084a(field, z, this.f8321c);
    }

    static boolean m12084a(Field field, boolean z, Excluder excluder) {
        return (excluder.m12266a(field.getType(), z) || excluder.m12267a(field, z)) ? false : true;
    }

    private String m12082a(Field field) {
        return ReflectiveTypeAdapterFactory.m12081a(this.f8320b, field);
    }

    static String m12081a(FieldNamingStrategy fieldNamingStrategy, Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        return serializedName == null ? fieldNamingStrategy.m12302a(field) : serializedName.m12021a();
    }

    public <T> TypeAdapter<T> m12085a(Gson gson, TypeToken<T> typeToken) {
        Class a = typeToken.m12300a();
        if (Object.class.isAssignableFrom(a)) {
            return new ReflectiveTypeAdapterFactory(m12083a(gson, (TypeToken) typeToken, a), null);
        }
        return null;
    }

    private ReflectiveTypeAdapterFactory m12078a(Gson gson, Field field, String str, TypeToken<?> typeToken, boolean z, boolean z2) {
        return new ReflectiveTypeAdapterFactory(this, str, z, z2, gson, field, typeToken, Primitives.m12285a(typeToken.m12300a()));
    }

    private TypeAdapter<?> m12080a(Gson gson, Field field, TypeToken<?> typeToken) {
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        if (jsonAdapter != null) {
            TypeAdapter<?> a = JsonAdapterAnnotationTypeAdapterFactory.m12050a(this.f8319a, gson, typeToken, jsonAdapter);
            if (a != null) {
                return a;
            }
        }
        return gson.m12335a((TypeToken) typeToken);
    }

    private Map<String, ReflectiveTypeAdapterFactory> m12083a(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        Map<String, ReflectiveTypeAdapterFactory> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b = typeToken.m12301b();
        Class a;
        while (a != Object.class) {
            for (Field field : a.getDeclaredFields()) {
                boolean a2 = m12086a(field, true);
                boolean a3 = m12086a(field, false);
                if (a2 || a3) {
                    field.setAccessible(true);
                    ReflectiveTypeAdapterFactory a4 = m12078a(gson, field, m12082a(field), TypeToken.m12297a(C$Gson$Types.m12223a(typeToken.m12301b(), a, field.getGenericType())), a2, a3);
                    a4 = (ReflectiveTypeAdapterFactory) linkedHashMap.put(a4.f8308g, a4);
                    if (a4 != null) {
                        throw new IllegalArgumentException(b + " declares multiple JSON fields named " + a4.f8308g);
                    }
                }
            }
            typeToken = TypeToken.m12297a(C$Gson$Types.m12223a(typeToken.m12301b(), a, a.getGenericSuperclass()));
            a = typeToken.m12300a();
        }
        return linkedHashMap;
    }
}
