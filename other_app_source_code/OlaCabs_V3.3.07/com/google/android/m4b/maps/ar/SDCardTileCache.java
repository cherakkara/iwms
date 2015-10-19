package com.google.android.m4b.maps.ar;

import android.util.Pair;
import com.google.android.m4b.maps.ad.RandomAccessible.RandomAccessible;
import com.google.android.m4b.maps.ae.PerformanceProfile;
import com.google.android.m4b.maps.an.MockTile;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.ap.RefCountUtil;
import com.google.android.m4b.maps.aq.CompositeTileCallback;
import com.google.android.m4b.maps.aq.TileCallback;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.ByteArrayDataOutput;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p049i.ByteArrayDataInput;
import com.google.android.m4b.maps.p058v.Util;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

/* renamed from: com.google.android.m4b.maps.ar.j */
public final class SDCardTileCache implements DiskTileCache {
    private static final aa f3981a;
    private static final byte[] f3982b;
    private final String f3983c;
    private final int f3984d;
    private final SDCardTileCache f3985e;
    private final HashMap<ac, SDCardTileCache> f3986f;
    private GenericDiskCache f3987g;
    private final int f3988h;
    private final ai f3989i;
    private int f3990j;
    private Clock f3991k;
    private SDCardTileCache f3992l;

    /* renamed from: com.google.android.m4b.maps.ar.j.c */
    public interface SDCardTileCache {
        aa m5530a(ac acVar, byte[] bArr, int i, long j, long j2);
    }

    /* renamed from: com.google.android.m4b.maps.ar.j.a */
    static class SDCardTileCache implements GenericDiskCacheListener {
        private ai f3969a;
        private Queue<SDCardTileCache> f3970b;
        private Map<ac, aa> f3971c;
        private DiskTileCacheListener f3972d;

        /* renamed from: com.google.android.m4b.maps.ar.j.a.a */
        interface SDCardTileCache {
            void m6476a(DiskTileCacheListener diskTileCacheListener);
        }

        /* renamed from: com.google.android.m4b.maps.ar.j.a.b */
        class SDCardTileCache implements SDCardTileCache {
            private /* synthetic */ SDCardTileCache f3957a;

            private SDCardTileCache(SDCardTileCache sDCardTileCache) {
                this.f3957a = sDCardTileCache;
            }

            public final void m6477a(DiskTileCacheListener diskTileCacheListener) {
                this.f3957a.f3969a;
            }
        }

        /* renamed from: com.google.android.m4b.maps.ar.j.a.c */
        class SDCardTileCache implements SDCardTileCache {
            private /* synthetic */ SDCardTileCache f3958a;

            private SDCardTileCache(SDCardTileCache sDCardTileCache) {
                this.f3958a = sDCardTileCache;
            }

            public final void m6478a(DiskTileCacheListener diskTileCacheListener) {
                this.f3958a.f3969a;
            }
        }

        /* renamed from: com.google.android.m4b.maps.ar.j.a.d */
        class SDCardTileCache implements SDCardTileCache {
            private int f3959a;
            private /* synthetic */ SDCardTileCache f3960b;

            public SDCardTileCache(SDCardTileCache sDCardTileCache, int i) {
                this.f3960b = sDCardTileCache;
                this.f3959a = i;
            }

            public final void m6479a(DiskTileCacheListener diskTileCacheListener) {
                this.f3960b.f3969a;
                int i = this.f3959a;
            }
        }

        /* renamed from: com.google.android.m4b.maps.ar.j.a.e */
        class SDCardTileCache implements SDCardTileCache {
            private aa f3961a;
            private int f3962b;
            private /* synthetic */ SDCardTileCache f3963c;

            public SDCardTileCache(SDCardTileCache sDCardTileCache, aa aaVar, int i) {
                this.f3963c = sDCardTileCache;
                this.f3961a = aaVar;
                this.f3962b = i;
            }

