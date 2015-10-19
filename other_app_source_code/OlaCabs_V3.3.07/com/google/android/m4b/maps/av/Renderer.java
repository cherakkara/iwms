package com.google.android.m4b.maps.av;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.FloatMath;
import android.widget.TextView;
import com.google.android.m4b.maps.ae.PerformanceProfile;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.at;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.av.TileOverlay.TileOverlay;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.av.as.GmmGLSurfaceView;
import com.google.android.m4b.maps.aw.GLTileCacheManager;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.ax.CameraPositionSanitizer.CameraPositionSanitizer;
import com.google.android.m4b.maps.ay.GL20;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.NativeAllocator;
import com.google.android.m4b.maps.ay.PointPool;
import com.google.android.m4b.maps.ba.GLLabel;
import com.google.android.m4b.maps.ba.GLLineGroup;
import com.google.android.m4b.maps.ba.GLLineMesh;
import com.google.android.m4b.maps.bb.GLHudOverlay;
import com.google.android.m4b.maps.bb.HudCopyright;
import com.google.android.m4b.maps.bc.LabelTheme;
import com.google.android.m4b.maps.bc.Labeler;
import com.google.android.m4b.maps.bd.EntityRenderer;
import com.google.android.m4b.maps.bd.GmmRenderTarget;
import com.google.android.m4b.maps.p040u.ExceptionReporter;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.android.m4b.maps.p058v.Util;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.google.p025a.p028c.bk;
import com.newrelic.agent.android.api.v1.Defaults;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.sothree.slidinguppanel.p086a.R.R;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.av.j */
public final class Renderer implements GmmGLSurfaceView, CameraPositionSanitizer {
    public static volatile boolean f4571a;
    public static final ThreadLocal<PointPool> f4572b;
    private static final int[] f4573c;
    private static final int[] f4574d;
    private static final int[] f4575e;
    private static final int[] f4576f;
    private static final Comparator<al> f4577i;
    private final GLHudOverlay f4578A;
    private HudCopyright f4579B;
    private final HashSet<String> f4580C;
    private final HashSet<String> f4581D;
    private final int[] f4582E;
    private final List<GLLabel> f4583F;
    private long f4584G;
    private boolean f4585H;
    private final TileOverlay f4586I;
    private boolean f4587J;
    private Bitmap f4588K;
    private boolean f4589L;
    private float f4590M;
    private long f4591N;
    private volatile ac f4592O;
    private final List<AnimationCallback> f4593P;
    private final List f4594Q;
    private RenderInstrumentation f4595R;
    private volatile RenderInstrumentation f4596S;
    private volatile boolean f4597T;
    private long f4598U;
    private int f4599V;
    private volatile TileOverlay f4600W;
    private boolean f4601X;
    private volatile float f4602Y;
    private final Object f4603Z;
    private boolean aa;
    private volatile int ab;
    private boolean ac;
    private int ad;
    private long ae;
    private Map<al, List<RenderPass>> af;
    private List<RenderPass> ag;
    private boolean ah;
    private boolean ai;
    private final EntityRenderer aj;
    private volatile long ak;
    private final Object al;
    private volatile LabelTheme f4604g;
    private volatile CameraPosition f4605h;
    private GLState f4606j;
    private volatile int f4607k;
    private volatile int f4608l;
    private final LinkedList<al> f4609m;
    private final ArrayList<Renderer> f4610n;
    private final ArrayList<TileOverlay> f4611o;
    private final ArrayList<ai> f4612p;
    private final Camera f4613q;
    private final ah f4614r;
    private final NativeAllocator f4615s;
    private final Resources f4616t;
    private final float f4617u;
    private Labeler f4618v;
    private final LabelOverlay f4619w;
    private final LabelOverlay f4620x;
    private final ab f4621y;
    private final ai f4622z;

    /* renamed from: com.google.android.m4b.maps.av.j.1 */
    static class Renderer extends ThreadLocal<PointPool> {
        Renderer() {
        }

