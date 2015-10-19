package com.google.gson;

import com.google.gson.p064b.ConstructorConstructor;
import com.google.gson.p064b.Excluder;
import com.google.gson.p064b.Primitives;
import com.google.gson.p064b.Streams;
import com.google.gson.p064b.p065a.ArrayTypeAdapter;
import com.google.gson.p064b.p065a.CollectionTypeAdapterFactory;
import com.google.gson.p064b.p065a.DateTypeAdapter;
import com.google.gson.p064b.p065a.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.p064b.p065a.JsonTreeReader;
import com.google.gson.p064b.p065a.MapTypeAdapterFactory;
import com.google.gson.p064b.p065a.ObjectTypeAdapter;
import com.google.gson.p064b.p065a.ReflectiveTypeAdapterFactory;
import com.google.gson.p064b.p065a.SqlDateTypeAdapter;
import com.google.gson.p064b.p065a.TimeTypeAdapter;
import com.google.gson.p064b.p065a.TypeAdapters;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.gson.f */
public final class Gson {
    final JsonDeserializationContext f8482a;
    final JsonSerializationContext f8483b;
    private final ThreadLocal<Map<TypeToken<?>, Gson<?>>> f8484c;
    private final Map<TypeToken<?>, TypeAdapter<?>> f8485d;
    private final List<TypeAdapterFactory> f8486e;
    private final ConstructorConstructor f8487f;
    private final boolean f8488g;
    private final boolean f8489h;
    private final boolean f8490i;
    private final boolean f8491j;

    /* renamed from: com.google.gson.f.1 */
    class Gson implements JsonDeserializationContext {
        final /* synthetic */ Gson f8476a;

        Gson(Gson gson) {
            this.f8476a = gson;
        }
    }

    /* renamed from: com.google.gson.f.2 */
    class Gson implements JsonSerializationContext {
        final /* synthetic */ Gson f8477a;

        Gson(Gson gson) {
            this.f8477a = gson;
        }
    }

    /* renamed from: com.google.gson.f.3 */
    class Gson extends TypeAdapter<Number> {
        final /* synthetic */ Gson f8478a;

        Gson(Gson gson) {
            this.f8478a = gson;
        }

        public /* synthetic */ Object m12316b(JsonReader jsonReader) throws IOException {
            return m12313a(jsonReader);
        }

