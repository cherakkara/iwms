package com.google.gson.p064b.p065a;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.gson.b.a.f */
public final class JsonTreeWriter extends JsonWriter {
    private static final Writer f8294a;
    private static final JsonPrimitive f8295b;
    private final List<JsonElement> f8296c;
    private String f8297d;
    private JsonElement f8298e;

    /* renamed from: com.google.gson.b.a.f.1 */
    static class JsonTreeWriter extends Writer {
        JsonTreeWriter() {
        }

        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    }

    static {
        f8294a = new JsonTreeWriter();
        f8295b = new JsonPrimitive("closed");
    }

    public JsonTreeWriter() {
        super(f8294a);
        this.f8296c = new ArrayList();
        this.f8298e = JsonNull.f8508a;
    }

    public JsonElement m12058a() {
        if (this.f8296c.isEmpty()) {
            return this.f8298e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f8296c);
    }

    private JsonElement m12057b() {
        return (JsonElement) this.f8296c.get(this.f8296c.size() - 1);
    }

    private void m12056a(JsonElement jsonElement) {
        if (this.f8297d != null) {
            if (!jsonElement.m12366j() || getSerializeNulls()) {
                ((JsonObject) m12057b()).m12378a(this.f8297d, jsonElement);
            }
            this.f8297d = null;
        } else if (this.f8296c.isEmpty()) {
            this.f8298e = jsonElement;
        } else {
            JsonElement b = m12057b();
            if (b instanceof JsonArray) {
                ((JsonArray) b).m12372a(jsonElement);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public JsonWriter beginArray() throws IOException {
        JsonElement jsonArray = new JsonArray();
        m12056a(jsonArray);
        this.f8296c.add(jsonArray);
        return this;
    }

    public JsonWriter endArray() throws IOException {
        if (this.f8296c.isEmpty() || this.f8297d != null) {
            throw new IllegalStateException();
        } else if (m12057b() instanceof JsonArray) {
            this.f8296c.remove(this.f8296c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter beginObject() throws IOException {
        JsonElement jsonObject = new JsonObject();
        m12056a(jsonObject);
        this.f8296c.add(jsonObject);
        return this;
    }

    public JsonWriter endObject() throws IOException {
        if (this.f8296c.isEmpty() || this.f8297d != null) {
            throw new IllegalStateException();
        } else if (m12057b() instanceof JsonObject) {
            this.f8296c.remove(this.f8296c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter name(String str) throws IOException {
        if (this.f8296c.isEmpty() || this.f8297d != null) {
            throw new IllegalStateException();
        } else if (m12057b() instanceof JsonObject) {
            this.f8297d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        m12056a(new JsonPrimitive(str));
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        m12056a(JsonNull.f8508a);
        return this;
    }

    public JsonWriter value(boolean z) throws IOException {
        m12056a(new JsonPrimitive(Boolean.valueOf(z)));
        return this;
    }

    public JsonWriter value(double d) throws IOException {
        if (isLenient() || !(Double.isNaN(d) || Double.isInfinite(d))) {
            m12056a(new JsonPrimitive(Double.valueOf(d)));
            return this;
        }
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d);
    }

    public JsonWriter value(long j) throws IOException {
        m12056a(new JsonPrimitive(Long.valueOf(j)));
        return this;
    }

    public JsonWriter value(Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        if (!isLenient()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        m12056a(new JsonPrimitive(number));
        return this;
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
        if (this.f8296c.isEmpty()) {
            this.f8296c.add(f8295b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
