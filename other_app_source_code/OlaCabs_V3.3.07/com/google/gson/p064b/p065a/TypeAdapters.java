package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.b.a.m.AnonymousClass15;
import com.google.gson.b.a.m.AnonymousClass20;
import com.google.gson.b.a.m.AnonymousClass21;
import com.google.gson.b.a.m.AnonymousClass23;
import com.google.gson.b.a.m.AnonymousClass24;
import com.google.gson.b.a.m.AnonymousClass25;
import com.google.gson.p063a.SerializedName;
import com.google.gson.p064b.LazilyParsedNumber;
import com.google.gson.p064b.p065a.TypeAdapters.TypeAdapters;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

/* renamed from: com.google.gson.b.a.m */
public final class TypeAdapters {
    public static final TypeAdapter<StringBuffer> f8344A;
    public static final TypeAdapterFactory f8345B;
    public static final TypeAdapter<URL> f8346C;
    public static final TypeAdapterFactory f8347D;
    public static final TypeAdapter<URI> f8348E;
    public static final TypeAdapterFactory f8349F;
    public static final TypeAdapter<InetAddress> f8350G;
    public static final TypeAdapterFactory f8351H;
    public static final TypeAdapter<UUID> f8352I;
    public static final TypeAdapterFactory f8353J;
    public static final TypeAdapterFactory f8354K;
    public static final TypeAdapter<Calendar> f8355L;
    public static final TypeAdapterFactory f8356M;
    public static final TypeAdapter<Locale> f8357N;
    public static final TypeAdapterFactory f8358O;
    public static final TypeAdapter<JsonElement> f8359P;
    public static final TypeAdapterFactory f8360Q;
    public static final TypeAdapterFactory f8361R;
    public static final TypeAdapter<Class> f8362a;
    public static final TypeAdapterFactory f8363b;
    public static final TypeAdapter<BitSet> f8364c;
    public static final TypeAdapterFactory f8365d;
    public static final TypeAdapter<Boolean> f8366e;
    public static final TypeAdapter<Boolean> f8367f;
    public static final TypeAdapterFactory f8368g;
    public static final TypeAdapter<Number> f8369h;
    public static final TypeAdapterFactory f8370i;
    public static final TypeAdapter<Number> f8371j;
    public static final TypeAdapterFactory f8372k;
    public static final TypeAdapter<Number> f8373l;
    public static final TypeAdapterFactory f8374m;
    public static final TypeAdapter<Number> f8375n;
    public static final TypeAdapter<Number> f8376o;
    public static final TypeAdapter<Number> f8377p;
    public static final TypeAdapter<Number> f8378q;
    public static final TypeAdapterFactory f8379r;
    public static final TypeAdapter<Character> f8380s;
    public static final TypeAdapterFactory f8381t;
    public static final TypeAdapter<String> f8382u;
    public static final TypeAdapter<BigDecimal> f8383v;
    public static final TypeAdapter<BigInteger> f8384w;
    public static final TypeAdapterFactory f8385x;
    public static final TypeAdapter<StringBuilder> f8386y;
    public static final TypeAdapterFactory f8387z;

    /* renamed from: com.google.gson.b.a.m.1 */
    static class TypeAdapters extends TypeAdapter<Class> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12141b(JsonReader jsonReader) throws IOException {
            return m12138a(jsonReader);
        }

