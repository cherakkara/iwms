package com.google.android.m4b.maps.p061y;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.SurfaceView;
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
import com.google.android.m4b.maps.av.ap;
import com.google.android.m4b.maps.av.ap.Gmm6TextureVectorMapView;
import com.google.android.m4b.maps.bc.LabelTheme;
import com.google.android.m4b.maps.be.IndoorStateInterface;
import com.google.android.m4b.maps.be.MapIdleWaiter;
import com.google.android.m4b.maps.be.MapViewUtils;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.android.m4b.maps.be.ag.MyLocationLayerImpl;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.google.android.m4b.maps.be.aq;
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

/* renamed from: com.google.android.m4b.maps.y.s */
public class TextureVectorMapViewImpl extends ap implements VectorMapView {
    private static /* synthetic */ boolean f8171A;
    private final VectorMapController f8172a;
    private final VectorMapControllerAdapter f8173b;
    private final CameraManagerImpl f8174c;
    private final OverlayRendererManagerImpl f8175d;
    private final GLMarkerManager f8176e;
    private final ViewPointRecorder f8177f;
    private final cc f8178g;
    private final Gmm6TextureVectorMapView f8179h;
    private final MapIdleWaiterImpl f8180i;
    private final TextureVectorMapViewImpl f8181j;
    private final TextureVectorMapViewImpl f8182k;
    private final IndoorStateAdapter f8183l;
    private final MyLocationRendererImpl f8184m;
    private final SnapshotterImpl f8185n;
    private ag f8186o;
    private ai f8187p;
    private al f8188q;
    private al f8189r;
    private al f8190s;
    private IndoorTileOverlay f8191t;
    private int f8192u;
    private int f8193v;
    private int f8194w;
    private int f8195x;
    private ba f8196y;
    private ca f8197z;

    /* renamed from: com.google.android.m4b.maps.y.s.c */
    interface TextureVectorMapViewImpl {
        IndoorTileOverlay m11843a(Context context, Resources resources);

        TileOverlay m11844a(Resources resources);

        TileOverlay m11845a(com.google.android.m4b.maps.an.ai aiVar, Resources resources);

        TileOverlay m11846a(TileOverlay tileOverlay, Resources resources);

        TileOverlay m11847b(com.google.android.m4b.maps.an.ai aiVar, Resources resources);
    }

    /* renamed from: com.google.android.m4b.maps.y.s.1 */
    static class TextureVectorMapViewImpl implements TextureVectorMapViewImpl {
        private /* synthetic */ String f8165a;
        private /* synthetic */ IndoorStateAdapter f8166b;

        TextureVectorMapViewImpl(String str, IndoorStateAdapter indoorStateAdapter) {
            this.f8165a = str;
            this.f8166b = indoorStateAdapter;
        }

        public final /* bridge */ /* synthetic */ TileOverlay m11849a(Resources resources) {
            TileOverlay a = TileOverlay.m6721a(resources, com.google.android.m4b.maps.an.ai.f3428f);
            TextureVectorMapViewImpl.m11876a(a, this.f8165a);
            return a;
        }

        public final TileOverlay m11850a(com.google.android.m4b.maps.an.ai aiVar, Resources resources) {
            return TileOverlay.m6724b(aiVar, resources);
        }

        public final TileOverlay m11852b(com.google.android.m4b.maps.an.ai aiVar, Resources resources) {
            TileOverlay a = TileOverlay.m6720a(aiVar, resources);
            TextureVectorMapViewImpl.m11876a(a, this.f8165a);
            return a;
        }

        public final IndoorTileOverlay m11848a(Context context, Resources resources) {
            return (IndoorTileOverlay) TileOverlay.m6718a(context, resources, this.f8166b.m11730f());
        }

