package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonSyntaxException;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.TypeAdapterFactory;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat format;

    /* renamed from: com.newrelic.com.google.gson.internal.bind.SqlDateTypeAdapter.1 */
    static class C07871 implements TypeAdapterFactory {
        C07871() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ? new SqlDateTypeAdapter() : null;
        }
    }

    public SqlDateTypeAdapter() {
        this.format = new SimpleDateFormat("MMM d, yyyy");
    }

    static {
        FACTORY = new C07871();
    }

    public synchronized Date read(JsonReader jsonReader) throws IOException {
        Date date;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            date = null;
        } else {
            try {
                date = new Date(this.format.parse(jsonReader.nextString()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return date;
    }

    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        jsonWriter.value(date == null ? null : this.format.format(date));
    }
}
