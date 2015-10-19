package com.google.android.m4b.maps.av;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import com.google.android.m4b.maps.ag.OneShotTileCoordGeneratorFactory;
import com.google.android.m4b.maps.ag.SingleZoomTileCoordGenerator;
import com.google.android.m4b.maps.ag.TileCoordGenerator;
import com.google.android.m4b.maps.ag.TileCoordGeneratorFactory;
import com.google.android.m4b.maps.ag.TileCoordGeneratorProvider;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.af;
import com.google.android.m4b.maps.an.ah;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.ar;
import com.google.android.m4b.maps.an.ay;
import com.google.android.m4b.maps.as.FetchRequestProvider;
import com.google.android.m4b.maps.as.TileFetcher;
import com.google.android.m4b.maps.au.Bitmask;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.av.RenderPass.RenderPass;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.aw.GLTileCacheManager.GLTileCacheManager;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ba.GLTile;
import com.google.android.m4b.maps.ba.GLVectorTile;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p028c.bk;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.av.m */
public class TileOverlay extends al {
    private static final TileCoordGeneratorProvider f4146M;
    private static final Bitmask f4147d;
    private static final Bitmask f4148e;
    private volatile boolean f4149A;
    private final Set<TileOverlay> f4150B;
    private final LRUCache<ac, ac> f4151C;
    private final Point f4152D;
    private CameraPosition f4153E;
    private long f4154F;
    private long f4155G;
    private long f4156H;
    private boolean f4157I;
    private final boolean f4158J;
    private final boolean f4159K;
    private final TileCoordGeneratorProvider f4160L;
    private int f4161N;
    private long f4162O;
    private final Set<GLTile> f4163P;
    private final ah f4164Q;
    private WeakReference<GLState> f4165R;
    protected volatile RepaintCallback f4166a;
    protected volatile boolean f4167b;
    protected final LabelSource f4168c;
    private final int f4169f;
    private final int f4170g;
    private final GLOverlay f4171h;
    private boolean f4172i;
    private final int f4173j;
    private final int f4174k;
    private final boolean f4175l;
    private final boolean f4176m;
    private final boolean f4177n;
    private final boolean f4178o;
    private final ai f4179p;
    private final TileFetcher f4180q;
    private final ArrayList<GLTile> f4181r;
    private final ArrayList<GLTile> f4182s;
    private final int[] f4183t;
    private final ArrayList<HashSet<ac>> f4184u;
    private final int[] f4185v;
    private final TileOverlay f4186w;
    private TileCoordGenerator f4187x;
    private SingleZoomTileCoordGenerator f4188y;
    private boolean f4189z;

    /* renamed from: com.google.android.m4b.maps.av.m.a */
    public interface TileOverlay {
        boolean m7217a();
    }

    /* renamed from: com.google.android.m4b.maps.av.m.1 */
    class TileOverlay implements TileFetcher.TileFetcher {
        private /* synthetic */ TileOverlay f4638a;

        TileOverlay(TileOverlay tileOverlay) {
            this.f4638a = tileOverlay;
        }

