package com.google.android.m4b.maps.av;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.widget.TextView;
import com.google.android.m4b.maps.aj.GestureController;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.av.VectorMapGestureListener.VectorMapGestureListener;
import com.google.android.m4b.maps.av.ah.FrameRateRegulator;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.av.ar.GmmEglConfigChooser;
import com.google.android.m4b.maps.aw.GLTileCacheManager;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ax.CameraPosition;
import com.google.android.m4b.maps.bb.GLHudOverlay;
import com.google.android.m4b.maps.bc.LabelTheme;
import com.google.android.m4b.maps.be.MapToolbar;
import com.google.android.m4b.maps.p059w.AndroidBuilds;
import com.google.p025a.p028c.ar;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Gmm6VectorMapView */
public class aq extends as implements FrameRateRegulator, VectorMapGestureListener {
    private final ae f4363a;
    private final Resources f4364b;
    private Renderer f4365c;
    private GestureController f4366d;
    private Gmm6VectorMapView f4367e;
    private Gmm6VectorMapView f4368f;
    private VectorMapGestureListener f4369g;
    private VectorMapController f4370h;
    private al f4371i;
    private boolean f4372j;
    private CameraPosition f4373k;
    private long f4374l;
    private boolean f4375m;
    private ah f4376n;
    private MapToolbar f4377o;
    private RenderInstrumentation f4378p;

    /* renamed from: com.google.android.m4b.maps.av.aq.a */
    public interface Gmm6VectorMapView {
        boolean m6947a(Point point);

        boolean m6948b(Point point);
    }

    /* renamed from: com.google.android.m4b.maps.av.aq.b */
    public interface Gmm6VectorMapView {
        void m6949a(Point point);

        void m6950b(Point point);
    }

    public aq(Context context, Resources resources, TextView textView, MapToolbar mapToolbar) {
        super(context);
        this.f4363a = new ae();
        this.f4374l = Long.MIN_VALUE;
        this.f4375m = false;
        this.f4364b = resources;
        this.f4377o = mapToolbar;
        m6966i(true);
        this.f4369g = new VectorMapGestureListener(this);
        this.f4366d = new GestureController();
        this.f4366d.m5137a(getContext(), this.f4369g);
        setFocusable(true);
        setClickable(true);
        float f = this.f4364b.getDisplayMetrics().density;
        this.f4376n = new ah(this);
        List<GmmEglConfigChooser> a = ar.m2515a();
        if (AndroidBuilds.m11561a()) {
            a.add(new GmmEglConfigChooser(8, 8, 8, 0, 16, 8));
        }
        a.add(new GmmEglConfigChooser(5, 6, 5, 0, 16, 8));
        a.add(new GmmEglConfigChooser(5, 6, 5, 0, 16, 0));
        for (GmmEglConfigChooser a2 : a) {
            a2.m7018a(false);
        }
        m6962a(new ar((GmmEglConfigChooser[]) a.toArray(new GmmEglConfigChooser[a.size()])));
        this.f4365c = new Renderer(this.f4376n, this.f4364b, new Camera(Camera.f4779a, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, f, null), TileOverlay.m6720a(ai.f3423a, this.f4364b), null, null, textView);
        m6963a(this.f4365c);
        m6965b(0);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
    }

    public void m6989a(boolean z, boolean z2) {
        this.f4376n.m6770a(z, z2);
    }

    public final void m6986a(VectorMapController vectorMapController) {
        this.f4370h = vectorMapController;
        this.f4370h.m7323a(this.f4376n);
        this.f4370h.m7327a(this.f4365c);
        this.f4365c.m7243a(this.f4370h);
    }

    public final void m6982a(Gmm6VectorMapView gmm6VectorMapView) {
        this.f4368f = gmm6VectorMapView;
    }

    public final void m6983a(Gmm6VectorMapView gmm6VectorMapView) {
        this.f4367e = gmm6VectorMapView;
    }

    public final void m6981a(al alVar) {
        this.f4365c.m7240a(alVar);
    }

    public final void m6993b(al alVar) {
        this.f4365c.m7252b(alVar);
        if (this.f4371i == alVar) {
            m7006u();
        }
    }

    public final ak m6976a(GLOverlay gLOverlay) {
        return this.f4365c.m7238a(gLOverlay);
    }

