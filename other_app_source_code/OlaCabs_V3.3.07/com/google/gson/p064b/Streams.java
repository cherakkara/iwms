package com.google.gson.p064b;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.p064b.p065a.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;

/* renamed from: com.google.gson.b.j */
public final class Streams {

    /* renamed from: com.google.gson.b.j.a */
    private static final class Streams extends Writer {
        private final Appendable f8459a;
        private final Streams f8460b;

        /* renamed from: com.google.gson.b.j.a.a */
        static class Streams implements CharSequence {
            char[] f8458a;

            Streams() {
            }

            public int length() {
                return this.f8458a.length;
            }

            public char charAt(int i) {
                return this.f8458a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.f8458a, i, i2 - i);
            }
        }

        private Streams(Appendable appendable) {
            this.f8460b = new Streams();
            this.f8459a = appendable;
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.f8460b.f8458a = cArr;
            this.f8459a.append(this.f8460b, i, i + i2);
        }

        public void write(int i) throws IOException {
            this.f8459a.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }
    }

    public static JsonElement m12286a(JsonReader jsonReader) throws JsonParseException {
        Object obj = 1;
        try {
            jsonReader.peek();
            obj = null;
            return (JsonElement) TypeAdapters.f8359P.m12035b(jsonReader);
        } catch (Throwable e) {
            if (obj != null) {
                return JsonNull.f8508a;
            }
            throw new JsonSyntaxException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonIOException(e22);
        } catch (Throwable e222) {
            throw new JsonSyntaxException(e222);
        }
    }

    public static void m12288a(JsonElement jsonElement, JsonWriter jsonWriter) throws IOException {
        TypeAdapters.f8359P.m12034a(jsonWriter, jsonElement);
    }

    public static Writer m12287a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new Streams(null);
    }
}
