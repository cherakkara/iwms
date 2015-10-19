package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.al.IndoorLevelOutline.IndoorLevelOutline;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.av;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.ap.TileStore;
import com.google.android.m4b.maps.ap.TileStoreFactory;
import com.google.android.m4b.maps.aq.TileCallback;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.p025a.p028c.bk;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* renamed from: com.google.android.m4b.maps.al.h */
public final class IndoorOutlineFetcher implements TileCallback {
    public static final IndoorLevelOutline f3307a;
    private final TileStore f3308b;
    private final TileStore.TileStore f3309c;
    private final LRUCache<ac, IndoorLevelOutline> f3310d;
    private int f3311e;
    private final Set<ac> f3312f;
    private final Set<IndoorOutlineFetcher> f3313g;

    /* renamed from: com.google.android.m4b.maps.al.h.1 */
    class IndoorOutlineFetcher implements TileStore.TileStore {
        private /* synthetic */ IndoorOutlineFetcher f3306a;

        IndoorOutlineFetcher(IndoorOutlineFetcher indoorOutlineFetcher) {
            this.f3306a = indoorOutlineFetcher;
        }

        public final void m5321a() {
            this.f3306a.m5326a();
        }

        public final void m5322a(aa aaVar) {
            if (aaVar != null) {
                this.f3306a.m5328a(aaVar.m5409a(), 0, aaVar);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.al.h.a */
    public interface IndoorOutlineFetcher {
        void m5323b();
    }

    static {
        f3307a = new IndoorLevelOutline(com.google.p025a.p028c.ac.m2345g());
    }

    public IndoorOutlineFetcher() {
        this.f3312f = bk.m2870a();
        this.f3313g = new CopyOnWriteArraySet();
        if (TileStoreFactory.m6312a(ai.f3436n)) {
            this.f3308b = TileStoreFactory.m6313b(ai.f3436n);
            this.f3310d = new LRUCache(100);
            this.f3309c = new IndoorOutlineFetcher(this);
            this.f3308b.m6173a(this.f3309c);
            return;
        }
        this.f3308b = null;
        this.f3310d = null;
        this.f3309c = null;
    }

    public final synchronized IndoorLevelOutline m5325a(ac acVar) {
        IndoorLevelOutline indoorLevelOutline;
        indoorLevelOutline = (IndoorLevelOutline) this.f3310d.m6235b((Object) acVar);
        if (indoorLevelOutline == null) {
            m5324b(acVar);
            this.f3311e++;
            indoorLevelOutline = null;
        }
        return indoorLevelOutline;
    }

    public final synchronized void m5326a() {
        this.f3310d.m6231a();
        this.f3312f.clear();
    }

    private synchronized void m5324b(ac acVar) {
        if (!this.f3312f.contains(acVar)) {
            this.f3312f.add(acVar);
            this.f3308b.m6172a(acVar, (TileCallback) this);
        }
    }

    public final void m5328a(ac acVar, int i, aa aaVar) {
        Object obj = null;
        Object obj2 = 1;
        Object obj3 = null;
        int i2;
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                IndoorLevelOutline a;
                if (aaVar instanceof aq) {
                    aq aqVar = (aq) aaVar;
                    IndoorLevelOutline indoorLevelOutline = new IndoorLevelOutline();
                    VectorTile i3 = aqVar.m5643i();
                    while (i3.hasNext()) {
                        bc bcVar = (bc) i3.next();
                        if (bcVar instanceof av) {
                            av avVar = (av) bcVar;
                            if (avVar.m5703j()) {
                                indoorLevelOutline.m5312a(avVar);
                            }
                        }
                    }
                    a = indoorLevelOutline.m5311a();
                } else {
                    a = f3307a;
                }
                IndoorLevelOutline indoorLevelOutline2 = a;
                i2 = 1;
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                obj3 = f3307a;
                i2 = 1;
                break;
            default:
                obj2 = null;
                break;
        }
        if (obj != null) {
            synchronized (this) {
                this.f3310d.m6239c(acVar, obj3);
            }
        }
        if (obj2 != null) {
            for (IndoorOutlineFetcher b : this.f3313g) {
                b.m5323b();
            }
            synchronized (this) {
                this.f3312f.remove(acVar);
            }
        }
    }

    public final void m5327a(IndoorOutlineFetcher indoorOutlineFetcher) {
        this.f3313g.add(indoorOutlineFetcher);
    }

    public final void m5329b(IndoorOutlineFetcher indoorOutlineFetcher) {
        this.f3313g.remove(indoorOutlineFetcher);
    }
}
