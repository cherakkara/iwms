package com.google.android.m4b.maps.bf;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.MotionEvent;
import com.google.android.m4b.maps.be.MapsEngineInfocardManager;
import com.google.android.m4b.maps.be.aa;
import com.google.android.m4b.maps.be.aa.MapsEngineLayerImpl;
import com.google.android.m4b.maps.be.ad;
import com.google.android.m4b.maps.be.ad.MarkerImpl;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.android.m4b.maps.be.ai;
import com.google.android.m4b.maps.be.aj;
import com.google.android.m4b.maps.be.aj.PolyModel;
import com.google.android.m4b.maps.be.az;
import com.google.android.m4b.maps.be.bd;
import com.google.android.m4b.maps.be.bd.TileOverlayImpl;
import com.google.android.m4b.maps.be.cb;
import com.google.android.m4b.maps.be.cb.GroundOverlayImpl;
import com.google.android.m4b.maps.p042r.ak;
import com.google.p025a.p028c.ar;
import com.olacabs.customer.p076d.dm;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.m4b.maps.bf.i */
public final class OverlayRendererManagerLite implements MarkerManagerImpl, ai {
    private final List<RenderableLite> f6173a;
    private final List<MarkerRendererLite> f6174b;
    private final List<ad> f6175c;
    private final MapRendererViewLite f6176d;
    private ad f6177e;
    private float f6178f;
    private float f6179g;
    private float f6180h;
    private float f6181i;
    private OverlayRendererManagerLite f6182j;

    /* renamed from: com.google.android.m4b.maps.bf.i.a */
    class OverlayRendererManagerLite implements Comparator<RenderableLite> {
        OverlayRendererManagerLite(OverlayRendererManagerLite overlayRendererManagerLite) {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return Float.compare(((RenderableLite) obj).m9689b(), ((RenderableLite) obj2).m9689b());
        }
    }

    public OverlayRendererManagerLite(MapRendererViewLite mapRendererViewLite, ScheduledExecutorService scheduledExecutorService) {
        this.f6173a = ar.m2515a();
        this.f6174b = ar.m2515a();
        this.f6175c = ar.m2515a();
        this.f6182j = new OverlayRendererManagerLite(this);
        this.f6176d = mapRendererViewLite;
    }

    public final GroundOverlayImpl m9672a(cb cbVar) {
        az.m8757a(5, "Ground Overlays are not supported in Lite Mode");
        return null;
    }

    public final MapsEngineLayerImpl m9667a(aa aaVar) {
        az.m8757a(5, "Maps Engine Layers are not supported in Lite Mode");
        return null;
    }

    public final PolyModel m9670a(aj ajVar, boolean z) {
        return new PolyRendererLite(ajVar, z, this);
    }

    public final TileOverlayImpl m9671a(bd bdVar) {
        az.m8757a(5, "Tile Overlays are not supported in Lite Mode");
        return null;
    }

    public final void m9674a(MapsEngineInfocardManager mapsEngineInfocardManager) {
    }

    public final void m9677a(ak akVar) {
    }

    final void m9676a(RenderableLite renderableLite) {
        this.f6173a.add(renderableLite);
        this.f6176d.invalidate();
    }

    final void m9681b(RenderableLite renderableLite) {
        this.f6173a.remove(renderableLite);
        this.f6176d.invalidate();
    }

    final void m9675a(MarkerRendererLite markerRendererLite) {
        this.f6174b.add(markerRendererLite);
        this.f6176d.invalidate();
    }

    final void m9680b(MarkerRendererLite markerRendererLite) {
        this.f6174b.remove(markerRendererLite);
        if (this.f6177e == markerRendererLite.m9661g()) {
            this.f6177e = null;
        }
        this.f6176d.invalidate();
    }

    final void m9683c(MarkerRendererLite markerRendererLite) {
        if (!(this.f6177e == null || this.f6177e == markerRendererLite.m9661g())) {
            this.f6177e = markerRendererLite.m9661g();
        }
        this.f6176d.invalidate();
    }

    final void m9685d(MarkerRendererLite markerRendererLite) {
        if (this.f6177e == markerRendererLite.m9661g()) {
            this.f6177e = null;
        }
        this.f6176d.invalidate();
    }

    final boolean m9687e(MarkerRendererLite markerRendererLite) {
        return this.f6177e == markerRendererLite.m9661g();
    }

