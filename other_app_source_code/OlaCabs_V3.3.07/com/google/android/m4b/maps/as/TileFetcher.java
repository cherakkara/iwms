package com.google.android.m4b.maps.as;

import android.os.SystemClock;
import android.util.Pair;
import com.google.android.m4b.maps.ag.TileCoordGenerator;
import com.google.android.m4b.maps.an.MutableVectorTile;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.an.bg;
import com.google.android.m4b.maps.ap.TileStore;
import com.google.android.m4b.maps.ap.TileStoreFactory;
import com.google.android.m4b.maps.ap.VectorModifierTileStore;
import com.google.android.m4b.maps.ap.VectorTileStore;
import com.google.android.m4b.maps.aq.TileCallback;
import com.google.android.m4b.maps.au.Bitmask;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.aw.GLTileCacheManager;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLImageTile;
import com.google.android.m4b.maps.ba.GLTile;
import com.google.android.m4b.maps.ba.GLVectorTile;
import com.google.android.m4b.maps.ba.MockGLTile;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p058v.Util;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.as.c */
public final class TileFetcher {
    private static final GLTile f4028a;
    private GLTileCacheManager f4029b;
    private final TileStore f4030c;
    private volatile GLTileCacheManager.GLTileCacheManager f4031d;
    private final Bitmask f4032e;
    private final List<VectorModifierTileStore> f4033f;
    private ah f4034g;
    private final LRUCache<ac, ac> f4035h;
    private int f4036i;
    private int f4037j;
    private final TileStore.TileStore f4038k;
    private AtomicInteger f4039l;
    private final Set<GLTileCacheManager.GLTileCacheManager> f4040m;
    private final LinkedList<TileFetcher> f4041n;
    private Map<ac, Long> f4042o;
    private Map<ac, Pair<TileFetchRequest, Long>> f4043p;
    private volatile int f4044q;
    private final FetchRequestProvider f4045r;
    private TileFetchRequest f4046s;
    private final TileCallback f4047t;
    private final TileCallback f4048u;
    private final TileFetcher f4049v;
    private volatile long f4050w;
    private Clock f4051x;
    private volatile GLState f4052y;

    /* renamed from: com.google.android.m4b.maps.as.c.1 */
    class TileFetcher implements TileStore.TileStore {
        private /* synthetic */ TileFetcher f4024a;

        TileFetcher(TileFetcher tileFetcher) {
            this.f4024a = tileFetcher;
        }

        public final void m6532a() {
            synchronized (this.f4024a.f4040m) {
                this.f4024a.f4040m.add(this.f4024a.f4031d);
            }
        }

