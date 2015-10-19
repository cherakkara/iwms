package com.google.android.m4b.maps.p061y;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.au.AlternatePaintfeParameters;
import com.google.android.m4b.maps.av.IndoorTileOverlay;
import com.google.android.m4b.maps.av.TileOverlay;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.av.VectorMapController;
import com.google.android.m4b.maps.av.ViewPointRecorder;
import com.google.android.m4b.maps.av.ac;
import com.google.android.m4b.maps.av.al;
import com.google.android.m4b.maps.av.aq;
import com.google.android.m4b.maps.av.aq.Gmm6VectorMapView;
import com.google.android.m4b.maps.bc.LabelTheme;
import com.google.android.m4b.maps.be.IndoorStateInterface;
import com.google.android.m4b.maps.be.MapIdleWaiter;
import com.google.android.m4b.maps.be.MapViewUtils;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.android.m4b.maps.be.ag.MyLocationLayerImpl;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.google.android.m4b.maps.be.ba;
import com.google.android.m4b.maps.be.be;
import com.google.android.m4b.maps.be.bm;
import com.google.android.m4b.maps.be.bp;
import com.google.android.m4b.maps.be.bt;
import com.google.android.m4b.maps.be.ca;
import com.google.android.m4b.maps.be.cc;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.TaskRunnerManager;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.android.m4b.maps.p042r.ag;
import com.google.android.m4b.maps.p042r.ai;
import com.google.android.m4b.maps.p058v.DeferredTasksHelper;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.android.m4b.maps.p061y.VectorMapView.VectorMapView;
import com.google.p025a.p026a.Strings;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.m4b.maps.y.x */
public class VectorMapViewImpl extends aq implements VectorMapView {
    private static /* synthetic */ boolean f8250A;
    private final VectorMapController f8251a;
    private final VectorMapControllerAdapter f8252b;
    private final CameraManagerImpl f8253c;
    private final OverlayRendererManagerImpl f8254d;
    private final GLMarkerManager f8255e;
    private final ViewPointRecorder f8256f;
    private final cc f8257g;
    private final Gmm6VectorMapView f8258h;
    private final MapIdleWaiterImpl f8259i;
    private final VectorMapViewImpl f8260j;
    private final VectorMapViewImpl f8261k;
    private final IndoorStateAdapter f8262l;
    private final MyLocationRendererImpl f8263m;
    private final SnapshotterImpl f8264n;
    private ag f8265o;
    private ai f8266p;
    private al f8267q;
    private al f8268r;
    private al f8269s;
    private IndoorTileOverlay f8270t;
    private int f8271u;
    private int f8272v;
    private int f8273w;
    private int f8274x;
    private ba f8275y;
    private ca f8276z;

    /* renamed from: com.google.android.m4b.maps.y.x.c */
    interface VectorMapViewImpl {
        IndoorTileOverlay m11967a(Context context, Resources resources);

        TileOverlay m11968a(Resources resources);

        TileOverlay m11969a(com.google.android.m4b.maps.an.ai aiVar, Resources resources);

        TileOverlay m11970a(TileOverlay tileOverlay, Resources resources);

        TileOverlay m11971b(com.google.android.m4b.maps.an.ai aiVar, Resources resources);
    }

    /* renamed from: com.google.android.m4b.maps.y.x.1 */
    static class VectorMapViewImpl implements VectorMapViewImpl {
        private /* synthetic */ String f8244a;
        private /* synthetic */ IndoorStateAdapter f8245b;

        VectorMapViewImpl(String str, IndoorStateAdapter indoorStateAdapter) {
            this.f8244a = str;
            this.f8245b = indoorStateAdapter;
        }

        public final /* bridge */ /* synthetic */ TileOverlay m11973a(Resources resources) {
            TileOverlay a = TileOverlay.m6721a(resources, com.google.android.m4b.maps.an.ai.f3428f);
            VectorMapViewImpl.m11987a(a, this.f8244a);
            return a;
        }

        public final TileOverlay m11974a(com.google.android.m4b.maps.an.ai aiVar, Resources resources) {
            return TileOverlay.m6724b(aiVar, resources);
        }

