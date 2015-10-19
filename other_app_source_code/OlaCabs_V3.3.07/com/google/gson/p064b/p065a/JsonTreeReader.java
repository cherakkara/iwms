package com.google.gson.p064b.p065a;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* renamed from: com.google.gson.b.a.e */
public final class JsonTreeReader extends JsonReader {
    private static final Reader f8291a;
    private static final Object f8292b;
    private final List<Object> f8293c;

    /* renamed from: com.google.gson.b.a.e.1 */
    static class JsonTreeReader extends Reader {
        JsonTreeReader() {
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    }

    static {
        f8291a = new JsonTreeReader();
        f8292b = new Object();
    }

    public JsonTreeReader(JsonElement jsonElement) {
        super(f8291a);
        this.f8293c = new ArrayList();
        this.f8293c.add(jsonElement);
    }

    public void beginArray() throws IOException {
        m12052a(JsonToken.BEGIN_ARRAY);
        this.f8293c.add(((JsonArray) m12053b()).iterator());
    }

    public void endArray() throws IOException {
        m12052a(JsonToken.END_ARRAY);
        m12054c();
        m12054c();
    }

    public void beginObject() throws IOException {
        m12052a(JsonToken.BEGIN_OBJECT);
        this.f8293c.add(((JsonObject) m12053b()).m12379o().iterator());
    }

    public void endObject() throws IOException {
        m12052a(JsonToken.END_OBJECT);
        m12054c();
        m12054c();
    }

    public boolean hasNext() throws IOException {
        JsonToken peek = peek();
        return (peek == JsonToken.END_OBJECT || peek == JsonToken.END_ARRAY) ? false : true;
    }

    public JsonToken peek() throws IOException {
        if (this.f8293c.isEmpty()) {
            return JsonToken.END_DOCUMENT;
        }
        Object b = m12053b();
        if (b instanceof Iterator) {
            boolean z = this.f8293c.get(this.f8293c.size() - 2) instanceof JsonObject;
            Iterator it = (Iterator) b;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            } else {
                if (z) {
                    return JsonToken.NAME;
                }
                this.f8293c.add(it.next());
                return peek();
            }
        } else if (b instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (b instanceof JsonArray) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (b instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) b;
                if (jsonPrimitive.m12392q()) {
                    return JsonToken.STRING;
                }
                if (jsonPrimitive.m12390o()) {
                    return JsonToken.BOOLEAN;
                }
                if (jsonPrimitive.m12391p()) {
                    return JsonToken.NUMBER;
                }
                throw new AssertionError();
            } else if (b instanceof JsonNull) {
                return JsonToken.NULL;
            } else {
                if (b == f8292b) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object m12053b() {
        return this.f8293c.get(this.f8293c.size() - 1);
    }

    private Object m12054c() {
        return this.f8293c.remove(this.f8293c.size() - 1);
    }

    private void m12052a(JsonToken jsonToken) throws IOException {
        if (peek() != jsonToken) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + peek());
        }
    }

    public String nextName() throws IOException {
        m12052a(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) m12053b()).next();
        this.f8293c.add(entry.getValue());
        return (String) entry.getKey();
    }

    public String nextString() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.STRING || peek == JsonToken.NUMBER) {
            return ((JsonPrimitive) m12054c()).m12384b();
        }
        throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + peek);
    }

    public boolean nextBoolean() throws IOException {
        m12052a(JsonToken.BOOLEAN);
        return ((JsonPrimitive) m12054c()).m12388f();
    }

    public void nextNull() throws IOException {
        m12052a(JsonToken.NULL);
        m12054c();
    }

    public double nextDouble() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.NUMBER || peek == JsonToken.STRING) {
            double c = ((JsonPrimitive) m12053b()).m12385c();
            if (isLenient() || !(Double.isNaN(c) || Double.isInfinite(c))) {
                m12054c();
                return c;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + c);
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
    }

    public long nextLong() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.NUMBER || peek == JsonToken.STRING) {
            long d = ((JsonPrimitive) m12053b()).m12386d();
            m12054c();
            return d;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
    }

    public int nextInt() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.NUMBER || peek == JsonToken.STRING) {
            int e = ((JsonPrimitive) m12053b()).m12387e();
            m12054c();
            return e;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
    }

    public void close() throws IOException {
        this.f8293c.clear();
        this.f8293c.add(f8292b);
    }

    public void skipValue() throws IOException {
        if (peek() == JsonToken.NAME) {
            nextName();
        } else {
            m12054c();
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void m12055a() throws IOException {
        m12052a(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) m12053b()).next();
        this.f8293c.add(entry.getValue());
        this.f8293c.add(new JsonPrimitive((String) entry.getKey()));
    }
}
