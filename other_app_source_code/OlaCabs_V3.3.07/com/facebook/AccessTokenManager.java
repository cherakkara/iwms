package com.facebook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.p022b.NativeProtocol;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.olacabs.customer.utils.Constants;
import java.util.Date;

/* renamed from: com.facebook.b */
final class AccessTokenManager {
    private static volatile AccessTokenManager f883a;
    private final LocalBroadcastManager f884b;
    private final AccessTokenCache f885c;
    private AccessToken f886d;
    private AccessTokenManager f887e;
    private Date f888f;

    /* renamed from: com.facebook.b.a */
    class AccessTokenManager implements ServiceConnection {
        final Messenger f722a;
        Messenger f723b;
        final /* synthetic */ AccessTokenManager f724c;

        AccessTokenManager(AccessTokenManager accessTokenManager, AccessToken accessToken) {
            this.f724c = accessTokenManager;
            this.f723b = null;
            this.f722a = new Messenger(new AccessTokenManager(accessToken, this));
        }

        public void m874a() {
            Context f = FacebookSdk.m1208f();
            Intent a = NativeProtocol.m1040a(f);
            if (a == null || !f.bindService(a, this, 1)) {
                m872b();
            } else {
                this.f724c.f888f = new Date();
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f723b = new Messenger(iBinder);
            m873c();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            m872b();
            try {
                FacebookSdk.m1208f().unbindService(this);
            } catch (IllegalArgumentException e) {
            }
        }

        private void m872b() {
            if (this.f724c.f887e == this) {
                this.f724c.f887e = null;
            }
        }

        private void m873c() {
            Bundle bundle = new Bundle();
            bundle.putString("access_token", this.f724c.m1177b().m698b());
            Message obtain = Message.obtain();
            obtain.setData(bundle);
            obtain.replyTo = this.f722a;
            try {
                this.f723b.send(obtain);
            } catch (RemoteException e) {
                m872b();
            }
        }
    }

    /* renamed from: com.facebook.b.b */
    static class AccessTokenManager extends Handler {
        private AccessToken f725a;
        private AccessTokenManager f726b;

        AccessTokenManager(AccessToken accessToken, AccessTokenManager accessTokenManager) {
            super(Looper.getMainLooper());
            this.f725a = accessToken;
            this.f726b = accessTokenManager;
        }

        public void handleMessage(Message message) {
            AccessToken a = AccessToken.m690a();
            if (!(a == null || !a.equals(this.f725a) || message.getData().getString("access_token") == null)) {
                AccessToken.m695a(AccessToken.m692a(this.f725a, message.getData()));
            }
            FacebookSdk.m1208f().unbindService(this.f726b);
            this.f726b.m872b();
        }
    }

    AccessTokenManager(LocalBroadcastManager localBroadcastManager, AccessTokenCache accessTokenCache) {
        this.f888f = new Date(0);
        Validate.m1146a((Object) localBroadcastManager, "localBroadcastManager");
        Validate.m1146a((Object) accessTokenCache, "accessTokenCache");
        this.f884b = localBroadcastManager;
        this.f885c = accessTokenCache;
    }

    static AccessTokenManager m1171a() {
        if (f883a == null) {
            synchronized (AccessTokenManager.class) {
                if (f883a == null) {
                    f883a = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.m1208f()), new AccessTokenCache());
                }
            }
        }
        return f883a;
    }

    AccessToken m1177b() {
        return this.f886d;
    }

    boolean m1178c() {
        AccessToken a = this.f885c.m868a();
        if (a == null) {
            return false;
        }
        m1174a(a, false);
        return true;
    }

    void m1176a(AccessToken accessToken) {
        m1174a(accessToken, true);
    }

    private void m1174a(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.f886d;
        this.f886d = accessToken;
        this.f887e = null;
        this.f888f = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.f885c.m869a(accessToken);
            } else {
                this.f885c.m870b();
            }
        }
        if (!Utility.m1125a((Object) accessToken2, (Object) accessToken)) {
            m1173a(accessToken2, accessToken);
        }
    }

    private void m1173a(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", accessToken2);
        this.f884b.sendBroadcast(intent);
    }

    void m1179d() {
        if (m1175e()) {
            this.f887e = new AccessTokenManager(this, this.f886d);
            this.f887e.m874a();
        }
    }

    private boolean m1175e() {
        if (this.f886d == null || this.f887e != null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (!this.f886d.m701e().m1180a() || valueOf.longValue() - this.f888f.getTime() <= Constants.MILLIS_IN_AN_HOUR || valueOf.longValue() - this.f886d.m702f().getTime() <= 86400000) {
            return false;
        }
        return true;
    }
}