        public void m12139a(JsonWriter jsonWriter, Class cls) throws IOException {
            if (cls == null) {
                jsonWriter.nullValue();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        public Class m12138a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.google.gson.b.a.m.20 */
    static class AnonymousClass20 implements TypeAdapterFactory {
        final /* synthetic */ Class f8331a;
        final /* synthetic */ TypeAdapter f8332b;

        AnonymousClass20(Class cls, TypeAdapter typeAdapter) {
            this.f8331a = cls;
            this.f8332b = typeAdapter;
        }

        public <T> TypeAdapter<T> m12142a(Gson gson, TypeToken<T> typeToken) {
            return typeToken.m12300a() == this.f8331a ? this.f8332b : null;
        }

        public String toString() {
            return "Factory[type=" + this.f8331a.getName() + ",adapter=" + this.f8332b + "]";
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.google.gson.b.a.m.21 */
    static class AnonymousClass21 implements TypeAdapterFactory {
        final /* synthetic */ Class f8333a;
        final /* synthetic */ Class f8334b;
        final /* synthetic */ TypeAdapter f8335c;

        AnonymousClass21(Class cls, Class cls2, TypeAdapter typeAdapter) {
            this.f8333a = cls;
            this.f8334b = cls2;
            this.f8335c = typeAdapter;
        }

        public <T> TypeAdapter<T> m12143a(Gson gson, TypeToken<T> typeToken) {
            Class a = typeToken.m12300a();
            return (a == this.f8333a || a == this.f8334b) ? this.f8335c : null;
        }

        public String toString() {
            return "Factory[type=" + this.f8334b.getName() + "+" + this.f8333a.getName() + ",adapter=" + this.f8335c + "]";
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.google.gson.b.a.m.23 */
    static class AnonymousClass23 implements TypeAdapterFactory {
        final /* synthetic */ Class f8336a;
        final /* synthetic */ Class f8337b;
        final /* synthetic */ TypeAdapter f8338c;

        AnonymousClass23(Class cls, Class cls2, TypeAdapter typeAdapter) {
            this.f8336a = cls;
            this.f8337b = cls2;
            this.f8338c = typeAdapter;
        }

        public <T> TypeAdapter<T> m12148a(Gson gson, TypeToken<T> typeToken) {
            Class a = typeToken.m12300a();
            return (a == this.f8336a || a == this.f8337b) ? this.f8338c : null;
        }

        public String toString() {
            return "Factory[type=" + this.f8336a.getName() + "+" + this.f8337b.getName() + ",adapter=" + this.f8338c + "]";
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.google.gson.b.a.m.24 */
    static class AnonymousClass24 implements TypeAdapterFactory {
        final /* synthetic */ Class f8339a;
        final /* synthetic */ TypeAdapter f8340b;

        AnonymousClass24(Class cls, TypeAdapter typeAdapter) {
            this.f8339a = cls;
            this.f8340b = typeAdapter;
        }

        public <T> TypeAdapter<T> m12149a(Gson gson, TypeToken<T> typeToken) {
            return this.f8339a.isAssignableFrom(typeToken.m12300a()) ? this.f8340b : null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f8339a.getName() + ",adapter=" + this.f8340b + "]";
        }
    }

    /* compiled from: TypeAdapters */
    /* renamed from: com.google.gson.b.a.m.25 */
    static /* synthetic */ class AnonymousClass25 {
        static final /* synthetic */ int[] f8341a;

        static {
            f8341a = new int[JsonToken.values().length];
            try {
                f8341a[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8341a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8341a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8341a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f8341a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8341a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f8341a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f8341a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f8341a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f8341a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* renamed from: com.google.gson.b.a.m.2 */
    static class TypeAdapters extends TypeAdapter<Number> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12169b(JsonReader jsonReader) throws IOException {
            return m12166a(jsonReader);
        }

        public Number m12166a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        public void m12167a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.b.a.m.3 */
    static class TypeAdapters extends TypeAdapter<Number> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12181b(JsonReader jsonReader) throws IOException {
            return m12178a(jsonReader);
        }

        public Number m12178a(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            switch (AnonymousClass25.f8341a[peek.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return new LazilyParsedNumber(jsonReader.nextString());
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    jsonReader.nextNull();
                    return null;
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + peek);
            }
        }

        public void m12179a(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.b.a.m.4 */
    static class TypeAdapters extends TypeAdapter<Character> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12185b(JsonReader jsonReader) throws IOException {
            return m12182a(jsonReader);
        }

        public Character m12182a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if (nextString.length() == 1) {
                return Character.valueOf(nextString.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + nextString);
        }

        public void m12183a(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch == null ? null : String.valueOf(ch));
        }
    }

    /* renamed from: com.google.gson.b.a.m.5 */
    static class TypeAdapters extends TypeAdapter<String> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12189b(JsonReader jsonReader) throws IOException {
            return m12186a(jsonReader);
        }

        public String m12186a(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else if (peek == JsonToken.BOOLEAN) {
                return Boolean.toString(jsonReader.nextBoolean());
            } else {
                return jsonReader.nextString();
            }
        }

        public void m12188a(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }
    }

    /* renamed from: com.google.gson.b.a.m.6 */
    static class TypeAdapters extends TypeAdapter<BigDecimal> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12193b(JsonReader jsonReader) throws IOException {
            return m12190a(jsonReader);
        }

        public BigDecimal m12190a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigDecimal(jsonReader.nextString());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void m12192a(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
            jsonWriter.value((Number) bigDecimal);
        }
    }

    /* renamed from: com.google.gson.b.a.m.7 */
    static class TypeAdapters extends TypeAdapter<BigInteger> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12197b(JsonReader jsonReader) throws IOException {
            return m12194a(jsonReader);
        }

        public BigInteger m12194a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigInteger(jsonReader.nextString());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void m12196a(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
            jsonWriter.value((Number) bigInteger);
        }
    }

    /* renamed from: com.google.gson.b.a.m.8 */
    static class TypeAdapters extends TypeAdapter<StringBuilder> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12201b(JsonReader jsonReader) throws IOException {
            return m12198a(jsonReader);
        }

        public StringBuilder m12198a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return new StringBuilder(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        public void m12200a(JsonWriter jsonWriter, StringBuilder stringBuilder) throws IOException {
            jsonWriter.value(stringBuilder == null ? null : stringBuilder.toString());
        }
    }

    /* renamed from: com.google.gson.b.a.m.9 */
    static class TypeAdapters extends TypeAdapter<StringBuffer> {
        TypeAdapters() {
        }

        public /* synthetic */ Object m12205b(JsonReader jsonReader) throws IOException {
            return m12202a(jsonReader);
        }

        public StringBuffer m12202a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return new StringBuffer(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        public void m12204a(JsonWriter jsonWriter, StringBuffer stringBuffer) throws IOException {
            jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* renamed from: com.google.gson.b.a.m.a */
    private static final class TypeAdapters<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<String, T> f8342a;
        private final Map<T, String> f8343b;

        public /* synthetic */ Object m12209b(JsonReader jsonReader) throws IOException {
            return m12206a(jsonReader);
        }

        public TypeAdapters(Class<T> cls) {
            this.f8342a = new HashMap();
            this.f8343b = new HashMap();
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    Object a;
                    String name = enumR.name();
                    SerializedName serializedName = (SerializedName) cls.getField(name).getAnnotation(SerializedName.class);
                    if (serializedName != null) {
                        a = serializedName.m12021a();
                    } else {
                        String str = name;
                    }
                    this.f8342a.put(a, enumR);
                    this.f8343b.put(enumR, a);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public T m12206a(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return (Enum) this.f8342a.get(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        public void m12207a(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(t == null ? null : (String) this.f8343b.get(t));
        }
    }

    static {
        f8362a = new TypeAdapters();
        f8363b = TypeAdapters.m12210a(Class.class, f8362a);
        f8364c = new TypeAdapter<BitSet>() {
            public /* synthetic */ Object m12111b(JsonReader jsonReader) throws IOException {
                return m12108a(jsonReader);
            }

            public BitSet m12108a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                BitSet bitSet = new BitSet();
                jsonReader.beginArray();
                JsonToken peek = jsonReader.peek();
                int i = 0;
                while (peek != JsonToken.END_ARRAY) {
                    boolean z;
                    switch (AnonymousClass25.f8341a[peek.ordinal()]) {
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            if (jsonReader.nextInt() == 0) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            z = jsonReader.nextBoolean();
                            break;
                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                            String nextString = jsonReader.nextString();
                            try {
                                if (Integer.parseInt(nextString) == 0) {
                                    z = false;
                                    break;
                                }
                                z = true;
                                break;
                            } catch (NumberFormatException e) {
                                throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + nextString);
                            }
                        default:
                            throw new JsonSyntaxException("Invalid bitset value type: " + peek);
                    }
                    if (z) {
                        bitSet.set(i);
                    }
                    i++;
                    peek = jsonReader.peek();
                }
                jsonReader.endArray();
                return bitSet;
            }

            public void m12110a(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
                if (bitSet == null) {
                    jsonWriter.nullValue();
                    return;
                }
                jsonWriter.beginArray();
                for (int i = 0; i < bitSet.length(); i++) {
                    int i2;
                    if (bitSet.get(i)) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    jsonWriter.value((long) i2);
                }
                jsonWriter.endArray();
            }
        };
        f8365d = TypeAdapters.m12210a(BitSet.class, f8364c);
        f8366e = new TypeAdapter<Boolean>() {
            public /* synthetic */ Object m12147b(JsonReader jsonReader) throws IOException {
                return m12144a(jsonReader);
            }

            public Boolean m12144a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                } else if (jsonReader.peek() == JsonToken.STRING) {
                    return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
                } else {
                    return Boolean.valueOf(jsonReader.nextBoolean());
                }
            }

            public void m12145a(JsonWriter jsonWriter, Boolean bool) throws IOException {
                if (bool == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(bool.booleanValue());
                }
            }
        };
        f8367f = new TypeAdapter<Boolean>() {
            public /* synthetic */ Object m12153b(JsonReader jsonReader) throws IOException {
                return m12150a(jsonReader);
            }

            public Boolean m12150a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Boolean.valueOf(jsonReader.nextString());
                }
                jsonReader.nextNull();
                return null;
            }

            public void m12151a(JsonWriter jsonWriter, Boolean bool) throws IOException {
                jsonWriter.value(bool == null ? "null" : bool.toString());
            }
        };
        f8368g = TypeAdapters.m12211a(Boolean.TYPE, Boolean.class, f8366e);
        f8369h = new TypeAdapter<Number>() {
            public /* synthetic */ Object m12157b(JsonReader jsonReader) throws IOException {
                return m12154a(jsonReader);
            }

            public Number m12154a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Byte.valueOf((byte) jsonReader.nextInt());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void m12155a(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        f8370i = TypeAdapters.m12211a(Byte.TYPE, Byte.class, f8369h);
        f8371j = new TypeAdapter<Number>() {
            public /* synthetic */ Object m12161b(JsonReader jsonReader) throws IOException {
                return m12158a(jsonReader);
            }

            public Number m12158a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Short.valueOf((short) jsonReader.nextInt());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void m12159a(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        f8372k = TypeAdapters.m12211a(Short.TYPE, Short.class, f8371j);
        f8373l = new TypeAdapter<Number>() {
            public /* synthetic */ Object m12165b(JsonReader jsonReader) throws IOException {
                return m12162a(jsonReader);
            }

            public Number m12162a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Integer.valueOf(jsonReader.nextInt());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void m12163a(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        f8374m = TypeAdapters.m12211a(Integer.TYPE, Integer.class, f8373l);
        f8375n = new TypeAdapter<Number>() {
            public /* synthetic */ Object m12173b(JsonReader jsonReader) throws IOException {
                return m12170a(jsonReader);
            }

            public Number m12170a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Long.valueOf(jsonReader.nextLong());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void m12171a(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        f8376o = new TypeAdapter<Number>() {
            public /* synthetic */ Object m12177b(JsonReader jsonReader) throws IOException {
                return m12174a(jsonReader);
            }

            public Number m12174a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) jsonReader.nextDouble());
                }
                jsonReader.nextNull();
                return null;
            }

            public void m12175a(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        f8377p = new TypeAdapters();
        f8378q = new TypeAdapters();
        f8379r = TypeAdapters.m12210a(Number.class, f8378q);
        f8380s = new TypeAdapters();
        f8381t = TypeAdapters.m12211a(Character.TYPE, Character.class, f8380s);
        f8382u = new TypeAdapters();
        f8383v = new TypeAdapters();
        f8384w = new TypeAdapters();
        f8385x = TypeAdapters.m12210a(String.class, f8382u);
        f8386y = new TypeAdapters();
        f8387z = TypeAdapters.m12210a(StringBuilder.class, f8386y);
        f8344A = new TypeAdapters();
        f8345B = TypeAdapters.m12210a(StringBuffer.class, f8344A);
        f8346C = new TypeAdapter<URL>() {
            public /* synthetic */ Object m12103b(JsonReader jsonReader) throws IOException {
                return m12100a(jsonReader);
            }

            public URL m12100a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextString = jsonReader.nextString();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URL(nextString);
            }

            public void m12102a(JsonWriter jsonWriter, URL url) throws IOException {
                jsonWriter.value(url == null ? null : url.toExternalForm());
            }
        };
        f8347D = TypeAdapters.m12210a(URL.class, f8346C);
        f8348E = new TypeAdapter<URI>() {
            public /* synthetic */ Object m12107b(JsonReader jsonReader) throws IOException {
                return m12104a(jsonReader);
            }

            public URI m12104a(JsonReader jsonReader) throws IOException {
                URI uri = null;
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    try {
                        String nextString = jsonReader.nextString();
                        if (!"null".equals(nextString)) {
                            uri = new URI(nextString);
                        }
                    } catch (Throwable e) {
                        throw new JsonIOException(e);
                    }
                }
                return uri;
            }

            public void m12106a(JsonWriter jsonWriter, URI uri) throws IOException {
                jsonWriter.value(uri == null ? null : uri.toASCIIString());
            }
        };
        f8349F = TypeAdapters.m12210a(URI.class, f8348E);
        f8350G = new TypeAdapter<InetAddress>() {
            public /* synthetic */ Object m12115b(JsonReader jsonReader) throws IOException {
                return m12112a(jsonReader);
            }

            public InetAddress m12112a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return InetAddress.getByName(jsonReader.nextString());
                }
                jsonReader.nextNull();
                return null;
            }

            public void m12114a(JsonWriter jsonWriter, InetAddress inetAddress) throws IOException {
                jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
            }
        };
        f8351H = TypeAdapters.m12212b(InetAddress.class, f8350G);
        f8352I = new TypeAdapter<UUID>() {
            public /* synthetic */ Object m12119b(JsonReader jsonReader) throws IOException {
                return m12116a(jsonReader);
            }

            public UUID m12116a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return UUID.fromString(jsonReader.nextString());
                }
                jsonReader.nextNull();
                return null;
            }

            public void m12118a(JsonWriter jsonWriter, UUID uuid) throws IOException {
                jsonWriter.value(uuid == null ? null : uuid.toString());
            }
        };
        f8353J = TypeAdapters.m12210a(UUID.class, f8352I);
        f8354K = new TypeAdapterFactory() {

            /* renamed from: com.google.gson.b.a.m.15.1 */
            class TypeAdapters extends TypeAdapter<Timestamp> {
                final /* synthetic */ TypeAdapter f8329a;
                final /* synthetic */ AnonymousClass15 f8330b;

                TypeAdapters(AnonymousClass15 anonymousClass15, TypeAdapter typeAdapter) {
                    this.f8330b = anonymousClass15;
                    this.f8329a = typeAdapter;
                }

                public /* synthetic */ Object m12123b(JsonReader jsonReader) throws IOException {
                    return m12120a(jsonReader);
                }

                public Timestamp m12120a(JsonReader jsonReader) throws IOException {
                    Date date = (Date) this.f8329a.m12035b(jsonReader);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void m12122a(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
                    this.f8329a.m12034a(jsonWriter, timestamp);
                }
            }

            public <T> TypeAdapter<T> m12124a(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.m12300a() != Timestamp.class) {
                    return null;
                }
                return new com.google.gson.p064b.p065a.TypeAdapters.15.TypeAdapters(this, gson.m12337a(Date.class));
            }
        };
        f8355L = new TypeAdapter<Calendar>() {
            public /* synthetic */ Object m12128b(JsonReader jsonReader) throws IOException {
                return m12125a(jsonReader);
            }

            public Calendar m12125a(JsonReader jsonReader) throws IOException {
                int i = 0;
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                jsonReader.beginObject();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (jsonReader.peek() != JsonToken.END_OBJECT) {
                    String nextName = jsonReader.nextName();
                    int nextInt = jsonReader.nextInt();
                    if ("year".equals(nextName)) {
                        i6 = nextInt;
                    } else if ("month".equals(nextName)) {
                        i5 = nextInt;
                    } else if ("dayOfMonth".equals(nextName)) {
                        i4 = nextInt;
                    } else if ("hourOfDay".equals(nextName)) {
                        i3 = nextInt;
                    } else if ("minute".equals(nextName)) {
                        i2 = nextInt;
                    } else if ("second".equals(nextName)) {
                        i = nextInt;
                    }
                }
                jsonReader.endObject();
                return new GregorianCalendar(i6, i5, i4, i3, i2, i);
            }

            public void m12127a(JsonWriter jsonWriter, Calendar calendar) throws IOException {
                if (calendar == null) {
                    jsonWriter.nullValue();
                    return;
                }
                jsonWriter.beginObject();
                jsonWriter.name("year");
                jsonWriter.value((long) calendar.get(1));
                jsonWriter.name("month");
                jsonWriter.value((long) calendar.get(2));
                jsonWriter.name("dayOfMonth");
                jsonWriter.value((long) calendar.get(5));
                jsonWriter.name("hourOfDay");
                jsonWriter.value((long) calendar.get(11));
                jsonWriter.name("minute");
                jsonWriter.value((long) calendar.get(12));
                jsonWriter.name("second");
                jsonWriter.value((long) calendar.get(13));
                jsonWriter.endObject();
            }
        };
        f8356M = TypeAdapters.m12213b(Calendar.class, GregorianCalendar.class, f8355L);
        f8357N = new TypeAdapter<Locale>() {
            public /* synthetic */ Object m12132b(JsonReader jsonReader) throws IOException {
                return m12129a(jsonReader);
            }

            public Locale m12129a(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextToken;
                String nextToken2;
                String nextToken3;
                StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
                if (stringTokenizer.hasMoreElements()) {
                    nextToken = stringTokenizer.nextToken();
                } else {
                    nextToken = null;
                }
                if (stringTokenizer.hasMoreElements()) {
                    nextToken2 = stringTokenizer.nextToken();
                } else {
                    nextToken2 = null;
                }
                if (stringTokenizer.hasMoreElements()) {
                    nextToken3 = stringTokenizer.nextToken();
                } else {
                    nextToken3 = null;
                }
                if (nextToken2 == null && nextToken3 == null) {
                    return new Locale(nextToken);
                }
                if (nextToken3 == null) {
                    return new Locale(nextToken, nextToken2);
                }
                return new Locale(nextToken, nextToken2, nextToken3);
            }

            public void m12131a(JsonWriter jsonWriter, Locale locale) throws IOException {
                jsonWriter.value(locale == null ? null : locale.toString());
            }
        };
        f8358O = TypeAdapters.m12210a(Locale.class, f8357N);
        f8359P = new TypeAdapter<JsonElement>() {
            public /* synthetic */ Object m12136b(JsonReader jsonReader) throws IOException {
                return m12133a(jsonReader);
            }

            public JsonElement m12133a(JsonReader jsonReader) throws IOException {
                JsonElement jsonArray;
                switch (AnonymousClass25.f8341a[jsonReader.peek().ordinal()]) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        return new JsonPrimitive(new LazilyParsedNumber(jsonReader.nextString()));
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        return new JsonPrimitive(jsonReader.nextString());
                    case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                        jsonReader.nextNull();
                        return JsonNull.f8508a;
                    case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                        jsonArray = new JsonArray();
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonArray.m12372a(m12133a(jsonReader));
                        }
                        jsonReader.endArray();
                        return jsonArray;
                    case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                        jsonArray = new JsonObject();
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            jsonArray.m12378a(jsonReader.nextName(), m12133a(jsonReader));
                        }
                        jsonReader.endObject();
                        return jsonArray;
                    default:
                        throw new IllegalArgumentException();
                }
            }

            public void m12134a(JsonWriter jsonWriter, JsonElement jsonElement) throws IOException {
                if (jsonElement == null || jsonElement.m12366j()) {
                    jsonWriter.nullValue();
                } else if (jsonElement.m12365i()) {
                    JsonPrimitive m = jsonElement.m12369m();
                    if (m.m12391p()) {
                        jsonWriter.value(m.m12382a());
                    } else if (m.m12390o()) {
                        jsonWriter.value(m.m12388f());
                    } else {
                        jsonWriter.value(m.m12384b());
                    }
                } else if (jsonElement.m12363g()) {
                    jsonWriter.beginArray();
                    Iterator it = jsonElement.m12368l().iterator();
                    while (it.hasNext()) {
                        m12134a(jsonWriter, (JsonElement) it.next());
                    }
                    jsonWriter.endArray();
                } else if (jsonElement.m12364h()) {
                    jsonWriter.beginObject();
                    for (Entry entry : jsonElement.m12367k().m12379o()) {
                        jsonWriter.name((String) entry.getKey());
                        m12134a(jsonWriter, (JsonElement) entry.getValue());
                    }
                    jsonWriter.endObject();
                } else {
                    throw new IllegalArgumentException("Couldn't write " + jsonElement.getClass());
                }
            }
        };
        f8360Q = TypeAdapters.m12212b(JsonElement.class, f8359P);
        f8361R = new TypeAdapterFactory() {
            public <T> TypeAdapter<T> m12137a(Gson gson, TypeToken<T> typeToken) {
                Class a = typeToken.m12300a();
                if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
                    return null;
                }
                if (!a.isEnum()) {
                    a = a.getSuperclass();
                }
                return new TypeAdapters(a);
            }
        };
    }

    public static <TT> TypeAdapterFactory m12210a(Class<TT> cls, TypeAdapter<TT> typeAdapter) {
        return new AnonymousClass20(cls, typeAdapter);
    }

    public static <TT> TypeAdapterFactory m12211a(Class<TT> cls, Class<TT> cls2, TypeAdapter<? super TT> typeAdapter) {
        return new AnonymousClass21(cls, cls2, typeAdapter);
    }

    public static <TT> TypeAdapterFactory m12213b(Class<TT> cls, Class<? extends TT> cls2, TypeAdapter<? super TT> typeAdapter) {
        return new AnonymousClass23(cls, cls2, typeAdapter);
    }

    public static <TT> TypeAdapterFactory m12212b(Class<TT> cls, TypeAdapter<TT> typeAdapter) {
        return new AnonymousClass24(cls, typeAdapter);
    }
}
