package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* renamed from: com.google.gson.b.a.k */
public final class TimeTypeAdapter extends TypeAdapter<Time> {
    public static final TypeAdapterFactory f8324a;
    private final DateFormat f8325b;

    /* renamed from: com.google.gson.b.a.k.1 */
    static class TimeTypeAdapter implements TypeAdapterFactory {
        TimeTypeAdapter() {
        }

        public <T> TypeAdapter<T> m12092a(Gson gson, TypeToken<T> typeToken) {
            return typeToken.m12300a() == Time.class ? new TimeTypeAdapter() : null;
        }
    }

    public TimeTypeAdapter() {
        this.f8325b = new SimpleDateFormat("hh:mm:ss a");
    }

    public /* synthetic */ Object m12096b(JsonReader jsonReader) throws IOException {
        return m12093a(jsonReader);
    }

    static {
        f8324a = new TimeTypeAdapter();
    }

    public synchronized Time m12093a(JsonReader jsonReader) throws IOException {
        Time time;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            time = null;
        } else {
            try {
                time = new Time(this.f8325b.parse(jsonReader.nextString()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return time;
    }

    public synchronized void m12095a(JsonWriter jsonWriter, Time time) throws IOException {
        jsonWriter.value(time == null ? null : this.f8325b.format(time));
    }
}
