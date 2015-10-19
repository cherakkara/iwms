package com.google.gson.p064b.p065a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.p064b.LinkedTreeMap;
import com.google.gson.p066c.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.gson.b.a.h */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory f8306a;
    private final Gson f8307b;

    /* renamed from: com.google.gson.b.a.h.1 */
    static class ObjectTypeAdapter implements TypeAdapterFactory {
        ObjectTypeAdapter() {
        }

        public <T> TypeAdapter<T> m12067a(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.m12300a() == Object.class) {
                return new ObjectTypeAdapter(null);
            }
            return null;
        }
    }

    /* renamed from: com.google.gson.b.a.h.2 */
    static /* synthetic */ class ObjectTypeAdapter {
        static final /* synthetic */ int[] f8305a;

        static {
            f8305a = new int[JsonToken.values().length];
            try {
                f8305a[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8305a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8305a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8305a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f8305a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8305a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    static {
        f8306a = new ObjectTypeAdapter();
    }

    private ObjectTypeAdapter(Gson gson) {
        this.f8307b = gson;
    }

    public Object m12069b(JsonReader jsonReader) throws IOException {
        switch (ObjectTypeAdapter.f8305a[jsonReader.peek().ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                List arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(m12069b(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                Object linkedTreeMap = new LinkedTreeMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedTreeMap.put(jsonReader.nextName(), m12069b(jsonReader));
                }
                jsonReader.endObject();
                return linkedTreeMap;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return jsonReader.nextString();
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return Double.valueOf(jsonReader.nextDouble());
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void m12068a(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter a = this.f8307b.m12337a(obj.getClass());
        if (a instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        a.m12034a(jsonWriter, obj);
    }
}
