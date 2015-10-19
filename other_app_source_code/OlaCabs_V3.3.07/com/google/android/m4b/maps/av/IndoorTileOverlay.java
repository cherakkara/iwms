package com.google.android.m4b.maps.av;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ag.IndoorTileCoordGenerator;
import com.google.android.m4b.maps.ag.SingleZoomTileCoordGenerator;
import com.google.android.m4b.maps.ag.TileCoordGenerator;
import com.google.android.m4b.maps.ag.TileCoordGeneratorProvider;
import com.google.android.m4b.maps.al.BuildingBoundProvider.BuildingBoundProvider;
import com.google.android.m4b.maps.al.IndoorElevation;
import com.google.android.m4b.maps.al.IndoorOutlineFetcher.IndoorOutlineFetcher;
import com.google.android.m4b.maps.al.IndoorState.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ay;
import com.google.android.m4b.maps.an.az;
import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.an.bi.IndoorLevel;
import com.google.android.m4b.maps.an.bj;
import com.google.android.m4b.maps.ap.IndoorBuildingStore;
import com.google.android.m4b.maps.as.TileFetcher;
import com.google.android.m4b.maps.av.RenderPass.RenderPass;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.ay.ColorUtil;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLTile;
import com.google.android.m4b.maps.bc.KeyedLabelSource;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.be.IndoorLevelInterface;
import com.google.android.m4b.maps.be.cf;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p028c.ai;
import com.google.p025a.p028c.ap;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.bk;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.olacabs.customer.p076d.br;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.av.b */
public final class IndoorTileOverlay extends TileOverlay implements BuildingBoundProvider, IndoorOutlineFetcher, IndoorState, AnimationCallback {
    private volatile boolean f4487d;
    private volatile boolean f4488e;
    private final com.google.android.m4b.maps.al.IndoorState f4489f;
    private final com.google.android.m4b.maps.al.IndoorOutlineFetcher f4490g;
    private IndoorTileCoordGenerator f4491h;
    private final IndoorTileOverlay f4492i;
    private final Set<IndoorElevation> f4493j;
    private volatile Set<FeatureId> f4494k;
    private final Map<LabelSource, az> f4495l;
    private final Map<FeatureId.FeatureId, LabelSource> f4496m;
    private final RenderTweak f4497n;

    /* renamed from: com.google.android.m4b.maps.av.b.1 */
    class IndoorTileOverlay implements RenderTweak {
        private /* synthetic */ IndoorTileOverlay f4475a;

        IndoorTileOverlay(IndoorTileOverlay indoorTileOverlay) {
            this.f4475a = indoorTileOverlay;
        }

        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return 0;
        }

        public final Set<FeatureId> m7110a() {
            return this.f4475a.f4494k;
        }

        public final void m7112a(GLState gLState, Camera camera, ad adVar, Point point) {
        }

