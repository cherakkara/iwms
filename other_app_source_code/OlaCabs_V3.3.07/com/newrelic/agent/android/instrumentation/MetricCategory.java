package com.newrelic.agent.android.instrumentation;

import java.util.HashMap;
import java.util.Map;

public enum MetricCategory {
    NONE("None"),
    VIEW_LOADING("View Loading"),
    VIEW_LAYOUT("Layout"),
    DATABASE("Database"),
    IMAGE("Images"),
    JSON("JSON"),
    NETWORK("Network");
    
    private static final Map<String, MetricCategory> methodMap;
    private String categoryName;

    /* renamed from: com.newrelic.agent.android.instrumentation.MetricCategory.1 */
    static class C07421 extends HashMap<String, MetricCategory> {
        C07421() {
            put("onCreate", MetricCategory.VIEW_LOADING);
        }
    }

    static {
        methodMap = new C07421();
    }

    private MetricCategory(String str) {
        this.categoryName = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public static MetricCategory categoryForMethod(String str) {
        if (str == null) {
            return NONE;
        }
        Object obj = null;
        int indexOf = str.indexOf("#");
        if (indexOf >= 0) {
            obj = str.substring(indexOf + 1);
        }
        MetricCategory metricCategory = (MetricCategory) methodMap.get(obj);
        if (metricCategory == null) {
            return NONE;
        }
        return metricCategory;
    }
}