        public final TileOverlay m11851a(TileOverlay tileOverlay, Resources resources) {
            TileOverlay a = TileOverlay.m6719a(tileOverlay.m6751o(), com.google.android.m4b.maps.an.ai.f3423a, resources);
            TextureVectorMapViewImpl.m11876a(a, this.f8165a);
            return a;
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.s.2 */
    class TextureVectorMapViewImpl implements ViewPointRecorder.ViewPointRecorder {
        TextureVectorMapViewImpl(TextureVectorMapViewImpl textureVectorMapViewImpl) {
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.s.3 */
    class TextureVectorMapViewImpl implements Gmm6TextureVectorMapView {
        private /* synthetic */ TextureVectorMapViewImpl f8167a;

        TextureVectorMapViewImpl(TextureVectorMapViewImpl textureVectorMapViewImpl) {
            this.f8167a = textureVectorMapViewImpl;
        }

        public final void m11853a(Point point) {
            if (this.f8167a.f8187p != null) {
                try {
                    this.f8167a.f8187p.m11102a(ConversionUtils.m11635a(point));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        }

        public final void m11854b(Point point) {
            if (this.f8167a.f8186o != null) {
                try {
                    this.f8167a.f8186o.m11099a(ConversionUtils.m11635a(point));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.s.4 */
    class TextureVectorMapViewImpl implements Runnable {
        private /* synthetic */ TextureVectorMapViewImpl f8168a;

        TextureVectorMapViewImpl(TextureVectorMapViewImpl textureVectorMapViewImpl) {
            this.f8168a = textureVectorMapViewImpl;
        }

        public final void run() {
            super.m6924a(true, false);
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.s.5 */
    class TextureVectorMapViewImpl implements Gmm6TextureVectorMapView {
        private /* synthetic */ VectorMapView f8169a;

        TextureVectorMapViewImpl(TextureVectorMapViewImpl textureVectorMapViewImpl, VectorMapView vectorMapView) {
            this.f8169a = vectorMapView;
        }

        public final boolean m11855a(Point point) {
            return this.f8169a.m11780f();
        }

        public final boolean m11856b(Point point) {
            VectorMapView vectorMapView = this.f8169a;
            return false;
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.s.a */
    interface TextureVectorMapViewImpl {
        TextureVectorMapViewImpl() {
        }

        void m11857a() {
            TaskRunner a = TaskRunnerManager.m11489a();
            DeferredTasksHelper.m11522a();
            DeferredTasksHelper.m11523a(DeferredTasksHelper.DeferredTasksHelper.ON_RESUME, a);
        }

        void m11858b() {
            DeferredTasksHelper.m11525b();
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.s.b */
    static final class TextureVectorMapViewImpl implements ViewPointRecorder.ViewPointRecorder {
        private IndoorStateAdapter f8170a;

        private TextureVectorMapViewImpl(IndoorStateAdapter indoorStateAdapter) {
            this.f8170a = indoorStateAdapter;
        }

        public final void m11859a(int i) {
            if (this.f8170a != null && this.f8170a.m11728d()) {
                UserEventReporter.m11502a(104, "v", "|z=" + i + '|');
            }
        }
    }

    static {
        f8171A = !TextureVectorMapViewImpl.class.desiredAssertionStatus();
    }

    static /* synthetic */ void m11876a(TileOverlay tileOverlay, String str) {
        if (!Strings.m1866b(str)) {
            tileOverlay.m6742a(new AlternatePaintfeParameters().m5688a(str).m5689a());
        }
    }

    public final /* synthetic */ ProjectionDelegate m11905r() {
        return new ProjectionImpl(m6940t(), this.f8192u, this.f8193v, this.f8194w, this.f8195x);
    }

    public static VectorMapView m11875a(Context context, Resources resources, bt btVar, ScheduledExecutorService scheduledExecutorService, View view, bm bmVar, be beVar, String str, boolean z, TextView textView, ba baVar) {
        Gmm6RendererInitializer.m11679a(context, btVar);
        DataRequestDispatcher a = btVar.m9044a();
        VectorMapController vectorMapController = new VectorMapController(resources);
        ViewPointRecorder viewPointRecorder = new ViewPointRecorder(a);
        IndoorStateAdapter a2 = IndoorStateAdapter.m11712a(IndoorState.m5334a(), new Handler(Looper.getMainLooper()), beVar);
        TextureVectorMapViewImpl textureVectorMapViewImpl = new TextureVectorMapViewImpl(str, a2);
        if (!Strings.m1866b(str)) {
            VectorGlobalState.m7281a(com.google.android.m4b.maps.an.ai.f3432j, context, resources, a);
            VectorGlobalState.m7281a(com.google.android.m4b.maps.an.ai.f3435m, context, resources, a);
        }
        return new TextureVectorMapViewImpl(context, resources, vectorMapController, viewPointRecorder, textureVectorMapViewImpl, a2, new TextureVectorMapViewImpl(), scheduledExecutorService, view, bmVar, str, z, textView, baVar);
    }

    private TextureVectorMapViewImpl(Context context, Resources resources, VectorMapController vectorMapController, ViewPointRecorder viewPointRecorder, TextureVectorMapViewImpl textureVectorMapViewImpl, IndoorStateAdapter indoorStateAdapter, TextureVectorMapViewImpl textureVectorMapViewImpl2, ScheduledExecutorService scheduledExecutorService, View view, bm bmVar, String str, boolean z, TextView textView, ba baVar) {
        super(context, resources, textView, bmVar.m8915e());
        this.f8196y = baVar;
        this.f8172a = vectorMapController;
        this.f8172a.m7319a(67.5f);
        this.f8173b = new VectorMapControllerAdapter(this.f8172a, this);
        m6921a(this.f8172a);
        m6891i(true);
        m6892j(!z);
        this.f8177f = viewPointRecorder;
        this.f8177f.m7349a(new TextureVectorMapViewImpl(this));
        this.f8172a.m7325a(this.f8177f);
        this.f8175d = new OverlayRendererManagerImpl(this, scheduledExecutorService);
        this.f8176e = GLMarkerManager.m11646a(this, this.f8173b, this.f8196y);
        this.f8197z = new ca(this, this.f8176e);
        ViewCompat.setAccessibilityDelegate(this, this.f8197z);
        Handler handler = new Handler(Looper.getMainLooper());
        this.f8174c = new CameraManagerImpl(this, this.f8173b, handler);
        this.f8184m = new MyLocationRendererImpl(this);
        this.f8180i = MapIdleWaiterImpl.m11731a(this, handler);
        this.f8183l = indoorStateAdapter;
        this.f8181j = textureVectorMapViewImpl;
        if (!Strings.m1866b(str)) {
            m6920a(this.f8181j.m11847b(com.google.android.m4b.maps.an.ai.f3423a, getResources()));
        }
        this.f8182k = textureVectorMapViewImpl2;
        this.f8185n = new SnapshotterImpl(this, view, bmVar.m8910a(), !(this instanceof SurfaceView), MapViewUtils.m9533a());
        this.f8179h = new TextureVectorMapViewImpl(this);
        this.f8178g = new cc(new TextureVectorMapViewImpl(this));
        m6916a(this.f8181j.m11847b(com.google.android.m4b.maps.an.ai.f3437o, getResources()));
        GmmSettings.m11527a();
        m6923a(GmmSettings.m11528a(resources) ? LabelTheme.f5411u : LabelTheme.f5410t);
        m6916a(this.f8176e.m11661c());
        m6916a(this.f8175d);
    }

    private void m11873G() {
        if (this.f8186o == null && this.f8187p == null) {
            m6918a(null);
        } else {
            m6918a(this.f8179h);
        }
    }

    public final void m11879a() {
        this.f8182k.m11857a();
        super.m6912a();
    }

    public final void m11887b() {
        this.f8182k.m11858b();
        super.m6926b();
    }

    public final void m11889c() {
        m11888b(false);
        m6892j(false);
        super.m6929c();
    }

    public final void m11891d() {
        this.f8180i.m11733a();
        this.f8178g.m9353a();
    }

    public final void m11882a(ag agVar) {
        this.f8186o = agVar;
        m11873G();
    }

    public final void m11883a(ai aiVar) {
        this.f8187p = aiVar;
        m11873G();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public final void m11884a(VectorMapView vectorMapView) {
        if (vectorMapView == null) {
            m6917a(null);
        } else {
            m6917a(new TextureVectorMapViewImpl(this, vectorMapView));
        }
    }

    public boolean canScrollHorizontally(int i) {
        return m6946z();
    }

    public boolean canScrollVertically(int i) {
        return m6946z();
    }

    public final com.google.android.m4b.maps.av.ai m11892e() {
        return m6908D().m7257e();
    }

    public final View m11893f() {
        return this;
    }

    public final bp m11894g() {
        return this.f8174c;
    }

    public final MarkerManagerImpl m11895h() {
        return this.f8176e;
    }

    public final com.google.android.m4b.maps.be.ai m11896i() {
        return this.f8175d;
    }

    public final MyLocationLayerImpl m11897j() {
        return this.f8184m;
    }

    public final IndoorStateInterface m11898k() {
        return this.f8183l;
    }

    public final aq m11899l() {
        return this.f8185n;
    }

    public final MapIdleWaiter m11900m() {
        return this.f8180i;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f8192u = i;
        this.f8193v = i2;
        this.f8194w = i3;
        this.f8195x = i4;
        m11891d();
    }

    public final boolean m11901n() {
        return this.f8188q != null;
    }

    public final void m11885a(boolean z) {
        if (z) {
            if (this.f8188q == null) {
                this.f8188q = this.f8181j.m11844a(getResources());
                m6916a(this.f8188q);
            }
        } else if (this.f8188q != null) {
            m6928b(this.f8188q);
            this.f8188q = null;
        }
        if (!f8171A) {
            boolean z2;
            if (this.f8188q != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z != z2) {
                throw new AssertionError();
            }
        }
    }

    public final boolean m11902o() {
        return this.f8183l != null && this.f8183l.m11729e();
    }

    public final boolean m11888b(boolean z) {
        if (!(this.f8183l == null || m11902o() == z)) {
            if (!z) {
                this.f8177f.m7348a(null);
                m6928b(this.f8191t);
                this.f8191t.g_();
                this.f8191t = null;
                this.f8183l.m11723b();
            } else if (this.f8183l.m11721a()) {
                this.f8191t = this.f8181j.m11843a(getContext(), getResources());
                m6916a(this.f8191t);
                this.f8177f.m7348a(new TextureVectorMapViewImpl((byte) 0));
            }
        }
        return m11902o();
    }

    public final boolean m11903p() {
        return this.f8189r != null;
    }

    public final void m11890c(boolean z) {
        if (z) {
            if (this.f8189r == null) {
                this.f8189r = this.f8181j.m11846a(m6909E(), getResources());
                m6916a(this.f8189r);
            }
        } else if (this.f8189r != null) {
            m6928b(this.f8189r);
            this.f8189r = null;
        }
        if (!f8171A) {
            boolean z2;
            if (this.f8189r != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z != z2) {
                throw new AssertionError();
            }
        }
    }

    public final void m11881a(int i) {
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
        if (this.f8190s != null) {
            m6928b(this.f8190s);
            this.f8190s = null;
        }
        if (!(aiVar == null || aiVar == com.google.android.m4b.maps.an.ai.f3423a)) {
            this.f8190s = this.f8181j.m11845a(aiVar, getResources());
            m6916a(this.f8190s);
        }
        m6915a(acVar);
    }

    public final void m11886a(boolean z, boolean z2) {
        this.f8197z.m9291a();
        super.m6924a(z, z2);
    }

    public final void m11880a(float f, float f2) {
        this.f8197z.m9291a();
        super.m6913a(f, f2);
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.f8197z.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    public final String m11904q() {
        return "G";
    }
}
