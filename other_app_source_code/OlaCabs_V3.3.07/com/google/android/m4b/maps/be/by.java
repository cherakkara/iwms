package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.m4b.maps.GoogleMapOptions;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.aa.ApiParameters;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.be.bi.ZoomButtons;
import com.google.android.m4b.maps.be.bp.CameraManager;
import com.google.android.m4b.maps.be.bu.CsiReporter;
import com.google.android.m4b.maps.bf.MapRendererViewLite;
import com.google.android.m4b.maps.cb.BuildUtils;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.model.BitmapDescriptor;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.CircleOptions;
import com.google.android.m4b.maps.model.GroundOverlayOptions;
import com.google.android.m4b.maps.model.MapsEngineLayerOptions;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.google.android.m4b.maps.model.PolygonOptions;
import com.google.android.m4b.maps.model.PolylineOptions;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.model.TileOverlayOptions;
import com.google.android.m4b.maps.model.internal.BitmapDescriptorParcelable;
import com.google.android.m4b.maps.model.internal.CameraUpdateParcelable;
import com.google.android.m4b.maps.model.internal.GroundOverlayOptionsParcelable;
import com.google.android.m4b.maps.model.internal.ICircleDelegate;
import com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate;
import com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate;
import com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.google.android.m4b.maps.model.internal.IPolygonDelegate;
import com.google.android.m4b.maps.model.internal.IPolylineDelegate;
import com.google.android.m4b.maps.model.internal.ITileOverlayDelegate;
import com.google.android.m4b.maps.model.internal.MarkerOptionsParcelable;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p042r.ICancelableCallback;
import com.google.android.m4b.maps.p042r.IGoogleMapDelegate.IGoogleMapDelegate;
import com.google.android.m4b.maps.p042r.IInfoWindowAdapter;
import com.google.android.m4b.maps.p042r.IInfoWindowRenderer;
import com.google.android.m4b.maps.p042r.ILocationSourceDelegate;
import com.google.android.m4b.maps.p042r.IProjectionDelegate;
import com.google.android.m4b.maps.p042r.ISnapshotReadyCallback;
import com.google.android.m4b.maps.p042r.IUiSettingsDelegate;
import com.google.android.m4b.maps.p042r.MapStateHelper;
import com.google.android.m4b.maps.p042r.ab;
import com.google.android.m4b.maps.p042r.ac;
import com.google.android.m4b.maps.p042r.ac.IOnCameraChangeListener;
import com.google.android.m4b.maps.p042r.ad;
import com.google.android.m4b.maps.p042r.ae;
import com.google.android.m4b.maps.p042r.ag;
import com.google.android.m4b.maps.p042r.ah;
import com.google.android.m4b.maps.p042r.ah.IOnMapLoadedCallback;
import com.google.android.m4b.maps.p042r.ai;
import com.google.android.m4b.maps.p042r.aj;
import com.google.android.m4b.maps.p042r.ak;
import com.google.android.m4b.maps.p042r.al;
import com.google.android.m4b.maps.p042r.am;
import com.google.android.m4b.maps.p042r.ao;
import com.google.android.m4b.maps.p042r.ap;
import com.google.android.m4b.maps.p047g.BuildConstants;
import com.google.android.m4b.maps.p061y.TextureVectorMapViewImpl;
import com.google.android.m4b.maps.p061y.VectorMapViewImpl;
import com.google.p025a.p026a.Preconditions;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.br;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.http.HttpStatus;

/* compiled from: GoogleMapImpl */
public final class by extends IGoogleMapDelegate implements cd, ab, IUiSettingsDelegate {
    private static final boolean f5930a;
    private final ZoomButtons f5931A;
    private GoogleMapImpl f5932B;
    private boolean f5933C;
    private boolean f5934D;
    private boolean f5935E;
    private ab f5936F;
    private InfoWindowRenderer f5937G;
    private final bp f5938b;
    private final MapRenderer f5939c;
    private final bl f5940d;
    private final ae f5941e;
    private final ah f5942f;
    private final ai f5943g;
    private final LocationSourceComponent f5944h;
    private final ag f5945i;
    private final bh f5946j;
    private final bm f5947k;
    private final MapIdleWaiter f5948l;
    private final ba f5949m;
    private final View f5950n;
    private final be f5951o;
    private final GoogleMapOptions f5952p;
    private final bu f5953q;
    private final IndoorStateInterface f5954r;
    private final ScheduledExecutorService f5955s;
    private final aq f5956t;
    private final bt f5957u;
    private final bq f5958v;
    private final bj f5959w;
    private final Handler f5960x;
    private int f5961y;
    private IUiSettingsDelegate f5962z;

    /* renamed from: com.google.android.m4b.maps.be.by.1 */
    class GoogleMapImpl implements ZoomButtons {
        private /* synthetic */ by f5918a;

