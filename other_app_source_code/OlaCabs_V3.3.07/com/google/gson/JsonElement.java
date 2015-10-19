package com.google.gson;

import com.google.gson.p064b.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* renamed from: com.google.gson.l */
public abstract class JsonElement {
    public boolean m12363g() {
        return this instanceof JsonArray;
    }

    public boolean m12364h() {
        return this instanceof JsonObject;
    }

    public boolean m12365i() {
        return this instanceof JsonPrimitive;
    }

    public boolean m12366j() {
        return this instanceof JsonNull;
    }

    public JsonObject m12367k() {
        if (m12364h()) {
            return (JsonObject) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public JsonArray m12368l() {
        if (m12363g()) {
            return (JsonArray) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public JsonPrimitive m12369m() {
        if (m12365i()) {
            return (JsonPrimitive) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    public boolean m12362f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    Boolean m12370n() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number m12357a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String m12358b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double m12359c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long m12360d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int m12361e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setLenient(true);
            Streams.m12288a(this, jsonWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
