package com.facebook.p022b;

import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* renamed from: com.facebook.b.q */
class ProfileInformationCache {
    private static final ConcurrentHashMap<String, JSONObject> f835a;

    static {
        f835a = new ConcurrentHashMap();
    }

    public static JSONObject m1067a(String str) {
        return (JSONObject) f835a.get(str);
    }

    public static void m1068a(String str, JSONObject jSONObject) {
        f835a.put(str, jSONObject);
    }
}