    final ad m9669a() {
        return (this.f6177e == null || !this.f6175c.contains(this.f6177e)) ? null : this.f6177e;
    }

    public final void m9679b() {
        this.f6176d.invalidate();
    }

    public final boolean m9678a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.f6177e == null || x < this.f6178f || x > this.f6178f + this.f6180h || y < this.f6179g || y > this.f6179g + this.f6181i) {
            Rect H;
            Object obj;
            float f = 1.0E30f;
            ad adVar = null;
            for (ad adVar2 : this.f6175c) {
                float f2;
                if (adVar2.m8388x()) {
                    H = adVar2.m8349H();
                    float exactCenterX = x - H.exactCenterX();
                    float exactCenterY = y - H.exactCenterY();
                    exactCenterY = (exactCenterY * exactCenterY) + (exactCenterX * exactCenterX);
                    if (exactCenterY < f) {
                        f2 = exactCenterY;
                        f = f2;
                        adVar = adVar2;
                    }
                }
                ad adVar22 = adVar;
                f2 = f;
                f = f2;
                adVar = adVar22;
            }
            if (adVar == null || !adVar.m8388x()) {
                obj = null;
            } else {
                Rect H2 = adVar.m8349H();
                H = new Rect(H2.left - 10, H2.top - 10, H2.right + 10, H2.bottom + 10);
                if (x < ((float) H.left) || x > ((float) H.right) || y < ((float) H.top) || y > ((float) H.bottom)) {
                    obj = null;
                } else {
                    int i = 1;
                }
            }
            if (obj != null) {
                adVar.m8385u();
                adVar.m8348G().m8406g(adVar);
                if (!(this.f6177e == null || this.f6177e == adVar)) {
                    this.f6177e.m8386v();
                }
                this.f6177e = adVar;
                this.f6176d.invalidate();
                return true;
            } else if (this.f6177e == null) {
                return false;
            } else {
                this.f6177e.m8386v();
                this.f6177e = null;
                this.f6176d.invalidate();
                return true;
            }
        }
        this.f6177e.m8348G().m8407h(this.f6177e);
        return true;
    }

    public final void m9673a(Canvas canvas, ProjectionLite projectionLite) {
        Collections.sort(this.f6173a, this.f6182j);
        for (RenderableLite a : this.f6173a) {
            a.m9688a(canvas, projectionLite);
        }
        for (MarkerRendererLite a2 : this.f6174b) {
            a2.m9655a(canvas, projectionLite);
        }
        this.f6175c.clear();
        if (projectionLite != null) {
            Rect rect = new Rect(0, 0, projectionLite.f6194f, projectionLite.f6195g);
            for (MarkerRendererLite a22 : this.f6174b) {
                if (Rect.intersects(rect, a22.m9662i())) {
                    this.f6175c.add(a22.m9661g());
                }
            }
        }
        if (this.f6177e != null) {
            Bitmap a3;
            ad adVar = this.f6177e;
            Bitmap f = adVar.m8370f();
            Rect H = adVar.m8349H();
            try {
                a3 = adVar.m8348G().m8400b().m9383a(adVar, canvas.getWidth(), canvas.getHeight());
            } catch (RemoteException e) {
                e.printStackTrace();
                a3 = null;
            }
            if (a3 != null) {
                this.f6180h = (float) a3.getWidth();
                this.f6181i = (float) a3.getHeight();
                float k = adVar.m8375k();
                float m = adVar.m8377m();
                Paint paint = new Paint();
                this.f6178f = ((k * ((float) f.getWidth())) + ((float) H.left)) - (this.f6180h / dm.DEFAULT_BACKOFF_MULT);
                this.f6179g = (((float) H.top) + (m * ((float) f.getHeight()))) - this.f6181i;
                canvas.drawBitmap(a3, this.f6178f, this.f6179g, paint);
            }
        }
    }

    public final ad m9682c() {
        if (this.f6175c.size() == 1) {
            return (ad) this.f6175c.get(0);
        }
        return null;
    }

    public final boolean m9686e() {
        return this.f6175c.size() > 1;
    }

    public final List<ad> m9684d() {
        return this.f6175c;
    }

    public final MarkerImpl m9668a(ad adVar) {
        return new MarkerRendererLite(adVar, this);
    }
}
