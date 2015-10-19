package com.google.android.m4b.maps.av;

import android.content.res.Resources;
import android.os.SystemClock;
import android.util.FloatMath;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.au.NavigationParameters;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.ax.BaseDynamicCameraPositionProvider;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.ax.CameraPositionProvider;
import com.google.android.m4b.maps.ax.CameraPositionSanitizer;
import com.google.android.m4b.maps.ax.DynamicCameraPositionProvider;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.Config;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.av.q */
public final class VectorMapController implements AnimationCallback {
    private static final float f4677a;
    private static final CameraPosition f4678b;
    private static float f4679c;
    private final CameraPositionSanitizer f4680d;
    private volatile CameraPosition f4681e;
    private volatile CameraPosition f4682f;
    private volatile float f4683g;
    private volatile CameraPositionProvider f4684h;
    private volatile boolean f4685i;
    private boolean f4686j;
    private RepaintCallback f4687k;
    private ViewPointRecorder f4688l;
    private PositionChangeFinishedCallback f4689m;
    private aa f4690n;
    private VectorMapController f4691o;
    private boolean f4692p;
    private int f4693q;
    private float f4694r;

    /* renamed from: com.google.android.m4b.maps.av.q.a */
    public static class VectorMapController extends BaseDynamicCameraPositionProvider {
        private final CameraPosition f4650b;
        private CameraPositionProvider f4651c;
        private final int f4652d;
        private final long f4653e;
        private int f4654f;
        private final Clock f4655g;
        private float f4656h;

        protected VectorMapController(CameraPosition cameraPosition, CameraPositionProvider cameraPositionProvider, int i, boolean z, float f) {
            super(cameraPosition);
            this.f4654f = 0;
            this.f4655g = Config.m11320a().m11334h();
            this.f4650b = cameraPosition;
            this.f4651c = cameraPositionProvider;
            this.f4653e = this.f4655g.m10152b();
            if (this.f4650b.equals(this.f4651c.m7291b())) {
                this.f4652d = 0;
            } else {
                this.f4652d = Math.max(0, i);
            }
            if (z) {
                this.f4656h = 0.0f;
            } else {
                this.f4656h = f;
            }
        }

        public final CameraPositionProvider m7296a(Camera camera) {
            float f;
            int a;
            long b = this.f4655g.m10152b();
            if (this.f4652d == 0) {
                f = br.DEFAULT_BACKOFF_MULT;
            } else {
                f = ((float) (b - this.f4653e)) / ((float) this.f4652d);
            }
            if (this.f4651c instanceof DynamicCameraPositionProvider) {
                DynamicCameraPositionProvider dynamicCameraPositionProvider = (DynamicCameraPositionProvider) this.f4651c;
                this.f4651c = dynamicCameraPositionProvider.m7293a(camera);
                a = dynamicCameraPositionProvider.m7292a();
            } else {
                a = 0;
            }
            if (f >= br.DEFAULT_BACKOFF_MULT) {
                this.f4654f = a;
                return this.f4651c;
            }
            this.a = CameraPosition.m7456a(this.f4650b, this.f4651c.m7291b().m7458a(this.f4650b), br.DEFAULT_BACKOFF_MULT - ((((float) Math.cos(((double) f) * 3.141592653589793d)) + br.DEFAULT_BACKOFF_MULT) * 0.5f), this.f4656h);
            this.f4654f = a | 2;
            return this;
        }

