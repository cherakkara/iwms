package com.google.android.m4b.maps.bf;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.google.android.m4b.maps.be.IndoorStateInterface;
import com.google.android.m4b.maps.be.MapIdleWaiter;
import com.google.android.m4b.maps.be.MapRenderer;
import com.google.android.m4b.maps.be.MapToolbar;
import com.google.android.m4b.maps.be.MapViewUtils;
import com.google.android.m4b.maps.be.ad;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.android.m4b.maps.be.ag.MyLocationLayerImpl;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.google.android.m4b.maps.be.aq;
import com.google.android.m4b.maps.be.az;
import com.google.android.m4b.maps.be.bm;
import com.google.android.m4b.maps.be.bp;
import com.google.android.m4b.maps.be.bt;
import com.google.android.m4b.maps.be.bx;
import com.google.android.m4b.maps.be.ca;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ag;
import com.google.android.m4b.maps.p042r.ai;
import java.util.Calendar;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.m4b.maps.bf.e */
public final class MapRendererViewLite extends View implements MapRenderer {
    private final MyLocationRendererLite f6142a;
    private final OverlayRendererManagerLite f6143b;
    private final CameraManagerLite f6144c;
    private final MapIdleWaiterLite f6145d;
    private final Resources f6146e;
    private final BaseMapRendererLite f6147f;
    private final BaseMapFetcherLite f6148g;
    private ProjectionLite f6149h;
    private final GestureDetector f6150i;
    private final OnGestureListenerLite f6151j;
    private ag f6152k;
    private ai f6153l;
    private final Context f6154m;
    private final bx f6155n;
    private final MapToolbar f6156o;
    private final ca f6157p;
    private final SnapshotterLite f6158q;

    public final /* synthetic */ ProjectionDelegate m9650r() {
        return this.f6144c.m9608b();
    }

    public static MapRendererViewLite m9618a(Context context, Resources resources, bt btVar, ScheduledExecutorService scheduledExecutorService, View view, bm bmVar, String str, boolean z, bx bxVar, TextView textView) {
        return new MapRendererViewLite(context, resources, btVar, scheduledExecutorService, view, bmVar, bxVar, textView);
    }

    private MapRendererViewLite(Context context, Resources resources, bt btVar, ScheduledExecutorService scheduledExecutorService, View view, bm bmVar, bx bxVar, TextView textView) {
        super(context);
        this.f6154m = context;
        this.f6146e = resources;
        this.f6143b = new OverlayRendererManagerLite(this, scheduledExecutorService);
        this.f6142a = new MyLocationRendererLite(this);
        Handler handler = new Handler(Looper.getMainLooper());
        this.f6144c = new CameraManagerLite(this, resources, handler);
        this.f6145d = MapIdleWaiterLite.m9615a(handler);
        this.f6156o = bmVar.m8915e();
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        this.f6151j = new OnGestureListenerLite(this, this.f6143b);
        this.f6150i = new GestureDetector(this.f6151j);
        this.f6147f = new BaseMapRendererLite(this);
        this.f6148g = new BaseMapFetcherLite(this.f6147f, textView, btVar.m9047b(), this.f6144c, Calendar.getInstance(), az.m8761b(this.f6154m));
        this.f6155n = bxVar;
        setFocusable(true);
        setClickable(true);
        this.f6157p = new ca(this, this.f6143b);
        ViewCompat.setAccessibilityDelegate(this, this.f6157p);
        this.f6158q = new SnapshotterLite(this, view, bmVar.m8910a(), MapViewUtils.m9534a(handler));
    }

    public final void m9633d() {
        this.f6148g.m9586a();
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f6148g.m9586a();
    }

    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f6149h = this.f6147f.m9590a(canvas, getWidth(), getHeight());
        if (this.f6149h != null) {
            this.f6143b.m9673a(canvas, this.f6149h);
            this.f6142a.m9664a(canvas, this.f6149h);
        }
        this.f6157p.m9291a();
        boolean e = this.f6143b.m9686e();
        ad c = this.f6143b.m9682c();
        this.f6143b.m9669a();
        if (c != null) {
            this.f6156o.m9512a(true, true, c, e);
        } else if (e) {
            this.f6156o.m9513b();
        } else {
            this.f6156o.m9512a(true, false, c, e);
        }
    }

    public final void m9624a(ag agVar) {
        this.f6152k = agVar;
    }

    public final void m9625a(ai aiVar) {
        this.f6153l = aiVar;
    }

    public final View m9635f() {
        return this;
    }

    public final bp m9637g() {
        return this.f6144c;
    }

    public final com.google.android.m4b.maps.be.ai m9641i() {
        return this.f6143b;
    }

    public final MyLocationLayerImpl m9642j() {
        return this.f6142a;
    }

    public final MarkerManagerImpl m9639h() {
        return this.f6143b;
    }

    public final IndoorStateInterface m9643k() {
        return null;
    }

    public final aq m9644l() {
        return this.f6158q;
    }

    public final MapIdleWaiter m9645m() {
        return this.f6145d;
    }

    public final Resources getResources() {
        return this.f6146e;
    }

    public final void m9631c() {
    }

    public final void m9651y() {
    }

    public final void m9628b() {
    }

    public final void m9622a() {
    }

    public final void m9634e(boolean z) {
        az.m8757a(5, "Toggling gestures is not supported in Lite Mode");
    }

    public final boolean m9652z() {
        return false;
    }

    public final void m9636f(boolean z) {
        az.m8757a(5, "Toggling gestures is not supported in Lite Mode");
    }

    public final boolean m9619A() {
        return false;
    }

    public final void m9638g(boolean z) {
        az.m8757a(5, "Toggling gestures is not supported in Lite Mode");
    }

    public final boolean m9620B() {
        return false;
    }

    public final void m9640h(boolean z) {
        az.m8757a(5, "Toggling gestures is not supported in Lite Mode");
    }

    public final boolean m9621C() {
        return false;
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
    }

    public final boolean m9646n() {
        return false;
    }

    public final void m9626a(boolean z) {
        az.m8757a(5, "Traffic is not supported in Lite Mode");
    }

    public final boolean m9647o() {
        return false;
    }

    public final boolean m9630b(boolean z) {
        az.m8757a(5, "Indoor is not supported in Lite Mode");
        return false;
    }

    public final boolean m9648p() {
        return false;
    }

    public final void m9632c(boolean z) {
        az.m8757a(5, "Buildings are not supported in Lite Mode");
    }

    public final void m9623a(int i) {
        this.f6148g.m9587a(i);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.f6150i.onTouchEvent(motionEvent);
        return true;
    }

    public final boolean m9627a(MotionEvent motionEvent) {
        if (this.f6149h == null) {
            return false;
        }
        if (this.f6152k == null) {
            this.f6155n.m9069a(this.f6149h.f6193e, this.f6143b.m9682c(), this.f6143b.m9686e());
        } else {
            try {
                this.f6152k.m11099a(this.f6149h.m9698a(new Point((int) motionEvent.getX(), (int) motionEvent.getY())));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        return true;
    }

    public final boolean m9629b(MotionEvent motionEvent) {
        if (this.f6149h == null || this.f6153l == null) {
            return false;
        }
        try {
            this.f6153l.m11102a(this.f6149h.m9698a(new Point((int) motionEvent.getX(), (int) motionEvent.getY())));
            return true;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.f6157p.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    public final String m9649q() {
        return "L";
    }
}
