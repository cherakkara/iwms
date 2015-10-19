package com.google.android.m4b.maps.p038a;

import java.util.Collections;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.a.b */
public interface Cache {

    /* renamed from: com.google.android.m4b.maps.a.b.a */
    public static class Cache {
        public byte[] f2870a;
        public String f2871b;
        public long f2872c;
        public long f2873d;
        public long f2874e;
        public Map<String, String> f2875f;

        public Cache() {
            this.f2875f = Collections.emptyMap();
        }
    }

    Cache m4679a(String str);

    void m4680a();

    void m4681a(String str, Cache cache);
}
