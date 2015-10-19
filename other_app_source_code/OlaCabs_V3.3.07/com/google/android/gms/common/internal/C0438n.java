package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.C0509o;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.google.android.gms.common.internal.n */
final class C0438n extends C0434m implements Callback {
    private final HashMap<C0435a, C0437b> f2298a;
    private final Context f2299b;
    private final Handler f2300c;
    private final C0509o f2301d;
    private final long f2302e;

    /* renamed from: com.google.android.gms.common.internal.n.a */
    private static final class C0435a {
        private final String f2287a;
        private final ComponentName f2288b;

        public C0435a(String str) {
            this.f2287a = C0453u.m3848a(str);
            this.f2288b = null;
        }

        public Intent m3655a() {
            return this.f2287a != null ? new Intent(this.f2287a).setPackage("com.google.android.gms") : new Intent().setComponent(this.f2288b);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0435a)) {
                return false;
            }
            C0435a c0435a = (C0435a) obj;
            return C0452t.m3845a(this.f2287a, c0435a.f2287a) && C0452t.m3845a(this.f2288b, c0435a.f2288b);
        }

        public int hashCode() {
            return C0452t.m3843a(this.f2287a, this.f2288b);
        }

        public String toString() {
            return this.f2287a == null ? this.f2288b.flattenToString() : this.f2287a;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.n.b */
    private final class C0437b {
        final /* synthetic */ C0438n f2290a;
        private final C0436a f2291b;
        private final Set<ServiceConnection> f2292c;
        private int f2293d;
        private boolean f2294e;
        private IBinder f2295f;
        private final C0435a f2296g;
        private ComponentName f2297h;

        /* renamed from: com.google.android.gms.common.internal.n.b.a */
        public class C0436a implements ServiceConnection {
            final /* synthetic */ C0437b f2289a;

            public C0436a(C0437b c0437b) {
                this.f2289a = c0437b;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (this.f2289a.f2290a.f2298a) {
                    this.f2289a.f2295f = iBinder;
                    this.f2289a.f2297h = componentName;
                    for (ServiceConnection onServiceConnected : this.f2289a.f2292c) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    this.f2289a.f2293d = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (this.f2289a.f2290a.f2298a) {
                    this.f2289a.f2295f = null;
                    this.f2289a.f2297h = componentName;
                    for (ServiceConnection onServiceDisconnected : this.f2289a.f2292c) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    this.f2289a.f2293d = 2;
                }
            }
        }

        public C0437b(C0438n c0438n, C0435a c0435a) {
            this.f2290a = c0438n;
            this.f2296g = c0435a;
            this.f2291b = new C0436a(this);
            this.f2292c = new HashSet();
            this.f2293d = 2;
        }

        public void m3661a(ServiceConnection serviceConnection, String str) {
            this.f2290a.f2301d.m4150a(this.f2290a.f2299b, serviceConnection, str, this.f2296g.m3655a());
            this.f2292c.add(serviceConnection);
        }

        public void m3662a(String str) {
            this.f2294e = this.f2290a.f2301d.m4151a(this.f2290a.f2299b, str, this.f2296g.m3655a(), this.f2291b, 129);
            if (this.f2294e) {
                this.f2293d = 3;
            } else {
                this.f2290a.f2301d.m4149a(this.f2290a.f2299b, this.f2291b);
            }
        }

        public boolean m3663a() {
            return this.f2294e;
        }

        public boolean m3664a(ServiceConnection serviceConnection) {
            return this.f2292c.contains(serviceConnection);
        }

        public int m3665b() {
            return this.f2293d;
        }

        public void m3666b(ServiceConnection serviceConnection, String str) {
            this.f2290a.f2301d.m4152b(this.f2290a.f2299b, serviceConnection);
            this.f2292c.remove(serviceConnection);
        }

        public void m3667b(String str) {
            this.f2290a.f2301d.m4149a(this.f2290a.f2299b, this.f2291b);
            this.f2294e = false;
            this.f2293d = 2;
        }

        public boolean m3668c() {
            return this.f2292c.isEmpty();
        }

        public IBinder m3669d() {
            return this.f2295f;
        }

        public ComponentName m3670e() {
            return this.f2297h;
        }
    }

    C0438n(Context context) {
        this.f2298a = new HashMap();
        this.f2299b = context.getApplicationContext();
        this.f2300c = new Handler(context.getMainLooper(), this);
        this.f2301d = C0509o.m4145a();
        this.f2302e = 5000;
    }

    private boolean m3672a(C0435a c0435a, ServiceConnection serviceConnection, String str) {
        boolean a;
        C0453u.m3847a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f2298a) {
            C0437b c0437b = (C0437b) this.f2298a.get(c0435a);
            if (c0437b != null) {
                this.f2300c.removeMessages(0, c0437b);
                if (!c0437b.m3664a(serviceConnection)) {
                    c0437b.m3661a(serviceConnection, str);
                    switch (c0437b.m3665b()) {
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            serviceConnection.onServiceConnected(c0437b.m3670e(), c0437b.m3669d());
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            c0437b.m3662a(str);
                            break;
                        default:
                            break;
                    }
                }
                throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + c0435a);
            }
            c0437b = new C0437b(this, c0435a);
            c0437b.m3661a(serviceConnection, str);
            c0437b.m3662a(str);
            this.f2298a.put(c0435a, c0437b);
            a = c0437b.m3663a();
        }
        return a;
    }

    private void m3674b(C0435a c0435a, ServiceConnection serviceConnection, String str) {
        C0453u.m3847a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f2298a) {
            C0437b c0437b = (C0437b) this.f2298a.get(c0435a);
            if (c0437b == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + c0435a);
            } else if (c0437b.m3664a(serviceConnection)) {
                c0437b.m3666b(serviceConnection, str);
                if (c0437b.m3668c()) {
                    this.f2300c.sendMessageDelayed(this.f2300c.obtainMessage(0, c0437b), this.f2302e);
                }
            } else {
                Log.e("GmsClientSupervisor", "Trying to unbind a GmsServiceConnection that was not bound before. config=" + c0435a);
            }
        }
    }

    public boolean m3676a(String str, ServiceConnection serviceConnection, String str2) {
        return m3672a(new C0435a(str), serviceConnection, str2);
    }

    public void m3677b(String str, ServiceConnection serviceConnection, String str2) {
        m3674b(new C0435a(str), serviceConnection, str2);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                C0437b c0437b = (C0437b) message.obj;
                synchronized (this.f2298a) {
                    if (c0437b.m3668c()) {
                        if (c0437b.m3663a()) {
                            c0437b.m3667b("GmsClientSupervisor");
                        }
                        this.f2298a.remove(c0437b.f2296g);
                    }
                    break;
                }
                return true;
            default:
                return false;
        }
    }
}