            public final void m6480a(DiskTileCacheListener diskTileCacheListener) {
                this.f3963c.f3969a;
                aa aaVar = this.f3961a;
                int i = this.f3962b;
            }
        }

        /* renamed from: com.google.android.m4b.maps.ar.j.a.f */
        class SDCardTileCache implements SDCardTileCache {
            private ac f3964a;
            private int f3965b;
            private /* synthetic */ SDCardTileCache f3966c;

            public SDCardTileCache(SDCardTileCache sDCardTileCache, ac acVar, int i) {
                this.f3966c = sDCardTileCache;
                this.f3964a = acVar;
                this.f3965b = i;
            }

            public final void m6481a(DiskTileCacheListener diskTileCacheListener) {
                this.f3966c.f3969a;
                ac acVar = this.f3964a;
                int i = this.f3965b;
            }
        }

        /* renamed from: com.google.android.m4b.maps.ar.j.a.g */
        class SDCardTileCache implements SDCardTileCache {
            private ac f3967a;
            private /* synthetic */ SDCardTileCache f3968b;

            public SDCardTileCache(SDCardTileCache sDCardTileCache, ac acVar) {
                this.f3968b = sDCardTileCache;
                this.f3967a = acVar;
            }

            public final void m6482a(DiskTileCacheListener diskTileCacheListener) {
                this.f3968b.f3969a;
                ac acVar = this.f3967a;
            }
        }

        public SDCardTileCache(ai aiVar, DiskTileCacheListener diskTileCacheListener) {
            this.f3969a = aiVar;
            this.f3970b = new LinkedList();
            this.f3971c = new HashMap();
            this.f3972d = diskTileCacheListener;
        }

        public final void m6488a(aa aaVar) {
            this.f3971c.put(aaVar.m5409a(), aaVar);
        }

        public final void m6489b() {
            while (this.f3970b.size() > 0) {
                ((SDCardTileCache) this.f3970b.poll()).m6476a(this.f3972d);
            }
            if (this.f3971c.size() > 0) {
                Util.m11550a("SDCardTileCache", this.f3971c.size() + " tiles were not inserted into the disk cache.");
                this.f3971c.clear();
            }
        }

        public final void m6487a(long j, int i) {
            ac a = Util.m11546a(j);
            aa aaVar = (aa) this.f3971c.remove(a);
            if (a != null && aaVar != null) {
                this.f3970b.offer(new SDCardTileCache(this, aaVar, i));
            }
        }

        public final void m6490b(long j, int i) {
            ac a = Util.m11546a(j);
            if (a != null) {
                this.f3970b.offer(new SDCardTileCache(this, a, i));
            }
        }

        public final void m6486a(long j) {
            ac a = Util.m11546a(j);
            if (a != null) {
                this.f3970b.offer(new SDCardTileCache(this, a));
            }
        }

        public final void m6485a(int i) {
            this.f3970b.offer(new SDCardTileCache(this, i));
        }

        public final void m6484a() {
            this.f3970b.clear();
            this.f3970b.offer(new SDCardTileCache());
        }

        public final void m6491c() {
            this.f3970b.offer(new SDCardTileCache());
        }
    }

    /* renamed from: com.google.android.m4b.maps.ar.j.b */
    static class SDCardTileCache {
        final int f3973a;
        final long f3974b;
        final String f3975c;
        final int f3976d;
        final byte[] f3977e;
        final aa f3978f;
        final ac f3979g;
        final TileCallback f3980h;

        SDCardTileCache(long j, String str, int i, ac acVar, TileCallback tileCallback) {
            this.f3973a = 1;
            this.f3974b = j;
            this.f3975c = str;
            this.f3976d = i;
            this.f3977e = null;
            this.f3979g = acVar;
            this.f3978f = null;
            this.f3980h = tileCallback;
        }

        SDCardTileCache(long j, String str, int i, byte[] bArr, aa aaVar, TileCallback tileCallback) {
            this.f3973a = 0;
            this.f3974b = j;
            this.f3975c = str;
            this.f3976d = i;
            this.f3977e = bArr;
            this.f3979g = aaVar == null ? null : aaVar.m5409a();
            this.f3978f = aaVar;
            this.f3980h = tileCallback;
        }
    }

