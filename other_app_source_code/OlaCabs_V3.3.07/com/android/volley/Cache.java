package com.android.volley;

import java.util.Collections;
import java.util.Map;

/* renamed from: com.android.volley.b */
public interface Cache {

    /* renamed from: com.android.volley.b.a */
    public static class Cache {
        public byte[] f467a;
        public String f468b;
        public long f469c;
        public long f470d;
        public long f471e;
        public Map<String, String> f472f;

        public Cache() {
            this.f472f = Collections.emptyMap();
        }

        public boolean m557a() {
            return this.f470d < System.currentTimeMillis();
        }

        public boolean m558b() {
            return this.f471e < System.currentTimeMillis();
        }
    }

    Cache m559a(String str);

    void m560a();

    void m561a(String str, Cache cache);
}