        public final void m6533a(aa aaVar) {
            if (aaVar != null) {
                TileFetcher.m6546a(this.f4024a, aaVar.m5409a(), this.f4024a.m6541a(aaVar.m5409a(), 0, aaVar), 0);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.as.c.a */
    class TileFetcher implements TileCallback {
        private /* synthetic */ TileFetcher f4025a;

        private TileFetcher(TileFetcher tileFetcher) {
            this.f4025a = tileFetcher;
        }

        public final void m6534a(ac acVar, int i, aa aaVar) {
            if (i != 3) {
                GLTile a = this.f4025a.m6541a(acVar, i, aaVar);
                if (a != null) {
                    Long l = (Long) this.f4025a.f4042o.remove(acVar);
                    if (l == null) {
                        Util.m11550a("TileFetcher", "Received an unknown tile " + acVar);
                        return;
                    }
                    TileFetcher.m6546a(this.f4025a, acVar, a, SystemClock.elapsedRealtime() - l.longValue());
                }
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.as.c.b */
    class TileFetcher implements TileCallback {
        private /* synthetic */ TileFetcher f4026a;

        private TileFetcher(TileFetcher tileFetcher) {
            this.f4026a = tileFetcher;
        }

        private GLTile m6535a(TileFetchRequest tileFetchRequest) {
            GLTile a = TileFetcher.m6542a(this.f4026a, tileFetchRequest.f4021a);
            if (a != null && a.m7844a(this.f4026a.f4051x)) {
                this.f4026a.m6548a(tileFetchRequest.f4021a, a);
                return a;
            } else if (a != null) {
                return a;
            } else {
                Pair pair = (Pair) this.f4026a.f4043p.get(tileFetchRequest.f4021a);
                if (pair != null) {
                    if (tileFetchRequest.f4022b) {
                        this.f4026a.f4043p.remove(tileFetchRequest.f4021a);
                        this.f4026a.f4044q = this.f4026a.f4044q - 1;
                    } else {
                        this.f4026a.f4043p.put(tileFetchRequest.f4021a, Pair.create(tileFetchRequest, pair.second));
                    }
                    return TileFetcher.f4028a;
                }
                synchronized (this.f4026a.f4045r) {
                    if (this.f4026a.f4045r.m6529a(tileFetchRequest)) {
                        this.f4026a.f4043p.put(tileFetchRequest.f4021a, Pair.create(tileFetchRequest, Long.valueOf(SystemClock.elapsedRealtime())));
                        this.f4026a.f4044q = this.f4026a.f4044q + 1;
                        this.f4026a.m6545a(tileFetchRequest.f4021a, tileFetchRequest.f4022b, this.f4026a.f4048u);
                    }
                }
                return a;
            }
        }

        private void m6536a(TileFetchRequest tileFetchRequest, boolean z) {
            while (true) {
                tileFetchRequest = this.f4026a.f4045r.m6526a(tileFetchRequest, z);
                if (tileFetchRequest != null) {
                    GLTile a = m6535a(tileFetchRequest);
                    if (a != null) {
                        z = a != TileFetcher.f4028a;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        public final void m6537a(ac acVar, int i, aa aaVar) {
            boolean z = true;
            if (acVar == TileStore.f3757d) {
                TileFetchRequest j;
                synchronized (this.f4026a.f4045r) {
                    j = this.f4026a.f4046s;
                    this.f4026a.f4046s = null;
                }
                m6536a(j, true);
                return;
            }
            Pair pair = (Pair) this.f4026a.f4043p.get(acVar);
            if (pair == null) {
                Util.m11550a("TileFetcher", "Received an unknown tile " + acVar);
                return;
            }
            GLTile gLTile;
            boolean z2;
            GLTile gLTile2;
            boolean z3;
            boolean z4;
            if (!this.f4026a.f4045r.m6529a((TileFetchRequest) pair.first)) {
                gLTile = null;
                z2 = false;
            } else if (i == 3) {
                z2 = true;
                z = ((TileFetchRequest) pair.first).f4022b;
                gLTile = null;
            } else {
                gLTile = this.f4026a.m6541a(acVar, i, aaVar);
                if (gLTile == null || gLTile == TileFetcher.f4028a) {
                    z2 = true;
                } else {
                    gLTile2 = gLTile;
                    z3 = true;
                    z4 = true;
                    if (z3) {
                        this.f4026a.f4043p.remove(acVar);
                        this.f4026a.f4044q = this.f4026a.f4044q - 1;
                    }
                    if (z4) {
                        m6536a((TileFetchRequest) pair.first, z);
                    }
                    if (gLTile2 != null) {
                        TileFetcher.m6546a(this.f4026a, acVar, gLTile2, SystemClock.elapsedRealtime() - ((Long) pair.second).longValue());
                    }
                }
            }
            z3 = z;
            z = false;
            boolean z5 = z2;
            gLTile2 = gLTile;
            z4 = z5;
            if (z3) {
                this.f4026a.f4043p.remove(acVar);
                this.f4026a.f4044q = this.f4026a.f4044q - 1;
            }
            if (z4) {
                m6536a((TileFetchRequest) pair.first, z);
            }
            if (gLTile2 != null) {
                TileFetcher.m6546a(this.f4026a, acVar, gLTile2, SystemClock.elapsedRealtime() - ((Long) pair.second).longValue());
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.as.c.c */
    class TileFetcher implements TileCallback {
        private /* synthetic */ TileFetcher f4027a;

        private TileFetcher(TileFetcher tileFetcher) {
            this.f4027a = tileFetcher;
        }

        public final void m6538a(ac acVar, int i, aa aaVar) {
            if (i != 3) {
                GLTile b = this.f4027a.m6551b(acVar, i, aaVar);
                if (b != null) {
                    TileFetcher.m6546a(this.f4027a, aaVar.m5409a(), b, 0);
                }
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.as.c.d */
    public interface TileFetcher {
        void m6539a(ac acVar, GLTile gLTile, boolean z);
    }

    static /* synthetic */ GLTile m6542a(TileFetcher tileFetcher, ac acVar) {
        GLTile a = tileFetcher.f4029b.m7388a(tileFetcher.f4052y, tileFetcher.f4031d, acVar, true);
        return (a == f4028a || a == null || !a.m7847b(tileFetcher.f4051x)) ? a : null;
    }

    static /* synthetic */ void m6546a(TileFetcher tileFetcher, ac acVar, GLTile gLTile, long j) {
        if (gLTile != null) {
            if (gLTile == f4028a) {
                gLTile = null;
            }
            boolean z = (tileFetcher.f4042o.size() == 0 && tileFetcher.f4044q == 0 && (tileFetcher.f4045r == null || tileFetcher.f4045r.m6531c())) ? false : true;
            synchronized (tileFetcher.f4041n) {
                if (tileFetcher.f4041n.size() == 1) {
                    ((TileFetcher) tileFetcher.f4041n.get(0)).m6539a(acVar, gLTile, z);
                } else {
                    ArrayList arrayList = new ArrayList(tileFetcher.f4041n.size());
                    arrayList.addAll(tileFetcher.f4041n);
                    for (int i = 0; i < arrayList.size(); i++) {
                        ((TileFetcher) arrayList.get(i)).m6539a(acVar, gLTile, z);
                    }
                }
            }
        }
    }

    static {
        f4028a = new MockGLTile();
    }

    public TileFetcher(ai aiVar, FetchRequestProvider fetchRequestProvider) {
        this(aiVar, new GLTileCacheManager.GLTileCacheManager(aiVar, null), fetchRequestProvider, Bitmask.f4072a);
    }

    public TileFetcher(ai aiVar, GLTileCacheManager.GLTileCacheManager gLTileCacheManager, FetchRequestProvider fetchRequestProvider, Bitmask bitmask) {
        this.f4035h = new LRUCache(HttpStatus.SC_MULTIPLE_CHOICES);
        this.f4036i = 0;
        this.f4037j = 0;
        this.f4039l = new AtomicInteger(0);
        this.f4040m = new TreeSet();
        this.f4042o = Collections.synchronizedMap(au.m2807a());
        this.f4043p = au.m2807a();
        this.f4044q = 0;
        this.f4047t = new TileFetcher();
        this.f4048u = new TileFetcher();
        this.f4049v = new TileFetcher();
        this.f4051x = Config.m11320a().m11334h();
        this.f4031d = gLTileCacheManager;
        this.f4029b = null;
        this.f4032e = bitmask;
        this.f4033f = new ArrayList();
        this.f4041n = new LinkedList();
        if (TileStoreFactory.m6312a(aiVar)) {
            this.f4030c = TileStoreFactory.m6313b(aiVar);
            this.f4038k = new TileFetcher(this);
            this.f4030c.m6173a(this.f4038k);
        } else {
            this.f4030c = null;
            this.f4038k = null;
        }
        this.f4045r = fetchRequestProvider;
    }

    public final GLTile m6563a(ac acVar) {
        GLTile a = this.f4029b.m7388a(this.f4052y, this.f4031d, acVar, false);
        if (a == f4028a) {
            return null;
        }
        if (a != null && !a.m7847b(this.f4051x)) {
            return a;
        }
        a = this.f4029b.m7388a(this.f4052y, this.f4031d, acVar.m5431a(), false);
        if (a == f4028a) {
            return null;
        }
        if (a != null && !a.m7847b(this.f4051x)) {
            return a;
        }
        this.f4039l.incrementAndGet();
        return null;
    }

    private boolean m6548a(ac acVar, GLTile gLTile) {
        if (gLTile != null && !gLTile.m7844a(this.f4051x)) {
            return false;
        }
        if (this.f4042o.put(acVar, Long.valueOf(SystemClock.elapsedRealtime())) == null) {
            m6545a(acVar, false, this.f4047t);
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m6545a(com.google.android.m4b.maps.an.ac r6, boolean r7, com.google.android.m4b.maps.aq.TileCallback r8) {
        /*
        r5 = this;
        r1 = r5.f4035h;
        monitor-enter(r1);
        r0 = r5.f4035h;	 Catch:{ all -> 0x004f }
        r0.m6238c(r6);	 Catch:{ all -> 0x004f }
        monitor-exit(r1);	 Catch:{ all -> 0x004f }
        r0 = r5.f4030c;
        if (r0 == 0) goto L_0x0014;
    L_0x000d:
        if (r7 == 0) goto L_0x0052;
    L_0x000f:
        r0 = r5.f4030c;
        r0.m6174b(r6, r8);
    L_0x0014:
        r0 = r5.f4033f;
        r0 = com.google.android.m4b.maps.ap.VectorTileStore.d;
        if (r6 == r0) goto L_0x0059;
    L_0x001a:
        r2 = r5.f4033f;
        monitor-enter(r2);
        r0 = 0;
        r1 = r5.f4030c;	 Catch:{ all -> 0x004c }
        if (r1 == 0) goto L_0x005a;
    L_0x0022:
        r1 = r5.f4033f;	 Catch:{ all -> 0x004c }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x004c }
        if (r1 != 0) goto L_0x005a;
    L_0x002a:
        r0 = r5.f4030c;	 Catch:{ all -> 0x004c }
        r1 = 1;
        r0 = r0.m6171a(r6, r1);	 Catch:{ all -> 0x004c }
        r0 = (com.google.android.m4b.maps.an.aq) r0;	 Catch:{ all -> 0x004c }
        r1 = r0;
    L_0x0034:
        r0 = r5.f4033f;	 Catch:{ all -> 0x004c }
        r3 = r0.iterator();	 Catch:{ all -> 0x004c }
    L_0x003a:
        r0 = r3.hasNext();	 Catch:{ all -> 0x004c }
        if (r0 == 0) goto L_0x0058;
    L_0x0040:
        r0 = r3.next();	 Catch:{ all -> 0x004c }
        r0 = (com.google.android.m4b.maps.ap.VectorModifierTileStore) r0;	 Catch:{ all -> 0x004c }
        r4 = r5.f4049v;	 Catch:{ all -> 0x004c }
        r0.m6317a(r6, r1, r4);	 Catch:{ all -> 0x004c }
        goto L_0x003a;
    L_0x004c:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x004f:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0052:
        r0 = r5.f4030c;
        r0.m6172a(r6, r8);
        goto L_0x0014;
    L_0x0058:
        monitor-exit(r2);	 Catch:{ all -> 0x004c }
    L_0x0059:
        return;
    L_0x005a:
        r1 = r0;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.as.c.a(com.google.android.m4b.maps.an.ac, boolean, com.google.android.m4b.maps.aq.d):void");
    }

    public final ai m6562a() {
        return this.f4030c.m6178h();
    }

    public final int m6572b() {
        return this.f4039l.get();
    }

    public final void m6574c() {
        this.f4029b.m7400d(this.f4052y, this.f4031d);
    }

    public final void m6575d() {
        if (DeviceSpecificInfo.f8006a) {
            this.f4029b.m7399c(this.f4052y, this.f4031d);
        }
    }

    public final void m6565a(TileCoordGenerator tileCoordGenerator, Point point, List<ac> list, Set<ac> set, Set<ac> set2, boolean z) {
        this.f4029b.m7400d(this.f4052y, this.f4031d);
        synchronized (this.f4045r) {
            this.f4045r.m6528a(tileCoordGenerator, point, list, set, null, z);
            TileFetchRequest b = this.f4045r.m6530b();
            if (this.f4046s == null) {
                m6545a(b.f4021a, b.f4022b, this.f4048u);
            }
            this.f4046s = b;
        }
    }

    public final void m6576e() {
        this.f4045r.m6527a();
    }

    public final void m6570a(List<GLTile> list) {
        List c = ar.m2527c(list.size());
        for (GLTile gLTile : list) {
            if (gLTile != f4028a) {
                c.add(gLTile.m7845b());
            }
        }
        this.f4029b.m7392a(this.f4052y, this.f4031d, c);
    }

    public final void m6573b(List<GLTile> list) {
        List c = ar.m2527c(list.size());
        for (GLTile gLTile : list) {
            if (gLTile != f4028a) {
                c.add(gLTile.m7845b());
            }
        }
        this.f4029b.m7397b(this.f4052y, this.f4031d, c);
    }

    public final void m6569a(GLState gLState) {
        Preconditions.m1818a((Object) gLState, (Object) "GLState should not be null");
        this.f4052y = gLState;
        GLTileCacheManager.m7383a(new Clock());
        this.f4029b = GLTileCacheManager.m7380a();
    }

    public final void m6571a(boolean z) {
        if (this.f4029b != null) {
            this.f4029b.m7393a(z);
        }
    }

    public final void m6577f() {
        if (this.f4029b != null) {
            this.f4029b.m7394b();
        }
    }

    public final boolean m6578g() {
        if (this.f4040m.isEmpty()) {
            return false;
        }
        synchronized (this.f4040m) {
            for (GLTileCacheManager.GLTileCacheManager gLTileCacheManager : this.f4040m) {
                if (this.f4029b != null) {
                    this.f4029b.m7390a(this.f4052y, gLTileCacheManager);
                }
            }
            this.f4040m.clear();
        }
        return true;
    }

    public final void m6579h() {
        if (this.f4029b != null) {
            this.f4029b.m7396b(this.f4052y, this.f4031d);
            m6578g();
        }
    }

    public final void m6567a(ai aiVar) {
        if (!(this.f4030c instanceof VectorTileStore)) {
            throw new UnsupportedOperationException("Modifiers not supported on store '" + this.f4030c.m6178h() + "'");
        } else if (!aiVar.f3447A) {
            throw new IllegalArgumentException("Only modifiers may be added, not " + aiVar);
        } else if (TileStoreFactory.m6312a(aiVar)) {
            TileStore b = TileStoreFactory.m6313b(aiVar);
            if (b instanceof VectorModifierTileStore) {
                synchronized (this.f4033f) {
                    if (this.f4033f.contains(b)) {
                        return;
                    }
                    b.m6173a(this.f4038k);
                    this.f4033f.add((VectorModifierTileStore) b);
                    Set treeSet = new TreeSet();
                    synchronized (this.f4033f) {
                        for (VectorModifierTileStore h : this.f4033f) {
                            treeSet.add(h.m6220h());
                        }
                    }
                    synchronized (this.f4040m) {
                        this.f4040m.add(this.f4031d);
                    }
                    this.f4031d = new GLTileCacheManager.GLTileCacheManager(this.f4030c.m6178h(), treeSet, this.f4032e);
                    return;
                }
            }
            throw new UnsupportedOperationException("Modifier store '" + b.m6178h() + "' must be a vector modifier store");
        } else {
            throw new IllegalArgumentException("Unknown tile store " + aiVar);
        }
    }

    private GLTile m6541a(ac acVar, int i, aa aaVar) {
        synchronized (this.f4035h) {
            this.f4035h.m6239c(acVar, acVar);
        }
        if (!this.f4034g.m5478a(acVar.m5447j())) {
            return null;
        }
        GLTile a;
        List list = this.f4033f;
        if (!(this.f4033f.isEmpty() || acVar.m5447j() == null || !(aaVar instanceof aq))) {
            aq aqVar;
            synchronized (this.f4033f) {
                aqVar = aaVar;
                for (VectorModifierTileStore a2 : this.f4033f) {
                    aa a3 = a2.m6204a(acVar, true);
                    if (a3 != null) {
                        aqVar = MutableVectorTile.m5891a(aqVar, (aq) a3);
                    } else {
                        aq aqVar2 = (aq) aaVar;
                    }
                }
            }
            aaVar = aqVar;
        }
        GLState gLState = this.f4052y;
        if (gLState != null && i == 0) {
            if (aaVar instanceof aq) {
                a = GLVectorTile.m8126a(aaVar, this.f4032e, gLState);
            } else if (aaVar instanceof bg) {
                a = GLImageTile.m7873a(aaVar, gLState);
            }
            if (a == null) {
                if (i == 2) {
                    return null;
                }
                a = f4028a;
            }
            if (this.f4029b != null) {
                return a;
            }
            this.f4029b.m7391a(gLState, this.f4031d, acVar, a);
            return a;
        }
        a = null;
        if (a == null) {
            if (i == 2) {
                return null;
            }
            a = f4028a;
        }
        if (this.f4029b != null) {
            return a;
        }
        this.f4029b.m7391a(gLState, this.f4031d, acVar, a);
        return a;
    }

    private GLTile m6551b(ac acVar, int i, aa aaVar) {
        if (aaVar == null) {
            return null;
        }
        if (((aq) aaVar).m5648o() == 0) {
            if (((aq) aaVar).m5644k() < 0) {
                return null;
            }
            GLTile a = this.f4029b.m7388a(this.f4052y, this.f4031d, acVar, false);
            if (!(a == null || a == f4028a)) {
                ((GLVectorTile) a).m8140b(((aq) aaVar).m5644k());
                return null;
            }
        }
        synchronized (this.f4035h) {
            if (this.f4035h.m6230a((Object) acVar) == null) {
                this.f4037j++;
                return null;
            }
            this.f4036i++;
            aa a2 = this.f4030c.m6171a(acVar, true);
            if (a2 != null) {
                return m6541a(acVar, i, a2);
            }
            return null;
        }
    }

    public final void m6568a(TileFetcher tileFetcher) {
        synchronized (this.f4041n) {
            this.f4041n.remove(tileFetcher);
            this.f4041n.add(tileFetcher);
        }
    }

    public final Clock m6580i() {
        return this.f4051x;
    }

    public final void m6564a(long j) {
        this.f4050w = j;
        if (this.f4029b != null) {
            GLTileCacheManager gLTileCacheManager = this.f4029b;
        }
    }

    public final void m6566a(ah ahVar) {
        this.f4034g = ahVar;
    }
}
