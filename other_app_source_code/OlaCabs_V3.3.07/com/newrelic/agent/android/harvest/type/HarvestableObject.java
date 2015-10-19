package com.newrelic.agent.android.harvest.type;

import com.newrelic.agent.android.harvest.type.Harvestable.Type;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonObject;
import java.util.Map;

public abstract class HarvestableObject extends BaseHarvestable {

    /* renamed from: com.newrelic.agent.android.harvest.type.HarvestableObject.1 */
    static class C07371 extends HarvestableObject {
        final /* synthetic */ Map val$map;

        C07371(Map map) {
            this.val$map = map;
        }

        public JsonObject asJsonObject() {
            return (JsonObject) new Gson().toJsonTree(this.val$map, GSON_STRING_MAP_TYPE);
        }
    }

    public abstract JsonObject asJsonObject();

    public static HarvestableObject fromMap(Map<String, String> map) {
        return new C07371(map);
    }

    public HarvestableObject() {
        super(Type.OBJECT);
    }
}
