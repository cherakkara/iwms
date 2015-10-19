package com.google.android.m4b.maps.p061y;

import android.content.Context;
import android.content.res.Resources;
import android.os.RemoteException;
import android.util.TypedValue;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.av.RepaintCallback;
import com.google.android.m4b.maps.av.TileOverlay;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.av.al;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.be.MapsEngineFeatureDataRequestBatch.MapsEngineFeatureDataRequestBatch;
import com.google.android.m4b.maps.be.MapsEngineFeatureImpl;
import com.google.android.m4b.maps.be.MapsEngineInfocardManager;
import com.google.android.m4b.maps.be.aa;
import com.google.android.m4b.maps.be.aa.MapsEngineLayerImpl;
import com.google.android.m4b.maps.be.ai;
import com.google.android.m4b.maps.be.aj;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.be.bd;
import com.google.android.m4b.maps.be.bd.TileOverlayImpl;
import com.google.android.m4b.maps.be.cb;
import com.google.android.m4b.maps.be.cb.GroundOverlayImpl;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.LatLngBounds.C0595a;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p042r.ak;
import com.google.android.m4b.maps.p061y.VectorMapView.VectorMapView;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.bk;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.m4b.maps.y.n */
public final class OverlayRendererManagerImpl extends al implements ai, VectorMapView {
    private static final GLOverlay f8133a;
    private static final Comparator<Overlay> f8134b;
    private final VectorMapView f8135c;
    private final Set<Overlay> f8136d;
    private final List<Overlay> f8137e;
    private final ScheduledExecutorService f8138f;
    private RepaintCallback f8139g;
    private GLState f8140h;
    private ak f8141i;
    private MapsEngineInfocardManager f8142j;
    private long f8143k;

    /* renamed from: com.google.android.m4b.maps.y.n.1 */
    static class OverlayRendererManagerImpl implements Comparator<Overlay> {
        OverlayRendererManagerImpl() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            Overlay overlay = (Overlay) obj;
            Overlay overlay2 = (Overlay) obj2;
            int compare = Float.compare(overlay.m11692f(), overlay2.m11692f());
            return compare != 0 ? compare : overlay.m11691e().compareTo(overlay2.m11691e());
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.n.2 */
    class OverlayRendererManagerImpl implements MapsEngineFeatureDataRequestBatch {
        private /* synthetic */ List f8130a;
        private /* synthetic */ List f8131b;
        private /* synthetic */ OverlayRendererManagerImpl f8132c;

        OverlayRendererManagerImpl(OverlayRendererManagerImpl overlayRendererManagerImpl, List list, List list2) {
            this.f8132c = overlayRendererManagerImpl;
            this.f8130a = list;
            this.f8131b = list2;
        }

