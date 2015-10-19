package com.newrelic.agent.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.newrelic.agent.android.crashes.CrashStore;
import com.newrelic.agent.android.harvest.crash.Crash;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonCrashStore implements CrashStore {
    private static final String STORE_FILE = "NRCrashStore";
    private static final AgentLog log;
    private final Context context;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public JsonCrashStore(Context context) {
        this.context = context;
    }

    public void store(Crash crash) {
        synchronized (this) {
            Editor edit = this.context.getSharedPreferences(STORE_FILE, 0).edit();
            edit.putString(crash.getUuid().toString(), crash.toJsonString());
            edit.commit();
        }
    }

    public List<Crash> fetchAll() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(STORE_FILE, 0);
        List<Crash> arrayList = new ArrayList();
        synchronized (this) {
            Map all = sharedPreferences.getAll();
        }
        for (Object next : all.values()) {
            if (next instanceof String) {
                try {
                    arrayList.add(Crash.crashFromJsonString((String) next));
                } catch (Throwable e) {
                    log.error("Exception encountered while deserializing crash", e);
                }
            }
        }
        return arrayList;
    }

    public int count() {
        return this.context.getSharedPreferences(STORE_FILE, 0).getAll().size();
    }

    public void clear() {
        synchronized (this) {
            Editor edit = this.context.getSharedPreferences(STORE_FILE, 0).edit();
            edit.clear();
            edit.commit();
        }
    }

    public void delete(Crash crash) {
        synchronized (this) {
            Editor edit = this.context.getSharedPreferences(STORE_FILE, 0).edit();
            edit.remove(crash.getUuid().toString());
            edit.commit();
        }
    }
}
