package com.google.android.m4b.maps.p044k;

import android.content.Context;
import android.os.Looper;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.p043e.Api;
import com.google.android.m4b.maps.p043e.BaseImplementation.BaseImplementation;
import com.google.android.m4b.maps.p043e.GoogleApiClient.GoogleApiClient;
import com.google.android.m4b.maps.p043e.Result;
import com.google.android.m4b.maps.p043e.Scope;
import com.google.android.m4b.maps.p047g.ClientSettings;
import com.google.android.m4b.maps.p051l.FusedLocationProviderApiImpl;
import com.google.android.m4b.maps.p051l.LocationClientImpl;

/* renamed from: com.google.android.m4b.maps.k.g */
public final class LocationServices {
    public static final Api f7456a;
    public static FusedLocationProviderApi f7457b;
    private static final Api.Api<LocationClientImpl> f7458c;
    private static final Api.Api f7459d;

    /* renamed from: com.google.android.m4b.maps.k.g.1 */
    static class LocationServices implements Api.Api {
        LocationServices() {
        }

        public final /* synthetic */ Api.Api m10556a(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient googleApiClient, GooglePlayServicesClient googlePlayServicesClient) {
            return new LocationClientImpl(context, looper, googleApiClient, googlePlayServicesClient, "locationServices");
        }
    }

    /* renamed from: com.google.android.m4b.maps.k.g.a */
    public static abstract class LocationServices<R extends Result> extends BaseImplementation<R, LocationClientImpl> {
        public LocationServices() {
            super(LocationServices.f7458c);
        }
    }

    static {
        f7458c = new Api.Api();
        f7459d = new LocationServices();
        f7456a = new Api(f7459d, f7458c, new Scope[0]);
        f7457b = new FusedLocationProviderApiImpl();
        GeofencingApi geofencingApi = new GeofencingApi();
    }
}
