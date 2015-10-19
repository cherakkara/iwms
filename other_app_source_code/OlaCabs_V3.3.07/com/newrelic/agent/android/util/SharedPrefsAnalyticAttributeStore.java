package com.newrelic.agent.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticAttributeStore;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SharedPrefsAnalyticAttributeStore implements AnalyticAttributeStore {
    private static final String STORE_FILE = "NRAnalyticAttributeStore";
    private static final AgentLog log;
    private final Context context;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public SharedPrefsAnalyticAttributeStore(Context context) {
        this.context = context;
    }

    public boolean store(AnalyticAttribute analyticAttribute) {
        boolean z = false;
        synchronized (this) {
            if (analyticAttribute.isPersistent()) {
                Editor edit = this.context.getSharedPreferences(STORE_FILE, 0).edit();
                if (analyticAttribute.isStringAttribute()) {
                    log.debug("SharedPrefsAnalyticAttributeStore.store - storing analytic attribute " + analyticAttribute.getName() + "=" + analyticAttribute.getStringValue());
                    edit.putString(analyticAttribute.getName(), analyticAttribute.getStringValue());
                } else {
                    log.debug("SharedPrefsAnalyticAttributeStore.store - storing analytic attribute " + analyticAttribute.getName() + "=" + analyticAttribute.getFloatValue());
                    edit.putFloat(analyticAttribute.getName(), analyticAttribute.getFloatValue());
                }
                z = edit.commit();
            }
        }
        return z;
    }

    public List<AnalyticAttribute> fetchAll() {
        log.debug("SharedPrefsAnalyticAttributeStore.fetchAll invoked.");
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(STORE_FILE, 0);
        List arrayList = new ArrayList();
        synchronized (this) {
            Map all = sharedPreferences.getAll();
        }
        for (Entry entry : all.entrySet()) {
            log.debug("SharedPrefsAnalyticAttributeStore.fetchAll - found analytic attribute " + entry.getKey() + "=" + entry.getValue());
            if (entry.getValue() instanceof String) {
                arrayList.add(new AnalyticAttribute(entry.getKey().toString(), entry.getValue().toString(), true));
            } else {
                arrayList.add(new AnalyticAttribute(entry.getKey().toString(), Float.valueOf(entry.getValue().toString()).floatValue(), true));
            }
        }
        return arrayList;
    }

    public int count() {
        int size = this.context.getSharedPreferences(STORE_FILE, 0).getAll().size();
        log.debug("SharedPrefsAnalyticAttributeStore.count - returning " + size);
        return size;
    }

    public void clear() {
        log.debug("SharedPrefsAnalyticAttributeStore.clear - flushing stored attributes");
        synchronized (this) {
            this.context.getSharedPreferences(STORE_FILE, 0).edit().clear().commit();
        }
    }

    public void delete(AnalyticAttribute analyticAttribute) {
        synchronized (this) {
            log.debug("SharedPrefsAnalyticAttributeStore.delete - deleting attribute " + analyticAttribute.getName());
            this.context.getSharedPreferences(STORE_FILE, 0).edit().remove(analyticAttribute.getName()).commit();
        }
    }
}
