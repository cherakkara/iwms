package com.google.android.m4b.maps.ap;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Pair;
import com.google.android.m4b.maps.ah.EventLog;
import com.google.android.m4b.maps.an.MutableVectorTile;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ad;
import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.ap.DashServerMapTileStore.DashServerMapTileStore;
import com.google.android.m4b.maps.ap.TileStore.TileStore;
import com.google.android.m4b.maps.aq.TileCallback;
import com.google.android.m4b.maps.ar.DiskTileCache;
import com.google.android.m4b.maps.ar.TileCache;
import com.google.android.m4b.maps.au.DriveAboutThread;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.android.m4b.maps.p058v.Stats;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.google.android.m4b.maps.ap.b */
public abstract class DashServerTileStore extends DriveAboutThread implements C0592f, TileStore, DataRequestListener {
    protected TileStoreCache f3758a;
    protected Clock f3759b;
    protected final ai f3760c;
    private volatile DashServerTileStore f3761e;
    private final ReentrantLock f3762f;
    private final DataRequestDispatcherInterface f3763g;
    private Handler f3764h;
    private boolean f3765i;
    private DashServerTileStore f3766j;
    private final List<DashServerTileStore> f3767k;
    private final LRUCache<ac, DashServerTileStore> f3768l;
    private final Map<ac, DashServerTileStore> f3769m;
    private final int f3770n;
    private volatile int f3771o;
    private volatile int f3772p;
    private volatile int f3773q;
    private boolean f3774r;
    private Stats f3775s;
    private final ArrayList<WeakReference<TileStore>> f3776t;
    private volatile boolean f3777u;
    private final TileCallback f3778v;

    /* renamed from: com.google.android.m4b.maps.ap.b.a */
    public static abstract class DashServerTileStore extends BaseDataRequest {
        private final DashServerTileStore[] f3743a;
        private int f3744b;
        private DashServerTileStore f3745c;
        private final Map<Pair<Long, String>, Integer> f3746d;

        protected abstract aa m6155b(int i);

        static /* synthetic */ boolean m6150a(DashServerTileStore dashServerTileStore, int i) {
            return 2 + dashServerTileStore.f3744b <= dashServerTileStore.f3743a.length;
        }

        protected DashServerTileStore(int i) {
            this.f3743a = new DashServerTileStore[8];
            this.f3744b = 0;
            this.f3746d = au.m2807a();
        }

        protected final void m6153a(Pair<Long, String> pair, DashServerTileStore dashServerTileStore) {
            if (pair.second == null) {
                pair = new Pair(pair.first, Trace.NULL);
            }
            if (this.f3746d.get(pair) != null) {
                throw new IllegalArgumentException("Duplicate tile key: " + pair + ", already exists in batch for " + dashServerTileStore);
            }
            this.f3746d.put(pair, Integer.valueOf(this.f3744b));
            DashServerTileStore[] dashServerTileStoreArr = this.f3743a;
            int i = this.f3744b;
            this.f3744b = i + 1;
            dashServerTileStoreArr[i] = dashServerTileStore;
        }

        public final int m6158k() {
            return this.f3744b;
        }

        public final DashServerTileStore m6151a(int i) {
            return this.f3743a[i];
        }

        public final Integer m6152a(Pair<Long, String> pair) {
            if (pair.second == null) {
                pair = new Pair(pair.first, Trace.NULL);
            }
            return (Integer) this.f3746d.get(pair);
        }

        protected final boolean m6159l() {
            return this.f3744b == this.f3743a.length;
        }

        protected int m6157j() {
            return -1;
        }

        protected byte[] m6156c(int i) {
            return null;
        }

