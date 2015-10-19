package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.TypeAdapterFactory;
import com.newrelic.com.google.gson.internal.LinkedTreeMap;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY;
    private final Gson gson;

    /* renamed from: com.newrelic.com.google.gson.internal.bind.ObjectTypeAdapter.1 */
    static class C07841 implements TypeAdapterFactory {
        C07841() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Object.class) {
                return new ObjectTypeAdapter(null);
            }
            return null;
        }
    }

    /* renamed from: com.newrelic.com.google.gson.internal.bind.ObjectTypeAdapter.2 */
    static /* synthetic */ class C07852 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    static {
        FACTORY = new C07841();
    }

    private ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    public Object read(JsonReader jsonReader) throws IOException {
        switch (C07852.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                List arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(read(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                Object linkedTreeMap = new LinkedTreeMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedTreeMap.put(jsonReader.nextName(), read(jsonReader));
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

    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(obj.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        adapter.write(jsonWriter, obj);
    }
}
