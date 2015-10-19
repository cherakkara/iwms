package com.google.gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.gson.i */
public final class JsonArray extends JsonElement implements Iterable<JsonElement> {
    private final List<JsonElement> f8507a;

    public JsonArray() {
        this.f8507a = new ArrayList();
    }

    public void m12372a(JsonElement jsonElement) {
        if (jsonElement == null) {
            jsonElement = JsonNull.f8508a;
        }
        this.f8507a.add(jsonElement);
    }

    public Iterator<JsonElement> iterator() {
        return this.f8507a.iterator();
    }

    public Number m12371a() {
        if (this.f8507a.size() == 1) {
            return ((JsonElement) this.f8507a.get(0)).m12357a();
        }
        throw new IllegalStateException();
    }

    public String m12373b() {
        if (this.f8507a.size() == 1) {
            return ((JsonElement) this.f8507a.get(0)).m12358b();
        }
        throw new IllegalStateException();
    }

    public double m12374c() {
        if (this.f8507a.size() == 1) {
            return ((JsonElement) this.f8507a.get(0)).m12359c();
        }
        throw new IllegalStateException();
    }

    public long m12375d() {
        if (this.f8507a.size() == 1) {
            return ((JsonElement) this.f8507a.get(0)).m12360d();
        }
        throw new IllegalStateException();
    }

    public int m12376e() {
        if (this.f8507a.size() == 1) {
            return ((JsonElement) this.f8507a.get(0)).m12361e();
        }
        throw new IllegalStateException();
    }

    public boolean m12377f() {
        if (this.f8507a.size() == 1) {
            return ((JsonElement) this.f8507a.get(0)).m12362f();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof JsonArray) && ((JsonArray) obj).f8507a.equals(this.f8507a));
    }

    public int hashCode() {
        return this.f8507a.hashCode();
    }
}
