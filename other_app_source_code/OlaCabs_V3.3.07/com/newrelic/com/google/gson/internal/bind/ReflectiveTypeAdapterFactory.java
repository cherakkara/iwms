package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.FieldNamingStrategy;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonSyntaxException;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.TypeAdapterFactory;
import com.newrelic.com.google.gson.annotations.SerializedName;
import com.newrelic.com.google.gson.internal.C$Gson$Types;
import com.newrelic.com.google.gson.internal.ConstructorConstructor;
import com.newrelic.com.google.gson.internal.Excluder;
import com.newrelic.com.google.gson.internal.ObjectConstructor;
import com.newrelic.com.google.gson.internal.Primitives;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String str, boolean z, boolean z2) {
            this.name = str;
            this.serialized = z;
            this.deserialized = z2;
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.1 */
    class C07861 extends BoundField {
        final TypeAdapter<?> typeAdapter;
        final /* synthetic */ Gson val$context;
        final /* synthetic */ Field val$field;
        final /* synthetic */ TypeToken val$fieldType;
        final /* synthetic */ boolean val$isPrimitive;

        C07861(String str, boolean z, boolean z2, Gson gson, TypeToken typeToken, Field field, boolean z3) {
            this.val$context = gson;
            this.val$fieldType = typeToken;
            this.val$field = field;
            this.val$isPrimitive = z3;
            super(str, z, z2);
            this.typeAdapter = this.val$context.getAdapter(this.val$fieldType);
        }

        void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
            new TypeAdapterRuntimeTypeWrapper(this.val$context, this.typeAdapter, this.val$fieldType.getType()).write(jsonWriter, this.val$field.get(obj));
        }

        void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
            Object read = this.typeAdapter.read(jsonReader);
            if (read != null || !this.val$isPrimitive) {
                this.val$field.set(obj, read);
            }
        }
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        private Adapter(ObjectConstructor<T> objectConstructor, Map<String, BoundField> map) {
            this.constructor = objectConstructor;
            this.boundFields = map;
        }

        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            T construct = this.constructor.construct();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    BoundField boundField = (BoundField) this.boundFields.get(jsonReader.nextName());
                    if (boundField == null || !boundField.deserialized) {
                        jsonReader.skipValue();
                    } else {
                        boundField.read(jsonReader, construct);
                    }
                }
                jsonReader.endObject();
                return construct;
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.serialized) {
                        jsonWriter.name(boundField.name);
                        boundField.write(jsonWriter, t);
                    }
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder;
    }

    public boolean excludeField(Field field, boolean z) {
        return (this.excluder.excludeClass(field.getType(), z) || this.excluder.excludeField(field, z)) ? false : true;
    }

    private String getFieldName(Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        return serializedName == null ? this.fieldNamingPolicy.translateName(field) : serializedName.value();
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class rawType = typeToken.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new Adapter(getBoundFields(gson, typeToken, rawType), null);
        }
        return null;
    }

    private BoundField createBoundField(Gson gson, Field field, String str, TypeToken<?> typeToken, boolean z, boolean z2) {
        return new C07861(str, z, z2, gson, typeToken, field, Primitives.isPrimitive(typeToken.getRawType()));
    }

    private Map<String, BoundField> getBoundFields(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        Map<String, BoundField> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = typeToken.getType();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                boolean excludeField = excludeField(field, true);
                boolean excludeField2 = excludeField(field, false);
                if (excludeField || excludeField2) {
                    field.setAccessible(true);
                    BoundField createBoundField = createBoundField(gson, field, getFieldName(field), TypeToken.get(C$Gson$Types.resolve(typeToken.getType(), cls, field.getGenericType())), excludeField, excludeField2);
                    createBoundField = (BoundField) linkedHashMap.put(createBoundField.name, createBoundField);
                    if (createBoundField != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + createBoundField.name);
                    }
                }
            }
            typeToken = TypeToken.get(C$Gson$Types.resolve(typeToken.getType(), cls, cls.getGenericSuperclass()));
            cls = typeToken.getRawType();
        }
        return linkedHashMap;
    }
}
