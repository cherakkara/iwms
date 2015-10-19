package com.google.android.m4b.maps.bj;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.Matrix;
import android.support.v4.util.TimeUtils;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.FloatMath;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.be.ay;
import com.google.android.m4b.maps.bj.ad.PanoramaManager;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.concurrent.Semaphore;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* compiled from: Renderer */
final class ag extends Thread implements Callback, PanoramaManager {
    private static final double f6375a;
    private static final Semaphore f6376b;
    private LabelMaker f6377A;
    private volatile boolean f6378B;
    private Renderer f6379C;
    private Renderer f6380D;
    private final ViewpointDetector f6381E;
    private final ad f6382F;
    private Context f6383c;
    private SurfaceHolder f6384d;
    private boolean f6385e;
    private EGL10 f6386f;
    private EGLContext f6387g;
    private EGLDisplay f6388h;
    private EGLSurface f6389i;
    private EGLConfig f6390j;
    private GL10 f6391k;
    private boolean f6392l;
    private boolean f6393m;
    private ay f6394n;
    private TextureCache f6395o;
    private PanoramaConfig f6396p;
    private Renderer f6397q;
    private af f6398r;
    private aa f6399s;
    private Renderer f6400t;
    private int f6401u;
    private Renderer f6402v;
    private int f6403w;
    private int f6404x;
    private Renderer f6405y;
    private boolean f6406z;

    /* renamed from: com.google.android.m4b.maps.bj.ag.c */
    interface Renderer {
        void m9705a(PanoramaConfig panoramaConfig, TextureCache textureCache, int i, int i2);

        void m9706a(GL10 gl10, ay ayVar, Renderer renderer, Renderer renderer2);
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.a */
    static class Renderer {
        Renderer f6346a;
        int f6347b;
        int f6348c;
        int f6349d;
        Object f6350e;

