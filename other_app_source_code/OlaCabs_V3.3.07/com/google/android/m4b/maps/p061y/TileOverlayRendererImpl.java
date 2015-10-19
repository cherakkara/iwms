package com.google.android.m4b.maps.p061y;

import android.content.res.Resources;
import com.google.android.m4b.maps.ag.TileCoordGenerator;
import com.google.android.m4b.maps.ag.TileCoordGeneratorFactory;
import com.google.android.m4b.maps.ag.TileCoordGeneratorProvider;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.TileOverlay;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLTile;
import com.google.android.m4b.maps.be.bd;
import com.google.android.m4b.maps.be.bd.TileOverlayImpl;
import com.google.android.m4b.maps.model.TileOverlayOptions;
import com.google.android.m4b.maps.p061y.TileFetcher.TileFetcher;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.bk;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.m4b.maps.y.u */
public final class TileOverlayRendererImpl implements TileOverlayImpl, Overlay, TileFetcher {
    private static final TileCoordGeneratorProvider f8214n;
    private final int f8215a;
    private final int f8216b;
    private final boolean f8217c;
    private final TileFetcher f8218d;
    private final ArrayList<GLTile> f8219e;
    private final TileOverlayRendererImpl f8220f;
    private TileCoordGenerator f8221g;
    private volatile RepaintCallback f8222h;
    private boolean f8223i;
    private volatile boolean f8224j;
    private boolean f8225k;
    private volatile boolean f8226l;
    private final TileCoordGeneratorProvider f8227m;
    private final Set<GLTile> f8228o;
    private final ah f8229p;
    private WeakReference<GLState> f8230q;
    private float f8231r;
    private final OverlayRendererManagerImpl f8232s;
    private final bd f8233t;

    /* renamed from: com.google.android.m4b.maps.y.u.a */
    public static class TileOverlayRendererImpl implements Comparator<ac> {
        private int f8212a;
        private int f8213b;

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            ac acVar = (ac) obj;
            ac acVar2 = (ac) obj2;
            int b = acVar.m5439b();
            int b2 = acVar2.m5439b();
            if (b != b2) {
                return b2 - b;
            }
            b = 536870912 >> b;
            b2 = acVar.m5442e() + b;
            int f = acVar.m5443f() + b;
            int e = acVar2.m5442e() + b;
            b += acVar2.m5443f();
            return (Math.abs(b2 - this.f8212a) + Math.abs(f - this.f8213b)) - (Math.abs(b - this.f8213b) + Math.abs(e - this.f8212a));
        }

