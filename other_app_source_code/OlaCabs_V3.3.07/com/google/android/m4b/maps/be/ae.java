package com.google.android.m4b.maps.be;

import android.graphics.Point;
import android.os.RemoteException;
import android.view.View;
import com.google.android.m4b.maps.be.ad.MarkerImpl;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.IInfoWindowRenderer;
import com.google.android.m4b.maps.p042r.al;
import com.google.android.m4b.maps.p042r.am;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.au;
import java.util.List;
import java.util.Map;

/* compiled from: MarkerManagerImpl */
public class ae {
    private int f5583a;
    private final Map<ad, MarkerImpl> f5584b;
    private final bl f5585c;
    private final bj f5586d;
    private final be f5587e;
    private final MarkerManagerImpl f5588f;
    private final ba f5589g;
    private al f5590h;
    private am f5591i;
    private com.google.android.m4b.maps.p042r.ae f5592j;
    private IInfoWindowRenderer f5593k;
    private MapToolbar f5594l;
    private MapRenderer f5595m;
    private boolean f5596n;

    /* renamed from: com.google.android.m4b.maps.be.ae.a */
    public interface MarkerManagerImpl {
        MarkerImpl m8391a(ad adVar);

        List<ad> m8392d();
    }

    static {
        ae.class.getSimpleName();
    }

    public ae(MarkerManagerImpl markerManagerImpl, IInfoWindowRenderer iInfoWindowRenderer, bl blVar, bj bjVar, ba baVar, be beVar, MapToolbar mapToolbar, MapRenderer mapRenderer, boolean z) {
        this.f5583a = 0;
        this.f5584b = au.m2818c();
        this.f5588f = markerManagerImpl;
        this.f5593k = iInfoWindowRenderer;
        this.f5585c = blVar;
        this.f5586d = bjVar;
        this.f5589g = baVar;
        this.f5587e = beVar;
        this.f5594l = mapToolbar;
        this.f5595m = mapRenderer;
        this.f5596n = z;
    }

    public final ad m8393a(MarkerOptions markerOptions) {
        this.f5589g.m8774a();
        Preconditions.m1823a(markerOptions.m10762c() != null, (Object) "no position in marker options");
        ad adVar = new ad(String.format("m%d", new Object[]{Integer.valueOf(this.f5583a)}), markerOptions, this, this.f5585c, this.f5586d, this.f5589g, this.f5587e);
        this.f5583a++;
        MarkerImpl a = this.f5588f.m8391a(adVar);
        adVar.m8353a(a);
        a.m8305a();
        this.f5584b.put(adVar, a);
        return adVar;
    }

    public final void m8395a(ad adVar) {
        this.f5594l.m9509a(adVar);
        MarkerImpl markerImpl = (MarkerImpl) this.f5584b.get(adVar);
        if (markerImpl != null) {
            markerImpl.m8307b();
            this.f5584b.remove(adVar);
        }
    }

    public final void m8394a() {
        this.f5589g.m8774a();
        for (ad c : this.f5584b.keySet()) {
            c.m8366c();
        }
        this.f5584b.clear();
    }

    public final void m8397a(al alVar) {
        this.f5589g.m8774a();
        this.f5590h = alVar;
    }

    public final void m8398a(am amVar) {
        this.f5589g.m8774a();
        this.f5591i = amVar;
    }

    public final void m8396a(com.google.android.m4b.maps.p042r.ae aeVar) {
        this.f5589g.m8774a();
        this.f5592j = aeVar;
    }

    public final boolean m8401b(ad adVar) {
        return ((MarkerImpl) this.f5584b.get(adVar)).m8311f();
    }

    final MarkerImpl m8402c(ad adVar) {
        MarkerImpl markerImpl = (MarkerImpl) this.f5584b.get(adVar);
        if (markerImpl == null) {
            return null;
        }
        return markerImpl;
    }

    public final void m8403d(ad adVar) {
        if (this.f5591i != null) {
            try {
                ((MarkerImpl) this.f5584b.get(adVar)).m8310e();
                this.f5591i.m11110a(adVar);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public final void m8404e(ad adVar) {
        if (this.f5591i != null) {
            try {
                ((MarkerImpl) this.f5584b.get(adVar)).m8310e();
                this.f5591i.m11111b(adVar);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public final void m8405f(ad adVar) {
        if (this.f5591i != null) {
            try {
                ((MarkerImpl) this.f5584b.get(adVar)).m8310e();
                this.f5591i.m11112c(adVar);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public final boolean m8406g(ad adVar) {
        try {
            if (this.f5590h == null) {
                this.f5587e.m8835b(UsageLog.MARKER_CLICK_WITHOUT_LISTENER);
            } else if (this.f5590h.m10058a(adVar)) {
                this.f5587e.m8835b(UsageLog.MARKER_CLICK_WITH_INTERRUPTING_LISTENER);
                return true;
            } else {
                this.f5587e.m8835b(UsageLog.MARKER_CLICK_WITH_LISTENER);
            }
            adVar.m8385u();
            if (!this.f5596n) {
                boolean z;
                if (this.f5588f.m8392d().size() > 1) {
                    z = true;
                } else {
                    z = false;
                }
                this.f5594l.m9512a(true, true, adVar, z);
            }
            return false;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m8407h(ad adVar) {
        if (this.f5592j == null) {
            this.f5587e.m8835b(UsageLog.MARKER_INFO_WINDOW_CLICK_WITHOUT_LISTENER);
            return;
        }
        try {
            this.f5587e.m8835b(UsageLog.MARKER_INFO_WINDOW_CLICK_WITH_LISTENER);
            this.f5592j.m11095a(adVar);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m8399a(IInfoWindowRenderer iInfoWindowRenderer) {
        this.f5593k = (IInfoWindowRenderer) Preconditions.m1817a((Object) iInfoWindowRenderer);
    }

    public final IInfoWindowRenderer m8400b() {
        return this.f5593k;
    }

    public final boolean m8408i(ad adVar) {
        Point a = this.f5595m.m9502r().m8550a(adVar.m8368d());
        View f = this.f5595m.m9487f();
        return a.x >= 0 && a.x < f.getWidth() && a.y >= 0 && a.y < f.getHeight();
    }
}
