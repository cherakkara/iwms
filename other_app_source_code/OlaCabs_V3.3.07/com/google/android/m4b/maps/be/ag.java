package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.be.ap.ReverseGeocodeDataRequest;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p042r.ILocationSourceDelegate;
import com.google.android.m4b.maps.p042r.af.IOnLocationChangeListener;
import com.google.android.m4b.maps.p042r.ao;
import com.google.android.m4b.maps.p042r.ap;
import com.google.p025a.p026a.Preconditions;

/* compiled from: MyLocationLayerImpl */
public final class ag extends IOnLocationChangeListener implements OnClickListener {
    private final bp f5600a;
    private final ILocationSourceDelegate f5601b;
    private final af f5602c;
    private final Context f5603d;
    private final Resources f5604e;
    private final MyLocationLayerImpl f5605f;
    private Location f5606g;
    private ILocationSourceDelegate f5607h;
    private final be f5608i;
    private boolean f5609j;
    private boolean f5610k;
    private ap f5611l;
    private ao f5612m;
    private boolean f5613n;

    /* renamed from: com.google.android.m4b.maps.be.ag.1 */
    class MyLocationLayerImpl implements ReverseGeocodeDataRequest {
        private /* synthetic */ View f5598a;
        private /* synthetic */ ag f5599b;

        MyLocationLayerImpl(ag agVar, View view) {
            this.f5599b = agVar;
            this.f5598a = view;
        }

        public final void m8413a(ap apVar) {
            if (apVar.m8589j() > 0) {
                ab.m8296a(this.f5598a, this.f5599b.f5604e.getString(R.YOUR_LOCATION) + ": " + apVar.m8583a(0).m8579a());
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.ag.a */
    public interface MyLocationLayerImpl {
        void m8414a();

        void m8415a(Location location);

        void m8416b();
    }

    public ag(Context context, Resources resources, bp bpVar, af afVar, MyLocationLayerImpl myLocationLayerImpl, ILocationSourceDelegate iLocationSourceDelegate, be beVar, boolean z) {
        this.f5603d = (Context) Preconditions.m1817a((Object) context);
        this.f5600a = (bp) Preconditions.m1817a((Object) bpVar);
        this.f5602c = (af) Preconditions.m1817a((Object) afVar);
        this.f5605f = (MyLocationLayerImpl) Preconditions.m1817a((Object) myLocationLayerImpl);
        this.f5601b = (ILocationSourceDelegate) Preconditions.m1817a((Object) iLocationSourceDelegate);
        this.f5607h = (ILocationSourceDelegate) Preconditions.m1817a((Object) iLocationSourceDelegate);
        this.f5608i = (be) Preconditions.m1817a((Object) beVar);
        this.f5604e = (Resources) Preconditions.m1817a((Object) resources);
        this.f5610k = true;
        this.f5613n = z;
    }

    public final void m8422a() {
        if (!this.f5609j) {
            this.f5609j = true;
            this.f5605f.m8414a();
            try {
                this.f5607h.m9426a(this);
                m8421f();
                if (this.f5606g != null) {
                    m8423a(this.f5606g);
                }
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public final void m8429b() {
        if (this.f5609j) {
            this.f5609j = false;
            m8421f();
            try {
                this.f5607h.m9427c();
                this.f5605f.m8416b();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public final boolean m8430c() {
        return this.f5609j;
    }

    public final void m8428a(boolean z) {
        if (this.f5610k != z) {
            this.f5610k = z;
            m8421f();
        }
    }

    private void m8421f() {
        OnClickListener onClickListener;
        boolean z = this.f5610k && this.f5609j;
        this.f5602c.m8411a(z);
        af afVar = this.f5602c;
        if (!z) {
            onClickListener = null;
        }
        afVar.m8410a(onClickListener);
    }

    public final boolean m8431d() {
        return this.f5610k;
    }

    public final void m8424a(IObjectWrapper iObjectWrapper) {
        m8423a((Location) ObjectWrapper.m10131a(iObjectWrapper));
    }

    public final void m8423a(Location location) {
        this.f5605f.m8415a(location);
        if (this.f5611l != null) {
            try {
                Object location2 = new Location(location);
                if (this.f5613n) {
                    this.f5611l.m11120a((Location) location2);
                } else {
                    this.f5611l.m11121a(ObjectWrapper.m10130a(location2));
                }
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f5606g = location;
    }

    @Deprecated
    public final Location m8432e() {
        Preconditions.m1829b(this.f5609j, (Object) "MyLocation layer not enabled");
        return this.f5606g;
    }

    public final void m8427a(ILocationSourceDelegate iLocationSourceDelegate) {
        if (this.f5609j) {
            try {
                this.f5607h.m9427c();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        if (iLocationSourceDelegate == null) {
            iLocationSourceDelegate = this.f5601b;
        }
        this.f5607h = iLocationSourceDelegate;
        if (this.f5609j) {
            try {
                this.f5607h.m9426a(this);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        }
    }

    @Deprecated
    public final void m8426a(ap apVar) {
        this.f5611l = apVar;
    }

    public final void m8425a(ao aoVar) {
        this.f5612m = aoVar;
    }

    public final void onClick(View view) {
        this.f5608i.m8833a(UsageLog.MY_LOCATION_BUTTON_CLICK);
        if (this.f5612m != null) {
            try {
                if (this.f5612m.m11118a()) {
                    return;
                }
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        Preconditions.m1829b(this.f5609j, (Object) "MyLocation layer not enabled");
        if (this.f5606g != null) {
            LatLng latLng = new LatLng(this.f5606g.getLatitude(), this.f5606g.getLongitude());
            this.f5600a.m8937a(CameraPosition.m10708a(this.f5600a.m8945c()).m10704a(latLng).m10703a(m8419a(latLng, this.f5606g.getAccuracy())).m10705a(), -1);
        }
        if (ab.m8297a(this.f5603d) && this.f5606g != null) {
            latLng = new LatLng(this.f5606g.getLatitude(), this.f5606g.getLongitude());
            DataRequest apVar = new ap(latLng, m8419a(latLng, this.f5606g.getAccuracy()));
            apVar.m8584a(new MyLocationLayerImpl(this, view));
            DataRequestDispatcher.m11393a().m11451c(apVar);
        }
    }

    private float m8419a(LatLng latLng, float f) {
        float f2 = this.f5600a.m8945c().f7530b;
        if (f2 <= 10.0f) {
            f2 = 15.0f;
        }
        bp bpVar = this.f5600a;
        double d = (double) f;
        double a = bw.m9061a(d);
        d = bw.m9063a(latLng, d);
        float f3 = bpVar.m8930a(new LatLngBounds(new LatLng(latLng.f7554a - a, latLng.f7555b - d), new LatLng(latLng.f7554a + a, latLng.f7555b + d))).f7530b;
        return f3 == -1.0f ? f2 : Math.min(f2, f3);
    }
}
