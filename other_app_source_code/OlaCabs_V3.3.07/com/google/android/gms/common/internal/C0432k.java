package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.C0270e;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0218b.C0215b;
import com.google.android.gms.common.api.C0226d.C0220a;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.C0226d.C0225e;
import com.google.android.gms.common.internal.C0425q.C0426a;
import com.google.android.gms.common.internal.C0433l.C0245a;
import com.google.android.gms.common.internal.C0442r.C0444a;
import com.google.android.gms.internal.C0521z;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.common.internal.k */
public abstract class C0432k<T extends IInterface> implements C0215b, C0245a {
    public static final String[] f2260c;
    final Handler f2261a;
    boolean f2262b;
    private final Context f2263d;
    private final C0419h f2264e;
    private final Looper f2265f;
    private final C0434m f2266g;
    private final Object f2267h;
    private C0442r f2268i;
    private boolean f2269j;
    private C0225e f2270k;
    private T f2271l;
    private final ArrayList<C0422c<?>> f2272m;
    private C0428e f2273n;
    private int f2274o;
    private final List<String> f2275p;
    private final Account f2276q;
    private final C0433l f2277r;
    private final int f2278s;

    /* renamed from: com.google.android.gms.common.internal.k.c */
    protected abstract class C0422c<TListener> {
        private TListener f2247a;
        private boolean f2248b;
        final /* synthetic */ C0432k f2249d;

        public C0422c(C0432k c0432k, TListener tListener) {
            this.f2249d = c0432k;
            this.f2247a = tListener;
            this.f2248b = false;
        }

        protected abstract void m3585a(TListener tListener);

        protected abstract void m3586b();

