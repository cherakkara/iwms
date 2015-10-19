package com.google.android.m4b.maps.p051l;

import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.ca.GooglePlayServicesUtil;
import com.google.android.m4b.maps.p043e.GoogleApiClient.GoogleApiClient;
import com.google.android.m4b.maps.p044k.LocationListener;
import com.google.android.m4b.maps.p044k.LocationRequest;
import com.google.android.m4b.maps.p047g.GmsClient;
import com.google.android.m4b.maps.p047g.IGmsServiceBroker;
import com.google.android.m4b.maps.p053n.PlacesClientImpl;

/* renamed from: com.google.android.m4b.maps.l.e */
public final class LocationClientImpl extends GmsClient<IGoogleLocationManagerService> {
    private final ServiceProvider<IGoogleLocationManagerService> f7476b;
    private final LocationClientHelper f7477c;
    private final String f7478d;

    /* renamed from: com.google.android.m4b.maps.l.e.a */
    final class LocationClientImpl implements ServiceProvider<IGoogleLocationManagerService> {
        private /* synthetic */ LocationClientImpl f7475a;

        private LocationClientImpl(LocationClientImpl locationClientImpl) {
            this.f7475a = locationClientImpl;
        }

        public final /* synthetic */ IInterface m10652b() {
            return (IGoogleLocationManagerService) this.f7475a.m10352j();
        }

        public final void m10651a() {
            this.f7475a.m10351i();
        }
    }

    public LocationClientImpl(Context context, Looper looper, GoogleApiClient googleApiClient, GooglePlayServicesClient googlePlayServicesClient, String str) {
        this(context, looper, context.getPackageName(), googleApiClient, googlePlayServicesClient, str, null);
    }

    private LocationClientImpl(Context context, Looper looper, String str, GoogleApiClient googleApiClient, GooglePlayServicesClient googlePlayServicesClient, String str2, String str3) {
        super(context, looper, googleApiClient, googlePlayServicesClient, new String[0]);
        this.f7476b = new LocationClientImpl();
        this.f7477c = new LocationClientHelper(context, this.f7476b);
        this.f7478d = str2;
        PlacesClientImpl placesClientImpl = new PlacesClientImpl(str, this.f7476b);
        ServiceProvider serviceProvider = this.f7476b;
    }

    public final void m10657b() {
        synchronized (this.f7477c) {
            if (m10345c()) {
                this.f7477c.m10646a();
                this.f7477c.m10648b();
            }
            super.m10343b();
        }
    }

    protected final String m10658e() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    protected final String m10659f() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    public final void m10656a(LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        synchronized (this.f7477c) {
            this.f7477c.m10647a(locationRequest, locationListener, null);
        }
    }

    protected final void m10655a(IGmsServiceBroker iGmsServiceBroker, GmsClient.GmsClient gmsClient) {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.f7478d);
        iGmsServiceBroker.m10403e(gmsClient, GooglePlayServicesUtil.f7231a, m10350h().getPackageName(), bundle);
    }
}