        protected boolean m6154a(DashServerTileStore dashServerTileStore) {
            return true;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.b.1 */
    class DashServerTileStore implements TileCallback {
        private /* synthetic */ DashServerTileStore f3785a;

        DashServerTileStore(DashServerTileStore dashServerTileStore) {
            this.f3785a = dashServerTileStore;
        }

        public final void m6227a(ac acVar, int i, aa aaVar) {
            if (i == 0) {
                DashServerTileStore.m6189a(this.f3785a, aaVar);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.b.2 */
    class DashServerTileStore extends LRUCache<ac, DashServerTileStore> {
        private /* synthetic */ DashServerTileStore f3790a;

        DashServerTileStore(DashServerTileStore dashServerTileStore, int i) {
            this.f3790a = dashServerTileStore;
            super(i);
        }

        public final /* bridge */ /* synthetic */ void m6242a(Object obj, Object obj2) {
            this.f3790a.m6208a((DashServerTileStore) obj2, 1, null);
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.b.3 */
    class DashServerTileStore extends Handler {
        private /* synthetic */ DashServerTileStore f3791a;

        DashServerTileStore(DashServerTileStore dashServerTileStore) {
            this.f3791a = dashServerTileStore;
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f3791a.m6194b((DashServerTileStore) message.obj);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    DashServerTileStore.m6188a(this.f3791a);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    DashServerTileStore.m6191a(this.f3791a, (DashServerTileStore) message.obj);
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    this.f3791a.f3774r = true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    synchronized (message.obj) {
                        message.obj.notify();
                        break;
                    }
                    DashServerTileStore.m6197c(this.f3791a);
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    Pair pair = (Pair) message.obj;
                    DashServerTileStore.m6190a(this.f3791a, (ad) ((Pair) pair.first).first, (DashServerMapTileStore) ((Pair) pair.first).second, (TileCallback) pair.second);
                default:
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.b.b */
    class DashServerTileStore extends DriveAboutThread {
        private volatile boolean f3792a;
        private volatile boolean f3793b;
        private /* synthetic */ DashServerTileStore f3794c;

        DashServerTileStore(DashServerTileStore dashServerTileStore) {
            this.f3794c = dashServerTileStore;
            super("CacheCommitter:" + dashServerTileStore.getName());
            if (dashServerTileStore.f3770n < 0) {
                this.f3793b = true;
            } else {
                start();
            }
        }

        public final void m6245f() {
            try {
                Process.setThreadPriority(VectorGlobalState.m7288d() + 1);
            } catch (SecurityException e) {
                Util.m11550a(getName(), "Could not set thread priority: " + e);
            }
            DiskTileCache b = this.f3794c.f3758a.m6308b();
            if (b != null) {
                if (this.f3792a || !this.f3794c.f3777u) {
                    do {
                        this.f3792a = false;
                        try {
                            for (int d = this.f3794c.f3770n; d > 0; d += HarvestErrorCodes.NSURLErrorBadURL) {
                                DashServerTileStore.sleep(1000);
                                if (this.f3794c.f3777u) {
                                    break;
                                }
                            }
                        } catch (InterruptedException e2) {
                            return;
                        }
                    } while (this.f3792a);
                    this.f3794c.f3777u = false;
                    b.f_();
                } else {
                    this.f3794c.f3777u = false;
                    b.f_();
                }
                this.f3793b = true;
                this.f3794c.m6203k();
            }
        }

        final void m6243a() {
            this.f3792a = true;
        }

        final boolean m6244b() {
            return this.f3793b;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.b.c */
    public static class DashServerTileStore implements TileCallback {
        private aa f3795a;

        protected DashServerTileStore() {
        }

        public final void m6247a(ac acVar, int i, aa aaVar) {
            if (i == 0) {
                this.f3795a = aaVar;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.b.d */
    public static class DashServerTileStore {
        final ac f3796a;
        final TileCallback f3797b;
        final boolean f3798c;
        final DashServerMapTileStore f3799d;
        final boolean f3800e;
        final boolean f3801f;
        final boolean f3802g;
        final ai f3803h;
        int f3804i;
        private volatile boolean f3805j;
        private DashServerTileStore f3806k;

        protected DashServerTileStore(ai aiVar, ac acVar, TileCallback tileCallback, DashServerMapTileStore dashServerMapTileStore, boolean z, boolean z2, int i, boolean z3) {
            boolean z4 = false;
            this.f3805j = false;
            this.f3806k = null;
            this.f3803h = aiVar;
            this.f3796a = acVar;
            this.f3797b = tileCallback;
            this.f3799d = dashServerMapTileStore;
            if (dashServerMapTileStore.equals(DashServerMapTileStore.PREFETCH_AREA) || dashServerMapTileStore.equals(DashServerMapTileStore.PREFETCH_ROUTE)) {
                z4 = true;
            }
            this.f3798c = z4;
            this.f3800e = z;
            this.f3804i = i;
            this.f3801f = z2;
            this.f3802g = z3;
        }

        protected DashServerTileStore(ai aiVar, ac acVar, TileCallback tileCallback) {
            this(aiVar, acVar, tileCallback, DashServerMapTileStore.NORMAL, false, false, -1, false);
        }

        protected DashServerTileStore(ai aiVar, ac acVar, TileCallback tileCallback, boolean z, boolean z2) {
            this(aiVar, acVar, tileCallback, DashServerMapTileStore.NORMAL, false, true, -1, false);
        }

        protected final boolean m6251a() {
            return this.f3806k != null;
        }

        final void m6250a(DashServerTileStore dashServerTileStore) {
            dashServerTileStore.f3806k = this.f3806k;
            this.f3806k = dashServerTileStore;
        }

        public final String toString() {
            return this.f3803h + "/" + this.f3796a;
        }
    }

    protected abstract DashServerTileStore m6219g();

    static /* synthetic */ void m6188a(DashServerTileStore dashServerTileStore) {
        dashServerTileStore.f3765i = false;
        dashServerTileStore.m6202j();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void m6189a(com.google.android.m4b.maps.ap.DashServerTileStore r4, com.google.android.m4b.maps.an.aa r5) {
        /*
        r2 = r4.f3776t;
        monitor-enter(r2);
        r1 = 0;
    L_0x0004:
        r0 = r4.f3776t;	 Catch:{ all -> 0x002b }
        r0 = r0.size();	 Catch:{ all -> 0x002b }
        if (r1 >= r0) goto L_0x002e;
    L_0x000c:
        r0 = r4.f3776t;	 Catch:{ all -> 0x002b }
        r0 = r0.get(r1);	 Catch:{ all -> 0x002b }
        r0 = (java.lang.ref.WeakReference) r0;	 Catch:{ all -> 0x002b }
        r0 = r0.get();	 Catch:{ all -> 0x002b }
        r0 = (com.google.android.m4b.maps.ap.TileStore.TileStore) r0;	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x0023;
    L_0x001c:
        r0.m5320a(r5);	 Catch:{ all -> 0x002b }
        r0 = r1;
    L_0x0020:
        r1 = r0 + 1;
        goto L_0x0004;
    L_0x0023:
        r3 = r4.f3776t;	 Catch:{ all -> 0x002b }
        r0 = r1 + -1;
        r3.remove(r1);	 Catch:{ all -> 0x002b }
        goto L_0x0020;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x002e:
        monitor-exit(r2);	 Catch:{ all -> 0x002b }
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ap.b.a(com.google.android.m4b.maps.ap.b, com.google.android.m4b.maps.an.aa):void");
    }

    static /* synthetic */ void m6190a(DashServerTileStore dashServerTileStore, ad adVar, DashServerMapTileStore dashServerMapTileStore, TileCallback tileCallback) {
        int a = RefCountUtil.m6291a(dashServerMapTileStore, false);
        DiskTileCache b = dashServerTileStore.f3758a.m6308b();
        while (true) {
            ac a2 = adVar.m5448a();
            if (a2 == null) {
                dashServerTileStore.f3777u = true;
                dashServerTileStore.m6203k();
                return;
            } else if (b != null) {
                b.m6354a(a2, tileCallback, a);
            } else {
                tileCallback.m5267a(a2, 1, null);
            }
        }
    }

    static /* synthetic */ void m6191a(DashServerTileStore dashServerTileStore, DashServerTileStore dashServerTileStore2) {
        Throwable e;
        if (dashServerTileStore.f3774r) {
            dashServerTileStore.f3774r = false;
            while (dashServerTileStore.f3768l.m6234b() != 0) {
                dashServerTileStore.m6194b((DashServerTileStore) dashServerTileStore.f3768l.m6237c());
            }
        }
        int j = dashServerTileStore2.m6157j();
        if (!(j == -1 || j == dashServerTileStore.f3758a.m6310d() || !dashServerTileStore.f3758a.m6307a(j))) {
            dashServerTileStore.m6201i();
        }
        if (dashServerTileStore.f3767k.remove(dashServerTileStore2)) {
            Object c;
            DiskTileCache b = dashServerTileStore.f3758a.m6308b();
            int k = dashServerTileStore2.m6158k();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            j = 0;
            while (i5 < dashServerTileStore2.m6158k()) {
                DashServerTileStore a = dashServerTileStore2.m6151a(i5);
                ac a2 = a.f3796a.m5435a(a.f3803h);
                int i6 = a.f3804i != -1 ? j + 1 : j;
                dashServerTileStore.f3769m.remove(a2);
                dashServerTileStore.f3771o--;
                int i7;
                int i8;
                try {
                    if (a.f3798c) {
                        dashServerTileStore.f3773q++;
                    } else {
                        dashServerTileStore.f3772p++;
                    }
                    byte[] bArr = null;
                    if (b != null) {
                        c = dashServerTileStore2.m6156c(i5);
                        if (c != null) {
                            bArr = new byte[c.length];
                            System.arraycopy(c, 0, bArr, 0, c.length);
                        }
                    }
                    ai c2 = DashServerTileStore.m6196c(a);
                    aa b2 = dashServerTileStore2.m6155b(i5);
                    aa c3;
                    if (b2 != null) {
                        aa aaVar;
                        if (!(dashServerTileStore.f3758a.f3832a == null || a.f3798c || (b2.m5411b() != null && b2.m5411b() == c2))) {
                            dashServerTileStore.f3758a.f3832a.m6348a(a2, b2);
                        }
                        if (b != null && (b2.m5411b() == null || b2.m5411b() != c2)) {
                            b.m6353a(a2, b2, bArr);
                        }
                        if (b2.m5411b() == null || b2.m5411b() != c2) {
                            aaVar = b2;
                        } else {
                            c3 = dashServerTileStore.f3758a.f3832a.m6352c(a2.m5435a(dashServerTileStore.f3760c));
                            if (c3 == null || dashServerTileStore.f3758a.f3832a.m6350a(c3)) {
                                dashServerTileStore.m6208a(a, 2, b2);
                                j = i4;
                                i7 = i3;
                                i8 = i2;
                                i4 = i;
                                i5++;
                                i2 = i8;
                                i = i4;
                                i3 = i7;
                                i4 = j;
                                j = i6;
                            } else {
                                aaVar = MutableVectorTile.m5893b((aq) c3, (aq) b2);
                            }
                        }
                        if (a.f3798c) {
                            j = i2 + 1;
                            i7 = i;
                        } else {
                            i7 = i + 1;
                            j = i2;
                        }
                        if (c2 != null) {
                            try {
                                if (b2.m5411b() == dashServerTileStore.f3760c) {
                                    i8 = j;
                                    j = i4;
                                    i4 = i7;
                                    i7 = i3;
                                    i5++;
                                    i2 = i8;
                                    i = i4;
                                    i3 = i7;
                                    i4 = j;
                                    j = i6;
                                }
                            } catch (IOException e2) {
                                e = e2;
                                Util.m11552a(dashServerTileStore.getName() + ": Could not parse tile: " + a2, e);
                                dashServerTileStore.m6208a(a, 1, null);
                                i8 = j;
                                j = i4;
                                i4 = i7;
                                i7 = i3;
                                i5++;
                                i2 = i8;
                                i = i4;
                                i3 = i7;
                                i4 = j;
                                j = i6;
                            }
                        }
                        dashServerTileStore.m6208a(a, 0, aaVar);
                        i8 = j;
                        j = i4;
                        i4 = i7;
                        i7 = i3;
                        i5++;
                        i2 = i8;
                        i = i4;
                        i3 = i7;
                        i4 = j;
                        j = i6;
                    } else {
                        GmmSettings.m11527a();
                        if (GmmSettings.m11532f()) {
                            DiskTileCache b3;
                            Object obj;
                            c3 = null;
                            if (dashServerTileStore.f3758a.f3832a == null || !dashServerTileStore.f3758a.f3832a.m6351b(a2)) {
                                b3 = dashServerTileStore.f3758a.m6308b();
                                if (b3 != null && b3.m6351b(a2)) {
                                    c3 = b3.m6352c(a2);
                                }
                            } else {
                                c3 = dashServerTileStore.f3758a.f3832a.m6352c(a2);
                            }
                            if (c3 == null || c3.m5415d() == -1) {
                                obj = null;
                            } else {
                                c3.m5414c(dashServerTileStore.f3759b);
                                if (!(dashServerTileStore.f3758a.f3832a == null || a.f3798c)) {
                                    dashServerTileStore.f3758a.f3832a.m6348a(a2, c3);
                                }
                                b3 = dashServerTileStore.f3758a.m6308b();
                                if (b3 != null) {
                                    byte[] a3 = b3.m6358a(a2);
                                    if (a3 != null) {
                                        b3.m6353a(a2, c3, a3);
                                    }
                                }
                                dashServerTileStore.m6208a(a, 0, c3);
                                obj = 1;
                            }
                            if (obj != null) {
                                i7 = i3 + 1;
                                i8 = i2;
                                j = i4;
                                i4 = i;
                            } else {
                                dashServerTileStore.m6187a(a, a2);
                                j = i4 + 1;
                                i7 = i3;
                                i8 = i2;
                                i4 = i;
                            }
                        } else {
                            dashServerTileStore.m6187a(a, a2);
                            j = i4 + 1;
                            i7 = i3;
                            i8 = i2;
                            i4 = i;
                        }
                        i5++;
                        i2 = i8;
                        i = i4;
                        i3 = i7;
                        i4 = j;
                        j = i6;
                    }
                } catch (Throwable e3) {
                    e = e3;
                    i7 = i;
                    j = i2;
                    Util.m11552a(dashServerTileStore.getName() + ": Could not parse tile: " + a2, e);
                    dashServerTileStore.m6208a(a, 1, null);
                    i8 = j;
                    j = i4;
                    i4 = i7;
                    i7 = i3;
                    i5++;
                    i2 = i8;
                    i = i4;
                    i3 = i7;
                    i4 = j;
                    j = i6;
                }
            }
            dashServerTileStore.m6203k();
            GmmSettings.m11527a();
            if (GmmSettings.m11532f()) {
                c = (dashServerTileStore.m6220h().equals(ai.f3423a) || dashServerTileStore.m6220h().equals(ai.f3424b) || dashServerTileStore.m6220h().equals(ai.f3437o)) ? 1 : null;
                if (c != null && dashServerTileStore.f3763g.m11376l() % 100 == 8) {
                    String str = "t=" + k;
                    String str2 = "f=" + i;
                    String str3 = "p=" + i2;
                    String str4 = "r=" + i3;
                    String str5 = "n=" + i4;
                    String str6 = "v=" + j;
                    String str7 = "d=" + aq.m5628q();
                    UserEventReporter.m11502a(109, "b", UserEventReporter.m11500a(new String[]{str, str2, str3, str4, str5, str6, str7}));
                }
            }
        }
    }

    static /* synthetic */ void m6197c(DashServerTileStore dashServerTileStore) {
        LinkedList linkedList = new LinkedList(dashServerTileStore.f3767k);
        dashServerTileStore.f3767k.clear();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            DashServerTileStore dashServerTileStore2 = (DashServerTileStore) it.next();
            for (int i = 0; i < dashServerTileStore2.m6158k(); i++) {
                dashServerTileStore.f3769m.remove(dashServerTileStore2.m6151a(i).f3796a);
                dashServerTileStore.f3771o--;
                dashServerTileStore.m6194b(dashServerTileStore2.m6151a(i));
            }
        }
    }

    protected DashServerTileStore(DataRequestDispatcherInterface dataRequestDispatcherInterface, ai aiVar, String str, TileCache tileCache, DiskTileCache diskTileCache, int i, boolean z, int i2, Locale locale, File file) {
        super(str);
        this.f3762f = new ReentrantLock();
        this.f3767k = new LinkedList();
        this.f3769m = new HashMap();
        this.f3759b = Config.m11320a().m11334h();
        this.f3774r = false;
        this.f3776t = new ArrayList();
        this.f3777u = false;
        this.f3778v = new DashServerTileStore(this);
        this.f3760c = aiVar;
        this.f3758a = new TileStoreCache(getName(), tileCache, diskTileCache, z, locale, file);
        this.f3770n = i;
        this.f3763g = dataRequestDispatcherInterface;
        this.f3766j = m6219g();
        this.f3766j.f3745c = this;
        this.f3768l = new DashServerTileStore(this, i2);
    }

    public void m6207a(ac acVar, TileCallback tileCallback) {
        m6186a(new DashServerTileStore(this.f3760c, acVar, tileCallback));
    }

    public final void m6213b(ac acVar, TileCallback tileCallback) {
        m6186a(new DashServerTileStore(this.f3760c, acVar, tileCallback, true, false));
    }

    public aa m6204a(ac acVar, boolean z) {
        Object dashServerTileStore = new DashServerTileStore();
        DashServerTileStore dashServerTileStore2 = (DashServerTileStore) m6184a(new DashServerTileStore(this.f3760c, acVar, dashServerTileStore), z, false).second;
        if (dashServerTileStore2 != null) {
            this.f3764h.sendMessage(this.f3764h.obtainMessage(1, dashServerTileStore2));
        }
        return dashServerTileStore.f3795a;
    }

    private void m6186a(DashServerTileStore dashServerTileStore) {
        if (this.f3761e != null) {
            this.f3761e.m6243a();
        }
        this.f3764h.sendMessage(this.f3764h.obtainMessage(1, dashServerTileStore));
    }

    public final void m6215c() {
        this.f3758a.m6309c();
        m6201i();
    }

    public final void m6209a(TileStore tileStore) {
        synchronized (this.f3776t) {
            this.f3776t.add(new WeakReference(tileStore));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m6201i() {
        /*
        r4 = this;
        r2 = r4.f3776t;
        monitor-enter(r2);
        r1 = 0;
    L_0x0004:
        r0 = r4.f3776t;	 Catch:{ all -> 0x002b }
        r0 = r0.size();	 Catch:{ all -> 0x002b }
        if (r1 >= r0) goto L_0x002e;
    L_0x000c:
        r0 = r4.f3776t;	 Catch:{ all -> 0x002b }
        r0 = r0.get(r1);	 Catch:{ all -> 0x002b }
        r0 = (java.lang.ref.WeakReference) r0;	 Catch:{ all -> 0x002b }
        r0 = r0.get();	 Catch:{ all -> 0x002b }
        r0 = (com.google.android.m4b.maps.ap.TileStore.TileStore) r0;	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x0023;
    L_0x001c:
        r0.m5319a();	 Catch:{ all -> 0x002b }
        r0 = r1;
    L_0x0020:
        r1 = r0 + 1;
        goto L_0x0004;
    L_0x0023:
        r3 = r4.f3776t;	 Catch:{ all -> 0x002b }
        r0 = r1 + -1;
        r3.remove(r1);	 Catch:{ all -> 0x002b }
        goto L_0x0020;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x002e:
        monitor-exit(r2);	 Catch:{ all -> 0x002b }
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ap.b.i():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m6216d() {
        /*
        r1 = this;
        r0 = r1.f3763g;
        r0.m11373a(r1);
        r1.start();
        monitor-enter(r1);	 Catch:{ InterruptedException -> 0x0014 }
    L_0x0009:
        r0 = r1.f3764h;	 Catch:{ all -> 0x0011 }
        if (r0 != 0) goto L_0x001d;
    L_0x000d:
        r1.wait();	 Catch:{ all -> 0x0011 }
        goto L_0x0009;
    L_0x0011:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ InterruptedException -> 0x0014 }
        throw r0;	 Catch:{ InterruptedException -> 0x0014 }
    L_0x0014:
        r0 = move-exception;
        r0 = java.lang.Thread.currentThread();
        r0.interrupt();
    L_0x001c:
        return;
    L_0x001d:
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ap.b.d():void");
    }

    public final void m6217e() {
        TileStoreCache tileStoreCache = this.f3758a;
        if (tileStoreCache.f3832a != null) {
            tileStoreCache.f3832a.m6349a();
        }
    }

    public final void m6210a(DataRequest dataRequest) {
        if ((dataRequest instanceof DashServerTileStore) && ((DashServerTileStore) dataRequest).f3745c == this) {
            this.f3764h.sendMessage(this.f3764h.obtainMessage(3, dataRequest));
        }
    }

    public final void m6206a(int i, boolean z, String str) {
        Util.m11550a(getName(), "Network Error! " + i + " : " + z + (str != null ? " : " + str : Trace.NULL));
        this.f3764h.sendMessage(this.f3764h.obtainMessage(4));
    }

    public final void m6205a() {
    }

    public final void m6212b() {
    }

    public final void m6214b(DataRequest dataRequest) {
    }

    public void m6218f() {
        try {
            Process.setThreadPriority(VectorGlobalState.m7288d());
        } catch (SecurityException e) {
            Util.m11550a(getName(), "Could not set thread priority: " + e);
        }
        Looper.prepare();
        Looper.myLooper();
        this.f3764h = new DashServerTileStore(this);
        synchronized (this) {
            notifyAll();
        }
        this.f3758a.m6306a();
        Looper.loop();
    }

    private void m6194b(DashServerTileStore dashServerTileStore) {
        boolean z = false;
        ac a = dashServerTileStore.f3796a.m5435a(this.f3760c);
        if (TileStore.f3757d.equals(a)) {
            m6208a(dashServerTileStore, 3, null);
            return;
        }
        boolean z2;
        DashServerTileStore dashServerTileStore2;
        DashServerTileStore dashServerTileStore3 = (DashServerTileStore) this.f3769m.get(a);
        ai c = DashServerTileStore.m6196c(dashServerTileStore);
        boolean z3 = c != null;
        Pair a2;
        if (dashServerTileStore.f3801f) {
            z2 = dashServerTileStore.f3800e;
            a2 = m6184a(dashServerTileStore, true, false);
            z2 = ((Boolean) a2.first).booleanValue();
            dashServerTileStore2 = (DashServerTileStore) a2.second;
        } else {
            if (!dashServerTileStore.f3800e && (dashServerTileStore3 == null || (!dashServerTileStore3.m6251a() && dashServerTileStore3.f3802g))) {
                a2 = m6184a(dashServerTileStore, true, z3);
                z2 = ((Boolean) a2.first).booleanValue();
                if (z2) {
                    dashServerTileStore2 = (DashServerTileStore) a2.second;
                } else {
                    z = z2;
                }
            }
            z2 = z;
            dashServerTileStore2 = dashServerTileStore;
        }
        if (!z2) {
            m6208a(dashServerTileStore, 3, null);
        }
        if (dashServerTileStore2 == null) {
            return;
        }
        if (dashServerTileStore3 != null) {
            if (!dashServerTileStore2.f3802g) {
                dashServerTileStore3.m6250a(dashServerTileStore2);
            }
        } else if (!this.f3774r || this.f3769m.isEmpty() || dashServerTileStore2.f3798c) {
            this.f3769m.put(a, dashServerTileStore2);
            if (!this.f3766j.m6154a(dashServerTileStore2) || (z3 && !DashServerTileStore.m6150a(this.f3766j, 2))) {
                m6202j();
            }
            this.f3766j.m6153a(Util.m11545a(m6220h(), a), dashServerTileStore2);
            if (z3) {
                ac a3 = dashServerTileStore.f3796a.m5435a(c);
                dashServerTileStore3 = new DashServerTileStore(c, a3, dashServerTileStore2.f3797b, dashServerTileStore2.f3799d, dashServerTileStore2.f3800e, dashServerTileStore2.f3801f, dashServerTileStore2.f3804i, dashServerTileStore2.f3802g);
                this.f3766j.m6153a(Util.m11545a(dashServerTileStore3.f3803h, a3), dashServerTileStore3);
            }
            this.f3771o++;
            if (this.f3766j.m6159l() || dashServerTileStore2.f3800e) {
                m6202j();
            } else if (!this.f3765i) {
                this.f3764h.sendMessageDelayed(this.f3764h.obtainMessage(2), 50);
                this.f3765i = true;
            }
        } else {
            dashServerTileStore3 = (DashServerTileStore) this.f3768l.m6235b((Object) a);
            if (dashServerTileStore3 != null) {
                dashServerTileStore3.m6250a(dashServerTileStore2);
            } else {
                this.f3768l.m6239c(a, dashServerTileStore2);
            }
        }
    }

    private void m6202j() {
        if (this.f3766j.m6158k() > 0) {
            EventLog.m4911b(new EventLog.EventLog("addRequest", this.f3766j));
            this.f3763g.m11374c(this.f3766j);
            this.f3767k.add(this.f3766j);
            this.f3766j = m6219g();
            this.f3766j.f3745c = this;
        }
    }

    private void m6187a(DashServerTileStore dashServerTileStore, ac acVar) {
        if (this.f3758a.f3832a != null) {
            this.f3758a.f3832a.a_(acVar);
        }
        m6208a(dashServerTileStore, 2, null);
    }

    private Pair<Boolean, DashServerTileStore> m6184a(DashServerTileStore dashServerTileStore, boolean z, boolean z2) {
        boolean z3 = true;
        Object obj = null;
        ac a = dashServerTileStore.f3796a.m5435a(m6220h());
        if (this.f3758a.f3832a != null) {
            aa c = this.f3758a.f3832a.m6352c(a);
            if (!(c == null || c.m5410a(this.f3759b))) {
                boolean z4;
                if (this.f3758a.f3832a.m6350a(c)) {
                    m6208a(dashServerTileStore, 2, null);
                    z4 = true;
                } else {
                    if (!(dashServerTileStore.f3798c || this.f3775s == null)) {
                        this.f3775s.m11540a();
                    }
                    DashServerTileStore a2 = m6185a(c, dashServerTileStore, z2);
                    DashServerTileStore dashServerTileStore2;
                    if (z2) {
                        dashServerTileStore2 = a2;
                        z4 = false;
                    } else {
                        aa aaVar;
                        if (!dashServerTileStore.f3798c) {
                            aaVar = c;
                        }
                        m6208a(dashServerTileStore, 0, aaVar);
                        dashServerTileStore2 = a2;
                        z4 = true;
                    }
                }
                return Pair.create(Boolean.valueOf(z4), obj);
            }
        }
        if (z) {
            DiskTileCache b = this.f3758a.m6308b();
            if (b != null) {
                if (!dashServerTileStore.f3798c) {
                    aa c2 = b.m6352c(a);
                    if (!(c2 == null || c2.m5410a(this.f3759b))) {
                        if (b.m6350a(c2)) {
                            if (!(dashServerTileStore.f3798c || this.f3775s == null)) {
                                this.f3775s.m11542c();
                            }
                            m6187a(dashServerTileStore, a);
                        } else {
                            if (!(dashServerTileStore.f3798c || this.f3775s == null)) {
                                this.f3775s.m11541b();
                            }
                            if (this.f3758a.f3832a != null) {
                                this.f3758a.f3832a.m6348a(a, c2);
                            }
                            obj = m6185a(c2, dashServerTileStore, z2);
                            if (z2) {
                                z3 = false;
                            } else {
                                m6208a(dashServerTileStore, 0, c2);
                            }
                        }
                        return Pair.create(Boolean.valueOf(z3), obj);
                    }
                } else if (b.m6351b(a)) {
                    m6208a(dashServerTileStore, 0, null);
                    return Pair.create(Boolean.valueOf(true), null);
                }
            }
        }
        if (!(dashServerTileStore.f3798c || this.f3775s == null)) {
            this.f3775s.m11542c();
        }
        dashServerTileStore.f3804i = -1;
        return Pair.create(Boolean.valueOf(false), null);
    }

    private DashServerTileStore m6185a(aa aaVar, DashServerTileStore dashServerTileStore, boolean z) {
        int d = this.f3758a.m6310d();
        int i = -1;
        Object obj = null;
        DashServerTileStore dashServerTileStore2 = null;
        if (d == -1 || d == aaVar.m5413c()) {
            GmmSettings.m11527a();
            if (GmmSettings.m11532f() && !dashServerTileStore.f3798c && (aaVar.m5412b(this.f3759b) || z)) {
                i = aaVar.m5415d();
                obj = 1;
            }
        } else {
            obj = 1;
        }
        if (obj != null) {
            dashServerTileStore2 = new DashServerTileStore(this.f3760c, dashServerTileStore.f3796a.m5435a(this.f3760c), this.f3778v, DashServerMapTileStore.NORMAL, true, false, i, true);
            GmmSettings.m11527a();
            if (GmmSettings.m11532f()) {
                d = aaVar.m5409a().m5439b();
                if (this.f3763g.m11376l() % 100 == 8) {
                    String str = "v=" + (i != -1 ? 1 : 0);
                    String str2 = "d=" + aq.m5628q();
                    String str3 = "z=" + d;
                    UserEventReporter.m11502a(109, "u", UserEventReporter.m11500a(new String[]{str, str2, str3}));
                }
            }
        }
        return dashServerTileStore2;
    }

    private void m6203k() {
        try {
            this.f3762f.lock();
            if (this.f3758a.f3833b != null && this.f3758a.f3833b.m6359b() && (this.f3761e == null || this.f3761e.m6244b())) {
                this.f3761e = new DashServerTileStore(this);
            }
            this.f3762f.unlock();
        } catch (Throwable th) {
            this.f3762f.unlock();
        }
    }

    final void m6208a(DashServerTileStore dashServerTileStore, int i, aa aaVar) {
        boolean z = false;
        DashServerTileStore dashServerTileStore2 = dashServerTileStore;
        while (dashServerTileStore2 != null) {
            if (i != 0 || !RefCountUtil.m6293a(dashServerTileStore2.f3799d)) {
                dashServerTileStore2.f3797b.m5267a(dashServerTileStore2.f3796a, i, aaVar);
            } else if (aaVar.m5416e()) {
                this.f3758a.m6308b().m6354a(dashServerTileStore.f3796a, dashServerTileStore2.f3797b, RefCountUtil.m6291a(dashServerTileStore2.f3799d, true));
                z = true;
            } else {
                dashServerTileStore2.f3797b.m5267a(dashServerTileStore2.f3796a, 4, aaVar);
            }
            dashServerTileStore2 = dashServerTileStore2.f3806k;
        }
        if (z) {
            this.f3777u = true;
            m6203k();
        }
    }

    public final void m6211a(Stats stats) {
        this.f3775s = stats;
    }

    public ai m6220h() {
        return this.f3760c;
    }

    private static ai m6196c(DashServerTileStore dashServerTileStore) {
        for (TileParameters tileParameters : dashServerTileStore.f3796a.m5447j().m5472a()) {
            if (tileParameters.m5455a() != null) {
                return tileParameters.m5455a();
            }
        }
        return null;
    }
}
