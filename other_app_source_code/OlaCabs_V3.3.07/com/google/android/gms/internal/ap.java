package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.C0206g;
import com.google.android.gms.common.api.C0208e;
import com.google.android.gms.common.api.C0218b.C0215b;
import com.google.android.gms.common.api.C0226d;
import com.google.android.gms.common.api.C0234j.C0231b;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.C0485i;
import com.google.android.gms.location.C0532g.C0464a;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

public class ap implements C0485i {

    /* renamed from: com.google.android.gms.internal.ap.1 */
    class C04841 extends C0464a<LocationSettingsResult> {
        final /* synthetic */ LocationSettingsRequest f2375b;
        final /* synthetic */ String f2376c;
        final /* synthetic */ ap f2377d;

        C04841(ap apVar, C0226d c0226d, LocationSettingsRequest locationSettingsRequest, String str) {
            this.f2377d = apVar;
            this.f2375b = locationSettingsRequest;
            this.f2376c = str;
            super(c0226d);
        }

        public /* synthetic */ C0206g m4014a(Status status) {
            return m4017c(status);
        }

        protected void m4015a(ak akVar) throws RemoteException {
            akVar.m4001a(this.f2375b, (C0231b) this, this.f2376c);
        }

        protected /* synthetic */ void m4016b(C0215b c0215b) throws RemoteException {
            m4015a((ak) c0215b);
        }

        public LocationSettingsResult m4017c(Status status) {
            return new LocationSettingsResult(status);
        }
    }

    public C0208e<LocationSettingsResult> m4019a(C0226d c0226d, LocationSettingsRequest locationSettingsRequest) {
        return m4020a(c0226d, locationSettingsRequest, null);
    }

    public C0208e<LocationSettingsResult> m4020a(C0226d c0226d, LocationSettingsRequest locationSettingsRequest, String str) {
        return c0226d.m3222a(new C04841(this, c0226d, locationSettingsRequest, str));
    }
}