        public Double m12313a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        public void m12314a(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            this.f8478a.m12331a(number.doubleValue());
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.f.4 */
    class Gson extends TypeAdapter<Number> {
        final /* synthetic */ Gson f8479a;

        Gson(Gson gson) {
            this.f8479a = gson;
        }

        public /* synthetic */ Object m12320b(JsonReader jsonReader) throws IOException {
            return m12317a(jsonReader);
        }

        public Float m12317a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Float.valueOf((float) jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        public void m12318a(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            this.f8479a.m12331a((double) number.floatValue());
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.f.5 */
    class Gson extends TypeAdapter<Number> {
        final /* synthetic */ Gson f8480a;

        Gson(Gson gson) {
            this.f8480a = gson;
        }

        public /* synthetic */ Object m12324b(JsonReader jsonReader) throws IOException {
            return m12321a(jsonReader);
        }

        public Number m12321a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Long.valueOf(jsonReader.nextLong());
            }
            jsonReader.nextNull();
            return null;
        }

        public void m12322a(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(number.toString());
            }
        }
    }

    /* renamed from: com.google.gson.f.a */
    static class Gson<T> extends TypeAdapter<T> {
        private TypeAdapter<T> f8481a;

        Gson() {
        }

        public void m12326a(TypeAdapter<T> typeAdapter) {
            if (this.f8481a != null) {
                throw new AssertionError();
            }
            this.f8481a = typeAdapter;
        }

        public T m12327b(JsonReader jsonReader) throws IOException {
            if (this.f8481a != null) {
                return this.f8481a.m12035b(jsonReader);
            }
            throw new IllegalStateException();
        }

        public void m12325a(JsonWriter jsonWriter, T t) throws IOException {
            if (this.f8481a == null) {
                throw new IllegalStateException();
            }
            this.f8481a.m12034a(jsonWriter, t);
        }
    }

    public Gson() {
        this(Excluder.f8423a, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    Gson(Excluder excluder, FieldNamingStrategy fieldNamingStrategy, Map<Type, InstanceCreator<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, LongSerializationPolicy longSerializationPolicy, List<TypeAdapterFactory> list) {
        this.f8484c = new ThreadLocal();
        this.f8485d = Collections.synchronizedMap(new HashMap());
        this.f8482a = new Gson(this);
        this.f8483b = new Gson(this);
        this.f8487f = new ConstructorConstructor(map);
        this.f8488g = z;
        this.f8490i = z3;
        this.f8489h = z4;
        this.f8491j = z5;
        List arrayList = new ArrayList();
        arrayList.add(TypeAdapters.f8360Q);
        arrayList.add(ObjectTypeAdapter.f8306a);
        arrayList.add(excluder);
        arrayList.addAll(list);
        arrayList.add(TypeAdapters.f8385x);
        arrayList.add(TypeAdapters.f8374m);
        arrayList.add(TypeAdapters.f8368g);
        arrayList.add(TypeAdapters.f8370i);
        arrayList.add(TypeAdapters.f8372k);
        arrayList.add(TypeAdapters.m12211a(Long.TYPE, Long.class, m12329a(longSerializationPolicy)));
        arrayList.add(TypeAdapters.m12211a(Double.TYPE, Double.class, m12330a(z6)));
        arrayList.add(TypeAdapters.m12211a(Float.TYPE, Float.class, m12334b(z6)));
        arrayList.add(TypeAdapters.f8379r);
        arrayList.add(TypeAdapters.f8381t);
        arrayList.add(TypeAdapters.f8387z);
        arrayList.add(TypeAdapters.f8345B);
        arrayList.add(TypeAdapters.m12210a(BigDecimal.class, TypeAdapters.f8383v));
        arrayList.add(TypeAdapters.m12210a(BigInteger.class, TypeAdapters.f8384w));
        arrayList.add(TypeAdapters.f8347D);
        arrayList.add(TypeAdapters.f8349F);
        arrayList.add(TypeAdapters.f8353J);
        arrayList.add(TypeAdapters.f8358O);
        arrayList.add(TypeAdapters.f8351H);
        arrayList.add(TypeAdapters.f8365d);
        arrayList.add(DateTypeAdapter.f8286a);
        arrayList.add(TypeAdapters.f8356M);
        arrayList.add(TimeTypeAdapter.f8324a);
        arrayList.add(SqlDateTypeAdapter.f8322a);
        arrayList.add(TypeAdapters.f8354K);
        arrayList.add(ArrayTypeAdapter.f8280a);
        arrayList.add(TypeAdapters.f8363b);
        arrayList.add(new CollectionTypeAdapterFactory(this.f8487f));
        arrayList.add(new MapTypeAdapterFactory(this.f8487f, z2));
        arrayList.add(new JsonAdapterAnnotationTypeAdapterFactory(this.f8487f));
        arrayList.add(TypeAdapters.f8361R);
        arrayList.add(new ReflectiveTypeAdapterFactory(this.f8487f, fieldNamingStrategy, excluder));
        this.f8486e = Collections.unmodifiableList(arrayList);
    }

    private TypeAdapter<Number> m12330a(boolean z) {
        if (z) {
            return TypeAdapters.f8377p;
        }
        return new Gson(this);
    }

    private TypeAdapter<Number> m12334b(boolean z) {
        if (z) {
            return TypeAdapters.f8376o;
        }
        return new Gson(this);
    }

    private void m12331a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> m12329a(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.f8375n;
        }
        return new Gson(this);
    }

    public <T> TypeAdapter<T> m12335a(TypeToken<T> typeToken) {
        TypeAdapter<T> typeAdapter = (TypeAdapter) this.f8485d.get(typeToken);
        if (typeAdapter == null) {
            Map map;
            Map map2 = (Map) this.f8484c.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.f8484c.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            Gson gson = (Gson) map.get(typeToken);
            if (gson == null) {
                try {
                    Gson gson2 = new Gson();
                    map.put(typeToken, gson2);
                    for (TypeAdapterFactory a : this.f8486e) {
                        typeAdapter = a.m12031a(this, typeToken);
                        if (typeAdapter != null) {
                            gson2.m12326a(typeAdapter);
                            this.f8485d.put(typeToken, typeAdapter);
                            map.remove(typeToken);
                            if (obj != null) {
                                this.f8484c.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + typeToken);
                } catch (Throwable th) {
                    map.remove(typeToken);
                    if (obj != null) {
                        this.f8484c.remove();
                    }
                }
            }
        }
        return typeAdapter;
    }

    public <T> TypeAdapter<T> m12336a(TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken) {
        Object obj = null;
        if (!this.f8486e.contains(typeAdapterFactory)) {
            obj = 1;
        }
        Object obj2 = obj;
        for (TypeAdapterFactory typeAdapterFactory2 : this.f8486e) {
            if (obj2 != null) {
                TypeAdapter<T> a = typeAdapterFactory2.m12031a(this, typeToken);
                if (a != null) {
                    return a;
                }
            } else if (typeAdapterFactory2 == typeAdapterFactory) {
                obj2 = 1;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + typeToken);
    }

    public <T> TypeAdapter<T> m12337a(Class<T> cls) {
        return m12335a(TypeToken.m12299b(cls));
    }

    public String m12346a(Object obj) {
        if (obj == null) {
            return m12345a(JsonNull.f8508a);
        }
        return m12347a(obj, obj.getClass());
    }

    public String m12347a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        m12352a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void m12350a(Object obj, Appendable appendable) throws JsonIOException {
        if (obj != null) {
            m12352a(obj, obj.getClass(), appendable);
        } else {
            m12349a(JsonNull.f8508a, appendable);
        }
    }

    public void m12352a(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            m12351a(obj, type, m12328a(Streams.m12287a(appendable)));
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }

    public void m12351a(Object obj, Type type, JsonWriter jsonWriter) throws JsonIOException {
        TypeAdapter a = m12335a(TypeToken.m12297a(type));
        boolean isLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.f8489h);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.f8488g);
        try {
            a.m12034a(jsonWriter, obj);
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }

    public String m12345a(JsonElement jsonElement) {
        Appendable stringWriter = new StringWriter();
        m12349a(jsonElement, stringWriter);
        return stringWriter.toString();
    }

    public void m12349a(JsonElement jsonElement, Appendable appendable) throws JsonIOException {
        try {
            m12348a(jsonElement, m12328a(Streams.m12287a(appendable)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private JsonWriter m12328a(Writer writer) throws IOException {
        if (this.f8490i) {
            writer.write(")]}'\n");
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.f8491j) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.f8488g);
        return jsonWriter;
    }

    public void m12348a(JsonElement jsonElement, JsonWriter jsonWriter) throws JsonIOException {
        boolean isLenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.f8489h);
        boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.f8488g);
        try {
            Streams.m12288a(jsonElement, jsonWriter);
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        } catch (Throwable e) {
            throw new JsonIOException(e);
        } catch (Throwable th) {
            jsonWriter.setLenient(isLenient);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }

    public <T> T m12343a(String str, Class<T> cls) throws JsonSyntaxException {
        return Primitives.m12283a((Class) cls).cast(m12344a(str, (Type) cls));
    }

    public <T> T m12344a(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return m12342a(new StringReader(str), type);
    }

    public <T> T m12341a(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        JsonReader jsonReader = new JsonReader(reader);
        Object a = m12340a(jsonReader, (Type) cls);
        Gson.m12333a(a, jsonReader);
        return Primitives.m12283a((Class) cls).cast(a);
    }

    public <T> T m12342a(Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        JsonReader jsonReader = new JsonReader(reader);
        Object a = m12340a(jsonReader, type);
        Gson.m12333a(a, jsonReader);
        return a;
    }

    private static void m12333a(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (Throwable e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T m12340a(JsonReader jsonReader, Type type) throws JsonIOException, JsonSyntaxException {
        boolean z = true;
        boolean isLenient = jsonReader.isLenient();
        jsonReader.setLenient(true);
        try {
            jsonReader.peek();
            z = false;
            T b = m12335a(TypeToken.m12297a(type)).m12035b(jsonReader);
            jsonReader.setLenient(isLenient);
            return b;
        } catch (Throwable e) {
            if (z) {
                jsonReader.setLenient(isLenient);
                return null;
            }
            throw new JsonSyntaxException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonSyntaxException(e22);
        } catch (Throwable th) {
            jsonReader.setLenient(isLenient);
        }
    }

    public <T> T m12338a(JsonElement jsonElement, Class<T> cls) throws JsonSyntaxException {
        return Primitives.m12283a((Class) cls).cast(m12339a(jsonElement, (Type) cls));
    }

    public <T> T m12339a(JsonElement jsonElement, Type type) throws JsonSyntaxException {
        if (jsonElement == null) {
            return null;
        }
        return m12340a(new JsonTreeReader(jsonElement), type);
    }

    public String toString() {
        return "{serializeNulls:" + this.f8488g + "factories:" + this.f8486e + ",instanceCreators:" + this.f8487f + "}";
    }
}
