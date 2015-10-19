package com.facebook.p022b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/* renamed from: com.facebook.b.p */
public abstract class PlatformServiceClient implements ServiceConnection {
    private final Context f826a;
    private final Handler f827b;
    private PlatformServiceClient f828c;
    private boolean f829d;
    private Messenger f830e;
    private int f831f;
    private int f832g;
    private final String f833h;
    private final int f834i;

    /* renamed from: com.facebook.b.p.1 */
    class PlatformServiceClient extends Handler {
        final /* synthetic */ PlatformServiceClient f825a;

        PlatformServiceClient(PlatformServiceClient platformServiceClient) {
            this.f825a = platformServiceClient;
        }

        public void handleMessage(Message message) {
            this.f825a.m1063a(message);
        }
    }

    /* renamed from: com.facebook.b.p.a */
    public interface PlatformServiceClient {
        void m1059a(Bundle bundle);
    }

    protected abstract void m1062a(Bundle bundle);

    public PlatformServiceClient(Context context, int i, int i2, int i3, String str) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.f826a = context;
        this.f831f = i;
        this.f832g = i2;
        this.f833h = str;
        this.f834i = i3;
        this.f827b = new PlatformServiceClient(this);
    }

    public void m1064a(PlatformServiceClient platformServiceClient) {
        this.f828c = platformServiceClient;
    }

    public boolean m1065a() {
        if (this.f829d || NativeProtocol.m1051b(this.f834i) == -1) {
            return false;
        }
        Intent b = NativeProtocol.m1052b(this.f826a);
        if (b == null) {
            return false;
        }
        this.f829d = true;
        this.f826a.bindService(b, this, 1);
        return true;
    }

    public void m1066b() {
        this.f829d = false;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f830e = new Messenger(iBinder);
        m1061c();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f830e = null;
        try {
            this.f826a.unbindService(this);
        } catch (IllegalArgumentException e) {
        }
        m1060b(null);
    }

    private void m1061c() {
        Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.extra.APPLICATION_ID", this.f833h);
        m1062a(bundle);
        Message obtain = Message.obtain(null, this.f831f);
        obtain.arg1 = this.f834i;
        obtain.setData(bundle);
        obtain.replyTo = new Messenger(this.f827b);
        try {
            this.f830e.send(obtain);
        } catch (RemoteException e) {
            m1060b(null);
        }
    }

    protected void m1063a(Message message) {
        if (message.what == this.f832g) {
            Bundle data = message.getData();
            if (data.getString("com.facebook.platform.status.ERROR_TYPE") != null) {
                m1060b(null);
            } else {
                m1060b(data);
            }
            this.f826a.unbindService(this);
        }
    }

    private void m1060b(Bundle bundle) {
        if (this.f829d) {
            this.f829d = false;
            PlatformServiceClient platformServiceClient = this.f828c;
            if (platformServiceClient != null) {
                platformServiceClient.m1059a(bundle);
            }
        }
    }
}