        GoogleMapImpl(by byVar) {
            this.f5918a = byVar;
        }

        public final void m9070a() {
            this.f5918a.f5951o.m8835b(UsageLog.ZOOM_IN_BUTTON_CLICK);
            this.f5918a.f5938b.m8943b(br.DEFAULT_BACKOFF_MULT, -1);
        }

        public final void m9071b() {
            this.f5918a.f5951o.m8835b(UsageLog.ZOOM_OUT_BUTTON_CLICK);
            this.f5918a.f5938b.m8943b(-1.0f, -1);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.by.2 */
    static class GoogleMapImpl extends IOnMapLoadedCallback {
        private /* synthetic */ bu f5919a;
        private /* synthetic */ CsiReporter f5920b;

        GoogleMapImpl(bu buVar, CsiReporter csiReporter) {
            this.f5919a = buVar;
            this.f5920b = csiReporter;
        }

        public final void m9073a() {
            this.f5919a.m9053a(this.f5920b);
            this.f5919a.m9054b();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.by.3 */
    class GoogleMapImpl extends IUiSettingsDelegate.IUiSettingsDelegate {
        private /* synthetic */ by f5921a;

        GoogleMapImpl(by byVar) {
            this.f5921a = byVar;
        }

        public final void m9096f(boolean z) {
            this.f5921a.m9259f(z);
        }

        public final void m9098h(boolean z) {
            this.f5921a.m9263h(z);
        }

        public final void m9099i(boolean z) {
            this.f5921a.m9265i(z);
        }

        public final void m9095c(boolean z) {
            this.f5921a.m9253c(z);
        }

        public final void m9100j(boolean z) {
            this.f5921a.m9266j(z);
        }

        public final void m9101k(boolean z) {
            this.f5921a.m9268k(z);
        }

        public final void m9102l(boolean z) {
            this.f5921a.m9270l(z);
        }

        public final void m9104m(boolean z) {
            this.f5921a.m9272m(z);
        }

        public final void m9105n(boolean z) {
            this.f5921a.m9274n(z);
        }

        public final void m9097g(boolean z) {
            this.f5921a.m9261g(z);
        }

        public final boolean m9107u() {
            return this.f5921a.m9282u();
        }

        public final boolean m9108v() {
            return this.f5921a.m9283v();
        }

        public final boolean m9109w() {
            return this.f5921a.m9284w();
        }

        public final boolean m9103l() {
            return this.f5921a.m9271l();
        }

        public final boolean m9110x() {
            return this.f5921a.m9285x();
        }

        public final boolean m9111y() {
            return this.f5921a.m9282u();
        }

        public final boolean m9112z() {
            return this.f5921a.m9287z();
        }

        public final boolean m9094A() {
            return this.f5921a.m9204A();
        }

        public final boolean m9106t() {
            return this.f5921a.m9281t();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.by.4 */
    class GoogleMapImpl implements OnClickListener {
        private /* synthetic */ by f5922a;

        GoogleMapImpl(by byVar) {
            this.f5922a = byVar;
        }

        public final void onClick(View view) {
            this.f5922a.f5951o.m8835b(UsageLog.COMPASS_BUTTON_CLICK);
            CameraPosition c = this.f5922a.f5938b.m8945c();
            this.f5922a.f5938b.m8937a(new CameraPosition(c.f7529a, c.f7530b, 0.0f, 0.0f), (int) HttpStatus.SC_BAD_REQUEST);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.by.5 */
    class GoogleMapImpl implements Runnable {
        private /* synthetic */ Bitmap f5923a;
        private /* synthetic */ ISnapshotReadyCallback f5924b;
        private /* synthetic */ by f5925c;

        GoogleMapImpl(by byVar, Bitmap bitmap, ISnapshotReadyCallback iSnapshotReadyCallback) {
            this.f5925c = byVar;
            this.f5923a = bitmap;
            this.f5924b = iSnapshotReadyCallback;
        }

        public final void run() {
            this.f5925c.f5956t.m8256b(this.f5923a, this.f5924b, false);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.by.6 */
    class GoogleMapImpl implements Runnable {
        private /* synthetic */ aj f5926a;
        private /* synthetic */ by f5927b;

        GoogleMapImpl(by byVar, aj ajVar) {
            this.f5927b = byVar;
            this.f5926a = ajVar;
        }

        public final void run() {
            try {
                this.f5926a.m10516a(this.f5927b);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.by.a */
    static final class GoogleMapImpl extends IOnCameraChangeListener {
        private final bi f5928a;
        private final bp f5929b;

        GoogleMapImpl(bp bpVar, bi biVar) {
            this.f5928a = biVar;
            this.f5929b = bpVar;
        }

        public final void m9113a(CameraPosition cameraPosition) {
            boolean z;
            boolean z2 = true;
            bi biVar = this.f5928a;
            if (cameraPosition.f7530b < this.f5929b.m8929a(cameraPosition.f7529a)) {
                z = true;
            } else {
                z = false;
            }
            biVar.m8866b(z);
            bi biVar2 = this.f5928a;
            if (cameraPosition.f7530b <= this.f5929b.m8948d()) {
                z2 = false;
            }
            biVar2.m8867c(z2);
        }
    }

    public final /* synthetic */ IIndoorBuildingDelegate m9207D() {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.INDOOR_GET_FOCUSED_BULIDING);
        cf c = this.f5954r.m9375c();
        return c != null ? new ce(this.f5954r, c, this.f5951o) : null;
    }

    static {
        f5930a = !BuildUtils.m10086b(BuildConstants.f7374a);
    }

    public static by m9188a(LayoutInflater layoutInflater, GoogleMapOptions googleMapOptions, boolean z) {
        return m9189a(layoutInflater, googleMapOptions, z, Trace.NULL, bb.m8775b(), false);
    }

    public static by m9189a(LayoutInflater layoutInflater, GoogleMapOptions googleMapOptions, boolean z, String str, ba baVar, boolean z2) {
        View view;
        Preconditions.m1817a((Object) googleMapOptions);
        ApiParameters d = ServerControlledParametersManager.m4810d();
        boolean z3 = d != null && d.m4742b();
        bu bvVar = new bv(new Clock(), "map_start_up", z3);
        bvVar.m9052a();
        CsiReporter a = bvVar.m9051a("init");
        CsiReporter a2 = bvVar.m9051a("map_load");
        Context context = layoutInflater.getContext();
        Context applicationContext = context.getApplicationContext();
        Resources a3 = InternalResourceLoader.m9392a();
        Handler handler = new Handler(Looper.getMainLooper());
        bq bqVar = new bq();
        bj bjVar = new bj();
        bt a4 = bt.m9042a(applicationContext);
        a4.m9044a();
        applicationContext = Initializer.m9389a(applicationContext, a4, z2);
        be b = bf.m8843b();
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);
        bh bhVar = new bh(applicationContext, a3);
        bx bxVar = new bx(applicationContext, b);
        bm bmVar = new bm(applicationContext, a3, MapToolbar.m9505a(applicationContext, a3, bxVar, m9192b(googleMapOptions)));
        bl a5 = bl.m8905a(applicationContext);
        View a6 = bhVar.m8855a();
        boolean a7 = m9190a(googleMapOptions);
        boolean b2 = m9192b(googleMapOptions);
        TextView b3 = bhVar.m8858b();
        MapRenderer a8 = b2 ? MapRendererViewLite.m9618a(applicationContext, a3, a4, newScheduledThreadPool, a6, bmVar, str, a7, bxVar, b3) : az.m8760a(z) ? TextureVectorMapViewImpl.m11875a(applicationContext, a3, a4, newScheduledThreadPool, a6, bmVar, b, str, a7, b3, baVar) : VectorMapViewImpl.m11986a(applicationContext, a3, a4, newScheduledThreadPool, a6, bmVar, b, str, a7, b3, baVar);
        b.m8834a(a8.m9501q());
        View f = a8.m9487f();
        if ((f instanceof SurfaceView) && googleMapOptions.m4652l() != null) {
            ((SurfaceView) f).setZOrderOnTop(googleMapOptions.m4652l().booleanValue());
        }
        f.setContentDescription(applicationContext.getResources().getString(R.GOOGLE_MAP));
        bp g = a8.m9489g();
        MarkerManagerImpl h = a8.m9491h();
        IInfoWindowRenderer a9 = InfoWindowRenderer.m9385a(baVar, applicationContext, a3);
        ae aeVar = new ae(h, a9, a5, bjVar, baVar, b, bmVar.m8915e(), a8, m9192b(googleMapOptions));
        ai i = a8.m9493i();
        ah ahVar = new ah();
        MapsEngineInfocardManager mapsEngineInfocardManager = new MapsEngineInfocardManager(context, a3, i, ahVar, a5, bjVar, b, baVar);
        i.m8440a(mapsEngineInfocardManager);
        ILocationSourceDelegate a10 = LocationSourceImpl.m9434a(applicationContext);
        ag agVar = new ag(applicationContext, a3, g, bmVar.m8913c(), a8.m9494j(), a10, b, z2);
        IndoorStateInterface k = a8.m9495k();
        aq l = a8.m9496l();
        MapIdleWaiter m = a8.m9497m();
        m.m9473b(new GoogleMapImpl(bvVar, a2));
        View frameLayout = new FrameLayout(applicationContext);
        frameLayout.addView(f);
        frameLayout.addView(bhVar.m8855a());
        frameLayout.addView(bmVar.m8910a());
        frameLayout.addView(mapsEngineInfocardManager.m9568d());
        if (z2) {
            View bzVar = new bz(applicationContext, frameLayout);
        } else {
            view = frameLayout;
        }
        by byVar = new by(view, a8, a5, aeVar, a10, agVar, i, ahVar, g, bhVar, bmVar, mapsEngineInfocardManager, m, baVar, b, googleMapOptions, bvVar, k, l, newScheduledThreadPool, a4, bxVar, bqVar, bjVar, a9, handler);
        ai aiVar = byVar.f5943g;
        if (byVar.f5952p.m4657q() != null) {
            byVar.m9263h(byVar.f5952p.m4657q().booleanValue());
        } else {
            byVar.m9198s(f5930a);
        }
        byVar.m9195p(true);
        byVar.m9194o(true);
        if (byVar.f5952p.m4656p() != null) {
            byVar.m9259f(byVar.f5952p.m4656p().booleanValue());
        } else {
            boolean z4 = f5930a && bg.m8854c();
            byVar.m9196q(z4);
        }
        if (byVar.f5952p.m4654n() != -1) {
            byVar.m9218a(byVar.f5952p.m4654n());
        }
        if (byVar.f5952p.m4659s() != null) {
            byVar.m9268k(byVar.f5952p.m4659s().booleanValue());
        } else {
            byVar.m9201v(true);
        }
        if (byVar.f5952p.m4658r() != null) {
            byVar.m9266j(byVar.f5952p.m4658r().booleanValue());
        } else {
            byVar.m9200u(true);
        }
        if (byVar.f5952p.m4660t() != null) {
            byVar.m9270l(byVar.f5952p.m4660t().booleanValue());
        } else {
            byVar.m9202w(true);
        }
        if (byVar.f5952p.m4661u() != null) {
            byVar.m9272m(byVar.f5952p.m4661u().booleanValue());
        } else {
            byVar.m9203x(true);
        }
        if (byVar.f5952p.m4663w() != null) {
            byVar.m9261g(byVar.f5952p.m4663w().booleanValue());
        } else if (m9192b(byVar.f5952p)) {
            byVar.m9197r(true);
        } else {
            byVar.m9197r(bg.m8853b());
        }
        byVar.m9199t(f5930a);
        b.m8833a(UsageLog.MAP_CREATED);
        an.m8572a(applicationContext).m8577a(1);
        bvVar.m9053a(a);
        return byVar;
    }

    private by(View view, MapRenderer mapRenderer, bl blVar, ae aeVar, LocationSourceComponent locationSourceComponent, ag agVar, ai aiVar, ah ahVar, bp bpVar, bh bhVar, bm bmVar, MapsEngineInfocardManager mapsEngineInfocardManager, MapIdleWaiter mapIdleWaiter, ba baVar, be beVar, GoogleMapOptions googleMapOptions, bu buVar, IndoorStateInterface indoorStateInterface, aq aqVar, ScheduledExecutorService scheduledExecutorService, bt btVar, bx bxVar, bq bqVar, bj bjVar, InfoWindowRenderer infoWindowRenderer, Handler handler) {
        this.f5961y = 1;
        this.f5931A = new GoogleMapImpl(this);
        this.f5935E = true;
        this.f5950n = view;
        this.f5939c = mapRenderer;
        this.f5940d = blVar;
        this.f5941e = aeVar;
        this.f5944h = locationSourceComponent;
        this.f5945i = agVar;
        this.f5943g = aiVar;
        this.f5942f = ahVar;
        this.f5938b = bpVar;
        this.f5946j = bhVar;
        this.f5947k = bmVar;
        this.f5948l = mapIdleWaiter;
        this.f5949m = baVar;
        this.f5951o = beVar;
        this.f5952p = googleMapOptions;
        this.f5953q = buVar;
        this.f5954r = indoorStateInterface;
        this.f5956t = aqVar;
        this.f5955s = scheduledExecutorService;
        this.f5957u = btVar;
        this.f5958v = bqVar;
        this.f5959w = bjVar;
        this.f5937G = infoWindowRenderer;
        this.f5960x = handler;
    }

    public final void m9220a(Bundle bundle) {
        CsiReporter a = this.f5953q.m9051a("on_create");
        CameraPosition cameraPosition = (CameraPosition) MapStateHelper.m11180a(bundle, "camera");
        if (cameraPosition == null) {
            cameraPosition = this.f5952p.m4655o() != null ? this.f5952p.m4655o() : bp.f5847a;
        }
        this.f5938b.m8937a(cameraPosition, 0);
        this.f5957u.m9048c();
        this.f5953q.m9053a(a);
    }

    public final void m9217a() {
        this.f5951o.m8832a();
        this.f5939c.m9484c();
        this.f5957u.m9049d();
    }

    public final void m9246b() {
        CsiReporter a = this.f5953q.m9051a("on_resume");
        this.f5939c.m9477a();
        this.f5944h.m9428a();
        this.f5953q.m9053a(a);
    }

    public final void m9252c() {
        this.f5944h.m9429b();
        this.f5939c.m9482b();
    }

    public final void m9254d() {
        this.f5939c.m9503y();
    }

    public final void m9247b(Bundle bundle) {
        MapStateHelper.m11181a(bundle, "MapOptions", this.f5952p);
        MapStateHelper.m11181a(bundle, "camera", this.f5938b.m8945c());
    }

    public final CameraPosition m9256e() {
        this.f5949m.m8774a();
        return this.f5938b.m8945c();
    }

    public final float m9258f() {
        this.f5949m.m8774a();
        return this.f5938b.m8929a(this.f5938b.m8945c().f7529a);
    }

    public final float m9260g() {
        this.f5949m.m8774a();
        return this.f5938b.m8948d();
    }

    public final void m9221a(IObjectWrapper iObjectWrapper) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_MOVE_CAMERA);
        this.f5938b.m8936a((CameraManager) ObjectWrapper.m10131a(iObjectWrapper), 0, null, this.f5951o);
    }

    public final void m9224a(CameraUpdateParcelable cameraUpdateParcelable) {
        this.f5949m.m8774a();
        m9221a(this.f5958v.m8981a(cameraUpdateParcelable));
    }

    public final void m9248b(IObjectWrapper iObjectWrapper) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ANIMATE_CAMERA);
        this.f5938b.m8936a((CameraManager) ObjectWrapper.m10131a(iObjectWrapper), -1, null, this.f5951o);
    }

    public final void m9249b(CameraUpdateParcelable cameraUpdateParcelable) {
        this.f5949m.m8774a();
        m9248b(this.f5958v.m8981a(cameraUpdateParcelable));
    }

    public final void m9223a(IObjectWrapper iObjectWrapper, ICancelableCallback iCancelableCallback) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ANIMATE_CAMERA_WITH_CALLBACK);
        this.f5938b.m8936a((CameraManager) ObjectWrapper.m10131a(iObjectWrapper), -1, iCancelableCallback, this.f5951o);
    }

    public final void m9226a(CameraUpdateParcelable cameraUpdateParcelable, ICancelableCallback iCancelableCallback) {
        this.f5949m.m8774a();
        m9223a(this.f5958v.m8981a(cameraUpdateParcelable), iCancelableCallback);
    }

    public final void m9222a(IObjectWrapper iObjectWrapper, int i, ICancelableCallback iCancelableCallback) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ANIMATE_CAMERA_WITH_CALLBACK_AND_CUSTOM_DURATION);
        CameraManager cameraManager = (CameraManager) ObjectWrapper.m10131a(iObjectWrapper);
        Preconditions.m1823a(i > 0, (Object) "durationMs must be positive");
        this.f5938b.m8936a(cameraManager, i, iCancelableCallback, this.f5951o);
    }

    public final void m9225a(CameraUpdateParcelable cameraUpdateParcelable, int i, ICancelableCallback iCancelableCallback) {
        this.f5949m.m8774a();
        m9222a(this.f5958v.m8981a(cameraUpdateParcelable), i, iCancelableCallback);
    }

    public final void m9262h() {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_STOP_ANIMATION);
        this.f5938b.m8931a();
    }

    public final void m9228a(ac acVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_CAMERA_CHANGE_LISTENER);
        this.f5938b.m8942a(acVar);
    }