        public final int m7295a() {
            return this.f4654f;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.q.b */
    static class VectorMapController extends BaseDynamicCameraPositionProvider {
        private final CameraPositionSanitizer f4657b;
        private float f4658c;
        private float f4659d;
        private long f4660e;

        public VectorMapController(CameraPosition cameraPosition, CameraPositionSanitizer cameraPositionSanitizer) {
            super(cameraPosition);
            this.f4657b = cameraPositionSanitizer;
        }

        final synchronized void m7299a(float f, float f2) {
            this.f4658c = f * -0.001f;
            this.f4659d = f2 * -0.001f;
            this.f4660e = SystemClock.uptimeMillis();
        }

        public final CameraPositionProvider m7298a(Camera camera) {
            float f;
            float f2;
            long uptimeMillis = SystemClock.uptimeMillis();
            synchronized (this) {
                long j = uptimeMillis - this.f4660e;
                this.f4660e = uptimeMillis;
                float exp = (float) Math.exp((double) (((float) (-j)) * 0.006f));
                f = (br.DEFAULT_BACKOFF_MULT - exp) / 0.006f;
                f2 = this.f4658c * f;
                f *= this.f4659d;
                this.f4658c *= exp;
                this.f4659d = exp * this.f4659d;
                if (Math.abs(this.f4658c) < 0.15f && Math.abs(this.f4659d) < 0.15f) {
                    this.f4658c = 0.0f;
                    this.f4659d = 0.0f;
                }
            }
            this.a = this.f4657b.m7464a(VectorMapController.m7307a(this.a, camera, f2, f));
            return (this.f4658c == 0.0f && this.f4659d == 0.0f) ? this.a : this;
        }

        public final int m7297a() {
            return (this.f4658c == 0.0f && this.f4659d == 0.0f) ? 0 : 2;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.q.c */
    static class VectorMapController extends BaseDynamicCameraPositionProvider {
        private final float f4661b;
        private final float f4662c;
        private final float f4663d;
        private final int f4664e;
        private final long f4665f;
        private final CameraPositionSanitizer f4666g;
        private DynamicCameraPositionProvider f4667h;

        protected VectorMapController(CameraPosition cameraPosition, CameraPositionSanitizer cameraPositionSanitizer, float f, float f2, float f3, int i) {
            super(cameraPosition);
            this.f4666g = cameraPositionSanitizer;
            this.f4661b = f;
            this.f4662c = f2;
            this.f4663d = f3;
            this.f4664e = i;
            this.f4665f = SystemClock.uptimeMillis();
        }

        public final CameraPositionProvider m7301a(Camera camera) {
            this.f4667h = new VectorMapController(this.a, VectorMapController.m7308a(this.a, camera, this.f4666g, this.f4661b, this.f4662c, this.f4663d), this.f4664e - ((int) (SystemClock.uptimeMillis() - this.f4665f)), true, 0.0f);
            return this.f4667h.m7293a(camera);
        }

        public final int m7300a() {
            if (this.f4667h == null) {
                return 0;
            }
            return this.f4667h.m7292a();
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.q.d */
    static class VectorMapController extends BaseDynamicCameraPositionProvider {
        private float f4668b;
        private float f4669c;
        private float f4670d;
        private float f4671e;
        private float f4672f;
        private float f4673g;
        private volatile boolean f4674h;
        private final CameraPositionSanitizer f4675i;
        private final float[] f4676j;

        public VectorMapController(CameraPosition cameraPosition, CameraPositionSanitizer cameraPositionSanitizer) {
            super(cameraPosition);
            this.f4676j = new float[2];
            this.f4675i = cameraPositionSanitizer;
        }

        final synchronized float[] m7304a(float f, float f2, float f3, float f4, float f5, float f6) {
            this.f4668b += f;
            this.f4669c += f2;
            this.f4670d += f5;
            this.f4671e += f6;
            if (!(f == 0.0f && f2 == 0.0f)) {
                this.f4672f = f3;
                this.f4673g = f4;
            }
            this.f4674h = true;
            this.f4676j[0] = Math.max(Math.min(this.a.m7457a() + this.f4668b, VectorMapController.f4679c), dm.DEFAULT_BACKOFF_MULT);
            this.f4676j[1] = VectorMapController.m7312c(this.a.m7462e() + this.f4669c);
            return this.f4676j;
        }

        public final CameraPositionProvider m7303a(Camera camera) {
            synchronized (this) {
                float max;
                float max2;
                float f = this.f4672f;
                float f2 = this.f4673g;
                float f3 = this.f4670d;
                float f4 = this.f4671e;
                if (this.f4668b < 0.0f) {
                    max = Math.max(this.f4668b, ((this.f4668b * this.f4668b) * this.f4668b) * 100.0f);
                } else {
                    max = Math.min(this.f4668b, ((this.f4668b * this.f4668b) * this.f4668b) * 100.0f);
                }
                if (this.f4669c < 0.0f) {
                    max2 = Math.max(this.f4669c, (this.f4669c * this.f4669c) * -0.1f);
                } else {
                    max2 = Math.min(this.f4669c, (this.f4669c * this.f4669c) * 0.1f);
                }
                this.f4668b -= max;
                this.f4669c -= max2;
                this.f4670d = 0.0f;
                this.f4671e = 0.0f;
                if (((double) Math.abs(max)) >= 0.001d || ((double) Math.abs(max2)) >= 0.001d || f3 != 0.0f || f4 != 0.0f) {
                    Object obj = (f3 == 0.0f && f4 == 0.0f) ? null : 1;
                    Object obj2 = max2 != 0.0f ? 1 : null;
                    Object obj3 = max != 0.0f ? 1 : null;
                    if (obj != null) {
                        this.a = VectorMapController.m7307a(this.a, camera, f3, f4);
                        if (!(obj2 == null && obj3 == null)) {
                            camera.m7427a(this.a);
                        }
                    }
                    if (obj2 != null) {
                        this.a = VectorMapController.m7306a(camera, this.f4675i, camera.m7435d(f, f2), max2);
                        if (obj3 != null) {
                            camera.m7427a(this.a);
                        }
                    }
                    if (obj3 == null) {
                        return this;
                    }
                    this.a = VectorMapController.m7308a(this.a, camera, this.f4675i, max, f, f2);
                    return this;
                }
                this.f4674h = false;
                CameraPosition cameraPosition = this.a;
                return cameraPosition;
            }
        }

        public final int m7302a() {
            return this.f4674h ? 2 : 0;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.q.e */
    public interface VectorMapController {
        void m7305b();
    }

    static {
        f4677a = (float) (1.0d / Math.log(2.0d));
        f4678b = null;
        f4679c = 21.0f;
    }

    public VectorMapController(Resources resources) {
        this.f4693q = 6;
        this.f4694r = br.DEFAULT_BACKOFF_MULT;
        this.f4681e = Camera.f4779a;
        this.f4684h = Camera.f4779a;
        this.f4680d = new CameraPositionSanitizer(resources);
        IndoorState.m5334a();
    }

    public final void m7323a(RepaintCallback repaintCallback) {
        this.f4687k = repaintCallback;
        this.f4692p = true;
    }

    public final void m7327a(CameraPositionSanitizer.CameraPositionSanitizer cameraPositionSanitizer) {
        this.f4680d.m7467a(cameraPositionSanitizer);
    }

    public final synchronized void m7322a(PositionChangeFinishedCallback positionChangeFinishedCallback) {
        this.f4689m = positionChangeFinishedCallback;
    }

    public final synchronized void m7321a(aa aaVar) {
        this.f4690n = aaVar;
    }

    public final synchronized void m7324a(VectorMapController vectorMapController) {
        this.f4691o = vectorMapController;
    }

    public final void m7325a(ViewPointRecorder viewPointRecorder) {
        this.f4688l = viewPointRecorder;
    }

    public final float m7318a(Point point) {
        CameraPositionSanitizer.CameraPositionSanitizer a = this.f4680d.m7465a();
        return a == null ? f4679c : a.m7221a(point);
    }

    public final float m7314a() {
        CameraPositionSanitizer.CameraPositionSanitizer a = this.f4680d.m7465a();
        if (a == null) {
            return dm.DEFAULT_BACKOFF_MULT;
        }
        return a.m7222c();
    }

    public final void m7319a(float f) {
        this.f4680d.m7466a(67.5f);
    }

    public final CameraPosition m7330b() {
        return this.f4681e;
    }

    public final float m7333c() {
        return this.f4683g;
    }

    private void m7310a(CameraPositionProvider cameraPositionProvider, CameraPosition cameraPosition) {
        synchronized (this) {
            if (this.f4691o != null) {
                this.f4691o.m7305b();
            }
            if (cameraPositionProvider instanceof DynamicCameraPositionProvider) {
                this.f4685i = true;
            }
            this.f4684h = cameraPositionProvider;
            this.f4686j = true;
            this.f4681e = this.f4680d.m7464a(this.f4684h.m7291b());
            if (cameraPosition != null) {
                cameraPosition = this.f4680d.m7464a(cameraPosition);
            }
            this.f4682f = cameraPosition;
            notifyAll();
        }
        if (this.f4687k != null) {
            this.f4687k.m6765a(false, true);
        }
    }

    private void m7309a(CameraPositionProvider cameraPositionProvider, int i) {
        m7326a(cameraPositionProvider, i, i == 0 ? 0 : -1);
    }

    public final void m7326a(CameraPositionProvider cameraPositionProvider, int i, int i2) {
        if (this.f4692p || !this.f4684h.equals(cameraPositionProvider)) {
            if (this.f4692p) {
                i = 0;
                i2 = 0;
            }
            this.f4692p = false;
            CameraPosition cameraPosition = this.f4681e;
            CameraPosition a = cameraPositionProvider.m7291b().m7458a(cameraPosition);
            this.f4683g = a.m7457a();
            NavigationParameters a2 = ParameterManager.m6641a();
            float abs = Math.abs(a.m7457a() - cameraPosition.m7457a());
            if (!a2.m6626b() || abs > ((float) a2.m6627c())) {
                m7310a(cameraPositionProvider, null);
                return;
            }
            int min = 1073741824 >> Math.min(Math.round((a.m7457a() + cameraPosition.m7457a()) * 0.5f), 30);
            float c = a.m7460c().m5951c(cameraPosition.m7460c());
            float f = c / ((float) min);
            if ((f <= ((float) this.f4693q) ? 1 : null) != null) {
                if (i == 0) {
                    m7310a(cameraPositionProvider, null);
                    return;
                }
                if (i == -1) {
                    min = (int) ((((Math.min(abs, 8.0f) / 8.0f) * 0.75f) + 1.5f) * 700.0f);
                } else {
                    min = i;
                }
                m7310a(new VectorMapController(cameraPosition, cameraPositionProvider, min, true, 0.0f), null);
                return;
            } else if (i2 == 0) {
                m7310a(cameraPositionProvider, null);
                return;
            } else {
                if (i2 == -1) {
                    min = (int) (((float) Math.min((int) (((((f - ((float) this.f4693q)) / ((1.07374182E9f / ((float) min)) - ((float) this.f4693q))) * 4.1f) + 1.4f) * 700.0f), dm.DEFAULT_TIMEOUT_MS)) * this.f4694r);
                } else {
                    min = i2;
                }
                m7310a(new VectorMapController(cameraPosition, cameraPositionProvider, min, false, (float) Math.pow(2.0d, ((Math.log((double) c) * ((double) f4677a)) * 2.39d) - 58.71d)), a);
                return;
            }
        }
        this.f4689m.m7194a(this.f4681e);
    }

    public final int m7329b(Camera camera) {
        int i = 0;
        synchronized (this) {
            if (this.f4684h instanceof DynamicCameraPositionProvider) {
                DynamicCameraPositionProvider dynamicCameraPositionProvider = (DynamicCameraPositionProvider) this.f4684h;
                int a = dynamicCameraPositionProvider.m7292a();
                this.f4684h = dynamicCameraPositionProvider.m7293a(camera);
                this.f4681e = this.f4680d.m7464a(this.f4684h.m7291b());
                notifyAll();
                i = dynamicCameraPositionProvider.m7292a() | a;
            } else {
                this.f4684h = this.f4680d.m7464a(this.f4684h.m7291b());
                this.f4681e = this.f4684h.m7291b();
                this.f4685i = false;
                notifyAll();
            }
            camera.m7427a(this.f4681e);
            if (this.f4686j && i == 0) {
                if (this.f4689m != null) {
                    this.f4689m.m7194a(this.f4681e);
                }
                this.f4686j = false;
            }
            if (this.f4690n != null) {
                this.f4690n.m6695b(this.f4681e);
            }
        }
        if ((i & 2) == 0 && this.f4688l != null) {
            this.f4688l.m7350a(camera);
        }
        return i;
    }

    public final CameraPosition m7334i() {
        return this.f4682f;
    }

    public final synchronized void m7320a(float f, float f2) {
        if (this.f4684h instanceof VectorMapController) {
            ((VectorMapController) this.f4684h).m7304a(0.0f, 0.0f, 0.0f, 0.0f, f, f2);
        } else {
            CameraPositionProvider vectorMapController = new VectorMapController(this.f4684h.m7291b(), this.f4680d);
            vectorMapController.m7304a(0.0f, 0.0f, 0.0f, 0.0f, f, f2);
            m7310a(vectorMapController, null);
        }
    }

    public final synchronized void m7331b(float f, float f2) {
        if (!(this.f4684h instanceof VectorMapController)) {
            m7310a(new VectorMapController(this.f4684h.m7291b(), this.f4680d), null);
        }
        ((VectorMapController) this.f4684h).m7299a(f, f2);
    }

    public final synchronized float m7315a(float f, float f2, float f3) {
        float a;
        CameraPosition cameraPosition = this.f4681e;
        if (cameraPosition.m7457a() == this.f4680d.m7464a(new CameraPosition(cameraPosition.m7460c(), cameraPosition.m7457a() + f, cameraPosition.m7461d(), cameraPosition.m7462e(), cameraPosition.m7463f())).m7457a()) {
            a = cameraPosition.m7457a();
        } else {
            if (this.f4684h instanceof VectorMapController) {
                a = ((VectorMapController) this.f4684h).m7304a(f, 0.0f, f2, f3, 0.0f, 0.0f)[0];
            } else {
                CameraPositionProvider vectorMapController = new VectorMapController(this.f4684h.m7291b(), this.f4680d);
                float f4 = vectorMapController.m7304a(f, 0.0f, f2, f3, 0.0f, 0.0f)[0];
                m7310a(vectorMapController, null);
                a = f4;
            }
            this.f4683g = a;
        }
        return a;
    }

    public final float m7317a(float f, int i) {
        CameraPosition cameraPosition = this.f4681e;
        CameraPositionProvider a = this.f4680d.m7464a(new CameraPosition(cameraPosition.m7460c(), cameraPosition.m7457a() + f, cameraPosition.m7461d(), cameraPosition.m7462e(), cameraPosition.m7463f()));
        m7309a(a, i);
        return a.m7457a();
    }

    public final float m7316a(float f, float f2, float f3, int i) {
        CameraPosition cameraPosition = this.f4681e;
        this.f4683g = cameraPosition.m7457a() + f;
        m7310a(new VectorMapController(cameraPosition, this.f4680d, f, f2, f3, i), null);
        return Math.max(Math.min(cameraPosition.m7457a() + f, f4679c), dm.DEFAULT_BACKOFF_MULT);
    }

    public final synchronized float m7328b(float f, float f2, float f3) {
        float f4;
        if (this.f4684h instanceof VectorMapController) {
            f4 = ((VectorMapController) this.f4684h).m7304a(0.0f, f3, f, f2, 0.0f, 0.0f)[1];
        } else {
            CameraPositionProvider vectorMapController = new VectorMapController(this.f4681e, this.f4680d);
            m7310a(vectorMapController, null);
            f4 = vectorMapController.m7304a(0.0f, f3, f, f2, 0.0f, 0.0f)[1];
        }
        return f4;
    }

    public final synchronized void m7332b(float f, int i) {
        CameraPosition cameraPosition = this.f4681e;
        m7309a(this.f4680d.m7464a(new CameraPosition(cameraPosition.m7460c(), cameraPosition.m7457a(), cameraPosition.m7461d() + f, cameraPosition.m7462e(), cameraPosition.m7463f())), 0);
    }

    public static CameraPosition m7307a(CameraPosition cameraPosition, Camera camera, float f, float f2) {
        float r = camera.m7450r() * f;
        float r2 = ((-f2) * camera.m7450r()) / FloatMath.cos(camera.m7443k() * 0.017453292f);
        Point o = camera.m7447o();
        Point p = camera.m7448p();
        Point point = new Point(o.m5958f(), o.m5960g());
        Point point2 = new Point(p.m5958f(), p.m5960g());
        Point.m5935b(point, r, point);
        Point.m5935b(point2, r2, point2);
        Point b = camera.m7430b();
        float a = cameraPosition.m7457a();
        int h = b.m5962h();
        Point e = b.m5957e(point);
        Point.m5931a(e, point2, e);
        e.m5944a(h);
        return new CameraPosition(e, a, cameraPosition.m7461d(), cameraPosition.m7462e(), 0.0f);
    }

    public static CameraPosition m7306a(Camera camera, CameraPositionSanitizer cameraPositionSanitizer, Point point, float f) {
        Point b = camera.m7430b();
        int f2 = b.m5958f() - point.m5958f();
        int g = b.m5960g() - point.m5960g();
        float f3 = (float) ((((double) f) * 3.141592653589793d) / 180.0d);
        float sin = FloatMath.sin(-f3);
        f3 = FloatMath.cos(-f3);
        return cameraPositionSanitizer.m7464a(new CameraPosition(new Point((int) (((float) point.m5958f()) + ((((float) f2) * f3) - (((float) g) * sin))), (int) (((((float) g) * f3) + (((float) f2) * sin)) + ((float) point.m5960g()))), camera.m7444l(), camera.m7443k(), VectorMapController.m7312c(camera.m7442j() + f), 0.0f));
    }

    public static CameraPosition m7308a(CameraPosition cameraPosition, Camera camera, CameraPositionSanitizer cameraPositionSanitizer, float f, float f2, float f3) {
        float e = f2 - (((float) camera.m7437e()) / dm.DEFAULT_BACKOFF_MULT);
        float f4 = f3 - (((float) camera.m7438f()) / dm.DEFAULT_BACKOFF_MULT);
        CameraPosition a = cameraPositionSanitizer.m7464a(VectorMapController.m7307a(cameraPosition, camera, e, f4));
        CameraPosition cameraPosition2 = new CameraPosition(a.m7460c(), Math.min(f4679c, a.m7457a() + f), a.m7461d(), a.m7462e(), 0.0f);
        camera.m7427a(cameraPosition2);
        return VectorMapController.m7307a(cameraPosition2, camera, -e, -f4);
    }

    private static float m7312c(float f) {
        float f2 = f;
        while (((double) f2) >= 360.0d) {
            f2 = (float) (((double) f2) - 360.0d);
        }
        while (((double) f2) < 0.0d) {
            f2 = (float) (((double) f2) + 360.0d);
        }
        return f2;
    }
}
