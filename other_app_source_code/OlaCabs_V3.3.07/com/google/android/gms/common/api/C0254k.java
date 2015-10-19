package com.google.android.gms.common.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.C0270e;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0218b.C0210a;
import com.google.android.gms.common.api.C0218b.C0215b;
import com.google.android.gms.common.api.C0218b.C0216c;
import com.google.android.gms.common.api.C0218b.C0217d;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.C0226d.C0225e;
import com.google.android.gms.common.api.C0234j.C0233a;
import com.google.android.gms.common.api.k.AnonymousClass10;
import com.google.android.gms.common.internal.C0249s.C0250a;
import com.google.android.gms.common.internal.C0411p;
import com.google.android.gms.common.internal.C0419h;
import com.google.android.gms.common.internal.C0433l;
import com.google.android.gms.common.internal.C0433l.C0245a;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.C0521z;
import com.google.android.gms.internal.bh;
import com.google.android.gms.internal.bi;
import com.google.android.gms.internal.bk;
import com.google.android.gms.internal.zzut;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.google.android.gms.common.api.k */
final class C0254k implements C0226d {
    private int f2049A;
    private boolean f2050B;
    private boolean f2051C;
    private C0411p f2052D;
    private boolean f2053E;
    private boolean f2054F;
    private final Set<C0255l<?>> f2055G;
    private final C0235e f2056H;
    private final C0221b f2057I;
    private final C0225e f2058J;
    private final C0245a f2059K;
    final Queue<C0232g<?>> f2060a;
    BroadcastReceiver f2061b;
    final Set<C0232g<?>> f2062c;
    private final Lock f2063d;
    private final Condition f2064e;
    private final C0433l f2065f;
    private final int f2066g;
    private final Context f2067h;
    private final Looper f2068i;
    private ConnectionResult f2069j;
    private int f2070k;
    private volatile int f2071l;
    private volatile boolean f2072m;
    private int f2073n;
    private boolean f2074o;
    private int f2075p;
    private long f2076q;
    private long f2077r;
    private final C0252c f2078s;
    private final Bundle f2079t;
    private final Map<C0217d<?>, C0215b> f2080u;
    private final Set<C0217d<?>> f2081v;
    private final Map<C0217d<?>, ConnectionResult> f2082w;
    private final List<String> f2083x;
    private boolean f2084y;
    private bh f2085z;

    /* renamed from: com.google.android.gms.common.api.k.g */
    interface C0232g<A extends C0215b> {
        void m3240a(A a) throws DeadObjectException;

        void m3241a(C0235e c0235e);

        void m3242b();

        void m3243b(Status status);

        C0217d<A> m3244f();

        int m3245g();
    }

    /* renamed from: com.google.android.gms.common.api.k.10 */
    class AnonymousClass10 implements C0222c {
        final /* synthetic */ int f2030a;
        final /* synthetic */ C0218b f2031b;
        final /* synthetic */ C0254k f2032c;

