package com.newrelic.agent.android.harvest.type;

import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.sothree.slidinguppanel.p086a.R.R;
import java.lang.reflect.Type;
import java.util.Map;

public class BaseHarvestable implements Harvestable {
    protected static final Type GSON_STRING_MAP_TYPE;
    private final Harvestable.Type type;

    /* renamed from: com.newrelic.agent.android.harvest.type.BaseHarvestable.1 */
    static class C07351 extends TypeToken<Map> {
        C07351() {
        }
    }

    /* renamed from: com.newrelic.agent.android.harvest.type.BaseHarvestable.2 */
    static /* synthetic */ class C07362 {
        static final /* synthetic */ int[] f8902x3e296f68;

        static {
            f8902x3e296f68 = new int[Harvestable.Type.values().length];
            try {
                f8902x3e296f68[Harvestable.Type.OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8902x3e296f68[Harvestable.Type.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8902x3e296f68[Harvestable.Type.VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        GSON_STRING_MAP_TYPE = new C07351().getType();
    }

    public BaseHarvestable(Harvestable.Type type) {
        this.type = type;
    }

    public JsonElement asJson() {
        switch (C07362.f8902x3e296f68[this.type.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return asJsonObject();
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return asJsonArray();
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return asJsonPrimitive();
            default:
                return null;
        }
    }

    public Harvestable.Type getType() {
        return this.type;
    }

    public String toJsonString() {
        return asJson().toString();
    }

    public JsonArray asJsonArray() {
        return null;
    }

    public JsonObject asJsonObject() {
        return null;
    }

    public JsonPrimitive asJsonPrimitive() {
        return null;
    }

    protected void notEmpty(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Missing Harvestable field.");
        }
    }

    protected void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Null field in Harvestable object");
        }
    }

    protected String optional(String str) {
        if (str == null) {
            return Trace.NULL;
        }
        return str;
    }
}
