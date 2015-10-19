package com.facebook;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;

/* renamed from: com.facebook.t */
final class ProfileManager {
    private static volatile ProfileManager f1284a;
    private final LocalBroadcastManager f1285b;
    private final ProfileCache f1286c;
    private Profile f1287d;

    ProfileManager(LocalBroadcastManager localBroadcastManager, ProfileCache profileCache) {
        Validate.m1146a((Object) localBroadcastManager, "localBroadcastManager");
        Validate.m1146a((Object) profileCache, "profileCache");
        this.f1285b = localBroadcastManager;
        this.f1286c = profileCache;
    }

    static ProfileManager m1708a() {
        if (f1284a == null) {
            synchronized (ProfileManager.class) {
                if (f1284a == null) {
                    f1284a = new ProfileManager(LocalBroadcastManager.getInstance(FacebookSdk.m1208f()), new ProfileCache());
                }
            }
        }
        return f1284a;
    }

    Profile m1712b() {
        return this.f1287d;
    }

    boolean m1713c() {
        Profile a = this.f1286c.m1362a();
        if (a == null) {
            return false;
        }
        m1710a(a, false);
        return true;
    }

    void m1711a(Profile profile) {
        m1710a(profile, true);
    }

    private void m1710a(Profile profile, boolean z) {
        Profile profile2 = this.f1287d;
        this.f1287d = profile;
        if (z) {
            if (profile != null) {
                this.f1286c.m1363a(profile);
            } else {
                this.f1286c.m1364b();
            }
        }
        if (!Utility.m1125a((Object) profile2, (Object) profile)) {
            m1709a(profile2, profile);
        }
    }

    private void m1709a(Profile profile, Profile profile2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_PROFILE", profile);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_PROFILE", profile2);
        this.f1285b.sendBroadcast(intent);
    }
}