        public void m3587c() {
            synchronized (this) {
                Object obj = this.f2247a;
                if (this.f2248b) {
                    Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if (obj != null) {
                try {
                    m3585a(obj);
                } catch (RuntimeException e) {
                    m3586b();
                    throw e;
                }
            }
            m3586b();
            synchronized (this) {
                this.f2248b = true;
            }
            m3588d();
        }

        public void m3588d() {
            m3589e();
            synchronized (this.f2249d.f2272m) {
                this.f2249d.f2272m.remove(this);
            }
        }

        public void m3589e() {
            synchronized (this) {
                this.f2247a = null;
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.k.a */
    private abstract class C0423a extends C0422c<Boolean> {
        public final int f2250a;
        public final Bundle f2251b;
        final /* synthetic */ C0432k f2252c;

        protected C0423a(C0432k c0432k, int i, Bundle bundle) {
            this.f2252c = c0432k;
            super(c0432k, Boolean.valueOf(true));
            this.f2250a = i;
            this.f2251b = bundle;
        }

        protected void m3590a(ConnectionResult connectionResult) {
            this.f2252c.f2277r.m3647a(connectionResult);
        }

        protected void m3591a(Boolean bool) {
            if (bool == null) {
                this.f2252c.m3607a(1, null);
                return;
            }
            switch (this.f2250a) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    if (!m3593a()) {
                        if (this.f2252c.f2273n != null) {
                            this.f2252c.f2266g.m3654b(this.f2252c.m3633e(), this.f2252c.f2273n, this.f2252c.m3635g());
                            this.f2252c.f2273n = null;
                        }
                        this.f2252c.m3607a(1, null);
                        m3590a(new ConnectionResult(8, null));
                    }
                case HTTP.LF /*10*/:
                    this.f2252c.m3607a(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    PendingIntent pendingIntent = this.f2251b != null ? (PendingIntent) this.f2251b.getParcelable("pendingIntent") : null;
                    if (this.f2252c.f2273n != null) {
                        this.f2252c.f2266g.m3654b(this.f2252c.m3633e(), this.f2252c.f2273n, this.f2252c.m3635g());
                        this.f2252c.f2273n = null;
                    }
                    this.f2252c.m3607a(1, null);
                    m3590a(new ConnectionResult(this.f2250a, pendingIntent));
            }
        }

        protected /* synthetic */ void m3592a(Object obj) {
            m3591a((Boolean) obj);
        }

        protected abstract boolean m3593a();

        protected void m3594b() {
        }
    }

    /* renamed from: com.google.android.gms.common.internal.k.b */
    final class C0424b extends Handler {
        final /* synthetic */ C0432k f2253a;

        public C0424b(C0432k c0432k, Looper looper) {
            this.f2253a = c0432k;
            super(looper);
        }

        public void handleMessage(Message message) {
            C0422c c0422c;
            if ((message.what == 1 || message.what == 5 || message.what == 6) && !this.f2253a.m3636h()) {
                c0422c = (C0422c) message.obj;
                c0422c.m3586b();
                c0422c.m3588d();
            } else if (message.what == 3) {
                this.f2253a.f2277r.m3647a(new ConnectionResult(((Integer) message.obj).intValue(), null));
            } else if (message.what == 4) {
                this.f2253a.m3607a(4, null);
                this.f2253a.f2277r.m3645a(((Integer) message.obj).intValue());
                this.f2253a.m3609a(4, 1, null);
            } else if (message.what == 2 && !this.f2253a.m3631c()) {
                c0422c = (C0422c) message.obj;
                c0422c.m3586b();
                c0422c.m3588d();
            } else if (message.what == 2 || message.what == 1 || message.what == 5 || message.what == 6) {
                ((C0422c) message.obj).m3587c();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle this message.");
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.k.d */
    public static final class C0427d extends C0426a {
        private C0432k f2254a;

        public C0427d(C0432k c0432k) {
            this.f2254a = c0432k;
        }

        private void m3598a() {
            this.f2254a = null;
        }

        public void m3599a(int i, Bundle bundle) {
            C0453u.m3847a(this.f2254a, (Object) "onAccountValidationComplete can be called only once per call to validateAccount");
            this.f2254a.m3622a(i, bundle);
            m3598a();
        }

        public void m3600a(int i, IBinder iBinder, Bundle bundle) {
            C0453u.m3847a(this.f2254a, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.f2254a.m3623a(i, iBinder, bundle);
            m3598a();
        }
    }

    /* renamed from: com.google.android.gms.common.internal.k.e */
    public final class C0428e implements ServiceConnection {
        final /* synthetic */ C0432k f2255a;

        public C0428e(C0432k c0432k) {
            this.f2255a = c0432k;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            C0453u.m3847a((Object) iBinder, (Object) "Expecting a valid IBinder");
            this.f2255a.f2268i = C0444a.m3775a(iBinder);
            this.f2255a.f2261a.sendMessage(this.f2255a.f2261a.obtainMessage(6, new C0430g(this.f2255a)));
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f2255a.f2261a.sendMessage(this.f2255a.f2261a.obtainMessage(4, Integer.valueOf(1)));
        }
    }

    /* renamed from: com.google.android.gms.common.internal.k.f */
    protected final class C0429f extends C0423a {
        public final IBinder f2256e;
        final /* synthetic */ C0432k f2257f;

        public C0429f(C0432k c0432k, int i, IBinder iBinder, Bundle bundle) {
            this.f2257f = c0432k;
            super(c0432k, i, bundle);
            this.f2256e = iBinder;
        }

        protected boolean m3601a() {
            try {
                if (!this.f2257f.m3634f().equals(this.f2256e.getInterfaceDescriptor())) {
                    return false;
                }
                IInterface a = this.f2257f.m3618a(this.f2256e);
                if (a == null || !this.f2257f.m3609a(2, 3, a)) {
                    return false;
                }
                this.f2257f.f2277r.m3644a();
                C0270e.m3393b(this.f2257f.f2263d);
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.k.g */
    protected final class C0430g extends C0423a {
        final /* synthetic */ C0432k f2258e;

        public C0430g(C0432k c0432k) {
            this.f2258e = c0432k;
            super(c0432k, 0, null);
        }

        protected boolean m3602a() {
            if (this.f2258e.f2269j) {
                C0453u.m3851a(this.f2258e.f2270k != null, (Object) "mConnectionProgressReportCallbacks should not be null if mReportProgress");
                this.f2258e.f2270k.m3218a();
            } else {
                this.f2258e.m3630b(null);
            }
            return true;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.k.h */
    protected final class C0431h extends C0423a {
        final /* synthetic */ C0432k f2259e;

        public C0431h(C0432k c0432k, int i, Bundle bundle) {
            this.f2259e = c0432k;
            super(c0432k, i, bundle);
        }

        protected boolean m3603a() {
            boolean z = this.f2259e.f2269j && this.f2259e.f2270k != null;
            C0453u.m3851a(z, (Object) "PostValidationCallback should not happen when mReportProgress is false ormConnectionProgressReportCallbacks is null");
            this.f2259e.f2270k.m3219b();
            return true;
        }
    }

    static {
        f2260c = new String[]{"service_esmobile", "service_googleme"};
    }

    @Deprecated
    protected C0432k(Context context, Looper looper, int i, C0221b c0221b, C0222c c0222c) {
        this.f2267h = new Object();
        this.f2269j = false;
        this.f2272m = new ArrayList();
        this.f2274o = 1;
        this.f2262b = false;
        this.f2263d = (Context) C0453u.m3846a((Object) context);
        this.f2265f = (Looper) C0453u.m3847a((Object) looper, (Object) "Looper must not be null");
        this.f2266g = C0434m.m3652a(context);
        this.f2277r = new C0433l(looper, this);
        this.f2261a = new C0424b(this, looper);
        this.f2278s = i;
        this.f2276q = null;
        this.f2275p = null;
        this.f2264e = new C0220a(context).m3210a();
        m3624a((C0221b) C0453u.m3846a((Object) c0221b));
        m3625a((C0222c) C0453u.m3846a((Object) c0222c));
    }

    protected C0432k(Context context, Looper looper, int i, C0221b c0221b, C0222c c0222c, C0419h c0419h) {
        this(context, looper, C0434m.m3652a(context), i, c0419h, c0221b, c0222c);
    }

    protected C0432k(Context context, Looper looper, C0434m c0434m, int i, C0419h c0419h) {
        this.f2267h = new Object();
        this.f2269j = false;
        this.f2272m = new ArrayList();
        this.f2274o = 1;
        this.f2262b = false;
        this.f2263d = (Context) C0453u.m3847a((Object) context, (Object) "Context must not be null");
        this.f2265f = (Looper) C0453u.m3847a((Object) looper, (Object) "Looper must not be null");
        this.f2266g = (C0434m) C0453u.m3847a((Object) c0434m, (Object) "Supervisor must not be null");
        this.f2277r = new C0433l(looper, this);
        this.f2261a = new C0424b(this, looper);
        this.f2278s = i;
        this.f2264e = (C0419h) C0453u.m3846a((Object) c0419h);
        this.f2276q = c0419h.m3575b();
        this.f2275p = m3612b(c0419h.m3577d());
    }

    protected C0432k(Context context, Looper looper, C0434m c0434m, int i, C0419h c0419h, C0221b c0221b, C0222c c0222c) {
        this(context, looper, c0434m, i, c0419h);
        m3624a((C0221b) C0453u.m3846a((Object) c0221b));
        m3625a((C0222c) C0453u.m3846a((Object) c0222c));
    }

    private void m3607a(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        C0453u.m3854b(z);
        synchronized (this.f2267h) {
            this.f2274o = i;
            this.f2271l = t;
        }
    }

    private boolean m3609a(int i, int i2, T t) {
        boolean z;
        synchronized (this.f2267h) {
            if (this.f2274o != i) {
                z = false;
            } else {
                m3607a(i2, (IInterface) t);
                z = true;
            }
        }
        return z;
    }

    private List<String> m3612b(List<String> list) {
        List<String> a = m3619a((List) list);
        if (a == null || a == list) {
            return a;
        }
        for (String contains : a) {
            if (!list.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return a;
    }

    protected abstract T m3618a(IBinder iBinder);

    protected List<String> m3619a(List<String> list) {
        return list;
    }

    public void m3620a() {
        this.f2262b = true;
        m3607a(2, null);
        int a = C0270e.m3379a(this.f2263d);
        if (a != 0) {
            m3607a(1, null);
            this.f2261a.sendMessage(this.f2261a.obtainMessage(3, Integer.valueOf(a)));
            return;
        }
        if (this.f2273n != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + m3633e());
            this.f2266g.m3654b(m3633e(), this.f2273n, m3635g());
        }
        this.f2273n = new C0428e(this);
        if (!this.f2266g.m3653a(m3633e(), this.f2273n, m3635g())) {
            Log.e("GmsClient", "unable to connect to service: " + m3633e());
            this.f2261a.sendMessage(this.f2261a.obtainMessage(3, Integer.valueOf(9)));
        }
    }

    public void m3621a(int i) {
        this.f2261a.sendMessage(this.f2261a.obtainMessage(4, Integer.valueOf(i)));
    }

    protected void m3622a(int i, Bundle bundle) {
        this.f2261a.sendMessage(this.f2261a.obtainMessage(5, new C0431h(this, i, bundle)));
    }

    protected void m3623a(int i, IBinder iBinder, Bundle bundle) {
        this.f2261a.sendMessage(this.f2261a.obtainMessage(1, new C0429f(this, i, iBinder, bundle)));
    }

    public void m3624a(C0221b c0221b) {
        this.f2277r.m3648a(c0221b);
    }

    public void m3625a(C0222c c0222c) {
        this.f2277r.m3649a(c0222c);
    }

    public void m3626a(C0225e c0225e) {
        this.f2270k = (C0225e) C0453u.m3847a((Object) c0225e, (Object) "Must provide a non-null ConnectionStatusReportCallbacks");
        this.f2269j = true;
    }

    public void m3627a(C0411p c0411p) {
        try {
            this.f2268i.m3697a(new C0427d(this), new zzae(c0411p, this.f2275p == null ? null : C0521z.m4181a(this.f2275p), this.f2263d.getPackageName(), m3642n()));
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            m3621a(1);
        } catch (Throwable e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    public void m3628a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).println("GmsClient:");
        CharSequence charSequence = str + "  ";
        printWriter.append(charSequence).append("mStartServiceAction=").println(m3633e());
        synchronized (this.f2267h) {
            int i = this.f2274o;
            IInterface iInterface = this.f2271l;
        }
        printWriter.append(charSequence).append("mConnectState=");
        switch (i) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                printWriter.print("DISCONNECTED");
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                printWriter.print("CONNECTING");
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                printWriter.print("CONNECTED");
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.println("null");
        } else {
            printWriter.append(m3634f()).append("@").println(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
    }

    public Bundle a_() {
        return null;
    }

    public void m3629b() {
        this.f2262b = false;
        synchronized (this.f2272m) {
            int size = this.f2272m.size();
            for (int i = 0; i < size; i++) {
                ((C0422c) this.f2272m.get(i)).m3589e();
            }
            this.f2272m.clear();
        }
        m3607a(1, null);
        if (this.f2273n != null) {
            this.f2266g.m3654b(m3633e(), this.f2273n, m3635g());
            this.f2273n = null;
        }
    }

    public void m3630b(C0411p c0411p) {
        try {
            zzi a = new zzi(this.f2278s).m3872a(this.f2263d.getPackageName()).m3870a(m3639k());
            if (this.f2275p != null) {
                a.m3873a(C0521z.m4181a(this.f2275p));
            }
            if (m3632d()) {
                a.m3869a(m3638j()).m3871a(c0411p);
            } else if (m3643o()) {
                a.m3869a(this.f2276q);
            }
            this.f2268i.m3698a(new C0427d(this), a);
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            m3621a(1);
        } catch (Throwable e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    public boolean b_() {
        return this.f2262b;
    }

    public boolean m3631c() {
        boolean z;
        synchronized (this.f2267h) {
            z = this.f2274o == 3;
        }
        return z;
    }

    public boolean m3632d() {
        return false;
    }

    protected abstract String m3633e();

    protected abstract String m3634f();

    protected String m3635g() {
        return this.f2264e.m3579f();
    }

    public boolean m3636h() {
        boolean z;
        synchronized (this.f2267h) {
            z = this.f2274o == 2;
        }
        return z;
    }

    public final Context m3637i() {
        return this.f2263d;
    }

    public final Account m3638j() {
        return this.f2276q != null ? this.f2276q : new Account("<<default account>>", "com.google");
    }

    protected Bundle m3639k() {
        return new Bundle();
    }

    protected final void m3640l() {
        if (!m3631c()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T m3641m() throws DeadObjectException {
        T t;
        synchronized (this.f2267h) {
            if (this.f2274o == 4) {
                throw new DeadObjectException();
            }
            m3640l();
            C0453u.m3851a(this.f2271l != null, (Object) "Client is connected but service is null");
            t = this.f2271l;
        }
        return t;
    }

    protected Bundle m3642n() {
        return null;
    }

    public boolean m3643o() {
        return false;
    }
}