    public final void m9231a(ag agVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MAP_CLICK_LISTENER);
        this.f5939c.m9479a(agVar);
    }

    public final void m9233a(ai aiVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MAP_LONG_CLICK_LISTENER);
        this.f5939c.m9480a(aiVar);
    }

    public final IPolylineDelegate m9215a(PolylineOptions polylineOptions) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ADD_POLYLINE);
        Object alVar = new al(polylineOptions, this.f5942f, this.f5951o, this.f5949m);
        alVar.m8528a(this.f5943g.m8437a(alVar, false));
        this.f5942f.m8434a(alVar);
        return alVar;
    }

    public final IPolygonDelegate m9214a(PolygonOptions polygonOptions) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ADD_POLYGON);
        Object akVar = new ak(polygonOptions, this.f5942f, this.f5951o, this.f5949m);
        akVar.m8480a(this.f5943g.m8437a(akVar, true));
        this.f5942f.m8434a(akVar);
        return akVar;
    }

    public final ICircleDelegate m9208a(CircleOptions circleOptions) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ADD_CIRCLE);
        Object brVar = new br(circleOptions, this.f5942f, this.f5951o, this.f5949m);
        brVar.m9008a(this.f5943g.m8437a(brVar, true));
        this.f5942f.m8434a(brVar);
        return brVar;
    }

    public final IMarkerDelegate m9212a(MarkerOptions markerOptions) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ADD_MARKER);
        return this.f5941e.m8393a(markerOptions);
    }

    public final IMarkerDelegate m9213a(MarkerOptions markerOptions, MarkerOptionsParcelable markerOptionsParcelable) {
        this.f5949m.m8774a();
        BitmapDescriptorParcelable b = markerOptionsParcelable.m10852b();
        if (b != null) {
            markerOptions.m10760a(new BitmapDescriptor(this.f5959w.m8879a(b)));
        }
        return m9212a(markerOptions);
    }

    public final void m9236a(al alVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MARKER_CLICK_LISTENER);
        this.f5941e.m8397a(alVar);
    }

    public final void m9237a(am amVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MARKER_DRAG_LISTENER);
        this.f5941e.m8398a(amVar);
    }

    public final void m9230a(ae aeVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_BUBBLE_CLICK_LISTENER);
        this.f5941e.m8396a(aeVar);
    }

    public final void m9232a(ah ahVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MAP_IDLE_LISTENER);
        this.f5948l.m9472a(ahVar);
    }

    public final void m9241a(IInfoWindowAdapter iInfoWindowAdapter) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MARKER_SET_INFO_CONTENTS_ADAPTER);
        this.f5937G.m9387a(iInfoWindowAdapter);
    }

    public final void m9242a(IInfoWindowRenderer iInfoWindowRenderer) {
        this.f5949m.m8774a();
        this.f5941e.m8399a(iInfoWindowRenderer);
    }

    public final IGroundOverlayDelegate m9209a(GroundOverlayOptions groundOverlayOptions) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ADD_GROUND_OVERLAY);
        cb cbVar = new cb(groundOverlayOptions, this.f5942f, this.f5940d, this.f5959w, this.f5951o, this.f5949m);
        cbVar.m9323a(this.f5943g.m8439a(cbVar));
        this.f5942f.m8434a(cbVar);
        return cbVar;
    }

    public final IGroundOverlayDelegate m9210a(GroundOverlayOptions groundOverlayOptions, GroundOverlayOptionsParcelable groundOverlayOptionsParcelable) {
        this.f5949m.m8774a();
        BitmapDescriptorParcelable b = groundOverlayOptionsParcelable.m10850b();
        if (b != null) {
            groundOverlayOptions.m10724a(new BitmapDescriptor(this.f5959w.m8879a(b)));
        }
        return m9209a(groundOverlayOptions);
    }

    public final ITileOverlayDelegate m9216a(TileOverlayOptions tileOverlayOptions) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ADD_TILE_OVERLAY);
        bd bdVar = new bd(tileOverlayOptions, this.f5942f, this.f5951o, this.f5949m);
        bdVar.m8817a(this.f5943g.m8438a(bdVar));
        this.f5942f.m8434a(bdVar);
        return bdVar;
    }

    public final IMapsEngineLayerDelegate m9211a(MapsEngineLayerOptions mapsEngineLayerOptions) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_ADD_TILE_OVERLAY);
        aa a = aa.m8276a(mapsEngineLayerOptions, this, this.f5955s, DataRequestDispatcher.m11393a(), this.f5942f, this.f5951o, this.f5949m);
        a.m8280a(this.f5943g.m8436a(a));
        this.f5942f.m8434a(a);
        return a;
    }

    public final void m9235a(ak akVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAPS_ENGINE_OVERLAY_CLICK_LISTENER);
        this.f5943g.m8441a(akVar);
    }

    public final void m9227a(ab abVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_OAUTH_TOKEN_PROVIDER);
        this.f5936F = abVar;
    }

    public final String m9264i() {
        if (this.f5936F != null) {
            return this.f5936F.m9186i();
        }
        return null;
    }

    public final void m9244a(String str) {
        if (this.f5936F != null) {
            this.f5936F.m9185a(str);
        }
    }

    public final void m9229a(ad adVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_INDOOR_LISTENER);
        this.f5954r.m9371a(adVar);
    }

    public final void m9219a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i = 0;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_VISIBLE_REGION);
        this.f5938b.m8935a(i, i2, i3, i4);
        this.f5939c.setPadding(i, i2, i3, i4);
        this.f5947k.m8911a(i, i2, i3, i4);
        this.f5946j.m8856a(i, i2, i3, i4);
    }

    public final boolean m9267j() {
        this.f5949m.m8774a();
        return this.f5939c.m9498n();
    }

    public final void m9245a(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_SET_TRAFFIC_ENABLED : UsageLog.MAP_SET_TRAFFIC_DISABLED);
        this.f5939c.m9481a(z);
    }

    public final boolean m9269k() {
        this.f5949m.m8774a();
        return this.f5939c.m9499o();
    }

    public final boolean m9251b(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_INDOOR : UsageLog.MAP_DISABLE_INDOOR);
        return m9194o(z);
    }

    private boolean m9194o(boolean z) {
        boolean b = this.f5939c.m9483b(z);
        if (b) {
            if (this.f5935E) {
                this.f5947k.m8914d().m9404a(0);
            }
            this.f5947k.m8914d().m9405a(this.f5954r);
        } else {
            this.f5947k.m8914d().m9405a(null);
            this.f5947k.m8914d().m9404a(8);
        }
        return b;
    }

    public final void m9253c(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_INDOOR_LEVEL_PICKER : UsageLog.MAP_DISABLE_INDOOR_LEVEL_PICKER);
        if (m9192b(this.f5952p)) {
            z = false;
        }
        if (this.f5939c.m9499o()) {
            if (z) {
                this.f5947k.m8914d().m9404a(0);
            } else {
                this.f5947k.m8914d().m9404a(8);
            }
        }
        this.f5935E = z;
    }

    public final boolean m9271l() {
        this.f5949m.m8774a();
        return this.f5935E;
    }

    public final boolean m9273m() {
        this.f5949m.m8774a();
        return this.f5939c.m9500p();
    }

    public final void m9255d(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_SET_BUILDINGS_ENABLED : UsageLog.MAP_SET_BUILDINGS_DISABLED);
        m9195p(z);
    }

    private void m9195p(boolean z) {
        this.f5939c.m9485c(z);
    }

    public final boolean m9275n() {
        this.f5949m.m8774a();
        return this.f5945i.m8430c();
    }

    public final void m9257e(boolean z) {
        this.f5949m.m8774a();
        if (z) {
            this.f5951o.m8835b(UsageLog.MAP_SET_MY_LOCATION_ENABLED);
            this.f5945i.m8422a();
            return;
        }
        this.f5951o.m8835b(UsageLog.MAP_SET_MY_LOCATION_DISABLED);
        this.f5945i.m8429b();
    }

    @Deprecated
    public final Location m9276o() {
        this.f5949m.m8774a();
        return this.f5945i.m8432e();
    }

    public final void m9243a(ILocationSourceDelegate iLocationSourceDelegate) {
        if (iLocationSourceDelegate != null) {
            this.f5951o.m8835b(UsageLog.MAP_SET_LOCATION_SOURCE);
        } else {
            this.f5951o.m8835b(UsageLog.MAP_CLEAR_LOCATION_SOURCE);
        }
        this.f5949m.m8774a();
        this.f5945i.m8427a(iLocationSourceDelegate);
    }

    @Deprecated
    public final void m9239a(ap apVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MY_LOCATION_CHANGE_LISTENER);
        this.f5945i.m8426a(apVar);
    }

    public final void m9238a(ao aoVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MY_LOCATION_BUTTON_CLICK_LISTENER);
        this.f5945i.m8425a(aoVar);
    }

    public final int m9277p() {
        this.f5949m.m8774a();
        return this.f5961y;
    }

    public final void m9218a(int i) {
        boolean z;
        boolean z2 = true;
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_MAP_TYPE);
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                z = true;
                break;
            default:
                z = false;
                break;
        }
        this.f5939c.m9478a(i);
        bh bhVar = this.f5946j;
        if (i == 0) {
            z2 = false;
        }
        bhVar.m8859b(z2);
        this.f5946j.m8857a(z);
        this.f5961y = i;
    }

    public final void m9278q() {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_CLEAR);
        this.f5941e.m8394a();
        this.f5942f.m8433a();
    }

    public final IProjectionDelegate m9279r() {
        this.f5949m.m8774a();
        return new am(this.f5951o, this.f5939c.m9502r());
    }

    public final IUiSettingsDelegate m9280s() {
        this.f5949m.m8774a();
        if (this.f5962z == null) {
            this.f5962z = new GoogleMapImpl(this);
        }
        return this.f5962z;
    }

    public final void m9259f(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_ZOOM_CONTROLS : UsageLog.MAP_DISABLE_ZOOM_CONTROLS);
        m9196q(z);
    }

    private void m9196q(boolean z) {
        if (m9192b(this.f5952p)) {
            z = false;
        }
        if (this.f5933C != z) {
            this.f5933C = z;
            bi b = this.f5947k.m8912b();
            if (z) {
                this.f5932B = new GoogleMapImpl(this.f5938b, b);
                this.f5932B.m9113a(m9256e());
                this.f5938b.m8944b(this.f5932B);
                b.m8864a(this.f5931A);
            } else {
                b.m8864a(null);
                this.f5938b.m8947c(this.f5932B);
                this.f5932B = null;
            }
            b.m8865a(z);
        }
    }

    public final boolean m9281t() {
        this.f5949m.m8774a();
        return this.f5947k.m8915e().m9508a().booleanValue();
    }

    public final void m9261g(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_MAP_TOOLBAR : UsageLog.MAP_DISABLE_MAP_TOOLBAR);
        m9197r(z);
    }

    private void m9197r(boolean z) {
        ac e = this.f5947k.m8915e();
        if (z != e.m9508a().booleanValue()) {
            e.m9511a(z);
            if (z) {
                this.f5938b.m8944b(e);
            } else {
                this.f5938b.m8947c(e);
            }
        }
    }

    public final void m9263h(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_COMPASS : UsageLog.MAP_DISABLE_COMPASS);
        m9198s(z);
    }

    private void m9198s(boolean z) {
        if (this.f5934D != z) {
            this.f5934D = z;
            Object f = this.f5947k.m8916f();
            f.m9034a(z, this.f5938b.m8945c());
            if (z) {
                f.setOnClickListener(new GoogleMapImpl(this));
                this.f5938b.m8944b(f);
                return;
            }
            this.f5938b.m8947c(f);
            f.setOnClickListener(null);
        }
    }

    public final void m9265i(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_MY_LOCATION_BUTTON : UsageLog.MAP_DISABLE_MY_LOCATION_BUTTON);
        m9199t(z);
    }

    private void m9199t(boolean z) {
        if (m9192b(this.f5952p)) {
            z = false;
        }
        this.f5945i.m8428a(z);
    }

    public final void m9266j(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_SCROLL : UsageLog.MAP_DISABLE_SCROLL);
        m9200u(z);
    }

    private void m9200u(boolean z) {
        this.f5939c.m9486e(z);
    }

    public final void m9268k(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_ZOOM : UsageLog.MAP_DISABLE_ZOOM);
        m9201v(z);
    }

    private void m9201v(boolean z) {
        this.f5939c.m9488f(z);
    }

    public final void m9270l(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_TILT : UsageLog.MAP_DISABLE_TILT);
        m9202w(z);
    }

    private void m9202w(boolean z) {
        this.f5939c.m9490g(z);
    }

    public final void m9272m(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_ROTATE : UsageLog.MAP_DISABLE_ROTATE);
        m9203x(z);
    }

    private void m9203x(boolean z) {
        this.f5939c.m9492h(z);
    }

    public final void m9274n(boolean z) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(z ? UsageLog.MAP_ENABLE_ALL_GESTURES : UsageLog.MAP_DISABLE_ALL_GESTURES);
        m9200u(z);
        m9201v(z);
        m9202w(z);
        m9203x(z);
    }

    public final boolean m9282u() {
        return this.f5933C;
    }

    public final boolean m9283v() {
        return this.f5934D;
    }

    public final boolean m9284w() {
        return this.f5945i.m8431d();
    }

    public final boolean m9285x() {
        return this.f5939c.m9504z();
    }

    public final boolean m9286y() {
        return this.f5939c.m9474A();
    }

    public final boolean m9287z() {
        return this.f5939c.m9475B();
    }

    public final boolean m9204A() {
        return this.f5939c.m9476C();
    }

    public final void m9240a(ISnapshotReadyCallback iSnapshotReadyCallback, IObjectWrapper iObjectWrapper) {
        Preconditions.m1818a((Object) iSnapshotReadyCallback, (Object) "Callback method is null.");
        Bitmap bitmap = (Bitmap) (iObjectWrapper != null ? ObjectWrapper.m10131a(iObjectWrapper) : null);
        this.f5951o.m8835b(bitmap == null ? UsageLog.MAP_SNAPSHOT : UsageLog.MAP_SNAPSHOT_ALLOCATED_BITMAP);
        new Thread(new GoogleMapImpl(this, bitmap, iSnapshotReadyCallback)).start();
    }

    public final View m9205B() {
        this.f5949m.m8774a();
        return this.f5950n;
    }

    public final boolean m9206C() {
        return m9190a(this.f5952p);
    }

    private static boolean m9190a(GoogleMapOptions googleMapOptions) {
        if (googleMapOptions.m4653m() != null) {
            return googleMapOptions.m4653m().booleanValue();
        }
        return false;
    }

    private static boolean m9192b(GoogleMapOptions googleMapOptions) {
        if (googleMapOptions.m4662v() != null) {
            return googleMapOptions.m4662v().booleanValue();
        }
        return false;
    }

    public final void m9234a(aj ajVar) {
        this.f5949m.m8774a();
        this.f5951o.m8835b(UsageLog.MAP_SET_ON_MAP_READY_CALLBACK);
        this.f5960x.post(new GoogleMapImpl(this, ajVar));
    }

    public final void m9250b(String str) {
        this.f5949m.m8774a();
        this.f5939c.m9487f().setContentDescription(str);
    }
}
