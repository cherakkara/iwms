package com.google.android.m4b.maps.p047g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import com.google.android.m4b.maps.p047g.GmsClient.GmsClient;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.google.android.m4b.maps.g.e */
public final class GmsClientSupervisor implements Callback {
    private static final Object f7418a;
    private static GmsClientSupervisor f7419b;
    private final Context f7420c;
    private final HashMap<String, GmsClientSupervisor> f7421d;
    private final Handler f7422e;

    /* renamed from: com.google.android.m4b.maps.g.e.a */
    final class GmsClientSupervisor {
        final /* synthetic */ GmsClientSupervisor f7410a;
        private final String f7411b;
        private final GmsClientSupervisor f7412c;
        private final HashSet<GmsClient> f7413d;
        private int f7414e;
        private boolean f7415f;
        private IBinder f7416g;
        private ComponentName f7417h;

        /* renamed from: com.google.android.m4b.maps.g.e.a.a */
        public class GmsClientSupervisor implements ServiceConnection {
            private /* synthetic */ GmsClientSupervisor f7409a;

            public GmsClientSupervisor(GmsClientSupervisor gmsClientSupervisor) {
                this.f7409a = gmsClientSupervisor;
            }

            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (this.f7409a.f7410a.f7421d) {
                    this.f7409a.f7416g = iBinder;
                    this.f7409a.f7417h = componentName;
                    Iterator it = this.f7409a.f7413d.iterator();
                    while (it.hasNext()) {
                        ((GmsClient) it.next()).onServiceConnected(componentName, iBinder);
                    }
                    this.f7409a.f7414e = 1;
                }
            }

            public final void onServiceDisconnected(ComponentName componentName) {
                synchronized (this.f7409a.f7410a.f7421d) {
                    this.f7409a.f7416g = null;
                    this.f7409a.f7417h = componentName;
                    Iterator it = this.f7409a.f7413d.iterator();
                    while (it.hasNext()) {
                        ((GmsClient) it.next()).onServiceDisconnected(componentName);
                    }
                    this.f7409a.f7414e = 2;
                }
            }
        }

        public GmsClientSupervisor(GmsClientSupervisor gmsClientSupervisor, String str) {
            this.f7410a = gmsClientSupervisor;
            this.f7411b = str;
            this.f7412c = new GmsClientSupervisor(this);
            this.f7413d = new HashSet();
            this.f7414e = 0;
        }

        public final void m10366a(GmsClient gmsClient) {
            this.f7413d.add(gmsClient);
        }

        public final void m10369b(GmsClient gmsClient) {
            this.f7413d.remove(gmsClient);
        }

        public final GmsClientSupervisor m10365a() {
            return this.f7412c;
        }

        public final String m10368b() {
            return this.f7411b;
        }

        public final void m10367a(boolean z) {
            this.f7415f = z;
        }

        public final boolean m10370c() {
            return this.f7415f;
        }

        public final int m10372d() {
            return this.f7414e;
        }

        public final boolean m10371c(GmsClient gmsClient) {
            return this.f7413d.contains(gmsClient);
        }

        public final boolean m10373e() {
            return this.f7413d.isEmpty();
        }

        public final IBinder m10374f() {
            return this.f7416g;
        }

        public final ComponentName m10375g() {
            return this.f7417h;
        }
    }

    static {
        f7418a = new Object();
    }

    public static GmsClientSupervisor m10376a(Context context) {
        synchronized (f7418a) {
            if (f7419b == null) {
                f7419b = new GmsClientSupervisor(context.getApplicationContext());
            }
        }
        return f7419b;
    }

    private GmsClientSupervisor(Context context) {
        this.f7422e = new Handler(context.getMainLooper(), this);
        this.f7421d = new HashMap();
        this.f7420c = context.getApplicationContext();
    }

    public final boolean m10378a(String str, GmsClient gmsClient) {
        boolean c;
        synchronized (this.f7421d) {
            GmsClientSupervisor gmsClientSupervisor = (GmsClientSupervisor) this.f7421d.get(str);
            if (gmsClientSupervisor != null) {
                this.f7422e.removeMessages(0, gmsClientSupervisor);
                if (!gmsClientSupervisor.m10371c(gmsClient)) {
                    gmsClientSupervisor.m10366a((GmsClient) gmsClient);
                    switch (gmsClientSupervisor.m10372d()) {
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            gmsClient.onServiceConnected(gmsClientSupervisor.m10375g(), gmsClientSupervisor.m10374f());
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            gmsClientSupervisor.m10367a(this.f7420c.bindService(new Intent(str).setPackage("com.google.android.gms"), gmsClientSupervisor.m10365a(), 129));
                            break;
                        default:
                            break;
                    }
                }
                throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + str);
            }
            gmsClientSupervisor = new GmsClientSupervisor(this, str);
            gmsClientSupervisor.m10366a((GmsClient) gmsClient);
            gmsClientSupervisor.m10367a(this.f7420c.bindService(new Intent(str).setPackage("com.google.android.gms"), gmsClientSupervisor.m10365a(), 129));
            this.f7421d.put(str, gmsClientSupervisor);
            c = gmsClientSupervisor.m10370c();
        }
        return c;
    }

    public final void m10379b(String str, GmsClient gmsClient) {
        synchronized (this.f7421d) {
            GmsClientSupervisor gmsClientSupervisor = (GmsClientSupervisor) this.f7421d.get(str);
            if (gmsClientSupervisor == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + str);
            } else if (gmsClientSupervisor.m10371c(gmsClient)) {
                gmsClientSupervisor.m10369b(gmsClient);
                if (gmsClientSupervisor.m10373e()) {
                    this.f7422e.sendMessageDelayed(this.f7422e.obtainMessage(0, gmsClientSupervisor), 5000);
                }
            } else {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + str);
            }
        }
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                GmsClientSupervisor gmsClientSupervisor = (GmsClientSupervisor) message.obj;
                synchronized (this.f7421d) {
                    if (gmsClientSupervisor.m10373e()) {
                        this.f7420c.unbindService(gmsClientSupervisor.m10365a());
                        this.f7421d.remove(gmsClientSupervisor.m10368b());
                    }
                    break;
                }
                return true;
            default:
                return false;
        }
    }
}
