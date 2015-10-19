package com.google.android.m4b.maps.p061y;

import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.bg;
import com.google.android.m4b.maps.au.Bitmask;
import com.google.android.m4b.maps.aw.GLTileCacheManager.GLTileCacheManager;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLImageTile;
import com.google.android.m4b.maps.ba.GLTile;
import com.google.android.m4b.maps.ba.MockGLTile;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.model.Tile;
import com.google.android.m4b.maps.model.ae;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.google.p025a.p026a.Preconditions;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.android.m4b.maps.y.t */
public final class TileFetcher {
    private static GLTile f8203b;
    final Set<TileFetcher> f8204a;
    private final GLTileCacheManager f8205c;
    private final ae f8206d;
    private final ScheduledExecutorService f8207e;
    private final Set<ac> f8208f;
    private volatile GLState f8209g;
    private final com.google.android.m4b.maps.aw.GLTileCacheManager f8210h;
    private volatile TileFetcher f8211i;

    /* renamed from: com.google.android.m4b.maps.y.t.a */
    public interface TileFetcher {
        void m11906b(boolean z);
    }

    /* renamed from: com.google.android.m4b.maps.y.t.b */
    class TileFetcher implements Runnable {
        private final Random f8198a;
        private final ac f8199b;
        private ScheduledFuture f8200c;
        private int f8201d;
        private /* synthetic */ TileFetcher f8202e;

        public TileFetcher(TileFetcher tileFetcher, ac acVar, Random random) {
            this.f8202e = tileFetcher;
            this.f8200c = null;
            this.f8201d = 0;
            this.f8199b = acVar;
            this.f8198a = random;
            tileFetcher.f8204a.add(this);
        }

        public final synchronized void m11909a() {
            if (this.f8200c != null) {
                this.f8200c.cancel(false);
                this.f8200c = null;
            }
        }

        private synchronized void m11908b() {
            this.f8200c = null;
            this.f8202e.f8204a.remove(this);
            TileFetcher.m11914a(this.f8202e, this.f8199b);
        }

        private synchronized void m11907a(Tile tile) {
            boolean z = true;
            synchronized (this) {
                Preconditions.m1823a(tile.f7622a > 0, (Object) "width of tile image must be positive");
                if (tile.f7623b <= 0) {
                    z = false;
                }
                Preconditions.m1823a(z, (Object) "height of tile image must be positive");
                try {
                    bg bgVar = new bg(this.f8199b, 0, tile.f7622a, tile.f7623b, tile.f7624c, ai.f3442t);
                    this.f8200c = null;
                    this.f8202e.f8204a.remove(this);
                    TileFetcher.m11915a(this.f8202e, this.f8199b, bgVar);
                } catch (IOException e) {
                    m11908b();
                }
            }
        }

        public final synchronized void run() {
            Tile a = this.f8202e.f8206d.m10800a(this.f8199b.m5440c(), this.f8199b.m5441d(), this.f8199b.m5439b());
            if (a == null) {
                int i = this.f8201d;
                this.f8201d = i + 1;
                long pow = (long) ((200.0d * Math.pow(2.0d, (double) i)) + ((double) this.f8198a.nextInt(100)));
                if (pow < 300000) {
                    this.f8200c = this.f8202e.f8207e.schedule(this, pow, TimeUnit.MILLISECONDS);
                } else {
                    m11908b();
                }
            } else if (a == ae.f7626a) {
                m11908b();
            } else {
                m11907a(a);
            }
        }
    }

    static /* synthetic */ void m11914a(TileFetcher tileFetcher, ac acVar) {
        Preconditions.m1828b(tileFetcher.f8209g != null);
        tileFetcher.m11913a(acVar, f8203b, tileFetcher.f8209g);
    }

    static /* synthetic */ void m11915a(TileFetcher tileFetcher, ac acVar, bg bgVar) {
        Preconditions.m1828b(tileFetcher.f8209g != null);
        GLState gLState = tileFetcher.f8209g;
        GLTile gLTile = null;
        if (gLState != null) {
            gLTile = GLImageTile.m7873a((aa) bgVar, gLState);
        }
        tileFetcher.m11913a(acVar, gLTile, gLState);
    }

    static {
        f8203b = new MockGLTile();
    }