        public final void m7111a(GLState gLState, ad adVar) {
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.b.a */
    static class IndoorTileOverlay extends ao {
        private int f4476a;
        private boolean f4477b;
        private float f4478c;
        private long f4479d;
        private final int f4480e;

        public IndoorTileOverlay(GLOverlay gLOverlay, int i) {
            super(gLOverlay);
            this.f4480e = HttpStatus.SC_MULTIPLE_CHOICES;
        }

        public final void m7114b(int i) {
            this.f4476a = i;
        }

        public final synchronized void m7115b(boolean z) {
            long b = Config.m11320a().m11334h().m10152b();
            if (z != this.f4477b) {
                this.f4477b = z;
                long j = (long) this.f4480e;
                float f = this.f4478c;
                if (!this.f4477b) {
                    f = br.DEFAULT_BACKOFF_MULT - f;
                }
                this.f4479d = b - ((long) ((int) (f * ((float) j))));
                this.f4478c = IndoorTileOverlay.m7113a(this.f4479d, this.f4479d, this.f4480e, this.f4477b);
            }
        }

        public final synchronized boolean m7116b() {
            boolean z;
            z = this.f4478c > 0.0f || this.f4477b;
            return z;
        }

        public final synchronized boolean m7117c() {
            boolean z;
            float a = IndoorTileOverlay.m7113a(this.f4479d, Config.m11320a().m11334h().m10152b(), this.f4480e, this.f4477b);
            if ((!this.f4477b || this.f4478c >= br.DEFAULT_BACKOFF_MULT) && (this.f4477b || this.f4478c <= 0.0f)) {
                z = false;
            } else {
                this.f4478c = a;
                a = this.f4478c;
                int i = this.f4476a;
                super.m6870b((((int) (a * ((float) ((i >> 24) & MotionEventCompat.ACTION_MASK)))) << 24) | (i & ViewCompat.MEASURED_SIZE_MASK));
                z = true;
            }
            return z;
        }

        private static float m7113a(long j, long j2, int i, boolean z) {
            float min = Math.min(br.DEFAULT_BACKOFF_MULT, Math.max(0.0f, ((float) (j2 - j)) / ((float) i)));
            return z ? min : br.DEFAULT_BACKOFF_MULT - min;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.b.b */
    static class IndoorTileOverlay implements RenderTweak {
        private final Set<? extends FeatureId> f4481a;

        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return 0;
        }

        public IndoorTileOverlay(Set<FeatureId.FeatureId> set) {
            this.f4481a = ai.m2291a((Collection) set);
        }

        public final Set<? extends FeatureId> m7118a() {
            return this.f4481a;
        }

        public final void m7120a(GLState gLState, Camera camera, ad adVar, Point point) {
            GL10 x = gLState.m7541x();
            if (adVar.m6705c().m7209b() == RenderPass.UNDERGROUND_STENCIL) {
                gLState.m7539v();
                x.glStencilOp(7681, 7681, 7681);
                x.glStencilFunc(519, MotionEventCompat.ACTION_MASK, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                x.glStencilMask(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                x.glTexEnvx(8960, 8704, 7681);
                ColorUtil.m7485a(x, -10461088);
            }
        }

        public final void m7119a(GLState gLState, ad adVar) {
            if (adVar.m6705c().m7209b() == RenderPass.UNDERGROUND_STENCIL) {
                gLState.m7540w();
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.b.c */
    static class IndoorTileOverlay implements TileCoordGeneratorProvider {
        private final TileCoordGeneratorProvider f4482a;
        private final int f4483b;
        private IndoorBuildingStore f4484c;
        private com.google.android.m4b.maps.al.BuildingBoundProvider f4485d;
        private final com.google.android.m4b.maps.al.IndoorState f4486e;

        public IndoorTileOverlay(TileCoordGeneratorProvider tileCoordGeneratorProvider, int i, Context context, com.google.android.m4b.maps.al.IndoorState indoorState) {
            this.f4482a = tileCoordGeneratorProvider;
            this.f4483b = i;
            this.f4486e = indoorState;
        }

        public final TileCoordGenerator m7122a(com.google.android.m4b.maps.an.ai aiVar, int i, boolean z, ah ahVar) {
            TileCoordGenerator a = this.f4482a.m4900a(aiVar, i, z, ahVar);
            if (this.f4484c == null) {
                this.f4484c = IndoorBuildingStore.m6260c();
            }
            if (this.f4485d == null) {
                this.f4485d = this.f4486e.m5367h();
            }
            return new IndoorTileCoordGenerator(a, this.f4485d, this.f4484c, this.f4483b, this.f4486e);
        }

        public final SingleZoomTileCoordGenerator m7121a(com.google.android.m4b.maps.an.ai aiVar, boolean z, ah ahVar) {
            return new SingleZoomTileCoordGenerator(aiVar, ahVar);
        }
    }

    protected IndoorTileOverlay(TileFetcher tileFetcher, TileCoordGeneratorProvider tileCoordGeneratorProvider, int i, int i2, int i3, GLOverlay gLOverlay, int i4, Context context, com.google.android.m4b.maps.al.IndoorState indoorState) {
        super(com.google.android.m4b.maps.an.ai.f3436n, tileFetcher, new IndoorTileOverlay(tileCoordGeneratorProvider, i, context, indoorState), i, i2, 4, gLOverlay, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, false, true, false, true, true, false);
        this.f4487d = false;
        this.f4488e = true;
        this.f4493j = bk.m2870a();
        this.f4494k = ai.m2296g();
        this.f4495l = au.m2807a();
        this.f4496m = au.m2807a();
        ar.m2515a();
        this.f4497n = new IndoorTileOverlay(this);
        IndoorBuildingStore.m6260c();
        this.f4489f = indoorState;
        this.f4490g = new com.google.android.m4b.maps.al.IndoorOutlineFetcher();
        this.f4492i = new IndoorTileOverlay(GLOverlay.NIGHT_DIMMER, HttpStatus.SC_MULTIPLE_CHOICES);
        this.f4492i.m7114b(-2142220208);
    }

    public final void m7144f() {
        this.f4489f.m5348a((IndoorState) this);
        this.f4490g.m5327a((IndoorOutlineFetcher) this);
        this.f4489f.m5367h().m5275a((BuildingBoundProvider) this);
    }

    public final void g_() {
        this.f4489f.m5358b((IndoorState) this);
        this.f4490g.m5329b((IndoorOutlineFetcher) this);
        this.f4489f.m5367h().m5277b(this);
    }

    public final boolean m7139a(Camera camera, GLState gLState) {
        if (this.f4491h != null) {
            FeatureId.FeatureId d;
            if (camera.m7445m() > 17.0f) {
                d = this.f4491h.m4888d(camera);
            } else {
                d = null;
            }
            this.f4489f.m5361c(d);
        }
        if (this.f4491h != null) {
            if (camera.m7445m() > 14.9f) {
                this.f4489f.m5354a(this.f4491h.m4886c(camera));
                for (ac a : this.f4491h.m4882a(camera)) {
                    this.f4490g.m5325a(a);
                }
            } else {
                this.f4489f.m5354a(null);
            }
        }
        this.f4495l.clear();
        if (camera.m7445m() > 14.9f) {
            this.f4487d = false;
            boolean a2 = super.m6743a(camera, gLState);
            this.f4495l.clear();
            this.f4496m.clear();
            if (camera.m7445m() > 17.0f) {
                for (GLTile gLTile : m6750n()) {
                    FeatureId.FeatureId b = ((bj) gLTile.m7845b().m5436a(TileParameters.INDOOR)).m5821b();
                    Object obj = (LabelSource) this.f4496m.get(b);
                    if (obj == null) {
                        obj = m7125a(b);
                        this.f4496m.put(b, obj);
                    }
                    ay a3 = this.f4490g.m5325a(gLTile.m7845b());
                    if (!(a3 == null || a3 == com.google.android.m4b.maps.al.IndoorOutlineFetcher.f3307a)) {
                        az azVar = (az) this.f4495l.get(obj);
                        if (azVar == null) {
                            this.f4495l.put(obj, new az(a3));
                        } else {
                            azVar.m5728a(a3);
                        }
                    }
                }
            }
            return a2;
        }
        this.f4487d = true;
        return true;
    }

    protected final Set<ac> m7131a(Camera camera) {
        if (this.f4491h == null) {
            return ai.m2296g();
        }
        return this.f4491h.m4884b(camera);
    }

    public final boolean m7140a(List<RenderPass> list) {
        return m7127b((List) list);
    }

    private boolean m7127b(List<RenderPass> list) {
        if (!this.f4488e) {
            return false;
        }
        this.f4488e = false;
        list.clear();
        if (this.f4492i.m7116b()) {
            list.add(m6662a(RenderPass.UNDERGROUND_MODE_MASK));
        }
        Set a = bk.m2870a();
        Set a2 = bk.m2870a();
        Set<Object> a3 = bk.m2870a();
        a3.addAll(this.f4489f.m5366g());
        synchronized (this.f4493j) {
            a3.addAll(this.f4493j);
        }
        for (Object obj : a3) {
            Object a4;
            if (obj.m5307c()) {
                a4 = m6664a(RenderPass.ANIMATED_ELEVATED_COLOR, obj);
            } else if (obj.m5306b() < 0.0f) {
                a4 = m6663a(RenderPass.UNDERGROUND_COLOR, com.google.p025a.p028c.ac.m2339a(obj), com.google.p025a.p028c.ac.m2339a(this.f4497n));
            } else {
                a4 = m6663a(RenderPass.ELEVATED_COLOR, com.google.p025a.p028c.ac.m2339a(obj), com.google.p025a.p028c.ac.m2339a(this.f4497n));
            }
            list.add(a4);
            if (obj.m5306b() > 0.0f && !obj.m5307c()) {
                a.addAll(obj.m5310f());
            } else if (obj.m5306b() < 0.0f && !obj.m5307c()) {
                a2.addAll(obj.m5310f());
                list.add(m6664a(RenderPass.DROP_SHADOWS_INNER, obj));
            }
        }
        if (!a.isEmpty()) {
            list.add(m6664a(RenderPass.DROP_SHADOWS_OUTER, new IndoorTileOverlay(a)));
        }
        if (!a2.isEmpty()) {
            list.add(m6664a(RenderPass.UNDERGROUND_STENCIL, new IndoorTileOverlay(a2)));
        }
        return true;
    }

    public final AnimationCallback m7145h() {
        return this;
    }

    public final void m7136a(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6705c().m7209b() == RenderPass.UNDERGROUND_MODE_MASK) {
            if (this.f4492i.m7116b()) {
                this.f4492i.m6869a(gLState, camera, adVar);
            }
        } else if (camera.m7445m() > 14.9f) {
            super.m6739a(gLState, camera, adVar);
        } else {
            this.b = true;
        }
    }

    public final void m7135a(GLState gLState) {
        super.m6737a(gLState);
        this.f4489f.m5361c(null);
        this.f4489f.m5354a(null);
    }

    private void m7128p() {
        if (this.f4491h != null) {
            this.f4491h.m4885b();
            RepaintCallback repaintCallback = this.a;
            if (repaintCallback != null) {
                repaintCallback.m6765a(true, false);
            }
        }
    }

    private void m7129q() {
        IndoorLevelInterface indoorLevelInterface;
        cf c = this.f4489f.m5360c();
        if (c == null) {
            indoorLevelInterface = null;
        } else {
            indoorLevelInterface = this.f4489f.m5355b(c);
        }
        IndoorTileOverlay indoorTileOverlay = this.f4492i;
        boolean z = indoorLevelInterface != null && indoorLevelInterface.m5802a().m11307b() < 0;
        indoorTileOverlay.m7115b(z);
    }

    public final void m7134a(com.google.android.m4b.maps.al.IndoorState indoorState) {
        this.f4487d = true;
        this.f4488e = true;
        m7129q();
        bh c = indoorState.m5360c();
        if (c != null) {
            this.f4494k = ai.m2290a(ap.m2482a(c.m5795b(), IndoorLevel.f3582a));
        } else {
            this.f4494k = ai.m2296g();
        }
        m7128p();
    }

    public final void m7137a(cf cfVar) {
        this.f4487d = true;
        this.f4488e = true;
        m7129q();
        if (this.f4491h != null) {
            IndoorLevelReference a = this.f4489f.m5347a(cfVar.m5783a());
            IndoorLevelReference b = this.f4489f.m5356b(cfVar.m5783a());
            if (!(a == null || b == null || this.f4489f.m5359b(b, a))) {
                this.f4491h.m4887c();
                this.f4489f.m5353a(b, a);
                IndoorElevation a2 = this.f4489f.m5346a(a.m11306a(), true, true, true);
                IndoorElevation a3 = this.f4489f.m5346a(b.m11306a(), true, true, false);
                if (!(a2 == null || a3 == null)) {
                    synchronized (this.f4493j) {
                        for (IndoorElevation d : this.f4493j) {
                            d.m5308d();
                        }
                        if (a2.m5306b() > a3.m5306b()) {
                            a2.m5302a(5);
                            a3.m5302a(24);
                        } else {
                            a2.m5302a(20);
                            a3.m5302a(10);
                        }
                        this.f4493j.clear();
                        this.f4493j.add(a2);
                        this.f4493j.add(a3);
                    }
                    this.f4491h.m4883a(cfVar.m5783a(), b);
                }
            }
        }
        m7128p();
    }

    public final void m7143c() {
        this.f4488e = true;
    }

    protected final void m7133a(TileCoordGenerator tileCoordGenerator) {
        this.f4491h = (IndoorTileCoordGenerator) tileCoordGenerator;
        super.m6732a(tileCoordGenerator);
    }

    public final int m7130a(com.google.android.m4b.maps.an.ar arVar, RankMergingFeatureIterator rankMergingFeatureIterator, Set<FeatureId> set) {
        com.google.android.m4b.maps.al.IndoorState indoorState = this.f4489f;
        List<cf> e = indoorState.m5364e();
        Set a = bk.m2870a();
        for (cf b : e) {
            IndoorLevelInterface b2 = indoorState.m5355b(b);
            if (b2 != null) {
                a.add(b2.m5802a());
            }
        }
        for (GLTile gLTile : m6750n()) {
            if (gLTile != null) {
                FeatureId.FeatureId b3 = ((bj) gLTile.m7845b().m5436a(TileParameters.INDOOR)).m5821b();
                if (a.contains(b3) && (arVar == null || arVar.m5656b(gLTile.m7845b().m5446i()))) {
                    gLTile.m7839a(m7125a(b3));
                    gLTile.m7843a(rankMergingFeatureIterator);
                }
            }
        }
        return 0;
    }

    public final void m7138a(Set<LabelSource> set, Map<LabelSource, ay> map) {
        for (IndoorLevelInterface b : this.f4489f.m5365f()) {
            set.add(m7125a(b.m5803b()));
        }
        map.putAll(this.f4495l);
    }

    private LabelSource m7125a(FeatureId.FeatureId featureId) {
        return new KeyedLabelSource(this.c, featureId, true);
    }

    public final int m7141b(Camera camera) {
        int i = 0;
        if (this.f4492i.m7117c()) {
            i = 2;
        }
        synchronized (this.f4493j) {
            if (this.f4493j.isEmpty()) {
            } else {
                Iterator it = this.f4493j.iterator();
                while (it.hasNext()) {
                    if (!((IndoorElevation) it.next()).m5309e()) {
                        it.remove();
                        this.f4488e = true;
                    }
                    i = 2;
                }
                if (this.f4493j.isEmpty()) {
                    this.f4491h.m4887c();
                    this.f4489f.m5357b();
                    m7128p();
                }
            }
        }
        return i;
    }

    public final CameraPosition m7146i() {
        return null;
    }

    public final void m7142b() {
        m7128p();
    }

    public final void m7132a() {
        m7128p();
    }
}
