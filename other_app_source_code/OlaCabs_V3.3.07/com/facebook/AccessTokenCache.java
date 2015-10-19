package com.facebook;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.p022b.Validate;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.a */
class AccessTokenCache {
    private final SharedPreferences f719a;
    private final AccessTokenCache f720b;
    private LegacyTokenHelper f721c;

    /* renamed from: com.facebook.a.a */
    static class AccessTokenCache {
        AccessTokenCache() {
        }

        public LegacyTokenHelper m797a() {
            return new LegacyTokenHelper(FacebookSdk.m1208f());
        }
    }

    AccessTokenCache(SharedPreferences sharedPreferences, AccessTokenCache accessTokenCache) {
        this.f719a = sharedPreferences;
        this.f720b = accessTokenCache;
    }

    public AccessTokenCache() {
        this(FacebookSdk.m1208f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new AccessTokenCache());
    }

    public AccessToken m868a() {
        if (m863c()) {
            return m864d();
        }
        if (!m865e()) {
            return null;
        }
        AccessToken f = m866f();
        if (f == null) {
            return f;
        }
        m869a(f);
        m867g().m1361b();
        return f;
    }

    public void m869a(AccessToken accessToken) {
        Validate.m1146a((Object) accessToken, "accessToken");
        try {
            JSONObject i = accessToken.m705i();
            this.f719a.edit().putString("com.facebook.AccessTokenManager.CachedAccessToken", !(i instanceof JSONObject) ? i.toString() : JSONObjectInstrumentation.toString(i)).apply();
        } catch (JSONException e) {
        }
    }

    public void m870b() {
        this.f719a.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
        if (m865e()) {
            m867g().m1361b();
        }
    }

    private boolean m863c() {
        return this.f719a.contains("com.facebook.AccessTokenManager.CachedAccessToken");
    }

    private AccessToken m864d() {
        AccessToken accessToken = null;
        String string = this.f719a.getString("com.facebook.AccessTokenManager.CachedAccessToken", accessToken);
        if (string != null) {
            try {
                accessToken = AccessToken.m693a(JSONObjectInstrumentation.init(string));
            } catch (JSONException e) {
            }
        }
        return accessToken;
    }

    private boolean m865e() {
        return FacebookSdk.m1204c();
    }

    private AccessToken m866f() {
        Bundle a = m867g().m1360a();
        if (a == null || !LegacyTokenHelper.m1356a(a)) {
            return null;
        }
        return AccessToken.m691a(a);
    }

    private LegacyTokenHelper m867g() {
        if (this.f721c == null) {
            synchronized (this) {
                if (this.f721c == null) {
                    this.f721c = this.f720b.m797a();
                }
            }
        }
        return this.f721c;
    }
}