        AnonymousClass10(C0254k c0254k, int i, C0218b c0218b) {
            this.f2032c = c0254k;
            this.f2030a = i;
            this.f2031b = c0218b;
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            this.f2032c.f2063d.lock();
            try {
                if (this.f2032c.m3340e()) {
                    if (this.f2030a != 2) {
                        int a = this.f2031b.m3202a().m3200a();
                        if (this.f2032c.m3287a(a, this.f2030a, connectionResult)) {
                            this.f2032c.f2069j = connectionResult;
                            this.f2032c.f2070k = a;
                        }
                    }
                    this.f2032c.f2082w.put(this.f2031b.m3204c(), connectionResult);
                    this.f2032c.m3309h();
                    this.f2032c.f2063d.unlock();
                }
            } finally {
                this.f2032c.f2063d.unlock();
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.e */
    interface C0235e {
        void m3255a(C0232g<?> c0232g);
    }

    /* renamed from: com.google.android.gms.common.api.k.1 */
    class C02361 implements C0235e {
        final /* synthetic */ C0254k f2033a;

        C02361(C0254k c0254k) {
            this.f2033a = c0254k;
        }

        public void m3256a(C0232g<?> c0232g) {
            this.f2033a.f2062c.remove(c0232g);
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.f */
    private abstract class C0237f implements C0221b {
        final /* synthetic */ C0254k f2034b;

        private C0237f(C0254k c0254k) {
            this.f2034b = c0254k;
        }

        public void m3257a(int i) {
            this.f2034b.f2063d.lock();
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    if (!this.f2034b.m3341f()) {
                        this.f2034b.f2072m = true;
                        if (this.f2034b.f2061b == null) {
                            this.f2034b.f2061b = new C0253d(this.f2034b);
                            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                            intentFilter.addDataScheme("package");
                            this.f2034b.f2067h.getApplicationContext().registerReceiver(this.f2034b.f2061b, intentFilter);
                        }
                        this.f2034b.f2078s.sendMessageDelayed(this.f2034b.f2078s.obtainMessage(1), this.f2034b.f2076q);
                        this.f2034b.f2078s.sendMessageDelayed(this.f2034b.f2078s.obtainMessage(2), this.f2034b.f2077r);
                        this.f2034b.m3279a(i);
                        break;
                    }
                    this.f2034b.f2063d.unlock();
                    return;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    try {
                        this.f2034b.m3279a(i);
                        this.f2034b.m3335b();
                        break;
                    } catch (Throwable th) {
                        this.f2034b.f2063d.unlock();
                    }
            }
            this.f2034b.f2063d.unlock();
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.2 */
    class C02382 extends C0237f {
        final /* synthetic */ C0254k f2035a;

        C02382(C0254k c0254k) {
            this.f2035a = c0254k;
            super(null);
        }

        public void m3258a(Bundle bundle) {
            this.f2035a.m3311i();
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.3 */
    class C02393 implements C0222c {
        final /* synthetic */ C0254k f2036a;

        C02393(C0254k c0254k) {
            this.f2036a = c0254k;
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            this.f2036a.f2063d.lock();
            try {
                this.f2036a.m3292b(new ConnectionResult(8, null));
            } finally {
                this.f2036a.f2063d.unlock();
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.4 */
    class C02404 implements Runnable {
        final /* synthetic */ zzaa f2037a;
        final /* synthetic */ C0254k f2038b;

        C02404(C0254k c0254k, zzaa com_google_android_gms_common_internal_zzaa) {
            this.f2038b = c0254k;
            this.f2037a = com_google_android_gms_common_internal_zzaa;
        }

        public void run() {
            this.f2038b.f2052D = this.f2037a.m3860a();
            this.f2038b.f2051C = true;
            this.f2038b.f2053E = this.f2037a.m3862c();
            this.f2038b.f2054F = this.f2037a.m3863d();
            this.f2038b.m3316l();
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.5 */
    class C02415 implements Runnable {
        final /* synthetic */ ConnectionResult f2039a;
        final /* synthetic */ C0254k f2040b;

        C02415(C0254k c0254k, ConnectionResult connectionResult) {
            this.f2040b = c0254k;
            this.f2039a = connectionResult;
        }

        public void run() {
            this.f2040b.f2063d.lock();
            try {
                if (this.f2040b.m3297c(this.f2039a)) {
                    this.f2040b.f2084y = false;
                    for (C0217d c0217d : this.f2040b.f2081v) {
                        C0215b c0215b = (C0215b) this.f2040b.f2080u.get(c0217d);
                        if (c0215b.m3198c()) {
                            c0215b.m3196b();
                        }
                        if (!this.f2040b.f2082w.containsKey(c0217d)) {
                            this.f2040b.f2082w.put(c0217d, new ConnectionResult(17, null));
                        }
                    }
                    this.f2040b.f2084y = true;
                    this.f2040b.m3319m();
                } else {
                    this.f2040b.m3281a(this.f2039a);
                }
                this.f2040b.f2063d.unlock();
            } catch (Throwable th) {
                this.f2040b.f2063d.unlock();
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.6 */
    class C02426 implements Runnable {
        final /* synthetic */ C0254k f2041a;

        C02426(C0254k c0254k) {
            this.f2041a = c0254k;
        }

        public void run() {
            this.f2041a.f2063d.lock();
            try {
                this.f2041a.m3319m();
            } finally {
                this.f2041a.f2063d.unlock();
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.7 */
    class C02437 extends C0237f {
        final /* synthetic */ C0254k f2042a;

        C02437(C0254k c0254k) {
            this.f2042a = c0254k;
            super(null);
        }

        public void m3259a(Bundle bundle) {
            this.f2042a.f2063d.lock();
            try {
                if (this.f2042a.m3340e()) {
                    if (bundle != null) {
                        this.f2042a.f2079t.putAll(bundle);
                    }
                    this.f2042a.m3309h();
                    this.f2042a.f2063d.unlock();
                }
            } finally {
                this.f2042a.f2063d.unlock();
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.8 */
    class C02448 implements C0225e {
        final /* synthetic */ C0254k f2043a;

        C02448(C0254k c0254k) {
            this.f2043a = c0254k;
        }

        private void m3260a(int i) {
            this.f2043a.f2063d.lock();
            try {
                if (this.f2043a.f2073n != i) {
                    Log.wtf("GoogleApiClientImpl", String.format("Internal error: step mismatch. Expected: %d, Actual: %d", new Object[]{Integer.valueOf(this.f2043a.f2073n), Integer.valueOf(i)}));
                    this.f2043a.m3279a(4);
                    return;
                }
                if (this.f2043a.f2071l == 1) {
                    this.f2043a.m3309h();
                }
                this.f2043a.f2063d.unlock();
            } finally {
                this.f2043a.f2063d.unlock();
            }
        }

        public void m3261a() {
            m3260a(0);
        }

        public void m3262b() {
            m3260a(1);
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.9 */
    class C02469 implements C0245a {
        final /* synthetic */ C0254k f2044a;

        C02469(C0254k c0254k) {
            this.f2044a = c0254k;
        }

        public Bundle a_() {
            return null;
        }

        public boolean b_() {
            return this.f2044a.f2084y;
        }

        public boolean m3264c() {
            return this.f2044a.m3339d();
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.a */
    private static class C0248a extends bk {
        private WeakReference<C0254k> f2045a;

        C0248a(C0254k c0254k) {
            this.f2045a = new WeakReference(c0254k);
        }

        public void m3270a(ConnectionResult connectionResult, zzut com_google_android_gms_internal_zzut) {
            C0254k c0254k = (C0254k) this.f2045a.get();
            if (c0254k != null) {
                c0254k.m3300d(connectionResult);
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.b */
    private static class C0251b extends C0250a {
        private WeakReference<C0254k> f2046a;

        C0251b(C0254k c0254k) {
            this.f2046a = new WeakReference(c0254k);
        }

        public void m3273a(zzaa com_google_android_gms_common_internal_zzaa) {
            C0254k c0254k = (C0254k) this.f2046a.get();
            if (c0254k != null) {
                c0254k.m3285a(com_google_android_gms_common_internal_zzaa);
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.c */
    private final class C0252c extends Handler {
        final /* synthetic */ C0254k f2047a;

        C0252c(C0254k c0254k, Looper looper) {
            this.f2047a = c0254k;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f2047a.m3325p();
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.f2047a.m3322o();
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.k.d */
    private static class C0253d extends BroadcastReceiver {
        private WeakReference<C0254k> f2048a;

        C0253d(C0254k c0254k) {
            this.f2048a = new WeakReference(c0254k);
        }

        public void onReceive(Context context, Intent intent) {
            Uri data = intent.getData();
            String str = null;
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
            if (str != null && str.equals("com.google.android.gms")) {
                C0254k c0254k = (C0254k) this.f2048a.get();
                if (c0254k != null) {
                    c0254k.m3322o();
                }
            }
        }
    }

    public C0254k(Context context, Looper looper, C0419h c0419h, C0216c<? extends bh, bi> c0216c, Map<C0218b<?>, C0210a> map, Map<C0218b<?>, Boolean> map2, Set<C0221b> set, Set<C0222c> set2, int i) {
        this.f2063d = new ReentrantLock();
        this.f2064e = this.f2063d.newCondition();
        this.f2060a = new LinkedList();
        this.f2071l = 4;
        this.f2073n = 0;
        this.f2074o = false;
        this.f2076q = 120000;
        this.f2077r = 5000;
        this.f2079t = new Bundle();
        this.f2080u = new HashMap();
        this.f2081v = new HashSet();
        this.f2082w = new HashMap();
        this.f2055G = Collections.newSetFromMap(new WeakHashMap());
        this.f2062c = Collections.newSetFromMap(new ConcurrentHashMap(16, 0.75f, 2));
        this.f2056H = new C02361(this);
        this.f2057I = new C02437(this);
        this.f2058J = new C02448(this);
        this.f2059K = new C02469(this);
        this.f2067h = context;
        this.f2065f = new C0433l(looper, this.f2059K);
        this.f2068i = looper;
        this.f2078s = new C0252c(this, looper);
        this.f2066g = i;
        for (C0221b a : set) {
            this.f2065f.m3648a(a);
        }
        for (C0222c a2 : set2) {
            this.f2065f.m3649a(a2);
        }
        this.f2083x = Collections.unmodifiableList(c0419h.m3577d());
        this.f2050B = false;
        this.f2049A = 2;
        int i2 = 0;
        for (C0218b c0218b : map.keySet()) {
            int i3;
            Object obj = map.get(c0218b);
            if (map2.get(c0218b) != null) {
                i3 = ((Boolean) map2.get(c0218b)).booleanValue() ? 2 : 1;
            } else {
                i3 = 0;
            }
            C0215b a3 = C0254k.m3275a(c0218b.m3202a(), obj, context, looper, c0419h, this.f2057I, m3276a(c0218b, i3));
            a3.m3193a(this.f2058J);
            this.f2080u.put(c0218b.m3204c(), a3);
            int i4 = (c0218b.m3202a().m3200a() == 1 ? 1 : 0) | i2;
            if (a3.m3199d()) {
                this.f2050B = true;
                if (i3 < this.f2049A) {
                    this.f2049A = i3;
                }
                if (i3 != 0) {
                    this.f2081v.add(c0218b.m3204c());
                }
            }
            i2 = i4;
        }
        if (i2 != 0) {
            this.f2050B = false;
        }
        if (this.f2050B) {
            c0419h.m3574a(Integer.valueOf(m3342g()));
            m3280a(context, looper, c0419h, (C0216c) c0216c);
        }
    }

    private static <C extends C0215b, O> C m3275a(C0216c<C, O> c0216c, Object obj, Context context, Looper looper, C0419h c0419h, C0221b c0221b, C0222c c0222c) {
        return c0216c.m3201a(context, looper, c0419h, obj, c0221b, c0222c);
    }

    private final C0222c m3276a(C0218b<?> c0218b, int i) {
        return new AnonymousClass10(this, i, c0218b);
    }

    private void m3279a(int i) {
        this.f2063d.lock();
        try {
            if (this.f2071l != 3) {
                if (i == -1) {
                    Iterator it;
                    C0232g c0232g;
                    if (m3340e()) {
                        it = this.f2060a.iterator();
                        while (it.hasNext()) {
                            c0232g = (C0232g) it.next();
                            if (c0232g.m3245g() != 1) {
                                c0232g.m3242b();
                                it.remove();
                            }
                        }
                    } else {
                        for (C0232g c0232g2 : this.f2060a) {
                            c0232g2.m3242b();
                        }
                        this.f2060a.clear();
                    }
                    for (C0232g c0232g22 : this.f2062c) {
                        c0232g22.m3241a(null);
                        c0232g22.m3242b();
                    }
                    this.f2062c.clear();
                    for (C0255l a : this.f2055G) {
                        a.m3343a();
                    }
                    this.f2055G.clear();
                    if (this.f2069j != null || this.f2060a.isEmpty()) {
                        this.f2082w.clear();
                    } else {
                        this.f2074o = true;
                        return;
                    }
                }
                boolean e = m3340e();
                boolean d = m3339d();
                this.f2071l = 3;
                if (e) {
                    if (i == -1) {
                        this.f2069j = null;
                    }
                    this.f2064e.signalAll();
                }
                this.f2084y = false;
                for (C0215b b : this.f2080u.values()) {
                    b.m3196b();
                }
                m3286a(i == -1);
                this.f2084y = true;
                this.f2071l = 4;
                if (d) {
                    if (i != -1) {
                        this.f2065f.m3645a(i);
                    }
                    this.f2084y = false;
                }
            }
            this.f2063d.unlock();
        } finally {
            this.f2063d.unlock();
        }
    }

    private void m3280a(Context context, Looper looper, C0419h c0419h, C0216c<? extends bh, bi> c0216c) {
        this.f2085z = (bh) C0254k.m3275a(c0216c, c0419h.m3580g(), context, looper, c0419h, new C02382(this), new C02393(this));
    }

    private void m3281a(ConnectionResult connectionResult) {
        this.f2074o = false;
        m3286a(!connectionResult.m3159a());
        m3279a(3);
        if (!(m3341f() && C0270e.m3399d(this.f2067h, connectionResult.m3161c()))) {
            m3325p();
            this.f2065f.m3647a(connectionResult);
        }
        this.f2084y = false;
    }

    private <A extends C0215b> void m3282a(C0232g<A> c0232g) throws DeadObjectException {
        this.f2063d.lock();
        try {
            C0453u.m3855b(c0232g.m3244f() != null, "This task can not be executed or enqueued (it's probably a Batch or malformed)");
            this.f2062c.add(c0232g);
            c0232g.m3241a(this.f2056H);
            if (m3341f()) {
                c0232g.m3243b(new Status(8));
                return;
            }
            C0215b a = m3329a(c0232g.m3244f());
            if (a.m3198c() || !this.f2082w.containsKey(c0232g.m3244f())) {
                c0232g.m3240a(a);
                this.f2063d.unlock();
                return;
            }
            c0232g.m3243b(new Status(17));
            this.f2063d.unlock();
        } finally {
            this.f2063d.unlock();
        }
    }

    private void m3285a(zzaa com_google_android_gms_common_internal_zzaa) {
        ConnectionResult b = com_google_android_gms_common_internal_zzaa.m3861b();
        if (b.m3160b()) {
            this.f2078s.post(new C02404(this, com_google_android_gms_common_internal_zzaa));
        } else {
            m3292b(b);
        }
    }

    private void m3286a(boolean z) {
        if (this.f2085z != null) {
            if (this.f2085z.m3198c()) {
                if (z) {
                    this.f2085z.c_();
                }
                this.f2085z.m3196b();
            }
            this.f2052D = null;
        }
    }

    private boolean m3287a(int i, int i2, ConnectionResult connectionResult) {
        return (i2 != 1 || connectionResult.m3159a()) ? this.f2069j == null || i < this.f2070k : false;
    }

    private void m3292b(ConnectionResult connectionResult) {
        this.f2078s.post(new C02415(this, connectionResult));
    }

    private boolean m3297c(ConnectionResult connectionResult) {
        return this.f2049A != 2 ? this.f2049A == 1 && !connectionResult.m3159a() : true;
    }

    private void m3300d(ConnectionResult connectionResult) {
        if (connectionResult.m3160b()) {
            this.f2078s.post(new C02426(this));
        } else {
            m3292b(connectionResult);
        }
    }

    private void m3309h() {
        this.f2075p--;
        if (this.f2075p != 0) {
            return;
        }
        if (this.f2069j != null) {
            m3281a(this.f2069j);
            return;
        }
        switch (this.f2073n) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                if (this.f2050B) {
                    this.f2073n = 1;
                    m3316l();
                    return;
                }
                m3319m();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                m3314k();
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f2071l = 2;
                m3325p();
                if (this.f2085z != null) {
                    if (this.f2053E) {
                        this.f2085z.m4068a(this.f2052D, this.f2054F);
                    }
                    m3286a(false);
                }
                this.f2064e.signalAll();
                m3320n();
                if (this.f2074o) {
                    this.f2074o = false;
                    m3279a(-1);
                    return;
                }
                this.f2065f.m3646a(this.f2079t.isEmpty() ? null : this.f2079t);
            default:
        }
    }

    private void m3311i() {
        this.f2085z.m4069a(new C0251b(this));
    }

    private Set<Scope> m3312j() {
        return new HashSet(Arrays.asList(C0521z.m4181a(this.f2083x)));
    }

    private void m3314k() {
        this.f2085z.m4067a(this.f2052D, m3312j(), new C0248a(this));
    }

    private void m3316l() {
        C0453u.m3851a(m3328a() == this.f2078s.getLooper(), (Object) "This method must be run on the mHandlerForCallbacks thread");
        if (this.f2073n == 1 && this.f2051C) {
            this.f2075p = this.f2080u.size();
            Iterator it = this.f2080u.keySet().iterator();
            while (it.hasNext()) {
                C0217d c0217d = (C0217d) it.next();
                if (this.f2082w.containsKey(c0217d)) {
                    this.f2063d.lock();
                    try {
                        m3309h();
                    } finally {
                        it = this.f2063d;
                        it.unlock();
                    }
                } else {
                    ((C0215b) this.f2080u.get(c0217d)).m3194a(this.f2052D);
                }
            }
        }
    }

    private void m3319m() {
        this.f2073n = 2;
        this.f2075p = this.f2080u.size();
        for (C0217d c0217d : this.f2080u.keySet()) {
            if (this.f2082w.containsKey(c0217d)) {
                m3309h();
            } else {
                ((C0215b) this.f2080u.get(c0217d)).m3197b(this.f2052D);
            }
        }
    }

    private void m3320n() {
        this.f2063d.lock();
        try {
            boolean z = m3339d() || m3341f();
            C0453u.m3851a(z, (Object) "GoogleApiClient is not connected yet.");
            while (!this.f2060a.isEmpty()) {
                m3282a((C0232g) this.f2060a.remove());
            }
            this.f2063d.unlock();
        } catch (Throwable e) {
            Log.w("GoogleApiClientImpl", "Service died while flushing queue", e);
        } catch (Throwable th) {
            this.f2063d.unlock();
        }
    }

    private void m3322o() {
        this.f2063d.lock();
        try {
            if (m3341f()) {
                m3335b();
            }
            this.f2063d.unlock();
        } catch (Throwable th) {
            this.f2063d.unlock();
        }
    }

    private void m3325p() {
        this.f2063d.lock();
        try {
            if (this.f2072m) {
                this.f2072m = false;
                this.f2078s.removeMessages(2);
                this.f2078s.removeMessages(1);
                if (this.f2061b != null) {
                    this.f2067h.getApplicationContext().unregisterReceiver(this.f2061b);
                    this.f2061b = null;
                }
                this.f2063d.unlock();
            }
        } finally {
            this.f2063d.unlock();
        }
    }

    public Looper m3328a() {
        return this.f2068i;
    }

    public <C extends C0215b> C m3329a(C0217d<C> c0217d) {
        Object obj = (C0215b) this.f2080u.get(c0217d);
        C0453u.m3847a(obj, (Object) "Appropriate Api was not requested.");
        return obj;
    }

    public <A extends C0215b, R extends C0206g, T extends C0233a<R, A>> T m3330a(T t) {
        C0453u.m3855b(this.f2080u.containsKey(t.m3253f()), "GoogleApiClient is not configured to use the API required for this call.");
        this.f2063d.lock();
        try {
            if (m3339d()) {
                m3334b((C0233a) t);
            } else {
                this.f2060a.add(t);
            }
            this.f2063d.unlock();
            return t;
        } catch (Throwable th) {
            this.f2063d.unlock();
        }
    }

    public void m3331a(C0221b c0221b) {
        this.f2065f.m3648a(c0221b);
    }

    public void m3332a(C0222c c0222c) {
        this.f2065f.m3649a(c0222c);
    }

    public void m3333a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).println("GoogleApiClient:");
        Object obj = str + "  ";
        printWriter.append(obj).append("mConnectionState=");
        switch (this.f2071l) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                printWriter.print("CONNECTING");
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                printWriter.print("CONNECTED");
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                printWriter.print("DISCONNECTING");
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                printWriter.print("DISCONNECTED");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mResuming=").print(this.f2072m);
        printWriter.append(" mWaitingToDisconnect=").println(this.f2074o);
        printWriter.append(obj).append("mWorkQueue.size()=").print(this.f2060a.size());
        printWriter.append(" mUnconsumedRunners.size()=").println(this.f2062c.size());
        for (C0215b a : this.f2080u.values()) {
            a.m3195a(obj, fileDescriptor, printWriter, strArr);
        }
    }

    public <A extends C0215b, T extends C0233a<? extends C0206g, A>> T m3334b(T t) {
        boolean z = m3339d() || m3341f();
        C0453u.m3851a(z, (Object) "GoogleApiClient is not connected yet.");
        m3320n();
        try {
            m3282a((C0232g) t);
        } catch (DeadObjectException e) {
            m3279a(1);
        }
        return t;
    }

    public void m3335b() {
        this.f2063d.lock();
        try {
            this.f2074o = false;
            if (m3339d() || m3340e()) {
                this.f2063d.unlock();
                return;
            }
            this.f2084y = true;
            this.f2069j = null;
            this.f2071l = 1;
            this.f2073n = 0;
            this.f2079t.clear();
            this.f2075p = this.f2080u.size();
            this.f2082w.clear();
            this.f2051C = false;
            this.f2053E = false;
            this.f2054F = false;
            if (this.f2050B) {
                this.f2085z.m3192a();
            }
            for (C0215b a : this.f2080u.values()) {
                a.m3192a();
            }
            this.f2063d.unlock();
        } catch (Throwable th) {
            this.f2063d.unlock();
        }
    }

    public void m3336b(C0221b c0221b) {
        this.f2065f.m3650b(c0221b);
    }

    public void m3337b(C0222c c0222c) {
        this.f2065f.m3651b(c0222c);
    }

    public void m3338c() {
        m3325p();
        m3279a(-1);
    }

    public boolean m3339d() {
        return this.f2071l == 2;
    }

    public boolean m3340e() {
        return this.f2071l == 1;
    }

    boolean m3341f() {
        return this.f2072m;
    }

    public int m3342g() {
        return System.identityHashCode(this);
    }
}
