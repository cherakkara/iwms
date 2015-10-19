package com.google.android.m4b.maps.bj;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;
import com.google.android.m4b.maps.be.ab;
import com.google.android.m4b.maps.be.ap;
import com.google.android.m4b.maps.be.ap.ReverseGeocodeDataRequest;
import com.google.android.m4b.maps.be.ar;
import com.google.android.m4b.maps.be.at.StreetViewPanoramaImpl;
import com.google.android.m4b.maps.be.av;
import com.google.android.m4b.maps.be.ay;
import com.google.android.m4b.maps.be.bt;
import com.google.android.m4b.maps.bj.DepthMap.DepthMap;
import com.google.android.m4b.maps.bj.ad.PanoramaManager;
import com.google.android.m4b.maps.bj.ag.Renderer;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.model.StreetViewPanoramaLocation;
import com.google.android.m4b.maps.model.StreetViewPanoramaOrientation;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaCameraChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaClickListener;
import com.google.android.m4b.maps.p061y.ConversionUtils;
import com.google.android.m4b.maps.p061y.Gmm6RendererInitializer;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.olacabs.customer.p076d.dm;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* renamed from: com.google.android.m4b.maps.bj.e */
public final class StreetViewSurfaceView extends SurfaceView implements OnDoubleTapListener, OnGestureListener, av, PanoramaManager, Renderer, ai {
    private Integer f6446A;
    private int f6447B;
    private float f6448C;
    private float f6449D;
    private float f6450E;
    private String f6451F;
    private Toast f6452G;
    private boolean f6453H;
    private boolean f6454I;
    private boolean f6455J;
    private final Handler f6456K;
    private Runnable f6457L;
    private final Handler f6458M;
    private final Object f6459N;
    private StreetViewSurfaceView f6460O;
    private float f6461P;
    private float f6462Q;
    private float f6463R;
    private float f6464S;
    private boolean f6465T;
    private float f6466a;
    private final Timer f6467b;
    private ag f6468c;
    private ad f6469d;
    private String f6470e;
    private MapPoint f6471f;
    private PanoramaLink f6472g;
    private boolean f6473h;
    private PanoramaConfig f6474i;
    private ab f6475j;
    private ay f6476k;
    private LatLng f6477l;
    private ay f6478m;
    private float f6479n;
    private float f6480o;
    private StreetViewGestureController f6481p;
    private IOnStreetViewPanoramaChangeListener f6482q;
    private StreetViewPanoramaImpl f6483r;
    private IOnStreetViewPanoramaCameraChangeListener f6484s;
    private IOnStreetViewPanoramaClickListener f6485t;
    private boolean f6486u;
    private int f6487v;
    private boolean f6488w;
    private Highlighter f6489x;
    private StreetViewSurfaceView f6490y;
    private int f6491z;

    /* renamed from: com.google.android.m4b.maps.bj.e.1 */
    class StreetViewSurfaceView extends Handler {
        private /* synthetic */ StreetViewSurfaceView f6426a;

