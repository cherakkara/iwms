package com.google.android.gms.internal;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.C0206g;
import com.google.android.gms.common.api.C0208e;
import com.google.android.gms.common.api.C0218b.C0215b;
import com.google.android.gms.common.api.C0226d;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.C0468c;
import com.google.android.gms.location.C0530f;
import com.google.android.gms.location.C0532g;
import com.google.android.gms.location.C0532g.C0464a;
import com.google.android.gms.location.LocationRequest;

public class ae implements C0468c {

    /* renamed from: com.google.android.gms.internal.ae.a */
    private static abstract class C0465a extends C0464a<Status> {
        public C0465a(C0226d c0226d) {
            super(c0226d);
        }

        public /* synthetic */ C0206g m3912a(Status status) {
            return m3913c(status);
        }

        public Status m3913c(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.internal.ae.1 */
    class C04661 extends C0465a {
        final /* synthetic */ LocationRequest f2355b;
        final /* synthetic */ C0530f f2356c;
        final /* synthetic */ ae f2357d;

        C04661(ae aeVar, C0226d c0226d, LocationRequest locationRequest, C0530f c0530f) {
            this.f2357d = aeVar;
            this.f2355b = locationRequest;
            this.f2356c = c0530f;
            super(c0226d);
        }

        protected void m3914a(ak akVar) throws RemoteException {
            akVar.m4000a(this.f2355b, this.f2356c, null);
            m3185a(Status.f1987a);
        }

        protected /* synthetic */ void m3915b(C0215b c0215b) throws RemoteException {
            m3914a((ak) c0215b);
        }
    }

    /* renamed from: com.google.android.gms.internal.ae.2 */
    class C04672 extends C0465a {
        final /* synthetic */ C0530f f2358b;
        final /* synthetic */ ae f2359c;

        C04672(ae aeVar, C0226d c0226d, C0530f c0530f) {
            this.f2359c = aeVar;
            this.f2358b = c0530f;
            super(c0226d);
        }

        protected void m3916a(ak akVar) throws RemoteException {
            akVar.m4002a(this.f2358b);
            m3185a(Status.f1987a);
        }

        protected /* synthetic */ void m3917b(C0215b c0215b) throws RemoteException {
            m3916a((ak) c0215b);
        }
    }

    public Location m3921a(C0226d c0226d) {
        try {
            return C0532g.m4364a(c0226d).m4004p();
        } catch (Exception e) {
            return null;
        }
    }

    public C0208e<Status> m3922a(C0226d c0226d, LocationRequest locationRequest, C0530f c0530f) {
        return c0226d.m3226b(new C04661(this, c0226d, locationRequest, c0530f));
    }

    public C0208e<Status> m3923a(C0226d c0226d, C0530f c0530f) {
        return c0226d.m3226b(new C04672(this, c0226d, c0530f));
    }
}
