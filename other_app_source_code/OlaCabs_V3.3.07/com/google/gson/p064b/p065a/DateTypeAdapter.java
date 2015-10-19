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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.google.gson.b.a.c */
public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory f8286a;
    private final DateFormat f8287b;
    private final DateFormat f8288c;
    private final DateFormat f8289d;

    /* renamed from: com.google.gson.b.a.c.1 */
    static class DateTypeAdapter implements TypeAdapterFactory {
        DateTypeAdapter() {
        }

        public <T> TypeAdapter<T> m12043a(Gson gson, TypeToken<T> typeToken) {
            return typeToken.m12300a() == Date.class ? new DateTypeAdapter() : null;
        }
    }

    public DateTypeAdapter() {
        this.f8287b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        this.f8288c = DateFormat.getDateTimeInstance(2, 2);
        this.f8289d = DateTypeAdapter.m12044a();
    }

    public /* synthetic */ Object m12049b(JsonReader jsonReader) throws IOException {
        return m12046a(jsonReader);
    }

    static {
        f8286a = new DateTypeAdapter();
    }

    private static DateFormat m12044a() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public Date m12046a(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return m12045a(jsonReader.nextString());
        }
        jsonReader.nextNull();
        return null;
    }

    private synchronized Date m12045a(String str) {
        Date parse;
        try {
            parse = this.f8288c.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.f8287b.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.f8289d.parse(str);
                } catch (Throwable e3) {
                    throw new JsonSyntaxException(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void m12048a(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(this.f8287b.format(date));
        }
    }
}