        public final void m11779a() {
            if (this.f8132c.f8142j != null) {
                this.f8132c.f8142j.m9565a(this.f8130a);
            }
            if (this.f8132c.f8141i != null) {
                List b = ar.m2523b(this.f8131b.size());
                for (MapsEngineFeatureImpl add : this.f8131b) {
                    b.add(add);
                }
                try {
                    this.f8132c.f8141i.m11106b(b);
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
        }
    }

    static {
        f8133a = GLOverlay.DESATURATE;
        f8134b = new OverlayRendererManagerImpl();
    }

    public OverlayRendererManagerImpl(VectorMapView vectorMapView, ScheduledExecutorService scheduledExecutorService) {
        this.f8136d = bk.m2870a();
        this.f8137e = ar.m2515a();
        this.f8135c = vectorMapView;
        this.f8138f = scheduledExecutorService;
        this.f8135c.m11865a((VectorMapView) this);
    }

    public final synchronized void m11792a(GLState gLState, RepaintCallback repaintCallback) {
        this.f8140h = gLState;
        this.f8139g = repaintCallback;
        for (Overlay a : this.f8137e) {
            a.m11683a(this.f8140h, this.f8139g);
        }
    }

    public final synchronized void m11791a(GLState gLState) {
        for (Overlay a : this.f8137e) {
            a.m11682a(gLState);
        }
    }

    public final synchronized void m11797a(boolean z) {
        for (Overlay a : this.f8137e) {
            a.m11685a(z);
        }
    }

    public final synchronized void h_() {
        for (Overlay b : this.f8137e) {
            b.m11687b();
        }
    }

    public final synchronized void m11789a(int i) {
        for (Overlay b : this.f8137e) {
            b.m11688b(i);
        }
    }

    final void m11800b() {
        this.f8135c.m11869d();
    }

    public final PolyModel m11785a(aj ajVar, boolean z) {
        if (z) {
            Overlay polygonRendererImpl = new PolygonRendererImpl(this, ajVar);
            m11783b(polygonRendererImpl);
            return polygonRendererImpl;
        }
        polygonRendererImpl = new PolylineRendererImpl(this, ajVar);
        m11783b(polygonRendererImpl);
        return polygonRendererImpl;
    }

    public final GroundOverlayImpl m11787a(cb cbVar) {
        Overlay groundOverlayRendererImpl = new GroundOverlayRendererImpl(this, cbVar);
        m11783b(groundOverlayRendererImpl);
        return groundOverlayRendererImpl;
    }

    public final MapsEngineLayerImpl m11784a(aa aaVar) {
        MapsEngineInfocardManager mapsEngineInfocardManager = this.f8142j;
        Context context = this.f8135c.getContext();
        Resources resources = this.f8135c.getResources();
        ScheduledExecutorService scheduledExecutorService = this.f8138f;
        VectorGlobalState.m7282a(com.google.android.m4b.maps.an.ai.f3446x, new MapsEngineDataRequestDispatcher(DataRequestDispatcher.m11393a(), scheduledExecutorService), context, resources);
        Overlay mapsEngineLayerRendererImpl = new MapsEngineLayerRendererImpl(aaVar, mapsEngineInfocardManager, TileOverlay.m6725c(com.google.android.m4b.maps.an.ai.f3446x, resources), this);
        m11783b(mapsEngineLayerRendererImpl);
        return mapsEngineLayerRendererImpl;
    }

    public final TileOverlayImpl m11786a(bd bdVar) {
        Overlay a = TileOverlayRendererImpl.m11930a(bdVar, this.f8135c.getResources(), this, this.f8138f);
        m11783b(a);
        return a;
    }

    private void m11783b(Overlay overlay) {
        synchronized (this) {
            this.f8136d.add(overlay);
            this.f8137e.add(overlay);
            m11801c();
            if (this.f8140h != null) {
                overlay.m11683a(this.f8140h, this.f8139g);
            }
            overlay.m11680a(this.f8143k);
        }
        m11800b();
    }

    public final void m11794a(MapsEngineInfocardManager mapsEngineInfocardManager) {
        this.f8142j = mapsEngineInfocardManager;
    }

    public final void m11795a(ak akVar) {
        this.f8141i = akVar;
    }

    final synchronized void m11796a(Overlay overlay) {
        if (this.f8136d.contains(overlay)) {
            this.f8137e.remove(overlay);
            this.f8136d.remove(overlay);
            overlay.m11690d();
        }
    }

    final void m11801c() {
        Collections.sort(this.f8137e, f8134b);
        m11800b();
    }

    public final synchronized boolean m11799a(Camera camera, GLState gLState) {
        for (Overlay a : this.f8137e) {
            a.m11681a(camera, gLState);
        }
        return true;
    }

    public final synchronized void m11793a(GLState gLState, Camera camera, ad adVar) {
        for (Overlay overlay : this.f8137e) {
            gLState.m7543z();
            overlay.m11684a(gLState, camera, adVar);
            gLState.m7503A();
        }
    }

    public final GLOverlay m11802d() {
        return f8133a;
    }

    public final synchronized boolean m11798a(float f, float f2, Point point, Camera camera) {
        boolean z;
        for (Overlay a : ar.m2519a(this.f8137e)) {
            if (a.m11686a(f, f2, point, camera)) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public final void m11788a(float f, float f2, Point point, Camera camera, MapsEngineLayerRendererImpl mapsEngineLayerRendererImpl, List<MapsEngineFeatureImpl> list) {
        List a = ar.m2515a();
        if (mapsEngineLayerRendererImpl.m11775g().m8294k()) {
            a.addAll(list);
        }
        for (Object obj : ar.m2519a(this.f8137e)) {
            if ((obj instanceof MapsEngineLayerRendererImpl) && obj != mapsEngineLayerRendererImpl) {
                ((MapsEngineLayerRendererImpl) obj).m11759a(f, f2, point, camera, list, a);
            }
        }
        if (!(a.isEmpty() || this.f8142j == null)) {
            MapsEngineInfocardManager mapsEngineInfocardManager = this.f8142j;
            float applyDimension = TypedValue.applyDimension(1, 52.0f, this.f8135c.getResources().getDisplayMetrics());
            float a2 = camera.m7419a(applyDimension, camera.m7420a(point, true));
            applyDimension = camera.m7419a(applyDimension, camera.m7420a(point, true));
            Point point2 = new Point((int) (((float) point.m5958f()) + a2), (int) (((float) point.m5960g()) - applyDimension));
            Point point3 = new Point((int) (((float) point.m5958f()) - a2), (int) (applyDimension + ((float) point.m5960g())));
            LatLng a3 = ConversionUtils.m11635a(point2);
            LatLng a4 = ConversionUtils.m11635a(point3);
            C0595a b = LatLngBounds.m10742b();
            b.m10738a(a3);
            b.m10738a(a4);
            mapsEngineInfocardManager.m9564a(b.m10739a(), a);
        }
        if (this.f8141i != null) {
            List b2 = ar.m2523b(list.size());
            for (MapsEngineFeatureImpl add : list) {
                b2.add(add);
            }
            try {
                this.f8141i.m11105a(b2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        DataRequestDispatcher.m11393a().m11451c(new com.google.android.m4b.maps.be.MapsEngineFeatureDataRequestBatch(list, new OverlayRendererManagerImpl(this, a, list)));
    }

    public final synchronized void m11790a(long j) {
        this.f8143k = j;
        for (Overlay a : this.f8137e) {
            a.m11680a(j);
        }
    }

    public final synchronized boolean m11803e() {
        boolean z;
        for (Overlay c : this.f8137e) {
            if (!c.m11689c()) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    public final boolean m11804f() {
        if (this.f8142j != null) {
            this.f8142j.m9566b();
        }
        return false;
    }
}
