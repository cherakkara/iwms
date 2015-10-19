package com.facebook;

import android.content.SharedPreferences;
import com.facebook.p022b.Validate;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.s */
final class ProfileCache {
    private final SharedPreferences f1044a;

    ProfileCache() {
        this.f1044a = FacebookSdk.m1208f().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0);
    }

    Profile m1362a() {
        String string = this.f1044a.getString("com.facebook.ProfileManager.CachedProfile", null);
        if (string != null) {
            try {
                return new Profile(JSONObjectInstrumentation.init(string));
            } catch (JSONException e) {
            }
        }
        return null;
    }

    void m1363a(Profile profile) {
        Validate.m1146a((Object) profile, "profile");
        JSONObject c = profile.m796c();
        if (c != null) {
            this.f1044a.edit().putString("com.facebook.ProfileManager.CachedProfile", !(c instanceof JSONObject) ? c.toString() : JSONObjectInstrumentation.toString(c)).apply();
        }
    }

    void m1364b() {
        this.f1044a.edit().remove("com.facebook.ProfileManager.CachedProfile").apply();
    }
}
