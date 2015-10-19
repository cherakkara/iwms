package com.google.android.m4b.maps.p051l;

import com.google.android.m4b.maps.p043e.Api.Api;
import com.google.android.m4b.maps.p043e.GoogleApiClient;
import com.google.android.m4b.maps.p043e.PendingResult;
import com.google.android.m4b.maps.p043e.Result;
import com.google.android.m4b.maps.p043e.Status;
import com.google.android.m4b.maps.p044k.FusedLocationProviderApi;
import com.google.android.m4b.maps.p044k.LocationListener;
import com.google.android.m4b.maps.p044k.LocationRequest;
import com.google.android.m4b.maps.p044k.LocationServices.LocationServices;

/* renamed from: com.google.android.m4b.maps.l.a */
public final class FusedLocationProviderApiImpl implements FusedLocationProviderApi {

    /* renamed from: com.google.android.m4b.maps.l.a.a */
    static abstract class FusedLocationProviderApiImpl extends LocationServices<Status> {
        private FusedLocationProviderApiImpl() {
        }

        public final /* bridge */ /* synthetic */ Result m10570a(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.m4b.maps.l.a.1 */
    class FusedLocationProviderApiImpl extends FusedLocationProviderApiImpl {
        private /* synthetic */ LocationRequest f7465a;
        private /* synthetic */ LocationListener f7466b;

        FusedLocationProviderApiImpl(FusedLocationProviderApiImpl fusedLocationProviderApiImpl, LocationRequest locationRequest, LocationListener locationListener) {
            this.f7465a = locationRequest;
            this.f7466b = locationListener;
            super();
        }

        protected final /* synthetic */ void m10571b(Api api) {
            ((LocationClientImpl) api).m10656a(this.f7465a, this.f7466b, null);
            m10249a(Status.f7358a);
        }
    }

    public final PendingResult<Status> m10572a(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
        return googleApiClient.m10266a(new FusedLocationProviderApiImpl(this, locationRequest, locationListener));
    }
}