    public TileFetcher(ae aeVar, String str, ScheduledExecutorService scheduledExecutorService) {
        this.f8208f = Collections.synchronizedSet(new HashSet());
        this.f8204a = Collections.synchronizedSet(new HashSet());
        Preconditions.m1817a((Object) aeVar);
        Preconditions.m1817a((Object) scheduledExecutorService);
        this.f8206d = aeVar;
        this.f8205c = new GLTileCacheManager(ai.f3442t, null, TileFetcher.m11910a(Integer.valueOf(str.replaceAll("\\D", Trace.NULL)).intValue()));
        this.f8207e = scheduledExecutorService;
        com.google.android.m4b.maps.aw.GLTileCacheManager.m7383a(new Clock());
        this.f8210h = com.google.android.m4b.maps.aw.GLTileCacheManager.m7380a();
    }

    private static Bitmask m11910a(int i) {
        Object obj = new int[32];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 32) {
            int i4;
            if (((1 << i2) & i) != 0) {
                i4 = i3 + 1;
                obj[i3] = i2;
            } else {
                i4 = i3;
            }
            i2++;
            i3 = i4;
        }
        int[] iArr = new int[i3];
        System.arraycopy(obj, 0, iArr, 0, i3);
        return Bitmask.m6608a(iArr);
    }

    public final void m11920a(TileFetcher tileFetcher) {
        boolean z = true;
        if (this.f8211i == null) {
            if (tileFetcher == null) {
                z = false;
            }
            Preconditions.m1822a(z);
        } else {
            if (tileFetcher != null) {
                z = false;
            }
            Preconditions.m1822a(z);
        }
        this.f8211i = tileFetcher;
    }

    public final void m11919a(GLState gLState) {
        Preconditions.m1818a((Object) gLState, (Object) "state must not be null.");
        this.f8209g = gLState;
    }

    public final GLTile m11917a(ac acVar) {
        return m11911a(acVar, false);
    }

    public final GLTile m11923b(ac acVar) {
        GLTile a = m11911a(acVar, true);
        if (a != null) {
            return a;
        }
        if (this.f8208f.add(acVar)) {
            this.f8207e.execute(new TileFetcher(this, acVar, new Random()));
        }
        return null;
    }

    private GLTile m11911a(ac acVar, boolean z) {
        if (this.f8209g == null) {
            return null;
        }
        return this.f8210h.m7388a(this.f8209g, this.f8205c, acVar, z);
    }

    public final void m11918a() {
        Preconditions.m1828b(this.f8209g != null);
        this.f8210h.m7400d(this.f8209g, this.f8205c);
    }

    public final void m11924b() {
        Preconditions.m1828b(this.f8209g != null);
        if (DeviceSpecificInfo.f8006a) {
            this.f8210h.m7399c(this.f8209g, this.f8205c);
        }
    }

    public final void m11921a(List<GLTile> list) {
        Preconditions.m1828b(this.f8209g != null);
        List arrayList = new ArrayList(list.size());
        for (GLTile gLTile : list) {
            if (gLTile != f8203b) {
                arrayList.add(gLTile.m7845b());
            }
        }
        this.f8210h.m7392a(this.f8209g, this.f8205c, arrayList);
    }

    public final void m11925b(List<GLTile> list) {
        Preconditions.m1828b(this.f8209g != null);
        List arrayList = new ArrayList(list.size());
        for (GLTile gLTile : list) {
            if (gLTile != f8203b) {
                arrayList.add(gLTile.m7845b());
            }
        }
        this.f8210h.m7397b(this.f8209g, this.f8205c, arrayList);
    }

    public final void m11927c() {
        if (this.f8209g != null) {
            this.f8210h.m7396b(this.f8209g, this.f8205c);
        }
    }

    public final void m11922a(boolean z) {
        GLState gLState = this.f8209g;
        if (gLState != null) {
            this.f8210h.m7390a(gLState, this.f8205c);
        }
    }

    public final void m11926b(boolean z) {
        this.f8210h.m7393a(z);
    }

    public final void m11928d() {
        this.f8210h.m7394b();
    }

    private void m11913a(ac acVar, GLTile gLTile, GLState gLState) {
        if (this.f8210h != null) {
            this.f8210h.m7391a(gLState, this.f8205c, acVar, gLTile);
        }
        this.f8208f.remove(acVar);
        boolean z = gLTile != null;
        TileFetcher tileFetcher = this.f8211i;
        if (tileFetcher != null) {
            tileFetcher.m11906b(z);
        }
    }
}
