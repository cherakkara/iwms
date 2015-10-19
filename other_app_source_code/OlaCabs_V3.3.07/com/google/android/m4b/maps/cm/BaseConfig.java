package com.google.android.m4b.maps.cm;

import android.content.Context;
import com.google.android.m4b.maps.p041b.ConnectionFactory;
import com.google.android.m4b.maps.p041b.InMemoryPersistentStore;
import com.google.android.m4b.maps.p041b.PersistentStore;
import com.google.android.m4b.maps.p045c.AndroidHttpConnectionFactory;
import com.google.android.m4b.maps.p045c.AndroidPersistentStore;

/* renamed from: com.google.android.m4b.maps.cm.a */
public class BaseConfig {
    protected static BaseConfig f7290a;
    protected static final Object f7291b;
    protected final Clock f7292c;
    private ConnectionFactory f7293d;
    private PersistentStore f7294e;

    static {
        f7291b = new Object();
    }

    protected BaseConfig() {
        this.f7294e = null;
        this.f7293d = null;
        this.f7292c = new Clock();
    }

    protected BaseConfig(Context context, PersistentStore persistentStore) {
        synchronized (f7291b) {
            BaseConfig baseConfig;
            if (persistentStore != null) {
                baseConfig = this;
            } else if (context == null) {
                persistentStore = new InMemoryPersistentStore();
                baseConfig = this;
            } else {
                persistentStore = new AndroidPersistentStore(context);
                baseConfig = this;
            }
            baseConfig.f7294e = persistentStore;
            this.f7292c = new Clock();
        }
        f7290a = this;
        this.f7293d = new AndroidHttpConnectionFactory(context);
    }

    public static BaseConfig m10147p() {
        return f7290a;
    }

    public Clock m10148h() {
        return this.f7292c;
    }

    public final PersistentStore m10149q() {
        return this.f7294e;
    }

    public final ConnectionFactory m10150r() {
        return this.f7293d;
    }
}