    static {
        f3981a = new MockTile();
        f3982b = new byte[0];
    }

    public SDCardTileCache(String str, int i, SDCardTileCache sDCardTileCache, ai aiVar, DiskTileCacheListener diskTileCacheListener) {
        this.f3990j = 0;
        this.f3991k = new Clock();
        this.f3992l = null;
        this.f3983c = str;
        this.f3984d = i;
        this.f3985e = sDCardTileCache;
        this.f3988h = Math.max(Math.min(AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, (VectorGlobalState.m7290f() >> 3) * 32), 64);
        this.f3986f = au.m2808a(this.f3988h);
        this.f3989i = aiVar;
        if (diskTileCacheListener != null) {
            this.f3992l = new SDCardTileCache(this.f3989i, diskTileCacheListener);
        }
    }

    public final synchronized boolean m6502a(File file) {
        boolean b;
        PerformanceProfile.m4867a();
        b = m6493b(file);
        PerformanceProfile.m4868b();
        return b;
    }

    private boolean m6493b(File file) {
        if (this.f3987g != null) {
            return true;
        }
        RandomAccessible randomAccessible = new RandomAccessible(file);
        this.f3991k.m10153c();
        try {
            this.f3987g = GenericDiskCache.m6410a(this.f3983c, randomAccessible, this.f3992l);
            Object obj = null;
        } catch (IOException e) {
            int i = 1;
        }
        if (obj == null && m6495g() == this.f3987g.m6452b()) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            try {
                this.f3987g = GenericDiskCache.m6409a(this.f3983c, this.f3984d, -1, new Locale(Trace.NULL), randomAccessible, this.f3992l);
                m6494f();
                if (this.f3992l != null) {
                    this.f3992l.m6484a();
                    this.f3992l.m6489b();
                }
            } catch (IOException e2) {
                Util.m11550a("SDCardTileCache", "Creating cache: " + e2);
                return false;
            }
        }
        this.f3991k.m10153c();
        return true;
    }

    public final synchronized void m6510e() {
        if (this.f3987g == null) {
            throw new IllegalStateException("Uninitialized");
        }
        try {
            this.f3987g.m6455d();
        } catch (IOException e) {
            Util.m11550a("SDCardTileCache", "shutDown(): " + e);
        }
    }

    public final void m6498a(ac acVar, TileCallback tileCallback, int i) {
        if (this.f3987g == null) {
            throw new IllegalStateException("Uninitialized");
        }
        synchronized (this.f3986f) {
            SDCardTileCache sDCardTileCache = (SDCardTileCache) this.f3986f.get(acVar);
            if (sDCardTileCache != null) {
                TileCallback tileCallback2;
                Object sDCardTileCache2;
                if (sDCardTileCache.f3980h == null) {
                    tileCallback2 = tileCallback;
                } else if (tileCallback != null) {
                    tileCallback2 = new CompositeTileCallback(sDCardTileCache.f3980h, tileCallback);
                } else {
                    tileCallback2 = sDCardTileCache.f3980h;
                }
                if (sDCardTileCache.f3978f != null) {
                    sDCardTileCache2 = new SDCardTileCache(sDCardTileCache.f3974b, sDCardTileCache.f3975c, RefCountUtil.m6290a(sDCardTileCache.f3976d, i), sDCardTileCache.f3977e, sDCardTileCache.f3978f, tileCallback2);
                } else {
                    SDCardTileCache sDCardTileCache3 = new SDCardTileCache(sDCardTileCache.f3974b, sDCardTileCache.f3975c, RefCountUtil.m6290a(sDCardTileCache.f3976d, i), acVar, tileCallback2);
                }
                this.f3986f.put(acVar, sDCardTileCache2);
            } else {
                Pair a = Util.m11545a(this.f3989i, acVar);
                this.f3986f.put(acVar, new SDCardTileCache(((Long) a.first).longValue(), (String) a.second, i, acVar, tileCallback));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m6497a(com.google.android.m4b.maps.an.ac r12, com.google.android.m4b.maps.an.aa r13, byte[] r14) {
        /*
        r11 = this;
        r8 = 0;
        r4 = 0;
        r6 = -1;
        r0 = r11.f3987g;
        if (r0 != 0) goto L_0x0011;
    L_0x0009:
        r0 = new java.lang.IllegalStateException;
        r1 = "Uninitialized";
        r0.<init>(r1);
        throw r0;
    L_0x0011:
        r0 = r13 instanceof com.google.android.m4b.maps.an.MutableVectorTile;
        if (r0 == 0) goto L_0x001d;
    L_0x0015:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Can't insert a MutableVectorTile into SD cache";
        r0.<init>(r1);
        throw r0;
    L_0x001d:
        r0 = r12.m5439b();
        r1 = 21;
        if (r0 > r1) goto L_0x00d4;
    L_0x0025:
        r0 = r11.f3989i;
        r9 = com.google.android.m4b.maps.p058v.Util.m11545a(r0, r12);
        r0 = r14.length;
        if (r0 <= 0) goto L_0x0103;
    L_0x002e:
        r0 = r13 instanceof com.google.android.m4b.maps.an.bb;
        if (r0 == 0) goto L_0x00ff;
    L_0x0032:
        r0 = r13;
        r0 = (com.google.android.m4b.maps.an.bb) r0;
        r0 = r0.m5619k();
        r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x00fc;
    L_0x003d:
        r2 = r11.f3991k;
        r2 = r2.m10152b();
        r0 = r0 - r2;
        r2 = r11.f3991k;
        r2 = r2.m10151a();
        r0 = r0 + r2;
        r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x00fc;
    L_0x004f:
        r2 = r4;
    L_0x0050:
        r0 = r13;
        r0 = (com.google.android.m4b.maps.an.bb) r0;
        r0 = r0.m5620p();
        r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r6 == 0) goto L_0x00f9;
    L_0x005b:
        r6 = r11.f3991k;
        r6 = r6.m10152b();
        r0 = r0 - r6;
        r6 = r11.f3991k;
        r6 = r6.m10151a();
        r0 = r0 + r6;
        r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r6 >= 0) goto L_0x00f9;
    L_0x006d:
        r1 = new java.io.ByteArrayOutputStream;
        r0 = r14.length;
        r0 = r0 + 24;
        r1.<init>(r0);
        r7 = new java.io.DataOutputStream;
        r7.<init>(r1);
        r0 = 24;
        r7.writeInt(r0);	 Catch:{ IOException -> 0x00d5, all -> 0x00e3 }
        r0 = 0;
        r7.writeInt(r0);	 Catch:{ IOException -> 0x00d5, all -> 0x00e3 }
        r7.writeLong(r2);	 Catch:{ IOException -> 0x00d5, all -> 0x00e3 }
        r7.writeLong(r4);	 Catch:{ IOException -> 0x00d5, all -> 0x00e3 }
        r7.write(r14);	 Catch:{ IOException -> 0x00d5, all -> 0x00e3 }
        r6 = r1.toByteArray();
        r7.close();	 Catch:{ IOException -> 0x00f5 }
        r1.close();	 Catch:{ IOException -> 0x00f5 }
    L_0x0096:
        r10 = r11.f3986f;
        monitor-enter(r10);
        r0 = r11.f3986f;	 Catch:{ all -> 0x00f0 }
        r0 = r0.get(r12);	 Catch:{ all -> 0x00f0 }
        r0 = (com.google.android.m4b.maps.ar.SDCardTileCache.SDCardTileCache) r0;	 Catch:{ all -> 0x00f0 }
        if (r0 == 0) goto L_0x00f7;
    L_0x00a3:
        r1 = r0.f3976d;	 Catch:{ all -> 0x00f0 }
        r2 = 0;
        r5 = com.google.android.m4b.maps.ap.RefCountUtil.m6290a(r1, r2);	 Catch:{ all -> 0x00f0 }
    L_0x00aa:
        r1 = new com.google.android.m4b.maps.ar.j$b;	 Catch:{ all -> 0x00f0 }
        r2 = r9.first;	 Catch:{ all -> 0x00f0 }
        r2 = (java.lang.Long) r2;	 Catch:{ all -> 0x00f0 }
        r2 = r2.longValue();	 Catch:{ all -> 0x00f0 }
        r4 = r9.second;	 Catch:{ all -> 0x00f0 }
        r4 = (java.lang.String) r4;	 Catch:{ all -> 0x00f0 }
        if (r0 == 0) goto L_0x00ee;
    L_0x00ba:
        r8 = r0.f3980h;	 Catch:{ all -> 0x00f0 }
    L_0x00bc:
        r7 = r13;
        r1.<init>(r2, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x00f0 }
        r2 = r11.f3990j;	 Catch:{ all -> 0x00f0 }
        r3 = r11.f3988h;	 Catch:{ all -> 0x00f0 }
        if (r2 >= r3) goto L_0x00d3;
    L_0x00c6:
        r2 = r11.f3986f;	 Catch:{ all -> 0x00f0 }
        r2.put(r12, r1);	 Catch:{ all -> 0x00f0 }
        if (r0 != 0) goto L_0x00d3;
    L_0x00cd:
        r0 = r11.f3990j;	 Catch:{ all -> 0x00f0 }
        r0 = r0 + 1;
        r11.f3990j = r0;	 Catch:{ all -> 0x00f0 }
    L_0x00d3:
        monitor-exit(r10);	 Catch:{ all -> 0x00f0 }
    L_0x00d4:
        return;
    L_0x00d5:
        r0 = move-exception;
        r6 = r1.toByteArray();
        r7.close();	 Catch:{ IOException -> 0x00e1 }
        r1.close();	 Catch:{ IOException -> 0x00e1 }
        goto L_0x0096;
    L_0x00e1:
        r0 = move-exception;
        goto L_0x0096;
    L_0x00e3:
        r0 = move-exception;
        r1.toByteArray();
        r7.close();	 Catch:{ IOException -> 0x00f3 }
        r1.close();	 Catch:{ IOException -> 0x00f3 }
    L_0x00ed:
        throw r0;
    L_0x00ee:
        r8 = 0;
        goto L_0x00bc;
    L_0x00f0:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x00f3:
        r1 = move-exception;
        goto L_0x00ed;
    L_0x00f5:
        r0 = move-exception;
        goto L_0x0096;
    L_0x00f7:
        r5 = r8;
        goto L_0x00aa;
    L_0x00f9:
        r4 = r0;
        goto L_0x006d;
    L_0x00fc:
        r2 = r0;
        goto L_0x0050;
    L_0x00ff:
        r4 = r6;
        r2 = r6;
        goto L_0x006d;
    L_0x0103:
        r6 = r14;
        goto L_0x0096;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ar.j.a(com.google.android.m4b.maps.an.ac, com.google.android.m4b.maps.an.aa, byte[]):void");
    }

    public final void m6496a(ac acVar, aa aaVar) {
        throw new IllegalStateException("Don't store unencrypted tiles into SD cache.");
    }

    public final void a_(ac acVar) {
        m6497a(acVar, f3981a, f3982b);
    }

    public final int m6507c() {
        if (this.f3987g != null) {
            return this.f3987g.m6446a();
        }
        throw new IllegalStateException("Uninitialized");
    }

    public final boolean m6500a(int i) {
        if (this.f3987g == null) {
            throw new IllegalStateException("Uninitialized");
        }
        try {
            this.f3987g.m6449a(i);
            return true;
        } catch (Throwable e) {
            Util.m11552a("SDCardTileCache", e);
            return false;
        }
    }

    public final Locale m6509d() {
        if (this.f3987g != null) {
            return this.f3987g.m6454c();
        }
        throw new IllegalStateException("Uninitialized");
    }

    public final boolean m6503a(Locale locale) {
        if (this.f3987g != null) {
            return m6492a(this.f3987g.m6446a(), locale);
        }
        throw new IllegalStateException("Uninitialized");
    }

    public final boolean m6499a() {
        if (this.f3987g != null) {
            return m6492a(this.f3987g.m6446a(), this.f3987g.m6454c());
        }
        throw new IllegalStateException("Uninitialized");
    }

    private boolean m6492a(int i, Locale locale) {
        try {
            synchronized (this.f3986f) {
                this.f3986f.clear();
                this.f3987g.m6450a(i, locale);
                m6494f();
                if (this.f3992l != null) {
                    this.f3992l.m6489b();
                }
            }
            return true;
        } catch (Throwable e) {
            Util.m11552a("SDCardTileCache", e);
            return false;
        }
    }

    private void m6494f() {
        ByteArrayDataOutput byteArrayDataOutput = new ByteArrayDataOutput();
        byteArrayDataOutput.writeLong(this.f3987g.m6452b());
        Config.m11320a().m10149q().m7752a(byteArrayDataOutput.m11313a(), "disk_creation_time_" + this.f3983c);
    }

    private long m6495g() {
        long j = 0;
        byte[] c = Config.m11320a().m10149q().m7757c("disk_creation_time_" + this.f3983c);
        if (c != null) {
            try {
                j = new ByteArrayDataInput(c).readLong();
            } catch (IOException e) {
                Config.m11320a().m10149q().m7756b("disk_creation_time_" + this.f3983c);
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.m4b.maps.an.aa m6508c(com.google.android.m4b.maps.an.ac r15) {
        /*
        r14 = this;
        r10 = -1;
        r8 = 0;
        r6 = 0;
        r0 = r14.f3987g;
        if (r0 != 0) goto L_0x0011;
    L_0x0009:
        r0 = new java.lang.IllegalStateException;
        r1 = "Uninitialized";
        r0.<init>(r1);
        throw r0;
    L_0x0011:
        r0 = r15.m5439b();
        r1 = 21;
        if (r0 <= r1) goto L_0x001b;
    L_0x0019:
        r0 = r8;
    L_0x001a:
        return r0;
    L_0x001b:
        r1 = r14.f3986f;
        monitor-enter(r1);
        r0 = r14.f3986f;	 Catch:{ all -> 0x002c }
        r0 = r0.get(r15);	 Catch:{ all -> 0x002c }
        r0 = (com.google.android.m4b.maps.ar.SDCardTileCache.SDCardTileCache) r0;	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x002f;
    L_0x0028:
        r0 = r0.f3978f;	 Catch:{ all -> 0x002c }
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
        goto L_0x001a;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x002f:
        monitor-exit(r1);
        r0 = r14.f3989i;
        r1 = com.google.android.m4b.maps.p058v.Util.m11545a(r0, r15);
        r2 = r14.f3987g;
        r0 = r1.first;
        r0 = (java.lang.Long) r0;
        r4 = r0.longValue();
        r0 = r1.second;
        r0 = (java.lang.String) r0;
        r2 = r2.m6451a(r4, r0);
        if (r2 != 0) goto L_0x004c;
    L_0x004a:
        r0 = r8;
        goto L_0x001a;
    L_0x004c:
        r0 = r2.length;	 Catch:{ IOException -> 0x00a5 }
        if (r0 != 0) goto L_0x0052;
    L_0x004f:
        r0 = f3981a;	 Catch:{ IOException -> 0x00a5 }
        goto L_0x001a;
    L_0x0052:
        r0 = new java.io.ByteArrayInputStream;	 Catch:{ IOException -> 0x00a5 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x00a5 }
        r1 = new java.io.DataInputStream;	 Catch:{ IOException -> 0x00a5 }
        r1.<init>(r0);	 Catch:{ IOException -> 0x00a5 }
        r3 = r1.readInt();	 Catch:{ IOException -> 0x00a5 }
        r1.readInt();	 Catch:{ IOException -> 0x00a5 }
        r4 = r1.readLong();	 Catch:{ IOException -> 0x00a5 }
        r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r0 == 0) goto L_0x007e;
    L_0x006b:
        r0 = r14.f3991k;	 Catch:{ IOException -> 0x00a5 }
        r12 = r0.m10151a();	 Catch:{ IOException -> 0x00a5 }
        r4 = r4 - r12;
        r0 = r14.f3991k;	 Catch:{ IOException -> 0x00a5 }
        r12 = r0.m10152b();	 Catch:{ IOException -> 0x00a5 }
        r4 = r4 + r12;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x007e;
    L_0x007d:
        r4 = r6;
    L_0x007e:
        r0 = 16;
        if (r3 <= r0) goto L_0x00d5;
    L_0x0082:
        r0 = r1.readLong();	 Catch:{ IOException -> 0x00a5 }
        r9 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
        if (r9 == 0) goto L_0x00d3;
    L_0x008a:
        r9 = r14.f3991k;	 Catch:{ IOException -> 0x00a5 }
        r10 = r9.m10151a();	 Catch:{ IOException -> 0x00a5 }
        r0 = r0 - r10;
        r9 = r14.f3991k;	 Catch:{ IOException -> 0x00a5 }
        r10 = r9.m10152b();	 Catch:{ IOException -> 0x00a5 }
        r0 = r0 + r10;
        r9 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r9 >= 0) goto L_0x00d3;
    L_0x009c:
        r0 = r14.f3985e;	 Catch:{ IOException -> 0x00a5 }
        r1 = r15;
        r0 = r0.m5530a(r1, r2, r3, r4, r6);	 Catch:{ IOException -> 0x00a5 }
        goto L_0x001a;
    L_0x00a5:
        r0 = move-exception;
        r1 = "SDCardTileCache";
        r2 = new java.lang.StringBuilder;
        r3 = "Could not unpack tile in ";
        r2.<init>(r3);
        r3 = r14.f3983c;
        r2 = r2.append(r3);
        r3 = ":";
        r2 = r2.append(r3);
        r2 = r2.append(r15);
        r3 = ":";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.google.android.m4b.maps.p058v.Util.m11550a(r1, r0);
        r0 = r8;
        goto L_0x001a;
    L_0x00d3:
        r6 = r0;
        goto L_0x009c;
    L_0x00d5:
        r6 = r10;
        goto L_0x009c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ar.j.c(com.google.android.m4b.maps.an.ac):com.google.android.m4b.maps.an.aa");
    }

    public final byte[] m6504a(ac acVar) {
        byte[] bArr;
        if (this.f3987g == null) {
            throw new IllegalStateException("Uninitialized");
        } else if (acVar.m5439b() > 21) {
            return null;
        } else {
            Object obj;
            synchronized (this.f3986f) {
                SDCardTileCache sDCardTileCache = (SDCardTileCache) this.f3986f.get(acVar);
                if (sDCardTileCache != null) {
                    obj = sDCardTileCache.f3977e;
                } else {
                    obj = null;
                }
            }
            if (obj == null) {
                Pair a = Util.m11545a(this.f3989i, acVar);
                obj = this.f3987g.m6451a(((Long) a.first).longValue(), (String) a.second);
            }
            if (obj == null || obj.length == 0) {
                return obj;
            }
            try {
                int readInt = new DataInputStream(new ByteArrayInputStream(obj)).readInt();
                int length = obj.length - readInt;
                if (length < 0 || readInt > 24) {
                    Util.m11550a("SDCardTileCache", "invalid tile data length[" + obj.length + "] in " + this.f3983c + ":" + acVar);
                    return null;
                }
                bArr = new byte[length];
                try {
                    System.arraycopy(obj, readInt, bArr, 0, length);
                    return bArr;
                } catch (IOException e) {
                    obj = e;
                    Util.m11550a("SDCardTileCache", "invalid tile data in " + this.f3983c + ":" + acVar + ":" + obj);
                    return bArr;
                }
            } catch (IOException e2) {
                IOException iOException = e2;
                bArr = null;
                Util.m11550a("SDCardTileCache", "invalid tile data in " + this.f3983c + ":" + acVar + ":" + obj);
                return bArr;
            }
        }
    }

    public final boolean m6506b(ac acVar) {
        if (this.f3987g == null) {
            throw new IllegalStateException("Uninitialized");
        } else if (acVar.m5439b() > 21) {
            return false;
        } else {
            Pair a = Util.m11545a(this.f3989i, acVar);
            if (this.f3987g.m6453b(((Long) a.first).longValue(), (String) a.second)) {
                return true;
            }
            boolean z;
            synchronized (this.f3986f) {
                SDCardTileCache sDCardTileCache = (SDCardTileCache) this.f3986f.get(acVar);
                if (sDCardTileCache == null || sDCardTileCache.f3977e == null) {
                    z = false;
                } else {
                    z = true;
                }
            }
            return z;
        }
    }

    public final boolean m6501a(aa aaVar) {
        return aaVar == f3981a;
    }

    public final void f_() {
        Iterator it;
        this.f3991k.m10153c();
        Collection a = ar.m2515a();
        ArrayList a2 = ar.m2515a();
        ArrayList a3 = ar.m2515a();
        synchronized (this.f3986f) {
            for (SDCardTileCache sDCardTileCache : this.f3986f.values()) {
                SDCardTileCache sDCardTileCache2;
                if (sDCardTileCache2.f3977e != null) {
                    byte[] bArr = sDCardTileCache2.f3977e;
                }
                switch (sDCardTileCache2.f3973a) {
                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                        a.add(GenericDiskCache.m6403a(sDCardTileCache2.f3974b, sDCardTileCache2.f3975c, sDCardTileCache2.f3976d, sDCardTileCache2.f3977e));
                        if (this.f3992l != null) {
                            this.f3992l.m6488a(sDCardTileCache2.f3978f);
                        }
                        if (sDCardTileCache2.f3980h != null) {
                            a2.add(Pair.create(sDCardTileCache2.f3980h, sDCardTileCache2.f3978f));
                            break;
                        }
                        continue;
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        a3.add(sDCardTileCache2);
                        continue;
                    default:
                        continue;
                }
            }
            this.f3990j = 0;
            this.f3986f.clear();
        }
        if (a.size() > 0) {
            int i;
            try {
                int i2;
                if (this.f3987g.m6448a(a) == -1) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                i = i2;
            } catch (Throwable e) {
                Util.m11552a("SDCardTileCache", e);
                i = 1;
            }
            it = a2.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                ((TileCallback) pair.first).m5267a(((aa) pair.second).m5409a(), i, (aa) pair.second);
            }
        }
        Iterator it2 = a3.iterator();
        while (it2.hasNext()) {
            int i3;
            sDCardTileCache2 = (SDCardTileCache) it2.next();
            if (sDCardTileCache2.f3976d > 0) {
                try {
                    if (this.f3987g.m6447a(sDCardTileCache2.f3974b, sDCardTileCache2.f3975c, sDCardTileCache2.f3976d) == -1) {
                        i3 = 2;
                        if (sDCardTileCache2.f3980h != null) {
                            sDCardTileCache2.f3980h.m5267a(sDCardTileCache2.f3979g, i3, null);
                        }
                    }
                } catch (IOException e2) {
                    i3 = 1;
                }
            }
            i3 = 0;
            if (sDCardTileCache2.f3980h != null) {
                sDCardTileCache2.f3980h.m5267a(sDCardTileCache2.f3979g, i3, null);
            }
        }
        if (this.f3992l != null) {
            this.f3992l.m6491c();
            this.f3992l.m6489b();
        }
    }

    public final boolean m6505b() {
        return !this.f3986f.isEmpty();
    }
}
