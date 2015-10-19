package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.common.api.C0234j.C0231b;
import com.google.android.gms.common.internal.C0453u;
import com.google.android.gms.internal.ai.C0475a;
import com.google.android.gms.location.C0530f;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

public class ak extends ac {
    private final aj f2373e;
    private final aa f2374f;

    /* renamed from: com.google.android.gms.internal.ak.a */
    private static final class C0483a extends C0475a {
        private C0231b<LocationSettingsResult> f2372a;

        public C0483a(C0231b<LocationSettingsResult> c0231b) {
            C0453u.m3855b(c0231b != null, "listener can't be null.");
            this.f2372a = c0231b;
        }

        public void m3999a(LocationSettingsResult locationSettingsResult) throws RemoteException {
            this.f2372a.m3239a(locationSettingsResult);
            this.f2372a = null;
        }
    }

    public ak(Context context, Looper looper, String str, C0221b c0221b, C0222c c0222c, String str2, String str3) {
        this(context, looper, str, c0221b, c0222c, str2, str3, null);
    }

    public ak(Context context, Looper looper, String str, C0221b c0221b, C0222c c0222c, String str2, String str3, String str4) {
        super(context, looper, c0221b, c0222c, str2);
        this.f2373e = new aj(context, this.d);
        this.f2374f = aa.m3897a(context, str3, str4, this.d);
    }

    public void m4000a(LocationRequest locationRequest, C0530f c0530f, Looper looper) throws RemoteException {
        synchronized (this.f2373e) {
            this.f2373e.m3994a(locationRequest, c0530f, looper);
        }
    }

    public void m4001a(LocationSettingsRequest locationSettingsRequest, C0231b<LocationSettingsResult> c0231b, String str) throws RemoteException {
        boolean z = true;
        m3640l();
        C0453u.m3855b(locationSettingsRequest != null, "locationSettingsRequest can't be null nor empty.");
        if (c0231b == null) {
            z = false;
        }
        C0453u.m3855b(z, "listener can't be null.");
        ((ah) m3641m()).m3947a(locationSettingsRequest, new C0483a(c0231b), str);
    }

    public void m4002a(C0530f c0530f) throws RemoteException {
        this.f2373e.m3995a(c0530f);
    }

    public void m4003b() {
        synchronized (this.f2373e) {
            if (m3631c()) {
                try {
                    this.f2373e.m3997b();
                    this.f2373e.m3998c();
                } catch (Throwable e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.m3629b();
        }
    }

    public Location m4004p() {
        return this.f2373e.m3993a();
    }
}