    public final MyLocationOverlay m6996d(boolean z) {
        return this.f4365c.m7249b(true);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f4365c.m7248a(!z);
    }

    public boolean isOpaque() {
        return true;
    }

    public void m6991b() {
        m7006u();
        this.f4376n.m6779f();
        super.m6964b();
        GLTileCacheManager a = GLTileCacheManager.m7380a();
        if (a != null && this.f4365c != null) {
            a.m7395b(this.f4365c.m7260h());
        }
    }

    public void m6977a() {
        super.m6961a();
        this.f4376n.m6778e();
    }

    public void m6994c() {
        this.f4365c.m7239a();
    }

    public final void m7010y() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - this.f4374l < 20000) {
            this.f4365c.m7255c(true);
        } else {
            this.f4365c.m7255c(false);
        }
        this.f4374l = uptimeMillis;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    public final VectorMapController m7007v() {
        return this.f4370h;
    }

    public final void m6980a(ac acVar) {
        this.f4365c.m7251b(acVar);
    }

    public final void m6988a(LabelTheme labelTheme) {
        this.f4365c.m7245a(labelTheme);
    }

    public final Bitmap m6975a(Bitmap bitmap) {
        Bitmap a;
        synchronized (this.f4365c) {
            m7008w();
            a = this.f4365c.m7237a(bitmap);
        }
        return a;
    }

    public final Camera m7005t() {
        return new Camera(this.f4370h.m7330b(), getWidth(), getHeight(), m6969d());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return isEnabled() && isClickable() && this.f4366d.m5138a(motionEvent);
    }

    public final boolean m6990a(MotionEvent motionEvent) {
        Camera camera = null;
        if (this.f4370h == null) {
            return false;
        }
        GLHudOverlay f = this.f4365c.m7258f();
        if (f.j_() && f.m8178c(motionEvent.getX(), motionEvent.getY(), null)) {
            m6989a(false, true);
            return true;
        }
        ArrayList b = this.f4365c.m7250b();
        for (int size = b.size() - 1; size >= 0; size--) {
            al alVar = (al) b.get(size);
            if (alVar.j_()) {
                if (camera == null) {
                    camera = new Camera(this.f4370h.m7330b(), getWidth(), getHeight(), m6969d());
                }
                if (alVar.m6679c(motionEvent.getX(), motionEvent.getY(), camera)) {
                    m6989a(false, true);
                    return true;
                }
            }
        }
        return false;
    }

    public void m6978a(float f, float f2) {
        if (this.f4367e != null) {
            Gmm6VectorMapView gmm6VectorMapView = this.f4367e;
        }
    }

    public final boolean m6999e(float f, float f2) {
        if (this.f4370h == null || this.f4365c.m7257e() == null) {
            return false;
        }
        Camera camera = new Camera(this.f4370h.m7330b(), getWidth(), getHeight(), m6969d());
        camera.m7435d(f, f2);
        return this.f4365c.m7257e().a_(f, f2, camera);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m6997d(float r12, float r13) {
        /*
        r11 = this;
        r7 = 1;
        r0 = r11.f4370h;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r4 = new com.google.android.m4b.maps.ax.b;
        r0 = r11.f4370h;
        r0 = r0.m7330b();
        r1 = r11.getWidth();
        r2 = r11.getHeight();
        r3 = r11.m6969d();
        r4.<init>(r0, r1, r2, r3);
        r3 = r4.m7435d(r12, r13);
        r0 = 0;
        r2 = r11.f4372j;
        r1 = r11.f4365c;
        r1 = r1.m7258f();
        if (r1 == 0) goto L_0x0036;
    L_0x002c:
        r0 = r11.f4365c;
        r0 = r0.m7258f();
        r0 = r0.m8174a(r12, r13, r3, r4);
    L_0x0036:
        if (r0 != 0) goto L_0x004a;
    L_0x0038:
        r1 = r11.f4365c;
        r1 = r1.m7257e();
        if (r1 == 0) goto L_0x004a;
    L_0x0040:
        r0 = r11.f4365c;
        r0 = r0.m7257e();
        r0 = r0.m6793a(r12, r13, r3, r4);
    L_0x004a:
        r1 = r11.f4368f;
        if (r1 == 0) goto L_0x0056;
    L_0x004e:
        if (r0 != 0) goto L_0x0056;
    L_0x0050:
        r0 = r11.f4368f;
        r0 = r0.m6947a(r3);
    L_0x0056:
        r1 = r11.f4365c;
        r8 = r1.m7250b();
        r9 = r8.size();
        r5 = new java.util.ArrayList;
        r5.<init>();
        r1 = r9 + -1;
        r6 = r0;
    L_0x0068:
        if (r6 != 0) goto L_0x0098;
    L_0x006a:
        if (r1 < 0) goto L_0x0098;
    L_0x006c:
        r0 = r8.get(r1);
        r0 = (com.google.android.m4b.maps.av.al) r0;
        r10 = r0.m6683l();
        if (r10 == 0) goto L_0x0082;
    L_0x0078:
        r0 = (com.google.android.m4b.maps.av.BubbleBlowerOverlay) r0;
        r5.add(r0);
        r0 = r6;
    L_0x007e:
        r1 = r1 + -1;
        r6 = r0;
        goto L_0x0068;
    L_0x0082:
        r10 = r5.isEmpty();
        if (r10 == 0) goto L_0x010a;
    L_0x0088:
        r10 = r11.f4365c;
        r10 = r10.m7257e();
        if (r0 == r10) goto L_0x010a;
    L_0x0090:
        r0 = r0.m6671a(r12, r13, r3, r4);
        if (r0 == 0) goto L_0x010a;
    L_0x0096:
        r0 = r7;
        goto L_0x007e;
    L_0x0098:
        if (r6 != 0) goto L_0x0108;
    L_0x009a:
        r0 = r5.isEmpty();
        if (r0 != 0) goto L_0x0108;
    L_0x00a0:
        if (r2 == 0) goto L_0x00b0;
    L_0x00a2:
        r0 = r11.f4370h;
        r0 = r0.m7330b();
        r1 = r11.f4373k;
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x00b5;
    L_0x00b0:
        r0 = r11.f4363a;
        r0.m6707a(r7);
    L_0x00b5:
        r0 = r11.f4363a;
        r1 = r12;
        r2 = r13;
        r0 = r0.m6708a(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x0108;
    L_0x00bf:
        r1 = r7;
    L_0x00c0:
        if (r1 != 0) goto L_0x00c7;
    L_0x00c2:
        r0 = r11.f4377o;
        r0.m9513b();
    L_0x00c7:
        r0 = r9 + -1;
        r2 = r0;
    L_0x00ca:
        if (r1 != 0) goto L_0x00ee;
    L_0x00cc:
        if (r2 < 0) goto L_0x00ee;
    L_0x00ce:
        r0 = r8.get(r2);
        r0 = (com.google.android.m4b.maps.av.al) r0;
        r5 = r0.m6683l();
        if (r5 != 0) goto L_0x0106;
    L_0x00da:
        r5 = r11.f4365c;
        r5 = r5.m7257e();
        if (r0 == r5) goto L_0x0106;
    L_0x00e2:
        r0 = r0.m6671a(r12, r13, r3, r4);
        if (r0 == 0) goto L_0x0106;
    L_0x00e8:
        r0 = r7;
    L_0x00e9:
        r1 = r2 + -1;
        r2 = r1;
        r1 = r0;
        goto L_0x00ca;
    L_0x00ee:
        if (r1 != 0) goto L_0x00f9;
    L_0x00f0:
        r0 = r11.f4367e;
        if (r0 == 0) goto L_0x00f9;
    L_0x00f4:
        r0 = r11.f4367e;
        r0.m6950b(r3);
    L_0x00f9:
        r0 = r11.f4370h;
        r0 = r0.m7330b();
        r11.f4373k = r0;
        r11.m7008w();
        goto L_0x0005;
    L_0x0106:
        r0 = r1;
        goto L_0x00e9;
    L_0x0108:
        r1 = r6;
        goto L_0x00c0;
    L_0x010a:
        r0 = r6;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.aq.d(float, float):void");
    }

    public final void m6979a(float f, float f2, float f3) {
        if (this.f4367e != null) {
            Gmm6VectorMapView gmm6VectorMapView = this.f4367e;
        }
    }

    public final void m7000f(float f, float f2) {
        if (this.f4370h != null) {
            boolean z;
            Camera camera = new Camera(this.f4370h.m7330b(), getWidth(), getHeight(), m6969d());
            Point d = camera.m7435d(f, f2);
            boolean z2 = false;
            if (this.f4365c.m7257e() != null) {
                z2 = this.f4365c.m7257e().m6795b(f, f2, d, camera);
            }
            if (this.f4368f == null || z2) {
                z = z2;
            } else {
                z = this.f4368f.m6948b(d);
            }
            ArrayList b = this.f4365c.m7250b();
            for (int size = b.size() - 1; size >= 0; size--) {
                al alVar = (al) b.get(size);
                if (alVar != this.f4365c.m7257e() && alVar.m6675b(f, f2, d, camera)) {
                    z = true;
                    break;
                }
            }
            if (!(z || this.f4367e == null)) {
                this.f4367e.m6949a(d);
            }
            m7008w();
        }
    }

    public final void m6992b(float f, float f2) {
        if (this.f4370h != null) {
            Camera camera = new Camera(this.f4370h.m7330b(), getWidth(), getHeight(), m6969d());
            ArrayList b = this.f4365c.m7250b();
            for (int size = b.size() - 1; size >= 0; size--) {
                al alVar = (al) b.get(size);
                if (alVar.m6676b(f, f2, camera)) {
                    this.f4371i = alVar;
                    m7008w();
                    return;
                }
            }
        }
    }

    public final boolean m6995c(float f, float f2) {
        Object obj = null;
        if (this.f4370h == null) {
            return false;
        }
        ArrayList b = this.f4365c.m7250b();
        int size = b.size();
        GLHudOverlay f3 = this.f4365c.m7258f();
        if (f3.j_() && f3.m8177c(f, f2, null, null)) {
            m6989a(false, true);
            return true;
        }
        Camera camera = null;
        for (int i = size - 1; i >= 0; i--) {
            al alVar = (al) b.get(i);
            if (alVar.j_()) {
                if (camera == null) {
                    camera = new Camera(this.f4370h.m7330b(), getWidth(), getHeight(), m6969d());
                    obj = camera.m7435d(f, f2);
                }
                if (alVar.m6678c(f, f2, obj, camera)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void m7006u() {
        if (this.f4371i != null) {
            this.f4371i.d_();
            this.f4371i = null;
            m7008w();
        }
    }

    public final void m6987a(BubbleBlower bubbleBlower, BubbleView bubbleView) {
        this.f4372j = true;
        this.f4365c.m7244a(bubbleBlower, bubbleView);
    }

    public final void m7004s() {
        this.f4372j = false;
        this.f4365c.m7256d();
    }

    public final void m6984a(RenderInstrumentation renderInstrumentation) {
        if (this.f4365c != null) {
            this.f4365c.m7241a(renderInstrumentation);
        }
        this.f4378p = renderInstrumentation;
    }

    public final void m7008w() {
        if (this.f4378p != null) {
            this.f4378p.m7203c();
        }
        super.m6968w();
    }

    public final void m7009x() {
        if (this.f4378p != null) {
            this.f4378p.m7201b();
        }
    }

    private float m6969d() {
        return this.f4364b.getDisplayMetrics().density;
    }

    public final void m6998e(boolean z) {
        this.f4369g.m7335a(z);
    }

    public final boolean m7011z() {
        return this.f4369g.m7336a();
    }

    public final void m7001f(boolean z) {
        this.f4369g.m7340b(z);
    }

    public final boolean m6970A() {
        return this.f4369g.m7341b();
    }

    public final void m7002g(boolean z) {
        this.f4369g.m7342c(z);
    }

    public final boolean m6971B() {
        return this.f4369g.m7343c();
    }

    public final void m7003h(boolean z) {
        this.f4369g.m7344d(z);
    }

    public final boolean m6972C() {
        return this.f4369g.m7345d();
    }

    public final Renderer m6973D() {
        return this.f4365c;
    }

    public final TileOverlay m6974E() {
        return this.f4365c.m7259g();
    }

    public final void m6985a(TileOverlay tileOverlay) {
        this.f4365c.m7242a(tileOverlay);
    }

    public Resources getResources() {
        return this.f4364b;
    }
}
