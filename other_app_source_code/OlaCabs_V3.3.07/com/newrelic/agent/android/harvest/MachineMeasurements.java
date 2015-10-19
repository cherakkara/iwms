package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.olacabs.customer.utils.Constants;
import java.util.HashMap;

public class MachineMeasurements extends HarvestableArray {
    private final MetricStore metrics;

    public MachineMeasurements() {
        this.metrics = new MetricStore();
    }

    public void addMetric(String str, double d) {
        Metric metric = new Metric(str);
        metric.sample(d);
        addMetric(metric);
    }

    public void addMetric(Metric metric) {
        this.metrics.add(metric);
    }

    public void clear() {
        this.metrics.clear();
    }

    public boolean isEmpty() {
        return this.metrics.isEmpty();
    }

    public MetricStore getMetrics() {
        return this.metrics;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        for (Metric metric : this.metrics.getAll()) {
            JsonElement jsonArray2 = new JsonArray();
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.BUNDLE_NAME, metric.getName());
            hashMap.put("scope", metric.getStringScope());
            jsonArray2.add(new Gson().toJsonTree(hashMap, GSON_STRING_MAP_TYPE));
            jsonArray2.add(metric.asJsonObject());
            jsonArray.add(jsonArray2);
        }
        return jsonArray;
    }
}