        public final TileOverlay m11976b(com.google.android.m4b.maps.an.ai aiVar, Resources resources) {
            TileOverlay a = TileOverlay.m6720a(aiVar, resources);
            VectorMapViewImpl.m11987a(a, this.f8244a);
            return a;
        }

        public final IndoorTileOverlay m11972a(Context context, Resources resources) {
            return (IndoorTileOverlay) TileOverlay.m6718a(context, resources, this.f8245b.m11730f());
        }

        public final TileOverlay m11975a(TileOverlay tileOverlay, Resources resources) {
            TileOverlay a = TileOverlay.m6719a(tileOverlay.m6751o(), com.google.android.m4b.maps.an.ai.f3423a, resources);
            VectorMapViewImpl.m11987a(a, this.f8244a);
            return a;
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.x.2 */
    class VectorMapViewImpl implements ViewPointRecorder.ViewPointRecorder {
        VectorMapViewImpl(VectorMapViewImpl vectorMapViewImpl) {
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.x.3 */
    class VectorMapViewImpl implements Gmm6VectorMapView {
        private /* synthetic */ VectorMapViewImpl f8246a;

        VectorMapViewImpl(VectorMapViewImpl vectorMapViewImpl) {
            this.f8246a = vectorMapViewImpl;
        }

        public final void m11977a(Point point) {
            if (this.f8246a.f8266p != null) {
                try {
                    this.f8246a.f8266p.m11102a(ConversionUtils.m11635a(point));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        }

        public final void m11978b(Point point) {
            if (this.f8246a.f8265o != null) {
                try {
                    this.f8246a.f8265o.m11099a(ConversionUtils.m11635a(point));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.x.4 */
    class VectorMapViewImpl implements Runnable {
        private /* synthetic */ VectorMapViewImpl f8247a;

        VectorMapViewImpl(VectorMapViewImpl vectorMapViewImpl) {
            this.f8247a = vectorMapViewImpl;
        }

        public final void run() {
            super.m6989a(true, false);
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.x.5 */
    class VectorMapViewImpl implements Gmm6VectorMapView {
        private /* synthetic */ VectorMapView f8248a;

        VectorMapViewImpl(VectorMapViewImpl vectorMapViewImpl, VectorMapView vectorMapView) {
            this.f8248a = vectorMapView;
        }

        public final boolean m11979a(Point point) {
            return this.f8248a.m11780f();
        }

        public final boolean m11980b(Point point) {
            VectorMapView vectorMapView = this.f8248a;
            return false;
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.x.a */
    interface VectorMapViewImpl {
        VectorMapViewImpl() {
        }

        void m11981a() {
            TaskRunner a = TaskRunnerManager.m11489a();
            DeferredTasksHelper.m11522a();
            DeferredTasksHelper.m11523a(DeferredTasksHelper.DeferredTasksHelper.ON_RESUME, a);
        }

        void m11982b() {
            DeferredTasksHelper.m11525b();
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.x.b */
    static final class VectorMapViewImpl implements ViewPointRecorder.ViewPointRecorder {
        private IndoorStateAdapter f8249a;

        private VectorMapViewImpl(IndoorStateAdapter indoorStateAdapter) {
            this.f8249a = indoorStateAdapter;
        }

        public final void m11983a(int i) {
            if (this.f8249a != null && this.f8249a.m11728d()) {
                UserEventReporter.m11502a(104, "v", "|z=" + i + '|');
            }
        }
    }

    static {
        f8250A = !VectorMapViewImpl.class.desiredAssertionStatus();
    }

    static /* synthetic */ void m11987a(TileOverlay tileOverlay, String str) {
        if (!Strings.m1866b(str)) {
            tileOverlay.m6742a(new AlternatePaintfeParameters().m5688a(str).m5689a());
        }
    }

    public final /* synthetic */ ProjectionDelegate m12016r() {
        return new ProjectionImpl(m7005t(), this.f8271u, this.f8272v, this.f8273w, this.f8274x);
    }

    public static VectorMapView m11986a(Context context, Resources resources, bt btVar, ScheduledExecutorService scheduledExecutorService, View view, bm bmVar, be beVar, String str, boolean z, TextView textView, ba baVar) {
        Gmm6RendererInitializer.m11679a(context, btVar);
        DataRequestDispatcher a = btVar.m9044a();
        VectorMapController vectorMapController = new VectorMapController(resources);
        ViewPointRecorder viewPointRecorder = new ViewPointRecorder(a);
        IndoorStateAdapter a2 = IndoorStateAdapter.m11712a(IndoorState.m5334a(), new Handler(Looper.getMainLooper()), beVar);
        VectorMapViewImpl vectorMapViewImpl = new VectorMapViewImpl(str, a2);
        if (!Strings.m1866b(str)) {
            VectorGlobalState.m7281a(com.google.android.m4b.maps.an.ai.f3432j, context, resources, a);
            VectorGlobalState.m7281a(com.google.android.m4b.maps.an.ai.f3435m, context, resources, a);
        }
        return new VectorMapViewImpl(context, resources, vectorMapController, viewPointRecorder, vectorMapViewImpl, a2, new VectorMapViewImpl(), scheduledExecutorService, view, bmVar, str, z, textView, baVar);
    }

    private VectorMapViewImpl(Context context, Resources resources, VectorMapController vectorMapController, ViewPointRecorder viewPointRecorder, VectorMapViewImpl vectorMapViewImpl, IndoorStateAdapter indoorStateAdapter, VectorMapViewImpl vectorMapViewImpl2, ScheduledExecutorService scheduledExecutorService, View view, bm bmVar, String str, boolean z, TextView textView, ba baVar) {
        super(context, resources, textView, bmVar.m8915e());
        this.f8275y = baVar;
        this.f8251a = vectorMapController;
        this.f8251a.m7319a(67.5f);
        this.f8252b = new VectorMapControllerAdapter(this.f8251a, this);
        m6986a(this.f8251a);
        m6966i(true);
        m6967j(!z);
        this.f8256f = viewPointRecorder;
        this.f8256f.m7349a(new VectorMapViewImpl(this));
        this.f8251a.m7325a(this.f8256f);
        this.f8254d = new OverlayRendererManagerImpl(this, scheduledExecutorService);
        this.f8255e = GLMarkerManager.m11646a(this, this.f8252b, this.f8275y);
        this.f8276z = new ca(this, this.f8255e);
        ViewCompat.setAccessibilityDelegate(this, this.f8276z);
        Handler handler = new Handler(Looper.getMainLooper());
        this.f8253c = new CameraManagerImpl(this, this.f8252b, handler);
        this.f8263m = new MyLocationRendererImpl(this);
        this.f8259i = MapIdleWaiterImpl.m11731a(this, handler);
        this.f8262l = indoorStateAdapter;
        this.f8260j = vectorMapViewImpl;
        if (!Strings.m1866b(str)) {
            m6985a(this.f8260j.m11971b(com.google.android.m4b.maps.an.ai.f3423a, getResources()));
        }
        this.f8261k = vectorMapViewImpl2;
        this.f8264n = new SnapshotterImpl(this, view, bmVar.m8910a(), false, MapViewUtils.m9533a());
        this.f8258h = new VectorMapViewImpl(this);
        this.f8257g = new cc(new VectorMapViewImpl(this));
        m6981a(this.f8260j.m11971b(com.google.android.m4b.maps.an.ai.f3437o, getResources()));
        GmmSettings.m11527a();
        m6988a(GmmSettings.m11528a(resources) ? LabelTheme.f5411u : LabelTheme.f5410t);
        m6981a(this.f8255e.m11661c());
        m6981a(this.f8254d);
    }

    private void m11984G() {
        if (this.f8265o == null && this.f8266p == null) {
            m6983a(null);
        } else {
            m6983a(this.f8258h);
        }
    }

    public final void m11990a() {
        this.f8261k.m11981a();
        super.m6977a();
    }

    public final void m11998b() {
        this.f8261k.m11982b();
        super.m6991b();
    }

    public final void m12000c() {
        m11999b(false);
        m6967j(false);
        super.m6994c();
    }

    public final void m12002d() {
        this.f8259i.m11733a();
        this.f8257g.m9353a();
    }

    public final void m11993a(ag agVar) {
        this.f8265o = agVar;
        m11984G();
    }

    public final void m11994a(ai aiVar) {
        this.f8266p = aiVar;
        m11984G();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public final void m11995a(VectorMapView vectorMapView) {
        if (vectorMapView == null) {
            m6982a(null);
        } else {
            m6982a(new VectorMapViewImpl(this, vectorMapView));
        }
    }

    public boolean canScrollHorizontally(int i) {
        return m7011z();
    }

    public boolean canScrollVertically(int i) {
        return m7011z();
    }

    public final com.google.android.m4b.maps.av.ai m12003e() {
        return m6973D().m7257e();
    }

    public final View m12004f() {
        return this;
    }

    public final bp m12005g() {
        return this.f8253c;
    }

    public final MarkerManagerImpl m12006h() {
        return this.f8255e;
    }

    public final com.google.android.m4b.maps.be.ai m12007i() {
        return this.f8254d;
    }

    public final MyLocationLayerImpl m12008j() {
        return this.f8263m;
    }

    public final IndoorStateInterface m12009k() {
        return this.f8262l;
    }

    public final com.google.android.m4b.maps.be.aq m12010l() {
        return this.f8264n;
    }

    public final MapIdleWaiter m12011m() {
        return this.f8259i;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f8271u = i;
        this.f8272v = i2;
        this.f8273w = i3;
        this.f8274x = i4;
        m12002d();
    }

    public final boolean m12012n() {
        return this.f8267q != null;
    }

    public final void m11996a(boolean z) {
        if (z) {
            if (this.f8267q == null) {
                this.f8267q = this.f8260j.m11968a(getResources());
                m6981a(this.f8267q);
            }
        } else if (this.f8267q != null) {
            m6993b(this.f8267q);
            this.f8267q = null;
        }
        if (!f8250A) {
            boolean z2;
            if (this.f8267q != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z != z2) {
                throw new AssertionError();
            }
        }
    }

    public final boolean m12013o() {
        return this.f8262l != null && this.f8262l.m11729e();
    }

    public final boolean m11999b(boolean z) {
        if (!(this.f8262l == null || m12013o() == z)) {
            if (!z) {
                this.f8256f.m7348a(null);
                m6993b(this.f8270t);
                this.f8270t.g_();
                this.f8270t = null;
                this.f8262l.m11723b();
            } else if (this.f8262l.m11721a()) {
                this.f8270t = this.f8260j.m11967a(getContext(), getResources());
                m6981a(this.f8270t);
                this.f8256f.m7348a(new VectorMapViewImpl((byte) 0));
            }
        }
        return m12013o();
    }

    public final boolean m12014p() {
        return this.f8268r != null;
    }

    public final void m12001c(boolean z) {
        if (z) {
            if (this.f8268r == null) {
                this.f8268r = this.f8260j.m11970a(m6974E(), getResources());
                m6981a(this.f8268r);
            }
        } else if (this.f8268r != null) {
            m6993b(this.f8268r);
            this.f8268r = null;
        }
        if (!f8250A) {
            boolean z2;
            if (this.f8268r != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z != z2) {
                throw new AssertionError();
            }
        }
    }

    public final void m11992a(int i) {
        ac acVar;
        com.google.android.m4b.maps.an.ai aiVar;
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                acVar = ac.RASTER_ONLY;
                aiVar = null;
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                aiVar = com.google.android.m4b.maps.an.ai.f3426d;
                acVar = ac.RASTER_ONLY;
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                aiVar = com.google.android.m4b.maps.an.ai.f3427e;
                acVar = ac.TERRAIN;
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                aiVar = com.google.android.m4b.maps.an.ai.f3426d;
                acVar = ac.HYBRID;
                break;
            default:
                aiVar = com.google.android.m4b.maps.an.ai.f3423a;
                acVar = ac.NORMAL;
                break;
        }
        if (this.f8269s != null) {
            m6993b(this.f8269s);
            this.f8269s = null;
        }
        if (!(aiVar == null || aiVar == com.google.android.m4b.maps.an.ai.f3423a)) {
            this.f8269s = this.f8260j.m11969a(aiVar, getResources());
            m6981a(this.f8269s);
        }
        m6980a(acVar);
    }

    public final void m11997a(boolean z, boolean z2) {
        this.f8276z.m9291a();
        super.m6989a(z, z2);
    }

    public final void m11991a(float f, float f2) {
        this.f8276z.m9291a();
        super.m6978a(f, f2);
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.f8276z.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    public final String m12015q() {
        return "G";
    }
}