        protected final /* synthetic */ Object initialValue() {
            return new PointPool();
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.j.2 */
    static class Renderer implements Comparator<al> {
        Renderer() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return ((al) obj).m6680d().ordinal() - ((al) obj2).m6680d().ordinal();
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.j.3 */
    class Renderer implements TileOverlay {
        private /* synthetic */ Renderer f4560a;

        Renderer(Renderer renderer) {
            this.f4560a = renderer;
        }

        public final boolean m7218a() {
            if (this.f4560a.f4579B != null) {
                synchronized (this.f4560a) {
                    this.f4560a.f4585H = true;
                }
            }
            return true;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.j.4 */
    static /* synthetic */ class Renderer {
        static final /* synthetic */ int[] f4561a;
        static final /* synthetic */ int[] f4562b;

        static {
            f4562b = new int[Renderer.values().length];
            try {
                f4562b[Renderer.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4562b[Renderer.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4562b[Renderer.REPLACE_BASE_TILE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            f4561a = new int[ac.values().length];
            try {
                f4561a[ac.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4561a[ac.TERRAIN.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4561a[ac.RASTER_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4561a[ac.HYBRID.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4561a[ac.NIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.j.a */
    static class Renderer {
        private final al[] f4563a;
        private final TileOverlay[] f4564b;

        public Renderer(List<al> list, List<TileOverlay> list2) {
            this.f4563a = (al[]) list.toArray(new al[list.size()]);
            this.f4564b = (TileOverlay[]) list2.toArray(new TileOverlay[list2.size()]);
        }

        public final al[] m7219a() {
            return this.f4563a;
        }

        public final TileOverlay[] m7220b() {
            return this.f4564b;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.j.b */
    static class Renderer {
        al f4569a;
        Renderer f4570b;

        /* renamed from: com.google.android.m4b.maps.av.j.b.a */
        public enum Renderer {
            ADD,
            REMOVE,
            REPLACE_BASE_TILE
        }

        Renderer(Renderer renderer, al alVar) {
            this.f4570b = renderer;
            this.f4569a = alVar;
        }
    }

    static {
        f4571a = false;
        f4573c = new int[]{60672, 59904, 57856, AccessibilityNodeInfoCompat.ACTION_CUT};
        f4574d = new int[]{AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_CUT};
        f4575e = new int[]{AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_PASTE, AccessibilityNodeInfoCompat.ACTION_CUT};
        f4576f = new int[]{0, 0, 0, 0};
        f4572b = new Renderer();
        f4577i = new Renderer();
    }

    public static int[] m7228a(ac acVar) {
        switch (Renderer.f4561a[acVar.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return f4573c;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return f4573c;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return f4573c;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return f4574d;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return f4575e;
            default:
                return f4576f;
        }
    }

    public Renderer(ah ahVar, Resources resources, Camera camera, TileOverlay tileOverlay, EntityRenderer entityRenderer, GmmRenderTarget gmmRenderTarget, TextView textView) {
        this.f4608l = 1;
        this.f4580C = new HashSet();
        this.f4581D = new HashSet();
        this.f4582E = new int[1];
        this.f4583F = new ArrayList();
        this.f4584G = 0;
        this.f4586I = new Renderer(this);
        this.f4593P = new CopyOnWriteArrayList();
        this.f4594Q = ar.m2515a();
        this.f4603Z = new Object();
        this.aa = false;
        this.ab = 0;
        this.ac = false;
        this.ad = Integer.MAX_VALUE;
        this.ae = 0;
        this.af = new WeakHashMap();
        this.ag = ar.m2515a();
        this.ah = true;
        this.ak = -1;
        this.al = new Object();
        this.f4604g = LabelTheme.f5409s;
        this.f4592O = ac.NORMAL;
        this.f4614r = ahVar;
        this.f4615s = new NativeAllocator(this);
        this.f4616t = resources;
        this.f4617u = resources.getDisplayMetrics().density;
        GLLineMesh.m8031a(this.f4617u);
        GLLineGroup.m8002a(this.f4617u);
        this.f4613q = camera;
        this.f4609m = new LinkedList();
        this.f4610n = new ArrayList();
        this.f4600W = tileOverlay;
        this.f4611o = new ArrayList();
        this.f4611o.add(this.f4600W);
        this.f4612p = new ArrayList();
        this.f4622z = new ai(resources);
        this.f4619w = new LabelOverlay(2, this.f4622z);
        this.f4620x = new LabelOverlay(1, this.f4622z);
        this.f4621y = new ab();
        this.f4578A = new GLHudOverlay();
        m7229c(this.f4600W);
        LabelOverlay labelOverlay = this.f4619w;
        m7229c(this.f4619w);
        m7229c(this.f4622z);
        m7229c(this.f4578A);
        m7229c(this.f4621y);
        al aoVar = new ao(GLOverlay.NIGHT_DIMMER);
        aoVar.m6868a(ac.NIGHT, ExploreByTouchHelper.INVALID_ID);
        m7229c(aoVar);
        m7229c(new an());
        if (VectorGlobalState.m7285a()) {
            this.f4579B = new HudCopyright(resources, textView);
        } else {
            this.f4579B = null;
        }
        this.f4600W.m6734a(this.f4586I);
        this.f4600W.m6744b(true);
        this.f4591N = SystemClock.uptimeMillis();
        this.aj = null;
    }

    public final void m7239a() {
        GLTileCacheManager a = GLTileCacheManager.m7380a();
        if (a != null) {
            a.m7389a(this.f4606j);
        }
    }

    public final void m7246a(GL10 gl10) {
        PerformanceProfile.m4867a();
        this.f4607k = Process.myTid();
        m7231i();
        DeviceSpecificInfo.m11563a(gl10.glGetString(7937));
        synchronized (this.f4609m) {
            Iterator it = this.f4609m.iterator();
            while (it.hasNext()) {
                ((al) it.next()).m6667a(null);
            }
            this.ac = false;
        }
        if (!(this.f4606j == null || this.f4606j.m7541x() == gl10)) {
            if (this.f4618v != null) {
                this.f4618v.m8206a();
            }
            if (this.f4606j != null) {
                this.f4606j.m7507E().m7472a();
                this.f4606j.m7513a().m7631a();
                this.f4606j.m7504B();
                GLState gLState = this.f4606j;
                gLState.f4853g.m7734c(gLState);
                gLState.f4850d.m7686c(gLState);
                gLState.f4849c.m7705c(gLState);
                gLState.f4855i.m7734c(gLState);
                gLState.f4851e.m7734c(gLState);
                gLState.f4854h.m7734c(gLState);
                gLState.f4852f.m7734c(gLState);
            }
            this.f4606j = null;
        }
        if (this.f4606j == null) {
            this.f4598U = SystemClock.uptimeMillis();
            this.f4606j = new GLState(gl10, this.f4615s, this.f4614r, this.aj, this.f4616t);
            this.f4606j.m7542y().m6774b(this.f4601X);
            GLLineMesh.m8032a(this.f4606j.m7506D());
            this.f4618v = new Labeler(this.f4604g, this.f4606j, this.f4616t);
            this.f4619w.m7148a(this.f4618v);
            this.f4620x.m7148a(this.f4618v);
        }
        this.f4614r.m6776c(true);
        this.f4589L = true;
        if (this.aa) {
            this.ab = 2;
        }
        this.aa = true;
        PerformanceProfile.m4868b();
    }

    public final void m7247a(GL10 gl10, int i, int i2) {
        if (!(this.f4606j.m7541x() == gl10 || (this.f4606j.m7541x() instanceof GL20))) {
            Throwable illegalStateException = new IllegalStateException("GL object has changed since onSurfaceCreated");
            ExceptionReporter.m11467a("DA:Renderer", illegalStateException);
            Util.m11552a("OpenGL error during initialization.", illegalStateException);
        }
        if (i > 0 && i2 > 0) {
            this.f4613q.m7424a(i, i2, this.f4617u);
            m7225a(this.f4613q);
            this.f4590M = this.f4613q.m7440h();
            this.f4602Y = Math.max(dm.DEFAULT_BACKOFF_MULT, FloatMath.ceil(Camera.m7414a(FloatMath.ceil(((float) ((int) Math.ceil(Math.hypot((double) i, (double) i2)))) / (this.f4617u * 256.0f)) + br.DEFAULT_BACKOFF_MULT)));
            this.f4614r.m6770a(true, false);
        }
    }

    public final void m7253b(GL10 gl10) {
        if (!(this.f4606j.m7541x() == gl10 || (this.f4606j.m7541x() instanceof GL20))) {
            Throwable illegalStateException = new IllegalStateException("GL object has changed since onSurfaceCreated");
            ExceptionReporter.m11467a("DA:Renderer", illegalStateException);
            Util.m11552a("OpenGL error during initialization.", illegalStateException);
        }
        GLState gLState = this.f4606j;
        GmmSettings.m11527a();
        gLState.m7515a(GmmSettings.m11531e());
        ac acVar = this.f4592O;
        GL10 x = this.f4606j.m7541x();
        int[] a = Renderer.m7228a(acVar);
        x.glClearColorx(a[0], a[1], a[2], a[3]);
        int i = AccessibilityNodeInfoCompat.ACTION_COPY;
        if (this.f4606j.m7525h()) {
            i = 16640;
            this.f4606j.m7526i();
        }
        if (this.f4606j.m7527j()) {
            x.glClearStencil(0);
            i |= Defaults.RESPONSE_BODY_LIMIT;
            this.f4606j.m7528k();
        }
        x.glClear(i);
        if (this.ab > 0) {
            this.ab--;
            this.f4614r.m6770a(true, true);
            return;
        }
        if (this.ak != -1) {
            synchronized (this.al) {
                if (this.ak < System.currentTimeMillis()) {
                    this.ak = -1;
                }
            }
            this.f4614r.m6777d();
        }
        if (ParameterManager.m6651d()) {
            boolean z;
            int j = m7232j();
            boolean z2 = (j & 2) != 0 || this.f4606j.m7521d();
            this.f4606j.m7542y().m6769a(z2);
            z2 = this.f4614r.m6780g();
            boolean z3 = this.f4613q.m7421a() != this.f4584G;
            if (z3) {
                this.f4584G = this.f4613q.m7421a();
                z = true;
            } else {
                z = z2;
            }
            m7230d(z3);
            this.f4606j.m7523f();
            m7235m();
            this.f4595R = this.f4596S;
            if (this.f4595R != null) {
                this.f4595R.m7199a(this);
                this.f4595R.m7204d();
            }
            if (this.f4613q.m7441i() > br.DEFAULT_BACKOFF_MULT) {
                m7226a(this.f4613q, j, z, true);
                this.f4606j.m7524g();
            }
            Object obj = (!this.f4614r.m6781h() || (j & 2) == 0) ? null : 1;
            z2 = this.ai && !this.f4618v.m8214c() && !this.f4606j.m7521d() && obj == null;
            if (this.f4595R != null) {
                this.f4595R.m7202b(z2);
                if (this.f4595R.m7205e()) {
                    this.f4614r.m6770a(false, false);
                }
            }
            synchronized (this) {
                z2 = this.f4587J;
            }
            if (z2) {
                GL10 x2 = this.f4606j.m7541x();
                int e = this.f4613q.m7437e();
                int f = this.f4613q.m7438f();
                Bitmap bitmap = this.f4588K;
                Bitmap a2 = (bitmap != null && bitmap.getWidth() == e && bitmap.getHeight() == f) ? bitmap : this.f4615s.m7591a(e, f, Config.ARGB_8888);
                int internalFormat = GLUtils.getInternalFormat(a2);
                int type = GLUtils.getType(a2);
                Buffer allocate = IntBuffer.allocate(e * f);
                x2.glReadPixels(0, 0, e, f, internalFormat, type, allocate);
                a2.setPixels(allocate.array(), 0, e, 0, 0, e, f);
                synchronized (this) {
                    this.f4588K = a2;
                    this.f4587J = false;
                    notifyAll();
                }
            }
            if (this.f4618v.m8214c() || this.f4606j.m7521d()) {
                this.f4614r.m6770a(false, false);
                return;
            }
            if (VERSION.SDK_INT < 9 && !z && j == 0) {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (uptimeMillis - this.f4591N > 10000) {
                    System.gc();
                    this.f4591N = uptimeMillis;
                }
            }
            if (this.f4606j.m7519c() >= 0) {
                this.f4614r.m6770a(false, false);
                return;
            }
            return;
        }
        this.f4614r.m6770a(false, false);
    }

    public final synchronized void m7248a(boolean z) {
        this.f4601X = z;
        if (this.f4606j != null) {
            this.f4606j.m7542y().m6774b(z);
            if (!z) {
                this.f4606j.m7542y().m6775c();
            }
        }
        m7231i();
    }

    private void m7231i() {
        if (this.f4607k >= 0) {
            try {
                Process.setThreadPriority(this.f4607k, this.f4601X ? 10 : this.f4608l);
            } catch (RuntimeException e) {
                Util.m11550a("Renderer", "Could not set thread priority: " + e);
            }
        }
    }

    public final void m7241a(RenderInstrumentation renderInstrumentation) {
        this.f4596S = renderInstrumentation;
    }

    public final ak m7238a(GLOverlay gLOverlay) {
        return new ak(gLOverlay, this.f4622z);
    }

    public final MyLocationOverlay m7249b(boolean z) {
        return new MyLocationOverlay(this.f4616t, this.f4622z, z);
    }

    public final void m7240a(al alVar) {
        synchronized (this.f4609m) {
            this.f4610n.add(new Renderer(Renderer.ADD, alVar));
        }
        this.f4614r.m6770a(true, false);
    }

    public final void m7252b(al alVar) {
        synchronized (this.f4609m) {
            this.f4610n.add(new Renderer(Renderer.REMOVE, alVar));
        }
        this.f4614r.m6770a(true, false);
    }

    public final ArrayList<al> m7250b() {
        ArrayList<al> arrayList = new ArrayList(this.f4609m.size());
        synchronized (this.f4609m) {
            arrayList.addAll(this.f4609m);
        }
        return arrayList;
    }

    public final void m7243a(AnimationCallback animationCallback) {
        this.f4593P.add(animationCallback);
    }

    public final float m7236a(Point point) {
        float f;
        synchronized (this.f4609m) {
            Iterator it = this.f4611o.iterator();
            f = 21.0f;
            while (it.hasNext()) {
                f = Math.min(f, ((TileOverlay) it.next()).m6727a(point));
            }
        }
        return f;
    }

    public final float m7254c() {
        return this.f4602Y;
    }

    private int m7232j() {
        boolean z = false;
        CameraPosition cameraPosition = null;
        int i = 0;
        for (AnimationCallback animationCallback : this.f4593P) {
            CameraPosition cameraPosition2;
            int b = animationCallback.m7123b(this.f4613q);
            if (b == 0 || animationCallback.m7124i() == null) {
                cameraPosition2 = cameraPosition;
            } else {
                cameraPosition2 = animationCallback.m7124i();
            }
            i |= b;
            cameraPosition = cameraPosition2;
        }
        if (i != 0) {
            if (!(cameraPosition == null || cameraPosition.equals(this.f4605h))) {
                this.f4600W.m6736a(cameraPosition);
                this.f4605h = cameraPosition;
            }
            this.f4614r.m6770a(false, false);
        } else {
            this.f4600W.m6736a(null);
            this.f4605h = null;
        }
        Camera camera = this.f4613q;
        if (i != 0) {
            z = true;
        }
        camera.m7428a(z);
        return i;
    }

    private void m7224a(Renderer renderer, boolean z) {
        int i = 0;
        synchronized (this.f4609m) {
            if (this.ah || z) {
                List list;
                int i2 = this.ah;
                this.ah = false;
                for (al alVar : renderer.m7219a()) {
                    list = (List) this.af.get(alVar);
                    if (list == null) {
                        list = ar.m2515a();
                        this.af.put(alVar, list);
                        i2 = 1;
                    }
                    i2 |= alVar.m6673a(list);
                }
                if (i2 != 0) {
                    this.ag.clear();
                    al[] a = renderer.m7219a();
                    int length = a.length;
                    while (i < length) {
                        list = (List) this.af.get(a[i]);
                        if (list != null) {
                            this.ag.addAll(list);
                        }
                        i++;
                    }
                    Collections.sort(this.ag);
                    Set a2 = bk.m2870a();
                    for (RenderPass renderPass : this.ag) {
                        if (a2.contains(renderPass.m7206a())) {
                            renderPass.m7208a(false);
                        } else {
                            renderPass.m7208a(true);
                            a2.add(renderPass.m7206a());
                        }
                    }
                    a2.clear();
                    for (RenderPass renderPass2 : ar.m2519a(this.ag)) {
                        if (a2.contains(renderPass2.m7206a())) {
                            renderPass2.m7211b(false);
                        } else {
                            renderPass2.m7211b(true);
                            a2.add(renderPass2.m7206a());
                        }
                    }
                }
                return;
            }
        }
    }

    private void m7226a(Camera camera, int i, boolean z, boolean z2) {
        int i2;
        RenderInstrumentation renderInstrumentation;
        int length;
        boolean z3;
        Renderer l = m7234l();
        this.f4606j.m7504B();
        ac acVar = this.f4592O;
        if (camera.m7440h() != this.f4590M) {
            m7225a(camera);
            this.f4590M = camera.m7440h();
        }
        GL10 x = this.f4606j.m7541x();
        x.glMatrixMode(5888);
        x.glLoadIdentity();
        x.glMultMatrixf(camera.m7451s(), 0);
        for (al a : l.m7219a()) {
            a.m6665a(i);
        }
        if (z) {
            for (al a2 : l.m7219a()) {
                a2.m6672a(camera, this.f4606j);
            }
            this.f4583F.clear();
            this.f4621y.m6698b(this.f4583F);
        }
        if (!(acVar == ac.NONE || acVar == ac.RASTER_ONLY)) {
            Labeler labeler;
            TileOverlay[] b = l.m7220b();
            if (this.f4595R != null) {
                renderInstrumentation = this.f4595R;
                labeler = this.f4618v;
            }
            Object obj = (i & 2) != 0 ? 1 : null;
            this.f4618v.m8207a(i);
            if (obj != null) {
                this.f4618v.m8208a(camera.m7454v());
                this.f4589L = true;
            } else if (z || this.f4589L) {
                com.google.android.m4b.maps.an.ar arVar;
                RankMergingFeatureIterator rankMergingFeatureIterator = new RankMergingFeatureIterator();
                Set hashSet = new HashSet();
                at v = camera.m7454v();
                obj = (camera.m7443k() == 0.0f && camera.m7442j() == 0.0f) ? 1 : null;
                if (obj != null) {
                    arVar = null;
                } else {
                    at atVar = v;
                }
                int i3 = 0;
                Set a3 = bk.m2870a();
                Map a4 = au.m2807a();
                for (TileOverlay tileOverlay : b) {
                    if (tileOverlay.m6747j()) {
                        i3 = Math.max(i3, tileOverlay.m6728a(arVar, rankMergingFeatureIterator, hashSet));
                        tileOverlay.m6740a(a3, a4);
                    }
                }
                synchronized (this) {
                }
                this.f4618v.m8209a(camera, v, i3, null, rankMergingFeatureIterator, hashSet, a3, a4, 20, this.f4600W.i_());
                this.f4589L = false;
            } else {
                this.f4618v.m8213b(20);
            }
            if (this.f4595R != null) {
                renderInstrumentation = this.f4595R;
                labeler = this.f4618v;
            }
        }
        if (this.f4579B != null) {
            synchronized (this) {
                z3 = this.f4585H;
                this.f4585H = false;
            }
            if (z3) {
                this.f4580C.clear();
                this.f4581D.clear();
                this.f4582E[0] = -1;
                int i4 = this.f4582E[0];
                TileOverlay[] b2 = l.m7220b();
                length = b2.length;
                int i5 = 0;
                while (i5 < length) {
                    b2[i5].m6735a(camera, acVar, this.f4580C, this.f4581D, this.f4582E);
                    if (this.f4582E[0] > i4) {
                        i2 = this.f4582E[0];
                    } else {
                        i2 = i4;
                    }
                    i5++;
                    i4 = i2;
                }
                this.f4579B.m8182a(this.f4580C, this.f4581D, i4);
            }
        }
        z3 = z || i != 0;
        m7224a(l, z3);
        ad adVar = new ad(acVar, 0);
        this.f4606j.m7530m();
        this.f4606j.m7535r();
        this.ai = true;
        for (RenderPass renderPass : this.ag) {
            adVar.m6703a(renderPass);
            al a5 = renderPass.m7206a();
            if (this.f4595R != null) {
                RenderInstrumentation renderInstrumentation2 = this.f4595R;
            }
            this.f4606j.m7543z();
            a5.m6669a(this.f4606j, camera, adVar);
            this.f4606j.m7503A();
            this.ai = a5.m6681e() & this.ai;
            if (this.f4595R != null) {
                renderInstrumentation = this.f4595R;
            }
        }
        this.f4606j.m7504B();
        i2 = this.f4606j.m7541x().glGetError();
        if (i2 != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            if (i2 == 1285) {
                stringBuilder.append("\nTime in current GL context: ").append(SystemClock.uptimeMillis() - this.f4598U).append("\n");
                stringBuilder.append(GLTileCacheManager.m7380a().m7398c());
                this.f4597T = true;
            }
            Util.m11559c("Renderer", "GL Error: " + i2 + " " + GLU.gluErrorString(i2) + stringBuilder);
            Util.m11550a("Renderer", "drawFrameInternal GL ERROR: " + i2 + stringBuilder);
        }
        boolean z4 = this.f4597T;
        if (i2 == 1285) {
            for (al h_ : l.m7219a()) {
                h_.h_();
            }
        }
        this.f4597T = false;
    }

    private synchronized void m7230d(boolean z) {
        Iterator it = this.f4594Q.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    private synchronized Bitmap m7233k() {
        Bitmap bitmap;
        this.f4587J = true;
        while (this.f4587J) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        bitmap = this.f4588K;
        this.f4588K = null;
        return bitmap;
    }

    public final synchronized Bitmap m7237a(Bitmap bitmap) {
        this.f4588K = bitmap;
        return m7233k();
    }

    private void m7229c(al alVar) {
        this.ah = true;
        this.f4609m.add(alVar);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.m4b.maps.av.Renderer.Renderer m7234l() {
        /*
        r9 = this;
        r5 = 0;
        r1 = 0;
        r3 = 1;
        r7 = r9.f4609m;
        monitor-enter(r7);
        r0 = r9.ac;	 Catch:{ all -> 0x0024 }
        if (r0 != 0) goto L_0x002a;
    L_0x000a:
        r0 = r9.f4609m;	 Catch:{ all -> 0x0024 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0024 }
    L_0x0010:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x0027;
    L_0x0016:
        r0 = r2.next();	 Catch:{ all -> 0x0024 }
        r0 = (com.google.android.m4b.maps.av.al) r0;	 Catch:{ all -> 0x0024 }
        r4 = r9.f4606j;	 Catch:{ all -> 0x0024 }
        r6 = r9.f4614r;	 Catch:{ all -> 0x0024 }
        r0.m6668a(r4, r6);	 Catch:{ all -> 0x0024 }
        goto L_0x0010;
    L_0x0024:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x0027:
        r0 = 1;
        r9.ac = r0;	 Catch:{ all -> 0x0024 }
    L_0x002a:
        r6 = r1;
        r2 = r1;
        r4 = r1;
    L_0x002d:
        r0 = r9.f4610n;	 Catch:{ all -> 0x0024 }
        r0 = r0.size();	 Catch:{ all -> 0x0024 }
        if (r6 >= r0) goto L_0x0112;
    L_0x0035:
        r0 = 1;
        r9.ah = r0;	 Catch:{ all -> 0x0024 }
        r0 = r9.f4610n;	 Catch:{ all -> 0x0024 }
        r0 = r0.get(r6);	 Catch:{ all -> 0x0024 }
        r0 = (com.google.android.m4b.maps.av.Renderer.Renderer) r0;	 Catch:{ all -> 0x0024 }
        r1 = com.google.android.m4b.maps.av.Renderer.Renderer.f4562b;	 Catch:{ all -> 0x0024 }
        r8 = r0.f4570b;	 Catch:{ all -> 0x0024 }
        r8 = r8.ordinal();	 Catch:{ all -> 0x0024 }
        r1 = r1[r8];	 Catch:{ all -> 0x0024 }
        switch(r1) {
            case 1: goto L_0x0055;
            case 2: goto L_0x00c5;
            case 3: goto L_0x010a;
            default: goto L_0x004d;
        };	 Catch:{ all -> 0x0024 }
    L_0x004d:
        r0 = r2;
        r1 = r4;
    L_0x004f:
        r2 = r6 + 1;
        r6 = r2;
        r4 = r1;
        r2 = r0;
        goto L_0x002d;
    L_0x0055:
        r1 = r9.f4609m;	 Catch:{ all -> 0x0024 }
        r8 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r1 = r1.contains(r8);	 Catch:{ all -> 0x0024 }
        if (r1 != 0) goto L_0x004d;
    L_0x005f:
        r1 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r4 = 0;
        r1.m6667a(r4);	 Catch:{ all -> 0x0024 }
        r1 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r1 = r1 instanceof com.google.android.m4b.maps.av.TileOverlay;	 Catch:{ all -> 0x0024 }
        if (r1 == 0) goto L_0x0177;
    L_0x006b:
        r1 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r1 = (com.google.android.m4b.maps.av.TileOverlay) r1;	 Catch:{ all -> 0x0024 }
        r4 = r9.f4586I;	 Catch:{ all -> 0x0024 }
        r1.m6734a(r4);	 Catch:{ all -> 0x0024 }
        r4 = r9.f4611o;	 Catch:{ all -> 0x0024 }
        r4.add(r1);	 Catch:{ all -> 0x0024 }
        r4 = r1.m6745d();	 Catch:{ all -> 0x0024 }
        r4 = r4.ordinal();	 Catch:{ all -> 0x0024 }
        r8 = r9.ad;	 Catch:{ all -> 0x0024 }
        if (r4 >= r8) goto L_0x00a2;
    L_0x0085:
        r4 = r3;
    L_0x0086:
        r2 = r1.m6748k();	 Catch:{ all -> 0x0024 }
        if (r2 == 0) goto L_0x00a8;
    L_0x008c:
        r2 = r9.f4612p;	 Catch:{ all -> 0x0024 }
        r8 = r2.iterator();	 Catch:{ all -> 0x0024 }
    L_0x0092:
        r2 = r8.hasNext();	 Catch:{ all -> 0x0024 }
        if (r2 == 0) goto L_0x00a8;
    L_0x0098:
        r2 = r8.next();	 Catch:{ all -> 0x0024 }
        r2 = (com.google.android.m4b.maps.an.ai) r2;	 Catch:{ all -> 0x0024 }
        r1.m6733a(r2);	 Catch:{ all -> 0x0024 }
        goto L_0x0092;
    L_0x00a2:
        r4 = 0;
        r1.m6744b(r4);	 Catch:{ all -> 0x0024 }
        r4 = r2;
        goto L_0x0086;
    L_0x00a8:
        r1 = r4;
    L_0x00a9:
        r2 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r9.m7229c(r2);	 Catch:{ all -> 0x0024 }
        r2 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r2 = r2.m6682h();	 Catch:{ all -> 0x0024 }
        if (r2 == 0) goto L_0x00b9;
    L_0x00b6:
        r9.m7243a(r2);	 Catch:{ all -> 0x0024 }
    L_0x00b9:
        r0 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r2 = r9.f4606j;	 Catch:{ all -> 0x0024 }
        r4 = r9.f4614r;	 Catch:{ all -> 0x0024 }
        r0.m6668a(r2, r4);	 Catch:{ all -> 0x0024 }
        r0 = r1;
        r1 = r3;
        goto L_0x004f;
    L_0x00c5:
        r1 = r9.f4609m;	 Catch:{ all -> 0x0024 }
        r8 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r1 = r1.remove(r8);	 Catch:{ all -> 0x0024 }
        if (r1 == 0) goto L_0x004d;
    L_0x00cf:
        r1 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r1 = r1 instanceof com.google.android.m4b.maps.av.TileOverlay;	 Catch:{ all -> 0x0024 }
        if (r1 == 0) goto L_0x0174;
    L_0x00d5:
        r1 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r1 = r1.m6680d();	 Catch:{ all -> 0x0024 }
        r1 = r1.ordinal();	 Catch:{ all -> 0x0024 }
        r8 = r9.ad;	 Catch:{ all -> 0x0024 }
        if (r1 != r8) goto L_0x0171;
    L_0x00e3:
        r1 = r3;
    L_0x00e4:
        r2 = r9.f4611o;	 Catch:{ all -> 0x0024 }
        r8 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r2.remove(r8);	 Catch:{ all -> 0x0024 }
    L_0x00eb:
        r2 = r9.af;	 Catch:{ all -> 0x0024 }
        r8 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r2.remove(r8);	 Catch:{ all -> 0x0024 }
        r2 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r8 = r9.f4606j;	 Catch:{ all -> 0x0024 }
        r2.m6667a(r8);	 Catch:{ all -> 0x0024 }
        r0 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r0 = r0.m6682h();	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x0106;
    L_0x0101:
        r2 = r9.f4593P;	 Catch:{ all -> 0x0024 }
        r2.remove(r0);	 Catch:{ all -> 0x0024 }
    L_0x0106:
        r0 = r1;
        r1 = r4;
        goto L_0x004f;
    L_0x010a:
        r0 = r0.f4569a;	 Catch:{ all -> 0x0024 }
        r0 = (com.google.android.m4b.maps.av.TileOverlay) r0;	 Catch:{ all -> 0x0024 }
        r9.f4600W = r0;	 Catch:{ all -> 0x0024 }
        goto L_0x004d;
    L_0x0112:
        r0 = r9.f4610n;	 Catch:{ all -> 0x0024 }
        r0.clear();	 Catch:{ all -> 0x0024 }
        if (r2 == 0) goto L_0x0153;
    L_0x0119:
        r0 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r9.ad = r0;	 Catch:{ all -> 0x0024 }
        r0 = r9.f4611o;	 Catch:{ all -> 0x0024 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0024 }
        r1 = r5;
    L_0x0125:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x014d;
    L_0x012b:
        r0 = r2.next();	 Catch:{ all -> 0x0024 }
        r0 = (com.google.android.m4b.maps.av.TileOverlay) r0;	 Catch:{ all -> 0x0024 }
        r5 = 0;
        r0.m6744b(r5);	 Catch:{ all -> 0x0024 }
        r5 = r0.m6745d();	 Catch:{ all -> 0x0024 }
        r5 = r5.ordinal();	 Catch:{ all -> 0x0024 }
        r6 = r9.ad;	 Catch:{ all -> 0x0024 }
        if (r5 >= r6) goto L_0x016f;
    L_0x0141:
        r1 = r0.m6745d();	 Catch:{ all -> 0x0024 }
        r1 = r1.ordinal();	 Catch:{ all -> 0x0024 }
        r9.ad = r1;	 Catch:{ all -> 0x0024 }
    L_0x014b:
        r1 = r0;
        goto L_0x0125;
    L_0x014d:
        if (r1 == 0) goto L_0x0153;
    L_0x014f:
        r0 = 1;
        r1.m6744b(r0);	 Catch:{ all -> 0x0024 }
    L_0x0153:
        if (r4 == 0) goto L_0x0164;
    L_0x0155:
        r0 = r9.f4609m;	 Catch:{ all -> 0x0024 }
        r0 = r0.size();	 Catch:{ all -> 0x0024 }
        if (r0 <= r3) goto L_0x0164;
    L_0x015d:
        r0 = r9.f4609m;	 Catch:{ all -> 0x0024 }
        r1 = f4577i;	 Catch:{ all -> 0x0024 }
        java.util.Collections.sort(r0, r1);	 Catch:{ all -> 0x0024 }
    L_0x0164:
        r0 = new com.google.android.m4b.maps.av.j$a;	 Catch:{ all -> 0x0024 }
        r1 = r9.f4609m;	 Catch:{ all -> 0x0024 }
        r2 = r9.f4611o;	 Catch:{ all -> 0x0024 }
        r0.<init>(r1, r2);	 Catch:{ all -> 0x0024 }
        monitor-exit(r7);	 Catch:{ all -> 0x0024 }
        return r0;
    L_0x016f:
        r0 = r1;
        goto L_0x014b;
    L_0x0171:
        r1 = r2;
        goto L_0x00e4;
    L_0x0174:
        r1 = r2;
        goto L_0x00eb;
    L_0x0177:
        r1 = r2;
        goto L_0x00a9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.j.l():com.google.android.m4b.maps.av.j$a");
    }

    private void m7225a(Camera camera) {
        int e = camera.m7437e();
        int f = camera.m7438f();
        if (e > 0 && f > 0) {
            GL10 x = this.f4606j.m7541x();
            x.glMatrixMode(5889);
            x.glLoadIdentity();
            x.glViewport(0, 0, e, f);
            x.glMultMatrixf(camera.m7452t(), 0);
            x.glEnable(3089);
            x.glScissor(0, 0, e, f);
        }
    }

    public final void m7251b(ac acVar) {
        if (acVar != this.f4592O) {
            this.f4592O = acVar;
            synchronized (this) {
                this.f4585H = true;
            }
            if (this.f4618v != null) {
                this.f4618v.m8212b();
                this.f4614r.m6770a(true, false);
            }
        }
    }

    public final void m7245a(LabelTheme labelTheme) {
        if (labelTheme != this.f4604g) {
            this.f4604g = labelTheme;
            if (this.f4618v != null) {
                this.f4618v.m8210a(labelTheme);
                this.f4614r.m6770a(true, false);
            }
        }
    }

    public final void m7255c(boolean z) {
        synchronized (this) {
            this.f4599V = z ? 2 : 1;
        }
        this.f4614r.m6770a(false, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m7235m() {
        /*
        r4 = this;
        r0 = 0;
        monitor-enter(r4);
        r1 = r4.f4599V;	 Catch:{ all -> 0x0030 }
        r2 = 0;
        r4.f4599V = r2;	 Catch:{ all -> 0x0030 }
        monitor-exit(r4);	 Catch:{ all -> 0x0030 }
        if (r1 == 0) goto L_0x0036;
    L_0x000a:
        r2 = 2;
        if (r1 != r2) goto L_0x0033;
    L_0x000d:
        r0 = 1;
        r1 = r0;
    L_0x000f:
        r0 = r4.f4618v;
        r0.m8211a(r1);
        r2 = r4.f4609m;
        monitor-enter(r2);
        r0 = r4.f4609m;	 Catch:{ all -> 0x002d }
        r3 = r0.iterator();	 Catch:{ all -> 0x002d }
    L_0x001d:
        r0 = r3.hasNext();	 Catch:{ all -> 0x002d }
        if (r0 == 0) goto L_0x0035;
    L_0x0023:
        r0 = r3.next();	 Catch:{ all -> 0x002d }
        r0 = (com.google.android.m4b.maps.av.al) r0;	 Catch:{ all -> 0x002d }
        r0.m6670a(r1);	 Catch:{ all -> 0x002d }
        goto L_0x001d;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0033:
        r1 = r0;
        goto L_0x000f;
    L_0x0035:
        monitor-exit(r2);	 Catch:{ all -> 0x002d }
    L_0x0036:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.j.m():void");
    }

    public final void m7244a(BubbleBlower bubbleBlower, BubbleView bubbleView) {
        this.f4622z.m6789a(bubbleBlower, bubbleView);
    }

    public final void m7256d() {
        this.f4622z.m6797c();
    }

    public final ai m7257e() {
        return this.f4622z;
    }

    public final GLHudOverlay m7258f() {
        return this.f4578A;
    }

    public final TileOverlay m7259g() {
        return this.f4600W;
    }

    public final GLState m7260h() {
        return this.f4606j;
    }

    public final void m7242a(TileOverlay tileOverlay) {
        synchronized (this.f4609m) {
            this.f4610n.add(new Renderer(Renderer.ADD, tileOverlay));
            this.f4610n.add(new Renderer(Renderer.REPLACE_BASE_TILE, tileOverlay));
            this.f4610n.add(new Renderer(Renderer.REMOVE, this.f4600W));
        }
        if (this.f4618v != null) {
            this.f4618v.m8212b();
        }
        this.f4614r.m6770a(true, false);
    }
}
