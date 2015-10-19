package com.google.android.gms.internal;

import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.location.C0477l;
import com.google.android.gms.location.C0477l.C0478a;
import com.google.android.gms.location.C0480m;
import com.google.android.gms.location.C0480m.C0481a;
import com.google.android.gms.location.C0530f;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzh;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashMap;
import java.util.Map;

public class aj {
    private final ao<ah> f2366a;
    private final Context f2367b;
    private ContentProviderClient f2368c;
    private boolean f2369d;
    private Map<C0530f, C0479b> f2370e;
    private Map<Object, C0482c> f2371f;

    /* renamed from: com.google.android.gms.internal.aj.a */
    private static class C0476a extends Handler {
        private final C0530f f2363a;

        public C0476a(C0530f c0530f) {
            this.f2363a = c0530f;
        }

        public C0476a(C0530f c0530f, Looper looper) {
            super(looper);
            this.f2363a = c0530f;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f2363a.m4359a(new Location((Location) message.obj));
                default:
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.aj.b */
    private static class C0479b extends C0478a {
        private Handler f2364a;

        C0479b(C0530f c0530f, Looper looper) {
            this.f2364a = looper == null ? new C0476a(c0530f) : new C0476a(c0530f, looper);
        }

        public void m3987a() {
            this.f2364a = null;
        }

        public void m3988a(Location location) {
            if (this.f2364a == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = location;
            this.f2364a.sendMessage(obtain);
        }
    }

    /* renamed from: com.google.android.gms.internal.aj.c */
    private static class C0482c extends C0481a {
        private Handler f2365a;

        public void m3991a(zzh com_google_android_gms_location_zzh) {
            if (this.f2365a == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.obj = com_google_android_gms_location_zzh;
            this.f2365a.sendMessage(obtain);
        }
    }

    public aj(Context context, ao<ah> aoVar) {
        this.f2368c = null;
        this.f2369d = false;
        this.f2370e = new HashMap();
        this.f2371f = new HashMap();
        this.f2367b = context;
        this.f2366a = aoVar;
    }

    private C0479b m3992a(C0530f c0530f, Looper looper) {
        C0479b c0479b;
        if (looper == null) {
            C0453u.m3847a(Looper.myLooper(), (Object) "Can't create handler inside thread that has not called Looper.prepare()");
        }
        synchronized (this.f2370e) {
            c0479b = (C0479b) this.f2370e.get(c0530f);
            if (c0479b == null) {
                c0479b = new C0479b(c0530f, looper);
            }
            this.f2370e.put(c0530f, c0479b);
        }
        return c0479b;
    }

    public Location m3993a() {
        this.f2366a.m3898a();
        try {
            return ((ah) this.f2366a.m3899c()).m3932a(this.f2367b.getPackageName());
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public void m3994a(LocationRequest locationRequest, C0530f c0530f, Looper looper) throws RemoteException {
        this.f2366a.m3898a();
        ((ah) this.f2366a.m3899c()).m3942a(zzpi.m4255a(zzpg.m4252a(locationRequest), m3992a(c0530f, looper)));
    }

    public void m3995a(C0530f c0530f) throws RemoteException {
        this.f2366a.m3898a();
        C0453u.m3847a((Object) c0530f, (Object) "Invalid null listener");
        synchronized (this.f2370e) {
            C0477l c0477l = (C0479b) this.f2370e.remove(c0530f);
            if (this.f2368c != null && this.f2370e.isEmpty()) {
                this.f2368c.release();
                this.f2368c = null;
            }
            if (c0477l != null) {
                c0477l.m3987a();
                ((ah) this.f2366a.m3899c()).m3942a(zzpi.m4256a(c0477l));
            }
        }
    }

    public void m3996a(boolean z) throws RemoteException {
        this.f2366a.m3898a();
        ((ah) this.f2366a.m3899c()).m3950a(z);
        this.f2369d = z;
    }

    public void m3997b() {
        try {
            synchronized (this.f2370e) {
                for (C0477l c0477l : this.f2370e.values()) {
                    if (c0477l != null) {
                        ((ah) this.f2366a.m3899c()).m3942a(zzpi.m4256a(c0477l));
                    }
                }
                this.f2370e.clear();
                for (C0480m c0480m : this.f2371f.values()) {
                    if (c0480m != null) {
                        ((ah) this.f2366a.m3899c()).m3942a(zzpi.m4257a(c0480m));
                    }
                }
                this.f2371f.clear();
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public void m3998c() {
        if (this.f2369d) {
            try {
                m3996a(false);
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