        public final void m11929a(Point point) {
            this.f8212a = point.m5958f();
            this.f8213b = point.m5960g();
        }
    }

    static {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        f8214n = new TileCoordGeneratorFactory();
    }

    public static TileOverlayRendererImpl m11930a(bd bdVar, Resources resources, OverlayRendererManagerImpl overlayRendererManagerImpl, ScheduledExecutorService scheduledExecutorService) {
        int a = TileOverlay.m6715a(resources, 332);
        Preconditions.m1823a(bdVar.m8823d() != null, (Object) "TileOverlay.Options must specify a TileProvider");
        TileFetcher tileFetcher = new TileFetcher(bdVar.m8823d(), bdVar.m8815a(), scheduledExecutorService);
        TileFetcher tileOverlayRendererImpl = new TileOverlayRendererImpl(overlayRendererManagerImpl, bdVar, tileFetcher, f8214n, a, 332, false);
        tileFetcher.m11920a(tileOverlayRendererImpl);
        return tileOverlayRendererImpl;
    }

    public final void m11936a(GLState gLState, RepaintCallback repaintCallback) {
        this.f8230q = new WeakReference(gLState);
        this.f8218d.m11919a(gLState);
        this.f8222h = repaintCallback;
        if (this.f8221g == null) {
            this.f8221g = this.f8227m.m4900a(ai.f3426d, this.f8216b, this.f8217c, this.f8229p);
        }
    }

    public final void m11935a(GLState gLState) {
        this.f8230q = null;
        this.f8222h = null;
        this.f8218d.m11927c();
        TileFetcher tileFetcher = this.f8218d;
        synchronized (tileFetcher.f8204a) {
            for (TileFetcher a : tileFetcher.f8204a) {
                a.m11909a();
            }
            tileFetcher.f8204a.clear();
        }
        this.f8224j = true;
    }

    public final void m11938a(boolean z) {
        this.f8218d.m11926b(z);
        this.f8224j = true;
    }

    public final void m11940b() {
        this.f8218d.m11928d();
        this.f8224j = true;
    }

    private TileOverlayRendererImpl(OverlayRendererManagerImpl overlayRendererManagerImpl, bd bdVar, TileFetcher tileFetcher, TileCoordGeneratorProvider tileCoordGeneratorProvider, int i, int i2, boolean z) {
        this.f8219e = ar.m2515a();
        this.f8220f = new TileOverlayRendererImpl();
        this.f8226l = false;
        this.f8228o = bk.m2880c();
        this.f8232s = overlayRendererManagerImpl;
        this.f8233t = bdVar;
        this.f8218d = tileFetcher;
        this.f8227m = tileCoordGeneratorProvider;
        this.f8215a = i;
        this.f8216b = 332;
        this.f8217c = false;
        this.f8229p = new ah();
        m11932a(-1);
    }

    public final void m11932a(int i) {
        if ((i & 2) != 0) {
            synchronized (this.f8232s) {
                this.f8231r = this.f8233t.m8826g();
                this.f8232s.m11801c();
            }
        }
        if ((i & 4) != 0) {
            this.f8232s.m11800b();
        }
    }

    public final void m11931a() {
        synchronized (this.f8232s) {
            this.f8232s.m11796a((Overlay) this);
        }
        this.f8232s.m11800b();
    }

    public final void m11942b(boolean z) {
        RepaintCallback repaintCallback = this.f8222h;
        if (repaintCallback != null && z) {
            repaintCallback.m6765a(true, false);
        }
    }

    public final void m11941b(int i) {
        this.f8223i = (i & 2) != 0;
    }

    public final void m11934a(Camera camera, GLState gLState) {
        this.f8226l = false;
        if (this.f8233t.m8828i()) {
            GLTile a;
            this.f8225k = true;
            List<ac> a2 = this.f8221g.m4876a(camera);
            if (a2.size() > 1) {
                this.f8220f.m11929a(camera.m7434c());
                Collections.sort(a2, this.f8220f);
            }
            this.f8228o.addAll(this.f8219e);
            this.f8219e.clear();
            this.f8218d.m11918a();
            boolean z = this.f8223i;
            for (ac acVar : a2) {
                if (z) {
                    a = this.f8218d.m11917a(acVar);
                } else {
                    a = this.f8218d.m11923b(acVar);
                }
                if (a != null) {
                    this.f8219e.add(a);
                    this.f8230q.get();
                    if (!this.f8228o.remove(a)) {
                        a.m7841a(true);
                    }
                    this.f8219e.size();
                    if (this.f8219e.size() == this.f8215a) {
                        break;
                    }
                }
                this.f8218d.m11924b();
            }
            this.f8226l = this.f8219e.size() == a2.size();
            this.f8224j = this.f8223i;
            for (GLTile a3 : this.f8228o) {
                a3.m7841a(false);
            }
            this.f8228o.clear();
        }
    }

    public final void m11937a(GLState gLState, Camera camera, ad adVar) {
        if (this.f8233t.m8828i() && adVar.m6704b() <= 0) {
            ad adVar2 = new ad(adVar);
            if (this.f8224j && !this.f8223i) {
                m11934a(camera, gLState);
            }
            if (this.f8225k) {
                this.f8218d.m11921a(this.f8219e);
            }
            if (this.f8219e.size() > 0) {
                gLState.m7543z();
                adVar2.m6702a(1);
                ((GLTile) this.f8219e.get(0)).m7846b(gLState, camera, adVar2);
                Iterator it = this.f8219e.iterator();
                while (it.hasNext()) {
                    GLTile gLTile = (GLTile) it.next();
                    if (!this.f8233t.m8830k()) {
                        gLTile.m7849d();
                    }
                    gLTile.m6659a(gLState, camera, adVar2);
                }
                gLState.m7503A();
            }
            if (this.f8225k) {
                this.f8218d.m11925b(this.f8219e);
                this.f8225k = false;
            }
        }
    }

    public final boolean m11939a(float f, float f2, Point point, Camera camera) {
        return false;
    }

    public final void m11933a(long j) {
    }

    public final synchronized boolean m11943c() {
        boolean z;
        z = !this.f8233t.m8828i() || this.f8226l;
        return z;
    }

    public final String m11945e() {
        return this.f8233t.m8815a();
    }

    public final synchronized void m11944d() {
        this.f8222h = null;
        this.f8218d.m11927c();
        this.f8218d.m11920a(null);
    }

    public final void m11947g() {
        this.f8218d.m11922a(false);
        this.f8232s.m11800b();
    }

    public final float m11946f() {
        return this.f8231r;
    }

    public final String toString() {
        return Objects.m1809a((Object) this).m1806a("id", this.f8233t.m8815a()).toString();
    }
}
