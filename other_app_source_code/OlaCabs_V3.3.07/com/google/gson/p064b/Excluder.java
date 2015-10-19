package com.google.gson.p064b;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.p063a.Expose;
import com.google.gson.p063a.Since;
import com.google.gson.p063a.Until;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.gson.b.d */
public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder f8423a;
    private double f8424b;
    private int f8425c;
    private boolean f8426d;
    private boolean f8427e;
    private List<ExclusionStrategy> f8428f;
    private List<ExclusionStrategy> f8429g;

    /* renamed from: com.google.gson.b.d.1 */
    class Excluder extends TypeAdapter<T> {
        final /* synthetic */ boolean f8417a;
        final /* synthetic */ boolean f8418b;
        final /* synthetic */ Gson f8419c;
        final /* synthetic */ TypeToken f8420d;
        final /* synthetic */ Excluder f8421e;
        private TypeAdapter<T> f8422f;

        Excluder(Excluder excluder, boolean z, boolean z2, Gson gson, TypeToken typeToken) {
            this.f8421e = excluder;
            this.f8417a = z;
            this.f8418b = z2;
            this.f8419c = gson;
            this.f8420d = typeToken;
        }

        public T m12257b(JsonReader jsonReader) throws IOException {
            if (!this.f8417a) {
                return m12255a().m12035b(jsonReader);
            }
            jsonReader.skipValue();
            return null;
        }

        public void m12256a(JsonWriter jsonWriter, T t) throws IOException {
            if (this.f8418b) {
                jsonWriter.nullValue();
            } else {
                m12255a().m12034a(jsonWriter, t);
            }
        }

        private TypeAdapter<T> m12255a() {
            TypeAdapter<T> typeAdapter = this.f8422f;
            if (typeAdapter != null) {
                return typeAdapter;
            }
            typeAdapter = this.f8419c.m12336a(this.f8421e, this.f8420d);
            this.f8422f = typeAdapter;
            return typeAdapter;
        }
    }

    public Excluder() {
        this.f8424b = -1.0d;
        this.f8425c = 136;
        this.f8426d = true;
        this.f8428f = Collections.emptyList();
        this.f8429g = Collections.emptyList();
    }

    protected /* synthetic */ Object clone() throws CloneNotSupportedException {
        return m12264a();
    }

    static {
        f8423a = new Excluder();
    }

    protected Excluder m12264a() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public <T> TypeAdapter<T> m12265a(Gson gson, TypeToken<T> typeToken) {
        Class a = typeToken.m12300a();
        boolean a2 = m12266a(a, true);
        boolean a3 = m12266a(a, false);
        if (a2 || a3) {
            return new Excluder(this, a3, a2, gson, typeToken);
        }
        return null;
    }

    public boolean m12267a(Field field, boolean z) {
        if ((this.f8425c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.f8424b != -1.0d && !m12259a((Since) field.getAnnotation(Since.class), (Until) field.getAnnotation(Until.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.f8427e) {
            Expose expose = (Expose) field.getAnnotation(Expose.class);
            if (expose == null || (z ? !expose.m12018a() : !expose.m12019b())) {
                return true;
            }
        }
        if (!this.f8426d && m12262b(field.getType())) {
            return true;
        }
        if (m12261a(field.getType())) {
            return true;
        }
        List<ExclusionStrategy> list = z ? this.f8428f : this.f8429g;
        if (!list.isEmpty()) {
            FieldAttributes fieldAttributes = new FieldAttributes(field);
            for (ExclusionStrategy a : list) {
                if (a.m12295a(fieldAttributes)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean m12266a(Class<?> cls, boolean z) {
        if (this.f8424b != -1.0d && !m12259a((Since) cls.getAnnotation(Since.class), (Until) cls.getAnnotation(Until.class))) {
            return true;
        }
        if (!this.f8426d && m12262b(cls)) {
            return true;
        }
        if (m12261a((Class) cls)) {
            return true;
        }
        for (ExclusionStrategy a : z ? this.f8428f : this.f8429g) {
            if (a.m12296a((Class) cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean m12261a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean m12262b(Class<?> cls) {
        return cls.isMemberClass() && !m12263c(cls);
    }

    private boolean m12263c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean m12259a(Since since, Until until) {
        return m12258a(since) && m12260a(until);
    }

    private boolean m12258a(Since since) {
        if (since == null || since.m12022a() <= this.f8424b) {
            return true;
        }
        return false;
    }

    private boolean m12260a(Until until) {
        if (until == null || until.m12023a() > this.f8424b) {
            return true;
        }
        return false;
    }
}
