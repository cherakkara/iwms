package com.google.android.m4b.maps.p047g;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.m4b.maps.ca.ConnectionResult;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.p043e.GoogleApiClient.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.google.android.m4b.maps.g.d */
public final class GmsClientEventManager {
    private final GmsClientEventManager f7403a;
    private final ArrayList<GoogleApiClient> f7404b;
    private ArrayList<GoogleApiClient> f7405c;
    private boolean f7406d;
    private final ArrayList<GooglePlayServicesClient> f7407e;
    private final Handler f7408f;

    /* renamed from: com.google.android.m4b.maps.g.d.b */
    public interface GmsClientEventManager {
        boolean m10271c();

        boolean k_();
    }

    /* renamed from: com.google.android.m4b.maps.g.d.a */
    final class GmsClientEventManager extends Handler {
        private /* synthetic */ GmsClientEventManager f7402a;

        public GmsClientEventManager(GmsClientEventManager gmsClientEventManager, Looper looper) {
            this.f7402a = gmsClientEventManager;
            super(looper);
        }

        public final void handleMessage(Message message) {
            if (message.what == 1) {
                synchronized (this.f7402a.f7404b) {
                    if (this.f7402a.f7403a.k_() && this.f7402a.f7403a.m10271c() && this.f7402a.f7404b.contains(message.obj)) {
                        this.f7402a.f7403a;
                        ((GoogleApiClient) message.obj).m9432a(null);
                    }
                }
                return;
            }
            Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        }
    }

    public GmsClientEventManager(Looper looper, GmsClientEventManager gmsClientEventManager) {
        this.f7404b = new ArrayList();
        this.f7405c = new ArrayList();
        this.f7406d = false;
        this.f7407e = new ArrayList();
        this.f7403a = gmsClientEventManager;
        this.f7408f = new GmsClientEventManager(this, looper);
    }

    protected final void m10355a() {
        synchronized (this.f7404b) {
            GmsClientEventManager gmsClientEventManager = this.f7403a;
            m10357a(null);
        }
    }

    public final void m10357a(Bundle bundle) {
        boolean z = true;
        synchronized (this.f7404b) {
            Preconditions.m10462a(!this.f7406d);
            this.f7408f.removeMessages(1);
            this.f7406d = true;
            if (this.f7405c.size() != 0) {
                z = false;
            }
            Preconditions.m10462a(z);
            Iterator it = new ArrayList(this.f7404b).iterator();
            while (it.hasNext()) {
                GoogleApiClient googleApiClient = (GoogleApiClient) it.next();
                if (this.f7403a.k_() && this.f7403a.m10271c()) {
                    if (!this.f7405c.contains(googleApiClient)) {
                        googleApiClient.m9432a(bundle);
                    }
                }
            }
            this.f7405c.clear();
            this.f7406d = false;
        }
    }

    public final void m10356a(int i) {
        this.f7408f.removeMessages(1);
        synchronized (this.f7404b) {
            this.f7406d = true;
            Iterator it = new ArrayList(this.f7404b).iterator();
            while (it.hasNext()) {
                GoogleApiClient googleApiClient = (GoogleApiClient) it.next();
                if (!this.f7403a.k_()) {
                    break;
                } else if (this.f7404b.contains(googleApiClient)) {
                    googleApiClient.m9431a(i);
                }
            }
            this.f7406d = false;
        }
    }

    public final void m10358a(ConnectionResult connectionResult) {
        this.f7408f.removeMessages(1);
        synchronized (this.f7407e) {
            Iterator it = new ArrayList(this.f7407e).iterator();
            while (it.hasNext()) {
                GooglePlayServicesClient googlePlayServicesClient = (GooglePlayServicesClient) it.next();
                if (!this.f7403a.k_()) {
                    return;
                } else if (this.f7407e.contains(googlePlayServicesClient)) {
                    googlePlayServicesClient.m9430a(connectionResult);
                }
            }
        }
    }

    public final void m10360a(GoogleApiClient googleApiClient) {
        Preconditions.m10459a((Object) googleApiClient);
        synchronized (this.f7404b) {
            if (this.f7404b.contains(googleApiClient)) {
                new StringBuilder("registerConnectionCallbacks(): listener ").append(googleApiClient).append(" is already registered");
            } else {
                this.f7404b.add(googleApiClient);
            }
        }
        if (this.f7403a.m10271c()) {
            this.f7408f.sendMessage(this.f7408f.obtainMessage(1, googleApiClient));
        }
    }

    public final void m10359a(GooglePlayServicesClient googlePlayServicesClient) {
        Preconditions.m10459a((Object) googlePlayServicesClient);
        synchronized (this.f7407e) {
            if (this.f7407e.contains(googlePlayServicesClient)) {
                new StringBuilder("registerConnectionFailedListener(): listener ").append(googlePlayServicesClient).append(" is already registered");
            } else {
                this.f7407e.add(googlePlayServicesClient);
            }
        }
    }
}