        public final void m7270a(ac acVar, GLTile gLTile, boolean z) {
            synchronized (this.f4638a.f4180q) {
                if (!(this.f4638a.f4151C.m6238c(acVar) == null || gLTile == null)) {
                    gLTile.m7849d();
                }
            }
            if (gLTile != null) {
                TileOverlay.m6726c(this.f4638a);
            }
            RepaintCallback repaintCallback = this.f4638a.f4166a;
            if (repaintCallback == null) {
                return;
            }
            if (gLTile != null || !z) {
                repaintCallback.m6765a(true, false);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.m.b */
    public static class TileOverlay implements Comparator<ac> {
        private int f4639a;
        private int f4640b;

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
            return (Math.abs(b2 - this.f4639a) + Math.abs(f - this.f4640b)) - (Math.abs(b - this.f4640b) + Math.abs(e - this.f4639a));
        }

        public final void m7271a(Point point) {
            this.f4639a = point.m5958f();
            this.f4640b = point.m5960g();
        }
    }

    static /* synthetic */ void m6726c(TileOverlay tileOverlay) {
        tileOverlay.f4161N++;
        if (SystemClock.elapsedRealtime() > tileOverlay.f4162O) {
            int i = tileOverlay.f4161N;
            UserEventReporter.m11502a(110, "l", UserEventReporter.m11500a(new String[]{"t=" + tileOverlay.f4179p.f3448B, "n=" + i}));
            tileOverlay.f4161N = 0;
            tileOverlay.f4162O = SystemClock.elapsedRealtime() + 4000;
        }
    }

    static {
        f4147d = Bitmask.m6608a(4);
        f4148e = Bitmask.m6607a(GLVectorTile.f5363a, f4147d);
        f4146M = new TileCoordGeneratorFactory();
    }

    public static int m6715a(Resources resources, int i) {
        int i2 = resources.getDisplayMetrics().widthPixels * resources.getDisplayMetrics().heightPixels;
        if (i2 < 409920) {
            i2 = 409920;
        }
        float f = (float) (i / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        return (int) (((float) ((i2 * 24) / 409920)) / (f * f));
    }

    public static int m6722b(Resources resources, int i) {
        return (((int) Math.floor((double) (((float) resources.getDisplayMetrics().widthPixels) / 128.0f))) + 2) * (((int) Math.floor((double) (((float) resources.getDisplayMetrics().heightPixels) / 128.0f))) + 2);
    }

    public static TileOverlay m6720a(ai aiVar, Resources resources) {
        Object obj = (aiVar == ai.f3423a || aiVar == ai.f3424b || aiVar == ai.f3425c) ? 1 : null;
        int a = TileOverlay.m6715a(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        int i = a * 2;
        boolean z = obj != null || aiVar == ai.f3432j;
        boolean z2 = z || aiVar == ai.f3437o || aiVar == ai.f3438p;
        Bitmask bitmask = obj != null ? f4148e : Bitmask.f4072a;
        boolean z3 = z && z2;
        return new TileOverlay(aiVar, new TileFetcher(aiVar, new GLTileCacheManager(aiVar, null, bitmask), new FetchRequestProvider(8, i, z3, false), bitmask), new OneShotTileCoordGeneratorFactory(), a, i, 8, GLOverlay.VECTORS, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, false, false, z, z, true, z2);
    }

    public static TileOverlay m6719a(TileCoordGeneratorProvider tileCoordGeneratorProvider, ai aiVar, Resources resources) {
        int a = TileOverlay.m6715a(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        int i = a * 2;
        return new BuildingTileOverlay(aiVar, new TileFetcher(aiVar, new GLTileCacheManager(aiVar, null, f4147d), new FetchRequestProvider(0, i, false, false), f4147d), tileCoordGeneratorProvider, a, i, 0, GLOverlay.BUILDINGS, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, false, false, false, false, false, false);
    }

    public static TileOverlay m6724b(ai aiVar, Resources resources) {
        int b = TileOverlay.m6722b(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        int i = b * 2;
        return new TileOverlay(aiVar, new TileFetcher(aiVar, new FetchRequestProvider(4, i, false, true)), f4146M, b, i, 4, GLOverlay.BASE_IMAGERY, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, 384, true, true, false, false, false, false);
    }

    public static ag m6725c(ai aiVar, Resources resources) {
        int b = TileOverlay.m6722b(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        int i = b * 2;
        return new ag(aiVar, new TileFetcher(aiVar, new FetchRequestProvider(4, i, false, true)), f4146M, b, i, 4, GLOverlay.LAYER_SHAPES, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, 384, false, true, false, false, false, false, resources);
    }

    public static TrafficTileOverlay m6721a(Resources resources, ai aiVar) {
        int a = TileOverlay.m6715a(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        int i = a * 2;
        return new TrafficTileOverlay(new TileFetcher(aiVar, new FetchRequestProvider(0, i, false, true)), f4146M, a, i, 0, GLOverlay.TRAFFIC, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, true, false, false, false, false);
    }

    public static TileOverlay m6718a(Context context, Resources resources, IndoorState indoorState) {
        int a = TileOverlay.m6715a(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) * 2;
        int i = a * 2;
        TileOverlay indoorTileOverlay = new IndoorTileOverlay(new TileFetcher(ai.f3436n, new FetchRequestProvider(4, i, false, true)), f4146M, a, i, 4, GLOverlay.INDOOR, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, context, indoorState);
        indoorTileOverlay.m7144f();
        return indoorTileOverlay;
    }

    protected void m6732a(TileCoordGenerator tileCoordGenerator) {
        this.f4187x = tileCoordGenerator;
        this.f4155G = -1;
    }

    public final void m6731a(long j) {
        this.f4180q.m6564a(j);
    }

    public final void m6738a(GLState gLState, RepaintCallback repaintCallback) {
        this.f4165R = new WeakReference(gLState);
        this.f4180q.m6569a(gLState);
        this.f4166a = repaintCallback;
        if (this.f4187x == null) {
            m6732a(this.f4160L.m4900a(this.f4179p, this.f4174k, this.f4178o, this.f4164Q));
            this.f4188y = this.f4160L.m4899a(this.f4179p, this.f4178o, this.f4164Q);
            if (this.f4188y != null) {
                return;
            }
            if (this.f4187x instanceof SingleZoomTileCoordGenerator) {
                this.f4188y = (SingleZoomTileCoordGenerator) this.f4187x;
                return;
            }
            throw new IllegalStateException("Bad out-of-bounds coord generator");
        }
    }

    public void m6737a(GLState gLState) {
        this.f4165R = null;
        this.f4166a = null;
        this.f4180q.m6579h();
        this.f4149A = true;
    }

    public final void m6741a(boolean z) {
        this.f4180q.m6571a(z);
        this.f4149A = true;
    }

    public final void h_() {
        this.f4180q.m6577f();
        this.f4149A = true;
    }

    public final void m6744b(boolean z) {
        this.f4172i = z;
    }

    public final void m6733a(ai aiVar) {
        this.f4180q.m6567a(aiVar);
        this.f4149A = true;
    }

    public final boolean m6747j() {
        return this.f4158J;
    }

    public final boolean m6748k() {
        return this.f4159K;
    }

    public final void m6736a(CameraPosition cameraPosition) {
        this.f4153E = cameraPosition;
    }

    TileOverlay(ai aiVar, TileFetcher tileFetcher, TileCoordGeneratorProvider tileCoordGeneratorProvider, int i, int i2, int i3, GLOverlay gLOverlay, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.f4186w = new TileOverlay();
        this.f4150B = Collections.synchronizedSet(new HashSet());
        this.f4151C = new LRUCache(80);
        this.f4152D = new Point();
        this.f4154F = 0;
        this.f4155G = -1;
        this.f4156H = -1;
        this.f4167b = false;
        this.f4163P = bk.m2880c();
        this.f4179p = aiVar;
        this.f4180q = tileFetcher;
        this.f4160L = tileCoordGeneratorProvider;
        this.f4169f = i;
        this.f4170g = i2;
        this.f4171h = gLOverlay;
        this.f4173j = i4;
        this.f4174k = i5;
        this.f4178o = z;
        this.f4175l = z2;
        this.f4176m = z3;
        this.f4177n = z4;
        this.f4158J = z5;
        this.f4159K = z6;
        this.f4164Q = new ah();
        this.f4180q.m6566a(this.f4164Q);
        if (!this.f4158J || aiVar.m5514e()) {
            this.f4168c = null;
        } else {
            this.f4168c = new LabelSource(toString());
        }
        this.f4182s = new ArrayList();
        this.f4181r = new ArrayList();
        this.f4183t = new int[i];
        this.f4184u = new ArrayList(i3);
        for (int i6 = 0; i6 < i3; i6++) {
            this.f4184u.add(new HashSet());
        }
        this.f4185v = new int[(i3 + 1)];
        this.f4161N = 0;
        this.f4162O = SystemClock.elapsedRealtime() + 4000;
        this.f4180q.m6568a(new TileOverlay(this));
    }

    public final void m6734a(TileOverlay tileOverlay) {
        this.f4150B.add(tileOverlay);
    }

    public final float m6727a(Point point) {
        if (this.f4187x == null) {
            return 21.0f;
        }
        return this.f4187x.m4872a(point);
    }

    public final boolean m6742a(af afVar) {
        return this.f4164Q.m5477a(afVar);
    }

    public final ai i_() {
        return this.f4179p;
    }

    public final GLOverlay m6745d() {
        return this.f4171h;
    }

    protected RenderPass m6749m() {
        return RenderPass.BASE;
    }

    public final void m6730a(int i) {
        this.f4189z = (i & 2) != 0;
    }

    protected Set<ac> m6729a(Camera camera) {
        return com.google.p025a.p028c.ai.m2296g();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m6743a(com.google.android.m4b.maps.ax.Camera r14, com.google.android.m4b.maps.ay.GLState r15) {
        /*
        r13 = this;
        r0 = 0;
        r13.f4167b = r0;
        r0 = 1;
        r13.f4157I = r0;
        r0 = r13.f4152D;
        r14.m7425a(r0);
        r0 = r13.f4187x;
        r7 = r0.m4876a(r14);
        r0 = r7.size();
        r1 = 1;
        if (r0 <= r1) goto L_0x0026;
    L_0x0018:
        r0 = r13.f4186w;
        r1 = r14.m7434c();
        r0.m7271a(r1);
        r0 = r13.f4186w;
        java.util.Collections.sort(r7, r0);
    L_0x0026:
        r0 = r13.f4173j;
        r0 = (float) r0;
        r1 = r14.m7439g();
        r0 = r0 * r1;
        r8 = (int) r0;
        r0 = r13.f4163P;
        r1 = r13.f4182s;
        r0.addAll(r1);
        r0 = r13.f4182s;
        r0.clear();
        r0 = r13.f4181r;
        r0.clear();
        r0 = r13.f4185v;
        r1 = 0;
        java.util.Arrays.fill(r0, r1);
        r0 = r13.f4184u;
        r9 = r0.size();
        r10 = com.google.p025a.p028c.bk.m2870a();
        r0 = r13.f4180q;
        r0 = r0.m6578g();
        r1 = r13.f4189z;
        if (r1 == 0) goto L_0x010b;
    L_0x005a:
        r0 = r13.f4153E;
        r1 = 0;
        r13.f4153E = r1;
        if (r0 == 0) goto L_0x0105;
    L_0x0061:
        r3 = new com.google.android.m4b.maps.ax.b;
        r1 = r14.m7437e();
        r2 = r14.m7437e();
        r4 = r14.m7439g();
        r3.<init>(r0, r1, r2, r4);
        r0 = r13.f4180q;
        r1 = r13.f4187x;
        r2 = new com.google.android.m4b.maps.an.g;
        r4 = r3.m7430b();
        r2.<init>(r4);
        r4 = r13.f4187x;
        r3 = r4.m4876a(r3);
        r4 = 0;
        r5 = 0;
        r6 = r13.f4189z;
        r0.m6565a(r1, r2, r3, r4, r5, r6);
    L_0x008c:
        r0 = r13.f4180q;
        r4 = r0.m6572b();
        r5 = com.google.android.m4b.maps.aw.GLTileCacheManager.m7380a();
        monitor-enter(r5);
        r0 = r13.f4180q;	 Catch:{ all -> 0x0102 }
        r0.m6574c();	 Catch:{ all -> 0x0102 }
        r0 = 0;
        r2 = r0;
        r3 = r7;
    L_0x009f:
        if (r2 > r9) goto L_0x0189;
    L_0x00a1:
        if (r2 != r9) goto L_0x0163;
    L_0x00a3:
        r0 = 0;
        r1 = r0;
    L_0x00a5:
        r3 = r3.iterator();	 Catch:{ all -> 0x0102 }
    L_0x00a9:
        r0 = r3.hasNext();	 Catch:{ all -> 0x0102 }
        if (r0 == 0) goto L_0x0171;
    L_0x00af:
        r0 = r3.next();	 Catch:{ all -> 0x0102 }
        r0 = (com.google.android.m4b.maps.an.ac) r0;	 Catch:{ all -> 0x0102 }
        r6 = r13.f4180q;	 Catch:{ all -> 0x0102 }
        r6 = r6.m6563a(r0);	 Catch:{ all -> 0x0102 }
        if (r6 == 0) goto L_0x00e5;
    L_0x00bd:
        r11 = r13.f4182s;	 Catch:{ all -> 0x0102 }
        r11.add(r6);	 Catch:{ all -> 0x0102 }
        r11 = r13.f4165R;	 Catch:{ all -> 0x0102 }
        r11.get();	 Catch:{ all -> 0x0102 }
        r11 = r13.f4163P;	 Catch:{ all -> 0x0102 }
        r11 = r11.remove(r6);	 Catch:{ all -> 0x0102 }
        if (r11 != 0) goto L_0x00d3;
    L_0x00cf:
        r11 = 1;
        r6.m7841a(r11);	 Catch:{ all -> 0x0102 }
    L_0x00d3:
        r11 = r13.f4185v;	 Catch:{ all -> 0x0102 }
        r12 = r11[r2];	 Catch:{ all -> 0x0102 }
        r12 = r12 + 1;
        r11[r2] = r12;	 Catch:{ all -> 0x0102 }
        r11 = r13.f4182s;	 Catch:{ all -> 0x0102 }
        r11 = r11.size();	 Catch:{ all -> 0x0102 }
        r12 = r13.f4169f;	 Catch:{ all -> 0x0102 }
        if (r11 == r12) goto L_0x0171;
    L_0x00e5:
        if (r6 == 0) goto L_0x00ed;
    L_0x00e7:
        r6 = r6.m7848c();	 Catch:{ all -> 0x0102 }
        if (r6 == 0) goto L_0x00a9;
    L_0x00ed:
        if (r1 == 0) goto L_0x00fc;
    L_0x00ef:
        r6 = r13.f4187x;	 Catch:{ all -> 0x0102 }
        r11 = r13.f4152D;	 Catch:{ all -> 0x0102 }
        r6 = r6.m4874a(r0, r11);	 Catch:{ all -> 0x0102 }
        if (r6 == 0) goto L_0x00fc;
    L_0x00f9:
        r1.add(r6);	 Catch:{ all -> 0x0102 }
    L_0x00fc:
        if (r2 != 0) goto L_0x00a9;
    L_0x00fe:
        r10.add(r0);	 Catch:{ all -> 0x0102 }
        goto L_0x00a9;
    L_0x0102:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0105:
        r0 = r13.f4180q;
        r0.m6576e();
        goto L_0x008c;
    L_0x010b:
        r2 = r13.f4154F;
        r4 = r14.m7421a();
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 != 0) goto L_0x0133;
    L_0x0115:
        r2 = r13.f4155G;
        r1 = r13.f4187x;
        r4 = r1.m4873a();
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 != 0) goto L_0x0133;
    L_0x0121:
        r2 = r13.f4156H;
        r1 = r13.f4164Q;
        r4 = r1.m5480b();
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 != 0) goto L_0x0133;
    L_0x012d:
        r1 = r13.f4149A;
        if (r1 != 0) goto L_0x0133;
    L_0x0131:
        if (r0 == 0) goto L_0x008c;
    L_0x0133:
        r4 = r13.m6729a(r14);
        r0 = r13.f4180q;
        r1 = r13.f4187x;
        r2 = new com.google.android.m4b.maps.an.g;
        r3 = r14.m7430b();
        r2.<init>(r3);
        r5 = 0;
        r6 = r13.f4189z;
        r3 = r7;
        r0.m6565a(r1, r2, r3, r4, r5, r6);
        r0 = r14.m7421a();
        r13.f4154F = r0;
        r0 = r13.f4187x;
        r0 = r0.m4873a();
        r13.f4155G = r0;
        r0 = r13.f4164Q;
        r0 = r0.m5480b();
        r13.f4156H = r0;
        goto L_0x008c;
    L_0x0163:
        r0 = r13.f4184u;	 Catch:{ all -> 0x0102 }
        r0 = r0.get(r2);	 Catch:{ all -> 0x0102 }
        r0 = (java.util.Set) r0;	 Catch:{ all -> 0x0102 }
        r0.clear();	 Catch:{ all -> 0x0102 }
        r1 = r0;
        goto L_0x00a5;
    L_0x0171:
        r0 = r13.f4182s;	 Catch:{ all -> 0x0102 }
        r0 = r0.size();	 Catch:{ all -> 0x0102 }
        r3 = r13.f4169f;	 Catch:{ all -> 0x0102 }
        if (r0 == r3) goto L_0x0189;
    L_0x017b:
        if (r1 == 0) goto L_0x0189;
    L_0x017d:
        r0 = r1.size();	 Catch:{ all -> 0x0102 }
        if (r0 == 0) goto L_0x0189;
    L_0x0183:
        r0 = r2 + 1;
        r2 = r0;
        r3 = r1;
        goto L_0x009f;
    L_0x0189:
        r0 = r13.f4180q;	 Catch:{ all -> 0x0102 }
        r0.m6575d();	 Catch:{ all -> 0x0102 }
        monitor-exit(r5);	 Catch:{ all -> 0x0102 }
        r0 = r13.f4185v;
        r1 = 0;
        r0 = r0[r1];
        r1 = r7.size();
        if (r0 != r1) goto L_0x01d0;
    L_0x019a:
        r0 = 1;
    L_0x019b:
        r13.f4167b = r0;
        r0 = r13.f4172i;
        if (r0 == 0) goto L_0x0207;
    L_0x01a1:
        r1 = r10.iterator();
    L_0x01a5:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x01d2;
    L_0x01ab:
        r0 = r1.next();
        r0 = (com.google.android.m4b.maps.an.ac) r0;
        r2 = 0;
        r3 = r14.m7444l();
        r3 = java.lang.Math.round(r3);
        r5 = r0.m5439b();
        r3 = r3 - r5;
        r2 = java.lang.Math.max(r2, r3);
        r3 = r13.f4181r;
        r5 = new com.google.android.m4b.maps.ba.e;
        r2 = r8 << r2;
        r5.<init>(r0, r2);
        r3.add(r5);
        goto L_0x01a5;
    L_0x01d0:
        r0 = 0;
        goto L_0x019b;
    L_0x01d2:
        r0 = r13.f4188y;
        r0 = r0.m4910b(r14);
        r1 = r0.iterator();
    L_0x01dc:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0207;
    L_0x01e2:
        r0 = r1.next();
        r0 = (com.google.android.m4b.maps.an.ac) r0;
        r2 = 0;
        r3 = r14.m7444l();
        r3 = java.lang.Math.round(r3);
        r5 = r0.m5439b();
        r3 = r3 - r5;
        r2 = java.lang.Math.max(r2, r3);
        r3 = r13.f4181r;
        r5 = new com.google.android.m4b.maps.ba.e;
        r2 = r8 << r2;
        r5.<init>(r0, r2);
        r3.add(r5);
        goto L_0x01dc;
    L_0x0207:
        r0 = r13.f4189z;
        r13.f4149A = r0;
        r0 = r13.f4180q;
        r0 = r0.m6572b();
        r0 = r0 - r4;
        r1 = r13.f4149A;
        if (r1 != 0) goto L_0x023f;
    L_0x0216:
        if (r0 != 0) goto L_0x023f;
    L_0x0218:
        r1 = r13.f4150B;
        monitor-enter(r1);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x023c }
        r2 = r13.f4150B;	 Catch:{ all -> 0x023c }
        r0.<init>(r2);	 Catch:{ all -> 0x023c }
        monitor-exit(r1);	 Catch:{ all -> 0x023c }
        r1 = r0.iterator();
    L_0x0227:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x023f;
    L_0x022d:
        r0 = r1.next();
        r0 = (com.google.android.m4b.maps.av.TileOverlay.TileOverlay) r0;
        r2 = r13.f4182s;
        r2.isEmpty();
        r0.m7217a();
        goto L_0x0227;
    L_0x023c:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x023f:
        r0 = r13.f4163P;
        r1 = r0.iterator();
    L_0x0245:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0256;
    L_0x024b:
        r0 = r1.next();
        r0 = (com.google.android.m4b.maps.ba.GLTile) r0;
        r2 = 0;
        r0.m7841a(r2);
        goto L_0x0245;
    L_0x0256:
        r0 = r13.f4163P;
        r0.clear();
        r0 = 1;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.m.a(com.google.android.m4b.maps.ax.b, com.google.android.m4b.maps.ay.e):boolean");
    }

    public final List<GLTile> m6750n() {
        return this.f4182s;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m6739a(com.google.android.m4b.maps.ay.GLState r24, com.google.android.m4b.maps.ax.Camera r25, com.google.android.m4b.maps.av.ad r26) {
        /*
        r23 = this;
        r3 = r26.m6704b();
        if (r3 <= 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r15 = r26.m6701a();
        r16 = r26.m6705c();
        r17 = new com.google.android.m4b.maps.av.ad;
        r0 = r17;
        r1 = r26;
        r0.<init>(r1);
        r3 = r16.m7215f();
        if (r3 == 0) goto L_0x00be;
    L_0x001e:
        r0 = r23;
        r3 = r0.f4149A;
        if (r3 != 0) goto L_0x004e;
    L_0x0024:
        r0 = r23;
        r3 = r0.f4182s;
        r4 = r3.iterator();
    L_0x002c:
        r3 = r4.hasNext();
        if (r3 == 0) goto L_0x00a8;
    L_0x0032:
        r3 = r4.next();
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r0 = r23;
        r5 = r0.f4180q;
        r5 = r5.m6580i();
        r3 = r3.m7844a(r5);
        if (r3 == 0) goto L_0x002c;
    L_0x0046:
        r3 = 1;
    L_0x0047:
        if (r3 == 0) goto L_0x004e;
    L_0x0049:
        r3 = 1;
        r0 = r23;
        r0.f4149A = r3;
    L_0x004e:
        r0 = r23;
        r3 = r0.f4149A;
        if (r3 == 0) goto L_0x0063;
    L_0x0054:
        r0 = r23;
        r3 = r0.f4189z;
        if (r3 != 0) goto L_0x0063;
    L_0x005a:
        r0 = r23;
        r1 = r25;
        r2 = r24;
        r0.m6743a(r1, r2);
    L_0x0063:
        r0 = r23;
        r3 = r0.f4181r;
        r3 = r3.size();
        if (r3 <= 0) goto L_0x00ad;
    L_0x006d:
        r24.m7543z();
        r3 = 1;
        r0 = r17;
        r0.m6702a(r3);
        r0 = r23;
        r3 = r0.f4181r;
        r4 = 0;
        r3 = r3.get(r4);
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r0 = r24;
        r1 = r25;
        r2 = r17;
        r3.m7846b(r0, r1, r2);
        r0 = r23;
        r3 = r0.f4181r;
        r4 = r3.iterator();
    L_0x0092:
        r3 = r4.hasNext();
        if (r3 == 0) goto L_0x00aa;
    L_0x0098:
        r3 = r4.next();
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r0 = r24;
        r1 = r25;
        r2 = r26;
        r3.m6659a(r0, r1, r2);
        goto L_0x0092;
    L_0x00a8:
        r3 = 0;
        goto L_0x0047;
    L_0x00aa:
        r24.m7503A();
    L_0x00ad:
        r0 = r23;
        r3 = r0.f4157I;
        if (r3 == 0) goto L_0x00be;
    L_0x00b3:
        r0 = r23;
        r3 = r0.f4180q;
        r0 = r23;
        r4 = r0.f4182s;
        r3.m6570a(r4);
    L_0x00be:
        r0 = r23;
        r3 = r0.f4176m;
        if (r3 == 0) goto L_0x01ba;
    L_0x00c4:
        r3 = com.google.android.m4b.maps.av.ac.NORMAL;
        if (r15 == r3) goto L_0x00cc;
    L_0x00c8:
        r3 = com.google.android.m4b.maps.av.ac.NIGHT;
        if (r15 != r3) goto L_0x01ba;
    L_0x00cc:
        r0 = r23;
        r3 = r0.f4181r;
        r3 = r3.isEmpty();
        if (r3 == 0) goto L_0x00e7;
    L_0x00d6:
        r0 = r23;
        r3 = r0.f4185v;
        r4 = 0;
        r3 = r3[r4];
        r0 = r23;
        r4 = r0.f4182s;
        r4 = r4.size();
        if (r3 >= r4) goto L_0x01ba;
    L_0x00e7:
        r3 = r16.m7212c();
        if (r3 == 0) goto L_0x01ba;
    L_0x00ed:
        r3 = 1;
        r5 = r3;
    L_0x00ef:
        r3 = r24.m7509G();
        if (r3 <= 0) goto L_0x01be;
    L_0x00f5:
        r0 = r23;
        r3 = r0.f4179p;
        r3 = r3.m5514e();
        if (r3 == 0) goto L_0x01be;
    L_0x00ff:
        r0 = r23;
        r3 = r0.f4159K;
        if (r3 == 0) goto L_0x01be;
    L_0x0105:
        r3 = r24.m7510H();
        if (r3 == 0) goto L_0x01be;
    L_0x010b:
        r3 = 1;
        r14 = r3;
    L_0x010d:
        if (r14 != 0) goto L_0x01c2;
    L_0x010f:
        r3 = r24.m7509G();
        if (r3 <= 0) goto L_0x01c2;
    L_0x0115:
        r0 = r23;
        r3 = r0.f4179p;
        r4 = com.google.android.m4b.maps.an.ai.f3446x;
        r3 = r3.equals(r4);
        if (r3 != 0) goto L_0x012d;
    L_0x0121:
        r0 = r23;
        r3 = r0.f4179p;
        r4 = com.google.android.m4b.maps.an.ai.f3445w;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x01c2;
    L_0x012d:
        r3 = 1;
        r13 = r3;
    L_0x012f:
        if (r14 != 0) goto L_0x0133;
    L_0x0131:
        if (r13 == 0) goto L_0x0136;
    L_0x0133:
        r24.m7539v();
    L_0x0136:
        r0 = r23;
        r3 = r0.f4182s;
        r8 = r3.size();
        r0 = r23;
        r3 = r0.f4184u;
        r4 = r3.size();
        r6 = 0;
        r18 = r16.m7213d();
        if (r13 == 0) goto L_0x01d3;
    L_0x014d:
        r3 = r24.m7541x();
        r7 = 7681; // 0x1e01 float:1.0763E-41 double:3.795E-320;
        r9 = 7681; // 0x1e01 float:1.0763E-41 double:3.795E-320;
        r10 = 7681; // 0x1e01 float:1.0763E-41 double:3.795E-320;
        r3.glStencilOp(r7, r9, r10);
        r3 = r24.m7541x();
        r7 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r3.glStencilMask(r7);
        r0 = r23;
        r3 = r0.f4182s;
        r3 = r3.size();
        r3 = r3 + -1;
        r7 = r3;
    L_0x016e:
        if (r7 < 0) goto L_0x01c6;
    L_0x0170:
        r0 = r23;
        r3 = r0.f4182s;
        r3 = r3.get(r7);
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r9 = r24.m7541x();
        r10 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r11 = r3.m7845b();
        r11 = r11.m5439b();
        r12 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r9.glStencilFunc(r10, r11, r12);
        r9 = r24.m7541x();
        r9.glPushMatrix();
        r3 = r3.m7845b();
        r3 = r3.m5446i();
        r9 = r3.m6050c();
        r3 = r3.m6053f();
        r3 = (float) r3;
        r0 = r24;
        r1 = r25;
        com.google.android.m4b.maps.av.Transform.m7275a(r0, r1, r9, r3);
        com.google.android.m4b.maps.ba.GLTileBounds.m8114a(r24);
        r3 = r24.m7541x();
        r3.glPopMatrix();
        r3 = r7 + -1;
        r7 = r3;
        goto L_0x016e;
    L_0x01ba:
        r3 = 0;
        r5 = r3;
        goto L_0x00ef;
    L_0x01be:
        r3 = 0;
        r14 = r3;
        goto L_0x010d;
    L_0x01c2:
        r3 = 0;
        r13 = r3;
        goto L_0x012f;
    L_0x01c6:
        r3 = r24.m7541x();
        r7 = 7680; // 0x1e00 float:1.0762E-41 double:3.7944E-320;
        r9 = 7680; // 0x1e00 float:1.0762E-41 double:3.7944E-320;
        r10 = 7680; // 0x1e00 float:1.0762E-41 double:3.7944E-320;
        r3.glStencilOp(r7, r9, r10);
    L_0x01d3:
        r9 = r4;
    L_0x01d4:
        if (r9 < 0) goto L_0x03bb;
    L_0x01d6:
        r0 = r23;
        r3 = r0.f4185v;
        r3 = r3[r9];
        if (r3 <= 0) goto L_0x03e0;
    L_0x01de:
        r0 = r23;
        r3 = r0.f4185v;
        r3 = r3[r9];
        r7 = r8 - r3;
        if (r5 == 0) goto L_0x0252;
    L_0x01e8:
        r4 = r7;
    L_0x01e9:
        if (r4 >= r8) goto L_0x0252;
    L_0x01eb:
        r0 = r23;
        r3 = r0.f4182s;
        r3 = r3.get(r4);
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r10 = r3.m7848c();
        if (r10 != 0) goto L_0x024e;
    L_0x01fb:
        r10 = r3.m7845b();
        r10 = r10.m5439b();
        r11 = 14;
        if (r10 < r11) goto L_0x024e;
    L_0x0207:
        r3 = r3.m7845b();
        r10 = r24.m7541x();
        r11 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r12 = r3.m5439b();
        r11 = r11 >> r12;
        r10.glPushMatrix();
        r3 = r3.m5444g();
        r11 = (float) r11;
        r0 = r24;
        r1 = r25;
        com.google.android.m4b.maps.av.Transform.m7275a(r0, r1, r3, r11);
        r0 = r24;
        r3 = r0.f4851e;
        r0 = r24;
        r3.m7736d(r0);
        r3 = com.google.android.m4b.maps.av.Renderer.m7228a(r15);
        r11 = 0;
        r11 = r3[r11];
        r12 = 1;
        r12 = r3[r12];
        r19 = 2;
        r19 = r3[r19];
        r20 = 3;
        r3 = r3[r20];
        r0 = r19;
        r10.glColor4x(r11, r12, r0, r3);
        r3 = 5;
        r11 = 0;
        r12 = 4;
        r10.glDrawArrays(r3, r11, r12);
        r10.glPopMatrix();
    L_0x024e:
        r3 = r4 + 1;
        r4 = r3;
        goto L_0x01e9;
    L_0x0252:
        if (r14 == 0) goto L_0x02c0;
    L_0x0254:
        r3 = r24.m7541x();
        r4 = 7681; // 0x1e01 float:1.0763E-41 double:3.795E-320;
        r10 = 7681; // 0x1e01 float:1.0763E-41 double:3.795E-320;
        r11 = 7681; // 0x1e01 float:1.0763E-41 double:3.795E-320;
        r3.glStencilOp(r4, r10, r11);
        r3 = r24.m7541x();
        r4 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r3.glStencilMask(r4);
        r4 = r7;
    L_0x026b:
        if (r4 >= r8) goto L_0x02b3;
    L_0x026d:
        r0 = r23;
        r3 = r0.f4182s;
        r3 = r3.get(r4);
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r10 = r24.m7541x();
        r11 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r12 = r4 + 1;
        r19 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r19;
        r10.glStencilFunc(r11, r12, r0);
        r10 = r24.m7541x();
        r10.glPushMatrix();
        r3 = r3.m7845b();
        r3 = r3.m5446i();
        r10 = r3.m6050c();
        r3 = r3.m6053f();
        r3 = (float) r3;
        r0 = r24;
        r1 = r25;
        com.google.android.m4b.maps.av.Transform.m7275a(r0, r1, r10, r3);
        com.google.android.m4b.maps.ba.GLTileBounds.m8114a(r24);
        r3 = r24.m7541x();
        r3.glPopMatrix();
        r3 = r4 + 1;
        r4 = r3;
        goto L_0x026b;
    L_0x02b3:
        r3 = r24.m7541x();
        r4 = 7680; // 0x1e00 float:1.0762E-41 double:3.7944E-320;
        r10 = 7680; // 0x1e00 float:1.0762E-41 double:3.7944E-320;
        r11 = 7680; // 0x1e00 float:1.0762E-41 double:3.7944E-320;
        r3.glStencilOp(r4, r10, r11);
    L_0x02c0:
        r0 = r23;
        r1 = r25;
        r4 = r0.m6716a(r1, r15, r7, r8);
        r3 = 0;
        r11 = r3;
        r12 = r4;
    L_0x02cb:
        if (r12 == 0) goto L_0x03b0;
    L_0x02cd:
        r3 = r12 & 1;
        if (r3 == 0) goto L_0x03a8;
    L_0x02d1:
        r24.m7543z();
        r0 = r17;
        r0.m6702a(r11);
        r0 = r23;
        r3 = r0.f4182s;
        r3 = r3.get(r7);
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r0 = r24;
        r1 = r25;
        r2 = r17;
        r3.m7846b(r0, r1, r2);
        r3 = 1;
        r19 = r3 << r11;
        r10 = r7;
    L_0x02f0:
        if (r10 >= r8) goto L_0x03a5;
    L_0x02f2:
        r0 = r23;
        r3 = r0.f4182s;
        r3 = r3.get(r10);
        r3 = (com.google.android.m4b.maps.ba.GLTile) r3;
        r4 = r3.m7845b();
        r20 = r4.m5447j();
        r4 = 0;
        r0 = r23;
        r0 = r0.f4179p;
        r21 = r0;
        r22 = com.google.android.m4b.maps.an.ai.f3436n;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x032a;
    L_0x0313:
        if (r20 == 0) goto L_0x032a;
    L_0x0315:
        r4 = com.google.android.m4b.maps.an.af.TileParameters.INDOOR;
        r0 = r20;
        r4 = r0.m5470a(r4);
        r4 = (com.google.android.m4b.maps.an.bj) r4;
        if (r4 != 0) goto L_0x039f;
    L_0x0321:
        r4 = 0;
    L_0x0322:
        r0 = r16;
        r4 = r0.m7207a(r4);
        if (r4 == 0) goto L_0x03e2;
    L_0x032a:
        r0 = r23;
        r0 = r0.f4183t;
        r20 = r0;
        r20 = r20[r10];
        r20 = r20 & r19;
        if (r20 == 0) goto L_0x03e2;
    L_0x0336:
        if (r14 == 0) goto L_0x034b;
    L_0x0338:
        r6 = r24.m7541x();
        r20 = 514; // 0x202 float:7.2E-43 double:2.54E-321;
        r21 = r10 + 1;
        r22 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r6.glStencilFunc(r0, r1, r2);
    L_0x034b:
        if (r13 == 0) goto L_0x0369;
    L_0x034d:
        r6 = r24.m7541x();
        r20 = 514; // 0x202 float:7.2E-43 double:2.54E-321;
        r21 = r3.m7845b();
        r21 = r21.m5439b();
        r22 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r6.glStencilFunc(r0, r1, r2);
        r3.m7849d();
    L_0x0369:
        r24.m7543z();
        r6 = r3.m7845b();
        r6 = r6.m5446i();
        r6 = r6.m6050c();
        if (r4 == 0) goto L_0x0383;
    L_0x037a:
        r0 = r24;
        r1 = r25;
        r2 = r17;
        r4.m5295a(r0, r1, r2, r6);
    L_0x0383:
        r0 = r24;
        r1 = r25;
        r2 = r17;
        r3.m6659a(r0, r1, r2);
        if (r4 == 0) goto L_0x0395;
    L_0x038e:
        r0 = r24;
        r1 = r17;
        r4.m5294a(r0, r1);
    L_0x0395:
        r3 = 1;
        r24.m7503A();
    L_0x0399:
        r4 = r10 + 1;
        r10 = r4;
        r6 = r3;
        goto L_0x02f0;
    L_0x039f:
        r4 = r4.m5821b();
        goto L_0x0322;
    L_0x03a5:
        r24.m7503A();
    L_0x03a8:
        r3 = r11 + 1;
        r4 = r12 >>> 1;
        r11 = r3;
        r12 = r4;
        goto L_0x02cb;
    L_0x03b0:
        if (r18 == 0) goto L_0x03b4;
    L_0x03b2:
        if (r6 != 0) goto L_0x03bb;
    L_0x03b4:
        r3 = r7;
    L_0x03b5:
        r4 = r9 + -1;
        r9 = r4;
        r8 = r3;
        goto L_0x01d4;
    L_0x03bb:
        if (r14 != 0) goto L_0x03bf;
    L_0x03bd:
        if (r13 == 0) goto L_0x03c2;
    L_0x03bf:
        r24.m7540w();
    L_0x03c2:
        r3 = r16.m7216g();
        if (r3 == 0) goto L_0x0006;
    L_0x03c8:
        r0 = r23;
        r3 = r0.f4157I;
        if (r3 == 0) goto L_0x0006;
    L_0x03ce:
        r0 = r23;
        r3 = r0.f4180q;
        r0 = r23;
        r4 = r0.f4182s;
        r3.m6573b(r4);
        r3 = 0;
        r0 = r23;
        r0.f4157I = r3;
        goto L_0x0006;
    L_0x03e0:
        r3 = r8;
        goto L_0x03b5;
    L_0x03e2:
        r3 = r6;
        goto L_0x0399;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.m.a(com.google.android.m4b.maps.ay.e, com.google.android.m4b.maps.ax.b, com.google.android.m4b.maps.av.ad):void");
    }

    private int m6716a(Camera camera, ac acVar, int i, int i2) {
        if (acVar == ac.NONE) {
            return 0;
        }
        int i3 = 0;
        while (i < i2) {
            this.f4183t[i] = ((GLTile) this.f4182s.get(i)).m7836a(camera, acVar);
            i++;
            i3 = this.f4183t[i] | i3;
        }
        return this.f4179p.m5507a(i3, acVar);
    }

    public final boolean m6746e() {
        return this.f4167b;
    }

    public final void m6735a(Camera camera, ac acVar, HashSet<String> hashSet, HashSet<String> hashSet2, int[] iArr) {
        m6716a(camera, acVar, this.f4182s.size(), 0);
        Iterator it = this.f4182s.iterator();
        int i = -1;
        while (it.hasNext()) {
            GLTile gLTile = (GLTile) it.next();
            gLTile.m7837a(-1, (Collection) hashSet);
            gLTile.m7840a((Collection) hashSet2);
            int e = gLTile.m7850e();
            if (e <= i) {
                e = i;
            }
            i = e;
        }
        if (iArr != null && iArr.length > 0) {
            iArr[0] = i;
        }
    }

    public final TileCoordGeneratorProvider m6751o() {
        return this.f4160L;
    }

    public int m6728a(ar arVar, RankMergingFeatureIterator rankMergingFeatureIterator, Set<FeatureId> set) {
        Iterator it = this.f4182s.iterator();
        int i = 0;
        while (it.hasNext()) {
            GLTile gLTile = (GLTile) it.next();
            if (gLTile != null && (arVar == null || arVar.m5656b(gLTile.m7845b().m5446i()))) {
                gLTile.m7839a(this.f4168c);
                if (gLTile.m7843a(rankMergingFeatureIterator)) {
                    i = Math.max(i, gLTile.m7845b().m5439b());
                }
            }
            if (gLTile instanceof GLVectorTile) {
                ((GLVectorTile) gLTile).m8138a((Set) set);
            }
        }
        return i;
    }

    public void m6740a(Set<LabelSource> set, Map<LabelSource, ay> map) {
        set.add(this.f4168c);
    }

    public String toString() {
        return Objects.m1809a((Object) this).m1806a("tileType", this.f4179p).m1807a("isBase", this.f4159K).m1804a("maxTilesPerView", this.f4169f).m1804a("maxTilesToFetch", this.f4170g).m1806a("drawOrder", this.f4171h).m1807a("fetchMissingAncestorTiles", this.f4175l).m1807a("allowMultiZoom", this.f4178o).m1807a("prefetchAncestors", this.f4177n).m1804a("tileSize", this.f4173j).m1807a("allowMultiZoom", this.f4178o).m1807a("isContributingLabels", this.f4158J).m1804a("maxTileSize", this.f4174k).toString();
    }
}
