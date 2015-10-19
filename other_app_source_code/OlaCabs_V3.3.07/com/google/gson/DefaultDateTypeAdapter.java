package com.google.gson;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.google.gson.a */
final class DefaultDateTypeAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {
    private final DateFormat f8277a;
    private final DateFormat f8278b;
    private final DateFormat f8279c;

    public /* synthetic */ Object m12030b(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return m12029a(jsonElement, type, jsonDeserializationContext);
    }

    DefaultDateTypeAdapter() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    DefaultDateTypeAdapter(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    public DefaultDateTypeAdapter(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    DefaultDateTypeAdapter(DateFormat dateFormat, DateFormat dateFormat2) {
        this.f8277a = dateFormat;
        this.f8278b = dateFormat2;
        this.f8279c = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        this.f8279c.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public JsonElement m12028a(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonPrimitive;
        synchronized (this.f8278b) {
            jsonPrimitive = new JsonPrimitive(this.f8277a.format(date));
        }
        return jsonPrimitive;
    }

    public Date m12029a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement instanceof JsonPrimitive) {
            Date a = m12026a(jsonElement);
            if (type == Date.class) {
                return a;
            }
            if (type == Timestamp.class) {
                return new Timestamp(a.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(a.getTime());
            }
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
        }
        throw new JsonParseException("The date should be a string value");
    }

    private Date m12026a(JsonElement jsonElement) {
        Date parse;
        synchronized (this.f8278b) {
            try {
                parse = this.f8278b.parse(jsonElement.m12358b());
            } catch (ParseException e) {
                try {
                    parse = this.f8277a.parse(jsonElement.m12358b());
                } catch (ParseException e2) {
                    try {
                        parse = this.f8279c.parse(jsonElement.m12358b());
                    } catch (Throwable e3) {
                        throw new JsonSyntaxException(jsonElement.m12358b(), e3);
                    }
                }
            }
        }
        return parse;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DefaultDateTypeAdapter.class.getSimpleName());
        stringBuilder.append('(').append(this.f8278b.getClass().getSimpleName()).append(')');
        return stringBuilder.toString();
    }
}
