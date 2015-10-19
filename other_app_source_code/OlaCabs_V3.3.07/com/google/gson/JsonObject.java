package com.google.gson;

import com.google.gson.p064b.LinkedTreeMap;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.google.gson.o */
public final class JsonObject extends JsonElement {
    private final LinkedTreeMap<String, JsonElement> f8509a;

    public JsonObject() {
        this.f8509a = new LinkedTreeMap();
    }

    public void m12378a(String str, JsonElement jsonElement) {
        if (jsonElement == null) {
            jsonElement = JsonNull.f8508a;
        }
        this.f8509a.put(str, jsonElement);
    }

    public Set<Entry<String, JsonElement>> m12379o() {
        return this.f8509a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof JsonObject) && ((JsonObject) obj).f8509a.equals(this.f8509a));
    }

    public int hashCode() {
        return this.f8509a.hashCode();
    }
}