        StreetViewSurfaceView(StreetViewSurfaceView streetViewSurfaceView) {
            this.f6426a = streetViewSurfaceView;
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    StreetViewSurfaceView.m9866a(this.f6426a, message.arg1 != 0, (PanoramaConfig) message.obj);
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    StreetViewSurfaceView streetViewSurfaceView = this.f6426a;
                    int i = message.arg1;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.f6426a.m9907c(message.arg1);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    this.f6426a.m9886a();
                default:
                    throw new IllegalArgumentException("Unknown message id " + message.what);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.e.2 */
    class StreetViewSurfaceView implements ReverseGeocodeDataRequest {
        private /* synthetic */ StreetViewSurfaceView f6427a;

        StreetViewSurfaceView(StreetViewSurfaceView streetViewSurfaceView) {
            this.f6427a = streetViewSurfaceView;
        }

        public final void m9859a(ap apVar) {
            if (apVar.m8589j() > 0) {
                this.f6427a.setContentDescription(this.f6427a.getContext().getResources().getString(com.google.android.m4b.maps.R.R.YOUR_LOCATION) + ": " + apVar.m8583a(0).m8579a());
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.e.3 */
    class StreetViewSurfaceView implements Runnable {
        private /* synthetic */ long f6428a;
        private /* synthetic */ Interpolator f6429b;
        private /* synthetic */ long f6430c;
        private /* synthetic */ ay f6431d;
        private /* synthetic */ float f6432e;
        private /* synthetic */ float f6433f;
        private /* synthetic */ float f6434g;
        private /* synthetic */ StreetViewSurfaceView f6435h;

        StreetViewSurfaceView(StreetViewSurfaceView streetViewSurfaceView, long j, Interpolator interpolator, long j2, ay ayVar, float f, float f2, float f3) {
            this.f6435h = streetViewSurfaceView;
            this.f6428a = j;
            this.f6429b = interpolator;
            this.f6430c = j2;
            this.f6431d = ayVar;
            this.f6432e = f;
            this.f6433f = f2;
            this.f6434g = f3;
        }

        public final void run() {
            long uptimeMillis = SystemClock.uptimeMillis() - this.f6428a;
            float interpolation = this.f6429b.getInterpolation(((float) uptimeMillis) / ((float) this.f6430c));
            float c = ar.m8600c(this.f6431d.m8752c() + (this.f6432e * interpolation));
            this.f6435h.m9890a(new ay(ar.m8590a(this.f6431d.m8750b() + (this.f6433f * interpolation)), c, (interpolation * this.f6434g) + this.f6431d.m8755e()));
            if (uptimeMillis < this.f6430c) {
                this.f6435h.f6456K.postDelayed(this, 16);
                return;
            }
            this.f6435h.f6456K.removeCallbacks(this.f6435h.f6457L);
            this.f6435h.f6457L = null;
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.e.a */
    class StreetViewSurfaceView implements Runnable {
        private final boolean f6436a;
        private float f6437b;
        private final boolean f6438c;
        private float f6439d;
        private /* synthetic */ StreetViewSurfaceView f6440e;

        StreetViewSurfaceView(StreetViewSurfaceView streetViewSurfaceView, float f, float f2) {
            boolean z = true;
            this.f6440e = streetViewSurfaceView;
            this.f6436a = f > 0.0f;
            if (!this.f6436a) {
                f = -f;
            }
            this.f6437b = f;
            if (f2 <= 0.0f) {
                z = false;
            }
            this.f6438c = z;
            if (!this.f6438c) {
                f2 = -f2;
            }
            this.f6439d = f2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
            r7 = this;
            r6 = 1063172178; // 0x3f5eb852 float:0.87 double:5.25276849E-315;
            r5 = 1000593162; // 0x3ba3d70a float:0.005 double:4.94358707E-315;
            r4 = 0;
            r0 = r7.f6440e;
            r1 = r0.f6459N;
            monitor-enter(r1);
        L_0x000e:
            r0 = r7.f6440e;	 Catch:{ InterruptedException -> 0x0065 }
            r0 = r0.f6459N;	 Catch:{ InterruptedException -> 0x0065 }
            r2 = 30;
            r0.wait(r2);	 Catch:{ InterruptedException -> 0x0065 }
        L_0x0019:
            r0 = r7.f6440e;	 Catch:{ all -> 0x0058 }
            r0 = r0.f6474i;	 Catch:{ all -> 0x0058 }
            if (r0 == 0) goto L_0x0063;
        L_0x0021:
            r0 = r7.f6437b;	 Catch:{ all -> 0x0058 }
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 > 0) goto L_0x002d;
        L_0x0027:
            r0 = r7.f6439d;	 Catch:{ all -> 0x0058 }
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x0063;
        L_0x002d:
            r2 = r7.f6440e;	 Catch:{ all -> 0x0058 }
            r3 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
            r0 = r7.f6436a;	 Catch:{ all -> 0x0058 }
            if (r0 == 0) goto L_0x005b;
        L_0x0035:
            r0 = r7.f6437b;	 Catch:{ all -> 0x0058 }
        L_0x0037:
            r3 = r3 * r0;
            r0 = r7.f6438c;	 Catch:{ all -> 0x0058 }
            if (r0 == 0) goto L_0x005f;
        L_0x003c:
            r0 = r7.f6439d;	 Catch:{ all -> 0x0058 }
        L_0x003e:
            r2.m9887a(r3, r0);	 Catch:{ all -> 0x0058 }
            r0 = r7.f6437b;	 Catch:{ all -> 0x0058 }
            r0 = r0 * r6;
            r0 = r0 - r5;
            r2 = 0;
            r0 = java.lang.Math.max(r0, r2);	 Catch:{ all -> 0x0058 }
            r7.f6437b = r0;	 Catch:{ all -> 0x0058 }
            r0 = r7.f6439d;	 Catch:{ all -> 0x0058 }
            r0 = r0 * r6;
            r0 = r0 - r5;
            r2 = 0;
            r0 = java.lang.Math.max(r0, r2);	 Catch:{ all -> 0x0058 }
            r7.f6439d = r0;	 Catch:{ all -> 0x0058 }
            goto L_0x000e;
        L_0x0058:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
        L_0x005b:
            r0 = r7.f6437b;	 Catch:{ all -> 0x0058 }
            r0 = -r0;
            goto L_0x0037;
        L_0x005f:
            r0 = r7.f6439d;	 Catch:{ all -> 0x0058 }
            r0 = -r0;
            goto L_0x003e;
        L_0x0063:
            monitor-exit(r1);	 Catch:{ all -> 0x0058 }
            return;
        L_0x0065:
            r0 = move-exception;
            goto L_0x0019;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bj.e.a.run():void");
        }

        final void m9860a() {
            synchronized (this.f6440e.f6459N) {
                this.f6437b = 0.0f;
                this.f6439d = 0.0f;
                this.f6440e.f6459N.notify();
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.bj.e.b */
    public static class StreetViewSurfaceView {
        public boolean f6441a;
        public boolean f6442b;
        public boolean f6443c;
        public boolean f6444d;
        public boolean f6445e;
    }

    static /* synthetic */ void m9866a(StreetViewSurfaceView streetViewSurfaceView, boolean z, PanoramaConfig panoramaConfig) {
        if (!z && panoramaConfig == null) {
            new StringBuilder("SV received panorama ").append(panoramaConfig);
        }
        if (!z || streetViewSurfaceView.f6488w) {
            streetViewSurfaceView.f6473h = panoramaConfig == null;
            streetViewSurfaceView.m9867a(panoramaConfig);
        }
        if (!z) {
            streetViewSurfaceView.invalidate();
        }
    }

    public final /* synthetic */ Object m9885a(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        if (this.f6474i == null) {
            return null;
        }
        float[] b = this.f6468c.m9831b(ar.m8611l(streetViewPanoramaOrientation.f7620b), ar.m8611l(streetViewPanoramaOrientation.f7619a + 90.0f));
        return b != null ? new Point((int) b[0], (int) b[1]) : null;
    }

    public static StreetViewSurfaceView m9861a(Context context, bt btVar) {
        Gmm6RendererInitializer.m11679a(context, btVar);
        Renderer streetViewSurfaceView = new StreetViewSurfaceView(context);
        streetViewSurfaceView.f6469d = new ad(new HttpCache(3, context.getCacheDir().getAbsolutePath(), 100));
        streetViewSurfaceView.f6465T = false;
        streetViewSurfaceView.setFocusable(true);
        streetViewSurfaceView.setFocusableInTouchMode(true);
        streetViewSurfaceView.setClickable(true);
        if (streetViewSurfaceView.f6468c != null) {
            throw new IllegalArgumentException("mRenderer already exists");
        }
        ad adVar = streetViewSurfaceView.f6469d;
        float f = streetViewSurfaceView.f6466a;
        streetViewSurfaceView.f6468c = new ag(adVar);
        boolean z = streetViewSurfaceView.f6465T;
        streetViewSurfaceView.f6468c.m9821a(streetViewSurfaceView.getContext(), streetViewSurfaceView.getHolder(), streetViewSurfaceView, (View) streetViewSurfaceView);
        ag agVar = streetViewSurfaceView.f6468c;
        streetViewSurfaceView.f6489x.m9982a(streetViewSurfaceView.f6468c);
        ViewCompat.setAccessibilityDelegate(streetViewSurfaceView, streetViewSurfaceView.f6468c.m9836e());
        streetViewSurfaceView.requestFocus();
        streetViewSurfaceView.f6488w = true;
        return streetViewSurfaceView;
    }

    private StreetViewSurfaceView(Context context) {
        super(context);
        this.f6467b = null;
        this.f6486u = true;
        this.f6487v = 0;
        this.f6488w = false;
        this.f6453H = true;
        this.f6454I = true;
        this.f6455J = true;
        this.f6456K = new Handler();
        this.f6458M = new StreetViewSurfaceView(this);
        this.f6459N = new Object();
        this.f6460O = null;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        this.f6466a = displayMetrics.density;
        new StringBuilder("SV ScreenDensity: ").append(this.f6466a).append(", DPI: ").append(displayMetrics.densityDpi);
        this.f6478m = new ay();
        this.f6475j = new ab(50);
        this.f6490y = new StreetViewSurfaceView();
        this.f6481p = new StreetViewGestureController(context, this, this, this);
        this.f6481p.m9854a(false);
        this.f6489x = new Highlighter();
    }

    private void m9879l() {
        if (this.f6468c != null) {
            this.f6468c.m9822a(new ay(this.f6478m));
            this.f6458M.sendMessage(Message.obtain(this.f6458M, 3));
        }
    }

    final void m9886a() {
        if (this.f6484s != null && this.f6486u) {
            try {
                this.f6484s.m11124a(ConversionUtils.m11636a(this.f6478m));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f6486u = true;
    }

    public final void invalidate() {
        int i = 0;
        super.invalidate();
        if (this.f6468c != null) {
            if (this.f6452G != null) {
                this.f6452G.cancel();
                this.f6452G = null;
            }
            String str = this.f6472g != null ? this.f6472g.f6623c : this.f6470e;
            if (str != null || this.f6471f != null) {
                PanoramaConfig a = this.f6475j.m9742a(str);
                if (a != null) {
                    a.f6599o = true;
                    this.f6469d.m9766a(a.f6592h);
                    m9867a(a);
                    int size = a.f6602r.size();
                    int i2 = 0;
                    while (i2 < size) {
                        this.f6469d.m9768a(this.f6468c, (ac) a.f6602r.get(i2), i2 == size + -1);
                        i2++;
                    }
                    m9882o();
                } else {
                    this.f6469d.m9767a(this, str, this.f6471f, this.f6446A, str == null, this.f6488w);
                    if (str != null) {
                        this.f6469d.m9769a(this.f6468c, str, 0, 0, 0, 1, false);
                        this.f6469d.m9769a(this.f6468c, str, 0, 0, 0, 2, false);
                        this.f6469d.m9769a(this.f6468c, str, 0, 0, 0, 3, false);
                        this.f6469d.m9769a(this.f6468c, str, 0, 0, 0, 4, false);
                        this.f6469d.m9769a(this.f6468c, str, 0, 0, 0, 5, false);
                        this.f6469d.m9769a(this.f6468c, str, 0, 0, 0, 6, false);
                        this.f6469d.m9769a(this.f6468c, str, 0, 0, 0, -1, true);
                    }
                    String str2 = Trace.NULL;
                }
            } else if (this.f6474i != null) {
                m9882o();
            } else if (this.f6473h) {
                r0 = com.google.android.m4b.maps.R.R.invalid_panorama_data;
            } else {
                r0 = com.google.android.m4b.maps.R.R.no_panorama_data;
            }
            StreetViewSurfaceView streetViewSurfaceView = this.f6490y;
            streetViewSurfaceView.f6441a = true;
            streetViewSurfaceView.f6442b = false;
            streetViewSurfaceView.f6443c = this.f6473h;
            streetViewSurfaceView.f6444d = false;
            streetViewSurfaceView.f6445e = false;
            if (this.f6474i != null && this.f6472g == null) {
                streetViewSurfaceView.f6442b = true;
                streetViewSurfaceView.f6444d = this.f6474i.m9998c();
                streetViewSurfaceView.f6445e = this.f6474i.f6585a;
            }
            streetViewSurfaceView = this.f6490y;
            if (streetViewSurfaceView.f6443c || streetViewSurfaceView.f6444d || streetViewSurfaceView.f6445e) {
                i = AbstractSpiCall.DEFAULT_TIMEOUT;
            } else if (streetViewSurfaceView.f6441a) {
                i = !streetViewSurfaceView.f6442b ? Constants.MILLIS_IN_A_SECOND : ActivityTrace.MAX_TRACES;
            }
            if (i != this.f6491z) {
                this.f6491z = i;
            }
        }
    }

    public final void m9894a(StreetViewPanoramaCamera streetViewPanoramaCamera, String str) {
        if (str.equals(Trace.NULL)) {
            m9890a(ConversionUtils.m11633a(streetViewPanoramaCamera));
        } else {
            m9899a(str, null, null, ConversionUtils.m11633a(streetViewPanoramaCamera));
        }
    }

    public final void m9903b() {
        if (this.f6468c != null) {
            this.f6468c.m9818a();
        }
    }

    public final void m9906c() {
        if (this.f6468c != null) {
            this.f6468c.m9832c();
        }
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.f6468c.m9836e().dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.f6487v = 0;
        if (m9883p()) {
            if (this.f6457L != null) {
                this.f6456K.removeCallbacks(this.f6457L);
            }
            this.f6468c.m9833c(1);
            if (this.f6481p.m9855a(motionEvent) && motionEvent.getAction() == 1) {
                this.f6487v++;
                m9865a(motionEvent);
            }
        }
        return true;
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onKeyDown(int r6, android.view.KeyEvent r7) {
        /*
        r5 = this;
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r2 = 0;
        r1 = 1;
        r3 = r5.m9883p();
        switch(r6) {
            case 4: goto L_0x001d;
            case 19: goto L_0x0020;
            case 20: goto L_0x002b;
            case 21: goto L_0x0036;
            case 22: goto L_0x0041;
            case 23: goto L_0x004c;
            case 35: goto L_0x0069;
            case 45: goto L_0x0079;
            case 48: goto L_0x004c;
            case 62: goto L_0x0069;
            default: goto L_0x000d;
        };
    L_0x000d:
        r1 = r2;
        r0 = r2;
    L_0x000f:
        if (r0 == 0) goto L_0x0081;
    L_0x0011:
        if (r1 == 0) goto L_0x001c;
    L_0x0013:
        r1 = r5.f6468c;
        if (r1 == 0) goto L_0x001c;
    L_0x0017:
        r1 = r5.f6468c;
        r1.m9833c(r2);
    L_0x001c:
        return r0;
    L_0x001d:
        r1 = r2;
        r0 = r2;
        goto L_0x000f;
    L_0x0020:
        if (r3 == 0) goto L_0x0086;
    L_0x0022:
        r3 = r5.f6454I;
        if (r3 == 0) goto L_0x0086;
    L_0x0026:
        r5.m9869b(r0);
        r0 = r1;
        goto L_0x000f;
    L_0x002b:
        if (r3 == 0) goto L_0x0086;
    L_0x002d:
        r0 = r5.f6454I;
        if (r0 == 0) goto L_0x0086;
    L_0x0031:
        r5.m9869b(r4);
        r0 = r1;
        goto L_0x000f;
    L_0x0036:
        if (r3 == 0) goto L_0x0086;
    L_0x0038:
        r0 = r5.f6454I;
        if (r0 == 0) goto L_0x0086;
    L_0x003c:
        r5.m9864a(r4);
        r0 = r1;
        goto L_0x000f;
    L_0x0041:
        if (r3 == 0) goto L_0x0086;
    L_0x0043:
        r3 = r5.f6454I;
        if (r3 == 0) goto L_0x0086;
    L_0x0047:
        r5.m9864a(r0);
        r0 = r1;
        goto L_0x000f;
    L_0x004c:
        if (r3 == 0) goto L_0x0086;
    L_0x004e:
        r3 = r5.f6453H;
        if (r3 == 0) goto L_0x0086;
    L_0x0052:
        r3 = r5.f6478m;
        r3 = r3.m8755e();
        r4 = r5.f6447B;
        r4 = r4 + -1;
        r4 = (float) r4;
        r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1));
        if (r4 >= 0) goto L_0x0067;
    L_0x0061:
        r5.m9874c(r0);
        r0 = r1;
        r1 = r2;
        goto L_0x000f;
    L_0x0067:
        r0 = -r3;
        goto L_0x0061;
    L_0x0069:
        if (r3 == 0) goto L_0x0086;
    L_0x006b:
        r0 = r5.f6453H;
        if (r0 == 0) goto L_0x0086;
    L_0x006f:
        r5.m9874c(r4);
        r0 = 0;
        r5.m9869b(r0);
        r0 = r1;
        r1 = r2;
        goto L_0x000f;
    L_0x0079:
        if (r3 == 0) goto L_0x0086;
    L_0x007b:
        r5.m9881n();
        r0 = r1;
        r1 = r2;
        goto L_0x000f;
    L_0x0081:
        r0 = super.onKeyDown(r6, r7);
        goto L_0x001c;
    L_0x0086:
        r0 = r1;
        r1 = r2;
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bj.e.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    protected final void onDetachedFromWindow() {
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        m9865a(motionEvent);
        return true;
    }

    public final void onLongPress(MotionEvent motionEvent) {
    }

    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (m9883p() && !this.f6489x.m9985b(motionEvent2.getX(), motionEvent2.getY()) && this.f6454I) {
            this.f6479n = 0.02f * f;
            this.f6480o = -0.01f * f2;
            m9887a((this.f6450E * f) / ((float) getWidth()), (this.f6449D * f2) / (-22.5f * ((float) getHeight())));
        }
        return true;
    }

    public final StreetViewPanoramaOrientation m9884a(int i, int i2) {
        if (this.f6474i == null) {
            return null;
        }
        float[] a = this.f6468c.m9827a((float) i, (float) i2, false);
        if (a != null) {
            return new StreetViewPanoramaOrientation(ar.m8612m(a[1]) - 90.0f, ar.m8612m(a[0]));
        }
        return null;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.f6489x.m9983a()) {
            return false;
        }
        if (this.f6454I) {
            float a = ar.m8592a(this.f6479n, -2.5f, 2.5f) * 0.8f;
            float a2 = ar.m8592a(this.f6480o, -2.5f, 2.5f) * 0.5f;
            synchronized (this.f6459N) {
                if (this.f6460O != null) {
                    this.f6460O.m9860a();
                }
                this.f6460O = new StreetViewSurfaceView(this, a, a2);
                new Thread(this.f6460O, "Flinger").start();
            }
        }
        return true;
    }

    public final void onShowPress(MotionEvent motionEvent) {
    }

    public final boolean onDown(MotionEvent motionEvent) {
        synchronized (this.f6459N) {
            if (this.f6460O != null) {
                this.f6460O.m9860a();
            }
        }
        this.f6489x.m9984a(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.f6474i.f6609y != null && this.f6455J) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.f6474i.f6609y != null) {
                float[] a = this.f6468c.m9827a(x, y, false);
                this.f6474i.m9995a(a[0], a[1], a);
                float[] fArr = new float[2];
                this.f6474i.f6609y.m9960a();
                String b = this.f6474i.f6609y.m9962b(a[0], a[1], fArr);
                if (!(b == null || b.equals(this.f6474i.f6592h))) {
                    PanoramaConfig panoramaConfig = this.f6474i;
                    float f = fArr[0];
                    float f2 = fArr[1];
                    fArr[0] = (f + ar.m8611l(panoramaConfig.f6603s)) + 0.5f;
                    fArr[1] = (ar.m8609j(panoramaConfig.f6610z) * FloatMath.cos(ar.m8610k(fArr[0]) - ar.m8614o(panoramaConfig.f6604t))) + f2;
                    if (!this.f6474i.f6609y.m9958a(a[0], a[1]).m9949a()) {
                        this.f6478m.m8747a(ar.m8612m(ar.m8598b(fArr[0])));
                        this.f6478m.m8751b(ar.m8598b(fArr[1]) * dm.DEFAULT_BACKOFF_MULT);
                    }
                    DepthMap b2 = this.f6474i.f6609y.m9961b(a[0], a[1]);
                    Renderer renderer = new Renderer(b2.f6524a, b2.f6525b, this.f6478m, Constants.MILLIS_IN_A_SECOND);
                    renderer.m9792a(this.f6474i.f6609y);
                    this.f6468c.m9824a(renderer);
                    m9899a(b, null, null, this.f6478m);
                }
            }
        }
        return true;
    }

    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.f6487v++;
        if (this.f6485t != null && this.f6487v == 3) {
            try {
                this.f6485t.m11128a(m9884a((int) motionEvent.getX(), (int) motionEvent.getY()));
                this.f6487v = 0;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        return false;
    }

    private void m9865a(MotionEvent motionEvent) {
        int c = this.f6489x.m9986c(motionEvent.getX(), motionEvent.getY());
        if (c != -1 && c != -2) {
            this.f6487v = 0;
            PanoramaLink d = this.f6468c.m9834d(c);
            if (d == null) {
                return;
            }
            if (d.f6623c == null || d.f6623c.length() == 0) {
                Toast.makeText(getContext(), com.google.android.m4b.maps.R.R.panorama_disabled, 0).show();
                return;
            }
            this.f6473h = false;
            this.f6470e = null;
            this.f6471f = null;
            this.f6472g = d;
            new StringBuilder("SV step to panorama ").append(this.f6472g.f6623c);
            this.f6451F = null;
            this.f6468c.m9819a(-1.0f, -1.0f);
            this.f6474i.f6609y.m9960a();
            DepthMap a = this.f6474i.f6609y.m9959a(d.f6623c);
            if (a != null) {
                Renderer renderer = new Renderer(a.f6524a, a.f6525b, this.f6478m, Constants.MILLIS_IN_A_SECOND);
                renderer.m9792a(this.f6474i.f6609y);
                this.f6468c.m9824a(renderer);
            } else {
                this.f6488w = true;
            }
            m9899a(d.f6623c, null, null, this.f6478m);
        }
    }

    public final void m9888a(int i) {
        m9871b(1, (int) (2000.0d + (0.8d * ((double) i))));
    }

    public final void m9904b(int i) {
        m9871b(2, -2);
    }

    final void m9907c(int i) {
        if (i == -2) {
            try {
                if (ab.m8297a(getContext())) {
                    if (this.f6474i == null || this.f6474i.f6593i == null) {
                        setContentDescription(getContext().getResources().getString(com.google.android.m4b.maps.R.R.YOUR_LOCATION) + ": invalid point");
                    } else {
                        DataRequest apVar = new ap(this.f6474i.f6593i, 21.0f);
                        apVar.m8584a(new StreetViewSurfaceView(this));
                        DataRequestDispatcher.m11393a().m11451c(apVar);
                    }
                }
                if (this.f6483r != null) {
                    this.f6483r.m8644a(this.f6474i != null ? this.f6474i.f6595k : Trace.NULL, m9909d());
                }
                if (this.f6482q != null) {
                    this.f6482q.m11126a(m9909d());
                }
                this.f6488w = false;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public final StreetViewPanoramaLocation m9909d() {
        if (this.f6474i == null) {
            return null;
        }
        PanoramaConfig panoramaConfig = this.f6474i;
        return new StreetViewPanoramaLocation(ConversionUtils.m11638a(panoramaConfig.f6608x), panoramaConfig.f6593i, panoramaConfig.f6592h);
    }

    public final StreetViewPanoramaCamera m9911e() {
        return ConversionUtils.m11636a(m9880m());
    }

    private ay m9880m() {
        if (this.f6478m == null) {
            return this.f6476k;
        }
        return this.f6478m;
    }

    public final void m9901a(boolean z, PanoramaConfig panoramaConfig) {
        int i;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        this.f6458M.sendMessage(Message.obtain(this.f6458M, 0, i, 0, panoramaConfig));
        if (panoramaConfig == null && this.f6488w) {
            m9871b(2, -2);
        }
    }

    private final void m9871b(int i, int i2) {
        this.f6458M.sendMessage(Message.obtain(this.f6458M, i, i2, 0));
    }

    public final void m9899a(String str, LatLng latLng, Integer num, ay ayVar) {
        m9872b(str, latLng, num, ayVar);
    }

    private void m9881n() {
        float f;
        if (this.f6476k != null) {
            this.f6478m = this.f6476k;
            this.f6476k = null;
        } else if (!this.f6488w) {
            float f2;
            if (this.f6472g != null) {
                float f3 = Float.POSITIVE_INFINITY;
                float f4 = this.f6472g.f6621a;
                if (this.f6474i != null) {
                    PanoramaLink[] panoramaLinkArr = this.f6474i.f6608x;
                    if (panoramaLinkArr != null) {
                        int length = panoramaLinkArr.length;
                        int i = 0;
                        f = 0.0f;
                        while (i < length) {
                            f2 = (panoramaLinkArr[i].f6621a - f4) + 180.0f;
                            f2 = (f2 - (FloatMath.floor(0.0027777778f * f2) * 360.0f)) - 180.0f;
                            float abs = Math.abs(f2);
                            if (abs > 25.0f || abs > f3) {
                                f2 = f;
                                f = f3;
                            } else {
                                f = abs;
                            }
                            i++;
                            f3 = f;
                            f = f2;
                        }
                        f2 = this.f6478m.m8750b() + f;
                    }
                }
                f = 0.0f;
                f2 = this.f6478m.m8750b() + f;
            } else {
                f2 = this.f6474i != null ? this.f6474i.f6603s : 0.0f;
            }
            this.f6478m = new ay(f2, 0.5f, this.f6478m.m8755e());
        } else {
            return;
        }
        f = (float) getHeight();
        this.f6448C = ((float) getWidth()) / f;
        this.f6449D = ag.m9796a(this.f6448C);
        this.f6450E = ag.m9807b(this.f6448C);
        this.f6447B = Math.min(this.f6474i.f6594j, Math.max(0, (int) ar.m8602d(((((float) this.f6474i.f6589e) / f) * this.f6449D) * 0.0055555557f)) + 2);
        if (this.f6468c != null) {
            this.f6468c.m9820a(this.f6447B);
        }
        this.f6486u = false;
        m9870b(0.0f, 0.0f);
        m9874c(0.0f);
    }

    public final synchronized void m9887a(float f, float f2) {
        m9870b(f, f2);
        m9879l();
    }

    private synchronized void m9864a(float f) {
        m9870b(f, 0.0f);
        m9879l();
    }

    private synchronized void m9869b(float f) {
        m9878f(f);
        m9879l();
    }

    private void m9874c(float f) {
        m9876d(f);
        m9879l();
    }

    private void m9870b(float f, float f2) {
        if (this.f6454I) {
            this.f6478m.m8747a(ar.m8590a(this.f6478m.m8750b() + (this.f6478m.m8756f() * f)));
            m9878f(f2);
        }
    }

    private void m9876d(float f) {
        if (this.f6453H) {
            this.f6478m.m8748a(f, this.f6447B - 1);
        }
    }

    private static float m9877e(float f) {
        return 0.5f - (0.0055555557f * f);
    }

    private void m9878f(float f) {
        if (this.f6454I) {
            float f2 = this.f6478m.m8756f() * 0.125f;
            float e = f2 + StreetViewSurfaceView.m9877e(this.f6474i.f6607w);
            float e2 = StreetViewSurfaceView.m9877e(this.f6474i.f6606v) - f2;
            if (e > e2) {
                e2 = (e2 + e) * 0.5f;
                e = e2;
            }
            this.f6478m.m8751b(ar.m8592a((f2 * f) + this.f6478m.m8752c(), e, e2));
        }
    }

    private void m9872b(String str, LatLng latLng, Integer num, ay ayVar) {
        this.f6473h = false;
        this.f6472g = null;
        this.f6470e = str;
        this.f6471f = latLng != null ? new MapPoint(latLng.f7554a, latLng.f7555b) : null;
        this.f6446A = num;
        this.f6477l = null;
        this.f6476k = ayVar;
        invalidate();
    }

    private CharSequence m9882o() {
        if (this.f6474i == null) {
            return Trace.NULL;
        }
        int i;
        if (this.f6474i.f6585a) {
            i = com.google.android.m4b.maps.R.R.panorama_disabled;
            return null;
        } else if (this.f6474i.m9997b()) {
            i = com.google.android.m4b.maps.R.R.service_unavailable;
            return null;
        } else {
            String str = this.f6474i.f6597m;
            CharSequence charSequence = this.f6474i.f6596l;
            if (str == null) {
                return charSequence == null ? Trace.NULL : charSequence;
            } else {
                if (charSequence == null) {
                    return str;
                }
                return String.format(getContext().getString(com.google.android.m4b.maps.R.R.street_range_name_format), new Object[]{str, charSequence});
            }
        }
    }

    private void m9867a(PanoramaConfig panoramaConfig) {
        if (panoramaConfig != null && this.f6488w) {
            String str = panoramaConfig.f6592h;
            if (!(this.f6474i == null || this.f6474i.f6609y == null || this.f6474i.f6592h.equals(str))) {
                this.f6488w = false;
                this.f6474i.f6609y.m9960a();
                DepthMap a = this.f6474i.f6609y.m9959a(str);
                if (!(a == null || this.f6468c == null)) {
                    Renderer renderer = new Renderer(a.f6524a, a.f6525b, m9880m(), Constants.MILLIS_IN_A_SECOND);
                    renderer.m9792a(this.f6474i.f6609y);
                    this.f6468c.m9824a(renderer);
                }
            }
        }
        this.f6474i = panoramaConfig;
        if (!(this.f6474i == null || this.f6474i.f6609y == null)) {
            this.f6474i.f6609y.m9960a();
        }
        if (this.f6468c != null) {
            this.f6468c.m9825a(this.f6474i);
        }
        if (this.f6474i != null) {
            this.f6475j.m9743a(panoramaConfig);
            m9881n();
            this.f6472g = null;
            this.f6470e = null;
            this.f6471f = null;
        }
    }

    private boolean m9883p() {
        return (this.f6474i == null || this.f6468c == null) ? false : true;
    }

    public final boolean m9902a(ah ahVar) {
        if (this.f6453H) {
            float[] a = this.f6468c.m9827a(ahVar.m9837a(), ahVar.m9838b(), false);
            if (a == null) {
                return false;
            }
            float f = a[0];
            float f2 = a[1];
            float l = ar.m8611l(this.f6478m.m8750b());
            float c = this.f6478m.m8752c() / dm.DEFAULT_BACKOFF_MULT;
            float f3 = this.f6478m.m8756f();
            this.f6463R = f;
            this.f6464S = f2;
            this.f6461P = ar.m8605f(ar.m8601c(l, f)) / ar.m8605f(ar.m8611l(this.f6450E / dm.DEFAULT_BACKOFF_MULT) * f3);
            this.f6462Q = ar.m8605f(ar.m8601c(c, f2)) / ar.m8605f(ar.m8611l(this.f6449D / dm.DEFAULT_BACKOFF_MULT) * f3);
            m9876d(((ahVar.m9839c() - ahVar.m9840d()) / 180.0f) / this.f6466a);
            f = this.f6478m.m8756f();
            f2 = this.f6463R + ar.m8606g(this.f6461P * ar.m8605f(ar.m8611l(this.f6450E / dm.DEFAULT_BACKOFF_MULT) * f));
            f = ar.m8606g(ar.m8605f(f * ar.m8611l(this.f6449D / dm.DEFAULT_BACKOFF_MULT)) * this.f6462Q) + this.f6464S;
            this.f6478m.m8747a(ar.m8612m(f2));
            this.f6478m.m8751b(f * dm.DEFAULT_BACKOFF_MULT);
            m9879l();
        }
        return true;
    }

    public final void m9912f() {
        for (int i = 0; i < 5; i++) {
            m9874c(-0.2f);
            synchronized (this) {
                try {
                    wait(30);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public final void m9900a(boolean z) {
        this.f6453H = z;
    }

    public final boolean m9913g() {
        return this.f6453H;
    }

    public final void m9905b(boolean z) {
        this.f6454I = z;
    }

    public final boolean m9914h() {
        return this.f6454I;
    }

    public final void m9908c(boolean z) {
        this.f6455J = z;
        if (this.f6468c != null) {
            this.f6468c.m9830b(z);
            this.f6468c.m9828b();
        }
    }

    public final boolean m9915i() {
        return this.f6455J;
    }

    public final void m9910d(boolean z) {
        if (this.f6468c != null) {
            this.f6468c.m9826a(z);
            this.f6468c.m9828b();
        }
    }

    public final boolean m9916j() {
        if (this.f6468c == null) {
            return false;
        }
        return this.f6468c.m9835d();
    }

    public final void m9890a(ay ayVar) {
        if (this.f6478m != null) {
            this.f6486u = !this.f6478m.equals(ayVar);
        }
        this.f6478m = ayVar;
        m9879l();
    }

    public final void m9893a(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
        ay a = ConversionUtils.m11633a(streetViewPanoramaCamera);
        if (this.f6457L != null) {
            this.f6456K.removeCallbacks(this.f6457L);
        }
        a.m8753c(ar.m8592a(a.m8755e(), 0.0f, (float) this.f6447B));
        if (j == 0) {
            m9890a(a);
            return;
        }
        ay m = m9880m();
        float c = a.m8752c() - m.m8752c();
        this.f6457L = new StreetViewSurfaceView(this, SystemClock.uptimeMillis(), new AccelerateDecelerateInterpolator(), j, m, c, ar.m8599b(a.m8750b(), m.m8750b()), a.m8755e() - m.m8755e());
        this.f6456K.post(this.f6457L);
    }

    public final void m9889a(StreetViewPanoramaImpl streetViewPanoramaImpl) {
        this.f6483r = streetViewPanoramaImpl;
    }

    public final void m9896a(IOnStreetViewPanoramaChangeListener iOnStreetViewPanoramaChangeListener) {
        this.f6482q = iOnStreetViewPanoramaChangeListener;
    }

    public final void m9895a(IOnStreetViewPanoramaCameraChangeListener iOnStreetViewPanoramaCameraChangeListener) {
        this.f6484s = iOnStreetViewPanoramaCameraChangeListener;
    }

    public final void m9897a(IOnStreetViewPanoramaClickListener iOnStreetViewPanoramaClickListener) {
        this.f6485t = iOnStreetViewPanoramaClickListener;
    }

    public final View m9917k() {
        return this;
    }

    public final void m9898a(String str) {
        this.f6488w = true;
        m9872b(str, null, null, m9880m());
    }

    public final void m9891a(LatLng latLng) {
        this.f6488w = true;
        m9872b(null, latLng, null, m9880m());
    }

    public final void m9892a(LatLng latLng, int i) {
        this.f6488w = true;
        m9872b(null, latLng, Integer.valueOf(i), m9880m());
    }
}
