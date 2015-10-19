package com.google.android.m4b.maps.as;

import android.util.Pair;
import com.google.android.m4b.maps.ag.LightweightAncestorGenerator;
import com.google.android.m4b.maps.ag.TileCoordGenerator;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.ap.TileStore;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.bk;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.as.a */
public final class FetchRequestProvider {
    private int f4004a;
    private boolean f4005b;
    private int f4006c;
    private final LinkedHashSet<ac> f4007d;
    private final LinkedHashSet<ac> f4008e;
    private Iterator<ac> f4009f;
    private final FetchRequestProvider f4010g;
    private final LinkedList<Pair<ac, Boolean>> f4011h;
    private TileCoordGenerator f4012i;
    private Point f4013j;
    private long f4014k;
    private long f4015l;
    private byte f4016m;
    private boolean f4017n;
    private Map<ac, TileFetchRequest> f4018o;
    private Set<ac> f4019p;
    private TileFetchRequest f4020q;

    /* renamed from: com.google.android.m4b.maps.as.a.a */
    class FetchRequestProvider {
        private boolean f3997a;
        private int f3998b;
        private ac f3999c;
        private LinkedHashSet<ac> f4000d;
        private LinkedHashSet<ac> f4001e;
        private Iterator<ac> f4002f;
        private /* synthetic */ FetchRequestProvider f4003g;

        public FetchRequestProvider(FetchRequestProvider fetchRequestProvider, boolean z) {
            this.f4003g = fetchRequestProvider;
            this.f3998b = 0;
            this.f4000d = bk.m2878b();
            this.f4001e = bk.m2878b();
            this.f3997a = z;
        }

        public final void m6517a() {
            this.f3998b = 0;
            this.f4000d.clear();
            this.f4001e.clear();
            this.f3999c = null;
            this.f4000d.addAll(this.f4003g.f4007d);
            this.f4002f = this.f4000d.iterator();
        }

        public final TileFetchRequest m6516a(boolean z) {
            if (this.f3999c != null && this.f3998b < this.f4003g.f4004a) {
                if (!z) {
                    ac a = this.f4003g.f4012i.m4874a(this.f3999c, this.f4003g.f4013j);
                    if (a != null) {
                        this.f4001e.add(a);
                    }
                }
                if (!(this.f4002f.hasNext() || this.f4001e.isEmpty())) {
                    this.f3998b++;
                    LinkedHashSet linkedHashSet = this.f4000d;
                    this.f4000d = this.f4001e;
                    this.f4001e = linkedHashSet;
                    this.f4001e.clear();
                    this.f4002f = this.f4000d.iterator();
                }
            }
            if (!this.f4002f.hasNext()) {
                return null;
            }
            this.f3999c = (ac) this.f4002f.next();
            ac acVar = this.f3999c;
            long b = this.f4003g.m6522d();
            boolean z2 = (this.f3997a || this.f3998b == 0) ? false : true;
            return new TileFetchRequest(acVar, b, z2);
        }
    }

    public FetchRequestProvider(int i, int i2, boolean z, boolean z2) {
        this.f4007d = bk.m2878b();
        this.f4008e = bk.m2878b();
        this.f4011h = ar.m2524b();
        this.f4014k = 0;
        this.f4016m = (byte) 4;
        this.f4018o = au.m2807a();
        this.f4019p = bk.m2870a();
        this.f4004a = i;
        this.f4010g = new FetchRequestProvider(this, z2);
        this.f4005b = z;
        this.f4006c = i2;
    }

    private long m6522d() {
        long j = this.f4014k + 1;
        this.f4014k = j;
        return j;
    }

    public final synchronized void m6528a(TileCoordGenerator tileCoordGenerator, Point point, List<ac> list, Set<ac> set, Set<ac> set2, boolean z) {
        this.f4016m = (byte) 0;
        this.f4015l = m6522d();
        this.f4012i = tileCoordGenerator;
        this.f4013j = point;
        this.f4020q = new TileFetchRequest(TileStore.f3757d, m6522d(), Boolean.TRUE.booleanValue());
        this.f4007d.clear();
        this.f4007d.addAll(list);
        this.f4011h.clear();
        if (set != null) {
            for (ac create : set) {
                this.f4011h.add(Pair.create(create, Boolean.TRUE));
            }
        }
        if (set2 != null) {
            for (ac create2 : set2) {
                this.f4011h.add(Pair.create(create2, Boolean.FALSE));
            }
        }
        this.f4017n = z;
    }

    public final synchronized void m6527a() {
        if (!this.f4017n) {
            this.f4016m = (byte) 4;
        }
    }

    private synchronized void m6525e() {
        this.f4018o.clear();
        this.f4008e.clear();
        this.f4019p.clear();
        if (this.f4005b) {
            Collection b = bk.m2878b();
            Iterator it = this.f4007d.iterator();
            while (it.hasNext()) {
                b.add(((ac) it.next()).m5431a());
            }
            LightweightAncestorGenerator.m4889a(this.f4012i, this.f4004a, b, this.f4013j, 8, this.f4008e);
            if (this.f4017n) {
                this.f4008e.addAll(this.f4012i.m4875a(2, this.f4013j));
            } else {
                ac acVar;
                List<ac> a = ar.m2516a(this.f4008e);
                Collections.reverse(a);
                for (ac acVar2 : a) {
                    this.f4011h.add(0, Pair.create(acVar2, Boolean.FALSE));
                }
                Iterator it2 = this.f4008e.iterator();
                int i = -1;
                while (it2.hasNext()) {
                    acVar2 = (ac) it2.next();
                    if (i == -1) {
                        i = acVar2.m5439b();
                    } else if (i != acVar2.m5439b()) {
                        it2.remove();
                    }
                }
            }
        }
        this.f4009f = this.f4008e.iterator();
    }

