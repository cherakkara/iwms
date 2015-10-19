package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityHistory extends HarvestableArray {
    private final List<ActivitySighting> activityHistory;

    public ActivityHistory(List<ActivitySighting> list) {
        this.activityHistory = list;
    }

    public int size() {
        return this.activityHistory.size();
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        for (ActivitySighting asJsonArray : this.activityHistory) {
            jsonArray.add(asJsonArray.asJsonArray());
        }
        return jsonArray;
    }

    public JsonArray asJsonArrayWithoutDuration() {
        JsonArray jsonArray = new JsonArray();
        for (ActivitySighting asJsonArrayWithoutDuration : this.activityHistory) {
            jsonArray.add(asJsonArrayWithoutDuration.asJsonArrayWithoutDuration());
        }
        return jsonArray;
    }

    public static ActivityHistory newFromJson(JsonArray jsonArray) {
        List arrayList = new ArrayList();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            arrayList.add(ActivitySighting.newFromJson(((JsonElement) it.next()).getAsJsonArray()));
        }
        return new ActivityHistory(arrayList);
    }
}
