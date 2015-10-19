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
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* renamed from: com.google.gson.b.a.j */
public final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory f8322a;
    private final DateFormat f8323b;

    /* renamed from: com.google.gson.b.a.j.1 */
    static class SqlDateTypeAdapter implements TypeAdapterFactory {
        SqlDateTypeAdapter() {
        }

        public <T> TypeAdapter<T> m12087a(Gson gson, TypeToken<T> typeToken) {
            return typeToken.m12300a() == Date.class ? new SqlDateTypeAdapter() : null;
        }
    }

    public SqlDateTypeAdapter() {
        this.f8323b = new SimpleDateFormat("MMM d, yyyy");
    }

    public /* synthetic */ Object m12091b(JsonReader jsonReader) throws IOException {
        return m12088a(jsonReader);
    }

    static {
        f8322a = new SqlDateTypeAdapter();
    }

    public synchronized Date m12088a(JsonReader jsonReader) throws IOException {
        Date date;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            date = null;
        } else {
            try {
                date = new Date(this.f8323b.parse(jsonReader.nextString()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return date;
    }

    public synchronized void m12090a(JsonWriter jsonWriter, Date date) throws IOException {
        jsonWriter.value(date == null ? null : this.f8323b.format(date));
    }
}
