package com.olacabs.customer.app;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.olacabs.customer.app.h */
public class DataUpdaterManager {
    private static volatile DataUpdaterManager f9369b;
    private Context f9370a;
    private Map<String, DataUpdater> f9371c;
    private ObservableData f9372d;

    private DataUpdaterManager(Context context) {
        this.f9371c = new HashMap();
        this.f9370a = context;
        this.f9372d = new ObservableData();
        m13262b();
    }

    private void m13262b() {
        ConfigDataUpdater configDataUpdater = new ConfigDataUpdater(this.f9370a);
        configDataUpdater.m12895a(true);
        DataUpdater authDataUpdater = new AuthDataUpdater(this.f9370a);
        configDataUpdater.m12894a(authDataUpdater);
        this.f9371c.put("app_config", configDataUpdater);
        this.f9371c.put("app_config_without_auth", new ConfigDataUpdater(this.f9370a));
        this.f9371c.put("auth_config", authDataUpdater);
        this.f9371c.put("profile_data", new ProfileDataUpdater(this.f9370a));
    }

    public static DataUpdaterManager m13261a(Context context) {
        if (f9369b == null) {
            synchronized (DataUpdaterManager.class) {
                if (f9369b == null) {
                    OLog.m13311a("Creating data manager instance", new Object[0]);
                    f9369b = new DataUpdaterManager(context);
                }
            }
        }
        return f9369b;
    }

    public void m13264a(String... strArr) {
        for (String str : strArr) {
            OLog.m13311a("Key : " + str, new Object[0]);
            DataUpdater dataUpdater = (DataUpdater) this.f9371c.get(str);
            if (dataUpdater != null) {
                dataUpdater.m12883a();
            }
        }
    }

    public ObservableData m13263a() {
        return this.f9372d;
    }
}
