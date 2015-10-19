package com.google.android.m4b.maps;

import android.os.RemoteException;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.Marker;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.google.android.m4b.maps.p042r.IGoogleMapDelegate;
import com.google.android.m4b.maps.p042r.ac.IOnCameraChangeListener;
import com.google.android.m4b.maps.p042r.al.IOnMarkerClickListener;
import com.google.android.m4b.maps.p047g.Preconditions;

/* renamed from: com.google.android.m4b.maps.c */
public class GoogleMap {
    private final IGoogleMapDelegate f7226a;
    private UiSettings f7227b;

    /* renamed from: com.google.android.m4b.maps.c.1 */
    class GoogleMap extends IOnMarkerClickListener {
        private /* synthetic */ GoogleMap f7223a;

        GoogleMap(GoogleMap googleMap, GoogleMap googleMap2) {
            this.f7223a = googleMap2;
        }

        public final boolean m10059a(IMarkerDelegate iMarkerDelegate) {
            return this.f7223a.m10062a(new Marker(iMarkerDelegate));
        }
    }

    /* renamed from: com.google.android.m4b.maps.c.2 */
    class GoogleMap extends IOnCameraChangeListener {
        private /* synthetic */ GoogleMap f7224a;

        GoogleMap(GoogleMap googleMap, GoogleMap googleMap2) {
            this.f7224a = googleMap2;
        }

        public final void m10060a(CameraPosition cameraPosition) {
            this.f7224a.m10061a(cameraPosition);
        }
    }

    /* renamed from: com.google.android.m4b.maps.c.a */
    public interface GoogleMap {
        void m10061a(CameraPosition cameraPosition);
    }

    /* renamed from: com.google.android.m4b.maps.c.b */
    public interface GoogleMap {
        boolean m10062a(Marker marker);
    }

    protected GoogleMap(IGoogleMapDelegate iGoogleMapDelegate) {
        this.f7226a = (IGoogleMapDelegate) Preconditions.m10459a((Object) iGoogleMapDelegate);
    }

    final IGoogleMapDelegate m10069a() {
        return this.f7226a;
    }

    public final float m10075b() {
        try {
            return this.f7226a.m9164f();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10071a(CameraUpdate cameraUpdate) {
        try {
            this.f7226a.m9155b(cameraUpdate.m4740a());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Marker m10068a(MarkerOptions markerOptions) {
        try {
            IMarkerDelegate a = this.f7226a.m9120a(markerOptions);
            if (a != null) {
                return new Marker(a);
            }
            return null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10076c() {
        try {
            this.f7226a.m9173q();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10074a(boolean z) {
        try {
            this.f7226a.m9163e(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings m10077d() {
        try {
            if (this.f7227b == null) {
                this.f7227b = new UiSettings(this.f7226a.m9175s());
            }
            return this.f7227b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10072a(GoogleMap googleMap) {
        if (googleMap == null) {
            try {
                this.f7226a.m9136a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f7226a.m9136a(new GoogleMap(this, googleMap));
    }

    public final void m10073a(GoogleMap googleMap) {
        if (googleMap == null) {
            try {
                this.f7226a.m9144a(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.f7226a.m9144a(new GoogleMap(this, googleMap));
    }

    public final void m10070a(int i, int i2, int i3, int i4) {
        try {
            this.f7226a.m9127a(i, i2, i3, i4);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
