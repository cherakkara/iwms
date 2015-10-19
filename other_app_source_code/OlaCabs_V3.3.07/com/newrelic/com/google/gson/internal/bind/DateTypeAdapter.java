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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat enUsFormat;
    private final DateFormat iso8601Format;
    private final DateFormat localFormat;

    /* renamed from: com.newrelic.com.google.gson.internal.bind.DateTypeAdapter.1 */
    static class C07811 implements TypeAdapterFactory {
        C07811() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ? new DateTypeAdapter() : null;
        }
    }

    public DateTypeAdapter() {
        this.enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        this.localFormat = DateFormat.getDateTimeInstance(2, 2);
        this.iso8601Format = buildIso8601Format();
    }

    static {
        FACTORY = new C07811();
    }

    private static DateFormat buildIso8601Format() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public Date read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return deserializeToDate(jsonReader.nextString());
        }
        jsonReader.nextNull();
        return null;
    }

    private synchronized Date deserializeToDate(String str) {
        Date parse;
        try {
            parse = this.localFormat.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.enUsFormat.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.iso8601Format.parse(str);
                } catch (Throwable e3) {
                    throw new JsonSyntaxException(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(this.enUsFormat.format(date));
        }
    }
}
