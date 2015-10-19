package com.google.android.m4b.maps.p041b;

import com.google.android.m4b.maps.cm.BaseConfig;

/* renamed from: com.google.android.m4b.maps.b.a */
public abstract class BaseConnectionFactory implements ConnectionFactory {
    private boolean f5009a;
    private boolean f5010b;
    private boolean f5011c;
    private PersistentStore f5012d;
    private final String f5013e;

    protected BaseConnectionFactory(String str) {
        this.f5009a = false;
        this.f5010b = false;
        this.f5011c = false;
        this.f5013e = str;
        this.f5012d = BaseConfig.m10147p().m10149q();
    }

    public final synchronized boolean m7750a(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            this.f5009a = true;
            if (m7749a()) {
                z2 = false;
            } else {
                this.f5010b = true;
                this.f5012d.m7754a(this.f5013e, new byte[]{(byte) 0});
            }
        }
        return z2;
    }

    public final boolean m7749a() {
        if (!this.f5011c) {
            boolean z = this.f5012d.m7755a(this.f5013e) != null;
            this.f5011c = true;
            this.f5010b = z;
        }
        return this.f5010b;
    }

    public final boolean m7751b() {
        return this.f5009a;
    }
}
