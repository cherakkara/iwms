package com.google.android.m4b.maps.p061y;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.m4b.maps.av.BubbleBlower;
import com.google.android.m4b.maps.av.BubbleBlowerOverlay.BubbleBlowerOverlay;
import com.google.android.m4b.maps.av.BubbleView;
import com.google.android.m4b.maps.av.ai;
import com.google.android.m4b.maps.av.ai.GLBubbleOverlay;
import com.google.android.m4b.maps.av.ak;
import com.google.android.m4b.maps.av.ak.GLMarkerOverlay;
import com.google.android.m4b.maps.av.al;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ba.GLMarker;
import com.google.android.m4b.maps.be.ad;
import com.google.android.m4b.maps.be.ad.MarkerImpl;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.android.m4b.maps.be.ba;
import com.google.p025a.p028c.au;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.y.d */
public final class GLMarkerManager implements GLBubbleOverlay, GLMarkerOverlay, BubbleBlowerOverlay, MarkerManagerImpl {
    private final Map<String, ad> f8072a;
    private final VectorMapControllerAdapter f8073b;
    private final VectorMapView f8074c;
    private final ak f8075d;
    private final ai f8076e;
    private final ba f8077f;

    /* renamed from: com.google.android.m4b.maps.y.d.1 */
    class GLMarkerManager implements OnGlobalLayoutListener {
        private /* synthetic */ GLMarkerRenderer f8070a;
        private /* synthetic */ GLMarkerManager f8071b;

        GLMarkerManager(GLMarkerManager gLMarkerManager, GLMarkerRenderer gLMarkerRenderer) {
            this.f8071b = gLMarkerManager;
            this.f8070a = gLMarkerRenderer;
        }

        @SuppressLint({"NewApi"})
        public final void onGlobalLayout() {
            if (this.f8071b.m11651e()) {
                this.f8071b.m11659b(this.f8070a);
                ViewTreeObserver viewTreeObserver = this.f8071b.f8074c.m9487f().getViewTreeObserver();
                if (VERSION.SDK_INT < 16) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                } else {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        }
    }

    private GLMarkerManager(VectorMapView vectorMapView, VectorMapControllerAdapter vectorMapControllerAdapter, ak akVar, ai aiVar, ba baVar) {
        this.f8072a = au.m2807a();
        this.f8074c = vectorMapView;
        this.f8073b = vectorMapControllerAdapter;
        this.f8075d = akVar;
        this.f8076e = aiVar;
        this.f8077f = baVar;
        this.f8075d.m6813a((BubbleBlowerOverlay) this);
        this.f8075d.m6824a((GLMarkerOverlay) this);
        aiVar.m6788a((GLBubbleOverlay) this);
    }

    public static GLMarkerManager m11646a(VectorMapView vectorMapView, VectorMapControllerAdapter vectorMapControllerAdapter, ba baVar) {
        return new GLMarkerManager(vectorMapView, vectorMapControllerAdapter, vectorMapView.m11861a(GLOverlay.LAYER_MARKERS), vectorMapView.m11870e(), baVar);
    }

    protected final void m11653a() {
        this.f8074c.m11869d();
    }

    protected final void m11657b() {
        this.f8075d.m6833c();
    }

    protected final void m11656a(GLMarkerRenderer gLMarkerRenderer) {
        this.f8075d.m6828a(gLMarkerRenderer.m11675g());
        ad h = gLMarkerRenderer.m11676h();
        this.f8072a.put(h.m8350a(), h);
        m11653a();
    }

    public final void m11655a(GLMarker gLMarker) {
        this.f8072a.remove(gLMarker.m8076m());
        this.f8075d.m6831b(gLMarker);
    }

    public final void m11659b(GLMarkerRenderer gLMarkerRenderer) {
        if (!m11651e()) {
            ViewTreeObserver viewTreeObserver = this.f8074c.m9487f().getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new GLMarkerManager(this, gLMarkerRenderer));
            }
        }
        BubbleBlower g = gLMarkerRenderer.m11675g();
        ad h = gLMarkerRenderer.m11676h();
        if (g.m8067d()) {
            Bitmap a;
            try {
                a = h.m8348G().m8400b().m9383a(gLMarkerRenderer.m11676h(), this.f8074c.getWidth(), this.f8074c.getHeight());
            } catch (RemoteException e) {
                e.printStackTrace();
                a = null;
            }
            if (a != null) {
                this.f8074c.m11864a(g, new BubbleView(a));
            }
        }
    }

    private boolean m11651e() {
        return (this.f8074c.getWidth() == 0 && this.f8074c.getHeight() == 0) ? false : true;
    }

    public final void m11663c(GLMarkerRenderer gLMarkerRenderer) {
        GLMarker g = gLMarkerRenderer.m11675g();
        if (g.m8067d()) {
            synchronized (this.f8076e) {
                if (g.m8075l()) {
                    this.f8074c.m11871s();
                }
            }
        }
    }

    protected final boolean m11660b(GLMarker gLMarker) {
        boolean l;
        synchronized (this.f8076e) {
            l = gLMarker.m8075l();
        }
        return l;
    }

    public final al m11661c() {
        return this.f8075d;
    }

    public final void m11654a(BubbleBlower bubbleBlower) {
        this.f8077f.m8774a();
        ad c = m11649c(bubbleBlower);
        if (c != null && !c.m8348G().m8406g(c)) {
            GLMarker d = GLMarkerManager.m11650d(bubbleBlower);
            if (d != null) {
                this.f8073b.m11957a(d.m8069f(), (int) HttpStatus.SC_MULTIPLE_CHOICES);
            }
        }
    }

    public final void m11662c(GLMarker gLMarker) {
        this.f8077f.m8774a();
        ad c = m11649c((BubbleBlower) gLMarker);
        if (c != null) {
            c.m8348G().m8403d(c);
        }
    }

    public final void m11665d(GLMarker gLMarker) {
        this.f8077f.m8774a();
        ad c = m11649c((BubbleBlower) gLMarker);
        if (c != null) {
            c.m8348G().m8404e(c);
        }
    }

    public final void m11666e(GLMarker gLMarker) {
        this.f8077f.m8774a();
        ad c = m11649c((BubbleBlower) gLMarker);
        if (c != null) {
            c.m8348G().m8405f(c);
        }
    }

    public final void m11658b(BubbleBlower bubbleBlower) {
        this.f8077f.m8774a();
        ad c = m11649c(bubbleBlower);
        if (c != null) {
            c.m8348G().m8407h(c);
        }
    }

    private ad m11649c(BubbleBlower bubbleBlower) {
        GLMarker d = GLMarkerManager.m11650d(bubbleBlower);
        if (d == null) {
            return null;
        }
        String m = d.m8076m();
        this.f8077f.m8774a();
        return (ad) this.f8072a.get(m);
    }

    private static GLMarker m11650d(BubbleBlower bubbleBlower) {
        if (bubbleBlower instanceof GLMarker) {
            return (GLMarker) bubbleBlower;
        }
        return null;
    }

    public final List<ad> m11664d() {
        return this.f8075d.m6839j();
    }

    public final MarkerImpl m11652a(ad adVar) {
        return new GLMarkerRenderer(adVar, this);
    }
}