    public final synchronized TileFetchRequest m6530b() {
        return this.f4020q;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.android.m4b.maps.as.TileFetchRequest m6526a(com.google.android.m4b.maps.as.TileFetchRequest r3, boolean r4) {
        /*
        r2 = this;
        monitor-enter(r2);
    L_0x0001:
        r3 = r2.m6520b(r3, r4);	 Catch:{ all -> 0x0030 }
        if (r3 == 0) goto L_0x002e;
    L_0x0007:
        r4 = 0;
        r0 = r2.f4018o;	 Catch:{ all -> 0x0030 }
        r1 = r3.f4021a;	 Catch:{ all -> 0x0030 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0030 }
        r0 = (com.google.android.m4b.maps.as.TileFetchRequest) r0;	 Catch:{ all -> 0x0030 }
        if (r0 == 0) goto L_0x001c;
    L_0x0014:
        r0 = r0.f4022b;	 Catch:{ all -> 0x0030 }
        if (r0 == 0) goto L_0x0001;
    L_0x0018:
        r0 = r3.f4022b;	 Catch:{ all -> 0x0030 }
        if (r0 != 0) goto L_0x0001;
    L_0x001c:
        r0 = r2.f4018o;	 Catch:{ all -> 0x0030 }
        r1 = r3.f4021a;	 Catch:{ all -> 0x0030 }
        r0.put(r1, r3);	 Catch:{ all -> 0x0030 }
        r0 = r3.f4022b;	 Catch:{ all -> 0x0030 }
        if (r0 != 0) goto L_0x002e;
    L_0x0027:
        r0 = r2.f4019p;	 Catch:{ all -> 0x0030 }
        r1 = r3.f4021a;	 Catch:{ all -> 0x0030 }
        r0.add(r1);	 Catch:{ all -> 0x0030 }
    L_0x002e:
        monitor-exit(r2);
        return r3;
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.as.a.a(com.google.android.m4b.maps.as.b, boolean):com.google.android.m4b.maps.as.b");
    }

    private synchronized TileFetchRequest m6520b(TileFetchRequest tileFetchRequest, boolean z) {
        Object obj = 1;
        TileFetchRequest tileFetchRequest2 = null;
        synchronized (this) {
            if (m6529a(tileFetchRequest)) {
                long j = tileFetchRequest.f4023c;
                if (this.f4016m == null) {
                    if (j == this.f4020q.f4023c) {
                        this.f4016m = (byte) 1;
                        m6525e();
                        z = false;
                    }
                }
                if (z && tileFetchRequest != this.f4020q) {
                    this.f4007d.remove(tileFetchRequest.f4021a);
                    this.f4019p.add(tileFetchRequest.f4021a);
                }
                if (this.f4016m == (byte) 1) {
                    if (j == this.f4014k) {
                        if (this.f4019p.size() + this.f4007d.size() < this.f4006c && this.f4009f.hasNext()) {
                            tileFetchRequest2 = new TileFetchRequest((ac) this.f4009f.next(), m6522d(), Boolean.TRUE.booleanValue());
                        } else if (this.f4017n) {
                            this.f4016m = (byte) 4;
                        } else {
                            this.f4016m = (byte) 2;
                            this.f4010g.m6517a();
                        }
                    }
                }
                if (this.f4019p.size() >= this.f4006c) {
                    obj = null;
                }
                if (obj == null) {
                    this.f4016m = (byte) 4;
                } else {
                    if (this.f4016m == 2) {
                        if (this.f4007d.isEmpty()) {
                            j = this.f4014k;
                            this.f4016m = (byte) 3;
                        } else if (tileFetchRequest.f4023c == this.f4014k && obj != null) {
                            tileFetchRequest2 = this.f4010g.m6516a(z);
                        }
                    }
                    if (this.f4016m == 3 && r2 == this.f4014k) {
                        if (this.f4011h.isEmpty() || obj == null) {
                            this.f4016m = (byte) 4;
                        } else {
                            Pair pair = (Pair) this.f4011h.remove(0);
                            tileFetchRequest2 = new TileFetchRequest((ac) pair.first, m6522d(), ((Boolean) pair.second).booleanValue());
                        }
                    }
                }
            }
        }
        return tileFetchRequest2;
    }

    public final synchronized boolean m6531c() {
        return this.f4016m == 4;
    }

    public final synchronized boolean m6529a(TileFetchRequest tileFetchRequest) {
        boolean z;
        if (tileFetchRequest != null) {
            if (tileFetchRequest.f4023c >= this.f4015l && !m6531c()) {
                z = true;
            }
        }
        z = false;
        return z;
    }
}
