package com.google.android.m4b.maps.be;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.m4b.maps.ca.ConnectionResult;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.ILocationSourceDelegate.ILocationSourceDelegate;
import com.google.android.m4b.maps.p042r.af;
import com.google.android.m4b.maps.p043e.GoogleApiClient.GoogleApiClient;
import com.google.android.m4b.maps.p044k.LocationListener;
import com.google.android.m4b.maps.p044k.LocationRequest;
import com.google.android.m4b.maps.p044k.LocationServices;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.be.n */
public final class LocationSourceImpl extends ILocationSourceDelegate implements LocationSourceComponent, GooglePlayServicesClient, GoogleApiClient, LocationListener, Runnable {
    private static final LocationRequest f6050f;
    private final Handler f6051a;
    private boolean f6052b;
    private af f6053c;
    private com.google.android.m4b.maps.p043e.GoogleApiClient f6054d;
    private Location f6055e;

    static {
        f6050f = LocationRequest.m10548a().m10551a(5000).m10553b(16).m10550a(100);
    }

    private LocationSourceImpl(Handler handler) {
        this.f6051a = handler;
    }

    public static LocationSourceImpl m9434a(Context context) {
        GooglePlayServicesClient locationSourceImpl = new LocationSourceImpl(new Handler(Looper.getMainLooper()));
        locationSourceImpl.f6054d = new GoogleApiClient(context.getApplicationContext()).m10263a(LocationServices.f7456a).m10264a((GoogleApiClient) locationSourceImpl).m10262a(locationSourceImpl).m10265a();
        return locationSourceImpl;
    }

    private void m9435d() {
        this.f6054d.m10267a();
    }

    private void m9436e() {
        this.f6054d.m10268b();
        this.f6051a.removeCallbacks(this);
        this.f6055e = null;
    }

    public final void m9440a(Bundle bundle) {
        LocationServices.f7457b.m10544a(this.f6054d, f6050f, this);
    }

    public final void m9438a(int i) {
    }

    public final void m9437a() {
        this.f6052b = true;
        if (this.f6053c != null) {
            m9435d();
        }
    }

    public final void m9443b() {
        if (this.f6053c != null) {
            m9436e();
        }
        this.f6052b = false;
    }

    public final void m9442a(af afVar) {
        boolean z = true;
        Preconditions.m1829b(this.f6053c == null, (Object) "already activated");
        if (afVar == null) {
            z = false;
        }
        Preconditions.m1823a(z, (Object) "listener cannot be null");
        this.f6053c = afVar;
        if (this.f6052b) {
            m9435d();
        }
    }

    public final void m9444c() {
        Preconditions.m1829b(this.f6053c != null, (Object) "already activated");
        this.f6053c = null;
        if (this.f6052b) {
            m9436e();
        }
    }

    public final void m9439a(Location location) {
        this.f6055e = location;
        this.f6051a.post(this);
    }

    public final void m9441a(ConnectionResult connectionResult) {
    }

    public final void run() {
        try {
            if (this.f6053c != null) {
                this.f6053c.m8418a(ObjectWrapper.m10130a(this.f6055e));
            }
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
