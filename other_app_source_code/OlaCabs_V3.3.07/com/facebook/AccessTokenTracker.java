package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.p022b.Validate;

/* renamed from: com.facebook.d */
public abstract class AccessTokenTracker {
    private final BroadcastReceiver f899a;
    private final LocalBroadcastManager f900b;
    private boolean f901c;

    /* renamed from: com.facebook.d.a */
    private class AccessTokenTracker extends BroadcastReceiver {
        final /* synthetic */ AccessTokenTracker f898a;

        private AccessTokenTracker(AccessTokenTracker accessTokenTracker) {
            this.f898a = accessTokenTracker;
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED".equals(intent.getAction())) {
                this.f898a.m1183a((AccessToken) intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN"), (AccessToken) intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN"));
            }
        }
    }

    protected abstract void m1183a(AccessToken accessToken, AccessToken accessToken2);

    public AccessTokenTracker() {
        this.f901c = false;
        Validate.m1145a();
        this.f899a = new AccessTokenTracker();
        this.f900b = LocalBroadcastManager.getInstance(FacebookSdk.m1208f());
        m1182a();
    }

    public void m1182a() {
        if (!this.f901c) {
            m1181b();
            this.f901c = true;
        }
    }

    private void m1181b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        this.f900b.registerReceiver(this.f899a, intentFilter);
    }
}
