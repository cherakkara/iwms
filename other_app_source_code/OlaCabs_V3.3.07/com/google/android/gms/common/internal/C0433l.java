package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.google.android.gms.common.internal.l */
public final class C0433l implements Callback {
    final ArrayList<C0221b> f2279a;
    private final C0245a f2280b;
    private final ArrayList<C0221b> f2281c;
    private boolean f2282d;
    private final ArrayList<C0222c> f2283e;
    private final Handler f2284f;

    /* renamed from: com.google.android.gms.common.internal.l.a */
    public interface C0245a {
        Bundle a_();

        boolean b_();

        boolean m3263c();
    }

    public C0433l(Looper looper, C0245a c0245a) {
        this.f2281c = new ArrayList();
        this.f2279a = new ArrayList();
        this.f2282d = false;
        this.f2283e = new ArrayList();
        this.f2280b = c0245a;
        this.f2284f = new Handler(looper, this);
    }

    protected void m3644a() {
        synchronized (this.f2281c) {
            m3646a(this.f2280b.a_());
        }
    }

    public void m3645a(int i) {
        this.f2284f.removeMessages(1);
        synchronized (this.f2281c) {
            this.f2282d = true;
            Iterator it = new ArrayList(this.f2281c).iterator();
            while (it.hasNext()) {
                C0221b c0221b = (C0221b) it.next();
                if (!this.f2280b.b_()) {
                    break;
                } else if (this.f2281c.contains(c0221b)) {
                    c0221b.m3212a(i);
                }
            }
            this.f2279a.clear();
            this.f2282d = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m3646a(android.os.Bundle r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r3 = r5.f2281c;
        monitor-enter(r3);
        r2 = r5.f2282d;	 Catch:{ all -> 0x0062 }
        if (r2 != 0) goto L_0x0052;
    L_0x0009:
        r2 = r0;
    L_0x000a:
        com.google.android.gms.common.internal.C0453u.m3850a(r2);	 Catch:{ all -> 0x0062 }
        r2 = r5.f2284f;	 Catch:{ all -> 0x0062 }
        r4 = 1;
        r2.removeMessages(r4);	 Catch:{ all -> 0x0062 }
        r2 = 1;
        r5.f2282d = r2;	 Catch:{ all -> 0x0062 }
        r2 = r5.f2279a;	 Catch:{ all -> 0x0062 }
        r2 = r2.size();	 Catch:{ all -> 0x0062 }
        if (r2 != 0) goto L_0x0054;
    L_0x001e:
        com.google.android.gms.common.internal.C0453u.m3850a(r0);	 Catch:{ all -> 0x0062 }
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0062 }
        r1 = r5.f2281c;	 Catch:{ all -> 0x0062 }
        r0.<init>(r1);	 Catch:{ all -> 0x0062 }
        r1 = r0.iterator();	 Catch:{ all -> 0x0062 }
    L_0x002c:
        r0 = r1.hasNext();	 Catch:{ all -> 0x0062 }
        if (r0 == 0) goto L_0x0048;
    L_0x0032:
        r0 = r1.next();	 Catch:{ all -> 0x0062 }
        r0 = (com.google.android.gms.common.api.C0226d.C0221b) r0;	 Catch:{ all -> 0x0062 }
        r2 = r5.f2280b;	 Catch:{ all -> 0x0062 }
        r2 = r2.b_();	 Catch:{ all -> 0x0062 }
        if (r2 == 0) goto L_0x0048;
    L_0x0040:
        r2 = r5.f2280b;	 Catch:{ all -> 0x0062 }
        r2 = r2.m3263c();	 Catch:{ all -> 0x0062 }
        if (r2 != 0) goto L_0x0056;
    L_0x0048:
        r0 = r5.f2279a;	 Catch:{ all -> 0x0062 }
        r0.clear();	 Catch:{ all -> 0x0062 }
        r0 = 0;
        r5.f2282d = r0;	 Catch:{ all -> 0x0062 }
        monitor-exit(r3);	 Catch:{ all -> 0x0062 }
        return;
    L_0x0052:
        r2 = r1;
        goto L_0x000a;
    L_0x0054:
        r0 = r1;
        goto L_0x001e;
    L_0x0056:
        r2 = r5.f2279a;	 Catch:{ all -> 0x0062 }
        r2 = r2.contains(r0);	 Catch:{ all -> 0x0062 }
        if (r2 != 0) goto L_0x002c;
    L_0x005e:
        r0.m3213a(r6);	 Catch:{ all -> 0x0062 }
        goto L_0x002c;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0062 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.l.a(android.os.Bundle):void");
    }

    public void m3647a(ConnectionResult connectionResult) {
        this.f2284f.removeMessages(1);
        synchronized (this.f2283e) {
            Iterator it = new ArrayList(this.f2283e).iterator();
            while (it.hasNext()) {
                C0222c c0222c = (C0222c) it.next();
                if (!this.f2280b.b_()) {
                    return;
                } else if (this.f2283e.contains(c0222c)) {
                    c0222c.onConnectionFailed(connectionResult);
                }
            }
        }
    }

    public void m3648a(C0221b c0221b) {
        C0453u.m3846a((Object) c0221b);
        synchronized (this.f2281c) {
            if (this.f2281c.contains(c0221b)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + c0221b + " is already registered");
            } else {
                this.f2281c.add(c0221b);
            }
        }
        if (this.f2280b.m3263c()) {
            this.f2284f.sendMessage(this.f2284f.obtainMessage(1, c0221b));
        }
    }

    public void m3649a(C0222c c0222c) {
        C0453u.m3846a((Object) c0222c);
        synchronized (this.f2283e) {
            if (this.f2283e.contains(c0222c)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + c0222c + " is already registered");
            } else {
                this.f2283e.add(c0222c);
            }
        }
    }

    public void m3650b(C0221b c0221b) {
        C0453u.m3846a((Object) c0221b);
        synchronized (this.f2281c) {
            if (!this.f2281c.remove(c0221b)) {
                Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + c0221b + " not found");
            } else if (this.f2282d) {
                this.f2279a.add(c0221b);
            }
        }
    }

    public void m3651b(C0222c c0222c) {
        C0453u.m3846a((Object) c0222c);
        synchronized (this.f2283e) {
            if (!this.f2283e.remove(c0222c)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + c0222c + " not found");
            }
        }
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            C0221b c0221b = (C0221b) message.obj;
            synchronized (this.f2281c) {
                if (this.f2280b.b_() && this.f2280b.m3263c() && this.f2281c.contains(c0221b)) {
                    c0221b.m3213a(this.f2280b.a_());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        return false;
    }
}