        Renderer() {
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.b */
    static class Renderer {
        Renderer f6351a;
        Renderer f6352b;
        Renderer f6353c;

        private Renderer() {
        }

        final boolean m9787a() {
            return this.f6352b == null;
        }

        final void m9786a(int i, int i2, int i3, Object obj) {
            Renderer renderer = this.f6351a;
            if (renderer != null) {
                this.f6351a = renderer.f6346a;
                renderer.f6346a = null;
            } else {
                renderer = new Renderer();
            }
            renderer.f6347b = i;
            renderer.f6348c = i2;
            renderer.f6349d = i3;
            renderer.f6350e = obj;
            if (this.f6353c == null) {
                this.f6353c = renderer;
                this.f6352b = renderer;
                return;
            }
            this.f6353c.f6346a = renderer;
            this.f6353c = renderer;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.d */
    static class Renderer {
        public final ac f6354a;
        public final Bitmap f6355b;

        public Renderer(ac acVar, Bitmap bitmap) {
            this.f6354a = acVar;
            this.f6355b = bitmap;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.e */
    static class Renderer {
        public final float f6356a;
        public final float f6357b;
        public final boolean f6358c;
        public boolean f6359d;
        public float[] f6360e;

        Renderer(float f, float f2, boolean z) {
            this.f6356a = f;
            this.f6357b = f2;
            this.f6358c = z;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.f */
    static class Renderer {
        public float f6361a;
        public int f6362b;

        Renderer() {
        }

        public final void m9788a(float f) {
            this.f6362b++;
            this.f6361a += f;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.g */
    interface Renderer {
        void m9789a(int i);

        void m9790b(int i);
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.h */
    static class Renderer {
        public final PanoramaConfig f6363a;

        public Renderer(PanoramaConfig panoramaConfig) {
            this.f6363a = panoramaConfig;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.i */
    public static class Renderer {
        public final ay f6364a;
        private float f6365b;
        private float f6366c;
        private float f6367d;
        private float f6368e;
        private final long f6369f;
        private final int f6370g;

        public Renderer(float f, float f2, ay ayVar, int i) {
            this.f6365b = f;
            this.f6366c = f2;
            this.f6367d = f;
            this.f6368e = f2;
            this.f6364a = new ay(ayVar);
            this.f6369f = System.currentTimeMillis();
            this.f6370g = Constants.MILLIS_IN_A_SECOND;
        }

        public final float m9791a() {
            return Math.min(br.DEFAULT_BACKOFF_MULT, ((float) (System.currentTimeMillis() - this.f6369f)) / ((float) this.f6370g));
        }

        public final boolean m9793b() {
            return this.f6369f + ((long) this.f6370g) < System.currentTimeMillis();
        }

        public final boolean m9792a(DepthMap depthMap) {
            float[] fArr = new float[2];
            ar.m8596a(this.f6365b, 0.0f, -this.f6366c, fArr);
            float a = depthMap.m9957a(fArr[0], fArr[1], null) * 0.9f;
            this.f6367d = this.f6365b;
            this.f6368e = this.f6366c;
            float sqrt = FloatMath.sqrt((this.f6365b * this.f6365b) + (this.f6366c * this.f6366c));
            if (a <= 0.0f || sqrt <= a) {
                return false;
            }
            this.f6367d *= a / sqrt;
            this.f6368e *= a / sqrt;
            return true;
        }

        public final float m9794c() {
            return this.f6367d;
        }

        public final float m9795d() {
            return this.f6368e;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.ag.j */
    static class Renderer {
        public final float f6371a;
        public final float f6372b;
        public boolean f6373c;
        public float[] f6374d;

        Renderer(float f, float f2) {
            this.f6371a = f;
            this.f6372b = f2;
        }
    }

    static {
        f6375a = Math.tan((double) ar.m8614o(45.0f));
        f6376b = new Semaphore(1);
    }

    ag(ad adVar) {
        this.f6381E = new ViewpointDetector();
        this.f6382F = adVar;
        this.f6403w = 0;
        this.f6404x = 0;
        this.f6377A = new LabelMaker(true, AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        this.f6400t = new Renderer();
        this.f6405y = new Renderer();
        this.f6398r = new af();
    }

    private static boolean m9810c(float f) {
        return f >= br.DEFAULT_BACKOFF_MULT;
    }

    public static float m9796a(float f) {
        if (m9810c(f)) {
            return ar.m8613n(((float) Math.atan(f6375a / ((double) f))) * dm.DEFAULT_BACKOFF_MULT);
        }
        return 90.0f;
    }

    public static float m9807b(float f) {
        if (m9810c(f)) {
            return 90.0f;
        }
        return ar.m8613n(((float) Math.atan(f6375a * ((double) f))) * dm.DEFAULT_BACKOFF_MULT);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m9821a(android.content.Context r4, android.view.SurfaceHolder r5, com.google.android.m4b.maps.bj.ag.Renderer r6, android.view.View r7) {
        /*
        r3 = this;
        r3.f6383c = r4;
        r3.f6384d = r5;
        r0 = r3.f6384d;
        r0.addCallback(r3);
        r0 = new com.google.android.m4b.maps.bj.f;
        r1 = 17;
        r0.<init>(r1);
        r3.f6395o = r0;
        r3.f6402v = r6;
        r0 = 0;
        r3.f6397q = r0;
        r0 = r3.f6384d;
        r1 = 2;
        r0.setType(r1);
        r0 = new com.google.android.m4b.maps.bj.aa;
        r1 = r3.f6383c;
        r2 = r3.f6398r;
        r0.<init>(r1, r2, r7);
        r3.f6399s = r0;
        r0 = "Renderer";
        r3.setName(r0);
        r0 = 6;
        r3.setPriority(r0);
        r3.start();
        monitor-enter(r3);
    L_0x0035:
        r0 = r3.f6406z;	 Catch:{ all -> 0x0041 }
        if (r0 != 0) goto L_0x003f;
    L_0x0039:
        r3.wait();	 Catch:{ InterruptedException -> 0x003d }
        goto L_0x0035;
    L_0x003d:
        r0 = move-exception;
        goto L_0x0035;
    L_0x003f:
        monitor-exit(r3);	 Catch:{ all -> 0x0041 }
        return;
    L_0x0041:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bj.ag.a(android.content.Context, android.view.SurfaceHolder, com.google.android.m4b.maps.bj.ag$g, android.view.View):void");
    }

    public final void run() {
        Renderer renderer = null;
        try {
            synchronized (this) {
                this.f6406z = true;
                notifyAll();
            }
            while (!this.f6378B) {
                Renderer renderer2 = renderer;
                while (true) {
                    renderer2 = m9797a(renderer2);
                    if (renderer2 != null) {
                        switch (renderer2.f6347b) {
                            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                                this.f6385e = true;
                                m9814h();
                                break;
                            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                                m9815i();
                                this.f6385e = false;
                                break;
                            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                                int i = renderer2.f6348c;
                                int i2 = renderer2.f6349d;
                                Object obj = (this.f6403w == 0 && this.f6404x == 0) ? 1 : null;
                                if (obj == null) {
                                    String.format("Window changed size: %d,%d -> %d,%d Recreating OpenGL surface", new Object[]{Integer.valueOf(this.f6403w), Integer.valueOf(this.f6404x), Integer.valueOf(i), Integer.valueOf(i2)});
                                    m9816j();
                                    m9816j();
                                }
                                this.f6403w = i;
                                this.f6404x = i2;
                                this.f6392l = true;
                                this.f6381E.m9942a(m9807b(((float) this.f6403w) / ((float) this.f6404x)));
                                break;
                            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                                m9803a((Renderer) renderer2.f6350e);
                                break;
                            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                                int i3 = renderer2.f6348c;
                                if (this.f6379C == null) {
                                    break;
                                }
                                break;
                            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                                ay ayVar = (ay) renderer2.f6350e;
                                if (this.f6379C != null) {
                                    break;
                                }
                                this.f6394n = ayVar;
                                this.f6381E.m9943a(this.f6394n);
                                break;
                            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                                Renderer renderer3 = (Renderer) renderer2.f6350e;
                                ac acVar = renderer3.f6354a;
                                Bitmap bitmap = renderer3.f6355b;
                                if (this.f6395o.m9921a((Object) acVar) != null) {
                                    break;
                                }
                                this.f6395o.m9929a(acVar, bitmap);
                                break;
                            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                            case LangUtils.HASH_SEED /*17*/:
                                break;
                            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                                m9815i();
                                break;
                            case HTTP.HT /*9*/:
                                m9814h();
                                this.f6392l = true;
                                break;
                            case HTTP.LF /*10*/:
                                this.f6399s.m9739b(renderer2.f6348c);
                                break;
                            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                                this.f6399s.m9734a(renderer2.f6348c, System.currentTimeMillis());
                                break;
                            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                                m9805a(renderer2.f6350e);
                                break;
                            case HTTP.CR /*13*/:
                                this.f6378B = true;
                                break;
                            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                                m9802a((Renderer) renderer2.f6350e);
                                break;
                            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                                float intBitsToFloat = Float.intBitsToFloat(renderer2.f6348c);
                                float intBitsToFloat2 = Float.intBitsToFloat(renderer2.f6349d);
                                if (this.f6396p.f6609y == null) {
                                    break;
                                }
                                Renderer renderer4 = new Renderer(intBitsToFloat, intBitsToFloat2, false);
                                m9802a(renderer4);
                                float[] fArr = new float[2];
                                this.f6396p.m9995a(renderer4.f6360e[0], renderer4.f6360e[1], fArr);
                                float[] fArr2 = new float[3];
                                this.f6396p.f6609y.m9960a();
                                this.f6396p.f6609y.m9957a(fArr[0], fArr[1], fArr2);
                                this.f6396p.f6609y.m9964c(fArr[0], fArr[1]);
                                this.f6396p.m9994a(fArr2[0], fArr2[1], fArr2[2], fArr2);
                                break;
                            case SizeUtil.textSize0_1 /*18*/:
                                this.f6379C = (Renderer) renderer2.f6350e;
                                this.f6380D = null;
                                break;
                            case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                                m9804a((Renderer) renderer2.f6350e);
                                break;
                            default:
                                throw new IllegalArgumentException("Unknown message id " + renderer2.f6347b);
                        }
                    }
                    if (this.f6387g != null) {
                        m9801a(System.currentTimeMillis());
                    }
                    m9812f();
                    renderer = renderer2;
                }
            }
            m9813g();
        } catch (InterruptedException e) {
            m9813g();
        } catch (Throwable th) {
            m9813g();
        }
    }

    private Renderer m9797a(Renderer renderer) {
        Renderer renderer2;
        synchronized (this.f6405y) {
            Renderer renderer3;
            if (renderer != null) {
                renderer3 = this.f6405y;
                renderer.f6346a = renderer3.f6351a;
                renderer.f6350e = null;
                renderer3.f6351a = renderer;
            }
            renderer3 = this.f6405y;
            renderer2 = renderer3.f6352b;
            if (renderer2 != null) {
                renderer3.f6352b = renderer2.f6346a;
                renderer2.f6346a = null;
                if (renderer3.f6352b == null) {
                    renderer3.f6353c = null;
                }
            }
        }
        return renderer2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m9812f() {
        /*
        r8 = this;
        r2 = 0;
        r0 = r8.f6379C;
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r4 = r8.f6405y;
        monitor-enter(r4);
    L_0x000a:
        r0 = r8.f6405y;	 Catch:{ all -> 0x002a }
        r0 = r0.m9787a();	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x003a;
    L_0x0012:
        r0 = r8.f6387g;	 Catch:{ all -> 0x002a }
        if (r0 != 0) goto L_0x002d;
    L_0x0016:
        r0 = r2;
    L_0x0017:
        r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r5 == 0) goto L_0x0034;
    L_0x001b:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x002a }
        r0 = r0 - r6;
        r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r5 <= 0) goto L_0x003a;
    L_0x0024:
        r5 = r8.f6405y;	 Catch:{ all -> 0x002a }
        r5.wait(r0);	 Catch:{ all -> 0x002a }
        goto L_0x000a;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x002d:
        r0 = r8.f6399s;	 Catch:{ all -> 0x002a }
        r0 = r0.m9732a();	 Catch:{ all -> 0x002a }
        goto L_0x0017;
    L_0x0034:
        r0 = r8.f6405y;	 Catch:{ all -> 0x002a }
        r0.wait();	 Catch:{ all -> 0x002a }
        goto L_0x000a;
    L_0x003a:
        monitor-exit(r4);	 Catch:{ all -> 0x002a }
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bj.ag.f():void");
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        m9811e(0);
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        m9811e(1);
        try {
            if (this.f6378B) {
                join();
                return;
            }
            Object num = new Integer(0);
            synchronized (num) {
                m9800a(12, num);
                num.wait();
            }
        } catch (InterruptedException e) {
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        m9798a(2, i2, i3);
    }

    public final void m9825a(PanoramaConfig panoramaConfig) {
        m9809b(3, new Renderer(panoramaConfig));
    }

    public final void m9822a(ay ayVar) {
        m9809b(5, (Object) ayVar);
    }

    public final void m9824a(Renderer renderer) {
        m9809b(18, (Object) renderer);
    }

    public final void m9820a(int i) {
        m9808b(4, i);
    }

    public final void m9823a(ac acVar, Bitmap bitmap) {
        m9800a(6, new Renderer(acVar, bitmap));
    }

    public final void m9818a() {
        m9811e(8);
    }

    public final void m9828b() {
        m9811e(7);
    }

    public final void m9832c() {
        m9811e(9);
    }

    public final void m9829b(int i) {
        m9808b(10, i);
    }

    public final void m9833c(int i) {
        m9808b(11, i);
    }

    public final void m9819a(float f, float f2) {
        m9798a(14, -1, -1);
    }

    public final int m9817a(int i, int i2) {
        return this.f6399s.m9731a(i, (this.f6404x - 1) - i2);
    }

    public final float[] m9827a(float f, float f2, boolean z) {
        Object renderer = new Renderer(f, f2, false);
        synchronized (renderer) {
            m9800a(15, renderer);
            do {
                try {
                    renderer.wait();
                } catch (InterruptedException e) {
                }
            } while (!renderer.f6359d);
        }
        return renderer.f6360e;
    }

    public final float[] m9831b(float f, float f2) {
        Object renderer = new Renderer(f, f2);
        synchronized (renderer) {
            m9800a(19, renderer);
            do {
                try {
                    renderer.wait();
                } catch (InterruptedException e) {
                }
            } while (!renderer.f6373c);
        }
        return renderer.f6374d;
    }

    public final PanoramaLink m9834d(int i) {
        return this.f6399s.m9733a(i);
    }

    private void m9813g() {
        m9815i();
        this.f6384d.removeCallback(this);
    }

    private void m9802a(Renderer renderer) {
        float[] fArr;
        Renderer renderer2;
        if (this.f6394n == null) {
            fArr = null;
            renderer2 = renderer;
        } else {
            float[] fArr2;
            this.f6398r.m9781a(this.f6391k);
            if (renderer.f6358c) {
                fArr = new float[16];
                Matrix.multiplyMM(fArr, 0, this.f6394n.m8749a(), 0, this.f6396p.m9999d(), 0);
                Matrix.rotateM(fArr, 0, -this.f6396p.f6603s, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f);
                fArr2 = fArr;
            } else {
                fArr2 = this.f6394n.m8749a();
            }
            r6 = new float[4];
            this.f6398r.m9785b(new float[]{renderer.f6356a, ((float) this.f6404x) - renderer.f6357b, br.DEFAULT_BACKOFF_MULT}, 0, fArr2, r6, 0);
            fArr = new float[2];
            ar.m8596a(r6[0], r6[1], r6[2], fArr);
            if (!renderer.f6358c) {
                fArr[0] = ar.m8598b(fArr[0] + 0.5f);
            }
            if (Float.isNaN(fArr[0]) || Float.isNaN(fArr[1])) {
                fArr = null;
                renderer2 = renderer;
            } else {
                renderer2 = renderer;
            }
        }
        renderer2.f6360e = fArr;
        synchronized (renderer) {
            renderer.f6359d = true;
            renderer.notify();
        }
    }

    private void m9804a(Renderer renderer) {
        float[] fArr;
        Renderer renderer2;
        if (this.f6394n == null) {
            fArr = null;
            renderer2 = renderer;
        } else {
            float[] fArr2 = new float[3];
            ar.m8597a(ar.m8598b(renderer.f6371a - 0.5f), renderer.f6372b, fArr2, 0);
            this.f6398r.m9781a(this.f6391k);
            float[] fArr3 = new float[4];
            this.f6398r.m9783a(fArr2, 0, this.f6394n.m8749a(), fArr3, 0);
            if (Float.isNaN(fArr3[0]) || Float.isNaN(fArr3[1]) || fArr3[2] > br.DEFAULT_BACKOFF_MULT) {
                fArr = null;
                renderer2 = renderer;
            } else {
                fArr = new float[]{fArr3[0], (float) (this.f6404x - ((int) fArr3[1]))};
                renderer2 = renderer;
            }
        }
        renderer2.f6374d = fArr;
        synchronized (renderer) {
            renderer.f6373c = true;
            renderer.notify();
        }
    }

    private final void m9800a(int i, Object obj) {
        synchronized (this.f6405y) {
            if (this.f6405y.m9787a()) {
                this.f6405y.notify();
            }
            this.f6405y.m9786a(i, 0, 0, obj);
        }
    }

    private final void m9811e(int i) {
        m9799a(i, 0, 0, null);
    }

    private final void m9809b(int i, Object obj) {
        m9799a(i, 0, 0, obj);
    }

    private final void m9808b(int i, int i2) {
        m9799a(i, i2, 0, null);
    }

    private final void m9798a(int i, int i2, int i3) {
        m9799a(i, i2, i3, null);
    }

    private final void m9799a(int i, int i2, int i3, Object obj) {
        synchronized (this.f6405y) {
            if (this.f6405y.m9787a()) {
                this.f6405y.notify();
            }
            Renderer renderer = this.f6405y;
            Renderer renderer2 = renderer.f6353c;
            if (renderer2 == null || renderer2.f6347b != i) {
                renderer.m9786a(i, i2, i3, obj);
            } else {
                renderer2.f6348c = i2;
                renderer2.f6349d = i3;
                renderer2.f6350e = obj;
            }
        }
    }

    private void m9814h() {
        if (this.f6387g == null && this.f6385e) {
            f6376b.acquire();
            this.f6386f = (EGL10) EGLContext.getEGL();
            EGLDisplay eglGetDisplay = this.f6386f.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.f6386f.eglInitialize(eglGetDisplay, new int[2]);
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            this.f6386f.eglChooseConfig(eglGetDisplay, new int[]{12325, 16, 12344}, eGLConfigArr, 1, new int[1]);
            EGLConfig eGLConfig = eGLConfigArr[0];
            EGLContext eglCreateContext = this.f6386f.eglCreateContext(eglGetDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, null);
            this.f6388h = eglGetDisplay;
            this.f6390j = eGLConfig;
            this.f6387g = eglCreateContext;
            if (m9806a(this.f6386f)) {
                this.f6391k = null;
                f6376b.release();
                return;
            }
            this.f6391k = (GL10) eglCreateContext.getGL();
            this.f6391k = new GLMatrixWrapper(this.f6391k);
            GL10 gl10 = this.f6391k;
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            EGLDisplay eGLDisplay = this.f6388h;
            this.f6389i = egl10.eglCreateWindowSurface(eGLDisplay, this.f6390j, this.f6384d, null);
            egl10.eglMakeCurrent(eGLDisplay, this.f6389i, this.f6389i, this.f6387g);
            this.f6395o.m9931a(gl10);
            this.f6377A.m10016a(gl10);
            gl10.glDisable(2929);
            gl10.glDisable(3089);
            gl10.glDisable(3024);
            gl10.glDisable(2896);
            gl10.glDisable(3042);
            gl10.glHint(3152, 4354);
            gl10.glShadeModel(7424);
            gl10.glDisable(2884);
            gl10.glFrontFace(2305);
            gl10.glDepthFunc(515);
        }
    }

    private boolean m9806a(EGL10 egl10) {
        if (egl10.eglGetError() == 12288) {
            return false;
        }
        String.format("EGL error: %d", new Object[]{Integer.valueOf(egl10.eglGetError())});
        this.f6391k = null;
        return true;
    }

    private void m9815i() {
        EGLContext eGLContext = this.f6387g;
        GL10 gl10 = this.f6391k;
        EGLDisplay eGLDisplay = this.f6388h;
        EGLSurface eGLSurface = this.f6389i;
        if (eGLContext != null) {
            this.f6395o.m9928a();
            this.f6377A.m10019b(gl10);
            this.f6399s.m9736a(gl10);
            if (eGLDisplay != null) {
                this.f6386f.eglMakeCurrent(eGLDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.f6386f.eglDestroyContext(eGLDisplay, eGLContext);
                if (eGLSurface != null) {
                    this.f6386f.eglDestroySurface(eGLDisplay, eGLSurface);
                }
                this.f6386f.eglTerminate(eGLDisplay);
            }
            this.f6386f = null;
            this.f6387g = null;
            f6376b.release();
        }
    }

    private void m9816j() {
        EGL10 egl10 = this.f6386f;
        if (egl10 != null) {
            EGLDisplay eGLDisplay = this.f6388h;
            EGLSurface eGLSurface = this.f6389i;
            egl10.eglMakeCurrent(eGLDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
            this.f6389i = egl10.eglCreateWindowSurface(eGLDisplay, this.f6390j, this.f6384d, null);
            egl10.eglMakeCurrent(eGLDisplay, this.f6389i, this.f6389i, this.f6387g);
            m9806a(egl10);
        }
    }

    private synchronized void m9803a(Renderer renderer) {
        if (this.f6379C != null) {
            this.f6380D = renderer;
        } else {
            if (!(this.f6396p == null || this.f6396p.f6609y == null)) {
                this.f6396p.f6609y.m9963b();
            }
            this.f6396p = renderer.f6363a;
            this.f6393m = true;
            if (this.f6396p != null) {
                this.f6381E.m9944a(this.f6396p, this.f6394n);
            }
        }
    }

    private static void m9805a(Object obj) {
        synchronized (obj) {
            obj.notifyAll();
        }
    }

    private void m9801a(long j) {
        if (this.f6403w != 0 && this.f6404x != 0 && this.f6387g != null) {
            int i = this.f6392l ? 2 : 1;
            for (int i2 = 0; i2 < i; i2++) {
                GL10 gl10 = this.f6391k;
                if (gl10 != null) {
                    Object obj;
                    int i3;
                    int i4;
                    int i5;
                    Renderer renderer;
                    if (this.f6393m && this.f6396p != null) {
                        switch (this.f6396p.f6601q) {
                            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                                this.f6397q = new Cube(this.f6382F, this);
                                break;
                            default:
                                this.f6397q = new Sphere(this.f6382F, this);
                                break;
                        }
                    }
                    if (!(this.f6396p == null || this.f6380D == null)) {
                        Object obj2 = (this.f6379C == null || !this.f6379C.m9793b()) ? null : 1;
                        Object obj3 = null;
                        boolean z = false;
                        if (!(this.f6380D == null || this.f6380D.f6363a == null)) {
                            if (!this.f6380D.f6363a.f6599o) {
                                for (ac a : this.f6380D.f6363a.f6602r) {
                                    if (this.f6395o.m9921a((Object) a) == null) {
                                        obj = null;
                                        obj3 = obj;
                                        z = this.f6380D.f6363a.f6585a;
                                    }
                                }
                            }
                            i3 = 1;
                            obj3 = obj;
                            z = this.f6380D.f6363a.f6585a;
                        }
                        obj = (obj2 == null || !(obj3 != null || z || this.f6380D == null || this.f6380D.f6363a == null)) ? null : 1;
                        if (obj != null) {
                            if (this.f6396p.f6609y != null) {
                                this.f6396p.f6609y.m9963b();
                            }
                            this.f6394n = this.f6379C.f6364a;
                            this.f6379C = null;
                            m9803a(this.f6380D);
                            this.f6380D = null;
                            obj = 1;
                            i4 = this.f6403w;
                            i5 = this.f6404x;
                            if ((this.f6392l || this.f6393m) && this.f6396p != null) {
                                this.f6397q.m9705a(this.f6396p, this.f6395o, i4, i5);
                                this.f6398r.m9780a(0, 0, i4, i5);
                                this.f6399s.m9735a(this.f6396p, i4, i5);
                            }
                            if (this.f6396p == null || r0 == null) {
                                gl10.glClearColor(0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
                                gl10.glClear(AccessibilityNodeInfoCompat.ACTION_COPY);
                                if (!(this.f6396p == null || ((this.f6396p != null && this.f6396p.m9997b()) || this.f6394n == null || this.f6397q == null))) {
                                    gl10.glViewport(0, 0, i4, i5);
                                    this.f6397q.m9706a(gl10, this.f6379C != null ? this.f6394n : new ay((ar.m8599b(this.f6379C.f6364a.m8750b(), this.f6394n.m8750b()) * this.f6379C.m9791a()) + this.f6394n.m8750b(), ((this.f6379C.f6364a.m8752c() - this.f6394n.m8752c()) * this.f6379C.m9791a()) + this.f6394n.m8752c(), 0.0f), this.f6400t, this.f6379C);
                                    if (this.f6402v != null) {
                                        if (this.f6396p.m9998c()) {
                                            renderer = this.f6400t;
                                            i3 = renderer.f6362b != 0 ? 0 : (int) ((renderer.f6361a * 10000.0f) / ((float) renderer.f6362b));
                                        } else {
                                            i3 = AbstractSpiCall.DEFAULT_TIMEOUT;
                                        }
                                        if (i3 != this.f6401u) {
                                            this.f6402v.m9789a(i3);
                                            this.f6401u = i3;
                                        }
                                    }
                                    if (this.f6379C == null) {
                                        this.f6399s.m9737a(gl10, this.f6394n, j);
                                    }
                                    if (this.f6396p.f6599o || !this.f6396p.f6600p) {
                                        this.f6402v.m9790b(-2);
                                        this.f6396p.f6599o = false;
                                        this.f6396p.f6600p = true;
                                    }
                                }
                            }
                        }
                    }
                    obj = null;
                    i4 = this.f6403w;
                    i5 = this.f6404x;
                    this.f6397q.m9705a(this.f6396p, this.f6395o, i4, i5);
                    this.f6398r.m9780a(0, 0, i4, i5);
                    this.f6399s.m9735a(this.f6396p, i4, i5);
                    if (this.f6396p == null) {
                    }
                    gl10.glClearColor(0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT);
                    gl10.glClear(AccessibilityNodeInfoCompat.ACTION_COPY);
                    gl10.glViewport(0, 0, i4, i5);
                    if (this.f6379C != null) {
                    }
                    this.f6397q.m9706a(gl10, this.f6379C != null ? this.f6394n : new ay((ar.m8599b(this.f6379C.f6364a.m8750b(), this.f6394n.m8750b()) * this.f6379C.m9791a()) + this.f6394n.m8750b(), ((this.f6379C.f6364a.m8752c() - this.f6394n.m8752c()) * this.f6379C.m9791a()) + this.f6394n.m8752c(), 0.0f), this.f6400t, this.f6379C);
                    if (this.f6402v != null) {
                        if (this.f6396p.m9998c()) {
                            renderer = this.f6400t;
                            if (renderer.f6362b != 0) {
                            }
                        } else {
                            i3 = AbstractSpiCall.DEFAULT_TIMEOUT;
                        }
                        if (i3 != this.f6401u) {
                            this.f6402v.m9789a(i3);
                            this.f6401u = i3;
                        }
                    }
                    if (this.f6379C == null) {
                        this.f6399s.m9737a(gl10, this.f6394n, j);
                    }
                    if (!this.f6396p.f6599o) {
                    }
                    this.f6402v.m9790b(-2);
                    this.f6396p.f6599o = false;
                    this.f6396p.f6600p = true;
                }
                this.f6392l = false;
                this.f6393m = false;
                ((EGL10) EGLContext.getEGL()).eglSwapBuffers(this.f6388h, this.f6389i);
            }
        }
    }

    public final void m9826a(boolean z) {
        this.f6399s.m9738a(z);
    }

    public final boolean m9835d() {
        return this.f6399s.m9741b();
    }

    public final void m9830b(boolean z) {
        this.f6399s.m9740b(z);
    }

    public final aa m9836e() {
        return this.f6399s;
    }
}
