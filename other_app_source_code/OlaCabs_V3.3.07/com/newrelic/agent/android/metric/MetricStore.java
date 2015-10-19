package com.newrelic.agent.android.metric;

import com.newrelic.agent.android.instrumentation.Trace;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MetricStore {
    private final Map<String, Map<String, Metric>> metricStore;

    public MetricStore() {
        this.metricStore = new ConcurrentHashMap();
    }

    public void add(Metric metric) {
        String stringScope = metric.getStringScope();
        String name = metric.getName();
        if (!this.metricStore.containsKey(stringScope)) {
            this.metricStore.put(stringScope, new HashMap());
        }
        if (((Map) this.metricStore.get(stringScope)).containsKey(name)) {
            ((Metric) ((Map) this.metricStore.get(stringScope)).get(name)).aggregate(metric);
        } else {
            ((Map) this.metricStore.get(stringScope)).put(name, metric);
        }
    }

    public Metric get(String str) {
        return get(str, Trace.NULL);
    }

    public Metric get(String str, String str2) {
        try {
            Map map = this.metricStore;
            if (str2 == null) {
                str2 = Trace.NULL;
            }
            return (Metric) ((Map) map.get(str2)).get(str);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<Metric> getAll() {
        List<Metric> arrayList = new ArrayList();
        for (Entry value : this.metricStore.entrySet()) {
            for (Entry value2 : ((Map) value.getValue()).entrySet()) {
                arrayList.add(value2.getValue());
            }
        }
        return arrayList;
    }

    public List<Metric> getAllByScope(String str) {
        List<Metric> arrayList = new ArrayList();
        try {
            for (Entry value : ((Map) this.metricStore.get(str)).entrySet()) {
                arrayList.add(value.getValue());
            }
        } catch (NullPointerException e) {
        }
        return arrayList;
    }

    public List<Metric> getAllUnscoped() {
        return getAllByScope(Trace.NULL);
    }

    public void remove(Metric metric) {
        String stringScope = metric.getStringScope();
        String name = metric.getName();
        if (this.metricStore.containsKey(stringScope) && ((Map) this.metricStore.get(stringScope)).containsKey(name)) {
            ((Map) this.metricStore.get(stringScope)).remove(name);
        }
    }

    public void removeAll(List<Metric> list) {
        synchronized (this.metricStore) {
            for (Metric remove : list) {
                remove(remove);
            }
        }
    }

    public List<Metric> removeAllWithScope(String str) {
        List<Metric> allByScope = getAllByScope(str);
        if (!allByScope.isEmpty()) {
            removeAll(allByScope);
        }
        return allByScope;
    }

    public void clear() {
        this.metricStore.clear();
    }

    public boolean isEmpty() {
        return this.metricStore.isEmpty();
    }
}
